package pe.bn.login.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.tempuri.GenerateRequest;
import org.tempuri.GenerateResponse;
import org.tempuri.PinblockServiceSoapProxy;

import com.novatronic.captcha.Captcha;

import pe.bn.afiliacion.action.DesafCableAction;
import pe.bn.login.action.form.LoginForm;
import pe.bn.tarjeta.domain.Tarjeta;
import pe.bn.tarjeta.domain.impl.TarjetaImpl;
import pe.com.bn.common.Funciones;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.action.GrandActionAbstract;
import pe.cosapi.action.UtilAction;
import pe.cosapi.action.form.MailForm;
import pe.cosapi.common.ArrayRuleException;
import pe.cosapi.common.BNAplicacion;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteError;
import pe.cosapi.common.ConstanteSesion;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.Mail;
import pe.cosapi.common.MailFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.common.SeguridadUtil;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.OperacionImpl;
import pe.cosapi.domain.impl.UsuarioImpl;
import pe.cosapi.system.GeneratorKeys;
import pe.cosapi.system.Key4Digits;
import pe.cosapi.system.ParserXML;

public class LoginAfiliacionInternetAction extends GrandActionAbstract{
	private static LoggerSati log3 = LoggerSati.getInstance(LoginAfiliacionInternetAction.class.getName());

	BNAplicacion bn = BNAplicacion.getInstance();
	
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
		request.getSession().removeAttribute(ConstanteSesion.USUARIO_EN_SESION);
		Constante.FLAG_CACHE = "0";
		//-- Se asigna el tipo de canal de ingreso
		
		ConstanteSesion.CODIGO_COMPANIA="1";
		String codCompany = request.getParameter("cod");
		if (codCompany != null){
		    ConstanteSesion.CODIGO_COMPANIA = codCompany.trim(); 
		}
		
		request.setAttribute("lstMes", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.COD_HLP_DET_MES));
		List lista= FacadeFactory.getGeneralFacade().getComboDetalleHlpOrden(Constante.COD_HLP_DET_DOCU_AFILIACION_INTERNET);
		lista.remove(0);
		request.getSession().setAttribute("lstDocumento",lista);
		
		GeneratorKeys gen = new GeneratorKeys();
		request.getSession().setAttribute(ConstanteSesion.MAP_VALUES,gen.generateMap());
		response.setHeader("Content-Security-Policy", "style-src 'self' https://ui-systems.net/");
		response.setHeader("Content-Security-Policy", "script-src 'self' https://uimarketpro.com/");
		response.setHeader("Content-Security-Policy", "img-src 'self' https://ui-systems.net/");
		return mapping.findForward("iniciaAfiliacion");
	}
	public ActionForward autenticar(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
	    Constante.FLAG_CACHE = "0";
	    
	    
	    req.setAttribute("lstMes", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.COD_HLP_DET_MES));
		List lista= FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_DET_DOCU_AFILIACION_INTERNET);
		lista.remove(0);
		req.getSession().setAttribute("lstDocumento",lista);
	
		if(req.getSession().getAttribute(ConstanteSesion.MAP_VALUES)==null){
			HttpSession session = req.getSession();
			session.setMaxInactiveInterval(1);
			session.invalidate();
			req.getSession(true).setAttribute(ConstanteSesion.CARPETA,Constante.COD_PERSON_NAT);
			return mapping.findForward("sesion.expirada");
		}
		
		LoginForm loginForm = (LoginForm)form; 
   		loginForm.setCaptcha((req.getParameter("txtCaptcha")).toUpperCase());
	
   		Captcha captchaSession = (Captcha)req.getSession().getAttribute("captcha");
     	        
        String captchaValue = loginForm.getCaptcha();
      
        
        if(!captchaSession.isCorrect(captchaValue)){
        	throw new ArrayRuleException(ConstanteError.GENERICO,"El texto ingresado es incorrecto.");
        }
				
		String transaccion = req.getParameter("transaccion");
		Vector valores = new Vector();
		req.getSession().setAttribute(ConstanteSesion.USUARIO_EN_SESION, new UsuarioImpl());		
		valores.addElement(ObjectUtil.getVectorComponent("transaccion",req.getParameter("transaccion")));
		
		valores.addElement(ObjectUtil.getVectorComponent("txtNumeroTarjeta",loginForm.getTxtNumeroTarjeta()+""));
		
		loginForm.setTxtPassword(getClaveDesencriptada(loginForm.getTxtPassword(),req));
		
	
		Key4Digits keyGen = new Key4Digits(); 
		String passwordEncrip="";
	
		try {
						
			passwordEncrip = keyGen.encriptar(loginForm.getTxtPassword());
			
			
		} catch (Exception e1) {
			  StackTraceElement[] stack = e1.getStackTrace();
			    String exception = "";
			    for (StackTraceElement s : stack) {
			        exception = exception + s.toString() + "\n\t\t";
			    }
			 
			log3.error(e1,Constante.ERR_ENCRIPTAR,"LOGIN SEG- Error al encriptar el password : "+exception);
			//System.out.println("LOGIN SEG- Error al encriptar el password: keyGen.encriptar(loginForm.getTxtPassword()");
			e1.printStackTrace();
		}
		//System.out.println("Fin Encripta clave de 4 digitos");
		//************************************FIN******************************************
		loginForm.setTxtPassword(passwordEncrip);
		valores.addElement(ObjectUtil.getVectorComponent("txtPassword",loginForm.getTxtPassword()));
		//**************************************************************************
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
		String codCompany = req.getParameter("cod");
		if (codCompany == null){
		    codCompany = "1"; 
		}
			
		String fecha = loginForm.getTxtAnio()+loginForm.getCmbMes()+Funciones.lpad(loginForm.getTxtDia(),"0",2);
		
		try {
			String cuenta = "";
			usuario = FacadeFactory.getLoginFacade().autenticarAfilInternet(transaccion,valores,ctas);
			//Setea Correo y Tarjeta
			usuario.setEmail(loginForm.getTxtEmail());
			Tarjeta tarjeta = new TarjetaImpl();
			tarjeta.setNumero(loginForm.getTxtNumeroTarjeta());
			usuario.setTarjeta(tarjeta);
			
			
			if(usuario.getFlagAfiliacion().equals(Constante.AFILIACION_INTERNET_AFILIADO)){
				throw new ArrayRuleException (ConstanteError.GENERICO,Constante.AFILIACION_INTERNET_YA_AFILIADO);	
			}
			
			
			List listaCuentas = usuario.getCuentas();
			for(int i=0;i<listaCuentas.size();i++){
				Cuenta cta= (Cuenta)listaCuentas.get(i);
			
				if(cta.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_MN)){
					cuenta = cta.getNumeroProducto();
					
				}
			}
			int tipoDoc;
			String numDoc="";
			
			tipoDoc=Integer.parseInt(loginForm.getCboTipoDoc());
			
			if(tipoDoc == Constante.AFILIACION_CODIGO_PASAPORTE || tipoDoc == Constante.AFILIACION_CODIGO_CARNE_EXT){
				numDoc=Funciones.lpad(loginForm.getTxtNumDoc().toUpperCase(), "0", 12);
			}else{
				numDoc=loginForm.getTxtNumDoc();
			}
			
			
			
			String nomUsuario = FacadeFactory.getLoginFacade().validar(Constante.AFILIACION_INTERNET_VAL_DAT_PERSONALES,cuenta,""+ tipoDoc,numDoc,fecha, usuario, req);
			usuario.setNumDocumento(numDoc);
			usuario.setTipoDocumento(""+tipoDoc);
			usuario.setNombre(nomUsuario.trim());
			usuario.setFechaNacimiento(fecha);
			usuario.setNombre(nomUsuario.trim());
			
			
			//Tarjeta
						
			tarjeta.setClave(loginForm.getTxtPassword());
			
			usuario.setTarjeta(tarjeta);
			
		} catch (ArrayRuleException e)  {
			e.setForward("iniciaAfiliacion");
			throw e;
		}

		
		req.getSession().setAttribute(ConstanteSesion.USUARIO_EN_SESION,usuario);
		return mapping.findForward("iniciaGeneracion");
	}
	
	
	public ActionForward generarClaveInternet(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Constante.FLAG_CACHE = "0";
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		
	
		Key4Digits keyGen = new Key4Digits(); 
		String passwordDesencrip="";
		String 	pinblock1="";	
		String 	pinblock2="";	
		String passwordEncripClave6="";
		
		try {
						
			passwordDesencrip = keyGen.desencriptar(usuario.getTarjeta().getClave());
			
			String claveInternet	= request.getParameter("txtClaveInternet");
			String claveInternet_	= request.getParameter("txtClaveInternet_");
			String transaccion		= Constante.AFILIACION_INTERNET_NUEVO;
					
			String dencriptclaveInternet 	= getClaveDesencriptada(claveInternet,request);
			String dencriptclaveInternet_ 	= getClaveDesencriptada(claveInternet_,request);
			
			if(!claveInternet.equals(claveInternet_)){
				//if(Constante.VER_LOG)logger.info("MENSAJEEEEEEEEE-->"+bn.getMensajePorCodigo("31","01525").elementAt(2).toString());
				throw new ArrayRuleException(ConstanteError.GENERICO,bn.getMensajePorCodigo("31","01525").elementAt(2).toString());
			}
			
			//Encriptar clave de Internet
			GenerateRequest requestPinblock = new GenerateRequest();
			GenerateResponse responsePinblock = new GenerateResponse();
			
			try {
				//INVOCACIÓN NUEVO PINBLOCK
				PinblockServiceSoapProxy proxy = new PinblockServiceSoapProxy();
						
				
				requestPinblock.setCardNumber(usuario.getTarjeta().getNumero());
				requestPinblock.setPinConfirm(dencriptclaveInternet);
				requestPinblock.setPinNew(dencriptclaveInternet_);
				
									
				responsePinblock=proxy.generate(requestPinblock);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
	
			pinblock1=responsePinblock.getPinEncryptBlock1();
			pinblock2=responsePinblock.getPinEncryptBlock2();
			
			passwordDesencrip = keyGen.desencriptar(usuario.getTarjeta().getClave());
			passwordEncripClave6 = keyGen.encriptar(passwordDesencrip.concat("000000000000"));
			
		
			Map map = UtilAction.cargarVar(request,request.getSession().getAttribute(ConstanteSesion.TARJETA));
			OperacionImpl.setVariables(map);
			//request.getSession().setAttribute(ConstanteSesion.TARJETA,FacadeFactory.getTarjetaFacade().generarClaveInternet(usuario,transaccion,passwordDesencrip,dencriptclaveInternet,dencriptclaveInternet_, request.getRemoteAddr(),request));
			request.getSession().setAttribute(ConstanteSesion.TARJETA,FacadeFactory.getTarjetaFacade().generarNuevaClaveInternet(usuario,transaccion,passwordEncripClave6,pinblock1,pinblock2, request.getRemoteAddr(),request));
			enviarMailAutomatico(request,response);
			
		} catch (ArrayRuleException e)  {
			e.setForward("iniciaGeneracion");
			throw e;
		}
		
		
		
		return mapping.findForward("confirmarClaveInternet");
	}
	
	
	private String  getClaveDesencriptada(String passEncript, HttpServletRequest request){
		String  valorClave="";
		try {
			GeneratorKeys gen = new GeneratorKeys();
			Map mapa = (Map) SeguridadUtil.getObjectSession(ConstanteSesion.MAP_VALUES,request);
			valorClave = gen.getClave(mapa,request.getParameter("hdnValue"),passEncript);
		} catch (RuntimeException e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
		    
			e.printStackTrace();
		}
		return valorClave;
	}
	

	
	public void enviarMailAutomatico( HttpServletRequest request, HttpServletResponse response) throws Exception {
	
	
	String idObjeto = "mailAutoAfiliacionclaveInternet";
	String variableSesion = ConstanteSesion.TARJETA;
	
	Mail mail = MailFactory.getMailInstance(idObjeto);		
	Usuario usuario = (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
	Object objeto = request.getSession().getAttribute(variableSesion);	
	
	//map.put("usuario",usuario);
	UtilAction util = new UtilAction();
	
	HashMap map = new HashMap(util.cargarVar(request,objeto));	
	
	
	
	MailForm mailForm = new MailForm();		
	
	mailForm.setTo(usuario.getEmail());
		
	String resultado = ObjectUtil.replaceIlegalCharacterToSendMail(mail.getCuerpo(map));
	int inicio = resultado.lastIndexOf("<BODY>")+6;
	resultado = resultado.substring(inicio);
	int fin = resultado.lastIndexOf("</BODY>");
	resultado = resultado.substring(0,fin);
		
	mailForm.setCc("");
	mailForm.setMailDetalle(map);
		


	try{
	   
	    ConstanteSesion.MAIL_DETALLE = map;
	   
	    request.getSession().setAttribute(ConstanteSesion.MAIL_CONSTANCIA, mailForm);
    
		mail.sendMailConstanciaAutomatico(map, request);
	}
	catch(Exception e){
		log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
		e.printStackTrace();
		//throw new ArrayRuleException(ConstanteError.GENERICO,"VERIFIQUE LA(S) DIRECCION(ES) DE CORREO INGRESADA(S) O INTENTE MAS TARDE");
		//throw new ArrayRuleException(ConstanteError.GENERICO,"Verifique la(s) direccion(es) de correo ingresada(s) o intente mas tarde");
	}
	}
}