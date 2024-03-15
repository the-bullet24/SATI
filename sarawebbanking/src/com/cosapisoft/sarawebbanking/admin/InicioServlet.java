package com.cosapisoft.sarawebbanking.admin;
import java.io.IOException;
import java.io.Serializable;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class InicioServlet extends HttpServlet implements Servlet,Serializable{
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public InicioServlet() {
		super();
	}

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest arg0, HttpServletResponse arg1)
	 */
	protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		performTask(arg0, arg1);
	}

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest arg0, HttpServletResponse arg1)
	 */
	protected void doPost(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		performTask(arg0, arg1);
	}

	private void performTask(HttpServletRequest req, HttpServletResponse res){
		String paginaDestino = "";
		try {
			HttpSession session = req.getSession(false);
			if (session == null){
				session = req.getSession(true);
			}
			NavigationBean navigation = new NavigationBean();
			session.setAttribute("navigationBean",navigation);	

			String action = req.getParameter("actioncommand");
			if (action.equals(NavigationConstant.ID_MENU_INICIO_DIA)){
				paginaDestino = NavigationConstant.PAG_INICIO_DIA;
			}else if (action.equals(NavigationConstant.ID_MENU_CONFIGURACION)){
				paginaDestino = NavigationConstant.PAG_CONFIGURACION;
			}else if (action.equals(NavigationConstant.ID_MENU_CONFIGURACION)){
				paginaDestino = NavigationConstant.PAG_SERVIDOR_BACKUP;
			}
			if (session.getAttribute("currentUser")==null){
				((NavigationBean) session.getAttribute("navigationBean")).setTargetPage(paginaDestino);
				paginaDestino = NavigationConstant.PAG_INICIO_SESION;
			}			
			callPage(req, res,paginaDestino);
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