package CosapiSoft.SARAWebManager;

/**
 * This type was created in VisualAge.
 */
import CosapiSoft.SARAWebBanking.*;
public class BloqueoDeOficinaServlet extends javax.servlet.http.HttpServlet {
/**
 * AyudaDeCampoServlet constructor comment.
 */
public BloqueoDeOficinaServlet() {
	super();
}
public void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws javax.servlet.ServletException, java.io.IOException {
	doPost(req,res);
}
public void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws java.io.IOException {
	try {
		if (req.getSession(false) == null) {
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
		String value = req.getParameter("BtnInf");
		InformacionDeAgencias bean;
		if (req.getSession(false).getAttribute("tbrainf") != null) {
			bean = (InformacionDeAgencias) req.getSession(false).getAttribute("tbrainf");
		} else {
			bean = new InformacionDeAgencias();
			bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("manager"));
			req.getSession(false).setAttribute("tbrainf", bean);
		}
		//
		bean.getLogin().setError("");
		bean.getLogin().setNamePantalla(Mensajes.getMessage(Mensajes.BLOQUEO_DE_SUCURSAL));
		if (value.equals("Otro")) {
			bean.getLogin().setCodact("38");
			bean.getLogin().setAccion("Consulta");
			if (!bean.getLogin().hasAccesAccion()) {
				bean.getLogin().setCodact("39");
				if (!bean.getLogin().hasAccesAccion()) {
					//					res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilManagerError.jsp");
				 	res.sendRedirect(JspServlet.ERROR_MANAGER_JSP);
				 	//((com.sun.server.http.HttpServiceResponse) res).callPage(JspServlet.ERROR_MANAGER_JSP, req);
					return;
				}
			}
			if (!bean.existeOficinaVirtual()) {
				bean.getLogin().setError(Mensajes.getMessage(Mensajes.DEBE_CREAR_LA_SUCURSAL_VIRTUAL));
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilManagerError.jsp");
			 	res.sendRedirect(JspServlet.ERROR_MANAGER_JSP);
			 	//((com.sun.server.http.HttpServiceResponse) res).callPage(JspServlet.ERROR_MANAGER_JSP, req);
				return;
			}
			bean.consultar();
		}
		if (value.equals("Aceptar")) {
			bean.getLogin().setCodact("39");
			bean.getLogin().setAccion("Modificación");
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilManagerError.jsp");
			 	//((com.sun.server.http.HttpServiceResponse) res).callPage(JspServlet.ERROR_MANAGER_JSP, req);
			 	res.sendRedirect(JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			bean.setFlgblkbra(req.getParameter("TxtFlgblkbra"));
			//bean.bloquearOficina();
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebManager/AdministracionDeAgencias/BloqueoDeOficina.jsp");
		 	//((com.sun.server.http.HttpServiceResponse) res).callPage(JspServlet.BLOQUEO_SUCURSAL_JSP, req);
		 	res.sendRedirect(JspServlet.BLOQUEO_SUCURSAL_JSP);
			return;
		}
		bean.setError("");
		bean.loadGrid();
		//		res.sendRedirect("../../../SARAWebBanking/SARAWebManager/AdministracionDeAgencias/BloqueoDeOficina.jsp");
	 	res.sendRedirect(JspServlet.BLOQUEO_SUCURSAL_JSP);
	 	//((com.sun.server.http.HttpServiceResponse) res).callPage(JspServlet.BLOQUEO_SUCURSAL_JSP, req);
	} catch (Exception e) {
		//try {
			req.setAttribute("error", new Exception(Mensajes.ERROR_GENERAL));
			res.sendRedirect(JspServlet.ERROR_JSP);
			//((com.sun.server.http.HttpServiceResponse) res).callPage(JspServlet.ERROR_JSP, req);
		//} catch (javax.servlet.ServletException se) {
		//	res.getWriter().println(se.getMessage());
		//}
	}
}
}