/*
 * Creado el 06/02/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.bn.servlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.common.Constante;
/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class InitLog4j extends HttpServlet{
	private static LoggerSati log3 = LoggerSati.getInstance(InitLog4j.class.getName());

	//private static Logger flog = Logger.getLogger(InitLog4j.class);
	
	
    public void init() throws ServletException {
	    super.init();	    
		String prefix = getServletContext().getRealPath(java.io.File.separator);
	    //String file = getInitParameter("log4j");
	   /* if(file == null || file.length() == 0 || !(new File(prefix+file)).isFile()){
		    throw new ServletException();
	    }*/
	    try {
	        //PropertyConfigurator.configure(prefix + file);
		   // flog.info("******************Se inició satisfactoriamente el Log4j****************");
	    }
	    catch (Exception e) {
	    	log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
	        e.printStackTrace();
			System.exit(0);
	    }
	    
	    
	}

	/** @modelguid {77FE7364-15A6-4AC9-B867-3436956B7C6D} */
	public void destroy() {
        super.destroy();
    }
}
