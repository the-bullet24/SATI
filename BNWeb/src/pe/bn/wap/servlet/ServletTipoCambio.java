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

public class ServletTipoCambio extends HttpServlet implements Servlet {
	private static LoggerSati log3 = LoggerSati.getInstance(ServletTipoCambio.class.getName());
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ServletTipoCambio() {
		super();
	}

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest arg0, HttpServletResponse arg1)
	 */
	protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		process(arg0,arg1);
	}

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest arg0, HttpServletResponse arg1)
	 */
	protected void doPost(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		process(arg0,arg1);
	}

	public void process(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		ObjectUtil.getParametrosRequest(req);
		ConstanteSesion.WAP_CTA_CTE="";
		ConstanteSesion.WAP_DNI_CTA_CTE="";
		ConstanteSesion.WAP_NUM_TAR="";
		String codCompany = req.getParameter("cod").trim();
		ConstanteSesion.CODIGO_COMPANIA="3";
		if (codCompany == null || codCompany.equals("null"))
		    codCompany = ConstanteSesion.CODIGO_COMPANIA;
		ConstanteSesion.CODIGO_COMPANIA = codCompany; 
		int error = 0;
		String mensajeError = null;
		try{			
			req.setAttribute(ConstanteSesion.CONSULTA,FacadeFactory.getConsultaWAPFacade().getTipoCambio(req.getRemoteAddr()));
			
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
		getServletContext().getRequestDispatcher("/WEB-INF/wap/bnRespuestaTipoCambio.jsp").forward(req,resp);
	}

}