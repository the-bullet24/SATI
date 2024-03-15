package pe.bn.login.action;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.novatronic.captcha.Captcha;

import pe.bn.afiliacion.action.DesafCableAction;
import pe.bn.login.action.form.LoginForm;
import pe.bn.tarjeta.domain.Tarjeta;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.action.GrandActionAbstract;
import pe.cosapi.common.ArrayRuleException;
import pe.cosapi.common.BNAplicacion;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteError;
import pe.cosapi.common.ConstanteSesion;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.common.SeguridadUtil;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.UsuarioImpl;
import pe.cosapi.system.GeneratorKeys;
import pe.cosapi.system.Key4Digits;
import pe.cosapi.system.ParserXML;

public class LoginBloqueoAction extends GrandActionAbstract{
	private static LoggerSati log3 = LoggerSati.getInstance(LoginBloqueoAction.class.getName());

	public ActionForward ayuda(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	    Constante.FLAG_CACHE = "0";
		BNAplicacion apli = BNAplicacion.getInstance();
		request.setAttribute("mensajeAyuda",((Vector)apli.getMensajePorCodigo("LG01","00002")).elementAt(2).toString());
		return mapping.findForward("ayuda");
	}
	public ActionForward ayudaCvv2(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	    Constante.FLAG_CACHE = "0";
		BNAplicacion apli = BNAplicacion.getInstance();
		request.setAttribute("mensajeCvv2",((Vector)apli.getMensajePorCodigo("LG01","00001")).elementAt(2).toString());
		return mapping.findForward("cvv2");
	}
	public ActionForward salir(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	    Constante.FLAG_CACHE = "0";
		if(request.getSession().getAttribute(ConstanteSesion.MAP_VALUES)==null){
			//if(Constante.VER_LOG)System.out.println("No hay usuario en sesion");
			request.getSession().setAttribute(ConstanteSesion.CARPETA,Constante.COD_PERSON_NAT);
			return mapping.findForward("salirExito");
		}
		
		request.getSession().setAttribute(ConstanteSesion.CARPETA,Constante.COD_PERSON_NAT);
		return mapping.findForward("salirExito");
	}
	
	public ActionForward iniciar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getSession().removeAttribute(ConstanteSesion.MAP_VALUES);
		
		
		Constante.FLAG_CACHE = "0";
		//-- Se asigna el tipo de canal de ingreso
		
		ConstanteSesion.CODIGO_COMPANIA="1";
		String codCompany = request.getParameter("cod");
		if (codCompany != null){
		    ConstanteSesion.CODIGO_COMPANIA = codCompany.trim(); 
		}
		
		
		GeneratorKeys gen = new GeneratorKeys();
		request.getSession().setAttribute(ConstanteSesion.MAP_VALUES,gen.generateMap());
		return mapping.findForward("iniciar");
	}
	public ActionForward autenticar(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
	    Constante.FLAG_CACHE = "0";
	    
		if(req.getSession().getAttribute(ConstanteSesion.MAP_VALUES)==null){
			HttpSession session = req.getSession();
			session.setMaxInactiveInterval(1);
			session.invalidate();
			req.getSession(true).setAttribute(ConstanteSesion.CARPETA,Constante.COD_PERSON_NAT);
			return mapping.findForward("sesion.expirada");
		}
		
		String cNroDni = req.getParameter("txtNumero");
		String cTxtPassword = req.getParameter("txtPassword");
		String cTxtPersona  = req.getParameter("cboTipoTarjeta");
		
		
		
		LoginForm loginForm = (LoginForm)form; 
   		loginForm.setCaptcha((req.getParameter("txtCaptcha")).toUpperCase());
	
   		Captcha captchaSession = (Captcha)req.getSession().getAttribute("captcha");
     	        
        String captchaValue = loginForm.getCaptcha();
            
		
		try {
			  
	        if(!captchaSession.isCorrect(captchaValue)){
	        	throw new ArrayRuleException(ConstanteError.GENERICO,"El texto ingresado es incorrecto.");
	        }
			
			
		} catch (ArrayRuleException e)  {
			e.setForward("iniciaBloqueo");
			throw e;
		}
		
		loginForm.setCboTipoTarjeta("02");
		loginForm.setCboTipoPersona(cTxtPersona);
		loginForm.setTxtNumeroTarjeta("");
		loginForm.setTxtCvv2("");
		loginForm.setTxtDNI(cNroDni);
		loginForm.setTxtPassword(cTxtPassword);
		String transaccion = "BL00";
		Vector valores = new Vector();
		req.getSession().setAttribute(ConstanteSesion.USUARIO_EN_SESION, new UsuarioImpl());		
		valores.addElement(ObjectUtil.getVectorComponent("transaccion","BL00"));
		valores.addElement(ObjectUtil.getVectorComponent("tipoDoc",cTxtPersona));
		if(loginForm.getCboTipoTarjeta().equals(Constante.TARJETA_VACIA)){
			valores.addElement(ObjectUtil.getVectorComponent("numDoc",loginForm.getTxtDNI()));
		}
		else{
			valores.addElement(ObjectUtil.getVectorComponent("numDoc",loginForm.getTxtDNI()));
		}
		 
		loginForm.setTxtPassword(getClaveDesencriptada(loginForm.getTxtPassword(),req));
				

		Key4Digits keyGen = new Key4Digits(); 
		String passwordEncrip="";
		try {
			passwordEncrip = keyGen.encriptar(loginForm.getTxtPassword());
		} catch (Exception e1) {
			log3.error(e1,Constante.ERR_LOGICA_NEGOCIO,"");
		    System.out.println("LOGIN SEG- Error al encriptar el password: keyGen.encriptar(loginForm.getTxtPassword()");
			e1.printStackTrace();
		}

		loginForm.setTxtPassword(passwordEncrip);
		
		
		valores.addElement(ObjectUtil.getVectorComponent("txtPassword",loginForm.getTxtPassword()));

		if(loginForm.getCboTipoPersona().equals(Constante.COD_PERSON_JUR)){		
			req.getSession().setAttribute(ConstanteSesion.CARPETA,Constante.COD_PERSON_JUR);
		}
		else{
			req.getSession().setAttribute(ConstanteSesion.CARPETA,Constante.COD_PERSON_NAT);
		}
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
		//*********************************************************************************
		//System.out.println("Inicio Autenticacion de usuario");
		Usuario usuario=null;
		
	    // Se consigue el valor de COMPAÑIA
		String codCompany = "1";
		if (codCompany == null){
		    codCompany = "1"; 
		}
		try {
			usuario = FacadeFactory.getLoginFacade().autenticarDni(transaccion,codCompany,valores);
			usuario.getTarjeta().setClave(loginForm.getTxtPassword());
			
		} catch (ArrayRuleException e)  {
			e.setForward("iniciaBloqueo");
			throw e;
		}
		
		
		req.getSession().setAttribute(ConstanteSesion.USUARIO_EN_SESION,usuario);
		return mapping.findForward("iniciaBloqueoDni");
	}
	
	private String  getClaveDesencriptada(String passEncript, HttpServletRequest request){
		String  valorClave="";
		try {
			GeneratorKeys gen = new GeneratorKeys();
			Map mapa = (Map) SeguridadUtil.getObjectSession(ConstanteSesion.MAP_VALUES,request);
			valorClave = gen.getClave(mapa,request.getParameter("hdnValue"),passEncript);
		} catch (RuntimeException e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
		    System.out.println("LOGIN SEG- error al Desencriptar la clave: "+e);
			e.printStackTrace();
		}
		return valorClave;
	}
	
	private Usuario processUser(Usuario usuario,HttpServletRequest request){
		try {
			GeneratorKeys gen = new GeneratorKeys();
			Tarjeta t =  usuario.getTarjeta();
			Map mapa = (Map) SeguridadUtil.getObjectSession(ConstanteSesion.MAP_VALUES,request);
			String  valorClave = gen.getClave(mapa,request.getParameter("hdnValue"),t.getClave());
			t.setClave(valorClave);
			usuario.setTarjeta(t);
		} catch (RuntimeException e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
		    System.out.println("LOGIN SEG- error en el metodo processUser(): "+e);
			e.printStackTrace();
		}
		return usuario;
	}	
}