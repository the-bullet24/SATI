package CosapiSoft.SARAWebBanking;

import java.util.ArrayList;
import java.util.Vector;

import com.app.sarawebmanager.ws.bean.CustomerAuthenticationProxy;
import com.app.sarawebmanager.ws.bean.client.ClienteWS;
import com.app.sarawebmanager.ws.bean.client.HelperClientFrames;
import com.app.sarawebmanager.ws.bean.client.vo.ActivityVO;
import com.app.sarawebmanager.ws.bean.client.vo.ClientResultVO;
import com.app.sarawebmanager.ws.bean.client.vo.LogginResultVO;
import com.cosapisoft.sarawebbranch.common.beans.LogginResult;
import com.cosapisoft.sarawebmanager.security.beans.Actividad;
import pe.cosapi.common.Constante;

/**
 * This type was created in VisualAge.
 */
public class InicioSesionServlet extends JavaServlet {
/**
 * InicioSesionServlet constructor comment.
 */
public InicioSesionServlet() {
	super();
}
public void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws java.io.IOException, javax.servlet.ServletException {
	setCache(res, true);
	String modulo = req.getParameter("Modulo");
	modulo = (modulo == null) ? "SARA Web Manager" : modulo.replace('_', ' ');
	InicioSesion bean = new InicioSesion();
	bean.setNameProducto(modulo);
	String messageResultTra = "";
	Constante.SESSION_CADUCA="1";
	try {
		//InicioSesion.loadParameters();
		Mensajes.loadMessages();
		
		/**
		if (modulo.equalsIgnoreCase("Inicio de Dia")) {
			new InicioDeDia1Servlet().doPost(req, res);
			return;
		}
		*/
		
		String codModulo = " ";
		
		if (modulo.equalsIgnoreCase("Inicio de Dia")) {
			codModulo = "11";
		} else if (modulo.equalsIgnoreCase("SARA Web Builder")) {
			codModulo = "12";
		} else if (modulo.equalsIgnoreCase("SARA Web Manager")) {
			codModulo = "13";
		}
		
		Constante.CONST_MODULO = codModulo;
		System.out.println("Modulo Constante:"+Constante.CONST_MODULO);
				
		InicioSesion manager = new InicioSesion();
		manager.setNameProducto("SARA Web Manager");
		//
		InicioSesion builder = new InicioSesion();
		builder.setNameProducto("SARA Web Builder");
		//
		req.getSession(true).setAttribute("login", bean);
		String value = (req.getParameter("BtnLogin") == null) ? "Otro" : req.getParameter("BtnLogin").trim();
		if (value.equals("Aceptar")) {
			bean.setUsuario(req.getParameter("TxtUsuario"));
			bean.setClave(req.getParameter("TxtClave"));
			
			///////////////////////////////////////////////////////////
			String codUsuario = req.getParameter("TxtUsuario");
			String clave = req.getParameter("TxtClave");
			//String codUsuario = "1212122";
			//String clave = "111111";
	    	String codAgencia = "8888";
	    	
	    	
	    	LogginResult logginResult = new LogginResult();
	    	
	         
	        ClientResultVO cliRVO = new ClientResultVO();
	        
	        // Verifica el Usuario Ingresado
	        System.out.println("Manager y Builder");
	        
	    	//cliRVO = clienteWS.usersLoggin(codModulo, codAgencia, codUsuario, clave);
	     	//LogginResultVO logRVO = (LogginResultVO) cliRVO.getObject();
	        CustomerAuthenticationProxy autenticacion = new CustomerAuthenticationProxy();
	    	String trama = autenticacion.autenticarUsuarios(HelperClientFrames.generateUsersLoggin(codModulo, codAgencia, codUsuario, clave));
	    	System.out.println("trama:"+trama);
	    	LogginResultVO logRVO = HelperClientFrames.parseUserTeller(trama);
	    	
	    	cliRVO.setCode("1");
	    	cliRVO.setObject(logRVO);
	    	
	    	messageResultTra =cliRVO.getMessage();
	    	
	    				
	    	if (cliRVO.getCode().equals("1")){
	    	//if ("1".equals("1")){
	    		 //*******************************************************************************************
			    //ArrayList all = new ArrayList();

			    //*******************************************************************************************
				//bean.loadModulos();
				//if (bean.hasAccesModulo()) {
	    		logginResult.setState(logRVO.getState());
			    logginResult.setMessage(logRVO.getMessage());
			    logginResult.setNameUser(logRVO.getName());
			    logginResult.setFlagPasswordChange(logRVO.getFlagPasswordChange());
			    //System.out.println("logginResult.getFlagPasswordChange:"+logginResult.getFlagPasswordChange());
			    
			    if (logginResult.getState().equals("1")){ //1: Login Exitoso
			        
			        if (logRVO.getFlagPasswordChange().equals("1")){
			    		bean.setError(logRVO.getMessage());
			    		res.sendRedirect(JspServlet.INICIO_SESION_JSP);	    		
			    	}  
			        
				    int listAct = 0;	    	
					listAct  = logRVO.getActivities().size();
				    //listAct  = all.size();
					bean.acciones = new Vector();
					for(int i=0; i<=listAct-1; i++){			    
					    ActivityVO aVO = (ActivityVO) logRVO.getActivities().get(i);
					    	
					    Actividad act = new Actividad();
					    act.setCodModulo(codModulo);
					    act.setCodActividad(aVO.getCode());
					    act.setNomActividad(aVO.getName());
					    logginResult.getActivities().add(act);
					    bean.acciones.addElement(aVO.getCode());
					    //System.out.println("aVO.getCode():"+aVO.getCode());
					}
					    	
			    		//bean.loadAcciones();
						
						if (bean.getNameProducto().toUpperCase().equalsIgnoreCase("SARA WEB MANAGER")) {
							manager = bean;
							req.getSession(false).setAttribute("manager", manager);
							res.sendRedirect(JspServlet.MANAGER_HTML);
								//JspServlet.MANAGER_HTML);
						} else if (bean.getNameProducto().toUpperCase().equalsIgnoreCase("SARA WEB BUILDER")) {
							builder = bean;
							req.getSession(false).setAttribute("builder", builder);
							res.sendRedirect(JspServlet.BUILDER_HTML);
						} else {
							builder = bean;
							req.getSession(false).setAttribute("inicio", builder);
							res.sendRedirect(JspServlet.ADMIN_HTML);
						}
						return;
					//}
			    } else {
			        if (logRVO.getFlagPasswordChange().equals("1")){	
			    		bean.setError(logRVO.getMessage());
			    		res.sendRedirect(JspServlet.INICIO_SESION_JSP);	
			    	}else{
			    		bean.setError(logRVO.getMessage());	
						res.sendRedirect(JspServlet.INICIO_SESION_JSP);	
			    	}
			    }
				//}
			}
			//			res.sendRedirect(JspServlet.INICIO_SESION_JSP);
			callPage(req, res, JspServlet.INICIO_SESION_JSP);
			return;
		}
		if (value.equals("Regresar")) {
			res.sendRedirect(JspServlet.SARA_WEB_BANKING_HTML);
			return;
		}
		if (value.equals("Modificar Clave")) {
			bean.setUsuario(req.getParameter("TxtUsuario"));
			bean.setClave(req.getParameter("TxtClave"));
			bean.loadModulos();
			//			if (!bean.hasAccesModulo()) {
			//				res.sendRedirect("../SARAWebBanking/PerfilError.jsp");
			//				return;
			//			}
			if (bean.isValidoLogin() && bean.hasAccesModulo()) {
				res.sendRedirect(JspServlet.MODIFICAR_CLAVE_SERVLET + "?TxtCodusr=" + req.getParameter("TxtUsuario") + "&BtnClave=Otro");
				return;
			}
			//			res.sendRedirect(JspServlet.INICIO_SESION_JSP);
			callPage(req, res, JspServlet.INICIO_SESION_JSP);
			return;
		}
		bean.setError("");
		//		res.sendRedirect(JspServlet.INICIO_SESION_JSP);
		callPage(req, res, JspServlet.INICIO_SESION_JSP);
	}
	catch (Exception e) {
		try {
			System.out.println("Error: " + e);
			System.out.println("Error: " + e.getMessage());
			System.out.println("Error: " + e.getCause());
			System.out.println("Error: " + e.getStackTrace());
			e.printStackTrace();
			setAttribute(req, "error", new Exception(Mensajes.ERROR_GENERAL));
			setAttribute(req, "login", bean);
			callPage(req, res, JspServlet.ERROR_JSP);
		}
		catch (javax.servlet.ServletException se) {
			res.getWriter().println(se.getMessage());
		}
	}
}
public boolean validate(Object obj) throws Exception{
	return false;
}
}