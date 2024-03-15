package CosapiSoft.SARAWebManager;

/**
 * This type was created in VisualAge.
 */
import pe.cosapi.common.Constante;
import CosapiSoft.SARAWebBanking.*;
public class InformacionDeAgenciasServlet extends JavaServlet {
/**
 * AyudaDeCampoServlet constructor comment.
 */
public InformacionDeAgenciasServlet() {
	super();
}
public void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws java.io.IOException, javax.servlet.ServletException {
	InicioSesion login = new InicioSesion();
	login.setNameProducto("SARA Web Manager");
	try {
	    if (Constante.SESSION_CADUCA.equals("2")) {
			//			res.sendRedirect(JspServlet.SARA_WEB_BANKING_HTML);
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
		String value = req.getParameter("BtnAge");
		value = (value == null) ? "Otro" : value.trim();
		InformacionDeAgencias bean;
		//		if (req.getSession(false).getAttribute("tbrainf") != null) {
		//			bean = (InformacionDeAgencias) req.getSession(false).getAttribute("tbrainf");
		//		} else {
		bean = new InformacionDeAgencias();
		bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("manager"));
		//			req.getSession(false).putValue("tbrainf", bean);
		req.getSession(false).setAttribute("tbrainf", bean);
		//setAttribute(req, );
		//		}
		//
		bean.getLogin().setError("");
		bean.getLogin().setNamePantalla(Mensajes.getMessage(Mensajes.INFORMACION_SUCURSAL_VIRTUAL));
		System.out.println("Mensaje Opción:"+bean.getLogin().getNamePantalla());
		if (value.equals("Otro")) {
			/**
			**bean.getLogin().setCodact("01");
			**bean.getLogin().setAccion("Consulta");
			**if (!bean.getLogin().hasAccesAccion()) {
			*/
				bean.getLogin().setCodact("307");
				bean.getLogin().setAccion("Consulta");
				if (!bean.getLogin().hasAccesAccion()) {
					//					res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilManagerError.jsp");
					callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
					return;
				}
			//}
			bean.consultar();
		}
		if (value.equals("Modificar")) {
			bean.getLogin().setCodact("307");
			bean.getLogin().setAccion("Modificación");
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilManagerError.jsp");
				callPage(req, res, JspServlet.ERROR_MANAGER_JSP);
				return;
			}
			bean.setCodbra(req.getParameter("TxtCodbra"));
			bean.setNumsum(req.getParameter("TxtNumsum"));
			bean.setDayprgtra(req.getParameter("TxtDayprgtra"));
			bean.setBandef(req.getParameter("TxtBandef"));
			bean.setHouini(req.getParameter("TxtHouini"));
			bean.setHoufin(req.getParameter("TxtHoufin"));
			bean.setBanpna(req.getParameter("TxtBanpna"));
			bean.setBanpju(req.getParameter("TxtBanpju"));
			bean.setFlgblkbra(req.getParameter("TxtFlgblkbra"));
			bean.setTipoEncripcion(req.getParameter("CboTipoEncripcion"));
					
			if (!validate(bean)) {
				bean.modificar();
				//bean.loadGrid();
			}
			bean.loadGrid();
			
			callPage(req, res, JspServlet.INFORMACION_DE_AGENCIAS_JSP);
			return;
		}
		bean.loadGrid();
		callPage(req, res, JspServlet.INFORMACION_DE_AGENCIAS_JSP);
		return;
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
	InformacionDeAgencias bean = (InformacionDeAgencias) obj;
	/*try {
		bean.setError("");
		if (bean.getCodbra().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_EL_CODIGO_DE_SUCURSAL_VIRTUAL));
			return true;
		}
		if (!CosapiSoft.SARAWebBanking.CString.isInteger(bean.getCodbra())) {
			bean.setError(Mensajes.getMessage(Mensajes.CODIGO_DE_SUCURSAL_VIRTUAL_DEBE_CONTENER_NUMEROS));
			return true;
		}
		if (!AdministracionDeAgencias.search(bean.getCodbra())) {
			bean.setError(Mensajes.getMessage(Mensajes.CODIGO_DE_OFICINA_NO_EXISTE));
			return true;
		}
		*/
	try {
		if (bean.getNumsum().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_EL_NRO_TOTAL_DE_SUCURSAL_VIRTUAL));
			return true;
		}
		if (!CosapiSoft.SARAWebBanking.CString.isInteger(bean.getNumsum())) {
			bean.setError(Mensajes.getMessage(Mensajes.NRO_TOTAL_DE_SUCURSAL_VIRTUAL_DEBE_CONTENER_NUMEROS));
			return true;
		}/*
		if (bean.getFecpro().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_LA_FECHA_PROCESO));
			return true;
		}
		if (!bean.getFecpro().equals("")) {
			if (bean.getFecpro().length() < 8) {
				bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_OCHO_CARACTERES_FECHA));
				return true;
			}
			CFormat f = new CFormat("0000/00/00", bean.getFecpro());
			if (f.isValidoText()) {
				bean.setFecpro(f.changeFormat());
				if (!f.isValidoDate()) {
					CString str = new CString(bean.getFecpro());
					str.deleteAll('/');
					bean.setFecpro(str.toString() + " " + Mensajes.getMessage(Mensajes.ERROR_EN_FECHA));
					bean.setError(f.getError());
					return true;
				}
				CString str = new CString(bean.getFecpro());
				str.deleteAll('/');
				bean.setFecpro(str.toString());
			}
			else {
				bean.setError(f.getError() + " " + Mensajes.getMessage(Mensajes.ERROR_EN_FECHA));
				return true;
			}
		}*/
		/*
		if (bean.getFecnxtday().equals("")) {
		bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_LA_FECHA_SIG_DIA_PROCESO));
		return true;
		}
		if (!bean.getFecnxtday().equals("")) {
		if (bean.getFecnxtday().length() < 8) {
		bean.setError(Mensajes.getMessage(Mensajes.FECHA_SIG_DIA_PROCESO_DEBE_CONTENER_NUMEROS));
		return true;
		}
		CFormat f = new CFormat("0000/00/00", bean.getFecnxtday());
		if (f.isValidoText()) {
		bean.setFecnxtday(f.changeFormat());
		if (!f.isValidoDate()) {
		CString str = new CString(bean.getFecnxtday());
		str.deleteAll('/');
		bean.setFecnxtday(str.toString());
		bean.setError(f.getError() + " "+Mensajes.getMessage(Mensajes.FECHA_SIG_DIA_PROCESO));
		return true;
		}
		CString str = new CString(bean.getFecnxtday());
		str.deleteAll('/');
		bean.setFecnxtday(str.toString());
		} else {
		bean.setError(f.getError() + " "+Mensajes.getMessage(Mensajes.FECHA_SIG_DIA_PROCESO));
		return true;
		}
		}
		if (bean.getFecpro().compareTo(bean.getFecnxtday()) > 0) {
		bean.setError(Mensajes.getMessage(Mensajes.FECHA_SIG_DIA_PROCESO_MAYOR_FECHA_PROCESO));
		return true;
		}
		*/
		/*if (bean.getDayprgope().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_NRO_DIAS_ALMAC_LOG_OPE));
			return true;
		}
		
		int ope = new Integer(bean.getDayprgope()).intValue();
		bean.setDayprgope("" + ope);
		if (ope <= 0) {
			bean.setError(Mensajes.getMessage(Mensajes.NRO_DIAS_ALMAC_LOG_OPE_DEBE_MAYOR_A_CERO));
			return true;
		}
		*/
		
		if (bean.getDayprgtra().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_NRO_DIAS_ALMAC_LOG_TRX));
			return true;
		}
		if (!CosapiSoft.SARAWebBanking.CString.isInteger(bean.getDayprgtra())) {
			bean.setError(Mensajes.getMessage(Mensajes.NRO_DIAS_ALMAC_LOG_TRX_DEBE_CONTENER_NUMEROS));
			return true;
		}
		if (!CosapiSoft.SARAWebBanking.CFormat.isTime(bean.getHouini())) {
			bean.setError(Mensajes.getMessage(Mensajes.HORAINI_INGRESADA_INCORRECTA));
			return true;
		}
		if (!CosapiSoft.SARAWebBanking.CFormat.isTime(bean.getHoufin())) {
			bean.setError(Mensajes.getMessage(Mensajes.HORAFIN_INGRESADA_INCORRECTA));
			return true;
		}
		if (!CosapiSoft.SARAWebBanking.CFormat.isImage(bean.getBandef())) {
			bean.setError(Mensajes.getMessage(Mensajes.BANDEF_INGRESO_INCORRECTA));
			return true;
		}
		if (!CosapiSoft.SARAWebBanking.CFormat.isImage(bean.getBanpna())) {
			bean.setError(Mensajes.getMessage(Mensajes.BANPNA_INGRESO_INCORRECTA));
			return true;
		}
		if (!CosapiSoft.SARAWebBanking.CFormat.isImage(bean.getBanpju())) {
			bean.setError(Mensajes.getMessage(Mensajes.BANPJU_INGRESO_INCORRECTA));
			return true;
		}
		if (bean.getBandef().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_BANDEF));
			return true;
		}
		if (bean.getBanpna().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_BANPNA));
			return true;
		}
		if (bean.getBanpju().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_BANPJU));
			return true;
		}
		if (!CosapiSoft.SARAWebBanking.CFormat.isTimemayor(bean.getHouini(),bean.getHoufin())) {
			bean.setError(Mensajes.getMessage(Mensajes.HORA_DESDE_MENOR_A_HORA_HASTA));
			return true;
		}
		/*
		int tra = new Integer(bean.getDayprgtra()).intValue();
		bean.setDayprgtra("" + tra);
		if (tra <= 0) {
			bean.setError(Mensajes.getMessage(Mensajes.NRO_DIAS_ALMAC_LOG_TRX_DEBE_MAYOR_A_CERO));
			return true;
		}
		if (bean.getDayprgali().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_NRO_DIAS_ALMAC_LOG_ALI));
			return true;
		}
		if (!CosapiSoft.SARAWebBanking.CString.isInteger(bean.getDayprgali())) {
			bean.setError(Mensajes.getMessage(Mensajes.NRO_DIAS_ALMAC_LOG_ALI_DEBE_CONTENER_NUMEROS));
			return true;
		}
		int ali = new Integer(bean.getDayprgali()).intValue();
		bean.setDayprgali("" + ali);
		if (ali <= 0) {
			bean.setError(Mensajes.getMessage(Mensajes.NRO_DIAS_ALMAC_LOG_ALI_DEBE_MAYOR_A_CERO));
			return true;
		}
		if (bean.getDayprgmsg().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_NRO_DIAS_ALMAC_LOG_MSG));
			return true;
		}
		if (!CosapiSoft.SARAWebBanking.CString.isInteger(bean.getDayprgmsg())) {
			bean.setError(Mensajes.getMessage(Mensajes.NRO_DIAS_ALMAC_LOG_MSG_DEBE_CONTENER_NUMEROS));
			return true;
		}
		int msg = new Integer(bean.getDayprgmsg()).intValue();
		bean.setDayprgmsg("" + msg);
		if (msg <= 0) {
			bean.setError(Mensajes.getMessage(Mensajes.NRO_DIAS_ALMAC_LOG_MSG_DEBE_MAYOR_A_CERO));
			return true;
		}*/
		return false;
	}
	catch (Exception e) {
		e.printStackTrace();
		throw new Exception("\n... validate() - " + getClass().getName() + " -> " + e.getMessage());
	}
	
}
}