package CosapiSoft.SARAWebManager;

/**
 * This type was created in VisualAge.
 */
import pe.cosapi.common.Constante;
import CosapiSoft.SARAWebBanking.*;
public class GrupoPantallasServlet extends JavaServlet {
/**
 * AyudaDeCampoServlet constructor comment.
 */
public GrupoPantallasServlet() {
	super();
}
public void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws java.io.IOException, javax.servlet.ServletException {
	InicioSesion login = new InicioSesion();
	login.setNameProducto("SARA Web Manager");
	try {
	    //System.out.println("Sesion de CADUCIDAD"+Constante.SESSION_CADUCA);
	    if (Constante.SESSION_CADUCA.equals("2")) {
			try {
				req.getSession(false).setAttribute("error", new Exception(Mensajes.getMessage(Mensajes.SESION_TERMINADA) + " - " + this.getClass().toString()));;
				//req.getSession(false).setAttribute("error",new Exception(Mensajes.getMessage(Mensajes.SESION_TERMINADA) + " - " + this.getClass().toString()));;
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
	    
		String value = req.getParameter("BtnGrp");
		value = (value == null) ? "Otro" : value.trim();
		GrupoPantallas bean;
		if (req.getSession(false).getAttribute("tgrppan") != null) {
			bean = ((GrupoPantallas) req.getSession(false).getAttribute("tgrppan"));
		}
		else {
			bean = new GrupoPantallas();
			bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("manager"));
			req.getSession(false).setAttribute("tgrppan", bean);
		}

		//
		bean.getLogin().setError("");
		bean.getLogin().setNamePantalla("MENSAJES PRE-ESTABLECIDOS POR PANTALLA");
		System.out.println("Mensaje Opción: "+bean.getLogin().getNamePantalla());
		if (value.equalsIgnoreCase("Otro")) {
			
			//bean.getLogin().setCodact("92");
			//bean.getLogin().setAccion("Consulta");
			//if (!bean.getLogin().hasAccesAccion()) {
				bean.getLogin().setCodact("304");
				bean.getLogin().setAccion("Consulta");
				if (!bean.getLogin().hasAccesAccion()) {
					//					res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilManagerError.jsp");
					callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
					return;
				}
			//}
			bean.consult();
		}
		if (value.equalsIgnoreCase("Agregar")) {
			bean.getLogin().setCodact("304");
			bean.getLogin().setAccion("Inserción");
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilManagerError.jsp");
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			bean.setStatus("0");
			bean.setCodgrp(req.getParameter("TxtCodgrp").toUpperCase());
			bean.setTxtdes(req.getParameter("TxtTxtdes"));
			if (!validate(bean)) {
				bean.insert();
				if (bean.getError().equals("")) {
					bean.setCodgrp("");
					bean.setTxtdes("");
				}
			}
			
			callPage(req, res, JspServlet.GRUPO_PANTALLAS_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Modificar")) {
			bean.getLogin().setCodact("304");
			bean.getLogin().setAccion("Modificación");
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilManagerError.jsp");
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			bean.setStatus("0");
			bean.setCodgrp(req.getParameter("TxtCodgrp"));
			bean.setTxtdes(req.getParameter("TxtTxtdes"));
			if (!validate(bean)) {
				bean.update();
				if (bean.getError().equals("")) {
					bean.setCodgrp("");
					bean.setTxtdes("");
				}
			}
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebManager/MensajesAplicativo/GrupoMensajes.jsp");
			callPage(req, res, JspServlet.GRUPO_PANTALLAS_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Eliminar")) {
			bean.getLogin().setCodact("304");
			bean.getLogin().setAccion("Eliminación");
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilManagerError.jsp");
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			bean.delete(req);
			bean.setCodgrp("");
			bean.setTxtdes("");
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebManager/MensajesAplicativo/GrupoMensajes.jsp");
			callPage(req, res, JspServlet.GRUPO_PANTALLAS_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Buscar")) {
			bean.setCodgrp(req.getParameter("TxtCodgrp"));
			
			bean.search();
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebManager/MensajesAplicativo/GrupoMensajes.jsp");
			callPage(req, res, JspServlet.GRUPO_PANTALLAS_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Mensajes")) {
			bean.setCodgrp(req.getParameter("TxtCodgrp"));
			if (bean.search())
				res.sendRedirect(JspServlet.MENSAJES_PANTALLAS_SERVLET + "?TxtCodgrp=" + bean.getCodgrp() + "&BtnDatos=Otro");
			else {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebManager/MensajesAplicativo/GrupoMensajes.jsp");
				callPage(req, res, JspServlet.GRUPO_PANTALLAS_JSP);
			}
			return;
		}
		bean.setError("");
		bean.loadGrid();
		bean.setCodgrp("");
		bean.setTxtdes("");
		//		res.sendRedirect("../../../SARAWebBanking/SARAWebManager/MensajesAplicativo/GrupoMensajes.jsp");
		callPage(req, res, JspServlet.GRUPO_PANTALLAS_JSP);
	}
	catch (Exception e) {
		try {
			e.printStackTrace();
			setAttribute(req, "error", new Exception(Mensajes.ERROR_GENERAL));
			setAttribute(req, "login", login);
			callPage(req, res, JspServlet.ERROR_JSP);
		}
		catch (javax.servlet.ServletException se) {
			se.printStackTrace();
			res.getWriter().println(se.getMessage());
		}
	}
}
public boolean validate(Object obj) throws Exception {
	try {
		GrupoPantallas bean = (GrupoPantallas) obj;
		bean.setError("");
		if (bean.getCodgrp().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_CODIGO_DE_GRUPO));
			return true;
		}
		
		if (bean.getTxtdes().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_DESCRIPCION_GRUPO));
			return true;
		}
		if(!CFormat.ValidoAN(bean.getCodgrp())){
			bean.setError(Mensajes.getMessage(Mensajes.CODIGO_NO_VALIDO));
			return true;
		}
		
		return false;
	}
	catch (Exception e) {
		throw new Exception("\n... validate - " + getClass().getName() + " -> " + e.getMessage());
	}
}
}
