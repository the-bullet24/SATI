package CosapiSoft.SARAWebManager;

/**
 * This type was created in VisualAge.
 */
import pe.cosapi.common.Constante;
import CosapiSoft.SARAWebBanking.*;


public class DepartamentoServlet extends JavaServlet {
/**
 * AyudaDeCampoServlet constructor comment.
 */
public DepartamentoServlet() {
	super();
}
public void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws java.io.IOException, javax.servlet.ServletException {
	try {
	    if (Constante.SESSION_CADUCA.equals("2")) {
			try {
				req.getSession(false).setAttribute("error",new Exception(Mensajes.getMessage(Mensajes.SESION_TERMINADA) + " - " + this.getClass().toString()));;
				callPage(req, res, JspServlet.ERROR_JSP);
			}
			catch (javax.servlet.ServletException se) {
				res.getWriter().println(se.getMessage());
			}
			return;
		}
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
		String value = req.getParameter("BtnDep");
		value = (value == null) ? "Otro" : value.trim();
		Departamento bean;
		if (req.getSession(false).getAttribute("tdepdat") != null) {
			bean = (Departamento) (req.getSession(false).getAttribute("tdepdat"));
		}
		else {
			bean = new Departamento();
			bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("manager"));
			req.getSession(false).setAttribute("tdepdat", bean);
		}

		bean.getLogin().setError("");
		bean.getLogin().setNamePantalla("DEPARTAMENTOS");
		System.out.println("Mensaje Opción:"+bean.getLogin().getNamePantalla());
		if (value.equalsIgnoreCase("Otro")) {
				bean.getLogin().setCodact("325");
				bean.getLogin().setAccion("Consulta");
				if (!bean.getLogin().hasAccesAccion()) {
					callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
					return;
				}
			bean.consult();
		}
		
		if (value.equalsIgnoreCase("Agregar")) {
			bean.getLogin().setCodact("325");
			bean.getLogin().setAccion("Inserción");
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			bean.setStatus("0");
			bean.setCubigeo(req.getParameter("txtCubigeo"));
			bean.setCdepartamento(req.getParameter("txtCdepartamento"));
			bean.setDepartamento(req.getParameter("txtDepartamento"));
			bean.setCprovincia(req.getParameter("txtCprovincia"));
			bean.setProvincia(req.getParameter("txtProvincia"));
			bean.setCdistrito(req.getParameter("txtCdistrito"));
			bean.setDistrito(req.getParameter("txtDistrito"));
			bean.setZonal(req.getParameter("txtZonal"));
			bean.setUbigeo(req.getParameter("txtUbigeo"));
			bean.setCregion(req.getParameter("txtCregion"));
			
			if (!validate(bean)) {
				bean.setGrid(null);
				bean.insert();
				bean.loadGrid();
				if (bean.getError().equalsIgnoreCase("")) {
					bean.setCubigeo("");
					bean.setCdepartamento("");
					bean.setDepartamento("");
					bean.setCprovincia("");
					bean.setProvincia("");
					bean.setCdistrito("");
					bean.setDistrito("");
					bean.setZonal("");
					bean.setUbigeo("");
					bean.setCregion("");
				}
			}
			bean.loadGrid();
			callPage(req, res, JspServlet.DEPARTAMENTO_JSP);
			return;
		}

		if (value.equalsIgnoreCase("Modificar")) {
			bean.getLogin().setCodact("325");
			bean.getLogin().setAccion("Modificación");
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			bean.setStatus("0");
			bean.setCubigeo(req.getParameter("txtCubigeo"));
			bean.setCdepartamento(req.getParameter("txtCdepartamento"));
			bean.setDepartamento(req.getParameter("txtDepartamento"));
			bean.setCprovincia(req.getParameter("txtCprovincia"));
			bean.setProvincia(req.getParameter("txtProvincia"));
			bean.setCdistrito(req.getParameter("txtCdistrito"));
			bean.setDistrito(req.getParameter("txtDistrito"));
			bean.setZonal(req.getParameter("txtZonal"));
			bean.setUbigeo(req.getParameter("txtUbigeo"));
			bean.setCregion(req.getParameter("txtCregion"));
			if (!validate(bean)) {
				bean.setGrid(null);
				bean.update();
				bean.loadGrid();
				if (bean.getError().equalsIgnoreCase("")) {
					bean.setCubigeo("");
					bean.setCdepartamento("");
					bean.setDepartamento("");
					bean.setCprovincia("");
					bean.setProvincia("");
					bean.setCdistrito("");
					bean.setDistrito("");
					bean.setZonal("");
					bean.setUbigeo("");
					bean.setCregion("");
				}
			}
			
			callPage(req, res, JspServlet.DEPARTAMENTO_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Eliminar")) {
			bean.getLogin().setCodact("325");
			bean.getLogin().setAccion("Eliminación");
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			bean.setGrid(null);
			bean.delete(req);
			bean.setCubigeo("");
			bean.setCdepartamento("");
			bean.setDepartamento("");
			bean.setCprovincia("");
			bean.setProvincia("");
			bean.setCdistrito("");
			bean.setDistrito("");
			bean.setZonal("");
			bean.setUbigeo("");
			bean.setCregion("");
			bean.loadGrid();
			callPage(req, res, JspServlet.DEPARTAMENTO_JSP);
			return;
		}
		
		if (value.equalsIgnoreCase("Buscar")) {
			bean.setCubigeo(req.getParameter("txtCubigeo"));
			bean.setGrid(null);
			bean.search();
			bean.loadGrid();
			callPage(req, res, JspServlet.DEPARTAMENTO_JSP);
			return;
		}
		
		bean.setError("");
		bean.setGrid(null);
		bean.setCubigeo("");
		bean.setCdepartamento("");
		bean.setDepartamento("");
		bean.setCprovincia("");
		bean.setProvincia("");
		bean.setCdistrito("");
		bean.setDistrito("");
		bean.setZonal("");
		bean.setUbigeo("");
		bean.setCregion("");
		bean.loadGrid();
		callPage(req, res, JspServlet.DEPARTAMENTO_JSP);
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
		Departamento bean = (Departamento) obj;
		
		if (bean.getCubigeo().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEPDAT_CUBIGEO_BLANCO));
			return true;
		}
		if (bean.getCdepartamento().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEPDAT_CDEPARTAMENTO_BLANCO));
			return true;
		}
		if (bean.getDepartamento().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEPDAT_DEPARTAMENTO_BLANCO));
			return true;
		}
		if (bean.getCprovincia().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEPDAT_CPROVINCIA_BLANCO));
			return true;
		}
		if (bean.getProvincia().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEPDAT_PROVINCIA_BLANCO));
			return true;
		}
		if (bean.getCdistrito().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEPDAT_CDISTRITO_BLANCO));
			return true;
		}
		if (bean.getDistrito().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEPDAT_DISTRITO_BLANCO));
			return true;
		}
		if (bean.getZonal().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEPDAT_ZONAL_BLANCO));
			return true;
		}
		if (bean.getUbigeo().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEPDAT_UBIGEO_BLANCO));
			return true;
		}
		
		return false;
	}
	catch (Exception e) {
		throw new Exception("validate() -> LogDeOperacionesServlet - " + e.getMessage());
	}
	
}
}