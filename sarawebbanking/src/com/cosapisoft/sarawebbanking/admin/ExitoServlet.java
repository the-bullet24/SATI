package com.cosapisoft.sarawebbanking.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class for Servlet: ExitoServlet
 *
 */
 public class ExitoServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ExitoServlet() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		performTask(arg0, arg1);
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		performTask(arg0, arg1);
	}
	
	private void performTask(HttpServletRequest req, HttpServletResponse res){
		try {
			String paginaDestino = "";
			HttpSession session = req.getSession(false);
			if (session == null){
				session = req.getSession(true);
			}
			if (session.getAttribute("navigationBean") == null){
				((NavigationBean) session.getAttribute("navigationBean")).setMessageError("Ha ocurrido un error inesperado");
				paginaDestino = NavigationConstant.PAG_ERROR;
			}else{
				//paginaDestino = ((NavigationBean)req.getSession().getAttribute("navigationBean")).getTargetPage();
				// paginaDestino = NavigationConstant.PAG_INICIO_DIA;
				paginaDestino = "/sarawebbanking/SARAWebBanking/body.jsp";
			}
			callPage(req, res, paginaDestino);	
		}catch(IOException e){
			// System.out.println(e.getMessage());
		}catch (ServletException e){
			// System.out.println(e.getMessage());
		}
	}
	
	private void callPage(HttpServletRequest req, HttpServletResponse res, String destino) throws ServletException, IOException{
		//RequestDispatcher rd = this.getServletConfig().getServletContext().getRequestDispatcher(destino);
		//rd.forward(req, res);
		res.sendRedirect(destino);
	}
}