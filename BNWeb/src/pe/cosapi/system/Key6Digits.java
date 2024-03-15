/**-----------------------------------------------------------------|
|   Componente: Key6Digits.java      								|
|	Def: Clase encargada de Proveer metodos para validar la			|
|		 la clave de 6 digitos.										|
|																	|
|-----------------------------------------------------------------**/
package pe.cosapi.system;

import org.apache.soap.SOAPException;

import pe.bn.telegiro.action.TelegiroAction;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.common.ArrayRuleException;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteError;
import pe.cosapi.system.services.UtilWebServices;

public class Key6Digits {
	private static LoggerSati log3 = LoggerSati.getInstance(Key6Digits.class.getName());
	
	private static final String TIPO_DOC_DNI  = "1";
	private static final String TIPO_DOC_FFAA = "2";
	private static final String TIPO_DOC_FFPP = "3";
	
	//2 = Carnet de FFAA  y 3= CArnet de FFPP
	
	public static void main(String[] args) throws Exception{
		Key6Digits key6_ = new Key6Digits();
		//key6_.processValidationCardAhorro("4214100001609900","1234","654321","654321");
		String cRetorno = key6_.processValidationCardAhorroClave("4214100021758760","8760","111111","111111");
	}
	/**
	 * 
	 * @param nroTarjeta 	: Numero de Tarjeta de Cuenta de Ahorros
	 * @param wPINEncript 	: Clave de 4 digitos Encriptada
	 * @param wPINBLOCKNew 	: Clave de 6 digitos sin encriptar
	 * @param wPINBLOCKConf : Confirmacion de Clave de 6 digitos sin encriptar 
	 * @throws Exception
	 */
	public void processValidationCardAhorro(String nroTarjeta,String wPINEncript,String wPINBLOCKNew,String wPINBLOCKConf)throws Exception{
	    //if(Constante.VER_LOG) System.out.println("******** clave 4 digitos encriptado wPINEncript:"+wPINEncript);
	    Key4Digits key4Gen = new Key4Digits(); 
	    
	    wPINEncript =  key4Gen.desencriptar(wPINEncript);
	    //if(Constante.VER_LOG) System.out.println("++++++++ clave 4 digitos desencriptado wPINEncript:"+wPINEncript);
	    
		String[] result = UtilWebServices.getInstance().callWebServiceValidatorCardAhorro(nroTarjeta,wPINEncript,wPINBLOCKNew,wPINBLOCKConf);
		
		
		if (result!=null){
		//if(Constante.VER_LOG) System.out.println("- result="+result[0]+"|"+result[1]);
			if (!Constante.COD_RESP.equals(result[0])){
				String mensaje ="";
				if(result[0].equals(Constante.CLAVE_INTERNET_INCORRECTO)){
					 mensaje = Constante.CLAVE_INTERNET_INCORRECTO_DESCRIPCION;
				}else{
					 mensaje = result[1];
				}
				
				
				throw new  ArrayRuleException(ConstanteError.GENERICO,mensaje);
			}
		}else{
			//System.out.println("Cuenta Ahorro: Error el resultado del Web Service es Nulo");
			throw new SOAPException("","");
		}
	}
	
	/**
	 * @param nroTarjeta 	: Numero de Tarjeta de Cuenta de Ahorros
	 * @param wPINEncript 	: Clave de 4 digitos Encriptada
	 * @param wPINBLOCKNew 	: Clave de 6 digitos sin encriptar
	 * @param wPINBLOCKConf : Confirmacion de Clave de 6 digitos sin encriptar 
	 * @throws Exception
	 */
	public String processValidationCardAhorroClave(String nroTarjeta,String wPINEncript,String wPINBLOCKNew,String wPINBLOCKConf)throws Exception{
	    //if(Constante.VER_LOG) System.out.println("******** clave 4 digitos encriptado wPINEncript:"+wPINEncript);
	    Key4Digits key4Gen = new Key4Digits(); 
	    
	    wPINEncript =  key4Gen.desencriptar(wPINEncript);
	    String cResult = "0";
	    //if(Constante.VER_LOG) System.out.println("++++++++ clave 4 digitos desencriptado wPINEncript:"+wPINEncript);
	    
		String[] result = UtilWebServices.getInstance().callWebServiceValidatorCardAhorro(nroTarjeta,wPINEncript,wPINBLOCKNew,wPINBLOCKConf);
		
		//System.out.println("result:"+result);
		
		if (result!=null){
			if(Constante.VER_LOG) System.out.println("- result="+result[0]+"|"+result[1]);
			if (result[0].equals("0") || result[0].equals("10")){
				cResult = "1";
			}
		}
		return cResult;
	}
	
	/**
	 * 
	 * @param tipDocumento	: Valores que puede tomar 1 = DNI, 2 = Carnet de FFAA, 3 = Carnet de FFPP
	 * @param nroDocumento	: Nro de Documento 
	 * @param wPINEncript 	: Clave de 4 digitos
	 * @param wPINBLOCKNew 	: Clave de 6 digitos
	 * @param wPINBLOCKConf : Confirmacion de clave de 6 digitos
	 * @throws Exception	
	 */
	public void processValidationCardCuentaCorriente(String tipDocumento, String nroDocumento,String wPINEncript,String wPINBLOCKNew,String wPINBLOCKConf)throws Exception{
		String[] result = UtilWebServices.getInstance().callWebServiceValidatorCardCorriente(tipDocumento,nroDocumento,wPINEncript,wPINBLOCKNew,wPINBLOCKConf);
		if (result!=null){
			//if(Constante.VER_LOG) System.out.println("- result="+result[0]+"|"+result[1]);
			if (!Constante.COD_RESP.equals(result[0])){
				String mensaje = result[1];
				throw new  ArrayRuleException(ConstanteError.GENERICO,mensaje);
			}
		}else{
			//System.out.println("Cuenta Corriente: Error el resultado del Web Service es Nulo");
			throw new SOAPException("","");
		}
	}
	
	/**
	 * 
	 * @param tarjeta       : Numero de Tarjeta de Cuenta de Ahorros
	 * @param wPINEncript   : Clave de 4 digitos Encriptado
	 * @param wPINBLOCKNew  : Nueva clave de 6 digitos 
	 * @param wPINBLOCKConf : clave actual de 6 Digitos.(clave a ser cmabiada)
	 * @throws Exception    
	 */
	public void processChangeKey6DigitsAhorro(String tarjeta,String wPINEncript,String wPINBLOCKNew,String wPINBLOCKConf)throws Exception{
	    //if(Constante.VER_LOG) System.out.println("******** clave 4 digitos encriptado wPINEncript:"+wPINEncript);
	    Key4Digits key4Gen = new Key4Digits(); 
	    
	    wPINEncript =  key4Gen.desencriptar(wPINEncript);
	    //if(Constante.VER_LOG) System.out.println("++++++++ clave 4 digitos desencriptado wPINEncript:"+wPINEncript);
	    
		String[] result = UtilWebServices.getInstance().callWebServiceChangeKey6DigitsAhorro(tarjeta,wPINEncript,wPINBLOCKNew,wPINBLOCKConf);
		if (result!=null){
			//if(Constante.VER_LOG) System.out.println("- result="+result[0]+"|"+result[1]);
			if (!Constante.COD_RESP.equals(result[0])){
				String mensaje = result[1];
				throw new  ArrayRuleException(ConstanteError.GENERICO,mensaje);
			}
		}else{
			//System.out.println("processChangeKey6DigitsAhorro: Error el resultado del Web Service es Nulo");
			throw new SOAPException("","");
		}
	}
	
	/**
	 * 
	 * @param tarjeta       : Numero de Tarjeta de Cuenta de Ahorros
	 * @param wPINEncript   : Clave de 4 digitos Encriptado
	 * @param wPINBLOCKNew  : Nueva clave de 6 digitos 
	 * @param wPINBLOCKConf : clave actual de 6 Digitos.(clave a ser cmabiada)
	 * @throws Exception    
	 */
	public void processDesafiliaKey6DigitsAhorro(String tarjeta,String wPINEncript,String wPINBLOCKNew,String wPINBLOCKConf)throws Exception{
	    //if(Constante.VER_LOG) System.out.println("******** clave 4 digitos encriptado wPINEncript:"+wPINEncript);
	    Key4Digits key4Gen = new Key4Digits(); 
	    
	    wPINEncript =  key4Gen.desencriptar(wPINEncript);
	    //if(Constante.VER_LOG) System.out.println("++++++++ clave 4 digitos desencriptado wPINEncript:"+wPINEncript);
	    
		String[] result = UtilWebServices.getInstance().callWebServiceDesafiliaKey6DigitsAhorro(tarjeta,wPINEncript,wPINBLOCKNew,wPINBLOCKConf);
		if (result!=null){
			//if(Constante.VER_LOG) System.out.println("- result="+result[0]+"|"+result[1]);
			if (!Constante.COD_RESP.equals(result[0])){
				String mensaje = result[1];
				throw new  ArrayRuleException(ConstanteError.GENERICO,mensaje);
			}
		}else{
			//System.out.println("processChangeKey6DigitsAhorro: Error el resultado del Web Service es Nulo");
			throw new SOAPException("","");
		}
	}
	
	/**
	 * 
	 * @param wTipDoc@		: Valores que puede tomar 1 = DNI, 2 = Carnet de FFAA, 3 = Carnet de FFPP
	 * @param nroDocumento  : Nro de documento
	 * @param wPINEncript   : Clave de 4 digitos
	 * @param wPINBLOCKNew  : nueva Actual de 6 digitos (clave a ser cambiada)
	 * @param wPINBLOCKConf : Nueva Clave de 6 digitos
	 * @throws Exception
	 */
	public void processChangeKey6DigitsCorriente(String wTipDoc,String nroDocumento,String wPINEncript,String wPINBLOCKNew,String wPINBLOCKConf)throws Exception{
		String[] result = UtilWebServices.getInstance().callWebServiceChangeKey6DigitsCorriente(wTipDoc,nroDocumento,wPINEncript,wPINBLOCKNew,wPINBLOCKConf);
		if (result!=null){
			//if(Constante.VER_LOG) System.out.println("- result="+result[0]+"|"+result[1]);
			if (!Constante.COD_RESP.equals(result[0])){
				String mensaje = result[1];
				throw new  ArrayRuleException(ConstanteError.GENERICO,mensaje);
			}
		}else{
			//System.out.println("processChangeKey6DigitsCorriente: Error el resultado del Web Service es Nulo");
			throw new SOAPException("","");
		}
	}
	
	/**
	 * @param nroTarjeta	: Numero de Tarjeta
	 * @param wPINEncript   : clave Encriptada de 4 digitos.
	 * @param wPINBLOCKNew  : clave de 6 digitos
	 * @param wPINBLOCKConf : confirmacion de clave de 6 digitos 
	 * @throws Exception
	 */
	public void processGenerationKey6DigitsAhorro(String nroTarjeta,String wPINEncript,String wPINBLOCKNew,String wPINBLOCKConf)throws Exception{
	    //if(Constante.VER_LOG) System.out.println("******** clave 4 digitos encriptado wPINEncript:"+wPINEncript);
	    Key4Digits key4Gen = new Key4Digits(); 
	    
	    wPINEncript =  key4Gen.desencriptar(wPINEncript);
	    //if(Constante.VER_LOG) System.out.println("++++++++ clave 4 digitos desencriptado wPINEncript:"+wPINEncript);
	    
		String[] result = UtilWebServices.getInstance().callWebServiceGenerationKey6DigitsAhorro(nroTarjeta,wPINEncript,wPINBLOCKNew,wPINBLOCKConf);
		if (result!=null){
			//if(Constante.VER_LOG) System.out.println("- result="+result[0]+"|"+result[1]);
			if (!Constante.COD_RESP.equals(result[0])){
				String mensaje = result[1];
				throw new  ArrayRuleException(ConstanteError.GENERICO,mensaje);
			}
		}else{
			//System.out.println("processGenerationKey6DigitsAhorro: Error el resultado del Web Service es Nulo");
			throw new SOAPException("","");
		}
	}
	
	public void processGenerationOlvidoKey6DigitsAhorro(String nroTarjeta,String wPINEncript,String wPINBLOCKNew,String wPINBLOCKConf)throws Exception{
	    //if(Constante.VER_LOG) System.out.println("******** clave 4 digitos encriptado wPINEncript:"+wPINEncript);
	    Key4Digits key4Gen = new Key4Digits(); 
	    
	    wPINEncript =  key4Gen.desencriptar(wPINEncript);
	    //if(Constante.VER_LOG) System.out.println("++++++++ clave 4 digitos desencriptado wPINEncript:"+wPINEncript);
	    
		String[] result = UtilWebServices.getInstance().callWebServiceGenerationOlvidoKey6DigitsAhorro(nroTarjeta,wPINEncript,wPINBLOCKNew,wPINBLOCKConf);
		if (result!=null){
			//if(Constante.VER_LOG) System.out.println("- result="+result[0]+"|"+result[1]);
			if (!Constante.COD_RESP.equals(result[0])){
				String mensaje = result[1];
				throw new  ArrayRuleException(ConstanteError.GENERICO,mensaje);
			}
		}else{
			//System.out.println("processGenerationKey6DigitsAhorro: Error el resultado del Web Service es Nulo");
			throw new SOAPException("","");
		}
	}
	
	
	public void processGenerationKey6DigitsCorriente(String wTipDoc,String nroDocumento,String wPINEncript,String wPINBLOCKNew,String wPINBLOCKConf)throws Exception{
		String[] result = UtilWebServices.getInstance().callWebServiceGenerationKey6DigitsCorriente(wTipDoc,nroDocumento,wPINEncript,wPINBLOCKNew,wPINBLOCKConf);
		if (result!=null){
			//if(Constante.VER_LOG) System.out.println("- result="+result[0]+"|"+result[1]);
			if (!Constante.COD_RESP.equals(result[0])){
				String mensaje = result[1];
				throw new  ArrayRuleException(ConstanteError.GENERICO,mensaje);
			}
		}else{
			//System.out.println("processGenerationKey6DigitsCorriente: Error el resultado del Web Service es Nulo");
			throw new SOAPException("","");
		}
	}
	
}
