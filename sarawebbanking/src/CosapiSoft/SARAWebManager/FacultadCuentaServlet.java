package CosapiSoft.SARAWebManager;

/**
 * This type was created in VisualAge.
 */
import CosapiSoft.SARAWebBanking.*;
public class FacultadCuentaServlet extends JavaServlet {
/**
 * AyudaDeCampoServlet constructor comment.
 */
public FacultadCuentaServlet() {
	super();
}
public void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws java.io.IOException, javax.servlet.ServletException {
	try {
		if (!isLoggedIn(req)) {
			try {
				req.getSession(false).setAttribute("error",new Exception(Mensajes.getMessage(Mensajes.SESION_TERMINADA) + " - " + this.getClass().toString()));;
				callPage(req, res, JspServlet.ERROR_JSP);
			}
			catch (javax.servlet.ServletException se) {
				res.getWriter().println(se.getMessage());
			}
			return;
		}
		String value = req.getParameter("BtnTrx");
		value = (value == null) ? "Otro" : value.trim();
		FacultadCuenta bean;
		if (req.getSession(false).getAttribute("tfacacc") != null) {
			bean = ((FacultadCuenta) req.getSession(false).getAttribute("tfacacc"));
		}
		else {
			bean = new FacultadCuenta();
			bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("manager"));
			req.getSession(false).setAttribute("tfacacc", bean);
		}
		bean.getLogin().setError("");
		bean.getLogin().setNamePantalla(Mensajes.getMessage(Mensajes.TRANSACCIONES));
		// System.out.println(value);
		if (value.equalsIgnoreCase("Otro")) {
			//bean.getLogin().setCodact("28");
			bean.getLogin().setAccion("Consulta");
			bean.setCodorg(req.getParameter("TxtCodorg"));
			bean.setCodigo(req.getParameter("TxtCodusr"));
			/**
			if (!bean.getLogin().hasAccesAccion()) {
				bean.getLogin().setCodact("29");
				if (!bean.getLogin().hasAccesAccion()) {
					//					res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilBuilderError.jsp");
					callPage(req, res, JspServlet.ERROR_BUILDER_JSP);
					return;
				}
			}
			*/
			bean.consultar();
		}

		//
		if (value.equalsIgnoreCase("Actualizar")) {
			//bean.getLogin().setCodact("29");
			bean.getLogin().setAccion("Inserción");
			/**
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_BUILDER_JSP);
				return;
			}
			*/
			bean.setCuenta(req.getParameter("CboCuentas"));
			if (req.getParameter("TxtLimitePago").equals("")) bean.setLimpay("0"); else bean.setLimpay(req.getParameter("TxtLimitePago"));
			if (req.getParameter("TxtLimiteTrans").equals("")) bean.setLimtrf("0"); else bean.setLimtrf(req.getParameter("TxtLimiteTrans"));
			bean.setStrtimmon(req.getParameter("TxtHoraInicialLunes"));
			bean.setStrtimtue(req.getParameter("TxtHoraInicialMartes"));
			bean.setStrtimwed(req.getParameter("TxtHoraInicialMiercoles"));
			bean.setStrtimthu(req.getParameter("TxtHoraInicialJueves"));
			bean.setStrtimfri(req.getParameter("TxtHoraInicialViernes"));
			bean.setStrtimsat(req.getParameter("TxtHoraInicialSabado"));
			bean.setStrtimsun(req.getParameter("TxtHoraInicialDomingo"));
			bean.setEndtimmon(req.getParameter("TxtHoraFinalLunes"));
			bean.setEndtimtue(req.getParameter("TxtHoraFinalMartes"));
			bean.setEndtimwed(req.getParameter("TxtHoraFinalMiercoles"));
			bean.setEndtimthu(req.getParameter("TxtHoraFinalJueves"));
			bean.setEndtimfri(req.getParameter("TxtHoraFinalViernes"));
			bean.setEndtimsat(req.getParameter("TxtHoraFinalSabado"));
			bean.setEndtimsun(req.getParameter("TxtHoraFinalDomingo"));
			if (req.getParameter("ChkConsulta")==null) bean.setFlgcns("0");
				else bean.setFlgcns("1");
			if (req.getParameter("ChkTransferencia")==null) bean.setFlgtrf("0");
				else bean.setFlgtrf("1");
			if (req.getParameter("ChkPago")==null) bean.setFlgpay("0");
				else bean.setFlgpay("1");
			if (req.getParameter("ChkLunes")==null) bean.setFlgmon("0");
				else bean.setFlgmon("1");
			if (req.getParameter("ChkMartes")==null) bean.setFlgtue("0");
				else bean.setFlgtue("1");
			if (req.getParameter("ChkMiercoles")==null) bean.setFlgwed("0");
				else bean.setFlgwed("1");
			if (req.getParameter("ChkJueves")==null) bean.setFlgthu("0");
				else bean.setFlgthu("1");
			if (req.getParameter("ChkViernes")==null) bean.setFlgfri("0");
				else bean.setFlgfri("1");
			if (req.getParameter("ChkSabado")==null) bean.setFlgsat("0");
				else bean.setFlgsat("1");
			if (req.getParameter("ChkDomingo")==null) bean.setFlgsun("0");
				else bean.setFlgsun("1");
			if (!validate(bean)) {
				bean.agregar();
				if (bean.getError().equals("")) {
					bean.setCuenta("");
					bean.setFlgcns("");
					bean.setFlgtrf("");
					bean.setFlgpay("");
					bean.setFlgmon("");
					bean.setFlgtue("");
					bean.setFlgwed("");
					bean.setFlgthu("");
					bean.setFlgfri("");
					bean.setFlgsat("");
					bean.setFlgsun("");
					bean.setLimpay("");
					bean.setLimtrf("");
					bean.setStrtimmon("");
					bean.setStrtimtue("");
					bean.setStrtimwed("");
					bean.setStrtimthu("");
					bean.setStrtimfri("");
					bean.setStrtimsat("");
					bean.setStrtimsun("");
					bean.setEndtimmon("");
					bean.setEndtimtue("");
					bean.setEndtimwed("");
					bean.setEndtimthu("");
					bean.setEndtimfri("");
					bean.setEndtimsat("");
					bean.setEndtimsun("");
				}
			}
			callPage(req, res, JspServlet.FACULTAD_CUENTA_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Buscar")) {
			bean.setCuenta(req.getParameter("CboCuentas"));
			if (bean.getCuenta().equals(""))
				bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_NOMBRE_USUARIO));
			else {
				bean.buscar();
			}
			callPage(req, res, JspServlet.FACULTAD_CUENTA_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Regresar")) {
			callPage(req, res, JspServlet.USUARIOS_EMPRESA_JSP);
			return;
		}
		bean.setError("");
		bean.loadGrid();
		bean.setCuenta("");
		bean.setFlgcns("");
		bean.setFlgtrf("");
		bean.setFlgpay("");
		bean.setFlgmon("");
		bean.setFlgtue("");
		bean.setFlgwed("");
		bean.setFlgthu("");
		bean.setFlgfri("");
		bean.setFlgsat("");
		bean.setFlgsun("");
		bean.setStrtimmon("");
		bean.setStrtimtue("");
		bean.setStrtimwed("");
		bean.setStrtimthu("");
		bean.setStrtimfri("");
		bean.setStrtimsat("");
		bean.setStrtimsun("");
		bean.setEndtimmon("");
		bean.setEndtimtue("");
		bean.setEndtimwed("");
		bean.setEndtimthu("");
		bean.setEndtimfri("");
		bean.setEndtimsat("");
		bean.setEndtimsun("");
		callPage(req, res, JspServlet.FACULTAD_CUENTA_JSP);
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
	FacultadCuenta bean = (FacultadCuenta) obj;
	try {
		bean.setError("");
		/*if (bean.getId().trim().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_NOMBRE_USUARIO));
			return true;
		}
		if (bean.getNombre().trim().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_NOMBRE_USUARIO));
			return true;
		}
		if (bean.getApaterno().trim().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_NOMBRE_USUARIO));
			return true;
		}
		if (bean.getAmaterno().trim().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_NOMBRE_USUARIO));
			return true;
		}
		if (bean.getPass().trim().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_NOMBRE_USUARIO));
			return true;
		}*/
		return false;
	}
	catch (Exception e) {
		throw new Exception("\n... validate() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
}