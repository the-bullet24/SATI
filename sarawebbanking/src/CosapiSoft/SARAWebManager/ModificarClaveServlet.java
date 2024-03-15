package CosapiSoft.SARAWebManager;

/**
 * This type was created in VisualAge.
 */
import com.app.sarawebmanager.ws.bean.client.ClienteWS;
import com.app.sarawebmanager.ws.bean.client.vo.ClientResultVO;
import com.app.sarawebmanager.ws.bean.client.vo.LogginResultVO;
import pe.cosapi.common.Constante;

import CosapiSoft.SARAWebBanking.*;
public class ModificarClaveServlet extends JavaServlet {
/**
 * AyudaDeCampoServlet constructor comment.
 */
public ModificarClaveServlet() {
	super();
}
public void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws javax.servlet.ServletException, java.io.IOException {
	doPost(req, res);
}
public void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws java.io.IOException, javax.servlet.ServletException {
	InicioSesion login = new InicioSesion();
	login.setNameProducto("SARA Web Manager");
	
	try {
		setCache(res, true);
		if (Constante.SESSION_CADUCA.equals("2")) {
			//			res.sendRedirect(JspServlet.SARA_WEB_BANKING_HTML);
			try {
				req.getSession(false).setAttribute("error",new Exception(Mensajes.getMessage(Mensajes.SESION_TERMINADA) + " - " + this.getClass().toString()));;
				setAttribute(req, "login", login);
				callPage(req, res, JspServlet.ERROR_JSP);
			}
			catch (javax.servlet.ServletException se) {
				res.getWriter().println(se.getMessage());
			}
			return;
		}
		if (!isLoggedIn(req)) {
			try {
				req.getSession(false).setAttribute("error",new Exception(Mensajes.getMessage(Mensajes.SESION_TERMINADA) + " - " + this.getClass().toString()));         
				callPage(req, res, JspServlet.ERROR_JSP);
			}
			catch (javax.servlet.ServletException se) {
				res.getWriter().println(se.getMessage());
			}
			return;
		}
		String value = req.getParameter("BtnClave");
		value = (value == null) ? "Otro" : value.trim();
		login = (CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("login");
		GestionDeUsuarios bean;
		if (req.getSession(false).getAttribute("tusrdat") != null) {
			bean = ((GestionDeUsuarios) req.getSession(false).getAttribute("tusrdat"));
		}
		else {
			bean = new GestionDeUsuarios();
			bean.setLogin(login);
			bean.consult();
			bean.loadOficinas();
			req.getSession(false).setAttribute("tusrdat", bean);
		}

		//
		if (value.equalsIgnoreCase("Aceptar")) {
			bean.setCodusr(req.getParameter("TxtCodusr"));
			bean.setOldclave(req.getParameter("TxtOldclave"));
			String nc1 = req.getParameter("TxtNewclave1");
			String nc2 = req.getParameter("TxtNewclave2");
			if (bean.getCodusr().equals(""))
				bean.setError(CosapiSoft.SARAWebBanking.Mensajes.getMessage(CosapiSoft.SARAWebBanking.Mensajes.USUARIO_CUATRO_CARACTERES));
			else {
				login.setUsuario(bean.getCodusr());
				login.setClave(bean.getOldclave());
				if (!login.isValidoLogin()) {
					bean.setError(login.getError());
				}
				else {
					if (nc1.length() < 6) {
						bean.setError(Mensajes.getMessage(Mensajes.NUEVA_CLAVE_SEIS_CARACTERES));
					}
					else {
						if (!nc1.equals(nc2)) {
							bean.setError(Mensajes.getMessage(Mensajes.CONFIRMACION_DE_CLAVE_ERRADA));
						}
						else {
							if (nc1.equals(login.getClave())) {
								bean.setError(Mensajes.getMessage(Mensajes.NUEVA_CLAVE_DIFERENTE_CLAVE_ACTUAL));
							}
							else {
								if (bean.getCodusr().equals(nc1)) {
									bean.setError(Mensajes.getMessage(Mensajes.NUEVA_CLAVE_DIFERENTE_CODIGO_USUARIO));
								}
								else {
									bean.setNewclave(nc1);
									bean.updateClave();
									// Nuevo Codigo para el cambio de Clave
									ClienteWS clienteWS = new ClienteWS();	       
							        ClientResultVO cliRVO = new ClientResultVO();
							        String codModulo = Constante.CONST_MODULO;
							        System.out.println("Modulo Constante Change:"+codModulo);
							        String codAgencia = "8888";
			
							        cliRVO = clienteWS.passwordChangeRequest(codModulo, codAgencia, bean.getCodusr(), bean.getOldclave(), bean.getNewclave());
						    		LogginResultVO logRVO_changePass = (LogginResultVO) cliRVO.getObject();
						    		
						    		if (logRVO_changePass.getState().equals("1")){ 		        
							            bean.setError(Mensajes.getMessage(Mensajes.CLAVE_MODIFICADA));
						    		}else{
						    		    if (logRVO_changePass.getFlagPasswordChange().equals("0")){
						    		        String msgError = logRVO_changePass.getMessage();
						    		        bean.setError(msgError);
						    		    } else {
						    		        String msgError = logRVO_changePass.getMessage();
						    		        bean.setError(msgError);
						    		    }
						    		}
									// Fin de Nuevo Codigo para el cambio de clave
								}
							}
						}
					}
				}
				//				res.sendRedirect(CosapiSoft.SARAWebBanking.JspServlet.MODIFICAR_CLAVE_JSP);
				callPage(req, res, JspServlet.MODIFICAR_CLAVE_JSP);
				return;
			}
		}
		if (value.equalsIgnoreCase("Regresar")) {
			bean.setError("");
			res.sendRedirect(JspServlet.SARA_WEB_BANKING_HTML);
			return;
			//callPage(req, res, JspServlet.INICIO_SESION_JSP);
			//return;
		}
		bean.setError("");
		//		res.sendRedirect(CosapiSoft.SARAWebBanking.JspServlet.MODIFICAR_CLAVE_JSP);
		callPage(req, res, JspServlet.MODIFICAR_CLAVE_JSP);
	}
	catch (Exception e) {
		try {
			e.printStackTrace();
			setAttribute(req, "error", new Exception(Mensajes.ERROR_GENERAL));
			setAttribute(req, "login", login);
			callPage(req, res, JspServlet.ERROR_JSP);
		}
		catch (javax.servlet.ServletException se) {
			res.getWriter().println(se.getMessage());
		}
	}
}
public boolean validate(Object obj) throws Exception {
	return false;
}
}