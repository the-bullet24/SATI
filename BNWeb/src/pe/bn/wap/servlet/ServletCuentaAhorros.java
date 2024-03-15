package pe.bn.wap.servlet;

import java.io.IOException;
import java.util.Vector;

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
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.CuentaImpl;
import pe.cosapi.system.Key4Digits;

public class ServletCuentaAhorros extends HttpServlet implements Servlet {
	private static LoggerSati log3 = LoggerSati.getInstance(ServletCuentaAhorros.class.getName());
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ServletCuentaAhorros() {
		super();
	}

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest arg0, HttpServletResponse arg1)
	 */
	protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		process(arg0, arg1);
	}

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest arg0, HttpServletResponse arg1)
	 */
	protected void doPost(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		process(arg0, arg1);
	}

	public void process(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		ObjectUtil.getParametrosRequest(req);
		 
		String codCompany = req.getParameter("cod");
		//System.out.println("COMPAÑIA..." + codCompany + "*");
		ConstanteSesion.CODIGO_COMPANIA="3";
		if (codCompany == null || codCompany.equals("null"))
		    codCompany = ConstanteSesion.CODIGO_COMPANIA;  
		    
		int error = 0;
		String mensajeError = null;
		String pagina = req.getParameter("pagina");
		req.setAttribute("accion","ahorros");

		
		String tarjeta = req.getParameter("cboTarjeta");
		String numero = req.getParameter("txtNumero");
		String password = req.getParameter("txtPassword");
		String cvv2 = req.getParameter("txtCVV2");
		 
		String transaccion = "LG01";
		Vector valores = new Vector();
		
		valores.addElement(ObjectUtil.getVectorComponent("transaccion",transaccion));
		valores.addElement(ObjectUtil.getVectorComponent("cboTipoPersona",Constante.COD_PERSON_NAT));

		valores.addElement(ObjectUtil.getVectorComponent("cboTipoTarjeta",Constante.TARJETA_MULTIRED));

		String prefijo = "8018";
		if(tarjeta.equals("002"))
			prefijo = "42141000";
		
		valores.addElement(ObjectUtil.getVectorComponent("txtNumeroTarjeta",prefijo+numero));

		valores.addElement(ObjectUtil.getVectorComponent("txtCvv2",cvv2));
						
		Key4Digits keyGen = new Key4Digits(); 
		String passwordEncrip = keyGen.encriptar(password);
		
		valores.addElement(ObjectUtil.getVectorComponent("txtPassword",passwordEncrip));
		

		java.util.Vector ctas=new java.util.Vector();
		java.util.Vector v=new java.util.Vector();
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement("");
		v.addElement(req.getRemoteAddr());
		ctas.addElement(v);
		
		
		try{				
			Usuario usuario = FacadeFactory.getLoginFacade().autenticar(transaccion,codCompany,valores,ctas, "", "");
			usuario.setTipoIngreso(codCompany);
			req.setAttribute(ConstanteSesion.USUARIO_EN_SESION, usuario);
			Cuenta cta= (Cuenta)FacadeFactory.getConsultaWAPFacade().getSaldoCuentaAhorro(usuario,req.getRemoteAddr());
			ConstanteSesion.WAP_CTA_CTE = cta.getNumeroProducto();
			CuentaImpl obj =(CuentaImpl) FacadeFactory.getConsultaFacade().consultar("01",cta.getNumeroProducto(),cta.getMonedaProducto(), "", req.getRemoteAddr(),usuario,req);// 01= codigo de consulta segun consultaaction
			req.setAttribute(ConstanteSesion.CONSULTA,obj); 
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
		getServletContext().getRequestDispatcher("/WEB-INF/wap/bnRespuestaCuentaAhorros.jsp").forward(req,resp);

	}
	
}