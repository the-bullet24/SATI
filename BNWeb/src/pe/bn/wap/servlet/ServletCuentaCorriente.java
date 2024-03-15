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
import pe.cosapi.domain.Usuario;
import pe.cosapi.system.Key4Digits;

public class ServletCuentaCorriente extends HttpServlet implements Servlet {
	private static LoggerSati log3 = LoggerSati.getInstance(ServletCuentaCorriente.class.getName());
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ServletCuentaCorriente() {
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
		int error = 0;
		String codCompany = req.getParameter("cod");
		//System.out.println("COMPAÑIA..." + codCompany);
		ConstanteSesion.CODIGO_COMPANIA="1";
		if (codCompany == null || codCompany.equals("null"))
		    codCompany = ConstanteSesion.CODIGO_COMPANIA;  
		
		String mensajeError = null;
		String pagina = req.getParameter("pagina");
		req.setAttribute("accion","corriente");
		req.getSession().setAttribute("WapDniCtaCte",req.getParameter("txtDNI"));
		ConstanteSesion.WAP_DNI_CTA_CTE=(String)req.getSession().getAttribute("WapDniCtaCte"); 
		if(pagina==null){
			getServletContext().getRequestDispatcher("/WEB-INF/wap/bnTipoCuenta.jsp").forward(req,resp);
		}
		else if(pagina.equals("0")){			
			ObjectUtil.parameter2Atributte(req,"cboTipoCuenta");
			getServletContext().getRequestDispatcher("/WEB-INF/wap/bnDNI.jsp").forward(req,resp);
		}
		else if(pagina.equals("1")){
			ObjectUtil.parameter2Atributte(req,"cboTipoCuenta");
			ObjectUtil.parameter2Atributte(req,"txtDNI");
			getServletContext().getRequestDispatcher("/WEB-INF/wap/bnPassword6.jsp").forward(req,resp);
		}
		if(pagina.equals("2")){			
			String tipoCuenta = req.getParameter("cboTipoCuenta");
			String DNI = req.getParameter("txtDNI");
			String password = req.getParameter("txtPassword");
			ObjectUtil.parameter2Atributte(req,"cboTipoCuenta");
			ObjectUtil.parameter2Atributte(req,"txtDNI");
			ObjectUtil.parameter2Atributte(req,"txtPassword");
			
			/************************/
			String transaccion = "LG01";
			Vector valores = new Vector();
			
			valores.addElement(ObjectUtil.getVectorComponent("transaccion",transaccion));
			valores.addElement(ObjectUtil.getVectorComponent("cboTipoPersona",Constante.COD_PERSON_NAT));

			valores.addElement(ObjectUtil.getVectorComponent("cboTipoTarjeta",Constante.TARJETA_VACIA));

			valores.addElement(ObjectUtil.getVectorComponent("txtNumeroTarjeta",DNI));

			valores.addElement(ObjectUtil.getVectorComponent("txtCvv2","000"));
							
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

			/************************/
			
			
			
			
			try{
				Usuario usuario = FacadeFactory.getLoginFacade().autenticar(transaccion,codCompany,valores,ctas, "", "");
				usuario.setTipoIngreso(codCompany);
				//System.out.println("USUARIO_SESION + " +usuario);
				ConstanteSesion.USUARIO_EN_SESION_WAP=usuario;
				req.getSession().setAttribute(ConstanteSesion.USUARIO_EN_SESION, usuario);
				req.setAttribute(ConstanteSesion.CONSULTA,FacadeFactory.getConsultaWAPFacade().getCuentasCorrientes(usuario)); 
				 
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
			getServletContext().getRequestDispatcher("/WEB-INF/wap/bnListaCuentas.jsp?cod="+codCompany).forward(req,resp);
		}
		else if(pagina.equals("3")){
			String tipoCuenta = req.getParameter("cboTipoCuenta");
			String DNI = req.getParameter("txtDNI");
			String password = req.getParameter("txtPassword");
			String cuenta = req.getParameter("cboCuenta");
			//System.out.println("TIPOCUENTA-->"+tipoCuenta);
			//System.out.println("DNI-->"+DNI);
			//System.out.println("PASSWORD-->"+password);
			//System.out.println("CUENTA-->"+cuenta);
			Usuario usuario = (Usuario) ConstanteSesion.USUARIO_EN_SESION_WAP;
			ConstanteSesion.WAP_CTA_CTE=cuenta;
			//System.out.println("USUARIO_SESION 3+ " +usuario);
			try{			
				
				req.setAttribute(ConstanteSesion.CONSULTA,FacadeFactory.getConsultaWAPFacade().getSaldoCuentaCorriente(usuario,cuenta,req.getRemoteAddr(),req)); 
			}
			catch(Exception e){
				log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
				String mensaje =ObjectUtil.getMensajeErrorWAP(e);
				error = 1;
				e.printStackTrace();
				req.setAttribute("error",mensaje);
				getServletContext().getRequestDispatcher("/WEB-INF/wap/bnError.jsp").forward(req,resp);
				return;
			}
			getServletContext().getRequestDispatcher("/WEB-INF/wap/bnRespuestaCuentaCorriente.jsp").forward(req,resp);
		}
	}
	
}