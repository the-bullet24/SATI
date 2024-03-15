package CosapiSoft.SARAWebBuilder;

/**
 * This type was created in VisualAge.
 */
import java.util.Vector;

import CosapiSoft.SARAWebBanking.*;
public class JournalTransaccionesServlet extends CosapiSoft.SARAWebBanking.JavaServlet {
/**
 * JournalTransaccionesServlet constructor comment.
 */
public JournalTransaccionesServlet() {
	super();
}
public void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws java.io.IOException, javax.servlet.ServletException {
	try {
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
		//
		JournalTransacciones bean;
		if (req.getSession(false).getAttribute("tjoudic") != null) {
			bean = ((JournalTransacciones) req.getSession(false).getAttribute("tjoudic"));
		}
		else {
			bean = new JournalTransacciones();
			bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("builder"));
			bean.consult();
			req.getSession(false).setAttribute("tjoudic", bean);
		}
		bean.setCodtra(trx.getCodtra());
		bean.setTxttra(trx.getTxttra());
		bean.setCodgrp(trx.getCodgrp());
		bean.setTxtgrp(trx.getGrp_nam());

		//
		bean.getLogin().setError("");
		bean.getLogin().setNamePantalla(Mensajes.getMessage(Mensajes.JOURNAL_DE_TRANSACCIONES));
		if (value.equalsIgnoreCase("Agregar")) {
			//System.out.println("aa1");
			bean.getLogin().setCodact("203");
			//System.out.println("aa2");
			bean.getLogin().setAccion("Inserción");
			//System.out.println("aa3");
			if (!bean.getLogin().hasAccesAccion()) {
				//System.out.println("aa4");
				callPage(req, res, JspServlet.ERROR_BUILDER_JSP);
				return;
			}
			//System.out.println("aa5");
			bean.setStatus("0");
			//System.out.println("aa6");
			bean.setCoddic(req.getParameter("TxtCoddic"));
			//System.out.println("aa7");
			bean.setIdefld(req.getParameter("TxtIdefld"));
			//System.out.println("aa8");
			if (!validate(bean)) {
				//System.out.println("aa9");
				bean.insert();
				//System.out.println("aa10");
				if (bean.getError().equals("")) {
					//System.out.println("aa11");
					bean.setIdefld("");
				}
			}
			//System.out.println("aa12");
			callPage(req, res, JspServlet.JOURNAL_TRANSACCIONES_JSP);
			//System.out.println("aa13");
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
			bean.setIdefld(req.getParameter("TxtIdefld"));
			if (!validate(bean)) {
				bean.update();
				if (bean.getError().equals("")) {
					bean.setIdefld("");
				}
			}
			callPage(req, res, JspServlet.JOURNAL_TRANSACCIONES_JSP);
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
			bean.setIdefld("");
			callPage(req, res, JspServlet.JOURNAL_TRANSACCIONES_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Buscar")) {
			bean.setCoddic(req.getParameter("TxtCoddic"));
			bean.search();
			callPage(req, res, JspServlet.JOURNAL_TRANSACCIONES_JSP);
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
		bean.setIdefld("");
		callPage(req, res, JspServlet.JOURNAL_TRANSACCIONES_JSP);
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
		JournalTransacciones bean = (JournalTransacciones) obj;
		if (bean.getIdefld().trim().equals("")) {
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