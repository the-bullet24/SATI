package pe.bn.listener;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class Util {
	
    private static final boolean READ_ONLY = false;
	private static final boolean READ_ONLW_UPLOAD = true;
	private static final boolean RESTRICT_BROWSING = false;
    private static final boolean RESTRICT_WHITELIST = false;
    private static final String RESTRICT_PATH = "/etc;/var";
    
	public static String stringNull(String obj,int tam){
		String valor = "";
		try {
			if (obj != null){
				if (obj.length()> tam){
					valor = obj.substring(0,tam);
				}	
				else
					valor = obj;
			}
		} catch (Exception e) {
		}
		return valor;
	}
	
	private static Logger logs = Logger.getLogger(Util.class.getName());
	
	public static String returReplace(String cadena){
		
		
		for(int i=0;i<cadena.length();i++)
		{
			
			if(cadena.substring(i,i+1).equals("?")){
				cadena=cadena.trim().replace('?',' ').trim();
			}
			
			
		}
		return cadena;
		
		
		
	}

	public static Bnmvf07Parametro getBnProperties(ServletContext contexto){
		
		Bnmvf07Parametro bnProperties;
		
		if(contexto!=null && contexto.getAttribute("bnProperties")!=null){
			bnProperties = (Bnmvf07Parametro)contexto.getAttribute("bnProperties");	
		}else{
			bnProperties = null;
			//cargaParametro(contexto);
			bnProperties = (Bnmvf07Parametro)contexto.getAttribute("bnProperties");
		}
    	return bnProperties;
	} 



	  public static void cargaParametroEventInitApp(ServletContextEvent arg0){
		  
//		  System.out.println("cargaParametroEventInitApp...");
		  
			ServletContext contexto = arg0.getServletContext();		
			//ResourceBundle rb = ResourceBundle.getBundle("parametro");		
		    //String fileConfig = rb.getString("bn.file.config");	    
		    //fileConfig = arg0.getServletContext().getRealPath("/WEB-INF/classes/"+ fileConfig);
		    //cargaParametro(contexto);
		    
	}
	  
  

		
//	private static void cargaParametro(ServletContext contexto){
//		
//		//Bnacf00Parametro parametro = new Bnacf00Parametro();
//		
//		Bnmvf07Parametro parametro = new Bnmvf07Parametro();
//		
//		SistemaParametro sistemaParametro = null;
//		
//		
//		try {
//			ResourceBundle rb = ResourceBundle.getBundle("parametro");
//			String keyPath = rb.getString("bn.claveSegura.keyPath");
//			String keyName = rb.getString("bn.claveSegura.keyName");
//			String sistema = rb.getString("bn.claveSegura.sistema");
//			String cuenta = rb.getString("bn.claveSegura.cuenta");
//			String semillaKey = rb.getString("bn.claveSegura.semillaKey");
//			String usuario = rb.getString("bn.claveSegura.usuario");
//			
//			String path= keyPath + "/" + keyName;
//			
//			byte[] clave = BNClaveSegura.encrypt(path, semillaKey);
//			
//			System.out.println("clave:"+clave);
//			
//			System.out.println("INSTANCE SERVICE PARAMETRO ");	
//			
//			ParametroInterfazProxy proxi1 = new ParametroInterfazProxy();
//			
//			sistemaParametro = proxi1.datoParametroService(sistema, cuenta, clave, usuario);
//			
//			System.out.println("sistemaParametro.getProceso().getCodigo():"+sistemaParametro.getProceso().getCodigo());
//			System.out.println("sistemaParametro.getProceso().getDescripcion():"+sistemaParametro.getProceso().getDescripcion());
//			
//			if(sistemaParametro!=null && sistemaParametro.getProceso()!=null){
//				String estado = sistemaParametro.getProceso().getCodigo();
//				
//				if(estado.equals(Constante.CONST_PROCESO_OK)){
//	
//					for(int t=0;t<sistemaParametro.getGrupoParametro().length;t++){
//						
//					
//							//Configuracion de Db App
//							if(sistemaParametro.getGrupoParametro()[t].getAliasGrupo().equals("JNDI/SERVICE")){
//								ParamConJDNI paramConJDNI = null;
//								if(sistemaParametro.getGrupoParametro()[t].getParametro() != null){
//									paramConJDNI = parametro.new ParamConJDNI();									
//									for(int m=0;m<sistemaParametro.getGrupoParametro()[t].getParametro().length;m++){
//										
//										if(sistemaParametro.getGrupoParametro()[t].getParametro()[m].getAliasParam().equals("JNDIOracleSpcl".trim())){
//											paramConJDNI.setParamJNDIOracleSpcl(sistemaParametro.getGrupoParametro()[t].getParametro()[m].getValorParam());	
//										}
//										
//										if(sistemaParametro.getGrupoParametro()[t].getParametro()[m].getAliasParam().equals("JNDISqlServerSbs".trim())){
//											paramConJDNI.setParamJNDISqlServerSbs(sistemaParametro.getGrupoParametro()[t].getParametro()[m].getValorParam());	
//										}
//										if(sistemaParametro.getGrupoParametro()[t].getParametro()[m].getAliasParam().equals("JNDIReniec".trim())){
//											paramConJDNI.setParamJNDIReniec(sistemaParametro.getGrupoParametro()[t].getParametro()[m].getValorParam());	
//										}	
//	
//									}
//								}
//							parametro.setParamConJNDI(paramConJDNI);
//						} 
//					}//Fin de FOR
//					
//					System.out.println(" subiendo a contexto ...."+parametro);
//					contexto.setAttribute("bnProperties",parametro);
//					
//					
//					
//				}else{
//					throw new Exception("COD:" + sistemaParametro.getProceso().getCodigo() 
//							+ " DESC:" + sistemaParametro.getProceso().getDescripcion());
//				}
//			}else{
//				
//			}
//				
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	
//	}

	  


	
	private static Logger log = Logger.getLogger(Util.class.getName());

    public static byte[] readFileKey(String filename){
    	
    	byte[] data = null;
    	File file = null;
    	InputStream is = null;
        try {
    	    file = new File(filename);
    	    if(!file.exists()){
    	    	throw new IOException("EL ARCHIVO NO EXISTE.");
    	    }
        	is = new FileInputStream(file);
            long length = file.length();
            if (length > Integer.MAX_VALUE) {
            	throw new IOException("EL ARCHIVO ES MUY LARGO.");
            }
            data = new byte[(int)length];
            int offset = 0;
            int numRead = 0;
            while (offset < data.length
                   && (numRead=is.read(data, offset, data.length-offset)) >= 0) {
                	offset += numRead;
            }
            
            if (offset < data.length) {
                throw new IOException("NO PODRIA COMPLETAR LA LECTURA DEL ARCHIVO.");
            }
            is.close();
		    
        } catch (IOException e) {
        	e.printStackTrace();
        	log.error("[sach-readFileKey] ERROR SOAP:" + e);
        	data = null;
        }finally{
        	if(is!= null){
        		try {is.close();} catch (Exception e) {}
        		is=null;
        	}
        	file=null;
        }

        return data;
    }
    
    
    

	public static ArrayList copyArray(ArrayList data){
		
		ArrayList newArr = new ArrayList();
		
		for(int i=0;i< data.size();i++){
			newArr.add(i,data.get(i));
		}
		return newArr;
	}
	
	public static List CopyArray(List data){
		
		List newArr = new ArrayList();
		
		
		for(int i=0;i< data.size();i++){
			newArr.add(i,data.get(i) );
		}
		return newArr;
	}
	


    public static String lpad(String valueToPad, String filler, int size) { 
        while (valueToPad.length() < size) {   
            valueToPad = filler + valueToPad;   
        }   
        return valueToPad;   
    }   
    public static String formatearFecha(String fecha){
    	try{
	    if(fecha==null || fecha.trim().equals("")) fecha="";
	    	else{
	    		fecha = fecha.substring(6)+"/"+fecha.substring(4,6)+"/"+fecha.substring(0,4);
	    	}
		return fecha;
    	}catch(Exception e){}
    	return fecha;
    }
    
    
    public static String formatearFechaToHost(String fecha){
    	try{
	 
	    		//30/11/2009  yyyymmdd
	    		fecha = fecha.substring(6,10)+fecha.substring(3,5)+fecha.substring(0,2);
	    
		return fecha;
    	}catch(Exception e){}
    	return fecha;
    } 
    
	public static String formatHoraHost(String hora){
		if(hora==null)
		return "000000";
		hora=hora.trim();
		int valIndex=hora.indexOf(":");
		if(valIndex!=-1)
		return hora.substring(0, valIndex)+""+hora.substring((valIndex+1), hora.length())+"00";
		
		
		return "000000";	
	}
		
    public static String formatearFecha(Date fechax, String formato){
    	try{
	    Date fec =fechax;
		SimpleDateFormat f =  new SimpleDateFormat(formato);
		return f.format(fec);
    	}catch(Exception e){}
    	return "";
    }
    
    public static String formatearFecha(String fecha, String formato){
    	try{
	    Date fec = parseDate(fecha);
		SimpleDateFormat f =  new SimpleDateFormat(formato);
		return f.format(fec);
    	}catch(Exception e){}
    	return fecha;
    }
	public static java.util.Date copyDate(String vari) throws ParseException{
		SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date d = null;
		if(vari == null || vari == "null" )
			vari ="10-10-2000";
			d=sdf.parse(vari);
		return d;
		
	}
	
	public static String formatearExpediente(String expediente,String formato){
		//tomando en cuenta de que el separador es el simbolo '-'
		if(formato.length()>expediente.length()){
			String cf = ""; 
			for(int i = 0,j=0 ; i < formato.length();i++){
				if(formato.charAt(i) =='-'){
					cf+='-';
				}
				else{
					cf+=expediente.charAt(j);
					j++;
				}
			}
			return cf;
		}
		return expediente;
	}
	
	public static String convertirFecha(Date fecha){
		 
		String tfecha = "";
		
		try{
			if(fecha!=null){
				int dia = fecha.getDate();
				int ndia = fecha.getDay();
				int mes = fecha.getMonth();
				int año = fecha.getYear();
				
				
				
				String ddia = "";
				switch(ndia){
					case 1: ddia = "Lunes "; break;
					case 2: ddia = "Martes "; break;
					case 3: ddia = "Miércoles "; break;
					case 4: ddia = "Jueves "; break;
					case 5: ddia = "Viernes "; break;
					case 6: ddia = "Sábado "; break;
					case 7: ddia = "Domingo "; break;
				}
				
				String dmes = "";
				switch(mes){
					case 0:  dmes = " de Enero "; break;
					case 1:  dmes = " de Febrero "; break;
					case 2:  dmes = " de Marzo "; break;
					case 3:  dmes = " de Abril "; break;
					case 4:  dmes = " de Mayo "; break;
					case 5:  dmes = " de Junio "; break;
					case 6:  dmes = " de Julio "; break;
					case 7:  dmes = " de Agosto "; break;
					case 8:  dmes = " de Setiembre "; break;
					case 9:  dmes = " de Octubre "; break;
					case 10: dmes = " de Noviembre "; break;
					case 11: dmes = " de Diciembre "; break;
				}
				
				año += 1900;
				
				tfecha = "Lima, " + ddia + ((dia<10)?("0"+ dia):String.valueOf(dia)) + dmes + "del " + año; 
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return tfecha;
	}
	
	public static boolean validar_RUC (String ruc){
		

		boolean result = false;
		
		try{
			//Validar tamaño
			long nroruc  = Long.parseLong(ruc);
			
			if(ruc.length() == 11){
				
				if(nroruc == 0){ 
					result = true;
				}else{
				
					String[] nruc = ruc.split("|");
					
					for(int i=1; i<nruc.length; i++){

					}
					
					if(
						( nruc[1].equals("1") && nruc[2].equals("0") )||
						( nruc[1].equals("1") && nruc[2].equals("5") )||
						( nruc[1].equals("1") && nruc[2].equals("7") )||
						( nruc[1].equals("2") && nruc[2].equals("0") )
					){
						
						int suma = 0;
						int[] multiplicantes = {5,4,3,2,7,6,5,4,3,2}; 
						for(int i=0; i<10; i++){
							suma += Long.parseLong(nruc[i+1]) * multiplicantes[i];
						}
						
						int residuo = (suma % 11);
						int resultado = 11 - residuo;
						
							
						// Comprobar resultados
						if(
							(resultado==11 && nruc[11].equals("1")) || 
							(resultado==10 && nruc[11].equals("0")) ||
							(resultado<10 && nruc[11].equals(String.valueOf(resultado)))
						  ){
							result = true;
						}
						
					}
				}
			}
			//Validar cabecera
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static String formatearMontoForDic(BigDecimal monto){		
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		simbolos.setGroupingSeparator(',');		
		DecimalFormat formateador = new DecimalFormat("###,###,###,###,###,##0.0000");
		formateador.setDecimalFormatSymbols(simbolos);
		return formateador.format(monto);		
	}
	
	public static String formatearMonto(BigDecimal monto){		
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		simbolos.setGroupingSeparator(',');		
		DecimalFormat formateador = new DecimalFormat("###,###,###,###,###,##0.00");
		formateador.setDecimalFormatSymbols(simbolos);
		return formateador.format(monto);		
	}

	public static void main(String[] args) {
		
		
		
		
	}
	public static String formatearMontoDec(BigDecimal monto){		
		BigDecimal montofinal = new BigDecimal(monto.toString()).setScale(2, BigDecimal.ROUND_HALF_UP);
		return montofinal.toString();		
	}
	
	public static String formatearMonto(Double monto){
		monto = parseDouble(monto);
		BigDecimal montofinal = new BigDecimal(monto.toString()).setScale(2, BigDecimal.ROUND_HALF_UP);
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		simbolos.setGroupingSeparator(',');		
		DecimalFormat formateador = new DecimalFormat("###,###,###,###,###,##0.00");
		formateador.setDecimalFormatSymbols(simbolos);
		return formateador.format(montofinal);		
	}
	
	public static String formatearNumero(BigDecimal monto, String formato){		
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		simbolos.setGroupingSeparator(',');		
		DecimalFormat formateador = new DecimalFormat(formato);
		formateador.setDecimalFormatSymbols(simbolos);
		return formateador.format(monto);		
	}
	public static String copy(String vari){
		if(vari == null || vari == "null" )
			vari="";
		return vari;
		
	}
	
	public static String replaceIlegalCharacterToSendMail(String cadena){
		char[] ilegalcharacters= {'á','é','í','ó','ú','ñ','Á','É','Í','Ó','Ú','Ñ'};
		String[] legalcharacters= {"&aacute;","&eacute;","&iacute;","&oacute;","&uacute;","&ntilde;","&Aacute;","&Eacute;","&Iacute;","&Oacute;","&Uacute;","&Ntilde;"};
		String c="", cadenalegal="";
		
		for(int i=0; i<cadena.length();i++){
			c= "" + cadena.charAt(i);
			for(int j=0;j<ilegalcharacters.length;j++){
				if(c.equals(""+ilegalcharacters[j])){
					c=legalcharacters[j];
					break;
				}
			}
			cadenalegal+=c;
		}
		return cadenalegal;
	}
	
	
	
	public static Date getFechaActual(){
		java.util.Date fecha = new Date();
		return fecha; 
	}
	
	public String cadenaHostEnvio(String cadenaEnvio,String cadenaAgregar,int tamano){
		int j = 0;
		if(cadenaEnvio.trim().length()<=tamano){
			j = tamano - cadenaEnvio.trim().length();
			for(int i=0;i<j;i++){
				cadenaEnvio=cadenaEnvio.concat(cadenaAgregar);
			}
		}
		else{
			cadenaEnvio = cadenaEnvio.substring(0,tamano);
		}
        return cadenaEnvio;	
    }
	public String cadenaNumHostEnvio(String cadenaEnvio,String cadenaAgregar,int tamano){
		int j = 0;
		if(cadenaEnvio.trim().length()<=tamano){
			 j = tamano - cadenaEnvio.trim().length();
    		 if(j>0){
    			for(int i=0;i<j;i++){
    				cadenaEnvio=cadenaAgregar+cadenaEnvio;
    			}
    		 }
		}
		else{
			cadenaEnvio = cadenaEnvio.substring(cadenaEnvio.length()-tamano,cadenaEnvio.length());
		}
        return cadenaEnvio;	
    }
	public String numHostEnvio(String montoEnvio, int numEnt,int numDec, String signo){
	int j = montoEnvio.indexOf(".");
	int tamano = montoEnvio.length();
	String parteEntera = "";
	String parteDecimal = "";
	 if(tamano<numEnt+numDec+1){
		if(j != -1){
			parteEntera = montoEnvio.substring(0,j);
			parteDecimal = montoEnvio.substring(j+1,tamano);
			int tamdec = numDec - parteDecimal.length();
			int tament = numEnt - parteEntera.length();
			if (tamdec > 0){
				for(int d=0; d<tamdec; d++){
					parteDecimal = parteDecimal+"0";
				}
			}
			if (tament > 0){
				for(int e=0; e<tament; e++){
				parteEntera = "0"+parteEntera;
				}
			}
		}
		else{
			if(montoEnvio != null && !montoEnvio.trim().equals("")){
				parteEntera = montoEnvio.trim();
				parteDecimal = "";
				int tamdec = numDec - parteDecimal.length();
				int tament = numEnt - parteEntera.length();
				if (tamdec > 0){
					for(int d=0; d<tamdec; d++){
						parteDecimal = parteDecimal+"0";
					}
				}
				if (tament > 0){
					for(int e=0; e<tament; e++){
					parteEntera = "0"+parteEntera;
					}
				}
			}
			else{
				for(int d=0; d<numDec; d++){
					parteDecimal = parteDecimal+"0";
				}
				for(int e=0; e<numEnt; e++){
					parteEntera = "0"+parteEntera;
				}
			}
		}
	}
	
		String todo= signo+parteEntera+"."+parteDecimal;
        return todo;	
    }
	
	
	public String montoTrama(String monto){
		try{
			if(monto!=null && monto.length()>0){
				int tam = monto.length();
				int punto = monto.indexOf(".");
				int parteEntera = Integer.parseInt(monto.substring(1,punto));
				String parteDecimal = monto.substring(punto+1,tam);
				
				String tempdec = "";
				for(int i = parteDecimal.length()-1; i>=0; i--){
					tempdec += parteDecimal.charAt(i);
				}
				
				tempdec = String.valueOf( Util.parseInt(tempdec).intValue() );
				parteDecimal = "";
				for(int i=0; i < tempdec.length() ;i++ ){
					parteDecimal = tempdec.charAt(i) + parteDecimal;
				}
				monto = parteEntera+"."+parteDecimal;
			}else{
				monto = "0.00";
			}
		}catch(Exception e){e.printStackTrace();}
		return monto;
	}
	
	
	
	
	public static final String copyString(String vari){
 		try {

 			if(vari == null || vari == "null" || vari == "")vari="";
 			if( vari == "" ){vari=null;}
 			return new String(vari);
 		} catch (Exception e) {
 			// TODO: handle exception
 			return null;
 		}
 	}
 	
 	
	public static final String copyStringc(String vari){
 		try {

 			if(vari == null || vari == "null" || vari == "")vari="";
 			if( vari == "" ){vari=null;}
 			return new String(vari);
 		} catch (Exception e) {
 			// TODO: handle exception
 			return "0";
 		}
 	}

	public static final Double copyDoublec(String vari){
 		
 		try {

 			if(vari == null || vari == "null" )
 				vari="0";
 			if( vari == "" ){vari=null;}
 			
 			return new Double(vari);
 		} catch (Exception e) {
 			// TODO: handle exception
 			
 			return new Double("0");
 		}
	}
	
	public static final Double copyDouble(String vari){
 		
 		try {

 			if(vari == null || vari == "null" )
 				vari="0";
 			if( vari == "" ){vari=null;}
 			
 			return new Double(vari);
 		} catch (Exception e) {
 			// TODO: handle exception
 			return null;
 		}
 		
 		
  
 		//return Double.parseDouble(vari);
 	}	
	public static final Long copyLong(String vari){
 		
 			
 		try {
 		
 			if(vari == null || vari == "null"  )
 				vari="0";
 			if( vari == "" ){vari="0";}
 			return new Long(vari);
 		} catch (Exception e) {
 			// TODO: handle exception
 			
 			return null;
 		} 
 	}


 	

	public static final Long copyLongc(String vari){
 		
 		try {
 			if(vari == null || vari == "null"  )
 				vari="0";
 			if( vari == "" ){vari="0";}
 			return new Long(vari);
 		} catch (Exception e) {
 			// TODO: handle exception
 			
 			return new Long("0");
 		} 
 	}
 	

	/***********************************************************/
	/**        METODOS DE VALIDACION DE TIPOS DE DATOS
	/***********************************************************/
	
	

	public static final BigDecimal parseBigDecimal(String value){
		BigDecimal result = new BigDecimal(0);
		try{
			value= value.trim();
			result = new BigDecimal(value);
		}catch(Exception e){} 
		return result;
	}
	
	
	public static final BigDecimal parseBigDecimal(Double value){
		BigDecimal result = new BigDecimal(0);
		try{	
			result = new BigDecimal(value.doubleValue());
		}catch(Exception e){} 
		return result;
	}
	
	public static final Double parseDouble(String value){
		Double result = new Double(0);
		try{
			value= value.trim();
			result = new Double(value);
		}catch(Exception e){} 
		return result;
	}
	public static final Double parseDouble(Double value){
		Double result = new Double(0);
		try{	
			result = new Double(value.doubleValue());
		}catch(Exception e){} 
		return result;
	}
	public static final Long parseLong(String value){
		Long result = new Long(0);
		try{
			value= value.trim();
			result = new Long(value);
		}catch(Exception e){ } 
		return result;
	}
	public static final Long parseLong(Long value){
		Long result = new Long(0);
		try{
			result = new Long(value.longValue());
		}catch(Exception e){} 
		return result;
	}
	public static final Long parseLong(Double value){
		Long result = new Long(0);
		try{
			result = new Long(value.longValue());
		}catch(Exception e){} 
		return result;
	}
	public static final Integer parseInt(String value){
		Integer result = new Integer(0);
		try{
			value= value.trim();
			result = new Integer(value);
		}catch(Exception e){} 
		return result;
	}
	public static final Integer parseInt(Integer value){
		Integer result = new Integer(0);
		try{
			result = new Integer(value.intValue());
		}catch(Exception e){} 
		return result;
	}
	public static final Integer parseInt(Double value){
		Integer result = new Integer(0);
		try{
			result = new Integer(value.intValue());
		}catch(Exception e){} 
		return result;
	}
	public static final String parseString(String value){
		String result = "";
		try{
			if(value==null) value = "";
			value = value.trim();
			result = value;
		}catch(Exception e){}
		return result;
	}
	public static final String parseNull(String value){
		String result = null;
		try{
			if(value==null || value.trim().length()==0) 
				result = null;
			else
				result = value.trim();
		}catch(Exception e){}
		return result;
	}
	public static final String parseString(Object value){
		String result = "";
		try{
			if(value!=null){
				result = (String)value; 
				result = result.trim();
			}
		}catch(Exception e){}
		return result;
	}
	public static final Date parseDate(String value){
		Date result = null;
		try{
			if(value.indexOf("-")>-1 || value.indexOf("/")>-1){
				String values[] = null;
				if(value.indexOf("-")>-1){
					values = value.split("-");
				}else{
					values = value.split("/");
				}
				
				if(values.length==3){
					String año = "", mes = "", dia = "";
					if(values[2].length()>values[0].length()){
						// Año es Value 2
						año = values[2];
						dia = values[0];
					}else if(values[0].length()>values[2].length()){
						año = values[0];
						dia = values[2];
					}else{
						año = values[2];
						dia = values[0];
					}
					mes = values[1];
					
					Calendar calendar = Calendar.getInstance();
					calendar.set(Integer.parseInt(año),
								Integer.parseInt(mes)-1,
								Integer.parseInt(dia)
							);
					
					result = calendar.getTime();
					
				}else{
					throw new Exception("Cantidad de partes de la fecha diferente de 3");
				}
			}
		}catch(Exception e){e.printStackTrace();}
		return result;
	}
	public static final String parseDate(Date value){

		Calendar c1 = new GregorianCalendar();
		try {
			c1.setTime(value);
			return 
				autocompletarCadena(String.valueOf(c1.get(c1.DAY_OF_MONTH)),'0',2) + "/" +
				autocompletarCadena(String.valueOf((c1.get(c1.MONTH)+1)),'0',2) + "/" +
				c1.get(c1.YEAR);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "";
		}

	}
	public static final String parseFullDate(Date value){

		Calendar c1 = new GregorianCalendar();
		try {
			c1.setTime(value);
			return 
				autocompletarCadena(String.valueOf(c1.get(c1.DAY_OF_MONTH)),'0',2) + "/" +
				autocompletarCadena(String.valueOf((c1.get(c1.MONTH)+1)),'0',2) + "/" +
				c1.get(c1.YEAR) + " " +
				autocompletarCadena(String.valueOf(c1.get(c1.HOUR)),'0',2) + ":" +
				autocompletarCadena(String.valueOf(c1.get(c1.MINUTE)),'0',2) + ":" +
				autocompletarCadena(String.valueOf(c1.get(c1.SECOND)),'0',2);
		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}

	}
	/***********************************************************/
	/**        METODOS PARA FILENAMES
	/***********************************************************/
	public static String filenameType(String filename){
		String result = "";
		try{
			String[] types = {"bmp","doc","exe","gif","html","java","jpeg","jpg","pdf","png","ppt","rar","txt","xls","zip"};
			if(filename.indexOf(".")>-1){
				String[] name = filename.split("\\.");
				for(int i=0; i<types.length; i++){
					if(name[name.length-1].equalsIgnoreCase(types[i])){
						result = types[i];
						break;
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		};
		return result;
	}
	public static String reduceFilename(String filename, int caracteres){
		String result = "";
		filename = parseString(filename);
		if(filename.length()>caracteres){
			if(filename.indexOf(".")>-1){
				String[] name = filename.split("\\.");
				
				int cantExt = name[name.length-1].length() + (name.length-1); // +1 por el punto
				int cantName = concat(interseccion(name,0,name.length-2),"").length();
				result = cortar(concat(interseccion(name,0,name.length-2),""), Math.abs(caracteres-cantExt), true) + "." + name[name.length-1];
				
			}else{
				result = cortar(filename, caracteres, true);
			}
		}else{
			result = filename;
		}
		return result;
	}
	public static String tamañoArchivo(long bytes){
		String result = "";
		if(bytes<1024){
			result = bytes + " bytes";
		}else if(bytes/1024 < 1024 ){
			result = new BigDecimal(bytes/1024).setScale(2) + " KB";
		}else if(bytes/1024/1024 < 1024){
			result = new BigDecimal(bytes/1024/1024).setScale(2) + " MB";
		}else if(bytes/1024/1024/1024 < 1024){
			result = new BigDecimal(bytes/1024/1024/1024).setScale(2) + " GB";
		}else if(bytes/1024/1024/1024/1024 < 1024){
			result = new BigDecimal(bytes/1024/1024/1024/1024).setScale(2) + " TB";
		}
		
		
		return result;
	}
	/***********************************************************/
	/**        METODOS PARA STRING
	/***********************************************************/
	public static String cortar(String cadena, int cantidad, boolean desdeIzquierda){
		String result = "";
		try{
			cadena = parseString(cadena);
			if(cadena.length()>cantidad){
				
				if(desdeIzquierda){
					for(int i=0; i<cantidad;i++){
						result += cadena.charAt(i);
					}
				}else{
					for(int i=cadena.length()-1; i>=cadena.length()-cantidad;i--){
						result = cadena.charAt(i) + result;
					}
				}
				
			}else if(cadena.length()<=cantidad){
				result = cadena;
			}
		}catch(Exception e){ e.printStackTrace();}
		return result;
	}
	
	public static String soloNumeros(String value){
		String result = null;
		try{
			if(value!=null){
				if(value.length()>0){
					result = "";
					for(int i = 0; i<value.length(); i++){
						if(value.charAt(i)>=48 && value.charAt(i)<=57){
							result += value.charAt(i); 
						}
					}
				}
			}
		}catch(Exception e){e.printStackTrace();}
		return result;
	}
	/**
	 * TODO noRepetir: de una cadena se evita que se repita un caracter consecutivo
	 * @param cadena
	 * @param CharAfiltrar
	 * @return
	 */
	public static String noRepetir(String cadena, char CharAfiltrar){
		String result = "";
		try{
			if(cadena!=null){
				boolean anterior = false;
				for(int i =0; i<cadena.length();i++){
					if(cadena.charAt(i) == CharAfiltrar){
						if(anterior==false){
							result += cadena.charAt(i);
							anterior = true;
						}
					}else{
						result += cadena.charAt(i);
						anterior = false;
					}
				}
			}
		}catch(Exception e){ e.printStackTrace();}
		return result;
	}
	public static String autocompletar(String cadena, char caracter,int cantidad,boolean desdeIzquierda){
		String result = "";
		try{
			if(cadena!=null){
				cadena = cadena.trim();
				if(desdeIzquierda){    // AAAAA00000
					for(int i=0; i<cantidad;i++){
						if(i<=cadena.length()-1){
							result += cadena.charAt(i);
						}else{
							result += caracter;
						}
					}
				}else{			// 00000AAAA
					for(int i=0; i<cantidad;i++){
						if(i<=cadena.length()-1){
							result += cadena.charAt(i);
						}else{
							result = caracter + result;
						}
					}
				}
			}
		}catch(Exception e){ e.printStackTrace(); }
		return result;
	}
	public static String autocompletarCadena(String cadena, char caracter,int cantidad){
		String result = "";
		try{
			if(cadena!=null && cadena.length() < cantidad){
				cadena = cadena.trim();
				result = cadena;
					for(int i=0; i<cantidad-cadena.length();i++){
							result = caracter + result;
					}
				
			}
			else{
			result = cadena;	
			}
		}catch(Exception e){ e.printStackTrace(); }
		return result;
	}
	public static final String nullSi(String value, String igual){
		String result = "";
		try{
			value = parseString(value);
			if(value.equals(parseString(igual))){
				result = null;
			}else{
				result = value;
			}
			
		}catch(Exception e){e.printStackTrace();}
		return result;
	}
	
	public static final boolean sonVacios(String[] values){
		boolean result = false;
		try{
			if(values!=null){
				for(int i = 0; i<values.length; i++){
					if(values[i]==null || values[i].length()==0){
						result = true; break;
					}
				}
			}
			
		}catch(Exception e){ result = true;}
		return result;
	}
	public static final boolean sonVaciosoZeros(String[] values){
		boolean result = false;
		try{
			if(values!=null){
				for(int i = 0; i<values.length; i++){
					if(values[i]==null || values[i].length()==0 || parseDouble(parseString(values[i])).doubleValue()==0 ){
						result = true; break;
					}
				}
			}
			
		}catch(Exception e){ result = true;}
		return result;
	}
	
	public static final String replaceSi (String value, String esiguala, String resultado, String sinoesigual){
		String result = "";
		try{
			if(value!=null && value.equals(esiguala))
				result = resultado;
			else
				result = sinoesigual;
		}catch(Exception e){}
		return result;
	}
	public static final Long replaceSi (String value, String esiguala, long resultado, long sinoesigual){
		Long result = new Long(sinoesigual);
		try{
			if(value.equals(esiguala))
				result = new Long(resultado);
			else
				result = new Long(sinoesigual);
		}catch(Exception e){}
		return result;
	}
	public static final String replaceSi (String value, long esiguala, String resultado, String sinoesigual){
		String result = sinoesigual;
		try{
			if(parseLong(value).longValue() == esiguala)
				result = resultado;
			else
				result = sinoesigual;
		}catch(Exception e){}
		return result;
	}
	public static final String getTimestamp(){
		Calendar fecha = Calendar.getInstance();
		SimpleDateFormat formato =  new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss.SSSSSS");
		return formato.format(fecha.getTime());
	}
	public static final String getTimestamp(String format){
		Calendar fecha = Calendar.getInstance();
		SimpleDateFormat formato =  new SimpleDateFormat(format);
		return formato.format(fecha.getTime());
	}
	public static final BigDecimal getRandom(long limite){
		return new BigDecimal( Math.random()*limite+1 );
	}
	/***********************************************************/
	/**        METODOS PARA ARRAYS 
	/***********************************************************/
	public static String[] interseccion(String[] array, int desde, int hasta){
		String[] result = null;
		try{
			if(array!=null){
				if(array.length>hasta && array.length>desde){
					if(desde<=hasta){
						if(desde>=0){
							result = new String[hasta-desde+1];
							for(int i=desde; i<=hasta; i++){
								result[i] = array[i];
							}
						}else{
							throw new Exception("DesdeMenorACeroException");
						}
					}else{
						throw new Exception("DesdeMayorQueHastaException");
					}
				}else{
					throw new Exception("HastaODesdeMayorATamañoArrayException");
				}
			}
		}catch(Exception e){e.printStackTrace();}

		return result;
	}
	public static String concat(String[] array, String encadenador){
		String result = "";
		try{
			if(array!=null){
				for(int i=0; i<array.length; i++){
					result += ((i==0)?"":encadenador) + parseString(array[i]);
				}
			}
		}catch(Exception e){e.printStackTrace();}
		log.error("CONCAT: " + result);
		return result;
	}
	public static String concat(List array, String encadenador){
		String result = "";
		try{
			if(array!=null){
				for(int i=0; i<array.size(); i++){
					result += ((i==0)?"":encadenador) + parseString(array.get(i));
				}
			}
		}catch(Exception e){e.printStackTrace();}
		log.error("CONCAT: " + result);
		return result;
	}
	public static String concat(StackTraceElement[] array, String encadenador){
		String result = "";
		try{
			if(array!=null){
				for(int i=0; i<array.length; i++){
					result += ((i==0)?"":encadenador) + parseString(array[i].toString());
				}
			}
		}catch(Exception e){e.printStackTrace();}
		log.error("CONCAT: " + result);
		return result;
	}
	public static String enmascararTramaEnvio(String value){
		if(value==null) return null;
		
		value = value.replaceAll("ñ", "#");
		value = value.replaceAll("Ñ", "#");
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
		
		return value;
	}
	public static String enmascararTramaRecepcion(String value){
		if(value==null) return null;
		
		value = value.replaceAll("#", "Ñ");
		
		return value;
	}
	public static ArrayList ArrayToList(String[] value){
		
		ArrayList result = null;
		
		try{
			if(value!=null && value.length>0){
				result = new ArrayList();
				for(int i = 0; i<value.length; i++){
					result.add( value[i] );
				}
			}
		}catch (Exception e){}
		
		return result;
		
	}
	
	/*********************************************************
	 *   RENIEC.   DESCOMPOSICION DE NOMBRE Y APELLIDO CASADA
	 * ******************************************************/
	
	public static class RENIEC {
		
		public static String[] getApellidosMaternoCasada(String apeMat, String sexo, String estadoCivil){
			
			sexo = parseString(sexo);
			estadoCivil = parseString(estadoCivil);
			apeMat = parseString(apeMat);
			String apeCas = "";
			
			if(sexo.equals("2") && estadoCivil.equals("2")){
				// Femenino y casada
				
				String[] array = noRepetir(apeMat, ' ').toUpperCase().split(" ");
				String apemat = "", apecas = "";
				boolean deanterior=false;
				for(int a=0;a<array.length;a++){
					
					if(array[a].trim().equals("DE")){
						if(a+1<array.length){
							if(array[a+1].trim().equals("LOS") ||
									array[a+1].trim().equals("LA") ||
									array[a+1].trim().equals("SAN") ||
									array[a+1].trim().equals("SANTA")){
								String ape="";
								if(a+2<array.length)
									ape = array[a+2];
								if(deanterior){
									apecas = array[a] + " " + array[a+1] + ((ape.trim().length()>0)?" ":"") + ape;
								}else{
									if(apemat.trim().length()==0){
										apemat = array[a] + " " + array[a+1] + ((ape.trim().length()>0)?" ":"") + ape;
									}else{
										apecas = array[a] + " " + array[a+1] + ((ape.trim().length()>0)?" ":"") + ape;
									}
									
								}
								a+=2;
								deanterior=false;
							}else if(array[a+1].trim().equals("DE")){
								deanterior=true;
							}else{
								apecas = array[a+1];
								a++;
							}
						}
					}else{
						if(deanterior==false){
							apemat = array[a];
						}
					}
				}
				
				apeMat=apemat;
				apeCas=apecas;
			}
			
			return new String[]{apeMat, apeCas};
		}
		
		
		
		
		public static String[] getNombresPrimeroSegundo(String nombres){
			String priNom="", segNom = "";
			
			nombres = parseString(nombres);
			if(nombres.length()>0){
				String pri="",seg="";
				boolean bpri=false, bseg=false;
				String[] array = noRepetir(nombres, ' ').split(" ");
				for(int i=0;i<array.length;i++){
					if(array[i].equals("DE") || 
							array[i].equals("DEL")){
						// Compuesto
						if(i+1<array.length){
							if(array[i+1].equals("LA") ||
								array[i+1].equals("LOS") ||
								array[i+1].equals("LAS")){
								
								if(i+2<array.length){
									if(bseg){
										seg += " " + array[i] + " " + array[i+1] + " " + array[i+2];
									}else{
										if(bpri){
											pri += " " + array[i] + " " + array[i+1] + " " + array[i+2];
										}else{
											pri = array[i] + " " + array[i+1] + " " + array[i+2];
											bpri =true;
										}
									}
									i++;
								}
							}else{
								if(bseg){
									seg += " " + array[i] + " " + array[i+1];
								}else{
									if(bpri){
										pri += " " + array[i] + " " + array[i+1];
									}else{
										pri = array[i] + " " + array[i+1];
										bpri =true;
									}
								}
							}
							i++;
						}
						
					}else{
						if(bpri){//Existe primer nombre
							if(bseg){ // Existe segundo nombre
								seg += " " + array[i]; // Se adjunta siguiente nombre
							}else{
								seg = array[i];
								bseg = true;
							}
						}else{
							pri = array[i];
							bpri = true;
						}
					}
				}
				
				priNom = pri;
				segNom = seg;
			}
			
			return new String[]{priNom, segNom};
			
		}
		
		
		
		
		
	}
	
	
	
	
	/***********************************************************/
	/**        METODOS PARA CONTROL JSON (AJAX)
	/***********************************************************/
	public static class JSON {
		ArrayList listado;
		String root = "root";
		public JSON(){
			 listado = new ArrayList();
		}
		public void setParameter(String root){
			this.root = root;
		}
		public void put(String key, BigDecimal value){
			listado.add(new String[]{key,value.toString(),""});
		}
		public void put(String key, String value){
			listado.add(new String[]{key,parseString(value),"'"});
		}
		public void put(String key, Double value){
			listado.add(new String[]{key,parseDouble(value).toString(),""});
		}
		public void put(String key, Integer value){
			listado.add(new String[]{key,parseInt(value).toString(),""});
		}
		public void put(String key, Long value){
			listado.add(new String[]{key,parseLong(value).toString(),""});
		}
		public void put(String key, String value, int scale){
			value = parseString(value);
			BigDecimal tmp = new BigDecimal(value).setScale(scale, BigDecimal.ROUND_HALF_UP);
			put(key, tmp);
		}
		public void put(String key, Integer value, int scale){
			value = parseInt(value);
			BigDecimal tmp = new BigDecimal(value.intValue()).setScale(scale, BigDecimal.ROUND_HALF_UP);
			put(key, tmp);
		}
		public void put(String key, Long value, int scale){
			value = parseLong(value);
			BigDecimal tmp = new BigDecimal(value.longValue()).setScale(scale, BigDecimal.ROUND_HALF_UP);
			put(key, tmp);
		}
		public void put(String key, Double value, int scale){
			value = parseDouble(value);
			BigDecimal tmp = new BigDecimal(value.doubleValue()).setScale(scale, BigDecimal.ROUND_HALF_UP);
			put(key, tmp);
		}
		public String getFormat(){
			StringBuffer result = new StringBuffer("{");
			Iterator iterat = listado.iterator();
			int c = 0;
			while(iterat.hasNext()){
				String[] i = (String[])iterat.next();
				if(i!=null)
					result.append((c==0?"":",") + i[0] + ":" + i[2] + i[1] + i[2] );
				c++;
			}
			result.append("}");
			
			return result.toString();
		}
		
		public String toXML(String root){
			StringBuffer result = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			Iterator iterat = listado.iterator();
			result.append("<" + root + ">");
			int c = 0;
			while(iterat.hasNext()){
				String[] i = (String[])iterat.next();
				if(i!=null){
					result.append("<" + i[0] + ">" + i[1] + "</" + i[0] + ">");
				}
				c++;
			}
			result.append("</" + root + ">");
			
			return result.toString();
		}
		public String toXMLWithoutHeader(String root){
			StringBuffer result = new StringBuffer("");
			Iterator iterat = listado.iterator();
			result.append("<" + root + ">");
			int c = 0;
			while(iterat.hasNext()){
				String[] i = (String[])iterat.next();
				if(i!=null){
					result.append("<" + i[0] + ">" + i[1] + "</" + i[0] + ">");
				}
				c++;
			}
			result.append("</" + root + ">");
			
			return result.toString();
		}
		public String toXML(){
			return toXML(this.root);
		}
		public String toXMLWithoutHeader(){
			return toXMLWithoutHeader(this.root);
		}
		public String toString() {
			String res = super.toString();
			res = getFormat();
			return res;
		}
	}
	
	public static class JSONArray {
		
		
		
		public ArrayList getListado() {
			return listado;
		}
		public void setListado(ArrayList listado) {
			this.listado = listado;
		}
		ArrayList listado;
		String root="root";
		String child="child";
		public JSONArray(){
			 listado = new ArrayList();
		}
		public void put(JSON jsonObject){
			listado.add(jsonObject);
		}
		public void setParameters(String root, String child){
			this.root = root;
			this.child = child;
		}
		public String getFormat(){
			StringBuffer result = new StringBuffer("[");
			Iterator iterat = listado.iterator();
			int c = 0;
			while(iterat.hasNext()){
				JSON i = (JSON)iterat.next();
				result.append((c==0?"":",") + i.getFormat());
				c++;
			}
			result.append("]");
			
			return result.toString();
		}
		
		public String toXML(String root, String child){
			StringBuffer result = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			Iterator iterat = listado.iterator();
			result.append("<" + root + ">");
			int c = 0;
			while(iterat.hasNext()){
				JSON i = (JSON)iterat.next();
				result.append(i.toXML(child));
				c++;
			}
			result.append("</" + root + ">");
			
			return result.toString();
		}
		public String toXML(){
			return toXML(root, child);
		}
		/* (sin Javadoc)
		 * @see java.lang.Object#toString()
		 */
		
		public String toString() {
			String res = super.toString();
			res = getFormat();
			return res;
		}
	}

	public static class Ajax {
		public static void sendHeader(String header, String json, HttpServletResponse response){
			if(json!=null){
				response.setHeader(header, json);
				response.setHeader("Cache-Control", "no-cache");
			}
		}
		public static void sendHeader(String header, JSON json, HttpServletResponse response){
			if(json!=null){
				response.setHeader(header, json.toString());
				response.setHeader("Cache-Control", "no-cache");
			}
		}
		public static void sendHeader(String header, JSONArray jsonarray, HttpServletResponse response){
			if(jsonarray!=null){
				response.setHeader(header, jsonarray.toString());
				response.setHeader("Cache-Control", "no-cache");
			}
		}
		public static void sendXJSON(String json, HttpServletResponse response){
			if(json!=null){
				response.setHeader("X-JSON", json);
				response.setHeader("Cache-Control", "no-cache");
			}
		}
		public static void sendXJSON(JSON json, HttpServletResponse response){
			if(json!=null){
				response.setHeader("X-JSON", json.toString());
				response.setHeader("Cache-Control", "no-cache");
			}
		}
		public static void sendXJSON(JSONArray jsonarray, HttpServletResponse response){
			if(jsonarray!=null){
				response.setHeader("X-JSON", jsonarray.toString());
				response.setHeader("Cache-Control", "no-cache");
			}
		}
		public static void sendXML(String contenido, HttpServletResponse response) throws IOException{
			response.setContentType("text/xml; charset=UTF-8");
			String content = contenido;
			if(parseString(content).length()==0){
				content = "<NoRegisters />";
			}
			response.getWriter().write(content);
		}
		public static void sendXML(JSON json, HttpServletResponse response) throws IOException{
			response.setContentType("text/xml; charset=UTF-8");
			String content = null;
			if(json==null){
				content = "<NoRegisters />";
			}else{
				content=json.toXMLWithoutHeader();
				if(parseString(content).length()==0){
					content = "<MalFormedXML />";
				}
			}
			response.getWriter().write(content);
		}
		public static void sendXML(JSONArray jsonarray, HttpServletResponse response) throws IOException{
			response.setContentType("text/xml; charset=UTF-8");
			String content = null;
			if(jsonarray==null){
				content = "<NoRegisters />";
			}else{
				content=jsonarray.toXML();
				if(parseString(content).length()==0){
					content = "<MalFormedXML />";
				}
			}
			response.getWriter().write(content);
		}
	}
	
	public static String mascaraTrama(String cadena, char caracterOld, char caracterNew){
		int tamCadena=0;
		if(cadena==null || cadena.trim().equals("")){
			return "";
		}
		else{
			return cadena.toUpperCase().replace(caracterOld, caracterNew);
		}
		
	}
	
	public static class Error {
		public String indicador;
		public String error;
		public String detail;
		public Error(String indicador, String error, String detail){
			this();
			this.indicador = indicador;
			this.error = error;
			this.detail = detail;
		}
		public Error(String error, String detail){
			this();
			this.error = error;
			this.detail = detail;
		}
		public Error(String error){
			this();
			this.error = error;
		}
		public Error(){
			this.indicador = "";
			this.error = "";
			this.detail = "";
		}
		
		
		
		/**
		 * @return Devuelve detail.
		 */
		public String getDetail() {
			return detail;
		}
		/**
		 * @param detail El detail a establecer.
		 */
		public void setDetail(String detail) {
			this.detail = detail;
		}
		/**
		 * @return Devuelve error.
		 */
		public String getError() {
			return error;
		}
		/**
		 * @param error El error a establecer.
		 */
		public void setError(String error) {
			this.error = error;
		}
		/**
		 * @return Devuelve indicador.
		 */
		public String getIndicador() {
			return indicador;
		}
		/**
		 * @param indicador El indicador a establecer.
		 */
		public void setIndicador(String indicador) {
			this.indicador = indicador;
		}
		/* (sin Javadoc)
		 * @see java.lang.Object#toString()
		 */
		public String toString() {
			//return super.toString();
			
			String ret = super.toString();
			
			if(error!=null && error.length()>0){
				
				ret = "<label>" + error + "</label>";
				
				if(detail!=null && detail.length()>0){
					
					ret += "<label>" + detail + "</label>";
					
				}
				
			}
			
			

			return ret;
		}
	}
	
	
    public static boolean isAllowed(File path, boolean write) throws IOException{
		if (READ_ONLY && write) return false;
		if (RESTRICT_BROWSING) {
            StringTokenizer stk = new StringTokenizer(RESTRICT_PATH, ";");
            while (stk.hasMoreTokens()){
			    if (path!=null && path.getCanonicalPath().startsWith(stk.nextToken()))
                    return RESTRICT_WHITELIST;
            }
            return !RESTRICT_WHITELIST;
		}
		else return true;
	}
    
    public static void copyStreamsWithoutClose(InputStream in, OutputStream out, byte[] buffer)
	throws IOException {
		int b;
		while ((b = in.read(buffer)) != -1)
			out.write(buffer, 0, b);
	}
    
    public static String removeZero(String str)
    {
        // Count leading zeros
        int i = 0;
        while (i < str.length() && str.charAt(i) == '0')
            i++;
  
        // Convert str into StringBuffer as Strings
        // are immutable.
        StringBuffer sb = new StringBuffer(str);
  
        // The  StringBuffer replace function removes
        // i characters from given index (0 here)
        sb.replace(0, i, "");
  
        return sb.toString();  // return in String
    }
}

