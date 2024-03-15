package CosapiSoft.SARAWebBuilder;

/**
 * This type was created in VisualAge.
 */
import java.util.Vector;

import CosapiSoft.SARAWebBanking.*;
public class MascaraServlet extends CosapiSoft.SARAWebBanking.JavaServlet {
/**
 * MascaraServlet constructor comment.
 */
public MascaraServlet() {
	super();
}
public void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws java.io.IOException, javax.servlet.ServletException {
	try {
		// System.out.println("entro al dopost");
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
		String value = req.getParameter("BtnTrx");
		value = (value == null) ? "Otro" : value.trim();
		Transacciones trx = (Transacciones) req.getSession(false).getAttribute("ttradat");
		Mascara bean;
		if (req.getSession(false).getAttribute("tctrtra") != null) {
			bean = ((Mascara) req.getSession(false).getAttribute("tctrtra"));
		}
		else {
			bean = new Mascara();
			bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("builder"));
			bean.consult();
			req.getSession(false).setAttribute("tctrtra", bean);
		}
		bean.setCodtra(trx.getCodtra());
		bean.setTxttra(trx.getTxttra());
		bean.setCodgrp(trx.getCodgrp());
		bean.setTxtgrp(trx.getGrp_nam());

		//
		bean.getLogin().setError("");
		//bean.getLogin().setNamePantalla(Mensajes.getMessage(Mensajes.MASK_DE_TRANSACCIONES));
		if (value.equalsIgnoreCase("Agregar")) {
			bean.getLogin().setCodact("203");
			bean.getLogin().setAccion("Inserción");
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_BUILDER_JSP);
				return;
			}
			bean.setStatus("0");
			bean.setCoddic(req.getParameter("TxtCoddic"));
			bean.setFlgnul(req.getParameter("ChkFlgnul"));
			bean.setTxtdes(req.getParameter("TxtTxtdes"));
			bean.setTxtctr(req.getParameter("TxtTxtctr"));
			if(req.getParameter("TxtCodhlp").equals(""))
				bean.setCodhlp(req.getParameter(""));
			else
				bean.setCodhlp(req.getParameter("TxtCodhlp"));
			if (!validate(bean)) {
				bean.insert();
				if (bean.getError().equals("")) {
					bean.setFlgnul("");
				}
			}
			bean.setFlgnul("");
			bean.setTxtdes("");
			bean.setTxtctr("");
			bean.setCodhlp("");
			bean.setCoddic("");
			callPage(req, res, JspServlet.MASK_TRANSACCIONES_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Modificar")) {
			bean.getLogin().setCodact("203");
			bean.getLogin().setAccion("Modificación");
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_BUILDER_JSP);
				return;
			}
			bean.setStatus("0");
			bean.setCoddic(req.getParameter("TxtCoddic"));
			bean.setFlgnul(req.getParameter("ChkFlgnul"));
			bean.setTxtdes(req.getParameter("TxtTxtdes"));
			bean.setTxtctr(req.getParameter("TxtTxtctr"));
			if(req.getParameter("TxtCodhlp").equals(""))
				bean.setCodhlp(req.getParameter(""));
			else
				bean.setCodhlp(req.getParameter("TxtCodhlp"));
				bean.update();
				if (bean.getError().equals("")) {
					bean.setFlgnul("");
				}
				bean.setFlgnul("");
				bean.setTxtdes("");
				bean.setTxtctr("");
				bean.setCodhlp("");
				bean.setCoddic("");
			callPage(req, res, JspServlet.MASK_TRANSACCIONES_JSP);
			return;
		}
		
		if (value.equalsIgnoreCase("Eliminar")) {
			bean.getLogin().setCodact("203");
			bean.getLogin().setAccion("Eliminación");
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_BUILDER_JSP);
				return;
			}
			bean.delete(req);
			bean.setFlgnul("");
			bean.setTxtdes("");
			bean.setTxtctr("");
			bean.setCodhlp("");
			bean.setCoddic("");
			callPage(req, res, JspServlet.MASK_TRANSACCIONES_JSP);
			return;
		}
		
		if (value.equalsIgnoreCase("Buscar")) {
			bean.setCoddic(req.getParameter("TxtCoddic"));
			bean.search();
			callPage(req, res, JspServlet.MASK_TRANSACCIONES_JSP);
			return;
		}
		
		if (value.equalsIgnoreCase("Regresar")) {
			callPage(req, res, JspServlet.TRANSACCIONES_JSP);
			return;
		}
		bean.setError("");
		bean.setGrid(new Vector());
		bean.loadGrid();
		bean.loadDiccionarios();
		bean.setFlgnul("");
		// System.out.println("antes de llamar al jsp .------");
		callPage(req, res, JspServlet.MASK_TRANSACCIONES_JSP);
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
/**
 * validate method comment.
 */
public boolean validate(Object obj) throws Exception {
	try {
		Mascara bean = (Mascara) obj;
		if (bean.getFlgnul().trim().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_IDENTIFICADOR_DE_CAMPO));
			return true;
		}
		return false;
	}
	catch (Exception e) {
		throw new Exception("\n... validate() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
}