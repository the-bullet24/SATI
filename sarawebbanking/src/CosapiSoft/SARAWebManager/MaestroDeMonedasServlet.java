package CosapiSoft.SARAWebManager;

/**
 * This type was created in VisualAge.
 */
public class MaestroDeMonedasServlet extends javax.servlet.http.HttpServlet {
/**
 * AyudaDeCampoServlet constructor comment.
 */
public MaestroDeMonedasServlet() {
	super();
}
private boolean containError(MaestroDeMonedas bean) throws Exception {
	try {
		bean.setError("");
		if (bean.getCodcur().equals("")) {
			bean.setError("Error : Ingrese el Código de Moneda");
			return true;
		}
		if (!CosapiSoft.SARAWebBanking.CString.isInteger(bean.getCodcur())) {
			bean.setError("Error : El Código de Moneda sólo debe contener números");
			return true;
		}
		if (bean.getTxtcursht().equals("")) {
			bean.setError("Error : Ingrese Nombre Corto para la Moneda");
			return true;
		}
		if (bean.getTxtcurlon().equals("")) {
			bean.setError("Error : Ingrese Nombre Largo para la Moneda");
			return true;
		}
		if (bean.getCodcurbcr().equals("")) {
			bean.setError("Error : Ingrese Código BCR para la Moneda");
			return true;
		}
		if (bean.getCodcurswi().equals("")) {
			bean.setError("Error : Ingrese Código SWIFT para la Moneda");
			return true;
		}
		if (bean.getAmoarbpur().equals("")) {
			bean.setError("Error : Ingrese Valor de Compra para la Moneda");
			return true;
		}
		if (!CosapiSoft.SARAWebBanking.CString.isDouble(bean.getAmoarbpur())) {
			bean.setError("Error : El Valor de Compra sólo debe ser un valor númerico");
			return true;
		}
		if (bean.getAmoarbsal().equals("")) {
			bean.setError("Error : Ingrese Valor de Venta para la Moneda");
			return true;
		}
		if (!CosapiSoft.SARAWebBanking.CString.isDouble(bean.getAmoarbsal())) {
			bean.setError("Error : El Valor de Venta sólo debe ser un valor númerico");
			return true;
		}
		return false;
	} catch (Exception e) {
		throw new Exception("containError() -> MaestroDeMonedasServlet " + e.getMessage());
	}
}
public void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws javax.servlet.ServletException, java.io.IOException {
	doPost(req,res);
}
public void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws java.io.IOException {
	try {
		if (req.getSession(false) == null) {
			res.getWriter().println("La Sesión se ha Terminado - MaestroDeMonedas");
			return;
		}
		String value = req.getParameter("BtnCur");
		MaestroDeMonedas bean;
		//		if (((com.sun.server.http.HttpServiceRequest)req).getSession(false).getValue("tcurdat") != null) {
		//			bean = (MaestroDeMonedas) (((com.sun.server.http.HttpServiceRequest)req).getSession(false).getValue("tcurdat"));
		//		} else {
		bean = new MaestroDeMonedas();
		bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("manager"));
		//			req.getSession(false).putValue("tcurdat", bean);
	 	req.setAttribute("tcurdat", bean);
		//		}
		//
		bean.getLogin().setError("");
		bean.getLogin().setNamePantalla("Maestro de Monedas");
		if (value.equals("Otro")) {
			//bean.getLogin().setCodact("15");
			bean.getLogin().setAccion("Consulta");
			/**
			if (!bean.getLogin().hasAccesAccion()) {
				bean.getLogin().setCodact("16");
				if (!bean.getLogin().hasAccesAccion()) {
					//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilManagerError.jsp");
				 	res.sendRedirect("/SARAWebBanking/SARAWebBanking/PerfilManagerError.jsp");
				 	//((com.sun.server.http.HttpServiceResponse) res).callPage("/SARAWebBanking/SARAWebBanking/PerfilManagerError.jsp", req);
					return;
				}
			}
			*/
			bean.consultar();
		}
		if (value.equals("Agregar")) {
			//bean.getLogin().setCodact("16");
			bean.getLogin().setAccion("Inserción");
			/**
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilManagerError.jsp");
			 	res.sendRedirect("/SARAWebBanking/SARAWebBanking/PerfilManagerError.jsp");
			 	//((com.sun.server.http.HttpServiceResponse) res).callPage("/SARAWebBanking/SARAWebBanking/PerfilManagerError.jsp", req);
				return;
			}
			*/
			bean.loadGrid();
			bean.setStatus("0");
			bean.setCodcur(req.getParameter("TxtCodcur"));
			bean.setTxtcurlon(req.getParameter("TxtTxtcurlon"));
			bean.setTxtcursht(req.getParameter("TxtTxtcursht"));
			bean.setCodcurbcr(req.getParameter("TxtCodcurbcr"));
			bean.setCodcurswi(req.getParameter("TxtCodcurswi"));
			bean.setCodcursym(req.getParameter("TxtCodcursym"));
			bean.setAmoarbpur(req.getParameter("TxtAmoarbpur"));
			bean.setAmoarbsal(req.getParameter("TxtAmoarbsal"));
			bean.setFlgcurdec(req.getParameter("CmbFlgcurdec"));
			if (!containError(bean)) {
				bean.agregar();
				if (bean.getError().equals("")) {
					bean.setCodcur("");
					bean.setTxtcurlon("");
					bean.setTxtcursht("");
					bean.setCodcurbcr("");
					bean.setCodcurswi("");
					bean.setCodcursym("");
					bean.setAmoarbpur("");
					bean.setAmoarbsal("");
					bean.setFlgcurdec("");
				}
			}
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebManager/Tablas/MaestroDeMonedas.jsp");
		 	res.sendRedirect("/SARAWebBanking/SARAWebManager/Tablas/MaestroDeMonedas.jsp");
		 	//((com.sun.server.http.HttpServiceResponse) res).callPage("/SARAWebBanking/SARAWebManager/Tablas/MaestroDeMonedas.jsp", req);
			return;
		}
		if (value.equals("Modificar")) {
			//bean.getLogin().setCodact("16");
			bean.getLogin().setAccion("Modificación");
			/**
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilManagerError.jsp");
			 	res.sendRedirect("/SARAWebBanking/SARAWebBanking/PerfilManagerError.jsp");
			 	//((com.sun.server.http.HttpServiceResponse) res).callPage("/SARAWebBanking/SARAWebBanking/PerfilManagerError.jsp", req);
				return;
			}
			*/
			bean.loadGrid();
			bean.setCodcur(req.getParameter("TxtCodcur"));
			bean.setTxtcurlon(req.getParameter("TxtTxtcurlon"));
			bean.setTxtcursht(req.getParameter("TxtTxtcursht"));
			bean.setCodcurbcr(req.getParameter("TxtCodcurbcr"));
			bean.setCodcurswi(req.getParameter("TxtCodcurswi"));
			bean.setCodcursym(req.getParameter("TxtCodcursym"));
			bean.setAmoarbpur(req.getParameter("TxtAmoarbpur"));
			bean.setAmoarbsal(req.getParameter("TxtAmoarbsal"));
			bean.setFlgcurdec(req.getParameter("CmbFlgcurdec"));
			if (!containError(bean)) {
				bean.modificar();
				if (bean.getError().equals("")) {
					bean.setCodcur("");
					bean.setTxtcurlon("");
					bean.setTxtcursht("");
					bean.setCodcurbcr("");
					bean.setCodcurswi("");
					bean.setCodcursym("");
					bean.setAmoarbpur("");
					bean.setAmoarbsal("");
					bean.setFlgcurdec("");
				}
			}
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebManager/Tablas/MaestroDeMonedas.jsp");
		 	res.sendRedirect("/SARAWebBanking/SARAWebManager/Tablas/MaestroDeMonedas.jsp");
		 	//((com.sun.server.http.HttpServiceResponse) res).callPage("/SARAWebBanking/SARAWebManager/Tablas/MaestroDeMonedas.jsp", req);
			return;
		}
		if (value.equals("Eliminar")) {
			//bean.getLogin().setCodact("16");
			bean.getLogin().setAccion("Eliminación");
			/**
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilManagerError.jsp");
			 	res.sendRedirect("/SARAWebBanking/SARAWebBanking/PerfilManagerError.jsp");
			 	//((com.sun.server.http.HttpServiceResponse) res).callPage("/SARAWebBanking/SARAWebBanking/PerfilManagerError.jsp", req);
				return;
			}
			*/
			bean.eliminar(req);
			bean.setCodcur("");
			bean.setTxtcurlon("");
			bean.setTxtcursht("");
			bean.setCodcurbcr("");
			bean.setCodcurswi("");
			bean.setCodcursym("");
			bean.setAmoarbpur("");
			bean.setAmoarbsal("");
			bean.setFlgcurdec("");
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebManager/Tablas/MaestroDeMonedas.jsp");
		 	res.sendRedirect("/SARAWebBanking/SARAWebManager/Tablas/MaestroDeMonedas.jsp");
		 	//((com.sun.server.http.HttpServiceResponse) res).callPage("/SARAWebBanking/SARAWebManager/Tablas/MaestroDeMonedas.jsp", req);
			return;
		}
		if (value.equals("Buscar")) {
			bean.loadGrid();
			bean.setCodcur(req.getParameter("TxtCodcur"));
			bean.buscar();
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebManager/Tablas/MaestroDeMonedas.jsp");
		 	res.sendRedirect("/SARAWebBanking/SARAWebManager/Tablas/MaestroDeMonedas.jsp");
		 	//((com.sun.server.http.HttpServiceResponse) res).callPage("/SARAWebBanking/SARAWebManager/Tablas/MaestroDeMonedas.jsp", req);
			return;
		}
		bean.setError("");
		bean.loadGrid();
		bean.setCodcur("");
		bean.setTxtcurlon("");
		bean.setTxtcursht("");
		bean.setCodcurbcr("");
		bean.setCodcurswi("");
		bean.setCodcursym("");
		bean.setAmoarbpur("");
		bean.setAmoarbsal("");
		bean.setFlgcurdec("");
		//		res.sendRedirect("../../../SARAWebBanking/SARAWebManager/Tablas/MaestroDeMonedas.jsp");
	 	res.sendRedirect("/SARAWebBanking/SARAWebManager/Tablas/MaestroDeMonedas.jsp");
	 	//((com.sun.server.http.HttpServiceResponse) res).callPage("/SARAWebBanking/SARAWebManager/Tablas/MaestroDeMonedas.jsp", req);
	} catch (Exception e) {
		res.getWriter().println("Maestro de Monedas Servlet --> doPost() \n" + e.getMessage());
	}
}
}