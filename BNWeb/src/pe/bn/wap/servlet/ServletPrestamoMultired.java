package pe.bn.wap.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.bn.afiliacion.action.DesafCableAction;
import pe.bn.consulta.domain.Prestamo;
import pe.bn.consulta.domain.impl.PrestamoImpl;
import pe.bn.tarjeta.domain.Tarjeta;
import pe.bn.tarjeta.domain.impl.TarjetaImpl;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteSesion;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.Itf;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.CuentaImpl;
import pe.cosapi.domain.impl.ItfImpl;
import pe.cosapi.domain.impl.UsuarioImpl;
import pe.cosapi.system.Key4Digits;

public class ServletPrestamoMultired extends HttpServlet implements Servlet {
	private static LoggerSati log3 = LoggerSati.getInstance(ServletPrestamoMultired.class.getName());
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ServletPrestamoMultired() {
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
		String accion = req.getParameter("accion");
		
		if ("listar".equals(accion)){
		    listarPrestamos(req, resp);
		} else if ("ver".equals(accion)) {
		    verDetallePrestamo(req, resp);
		} else {
			req.setAttribute("error","Accion no indicada.");
			getServletContext().getRequestDispatcher("/WEB-INF/wap/bnError.jsp").forward(req,resp);
			return;    
		}
	}

	public void listarPrestamos(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		String codCompany = req.getParameter("cod");
		ConstanteSesion.CODIGO_COMPANIA="3";
		if (codCompany == null || codCompany.equals("null"))
		    codCompany = ConstanteSesion.CODIGO_COMPANIA;
		
		int error = 0;
		String mensajeError = null;

		String tarjeta = req.getParameter("cboTarjeta");
		String numero = req.getParameter("txtNumero");
		String password = req.getParameter("txtPassword");
		String cvv2 = req.getParameter("txtCVV2");
		
		/************************/
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

		req.setAttribute("fecha",ObjectUtil.getFechaActual());
		try{
		 
			Usuario usuario = FacadeFactory.getLoginFacade().autenticar(transaccion,codCompany,valores,ctas, "", "");
			usuario.setTipoPersona(codCompany);
			
			List cuentasPrestamos = new ArrayList();
			List cuentas = (ArrayList)usuario.getCuentas();
			CuentaImpl cta = null;
			for (int i = 0; cuentas!=null && i < cuentas.size(); i++) {
                cta = (CuentaImpl)cuentas.get(i);
                if (cta!=null && cta.getEsCuentaPrestamo()){
                    cuentasPrestamos.add(cta);
                }
            }
			req.setAttribute(ConstanteSesion.USUARIO_EN_SESION, usuario);
			req.setAttribute(ConstanteSesion.CONSULTA, cuentasPrestamos);
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
		getServletContext().getRequestDispatcher("/WEB-INF/wap/bnPresMulLista.jsp").forward(req,resp);

	}
	
	public void verDetallePrestamo(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		String numeroCuenta ="";
		String codConsulta ="";
		String codMoneda ="";
		String numeroPrestamo="";
		String usuTipoTarjeta = "";
		String usuTarjeta = "";

		codConsulta  = req.getParameter("codConsulta");
		numeroCuenta = req.getParameter("numCuenta");
		codMoneda 	 = req.getParameter("moneda");
		numeroPrestamo = req.getParameter("numDesembolso");
		usuTipoTarjeta = req.getParameter("usuTipoTarjeta");
		usuTarjeta = req.getParameter("usuTarjeta");

		String codCompany = req.getParameter("cod");
		ConstanteSesion.CODIGO_COMPANIA="3";
		if (codCompany == null || codCompany.equals("null"))
		    codCompany = ConstanteSesion.CODIGO_COMPANIA;

		req.setAttribute("fecha",ObjectUtil.getFechaActual());
		try{	
			Usuario usuario = new UsuarioImpl();
			usuario.setTipoTarjeta(usuTipoTarjeta);
			Tarjeta tarjeta = new TarjetaImpl();
			tarjeta.setNumero(usuTarjeta);
			usuario.setTarjeta(tarjeta);
			usuario.setTipoPersona(codCompany);

			req.setAttribute(ConstanteSesion.USUARIO_EN_SESION, usuario);

			
		    Object obj = null;
		    obj=FacadeFactory.getConsultaFacade().consultar(codConsulta,numeroCuenta,codMoneda,numeroPrestamo, req.getRemoteAddr(),usuario,req);
			req.setAttribute(ConstanteSesion.CONSULTA,getPrestamo(obj)); 
		}
		catch(Exception e){
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			String mensaje = ObjectUtil.getMensajeErrorWAP(e);
			e.printStackTrace();
			req.setAttribute("error",mensaje);
			getServletContext().getRequestDispatcher("/WEB-INF/wap/bnError.jsp").forward(req,resp);
			return;
		}
		getServletContext().getRequestDispatcher("/WEB-INF/wap/bnRespuestaMultired.jsp").forward(req,resp);
	}

	//01,02,03,06,07,12,13
	private Cuenta getCuenta(Object obj) throws Exception{
	    Cuenta objCuenta =  (CuentaImpl)obj;
	    return objCuenta;
	}
	
	//04,08
	private Itf getItf(Object obj) throws Exception{
	    Itf objItf =  (ItfImpl)obj;
	    return objItf;
	}
	
	//09,10
	private Prestamo getPrestamo(Object obj) throws Exception{
	    Prestamo objPrestamo =  (PrestamoImpl)obj;
	    return objPrestamo;
	}
}