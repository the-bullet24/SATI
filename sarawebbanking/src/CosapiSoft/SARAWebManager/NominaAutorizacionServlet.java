package CosapiSoft.SARAWebManager;

/**
 * This type was created in VisualAge.
 */
import CosapiSoft.SARAWebBanking.*;
public class NominaAutorizacionServlet extends JavaServlet {
/**
 * AyudaDeCampoServlet constructor comment.
 */
public NominaAutorizacionServlet() {
	super();
}
public void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws java.io.IOException, javax.servlet.ServletException {
	try {
		if (!isLoggedIn(req)) {
			try {
				req.getSession(false).setAttribute("error",new Exception(Mensajes.getMessage(Mensajes.SESION_TERMINADA) + " - " + this.getClass().toString()));         ;
				callPage(req, res, JspServlet.ERROR_JSP);
			}
			catch (javax.servlet.ServletException se) {
				res.getWriter().println(se.getMessage());
			}
			return;
		}
		String value = req.getParameter("BtnTrx");
		value = (value == null) ? "Otro" : value.trim();
		NominaAutorizacion bean;
		if (req.getSession(false).getAttribute("tlstaut") != null) {
			bean = ((NominaAutorizacion) req.getSession(false).getAttribute("tlstaut"));
		}
		else {
			bean = new NominaAutorizacion();
			bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("manager"));
			req.getSession(false).setAttribute("tlstaut", bean);
		}
		bean.getLogin().setError("");
		bean.getLogin().setNamePantalla(Mensajes.getMessage(Mensajes.TRANSACCIONES));
		if (value.equalsIgnoreCase("Otro")) {
			bean.getLogin().setCodact("28");
			bean.getLogin().setAccion("Consulta");
			bean.setCodorg(req.getParameter("TxtCodorg"));
			bean.setCodlst(req.getParameter("TxtCodlst"));
			if (!bean.getLogin().hasAccesAccion()) {
				bean.getLogin().setCodact("29");
				if (!bean.getLogin().hasAccesAccion()) {
					//					res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilBuilderError.jsp");
					callPage(req, res, JspServlet.ERROR_BUILDER_JSP);
					return;
				}
			}
			bean.consultar();
		}

		//
		if (value.equalsIgnoreCase("Agregar")) {
			bean.getLogin().setCodact("29");
			bean.getLogin().setAccion("Inserción");
			String todo=req.getParameter("hid01").substring(1);
			bean.gridIn=new java.util.Vector();
			int cont=0;
			while (todo.indexOf("~")>2)
			{
				java.util.Vector tmp=new java.util.Vector();
				cont++;
				String a=todo.substring(0,todo.indexOf("~"));
				tmp.addElement(a.substring(0,a.indexOf("-")));
				tmp.addElement(""+cont);
				bean.gridIn.addElement(tmp);
				if (todo.indexOf("~")>2)
					todo=todo.substring(todo.indexOf("~")+1);
				else todo="";
			}
			
			bean.agregar();
			callPage(req, res, JspServlet.NOMINA_AUTORIZACION_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Regresar")) {
			callPage(req, res, JspServlet.NOMINA_JSP);
			return;
		}
		bean.setError("");
		bean.loadGrid();
		callPage(req, res, JspServlet.NOMINA_AUTORIZACION_JSP);
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
	NominaAutorizacion bean = (NominaAutorizacion) obj;
	try {
		bean.setError("");
		return true;
	}
	catch (Exception e) {
		throw new Exception("\n... validate() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
}