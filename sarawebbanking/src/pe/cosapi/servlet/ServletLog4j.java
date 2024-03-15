/*
 * Usuario Creador  		: Wilder Hernandez Nuñez
 * Fecha Creacion			: 
 * Clase   					: ServletLog4j
 * paquete 					: uni.edu.matricula.system
 * Usuario Modificador  	: 
 * Descripcion de la Clase  : Servlet encargado de Configurar e Inicializar el framework log4j
 * 
 */
package pe.cosapi.servlet;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;


import org.apache.log4j.PropertyConfigurator;


public class ServletLog4j extends HttpServlet implements Servlet{
    
	public void destroy(){
		super.destroy();
	}
	
	public void init() throws ServletException{
		String rutaFisica = getServletContext().getRealPath(java.io.File.separator);
		
		if (rutaFisica.substring(rutaFisica.length()-1,rutaFisica.length()).equals(java.io.File.separator) == false)
			rutaFisica = rutaFisica + java.io.File.separator;		
			// System.out.println("rutaFisica ="+rutaFisica);
			
		String rutaArchivosConfiguracion=rutaFisica + "WEB-INF"+java.io.File.separator+"properties"+java.io.File.separator;
		// System.out.println("rutaArchivosConfiguracion ="+rutaArchivosConfiguracion);
		
		try {
			PropertyConfigurator.configure(rutaArchivosConfiguracion + "log4j.properties");
			System.out.println("******************Se inició satisfactoriamente el Log4j****************");
		} catch (Throwable t) {
			// System.out.println("  error inicializando archivo de log");
			t.printStackTrace();
			// System.out.println("  finalizando sistema...");
			System.exit(0);
		}
			super.init();
	}
}
