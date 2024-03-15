package pe.cosapi.system;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import pe.bn.telegiro.action.TelegiroAction;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.common.Constante;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class GeneratorKeys {
	private static LoggerSati log3 = LoggerSati.getInstance(GeneratorKeys.class.getName());
	
	public String getCodeRandomEncript(){
		long aleatorio = -1;
		
		for(;;){
		aleatorio = Math.round(Math.random()*100);
		//log.warn("GeneratorKeys.class - aleatorio: "+aleatorio);
			if (aleatorio==20){
				aleatorio = 0;
			}
			if (aleatorio<20){
				//log.warn("GeneratorKeys.class - si aleatorio<20; break");
				break;
			}
		}
		return procesarEncript(String.valueOf(aleatorio));
	}
	
	public String getCodeRandomEncriptDif(String value){
		//log.warn("GeneratorKeys.class - ENTRO al metodo getCodeRandomEncriptDif");
		long  ranTemp = Integer.parseInt(procesarDencript(value));
		
		long aleatorio = -1;
		
		for(;;){
		aleatorio = Math.round(Math.random()*100);
			if (aleatorio==20){
				aleatorio = 0;
			}
			if (aleatorio<20 && ranTemp!= aleatorio){
				//log.warn("GeneratorKeys.class - si aleatorio<20; break -  metodo getCodeRandomEncriptDif");
				break;
			}
		}
		//log.warn("GeneratorKeys.class - SALE DEL METODO getCodeRandomEncriptDif");
		return procesarEncript(String.valueOf(aleatorio));
		
	}
	
	public Map generateMap(){
		//log.warn("Entro a generateMap()");
		Map output =  new HashMap();
		for(int j = 0; j < 20; j++){
			Map map = new HashMap();
			String cadena = "";
			int i = 0;
			for(;;){
				if(i==10){
					break;
				}
				long aleatorio = Math.round(Math.random()*10);
				if(aleatorio==10)
					aleatorio = 0; 
				Long real = new Long(aleatorio);
				if(!map.containsKey(real)){
					map.put(real,new Long(aleatorio));
					cadena+=aleatorio;
					i++;
				}			
			}
			output.put(String.valueOf(j),procesarEncript(cadena));
		}
		//log.warn("SALIO de generateMap()");
		return output;
	}
	
	public Map generateMapSello(){
		//log.warn("Entro a generateMap()");
		Map output =  new HashMap();
		for(int j = 0; j < 20; j++){
			Map map = new HashMap();
			String cadena = "";
			int i = 0;
			for(;;){
				if(i==9){
					break;
				}
				long aleatorio = Math.round(Math.random()*10);
				if(aleatorio==10)
					aleatorio = 0; 
				
				if(aleatorio>=1) {
					Long real = new Long(aleatorio);
					if(!map.containsKey(real)){
						map.put(real,new Long(aleatorio));
						cadena+=aleatorio;
						i++;
					}		
				}
			}
			output.put(String.valueOf(j),procesarEncript(cadena));
		}
		//log.warn("SALIO de generateMap()");
		return output;
	}
	
	 /**
     * Encripta un String utilizando el algoritmo Triple DES
     *
     * @param clearText Texto en ckaro a encriptar
     * @return texto encriptado en base 64
     */
	 protected String encrypt (String clearText, Key key) {
	 	//log.warn("GeneratorKeys.class - ENTRO A encrypt");
	        String cipherTextB64 = "";
	        
	        try {
	        // Necesitamos un cifrador
	        Cipher cipher = Cipher.getInstance("DESede");
	        
	        // Ciframos el texto en claro
	        cipher.init(Cipher.ENCRYPT_MODE, key);                
	        byte cipherText[] = cipher.doFinal(clearText.getBytes());        
	        
	        // Codificamos el texto cifrado en base 64
	        BASE64Encoder base64encoder = new BASE64Encoder();
	        cipherTextB64 = base64encoder.encode(cipherText);
	        
	        }
	        catch(Exception e) {
	        	log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
	        	System.out.println("GeneratorKeys.class - Error al encriptar el texto plano - Metodo encrypt"+e); 
	        }
	        // Retornamos el texto cifrado en BASE64
	        //log.warn("GeneratorKeys.class - SALIO DE encrypt");
	        return cipherTextB64;
	    }
	 
	 /**
	     * Desencripta un testo cifrado en Triple DES i codificado en base 64
	     *
	     * @param String cipherTextB64 Testo cifrado en Triple DES y codificado en B64
	     * @return String Texto en claro
	     */
	 protected String decrypt (String cipherTextB64, Key key) {
	        
	        String clearText = "";
	        
	        try {
	        
	        // Necesitamos un cifrador
	        Cipher cipher = Cipher.getInstance("DESede");
	        	
	        // La clave está codificada en base 64
	        BASE64Decoder base64decoder = new BASE64Decoder();
	        byte cipherText[] = base64decoder.decodeBuffer(cipherTextB64);
	        
	        // Ciframos el texto en claro
	        cipher.init(Cipher.DECRYPT_MODE, key);                
	        byte bclearText[] = cipher.doFinal(cipherText);        
	        clearText = new String(bclearText);
	        
	        }
	        catch(Exception e) {
	        	log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
	        	System.out.println(""+e);
	        }
	        return clearText;
	    }
	    
	 public String  procesarEncript(String Valor){
	 	//log.warn("GeneratorKeys.class - ENTRO A procesarEncript");
	    	 // Tres claves de 64 bits tipo DES
	        // Por lo tanto este algoritmo es compatible con DES
	        byte[] secret = {0x12, 0x42, 0x31, 0x24, 0x19, 0x34, 0x72, 0x37, 0x12, 0x42, 0x31, 0x24, 0x19, 0x34, 0x72, 0x37, 0x12, 0x42, 0x31, 0x24, 0x19, 0x34, 0x72, 0x37};
	        
	        Key key = null;
	        try {            
	           SecretKeyFactory skf = SecretKeyFactory.getInstance("DESede");
	           key = skf.generateSecret(new DESedeKeySpec(secret));
	        }
	        catch(Exception e) {
	        	log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
	        	System.out.println("GeneratorKeys.class - ERROR AL GENERAR LA LLAVE - Metodo procesarEncript"+e);
	        }
	        
	        String cipherText = encrypt(Valor, key);
	       // log.warn("GeneratorKeys.class - SALIO DE procesarEncript");
	        return cipherText;
	    }
	    
	 public String  procesarDencript(String Valor){
	       byte[] secret = {0x12, 0x42, 0x31, 0x24, 0x19, 0x34, 0x72, 0x37, 0x12, 0x42, 0x31, 0x24, 0x19, 0x34, 0x72, 0x37, 0x12, 0x42, 0x31, 0x24, 0x19, 0x34, 0x72, 0x37};
	       Key key = null;
	       try {            
	          SecretKeyFactory skf = SecretKeyFactory.getInstance("DESede");
	          key = skf.generateSecret(new DESedeKeySpec(secret));
	       }
	       catch(Exception e) {
	    	   log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
	       	System.out.println(""+e);
	       }
	       String clearText = decrypt(Valor, key);
	       return clearText;
	}
	
	 public String getClave(Map mapTeclado,String indiceEncriptado,String valorCodificado){
	 	//log.warn("GeneratorKeys.class - ENTRO AL METODO getClave ");
	 	StringBuffer sb = new StringBuffer(); 
	 	String indiceDencriptado = procesarDencript(indiceEncriptado);
	 	
	 	String cadenaTecladoEncriptada  = (String)mapTeclado.get(indiceDencriptado);
	 	String cadenaTecladoDencriptada = procesarDencript(cadenaTecladoEncriptada);
	 	
	 	String valChar = "";
	 	for (int i=0;i<valorCodificado.length();i++){
	 		//log.warn("GeneratorKeys.class - Entro al FOR  - metodo getClave");
	 		String val = valorCodificado.substring(i,i+1);
	 		//log.warn("GeneratorKeys.class - FOR - val: "+ val +" - metodo getClave");
	 		valChar+=getValorTecla(val,cadenaTecladoDencriptada);
	 		//log.warn("GeneratorKeys.class - FOR - valChar: "+ valChar+" - metodo getClave");
	 	}
	 	//log.warn("GeneratorKeys.class - SALE DEL METODO getClave ");
	 	return valChar;
	 }
	 
	 public String getValorTecla(String val,String cadenaTecladoDencriptada){
	 	String value = "";
	 	
	 	if (val.equals("m")){
	 		value = cadenaTecladoDencriptada.substring(0,1);
	 	}else if (val.equals("n")){
	 		value = cadenaTecladoDencriptada.substring(1,2);
	 	}else if (val.equals("p")){
	 		value = cadenaTecladoDencriptada.substring(2,3);
	 	}else if (val.equals("i")){
	 		value = cadenaTecladoDencriptada.substring(3,4);
	 	}else if (val.equals("j")){
	 		value = cadenaTecladoDencriptada.substring(4,5);
	 	}else if (val.equals("k")){
	 		value = cadenaTecladoDencriptada.substring(5,6);
	 	}else if (val.equals("a")){
	 		value = cadenaTecladoDencriptada.substring(6,7);
	 	}else if (val.equals("y")){
	 		value = cadenaTecladoDencriptada.substring(7,8);
	 	}else if (val.equals("x")){
	 		value = cadenaTecladoDencriptada.substring(8,9);
	 	}else if (val.equals("t")){
	 		value = cadenaTecladoDencriptada.substring(9,cadenaTecladoDencriptada.length());
	 	}
	 	
	 	return value;
	 }
	 
	 
}
