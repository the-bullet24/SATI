package pe.bn.login.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.rpc.holders.StringHolder;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.simple.JSONObject;
import org.tempuri.GenerateRequest;
import org.tempuri.GenerateResponse;
import org.tempuri.PinblockServiceSoapProxy;

import pe.bn.afiliacion.action.form.AfiliacionDatosClientesForm;
import pe.bn.afiliacion.action.form.AfiliacionDatosContactoForm;
import pe.bn.afiliacion.action.form.AfiliacionDebitoAutomaticoForm;
import pe.bn.afiliacion.domain.Afiliacion;
import pe.bn.afiliacion.domain.impl.AfiliacionImpl;
import pe.bn.cldinamica.action.CargarParametrosTDC;
import pe.bn.cldinamica.action.SolicitarServiciosTDC;
import pe.bn.cldinamica.action.form.ElemenSeguridad;
import pe.bn.cldinamica.action.form.InvitacionesResponse;
import pe.bn.cldinamica.action.form.MensajesTDC;
import pe.bn.cldinamica.action.form.ParametrosElemSegTDC;
import pe.bn.cldinamica.action.form.ParametrosTDC;
import pe.bn.listener.ConstanteParametros;
import pe.bn.listener.Parametro;
import pe.bn.listener.Util;
import pe.bn.login.action.form.LoginForm;
import pe.bn.login.domain.impl.ImagenTarjetaImpl;
import pe.bn.login.domain.impl.IngresoTarjetaImpl;
import pe.bn.notificaciones.action.ServiciosNotificacionPrincipal;
import pe.bn.notificaciones.model.ParametrosSIAT;
import pe.bn.tarjeta.domain.Tarjeta;
import pe.bn.trasferenciacontacto.domain.TransferenciaContacto;
import pe.bn.trasferenciacontacto.domain.impl.TransferenciaContactoImpl;
import pe.com.bn.common.Funciones;
import pe.com.bn.common.LoadDataProperties;
import pe.com.bn.common.sati.bean.TokenSmsRequest;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.action.GrandActionAbstract;
import pe.cosapi.action.UtilAction;
import pe.cosapi.common.ArrayRuleException;
import pe.cosapi.common.BNAplicacion;
import pe.cosapi.common.ComboUtil;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteError;
import pe.cosapi.common.ConstanteSesion;
import pe.cosapi.common.DAOFactory;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.common.SeguridadUtil;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.UsuarioCLDI;
import pe.cosapi.domain.impl.CuentaImpl;
import pe.cosapi.domain.impl.DetalleAyudaDatosImpl;
import pe.cosapi.domain.impl.UsuarioCLDIImpl;
import pe.cosapi.domain.impl.UsuarioImpl;
import pe.cosapi.system.GeneratorKeys;
import pe.cosapi.system.Key4Digits;

import com.novatronic.captcha.Captcha;

public class LoginAction extends GrandActionAbstract{
	BNAplicacion aplicacion =BNAplicacion.getInstance();
	private static LoggerSati log3 = LoggerSati.getInstance(LoginAction.class.getName());
	private static final String SUCCESS = "success";
	
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
	
	public ActionForward expiro(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		
		HttpSession session = request.getSession(false);
		
		log3.error(null,Constante.ERR_ERROR_VALIDAR_LOGIN,"SESION EXPIRADA:"+" "+session);
		if(session!=null){		
			session.setMaxInactiveInterval(1);		
			session.invalidate();
		}
		System.out.println("LOGOUT - Redirige a la pagina de Confirmacion de cerrar sesion - MGL");
		log3.error(null,Constante.ERR_ERROR_VALIDAR_LOGIN,"SESION EXPIRADA: LOGOUT - Redirige a la pagina de Confirmacion de cerrar sesion");
		return mapping.findForward("expirosesion");
	}
	
	public ActionForward iniciar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
	
		request.getSession().removeAttribute(ConstanteSesion.MAP_VALUES);
		request.getSession().removeAttribute(ConstanteSesion.MAP_SELLO);
		request.getSession().removeAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		request.getSession().removeAttribute(ConstanteSesion.USUARIO_EN_SESION);
		request.getSession().removeAttribute(ConstanteSesion.CONSULTA);
		request.getSession().removeAttribute("txtImageSelloThumb");
		request.getSession().setAttribute(ConstanteSesion.MAP_VALUES, null);
		request.getSession().setAttribute(ConstanteSesion.MAP_SELLO, null);
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION, null);
		request.getSession().setAttribute(ConstanteSesion.USUARIO_EN_SESION, null);
		request.getSession().setAttribute(ConstanteSesion.USUARIO_EN_SESION_CLDI, null);
		request.getSession().setAttribute(ConstanteSesion.CONSULTA, null);
		request.getSession().setAttribute("txtImageSelloThumb", null);
		if(!SeguridadUtil.existeUsuarioSesion(request)) {
			//if(Constante.VER_LOG) System.out.println("No hay usuario en sesion");
			HttpSession session = request.getSession();
					
			long duracion = request.getSession().getLastAccessedTime()-request.getSession().getCreationTime();
			session.invalidate();
			
		}
		Constante.FLAG_CACHE = "0";
				
		//-- Se asigna el tipo de canal de ingreso
		
		ConstanteSesion.CODIGO_COMPANIA="1";
		String codCompany = request.getParameter("cod");
		if (codCompany != null){
		    ConstanteSesion.CODIGO_COMPANIA = codCompany.trim(); 
		}
		
		
		GeneratorKeys gen = new GeneratorKeys();
		request.getSession().setAttribute(ConstanteSesion.MAP_VALUES,gen.generateMap());
		request.getSession().setAttribute(ConstanteSesion.MAP_SELLO,gen.generateMapSello());
		//response.setHeader("Content-Security-Policy", "default-src 'self' srv1995.entelgystats.com ui-systems.net  uimarket.com");
		//response.setHeader("Content-Security-Policy-Report-Only", "default-src 'self'");
		response.setHeader("Content-Security-Policy", "style-src 'self' https://ui-systems.net/");
		response.setHeader("Content-Security-Policy", "script-src 'self' https://uimarketpro.com/");
		response.setHeader("Content-Security-Policy", "img-src 'self' https://ui-systems.net/");
		return mapping.findForward("iniciar");
	}
	public ActionForward autenticar(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) throws Exception {
	
		try {
		res.setHeader("X-Content-Type-Options", "nosniff");	
		res.setHeader("Strict-Transport-Security", "max-age=31536000;includeSubDomains");
		res.setHeader("Content-Security-Policy", "frame-ancestors 'self' https://bancaporinternet.bn.com.pe");
		req.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		Constante.FLAG_CACHE = "0";
		  
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
        	log3.error(null,Constante.ERR_ERROR_VALIDAR_LOGIN,"EL CAPTCHA ES INCORRECTO");
        	throw new ArrayRuleException(ConstanteError.GENERICO,"El texto ingresado es incorrecto.");
        }
        
        String tipoDoc = "";
        String numDoc="";
        String numDocForm="";
        
        tipoDoc= req.getParameter("cboTipoDoc");
    
        
        numDocForm= req.getParameter("txtNumDoc");
        numDoc= Util.lpad(numDocForm, "0", 12);
    
			
		String transaccion = "";//req.getParameter("transaccion");
		String tipoTarjeta = req.getParameter("cboTipoTarjeta");
		
	
		Vector valores = new Vector();
		if (tipoTarjeta.equals(Constante.TARJETA_VACIA)){
		
			transaccion="LG01";
		}else{
		
			transaccion="LG09";
		}
		
		valores.addElement(ObjectUtil.getVectorComponent("transaccion",transaccion));
		req.getSession().setAttribute(ConstanteSesion.USUARIO_EN_SESION, new UsuarioImpl());		
		
		req.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		valores.addElement(ObjectUtil.getVectorComponent("cboTipoPersona","01"));
		if(loginForm.getCboTipoTarjeta().equals(Constante.TARJETA_VACIA)){
			valores.addElement(ObjectUtil.getVectorComponent("cboTipoTarjeta",Constante.TARJETA_VACIA));
		}
		else{
			valores.addElement(ObjectUtil.getVectorComponent("cboTipoTarjeta",Constante.TARJETA_MULTIRED));
		}
		valores.addElement(ObjectUtil.getVectorComponent("txtNumeroTarjeta",loginForm.getTxtNumeroTarjeta()+loginForm.getTxtDNI()));
		
		
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
			 
			log3.error(e1,Constante.ERR_ERROR_VALIDAR_LOGIN,"LOGIN SEG- Error al encriptar el password : "+exception);
					
			e1.printStackTrace();
		}
		//System.out.println("Fin Encripta clave de 4 digitos");
		//************************************FIN******************************************
		loginForm.setTxtPassword(passwordEncrip);
		valores.addElement(ObjectUtil.getVectorComponent("txtPassword",loginForm.getTxtPassword()));
		//**************************************************************************
		//NUEVO LOGIN TIPO Y NUMERO DOCUMENTO
		valores.addElement(ObjectUtil.getVectorComponent("tipoDoc",tipoDoc));
		valores.addElement(ObjectUtil.getVectorComponent("numDoc",numDoc));
		valores.addElement(ObjectUtil.getVectorComponent("flagLogin","1"));
		//******************************************
		GenerateRequest requestPinblock = new GenerateRequest();
		GenerateResponse responsePinblock = new GenerateResponse();
		
		try {
			//INVOCACIÓN NUEVO PINBLOCK
			PinblockServiceSoapProxy proxy = new PinblockServiceSoapProxy();
					
			
			requestPinblock.setCardNumber(loginForm.getTxtNumeroTarjeta());
			requestPinblock.setPinConfirm(keyGen.desencriptar(loginForm.getTxtPassword()));
			requestPinblock.setPinNew(keyGen.desencriptar(loginForm.getTxtPassword()));
			
								
			responsePinblock=proxy.generate(requestPinblock);
		} catch (Exception e) {
			  StackTraceElement[] stack = e.getStackTrace();
			    String exception = "";
			    for (StackTraceElement s : stack) {
			        exception = exception + s.toString() + "\n\t\t";
			    }
			log3.error(e,Constante.ERR_ERROR_VALIDAR_LOGIN,"LOGIN SEG- Error al encriptar el password : "+exception);
			
			e.printStackTrace();
		}
		
						
		valores.addElement(ObjectUtil.getVectorComponent("pinblock1",responsePinblock.getPinEncryptBlock1()));
		valores.addElement(ObjectUtil.getVectorComponent("pinblock2",responsePinblock.getPinEncryptBlock2()));
				
		
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
	    // Se consigue el valor de COMPAÑIA
		String codCompany = req.getParameter("cod");
		if (codCompany == null){
		    codCompany = "1"; 
		}
		
		
		Usuario usuario = FacadeFactory.getLoginFacade().autenticar(transaccion,codCompany,valores,ctas, tipoDoc, numDocForm);
		//MGL-EXPIRA	
		//borrar despues todo el if
//		if(Constante.CONSTANTE_EXPIRO.equals("V")){
//			System.out.println("usuario.getNotificacionClave().getEstadoExpira()::::"+usuario.getNotificacionClave().getEstadoExpira());
//			req.getSession().setAttribute("estadoExpira",usuario.getNotificacionClave().getEstadoExpira());
//			
//			return mapping.findForward("alertaClaveVencida");	
//		}
		
//		if(Constante.CONSTANTE_POR_VENCER.equals("W")){
//			req.getSession().setAttribute("estadoExpira",Constante.CONSTANTE_POR_VENCER_WEB);
//			String cadena = "5";
//			
//			req.getSession().setAttribute("diasExpira",cadena.trim());
//			req.getSession().setAttribute("vPorPrimera","1");
//			
//			req.setAttribute("mensajeAlertaPorVencer1",((Vector)aplicacion.getMensajePorCodigo("BC01","00017")).elementAt(2).toString());
//			req.setAttribute("mensajeAlertaPorVencer2",((Vector)aplicacion.getMensajePorCodigo("BC01","00018")).elementAt(2).toString());
//
//			req.getSession().setAttribute("menAlerProxVencer1",((Vector)aplicacion.getMensajePorCodigo("BC01","00017")).elementAt(2).toString());
//			req.getSession().setAttribute("menAlerProxVencer2",((Vector)aplicacion.getMensajePorCodigo("BC01","00018")).elementAt(2).toString());
//			
//		}
		
		
		
	//descomentar	
		if(usuario.getNotificacionClave().getEstadoExpira().equals(Constante.CONSTANTE_EXPIRO)){//CONSTANTE_EXPIRO
			System.out.println("usuario.getNotificacionClave().getEstadoExpira()::::"+usuario.getNotificacionClave().getEstadoExpira());
			req.getSession().setAttribute("estadoExpira",usuario.getNotificacionClave().getEstadoExpira());
			
			req.setAttribute("mensajeAlertaVencida",((Vector)aplicacion.getMensajePorCodigo("BC01","00016")).elementAt(2).toString());

			
			return mapping.findForward("alertaClaveVencida");	
			
		}else if(usuario.getNotificacionClave().getEstadoExpira().equals(Constante.CONSTANTE_POR_VENCER)){//CONSTANTE_POR_VENCER
			System.out.println("usuario.getNotificacionClave().getEstadoExpira()::::"+usuario.getNotificacionClave().getEstadoExpira());
			req.getSession().setAttribute("estadoExpira",Constante.CONSTANTE_POR_VENCER_WEB);
			String cadena = usuario.getNotificacionClave().getDiasVencimiento().replaceAll("^0+", "");
			
			req.getSession().setAttribute("diasExpira",cadena.trim());
			req.getSession().setAttribute("vPorPrimera","1");
			
			req.setAttribute("mensajeAlertaPorVencer1",((Vector)aplicacion.getMensajePorCodigo("BC01","00017")).elementAt(2).toString());
			req.setAttribute("mensajeAlertaPorVencer2",((Vector)aplicacion.getMensajePorCodigo("BC01","00018")).elementAt(2).toString());

			req.getSession().setAttribute("menAlerProxVencer1",((Vector)aplicacion.getMensajePorCodigo("BC01","00017")).elementAt(2).toString());
			req.getSession().setAttribute("menAlerProxVencer2",((Vector)aplicacion.getMensajePorCodigo("BC01","00018")).elementAt(2).toString());
	
							
		}else{
			System.out.println("usuario.getNotificacionClave().getEstadoExpira()::::"+usuario.getNotificacionClave().getEstadoExpira());
			req.getSession().setAttribute("estadoExpira",Constante.CONSTANTE_POR_SESION_OK);
		}
			
		//Validar tipo y numero documento
		
		List lista = FacadeFactory.getGeneralFacade().getComboDetalleAyuda1(Constante.SEL_MONTO_CODIGO_BD);
		
		for(int z=0; z< lista.size();z++){
			DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
			dHelp_ = (DetalleAyudaDatosImpl)lista.get(z);
			if(StringUtils.isEmpty(usuario.getCodMontoLimite())){
				usuario.setMontoLimite(Constante.SEL_MONTO_VALOR_DEFECTO);
				break;
			}else if(usuario.getCodMontoLimite().equals(dHelp_.getCodigoDetalleAyuda()))
			{
				usuario.setMontoLimite(dHelp_.getDescripcionDetalle());
			}
		}
		
		List listaCuentas = usuario.getCuentas();
		for(int i=0;i<listaCuentas.size();i++){
			Cuenta cta= (Cuenta)listaCuentas.get(i);
		}

		//AQUI SE SETEA EL TIPO DE TARJETA A AA, SI ES TARJETA ADICIONAL 
		if (usuario.getTarjeta().isAsociado()){
		    usuario.setTipoTarjeta(loginForm.getCboTipoTarjeta());
		}else{
		    usuario.setTipoTarjeta("AA");//DESCOMENTAR ESTO PARA EL CMABIO DE TAR. ADICIONAL
		}
		
		
		//AQUI SE DETERMINA EL REDIRECCIONAMIENTO AL FRAME DE INICIO
		//DEPENDIENDO DE LA SELECCION DEL COMBO TIPO DE TARJETA(2: TARJETA_GLOBAL_DEBITO, )
		if(loginForm.getCboTipoTarjeta().equals(Constante.TARJETA_GLOBAL_DEBITO)){
			//Verifica fecha de ingreso - Agregado 08/04/2013 
			List listaIngreso = null;
			List listaIngresoUlt = null;
			List listaTarjeta = null;
			BNAplicacion aplicacion = BNAplicacion.getInstance();
			
			listaIngreso = FacadeFactory.getLoginFacade().getValidaExiste(loginForm.getTxtNumeroTarjeta());
			String ipIng = req.getRemoteAddr().toString();
			
			if(listaIngreso == null || listaIngreso.size() == 0){
				String cNumSecIng = FacadeFactory.getLoginFacade().getNextSec();
				FacadeFactory.getLoginFacade().getFechaIngreso(loginForm.getTxtNumeroTarjeta(),cNumSecIng,ipIng);
			}else{
				FacadeFactory.getLoginFacade().getUltimoIngresoFecha(loginForm.getTxtNumeroTarjeta(),ipIng);
			}
			
			//Actualiza la lista con la ultima fecha de ingreso
			listaIngresoUlt = FacadeFactory.getLoginFacade().getValidaExiste(loginForm.getTxtNumeroTarjeta());
			 if(listaIngresoUlt!=null){
				 IngresoTarjetaImpl ingresoMV = (IngresoTarjetaImpl) listaIngresoUlt.get(0);
				 usuario.setIngreso(ingresoMV);
			 }
		
			req.getSession().setAttribute("USUARIO_FECHA_INGRESO", "01");
			req.getSession().setAttribute("FLAG_CLIENTE_USUARIO", Constante.FLAG_MOSTRAR_TITULO_CLIENTE);
			req.getSession().setAttribute("msgSello1",((Vector)aplicacion.getMensajePorCodigo("LG01","SG001")).elementAt(2).toString());
			req.getSession().setAttribute("msgCorreo1",((Vector)aplicacion.getMensajePorCodigo("LG01","SG016")).elementAt(2).toString());
			req.getSession().setAttribute("txtNumTarVal", loginForm.getTxtNumeroTarjeta().trim());
			
			listaTarjeta = FacadeFactory.getLoginFacade().getValidaImagenMV(loginForm.getTxtNumeroTarjeta());
			if (listaTarjeta == null){
				return mapping.findForward("afiliaCategoria");
				 
			}else {
	            
				UsuarioCLDI usuarioCLDI = new UsuarioCLDIImpl();
				usuarioCLDI.setCodigoCLDI(usuario.getCodigoCLDI());
				usuarioCLDI.setCodigoCic(usuario.getCodigoCic());
				
				ImagenTarjetaImpl imagenMV = (ImagenTarjetaImpl) listaTarjeta.get(0);
				
				ParametrosTDC parametros = new ParametrosTDC();
				
				String cNumTarjeta = usuario.getCodigoCLDI();
				
				req.getSession().setAttribute(ConstanteSesion.USUARIO_EN_SESION_CLDI,usuarioCLDI);
				req.getSession().setAttribute("txtImageSelloThumb", "/imagenes/sello/thumb/"+imagenMV.getCodCat()+ "Tarjeta"+imagenMV.getNomValorImg().substring(6)+".png");
				try{
					/**** SETEO DE CODIGOS DE EQUIVALENCIAS PARA TYPETOKEN ***************************/
					ParametrosElemSegTDC elementos = setTipoElementoSeguridad(parametros, usuario, req);
					req.getSession().setAttribute("tipoElemento",elementos);

//					System.out.println("elementos getTipoElementoSeguridad::::" + elementos.getTipoElementoSeguridad());
					req.getSession().setAttribute(ConstanteSesion.USUARIO_EN_SESION,usuario);
					
					parametros = CargarParametrosTDC.mostrarParamentroTDC(cNumTarjeta);
					usuario.setClaveDinamica(parametros);																
					req.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,parametros);	
					
					
					
				}catch(ArrayRuleException ar){
						log3.error(ar,Constante.ERR_CARGA_PARAMETROS_TDC,"");
						ar.printStackTrace();
						ar.setForward("iniciar");
						throw ar;
				}
				
				
				/****SE OBTIENE DATOS DEL USUARIO PARA VALIDAR SU CORREO ELECTRONICO********************/
				Afiliacion datosUsuarioConsulta = FacadeFactory.getAfiliacionFacade().getConsultaDatosClienteLogin(Constante.DAT_CLIENTES_CONSULTA, req.getRemoteAddr(), usuario, req);
	            
	            req.getSession().setAttribute(ConstanteSesion.DATOS_CONSULTA_CLI, datosUsuarioConsulta);
				
	            if(StringUtils.isEmpty(datosUsuarioConsulta.getCorreoPersonal())){
	            	return mapping.findForward("afiliaCorreo");
	            }
	            
	            usuario.setEmail(datosUsuarioConsulta.getCorreoPersonal());
	            usuario.setCelular(datosUsuarioConsulta.getTelCelular());
	            if(datosUsuarioConsulta.getTelCelular()!= null &&  !datosUsuarioConsulta.getTelCelular().trim().equals("")){
	            	   usuario.setOpeCelular(usuario.getCelular().trim().substring(0, 1));
	            	   usuario.setCelularFormat(usuario.getCelular().trim().substring(1, 10));
	            }
	            
	            /****SE OBTIENE INDICADOR DE AFILIACION A CONTACTOS********************/
	            TransferenciaContacto responseConsulta = new TransferenciaContactoImpl();
	            responseConsulta=FacadeFactory.getTransferenciaContactoFacade().consultarContacto(usuario, req.getRemoteAddr());
	            
	            if(responseConsulta.getCodigoErr().equals(Constante.CONST_PROCESO_OK) && responseConsulta.getEstadoAfil().equals(Constante.CONSTANTE_AFIL_CONTACTO)){
	    			usuario.setIndTransfContacto("1");
	    		}else if(responseConsulta.getEstadoAfil().equals(Constante.CONSTANTE_SIN_AFIL_CONTACTO)){
	    			usuario.setIndTransfContacto("0");
	    		}
	    		else{
	    			usuario.setIndTransfContacto("0");	
	    		}
	            
	            req.getSession().setAttribute(ConstanteSesion.USUARIO_EN_SESION,usuario);
				
	         
				return mapping.findForward("exitoso");
			}
			
		}else {//TERMINA VALIDACION DE LA TARJETA GLOBAL DEBITO
			
			String numTar = "";
			if(loginForm.getCboTipoTarjeta().equals(Constante.TARJETA_MULTIRED)){
				numTar = loginForm.getTxtNumeroTarjeta().trim();
			}else{
				numTar = loginForm.getTxtDNI().trim();
			}
			
			//Verifica fecha de ingreso - Agregado 20/06/2013 
			List listaIngreso = null;
			List listaIngresoUlt = null;
			listaIngreso = FacadeFactory.getLoginFacade().getValidaExiste(numTar);
			String ipIng = req.getRemoteAddr().toString();
			
			if(listaIngreso == null || listaIngreso.size() == 0){
				String cNumSecIng = FacadeFactory.getLoginFacade().getNextSec();
				FacadeFactory.getLoginFacade().getFechaIngreso(numTar,cNumSecIng,ipIng);
			}else{
				FacadeFactory.getLoginFacade().getUltimoIngresoFecha(numTar,ipIng);
			}
			
			//Actualiza la lista con la ultima fecha de ingreso
			listaIngresoUlt = FacadeFactory.getLoginFacade().getValidaExiste(numTar);
			if(listaIngresoUlt!=null){
				IngresoTarjetaImpl ingresoMV = (IngresoTarjetaImpl) listaIngresoUlt.get(0);
				usuario.setIngreso(ingresoMV);
			}
			
			if(usuario.getTipoTarjeta().equals(Constante.TARJETA_VACIA) && usuario.getTipoPersona().equals(Constante.COD_PERSON_NAT)){
				req.getSession().setAttribute("FLAG_CLIENTE_USUARIO", Constante.FLAG_MOSTRAR_TITULO_USUARIO);
			}
			
			req.getSession().setAttribute("USUARIO_FECHA_INGRESO", "01");
			req.getSession().setAttribute(ConstanteSesion.USUARIO_EN_SESION,usuario);
			req.getSession().setAttribute("txtImageSelloThumb","");
			
			
			 
			return mapping.findForward("exitoso");
			}
		
		} catch (Exception e) {
			log3.error(e, Constante.ERR_ERROR_VALIDAR_LOGIN, "ERROR-LOGIN");
			throw e;
		}
	}
	
	public ActionForward actualizarCorreoCliente(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(":::::actualizarCorreoCliente::::::::::");
		
		//request.getSession().setAttribute("mensajeCabecera",((Vector) aplicacion.getMensajePorCodigo("DC00", "DC01")).elementAt(2).toString());
        //request.getSession().setAttribute("mensajeYaActualizado",((Vector) aplicacion.getMensajePorCodigo("DC00", "DC02")).elementAt(2).toString());
        String txtCorreo = request.getParameter("txtCorreo").toString();
        String cpersonal0 = txtCorreo.split(Constante.SEP_ALPHANUMERIC)[0].toString();
        String cpersonal1 = txtCorreo.split(Constante.SEP_ALPHANUMERIC)[1].toString();
        //String txtCorreoAdicional = request.getParameter("txtCorreoAdicional").toString();
        //String txtCorreoAdicional1 = request.getParameter("txtCorreoAdicional1").toString();        
        Usuario usuario = (Usuario) SeguridadUtil.getUsuarioSession(request);
        Tarjeta tarjeta = usuario.getTarjeta();
		Afiliacion datosUsuarioConsulta = (Afiliacion)request.getSession().getAttribute(ConstanteSesion.DATOS_CONSULTA_CLI);
		
//		System.out.println("bean0 antes de actualizar---------->" + datosUsuarioConsulta.getCuenta());
		AfiliacionDatosClientesForm frm = new AfiliacionDatosClientesForm();
		//Enviando datos al formulario
        frm.setCmbDepartamento(datosUsuarioConsulta.getCodDepartamento());
        frm.setCmbProvincia(datosUsuarioConsulta.getCodProvincia());
        frm.setCmbDistrito(datosUsuarioConsulta.getCodDistrito());
        frm.setCmbTipoVia(datosUsuarioConsulta.getTipoVia());
        frm.setTxtNombreVia(datosUsuarioConsulta.getNomVia1().toLowerCase() + "" + datosUsuarioConsulta.getNomVia2().toLowerCase());
        frm.setTxtNumeroVia(datosUsuarioConsulta.getNumero());
        frm.setTxtBloque(datosUsuarioConsulta.getBloque());
        frm.setTxtLote(datosUsuarioConsulta.getLote().toLowerCase());
        frm.setTxtInterior(datosUsuarioConsulta.getInterior());
        frm.setTxtManzana(datosUsuarioConsulta.getManzana());
        frm.setTxtPiso(datosUsuarioConsulta.getPiso());
        frm.setTxtReferencia(datosUsuarioConsulta.getReferencia().toLowerCase() + "" + datosUsuarioConsulta.getReferencia1().toLowerCase());
        frm.setTxtTelFijo(datosUsuarioConsulta.getTelFijo());
        frm.setTxtTelFijoLab(datosUsuarioConsulta.getTelFijoLab());
        frm.setTxtAnexo(datosUsuarioConsulta.getAnexo());
        frm.setCmbConsentimiento(datosUsuarioConsulta.getFlagConsentimiento());

        if (datosUsuarioConsulta.getTelCelular() != null) {
               int datosCelular = datosUsuarioConsulta.getTelCelular().trim().length();
              
               if (datosCelular > Constante.DAT_CLIENTES_LONG_CELULAR) {
                      frm.setCmbOperador(datosUsuarioConsulta.getTelCelular().substring(0, 1));
                      frm.setTxtCelular(datosUsuarioConsulta.getTelCelular().substring(1));

               } else {
                      frm.setCmbOperador(Constante.DAT_CLIENTES_OPERADOR_POR_DEFECTO);
                      frm.setTxtCelular(datosUsuarioConsulta.getTelCelular());
               }

        }

        frm.setTxtCorreo(datosUsuarioConsulta.getCorreoPersonal().toLowerCase());
        frm.setTxtAdicional(datosUsuarioConsulta.getCorreoAdicional().toLowerCase());
        frm.setCmbZonaTelFijo(datosUsuarioConsulta.getDiscadoTelFijo());
        frm.setCmbZonaTelFijoLab(datosUsuarioConsulta.getDiscadoTelFijoLab());
		
        //CARGAR BEAN afiliacion para actualizar CON: 
        //CODCLIENTE
        //DATOS DE CONSULTA DE CLIENTE, UTILIZADOS PARA CARGAR (AfiliacionDatosClientesForm)
        //TARJETA
        //USUARIO
        //REQUEST
        Afiliacion afiliacion = setearAfiliacion(datosUsuarioConsulta.getCodCliente(), frm, tarjeta, usuario, request);
        afiliacion.setCorreoPersonal(cpersonal0);
        afiliacion.setCorreoPersonal1(cpersonal1);
        List x = usuario.getCuentas();
        CuentaImpl cuenta1 = (CuentaImpl) x.get(0);
        afiliacion.setCuenta(cuenta1);
        
        List listOperador = new ArrayList();
        Afiliacion datosResult = new AfiliacionImpl();

        try {
           listOperador = FacadeFactory.getGeneralFacade().getComboDetalleAyuda1(Constante.DAT_CLIENTES_TIPO_OPERADOR);
           for (int i = 0; i < listOperador.size(); i++) {
	    	   DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
	    	   dHelp_ = (DetalleAyudaDatosImpl) listOperador.get(i);
	              if (afiliacion.getOperador().equals(dHelp_.getCodigoDetalleAyuda())) {
	                 if (afiliacion.getOperador().equals(Constante.DAT_CLIENTES_OPERADOR_POR_DEFECTO)) {
	                       afiliacion.setMostrarOperador(Constante.DAT_CLIENTES_SIN_DESCRIPCION);
	                       break;
	                 } else {
	                       afiliacion.setMostrarOperador(dHelp_.getDescripcionDetalle().toUpperCase());
	                 }
	              }
           }
           
           //SE ACTUALIZA DATOS DEL USUARIO EN BD
           datosResult = FacadeFactory.getAfiliacionFacade().getActualizaDatosCliente(
        		   Constante.DAT_CLIENTES_ACTUALIZACION,
        		   request.getRemoteAddr(), 
        		   afiliacion, 
        		   usuario, 
        		   request);
           
           System.out.println("bean1 despues de actualizar---------->" + datosUsuarioConsulta.getCuenta());
           
           /*
           datosResult.setFecha(pe.cosapi.common.ObjectUtil.getFechaActual());
           datosResult.setHora(pe.cosapi.common.ObjectUtil.getHHMMSSformateado());
           datosResult.setMostrarOperador(afiliacion.getMostrarOperador());
           datosResult.setEmail(txtCorreo);
           datosResult.setCorreo(txtCorreo);
           //datos.setCorreoAdicional(StringUtils.isEmpty(txtCorreoAdicional)?txtCorreoAdicional:Constante.DAT_CLIENTES_SIN_DESCRIPCION); //---
           //datos.setCorreoAdicional1(StringUtils.isEmpty(txtCorreoAdicional1)?txtCorreoAdicional1:Constante.DAT_CLIENTES_SIN_DESCRIPCION); //---
			 
		   request.getSession().setAttribute(ConstanteSesion.DATOS_CONSULTA_CLI, datosResult);
		   */
		} catch (ArrayRuleException e) {
		   e.printStackTrace();
		   log3.error(e, Constante.ERR_LOGICA_NEGOCIO, "");
		   e.setForward("iniciar");
		   throw e;
		}
		
		return mapping.findForward("exitoso");
	}
	
	private Afiliacion setearAfiliacion(String codCliente, AfiliacionDatosClientesForm frm, Tarjeta tarjeta, Usuario usuario, HttpServletRequest request) throws Exception {
	     Afiliacion afiliacion = new AfiliacionImpl();
	     try {
	
	            afiliacion.setCodCliente(codCliente);
	            afiliacion.setCodDepartamento(frm.getCmbDepartamento());
	            afiliacion.setCodProvincia(frm.getCmbProvincia());
	            afiliacion.setCodDistrito(frm.getCmbDistrito());
	            afiliacion.setTipoVia(frm.getCmbTipoVia());
	            if (frm.getTxtNombreVia().trim().length() > 30) {
                   int fin = frm.getTxtNombreVia().trim().length();
                   afiliacion.setNomVia1(frm.getTxtNombreVia().trim().substring(0, 30).toUpperCase());
                   afiliacion.setNomVia2(frm.getTxtNombreVia().trim().substring(30, fin).toUpperCase());
	            } else {
	                   afiliacion.setNomVia1(frm.getTxtNombreVia().trim().toUpperCase());
	                   afiliacion.setNomVia2("");
	            }
	
	            afiliacion.setNumero(frm.getTxtNumeroVia().trim());
	            afiliacion.setBloque(frm.getTxtBloque()== null?"":frm.getTxtBloque().trim().toUpperCase());
	            afiliacion.setLote(frm.getTxtLote()== null?"":frm.getTxtLote().trim().toUpperCase());
	            afiliacion.setInterior(frm.getTxtInterior()== null?"":frm.getTxtInterior().trim().toUpperCase());
	            afiliacion.setManzana(frm.getTxtManzana()== null?"":frm.getTxtManzana().trim().toUpperCase());
	            afiliacion.setPiso(frm.getTxtManzana()== null?"":frm.getTxtPiso().trim().toUpperCase());
	
	            if (frm.getTxtReferencia().trim().length() > 30) {
	                   int fin = frm.getTxtReferencia().trim().length();
	                   afiliacion.setReferencia(frm.getTxtReferencia().trim().substring(0, 30).toUpperCase());
	                   afiliacion.setReferencia1(frm.getTxtReferencia().trim().substring(30, fin).toUpperCase());
	            } else {
	                   afiliacion.setReferencia(frm.getTxtReferencia().trim().toUpperCase());
	                   afiliacion.setReferencia1("");
	            }
	
	            afiliacion.setTelFijo(frm.getCmbZonaTelFijo() + frm.getTxtTelFijo().trim());
	            afiliacion.setTelFijoLab(frm.getCmbZonaTelFijoLab() + frm.getTxtTelFijoLab().trim());
	            afiliacion.setAnexo(frm.getTxtAnexo().trim());
	            afiliacion.setTelCelular(frm.getTxtCelular().trim());
	            afiliacion.setOperador(frm.getCmbOperador().trim());
	            afiliacion.setFlagConsentimiento(frm.getCmbConsentimiento());
	
	            String correo = frm.getTxtCorreo().trim();
	            if (!correo.equals(null) && !correo.equals("")) {
	                   String[] varCorreo = correo.split(Constante.SEP_ALPHANUMERIC);
	                   afiliacion.setCorreoPersonal(varCorreo[0].toUpperCase());
	                   afiliacion.setCorreoPersonal1(varCorreo[1].toUpperCase());
	            }
	            String adicional = frm.getTxtAdicional().trim();
	
	            if (!adicional.equals(null) && !adicional.equals("")) {
	
	                   String[] varAdicional = adicional.split(Constante.SEP_ALPHANUMERIC);
	                   afiliacion.setCorreoAdicional(varAdicional[0].toUpperCase());
	                   afiliacion.setCorreoAdicional1(varAdicional[1].toUpperCase());
	
	            }
	
	     } catch (Exception e) {
            e.printStackTrace();
	     }
	
	     return afiliacion;
	}
	
	
	
	
	public ActionForward volverAfiliaCategoria(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		
		return mapping.findForward("afiliaCategoria");
	}
	
	public ActionForward volverAfiliaCategoriaPost(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		
		return mapping.findForward("afiliaCategoriaPost");
	}
	
	public ActionForward volverCambiaCategoriaPost(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		
		return mapping.findForward("cambiaCategoria");
	}
	
	
	
	public ActionForward muestraCategoria(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//request.getSession().removeAttribute(ConstanteSesion.MAP_VALUES);
		
		if(request.getSession().getAttribute(ConstanteSesion.MAP_VALUES)==null){
			//if(Constante.VER_LOG) System.out.println("No hay usuario en sesion");
			HttpSession session = request.getSession();
			//System.out.println("ID SESSION:"+session.getId());
			session.setMaxInactiveInterval(1);
			session.invalidate();
			request.getSession(true).setAttribute(ConstanteSesion.CARPETA,Constante.COD_PERSON_NAT);
			return mapping.findForward("sesion.expirada");
		}
		
		LoginForm loginForm = (LoginForm)form;
		String cCat = (String) request.getParameter("cboCategoria").trim();
		request.getSession().setAttribute("cboCategoria",cCat);
		
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		request.getSession().setAttribute("msgSello5",((Vector)aplicacion.getMensajePorCodigo("LG01","SG005")).elementAt(2).toString());
		
		if(request.getAttribute("listImagen")!=null){
			request.setAttribute("listImagen",null);
		
		}
		
		if (cCat.equals("01")){
		
			request.setAttribute("listImagen","/imagenes/sello/01");
		} else if (cCat.equals("02")){
			request.setAttribute("listImagen","/imagenes/sello/02");
		
		} else if (cCat.equals("03")){
			request.setAttribute("listImagen","/imagenes/sello/03");
		
		} else if (cCat.equals("04")){
			request.setAttribute("listImagen","/imagenes/sello/04");
		
		} else if (cCat.equals("05")){
			request.setAttribute("listImagen","/imagenes/sello/05");
		
		} else if (cCat.equals("06")){
			request.setAttribute("listImagen","/imagenes/sello/06");
		
		} else {
			request.setAttribute("listImagen","/imagenes/sello/07");
		
		}
		
			
		return mapping.findForward("listaImagen0");

	}
//	public ActionForward validaClaveSeis(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
//		String clave = SeguridadUtil.getClaveDesencriptada(request.getParameter("txtClaveInternet"),request);
//		
//		
//		try {
//			
//			FacadeFactory.getGeneralFacade().processValidationCardAhorro(usuario.getTarjeta().getNumero(),usuario.getTarjeta().getClave(),clave,clave);
//		
//		}
//		catch( ArrayRuleException ar)
//		
//		{
//			ar.setForward("clave6digitos");
//			ar.printStackTrace();
//			throw ar;
//			
//		}
//		List listaTarjeta = null;
//		LoginForm loginForm = (LoginForm)form; 
//		
//	
//		listaTarjeta = FacadeFactory.getLoginFacade().getValidaImagenMV(request.getSession().getAttribute("txtNumTarVal").toString().trim());
//		ImagenTarjetaImpl imagenMV = (ImagenTarjetaImpl) listaTarjeta.get(0);
//
//	 
//		request.getSession().setAttribute("txtImageSelloThumb", "/imagenes/sello/thumb/"+imagenMV.getCodCat()+ "Tarjeta"+imagenMV.getNomValorImg().substring(6)+".png");
//		
//	
//		ParametrosTDC parametros = new ParametrosTDC();
//		String cNumTarjeta = request.getSession().getAttribute("txtNumTarVal").toString().trim();
//		try{
//			parametros = CargarParametrosTDC.mostrarParamentroTDC(cNumTarjeta);
//			usuario.setClaveDinamica(parametros);
//			request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,parametros);
//			
//		}catch(ArrayRuleException ar){
//				log3.error(ar,Constante.ERR_CARGA_PARAMETROS_TDC,"");
//				ar.printStackTrace();
//				ar.setForward("iniciar");
//				throw ar;
//		}
//			
//		request.getSession().setAttribute(ConstanteSesion.USUARIO_EN_SESION,usuario);
//
//		return mapping.findForward("exitoso");
//	
//	}
	
	public ActionForward afiliaCorreo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//request.getSession().removeAttribute(ConstanteSesion.MAP_VALUES);
		if(request.getSession().getAttribute(ConstanteSesion.MAP_VALUES)==null){
			//if(Constante.VER_LOG) System.out.println("No hay usuario en sesion");
			HttpSession session = request.getSession();
			//System.out.println("ID SESSION:"+session.getId());
			session.setMaxInactiveInterval(1);
			session.invalidate();
			request.getSession(true).setAttribute(ConstanteSesion.CARPETA,Constante.COD_PERSON_NAT);
			return mapping.findForward("sesion.expirada");
		}
		
		
		//LoginForm loginForm = (LoginForm)form;
		String correo = (String) request.getParameter("txtCorreo").trim();
		request.getSession().setAttribute("correo",correo);
		
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		request.getSession().setAttribute("msgSello2",((Vector)aplicacion.getMensajePorCodigo("LG01","SG002")).elementAt(2).toString());
		
		
		String var= request.getParameter("var");
		String afiliaCategoria = "";
		
		if(var==null){
			var="2";
		}
		
		if(var.equals("1")){
			afiliaCategoria ="listaAfiliaPost";
		}else{
			afiliaCategoria ="listaAfilia0";
		}
		

		return mapping.findForward(afiliaCategoria);
	
		
	}
	
	public ActionForward afiliaCategoria(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//request.getSession().removeAttribute(ConstanteSesion.MAP_VALUES);
		if(request.getSession().getAttribute(ConstanteSesion.MAP_VALUES)==null){
			//if(Constante.VER_LOG) System.out.println("No hay usuario en sesion");
			HttpSession session = request.getSession();
			//System.out.println("ID SESSION:"+session.getId());
			session.setMaxInactiveInterval(1);
			session.invalidate();
			request.getSession(true).setAttribute(ConstanteSesion.CARPETA,Constante.COD_PERSON_NAT);
			return mapping.findForward("sesion.expirada");
		}
		
		
		LoginForm loginForm = (LoginForm)form;
		String cCat = (String) request.getParameter("cboCategoria").trim();
		request.getSession().setAttribute("cboCategoria",cCat);
		
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		request.getSession().setAttribute("msgSello2",((Vector)aplicacion.getMensajePorCodigo("LG01","SG002")).elementAt(2).toString());
		
		if (cCat.equals("01")){
			request.setAttribute("listCategoria","/imagenes/sello/01");
			//return mapping.findForward("listaAfilia1");
		} else if (cCat.equals("02")){
			request.setAttribute("listCategoria","/imagenes/sello/02");
			//return mapping.findForward("listaAfilia2");
		} else if (cCat.equals("03")){
			request.setAttribute("listCategoria","/imagenes/sello/03");
			//return mapping.findForward("listaAfilia3");
		} else if (cCat.equals("04")){
			request.setAttribute("listCategoria","/imagenes/sello/04");
			//return mapping.findForward("listaAfilia4");
		} else if (cCat.equals("05")){
			request.setAttribute("listCategoria","/imagenes/sello/05");
			//return mapping.findForward("listaAfilia5");
		} else if (cCat.equals("06")){
			request.setAttribute("listCategoria","/imagenes/sello/06");
			//return mapping.findForward("listaAfilia6");
		} else {
			request.setAttribute("listCategoria","/imagenes/sello/");
			//return mapping.findForward("listaAfilia7");
		}
		String var= request.getParameter("var");
		String afiliaCategoria = "";
		
		if(var==null){
			var="2";
		}
		
		if(var.equals("1")){
			afiliaCategoria ="listaAfiliaPost";
		}else{
			afiliaCategoria ="listaAfilia0";
		}
		

		return mapping.findForward(afiliaCategoria);
	
		
	}
	
	public ActionForward afiliaSello(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//request.getSession().removeAttribute(ConstanteSesion.MAP_VALUES);
		
		
		if(request.getSession().getAttribute(ConstanteSesion.MAP_VALUES)==null){
			//if(Constante.VER_LOG) System.out.println("No hay usuario en sesion");
			HttpSession session = request.getSession();
			//System.out.println("ID SESSION:"+session.getId());
			session.setMaxInactiveInterval(1);
			session.invalidate();
			request.getSession(true).setAttribute(ConstanteSesion.CARPETA,Constante.COD_PERSON_NAT);
			return mapping.findForward("sesion.expirada");
		}
		
		String cNumTarjeta = request.getSession().getAttribute("txtNumTarVal").toString().trim();
		String codCategoria = request.getSession().getAttribute("cboCategoria").toString().trim();
		
		String ip = request.getRemoteAddr().toString();
		
		
		String cVarImg = request.getParameter("selTarjeta").toString().trim();
		
		String cNumSec = FacadeFactory.getLoginFacade().getNextSec();
		
		cNumSec = ObjectUtil.setearCaracterLeft(cNumSec,'0',10-cNumSec.length());
				
		
		FacadeFactory.getLoginFacade().getAfiliaImagenMV(cNumTarjeta, "imgtmv"+cVarImg.trim(), cNumSec, codCategoria,ip,Constante.ESTADO_SELLO_SEGURIDAD_PRIMERA_VEZ);
		
		
		// -- LLamando a la Página de INICIO de MRV
		//request.getSession().removeAttribute(ConstanteSesion.MAP_VALUES);
		Constante.FLAG_CACHE = "0";
		//-- Se asigna el tipo de canal de ingreso
		
		ConstanteSesion.CODIGO_COMPANIA="1";
		String codCompany = request.getParameter("cod");
		if (codCompany != null){
		    ConstanteSesion.CODIGO_COMPANIA = codCompany.trim();
		}
		
		GeneratorKeys gen = new GeneratorKeys();
		request.getSession().setAttribute("numTarMV",cNumTarjeta.trim());
		request.getSession().setAttribute(ConstanteSesion.MAP_VALUES,gen.generateMap());
		//return mapping.findForward("iniciarMV");
	
		BNAplicacion aplicacion = BNAplicacion.getInstance();
	
		
		String var= request.getParameter("var");
		String afiliaSello = "";
		
		if(var==null){
			var="2";
		}
		
		if(var.equals("1")){
			afiliaSello ="confirmaAfiliacionPost";
			request.getSession().setAttribute("msgSello3",((Vector)aplicacion.getMensajePorCodigo("LG01","SG015")).elementAt(2).toString());
		}else{
			afiliaSello ="confirmaAfiliacion";
			request.getSession().setAttribute("msgSello3",((Vector)aplicacion.getMensajePorCodigo("LG01","SG015")).elementAt(2).toString());
		}
		
		request.getSession().setAttribute("txtImageSelloThumb", "/imagenes/sello/thumb/"+codCategoria+ "Tarjeta"+cVarImg.trim()+".png");
	
		
		return mapping.findForward(afiliaSello);
		
	}
	
	public ActionForward inicioCambiaCategoria(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//request.getSession().removeAttribute(ConstanteSesion.MAP_VALUES);
		
		ParametrosElemSegTDC elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento"); //5,6,7
		
		Usuario usuario = (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
        
		if(Constante.CODIGO_SI_PENDIENTE_ENROLAMIENTO.equals(elementos.getPendienteEnrolamiento()))			
		{
			return mapping.findForward("pendienteEnrolamiento");
		}
		
		if(elementos.getPermiteOperaciones().equals(Constante.CODIGO_NO_PERMITE_OPERACIONES))			
		{
			return mapping.findForward("noPermiteOperaciones");
		}
		
		
		
//		String cValorClave = FacadeFactory.getGeneralFacade().processValidationCardAhorroClave(usuario.getTarjeta().getNumero(),"9999","987654","987654");
//		
//		request.getSession().setAttribute("txtNumTarVal",usuario.getTarjeta().getNumero());
//		
//		if (cValorClave.equals("0")){
//			request.getSession().setAttribute("mensajeClaveDinamica", "Es necesario estar afiliado a la clave de 6 Dígitos...");
//			return mapping.findForward("sesion.sello");
//		}
		
		List listaTarjeta = null;
		listaTarjeta = FacadeFactory.getLoginFacade().getValidaImagenMV(usuario.getTarjeta().getNumero());
				
		if(listaTarjeta==null){
			request.getSession().setAttribute("mensajeClaveDinamica", "Es necesario que primero genere el Sello de Seguridad.");
			return mapping.findForward("sesion.sello");
		
		}
		if(request.getSession().getAttribute(ConstanteSesion.MAP_VALUES)==null){
			//if(Constante.VER_LOG) System.out.println("No hay usuario en sesion");
			HttpSession session = request.getSession();
			//System.out.println("ID SESSION:"+session.getId());
			session.setMaxInactiveInterval(1);
			session.invalidate();
			request.getSession(true).setAttribute(ConstanteSesion.CARPETA,Constante.COD_PERSON_NAT);
			return mapping.findForward("sesion.expirada");
		}
		 
		
		return mapping.findForward("cambiaCategoria");
	}
	
	public ActionForward inicioGeneraCategoria(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//request.getSession().removeAttribute(ConstanteSesion.MAP_VALUES);
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		
		ParametrosElemSegTDC elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento"); //5,6,7
        
		if(Constante.CODIGO_SI_PENDIENTE_ENROLAMIENTO.equals(elementos.getPendienteEnrolamiento()))			
		{
			return mapping.findForward("pendienteEnrolamiento");
		}
		
		if(elementos.getPermiteOperaciones().equals(Constante.CODIGO_NO_PERMITE_OPERACIONES))			
		{
			return mapping.findForward("noPermiteOperaciones");
		}
			//String cValorClave = FacadeFactory.getGeneralFacade().processValidationCardAhorroClave(usuario.getTarjeta().getNumero(),"9999","987654","987654");
			
			request.getSession().setAttribute("txtNumTarVal",usuario.getTarjeta().getNumero());
			
//		if (cValorClave.equals("0")){
//			request.getSession().setAttribute("mensajeClaveDinamica", "Es necesario estar afiliado a la clave de 6 Dígitos...");
//			return mapping.findForward("sesion.sello");
//		}
		
		
		List listaTarjeta = null;
		listaTarjeta = FacadeFactory.getLoginFacade().getValidaImagenMV(usuario.getTarjeta().getNumero());
				
		if(listaTarjeta!=null){
			request.getSession().setAttribute("mensajeClaveDinamica", "En este momento Ud. ya tiene generado un Sello de Seguridad");
			return mapping.findForward("sesion.sello");
		
		}
		
		if(request.getSession().getAttribute(ConstanteSesion.MAP_VALUES)==null){
			//if(Constante.VER_LOG) System.out.println("No hay usuario en sesion");
			HttpSession session = request.getSession();
			//System.out.println("ID SESSION:"+session.getId());
			session.setMaxInactiveInterval(1);
			session.invalidate();
			request.getSession(true).setAttribute(ConstanteSesion.CARPETA,Constante.COD_PERSON_NAT);
			return mapping.findForward("sesion.expirada");
		}
		
		return mapping.findForward("afiliaCategoriaPost");
	}
	
	public ActionForward cambiaCategoria(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//request.getSession().removeAttribute(ConstanteSesion.MAP_VALUES);
		
		Usuario usuario = (Usuario)SeguridadUtil.getUsuarioSession(request);
		
		if(request.getSession().getAttribute(ConstanteSesion.MAP_VALUES)==null){
			//if(Constante.VER_LOG) System.out.println("No hay usuario en sesion");
			HttpSession session = request.getSession();
			//System.out.println("ID SESSION:"+session.getId());
			session.setMaxInactiveInterval(1);
			session.invalidate();
			request.getSession(true).setAttribute(ConstanteSesion.CARPETA,Constante.COD_PERSON_NAT);
			return mapping.findForward("sesion.expirada");
		}
		
		LoginForm loginForm = (LoginForm)form;
		String cCat = (String) request.getParameter("cboCategoria").trim();
		request.getSession().setAttribute("cboCategoria",cCat);
		
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		request.getSession().setAttribute("msgSello2",((Vector)aplicacion.getMensajePorCodigo("LG01","SG002")).elementAt(2).toString());
		
		
		//REALIZAR CONSULTA DE COORDENADAS
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		ParametrosElemSegTDC elementos = null;
		ElemenSeguridad resultCoord = null;
		
		try{
			elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
			
			if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS) ||
			   elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
				elementos = SolicitarServiciosTDC.verificarTipoElemento(param,usuario);
			}
		}catch(ArrayRuleException ar){
			log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
			ar.printStackTrace();
			//ar.setForward("inicio");
			ar.setForward("cambiaCategoria");
			throw ar;
		}
		
		if(param!= null){
				resultCoord = new ElemenSeguridad();

				try{
					request.getSession().setAttribute("resultCoord",null );
		
					
					if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)) 
					{
							resultCoord = SolicitarServiciosTDC.solicitCoordenada(param);
					}
					else{
						if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS))
						{
							resultCoord=SolicitarServiciosTDC.solicitarTokens(param);
						}
					}

				}catch(ArrayRuleException ar){
					log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
					ar.printStackTrace();
					ar.setForward("cambiaCategoria");
					throw ar;
				}
				request.getSession().setAttribute("resultCoord",resultCoord );
		}else{
			
			throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
		}
	

		
		if (cCat.equals("01")){
			request.setAttribute("listCategoria","/imagenes/sello/01");
			//return mapping.findForward("listaAfilia1");
		} else if (cCat.equals("02")){
			request.setAttribute("listCategoria","/imagenes/sello/02");
			//return mapping.findForward("listaAfilia2");
		} else if (cCat.equals("03")){
			request.setAttribute("listCategoria","/imagenes/sello/03");
			//return mapping.findForward("listaAfilia3");
		} else if (cCat.equals("04")){
			request.setAttribute("listCategoria","/imagenes/sello/04");
			//return mapping.findForward("listaAfilia4");
		} else if (cCat.equals("05")){
			request.setAttribute("listCategoria","/imagenes/sello/05");
			//return mapping.findForward("listaAfilia5");
		} else if (cCat.equals("06")){
			request.setAttribute("listCategoria","/imagenes/sello/06");
			//return mapping.findForward("listaAfilia6");
		} else {
			request.setAttribute("listCategoria","/imagenes/sello/");
			//return mapping.findForward("listaAfilia7");
		}
		String var= request.getParameter("var");
		String afiliaCategoria = "";
		
	

		return mapping.findForward("listaCambio1");
		
		
	}
	
	public ActionForward afiliaCambioSello(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//request.getSession().removeAttribute(ConstanteSesion.MAP_VALUES);
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		TokenSmsRequest tokenSms = new TokenSmsRequest();
		Afiliacion afiliacion = new AfiliacionImpl();
		
		
		
//		Afiliacion datosUsuario  = new AfiliacionImpl();
//		datosUsuario = FacadeFactory.getGeneralFacade().obtenerDatosPersonalesAfiliacion(usuario);
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
		afiliacion.setCodCliente(usuario.getCodigoCic());					
		//afiliacion.setCodCliente(codCliente);
		afiliacion.setTipoDocumento(Funciones.lpad(tipoDoc,"0",3));
		afiliacion.setNroDocumento(numDoc);
		
		if(request.getSession().getAttribute(ConstanteSesion.MAP_VALUES)==null){
			//if(Constante.VER_LOG) System.out.println("No hay usuario en sesion");
			HttpSession session = request.getSession();
			//System.out.println("ID SESSION:"+session.getId());
			session.setMaxInactiveInterval(1);
			session.invalidate();
			request.getSession(true).setAttribute(ConstanteSesion.CARPETA,Constante.COD_PERSON_NAT);
			return mapping.findForward("sesion.expirada");
		}
		

		String ip = request.getRemoteAddr().toString();
		
		ParametrosElemSegTDC elementos = null;
		
		try{
			
			//FacadeFactory.getGeneralFacade().processValidationCardAhorro(usuario.getTarjeta().getNumero(),usuario.getTarjeta().getClave(),clave,clave);
			
			ElemenSeguridad resultCoord = null;
			ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
			ElemenSeguridad coord = (ElemenSeguridad)request.getSession().getAttribute("resultCoord");
			
			try{
				elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
				
				if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS) ||
				   elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
					elementos = SolicitarServiciosTDC.verificarTipoElemento(param,usuario);
				}
					
			}catch(ArrayRuleException ar){
				log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
				ar.printStackTrace();
				ar.setForward("cambiaCategoria");
				throw ar;
			}
			
			
			String pinblock = ObjectUtil.getClaveDesencriptada(request.getParameter("txtCoordenada"),request);
			MensajesTDC resultado = new MensajesTDC();
			
					
			try{
				
				if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){			
					resultado=SolicitarServiciosTDC.validarTDC(param ,coord,pinblock);				
				} else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS)) {					
						resultado=SolicitarServiciosTDC.validarTokens(param ,coord,pinblock);					
				} else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {					
					
					tokenSms.setCodeSMS(pinblock);
					
					tokenSms.setCodCliente(afiliacion.getCodCliente());
					tokenSms.setTipoDocumento(afiliacion.getTipoDocumento());
					tokenSms.setNroDocumento(afiliacion.getNroDocumento());
					
					tokenSms.setTypeTrans("40");
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
				
				
			//SolicitarServiciosTDC.validarTDC(param ,coord,pinblock);
			}
			catch (ArrayRuleException e) {
				log3.error(e,Constante.ERR_ERROR_VALIDAR_TDC,"");
				e.printStackTrace();
				//resultCoord = SolicitarServiciosTDC.solicitCoordenada(param);
				
				if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC))
				{
						resultCoord = SolicitarServiciosTDC.solicitCoordenada(param);
				}
				else{
					if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS))
					{
						
						resultCoord=SolicitarServiciosTDC.solicitarTokens(param);
					}
				}
				
				request.getSession().setAttribute("resultCoord",resultCoord );
				e.setForward("cambiaCategoria");
				throw e;
			}


			
			
			String cNumTarjeta = request.getSession().getAttribute("txtNumTarVal").toString().trim();
			String codCategoria = request.getSession().getAttribute("cboCategoria").toString().trim();
			
			String cVarImg = request.getParameter("selTarjeta").toString().trim();
			
			String cNumSec = FacadeFactory.getLoginFacade().getNextSec();
			
			cNumSec = ObjectUtil.setearCaracterLeft(cNumSec,'0',10-cNumSec.length());
			


			List listaTarjeta = null;
				listaTarjeta = FacadeFactory.getLoginFacade().getValidaImagenMV(cNumTarjeta);
				
			
			if(listaTarjeta!=null){
				ImagenTarjetaImpl nuevaLista = new ImagenTarjetaImpl();
				
					nuevaLista = (ImagenTarjetaImpl)listaTarjeta.get(0);
					FacadeFactory.getLoginFacade().getCambioEstado(nuevaLista.getNumSec(),Constante.ESTADO_SELLO_SEGURIDAD_NUEVO_NO_VIGENTE);
				
			}
			
			FacadeFactory.getLoginFacade().getAfiliaImagenMV(cNumTarjeta, "imgtmv"+cVarImg.trim(), cNumSec, codCategoria,ip,Constante.ESTADO_SELLO_SEGURIDAD_NUEVO_CAMBIO_SELLO);		
			BNAplicacion aplicacion = BNAplicacion.getInstance();
			request.getSession().setAttribute("msgSello11",((Vector)aplicacion.getMensajePorCodigo("LG01","SG011")).elementAt(2).toString());
			
			// -- LLamando a la Página de INICIO de MRV
			//request.getSession().removeAttribute(ConstanteSesion.MAP_VALUES);
			Constante.FLAG_CACHE = "0";
			//-- Se asigna el tipo de canal de ingreso
			
			ConstanteSesion.CODIGO_COMPANIA="1";
			String codCompany = request.getParameter("cod");
			if (codCompany != null){
			    ConstanteSesion.CODIGO_COMPANIA = codCompany.trim();
			}
			
			GeneratorKeys gen = new GeneratorKeys();
			request.getSession().setAttribute("numTarMV",cNumTarjeta.trim());
			request.getSession().setAttribute(ConstanteSesion.MAP_VALUES,gen.generateMap());
			
			request.getSession().setAttribute("txtImageSelloThumb", "/imagenes/sello/thumb/"+codCategoria+ "Tarjeta"+cVarImg.trim()+".png");
			
			ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
			request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION, registro);
			
			if(usuario.getNotificacion().getEstadoNotificacion().equals("1")){
				ServiciosNotificacionPrincipal.enviarNotificacion(
						usuario,
						usuario.getNombre(), 
						"", 
						"", 
						usuario.getTipoCambio().getVenta(), 
						Constante.COD_TRANSACCION_ADMINISTRATIVA, 
						usuario.getNotificacion());
			}
			
			
			if (registro != null) {
				
			}

			
		}
		catch (ArrayRuleException e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			e.setForward("cambiaCategoria");
			throw e;
		}
		finally{
			request.getSession().setAttribute("cuenta", (Cuenta)request.getSession().getAttribute("ctaSession"));
			request.getSession().setAttribute("cmbCuenta",request.getParameter("cmbCuenta"));
		}
		
		// FIN DE VALIDACION DE 6 DIGITOS
		
		
		
		
		return mapping.findForward("confirmarCambio");
	}
	
	public ActionForward desafiliaClave6(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//request.getSession().removeAttribute(ConstanteSesion.MAP_VALUES);
		
		//String cVarImg = request.getParameter("selTarjeta").toString().trim();
		String cNumTarjeta = request.getSession().getAttribute("txtNumTarVal").toString().trim();
		//String codCategoria = request.getSession().getAttribute("cboCategoria").toString().trim();
		
		String claveTarjeta		= request.getParameter("txtClaveInternet"); 
		String claveInternet	= request.getParameter("txtClaveInternet");
		String claveInternet_	= request.getParameter("txtClaveInternet_");
		
		//String claveCuatro		= request.getParameter("txtClaveTarjeta");
		//String claveTarjeta		= request.getParameter("txtClaveInternet"); 
		//String claveInternet	= request.getParameter("txtClaveInternet");
		//String claveInternet_	= request.getParameter("txtClaveInternet_");
		
		String transaccion		= "GC04";
		
		String dencriptClaveTarjeta 	= getClaveDesencriptadaClave6(claveTarjeta,request);
		String dencriptclaveInternet 	= getClaveDesencriptadaClave6(claveInternet,request);
		String dencriptclaveInternet_ 	= getClaveDesencriptadaClave6(claveInternet_,request);
		
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		request.getSession().setAttribute("msgSello9",((Vector)aplicacion.getMensajePorCodigo("LG01","SG009")).elementAt(2).toString());
		String ip = request.getRemoteAddr().toString();
						
		try {
			request.getSession().setAttribute(ConstanteSesion.TARJETA,FacadeFactory.getTarjetaFacade().desafiliarClaveSeis(cNumTarjeta, "0000", transaccion,dencriptClaveTarjeta,dencriptclaveInternet,dencriptclaveInternet_, request.getRemoteAddr()));
			List listaTarjeta = null;
			listaTarjeta = FacadeFactory.getLoginFacade().getValidaImagenMV(cNumTarjeta);
			
			request.getSession().setAttribute(ConstanteSesion.USUARIO_EN_SESION,listaTarjeta);
			
			ImagenTarjetaImpl imagenMV = (ImagenTarjetaImpl) listaTarjeta.get(0);
			FacadeFactory.getLoginFacade().getIntentosImagenMV(imagenMV.getNumSec(), 1,ip);
			return mapping.findForward("confirmaDesafiliacion");
			
		} catch (Exception e1) {
			log3.error(e1,Constante.ERR_LOGICA_NEGOCIO,"");
			e1.printStackTrace();
			throw new ArrayRuleException(ConstanteError.GENERICO,"Error al validar su clave de Internet, ingresar nuevamente su clave de Internet...");
			//return mapping.findForward("iniciarMV");
		}	
	}
	
	private String  getClaveDesencriptadaClave6(String passEncript, HttpServletRequest request){
		GeneratorKeys gen = new GeneratorKeys();
		Map mapa = (Map) SeguridadUtil.getObjectSession(ConstanteSesion.MAP_VALUES,request);
		//if(Constante.VER_LOG)System.out.println("request.getParameter('hdnValue')="+request.getParameter("hdnValue"));
		String  valorClave = gen.getClave(mapa,request.getParameter("hdnValue"),passEncript);
		return valorClave;
	}
	
	/**
	 * 
	 * @param passEncript
	 * @param request
	 * @return
	 */
	
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
	
	  public Date getDate(String date)     {         
		  DateFormat df = new SimpleDateFormat("dd/MM/yyyy");           
		  try {               
			  return df.parse(date);           
			  } 
		  catch (ParseException ex) {  
			  ex.printStackTrace();
		  }           
		  return null;    
		  
	  } 
	  
	  public Date getHora(String date)     {         
		  DateFormat df = new SimpleDateFormat("hh:mm:ss");           
		  try {               
			  return df.parse(date);           
			  } 
		  catch (ParseException ex) {  
			  ex.printStackTrace();
		  }           
		  return null;    
		  
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
	
	public ParametrosElemSegTDC setTipoElementoSeguridad(ParametrosTDC parametrosTDC, Usuario usuario, HttpServletRequest request){
	
		ParametrosElemSegTDC elementos = new ParametrosElemSegTDC();
		
		try{
//			Afiliacion datosUsuario  = FacadeFactory.getGeneralFacade().obtenerDatosPersonalesAfiliacion(usuario); 
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

			String urlConsultaTokenSms = Parametro.getString(ConstanteParametros.BN_PARAM_CLAVE_SMS_CONSULTA_TOKEN);
			tipoDoc = Funciones.lpad(tipoDoc,"0",3);
						
			Map<String, String> mapParametros = new HashMap<String, String>();
			mapParametros.put("typeDocument", Integer.parseInt(tipoDoc)+"");
			mapParametros.put("numDocument", numDoc);
			mapParametros.put("typeQuery", "1");
			
//			System.out.println("mapParametros::"+mapParametros);
//			System.out.println("usuario.getCodigoCic()::"+usuario.getCodigoCic());
			
			JSONObject jsonConsultaTipoToken = UtilAction.callRestPOST(urlConsultaTokenSms+usuario.getCodigoCic(), mapParametros);
			
			///System.out.println("jsonConsultaTipoToken::"+jsonConsultaTipoToken);
			
			JSONObject jeyObjTypeToken = (JSONObject)jsonConsultaTipoToken.get("data");

//			System.out.println("jeyObjTypeToken::"+jeyObjTypeToken);
			
			if(jeyObjTypeToken!=null){
				String typeToken = (String)jeyObjTypeToken.get("typeToken"); 
				String estadoAfiliacion = (String)jeyObjTypeToken.get("affilStatus");
				String channel = (String)jeyObjTypeToken.get("channel");
				String application = (String)jeyObjTypeToken.get("application");
				String estadoToken = (String)jeyObjTypeToken.get("tokenStatus");
				String pendienteEnrolamiento = (String)jeyObjTypeToken.get("pendienteEnrolamiento");
				
				elementos.setIdOperatorMobile((String)jeyObjTypeToken.get("operatorMobile"));
				elementos.setChannel(channel);
				elementos.setApplication(application);
				elementos.setEstadoToken(estadoToken);
				elementos.setPendienteEnrolamiento(pendienteEnrolamiento);
				
				if(application!=null)
				if(application.equals("SABM")){
					elementos.setDesApplication("Banca Móvil");
				}else if(application.equals("SATI")){
					elementos.setDesApplication("Multired Virtual");
				} else if(application.equals("SARA")){
					elementos.setDesApplication("Ventanilla");
				} else if(application.equals("ATM")){
					elementos.setDesApplication("ATM");
				}
				
				//A activo V vencido R pend act B bloqueado
				
				if(elementos.getIdOperatorMobile().equals(ConstanteSesion.CODIGO_OPERADOR_MOVISTAR)){
					elementos.setDesOperatorMobile(ConstanteSesion.DESCRIPCION_OPERADOR_MOVISTAR);
				}else if(elementos.getIdOperatorMobile().equals(ConstanteSesion.CODIGO_OPERADOR_CLARO)){
					elementos.setDesOperatorMobile(ConstanteSesion.DESCRIPCION_OPERADOR_CLARO);
				}else if(elementos.getIdOperatorMobile().equals(ConstanteSesion.CODIGO_OPERADOR_ENTEL)){
					elementos.setDesOperatorMobile(ConstanteSesion.DESCRIPCION_OPERADOR_ENTEL);				
				}else if(elementos.getIdOperatorMobile().equals(ConstanteSesion.CODIGO_OPERADOR_BITEL)){
					elementos.setDesOperatorMobile(ConstanteSesion.DESCRIPCION_OPERADOR_BITEL);
				}
				
				elementos.setNumberMobile((String)jeyObjTypeToken.get("numberMobile"));
				elementos.setPermiteOperaciones((String)jeyObjTypeToken.get("permiteOperaciones"));
				
//				/******* BORRAR ****************/
//				elementos.setPermiteOperaciones("1");
//				typeToken="1";
//				/******* BORRAR ****************/
				
				if(typeToken!=null && typeToken.equals("1")){
				/** FISICO ***/ 
					elementos.setTipoElementoSeguridad("5");
				} else if(typeToken!=null && typeToken.equals("2") && estadoAfiliacion.equals("AC")){			
				/** SMS ACTIVO***/				
					elementos.setTipoElementoSeguridad("6");
				} else if(typeToken!=null && typeToken.equals("2") && estadoAfiliacion.equals("AF")){
				/*** SMS AFILIADO NO ACTIVO ***/
//					String mostrarPopup = (String)request.getSession().getAttribute("mostrarPopup");
//					request.getSession().setAttribute("mensajeTitulo",((Vector) aplicacion.getMensajePorCodigo("CS01", "00004")).elementAt(2).toString().trim());
//					request.getSession().setAttribute("mensajeDescripcion",((Vector) aplicacion.getMensajePorCodigo("CS02", "00005")).elementAt(2).toString().trim());
//					request.getSession().setAttribute("mostrarPopupActivacion", "1");
//					request.getSession().setAttribute("codTituloMigrar", "00004");
//					if(mostrarPopup==null || !mostrarPopup.equals("0")){
//						request.getSession().setAttribute("mostrarPopup", "1");
//					}
					
					elementos.setTipoElementoSeguridad("7");
				} else if(typeToken!=null && typeToken.equals("2") && (!estadoAfiliacion.equals("AC") && !estadoAfiliacion.equals("AF")) ){
				/*** SMS AFILIADO / DESAFILIADO ***/					
					elementos.setTipoElementoSeguridad("99");			
				} else if(typeToken!=null && typeToken.equals("0") && (!estadoAfiliacion.equals("AC") && !estadoAfiliacion.equals("AF")) ){
					/*** SMS AFILIADO / DESAFILIADO ***/					
					elementos.setTipoElementoSeguridad("0");			
				}
				
				/** TARJETA DE COORDENADAS ***/
				if(typeToken==null || typeToken.equals("")){ elementos.setTipoElementoSeguridad("2");
				
				}
				
				
//				 -----------------------------------------------------------
//				 Requerimiento de cambio de mensajes en base a un código en
//				 en el grupo 'SMS'

//				No se encuentra afiliado a Clave Dinámica Digital ni token
				String codeMsgToken = "6603";

//				 Se encuentra afiliado a token físico o Clave Dinámica Digital			
				if (typeToken.matches("1|2")) {
				    // Verificar estado de afiliación !activo & afiliado
				    if (!estadoAfiliacion.equals("AC") && estadoAfiliacion.equals("AF")) {
				        codeMsgToken = "6604";
				    }
				    // Verificar estado de afiliación activo||afiliado
				    else if (estadoAfiliacion.matches("AC|AF| ")) {
				        // Bloqueo temporal
				        if (estadoToken.equals("BT")) {
				            codeMsgToken = "6605";
				        }
				        // Bloqueo permanente
				        else if (estadoToken.equals("BP")) {
				            codeMsgToken = "6606";
				        }
				        // Bloqueo permanente
				        else if (estadoToken.equals("99")) {
				            codeMsgToken = "6607";
				        }
				        // Bloqueo permanente
				        else if (estadoToken.equals("00")) {
				            codeMsgToken = "6608";
				        }
				        // Bloqueo permanente
                        else if (!estadoToken.equals("A") && typeToken.equals("1")) {
                            codeMsgToken = "6610";
                        }
				        if (estadoToken.equals("B")) {
				            codeMsgToken = "6603";
				        }
				    }
				    
				    else if (estadoAfiliacion.matches("DE")) {
				    	codeMsgToken = "6603";
				    }
				}
				String mensaje = aplicacion.getMsjesHost("CS", codeMsgToken).elementAt(2).toString();
				elementos.setDesMensajeToken(mensaje);
//				 -----------------------------------------------------------
				
				//consultaInvitaciones(parametrosTDC, usuario, request, typeToken, estadoToken, estadoAfiliacion);
				
				
				
			}
			
			
			
			
			return elementos;
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	public void consultaInvitaciones(ParametrosTDC parametrosTDC, Usuario usuario, 
			HttpServletRequest request, String tipoToken, String estadoToken, String estadoAfiliacion){
		
		try{
			BNAplicacion aplicacion = BNAplicacion.getInstance();
			ResourceBundle rb = ResourceBundle.getBundle("softoken");
			
			String urlConsultaInvitaciones = Parametro.getString(ConstanteParametros.BN_PARAM_MS_CONSULTA_INVITACIONES);
			
			
			InvitacionesResponse invResp = UtilAction.consultarInvitacionesClaveSms(urlConsultaInvitaciones, usuario.getCodigoCic());
			
			request.getSession().setAttribute("mensajeCondicion","");
			request.getSession().setAttribute("mensajeCondicion","");
			
			if(invResp.getDataInvitaciones()!=null){
				String codTitulo = "";
				String codTitulo2 = "";
				String codDescripcion = "";
				String codDescripcion2 = "";

				if (tipoToken.equals("1")){
					if (estadoToken.matches("A|R")){
						codTitulo = "00002";
						codDescripcion = "00003";
						codTitulo2 = "00061";
						codDescripcion2 = "00007";
					}
				}
				// ClaveSMS
				else if(tipoToken.equals("2")) {
					if (estadoAfiliacion.matches("AF")){
						//codTitulo = "00004";
						//codDescripcion = "00005";
					}
				}
				// Sin token
				else if (tipoToken.equals("0") || (tipoToken.equals("1") && !estadoToken.equals("A|R")) || 
						(tipoToken.equals("2") && !estadoAfiliacion.equals("DE"))){
						codTitulo = "00006";
						codTitulo2 = "00061";
						codDescripcion = "00005";
						codDescripcion2 = "00007";
				}
				if(!codTitulo.equals("") && !codDescripcion.equals("")){
					request.getSession().setAttribute("mensajeTitulo",((Vector) aplicacion.getMensajePorCodigo("CS01", codTitulo)).elementAt(2).toString().trim());
					request.getSession().setAttribute("mensajeTitulo2",((Vector) aplicacion.getMensajePorCodigo("CS01", codTitulo2)).elementAt(2).toString().trim());
					request.getSession().setAttribute("mensajeDescripcion",((Vector) aplicacion.getMensajePorCodigo("CS02", codDescripcion)).elementAt(2).toString().trim());
					request.getSession().setAttribute("mensajeDescripcion2",((Vector) aplicacion.getMensajePorCodigo("CS02", codDescripcion2)).elementAt(2).toString().trim());
					
					request.getSession().setAttribute("invitaciones", invResp);
					request.getSession().setAttribute("codTituloMigrar", codTitulo);
				}
			} 
	
			
			
			
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public ActionForward generarClaveSmsCambiaSello(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		TokenSmsRequest tokenSms = new TokenSmsRequest();
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		// Afiliacion afiliacion = (AfiliacionImpl)request.getSession().getAttribute("afiliacion");
		String monto = (String) request.getSession().getAttribute("monto");
//		Afiliacion datosUsuario = new AfiliacionImpl();

		try {

			Afiliacion afiliacion = new AfiliacionImpl();
			//afiliacion.setDescripcionTipoDocumento(NomTipoDocBenef(afiliacion.getCodigoServicio(), Constante.COD_HLP_DET_DOCU_GIRO));
			request.getSession().setAttribute("afiliacion", afiliacion);
			request.setAttribute("optTelegiro", afiliacion.getFlagRegistro());
			
//			datosUsuario = FacadeFactory.getGeneralFacade().obtenerDatosPersonalesAfiliacion(usuario);
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
            afiliacion.setTipoDocumento(Funciones.lpad(tipoDoc,"0",3));
			afiliacion.setNroDocumento(numDoc);
			afiliacion.setCodCliente(usuario.getCodigoCic());

			// REALIZAR CONSULTA TIPO CLAVE DINAMICA --> ADD M.D.B.
			ParametrosTDC param = (ParametrosTDC) request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
			request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION, param);
			ParametrosElemSegTDC elementos = null;

			elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
			request.getSession().setAttribute("tipoElemento", elementos);

			//REALIZAR CONSULTA DE COORDENADAS
			ElemenSeguridad resultCoord = null;

			if (param != null) {
				resultCoord = new ElemenSeguridad();
				request.getSession().setAttribute("resultCoord", null);

				if (elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {
					
					tokenSms.setCodCliente(usuario.getCodigoCic());
					tokenSms.setTipoDocumento(Funciones.lpad(tipoDoc,"0",3));
					tokenSms.setNroDocumento(numDoc);

					tokenSms.setTypeTrans(Constante.TIP_OPER_OPCIONES_SEGURIDAD_CAMBIO_SELLO);
					tokenSms.setTypeOp(Constante.COD_TRANSACCION_ADMINISTRATIVA);
					tokenSms.setTypeCurrency(Constante.MONEDA_SOL_ISO);
					

					MensajesTDC resultado = UtilAction.generarClaveSms(tokenSms);
					
					if(resultado.isExito()==false){		
						String mensaje = aplicacion.getMsjesHost("CS", resultado.getCodResult()).elementAt(2).toString();
						response.setStatus(404);
						response.getWriter().write(mensaje!=null&&!mensaje.equals("")?mensaje:"Ocurrió un problema al generar la Clave Dinámica Digital");
						response.getWriter().flush();
						response.getWriter().close();
					}
				}

				request.getSession().setAttribute("resultCoord", resultCoord);

			} else {
				throw new ArrayRuleException(ConstanteError.GENERICO, Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
			}

		} catch (ArrayRuleException e) {
			log3.error(e, Constante.ERR_LOGICA_NEGOCIO, "");
			response.setStatus(404);
			request.setAttribute("cmbCuenta", request.getParameter("cmbCuenta"));
			request.getSession().setAttribute("cmbTransferencia", request.getParameter("cmbTransferencia"));
			//loadMessage(request);
			e.setForward("verMBTransferencia");
			throw e;
		}
		return mapping.findForward("confirmarTelegiro");

	}
	
	
	public ActionForward generarClaveSmsGeneraSello(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		TokenSmsRequest tokenSms = new TokenSmsRequest();
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		// Afiliacion afiliacion = (AfiliacionImpl)request.getSession().getAttribute("afiliacion");
		String monto = (String) request.getSession().getAttribute("monto");
		//Afiliacion datosUsuario = new AfiliacionImpl();

		try {

			Afiliacion afiliacion = new AfiliacionImpl();
			//afiliacion.setDescripcionTipoDocumento(NomTipoDocBenef(afiliacion.getCodigoServicio(), Constante.COD_HLP_DET_DOCU_GIRO));
			request.getSession().setAttribute("afiliacion", afiliacion);
			request.setAttribute("optTelegiro", afiliacion.getFlagRegistro());
			
			//datosUsuario = FacadeFactory.getGeneralFacade().obtenerDatosPersonalesAfiliacion(usuario);
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

            afiliacion.setTipoDocumento(Funciones.lpad(tipoDoc,"0",3));
			afiliacion.setNroDocumento(numDoc);
			afiliacion.setCodCliente(usuario.getCodigoCic());

			// REALIZAR CONSULTA TIPO CLAVE DINAMICA --> ADD M.D.B.
			ParametrosTDC param = (ParametrosTDC) request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
			request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION, param);
			ParametrosElemSegTDC elementos = null;

			elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
			request.getSession().setAttribute("tipoElemento", elementos);

			//REALIZAR CONSULTA DE COORDENADAS
			ElemenSeguridad resultCoord = null;

			if (param != null) {
				resultCoord = new ElemenSeguridad();
				request.getSession().setAttribute("resultCoord", null);

				if (elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {
					
					tokenSms.setCodCliente(usuario.getCodigoCic());
					tokenSms.setTipoDocumento(Funciones.lpad(tipoDoc,"0",3));
					tokenSms.setNroDocumento(numDoc);

					tokenSms.setTypeTrans("45");
					tokenSms.setTypeOp(Constante.COD_TRANSACCION_ADMINISTRATIVA);
					tokenSms.setTypeCurrency(Constante.MONEDA_SOL_ISO);
					

					MensajesTDC resultado = UtilAction.generarClaveSms(tokenSms);
					
					if(resultado.isExito()==false){		
						String mensaje = aplicacion.getMsjesHost("CS", resultado.getCodResult()).elementAt(2).toString();
						response.setStatus(404);
						response.getWriter().write(mensaje!=null&&!mensaje.equals("")?mensaje:"Ocurrió un problema al generar la Clave Dinámica Digital");
						response.getWriter().flush();
						response.getWriter().close();
					}
				}

				request.getSession().setAttribute("resultCoord", resultCoord);

			} else {
				throw new ArrayRuleException(ConstanteError.GENERICO, Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
			}

		} catch (ArrayRuleException e) {
			log3.error(e, Constante.ERR_LOGICA_NEGOCIO, "");
			response.setStatus(404);
			request.setAttribute("cmbCuenta", request.getParameter("cmbCuenta"));
			request.getSession().setAttribute("cmbTransferencia", request.getParameter("cmbTransferencia"));
			//loadMessage(request);
			e.setForward("verMBTransferencia");
			throw e;
		}
		return mapping.findForward("confirmarTelegiro");

	}
	
	public String NomTipoDocBenef(String tipodoc, String campoasoc) {
		int td = Integer.parseInt(tipodoc);
		List lista = null;
		if (tipodoc != null && campoasoc != null)
			lista = DAOFactory.getGeneraDAO().getComboDetalleHlpHijoMod(
					campoasoc, tipodoc);
		if (lista != null) {
			for (Iterator iter = lista.iterator(); iter.hasNext();) {
				ComboUtil element = (ComboUtil) iter.next();

				if (element.getCodigo().trim().equals(tipodoc + "")) {
					return element.getDescripcion();
				}
			}
		}

		return "";
	}
	
	public void cerrarInvitacion(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response){
		
		request.getSession().setAttribute("mostrarPopup", "0");
		
	}
	
	public ActionForward irMigrarClaveSms(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		request.getSession().setAttribute("TITULO",Constante.TIT_MIGRA_PAGINA1);
		return mapping.findForward("inicioMigrarClaveSms");
	}
	
	public ActionForward recSeguridad(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	      
		System.out.println("loginAction-recomendacionSeguridad");
		String forward = "";		
		forward = "recomSeguridad";		
		return mapping.findForward(forward);
	}
	
	public ActionForward cboClave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	      
		System.out.println("loginAction-cambioClave");
		String forward = "";		
		
		request.getSession().setAttribute("titulo","1");
		
		//request.getSession().removeAttribute(ConstanteSesion.MAP_VALUES);
		//request.getSession().removeAttribute(ConstanteSesion.USUARIO_EN_SESION);
				
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
		
		forward = "cambioClave";		
		return mapping.findForward(forward);
	}
	
	
}