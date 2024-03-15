package pe.bn.wap.servlet;

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
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.domain.impl.UsuarioImpl;

public class ServletLimitePrestamo extends HttpServlet implements Servlet {
	private static LoggerSati log3 = LoggerSati.getInstance(ServletLimitePrestamo.class.getName());
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ServletLimitePrestamo() {
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
		ObjectUtil.getParametrosRequest(req);
		String codCompany = req.getParameter("cod").trim();
		//System.out.println("COMPAÑIA..." + codCompany);
		ConstanteSesion.CODIGO_COMPANIA="3";
		if (codCompany == null || codCompany.equals("null"))
		    codCompany = ConstanteSesion.CODIGO_COMPANIA;  
		
		int error = 0;
		String mensajeError = null;
		/*String pagina = req.getParameter("pagina");
		req.setAttribute("accion","multired");
		if(pagina==null){
			getServletContext().getRequestDispatcher("/WEB-INF/wap/bnTarjeta.jsp").forward(req,resp);
		}
		else if(pagina.equals("1")){			
			ObjectUtil.parameter2Atributte(req,"cboTarjeta");
			getServletContext().getRequestDispatcher("/WEB-INF/wap/bnNumero.jsp").forward(req,resp);
		}
		else if(pagina.equals("2")){			
			ObjectUtil.parameter2Atributte(req,"cboTarjeta");
			ObjectUtil.parameter2Atributte(req,"txtNumero");
			getServletContext().getRequestDispatcher("/WEB-INF/wap/bnPassword4.jsp").forward(req,resp);
		}
		else if(pagina.equals("3")){*/			
			String tarjeta = req.getParameter("cboTarjeta");
			String numero = req.getParameter("txtNumero");
			String password = req.getParameter("txtPassword");			
			try{				
				req.setAttribute(ConstanteSesion.USUARIO_EN_SESION, ObjectUtil.formatearObjetoWAP(new UsuarioImpl()));
				req.setAttribute(ConstanteSesion.CONSULTA,FacadeFactory.getConsultaWAPFacade().getLimitePrestamo(tarjeta,numero,password,req.getRemoteAddr())); 
			}
			catch(Exception e){
				log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
				String mensaje = ObjectUtil.getMensajeErrorWAP(e);
				error = 1;
				e.printStackTrace();
				req.setAttribute("error",mensaje);
				getServletContext().getRequestDispatcher("/WEB-INF/wap/bnError.jsp").forward(req,resp);
				return;
			}
			getServletContext().getRequestDispatcher("/WEB-INF/wap/bnRespuestaLimitePresPer.jsp").forward(req,resp);
			return;
		//}
	}
	
	
}