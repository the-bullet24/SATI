package pe.cosapi.sarawebmanager.servlet;


import java.math.BigDecimal;
import java.text.ParseException;

import CosapiSoft.SARAWebBanking.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestAttributes;

import com.ibm.etools.webservice.wscommonext.Timestamp;
import com.ibm.ws.webservices.engine.xmlsoap.ext.RequestResponse;

import pe.cosapi.common.Constante;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.common.domain.Banner;
import pe.cosapi.common.domain.impl.BannerImpl;


import CosapiSoft.SARAWebBanking.*;
import CosapiSoft.SARAWebManager.BannerPromocional;

public class BannerServlet extends HttpServlet {
    
	public BannerServlet() {
		super();
	}
	
	public void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws java.io.IOException, javax.servlet.ServletException {
	    System.out.println("++++++++++++++++++++++++++++++++++++doGet Ingreso servlet++++++++++++++++++++++++++++++++++++");
	    BannerImpl banner = new BannerImpl();
	    if (Constante.SESSION_CADUCA.equals("2")) {
			try {
			    request.getSession(false).setAttribute("error",new Exception(Mensajes.getMessage(Mensajes.SESION_TERMINADA) + " - " + this.getClass().toString()));
				getServletContext().getRequestDispatcher("/SARAWebBanking/Error.jsp").forward(request, response);
				
			}
			catch (Exception se) {
			    response.getWriter().println(se.getMessage());
			}
			return;
		}
	    if (!isLoggedIn(request)) {
	    	try {
			    request.getSession(false).setAttribute("error",new Exception(Mensajes.getMessage(Mensajes.SESION_TERMINADA) + " - " + this.getClass().toString()));
				getServletContext().getRequestDispatcher("/SARAWebBanking/Error.jsp").forward(request, response);
				
			}
			catch (Exception se) {
			    response.getWriter().println(se.getMessage());
			}
			return;
		}
	    
	    try {
	        banner = FacadeFactory.getBannerFacade().getBanner("01");
	        
        } catch (Exception e) {
            System.out.println(""+e);
        }
        request.setAttribute("banner",banner);
        
       
		getServletContext().getRequestDispatcher("/SARAWebManager/Tablas/BannerPromocional.jsp").forward(request, response);
		//response.sendRedirect("/sarawebbanking/SARAWebManager/Tablas/BannerPromocional.jsp");
		
		
	}
	

	public void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws java.io.IOException, javax.servlet.ServletException {
	    System.out.println("1 ++++++++++++++++++++++++++++++++++++doPostIngreso al servlet++++++++++++++++++++++++++++++++++++");
	    BannerImpl banner = new BannerImpl();
	    if (Constante.SESSION_CADUCA.equals("2")) {
			try {
			    request.getSession(false).setAttribute("error",new Exception(Mensajes.getMessage(Mensajes.SESION_TERMINADA) + " - " + this.getClass().toString()));;
			    getServletContext().getRequestDispatcher("/SARAWebBanking/Error.jsp").forward(request, response);
			}
			catch (Exception se) {
			    response.getWriter().println(se.getMessage());
			}
			return;
		}
		
		System.out.println("metodo:"+request.getParameter("hdMetodo"));
		try {
			
			BannerPromocional bean;
			bean = new BannerPromocional();
			bean.setLogin((CosapiSoft.SARAWebBanking.InicioSesion) request.getSession(false).getAttribute("manager"));
			request.getSession(false).setAttribute("tbandat", bean);
			
			bean.getLogin().setCodact("306");
			bean.getLogin().setAccion("Consulta");
			if (!bean.getLogin().hasAccesAccion()) {
				//JavaServlet.this.callPage(request, response, JspServlet.ERROR_MANAGER_JSP);
				//callPage(request, response, JspServlet.ERROR_MANAGER_JSP);
				// Puede ser...
				//request.sendRedirect(JspServlet.ERROR_MANAGER_JSP);
				return;
			}

		    if (request.getParameter("hdMetodo").equals("modificar")){
		        modificar(request);
		        banner = FacadeFactory.getBannerFacade().getBanner("01");
		    }
        } catch (Exception e) {
            System.out.println(""+e);
        }	
       request.setAttribute("banner",banner);
       
       
		getServletContext().getRequestDispatcher("/SARAWebManager/Tablas/BannerPromocional.jsp").forward(request, response);
		
		
	}

	public boolean isLoggedIn(javax.servlet.http.HttpServletRequest req) {
		boolean status = false;
		javax.servlet.http.HttpSession session = req.getSession(false);
		if (session != null)
			status = true;
		return status;
	}


	
	
	private void modificar(HttpServletRequest request) throws Exception{
	    FacadeFactory.getBannerFacade().modificarBanner(getBanner(request));
	}
	
	
	
	
	private Banner getBanner(HttpServletRequest request) {
	    Banner temp = new BannerImpl();
	    temp.setTipoPersona("01");
	    try {
			temp.setFechaInicio((Timestamp) ObjectUtil.stringToTimestamp(request.getParameter("txtDatbeg").substring(0,2),request.getParameter("txtDatbeg").substring(3,5),request.getParameter("txtDatbeg").substring(6)));
			 temp.setFechaFin((Timestamp) ObjectUtil.stringToTimestamp(request.getParameter("txtDatbeg").substring(0,2),request.getParameter("txtDatbeg").substring(3,5),request.getParameter("txtDatbeg").substring(6)));
			    temp.setNombreArchivo(request.getParameter("txtFilnam"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	    
	    return temp;
	}
	
}