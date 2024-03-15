package com.cosapisoft.sarawebbranch.common.beans;
/**
 * Insert the type's description here.
 * Creation date: (3/7/01 5:21:20 PM)
 * @author: Administrator
 */
import java.io.*;
import java.net.*;
public class TCPIPSocket {
	Socket socket = null;
	DataOutputStream out = null;
	DataInputStream in = null;
	byte[] bBufferMessageFromHost;
//	private static String encoding = "CP037";
	
	private java.lang.String hostAddess;
	private int HostPortNumber;
	private int TimeOutSeconds;
	private byte[] MessageToHost;
	private byte[] MessageFromHost;
	private int StateOfLine;
	private String LuName = ""; 
	private static int HST_MAX_LEN = 32768;
	public final static int PROTOCOL_BYTES = 2;
	private int bytesLeidos = 0;
	
	byte bProtocol = 7; 
	byte bTypeDatos = 1;
	byte bTypeControlStatus = 2;
	byte bTypeControlCommand = 3;

	byte bControlReqEstatus = 1;

	byte bCommandOpenSession = 1;
	byte bCommandCloseSession = 2;

	public static TCPIPSocket mySocket;
	
	private static String sClaveParaClaves = ""; 
	
public TCPIPSocket() {

}
/**
	 * Insert the method's description here.
	 * Creation date: (3/19/01 3:45:24 PM)
	 * @param HostAddress java.lang.String
	 * @param HostPortNumber int
	 */
public TCPIPSocket(String HostAddress, String LuName, int HostPortNumber, int TimeOut) {
}

public boolean Initialize() {
/*	int bytesLeidos = 0;
	int intentos = 0;
	boolean recibioEstado = false;
	boolean recibioError = false;
*/
	try{	
		//Crea socket y variables para envio y recepcion de datos
		this.listenSocket();
		
		this.OpenSession();
		
		if (StateOfLine == 1)
			return true;
		else
			return false;
/*		//Se espera por el estatus de la sesión SNA
		bytesLeidos = 0;
		recibioEstado = false; 
		while (!recibioEstado && !recibioError){
			//Verifica si existe algún caracter por leer.
			if ((bytesLeidos = leeSocket()) != -1){
				//Verifica respuesta de DriverSNA respecto a estado de Línea
				if (!validateProtocol(new String(bufferMessageFromHost).getBytes(), bTypeControlStatus))
					return;
				//Se verifica que lo recibido sea un estado.
				if (bufferMessageFromHost[2] != (char)0){
					recibioEstado = true;
				}else{
					System.out.println("Estado Sesión SNA: Conectándose... " + getMessageFromHost());
				}
			}else{
				System.out.println("Erro: La lectura a DriverSNA ha vencido por timeout.");
				break;
			}
		}
		//Si se recibió error, para la aplicación es solo OffLine
		if (recibioError){
			StateOfLine = 0;
			return;
		}
		//Si recibió estado, verifica respuesta de DriverSNA respecto a estado de Línea
		if (bufferMessageFromHost[2] == (char)1){
			//OnLine.
			StateOfLine = 1;
		}else{
			//OffLine.
			StateOfLine = 0;
		}*/
	} catch (Exception e) {
		// System.out.println("Error al establecer comunicaciones con DriverSNA: " + e.toString());
		return false;
//////////Cerrar socket y vars por default... controlar el error...!!! 
//////////out.close();
	}
}

/**
 * Insert the method's description here.
 */
private void listenSocket() throws Exception {
	try {
		//Crea Socket con Dirección, Puerto y Timeout recibidos
		socket = new Socket(getHostAddess(), getHostPortNumber());
		socket.setSoTimeout(getTimeOutSeconds());

		//Crea buffers para enviar y recibir datos: Writer y Reader
		out = new DataOutputStream(socket.getOutputStream());
		in = new DataInputStream(socket.getInputStream());
		
	} catch (UnknownHostException e) {
		// System.out.println("listSocket: Host Desconocido o imposible de encontrar...");
		System.exit(1);
	} catch (IOException e) {
		// System.out.println("listSocket: " + e.toString());
		System.exit(1);
	}
}

/**
 * Insert the method's description here.
 */
private int leeSocket(){
	int bytesRecibidos = 0;
	int intentos = 0;
	
	try{
		//Se define el buffer máximo de 32KB.  
		byte[] tempMessageFromHost = new byte[HST_MAX_LEN];
		
		bytesRecibidos = in.available();
		while ((bytesRecibidos = in.read(tempMessageFromHost)) == -1) {
			// System.out.println("leeSocket: No hay datos para leer...");
			if (intentos != 3) {
				intentos++;
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
				}
			} else {
				bytesRecibidos = 0;
				return -1;
			}
		}
		ByteArrayOutputStream bArray = new ByteArrayOutputStream(bytesRecibidos); 
//		int bytes = in.available();
		bArray.write(tempMessageFromHost, 0, bytesRecibidos);
		bBufferMessageFromHost = bArray.toByteArray();
		
		return bytesRecibidos;
	} catch (Exception e) {
		// System.out.println("leeSocket: Error al leer datos desde DriverSNA: " + e.toString());
		return -1;
	}
}

/**
 * Insert the method's description here.
 */
public void OpenSession() throws Exception {
	boolean recibioError = false;
	boolean recibioEstado = false;
//	int bytesLeidos = 0;
	try{
		//Se Concatenan los bytes del protocolo con DriverSNA
		ByteArrayOutputStream bBytesToHost = new ByteArrayOutputStream (1);
		bBytesToHost.write(bProtocol);
		bBytesToHost.write(bTypeControlCommand);
		bBytesToHost.write(bCommandOpenSession);
		bBytesToHost.write(LuName.getBytes());
		//Se envían datos a DriverSNA
		out.write(bBytesToHost.toByteArray());
		out.flush();
		
		//Se espera por el estatus de la sesión SNA
		bytesLeidos = 0;
		recibioEstado = false; 
		while (!recibioEstado && !recibioError){
			//Verifica si existe algún caracter por leer.
			if ((bytesLeidos = leeSocket()) != -1){
				//Verifica respuesta de DriverSNA respecto a estado de Línea
				if (!validateProtocol(bBufferMessageFromHost, bTypeControlStatus))
					return;
				//Se verifica que lo recibido sea un estado.
				if (bBufferMessageFromHost[2] != 0){
					recibioEstado = true;
				}else{
					// System.out.println("Estado Sesión SNA: Conectándose... " + getMessageFromHost());
				}
			}else{
				// System.out.println("Erro: La lectura a DriverSNA ha vencido por timeout.");
				break;
			}
		}
		//Si se recibió error, para la aplicación es solo OffLine
		if (recibioError){
			StateOfLine = 0;
			return;
		}
		//Si recibió estado, verifica respuesta de DriverSNA respecto a estado de Línea
		if (bBufferMessageFromHost[2] == 1){
			//OnLine.
			StateOfLine = 1;
		}else{
			//OffLine.
			StateOfLine = 0;
		}
	} catch (Exception e) {
		// System.out.println("OpenSession: Error al abrir sesion SNA... " + e.toString());
	}
}

/**
 * Insert the method's description here.
 */
public boolean sendTxn() throws Exception {
boolean Rpta = false;

	try {
		//Se Concatenan los bytes del protocolo con DriverSNA
		ByteArrayOutputStream bBytesToHost = new ByteArrayOutputStream (1 + 1 + MessageToHost.length); //bProtocol + bTypeDatos + Datos...
		bBytesToHost.write(bProtocol);
		bBytesToHost.write(bTypeDatos);
		bBytesToHost.write(intToBytes(MessageToHost.length));

		bBytesToHost.write(MessageToHost);
		//Se envían datos a DriverSNA
		out.write(bBytesToHost.toByteArray());
		out.flush();

		bytesLeidos = 0;
		if ((bytesLeidos = leeSocket()) == -1){
			// System.out.println("sendTxn: La lectura del DriverSNA ha vencido por timeout.");
		}else{
			//Limpia buffer de respuesta.
			setMessageFromHost("".getBytes());
			
			//Verifica bytes de protocolo con DriverSNA
			byte bSNAStatus = bBufferMessageFromHost[1];
			//Si bSNAStatus == 0, se recibieron datos.
			if (bSNAStatus == 0){
				
				//Se cargan los bytes de Control a un byte[] para concatenar despues.
				ByteArrayOutputStream bMessageFromHost = new ByteArrayOutputStream (bBufferMessageFromHost.length - TCPIPSocket.PROTOCOL_BYTES);
				bMessageFromHost.write(bBufferMessageFromHost, 2, bBufferMessageFromHost.length - TCPIPSocket.PROTOCOL_BYTES);
				setMessageFromHost(bMessageFromHost.toByteArray());
				Rpta = true;
			}else{
				//Si bSNAStatus == 1, no hay conexion con el Host.
				if (bSNAStatus == 1){
					Rpta = false;
				}
			}
		}
		return Rpta;
	} catch (IOException e) {
		// System.out.println("sendTxn: Error en lectura " + e.toString());
		return false;
	}
}


public boolean enviaTxn() throws Exception {
	boolean Rpta = false;

		try {
			//Se Concatenan los bytes del protocolo con DriverSNA
			ByteArrayOutputStream bBytesToHost = new ByteArrayOutputStream (1 + 1 + MessageToHost.length); //bProtocol + bTypeDatos + Datos...
			bBytesToHost.write(bProtocol);
			bBytesToHost.write(bTypeDatos);
			bBytesToHost.write(intToBytes(MessageToHost.length));

			bBytesToHost.write(MessageToHost);
			//Se envían datos a DriverSNA
			out.write(bBytesToHost.toByteArray());
			out.flush();

			//Limpia buffer de respuesta.
			setMessageFromHost("".getBytes());
			
			//Se cargan los bytes de Control a un byte[] para concatenar despues.
			ByteArrayOutputStream bMessageFromHost = new ByteArrayOutputStream (bBufferMessageFromHost.length - TCPIPSocket.PROTOCOL_BYTES);
			bMessageFromHost.write(bBufferMessageFromHost, 2, bBufferMessageFromHost.length - TCPIPSocket.PROTOCOL_BYTES);
			setMessageFromHost(bMessageFromHost.toByteArray());
			
			Rpta = true;
			
			
			return Rpta;
		} catch (IOException e) {
			// System.out.println("sendTxn: Error en lectura " + e.toString());
			return false;
		}
	}

/**
 * Insert the method's description here.
 */
public int getStateOfLine(){
/*	byte[] messageToHost = {};
	int bytesLeidos = 0;
	boolean recibioEstado = false;
	boolean recibioError = false;
*/
	return StateOfLine;
	
/*	try{
		//Se Concatenan los bytes del protocolo con DriverSNA
		ByteArrayOutputStream bBytesToHost = new ByteArrayOutputStream (1 + 1 + 1); //bProtocol + bTypeControlStatus + '1'
		bBytesToHost.write(bProtocol);
		bBytesToHost.write(bTypeControlStatus);
		bBytesToHost.write(1);
		bBytesToHost.write(bControlReqEstatus);

		//Se envían datos a DriverSNA
		out.print(bBytesToHost);
		out.flush();

		//Se espera por el estatus de la sesión SNA
		bytesLeidos = 0;
		recibioEstado = false; 
		while (!recibioEstado && !recibioError){
			//Verifica si existe algún caracter por leer.
			if ((bytesLeidos = leeSocket()) != -1){
				//Verifica respuesta de DriverSNA respecto a estado de Línea
				if (!validateProtocol(new String(bufferMessageFromHost).getBytes(), bTypeControlStatus))
					return 0;
				//Se verifica que lo recibido sea un estado.
				if (bufferMessageFromHost[2] != (char)0){
					recibioEstado = true;
				}else{
					System.out.println("Estado Sesión SNA: Conectándose... " + getMessageFromHost());
				}
			}else{
				System.out.println("Erro: La lectura a DriverSNA ha vencido por timeout.");
				break;
			}
		}
		//Si se recibió error, para la aplicación es solo OffLine
		if (recibioError){
			StateOfLine = 0;
			return StateOfLine;
		}
		//Si recibió estado, verifica respuesta de DriverSNA respecto a estado de Línea
		if (bufferMessageFromHost[2] == (char)1){
			//OnLine.
			StateOfLine = 1;
		}else{
			//OffLine.
			StateOfLine = 0;
		}
		return StateOfLine;
	} catch (Exception e) {
		e.printStackTrace();
		return -1;
	}
*/
}

/**
 * Insert the method's description here.
 */
public void CloseSession(){
//	int bytesLeidos = 0;
	try{
		//Se Concatenan los bytes del protocolo con DriverSNA
		ByteArrayOutputStream bBytesToHost = new ByteArrayOutputStream (1);
		bBytesToHost.write(bProtocol);
		bBytesToHost.write(bTypeControlCommand);
		bBytesToHost.write(bCommandCloseSession);

		//Se envían datos a DriverSNA
		out.write(bBytesToHost.toByteArray());
		out.flush();
		
		//No se espera respuesta, solo se cierra el socket con el DriverSNA.
		if (out != null)
			out.close();
		if (in != null)
			in.close();
		if (socket != null)
			socket.close();
	} catch (IOException e) {
		// System.out.println("CloseSocket: Error al cerrar socket de comunicaciones " + e.toString());
	}
}

/**
 * Insert the method's description here.
 */
private boolean validateProtocol(byte[] bMessageFromDriverSNA, byte bType){
	if (bMessageFromDriverSNA[0] != bProtocol){
		// System.out.println("Trama recibida no es del protocolo indicado...!!!" );
		return false;
	}
	if (bMessageFromDriverSNA[1] != bType){
		// System.out.println("Trama recibida no es del tipo esperado...!!!" );
		return false;
	}
	return true;
}

/**
 * Convierte un valor entero en un arreglo de 4 bytes, el cual retorna.
 */
public static byte[] intToBytes(int intToConvert){
	byte b[] = new byte[4];
	
	b[0] = (byte)(intToConvert & 0xFF);
	b[1] = (byte)((intToConvert >>> 8) & 0xFF);
	b[2] = (byte)((intToConvert >>> 16) & 0xFF);
	b[3] = (byte)((intToConvert >>> 24) & 0xFF);
	
	return b;
}

/*
public static boolean initSNASession(){
	try {
		//Abre socket de comunicación con DriverSNA
//////////////Es necesario obtener estos valorse desde variables del servidor.	
//		InitialParametersInfo pi =  ((MaskCenter) Transaction.getMaskGlobal()).getFrameBranch().getParametrosIniciales();
		
		mySocket = new TCPIPSocket();
		mySocket.setHostAddess("cosapisac01");
		mySocket.setHostPortNumber(7000); 
		mySocket.setLuName("EHC0901 ");
		
//		Abre socket y abrir sesion SNA...!!!			
		if (mySocket.Initialize()){
			System.out.println("ON LINE");
			return true;
		}else{
			System.out.println("OFF LINE");
			return false;
		}
	} catch(Exception ise) {
		System.out.println("initSNASession: Eror al establecer sesión con Driver SNA...:" + ise.getMessage());
		return false;
	}
}*/

	/**
	 * Insert the method's description here.
	 * Creation date: (3/17/01 5:53:21 PM)
	 * @return java.lang.String
	 */
	public java.lang.String getHostAddess() {
		return hostAddess;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (3/17/01 6:05:01 PM)
	 * @return int
	 */
	public int getHostPortNumber() {
		return HostPortNumber;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (3/17/01 5:55:05 PM)
	 * @return int
	 */
//	public java.lang.String getMessageFromHost() {
//		return new String(MessageFromHost);
	public byte[] getMessageFromHost() {
		return MessageFromHost;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (3/17/01 5:54:50 PM)
	 * @return java.lang.String
	 */
	public byte[] getMessageToHost() {
		return MessageToHost;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (3/17/01 6:05:42 PM)
	 * @return long
	 */
	public int getTimeOutSeconds() {
		return TimeOutSeconds;
	}
	/**
	 * Insert the method's description here.
	 * @return int
	 */
	public int getBytesLeidos() {
		return bytesLeidos;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (3/17/01 5:53:21 PM)
	 * @param newHostAddess java.lang.String
	 */
	public void setHostAddess(java.lang.String newHostAddess) {
		hostAddess = newHostAddess;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (3/17/01 6:05:01 PM)
	 * @param newHostPortNumber int
	 */
	public void setHostPortNumber(int newHostPortNumber) {
		HostPortNumber = newHostPortNumber;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (3/17/01 5:55:05 PM)
	 * @param newMessageFromHost int
	 */
	public void setMessageFromHost(byte[] newMessageFromHost) {
		MessageFromHost = newMessageFromHost;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (3/17/01 5:54:50 PM)
	 * @param newMessageToHost java.lang.String
	 */
	public void setMessageToHost(byte[] newMessageToHost) {
		MessageToHost = newMessageToHost;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (3/17/01 6:05:42 PM)
	 * @param newTimeOutSeconds long
	 */
	public void setTimeOutSeconds(int newTimeOutSeconds) {
		TimeOutSeconds = newTimeOutSeconds;
	}
	
/*	public static String EbcdicToAscii(byte[] ascii ) 
	{
	 byte[] rpta;
	 try {
	 	ByteArrayOutputStream bRptaEBCDIC = new ByteArrayOutputStream(ascii.length);
	 	ByteArrayOutputStream bRptaASCII = new ByteArrayOutputStream(ascii.length);
	 	
	 	int inicioDatos = -1;
	 	int finDatos = -1;
	 	int inicioNoDatos = -1;
	 	boolean buscandoInicioDatos = true;
	 	for (int i=0; i<= ascii.length - 1; i++){
	 		if (buscandoInicioDatos){
	 			if ((ascii[i] < -1) || (ascii[i] > 31)){
	 				if (inicioNoDatos > -1){
		 				bRptaASCII.write(ascii, inicioNoDatos, i-inicioNoDatos);
		 				inicioNoDatos = -1;
	 				}
	 				inicioDatos = i;
	 				buscandoInicioDatos = false; 
	 			}else{
	 				if (inicioNoDatos == -1){
	 					inicioNoDatos = i;
	 				}
	 			}
	 		}else{
	 			if ((ascii[i] >= -1) && (ascii[i] <= 31)){
	 				finDatos = i;
	 				bRptaEBCDIC.reset();
	 				bRptaEBCDIC.write(ascii, inicioDatos, finDatos-inicioDatos);
	 				bRptaASCII.write( new String(bRptaEBCDIC.toByteArray(), encoding).getBytes());
	 				buscandoInicioDatos = true; 
	 				i--;
	 			}else{
	 				if (i == ascii.length - 1){
		 				bRptaEBCDIC.reset();
		 				bRptaEBCDIC.write(ascii, inicioDatos, i-inicioDatos);
		 				bRptaASCII.write( new String(bRptaEBCDIC.toByteArray(), encoding).getBytes());
	 				}
	 			}
	 		}
	 	}
	 	return bRptaASCII.toString();
	 }
	 catch(Exception e){System.out.println("AsciiToEbcdic : "+e.getMessage());}
	 return ""; 
	}*/
/*
	private byte[] concatBytes(byte[] bHeader, byte[] bBody){
		try{
			ByteArrayOutputStream bResult = new ByteArrayOutputStream (bHeader.length + bBody.length);
			bResult.write(bHeader);
			bResult.write(bBody);
			return bResult.toByteArray();
		}catch(Exception e){
			System.out.println("concatBytes: Exception: " + e.getMessage());
			return null;
		}
	}
		
	public static String AsciiToEbcdic(String ebcdic){
		 byte[] cad = null;    
		 try {
		    cad = ebcdic.getBytes(encoding);  
		    return new String(cad);    
		 }catch(Exception e){
		 	System.out.println("Method AsciiToEbcdic : "+e.getMessage());}
		 return new String(cad);
		}
/*
	public static byte[] prepareHeaderAscii(String codTransaccion) {
		char cDelimDatoAlfanumerico = 124;
		char cDelimDatoNumerico = 95;

		try{
			Time horaTerminal = new Time();
			String sDatosCabecera = new String("DI00");
			//Canal.
			sDatosCabecera = sDatosCabecera.concat("SAW");
			//Codigo de transaccion.
			sDatosCabecera = sDatosCabecera.concat(codTransaccion);
			//Codigo de Usuario.
			sDatosCabecera = sDatosCabecera.concat("1067");
//////////////Codigo de Autorizador.
			sDatosCabecera = sDatosCabecera.concat("1067");
			sDatosCabecera = sDatosCabecera.concat(CString.completeToBeginWithNChars(horaTerminal.getHours()+"",2,'0'));
			sDatosCabecera = sDatosCabecera.concat(CString.completeToBeginWithNChars(horaTerminal.getMinutes()+"",2,'0'));
			sDatosCabecera = sDatosCabecera.concat(CString.completeToBeginWithNChars(horaTerminal.getSeconds()+"",2,'0'));
			//CLOG-TRSQ.
			sDatosCabecera = sDatosCabecera.concat("0");
			//Causal.
			sDatosCabecera = sDatosCabecera.concat("SAR");
			//Hora de Terminal.
			sDatosCabecera = sDatosCabecera.concat(CString.completeToBeginWithNChars(horaTerminal.getHours()+"",2,'0'));
			sDatosCabecera = sDatosCabecera.concat(CString.completeToBeginWithNChars(horaTerminal.getMinutes()+"",2,'0'));
			sDatosCabecera = sDatosCabecera.concat(CString.completeToBeginWithNChars(horaTerminal.getSeconds()+"",2,'0'));
//////////////Fecha de Terminal.
			sDatosCabecera = sDatosCabecera.concat("2007");
			sDatosCabecera = sDatosCabecera.concat("04");
			sDatosCabecera = sDatosCabecera.concat("27");
//////////////Log de Extorno.!!!!!!!!!!!!!!!!!!
			sDatosCabecera = sDatosCabecera.concat("0000000");
			//DELIM-NUM.
			sDatosCabecera = sDatosCabecera.concat(" ");
			//DELIM-ALFANUM.
			sDatosCabecera = sDatosCabecera.concat(" ");
			//FILLER.
			sDatosCabecera = sDatosCabecera.concat("                  ");

			sClaveParaClaves = sDatosCabecera.substring(35, 43) + sDatosCabecera.substring(11, 15) + sDatosCabecera.substring(29, 33); 
				
			//Convierte cabecera a Ebcdic
			sDatosCabecera = AsciiToEbcdic(sDatosCabecera);
			
			byte[] bDatosCabecera = sDatosCabecera.getBytes(); 
			bDatosCabecera[50] = (byte)cDelimDatoNumerico;
			bDatosCabecera[51] = (byte)cDelimDatoAlfanumerico;

			return bDatosCabecera;
		}catch (Exception e){
			System.out.println("prepareHeaderAscii: Exception... " + e.getMessage());
			return null;
		}
	}*/
	
	/**
	 * @return Returns the luName.
	 */
	public String getLuName() {
		return LuName;
	}
	/**
	 * @param luName The luName to set.
	 */
	public void setLuName(String luName) {
		LuName = luName;
	}
}
