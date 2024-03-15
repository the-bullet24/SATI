package CosapiSoft.SARAWebManager;

/**
 * This type was created in VisualAge.
 */
import pe.cosapi.common.Constante;
import CosapiSoft.SARAWebBanking.*;
public class BannerPromocionalServlet extends JavaServlet {
/**
 * AyudaDeCampoServlet constructor comment.
 */
public BannerPromocionalServlet() {
	super();
}
public void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws java.io.IOException, javax.servlet.ServletException {
	InicioSesion login = new InicioSesion();
	login.setNameProducto("SARA Web Manager");
	try {
	    System.out.println("Sesion de CADUCIDAD"+Constante.SESSION_CADUCA);
	    if (Constante.SESSION_CADUCA.equals("2")) {
			try {
				req.getSession(false).setAttribute("error", new Exception(Mensajes.getMessage(Mensajes.SESION_TERMINADA) + " - " + this.getClass().toString()));;
				//req.getSession(false).setAttribute("error",new Exception(Mensajes.getMessage(Mensajes.SESION_TERMINADA) + " - " + this.getClass().toString()));;
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

		String value = req.getParameter("BtnBnn");
		value = (value == null) ? "Otro" : value.trim();
		BannerPromocional bean;
		if (req.getSession(false).getAttribute("tbandat") != null) {
			bean = ((BannerPromocional) req.getSession(false).getAttribute("tbandat"));
		}
		else {
			bean = new BannerPromocional();
			bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("manager"));
			req.getSession(false).setAttribute("tbandat", bean);
		}

		//
		bean.getLogin().setError("");
		bean.getLogin().setNamePantalla("BANNER PROMOCIONAL");
		System.out.println("Mensaje Opción:"+bean.getLogin().getNamePantalla());
		if (value.equalsIgnoreCase("Otro")) {
			/**
			bean.getLogin().setCodact("96");
			bean.getLogin().setAccion("Consulta");
			if (!bean.getLogin().hasAccesAccion()) {
			*/
				bean.getLogin().setCodact("308");
				bean.getLogin().setAccion("Consulta");
				if (!bean.getLogin().hasAccesAccion()) {
					//					res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilManagerError.jsp");
					callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
					return;
				}
			//}
			bean.consult();
			value="Buscar";
			
		}
		if (value.equalsIgnoreCase("Grabar")) {
			bean.getLogin().setCodact("308");
			bean.getLogin().setAccion("Modificar");
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilManagerError.jsp");
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			bean.setStatus("0");
			bean.setTypper("01");
			bean.setDatbeg(req.getParameter("txtDatbeg"));
			System.out.println("fecha de inicio:   "+req.getParameter("txtDatbeg"));
			// System.out.println("fecha de fin:  "+req.getParameter("txtDatend"));
			bean.setDatend(req.getParameter("txtDatend"));
			bean.setFilnam(req.getParameter("txtFilnam"));
			bean.setTypper1("02");
			bean.setDatbeg1(req.getParameter("txtDatbeg1"));
			bean.setDatend1(req.getParameter("txtDatend1"));
			bean.setFilnam1(req.getParameter("txtFilnam1"));
			//if(!validate(bean)){
			  if (!bean.search()) {
				System.out.println("entro hasta aki   ");
				bean.insert();
				if (bean.getError().equals("")) {
					bean.setTypper("");
					bean.setDatbeg("");
					bean.setDatend("");
					bean.setFilnam("");
				}
			  }
			  else{
				bean.setDatbeg(req.getParameter("txtDatbeg"));
				bean.setDatend(req.getParameter("txtDatend"));
				bean.setFilnam(req.getParameter("txtFilnam"));
				bean.setDatbeg1(req.getParameter("txtDatbeg1"));
				bean.setDatend1(req.getParameter("txtDatend1"));
				bean.setFilnam1(req.getParameter("txtFilnam1"));
			   bean.update();	
			  }
			//}
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebManager/MensajesAplicativo/GrupoMensajes.jsp");
			callPage(req, res, JspServlet.BANNER_PROMOCIONAL_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Modificar")) {
			bean.getLogin().setCodact("308");
			bean.getLogin().setAccion("Modificación");
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilManagerError.jsp");
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			bean.setStatus("0");
			bean.setTypper("01");
			bean.setDatbeg(req.getParameter("txtDatbeg"));
			bean.setDatend(req.getParameter("txtDatend"));
			bean.setFilnam(req.getParameter("txtFilnam"));
			bean.setTypper1("02");
			bean.setDatbeg1(req.getParameter("txtDatbeg1"));
			bean.setDatend1(req.getParameter("txtDatend1"));
			bean.setFilnam1(req.getParameter("txtFilnam1"));
			if (!validate(bean)) {
				bean.update();
				if (bean.getError().equals("")) {
					bean.setTypper("");
					bean.setDatbeg("");
					bean.setDatend("");
					bean.setFilnam("");
				}
			}
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebManager/MensajesAplicativo/GrupoMensajes.jsp");
			callPage(req, res, JspServlet.BANNER_PROMOCIONAL_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Eliminar")) {
			bean.getLogin().setCodact("308");
			bean.getLogin().setAccion("Eliminación");
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilManagerError.jsp");
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			bean.delete(req);
			bean.setTypper("");
			bean.setDatbeg("");
			bean.setDatend("");
			bean.setFilnam("");
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebManager/MensajesAplicativo/GrupoMensajes.jsp");
			callPage(req, res, JspServlet.BANNER_PROMOCIONAL_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Buscar")) {
			//bean.setTypper(req.getParameter("TxtTypper"));
			//bean.setTypper(req.getParameter("TxtTypper1"));
			bean.search();
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebManager/MensajesAplicativo/GrupoMensajes.jsp");
			callPage(req, res, JspServlet.BANNER_PROMOCIONAL_JSP);
			return;
		}
		/*if (value.equalsIgnoreCase("Mensajes")) {
			bean.setTypper(req.getParameter("TxtTypper"));
			if (bean.search())
				res.sendRedirect(JspServlet.MENSAJES_DEL_APLICATIVO_SERVLET + "?TxtCodgrp=" + bean.getCodgrp() + "&BtnDatos=Otro");
			else {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebManager/MensajesAplicativo/GrupoMensajes.jsp");
				callPage(req, res, JspServlet.GRUPO_DE_MENSAJES_JSP);
			}
			return;
		}*/
		bean.setError("");
		bean.loadGrid();
		bean.setTypper("");
		bean.setDatbeg("");
		bean.setDatend("");
		bean.setFilnam("");
		//		res.sendRedirect("../../../SARAWebBanking/SARAWebManager/MensajesAplicativo/GrupoMensajes.jsp");
		callPage(req, res, JspServlet.BANNER_PROMOCIONAL_JSP);
	}
	catch (Exception e) {
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
		
		BannerPromocional bean = (BannerPromocional) obj;
		bean.setError("");
		if(bean.getDatbeg().equals("") || bean.getDatbeg1().equals("") || bean.getDatend().equals("") || bean.getDatend1().equals("")){
			bean.setError(Mensajes.getMessage(Mensajes.BANNER_NO_NULO));
			return true;
		}
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
		java.util.Date fecha1 = sdf.parse(bean.getDatbeg());
		java.util.Date fecha2 = sdf.parse(bean.getDatbeg1());
		java.util.Date fecha3 = sdf.parse(bean.getDatend());
		java.util.Date fecha4 = sdf.parse(bean.getDatend1());
		if(bean.getFilnam1().equals("") || bean.getFilnam1().equals("")){
			bean.setError(Mensajes.getMessage(Mensajes.BANNER_NO_NULO));
			return true;
		}
		if(!CFormat.isImage(bean.getFilnam())){
			bean.setError(Mensajes.getMessage(Mensajes.BANNER_NO_VALIDO));
			return true;
			}
		if(!CFormat.isImage(bean.getFilnam1())){
			bean.setError(Mensajes.getMessage(Mensajes.BANNER_NO_VALIDO));
			return true;
			}
		if( fecha3.before(fecha1) || fecha4.before(fecha2)){
			bean.setError(Mensajes.getMessage(Mensajes.FECHAS_BANNER_NO_VALIDO));
			return true;
			}
	
		
		
		return false;
	}
	catch (Exception e) {
		throw new Exception("\n... validate - " + getClass().getName() + " -> " + e.getMessage());
	}
}
}
