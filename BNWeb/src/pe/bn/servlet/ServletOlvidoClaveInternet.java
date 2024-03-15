/*
 * Creado el 02/02/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.bn.servlet;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import pe.bn.afiliacion.action.DesafCableAction;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.common.BNAplicacion;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteSesion;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.SpringWebApplicationContext;
import pe.cosapi.system.GeneratorKeys;


/**
 * @author Mily Dolores Bustamante
 * 
 * TODO Para cambiar la plantilla de este comentario generado, vaya a Ventana -
 * Preferencias - Java - Estilo de código - Plantillas de código
 */
public class ServletOlvidoClaveInternet extends HttpServlet {
	private static LoggerSati log3 = LoggerSati.getInstance(ServletOlvidoClaveInternet.class.getName());
	
	 
	
	public void init(ServletConfig config) throws ServletException {		
		super.init(config);								
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());				
	    SpringWebApplicationContext.getInstance().setWebApplicationContext(wac);
	    
	    BNAplicacion aplicacion = BNAplicacion.getInstance(); 
	    try{
	    	System.out.println("*************INICIALIZANDO LAS VARIABLES DE APLICACION**************");
	    	aplicacion.setRutaClasspath(getServletContext().getRealPath("/"));
			aplicacion.cargar();
			aplicacion.getMensajes();
			aplicacion.getDiccionario();
			aplicacion.getControl();
			aplicacion.getEsquema();
			aplicacion.getParametros();
	    	
	    	//System.out.println("rutaClasspath:"+aplicacion.getRutaClasspath());
	    	//System.out.println("*************VARIABLES DE APLICACION CARGADAS**************");
	    }
	    catch(Exception e){
	    	log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
	    	System.out.println(e);
	    	throw new ServletException("*********************NO SE PUDO CARGAR LAS VARIABLES DE LA APLICACION******************");
	    }
	}

	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
	process(req,resp);
}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		process(req,resp);
	}
	
	public void process(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		req.getSession().setAttribute(ConstanteSesion.CARPETA,Constante.COD_PERSON_NAT);

		req.getSession().removeAttribute(ConstanteSesion.MAP_VALUES);
		req.getSession().removeAttribute(ConstanteSesion.USUARIO_EN_SESION);
				
		Constante.FLAG_CACHE = "0";
		//-- Se asigna el tipo de canal de ingreso
		
		ConstanteSesion.CODIGO_COMPANIA="1";
		String codCompany = req.getParameter("cod");
		if (codCompany != null){
		    ConstanteSesion.CODIGO_COMPANIA = codCompany.trim(); 
		}
		
		
	
		try {
			req.setAttribute("lstMes", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.COD_HLP_DET_MES));
			List lista= FacadeFactory.getGeneralFacade().getComboDetalleHlpOrden(Constante.COD_HLP_DET_DOCU_AFILIACION_INTERNET);
			lista.remove(0);
			req.getSession().setAttribute("lstDocumento",lista);
			
		} catch (Exception e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
			
		}
		
		GeneratorKeys gen = new GeneratorKeys();
		req.getSession().setAttribute(ConstanteSesion.MAP_VALUES,gen.generateMap());
		
		getServletContext().getRequestDispatcher("/WEB-INF/page/sistema/inicioOlvidoClaveInternet.jsp").forward(req,resp);
		
	}

	
}