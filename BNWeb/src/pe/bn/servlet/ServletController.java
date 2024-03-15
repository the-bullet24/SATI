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
public class ServletController extends HttpServlet {
	private static LoggerSati log3 = LoggerSati.getInstance(ServletController.class.getName());
	 
	
	public void init(ServletConfig config) throws ServletException {		
		
		ServletContext contexto = config.getServletContext();
	    ResourceBundle rb = ResourceBundle.getBundle("parametro");
	    String fileConfig = rb.getString("bn.file.config");

	    fileConfig = config.getServletContext().getRealPath("/WEB-INF/" + fileConfig);
	    //boolean conexionComp = cargaParametro(contexto, fileConfig);
	    boolean conexionComp = true;
	    
	    
		
	    
		
	    if (conexionComp == true)
        {
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
		    					
				getServiceMail();
				
		    	//System.out.println("rutaClasspath:"+aplicacion.getRutaClasspath());
		    	//System.out.println("*************VARIABLES DE APLICACION CARGADAS**************");
//				System.out.println("**************************************");
							
				
				
		    }
		    catch(Exception e){
		    	log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
		    	System.out.println(e);
		    	throw new ServletException("*********************NO SE PUDO CARGAR LAS VARIABLES DE LA APLICACION******************");
		    }
		    
		    
        } else{
            throw new ServletException(
                    "*********************NO SE PUDO CARGAR LAS VARIABLES DE LA APLICACION******************");
        }

 	
	}

	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
	resp.setHeader("X-Content-Type-Options", "nosniff");
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
		//resp.setHeader("Content-Security-Policy", "default-src 'self'");
		resp.setHeader("Content-Security-Policy", "style-src 'self' https://ui-systems.net/");
		resp.setHeader("Content-Security-Policy", "script-src 'self' https://uimarketpro.com/");
		resp.setHeader("Content-Security-Policy", "img-src 'self' https://ui-systems.net/");

		resp.setHeader("X-Content-Type-Options", "nosniff");	
		resp.setHeader("Strict-Transport-Security", "max-age=31536000;includeSubDomains");
		resp.setHeader("Content-Security-Policy", "frame-ancestors 'self' https://bancaporinternet.bn.com.pe");
			
		getServletContext().getRequestDispatcher("/WEB-INF/page/sistema/login.jsp").forward(req,resp);

	
	}

	
	
	
	
	
	private boolean cargaParametro(ServletContext contexto, String fileConfig)
    {
        boolean    conexionComp = false;
        SAXBuilder builder      = new SAXBuilder(false);

        BnwsParametro bnProperties = new BnwsParametro();
        try {
            Document doc      = builder.build(fileConfig);//System.out.println("doc:"+doc);
            Element  raiz     = doc.getRootElement();//System.out.println("raiz:"+raiz);
            Element  elemento = raiz.getChild("parameter");

            Element                elem1      = elemento.getChild("WebService");
            Element                elem2      = elemento.getChild("ClaveSegura");
            String                 keyPath    = elem2.getChildText("keyPath");
            String                 keyName    = elem2.getChildText("keyName");
            String                 sistema    = elem2.getChildText("sistema");
            String                 cuenta     = elem2.getChildText("cuenta");
            String                 semillaKey = elem2.getChildText("semillaKey");
            String                 usuario    = elem2.getChildText("usuario");
            ParametroInterfazProxy proxi      = new ParametroInterfazProxy();

            String path  = keyPath + "/" + keyName;//System.out.println("path:"+path);
            byte[] clave = BNClaveSegura.encrypt(path, semillaKey);

            SistemaParametro sistemaParametro = null;
            log3.debug("cargaParametro", "CARGANDO.. SATI INSTANCE SERVICE PARAMETRO ", "1");
            //System.out.println("(sistema, cuenta, clave, usuario):"+sistema+","+cuenta+","+clave+","+usuario);

//            System.out.println("CARGANDO... cargaParametro - SATI INSTANCE SERVICE PARAMETRO");
            sistemaParametro = proxi.datoParametroService(sistema, cuenta, clave,
                                                          usuario);//.datoParametroService(sistema, cuenta, clave, usuario);

            //Si la autenticacion fue correcta se asigna los parametros
            log3.debug("cargaParametro",
                       "SATI RESULTADO SERVICE PARAMETRO:" + sistemaParametro.getProceso().getDescripcion(), "1");
//            System.out.println("cargaParametro - SATI RESULTADO SERVICE PARAMETRO:" + sistemaParametro.getProceso().getDescripcion());
            //System.out.println("sistemaParametro:"+sistemaParametro);
            //System.out.println("sistemaParametro.getProceso().getCodigo():"+sistemaParametro.getProceso().getCodigo());

            if (sistemaParametro.getProceso().getCodigo().equals("00000")) {
                conexionComp = true;
                //List<MicroservicesEndpoints> endpoints = new ArrayList<MicroservicesEndpoints>();

                for (int t = 0; t < sistemaParametro.getGrupoParametro().length; t++) {

                    //--------------------------------------------------------------------------------------------------
                    // Precarga de system properties para las URI's de endpoint's de WEBSERVICES/SIMM.
                    if (sistemaParametro.getGrupoParametro()[t].getAliasGrupo().equals("SERVICES")) {
                        ParamUrl paramUrl = null;
                        if (sistemaParametro.getGrupoParametro()[t].getParametro() != null) {
                            paramUrl = bnProperties.new ParamUrl();
                            for (int m = 0; m < sistemaParametro.getGrupoParametro()[t].getParametro().length; m++) {

                                if (sistemaParametro.getGrupoParametro()[t].getParametro()[m].getAliasParam().equals(
                                        "url.servidorCorreo".trim())) {
                                    paramUrl.setParamUrlUrl(
                                            sistemaParametro.getGrupoParametro()[t].getParametro()[m].getValorParam());

                                }
                            }
                        }
                        bnProperties.setParamUrl(paramUrl);
                        Constante.BN_PARAM_URL = bnProperties.getParamUrl();
//                        System.out.println("Constante.BN_PARAM_URL:"+Constante.BN_PARAM_URL);
                        
                        
                    }

                }

                contexto.setAttribute("bnProperties", bnProperties);

            } else {
            	log3.error(null, "ERROR CONSULTAR COMP" + Constante.ERR_LOGICA_NEGOCIO, sistemaParametro.getProceso().getCodigo());
                log3.error(null, "ERROR CONSULTAR COMP" + Constante.ERR_LOGICA_NEGOCIO, sistemaParametro.getProceso().getDescripcion());
                
                throw new Exception(sistemaParametro.getProceso().getDescripcion());
            }//Fin IF

        } catch (NullPointerException ne) {
            //ne.printStackTrace();
            log3.error(ne, null, "====== ERROR NULL ======" + ne.getMessage() + "(JavaNullException)");
            //  System.out.println("====== ERROR NULL ======");
            log3.error(ne, null, "Intentando conectar: " + ne.getMessage() + "(JavaNullException)");
            //System.out.println("========================");
        } catch (Exception e) {
            //e.printStackTrace();
            //System.out.println("Cargando datos...");
            log3.error(e, null, "=====ERROR NULL WEB SERVICE=====");
            log3.error(e, null, " Cargando conexi?n WebService: " + e.getMessage() + "(JavaNullException)");
            //  System.out.println("=====ERROR NULL WEB SERVICE=====");
            ///System.out.println("Cargando conexi?n WebService: "+e.getMessage()+"(JavaNullException)");
            //System.out.println("==================================");
        }

        return conexionComp;
        

    }
	
	
	
	public static java.lang.String getServiceMail() {
    					
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
	
	
}