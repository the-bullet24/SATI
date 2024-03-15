package com.cosapisoft.sarawebbranch.common.beans;

import java.io.ByteArrayOutputStream;

import pe.cosapi.common.ObjectUtil;

public class HostServer {

	public static TCPIPSocket mySocket;
	
	public static String claveMac = ""; 	//"722A32A4769D019C";
	public static String claveDES_A = "";	//"9EE90A1F752F1BC7";
	public static String claveDES_B = "";	//"D2595C3D2A853626";
	public static String claveDeClaves = "";
	public static String configTrxClaves = "";
	
	public static void main(String[] args) {
		try {
			
			HostServer h = new HostServer();
			byte[] trama = h.prepareHeaderAscii("0013", "0089", "0089");
			
			if (!initSNASessionBySocket("cosapisac01", 7000, "EHC0901 "))
				return;
			
			mySocket.setMessageToHost(trama);
			if (mySocket.sendTxn()){
				String sRespuestaHost = new String(Crypto.EbcdicToAscii(mySocket.getMessageFromHost()).getBytes());
				//Se verifica que no exista error en la transaccion.
				if (!sRespuestaHost.substring(85, 87).equalsIgnoreCase("00")){
					// System.err.println("Error al recibir trama de claves: " + sRespuestaHost.substring(106, 186));
				}else{
					// System.err.println("Trama de claves recibda Ok...");
				}
			}else{
				// System.err.println("Error al recibir trama de claves...!");
			}
			ByteArrayOutputStream bTramaDesdeHost = new ByteArrayOutputStream(mySocket.getMessageFromHost().length);
			bTramaDesdeHost.write(mySocket.getMessageFromHost());
			// System.err.println("commHost: Trama desde Host:" + new String(mySocket.getMessageFromHost()));
			
			ByteArrayOutputStream bClave = new ByteArrayOutputStream(30);
			bClave.write(mySocket.getMessageFromHost(), 1050, 30);
			byte[] bTrama = Crypto.desencriptaTramaDES(bClave.toByteArray(), HostServer.claveDeClaves);
			String sTrama = Crypto.EbcdicToAscii(bTrama); 
			
			bClave.reset();
			bClave.write(mySocket.getMessageFromHost(), 1080, 30);
			bTrama = Crypto.desencriptaTramaDES(bClave.toByteArray(), HostServer.claveDeClaves);
			sTrama = Crypto.EbcdicToAscii(bTrama);
//			h.setClaveMac(sTrama);
			
			bClave.reset();
			bClave.write(mySocket.getMessageFromHost(), 1110, 30);
			bTrama = Crypto.desencriptaTramaDES(bClave.toByteArray(), HostServer.claveDeClaves);
			sTrama = Crypto.EbcdicToAscii(bTrama);
//			h.setClaveDES_A(sTrama);
			
			bClave.reset();
			bClave.write(mySocket.getMessageFromHost(), 1140, 30);
			bTrama = Crypto.desencriptaTramaDES(bClave.toByteArray(), HostServer.claveDeClaves);
			sTrama = Crypto.EbcdicToAscii(bTrama);
//			h.setClaveDES_B(sTrama);

//			System.out.println(h.getClaveMac());
//			System.out.println(h.getClaveDES_A());
//			System.out.println(h.getClaveDES_B());
			
//			trama = h.prepareHeaderAscii("0201", "0089", "    ");
			
			mySocket.CloseSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean initSNASessionBySocket(String sHostAddress, int iHostPortNumber, String sLUName){
		/*
		 * Inicia Sesión SNA desde DriverSNA. 
		 */
		try {
			//Abre socket de comunicación con DriverSNA
			mySocket = new TCPIPSocket();
			mySocket.setHostAddess(sHostAddress);
			mySocket.setHostPortNumber(iHostPortNumber); 
			mySocket.setLuName(sLUName);
			
//			Abre socket y abrir sesion SNA...!!!			
			return mySocket.Initialize();
		} catch(Exception ise) {
			// System.out.println("initSNASession: Eror al establecer sesión con Driver SNA...:" + ise.getMessage());
			return false;
		}
	}
	
	public static byte[] prepareHeaderAscii(String codTrx, String codUsuario, String codUsuAutorizador) {
		try{
			java.text.SimpleDateFormat sDateFormat = new java.text.SimpleDateFormat("yyyyMMdd");
			java.util.Calendar cCalendar = java.util.Calendar.getInstance(); 
			String sFechaTerminal = sDateFormat.format(cCalendar.getTime());

			return buildStringHeaderAscii(codTrx, codUsuario, codUsuAutorizador, sFechaTerminal);
		}catch (Exception e){
			e.printStackTrace();
			// System.out.println("prepareHeaderAscii: Exception... " + e.getMessage());
			return null;
		}
	}

	private static byte[] buildStringHeaderAscii(String codTrx, String codUsuario, String codUsuAutorizador, String sFechaTerminal) {
		char cDelimDatoAlfanumerico = 124;
		char cDelimDatoNumerico = 95;

		//Se obtiene Fecha y Hora.
		java.text.SimpleDateFormat sDateFormat = new java.text.SimpleDateFormat("HHmmss");
		java.util.Calendar cCalendar = java.util.Calendar.getInstance(); 
		String horaTerminal = sDateFormat.format(cCalendar.getTime());	

		//Se inicia construccion de cabecera de trama.
		String sDatosCabecera = ("DI00");
		//Canal.
		sDatosCabecera = sDatosCabecera.concat("WEB");
		//Codigo de transaccion.
		sDatosCabecera = sDatosCabecera.concat(codTrx);
		//Codigo de Usuario.
		sDatosCabecera = sDatosCabecera.concat(codUsuario);
        //Codigo de Autorizador.
		sDatosCabecera = sDatosCabecera.concat(codUsuAutorizador);
		//Hora de transaccion.
		sDatosCabecera = sDatosCabecera.concat(horaTerminal);
		//CLOG-TRSQ.
		sDatosCabecera = sDatosCabecera.concat("0");
		//Causal.
		sDatosCabecera = sDatosCabecera.concat("WEB");
		//Hora de Terminal.
		sDatosCabecera = sDatosCabecera.concat(horaTerminal);
        //Fecha de Terminal: Formato AAAAMMDD.
		sDatosCabecera = sDatosCabecera.concat(sFechaTerminal);
//////////////Log de Extorno.!!!!!!!!!!!!!!!!!!
		sDatosCabecera = sDatosCabecera.concat("0000000");
		//DELIM-NUM.
		sDatosCabecera = sDatosCabecera.concat(" ");
		//DELIM-ALFANUM.
		sDatosCabecera = sDatosCabecera.concat(" ");
		//FILLER.
		sDatosCabecera = sDatosCabecera.concat("                  ");
		
		//Para efectos de Trace.
//		sTraceHeader = sDatosCabecera; 
		System.out.println("Trama envio1: " + sDatosCabecera);
		//Se arma clave para desencriptar claves.
		claveDeClaves = "";
		java.util.StringTokenizer listPosiciones = new java.util.StringTokenizer(HostServer.configTrxClaves, "|");
		while (listPosiciones.hasMoreElements()){
			java.util.StringTokenizer listValores = new java.util.StringTokenizer(listPosiciones.nextToken(), ",");
			
			String s = (String)listValores.nextElement();
			int sPosIni = new Integer(listValores.nextElement().toString()).intValue();
			int sPosFin = new Integer(listValores.nextElement().toString()).intValue();
			
			claveDeClaves += sDatosCabecera.substring(sPosIni, sPosFin);
		}
		
		sDatosCabecera = ObjectUtil.procesarLongitud(sDatosCabecera.length()) + sDatosCabecera;
		System.out.println("Trama envio2: " + sDatosCabecera);
		// System.out.println("sDatosCabecera : "+sDatosCabecera);
		
		//Convierte cabecera a Ebcdic
		//sDatosCabecera = new String(Crypto.AsciiToEbcdic(sDatosCabecera));
		
		//Se reemplazan los delimitadores de 
		byte[] bDatosCabecera = sDatosCabecera.getBytes(); 
		bDatosCabecera[50] = (byte)cDelimDatoNumerico;
		bDatosCabecera[51] = (byte)cDelimDatoAlfanumerico;
		System.out.println("Trama envio3: "+  new String(bDatosCabecera));
		return bDatosCabecera;
	}	

}
