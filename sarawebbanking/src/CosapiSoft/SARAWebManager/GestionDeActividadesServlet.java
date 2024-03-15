package CosapiSoft.SARAWebManager;

/**
 * This type was created in VisualAge.
 */
import CosapiSoft.SARAWebBanking.*;
public class GestionDeActividadesServlet extends javax.servlet.http.HttpServlet {
/**
 * AyudaDeCampoServlet constructor comment.
 */
public GestionDeActividadesServlet() {
	super();
}
private boolean containError(GestionDeActividades bean) throws Exception {
	try {
		bean.setError("");
		if (bean.getCodact().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_CODIGO_DE_ACTIVIDAD));
			return true;
		}
		if (!CosapiSoft.SARAWebBanking.CString.isInteger(bean.getCodact())) {
			bean.setError(Mensajes.getMessage(Mensajes.CODIGO_DE_ACTIVIDAD_DEBE_CONTENER_NUMEROS));
			return true;
		}
		if (bean.getTxtact().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_DESCRIPCION_ACTIVIDAD));
			return true;
		}
		return false;
	} catch (Exception e) {
		throw new Exception("containError() -> GestionDeActividadesServlet " + e.getMessage());
	}
}
public void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws javax.servlet.ServletException, java.io.IOException {
	doPost(req,res);
}
public void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws java.io.IOException {
	try {
		if (req.getSession(false) == null) {
			try {
				req.setAttribute("error", new Exception(Mensajes.getMessage(Mensajes.SESION_TERMINADA) + " - " + this.getClass().toString()));;
				//((com.sun.server.http.HttpServiceResponse) res).callPage(JspServlet.ERROR_JSP, req);
				res.sendRedirect(JspServlet.ERROR_JSP);
			} catch (javax.servlet.ServletException se) {
				res.getWriter().println(se.getMessage());
			}
			return;
		}
		String value = req.getParameter("BtnAct");
		GestionDeActividades bean;
		//		if (req.getSession(false).getAttribute("tactdat") != null) {
		//			bean = (GestionDeActividades) (req.getSession(false).getAttribute("tactdat"));
		//		} else {
		bean = new GestionDeActividades();
		bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("manager"));
		//			req.getSession(false).putValue("tactdat", bean);
	 	req.getSession(false).setAttribute("tactdat", bean);
		//	,	}
		//
		bean.getLogin().setError("");
		bean.getLogin().setNamePantalla(Mensajes.getMessage(Mensajes.GESTION_DE_ACTIVIDADES));
		if (value.equals("Otro")) {
			/**
			bean.getLogin().setCodact("21");
			bean.getLogin().setAccion("Consulta");
			if (!bean.getLogin().hasAccesAccion()) {
			*/	
				bean.getLogin().setCodact("313");
				bean.getLogin().setAccion("Consulta");
				if (!bean.getLogin().hasAccesAccion()) {
					//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilManagerError.jsp");
				 	//((com.sun.server.http.HttpServiceResponse) res).callPage(JspServlet.ERROR_MANAGER_JSP, req);
				 	res.sendRedirect(JspServlet.ERROR_MANAGER_JSP);
					return;
				}
			//}
			bean.consultar();
		}
		if (value.equals("Agregar")) {
			bean.getLogin().setCodact("313");
			bean.getLogin().setAccion("Inserción");
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilManagerError.jsp");
			 	//((com.sun.server.http.HttpServiceResponse) res).callPage(JspServlet.ERROR_MANAGER_JSP, req);
			 	res.sendRedirect(JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			bean.loadGrid();
			bean.setStatus("0");
			bean.setCodact(req.getParameter("TxtCodact"));
			bean.setTxtact(req.getParameter("TxtTxtact"));
			if (!containError(bean)) {
				bean.agregar();
				if (bean.getError().equals("")) {
					bean.setCodact("");
					bean.setTxtact("");
				}
			}
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebManager/GestionDeActividades/GestionDeActividades.jsp");
		 	//((com.sun.server.http.HttpServiceResponse) res).callPage(JspServlet.GESTION_DE_ACTIVIDADES_JSP, req);
		 	res.sendRedirect(JspServlet.GESTION_DE_ACTIVIDADES_JSP);
			return;
		}
		if (value.equals("Modificar")) {
			bean.getLogin().setCodact("313");
			bean.getLogin().setAccion("Modificación");
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilManagerError.jsp");
			 	res.sendRedirect(JspServlet.ERROR_MANAGER_JSP);
			 	//((com.sun.server.http.HttpServiceResponse) res).callPage(JspServlet.ERROR_MANAGER_JSP, req);
				return;
			}
			bean.loadGrid();
			bean.setStatus("0");
			bean.setCodact(req.getParameter("TxtCodact"));
			bean.setTxtact(req.getParameter("TxtTxtact"));
			if (!containError(bean)) {
				bean.modificar();
				if (bean.getError().equals("")) {
					bean.setCodact("");
					bean.setTxtact("");
				}
			}
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebManager/GestionDeActividades/GestionDeActividades.jsp");
		 	res.sendRedirect(JspServlet.GESTION_DE_ACTIVIDADES_JSP);
		 	//((com.sun.server.http.HttpServiceResponse) res).callPage(JspServlet.GESTION_DE_ACTIVIDADES_JSP, req);
			return;
		}
		if (value.equals("Eliminar")) {
			bean.getLogin().setCodact("313");
			bean.getLogin().setAccion("Eliminación");
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilManagerError.jsp");
			 	//((com.sun.server.http.HttpServiceResponse) res).callPage(JspServlet.ERROR_MANAGER_JSP, req);
			 	res.sendRedirect(JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			bean.eliminar(req);
			bean.setCodact("");
			bean.setTxtact("");
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebManager/GestionDeActividades/GestionDeActividades.jsp");
		 	//((com.sun.server.http.HttpServiceResponse) res).callPage(JspServlet.GESTION_DE_ACTIVIDADES_JSP, req);
		 	res.sendRedirect(JspServlet.GESTION_DE_ACTIVIDADES_JSP);
			return;
		}
		if (value.equals("Buscar")) {
			bean.loadGrid();
			bean.setCodact(req.getParameter("TxtCodact"));
			bean.buscar();
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebManager/GestionDeActividades/GestionDeActividades.jsp");
		 	res.sendRedirect(JspServlet.GESTION_DE_ACTIVIDADES_JSP);
		 	//((com.sun.server.http.HttpServiceResponse) res).callPage(JspServlet.GESTION_DE_ACTIVIDADES_JSP, req);
			return;
		}
		bean.setError("");
		bean.loadGrid();
		bean.setCodact("");
		bean.setTxtact("");
		//		res.sendRedirect("../../../SARAWebBanking/SARAWebManager/GestionDeActividades/GestionDeActividades.jsp");
	 	//((com.sun.server.http.HttpServiceResponse) res).callPage(JspServlet.GESTION_DE_ACTIVIDADES_JSP, req);
	 	res.sendRedirect(JspServlet.GESTION_DE_ACTIVIDADES_JSP);
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