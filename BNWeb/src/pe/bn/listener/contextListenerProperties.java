/*
 * Creado el 30/04/2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.bn.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import pe.com.bn.sati.common.LoggerSati;



/**
 * @author 
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class contextListenerProperties implements ServletContextListener{

	ServletContextEvent arg;
	  private static LoggerSati log3 = LoggerSati.getInstance(contextListenerProperties.class.getName());
	
	public void contextInitialized(ServletContextEvent arg0) {

		arg = arg0;
		//Thread connectThread = new Thread(this);
        //connectThread.start();
		run();
		
	}

    public void run() {
    	
		//CargaDatos Parametro
    	Util.cargaParametroEventInitApp(arg);
    	Parametro.getInstance();
    	Parametro.cargaKeyGateway();
		
				
    }

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Apéndice de método generado automáticamente
		
	}
    
	



}
