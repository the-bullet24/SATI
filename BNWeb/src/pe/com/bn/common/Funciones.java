package pe.com.bn.common;

import java.math.BigDecimal;

import pe.bn.servlet.ServletController;
import pe.com.bn.comp.cryto.service.BNClaveSegura;
import pe.com.bn.comp.ws.bean.SistemaParametro;
import pe.com.bn.comp.ws.service.ParametroInterfazProxy;
import pe.com.bn.sati.common.LoggerSati;
import pe.com.bn.sati.domain.BnwsParametro;
import pe.com.bn.sati.domain.BnwsParametro.ParamUrl;
import pe.cosapi.common.Constante;
import pe.cosapi.domain.impl.DetalleAyudaDatosImpl;

import java.text.StringCharacterIterator;
import java.text.CharacterIterator;
import java.util.ArrayList;
import java.util.ResourceBundle;
public class Funciones{
	private static LoggerSati log3 = LoggerSati.getInstance(Funciones.class.getName());
	

	
	private static StringBuffer sb;
	private static StringCharacterIterator sci;
	
  
	public static String setNumerotarjetaAsteriscos(String numeroTarjeta) {

		if (numeroTarjeta != null && numeroTarjeta.trim().length() > 8) {
			Integer l = numeroTarjeta.trim().length();
			return numeroTarjeta.trim().substring(0, 4) + "****" + numeroTarjeta.trim().substring(l - 4, l);
		}
		return "";
	}
	
  
	public static String convertArrToString(String [] finArr){
		
		String fin="";
		for (int i = 0; i < finArr.length; i++) {
			fin=fin+rpad(finArr[i].toString(), "0", 3);
		}
		
		return fin;
	}

	public static String izquierda (String stringToPad, String padder, int size) {
		if (padder.length() == 0) {
			return stringToPad;
		}
		sb = new StringBuffer(size);
		sci  = new StringCharacterIterator(padder);
 
        while (sb.length() < (size - stringToPad.length())) {
			for (char ch = sci.first(); ch != CharacterIterator.DONE ; ch = sci.next()) {
				if (sb.length() <  size - stringToPad.length()) {
					sb.insert(  sb.length(),String.valueOf(ch));
				}
			}
		}
		return sb.append(stringToPad).toString();
	}

    public static String lpad(String valueToPad, String filler, int size) { 
        while (valueToPad.length() < size) {   
            valueToPad = filler + valueToPad;   
        }   
        return valueToPad;   
    }

    
    public static String rpad(String valueToPad, String filler, int size) { 
        while (valueToPad.length() < size) {   
            valueToPad = valueToPad+filler;   
        }   
        return valueToPad;   
    }   
    


	 
	public static String enmascararTramaRecepcion(String value){
		if(value==null) return null;
		
		value = value.replaceAll("#", "Ñ");
		
		return value;
	}
	
	public static String enmascararTramaEnvio(String value){
		if(value==null) return null;
		
		value = value.replaceAll("ñ", "");
		value = value.replaceAll("Ñ", "");
		value = value.replaceAll("Á", "A");
		value = value.replaceAll("á", "A");
		value = value.replaceAll("Ä", "A");
		value = value.replaceAll("ä", "A");
		value = value.replaceAll("É", "E");
		value = value.replaceAll("é", "E");
		value = value.replaceAll("Ë", "E");
		value = value.replaceAll("ë", "E");
		value = value.replaceAll("Í", "I");
		value = value.replaceAll("í", "I");
		value = value.replaceAll("Ï", "I");
		value = value.replaceAll("ï", "I");
		value = value.replaceAll("Ó", "O");
		value = value.replaceAll("ó", "O");
		value = value.replaceAll("Ö", "O");
		value = value.replaceAll("ö", "O");
		value = value.replaceAll("Ú", "U");
		value = value.replaceAll("ú", "U");
		value = value.replaceAll("Ü", "U");
		value = value.replaceAll("ü", "U");
		value = value.replaceAll("#", "");
		value = value.replaceAll("&", "");
		value = value.replaceAll("", "");
		
		
		return value;
	}

	/***********************************************************/
	/**        METODOS DE VALIDACION DE TIPOS DE DATOS
	/***********************************************************/
	
	

	public static final BigDecimal parseBigDecimal(String value){
		BigDecimal result = new BigDecimal(0);
		try{
			value= value.trim();
			result = new BigDecimal(value);
		}catch(Exception e){
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			
		} 
		return result;
	}
	
	
	public static final BigDecimal parseBigDecimal(Double value){
		BigDecimal result = new BigDecimal(0);
		try{	
			result = new BigDecimal(value.doubleValue());
		}catch(Exception e){log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
		} 
		return result;
	}
	
	public static final Double parseDouble(String value){
		Double result = new Double(0);
		try{
			value= value.trim();
			result = new Double(value);
		}catch(Exception e){
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
		} 
		return result;
	}
	public static final Double parseDouble(Double value){
		Double result = new Double(0);
		try{	
			result = new Double(value.doubleValue());
		}catch(Exception e){log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");} 
		return result;
	}
	public static final Long parseLong(String value){
		Long result = new Long(0);
		try{
			value= value.trim();
			result = new Long(value);
		}catch(Exception e){ log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");} 
		return result;
	}
	public static final Long parseLong(Long value){
		Long result = new Long(0);
		try{
			result = new Long(value.longValue());
		}catch(Exception e){log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");} 
		return result;
	}
	public static final Long parseLong(Double value){
		Long result = new Long(0);
		try{
			result = new Long(value.longValue());
		}catch(Exception e){log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");} 
		return result;
	}
	public static final Integer parseInt(String value){
		Integer result = new Integer(0);
		try{
			value= value.trim();
			result = new Integer(value);
		}catch(Exception e){log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");} 
		return result;
	}
	public static final Integer parseInt(Integer value){
		Integer result = new Integer(0);
		try{
			result = new Integer(value.intValue());
		}catch(Exception e){log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");} 
		return result;
	}
	public static final Integer parseInt(Double value){
		Integer result = new Integer(0);
		try{
			result = new Integer(value.intValue());
		}catch(Exception e){log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");} 
		return result;
	}
	public static final String parseString(String value){
		String result = "";
		try{
			if(value==null) value = "";
			value = value.trim();
			result = value;
		}catch(Exception e){log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");}
		return result;
	}
	public static final String parseNull(String value){
		String result = null;
		try{
			if(value==null || value.trim().length()==0) 
				result = null;
			else
				result = value.trim();
		}catch(Exception e){log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");}
		return result;
	}
	public static final String parseString(Object value){
		String result = "";
		try{
			if(value!=null){
				result = (String)value; 
				result = result.trim();
			}
		}catch(Exception e){log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");}
		return result;
	}
	
	public static final String monedaBySimbolo(String simbolo){
		String moneda = "";
		if(simbolo.equals(Constante.SIMBOLO_MONEDA_SOL)){
			moneda = Constante.NOMBRE_MONEDA_SOL;
		}else if(simbolo.equals(Constante.SIMBOLO_MONEDA_DOLAR)){
			moneda =Constante.NOMBRE_MONEDA_DOLAR ;
		}
		return moneda;
	}
	
	
	
	public static ParamUrl  invokeServiceUrl(){

		SistemaParametro sistemaParametro = null;
		ParamUrl paramUrl = null;

		try
		{
			ResourceBundle rb = ResourceBundle.getBundle("parametro");
			String keyPath = rb.getString("bn.claveSegura.keyPath");
			String keyName = rb.getString("bn.claveSegura.keyName");
			String sistema = rb.getString("bn.claveSegura.sistema");
			String cuenta = rb.getString("bn.claveSegura.cuenta");
			String semillaKey = rb.getString("bn.claveSegura.semillaKey");
			String usuario = rb.getString("bn.claveSegura.usuario");
			//System.out.println("eors_datos:"+keyPath+keyName+sistema+cuenta+semillaKey+usuario);
			String path= keyPath + "/" + keyName;

			byte[] clave = BNClaveSegura.encrypt(path, semillaKey);

			ParametroInterfazProxy proxi1 = new ParametroInterfazProxy();
			log3.debug("invokeWebServiceUrl", "SABM INSTANCE SERVICE PARAMETRO ","1");
			sistemaParametro = proxi1.datoParametroService(sistema, cuenta, clave, usuario);
			log3.debug("invokeWebServiceUrl", "SABM RESULTADO SERVICE PARAMETRO:"+sistemaParametro.getProceso().getDescripcion(),"1");

			if(sistemaParametro!=null && sistemaParametro.getProceso()!=null)
			{
				String estado = sistemaParametro.getProceso().getCodigo();
				String descest= sistemaParametro.getProceso().getDescripcion();
				if(estado.equals(Constante.CONST_PROCESO_OK)){
					for(int t=0;t<sistemaParametro.getGrupoParametro().length;t++){
						//Configuracion de Db App
						if(sistemaParametro.getGrupoParametro()[t].getAliasGrupo().equals("SERVICES")){
							if(sistemaParametro.getGrupoParametro()[t].getParametro() != null){
								paramUrl = (new BnwsParametro()).new ParamUrl();
								for(int m=0;m<sistemaParametro.getGrupoParametro()[t].getParametro().length;m++){
									if(sistemaParametro.getGrupoParametro()[t].getParametro()[m].getAliasParam().equals("url.servidorCorreo".trim())){
										paramUrl.setParamUrlUrl(sistemaParametro.getGrupoParametro()[t].getParametro()[m].getValorParam());
									}

								}
							}
					}



						//Configuracion de JNDI/SERVICE
					}//Fin de FOR

				}else{
					log3.debug("invokeWebServiceUrl", "Parametros retorno de WS:" + estado+"["+descest+"]","1");
				}
			}else{
				log3.debug("invokeWebServiceUrl", "sistemaParametro es NULL","1");
			}

		} catch (Exception e) {

			log3.error(e,"9993","");
		}


		log3.debug("invokeWebServiceUrl", "Parametro URL Inicializado:" + paramUrl,"1");


		return paramUrl;
	}
	
	
	public static java.lang.String getServiceAdress() {
    	    	
    	DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
		String url="";
		
		try {
			ArrayList<DetalleAyudaDatosImpl> lista0 = (ArrayList<DetalleAyudaDatosImpl>) dHelp_.getListDetalleAyuda(Constante.COD_HLP_CORREO);
//			System.out.println("lista0.get(i).getDescripcionDetalle():::"+lista0.get(0).getDescripcionDetalle());
			url=lista0.get(0).getDescripcionDetalle();
			Constante.BN_PARAM_SERVICE_CORREO=url;
		} catch (Exception e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		
//    	System.out.println("url servidor correo::"+url);
    	return url;
    	
    	
    }
	
	public static String obtenerOperadorLetraToNumero(String operador) {
		String numOperador="";
		try {

			if(operador.toUpperCase().trim().equals("1")){
				numOperador="M";
			}else if(operador.toUpperCase().trim().equals("2")){
				numOperador="C";
			}else if(operador.toUpperCase().trim().equals("3")){
				numOperador="E";
			}else if(operador.toUpperCase().trim().equals("4")){
				numOperador="B";
			}

		} catch (Exception e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}

		 return numOperador;

	}	
	
	
	public static String elmiminarCerosAlaIzquierda(String texto) {
		String valor = "";
		for (int i = 0; i < texto.length(); i++) {
			if (texto.charAt(i) != '0') {
				valor = texto.substring(i);
				break;
			}
		}

		return valor;
	}
	
	public static String llenarCerosAlaIzquierda(String text, int longitud){
		StringBuffer sb = new StringBuffer(text);
		
		for (int i =0; i<longitud - text.length();i++) {
		  sb.append("0");
		}
		return sb.toString();
	}
	
	public static String formatearCuenta1(String numCuenta){
		
		if(numCuenta == null || numCuenta.isEmpty()){
			return "";
		}else{
			return numCuenta = numCuenta.substring(7);
		}
	}
	
	public static String formatearCuenta2(String numCuenta){
		
		if(numCuenta == null || numCuenta.isEmpty()){
			return "";
		}else{
			numCuenta = numCuenta.substring(7);
			numCuenta = numCuenta.substring(0,2)+"-"+numCuenta.substring(2,5)+"-"+numCuenta.substring(5);			
			return numCuenta;
		}
	}
	
	public static String llenarCerosAlaIzquierdaV2(String text, int longitud){
		
		 String formatted = String.format("%0" + longitud + "d", Integer.valueOf(text));
		
		return formatted;
	}
}
