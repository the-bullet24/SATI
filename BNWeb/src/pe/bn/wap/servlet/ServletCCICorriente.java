package pe.bn.wap.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.bn.afiliacion.action.DesafCableAction;
import pe.bn.consulta.facade.ConsultaWAPFacade;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteSesion;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.CuentaImpl;
import pe.cosapi.domain.impl.UsuarioImpl;
import pe.cosapi.sarabank.bean.Transaction;
import pe.cosapi.system.GeneralTest;
import pe.cosapi.system.Key4Digits;


public class ServletCCICorriente extends HttpServlet implements Servlet {
	
	private static LoggerSati log3 = LoggerSati.getInstance(ServletCCICorriente.class.getName());
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ServletCCICorriente() {
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
		if(codCompany==null){
			codCompany=ConstanteSesion.CODIGO_COMPANIA;
		}
		/**
		System.out.println("COMPAÑIA..." + codCompany);
		if (codCompany != null){
			ConstanteSesion.CODIGO_COMPANIA = codCompany.trim();
		}
		*/
		
		
		int error = 0; 
		String mensajeError = null;
		String pagina = req.getParameter("pagina");
		req.setAttribute("accion","CCI");
		 
		if(pagina==null){
			
			req.setAttribute("cboTipoCuenta","001");
			getServletContext().getRequestDispatcher("/WEB-INF/wap/bnDNI.jsp").forward(req,resp);
		}
		else if(pagina.equals("1")){			 
			ObjectUtil.parameter2Atributte(req,"cboTipoCuenta");
			ObjectUtil.parameter2Atributte(req,"txtDNI");
			req.getSession().setAttribute("WapDniCCICte",req.getParameter("txtDNI"));
			ConstanteSesion.WAP_DNI_CTA_CTE=(String)req.getSession().getAttribute("WapDniCCICte");
			getServletContext().getRequestDispatcher("/WEB-INF/wap/bnPassword6.jsp").forward(req,resp);
		}
		else if(pagina.equals("2")){			
			
			req.setAttribute("fecha",ObjectUtil.getFechaActual());
			String tipoCuenta = req.getParameter("cboTipoCuenta"); 
			String DNI = req.getParameter("txtDNI");
			ConstanteSesion.WAP_DNI_CTA_CTE=req.getParameter("txtDNI");
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
			try
			{
				/*			
				req.setAttribute(ConstanteSesion.CONSULTA, FacadeFactory.getConsultaWAPFacade().getCuentasCorrientes(tipoCuenta,DNI,password,req.getRemoteAddr()));
				 */
				
				Usuario usuario = FacadeFactory.getLoginFacade().autenticar(transaccion, codCompany,valores,ctas, "", "");
				usuario.setTipoIngreso(codCompany);
				usuario.getTarjeta().setNumero(ConstanteSesion.WAP_DNI_CTA_CTE);
				req.getSession().setAttribute(ConstanteSesion.USUARIO_EN_SESION, usuario);
				ConstanteSesion.USUARIO_EN_SESION_WAP = usuario;
				ConsultaWAPFacade consultaWAPFacade = FacadeFactory.getConsultaWAPFacade();
                List cuentasCorrientes = consultaWAPFacade.getCuentasCorrientes(usuario);
                req.setAttribute(ConstanteSesion.CONSULTA,cuentasCorrientes); 
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
			ConstanteSesion.WAP_CTA_CTE=req.getParameter("cboCuenta");
			ConstanteSesion.WAP_NUM_TAR=req.getParameter("txtDNI");
			try{
			    //@DPS req.setAttribute(ConstanteSesion.USUARIO_EN_SESION, ObjectUtil.formatearObjetoWAP(new UsuarioImpl()));
			    Usuario usuario = (Usuario)req.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
			    req.setAttribute(ConstanteSesion.USUARIO_EN_SESION, ObjectUtil.formatearObjetoWAP(usuario));
	
				//
				String transaccion = "CL01"; 

				Transaction t = new Transaction(transaccion);				
				
				Vector valores = new Vector();
				Vector valor = new Vector();
				// valor.addElement("transaccion");
				valor.addElement("transaccion");
				valor.addElement(transaccion);
				valores.add(valor);
				
				valor = new Vector();
				// valor.addElement("hidCuenta");
				valor.addElement("txtNumCta");
				valor.addElement(cuenta);
				valores.add(valor);
				
				//---
				valor = new Vector();
				valor.addElement("txtTipoAfectacion");
				valor.addElement("");
				valores.add(valor);

				valor = new Vector();
				valor.addElement("txtNumTran");
				valor.addElement("0125");
				valores.add(valor);
				//--
				
				Vector ctas=new Vector();
				Vector v=new Vector();
				v.addElement("07");
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
				
				
				t.setValues(valores);
				t.setCuentas(ctas);		
				/*t.loadVectors();
				Vector resultado = t.getMascVector();*/
				
				int anioItf = ObjectUtil.getYear();
				anioItf = anioItf - 1;
				
				
				//
				
				
				Cuenta cuentaCCI = FacadeFactory.getConsultaWAPFacade().getNumeroCCI(tipoCuenta,DNI,password,cuenta,req.getRemoteAddr());
				//Cuenta cuentaCCI = FacadeFactory.getConsultaWAPFacade().getNumeroCCI((Usuario)ObjectUtil.formatearObjetoWAP(new UsuarioImpl()),req.getRemoteAddr());
				cuentaCCI.getConsultaCtaCteCci(t,(Usuario)ConstanteSesion.USUARIO_EN_SESION_WAP);
				
				GeneralTest gt= new GeneralTest();
				cuentaCCI.setNumeroProducto(cuenta);
				gt.generarLog2(t,usuario,Constante.REFRENDO_CTACTECCI,(CuentaImpl)cuentaCCI,req);					
				
			//	cuentaCCI.getc.getConsultaCtaCteCci();
                req.setAttribute(ConstanteSesion.CONSULTA,cuentaCCI); 
			}
			catch(Exception e)
				{
				log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
				String mensaje ="error generico";//ObjectUtil.getMensajeErrorWAP(e);
				error = 1;
				e.printStackTrace();
				req.setAttribute("error",ConstanteSesion.HOST_ERROR_WAP);
				getServletContext().getRequestDispatcher("/WEB-INF/wap/bnError.jsp").forward(req,resp);			
				return;
			}
			getServletContext().getRequestDispatcher("/WEB-INF/wap/bnRespuestaCCICorriente.jsp").forward(req,resp);
		}
	}

}