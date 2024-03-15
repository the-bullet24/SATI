package CosapiSoft.SARAWebBuilder;

/**
 * This type was created in VisualAge.
 */
import CosapiSoft.SARAWebBanking.*;
public class DatosDeTransaccionesServlet extends JavaServlet {
/**
 * AyudaDeCampoServlet constructor comment.
 */
public DatosDeTransaccionesServlet() {
	super();
}
public void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws java.io.IOException, javax.servlet.ServletException {
	InicioSesion login = new InicioSesion();
	login.setNameProducto("SARA Web Builder");
	try {
		if (!isLoggedIn(req)) {
			try {
				//req.getSession(false).setAttribute("error",new Exception(Mensajes.getMessage(Mensajes.SESION_TERMINADA) + " - " + this.getClass().toString()));         
				req.getSession(false).setAttribute("error", new Exception(Mensajes.getMessage(Mensajes.SESION_TERMINADA) + " - " + this.getClass().toString()));;
				setAttribute(req, "login", login);
				callPage(req, res, JspServlet.ERROR_JSP);
			}
			catch (javax.servlet.ServletException se) {
				res.getWriter().println(se.getMessage());
			}
			return;
		}
		String value = req.getParameter("BtnTrx");
		Transacciones trx = (Transacciones) req.getSession(false).getAttribute("ttradat");
		Diccionario tdicdat;
		if (req.getSession(false).getAttribute("tdicdat") != null) {
			tdicdat = (Diccionario) req.getSession(false).getAttribute("tdicdat");
		}
		else {
			tdicdat = new Diccionario();
			tdicdat.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("builder"));
			tdicdat.consultar();
			req.getSession(false).setAttribute("tdicdat", tdicdat);
		}
		//
		DatosDeTransacciones bean;
		if (req.getSession(false).getAttribute("tdictra") != null) {
			bean = ((DatosDeTransacciones) req.getSession(false).getAttribute("tdictra"));
		}
		else {
			bean = new DatosDeTransacciones();
			bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("builder"));
			bean.consultar();
			req.getSession(false).setAttribute("tdictra", bean);
		}
		bean.setCodtra(trx.getCodtra());

		//
		bean.getLogin().setError("");
		bean.getLogin().setNamePantalla(Mensajes.getMessage(Mensajes.DATOS_DE_TRANSACCIONES));
		if (value.equals("Agregar")) {
			bean.getLogin().setCodact("302");
			bean.getLogin().setAccion("Inserción");
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilBuilderError.jsp");
				callPage(req, res, JspServlet.ERROR_BUILDER_JSP);
				return;
			}
			bean.setStatus("0");
			bean.setTra_dic("");
			bean.setCodtra(req.getParameter("TxtCodtra"));
			bean.setCoddic(req.getParameter("TxtCoddic"));
			bean.setTxtctr(req.getParameter("TxtTxtctr"));
			bean.setOrdsnd(req.getParameter("TxtOrdsnd"));
			bean.setTxtdes(req.getParameter("TxtTxtdes"));
			bean.setFlgnul(req.getParameter("TxtFlgnul"));
			bean.setRutval(req.getParameter("TxtRutval"));
			if (!validate(bean)) {
				bean.agregar();
				if (bean.getError().equals("")) {
					bean.setCodtra("");
					bean.setCoddic("");
					bean.setTxtctr("");
					bean.setOrdsnd("");
					bean.setTxtdes("");
					bean.setFlgnul("");
					bean.setRutval("");
					bean.setDic_nam("");
				}
			}
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Transacciones/DatosDeTransaccion.jsp");
			callPage(req, res, JspServlet.DATOS_TRANSACCIONES_JSP);
			return;
		}
		if (value.equals("Modificar")) {
			bean.getLogin().setCodact("302");
			bean.getLogin().setAccion("Modificación");
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilBuilderError.jsp");
				callPage(req, res, JspServlet.ERROR_BUILDER_JSP);
				return;
			}
			bean.setStatus("0");
			bean.setTra_dic("");
			bean.setCodtra(req.getParameter("TxtCodtra"));
			bean.setCoddic(req.getParameter("TxtCoddic"));
			bean.setTxtctr(req.getParameter("TxtTxtctr"));
			bean.setOrdsnd(req.getParameter("TxtOrdsnd"));
			bean.setTxtdes(req.getParameter("TxtTxtdes"));
			bean.setFlgnul(req.getParameter("TxtFlgnul"));
			bean.setRutval(req.getParameter("TxtRutval"));
			if (!validate(bean)) {
				bean.modificar();
				if (bean.getError().equals("")) {
					bean.setCodtra("");
					bean.setCoddic("");
					bean.setTxtctr("");
					bean.setOrdsnd("");
					bean.setTxtdes("");
					bean.setFlgnul("");
					bean.setRutval("");
					bean.setDic_nam("");
				}
			}
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Transacciones/DatosDeTransaccion.jsp");
			callPage(req, res, JspServlet.DATOS_TRANSACCIONES_JSP);
			return;
		}
		if (value.equals("Eliminar")) {
			bean.getLogin().setCodact("302");
			bean.getLogin().setAccion("Eliminación");
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilBuilderError.jsp");
				callPage(req, res, JspServlet.ERROR_BUILDER_JSP);
				return;
			}
			bean.eliminar(req);
			bean.setCodtra("");
			bean.setCoddic("");
			bean.setTxtctr("");
			bean.setOrdsnd("");
			bean.setTxtdes("");
			bean.setFlgnul("");
			bean.setRutval("");
			bean.setDic_nam("");
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Transacciones/DatosDeTransaccion.jsp");
			callPage(req, res, JspServlet.DATOS_TRANSACCIONES_JSP);
			return;
		}
		if (value.equals("Buscar")) {
			bean.setTra_dic(req.getParameter("TxtTra_dic"));
			bean.setCodtra(req.getParameter("TxtCodtra"));
			bean.setCoddic(req.getParameter("TxtCoddic"));
			bean.buscar();
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Transacciones/DatosDeTransaccion.jsp");
			callPage(req, res, JspServlet.DATOS_TRANSACCIONES_JSP);
			return;
		}
		if (value.equals("Regresar")) {
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Transacciones/Transacciones.jsp");
			callPage(req, res, JspServlet.TRANSACCIONES_JSP);
			return;
		}
		bean.setError("");
		bean.loadGrid();
		bean.setCodtra("");
		bean.setCoddic("");
		bean.setTxtctr("");
		bean.setOrdsnd("");
		bean.setTxtdes("");
		bean.setFlgnul("");
		bean.setRutval("");
		bean.setDic_nam("");
		//		res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Transacciones/DatosDeTransaccion.jsp");
		callPage(req, res, JspServlet.DATOS_TRANSACCIONES_JSP);
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
	DatosDeTransacciones bean = (DatosDeTransacciones) obj;
	try {
		bean.setError("");
		if (bean.getCoddic().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_COD_DICCIONARIO));
			return true;
		}
		if (!CosapiSoft.SARAWebBanking.CString.isInteger(bean.getCoddic())) {
			bean.setError(Mensajes.getMessage(Mensajes.COD_DICCIONARIO_DEBE_CONTENER_NUMEROS));
			return true;
		}
		if (!Diccionario.search(bean.getCoddic())) {
			String str = Mensajes.getMessage(Mensajes.CODIGO_DE_DICCIONARIO_NO_EXISTE);
			int pos = str.indexOf('&');
			bean.setError(str.substring(0, pos) + bean.getCoddic() + str.substring(pos + 1));
			return true;
		}
		if (bean.getTxtctr().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_NOMBRE_DATO_TRX));
			return true;
		}
		if (bean.getOrdsnd().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_NRO_ORDEN_HOST));
			return true;
		}
		if (!CosapiSoft.SARAWebBanking.CString.isInteger(bean.getOrdsnd())) {
			bean.setError(Mensajes.getMessage(Mensajes.NRO_ORDEN_HOST_DEBE_CONTENER_NUMEROS));
			return true;
		}
		int seq = new Integer(bean.getOrdsnd()).intValue();
		if (seq < 0) {
			bean.setError(Mensajes.getMessage(Mensajes.NRO_ORDEN_HOST_DEBE_MAYOR_A_CERO));
			return true;
		}
		return false;
	}
	catch (Exception e) {
		throw new Exception("validate() -> DatosDeTransaccionesServlet " + e.getMessage());
	}
}
}