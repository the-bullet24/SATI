package pe.cosapi.sarawebmanager.servlet;


import java.math.BigDecimal;
import pe.cosapi.common.Constante;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.domain.Limit;
import pe.cosapi.common.domain.impl.LimitImpl;


import CosapiSoft.SARAWebBanking.*;
import CosapiSoft.SARAWebManager.Limites;
import CosapiSoft.SARAWebManager.Limits;


public class LimitsServlet extends HttpServlet {
   
	public LimitsServlet() {
		super();
	}
	
	public void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws java.io.IOException, javax.servlet.ServletException {
	    System.out.println("++++++++++++++++++++++++++++++++++++doGet Ingreso servlet++++++++++++++++++++++++++++++++++++");
	    Limit[] arrLimit = null;
	    System.out.println("Sesion de CADUCIDAD"+Constante.SESSION_CADUCA);
	    
	    try {
	        if (Constante.SESSION_CADUCA.equals("2")) {
				//request.getSession(false).setAttribute("error", new Exception(Mensajes.getMessage(Mensajes.SESION_TERMINADA) + " - " + this.getClass().toString()));;
				response.sendRedirect("/sarawebbanking/SARAWebBanking/Error.jsp");
				return;
			}
		    if (!isLoggedIn(request)) {
				    Constante.SESSION_CADUCA = "2";
					//request.getSession(false).setAttribute("error",new Exception(Mensajes.getMessage(Mensajes.SESION_TERMINADA) + " - " + this.getClass().toString()));
					response.sendRedirect("/sarawebbanking/SARAWebBanking/Error.jsp");
					//getServletContext().getRequestDispatcher("/SARAWebBanking/Error.jsp").forward(request, response);
				return;
			}
		    
	        Limits bean;
			if (request.getSession(false).getAttribute("tlimdat") != null) {
				bean = (Limits) (request.getSession(false).getAttribute("tlimdat"));
			}
			else {
				bean = new Limits();
				bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) request.getSession(false).getAttribute("manager"));
				request.getSession(false).setAttribute("tlimdat", bean);
			}
			
			bean.getLogin().setError("");
			bean.getLogin().setNamePantalla("LIMITES");
			System.out.println("Mensaje Opción:"+bean.getLogin().getNamePantalla());
			
			bean.getLogin().setCodact("306");
			bean.getLogin().setAccion("Consulta");
			if (!bean.getLogin().hasAccesAccion()) {
				//callPage(request, response, JspServlet.ERROR_MANAGER_JSP);
				getServletContext().getRequestDispatcher("/SARAWebBanking/PerfilManagerError.jsp").forward(request, response);
				return;
			}
			
	        arrLimit = FacadeFactory.getCommonFacade().getArrayLimit();
        } catch (Exception e) {
            System.out.println(""+e);
        }
        
        request.setAttribute("arrLimit",arrLimit);
		getServletContext().getRequestDispatcher("/SARAWebManager/Tablas/Limits.jsp").forward(request, response);
	}
	

	public void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws java.io.IOException, javax.servlet.ServletException {
	    System.out.println("1 ++++++++++++++++++++++++++++++++++++doPostIngreso al servlet++++++++++++++++++++++++++++++++++++");
	    Limit[] arrLimit = null;
	    System.out.println("Sesion de CADUCIDAD"+Constante.SESSION_CADUCA);
	    if (Constante.SESSION_CADUCA.equals("2")) {
			try {
			    request.getSession(false).setAttribute("error",new Exception(Mensajes.getMessage(Mensajes.SESION_TERMINADA) + " - " + this.getClass().toString()));;
			    response.sendRedirect("/sarawebbanking/SARAWebBanking/Error.jsp");
			}
			catch (Exception se) {
			    response.getWriter().println(se.getMessage());
			}
			return;
	    }
	    
	    if (!isLoggedIn(request)) {
	        try {
			    request.getSession(false).setAttribute("error",new Exception(Mensajes.getMessage(Mensajes.SESION_TERMINADA) + " - " + this.getClass().toString()));;
			    response.sendRedirect("/sarawebbanking/SARAWebBanking/Error.jsp");
			}
			catch (Exception se) {
			    response.getWriter().println(se.getMessage());
			}
			return;
		}
	    
		System.out.println("metodo:"+request.getParameter("hdMetodo"));
		try {
			
			Limits bean;
			bean = new Limits();
			bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) request.getSession(false).getAttribute("manager"));
			request.getSession(false).setAttribute("tlimdat", bean);
			
			bean.getLogin().setCodact("306");
			bean.getLogin().setAccion("Consulta");
			if (!bean.getLogin().hasAccesAccion()) {
				//JavaServlet.this.callPage(request, response, JspServlet.ERROR_MANAGER_JSP);
				//callPage(request, response, JspServlet.ERROR_MANAGER_JSP);
				// Puede ser...
				//request.sendRedirect(JspServlet.ERROR_MANAGER_JSP);
				return;
			}

		    if (request.getParameter("hdMetodo").equals("agregar")){
		        request.setAttribute("mensaje",agregar(request));
		        arrLimit = FacadeFactory.getCommonFacade().getArrayLimit();
		    }else if (request.getParameter("hdMetodo").equals("eliminar")){
		        eliminar(request);
		        arrLimit = FacadeFactory.getCommonFacade().getArrayLimit();
		    }else if (request.getParameter("hdMetodo").equals("modificar")){
		        modificar(request);
		        arrLimit = FacadeFactory.getCommonFacade().getArrayLimit();
		    }
        } catch (Exception e) {
            System.out.println(""+e);
        }	
        request.setAttribute("arrLimit",arrLimit);
		getServletContext().getRequestDispatcher("/SARAWebManager/Tablas/Limits.jsp").forward(request, response);
	}

	public boolean isLoggedIn(javax.servlet.http.HttpServletRequest req) {
		boolean status = false;
		javax.servlet.http.HttpSession session = req.getSession(false);
		if (session != null)
			status = true;
		return status;
	}


	private String agregar(HttpServletRequest request) throws Exception{
	    return FacadeFactory.getCommonFacade().insertarLimite(getLimit(request));
	}
	
	private void eliminar(HttpServletRequest request) throws Exception{
	    String[] cadena = request.getParameterValues("chk");
	    if (cadena == null)
	        return;
	    
	    if (cadena.length == 0)
	        return;
	    
	    for (int i=0; i<cadena.length;i++){
	        System.out.println("Eliminando cadena["+i+"]:"+cadena[i]);
	        FacadeFactory.getCommonFacade().eliminarLimite(getLim(cadena[i]));
	    }
	}
	
	private void modificar(HttpServletRequest request) throws Exception{
	    FacadeFactory.getCommonFacade().modificarLimite(getLimit(request));
	}
	
	
	private Limit getLim(String tipoCuenta){
	    Limit temp = new LimitImpl();
	    temp.setTipoCuenta(tipoCuenta);
	    return temp;
	}
	
	private Limit getLimit(HttpServletRequest request){
	    Limit temp = new LimitImpl();
	    temp.setTipoCuenta(request.getParameter("cboTipoCuenta"));
	    temp.setImporteMaximo(new BigDecimal(request.getParameter("TxtImporte")));
	    return temp;
	}
	
}