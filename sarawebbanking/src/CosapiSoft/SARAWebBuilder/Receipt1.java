package CosapiSoft.SARAWebBuilder;

/**
 * This type was created in VisualAge.
 */
import CosapiSoft.SARAWebBanking.*;
public class Receipt1 extends CosapiSoft.SARAWebBanking.JavaServlet {
/**
 * Transfer constructor comment.
 */
public Receipt1() {
	super();
}
public void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) {
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
		CosapiSoft.SARAWebBanking.InicioSesion login = (CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("builder");
		if (req.getParameter("cmd01").equals("Eliminar")) {
		}
		
/*Codigo para modificar un bean*/		
		
		
		if (req.getParameter("cmd01").equals("Modificar")) {
		}


/***************************************************************/		
/*Codigo para agregar un bean*/		
		
		if (req.getParameter("cmd01").equals("Grabar")) {
		}


/*********************************************************************************/		
		
		if (req.getParameter("cmd01").equals("Agregar")) {
			((ReceiptBean)req.getSession(false).getAttribute("rp")).setCode("");
			((ReceiptBean)req.getSession(false).getAttribute("rp")).setDescription("");
			((ReceiptBean)req.getSession(false).getAttribute("rp")).setData("");
			
			
		}

	res.sendRedirect("/sarawebbankung/SARAWebBuilder/receipt/receipt2.jsp");		
	}
	catch (Exception e) {
		e.printStackTrace();
		// System.out.println("ERROR: " + e.getMessage());
	}
}

public boolean validate(Object obj) throws Exception {
	return false;
}
}