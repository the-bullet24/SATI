package CosapiSoft.SARAWebBuilder;

/**
 * This type was created in VisualAge.
 */
import java.sql.SQLException;

import com.cosapisoft.sarawebbanking.admin.EjecucionDeProcesoServlet;

import CosapiSoft.SARAWebBanking.*;
import CosapiSoft.SARAWebBuilder.*;

public class ImportTablesServlet extends JavaServlet {

public ImportTablesServlet() {
	super();
}
public void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws java.io.IOException, javax.servlet.ServletException {
	InicioSesion login = new InicioSesion();
	login.setNameProducto("SARA Web Builder - Importar Tablas del Aplicativo");
	String modulo="builder";
	ImportTables bean= null;
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
		String value = req.getParameter("BtnDisImp");
		value = (value == null) ? "Otro" : value.trim();
		
		
		if (req.getSession(false).getAttribute("beanDistriImp") != null) {
			bean = ((ImportTables) req.getSession(false).getAttribute("beanDistriImp"));
		}
		else {
			bean = new ImportTables();
			bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("builder"));
			req.getSession(false).setAttribute("beanDistriImp", bean);
		}
		//bean.getLogin().setCodact("210");
		bean.getLogin().setError("");
		bean.getLogin().setNamePantalla("IMPORTAR");
		System.out.println("Mensaje Opción:"+bean.getLogin().getNamePantalla());
		if (value.equalsIgnoreCase("Otro")) {

			bean.getLogin().setAccion("Consulta");
			bean.getLogin().setCodact("210");
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_BUILDER_JSP);
				return;
			}
			callPage(req, res, JspServlet.IMPORTACION_JSP+"?mensajeDistribucion= ");
			return;
			
		}else{//EXPORTA TABLAS
			boolean exito=false, exitoDesh=false, exitoHab=false;
			String[] listaAcciones = req.getParameterValues("rbAccion");
			String ruta= req.getParameter("txtRuta");
			bean.getLogin().setAccion("Importar");
			/**************************************BLOQUEO AGENCIA VIRTUAL**********************************************/
			EjecucionDeProcesoServlet.cambiarEstadoBloqueoAgencia(true);
			System.out.println("Bloqueo Agencia Virtual");
			for(int i=0;i<listaAcciones.length;i++){
				System.out.println("Elementos :" + listaAcciones[i]);
				if(listaAcciones[i].equals("01")){
					exitoDesh=bean.deshabilitaConstraints("TCOMMSG","pv_swb");
					exitoDesh=bean.deshabilitaConstraints("TDICDAT","pv_swb");
					exito=bean.importar("TCOMMSG","pv_swb","01",ruta);
					if(!exito || !exitoDesh){
						EjecucionDeProcesoServlet.cambiarEstadoBloqueoAgencia(false);
						req.setAttribute("mensajeDistribucion","Ocurrió un error al ejecutar el proceso de importación");
						req.getRequestDispatcher("/SARAWebBuilder/Distribucion/Importacion.jsp").forward(req,res);
					}
					exito=bean.importar("TDICDAT","pv_swb","01",ruta);
					if(!exito){
						EjecucionDeProcesoServlet.cambiarEstadoBloqueoAgencia(false);
						req.setAttribute("mensajeDistribucion","Ocurrió un error al ejecutar el proceso de importación");
						req.getRequestDispatcher("/SARAWebBuilder/Distribucion/Importacion.jsp").forward(req,res);
					}
					exitoHab=bean.habilitaConstraints("TCOMMSG","pv_swb");
					exitoHab=bean.habilitaConstraints("TDICDAT","pv_swb");
				}else if(listaAcciones[i].equals("02")){
					exitoDesh=bean.deshabilitaConstraints("THLPDET","pv_swb");
					exitoDesh=bean.deshabilitaConstraints("THLPDAT","pv_swb");
					exito=bean.importar("THLPDAT","pv_swb","02",ruta);
					if(!exito || !exitoDesh){
						EjecucionDeProcesoServlet.cambiarEstadoBloqueoAgencia(false);
						req.setAttribute("mensajeDistribucion","Ocurrió un error al ejecutar el proceso de importación");
						req.getRequestDispatcher("/SARAWebBuilder/Distribucion/Importacion.jsp").forward(req,res);
					}
					exito=bean.importar("THLPDET","pv_swb","02",ruta);
					if(!exito){
						EjecucionDeProcesoServlet.cambiarEstadoBloqueoAgencia(false);
						req.setAttribute("mensajeDistribucion","Ocurrió un error al ejecutar el proceso de importación");
						req.getRequestDispatcher("/SARAWebBuilder/Distribucion/Importacion.jsp").forward(req,res);
					}
					exitoHab=bean.habilitaConstraints("THLPDAT","pv_swb");
					exitoHab=bean.habilitaConstraints("THLPDET","pv_swb");
				}else if(listaAcciones[i].equals("03")){
					exitoDesh=bean.deshabilitaConstraints("TMSGDAT","pv_swb");
					exitoDesh=bean.deshabilitaConstraints("TGRPMSG","pv_swb");
					exito=bean.importar("TMSGDAT","pv_swb","03",ruta);
					if(!exito || !exitoDesh){
						EjecucionDeProcesoServlet.cambiarEstadoBloqueoAgencia(false);
						req.setAttribute("mensajeDistribucion","Ocurrió un error al ejecutar el proceso de importación");
						req.getRequestDispatcher("/SARAWebBuilder/Distribucion/Importacion.jsp").forward(req,res);
					}
					exito=bean.importar("TGRPMSG","pv_swb","03",ruta);
					if(!exito){
						EjecucionDeProcesoServlet.cambiarEstadoBloqueoAgencia(false);
						req.setAttribute("mensajeDistribucion","Ocurrió un error al ejecutar el proceso de importación");
						req.getRequestDispatcher("/SARAWebBuilder/Distribucion/Importacion.jsp").forward(req,res);
					}
					exitoHab=bean.habilitaConstraints("TMSGDAT","pv_swb");
					exitoHab=bean.habilitaConstraints("TGRPMSG","pv_swb");
				}else if(listaAcciones[i].equals("04")){
					exitoDesh=bean.deshabilitaConstraints("TMSGPAN","pv_swb");
					exitoDesh=bean.deshabilitaConstraints("TGRPPAN","pv_swb");
					exito=bean.importar("TMSGPAN","pv_swb","04",ruta);
					if(!exito || !exitoDesh){
						EjecucionDeProcesoServlet.cambiarEstadoBloqueoAgencia(false);
						req.setAttribute("mensajeDistribucion","Ocurrió un error al ejecutar el proceso de importación");
						req.getRequestDispatcher("/SARAWebBuilder/Distribucion/Importacion.jsp").forward(req,res);
					}
					exito=bean.importar("TGRPPAN","pv_swb","04",ruta);
					if(!exito){
						EjecucionDeProcesoServlet.cambiarEstadoBloqueoAgencia(false);
						req.setAttribute("mensajeDistribucion","Ocurrió un error al ejecutar el proceso de importación");
						req.getRequestDispatcher("/SARAWebBuilder/Distribucion/Importacion.jsp").forward(req,res);
					}
					exitoHab=bean.habilitaConstraints("TMSGPAN","pv_swb");
					exitoHab=bean.habilitaConstraints("TGRPPAN","pv_swb");
				}else if(listaAcciones[i].equals("05")){
					exitoDesh=bean.deshabilitaConstraints("TLIMTRA","pv_swb");
					exito=bean.importar("TLIMTRA","pv_swb","05",ruta);
					if(!exito || !exitoDesh){
						EjecucionDeProcesoServlet.cambiarEstadoBloqueoAgencia(false);
						req.setAttribute("mensajeDistribucion","Ocurrió un error al ejecutar el proceso de importación");
						req.getRequestDispatcher("/SARAWebBuilder/Distribucion/Importacion.jsp").forward(req,res);
					}
					exitoHab=bean.habilitaConstraints("TLIMTRA","pv_swb");
				}else if(listaAcciones[i].equals("06")){
					exitoDesh=bean.deshabilitaConstraints("TMENDAT","pv_swb");
					exito=bean.importar("TMENDAT","pv_swb","06",ruta);
					if(!exito || !exitoDesh){
						EjecucionDeProcesoServlet.cambiarEstadoBloqueoAgencia(false);
						req.setAttribute("mensajeDistribucion","Ocurrió un error al ejecutar el proceso de importación");
						req.getRequestDispatcher("/SARAWebBuilder/Distribucion/Importacion.jsp").forward(req,res);
					}
					exitoHab=bean.habilitaConstraints("TMENDAT","pv_swb");
				}else if(listaAcciones[i].equals("07")){
					exitoDesh=bean.deshabilitaConstraints("TTRAHOR","pv_swb");
					exito=bean.importar("TTRAHOR","pv_swb","07",ruta);
					if(!exito || !exitoDesh){
						EjecucionDeProcesoServlet.cambiarEstadoBloqueoAgencia(false);
						req.setAttribute("mensajeDistribucion","Ocurrió un error al ejecutar el proceso de importación");
						req.getRequestDispatcher("/SARAWebBuilder/Distribucion/Importacion.jsp").forward(req,res);
					}
					exitoHab=bean.habilitaConstraints("TTRAHOR","pv_swb");
				}else if(listaAcciones[i].equals("08")){
					exitoDesh=bean.deshabilitaConstraints("TMSGHST","pv_swb");
					exito=bean.importar("TMSGHST","pv_swb","08",ruta);
					if(!exito || !exitoDesh){
						EjecucionDeProcesoServlet.cambiarEstadoBloqueoAgencia(false);
						req.setAttribute("mensajeDistribucion","Ocurrió un error al ejecutar el proceso de importación");
						req.getRequestDispatcher("/SARAWebBuilder/Distribucion/Importacion.jsp").forward(req,res);
					}
					exitoHab=bean.habilitaConstraints("TMSGHST","pv_swb");
				}else if(listaAcciones[i].equals("09")){
					exitoDesh=bean.deshabilitaConstraints("TDEPDAT","pv_swb");
					exitoDesh=bean.deshabilitaConstraints("TAGEDAT","pv_swb");
					exito=bean.importar("TDEPDAT","pv_swb","09",ruta);
					if(!exito || !exitoDesh){
						EjecucionDeProcesoServlet.cambiarEstadoBloqueoAgencia(false);
						req.setAttribute("mensajeDistribucion","Ocurrió un error al ejecutar el proceso de importación");
						req.getRequestDispatcher("/SARAWebBuilder/Distribucion/Importacion.jsp").forward(req,res);
					}
					exito=bean.importar("TAGEDAT","pv_swb","09",ruta);
					if(!exito ){
						EjecucionDeProcesoServlet.cambiarEstadoBloqueoAgencia(false);
						req.setAttribute("mensajeDistribucion","Ocurrió un error al ejecutar el proceso de importación");
						req.getRequestDispatcher("/SARAWebBuilder/Distribucion/Importacion.jsp").forward(req,res);
					}
					exitoHab=bean.habilitaConstraints("TDEPDAT","pv_swb");
					exitoHab=bean.habilitaConstraints("TAGEDAT","pv_swb");
				}else if(listaAcciones[i].equals("10")){	
					exitoDesh=bean.deshabilitaConstraints("TMETARGALT","pv_swb");
					exitoDesh=bean.deshabilitaConstraints("TMETARG","pv_swb");
					exitoDesh=bean.deshabilitaConstraints("TSCHTRA","pv_swb");
					exitoDesh=bean.deshabilitaConstraints("TTRAFAS","pv_swb");
					exitoDesh=bean.deshabilitaConstraints("TGRPTRA","pv_swb");
					exitoDesh=bean.deshabilitaConstraints("TJOUDIC","pv_swb");
					exitoDesh=bean.deshabilitaConstraints("TCURDAT","pv_swb");
					exitoDesh=bean.deshabilitaConstraints("TTRADAT","pv_swb");
					
					exito=bean.importar("TMETARGALT","pv_swb","10",ruta);
					if(!exito || !exitoDesh){
						EjecucionDeProcesoServlet.cambiarEstadoBloqueoAgencia(false);
						req.setAttribute("mensajeDistribucion","Ocurrió un error al ejecutar el proceso de importación");
						req.getRequestDispatcher("/SARAWebBuilder/Distribucion/Importacion.jsp").forward(req,res);
					}
					exito=bean.importar("TMETARG","pv_swb","10",ruta);
					if(!exito ){
						EjecucionDeProcesoServlet.cambiarEstadoBloqueoAgencia(false);
						req.setAttribute("mensajeDistribucion","Ocurrió un error al ejecutar el proceso de importación");
						req.getRequestDispatcher("/SARAWebBuilder/Distribucion/Importacion.jsp").forward(req,res);
					}
					exito=bean.importar("TSCHTRA","pv_swb","10",ruta);
					if(!exito ){
						EjecucionDeProcesoServlet.cambiarEstadoBloqueoAgencia(false);
						req.setAttribute("mensajeDistribucion","Ocurrió un error al ejecutar el proceso de importación");
						req.getRequestDispatcher("/SARAWebBuilder/Distribucion/Importacion.jsp").forward(req,res);
					}
					exito=bean.importar("TTRAFAS","pv_swb","10",ruta);
					if(!exito ){
						EjecucionDeProcesoServlet.cambiarEstadoBloqueoAgencia(false);
						req.setAttribute("mensajeDistribucion","Ocurrió un error al ejecutar el proceso de importación");
						req.getRequestDispatcher("/SARAWebBuilder/Distribucion/Importacion.jsp").forward(req,res);
					}
					exito=bean.importar("TGRPTRA","pv_swb","10",ruta);
					if(!exito ){
						EjecucionDeProcesoServlet.cambiarEstadoBloqueoAgencia(false);
						req.setAttribute("mensajeDistribucion","Ocurrió un error al ejecutar el proceso de importación");
						req.getRequestDispatcher("/SARAWebBuilder/Distribucion/Importacion.jsp").forward(req,res);
					}
					exito=bean.importar("TJOUDIC","pv_swb","10",ruta);
					if(!exito ){
						EjecucionDeProcesoServlet.cambiarEstadoBloqueoAgencia(false);
						req.setAttribute("mensajeDistribucion","Ocurrió un error al ejecutar el proceso de importación");
						req.getRequestDispatcher("/SARAWebBuilder/Distribucion/Importacion.jsp").forward(req,res);
					}
					exito=bean.importar("TCURDAT","pv_swb","10",ruta);
					if(!exito ){
						EjecucionDeProcesoServlet.cambiarEstadoBloqueoAgencia(false);
						req.setAttribute("mensajeDistribucion","Ocurrió un error al ejecutar el proceso de importación");
						req.getRequestDispatcher("/SARAWebBuilder/Distribucion/Importacion.jsp").forward(req,res);
					}
					exito=bean.importar("TTRADAT","pv_swb","10",ruta);
					if(!exito ){
						EjecucionDeProcesoServlet.cambiarEstadoBloqueoAgencia(false);
						req.setAttribute("mensajeDistribucion","Ocurrió un error al ejecutar el proceso de importación");
						req.getRequestDispatcher("/SARAWebBuilder/Distribucion/Importacion.jsp").forward(req,res);
					}
					
					exitoHab=bean.habilitaConstraints("TMETARGALT","pv_swb");
					exitoHab=bean.habilitaConstraints("TMETARG","pv_swb");
					exitoHab=bean.habilitaConstraints("TSCHTRA","pv_swb");
					exitoHab=bean.habilitaConstraints("TTRAFAS","pv_swb");
					exitoHab=bean.habilitaConstraints("TGRPTRA","pv_swb");
					exitoHab=bean.habilitaConstraints("TJOUDIC","pv_swb");
					exitoHab=bean.habilitaConstraints("TCURDAT","pv_swb");
					exitoHab=bean.habilitaConstraints("TTRADAT","pv_swb");
					
				}else if(listaAcciones[i].equals("11")){
					exitoDesh=bean.deshabilitaConstraints("TMETDAT","pv_swb");
					exitoDesh=bean.deshabilitaConstraints("TCLADAT","pv_swb");
					exito=bean.importar("TMETDAT","pv_swb","11",ruta);
					if(!exito || !exitoDesh){
						System.out.println("REDIRECCIONÓ 1 ");
						EjecucionDeProcesoServlet.cambiarEstadoBloqueoAgencia(false);
						req.setAttribute("mensajeDistribucion","Ocurrió un error al ejecutar el proceso de importación");
						req.getRequestDispatcher("/SARAWebBuilder/Distribucion/Importacion.jsp").forward(req,res);
					}
					exito=bean.importar("TCLADAT","pv_swb","11",ruta);
					if(!exito){
						System.out.println("REDIRECCIONÓ 2");
						EjecucionDeProcesoServlet.cambiarEstadoBloqueoAgencia(false);
						req.setAttribute("mensajeDistribucion","Ocurrió un error al ejecutar el proceso de importación");
						req.getRequestDispatcher("/SARAWebBuilder/Distribucion/Importacion.jsp").forward(req,res);
					}
					exitoHab=bean.habilitaConstraints("TMETDAT","pv_swb");
					exitoHab=bean.habilitaConstraints("TCLADAT","pv_swb");
				}else if(listaAcciones[i].equals("12")){
					exitoDesh=bean.deshabilitaConstraints("TDICDAT","pv_swb");	
					exito=bean.importar("TDICDAT","pv_swb","12",ruta);
					if(!exito){
						System.out.println("EXITO!!! discdat");
						EjecucionDeProcesoServlet.cambiarEstadoBloqueoAgencia(false);
						req.setAttribute("mensajeDistribucion","Ocurrió un error al ejecutar el proceso de importación");
						req.getRequestDispatcher("/SARAWebBuilder/Distribucion/Importacion.jsp").forward(req,res);
					}
					exitoHab=bean.habilitaConstraints("TDICDAT","pv_swb");
				}
				
			}
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			/**************************************DESBLOQUEO AGENCIA VIRTUAL**********************************************/
			EjecucionDeProcesoServlet.cambiarEstadoBloqueoAgencia(false);
			System.out.println("Desbloqueo Agencia Virtual");
			/***************************************************************************************************************/
			req.setAttribute("mensajeDistribucion","La importación de datos ha sido realizada con éxito");
			
			req.getRequestDispatcher("/SARAWebBuilder/Distribucion/Importacion.jsp").forward(req,res);
			//callPage(req, res, JspServlet.DISTRIBUCION_JSP+"?mensajeDistribucion=La exportación de tablas ha sido ralizada con éxito");
		}
	}
	
	catch (Exception e) {
		System.out.println("Exception!!! 1" + e);
		e.printStackTrace();
		System.out.println("Exception!!! 2" + e.getMessage());
			
		//try {
			//setAttribute(req, "error", new Exception(Mensajes.ERROR_GENERAL));
			//setAttribute(req, "login", login);
			EjecucionDeProcesoServlet.cambiarEstadoBloqueoAgencia(false);
			req.setAttribute("mensajeDistribucion","Ocurrió un error al ejecutar el proceso de importación");
			req.getRequestDispatcher("/SARAWebBuilder/Distribucion/Importacion.jsp").forward(req,res);
		/*}
		catch (javax.servlet.ServletException se) {
			res.getWriter().println(se.getMessage());
		}*/

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

