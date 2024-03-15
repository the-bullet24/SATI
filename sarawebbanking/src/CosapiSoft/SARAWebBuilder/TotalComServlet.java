package CosapiSoft.SARAWebBuilder;

/**
 * This type was created in VisualAge.
 */
import CosapiSoft.SARAWebBanking.*;
public class TotalComServlet extends JavaServlet {
/**
 * AyudaDeCampoServlet constructor comment.
 */
public TotalComServlet() {
	super();
}
public void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws java.io.IOException, javax.servlet.ServletException {
	try {
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
		TotalCom bean;
		if (req.getSession(false).getAttribute("ttotcom") != null) {
			bean = ((TotalCom) req.getSession(false).getAttribute("ttotcom"));
		}
		else {
			bean = new TotalCom();
			bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("builder"));
			req.getSession(false).setAttribute("ttotcom", bean);
		}

		//
		String value = (req.getParameter("BtnTot") == null) ? "Otro" : req.getParameter("BtnTot").trim();
		bean.getLogin().setError("");
		bean.getLogin().setNamePantalla(Mensajes.getMessage(Mensajes.TOTALCOM));
		if (value.equalsIgnoreCase("Otro")) {
			bean.getLogin().setCodact("32");
			bean.getLogin().setAccion("Consulta");  
			if (!bean.getLogin().hasAccesAccion()) {
				bean.getLogin().setCodact("33");
				if (!bean.getLogin().hasAccesAccion()) {
					//					res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilBuilderError.jsp");
					callPage(req, res, JspServlet.ERROR_BUILDER_JSP);
					return;
				}
			}
			bean.consult();
		}
		if (value.equalsIgnoreCase("Agregar")) {
			bean.getLogin().setCodact("33");
			bean.getLogin().setAccion("Inserción");
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilBuilderError.jsp");
				callPage(req, res, JspServlet.ERROR_BUILDER_JSP);
				return;
			}
			bean.setStatus("0");
			bean.setCodtotcom(req.getParameter("TxtCodtotcom"));
			bean.setNomtotcom(req.getParameter("TxtNomtotcom"));
			if (!validate(bean)) {
				bean.insert();
				if (bean.getError().equals("")) {
					bean.setCodtotcom("");
					bean.setNomtotcom("");
				}
			}
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Transacciones/TotalCom.jsp");
			callPage(req, res, JspServlet.TOTALCOM_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Modificar")) {
			bean.getLogin().setCodact("33");
			bean.getLogin().setAccion("Modificación");
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilBuilderError.jsp");
				callPage(req, res, JspServlet.ERROR_BUILDER_JSP);
				return;
			}
			bean.setStatus("0");
			bean.setCodtotcom(req.getParameter("TxtCodtotcom"));
			bean.setNomtotcom(req.getParameter("TxtNomtotcom"));
			if (!validate(bean)) {
				bean.update();
				if (bean.getError().equals("")) {
					bean.setCodtotcom("");
					bean.setNomtotcom("");
				}
			}
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Transacciones/TotalCom.jsp");
			callPage(req, res, JspServlet.TOTALCOM_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Eliminar")) {
			bean.getLogin().setCodact("33");
			bean.getLogin().setAccion("Eliminación");
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilBuilderError.jsp");
				callPage(req, res, JspServlet.ERROR_BUILDER_JSP);
				return;
			}
			bean.delete(req);
			bean.setCodtotcom("");
			bean.setNomtotcom("");
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Transacciones/TotalCom.jsp");
			callPage(req, res, JspServlet.TOTALCOM_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Detalle")) {
			bean.setCodtotcom(req.getParameter("TxtCodtotcom"));
			if (bean.search())
				res.sendRedirect(JspServlet.TOTALCOMDET_SERVLET + "?TxtCodtotcom=" + bean.getcodtotcom() + "&BtnDet=Otro");
			else {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebManager/MensajesAplicativo/GrupoMensajes.jsp");
				callPage(req, res, JspServlet.TOTALCOM_JSP);
			}
			return;
		}
		if (value.equalsIgnoreCase("Buscar")) {
			bean.setCodtotcom(req.getParameter("TxtCodtotcom"));
			if (bean.getcodtotcom().equals(""))
				bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_CODIGO_DE_TOTALCOM));
			else {
				bean.search();
			}
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Transacciones/TotalCom.jsp");
			callPage(req, res, JspServlet.TOTALCOM_JSP);
			return;
		}
		bean.setError("");
		bean.loadGrid();
		bean.setCodtotcom("");
		bean.setNomtotcom("");
		//		res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Transacciones/TotalCom.jsp");
		callPage(req, res, JspServlet.TOTALCOM_JSP);
	}
	catch (Exception e) {
		try {
			e.printStackTrace();
			setAttribute(req, "error", new Exception(Mensajes.ERROR_GENERAL));
			callPage(req, res, JspServlet.ERROR_JSP);
		}
		catch (javax.servlet.ServletException se) {
			res.getWriter().println(se.getMessage());
		}
	}
}
public boolean validate(Object obj) throws Exception {
	try {
		TotalCom bean = (TotalCom) obj;
		bean.setError("");
		if (bean.getcodtotcom().trim().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_CODIGO_DE_TOTALCOM));
			return true;
		}
		if (!CosapiSoft.SARAWebBanking.CString.isInteger(bean.getcodtotcom())) {
			bean.setError(Mensajes.getMessage(Mensajes.CODIGO_DE_TOTALCOM_DEBE_CONTENER_NUMEROS));
			return true;
		}
		if (bean.getNomtotcom().trim().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_NOMBRE_DE_TOTALCOM));
			return true;
		}
		return false;
	}
	catch (Exception e) {
		throw new Exception("validate() -> TotalComServlet " + e.getMessage());
	}
}
}