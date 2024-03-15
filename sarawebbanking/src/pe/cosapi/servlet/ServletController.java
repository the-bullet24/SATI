package pe.cosapi.servlet;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.cosapisoft.sarawebbanking.admin.ServletInicioDiaAutomatico;

import pe.cosapi.common.BNAplicacion;
import pe.cosapi.common.SpringWebApplicationContext;

public class ServletController extends HttpServlet {
	 

	public void init(ServletConfig config) throws ServletException {
	    System.out.println("******************Ingresando al metodo Init****************");
		super.init(config);
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		BNAplicacion aplicacion = BNAplicacion.getInstance(); 
		
		aplicacion.setRutaClasspath(getServletContext().getRealPath("/"));
	    SpringWebApplicationContext.getInstance().setWebApplicationContext(wac);
	    System.out.println("******************SE INICIO CORRECTAMENTE EL CONTEXTO****************");
	}
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    process(req,resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req,resp);
	}
	
	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//getServletContext().getRequestDispatcher("/login.do?validar=false").forward(req,resp);
	}

	
}