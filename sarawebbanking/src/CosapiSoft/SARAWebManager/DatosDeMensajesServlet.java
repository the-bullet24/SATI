package CosapiSoft.SARAWebManager;

/**
 * This type was created in VisualAge.
 */
import CosapiSoft.SARAWebBanking.*;
public class DatosDeMensajesServlet extends javax.servlet.http.HttpServlet {
/**
 * AyudaDeCampoServlet constructor comment.
 */
public DatosDeMensajesServlet() {
	super();
}
private boolean containError(DatosDeMensajes bean) throws Exception {
	try {
		bean.setError("");
		if (bean.getCodgrp().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_CODIGO_DE_GRUPO));
			return true;
		}
		if (!CosapiSoft.SARAWebBanking.CString.isInteger(bean.getCodgrp())) {
			bean.setError(Mensajes.getMessage(Mensajes.CODIGO_DE_GRUPO_DEBE_CONTENER_NUMEROS));
			return true;
		}
		if (bean.getCodmsg().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_CODIGO_DE_MENSAJE));
			return true;
		}
		if (!CosapiSoft.SARAWebBanking.CString.isInteger(bean.getCodmsg())) {
			bean.setError(Mensajes.getMessage(Mensajes.CODIGO_DE_MENSAJE_DEBE_CONTENER_NUMEROS));
			return true;
		}
		if (bean.getDesmsg().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_DESCRIPCION_MENSAJE));
			return true;
		}
		
		return false;
	} catch (Exception e) {
		throw new Exception("containError() -> DatosDeMensajesServlet " + e.getMessage());
	}
}
public void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws javax.servlet.ServletException, java.io.IOException {
	doPost(req,res);
}
public void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws java.io.IOException {
	try {
		if (req.getSession(false) == null) {
			//			res.sendRedirect(JspServlet.SARA_WEB_BANKING_HTML);
			try {
				//req.setAttribute("error", new Exception(Mensajes.getMessage(Mensajes.SESION_TERMINADA) + " - " + this.getClass().toString()));;
				req.getSession(false).setAttribute("error", new Exception(Mensajes.getMessage(Mensajes.SESION_TERMINADA) + " - " + this.getClass().toString()));;
				res.sendRedirect(JspServlet.ERROR_JSP);
				//((com.sun.server.http.HttpServiceResponse) res).callPage(JspServlet.ERROR_JSP, req);
			} catch (javax.servlet.ServletException se) {
				res.getWriter().println(se.getMessage());
			}
			return;
		}
		GrupoDeMensajes tgrpmsg = ((GrupoDeMensajes) req.getSession(false).getAttribute("tgrpmsg"));
		String value = req.getParameter("BtnDatos");
		DatosDeMensajes bean;
		if (req.getSession(false).getAttribute("tmsgdat") != null) {
			bean = ((DatosDeMensajes) req.getSession(false).getAttribute("tmsgdat"));
		} else {
			bean = new DatosDeMensajes();
			bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("manager"));
			bean.consultar();
			req.getSession(false).setAttribute("tmsgdat", bean);
		}
		bean.setCodgrp(tgrpmsg.getCodgrp());
		// System.out.println("codigo de grupo....."+tgrpmsg.getCodgrp());
		bean.getLogin().setError("");
		//bean.getLogin().setNamePantalla(Mensajes.getMessage(Mensajes.MENSAJES_DEL_APLICATIVO));
		if (value.equals("Agregar")) {
			//bean.getLogin().setCodact("14");
			bean.getLogin().setAccion("Inserción");
			/**
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilManagerError.jsp");
			 	//((com.sun.server.http.HttpServiceResponse) res).callPage(JspServlet.ERROR_MANAGER_JSP, req);
			 	res.sendRedirect(JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			*/
			bean.setStatus("0");
			bean.setCodmsg(req.getParameter("TxtCodmsg"));
			bean.setDesmsg(req.getParameter("TxtDesmsg"));
			bean.setIdemsg(req.getParameter("TxtIdemsg"));
			if (!containError(bean)) {
				bean.agregar();
				if (bean.getError().equals("")) {
					bean.setCodmsg("");
					bean.setIdemsg("");
					bean.setDesmsg("");
				}
			}
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebManager/MensajesAplicativo/DatosMensajes.jsp");
		 	//((com.sun.server.http.HttpServiceResponse) res).callPage(JspServlet.MENSAJES_DEL_APLICATIVO_JSP, req);
		 	res.sendRedirect(JspServlet.MENSAJES_DEL_APLICATIVO_JSP);
			return;
		}
		if (value.equals("Modificar")) {
			//bean.getLogin().setCodact("14");
			bean.getLogin().setAccion("Eliminación");
			/**
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilManagerError.jsp");
			 	//((com.sun.server.http.HttpServiceResponse) res).callPage(JspServlet.ERROR_MANAGER_JSP, req);
			 	res.sendRedirect(JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			*/
			bean.setStatus("0");
			bean.setCodmsg(req.getParameter("TxtCodmsg"));
			bean.setDesmsg(req.getParameter("TxtDesmsg"));
			bean.setIdemsg(req.getParameter("TxtIdemsg"));
			if (!containError(bean)) {
				bean.modificar();
				if (bean.getError().equals("")) {
					bean.setCodmsg("");
					bean.setIdemsg("");
					bean.setDesmsg("");
				}
			}
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebManager/MensajesAplicativo/DatosMensajes.jsp");
		 	//((com.sun.server.http.HttpServiceResponse) res).callPage(JspServlet.MENSAJES_DEL_APLICATIVO_JSP, req);
		 	res.sendRedirect(JspServlet.MENSAJES_DEL_APLICATIVO_JSP);
			return;
		}
		if (value.equals("Buscar")) {
			bean.setCod_msg("");
			bean.setDes_msg("");
			bean.setCodmsg(req.getParameter("TxtCodmsg"));
			bean.buscar();
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebManager/MensajesAplicativo/DatosMensajes.jsp");
		 	//((com.sun.server.http.HttpServiceResponse) res).callPage(JspServlet.MENSAJES_DEL_APLICATIVO_JSP, req);
		 	res.sendRedirect(JspServlet.MENSAJES_DEL_APLICATIVO_JSP);
			return;
		}
		if (value.equals("Eliminar")) {
			//bean.getLogin().setCodact("14");
			bean.getLogin().setAccion("Eliminación");
			/**
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilManagerError.jsp");
			 	//((com.sun.server.http.HttpServiceResponse) res).callPage(JspServlet.ERROR_MANAGER_JSP, req);
			 	res.sendRedirect(JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			*/
			bean.eliminar(req);
			bean.setCodmsg("");
			bean.setDesmsg("");
			bean.setIdemsg("");
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebManager/MensajesAplicativo/DatosMensajes.jsp");
		 	//((com.sun.server.http.HttpServiceResponse) res).callPage(JspServlet.MENSAJES_DEL_APLICATIVO_JSP, req);
		 	res.sendRedirect(JspServlet.MENSAJES_DEL_APLICATIVO_JSP);
			return;
		}
		if (value.equals("Regresar")) {
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebManager/MensajesAplicativo/GrupoMensajes.jsp");
		 	//((com.sun.server.http.HttpServiceResponse) res).callPage(JspServlet.GRUPO_DE_MENSAJES_JSP, req);
		 	res.sendRedirect(JspServlet.GRUPO_DE_MENSAJES_JSP);
			return;
		}
		bean.loadGrid();
		bean.setError("");
		bean.setCodmsg("");
		bean.setIdemsg("");
		bean.setDesmsg("");
		//		res.sendRedirect("../../../SARAWebBanking/SARAWebManager/MensajesAplicativo/DatosMensajes.jsp");
	 	res.sendRedirect(JspServlet.MENSAJES_DEL_APLICATIVO_JSP);
	 	//((com.sun.server.http.HttpServiceResponse) res).callPage(JspServlet.MENSAJES_DEL_APLICATIVO_JSP, req);
	} catch (Exception e) {
		//try {
			req.setAttribute("error", new Exception(Mensajes.ERROR_GENERAL));
			//((com.sun.server.http.HttpServiceResponse) res).callPage(JspServlet.ERROR_JSP, req);
			res.sendRedirect(JspServlet.ERROR_JSP);
		//} catch (javax.servlet.ServletException se) {
		//	res.getWriter().println(se.getMessage());
		//}
	}
}
}