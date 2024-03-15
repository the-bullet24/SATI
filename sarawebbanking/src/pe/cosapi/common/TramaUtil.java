/*
 * Creado el 09/04/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.cosapi.common;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author cosapi_ati04
 * 
 * TODO Para cambiar la plantilla de este comentario generado, vaya a Ventana -
 * Preferencias - Java - Estilo de código - Plantillas de código
 */
public class TramaUtil {
	
	private final static String ENCODING = "Cp1047";
	
	public static final String byte2String(byte[]bytes){
		if(bytes==null)
			return null;
		String cadena	= "";
		for (int i = 0; i < bytes.length; i++) {
			cadena+=String.valueOf(bytes[i]);
		}
		return cadena;
	}
	
	
	public final static String ASCII2EBCDIC(String trama) throws Exception{
		byte[] bytes = trama.getBytes();
		StringBuffer stringBuffer = new StringBuffer("");
		for (int i = 0; i < bytes.length; i++) {
			stringBuffer.append(new String(new byte[]{bytes[i]},ENCODING));
		}
		return stringBuffer.toString();		
		/*
		byte[] cad = trama.getBytes(ENCODING);
		StringBuffer stringBuffer = new StringBuffer("");
	    for (int i = 0; i < cad.length; i++) {
	    	stringBuffer.append(new String(new byte[]{cad[i]},ENCODING));
		}
	    return stringBuffer.toString();
	    */
	}
	
	public final static byte ASCII2EBCDIC(byte byteASCII) throws Exception{
		String transformada = new String(new byte[]{byteASCII},ENCODING);
		//System.out.print("transformada.getBytes().length = "+transformada.getBytes().length);
		return transformada.getBytes()[0];						
	}

	
	public final static String EBCDIC2ASCII(String trama) throws Exception{
		StringBuffer stringBuffer = new StringBuffer("");

		for (int i = 0; i < trama.length(); i++) {
			char c = trama.charAt(i);
			stringBuffer.append(new String(String.valueOf(c).getBytes(),ENCODING));
			//stringBuffer.append(new String(new byte[]{bytes[i]},ENCODING));
		}
		/*return new String(trama.getBytes(),ENCODING);*/
		return stringBuffer.toString();
	}
	
	
	public final static String EBCDIC2Decimal(String trama) throws Exception{
		String resultado = "";
		byte[] resul = trama.getBytes(ENCODING);
		
		for (int i = 0; i < resul.length; i++) {
			int valor = new Byte(resul[i]).intValue();
			if(valor < 0)
				valor = 256 + valor;
			String car = String.valueOf(valor);
			resultado = resultado + ObjectUtil.setearCaracterLeft(car,'0',3-car.length()) ;
		}
		return resultado;
	}	

	public final static String decimal2EBCDIC(String resul) throws Exception{
		StringBuffer resultado = new StringBuffer("");
		for(int i = 0 ; i < resul.length();){			
			resultado.append(new String(new byte[]{new Integer(resul.substring(i,i+=3)).byteValue()},ENCODING));						
		}
		return resultado.toString();
	}	
	
	
	public static void main(String[] args) throws Exception{
		for(int i = 0 ; i < 256; i++){
			// System.out.println(i+"\t"+(char)i+"\t"+new String(new byte[]{(byte)i},ENCODING));
		}
		String ascii 	= "NAT001877900kyyp000000000";
		String ebcdic	= TramaUtil.ASCII2EBCDIC("NAT001877900kyyp000000000");
		// System.out.println(ebcdic);
		String decode = TramaUtil.EBCDIC2ASCII(ebcdic);
		// System.out.println(decode);
		/*
		 * —
		System.out.println(TramaUtil.EBCDIC2ASCII("ÕÁãğğñø÷÷ùğğ,¨¨øğğğğğğğğğ"));
		System.out.println(TramaUtil.ASCII2EBCDIC("NAT001877900kyyp000000000"));
		System.out.println(TramaUtil.EBCDIC2Decimal("ø"));
		System.out.println(TramaUtil.decimal2EBCDIC("239101070140140073112225225221140140107189189112140140140140140140140140140"));				
		System.out.println(TramaUtil.decimal2EBCDIC(TramaUtil.EBCDIC2Decimal("ÕÁãğğñø÷÷ùğğ,¨¨øğğğğğğğğğ")));
		*/
	}
	
	private static String encoding = "CP037";
	
	public static byte[] AsciiToEbcdic(String ebcdic){
		 byte[] cad = null;    
		 try {
		    return ebcdic.getBytes(encoding);    
		 }catch(Exception e){
		 	// System.out.println("AsciiToEbcdic: "+e.getMessage());
		 }
		 return cad;
	}
	
	public static String toHexadecimal(byte[] datos) {
        String resultado=""; 
        ByteArrayInputStream input = new ByteArrayInputStream(datos); 
        String cadAux; 
        int leido = input.read(); 
        while(leido != -1) 
        { 
                cadAux = Integer.toHexString(leido); 
                if(cadAux.length() < 2) //Hay que añadir un 0 
                        resultado += "0"; 
                resultado += cadAux; 
                leido = input.read(); 
        } 
        return resultado; 
	}
	
	public static String getAscii2EbcdicDecimal(char asciiValue){
		String valor 	= "";
		byte[] beb 		= AsciiToEbcdic(String.valueOf(asciiValue));
		String hexa2 	= toHexadecimal(beb);
		int value 		= Integer.parseInt(hexa2, 16);
		if (value<99){
			valor =  ObjectUtil.setearCaracterLeft(String.valueOf(value),'0',3-String.valueOf(value).length());
		}else{
			valor = String.valueOf(value);
		}
		return valor;
	}
	
	public static String getStringDecimalEbcdic(String ascii){
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < ascii.length(); i++) {
			sb.append(getAscii2EbcdicDecimal(ascii.charAt(i)));
		}
		return sb.toString();
	}
	
	private static Map getMapaEBCDIC(){
		Map output = new HashMap();
		output.put("064"," ");		  	output.put("080","&");  	output.put("096","-");  
		output.put("065"," ");    		output.put("081","é");    	output.put("097","/");
		output.put("066","â");        	output.put("082","ê");    	output.put("098","Â");
		output.put("067","ä");      	output.put("083","ë");   	output.put("099","Ä");
		output.put("068","à");      	output.put("084","è");      output.put("101","À");  
		output.put("069","á");      	output.put("085","í");      output.put("102","Á");  
		output.put("070","ã");      	output.put("086","î");      output.put("103","Ã");  
		output.put("071","å");      	output.put("087","ï");      output.put("104","Å");  
		output.put("072","ç");      	output.put("088","ì");      output.put("105","Ç");  
		output.put("073","ñ");      	output.put("089","ß");      output.put("106","Ñ");  
		output.put("074"," ");     		output.put("090","!");    	output.put("107",",");
		output.put("075",".");      	output.put("091","$");      output.put("108","%");  
		output.put("076","<");      	output.put("092","*");      output.put("109","_");  
		output.put("077","(");      	output.put("093",")");      output.put("110",">");  
		output.put("078","+");      	output.put("094",";");      output.put("111","?");  
		output.put("079","|");      	output.put("095","¬");      output.put("112","ø");  


		output.put("113","É");		  	output.put("129","a");  	output.put("145","j");  
		output.put("114","Ê");    		output.put("130","b");    	output.put("146","k");
		output.put("115","Ë");        	output.put("131","c");    	output.put("147","l");
		output.put("116","È");      	output.put("132","d");   	output.put("148","m");
		output.put("117","Í");      	output.put("133","e");      output.put("149","n");  
		output.put("118","Î");      	output.put("134","f");      output.put("150","o");  
		output.put("119","Ï");      	output.put("135","g");      output.put("151","p");  
		output.put("120","Ì");      	output.put("136","h");      output.put("152","q");  
		output.put("121","`");      	output.put("137","i");      output.put("153","r");  
		output.put("122",":");      	output.put("138"," ");      output.put("154"," ");  
		output.put("123","#");     		output.put("139"," ");    	output.put("155"," ");
		output.put("124","@");      	output.put("140"," ");      output.put("156","æ");  
		output.put("125","'");      	output.put("141","ı");      output.put("157"," ");  
		output.put("126","=");      	output.put("142"," ");      output.put("158","Æ");  
		output.put("127","\"");      	output.put("143"," ");      output.put("159"," ");  
		output.put("128","Ø");      	output.put("144"," ");      output.put("160"," ");  

		
		output.put("161","~");		  	output.put("177","£");  	output.put("193","A");  
		output.put("162","s");    		output.put("178","¥");    	output.put("194","B");
		output.put("163","t");        	output.put("179"," ");    	output.put("195","C");
		output.put("164","u");      	output.put("180"," ");   	output.put("196","D");
		output.put("165","v");      	output.put("181"," ");      output.put("197","E");  
		output.put("166","w");      	output.put("182"," ");      output.put("198","F");  
		output.put("167","x");      	output.put("183"," ");      output.put("199","G");  
		output.put("168","y");      	output.put("184"," ");      output.put("200","H");  
		output.put("169","z");      	output.put("185"," ");      output.put("201","I");  
		output.put("170"," ");      	output.put("186","[");      output.put("202"," ");  
		output.put("171"," ");     		output.put("187","]");    	output.put("203","ô");
		output.put("172"," ");      	output.put("188"," ");      output.put("204","ö");  
		output.put("173","İ");      	output.put("189"," ");      output.put("205","ò");  
		output.put("174"," ");      	output.put("190"," ");      output.put("206","ó");  
		output.put("175"," ");      	output.put("191"," ");      output.put("207","õ");  
		output.put("176","^");      	output.put("192","{");      output.put("208","}");
		
		output.put("209","J");		  	output.put("225"," ");  	output.put("241","1");  
		output.put("210","K");    		output.put("226","S");    	output.put("242","2");
		output.put("211","L");        	output.put("227","T");    	output.put("243","3");
		output.put("212","M");      	output.put("228","U");   	output.put("244","4");
		output.put("213","N");      	output.put("229","V");      output.put("245","5");  
		output.put("214","O");      	output.put("230","W");      output.put("246","6");  
		output.put("215","P");      	output.put("231","X");      output.put("247","7");  
		output.put("216","Q");      	output.put("232","Y");      output.put("248","8");  
		output.put("217","R");      	output.put("233","Z");      output.put("249","9");  
		output.put("218"," ");      	output.put("234"," ");      output.put("250","");  
		output.put("219","û");     		output.put("235","Ô");    	output.put("251","Û");
		output.put("220","ü");      	output.put("236","Ö");      output.put("252","Ü");  
		output.put("221","ù");      	output.put("237","Ò");      output.put("253","Ù");  
		output.put("222","ú");      	output.put("238","Ó");      output.put("254","Ú");  
		output.put("223","ÿ");      	output.put("239","Õ");      output.put("255","Ÿ");  
		output.put("224","\\");      	output.put("240","0");      output.put("000"," ");
		
		return output;
	}
	
	public static String getMapValue(String decimal){
		Map output = getMapaEBCDIC();
		String value = (String)output.get(decimal);
		if (value==null){
			//log.info("decimal no encontrado="+decimal);
			return " ";
		}
		//log.info("decimal no encontrado="+decimal);		
		return value;
	}
	
	public static String decodificarDecimal2Ebcdic(String cadenaDecimal){
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < cadenaDecimal.length(); i=i+3) {
			if (i+3 < cadenaDecimal.length()){
				sb.append(getMapValue(cadenaDecimal.substring(i,i+3)));
			}
			else{
				sb.append(getMapValue(cadenaDecimal.substring(i,cadenaDecimal.length())));
			}
		}
		return sb.toString();
	}
	
	public static byte[] getBytesTrama(String tramacodificada){		
		byte[] bytes = new byte[tramacodificada.length()/3];
		for (int i = 0,j=0; i < tramacodificada.length(); j++) {
			bytes[j] = ObjectUtil.positiveIntToByte(Integer.parseInt(tramacodificada.substring(i,i+=3)));
		}
		return bytes;
	}

	public static String decodificarDecimal2Ebcdic(byte[] ebcdic){
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < ebcdic.length; i++) {
			String byte_ = String.valueOf(ObjectUtil.byteToPositiveInt(ebcdic[i]));			
			byte_ = ObjectUtil.setearCaracterLeft(byte_,'0',3-byte_.length());
			sb.append(getMapValue(byte_));
		}
		return sb.toString();
	}
	/*
	public static byte[] decodificarDecimal2Ebcdic(byte[] ebcdic){
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < ebcdic.length; i++) {
			String byte_ = String.valueOf(ObjectUtil.byteToPositiveInt(ebcdic[i]));			
			byte_ = ObjectUtil.setearCaracterLeft(byte_,'0',3-byte_.length());
			sb.append(getMapValue(byte_));
		}
		return sb.toString();
	}
*/
	public static String decodificarDecimalASCII(byte[] ascii){
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < ascii.length; i++) {									
			sb.append((char)ObjectUtil.byteToPositiveInt(ascii[i]));
		}
		return sb.toString();
	}
	
}