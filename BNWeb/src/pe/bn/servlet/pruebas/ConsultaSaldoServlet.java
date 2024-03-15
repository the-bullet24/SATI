package pe.bn.servlet.pruebas;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.bn.afiliacion.action.DesafCableAction;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteSesion;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.UsuarioImpl;


public class ConsultaSaldoServlet extends HttpServlet implements Servlet {
	private static LoggerSati log3 = LoggerSati.getInstance(ConsultaSaldoServlet.class.getName());
	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */	
	public ConsultaSaldoServlet() {
		super();
	}

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest arg0, HttpServletResponse arg1)
	 */
	protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(arg0,arg1);
	}

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest arg0, HttpServletResponse arg1)
	 */
	protected void doPost(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(arg0,arg1);
	}

	public void process(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		Usuario usuario = new UsuarioImpl();
		req.getSession().setAttribute(ConstanteSesion.USUARIO_EN_SESION,usuario);
		try{
			/*
			 * 1er parametro : es el codigo de la consulta como es consulta de saldo de cuenta de ahorros es "01" 
			 * 2do paametro	: el número de la cuenta de la tarjeta, debe ser el numero de cuenta a consultar
			 * 3er parametro: ip del cliente
			 * 4to parametro: objeto usuario
			 */
			//04017800189
			req.getSession().setAttribute(ConstanteSesion.CONSULTA, FacadeFactory.getConsultaFacade().consultar("05","04017800189", "PEI", "", req.getRemoteAddr(),usuario,req));
		}
		catch (Exception e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			System.out.println("Exception no manejada");
		}
		req.getSession().setAttribute(ConstanteSesion.CARPETA,Constante.COD_PERSON_NAT);
		getServletContext().getRequestDispatcher("/WEB-INF/page/consulta/ahorros/consultaSaldoAhorros.jsp").forward(req,resp);
	}
	
	
}