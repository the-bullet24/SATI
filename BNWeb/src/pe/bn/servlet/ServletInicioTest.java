/*
 * Creado el 02/02/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.bn.servlet;


import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.input.SAXBuilder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import pe.bn.afiliacion.action.DesafCableAction;
import pe.bn.listener.ConstanteParametros;
import pe.bn.listener.Parametro;
import pe.com.bn.comp.cryto.service.BNClaveSegura;
import pe.com.bn.comp.ws.bean.SistemaParametro;
import pe.com.bn.comp.ws.service.ParametroInterfazProxy;
import pe.com.bn.sati.common.LoggerSati;
import pe.com.bn.sati.domain.BnContextParametro;
import pe.com.bn.sati.domain.BnwsParametro;
import pe.com.bn.sati.domain.BnwsParametro.ParamUrl;
import pe.cosapi.common.BNAplicacion;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteSesion;
import pe.cosapi.common.SpringWebApplicationContext;
import pe.cosapi.domain.impl.DetalleAyudaDatosImpl;
import pe.cosapi.system.GeneratorKeys;
import pe.cosapi.system.TecladoUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import org.jdom.Document;
import org.jdom.Element;





/**
 * @author cosapi_ati04
 * 
 * TODO Para cambiar la plantilla de este comentario generado, vaya a Ventana -
 * Preferencias - Java - Estilo de código - Plantillas de código
 */
public class ServletInicioTest extends HttpServlet {
	private static LoggerSati log3 = LoggerSati.getInstance(ServletInicioTest.class.getName());
	 
	
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
		
		
		TecladoUtil teclado = new TecladoUtil();
		teclado.generar_array_numeros();
		req.getSession().setAttribute(ConstanteSesion.CARPETA,Constante.COD_PERSON_NAT);

		req.getSession().removeAttribute(ConstanteSesion.MAP_VALUES);
		req.getSession().removeAttribute(ConstanteSesion.MAP_VALUES);
		req.getSession().removeAttribute(ConstanteSesion.MAP_SELLO);
		req.getSession().removeAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		req.getSession().removeAttribute(ConstanteSesion.USUARIO_EN_SESION);
		req.getSession().removeAttribute(ConstanteSesion.USUARIO_EN_SESION_CLDI);
		req.getSession().removeAttribute(ConstanteSesion.CONSULTA);
		req.getSession().setAttribute(ConstanteSesion.MAP_VALUES, null);
		req.getSession().setAttribute(ConstanteSesion.MAP_SELLO, null);
		req.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION, null);
		req.getSession().setAttribute(ConstanteSesion.USUARIO_EN_SESION, null);
		req.getSession().setAttribute(ConstanteSesion.USUARIO_EN_SESION_CLDI, null);
		req.getSession().setAttribute(ConstanteSesion.CONSULTA, null);
		req.getSession().setAttribute(ConstanteSesion.IND_CAPTCHA, null);
		Constante.FLAG_CACHE = "0";
		//-- Se asigna el tipo de canal de ingreso
		
		
		
		ConstanteSesion.CODIGO_COMPANIA="1";
		String codCompany = req.getParameter("cod");
		if (codCompany != null){
		    ConstanteSesion.CODIGO_COMPANIA = codCompany.trim(); 
		}
		
//		Cookie getTarjeta = new Cookie("Tarjeta", "4214100021758612");
//		resp.addCookie(getTarjeta);
		
		GeneratorKeys gen = new GeneratorKeys();
		req.getSession().setAttribute(ConstanteSesion.MAP_VALUES,gen.generateMap());
		req.getSession().setAttribute(ConstanteSesion.MAP_SELLO,gen.generateMapSello());
		
		String indCaptcha="0";
		
		indCaptcha=(String)Parametro.getString(ConstanteParametros.BN_PARAM_FLAG_VER_CAPTCHA);
		
	
//		getServletContext().getRequestDispatcher("/login.do?validar=false").forward(req,resp);
		req.getSession().setAttribute(ConstanteSesion.IND_CAPTCHA,indCaptcha);
		
	}

	
	
	
	
	
}