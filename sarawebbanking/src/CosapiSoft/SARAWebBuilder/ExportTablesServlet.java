package CosapiSoft.SARAWebBuilder;

/**
 * This type was created in VisualAge.
 */
import CosapiSoft.SARAWebBanking.*;
import CosapiSoft.SARAWebBuilder.*;

public class ExportTablesServlet extends JavaServlet {

public ExportTablesServlet() {
	super();
}
public void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws java.io.IOException, javax.servlet.ServletException {
	InicioSesion login = new InicioSesion();
	login.setNameProducto("SARA Web Builder - Exportar Tablas del Aplicativo");
	String modulo="builder";
	try {
		if (req.getSession() == null) {
			try {
				req.getSession().setAttribute("error",new Exception(Mensajes.getMessage(Mensajes.SESION_TERMINADA) + " - " + this.getClass().toString()));;
				setAttribute(req, "login", login);
				callPage(req, res, JspServlet.ERROR_JSP);
			}
			catch (javax.servlet.ServletException se) {
				
				res.getWriter().println(se.getMessage());
			}
			return;
		}
		String value = req.getParameter("BtnDis");
		value = (value == null) ? "Otro" : value.trim();
		ExportTables bean;
		
		if (req.getSession(false).getAttribute("beanDistri") != null) {
			bean = ((ExportTables) req.getSession(false).getAttribute("beanDistri"));
		}
		else {
			bean = new ExportTables();
			bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("builder"));
			req.getSession(false).setAttribute("beanDistri", bean);
		}
		//bean.getLogin().setCodact("201");
		bean.getLogin().setError("");
		bean.getLogin().setNamePantalla("EXPORTAR TABLAS");
		System.out.println("Mensaje Opción:"+bean.getLogin().getNamePantalla());
		if (value.equalsIgnoreCase("Otro")) {
			
			//bean.getLogin().setNamePantalla("GRUPO DE TRANSACCIONES");
			bean.getLogin().setCodact("209");
			bean.getLogin().setAccion("Consulta");
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_BUILDER_JSP);
				return;
			}
			callPage(req, res, JspServlet.DISTRIBUCION_JSP+"?mensajeDistribucion=Seleccione una opción");
			return;
			
		}else{//EXPORTA TABLAS
			String[] listaAcciones = req.getParameterValues("rbAccion");
			String ruta= req.getParameter("txtRuta");
			bean.getLogin().setAccion("Exportar");
			for(int i=0;i<listaAcciones.length;i++){
				System.out.println("Elementos :" + listaAcciones[i]);
				if(listaAcciones[i].equals("01")){
					bean.exportar("TCOMMSG","pv_swb","01",ruta);
					bean.exportar("TDICDAT","pv_swb","01",ruta);
				}else if(listaAcciones[i].equals("02")){
					bean.exportar("THLPDET","pv_swb","02",ruta);
					bean.exportar("THLPDAT","pv_swb","02",ruta);
				}else if(listaAcciones[i].equals("03")){
					bean.exportar("TMSGDAT","pv_swb","03",ruta);
					bean.exportar("TGRPMSG","pv_swb","03",ruta);
				}else if(listaAcciones[i].equals("04")){
					bean.exportar("TMSGPAN","pv_swb","04",ruta);
					bean.exportar("TGRPPAN","pv_swb","04",ruta);
				}else if(listaAcciones[i].equals("05")){
					bean.exportar("TLIMTRA","pv_swb","05",ruta);
				}else if(listaAcciones[i].equals("06")){
					bean.exportar("TMENDAT","pv_swb","06",ruta);
				}else if(listaAcciones[i].equals("07")){
					bean.exportar("TTRAHOR","pv_swb","07",ruta);
				}else if(listaAcciones[i].equals("08")){
					bean.exportar("TMSGHST","pv_swb","08",ruta);
				}else if(listaAcciones[i].equals("09")){
					bean.exportar("TDEPDAT","pv_swb","09",ruta);
					bean.exportar("TAGEDAT","pv_swb","09",ruta);
				}else if(listaAcciones[i].equals("10")){
					bean.exportar("TTRADAT","pv_swb","10",ruta);
					bean.exportar("TCTRTRA","pv_swb","10",ruta);
					//bean.exportar("TPRFTAR","pv_swb","10",ruta);
					bean.exportar("TMETARGALT","pv_swb","10",ruta);
					bean.exportar("TMETARG","pv_swb","10",ruta);
					bean.exportar("TSCHTRA","pv_swb","10",ruta);
					bean.exportar("TTRAFAS","pv_swb","10",ruta);
					bean.exportar("TGRPTRA","pv_swb","10",ruta);
					bean.exportar("TJOUDIC","pv_swb","10",ruta);
					bean.exportar("TCURDAT","pv_swb","10",ruta);
					//bean.exportar("TLIMDAT","pv_swb","10",ruta);
				}else if(listaAcciones[i].equals("11")){
					bean.exportar("TMETDAT","pv_swb","11",ruta);
					bean.exportar("TCLADAT","pv_swb","11",ruta);
				}else if(listaAcciones[i].equals("12")){
					bean.exportar("TDICDAT","pv_swb","12",ruta);
				}
			}
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			req.setAttribute("mensajeDistribucion","La exportación de tablas ha sido realizada con éxito");
			
			req.getRequestDispatcher("/SARAWebBuilder/Distribucion/Distribucion.jsp").forward(req,res);
			//callPage(req, res, JspServlet.DISTRIBUCION_JSP+"?mensajeDistribucion=La exportación de tablas ha sido ralizada con éxito");
		}
	}
	
	catch (Exception e) {
		System.out.println("Exception!!! " + e);
		e.printStackTrace();
		System.out.println("Exception!!! " + e.getMessage());
		try {
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
		try {
			return false;
		}
		catch (Exception e) {
			throw new Exception("\n... validate - " + getClass().getName() + " -> " + e.getMessage());
		}
	}
}

