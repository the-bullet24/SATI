package CosapiSoft.SARAWebBuilder;

/**
 * This type was created in VisualAge.
 */
import CosapiSoft.SARAWebBanking.*;
public class MetodosServlet extends JavaServlet {
/**
 * AyudaDeCampoServlet constructor comment.
 */
public MetodosServlet() {
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
		String value = req.getParameter("BtnMet");
		Metodos bean;
		Clases tcladat = (Clases) req.getSession(false).getAttribute("tcladat");
		if (req.getSession(false).getAttribute("tmetdat") != null) {
			bean = ((Metodos) req.getSession(false).getAttribute("tmetdat"));
		}
		else {
			bean = new Metodos();
			bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("builder"));
			bean.consult();
			req.getSession(false).setAttribute("tmetdat", bean);
			//			((com.sun.server.http.HttpServiceRequest) req).setAttribute("tmetdat", bean);
		}
		bean.setCodcla(tcladat.getCodcla());
		bean.setTxtcla(tcladat.getTxtcla());
		bean.getLogin().setError("");
		bean.getLogin().setNamePantalla(Mensajes.getMessage(Mensajes.METODOS));
		//
		if (value.equals("Agregar")) {
			bean.getLogin().setCodact("203");
			bean.getLogin().setAccion("Inserción");
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilBuilderError.jsp");
				callPage(req, res, CosapiSoft.SARAWebBanking.JspServlet.ERROR_BUILDER_JSP);
				return;
			}
			//			bean.loadGrid();
			bean.setStatus("0");
			bean.setCodmet(req.getParameter("TxtCodmet"));
			bean.setTxtmet(req.getParameter("TxtTxtmet"));
			bean.setTxtdes(req.getParameter("TxtTxtdes"));
			if (!validate(bean)) {
				bean.insert();
				if (bean.getError().equals("")) {
					bean.setCodmet("");
					bean.setTxtmet("");
					bean.setTxtdes("");
				}
			}
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Transacciones/Metodos.jsp");
			callPage(req, res, CosapiSoft.SARAWebBanking.JspServlet.METODOS_JSP);
			return;
		}
		if (value.equals("Modificar")) {
			bean.getLogin().setCodact("203");
			bean.getLogin().setAccion("Modificación");
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilBuilderError.jsp");
				callPage(req, res, CosapiSoft.SARAWebBanking.JspServlet.ERROR_BUILDER_JSP);
				return;
			}
			//			bean.loadGrid();
			bean.setStatus("0");
			bean.setCodmet(req.getParameter("TxtCodmet"));
			bean.setTxtmet(req.getParameter("TxtTxtmet"));
			bean.setTxtdes(req.getParameter("TxtTxtdes"));
			if (!validate(bean)) {
				bean.update();
				if (bean.getError().equals("")) {
					bean.setCodmet("");
					bean.setTxtmet("");
					bean.setTxtdes("");
				}
			}
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Transacciones/Metodos.jsp");
			callPage(req, res, CosapiSoft.SARAWebBanking.JspServlet.METODOS_JSP);
			return;
		}
		if (value.equals("Eliminar")) {
			bean.getLogin().setCodact("203");
			bean.getLogin().setAccion("Eliminación");
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilBuilderError.jsp");
				callPage(req, res, CosapiSoft.SARAWebBanking.JspServlet.ERROR_BUILDER_JSP);
				return;
			}
			bean.delete(req);
			bean.setCodmet("");
			bean.setTxtmet("");
			bean.setTxtdes("");
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Transacciones/Metodos.jsp");
			callPage(req, res, CosapiSoft.SARAWebBanking.JspServlet.METODOS_JSP);
			return;
		}
		if (value.equals("Buscar")) {
			//			bean.loadGrid();
			bean.setCodmet(req.getParameter("TxtCodmet"));
			if (bean.getCodmet().equals(""))
				bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_CODIGO_DE_METODO));
			else {
				bean.search();
			}
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Transacciones/Metodos.jsp");
			callPage(req, res, CosapiSoft.SARAWebBanking.JspServlet.METODOS_JSP);
			return;
		}
		if (value.equals("Regresar")) {
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Transacciones/Clases.jsp");
			callPage(req, res, JspServlet.CLASES_JSP);
			return;
		}
		bean.setError("");
		bean.setGrid(null);
		bean.loadGrid();
		bean.setCodmet("");
		bean.setTxtmet("");
		bean.setTxtdes("");
		//		res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Transacciones/Metodos.jsp");
		callPage(req, res, CosapiSoft.SARAWebBanking.JspServlet.METODOS_JSP);
	}
	catch (Exception e) {
		try {
			e.printStackTrace();
			setAttribute(req, "error", new Exception(Mensajes.ERROR_GENERAL));
			callPage(req, res, JspServlet.ERROR_JSP);
		}
		catch (Exception se) {
			res.getWriter().println(se.getMessage());
		}
	}
}
public boolean validate(Object obj) throws Exception {
	Metodos bean = (Metodos) obj;
	try {
		bean.setError("");
		if (bean.getCodmet().trim().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_CODIGO_DE_METODO));
			return true;
		}
		if (!CosapiSoft.SARAWebBanking.CString.isInteger(bean.getCodmet())) {
			bean.setError(Mensajes.getMessage(Mensajes.CODIGO_DE_METODO_DEBE_CONTENER_NUMEROS));
			return true;
		}
		if (bean.getTxtmet().trim().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_NOMBRE_METODO));
			return true;
		}
		return false;
	}
	catch (Exception e) {
		throw new Exception("\n... validate() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
}