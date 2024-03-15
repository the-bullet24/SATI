package CosapiSoft.SARAWebBuilder;

/**
 * This type was created in VisualAge.
 */
import CosapiSoft.SARAWebBanking.*;
public class DetalleTotalComServlet extends javax.servlet.http.HttpServlet {
/**
 * AyudaDeCampoServlet constructor comment.
 */
public DetalleTotalComServlet() {
	super();
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
				//((com.sun.server.http.HttpServiceResponse) res).callPage(JspServlet.ERROR_JSP, req);
				res.sendRedirect(JspServlet.ERROR_JSP);
			} catch (javax.servlet.ServletException se) {
				res.getWriter().println(se.getMessage());
			}
			return;
		}
		TotalCom ttotcom = ((TotalCom) req.getSession(false).getAttribute("ttotcom"));
		String value = req.getParameter("BtnDet");
		DetalleTotalCom bean;
		if (req.getSession(false).getAttribute("ttotcomdet") != null) {
			bean = ((DetalleTotalCom) req.getSession(false).getAttribute("ttotcomdet"));
		} else {
			bean = new DetalleTotalCom();
			bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("builder"));
			bean.consultar();
			req.getSession(false).setAttribute("ttotcomdet", bean);
		}
		bean.setCodtotcom(ttotcom.getcodtotcom());
		bean.getLogin().setError("");
		bean.getLogin().setNamePantalla(Mensajes.getMessage(Mensajes.DETTOTAL));
		if (value.equals("Agregar")) {
			bean.getLogin().setCodact("14");
			bean.getLogin().setAccion("Inserción");
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilManagerError.jsp");
			 	//((com.sun.server.http.HttpServiceResponse) res).callPage(JspServlet.ERROR_MANAGER_JSP, req);
			 	res.sendRedirect(JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			bean.setStatus("0");
			bean.setNumsec(req.getParameter("TxtNumsec"));
			bean.setCodtot(req.getParameter("TxtCodtot"));
			bean.setSigope(req.getParameter("TxtSigope"));
			if (!containError(bean)) {
				bean.agregar();
				if (bean.getError().equals("")) {
					bean.setNumsec("");
					bean.setCodtot("");
					bean.setSigope("");
				}
			}
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebManager/MensajesAplicativo/DatosMensajes.jsp");
		 	//((com.sun.server.http.HttpServiceResponse) res).callPage(JspServlet.TOTALCOMDET_JSP, req);
		 	res.sendRedirect(JspServlet.TOTALCOMDET_JSP);
			return;
		}
		if (value.equals("Modificar")) {
			bean.getLogin().setCodact("14");
			bean.getLogin().setAccion("Eliminación");
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilManagerError.jsp");
			 	//((com.sun.server.http.HttpServiceResponse) res).callPage(JspServlet.ERROR_MANAGER_JSP, req);
			 	res.sendRedirect(JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			bean.setStatus("0");
			bean.setNumsec(req.getParameter("TxtNumsec"));
			bean.setCodtot(req.getParameter("TxtCodtot"));
			bean.setSigope(req.getParameter("TxtSigope"));
			if (!containError(bean)) {
				bean.modificar();
				if (bean.getError().equals("")) {
					bean.setNumsec("");
					bean.setCodtot("");
					bean.setSigope("");
				}
			}
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebManager/MensajesAplicativo/DatosMensajes.jsp");
		 	//((com.sun.server.http.HttpServiceResponse) res).callPage(JspServlet.TOTALCOMDET_JSP, req);
		 	res.sendRedirect(JspServlet.TOTALCOMDET_JSP);
			return;
		}
		if (value.equals("Buscar")) {
			bean.setNumsec(req.getParameter("TxtNumsec"));
			bean.buscar();
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebManager/MensajesAplicativo/DatosMensajes.jsp");
		 	//((com.sun.server.http.HttpServiceResponse) res).callPage(JspServlet.TOTALCOMDET_JSP, req);
		 	res.sendRedirect(JspServlet.TOTALCOMDET_JSP);
			return;
		}
		if (value.equals("Eliminar")) {
			bean.getLogin().setCodact("14");
			bean.getLogin().setAccion("Eliminación");
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilManagerError.jsp");
			 	//((com.sun.server.http.HttpServiceResponse) res).callPage(JspServlet.ERROR_MANAGER_JSP, req);
			 	res.sendRedirect(JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			bean.eliminar(req);
			bean.setNumsec(req.getParameter("TxtNumsec"));
			bean.setCodtot(req.getParameter("TxtCodtot"));
			bean.setSigope(req.getParameter("TxtSigope"));
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebManager/MensajesAplicativo/DatosMensajes.jsp");
		 	//((com.sun.server.http.HttpServiceResponse) res).callPage(JspServlet.TOTALCOMDET_JSP, req);
		 	res.sendRedirect(JspServlet.TOTALCOMDET_JSP);
			return;
		}
		if (value.equals("Regresar")) {
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebManager/MensajesAplicativo/GrupoMensajes.jsp");
		 	//((com.sun.server.http.HttpServiceResponse) res).callPage(JspServlet.TOTALCOM_JSP, req);
		 	res.sendRedirect(JspServlet.TOTALCOM_JSP);
			return;
		}
		bean.loadGrid();
		bean.setError("");
		bean.setNumsec("");
		bean.setCodtot("");
		bean.setSigope("");
		//		res.sendRedirect("../../../SARAWebBanking/SARAWebManager/MensajesAplicativo/DatosMensajes.jsp");
	 	//((com.sun.server.http.HttpServiceResponse) res).callPage(JspServlet.TOTALCOMDET_JSP, req);
	 	res.sendRedirect(JspServlet.TOTALCOMDET_JSP);
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
private boolean containError(DetalleTotalCom bean) throws Exception {
	try {
		bean.setError("");
		return false;
	} catch (Exception e) {
		throw new Exception("containError() -> DatosDeMensajesServlet " + e.getMessage());
	}
}}