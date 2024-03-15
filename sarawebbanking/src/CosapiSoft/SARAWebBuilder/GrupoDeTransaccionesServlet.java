 package CosapiSoft.SARAWebBuilder;
 import CosapiSoft.SARAWebBanking.*;
 public class GrupoDeTransaccionesServlet extends JavaServlet {
   public GrupoDeTransaccionesServlet() {
	super();
   }
   public void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws java.io.IOException, javax.servlet.ServletException{
	InicioSesion login = new InicioSesion();
	login.setNameProducto("SARA Web Builder");
	try {
		if (!isLoggedIn(req)) {
			try {
				req.getSession(false).setAttribute("error",new Exception(Mensajes.getMessage(Mensajes.SESION_TERMINADA) + " - " + this.getClass().toString()));         
				setAttribute(req, "login", login);
				callPage(req, res, JspServlet.ERROR_JSP);
			}
			catch (javax.servlet.ServletException se) {
				res.getWriter().println(se.getMessage());
			}
			return;
		}
		String value = req.getParameter("BtnGrp");
		// System.out.println("Valor del botón:   "+value);
		GrupoDeTransacciones bean;
		if (req.getSession(false).getAttribute("tgrptra") != null) {
			bean = ((GrupoDeTransacciones) req.getSession(false).getAttribute("tgrptra"));
		}
		else {
			bean = new GrupoDeTransacciones();
			bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("builder"));
			bean.consult();
			req.getSession(false).setAttribute("tgrptra", bean);
		}
		bean.getLogin().setError("");
		bean.getLogin().setNamePantalla("GRUPO DE TRANSACCIONES");
		if (value.equals("Otro")) {
			/**
			bean.getLogin().setCodact("26");
			bean.getLogin().setAccion("Consulta");
			if (!bean.getLogin().hasAccesAccion()) {
			*/
		    	bean.getLogin().setNamePantalla("GRUPO DE TRANSACCIONES");
		    	bean.getLogin().setCodact("201");
				bean.getLogin().setAccion("Consulta");
				if (!bean.getLogin().hasAccesAccion()) {
					callPage(req, res, JspServlet.ERROR_BUILDER_JSP);
					return;
				}
			//}
			bean.consult();
		}
		if (value.equals("Agregar")) {
		    bean.getLogin().setNamePantalla("GRUPO DE TRANSACCIONES");
			bean.getLogin().setCodact("201");
			bean.getLogin().setAccion("Inserción");
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_BUILDER_JSP);
				return;
			}
			bean.setStatus("0");
			bean.setCodgrp(req.getParameter("TxtCodgrp"));
			bean.setTxtdes(req.getParameter("TxtTxtdes"));
			if (!validate(bean)) {
				bean.insert();
				if (bean.getError().equals("")) {
					bean.setCodgrp("");
					bean.setTxtdes("");
				}
			}
			callPage(req, res, JspServlet.GRUPO_TRANSACCIONES_JSP);
			return;
		}
		if (value.equals("Modificar")) {
		    bean.getLogin().setNamePantalla("GRUPO DE TRANSACCIONES");
			bean.getLogin().setCodact("201");
			bean.getLogin().setAccion("Modificación");
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_BUILDER_JSP);
				return;
			}
			bean.setStatus("0");
			bean.setCodgrp(req.getParameter("TxtCodgrp"));
			bean.setTxtdes(req.getParameter("TxtTxtdes"));
			if (!validate(bean)) {
				bean.update();
				if (bean.getError().equals("")) {
					bean.setCodgrp("");
					bean.setTxtdes("");
				}
			}
			callPage(req, res, JspServlet.GRUPO_TRANSACCIONES_JSP);
			return;
		}
		if (value.equals("Eliminar")) {
		    bean.getLogin().setNamePantalla("GRUPO DE TRANSACCIONES");
			bean.getLogin().setCodact("201");
			bean.getLogin().setAccion("Eliminación");
			if (!bean.getLogin().hasAccesAccion()) {
				callPage(req, res, JspServlet.ERROR_BUILDER_JSP);
				return;
			}
			bean.delete(req);
			bean.setCodgrp("");
			bean.setTxtdes("");
			callPage(req, res, JspServlet.GRUPO_TRANSACCIONES_JSP);
			return;
		}
		if (value.equals("Buscar")) {
			bean.setCodgrp(req.getParameter("TxtCodgrp"));
			if (bean.getCodgrp().equals("")) {
				String str = Mensajes.getMessage(Mensajes.CODIGO_DE_GRUPO_TRX_NO_EXISTE);
				int pos = str.indexOf('&');
				bean.setError(str.substring(0, pos) + bean.getCodgrp() + str.substring(pos + 1));
			}
			else {
				bean.search();
			}
			callPage(req, res, JspServlet.GRUPO_TRANSACCIONES_JSP);
			return;
		}
		if (value.equals("Transacciones")) {
			bean.setCodgrp(req.getParameter("TxtCodgrp"));
			if (bean.getCodgrp().equals("")) {
				bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_CODIGO_DE_GRUPO_TRX));
			}
			else {
				if (!bean.search()) {
					String str = Mensajes.getMessage(Mensajes.CODIGO_DE_GRUPO_TRX_NO_EXISTE);
					int pos = str.indexOf('&');
					bean.setError(str.substring(0, pos) + bean.getCodgrp() + str.substring(pos + 1));
				}
				else {
					res.sendRedirect("/sarawebbanking/servlet/TransaccionesServlet?BtnTrx=Otro&TxtCodgrp=" + bean.getCodgrp());
					return;
				}
			}
			bean.setCodgrp("");
			bean.setTxtdes("");
			callPage(req, res, JspServlet.GRUPO_TRANSACCIONES_JSP);
			return;
		}
		bean.setError("");
		bean.loadGrid();
		bean.setCodgrp("");
		bean.setTxtdes("");
		callPage(req, res, JspServlet.GRUPO_TRANSACCIONES_JSP);
	}
	catch (Exception e) {
		try {
			e.printStackTrace();
			setAttribute(req, "error", new Exception(Mensajes.ERROR_GENERAL));
			setAttribute(req, "login", login);
			callPage(req, res, JspServlet.ERROR_JSP);
		}
		catch (Exception se) {
			res.getWriter().println(se.getMessage());
		}
	}
   }
   public boolean validate(Object obj) throws Exception {
	GrupoDeTransacciones bean = (GrupoDeTransacciones) obj;
	try {
		bean.setError("");
		if (bean.getCodgrp().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_CODIGO_DE_GRUPO_TRX));
			return true;
		}
		if (!CosapiSoft.SARAWebBanking.CString.isInteger(bean.getCodgrp())) {
			bean.setError(Mensajes.getMessage(Mensajes.CODIGO_DE_GRUPO_TRX_DEBE_CONTENER_NUMEROS));
			return true;
		}
		if (bean.getTxtdes().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_DESCRIPCION_GRUPO_TRX));
			return true;
		}
		return false;
	}
	catch (Exception e) {
		throw new Exception("validate() -> GrupoDeTransaccionesServlet " + e.getMessage());
	}
   }
 }