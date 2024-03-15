package CosapiSoft.SARAWebManager;

/**
 * This type was created in VisualAge.
 */
import CosapiSoft.SARAWebBanking.*;
public class MensajesDeComunicacionServlet extends javax.servlet.http.HttpServlet {
/**
 * AyudaDeCampoServlet constructor comment.
 */
public MensajesDeComunicacionServlet() {
	super();
}
private boolean containError(MensajesDeComunicacion bean) throws Exception {
	try {
		bean.setError("");
		if (bean.getCodmsgcom().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_CODIGO_DE_COMUNICACION));
			return true;
		}
		if (!CosapiSoft.SARAWebBanking.CString.isInteger(bean.getCodmsgcom())) {
			bean.setError(Mensajes.getMessage(Mensajes.CODIGO_DE_COMUNICACION_DEBE_CONTENER_NUMEROS));
			return true;
		}
		if (bean.getNumseq().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_NRO_SECUENCIA));
			return true;
		}
		if (!CosapiSoft.SARAWebBanking.CString.isInteger(bean.getNumseq())) {
			bean.setError(Mensajes.getMessage(Mensajes.NRO_SECUENCIA_DEBE_CONTENER_NUMEROS));
			return true;
		}
		int seq = new Integer(bean.getNumseq()).intValue();
		bean.setNumseq("" + seq);
		if (seq < 0) {
			bean.setError(Mensajes.getMessage(Mensajes.NRO_SECUENCIA_DEBE_MAYOR_A_CERO));
			return true;
		}
		if (bean.getCoddic().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_COD_DICCIONARIO));
			return true;
		}
		if (!CosapiSoft.SARAWebBanking.CString.isInteger(bean.getCoddic())) {
			bean.setError(Mensajes.getMessage(Mensajes.COD_DICCIONARIO_DEBE_CONTENER_NUMEROS));
			return true;
		}
		if (!CosapiSoft.SARAWebBuilder.Diccionario.search(bean.getCoddic())) {
			bean.setError(Mensajes.getMessage(Mensajes.COD_DICCIONARIO_NO_EXISTE));
			return true;
		}
		if (bean.getNumbegpos().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_POS_INICIO));
			return true;
		}
		if (!CosapiSoft.SARAWebBanking.CString.isInteger(bean.getNumbegpos())) {
			bean.setError(Mensajes.getMessage(Mensajes.POS_INICIO_DEBE_CONTENER_NUMEROS));
			return true;
		}
		if (bean.getNumlon().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_LONG_DATO));
			return true;
		}
		if (!CosapiSoft.SARAWebBanking.CString.isInteger(bean.getNumlon())) {
			bean.setError(Mensajes.getMessage(Mensajes.LONG_DATO_DEBE_CONTENER_NUMEROS));
			return true;
		}
		
		seq = new Integer(bean.getNumlon()).intValue();
		bean.setNumlon("" + seq);
		if (seq < 0) {
			bean.setError(Mensajes.getMessage(Mensajes.LONG_DATO_DEBE_MAYOR_A_CERO));
			return true;
		}
		return false;
	} catch (Exception e) {
		e.printStackTrace();
		throw new Exception("containError() -> MensajesDeComunicacionServlet " + e.getMessage());
	}
}
public void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws javax.servlet.ServletException, java.io.IOException {
	doPost(req,res);
}
public void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws java.io.IOException {
	try {
		if (req.getSession(false) == null) {
			//			res.sendRedirect(JspServlet.SARA_WEB_BANKING_HTML);
			try {
				req.setAttribute("error", new Exception(Mensajes.getMessage(Mensajes.SESION_TERMINADA) + " - " + this.getClass().toString()));;
				res.sendRedirect(JspServlet.ERROR_JSP);
				//((com.sun.server.http.HttpServiceResponse) res).callPage(JspServlet.ERROR_JSP, req);
			} catch (javax.servlet.ServletException se) {
				res.getWriter().println(se.getMessage());
			}
			return;
		}
		String value = req.getParameter("BtnCom");
		MensajesDeComunicacion bean;

		bean = new MensajesDeComunicacion();
		bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("manager"));
		bean.consultar();
	 	req.getSession(false).setAttribute("tcommsg", bean);

		bean.getLogin().setError("");
		bean.getLogin().setNamePantalla(Mensajes.getMessage(Mensajes.MENSAJISTICA_DE_COMUNICACIONES));
		System.out.println("Mensaje Opción:"+bean.getLogin().getNamePantalla());
		if (value.equals("Otro")) {

				bean.getLogin().setCodact("301");
				bean.getLogin().setAccion("Consulta");
				if (!bean.getLogin().hasAccesAccion()) {
				 	res.sendRedirect(JspServlet.ERROR_MANAGER_JSP);
					return;
				}


			bean.consultar();
		}
		if (value.equals("Agregar")) {
			bean.getLogin().setCodact("301");
			bean.getLogin().setAccion("Inserción");
			if (!bean.getLogin().hasAccesAccion()) {
			 	res.sendRedirect(JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			bean.loadGrid();
			bean.setStatus("0");
			bean.setCodmsgcom(req.getParameter("TxtCodmsgcom"));
			bean.setNumseq(req.getParameter("TxtNumseq"));
			bean.setCoddic(req.getParameter("TxtCoddic"));
			bean.setTxtmsgcom(req.getParameter("TxtTxtmsgcom"));
			bean.setNumbegpos(req.getParameter("TxtNumbegpos"));
			bean.setNumlon(req.getParameter("TxtNumlon"));
			if (!containError(bean)) {
				// System.out.println("antes de agregar");
				bean.agregar();
				if (bean.getError().equals("")) {
					bean.setCodmsgcom("");
					bean.setNumseq("");
					bean.setCoddic("");
					bean.setTxtmsgcom("");
					bean.setNumbegpos("");
					bean.setNumlon("");
				}
			}
		 	res.sendRedirect(JspServlet.MENSAJES_DE_COMUNICACION_JSP);
			return;
		}
		if (value.equals("Modificar")) {
			bean.getLogin().setCodact("301");
			bean.getLogin().setAccion("Inserción");
			if (!bean.getLogin().hasAccesAccion()) {
			 	res.sendRedirect(JspServlet.ERROR_MANAGER_JSP);
				return;
			}

			bean.loadGrid();
			bean.setCodmsgcom(req.getParameter("TxtCodmsgcom"));
			bean.setNumseq(req.getParameter("TxtNumseq"));
			bean.setCoddic(req.getParameter("TxtCoddic"));
			bean.setTxtmsgcom(req.getParameter("TxtTxtmsgcom"));
			bean.setNumbegpos(req.getParameter("TxtNumbegpos"));
			bean.setNumlon(req.getParameter("TxtNumlon"));
			if (!containError(bean)) {
				bean.modificar();
				if (bean.getError().equals("")) {
					bean.setCodmsgcom("");
					bean.setNumseq("");
					bean.setCoddic("");
					bean.setTxtmsgcom("");
					bean.setNumbegpos("");
					bean.setNumlon("");
				}
			}
		 	res.sendRedirect(JspServlet.MENSAJES_DE_COMUNICACION_JSP);
			return;
		}
		if (value.equals("Buscar")) {
			bean.loadGrid();
			bean.setMsg_seq(req.getParameter("TxtMsg_seq"));
			bean.setCodmsgcom(req.getParameter("TxtCodmsgcom"));
			bean.setNumseq(req.getParameter("TxtNumseq"));
			bean.buscar();
		 	res.sendRedirect(JspServlet.MENSAJES_DE_COMUNICACION_JSP);
			return;
		}
		if (value.equals("Eliminar")) {
			bean.getLogin().setCodact("301");
			bean.getLogin().setAccion("Inserción");
			if (!bean.getLogin().hasAccesAccion()) {
			 	res.sendRedirect(JspServlet.ERROR_MANAGER_JSP);
				return;
			}

			bean.eliminar(req);
			bean.setCodmsgcom("");
			bean.setNumseq("");
			bean.setCoddic("");
			bean.setTxtmsgcom("");
			bean.setNumbegpos("");
			bean.setNumlon("");
		 	res.sendRedirect(JspServlet.MENSAJES_DE_COMUNICACION_JSP);
			return;
		}
		bean.loadGrid();
		bean.setCodmsgcom("");
		bean.setNumseq("");
		bean.setCoddic("");
		bean.setTxtmsgcom("");
		bean.setNumbegpos("");
		bean.setNumlon("");
	 	res.sendRedirect(JspServlet.MENSAJES_DE_COMUNICACION_JSP);
	} catch (Exception e) {
			req.setAttribute("error", new Exception(Mensajes.ERROR_GENERAL));
			res.sendRedirect(JspServlet.ERROR_JSP);
	}
}
}