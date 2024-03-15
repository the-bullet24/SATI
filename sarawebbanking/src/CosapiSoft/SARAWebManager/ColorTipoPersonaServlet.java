package CosapiSoft.SARAWebManager;

/**
 * This type was created in VisualAge.
 */
import CosapiSoft.SARAWebBanking.*;

public class ColorTipoPersonaServlet extends JavaServlet {
/**
 * AyudaDeCampoServlet constructor comment.
 */
public ColorTipoPersonaServlet() {
	super();
}


public void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws java.io.IOException, javax.servlet.ServletException {

	InicioSesion login = new InicioSesion();
	login.setNameProducto("SARA Web Manager");
	try {
		if (req.getSession(false) == null) {
			try {
				req.getSession(false).setAttribute("error",new Exception(Mensajes.getMessage(Mensajes.SESION_TERMINADA) + " - " + this.getClass().toString()));;
				setAttribute(req, "login", login);
				callPage(req, res, JspServlet.ERROR_JSP);
			}
			catch (javax.servlet.ServletException se) {
				res.getWriter().println(se.getMessage());
			}
			return;
		}
		String value = req.getParameter("BtnCol");
		value = (value == null) ? "Otro" : value.trim();
		ColorTipoPersona bean;
		if (req.getSession(false).getAttribute("tcoldat") != null) {
			bean = ((ColorTipoPersona) req.getSession(false).getAttribute("tcoldat"));
		}
		else {
			bean = new ColorTipoPersona();
			bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("manager"));
			req.getSession(false).setAttribute("tcoldat", bean);
		}

		//
		bean.getLogin().setError("");
		bean.getLogin().setNamePantalla(Mensajes.getMessage(Mensajes.COLOR_TIPO_PERSONA));
		System.out.println("Mensaje Opción:"+bean.getLogin().getNamePantalla());
		if (value.equalsIgnoreCase("Otro")) {
			/**
			bean.getLogin().setCodact("98");
			bean.getLogin().setAccion("Consulta");
			if (!bean.getLogin().hasAccesAccion()) {
			*/
				bean.getLogin().setCodact("309");
				bean.getLogin().setAccion("Consulta");
				if (!bean.getLogin().hasAccesAccion()) {
					//					res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilManagerError.jsp");
					callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
					return;
				}
			//}
			// System.out.println("entro a otr---------------");
			bean.consult();
			value="Buscar";
			
		}
		if (value.equalsIgnoreCase("Grabar")) {
			bean.getLogin().setCodact("309");
			bean.getLogin().setAccion("Inserción");
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilManagerError.jsp");
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			
			ColorTipoPersona ctp = new ColorTipoPersona();
			ctp.setStatus("0");
			ctp.setTypper("01");
			ctp.setTypper1("02");			
			
			ctp.setColbck(req.getParameter("txtColbck"));
			ctp.setColtit(req.getParameter("txtColtit"));
			ctp.setColsubtit(req.getParameter("txtColsubtit"));
			ctp.setColrow1(req.getParameter("txtColrow1"));
			ctp.setColrow2(req.getParameter("txtColrow2"));
			ctp.setColbck1(req.getParameter("txtColbck1"));
			ctp.setColtit1(req.getParameter("txtColtit1"));
			ctp.setColsubtit1(req.getParameter("txtColsubtit1"));
			ctp.setColrow11(req.getParameter("txtColrow11"));
			ctp.setColrow21(req.getParameter("txtColrow21"));
			// System.out.println("color..."+req.getParameter("txtColrow21"));
			
			if(!validate(ctp)){
				if (!ctp.search()) {
					bean.setStatus("0");
					bean.setTypper("01");
					bean.setTypper1("02");			
					
					bean.setColbck(req.getParameter("txtColbck"));
					bean.setColtit(req.getParameter("txtColtit"));
					bean.setColsubtit(req.getParameter("txtColsubtit"));
					bean.setColrow1(req.getParameter("txtColrow1"));
					bean.setColrow2(req.getParameter("txtColrow2"));
					bean.setColbck1(req.getParameter("txtColbck1"));
					bean.setColtit1(req.getParameter("txtColtit1"));
					bean.setColsubtit1(req.getParameter("txtColsubtit1"));
					bean.setColrow11(req.getParameter("txtColrow11"));
					bean.setColrow21(req.getParameter("txtColrow21"));
					// System.out.println("color..."+req.getParameter("txtColrow21"));

					bean.insert();
				
				}
				else{
					bean.setColbck(req.getParameter("txtColbck"));
					bean.setColtit(req.getParameter("txtColtit"));
					bean.setColsubtit(req.getParameter("txtColsubtit"));
					bean.setColrow1(req.getParameter("txtColrow1"));
					bean.setColrow2(req.getParameter("txtColrow2"));
					bean.setColbck1(req.getParameter("txtColbck1"));
					bean.setColtit1(req.getParameter("txtColtit1"));
					bean.setColsubtit1(req.getParameter("txtColsubtit1"));
					bean.setColrow11(req.getParameter("txtColrow11"));
					bean.setColrow21(req.getParameter("txtColrow21"));
					bean.update();	
				}
				
			}
			
			//bean.consult();
			//value="Buscar";			
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebManager/MensajesAplicativo/GrupoMensajes.jsp");
			if(ctp.getError()!=null)
				bean.setError(ctp.getError());
			req.getSession(false).setAttribute("tcoldat", bean);
			callPage(req, res, JspServlet.COLOR_TIPO_PERSONA_JSP);
			return;
		}
		/*if (value.equalsIgnoreCase("Modificar")) {
			bean.getLogin().setCodact("14");
			bean.getLogin().setAccion("Modificación");
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilManagerError.jsp");
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			bean.setStatus("0");
			bean.setTypper("NAT");
			bean.setDatbeg(req.getParameter("txtDatbeg"));
			bean.setDatend(req.getParameter("txtDatend"));
			bean.setFilnam(req.getParameter("txtFilnam"));
			bean.setTypper1("JUR");
			bean.setDatbeg1(req.getParameter("txtDatbeg1"));
			bean.setDatend1(req.getParameter("txtDatend1"));
			bean.setFilnam1(req.getParameter("txtFilnam1"));
			//if (!validate(bean)) {
				bean.update();
				if (bean.getError().equals("")) {
					bean.setTypper("");
					bean.setDatbeg("");
					bean.setDatend("");
					bean.setFilnam("");
				}
			//}
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebManager/MensajesAplicativo/GrupoMensajes.jsp");
			callPage(req, res, JspServlet.BANNER_PROMOCIONAL_JSP);
			return;
		}*/
		if (value.equalsIgnoreCase("Eliminar")) {
			bean.getLogin().setCodact("309");
			bean.getLogin().setAccion("Eliminación");
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilManagerError.jsp");
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			bean.delete(req);
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebManager/MensajesAplicativo/GrupoMensajes.jsp");
			callPage(req, res, JspServlet.COLOR_TIPO_PERSONA_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Buscar")) {
			//bean.setTypper(req.getParameter("TxtTypper"));
			//bean.setTypper(req.getParameter("TxtTypper1"));
			bean.search();
			// System.out.println("Iniciando busqueda");
			//req.getSession(false).setAttribute("tcoldat", bean);
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebManager/MensajesAplicativo/GrupoMensajes.jsp");
			callPage(req, res, JspServlet.COLOR_TIPO_PERSONA_JSP);
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
		/*bean.setError("");
		bean.loadGrid();
		bean.setTypper("");
		bean.setDatbeg("");
		bean.setDatend("");
		bean.setFilnam("");*/
		//		res.sendRedirect("../../../SARAWebBanking/SARAWebManager/MensajesAplicativo/GrupoMensajes.jsp");
		callPage(req, res, JspServlet.COLOR_TIPO_PERSONA_JSP);
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
		ColorTipoPersona bean = (ColorTipoPersona) obj;
		bean.setError("");
		if (bean.getColbck().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.COLOR_NO_NULO));
			return true;
		}
		if (bean.getColbck1().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.COLOR_NO_NULO));
			return true;
		}
		if (bean.getColrow1().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.COLOR_NO_NULO));
			return true;
		}
		if (bean.getColrow11().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.COLOR_NO_NULO));
			return true;
		}
		if (bean.getColrow2().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.COLOR_NO_NULO));
			return true;
		}
		// System.out.println("llego aki");
		if (bean.getColrow21().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.COLOR_NO_NULO));
			return true;
		}
		if (bean.getColsubtit().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.COLOR_NO_NULO));
			return true;
		}
		if (bean.getColsubtit1().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.COLOR_NO_NULO));
			return true;
		}
		if (bean.getColtit().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.COLOR_NO_NULO));
			return true;
		}
		if (bean.getColtit1().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.COLOR_NO_NULO));
			return true;
		}
		System.out.println("color1.."+bean.getColbck().trim());
		if (!CFormat.isHexad(bean.getColbck().trim())) {
			bean.setError(Mensajes.getMessage(Mensajes.COLOR_NO_VALIDO));
			return true;
		}
		if (!CFormat.isHexad(bean.getColbck1().trim())) {
			bean.setError(Mensajes.getMessage(Mensajes.COLOR_NO_VALIDO));
			return true;
		}
		if (!CFormat.isHexad(bean.getColrow1().trim())) {
			bean.setError(Mensajes.getMessage(Mensajes.COLOR_NO_VALIDO));
			return true;
		}
		if (!CFormat.isHexad(bean.getColrow11().trim())) {
			bean.setError(Mensajes.getMessage(Mensajes.COLOR_NO_VALIDO));
			return true;
		}
		if (!CFormat.isHexad(bean.getColrow2().trim())) {
			bean.setError(Mensajes.getMessage(Mensajes.COLOR_NO_VALIDO));
			return true;
		}
		if (!CFormat.isHexad(bean.getColrow21().trim())) {
			bean.setError(Mensajes.getMessage(Mensajes.COLOR_NO_VALIDO));
			return true;
		}
		if (!CFormat.isHexad(bean.getColsubtit().trim())) {
			bean.setError(Mensajes.getMessage(Mensajes.COLOR_NO_VALIDO));
			return true;
		}
		if (!CFormat.isHexad(bean.getColsubtit1().trim())) {
			bean.setError(Mensajes.getMessage(Mensajes.COLOR_NO_VALIDO));
			return true;
		}
		if (!CFormat.isHexad(bean.getColtit().trim())) {
			bean.setError(Mensajes.getMessage(Mensajes.COLOR_NO_VALIDO));
			return true;
		}
		if (!CFormat.isHexad(bean.getColtit1().trim())) {
			bean.setError(Mensajes.getMessage(Mensajes.COLOR_NO_VALIDO));
			return true;
		}
		boolean y=!CFormat.isHexad(bean.getColbck().trim());
		return false;
	}
	catch (Exception e) {
		e.printStackTrace();
		throw new Exception("\n... validate - " + getClass().getName() + " -> " + e.getMessage());
	}
}
}

