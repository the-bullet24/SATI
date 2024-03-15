package pe.com.bn.sati.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.mail.SendFailedException;


import pe.com.bn.common.Constante;



public class LoggerSati {

	private String FQCN = "";
	private static LoggerSati log3 = LoggerSati.getInstance(LoggerSati.class.getName());

	protected LoggerSati(String name) {
		FQCN = name;
	}

	  public static LoggerSati getInstance(String name) {
		
		LoggerSati hub = new LoggerSati(name); 
	    return hub; 
	  }
	  
  
//	  public void error(SendFailedException e,String codigoError,String mensaje) {
//	  	
//	  }
	  
		public void error(Exception e,String codigoError,String mensaje) {

			if(e!=null) 
				e.printStackTrace();
			
			if(!isPrintLogErr("ERROR")) return;
			
			try {
				String formato = "[ERROR/" + FQCN 
				+ "|ERR:" + (codigoError==null?".":codigoError);
				formato = formato + "]-";
				formato = formato + mensaje + "|" ;
				String tracer="";
				if(e!=null){
					formato = formato  + "STRING:" + e.toString() + "|MESSAGE:" + e.getMessage() + "|CAUSA:" + e.getCause(); 
					if(e.getStackTrace()!=null){
						
						for(int i=0;i<e.getStackTrace().length;i++){
							//if (e.getStackTrace()[i].getMethodName().equals(metodo)){
							tracer = tracer + e.getStackTrace()[i].toString() + "\n";
							//	break;
							//}
						}
					}
					formato = formato  + "|TRACE:\n" + tracer ;
				}
				
				grabarLog(formato,"E");
				
			} catch (Exception e1) {
				log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
				e1.printStackTrace();
			}
			
		}
		
	public void debug(String metodo,String mensaje,String nivel) {

		if(!isPrintLogDebug("DEBUG",nivel)) return;
		
		try {
			String formato = "[DEBUG/" + FQCN + "/" + metodo + "]\n" ;
			formato = formato + mensaje;
			grabarLog(formato,"D");
			
		} catch (Exception e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			e.printStackTrace();
		}
		
	}
	
	
    boolean isPrintLogDebug(String tipo,String nivel){
    
	    try{
    		BnParametroLogger parametro = new BnParametroLogger();
    		BnParametroLogger.ParamLogger log = parametro.new ParamLogger();

	    	if(nivel.equals(Constante.LOGGER_DEBUG_NIVEL_1)){
	    		if(tipo.equals("DEBUG") && log.getLoggerPrintDebugNivel_1().equals("true")) return true;
	    	}
	    	if(nivel.equals(Constante.LOGGER_DEBUG_NIVEL_2)){
	    		if(tipo.equals("DEBUG") && log.getLoggerPrintDebugNivel_2().equals("true")) return true;
	    	}	    		
			
		   }catch(Exception e){
			   log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
		  	    	 e.printStackTrace();
	   }
	   return false;
    	
    }
    
    boolean isPrintLogErr(String tipo){
        
	    try{
    		BnParametroLogger parametro = new BnParametroLogger();
    		BnParametroLogger.ParamLogger log = parametro.new ParamLogger();
			if(tipo.equals("ERROR") && log.getLoggerPrintError().equals("true")) return true;
			
		   }catch(Exception e){
			   log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
		  	   e.printStackTrace();
	   }
	   return false;
    	
    }
    
    
    
     void grabarLog(String mensaje,String tipo){
    	
        String timestamp1,timestamp12="";
        String strMensaje = "";
        Calendar fecha = Calendar.getInstance();
        SimpleDateFormat formato =  new SimpleDateFormat("yyyy/MM/dd HH.mm.ss");
        timestamp1 = formato.format(fecha.getTime());
        strMensaje = "[" + timestamp1 + "]" + mensaje;
        
		BnParametroLogger parametro = new BnParametroLogger();
		BnParametroLogger.ParamLogger log = parametro.new ParamLogger();
		
		Calendar fechaActual = Calendar.getInstance();
	    SimpleDateFormat formato2 =  new SimpleDateFormat("yyyyMMdd");
	    timestamp12 = formato2.format(fechaActual.getTime());
		String exte =".log";
	    
        try{
	    	if(tipo.equals("E")){
	    		if (log.getErrLoggerFile().equals("true")){
	    			saveFile(log.getErrLoggerPath()+"_"+timestamp12+exte,strMensaje);
	    		}
	    		if (log.getErrLoggerConsole().equals("true")){
	    			
	    			System.out.println(strMensaje);
	    		}
	    	}
	    	if(tipo.equals("D")){
	    		if (log.getProcLoggerFile().equals("true")){
	    			saveFile(log.getProcLoggerPath()+"_"+timestamp12+exte,strMensaje);
	    		}
	    		if (log.getProcLoggerConsole().equals("true")){
	    			System.out.println(strMensaje);
	    		}
	    	}
	    	
  	      }catch(Exception e){
  	    	log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
  	    	 e.printStackTrace();
  	      }
  	 }
	
     void saveFile(String path,String mensaje){
    	 
        BufferedWriter bw = null;
        File fichero =null;
        try{
        	fichero = new File(path);
        	bw = new BufferedWriter(new FileWriter(fichero, true));
        	bw.write(mensaje + "\n");
        	//bw.flush();
        	bw.close();

  	      }catch(Exception e){
  	    	log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
  	    	 e.printStackTrace();
  	      }finally{
  	    	if(bw!=null){try {bw.close();bw=null;} catch (Exception e){}}
  	    	if(fichero!=null){try {fichero=null;} catch (Exception e){}}
  	    	  
  	      }
  	      
  	 }

}