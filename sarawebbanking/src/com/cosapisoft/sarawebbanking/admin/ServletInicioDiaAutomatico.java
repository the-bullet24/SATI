package com.cosapisoft.sarawebbanking.admin;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ServletInicioDiaAutomatico extends HttpServlet implements Servlet {
	JobInicioDia job=null;
	public ServletInicioDiaAutomatico() {
		super();
		
	}
	
	public void init() throws ServletException{
		
		System.out.println("*****************************Cargando el Job de Inicio de Día automático*****************************");
		try{
			job = new JobInicioDia();
			job.start(); //run();	
			
		}
		catch (Exception e) {
			System.out.println("*****************************No se pudo cargar el inicio automático*****************************");
		}
		super.init();
	}
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

	}
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest arg0, HttpServletResponse arg1)
	 */
	protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		doPost(arg0,arg1);// TODO Auto-generated method stub
	}

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest arg0, HttpServletResponse arg1)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*if(request.getParameter("order")==null){
			JobInicioDia.flag=true;
			job = new JobInicioDia();
			job.run();	
		}else if(request.getParameter("order").toString().equals("stop")){
			JobInicioDia.flag=false;
			job.destroy();
			//job.stop();
		}*/
		init();
				
	}

}