package CosapiSoft.SARAWebBuilder;

/**
 * This type was created in VisualAge.
 */
import pe.cosapi.common.Constante;
import CosapiSoft.SARAWebBanking.*;
public class TransaccionesServlet extends JavaServlet {
/**
 * AyudaDeCampoServlet constructor comment.
 */
public TransaccionesServlet() {
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
		Fase1 ttrafas;
		if (req.getSession(false).getAttribute("Fase1") != null) {
			ttrafas = (Fase1) req.getSession(false).getAttribute("Fase1");
		}
		else {
			ttrafas = new Fase1();
			ttrafas.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("builder"));
			req.getSession(false).setAttribute("ttrafas", ttrafas);
		}
		String value = req.getParameter("BtnTrx");
		value = (value == null) ? "Otro" : value.trim();
		Transacciones bean;
		if (req.getSession(false).getAttribute("ttradat") != null) {
			bean = ((Transacciones) req.getSession(false).getAttribute("ttradat"));
		}
		else {
			bean = new Transacciones();
			bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("builder"));
			bean.setCodgrp(req.getParameter("TxtCodgrp"));
			req.getSession(false).setAttribute("ttradat", bean);
		}
		bean.getLogin().setError("");
		bean.getLogin().setNamePantalla(Mensajes.getMessage(Mensajes.TRANSACCIONES));
		if (value.equalsIgnoreCase("Otro")) {
			bean.loadGridGrupo();
			//bean.loadGridMoneda();
			//bean.loadGridCanal();
			/**
			bean.getLogin().setCodact("28");
			bean.getLogin().setAccion("Consulta");
			if (!bean.getLogin().hasAccesAccion()) {
			*/
				bean.getLogin().setNamePantalla(Mensajes.getMessage(Mensajes.TRANSACCIONES));
				bean.getLogin().setCodact("202");
				bean.getLogin().setAccion("Consulta");
				if (!bean.getLogin().hasAccesAccion()) {
					//					res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilBuilderError.jsp");
					callPage(req, res, JspServlet.ERROR_BUILDER_JSP);
					return;
				}
			//}
			bean.consult();
		}

		//
		if (value.equalsIgnoreCase("Agregar")) {
		    bean.getLogin().setNamePantalla(Mensajes.getMessage(Mensajes.TRANSACCIONES));
			bean.getLogin().setCodact("202");
			bean.getLogin().setAccion("Inserción");
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilBuilderError.jsp");
				callPage(req, res, JspServlet.ERROR_BUILDER_JSP);
				return;
			}
			bean.setStatus("0");
			bean.setCodgrp(req.getParameter("TxtCodgrp"));
			bean.setCodtra(req.getParameter("TxtCodtra"));
			bean.setTxttra(req.getParameter("TxtTxttra"));
			bean.setTxtprcgde(req.getParameter("TxtTxtprcgde"));
			bean.setFlgjou(req.getParameter("ChkFlgjou"));
			bean.setHouini(req.getParameter("TxtHouini"));
			bean.setHoufin(req.getParameter("TxtHoufin"));
			bean.setFlgenb(req.getParameter("ChkFlgenb"));
			bean.setTimout1(req.getParameter("TxtTimout1"));
			bean.setTimout2(req.getParameter("TxtTimout2"));
			
			
			if (!validate(bean)) {
				bean.insert();
				if (bean.getError().equals("")) {
					bean.setCodtra("");
					bean.setTxttra("");
					bean.setTxtprcgde("");
					bean.setFlgjou("");
					bean.setHouini("");
					bean.setHoufin("");
					bean.setFlgenb("");
					//bean.setFuncval("");
					//bean.setCodlim("");
				}
			}
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Transacciones/Transacciones.jsp");
			callPage(req, res, JspServlet.TRANSACCIONES_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Modificar")) {
		    bean.getLogin().setNamePantalla(Mensajes.getMessage(Mensajes.TRANSACCIONES));
			bean.getLogin().setCodact("202");
			bean.getLogin().setAccion("Modificación");
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilBuilderError.jsp");
				callPage(req, res, JspServlet.ERROR_BUILDER_JSP);
				return;
			}
			bean.setStatus("0");
			bean.setCodgrp(req.getParameter("TxtCodgrp"));
			bean.setCodtra(req.getParameter("TxtCodtra"));
			bean.setTxttra(req.getParameter("TxtTxttra"));
			bean.setTxtprcgde(req.getParameter("TxtTxtprcgde"));
			bean.setFlgjou(req.getParameter("ChkFlgjou"));
			bean.setHouini(req.getParameter("Txthouini"));
			bean.setHoufin(req.getParameter("Txthoufin"));
			bean.setFlgenb(req.getParameter("ChkFlgenb"));
			bean.setTimout1(req.getParameter("TxtTimout1"));
			bean.setTimout2(req.getParameter("TxtTimout2"));
			if (!validate(bean)) {
				bean.update();
				if (bean.getError().equals("")) {
					bean.setCodtra("");
					bean.setTxttra("");
					bean.setTxtprcgde("");
					bean.setFlgjou("");
					bean.setHouini("");
					bean.setHoufin("");
					bean.setFlgenb("");
				}
			}
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Transacciones/Transacciones.jsp");
			callPage(req, res, JspServlet.TRANSACCIONES_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Eliminar")) {
		    bean.getLogin().setNamePantalla(Mensajes.getMessage(Mensajes.TRANSACCIONES));
			bean.getLogin().setCodact("202");
			bean.getLogin().setAccion("Eliminación");
			if (!bean.getLogin().hasAccesAccion()) {
				//				res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilBuilderError.jsp");
				callPage(req, res, JspServlet.ERROR_BUILDER_JSP);
				return;
			}
			bean.setCodtra(req.getParameter("TxtCodtra"));
			//bean.setCodchn(req.getParameter("TxtCodchn"));
			bean.delete(req);
			bean.setCodtra("");
			bean.setCodchn("");
			bean.setTxttra("");
			bean.setTxtprcgde("");
			bean.setFlgofl("");
			bean.setFlgofd("");
			bean.setFlgjou("");
			bean.setFlgrec("");
			bean.setFlgrev("");
			bean.setCodrev("");
			bean.setCodcur("");
			bean.setLimsup("");
			bean.setLiminf("");
			bean.setCodnxttra("");
			bean.setCodlim("");
			bean.setTimout1("");
			bean.setTimout2("");
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Transacciones/Transacciones.jsp");
			callPage(req, res, JspServlet.TRANSACCIONES_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Buscar")) {
			bean.setCod_tra_chn(req.getParameter("TxtCod_tra_chn"));
			if (bean.getCodtra().equals(""))
				bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_CODIGO_DE_TRX));
			else {
				bean.search();
			}
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Transacciones/Transacciones.jsp");
			callPage(req, res, JspServlet.TRANSACCIONES_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Máscara")) {
			// System.out.println("va ha setear la mascara");
			bean.setCodgrp(req.getParameter("TxtCodgrp"));
			bean.setCodtra(req.getParameter("TxtCodtra"));
			//bean.setCodchn(req.getParameter("TxtCodchn"));
			if (bean.getCodtra().equals("")) {
				// System.out.println("no hay transaccion");
				bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_CODIGO_DE_TRX));
			}
			else {
				if (!bean.search()) {
					// System.out.println("entro al search");
					String str = Mensajes.getMessage(Mensajes.CODIGO_DE_TRX_NO_EXISTE);
					int pos = str.indexOf('&');
					bean.setError(str.substring(0, pos) + bean.getCodtra() + str.substring(pos + 1));
				}
				else {
					// System.out.println("va ha llamar al jsp");
					Transacciones.push(JspServlet.TRANSACCIONES_JSP);
					res.sendRedirect(JspServlet.MASK_TRANSACCIONES_SERVLET + "?BtnTrx=Otro");
					return;
				}
			}
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Transacciones/Transacciones.jsp");
			callPage(req, res, JspServlet.TRANSACCIONES_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Journal")) {
			bean.setCodgrp(req.getParameter("TxtCodgrp"));
			bean.setCodtra(req.getParameter("TxtCodtra"));
			//bean.setCodchn(req.getParameter("TxtCodchn"));
			if (bean.getCodtra().equals("")) {
				bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_CODIGO_DE_TRX));
			}
			else {
				if (!bean.search()) {
					String str = Mensajes.getMessage(Mensajes.CODIGO_DE_TRX_NO_EXISTE);
					int pos = str.indexOf('&');
					bean.setError(str.substring(0, pos) + bean.getCodtra() + str.substring(pos + 1));
				}
				else {
					Transacciones.push(JspServlet.TRANSACCIONES_JSP);
					res.sendRedirect(JspServlet.JOURNAL_TRANSACCIONES_SERVLET + "?BtnTrx=Otro");
					return;
				}
			}
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Transacciones/Transacciones.jsp");
			callPage(req, res, JspServlet.TRANSACCIONES_JSP);
			return;
		}
		if (value.equalsIgnoreCase("Copiar")) {
			String newtrx = req.getParameter("newtrx");
			String codgrp = req.getParameter("TxtCodgrp");
			String codtra = req.getParameter("TxtCodtra");
			//String codchn = req.getParameter("TxtCodchn");
			CosapiSoft.SARAWebBanking.InicioSesion login = (CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("builder");
			JDatabase jd = new JDatabase(login.driver, login.url, login.userid, login.password);
			JQuery db = new JQuery(jd.getConnection());
			String query1 = "select codtra, codgrp, txttra, txtprcgde,flgjou,houini,houfin,flgenb from "+Constante.ESQUEMA1+".ttradat where codtra='" + codtra + "'";
			db.setQuery(query1);
			db.executeQuery();
			db.getResultSet().next();
			query1 = "insert into "+Constante.ESQUEMA1+".ttradat values ('" + newtrx + "','" + db.getResultSet().getString(2) + "','" + db.getResultSet().getString(3) + "','" + db.getResultSet().getString(4) + "','" + db.getResultSet().getString(5) + "','" + db.getResultSet().getString(6) + "','" + db.getResultSet().getString(7) + "','" + db.getResultSet().getString(8) + "')";
			// System.out.println(query1);
			db.setQuery(query1);
			db.executeUpdate();

			//Para tctrtra
			query1 = "select * from "+Constante.ESQUEMA1+".tctrtra where codtra='" + codtra + "'";
			db.setQuery(query1);
			db.executeQuery();
			java.util.Vector tmp = new java.util.Vector();
			while (db.getResultSet().next()) {
				java.util.Vector tmp1 = new java.util.Vector();
				tmp1.addElement(db.getResultSet().getString(2));
				tmp1.addElement(db.getResultSet().getString(3));
				tmp1.addElement(db.getResultSet().getString(4));
				tmp1.addElement(db.getResultSet().getString(5));
				tmp1.addElement(db.getResultSet().getString(6));
				
				tmp.addElement(tmp1);
			}
			for (int i = 0; i < tmp.size(); i++) {
				query1 = "insert into "+Constante.ESQUEMA1+".tctrtra values ('" + newtrx + "','" + ((java.util.Vector) tmp.elementAt(i)).elementAt(0) + "','" + ((java.util.Vector) tmp.elementAt(i)).elementAt(1) + "','" + ((java.util.Vector) tmp.elementAt(i)).elementAt(2) + "','" + ((java.util.Vector) tmp.elementAt(i)).elementAt(3) + "','" + ((java.util.Vector) tmp.elementAt(i)).elementAt(4) + "')";
				// System.out.println(query1);
				db.setQuery(query1);
				db.executeUpdate();
			}
			//Para ttrafas
			query1 = "select * from "+Constante.ESQUEMA1+".ttrafas where codtra='" + codtra + "'";
			db.setQuery(query1);
			db.executeQuery();
			tmp = new java.util.Vector();
			while (db.getResultSet().next()) {
				java.util.Vector tmp1 = new java.util.Vector();
				tmp1.addElement(db.getResultSet().getString(2));
				tmp1.addElement(db.getResultSet().getString(3));
				tmp1.addElement(db.getResultSet().getString(4));
				tmp1.addElement(db.getResultSet().getString(5));
				tmp1.addElement(db.getResultSet().getString(6));
				tmp.addElement(tmp1);
			}
			for (int i = 0; i < tmp.size(); i++) {
				query1 = "insert into "+Constante.ESQUEMA1+".ttrafas values ('" + newtrx + "','" + ((java.util.Vector) tmp.elementAt(i)).elementAt(0) + "','" + ((java.util.Vector) tmp.elementAt(i)).elementAt(1) + "','" + ((java.util.Vector) tmp.elementAt(i)).elementAt(2) + "','" + ((java.util.Vector) tmp.elementAt(i)).elementAt(3) + "','" + ((java.util.Vector) tmp.elementAt(i)).elementAt(4) + "')";
				// System.out.println(query1);
				db.setQuery(query1);
				db.executeUpdate();
			}

			//Para tschtra
			query1 = "select * from "+Constante.ESQUEMA1+".tschtra where codtra='" + codtra + "'";
			db.setQuery(query1);
			db.executeQuery();
			tmp = new java.util.Vector();
			while (db.getResultSet().next()) {
				java.util.Vector tmp1 = new java.util.Vector();
				tmp1.addElement(db.getResultSet().getString(2));
				tmp1.addElement(db.getResultSet().getString(3));
				tmp1.addElement(db.getResultSet().getString(4));
				tmp1.addElement(db.getResultSet().getString(5));
				tmp1.addElement(db.getResultSet().getString(6));
				tmp1.addElement(db.getResultSet().getString(7));
				tmp1.addElement(db.getResultSet().getString(8));
				tmp1.addElement(db.getResultSet().getString(9));
				tmp1.addElement(db.getResultSet().getString(10));
				tmp1.addElement(db.getResultSet().getString(11));
				tmp.addElement(tmp1);
			}
			for (int i = 0; i < tmp.size(); i++) {
				query1 = "insert into "+Constante.ESQUEMA1+".tschtra values ('" + newtrx + "','" + ((java.util.Vector) tmp.elementAt(i)).elementAt(0) + "','" + ((java.util.Vector) tmp.elementAt(i)).elementAt(1) + "'," + ((java.util.Vector) tmp.elementAt(i)).elementAt(2) + ",'" + ((java.util.Vector) tmp.elementAt(i)).elementAt(3) + "','" + ((java.util.Vector) tmp.elementAt(i)).elementAt(4) + "','" + ((java.util.Vector) tmp.elementAt(i)).elementAt(5) + "','" + ((java.util.Vector) tmp.elementAt(i)).elementAt(6) + "','" + ((java.util.Vector) tmp.elementAt(i)).elementAt(7) + "','" + ((java.util.Vector) tmp.elementAt(i)).elementAt(8) + "','" + ((java.util.Vector) tmp.elementAt(i)).elementAt(9) + "')";
				// System.out.println(query1);
				db.setQuery(query1);
				db.executeUpdate();
			}
			
			//Para tmetarg
			query1 = "select * from "+Constante.ESQUEMA1+".tmetarg where codtra='" + codtra + "'";
			db.setQuery(query1);
			db.executeQuery();
			tmp = new java.util.Vector();
			while (db.getResultSet().next()) {
				java.util.Vector tmp1 = new java.util.Vector();
				tmp1.addElement(db.getResultSet().getString(2));
				tmp1.addElement(db.getResultSet().getString(3));
				tmp1.addElement(db.getResultSet().getString(4));
				tmp1.addElement(db.getResultSet().getString(5));
				tmp1.addElement(db.getResultSet().getString(6));
				tmp1.addElement(db.getResultSet().getString(7));
				tmp.addElement(tmp1);
			}
			for (int i = 0; i < tmp.size(); i++) {
				query1 = "insert into "+Constante.ESQUEMA1+".tmetarg values ('" + newtrx + "','" + ((java.util.Vector) tmp.elementAt(i)).elementAt(0) + "'," + ((java.util.Vector) tmp.elementAt(i)).elementAt(1) + ",'" + ((java.util.Vector) tmp.elementAt(i)).elementAt(2) + "'," + ((java.util.Vector) tmp.elementAt(i)).elementAt(3) + ",'" + ((java.util.Vector) tmp.elementAt(i)).elementAt(4) + "','" + ((java.util.Vector) tmp.elementAt(i)).elementAt(5) + "')";
				// System.out.println(query1);
				db.setQuery(query1);
				db.executeUpdate();
			}
			
			//Para tmetargalt
			query1 = "select * from "+Constante.ESQUEMA1+".tmetargalt where codtra='" + codtra + "'";
			db.setQuery(query1);
			db.executeQuery();
			tmp = new java.util.Vector();
			while (db.getResultSet().next()) {
				java.util.Vector tmp1 = new java.util.Vector();
				tmp1.addElement(db.getResultSet().getString(2));
				tmp1.addElement(db.getResultSet().getString(3));
				tmp1.addElement(db.getResultSet().getString(4));
				tmp1.addElement(db.getResultSet().getString(5));
				tmp1.addElement(db.getResultSet().getString(6));
				tmp.addElement(tmp1);
			}
			for (int i = 0; i < tmp.size(); i++) {
				query1 = "insert into "+Constante.ESQUEMA1+".tmetargalt values ('" + newtrx + "','" + ((java.util.Vector) tmp.elementAt(i)).elementAt(0) + "','" + ((java.util.Vector) tmp.elementAt(i)).elementAt(1) + "'," + ((java.util.Vector) tmp.elementAt(i)).elementAt(2) + ",'" + ((java.util.Vector) tmp.elementAt(i)).elementAt(3) + "','" + ((java.util.Vector) tmp.elementAt(i)).elementAt(4) + "')";
				// System.out.println(query1);
				db.setQuery(query1);
				db.executeUpdate();
			}
			
			//Para tctrfas
			query1 = "select * from "+Constante.ESQUEMA1+".tctrfas where codtra='" + codtra + "'";
			db.setQuery(query1);
			db.executeQuery();
			tmp = new java.util.Vector();
			while (db.getResultSet().next()) {
				java.util.Vector tmp1 = new java.util.Vector();
				tmp1.addElement(db.getResultSet().getString(1));
				tmp1.addElement(db.getResultSet().getString(2));
				tmp1.addElement(db.getResultSet().getString(4));
				tmp1.addElement(db.getResultSet().getString(5));
				tmp1.addElement(db.getResultSet().getString(6));
				tmp.addElement(tmp1);
			}
			for (int i = 0; i < tmp.size(); i++) {
				query1 = "insert into "+Constante.ESQUEMA1+".tctrfas values ('" + ((java.util.Vector) tmp.elementAt(i)).elementAt(0) + "','" + ((java.util.Vector) tmp.elementAt(i)).elementAt(1) + "','" + newtrx + "','" + ((java.util.Vector) tmp.elementAt(i)).elementAt(2) + "','" + ((java.util.Vector) tmp.elementAt(i)).elementAt(3) + "','" + ((java.util.Vector) tmp.elementAt(i)).elementAt(4) + "')";
				// System.out.println(query1);
				db.setQuery(query1);
				db.executeUpdate();
			}
			
			query1 = "select * from "+Constante.ESQUEMA1+".ttrafas where codtra='" + codtra + "'";
			db.setQuery(query1);
			db.executeQuery();
			tmp = new java.util.Vector();
			while (db.getResultSet().next()) {
				java.util.Vector tmp1 = new java.util.Vector();
				tmp1.addElement(db.getResultSet().getString(2));
				tmp1.addElement(db.getResultSet().getString(3));
				tmp1.addElement(db.getResultSet().getString(4));
				tmp1.addElement(db.getResultSet().getString(5));
				tmp.addElement(tmp1);
			}
			for (int i = 0; i < tmp.size(); i++) {
				query1 = "insert into ttrafas values ('" + newtrx + "','" + ((java.util.Vector) tmp.elementAt(i)).elementAt(0) + "','" + ((java.util.Vector) tmp.elementAt(i)).elementAt(1) + "','" + ((java.util.Vector) tmp.elementAt(i)).elementAt(2) + "','" + ((java.util.Vector) tmp.elementAt(i)).elementAt(3) + "')";
				// System.out.println(query1);
				db.setQuery(query1);
				db.executeUpdate();
			}

			//Para tschtra
			query1 = "select * from "+Constante.ESQUEMA1+".tschtra where codtra='" + codtra + "'";
			db.setQuery(query1);
			db.executeQuery();
			tmp = new java.util.Vector();
			while (db.getResultSet().next()) {
				java.util.Vector tmp1 = new java.util.Vector();
				tmp1.addElement(db.getResultSet().getString(2));
				tmp1.addElement(db.getResultSet().getString(3));
				tmp1.addElement(db.getResultSet().getString(4));
				tmp1.addElement(db.getResultSet().getString(5));
				tmp1.addElement(db.getResultSet().getString(6));
				tmp1.addElement(db.getResultSet().getString(7));
				tmp.addElement(tmp1);
			}
			for (int i = 0; i < tmp.size(); i++) {
				query1 = "insert into "+Constante.ESQUEMA1+".tschtra values ('" + newtrx + "','" + ((java.util.Vector) tmp.elementAt(i)).elementAt(0) + "','" + ((java.util.Vector) tmp.elementAt(i)).elementAt(1) + "'," + ((java.util.Vector) tmp.elementAt(i)).elementAt(2) + ",'" + ((java.util.Vector) tmp.elementAt(i)).elementAt(3) + "','" + ((java.util.Vector) tmp.elementAt(i)).elementAt(4) + "','" + ((java.util.Vector) tmp.elementAt(i)).elementAt(5) + "')";
				// System.out.println(query1);
				db.setQuery(query1);
				db.executeUpdate();
			}
			
			//Para tmetarg
			query1 = "select * from "+Constante.ESQUEMA1+".tmetarg where codtra='" + codtra + "'";
			db.setQuery(query1);
			db.executeQuery();
			tmp = new java.util.Vector();
			while (db.getResultSet().next()) {
				java.util.Vector tmp1 = new java.util.Vector();
				tmp1.addElement(db.getResultSet().getString(2));
				tmp1.addElement(db.getResultSet().getString(3));
				tmp1.addElement(db.getResultSet().getString(4));
				tmp1.addElement(db.getResultSet().getString(5));
				tmp1.addElement(db.getResultSet().getString(6));
				tmp.addElement(tmp1);
			}
			for (int i = 0; i < tmp.size(); i++) {
				query1 = "insert into "+Constante.ESQUEMA1+".tmetarg values ('" + newtrx + "','" + ((java.util.Vector) tmp.elementAt(i)).elementAt(0) + "'," + ((java.util.Vector) tmp.elementAt(i)).elementAt(1) + ",'" + ((java.util.Vector) tmp.elementAt(i)).elementAt(2) + "'," + ((java.util.Vector) tmp.elementAt(i)).elementAt(3) + ",'" + ((java.util.Vector) tmp.elementAt(i)).elementAt(4) + "')";
				// System.out.println(query1);
				db.setQuery(query1);
				db.executeUpdate();
			}
			
			//Para tmetargalt
			query1 = "select * from "+Constante.ESQUEMA1+".tmetargalt where codtra='" + codtra + "'";
			db.setQuery(query1);
			db.executeQuery();
			tmp = new java.util.Vector();
			while (db.getResultSet().next()) {
				java.util.Vector tmp1 = new java.util.Vector();
				tmp1.addElement(db.getResultSet().getString(2));
				tmp1.addElement(db.getResultSet().getString(3));
				tmp1.addElement(db.getResultSet().getString(4));
				tmp1.addElement(db.getResultSet().getString(5));
				tmp.addElement(tmp1);
			}
			for (int i = 0; i < tmp.size(); i++) {
				query1 = "insert into "+Constante.ESQUEMA1+".tmetargalt values ('" + newtrx + "','" + ((java.util.Vector) tmp.elementAt(i)).elementAt(0) + "','" + ((java.util.Vector) tmp.elementAt(i)).elementAt(1) + "'," + ((java.util.Vector) tmp.elementAt(i)).elementAt(2) + ",'" + ((java.util.Vector) tmp.elementAt(i)).elementAt(3) + "')";
				// System.out.println(query1);
				db.setQuery(query1);
				db.executeUpdate();
			}
			/*
			//Para tctrfas
			query1 = "select * from tctrfas where codtra='" + codtra + "'";
			db.setQuery(query1);
			db.executeQuery();
			tmp = new java.util.Vector();
			while (db.getResultSet().next()) {
				java.util.Vector tmp1 = new java.util.Vector();
				tmp1.addElement(db.getResultSet().getString(1));
				tmp1.addElement(db.getResultSet().getString(2));
				tmp1.addElement(db.getResultSet().getString(4));
				tmp1.addElement(db.getResultSet().getString(5));
				tmp1.addElement(db.getResultSet().getString(6));
				tmp.addElement(tmp1);
			}
			for (int i = 0; i < tmp.size(); i++) {
				query1 = "insert into tctrfas values ('" + ((java.util.Vector) tmp.elementAt(i)).elementAt(0) + "','" + ((java.util.Vector) tmp.elementAt(i)).elementAt(1) + "','" + newtrx + "','" + ((java.util.Vector) tmp.elementAt(i)).elementAt(2) + "','" + ((java.util.Vector) tmp.elementAt(i)).elementAt(3) + "','" + ((java.util.Vector) tmp.elementAt(i)).elementAt(4) + "')";
				System.out.println(query1);
				db.setQuery(query1);
				db.executeUpdate();
			}
			
			//Para tctrsch
			query1 = "select * from tctrsch where codtra='" + codtra + "' and codchn='" + codchn + "'";
			db.setQuery(query1);
			db.executeQuery();
			tmp = new java.util.Vector();
			while (db.getResultSet().next()) {
				java.util.Vector tmp1 = new java.util.Vector();
				tmp1.addElement(db.getResultSet().getString(1));
				tmp1.addElement(db.getResultSet().getString(3));
				tmp1.addElement(db.getResultSet().getString(4));
				tmp1.addElement(db.getResultSet().getString(5));
				tmp1.addElement(db.getResultSet().getString(6));
				tmp1.addElement(db.getResultSet().getString(7));
				tmp1.addElement(db.getResultSet().getString(8));
				tmp.addElement(tmp1);
			}
			for (int i = 0; i < tmp.size(); i++) {
				query1 = "insert into tctrsch values ('" + ((java.util.Vector) tmp.elementAt(i)).elementAt(0) + "','" + newtrx + "'," + ((java.util.Vector) tmp.elementAt(i)).elementAt(1) + ",'" + ((java.util.Vector) tmp.elementAt(i)).elementAt(2) + "','" + ((java.util.Vector) tmp.elementAt(i)).elementAt(3) + "','" + ((java.util.Vector) tmp.elementAt(i)).elementAt(4) + "','" + ((java.util.Vector) tmp.elementAt(i)).elementAt(5) + "','" + ((java.util.Vector) tmp.elementAt(i)).elementAt(6) + "')";
				System.out.println(query1);
				db.setQuery(query1);
				db.executeUpdate();
			}
			
			//Para tctrmetargalt
			query1 = "select * from tctrmetargalt where codtra='" + codtra + "' and codchn='" + codchn + "'";
			db.setQuery(query1);
			db.executeQuery();
			tmp = new java.util.Vector();
			while (db.getResultSet().next()) {
				java.util.Vector tmp1 = new java.util.Vector();
				tmp1.addElement(db.getResultSet().getString(1));
				tmp1.addElement(db.getResultSet().getString(3));
				tmp1.addElement(db.getResultSet().getString(4));
				tmp1.addElement(db.getResultSet().getString(5));
				tmp1.addElement(db.getResultSet().getString(6));
				tmp.addElement(tmp1);
			}
			for (int i = 0; i < tmp.size(); i++) {
				query1 = "insert into tctrmetargalt values ('" + ((java.util.Vector) tmp.elementAt(i)).elementAt(0) + "','" + newtrx + "'," + ((java.util.Vector) tmp.elementAt(i)).elementAt(1) + ",'" + ((java.util.Vector) tmp.elementAt(i)).elementAt(2) + "','" + ((java.util.Vector) tmp.elementAt(i)).elementAt(3) + "','" + ((java.util.Vector) tmp.elementAt(i)).elementAt(4) + "')";
				System.out.println(query1);
				db.setQuery(query1);
				db.executeUpdate();
			}
			
			//Para tctrmetarg
			query1 = "select * from tctrmetarg where codtra='" + codtra + "' and codchn='" + codchn + "'";
			db.setQuery(query1);
			db.executeQuery();
			tmp = new java.util.Vector();
			while (db.getResultSet().next()) {
				java.util.Vector tmp1 = new java.util.Vector();
				tmp1.addElement(db.getResultSet().getString(1));
				tmp1.addElement(db.getResultSet().getString(3));
				tmp1.addElement(db.getResultSet().getString(4));
				tmp1.addElement(db.getResultSet().getString(5));
				tmp1.addElement(db.getResultSet().getString(6));
				tmp1.addElement(db.getResultSet().getString(7));
				tmp.addElement(tmp1);
			}
			for (int i = 0; i < tmp.size(); i++) {
				query1 = "insert into tctrmetarg values ('" + ((java.util.Vector) tmp.elementAt(i)).elementAt(0) + "','" + newtrx + "'," + ((java.util.Vector) tmp.elementAt(i)).elementAt(1) + "," + ((java.util.Vector) tmp.elementAt(i)).elementAt(2) + ",'" + ((java.util.Vector) tmp.elementAt(i)).elementAt(3) + "','" + ((java.util.Vector) tmp.elementAt(i)).elementAt(4) + "','" + ((java.util.Vector) tmp.elementAt(i)).elementAt(5) + "')";
				System.out.println(query1);
				db.setQuery(query1);
				db.executeUpdate();
			}
			
			*/
			db.close();
			jd.close();

			bean.setGrid(null);
			bean.loadGrid();
			callPage(req, res, JspServlet.TRANSACCIONES_JSP);
			return;

		}
		if (value.equalsIgnoreCase("Esquema")) {
			bean.setCodgrp(req.getParameter("TxtCodgrp"));
			bean.setCodtra(req.getParameter("TxtCodtra"));
			if (bean.getCodtra().equals("")) {
				bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_CODIGO_DE_TRX));
			}
			else {
				if (!bean.search()) {
					String str = Mensajes.getMessage(Mensajes.CODIGO_DE_TRX_NO_EXISTE);
					int pos = str.indexOf('&');
					bean.setError(str.substring(0, pos) + bean.getCodtra() + str.substring(pos + 1));
				}
				else {
					Transacciones.push(JspServlet.TRANSACCIONES_JSP);
					ttrafas.setFunction("INSERT_FASE");
					ttrafas.setCodtra(bean.getCodtra());
					ttrafas.setTxttra(bean.getTxttra());
					if (ttrafas.hasTrxEsquema(bean.getCodtra())) {
						res.sendRedirect(JspServlet.FASE_SERVLET + "?BtnEsq=Otro");
					}
					else {
						res.sendRedirect(JspServlet.FASE_TRX1_1_SERVLET + "?BtnEsq=Otro");
					}
					return;
				}
			}
			//			res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Transacciones/Transacciones.jsp");
			callPage(req, res, JspServlet.TRANSACCIONES_JSP);
			return;
		}
		bean.setError("");
		bean.loadGrid();
		bean.setCodgrp("");
		bean.setCodtra("");
		bean.setTxttra("");
		bean.setTxtprcgde("");
		bean.setFlgjou("");
		bean.setHouini("");
		bean.setHoufin("");
		bean.setFlgenb("");
		bean.setTimout1("");
		bean.setTimout2("");
		//		res.sendRedirect("../../../SARAWebBanking/SARAWebBuilder/Transacciones/Transacciones.jsp");
		callPage(req, res, JspServlet.TRANSACCIONES_JSP);
	}
	catch (Exception e) {
		try {
			e.printStackTrace();
			req.setAttribute("error",new Exception(Mensajes.ERROR_GENERAL));
			callPage(req, res, JspServlet.ERROR_JSP);
		}
		catch (Exception se) {
			res.getWriter().println(se.getMessage());
		}
	}
}
public boolean validate(Object obj) throws Exception {
	Transacciones bean = (Transacciones) obj;
	try {
		bean.setError("");
		if (bean.getCodtra().trim().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_CODIGO_DE_TRX));
			return true;
		}
		if (bean.getTxttra().trim().equals("")) {
			bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_DESCRIPCION_TRX));
			return true;
		}
		
		/*if (bean.getCodnxttra().trim().equals("")) {
		bean.setError(Mensajes.getMessage(Mensajes.DEBE_INGRESAR_CODIGO_DE_TRX));
		return true;
		}*/
		// System.out.println("Codtra : " + bean.getCodtra());
		bean.setCodtra(bean.getCodtra().toUpperCase());
		return false;
	}
	catch (Exception e) {
		throw new Exception("\n... validate() - " + getClass().getName() + " -> " + e.getMessage());
	}
}
}