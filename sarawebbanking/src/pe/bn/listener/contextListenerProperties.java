package pe.bn.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;



public class contextListenerProperties  implements ServletContextListener {

	ServletContextEvent arg;
	
	
	public void contextInitialized(ServletContextEvent arg0) {
		run();
	}

    public void run() {
    	
        try {
        	
        	System.out.println("sarawebbanking - Parametros COMP");
    		Parametro.getInstance();
    		
    		
    		
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}


    
}

