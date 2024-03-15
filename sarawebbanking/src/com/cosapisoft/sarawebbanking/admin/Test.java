/*
 * Creado el 12/06/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package com.cosapisoft.sarawebbanking.admin;


import java.io.File;
import java.io.FileOutputStream;

/**
 * @author cosapi_ati02
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class Test {
	public static void main(String[] args) throws Exception{
		// System.out.println(System.getProperty("user.home"));
		/*
		InitialParametersInfo pi = new InitialParametersInfo();
		pi.setCodAgencia("0001");
		pi.setTipoEncripcion("D");
		pi.setConfiguracionTrxClaves("1,35,43|2,21,25|3,29,33");
		HostServer.configTrxClaves = pi.getConfiguracionTrxClaves();
		byte[] trama = HostServer.prepareHeaderAscii("0013", "0000", "0000");		
		
		String cuerpo = TramaUtil.getStringDecimalEbcdic(new String(trama));
		
		System.out.println(new String(trama));
		
		Map mapResp = UtilWebServices.getInstance().callNewSendHost(cuerpo);
		String tramaCodificada  = (String)mapResp.get("tramaRespuesta");
		String codResp 			= (String)mapResp.get("codResp");
		
		byte[] asciiEncriptado		= TramaUtil.getBytesTrama(tramaCodificada);
		
		String cabecera = TramaUtil.decodificarDecimal2Ebcdic(tramaCodificada);	
		
		System.out.println("respuesta: "+cabecera);
		
		//System.out.println("respuesta a desencriptar: "+tramaCodificada.substring(2226,2345));
		
		System.out.println("HostServer.claveDeClaves: "+HostServer.claveDeClaves);
				
		
		ByteArrayOutputStream bClave = new ByteArrayOutputStream(30);
		bClave.write(asciiEncriptado, 2226, 30);
		byte[] bTrama = Crypto.desencriptaTramaDES(bClave.toByteArray(), HostServer.claveDeClaves);
		String output = new String(Crypto.EbcdicToAscii(bTrama));
		
		System.out.println("tratando de decodificar ..."+new String(bClave.toByteArray()));
		System.out.println("sTrama: "+output);

		bClave = new ByteArrayOutputStream(30);
		bClave.write(asciiEncriptado, 2226+30, 30);
		bTrama = Crypto.desencriptaTramaDES(bClave.toByteArray(), HostServer.claveDeClaves);
		output = new String(Crypto.EbcdicToAscii(bTrama));

		System.out.println("tratando de decodificar ..."+new String(bClave.toByteArray()));
		System.out.println("sTrama: "+output);

		bClave = new ByteArrayOutputStream(30);
		bClave.write(asciiEncriptado, 2226+60, 30);
		bTrama = Crypto.desencriptaTramaDES(bClave.toByteArray(), HostServer.claveDeClaves);
		output = new String(Crypto.EbcdicToAscii(bTrama));

		System.out.println("tratando de decodificar ..."+new String(bClave.toByteArray()));
		System.out.println("sTrama: "+output);

		bClave = new ByteArrayOutputStream(30);
		bClave.write(asciiEncriptado, 2226+90, 30);
		bTrama = Crypto.desencriptaTramaDES(bClave.toByteArray(), HostServer.claveDeClaves);
		output = new String(Crypto.EbcdicToAscii(bTrama));
		
		System.out.println("tratando de decodificar ..."+new String(bClave.toByteArray()));
		System.out.println("sTrama: "+output);
*/
	}
	
	private static void imprimir2(byte[] trama){
		File f = new File("c:\\logs\\ResultWebServiceWAS_Encript.txt");
		try {
			FileOutputStream st = new FileOutputStream(f);
			st.write(trama);
			st.close();
			f = null;
			st = null;
		} catch (Exception e) {
			
		}
	}
	
}
