package CosapiSoft.SARAWebManager;

/**
 * This type was created in VisualAge.
 */
import pe.cosapi.common.Constante;
import CosapiSoft.SARAWebBanking.*;
public class GrupoDeMensajesServlet extends JavaServlet {
/**
 * AyudaDeCampoServlet constructor comment.
 */
public GrupoDeMensajesServlet() {
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
		GrupoDeMensajes bean;
		if (req.getSession(false).getAttribute("tgrpmsg") != null) {
			bean = ((GrupoDeMensajes) req.getSession(false).getAttribute("tgrpmsg"));
		}
		else {
			bean = new GrupoDeMensajes();
			bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("manager"));
			req.getSession(false).setAttribute("tgrpmsg", bean);
		}

		//
		bean.getLogin().setError("");
		bean.getLogin().setNamePantalla("GRUPO DE MENSAJES DEL APLICATIVO");
		System.out.println("Mensaje Opción:"+bean.getLogin().getNamePantalla());
		if (value.equalsIgnoreCase("Otro")) {
			/**
			bean.getLogin().setCodact("13");
			bean.getLogin().setAccion("Consulta");
			if (!bean.getLogin().hasAccesAccion()) {
			*/
				bean.getLogin().setCodact("303");
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
			bean.getLogin().setCodact("303");
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
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebManager/MensajesAplicativo/GrupoMensajes.jsp");
			callPage(req, res, JspServlet.GRUPO_DE_MENSAJES_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Modificar")) {
			bean.getLogin().setCodact("303");
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
			callPage(req, res, JspServlet.GRUPO_DE_MENSAJES_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Eliminar")) {
			bean.getLogin().setCodact("303");
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
			callPage(req, res, JspServlet.GRUPO_DE_MENSAJES_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Buscar")) {
			bean.setCodgrp(req.getParameter("TxtCodgrp"));
			bean.search();
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebManager/MensajesAplicativo/GrupoMensajes.jsp");
			callPage(req, res, JspServlet.GRUPO_DE_MENSAJES_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Mensajes")) {
			bean.setCodgrp(req.getParameter("TxtCodgrp"));
			if (bean.search())
				res.sendRedirect(JspServlet.MENSAJES_DEL_APLICATIVO_SERVLET + "?TxtCodgrp=" + bean.getCodgrp() + "&BtnDatos=Otro");
			else {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebManager/MensajesAplicativo/GrupoMensajes.jsp");
				callPage(req, res, JspServlet.GRUPO_DE_MENSAJES_JSP);
			}
			return;
		}
		bean.setError("");
		bean.loadGrid();
		// System.out.println("salio del load grid");
		bean.setCodgrp("");
		bean.setTxtdes("");
		//		res.sendRedirect("../../../SARAWebBanking/SARAWebManager/MensajesAplicativo/GrupoMensajes.jsp");
		callPage(req, res, JspServlet.GRUPO_DE_MENSAJES_JSP);
	}
	catch (Exception e) {
		try {
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
	try {
		GrupoDeMensajes bean = (GrupoDeMensajes) obj;
		bean.setError("");
		if (bean.getCodgrp().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_CODIGO_DE_GRUPO));
			return true;
		}
		if (!CosapiSoft.SARAWebBanking.CString.isInteger(bean.getCodgrp())) {
			bean.setError(Mensajes.getMessage(Mensajes.CODIGO_DE_GRUPO_DEBE_CONTENER_NUMEROS));
			return true;
		}
		if (bean.getTxtdes().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_DESCRIPCION_GRUPO));
			return true;
		}
		
		return false;
	}
	catch (Exception e) {
		throw new Exception("\n... validate - " + getClass().getName() + " -> " + e.getMessage());
	}
}
}