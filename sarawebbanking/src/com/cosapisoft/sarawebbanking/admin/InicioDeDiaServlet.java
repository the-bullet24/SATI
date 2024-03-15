package com.cosapisoft.sarawebbanking.admin;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import CosapiSoft.SARAWebBanking.InicioDeDia;
import CosapiSoft.SARAWebBanking.InicioSesionServlet;
import CosapiSoft.SARAWebBanking.JspServlet;
import CosapiSoft.SARAWebBuilder.Diccionario;
import pe.cosapi.common.Constante;


public class InicioDeDiaServlet extends HttpServlet implements Servlet, Serializable{
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public InicioDeDiaServlet() {
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

	private void performTask(HttpServletRequest req, HttpServletResponse res) throws java.io.IOException, javax.servlet.ServletException {
		String paginaDestino = "";
		try {
			HttpSession session = req.getSession(false);
			if (session == null){
				session = req.getSession(true);
			}
			
			/**
			InicioDeDia bean;
			bean = new InicioDeDia();
			bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) req.getSession(false).getAttribute("builder"));
			
			bean.getLogin().setCodact("101");
			bean.getLogin().setAccion("Consulta");
			if (!bean.getLogin().hasAccesAccion()) {
				//					res.sendRedirect("../../../SARAWebBanking/SARAWebBanking/PerfilBuilderError.jsp");
				callPage(req, res, JspServlet.ERROR_BUILDER_JSP);
				return;
			}
			*/
			
			if (session.getAttribute("currentUser") == null){
				NavigationBean navigationBean = new NavigationBean();
				navigationBean.setMessageError("Ha ocurrido un error inesperado");
				session.setAttribute("navigationBean", navigationBean);
				paginaDestino = NavigationConstant.PAG_ERROR;
			}else{
				((NavigationBean) session.getAttribute("navigationBean")).setMessageError("");
				String action = req.getParameter("actioncommand");
				if (action.equals(NavigationConstant.ID_INICIO_DIA)){
				    if (Constante.INICIO_101.trim().equals("101")) {
				        ProcessBean processBean = new ProcessBean();
						ArrayList listaSubProcesos = new ArrayList();
						
						processBean.setProcessID(NavigationConstant.ID_INICIO_DIA);
						processBean.setTitleProcess("Inicio de Día");
						
						//listaSubProcesos.add(ProcessBean.PROC_ACTUALIZACION_SISTEMA);
						//listaSubProcesos.add(ProcessBean.PROC_ACTUALIZACION_FECHA);
						listaSubProcesos.add(ProcessBean.PROC_ACTUALIZACION_AGENCIA);
						listaSubProcesos.add(ProcessBean.PROC_PASE_DIARIO);
						listaSubProcesos.add(ProcessBean.PROC_DEPURACION_HISTORICO);
						listaSubProcesos.add(ProcessBean.PROC_DESBLOQUEO_AGENCIA);
						
						processBean.setSubProcessList(listaSubProcesos);
						req.getSession().setAttribute("processBean",processBean);
						paginaDestino = NavigationConstant.PAG_EJECUCION;				        
				    } else {
				        NavigationBean navigationBean = new NavigationBean();
						navigationBean.setMessageError("Ud. no tiene acceso a esta opción");
						session.setAttribute("navigationBean", navigationBean);
						paginaDestino = NavigationConstant.PAG_ACCESO;
				    }
				        
				}else if (action.equals(NavigationConstant.ID_RE_INICIO_DIA)){
				    if (Constante.INICIO_102.trim().equals("102")) {
				        ProcessBean processBean = new ProcessBean();
						ArrayList listaSubProcesos = new ArrayList();
						
						processBean.setProcessID(NavigationConstant.ID_RE_INICIO_DIA);
						processBean.setTitleProcess("Re-Inicio de Día");
						
						//listaSubProcesos.add(ProcessBean.PROC_ACTUALIZACION_SISTEMA);
						//listaSubProcesos.add(ProcessBean.PROC_ACTUALIZACION_FECHA);
						listaSubProcesos.add(ProcessBean.PROC_ACTUALIZACION_AGENCIA);
						
						processBean.setSubProcessList(listaSubProcesos);
						req.getSession().setAttribute("processBean",processBean);
						paginaDestino = NavigationConstant.PAG_EJECUCION;				        
				    } else {
				        NavigationBean navigationBean = new NavigationBean();
				        navigationBean.setMessageError("Ud. no tiene acceso a esta opción");
						session.setAttribute("navigationBean", navigationBean);
						paginaDestino = NavigationConstant.PAG_ACCESO;
				    }
					
				}else if (action.equals(NavigationConstant.ID_FIN_DIA)){
					ProcessBean processBean = new ProcessBean();
					ArrayList listaSubProcesos = new ArrayList();
					
					processBean.setProcessID(NavigationConstant.ID_FIN_DIA);
					processBean.setTitleProcess("Fin de Día");
					
					listaSubProcesos.add(ProcessBean.PROC_GENERACION_ARCHIVO_RPT_ESTADO);
					listaSubProcesos.add(ProcessBean.PROC_BLOQUEO_AGENCIA);
					
					processBean.setSubProcessList(listaSubProcesos);
					req.getSession().setAttribute("processBean",processBean);
					paginaDestino = NavigationConstant.PAG_EJECUCION;
				}else if (action.equals(NavigationConstant.ID_CANCELAR)){
					ProcessBean processBean = new ProcessBean();
					processBean.setProcessID("");
					session.invalidate();
					paginaDestino = NavigationConstant.PAG_INICIO;
				}
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
	
	private boolean validateSession(HttpServletRequest req){
		boolean estado = false;
		if (req.getSession() == null){
			estado = false;
		}else if (req.getSession().getAttribute("currentUser") == null){
			estado = false;
		}else{
			estado = true;
		}
		
		return estado;
	}
}