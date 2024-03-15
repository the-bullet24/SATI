package CosapiSoft.SARAWebBanking;


import CosapiSoft.SARAWebManager.*;

public class MenuServlet extends JavaServlet {
/**
 * AyudaDeCampoServlet constructor comment.
 */
	
public MenuServlet() {
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
		String value = req.getParameter("BtnMen");
		value = (value == null) ? "Otro" : value.trim();
		Menu bean;
		if (req.getSession(false).getAttribute("tmendat") != null) {
			bean = (Menu) (req.getSession(false).getAttribute("tmendat"));
		}
		else {
			bean = new Menu();
			bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("manager"));
			req.getSession(false).setAttribute("tmendat", bean);
		}

		//

		bean.getLogin().setError("");
		bean.getLogin().setNamePantalla("MENU");
		System.out.println("Mensaje Opción:"+bean.getLogin().getNamePantalla());
		if (value.equalsIgnoreCase("Otro")) {
			/**
			*bean.getLogin().setCodact("94");
			*bean.getLogin().setAccion("Consulta");
			*if (!bean.getLogin().hasAccesAccion()) {
			*/
				bean.getLogin().setCodact("321");
				bean.getLogin().setAccion("Consulta");
				if (!bean.getLogin().hasAccesAccion()) {
					callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
					return;
				}
			/**}
			 * 
			 */
			bean.consult();
		}
		
		if (value.equalsIgnoreCase("Grabar")) {
			bean.getLogin().setCodact("321");
			bean.getLogin().setAccion("Modificación");
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			bean.setCodper("");
			bean.setCodtar("");
			bean.setGrid(null);
			bean.setCodper(req.getParameter("cboPersona"));
			bean.setCodtar(req.getParameter("cboTarjeta"));
			
			bean.delete(req);
			callPage(req, res, JspServlet.MENU_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Mostrar")) {
			bean.getLogin().setCodact("321");
			bean.getLogin().setAccion("Modificación");
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			bean.setCodper("");
			bean.setCodtar("");
			bean.setGrid(null);
			bean.setCodper(req.getParameter("cboPersona"));
			bean.setCodtar(req.getParameter("cboTarjeta"));
			bean.loadGrid();
			
			
			callPage(req, res, JspServlet.MENU_JSP);
			return;
		}
		
		bean.setError("");
		bean.setCodper("");
		bean.setCodtar("");
		
		callPage(req, res, JspServlet.MENU_JSP);
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
	Limites bean = (Limites) obj;
	try {
		bean.setError("");
		// System.out.println("entro al validate");
		if (bean.getLimdayinf().equals("") || bean.getLimdaysup().equals("") || bean.getLimopeinf().equals("") || bean.getLimopesup().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.LIMITE_NULO));
			return true;
		}
		if (!CFormat.validarImporte(bean.getLimdayinf()) || !CFormat.validarImporte(bean.getLimdaysup()) || !CFormat.validarImporte(bean.getLimopeinf()) || !CFormat.validarImporte(bean.getLimopesup())) {
			bean.setError(Mensajes.getMessage(Mensajes.LIMITE_INCORRECTO));
			return true;
		}
		if((Float.parseFloat(bean.getLimdayinf()) >= Float.parseFloat(bean.getLimdaysup()))){
			bean.setError(Mensajes.getMessage(Mensajes.LIMITE_INF_MAYOR_SUP));
			return true;
		}
		if((Float.parseFloat(bean.getLimopeinf()) >= Float.parseFloat(bean.getLimopesup()))){
			bean.setError(Mensajes.getMessage(Mensajes.LIMITE_INF_MAYOR_SUP));
			return true;
		}
		if((Float.parseFloat(bean.getLimdayinf()) <= Float.parseFloat(bean.getLimopeinf())) || (Float.parseFloat(bean.getLimdaysup()) <= Float.parseFloat(bean.getLimopesup()))){
			bean.setError(Mensajes.getMessage(Mensajes.LIMITE_OPE_MAYOR_DIA));
			return true;
		}
		if(bean.getTrahst().equals("")){
			bean.setError(Mensajes.getMessage(Mensajes.TRANSACCION_INVALIDA));
			return true;
		}
		if(!CFormat.ValidoAN(bean.getTrahst())){
			bean.setError(Mensajes.getMessage(Mensajes.TRANSACCION_INVALIDA));
			return true;
		}
			
		return false;
	}
	catch (Exception e) {
		e.printStackTrace();
		throw new Exception("\n... validate() - " + getClass().getName() + " -> " + e.getMessage());
	}
	
}
}