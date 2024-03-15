package pe.bn.tarjeta.action;

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
import pe.bn.afiliacion.domain.Afiliacion;
import pe.bn.afiliacion.domain.impl.AfiliacionImpl;
import pe.bn.cldinamica.action.SolicitarServiciosTDC;
import pe.bn.cldinamica.action.form.ElemenSeguridad;
import pe.bn.cldinamica.action.form.MensajesTDC;
import pe.bn.cldinamica.action.form.ParametrosElemSegTDC;
import pe.bn.cldinamica.action.form.ParametrosTDC;
import pe.bn.listener.Util;
import pe.bn.login.action.form.LoginForm;
import pe.bn.notificaciones.action.ServicioNotificacionConstancia;
import pe.bn.tarjeta.domain.Tarjeta;
import pe.bn.tarjeta.domain.impl.TarjetaImpl;
import pe.bn.transferencia.domain.impl.TransferenciaImpl;
import pe.com.bn.common.Funciones;
import pe.com.bn.common.sati.bean.TokenSmsRequest;
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

public class CambiarClaveSESAction extends GrandActionAbstract{
	private static LoggerSati log3 = LoggerSati.getInstance(CambiarClaveSESAction.class.getName());

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
		
		//request.getSession().removeAttribute(ConstanteSesion.MAP_VALUES);
		//request.getSession().removeAttribute(ConstanteSesion.USUARIO_EN_SESION);
		ParametrosElemSegTDC elemento = null;
		
		
		elemento = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
		
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		
		if(!elemento.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_AFILIADO_DESAFILIADO)){
			BNAplicacion aplicacion = BNAplicacion.getInstance();
			
			request.getSession().setAttribute("mensajecambiopie",((Vector)aplicacion.getMensajePorCodigo("GC02","00002")).elementAt(2).toString());
			request.getSession().setAttribute("mensajecambiocabecera",((Vector)aplicacion.getMensajePorCodigo("GC02","00003")).elementAt(2).toString());
			request.getSession().setAttribute("mensajecambioexito",((Vector)aplicacion.getMensajePorCodigo("GC02","00004")).elementAt(2).toString());
			request.getSession().setAttribute("mensajecambioatte",((Vector)aplicacion.getMensajePorCodigo("GC02","00005")).elementAt(2).toString());
			request.getSession().setAttribute("mensajecambioInf",((Vector)aplicacion.getMensajePorCodigo("GC02","00006")).elementAt(2).toString());
			
			ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
			request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
			ParametrosElemSegTDC elementos = null;
			
			try{
				elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
				
				if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS) ||
				   elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
					elementos = SolicitarServiciosTDC.verificarTipoElemento(param,usuario);
				}
			}catch(ArrayRuleException ar){
				log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
				ar.printStackTrace();
				ar.setForward("verificarCelular");
				throw ar;
			}
			
			return mapping.findForward("iniciarCambiarSESClaveInternet");//generarCambioSES
			
			
		}
		
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
		return mapping.findForward("iniciaCambioSES");
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
		
		//req.getSession().setAttribute(ConstanteSesion.USUARIO_EN_SESION, new UsuarioImpl());		
		
		valores.addElement(ObjectUtil.getVectorComponent("transaccion",req.getParameter("transaccion")));
		
		valores.addElement(ObjectUtil.getVectorComponent("txtNumeroTarjeta",loginForm.getTxtNumeroTarjeta()+""));
		
		loginForm.setTxtPassword(getClaveDesencriptadaAutentica(loginForm.getTxtPassword(),req));
		
	
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
			
			
			//Tarjeta
						
			tarjeta.setClave(loginForm.getTxtPassword());
			
			usuario.setTarjeta(tarjeta);
			
		} catch (ArrayRuleException e)  {
			e.printStackTrace();
			e.setForward("iniciaCambioSES");
			throw e;
		}

		BNAplicacion aplicacion = BNAplicacion.getInstance();
		req.getSession().setAttribute("mensajecambiopie",((Vector)aplicacion.getMensajePorCodigo("GC02","00002")).elementAt(2).toString());
		req.getSession().setAttribute("mensajecambiocabecera",((Vector)aplicacion.getMensajePorCodigo("GC02","00003")).elementAt(2).toString());
		req.getSession().setAttribute("mensajecambioexito",((Vector)aplicacion.getMensajePorCodigo("GC02","00004")).elementAt(2).toString());
		req.getSession().setAttribute("mensajecambioatte",((Vector)aplicacion.getMensajePorCodigo("GC02","00005")).elementAt(2).toString());
		req.getSession().setAttribute("mensajecambioInf",((Vector)aplicacion.getMensajePorCodigo("GC02","00006")).elementAt(2).toString());
	
		ParametrosTDC param = (ParametrosTDC)req.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		req.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		ParametrosElemSegTDC elementos = null;
		
		try{
			elementos = (ParametrosElemSegTDC) req.getSession().getAttribute("tipoElemento");
			
			if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS) ||
			   elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
				elementos = SolicitarServiciosTDC.verificarTipoElemento(param,usuario);
			}
		}catch(ArrayRuleException ar){
			log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
			ar.printStackTrace();
			ar.setForward("iniciaCambioSES");
			throw ar;
		}
		
		
		
		//req.getSession().setAttribute(ConstanteSesion.USUARIO_EN_SESION,usuario);
		return mapping.findForward("iniciarCambiarSESClaveInternet");//generarCambioSES
	}
	
	
	
	
	
public ActionForward confirmarCambiarSESClaveInternet(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		TokenSmsRequest tokenSms = new TokenSmsRequest();
				
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		
		request.setAttribute("mensajePantalla",((Vector)aplicacion.getMensajePorCodigo("GC02","00001")).elementAt(2).toString());
		
		request.getSession().setAttribute("mensajeCabecera",((Vector) aplicacion.getMensajePorCodigo("DC00", "DC01")).elementAt(2).toString());
        request.getSession().setAttribute("mensajeYaActualizado",((Vector) aplicacion.getMensajePorCodigo("DC00", "DC02")).elementAt(2).toString());
       		
        /****/
		try{
			
			ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
			request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
									
			
			
			ParametrosElemSegTDC elementos = null;
			MensajesTDC resultado = new MensajesTDC();
			
			try {

				elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");


			} catch (ArrayRuleException ar) {
				log3.error(ar, Constante.ERR_SOLICITAR_COORDENADA, "");
				ar.printStackTrace();
				ar.setForward("confirmarAfiliacion");
				throw ar;
			}
				
			
			System.out.println("elementos.getTipoElementoSeguridad()::"+elementos.getTipoElementoSeguridad());
			
//			CODIGO_TIPO_ELEM_TOKENS_AFILIACION = 0
//			CODIGO_TIPO_ELEM_TOKENS_AFILIADO_DESAFILIADO = 99
			
			if(elementos.getTipoElementoSeguridad()== Constante.CODIGO_TIPO_ELEM_TOKENS_AFILIADO_DESAFILIADO || 
					elementos.getTipoElementoSeguridad()==Constante.CODIGO_TIPO_ELEM_TOKENS_AFILIACION){
				System.out.println("es 99 o 0");
			}else{
				
				String pinblock = ObjectUtil.getClaveDesencriptadaCDD(request.getParameter("txtCoordenada"), request);
				ElemenSeguridad resultCoord = null;
				ElemenSeguridad coord = (ElemenSeguridad) request.getSession().getAttribute("resultCoord");
				
				
				if (elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)) {
					resultado = SolicitarServiciosTDC.validarTDC(param, coord,pinblock);
				} else if (elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS)) {
					resultado = SolicitarServiciosTDC.validarTokens(param,coord, pinblock);
				} else if (elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {
					String tipoDoc="";
					String numDoc="";
					
					if(usuario.getCodigoCLDI() != null){
						tipoDoc=usuario.getCodigoCLDI().substring(12,13);
						numDoc=usuario.getCodigoCLDI().substring(13);	
						if(tipoDoc != "1"){
							numDoc=Util.removeZero(numDoc);
						}else{
							numDoc= numDoc.substring(4);
						}
					}
					
					tokenSms.setCodCliente(usuario.getCodigoCic());
					tokenSms.setTipoDocumento(Funciones.lpad(tipoDoc,"0",3));
					tokenSms.setNroDocumento(numDoc);
					tokenSms.setCodeSMS(pinblock);
					tokenSms.setTypeTrans(Constante.TIP_OPER_CAMBIO_CLAVE_INTERNET);
					tokenSms.setTypeOp(Constante.COD_TRANSACCION_ADMINISTRATIVA);						
					tokenSms.setMsgClient("Validacion de Clave Dinamica Digital");
					tokenSms.setTypeCurrency("PEN");
					
					resultado = UtilAction.validarClaveSms(tokenSms);
				}
				
				if (!resultado.getCodRptaPrincipal().equals(Constante.CODIGO_RESULTADO_OK) 
						|| !resultado.getCodResultOper().equals(Constante.CODIGO_OPERACION_OK)) {

					String codErrEnt = "";
					String mensaje = "";

					if (!resultado.getCodRptaPrincipal().equals(Constante.CODIGO_RESULTADO_OK)) {
						codErrEnt = ObjectUtil.setearCaracterLeft(resultado.getCodRptaPrincipal().toString(), '0', 4 - resultado.getCodRptaPrincipal().toString().length());
						
						if (elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {
							codErrEnt = resultado.getCodResult();
							boolean existe = UtilAction.validarExisteCodigoErrorMs(codErrEnt);
							
							if(existe==true){
								mensaje = aplicacion.getMsjesHost("CS", codErrEnt).elementAt(2).toString();
								throw new ArrayRuleException(ConstanteError.GENERICO,mensaje + " (CS-" + codErrEnt + ")");
							}else {							
								mensaje = resultado.getMsg();
							}
						} else {
							mensaje = aplicacion.getMsjesHost("CD", codErrEnt).elementAt(2).toString();
						}

					} else {
						if (!resultado.getCodResultOper().equals(Constante.CODIGO_OPERACION_OK)) {
							codErrEnt = ObjectUtil.setearCaracterLeft(resultado.getCodRptaPrincipalOper().toString(), '0',4 - resultado.getCodRptaPrincipalOper().toString().length());
							mensaje = aplicacion.getMsjesHost("CD", codErrEnt).elementAt(2).toString();
						}
					}

					throw new ArrayRuleException(ConstanteError.GENERICO,mensaje + " (CD-" + codErrEnt + ")");

				}
			}
			
			
					
			String claveTarjeta		= request.getParameter("txtClaveTarjeta"); 
			String claveInternet	= request.getParameter("txtClaveInternet");
			String claveInternet_	= request.getParameter("txtClaveInternet_");
			String transaccion		= request.getParameter("transaccion");
			//if(Constante.VER_LOG)System.out.println("claveTarjeta:"+claveTarjeta);System.out.println("claveInternet:"+claveInternet);
			//if(Constante.VER_LOG)System.out.println("claveInternet_:"+claveInternet_);
			
			String dencriptClaveTarjeta 	= getClaveDesencriptada(claveTarjeta,request);
			String dencriptclaveInternet 	= getClaveDesencriptada(claveInternet,request);
			String dencriptclaveInternet_ 	= getClaveDesencriptada(claveInternet_,request);
			
			//if(Constante.VER_LOG)System.out.println("dencriptClaveTarjeta:"+dencriptClaveTarjeta);
			//if(Constante.VER_LOG)System.out.println("dencriptclaveInternet:"+dencriptclaveInternet);
			//if(Constante.VER_LOG)System.out.println("dencriptclaveInternet_:"+dencriptclaveInternet_);
			
			// Para poder cargar los datos de la mensajistica
			
			//MGL2612
			//FacadeFactory.getGeneralFacade().processValidationCardAhorro(usuario.getTarjeta().getNumero(),usuario.getTarjeta().getClave(),dencriptClaveTarjeta,dencriptClaveTarjeta);
			//NUEVO CODIGO - VALIDAR COORDENADA ------------------------
			Tarjeta tarjeta= FacadeFactory.getTarjetaFacade().getCambiarClaveInterna(ConstanteSesion.CODIGO_TRANSACCION_CAMBIO_CLAVE_INTERNO,usuario, dencriptClaveTarjeta, dencriptclaveInternet,  request);
						
			Map map = UtilAction.cargarVar(request,request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION));
			OperacionImpl.setVariables(map);
			request.getSession().setAttribute(ConstanteSesion.TARJETA,tarjeta);
			
			ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_SEGURIDAD_CLAVE_INTERNET_CAMBIO, ConstanteSesion.TARJETA, request);
		}
		catch (ArrayRuleException e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			e.printStackTrace();
			e.setForward("iniciarCambiarSESClaveInternet");
			throw e;
		}

		return mapping.findForward("confirmarCambiarSESClaveInternet");
	}
	
	
	
	
	
	
	
	
	
	
	
	private String  getClaveDesencriptadaAutentica(String passEncript, HttpServletRequest request){
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
	
	
	private String  getClaveDesencriptada(String passEncript, HttpServletRequest request){
		GeneratorKeys gen = new GeneratorKeys();
		Map mapa = (Map) SeguridadUtil.getObjectSession(ConstanteSesion.MAP_VALUES,request);
		//if(Constante.VER_LOG)System.out.println("request.getParameter('hdnValue')="+request.getParameter("hdnValue"));
		String  valorClave = gen.getClave(mapa,request.getParameter("hdnValue"),passEncript);
		return valorClave;
	}
	
	
	public ActionForward generarClaveSms(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		String monto 			= (String)request.getSession().getAttribute("monto");
		//Afiliacion datosUsuario  = FacadeFactory.getGeneralFacade().obtenerDatosPersonalesAfiliacion(usuario);
		Afiliacion afiliacion = (AfiliacionImpl)request.getSession().getAttribute("afiliacion");
		
		TransferenciaImpl transferencia = (TransferenciaImpl)request.getSession().getAttribute(ConstanteSesion.TRANSFERENCIA);
		
		try{
			ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
			ParametrosElemSegTDC elementos =  (ParametrosElemSegTDC )request.getSession().getAttribute("tipoElemento"); 
			
			request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
			request.getSession().setAttribute("tipoElemento",elementos );
			
			String tipoDoc="";
			String numDoc="";
			
			
			if(usuario.getCodigoCLDI() != null){
				tipoDoc=usuario.getCodigoCLDI().substring(12,13);
				numDoc=usuario.getCodigoCLDI().substring(13);	
				if(tipoDoc != "1"){
					numDoc=Util.removeZero(numDoc);
				}else{
					numDoc= numDoc.substring(4);
				}
				
			
			}
						
			if(param!= null){
				if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {
					
					TokenSmsRequest tokenSms = new TokenSmsRequest();
					
					tokenSms.setCodCliente(usuario.getCodigoCic());
					tokenSms.setTipoDocumento(Funciones.lpad(tipoDoc,"0",3));
					tokenSms.setNroDocumento(numDoc);	
					tokenSms.setTypeTrans(Constante.TIP_OPER_CAMBIO_CLAVE_INTERNET);
					tokenSms.setTypeOp(Constante.COD_TRANSACCION_ADMINISTRATIVA);
					tokenSms.setTypeCurrency("PEN");
			
					MensajesTDC resultado = UtilAction.generarClaveSms(tokenSms);
					
					
				}
				
			} else {
				throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
			}
		
		}catch (ArrayRuleException e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			response.setStatus(404);			
			loadMessage(request);
			e.setForward("iniciarCambiarSESClaveInternet");
			throw e;
		}
		return mapping.findForward("confirmarCambiarSESClaveInternet");
		
	}
	
	private void loadMessage(HttpServletRequest request) throws Exception{
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		request.getSession().setAttribute("mensajeAfiliacion",((Vector)aplicacion.getMensajePorCodigo("TB01","AF001")).elementAt(2).toString());
		request.getSession().setAttribute("mensajePantalla",((Vector)aplicacion.getMensajePorCodigo("TB01","00001")).elementAt(2).toString());
		request.getSession().setAttribute("mensajeExitoTranfMB",((Vector)aplicacion.getMensajePorCodigo("TB01","00005")).elementAt(2).toString());
	}
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void enviarMailAutomatico( HttpServletRequest request, HttpServletResponse response) throws Exception {
	
	
	String idObjeto = "mailAutoExpiraClaveInternet";
	String variableSesion = ConstanteSesion.TARJETA;
	
	Mail mail = MailFactory.getMailInstance(idObjeto);		
	Usuario usuario = (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
	Object objeto = request.getSession().getAttribute(variableSesion);	
	
	
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
		
	}
	}
}