
package pe.bn.afiliacion.action;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pe.bn.afiliacion.action.form.AfiliacionBancaCelularForm;
import pe.bn.afiliacion.action.form.AfiliacionDebitoAutomaticoForm;
import pe.bn.afiliacion.action.form.DesafiliacionForm;
import pe.bn.afiliacion.action.form.AfiliacionTransferenciaBancariaForm;
import pe.bn.afiliacion.domain.Afiliacion;
import pe.bn.afiliacion.domain.impl.AfiliacionImpl;
import pe.bn.cldinamica.action.SolicitarServiciosTDC;
import pe.bn.cldinamica.action.form.ElemenSeguridad;
import pe.bn.cldinamica.action.form.MensajesTDC;
import pe.bn.cldinamica.action.form.ParametrosElemSegTDC;
import pe.bn.cldinamica.action.form.ParametrosTDC;
import pe.bn.listener.Util;
import pe.bn.notificaciones.action.ServicioNotificacionConstancia;
import pe.bn.tarjeta.domain.Tarjeta;
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
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.common.SeguridadUtil;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.AfiliacionesAliasImpl;
import pe.cosapi.domain.impl.AfiliacionesImpl;
import pe.cosapi.domain.impl.CuentaImpl;
import pe.cosapi.domain.impl.DetalleAyudaDatosImpl;
import pe.cosapi.domain.impl.MovimientoImpl;
import pe.cosapi.domain.impl.OperacionImpl;
import pe.cosapi.system.GeneratorKeys;
import pe.cosapi.system.Key4Digits;


public class AfiliacionBancaCelularAction extends GrandActionAbstract {
	BNAplicacion aplicacion =BNAplicacion.getInstance();

	private static LoggerSati log3 = LoggerSati.getInstance(AfiliacionBancaCelularAction.class.getName());

	
	
	
	public ActionForward verCondiciones(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ParametrosElemSegTDC elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento"); //5,6,7
        Usuario usuario = (Usuario)SeguridadUtil.getUsuarioSession(request);
        
		if(Constante.CODIGO_SI_PENDIENTE_ENROLAMIENTO.equals(elementos.getPendienteEnrolamiento()))			
		{
			return mapping.findForward("pendienteEnrolamiento");
		}
        
		if(elementos.getPermiteOperaciones().equals(Constante.CODIGO_NO_PERMITE_OPERACIONES))			
		{
			return mapping.findForward("noPermiteOperaciones");
		}
		
		String flagActdatos = usuario.getFlagActualizaDatoHost();
		if(flagActdatos.equals("1"))
		{
			return mapping.findForward("actualizarDatosPersona");
		}
		
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		loadObject(request);


		
		List x=usuario.getCuentas();
		CuentaImpl cuenta1 = (CuentaImpl)x.get(0);
			
		request.getSession().setAttribute("mensajeCondicion",((Vector)aplicacion.getMensajePorCodigo("BC01","00006")).elementAt(2).toString().trim());
	
		try{
			FacadeFactory.getAfiliacionFacade().validacionBancaCel("VR01",  cuenta1.getNumeroProducto(), request.getRemoteAddr(),usuario, request);
		}
		catch (ArrayRuleException e) {
			e.printStackTrace();
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			request.setAttribute("flag", "0");
			e.setForward("verCondiciones");
			throw e;
		}
		

		return mapping.findForward("verCondiciones");
	}
	
	public ActionForward buscarCelular(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.getSession().setAttribute("consultaAlias", null);
		request.getSession().setAttribute("mensajePieConsultAlias",((Vector)aplicacion.getMensajePorCodigo("BC01","00001")).elementAt(2).toString());
		request.getSession().setAttribute("mensajeCabeceraConsultAlias",((Vector)aplicacion.getMensajePorCodigo("BC01","00007")).elementAt(2).toString());
		

		return mapping.findForward("mostrarConsulta");
		
	}
	
	public ActionForward mostrarConsulta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String telefono = null;
		List  consulta = null;
		List  mostrar = new ArrayList();
		Usuario usuario = (Usuario)SeguridadUtil.getUsuarioSession(request);
		
		AfiliacionBancaCelularForm frm = (AfiliacionBancaCelularForm)form;
	
		String celular = frm.getTxtNumeroServicio();
		List x=usuario.getCuentas();
		CuentaImpl cuenta1 = (CuentaImpl)x.get(0);
		
		request.getSession().setAttribute("mensajeCabeceraConsultAlias",((Vector)aplicacion.getMensajePorCodigo("BC01","00001")).elementAt(2).toString());
		
		AfiliacionesAliasImpl resultado = new AfiliacionesAliasImpl();
		Afiliacion afiliacion = new AfiliacionImpl();
		resultado.setFecha(pe.cosapi.common.ObjectUtil.getFechaActual());
		resultado.setHora(pe.cosapi.common.ObjectUtil.getHHMMSSformateado());
		resultado.setCuentaFormateada(ObjectUtil.formatearCuenta(cuenta1.getNumeroProducto(),Constante.FORMATO_CUENTA));
	
		try{
			afiliacion=FacadeFactory.getAfiliacionFacade().consultaCelularBancaCel(Constante.CONSULT_CELULAR_BANCA_CELULAR,cuenta1.getNumeroProducto() , celular,request.getRemoteAddr(),usuario, request);
								
			if(!afiliacion.equals("") && !afiliacion.equals(null)){
				
				consulta=FacadeFactory.getAfiliacionFacade().consultaAliasBancaCel(Constante.CONSULT_ALIAS_BANCA_CELULAR,afiliacion,""+Long.parseLong(afiliacion.getNumeroCelular())  ,request.getRemoteAddr(),usuario, request);
				
				
		     	for(int i=0;i<consulta.size();i++){
		     		AfiliacionesAliasImpl mto=(AfiliacionesAliasImpl)consulta.get(i);

		     		if(mto.getAliasServicio().trim().equals("")){
		     			
		     			mto.setAliasServicio("---");
		     		}
	        		if(i+1<10){
	        			mto.setSecuencia("0"+(i+1));
	        		}else
	        			mto.setSecuencia(""+(i+1));
	        		mostrar.add(i,mto);
	        	}
			}
			
	     	afiliacion.setCuenta(cuenta1);
	     	afiliacion.setAfiliados(mostrar);
	     	afiliacion.setNumeroCelular(""+Long.parseLong(afiliacion.getNumeroCelular()));
	     	if(afiliacion.getOperador().equals(Constante.BAN_CELULAR_OPE_CLARO)){
	     			afiliacion.setMostrarOperador(Constante.BAN_CELULAR_OPE_DES_CLARO);
	     	}else{
	     		if(afiliacion.getOperador().equals(Constante.BAN_CELULAR_OPE_MOVISTAR)){
	     			afiliacion.setMostrarOperador(Constante.BAN_CELULAR_OPE_DES_MOVISTAR);
	     		}
	     	}
	     	resultado.setResultado(mostrar);
		}
		catch (ArrayRuleException e) {
			e.printStackTrace();
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			request.setAttribute("flag", "0");
			e.setForward("verCondiciones");
			throw e;
		}
		
		request.getSession().setAttribute(ConstanteSesion.AFIL_BANCA_CELULAR,afiliacion);
		
		
		Map map = UtilAction.cargarVar(request,ConstanteSesion.AFIL_BANCA_CELULAR);
		OperacionImpl.setVariables(map);
		
		//Se envia la lista al formulario
		request.getSession().setAttribute("consultaAlias", mostrar);
		
		if(mostrar.size() > 0){
			ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_AFILIACION_BANCA_CONSULTA_AFIL, ConstanteSesion.AFIL_BANCA_CELULAR, request);
		}
		
		return mapping.findForward("mostrarConsulta");
		
	}
	
	
	public ActionForward iniciarAfiliacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ParametrosElemSegTDC elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento"); //5,6,7
		Usuario usuario = (Usuario)SeguridadUtil.getUsuarioSession(request);
        
		if(Constante.CODIGO_SI_PENDIENTE_ENROLAMIENTO.equals(elementos.getPendienteEnrolamiento()))			
		{
			return mapping.findForward("pendienteEnrolamiento");
		}
		
		if(elementos.getPermiteOperaciones().equals(Constante.CODIGO_NO_PERMITE_OPERACIONES))			
		{
			return mapping.findForward("noPermiteOperaciones");
		}		
		
		String flagActdatos = usuario.getFlagActualizaDatoHost();
		if(flagActdatos.equals("1"))
		{
			return mapping.findForward("actualizarDatosPersona");
		}
		
		
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		loadObject(request);
		
		
		List x=usuario.getCuentas();
		CuentaImpl cuenta1 = (CuentaImpl)x.get(0);
			
		
		request.getSession().setAttribute("mensajeCondicion",((Vector)aplicacion.getMensajePorCodigo("BC01","00006")).elementAt(2).toString().trim());
		
		if(elementos.getTipoElementoSeguridad().equals("5")){
			System.out.println("Token Fisico");
			usuario.getClaveDinamica().setTipoElementoSeguridad(elementos.getTipoElementoSeguridad());
			usuario.getClaveDinamica().setTipoElementoSeguridadFinal("Token Fisico");
			
		}else if(elementos.getTipoElementoSeguridad().equals("6")){
			System.out.println("Clave Dinamica Digital");
			usuario.getClaveDinamica().setTipoElementoSeguridad(elementos.getTipoElementoSeguridad());
			usuario.getClaveDinamica().setTipoElementoSeguridadFinal("Clave Dinamica Digital");
		}
		
		try{
			FacadeFactory.getAfiliacionFacade().validacionBancaCel("VR01",  cuenta1.getNumeroProducto(), request.getRemoteAddr(),usuario, request);
		}
		catch (ArrayRuleException e) {
			e.printStackTrace();
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			request.setAttribute("flag", "0");
			e.setForward("iniciarAfiliacion");
			throw e;
		}
		

		return mapping.findForward("iniciarAfiliacion");
	}
	
	public ActionForward consultarCelularAfiliacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.getSession().setAttribute("mensajeCabeceraCelAfil",((Vector)aplicacion.getMensajePorCodigo("BC01","00004")).elementAt(2).toString());

		return mapping.findForward("consultaCelAfiliacion");
		
	}
	
	public ActionForward mostrarAfiliacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		loadObject(request);
		
		AfiliacionBancaCelularForm frm = (AfiliacionBancaCelularForm)form;
		
		
		//Validar si esta afiliado a Banca Celular
		Afiliacion afiliacion = new AfiliacionImpl();
		Usuario usuario = (Usuario)SeguridadUtil.getUsuarioSession(request);
	
	
		//Setea el número del Celular
		String celular = frm.getTxtNumeroServicio();
		
		//Trae la Cuenta de Ahorros
		List x=usuario.getCuentas();
		CuentaImpl cuenta1 = (CuentaImpl)x.get(0);
		try{
			afiliacion=FacadeFactory.getAfiliacionFacade().consultaCelularBancaCel(Constante.CONSULT_CELULAR_BANCA_CELULAR,cuenta1.getNumeroProducto() ,celular,request.getRemoteAddr(),usuario, request);
		}
		catch (ArrayRuleException e) {
			e.printStackTrace();
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			request.setAttribute("flag", "0");
			e.setForward("iniciarAfiliacion");
			throw e;
		}
		
		//Mostrar el operador
		afiliacion.setOperador("1");
		if(afiliacion.getOperador().equals(Constante.BAN_CELULAR_OPE_CLARO)){
 			afiliacion.setMostrarOperador(Constante.BAN_CELULAR_OPE_DES_CLARO);
		}else{
	 		if(afiliacion.getOperador().equals(Constante.BAN_CELULAR_OPE_MOVISTAR)){
	 			afiliacion.setMostrarOperador(Constante.BAN_CELULAR_OPE_DES_MOVISTAR);
	 		}
		}
		afiliacion.setCuenta(cuenta1);
		//afiliacion.setNumeroCelular("987456321");
		afiliacion.setNumeroCelular(""+Long.parseLong(afiliacion.getNumeroCelular()));
		String correo = afiliacion.getCorreo().toLowerCase().trim()+Constante.DELIM_ALF+afiliacion.getServidorCorreo().toLowerCase().trim();
		//String correo = "gromsm@gmail.com";
		afiliacion.setEmail(correo);
		request.getSession().setAttribute("correo",correo);
		
		
		
//		REALIZAR CONSULTA TIPO CLAVE DINAMICA  --> ADD M.D.B.
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		ParametrosElemSegTDC elementos = null;
//		request.getSession().setAttribute("tipoElemento",null );	
		try{
			
			
			elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
			
			if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS) ||
			   elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
				elementos = SolicitarServiciosTDC.verificarTipoElemento(param,usuario);
			}
			
			
		}catch(ArrayRuleException ar){
			log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
			ar.printStackTrace();
			ar.setForward("iniciarAfiliacion");
			throw ar;
		}
		
		
//		REALIZAR CONSULTA DE COORDENADAS
		

		ElemenSeguridad resultCoord = null;
		
		if(param!= null){
			resultCoord = new ElemenSeguridad();
			try{
				request.getSession().setAttribute("resultCoord",null );
				
				if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC))
				{
					resultCoord = SolicitarServiciosTDC.solicitCoordenada(param);
				} else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS)) {
					resultCoord=SolicitarServiciosTDC.solicitarTokens(param);
				} else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {
					//generarClaveSms(mapping, form, request, response);
					request.getSession().setAttribute("exitoGeneraClvSms", true);
				}
				
				
				
			}catch(ArrayRuleException ar){
				log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
				ar.printStackTrace();
				ar.setForward("inicio");
				throw ar;
			}
			request.getSession().setAttribute("resultCoord",resultCoord );
		}
		else{
			
			throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
		}
		
		frm.resetTotal(mapping, request);
		request.getSession().setAttribute(ConstanteSesion.AFIL_BANCA_CELULAR,afiliacion);
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);


		return mapping.findForward("mostrarAfiliacion");
	}
	
	public ActionForward mostrarOpAfiliacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		loadObject(request);
		List valor = new ArrayList();
		ActionForward forward = new ActionForward();
		
		AfiliacionBancaCelularForm frm = (AfiliacionBancaCelularForm)form;
		frm.reset(mapping, request);
		
		request.setAttribute("listaAfiliacion", frm);
		request.getSession().setAttribute("lstPagoServicio", valor);
		String correo = request.getParameter("correo");
		request.getSession().setAttribute("correo",correo);
		forward = mapping.findForward("mostrarAfiliacion");
		return (forward);	

		}
	
	public ActionForward mostrarEntidad(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		loadObject(request);
		ActionForward forward = new ActionForward();
		
		AfiliacionBancaCelularForm frm = (AfiliacionBancaCelularForm)form;
	
		request.getSession().setAttribute("lstPagoServicio", FacadeFactory.getGeneralFacade().getComboDetalleHlpHijoCodhlp(Constante.BAN_CELULAR_PAGO_SERVICIO,frm.getCmbEntidad()));
				
		request.setAttribute("listaAfiliacion", frm);
		
		forward = mapping.findForward("mostrarAfiliacion");
		return (forward);	

		}
	


	public ActionForward afiliar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();		
		AfiliacionBancaCelularForm frm = (AfiliacionBancaCelularForm)form;
		Usuario usuario = (Usuario)SeguridadUtil.getUsuarioSession(request);
		Tarjeta tarjeta = usuario.getTarjeta(); 
		Afiliacion afiliacion = setearAfiliacion(frm,tarjeta, usuario, request);
		
		
//		Afiliacion datosUsuario = new AfiliacionImpl();
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

		//NOMBRE DEL BENEFICIARIO
		if (usuario.getNombre().length() > 30){
			afiliacion.setBeneficiario(usuario.getNombre().substring(0, 29).trim());
		} else {
			afiliacion.setBeneficiario(usuario.getNombre().trim());
		}
		
		afiliacion.setFecha(pe.cosapi.common.ObjectUtil.getFechaActual());
		afiliacion.setHora(pe.cosapi.common.ObjectUtil.getHHMMSSformateado());
		
				
		request.getSession().setAttribute("CORREO_AFILIADO",afiliacion.getEmail().trim());
		request.getSession().setAttribute("mensajecabeceraafbc",((Vector)aplicacion.getMensajePorCodigo("BC01","00002")).elementAt(2).toString());
		
		
	
		AfiliacionImpl afil_ = new AfiliacionImpl(afiliacion);
		
		
		try{
			
			
			//FacadeFactory.getGeneralFacade().validarDatosPersonalesAfiliacion(afiliacion,usuario);
		
			
		}
		catch (ArrayRuleException e) {
		
			request.setAttribute("listaAfiliacion", frm);
			mostrarEntidad(mapping,form,request,response);
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			loadObject(request);
			e.setForward("mostrarAfiliacion");
			throw e;
		}
		
	
		
				
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		ElemenSeguridad coord = (ElemenSeguridad)request.getSession().getAttribute("resultCoord");
		
		ElemenSeguridad resultCoord = null;
		String pinblock = request.getParameter("txtCoordenada");
		ParametrosElemSegTDC elementos = null;
		
		//REALIZAR CONSULTA TIPO CLAVE DINAMICA  --> ADD M.D.B.
		
		try{
			
			
			elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
			
			if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS) ||
			   elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
				elementos = SolicitarServiciosTDC.verificarTipoElemento(param,usuario);
			}
			
			
		}catch(ArrayRuleException ar){
			log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
			ar.printStackTrace();
			ar.setForward("mostrarAfiliacion");
			throw ar;
		}
		
		
		
		//Verifica Tarjeta de Coordenadas
		
		try {
				
		
			try{
				TokenSmsRequest tokenSms = new TokenSmsRequest();
				
				MensajesTDC resultado = new MensajesTDC();
				
				if(param!= null && coord!=null ){
	
						
//						if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC))
//						{
//					
//							resultado=SolicitarServiciosTDC.validarTDC(param ,coord,pinblock);
//						
//						}
//						else{
//							if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS))
//							{
//							
//								resultado=SolicitarServiciosTDC.validarTokens(param ,coord,pinblock);
//							}
//							
//						}
//						
//						if(!resultado.getCodRptaPrincipal().equals(Constante.CODIGO_RESULTADO_OK) || !resultado.getCodResultOper().equals(Constante.CODIGO_OPERACION_OK)){
//																
//							String codErrEnt="";
//							String mensaje="";
//							
//							if(!resultado.getCodRptaPrincipal().equals(Constante.CODIGO_RESULTADO_OK))	{
//								 	codErrEnt = ObjectUtil.setearCaracterLeft(resultado.getCodRptaPrincipal().toString(), '0', 4-resultado.getCodRptaPrincipal().toString().length());
//								 	mensaje=aplicacion.getMsjesHost("CD",codErrEnt).elementAt(2).toString();
//							}	
//							else{
//								if(!resultado.getCodResultOper().equals(Constante.CODIGO_OPERACION_OK)){
//									 codErrEnt = ObjectUtil.setearCaracterLeft(resultado.getCodRptaPrincipalOper().toString(), '0', 4-resultado.getCodRptaPrincipalOper().toString().length());
//									 mensaje=aplicacion.getMsjesHost("CD",codErrEnt).elementAt(2).toString();
//								}
//							}
//							
//							throw new ArrayRuleException(ConstanteError.GENERICO,mensaje+" (CD-"+codErrEnt+")");
//							
//						}
					
					
					if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {		
						
						pinblock = ObjectUtil.getClaveDesencriptada(request
								.getParameter("txtCoordenada"), request);
						
						tokenSms.setCodCliente(afiliacion.getCodCliente());
						tokenSms.setTipoDocumento(afiliacion.getTipoDocumento());
						tokenSms.setNroDocumento(afiliacion.getNroDocumento());

						tokenSms.setCodeSMS(pinblock);
						tokenSms.setTypeTrans("41");
						tokenSms.setTypeOp("ADMIN");
						tokenSms.setMsgClient("Validacion de Clave Dinamica Digital");
						tokenSms.setTypeCurrency("PEN");
						tokenSms.setNomCliDestinatario(usuario.getNombre());
						tokenSms.setCodCliDestinatario(usuario.getNumDocumento());
						
						resultado=UtilAction.validarClaveSms(tokenSms);
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
				
			}catch (ArrayRuleException e) {
				log3.error(e,Constante.ERR_ERROR_VALIDAR_TDC,"");
				e.printStackTrace();
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
				mostrarEntidad(mapping,form,request,response);
				loadObject(request);
				e.setForward("mostrarAfiliacion");
				throw e;
			}
			
			
			
			List x=usuario.getCuentas();
			CuentaImpl cuenta1 = (CuentaImpl)x.get(0);
			afiliacion.setCuenta(cuenta1);
			
			String correo = afiliacion.getEmail().toLowerCase();
			
		
			if(!correo.trim().equals("") && !correo.trim().equals(null)){
				
				String[] var = correo.split(Constante.SEP_ALPHANUMERIC);
				
				afiliacion.setCorreo(var[0]);
				afiliacion.setServidorCorreo(var[1]);
			}
			
			
			
			//Mostrar el tipo de afiliacion
			
			List tipoAfil = FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.BAN_CELULAR_AFILIACIONES );
			
			for(int y=0; y< tipoAfil.size();y++){
				
				DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
				
				dHelp_ = (DetalleAyudaDatosImpl)tipoAfil.get(y);
							
				if(afiliacion.getServicioAfiliacion().equals(dHelp_.getCodigoDetalleAyuda()))
				{
					
					afiliacion.setMostrarTipoAfil(dHelp_.getDescripcionDetalle());
					
				}
				
			}
			
			//Mostrar el tipo de Documento
			List doc = FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_DET_DOCU_OTRO);
			
			for(int a=0; a< doc.size();a++){
				
				ComboUtil combo = new ComboUtil();
				
				combo = (ComboUtil)doc.get(a);
				
					
				if(afiliacion.getTipoDocumento().equals(combo.getCodigo()))
				{
					
					afiliacion.setDocMostrar(combo.getDescripcion());
										
				}
				
			}
			
			//Mostrar el operador
			List operador = FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.BAN_CELULAR_OPERADOR);
			for(int z=0; z< operador.size();z++){
				
				ComboUtil combo = new ComboUtil();
				
				combo = (ComboUtil)operador.get(z);
									
				if(afiliacion.getOperador().equals(combo.getCodigo()))
				{
					afiliacion.setMostrarOperador(combo.getDescripcion());
										
				}
				
			}
			
			
			//Mostrar Operador Origen
			if(afiliacion.getOperadorOrigen().equals(Constante.BAN_CELULAR_OPE_CLARO)){
     			afiliacion.setOperadorOrigen(Constante.BAN_CELULAR_OPE_DES_CLARO);
			}else{
     		if(afiliacion.getOperadorOrigen().equals(Constante.BAN_CELULAR_OPE_MOVISTAR)){
     			afiliacion.setOperadorOrigen(Constante.BAN_CELULAR_OPE_DES_MOVISTAR);
     		}
     	}
			
			//Mostrar la cuenta/suministro o numero 
			
			if(afiliacion.getServicioAfiliacion().equals(Constante.BAN_CELULAR_TIPO_AFIL_TRANS)){
				afiliacion.setMostrarNumAfil(afiliacion.getNumeroCuentaDestino());
				forward = mapping.findForward("finAfiliacionTrans");
			}
			else{
				if(afiliacion.getServicioAfiliacion().equals(Constante.BAN_CELULAR_TIPO_AFIL_RECA)){
					
//					if(afiliacion.getOperador().equals(Constante.BAN_CELULAR_OPE_CLARO)){
//						afiliacion.setServicioAfiliacion(Constante.BAN_CELULAR_RECARGA_CLARO);
//					}
//					
//					if(afiliacion.getOperador().equals(Constante.BAN_CELULAR_OPE_MOVISTAR)){
//						afiliacion.setServicioAfiliacion(Constante.BAN_CELULAR_RECARGA_MOVISTAR);
//					}
					afiliacion.setMostrarNumAfil(afiliacion.getNumeroServicio());
					
					forward = mapping.findForward("finAfiliacionReca");
				}
				else{
					if(afiliacion.getServicioAfiliacion().equals(Constante.BAN_CELULAR_TIPO_AFIL_SERV)){
					
//						if(afiliacion.getServicio().equals(Constante.BAN_CELULAR_BD_RECIBO_SEDAPAL)){
//							afiliacion.setServicioAfiliacion(Constante.BAN_CELULAR_RECIBO_SEDAPAL);
//						}
//						if(afiliacion.getServicio().equals(Constante.BAN_CELULAR_BD_TV_MOVISTAR)){
//							afiliacion.setServicioAfiliacion(Constante.BAN_CELULAR_TV_MOVISTAR);
//						}
//						if(afiliacion.getServicio().equals(Constante.BAN_CELULAR_BD_RECIBO_MOVISTAR)){
//							afiliacion.setServicioAfiliacion(Constante.BAN_CELULAR_RECIBO_MOVISTAR);
//						}
//						if(afiliacion.getServicio().equals(Constante.BAN_CELULAR_BD_RECIBO_CLARO)){
//							afiliacion.setServicioAfiliacion(Constante.BAN_CELULAR_RECIBO_CLARO);
//						}
//						
//						if(afiliacion.getServicio().equals(Constante.BAN_CELULAR_BD_TELEFONIA_FIJA_MOVISTAR)){
//							afiliacion.setServicioAfiliacion(Constante.BAN_CELULAR_TELEFONIA_FIJA_MOVISTAR);
//						}
						afiliacion.setMostrarNumAfil(afiliacion.getNumSuministro());
						
						//Mostrar el servicio
						List servicio = FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.BAN_CELULAR_PAGO);
						for(int z=0; z< servicio.size();z++){
							
							ComboUtil combo = new ComboUtil();
							
							combo = (ComboUtil)servicio.get(z);
												
							if(afiliacion.getEmpresa().equals(combo.getCodigo()))
							{
								afiliacion.setEmpresaMostrar(combo.getDescripcion());
													
							}
							
						}
						
						//Mostrar el entidad
						List entidad = FacadeFactory.getGeneralFacade().getComboDetalleHlpHijoCodhlp(Constante.BAN_CELULAR_PAGO_SERVICIO,afiliacion.getEmpresa());
						for(int j=0; j< entidad.size();j++){
							
							ComboUtil combo = new ComboUtil();
							
							combo = (ComboUtil)entidad.get(j);
												
							if(afiliacion.getServicio().equals(combo.getCodigo()))
							{
								afiliacion.setServicioMostrar(combo.getDescripcion());
													
							}
							
						}
						forward = mapping.findForward("finAfiliacionServ");
					}
					else{
						afiliacion.setMostrarNumAfil(Constante.BAN_CELULAR_SIN_DESCRIPCION);
						forward = mapping.findForward("finAfiliacionTasa");
					}
				}
			}
			

			
			// AFILIA VIA TRAMA
							
			Afiliacion afil = FacadeFactory.getAfiliacionFacade().getAfiliacionBancaCel(Constante.AFILIA_BANCA_CELULAR, afiliacion, "0", request.getRemoteAddr(),usuario, request);
			

			afiliacion.setNroOperacion(afil.getNroOperacion());
						
			request.getSession().setAttribute(ConstanteSesion.AFILIAR,afiliacion);
			Map map = UtilAction.cargarVar(request,afiliacion);
			OperacionImpl.setVariables(map);
		
			
			loadObject(request);
			
			if(afiliacion.getServicioAfiliacion().equals(Constante.BAN_CELULAR_TIPO_AFIL_TRANS)){
				ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_AFILIACION_BANCA_AFILIACION_TRAN, ConstanteSesion.AFILIAR, request);
			}
			else{
				if(afiliacion.getServicioAfiliacion().equals(Constante.BAN_CELULAR_TIPO_AFIL_RECA)){	
					ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_AFILIACION_BANCA_AFILIACION_RECA, ConstanteSesion.AFILIAR, request);
				}
				else{
					if(afiliacion.getServicioAfiliacion().equals(Constante.BAN_CELULAR_TIPO_AFIL_SERV)){
						ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_AFILIACION_BANCA_AFILIACION_SERV, ConstanteSesion.AFILIAR, request);
					}
					else{
						ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_AFILIACION_BANCA_AFILIACION_TASA, ConstanteSesion.AFILIAR, request);
					}
				}
			}
		}
		catch (ArrayRuleException e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			loadObject(request);
			mostrarEntidad(mapping,form,request,response);
			e.setForward("mostrarAfiliacion");
			throw e;
		}
		
				
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		return forward;
	}
	
	
	public ActionForward iniciarDesafiliacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ParametrosElemSegTDC elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento"); //5,6,7
//		Trae datos del usuario
		Usuario usuario = (Usuario)SeguridadUtil.getUsuarioSession(request);
        
		if(Constante.CODIGO_SI_PENDIENTE_ENROLAMIENTO.equals(elementos.getPendienteEnrolamiento()))			
		{
			return mapping.findForward("pendienteEnrolamiento");
		}
		
		if(elementos.getPermiteOperaciones().equals(Constante.CODIGO_NO_PERMITE_OPERACIONES))			
		{
			return mapping.findForward("noPermiteOperaciones");
		}
		
		String flagActdatos = usuario.getFlagActualizaDatoHost();
		if(flagActdatos.equals("1"))
		{
			return mapping.findForward("actualizarDatosPersona");
		}
		
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		loadObject(request);
		AfiliacionBancaCelularForm frm = (AfiliacionBancaCelularForm)form;
		frm.resetTotal(mapping, request);
		
		//Muestra condiciones
		request.getSession().setAttribute("mensajeCondicion",((Vector)aplicacion.getMensajePorCodigo("BC01","00006")).elementAt(2).toString().trim());
		
		
		
		if(elementos.getTipoElementoSeguridad().equals("5")){
			System.out.println("Token Fisico");
			usuario.getClaveDinamica().setTipoElementoSeguridad(elementos.getTipoElementoSeguridad());
			usuario.getClaveDinamica().setTipoElementoSeguridadFinal("Token Fisico");
			
		}else if(elementos.getTipoElementoSeguridad().equals("6")){
			System.out.println("Clave Dinamica Digital");
			usuario.getClaveDinamica().setTipoElementoSeguridad(elementos.getTipoElementoSeguridad());
			usuario.getClaveDinamica().setTipoElementoSeguridadFinal("Clave Dinamica Digital");
		}
		
		//Trae el número de la cuenta
		List x=usuario.getCuentas();
		CuentaImpl cuenta1 = (CuentaImpl)x.get(0);
		
		try{
			FacadeFactory.getAfiliacionFacade().validacionBancaCel("VR01",  cuenta1.getNumeroProducto(), request.getRemoteAddr(),usuario, request);
		}
		catch (ArrayRuleException e) {
			e.printStackTrace();
			request.setAttribute("flag", "0");
			e.setForward("iniciarDesafiliacion");
			throw e;
		}
		

		return mapping.findForward("iniciarDesafiliacion");
	}
	
public ActionForward mostrarDesafiliacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
	
		List  consulta = null;
		List  mostrar = new ArrayList();
		Usuario usuario = (Usuario)SeguridadUtil.getUsuarioSession(request);
				
		List x=usuario.getCuentas();
		CuentaImpl cuenta1 = (CuentaImpl)x.get(0);
		
		AfiliacionesAliasImpl resultado = new AfiliacionesAliasImpl();
		Afiliacion afiliacion = new AfiliacionImpl();
		
		//Mostrar mensajes en el formulario
		request.getSession().setAttribute("mensajeCabeceraDesafiliacion",((Vector)aplicacion.getMensajePorCodigo("BC01","00007")).elementAt(2).toString());
		request.getSession().setAttribute("mensajePieDesafiliacion",((Vector)aplicacion.getMensajePorCodigo("BC01","00001")).elementAt(2).toString());
		
		
		//REALIZAR CONSULTA TIPO CLAVE DINAMICA  --> ADD M.D.B.
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		ParametrosElemSegTDC elementos = null;
		//request.getSession().setAttribute("tipoElemento",null );	
		try{
			
			
			elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
			
			if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS) ||
			   elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
				elementos = SolicitarServiciosTDC.verificarTipoElemento(param,usuario);
			}
			
			
		}catch(ArrayRuleException ar){
			log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
			ar.printStackTrace();
			ar.setForward("iniciarDesafiliacion");
			throw ar;
		}
		
		
		//REALIZAR CONSULTA DE COORDENADAS
			
		ElemenSeguridad resultCoord = null;
		
		if(param!= null){
			resultCoord = new ElemenSeguridad();
			try{
				request.getSession().setAttribute("resultCoord",null );
				
				if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)) {
					resultCoord = SolicitarServiciosTDC.solicitCoordenada(param);
				} else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS)) {
					resultCoord=SolicitarServiciosTDC.solicitarTokens(param);
				} else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {
					//generarClaveSms(mapping, form, request, response);
					request.getSession().setAttribute("exitoGeneraClvSms", true);
				}
				
				
				
			}catch(ArrayRuleException ar){
				log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
				ar.printStackTrace();
				ar.setForward("iniciarDesafiliacion");
				throw ar;
			}
			request.getSession().setAttribute("resultCoord",resultCoord );
		}
		else{
			
			throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
		}
		
		
	
		try{
			
			String celular = "";
			
			AfiliacionBancaCelularForm frm = (AfiliacionBancaCelularForm)form;
			
		
			
			if(!frm.getTxtNumeroServicio().equals(null) && !frm.getTxtNumeroServicio().trim().equals("")){
				
				afiliacion=FacadeFactory.getAfiliacionFacade().consultaCelularBancaCel(Constante.CONSULT_CELULAR_BANCA_CELULAR,cuenta1.getNumeroProducto() ,frm.getTxtNumeroServicio().trim(),request.getRemoteAddr(),usuario, request);
				
				if(!afiliacion.equals("") && !afiliacion.equals(null)){
					
					consulta=FacadeFactory.getAfiliacionFacade().consultaAliasBancaCel(Constante.CONSULT_ALIAS_BANCA_CELULAR,afiliacion,""+Long.parseLong(afiliacion.getNumeroCelular())  ,request.getRemoteAddr(),usuario, request);
					
					
			     	for(int i=0;i<consulta.size();i++){
			     		AfiliacionesAliasImpl mto=(AfiliacionesAliasImpl)consulta.get(i);

			     		if(mto.getAliasServicio().trim().equals("")){
			     			
			     			mto.setAliasServicio("---");
			     		}
		        		if(i+1<10){
		        			mto.setSecuencia("0"+(i+1));
		        		}else
		        			mto.setSecuencia(""+(i+1));
		        		mostrar.add(i,mto);
		        	}
				}
				
				//afiliacion.setOperador("1");
				//afiliacion.setNumeroCelular("987654321");
				afiliacion.setNumeroCelular(""+Long.parseLong(afiliacion.getNumeroCelular()));
				if(afiliacion.getOperador().equals(Constante.BAN_CELULAR_OPE_CLARO)){
	     			afiliacion.setMostrarOperador(Constante.BAN_CELULAR_OPE_DES_CLARO);
				}else{
		     		if(afiliacion.getOperador().equals(Constante.BAN_CELULAR_OPE_MOVISTAR)){
		     			afiliacion.setMostrarOperador(Constante.BAN_CELULAR_OPE_DES_MOVISTAR);
		     		}
				}
				//
				afiliacion.setCuenta(cuenta1);
		     	resultado.setResultado(mostrar);
			}
		
	     	
		}
		catch (ArrayRuleException e) {
			e.printStackTrace();
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			request.setAttribute("flag", "0");
			e.setForward("iniciarDesafiliacion");
			throw e;
		}
		
		
		//Se envia la lista al formulario
		request.getSession().setAttribute("consultaDesafiliar", mostrar);
		request.getSession().setAttribute(ConstanteSesion.AFIL_BANCA_CELULAR,afiliacion);
		
		Map map = UtilAction.cargarVar(request,ConstanteSesion.AFIL_BANCA_CELULAR);
		OperacionImpl.setVariables(map);
		
		return mapping.findForward("mostrarDesafiliacion");
		
	}
	
public ActionForward desafiliar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

	TokenSmsRequest tokenSms = new TokenSmsRequest();
	
	AfiliacionBancaCelularForm frm = (AfiliacionBancaCelularForm)form;
	ActionForward forward = new ActionForward();

	request.getSession().setAttribute("mensajecabeceradebc",((Vector)aplicacion.getMensajePorCodigo("BC01","00003")).elementAt(2).toString());

	Usuario usuario = (Usuario)SeguridadUtil.getUsuarioSession(request);
	Tarjeta tarjeta = usuario.getTarjeta(); 
	
	Afiliacion afiliacion = new AfiliacionImpl();
	
	afiliacion.setAlias(request.getParameter("aliasCuenta"));
	
	afiliacion.setNroTarjeta(tarjeta.getNumero());
	afiliacion.setTarjetaOculta(ObjectUtil.ocultarCuenta(tarjeta.getNumero(),Constante.FORMATO_TARJETA));
	afiliacion.setNumeroCelular(request.getParameter("numCelular"));
	afiliacion.setFecha(pe.cosapi.common.ObjectUtil.getFechaActual());
	afiliacion.setHora(pe.cosapi.common.ObjectUtil.getHHMMSSformateado());
	afiliacion.setMostrarOperador(request.getParameter("operador"));
	String[] id = ObjectUtil.getArrayStrings(request.getParameter("optSecuencia"),"-");
	
	
	//Afiliacion afiliacion = (Afiliacion)request.getSession().getAttribute("afiliacion");
//	Afiliacion datosUsuario  = new AfiliacionImpl();
//	datosUsuario = FacadeFactory.getGeneralFacade().obtenerDatosPersonalesAfiliacion(usuario);
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
	//afiliacion.setCodCliente(usuario.getCodigoCic());					
	//afiliacion.setCodCliente(codCliente);
	afiliacion.setTipoDocumento(Funciones.lpad(tipoDoc,"0",3));
	afiliacion.setNroDocumento(numDoc);
	
	if(id.length>0){
		
		//Setear los valores de la consulta	
		afiliacion.setServicioAfiliacion(id[0]);
		afiliacion.setAliasOperacion(id[1].trim());
		if(!afiliacion.getServicioAfiliacion().equals(Constante.BAN_CELULAR_TIPO_AFIL_TASA))
		{	
			afiliacion.setMostrarNumAfil(id[2].trim());
		}
		else{afiliacion.setMostrarNumAfil(Constante.BAN_CELULAR_SIN_DESCRIPCION);}
		
	}
	
	try{
	
	
			ElemenSeguridad resultCoord = null;
			String pinblock = request.getParameter("txtCoordenada");
			ParametrosElemSegTDC elementos = null;
			ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
			request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
			ElemenSeguridad coord = (ElemenSeguridad)request.getSession().getAttribute("resultCoord");

			
		//REALIZAR CONSULTA TIPO CLAVE DINAMICA  --> ADD M.D.B.
			
			try{
				
				
				elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
				
				if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS) ||
				   elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
					elementos = SolicitarServiciosTDC.verificarTipoElemento(param,usuario);
				}
				
				
			}catch(ArrayRuleException ar){
				log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
				ar.printStackTrace();
				ar.setForward("mostrarDesafiliacion");
				throw ar;
			}
		
			
			MensajesTDC resultado = new MensajesTDC();
			
			if(param!= null && coord!=null ){
				
				
				
				try{
					
//					if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC))
//					{
//				
//						resultado=SolicitarServiciosTDC.validarTDC(param ,coord,pinblock);
//					
//					}
//					else{
//						if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS))
//						{
//						
//							resultado=SolicitarServiciosTDC.validarTokens(param ,coord,pinblock);
//						}
//						
//					}
//					
//					if(!resultado.getCodRptaPrincipal().equals(Constante.CODIGO_RESULTADO_OK) || !resultado.getCodResultOper().equals(Constante.CODIGO_OPERACION_OK)){
//															
//						String codErrEnt = ObjectUtil.setearCaracterLeft(resultado.getCodRptaPrincipal().toString(), '0', 4-resultado.getCodRptaPrincipal().toString().length());
//						String mensaje=aplicacion.getMsjesHost("CD",codErrEnt).elementAt(2).toString();
//						
//						throw new ArrayRuleException(ConstanteError.GENERICO,mensaje+" (CD-"+codErrEnt+")");
//						
//					}
//					
					
					if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)){				
						
						pinblock = ObjectUtil.getClaveDesencriptada(request.getParameter("txtCoordenada"),request);
						
						tokenSms.setCodCliente(afiliacion.getCodCliente());
						tokenSms.setTipoDocumento(afiliacion.getTipoDocumento());
						tokenSms.setNroDocumento(afiliacion.getNroDocumento());
						
						tokenSms.setCodeSMS(pinblock);
						tokenSms.setTypeTrans("41");
						tokenSms.setTypeOp("ADMIN");						
						tokenSms.setMsgClient("Validacion de Clave Dinamica Digital");
						tokenSms.setTypeCurrency("PEN");
						tokenSms.setNomCliDestinatario(afiliacion.getNomCliente());
						tokenSms.setCodCliDestinatario(afiliacion.getNumDocumento());	
						
						resultado = UtilAction.validarClaveSms(tokenSms);			
					}
					
					if(!resultado.getCodRptaPrincipal().equals(Constante.CODIGO_RESULTADO_OK) || !resultado.getCodResultOper().equals(Constante.CODIGO_OPERACION_OK)){
						
						String codErrEnt="";
						String mensaje="";
						
						if(!resultado.getCodRptaPrincipal().equals(Constante.CODIGO_RESULTADO_OK))	{
							codErrEnt = ObjectUtil.setearCaracterLeft(resultado.getCodRptaPrincipal().toString(), '0', 4 - resultado.getCodRptaPrincipal().toString().length());
							if (elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {
								codErrEnt = resultado.getCodResult();
						 		mensaje = resultado.getMsg();
							} else {
								mensaje = aplicacion.getMsjesHost("CD", codErrEnt).elementAt(2).toString();
							}
						}	
						else{
							if(!resultado.getCodResultOper().equals(Constante.CODIGO_OPERACION_OK)){
								 codErrEnt = ObjectUtil.setearCaracterLeft(resultado.getCodRptaPrincipalOper().toString(), '0', 4-resultado.getCodRptaPrincipalOper().toString().length());
								 mensaje=aplicacion.getMsjesHost("CD",codErrEnt).elementAt(2).toString();
							}
						}
						
						throw new ArrayRuleException(ConstanteError.GENERICO,mensaje+" (CD-"+codErrEnt+")");
						
					}
					
									
				}
				catch (ArrayRuleException e) {
					log3.error(e,Constante.ERR_ERROR_VALIDAR_TDC,"");
					e.printStackTrace();
					
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

			
					e.setForward("mostrarDesafiliacion");
				
					throw e;
				}
			}
	
			
		
	List x=usuario.getCuentas();
	CuentaImpl cuenta1 = (CuentaImpl)x.get(0);
	afiliacion.setCuenta(cuenta1);
	
	
	//Mostrar el tipo de afiliacion
	
	List tipoAfil = FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.BAN_CELULAR_AFILIACIONES );
	
	for(int y=0; y< tipoAfil.size();y++){
		
		DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
		
		dHelp_ = (DetalleAyudaDatosImpl)tipoAfil.get(y);
					
		if(afiliacion.getServicioAfiliacion().equals(dHelp_.getCodigoDetalleAyuda()))
		{
			
			afiliacion.setMostrarTipoAfil(dHelp_.getDescripcionDetalle());
			
		}
		
	}

	//DESAFILIAR VIA TRAMA

	Afiliacion  afil = null;
	
	
	afil=FacadeFactory.getAfiliacionFacade().getDesafiliacionBancaCel(Constante.DESAFILIA_BANCA_CELULAR, afiliacion, "2", request.getRemoteAddr(),usuario, request); 
	afiliacion.setNroOperacion(afil.getNroOperacion());
	

	afiliacion.setNumeroCelular(""+Long.parseLong(afiliacion.getNumeroCelular()));

	request.getSession().setAttribute(ConstanteSesion.AFILIAR,afiliacion);
	Map map = UtilAction.cargarVar(request,afiliacion);
	OperacionImpl.setVariables(map);
	

	

	forward = mapping.findForward("finDesafiliacion");
	
	ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_AFILIACION_BANCA_DESAFILIACION, ConstanteSesion.AFILIAR, request);
	
	}catch (ArrayRuleException e) {
		log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
		loadObject(request);
		frm.setTxtNumClave("");
		e.setForward("mostrarDesafiliacion");
	throw e;
	}



	return forward;
}

public ActionForward iniciarVinculacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
	ParametrosElemSegTDC elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento"); //5,6,7
	Usuario usuario = (Usuario)SeguridadUtil.getUsuarioSession(request);
    
	if(Constante.CODIGO_SI_PENDIENTE_ENROLAMIENTO.equals(elementos.getPendienteEnrolamiento()))			
	{
		return mapping.findForward("pendienteEnrolamiento");
	}
	
	if(elementos.getPermiteOperaciones().equals(Constante.CODIGO_NO_PERMITE_OPERACIONES))			
	{
		return mapping.findForward("noPermiteOperaciones");
	}
	
	
	String flagActdatos = usuario.getFlagActualizaDatoHost();
	if(flagActdatos.equals("1"))
	{
		return mapping.findForward("actualizarDatosPersona");
	}
	
	BNAplicacion aplicacion = BNAplicacion.getInstance();
	loadObject(request);
	AfiliacionBancaCelularForm frm = (AfiliacionBancaCelularForm)form;
	frm.resetTotal(mapping, request);
	
	request.getSession().setAttribute("mensajeCondicion",((Vector)aplicacion.getMensajePorCodigo("BC01","00009")).elementAt(2).toString().trim());
	
	
	
	if(elementos.getTipoElementoSeguridad().equals("5")){
		System.out.println("Token Fisico");
		usuario.getClaveDinamica().setTipoElementoSeguridad(elementos.getTipoElementoSeguridad());
		usuario.getClaveDinamica().setTipoElementoSeguridadFinal("Token Fisico");
		
	}else if(elementos.getTipoElementoSeguridad().equals("6")){
		System.out.println("Clave Dinamica Digital");
		usuario.getClaveDinamica().setTipoElementoSeguridad(elementos.getTipoElementoSeguridad());
		usuario.getClaveDinamica().setTipoElementoSeguridadFinal("Clave Dinamica Digital");
	}
	
	//Trae el número de la cuenta
	List x=usuario.getCuentas();
	CuentaImpl cuenta1 = (CuentaImpl)x.get(0);
	
	try{
		FacadeFactory.getAfiliacionFacade().validacionBancaCel("VR01",  cuenta1.getNumeroProducto(), request.getRemoteAddr(),usuario, request);
	}
	catch (ArrayRuleException e) {
		e.printStackTrace();
		request.setAttribute("flag", "0");
		e.setForward("iniciarVinculacion");
		throw e;
	}
	

	return mapping.findForward("iniciarVinculacion");
}

public ActionForward mostrarVinculacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	BNAplicacion aplicacion = BNAplicacion.getInstance();
	loadObject(request);
	AfiliacionBancaCelularForm frm = (AfiliacionBancaCelularForm)form;
	frm.resetTotal(mapping, request);
	

	
	//Trae datos del usuario
	Usuario usuario = (Usuario)SeguridadUtil.getUsuarioSession(request);
	
	
	//Trae el número de la cuenta
	List x=usuario.getCuentas();
	CuentaImpl cuenta1 = (CuentaImpl)x.get(0);
	

	
	//REALIZAR CONSULTA TIPO CLAVE DINAMICA  --> ADD M.D.B.
	ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
	request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
	ParametrosElemSegTDC elementos = null;
//	request.getSession().setAttribute("tipoElemento",null );	
	try{
		
		
		elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
		
		if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS) ||
		   elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
			elementos = SolicitarServiciosTDC.verificarTipoElemento(param,usuario);
		}
		
		
	}catch(ArrayRuleException ar){
		log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
		ar.printStackTrace();
		ar.setForward("iniciarVinculacion");
		throw ar;
	}
	
	
	//REALIZAR CONSULTA DE COORDENADAS
		
	ElemenSeguridad resultCoord = null;
	
	if(param!= null){
		resultCoord = new ElemenSeguridad();
		try{
			request.getSession().setAttribute("resultCoord",null );
			
			if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
				resultCoord = SolicitarServiciosTDC.solicitCoordenada(param);
			} else if (elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS)) {
				resultCoord = SolicitarServiciosTDC.solicitarTokens(param);
			} else if (elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {
				//generarClaveSms(mapping,form, request, response);
				request.getSession().setAttribute("exitoGeneraClvSms", true);
			} 
		}catch(ArrayRuleException ar){
			log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
			ar.printStackTrace();
			ar.setForward("iniciarVinculacion");
			throw ar;
		}
		request.getSession().setAttribute("resultCoord",resultCoord );
	}
	else{
		
		throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
	}
	

	return mapping.findForward("mostrarVinculacion");
}

public ActionForward vincular(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {


	ActionForward forward = new ActionForward();
	AfiliacionBancaCelularForm frm = (AfiliacionBancaCelularForm)form;
		
	try{
	
		Usuario usuario = (Usuario)SeguridadUtil.getUsuarioSession(request);
		Tarjeta tarjeta = usuario.getTarjeta(); 
		
		Afiliacion afiliacion = new AfiliacionImpl();
		
		afiliacion.setNroTarjeta(tarjeta.getNumero());
		afiliacion.setTarjetaOculta(ObjectUtil.ocultarCuenta(tarjeta.getNumero(),Constante.FORMATO_TARJETA));
		
		afiliacion.setFecha(pe.cosapi.common.ObjectUtil.getFechaActual());
		afiliacion.setHora(pe.cosapi.common.ObjectUtil.getHHMMSSformateado());
		afiliacion.setOperador(frm.getCmbOperador());
		afiliacion.setTipoOperacion(frm.getCmbOperacion());
		afiliacion.setNumeroCelular(frm.getTxtNumeroServicio());
		afiliacion.setEmail(frm.getTxtMail());
		afiliacion.setTipoDocumento(frm.getCmbTipoDocIdent());
		afiliacion.setNroDocumento(frm.getTxtNumDoc());
		if (!frm.getTxtDia().equals(""))afiliacion.setFechaNacimiento(ObjectUtil.stringToTimestamp(frm.getTxtDia(),frm.getCmbMes(),frm.getTxtAnio()));
		

//		Afiliacion datosUsuario = new AfiliacionImpl();
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
		afiliacion.setTipoDocumento(Funciones.lpad(tipoDoc,"0",3));
		afiliacion.setNroDocumento(numDoc);
		afiliacion.setCodCliente(usuario.getCodigoCic());		
		//afiliacion.setCodCliente(codCliente);
		
		try{
						
			//FacadeFactory.getGeneralFacade().validarDatosPersonalesAfiliacion(afiliacion,usuario);
			
		}
		catch (ArrayRuleException e) {
		
			request.setAttribute("listaAfiliacion", frm);
			mostrarEntidad(mapping,form,request,response);
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			loadObject(request);
			e.setForward("mostrarVinculacion");
			throw e;
		}
		
		//Clave Dinamica
			ElemenSeguridad resultCoord = null;
			String pinblock = request.getParameter("txtCoordenada");
			ParametrosElemSegTDC elementos = null;
			ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
			request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
			ElemenSeguridad coord = (ElemenSeguridad)request.getSession().getAttribute("resultCoord");

			
		//REALIZAR CONSULTA TIPO CLAVE DINAMICA  --> ADD M.D.B.
			
			try{
				
				
				elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
				
				if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS) ||
				   elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
					elementos = SolicitarServiciosTDC.verificarTipoElemento(param,usuario);
				}
				
				
			}catch(ArrayRuleException ar){
				log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
				ar.printStackTrace();
				ar.setForward("mostrarVinculacion");
				throw ar;
			}
		
			
			MensajesTDC resultado = new MensajesTDC();
			
			if(param!= null && coord!=null ){
				TokenSmsRequest tokenSms = new TokenSmsRequest();
				
				
				
				try{
					
//					if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC))
//					{
//				
//						resultado=SolicitarServiciosTDC.validarTDC(param ,coord,pinblock);
//					
//					}
//					else{
//						if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS))
//						{
//						
//							resultado=SolicitarServiciosTDC.validarTokens(param ,coord,pinblock);
//						}
//						
//					}
//					
//					if(!resultado.getCodRptaPrincipal().equals(Constante.CODIGO_RESULTADO_OK) || !resultado.getCodResultOper().equals(Constante.CODIGO_OPERACION_OK)){
//															
//						String codErrEnt = ObjectUtil.setearCaracterLeft(resultado.getCodRptaPrincipal().toString(), '0', 4-resultado.getCodRptaPrincipal().toString().length());
//						String mensaje=aplicacion.getMsjesHost("CD",codErrEnt).elementAt(2).toString();
//						
//						throw new ArrayRuleException(ConstanteError.GENERICO,mensaje+" (CD-"+codErrEnt+")");
//						
//					}
					
					if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)){
						
//						pinblock = ObjectUtil.getClaveDesencriptada(request
//								.getParameter("txtCoordenada"), request);
						
						
						tokenSms.setCodCliente(afiliacion.getCodCliente());
						tokenSms.setTipoDocumento(afiliacion.getTipoDocumento());
						tokenSms.setNroDocumento(afiliacion.getNroDocumento());

						tokenSms.setCodeSMS(pinblock);
						tokenSms.setTypeTrans("40");
						tokenSms.setTypeOp("ADMIN");						
						tokenSms.setMsgClient("Validacion de Clave Dinamica Digital");
						tokenSms.setTypeCurrency("PEN");
						tokenSms.setNomCliDestinatario(afiliacion.getNomCliente());
						tokenSms.setCodCliDestinatario(afiliacion.getNumDocumento());	

						resultado = UtilAction.validarClaveSms(tokenSms);
					}

				}
				catch (ArrayRuleException e) {
					log3.error(e,Constante.ERR_ERROR_VALIDAR_TDC,"");
					e.printStackTrace();
					
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

			
					e.setForward("mostrarVinculacion");
				
					throw e;
				}
			}
	
			
		
	List x=usuario.getCuentas();
	CuentaImpl cuenta1 = (CuentaImpl)x.get(0);
	afiliacion.setCuenta(cuenta1);
	
	
	String correo = afiliacion.getEmail().toLowerCase();
	
	
	if(!correo.trim().equals("") && !correo.trim().equals(null)){
		
		String[] var = correo.split(Constante.SEP_ALPHANUMERIC);
		
		afiliacion.setCorreo(var[0]);
		afiliacion.setServidorCorreo(var[1]);
	}
	

	//VINCULAR VIA TRAMA

	Afiliacion  afil = null;
	Key4Digits keyGen = new Key4Digits(); 
	String clave = getClaveDesencriptada(request.getParameter("txtClaveInternet").trim(),request);
	//System.out.println("clave:"+clave);
	String claveEncrip =keyGen.encriptar(clave);
	
	//System.out.println("claveEncrip:"+claveEncrip);
	
	afil=FacadeFactory.getAfiliacionFacade().getVinculacionBancaCel(Constante.VINCULA_BANCA_CELULAR, afiliacion,claveEncrip ,request.getRemoteAddr(),usuario, request); 
	afiliacion.setNroOperacion(afil.getNroOperacion());
	
	//Mostrar Operador 
	if(afiliacion.getOperador().equals(Constante.BAN_CELULAR_OPE_CLARO)){
			afiliacion.setMostrarOperador(Constante.BAN_CELULAR_OPE_DES_CLARO);
	}else{
		if(afiliacion.getOperador().equals(Constante.BAN_CELULAR_OPE_MOVISTAR)){
			afiliacion.setMostrarOperador(Constante.BAN_CELULAR_OPE_DES_MOVISTAR);
		}
	}
	
	request.getSession().setAttribute(ConstanteSesion.AFILIAR,afiliacion);
	Map map = UtilAction.cargarVar(request,afiliacion);
	OperacionImpl.setVariables(map);
	
	if(afiliacion.getTipoOperacion().equals(Constante.BAN_CELULAR_VINCULACION)){
		request.getSession().setAttribute("mensajecabeceraVinc",((Vector)aplicacion.getMensajePorCodigo("BC01","00005")).elementAt(2).toString());
		forward = mapping.findForward("finVinculacion");
	}else{
		if(afiliacion.getTipoOperacion().equals(Constante.BAN_CELULAR_DESVINCULACION)){
		request.getSession().setAttribute("mensajecabeceraVinc",((Vector)aplicacion.getMensajePorCodigo("BC01","00008")).elementAt(2).toString());
		forward = mapping.findForward("finDesvinculacion");
		}
		
	}

	if(afiliacion.getTipoOperacion().equals(Constante.BAN_CELULAR_VINCULACION)){
		ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_AFILIACION_BANCA_VINCULACION, ConstanteSesion.AFILIAR, request);
	}else{
		ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_AFILIACION_BANCA_DESVINCULACION, ConstanteSesion.AFILIAR, request);
	}
	
	}catch (ArrayRuleException e) {
		log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
		loadObject(request);
		frm.setTxtNumClave("");
		e.setForward("mostrarVinculacion");
	throw e;
	}



	return forward;
}
	
	private void loadObject(HttpServletRequest request)throws Exception {
		List lista= FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_DET_DOCU_OTRO);
	
		
		lista.remove(0);
		request.setAttribute("lstDocumento",lista);
		
		request.setAttribute("lstTipoCuentaBancaCel", FacadeFactory.getGeneralFacade().getComboTipoCuentaBancaCel());
		request.setAttribute("lstMes", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.COD_HLP_DET_MES));
		request.setAttribute("lstAfiliaciones", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.BAN_CELULAR_AFILIACIONES));
		request.setAttribute("lstOperador", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.BAN_CELULAR_OPERADOR));
		request.setAttribute("lstOperacion", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.BAN_CELULAR_TIPO_OPERACION));
		request.setAttribute("lstPago", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.BAN_CELULAR_PAGO));
		request.setAttribute("lstPagoServicio", null);
		
		request.setAttribute("lstLocalidad", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.COD_HLP_DET_LOCAL));
	
			
	}
	
	private Afiliacion setearAfiliacion(AfiliacionBancaCelularForm frm,Tarjeta tarjeta, Usuario usuario, HttpServletRequest request) throws Exception{
		Afiliacion afiliacion = new AfiliacionImpl();
		Afiliacion temp = new AfiliacionImpl();
		try{

		temp= (Afiliacion)request.getSession().getAttribute(ConstanteSesion.AFIL_BANCA_CELULAR);
		afiliacion.setNumeroCelular(temp.getNumeroCelular());
		afiliacion.setAlias(temp.getAlias());
		afiliacion.setOperadorOrigen(temp.getOperador());
		afiliacion.setTipoAfiliacion(Constante.AFILIA_BANCA_CELULAR);
		afiliacion.setNroTarjeta(tarjeta.getNumero());
		afiliacion.setTarjetaOculta(ObjectUtil.ocultarCuenta(tarjeta.getNumero(),Constante.FORMATO_TARJETA));
		afiliacion.setTipoDocumento(frm.getCmbTipoDocIdent());
		afiliacion.setNroDocumento(frm.getTxtNumDoc());
		if (!frm.getTxtDia().equals(""))afiliacion.setFechaNacimiento(ObjectUtil.stringToTimestamp(frm.getTxtDia(),frm.getCmbMes(),frm.getTxtAnio()));
		afiliacion.setEmail(request.getParameter("correo"));
		afiliacion.setOperador(frm.getCmbOperador());
		afiliacion.setNumeroServicio(frm.getTxtNumeroServicio());
		afiliacion.setAliasOperacion(frm.getTxtAlias());
		afiliacion.setEmpresa(frm.getCmbEntidad());
		afiliacion.setServicio(frm.getCmbServicio());
		afiliacion.setNumSuministro(frm.getTxtNumeroSuministro());
		afiliacion.setTipoCuentaDestino(frm.getCmbTipoCuentaDestino());
		afiliacion.setNumeroCuentaDestino(frm.getTxtNumeroCuentaDestino());
		afiliacion.setCuentaPropia(request.getParameter("rdnCuentaPropia"));
		afiliacion.setServicioAfiliacion(frm.getCmbServicioAfiliacion());
		
		}catch (Exception e) {
			e.printStackTrace();
		}


		return afiliacion;
	}
	private boolean validaCuentaPropia(String cuenta, String flgCtapropia, Usuario user, HttpServletRequest request) throws Exception{
		List listaCuentas = user.getCuentas();
		boolean flag= false;
		if(flgCtapropia.equals("S")){
		    //System.out.println("Cuentaa: "+cuenta);
			for(int i=0;i<listaCuentas.size();i++){
				Cuenta cta= (Cuenta)listaCuentas.get(i);
				//System.out.println("Cta "+i+": "+cta.getNumeroProducto());
				if(cuenta.equals(cta.getNumeroProducto())){
					
				    //System.out.println("La cuenta " + cuenta + " es cuenta propia.");
					flag=true;
				}	
			}
			if(!flag){
				loadObject(request);
				throw new ArrayRuleException(ConstanteError.GENERICO,"CUENTA NO ASOCIADA CON TARJETA MULTIRED");
			}
		}else{
			for(int i=0;i<listaCuentas.size();i++){
				Cuenta cta= (Cuenta)listaCuentas.get(i);
				//System.out.println("Cta "+i+": "+cta.getNumeroProducto());
				if(cuenta.equals(cta.getNumeroProducto())){
					loadObject(request);
					//flag=false;
					throw new ArrayRuleException(ConstanteError.GENERICO,"CUENTA ESTA ASOCIADA CON TARJETA MULTIRED" );
				}
			}
			flag=true;
		}
		return flag;
	}

	@Override
	public ActionForward iniciar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Apéndice de método generado automáticamente
		return null;
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
	
	
	public ActionForward generarClaveSms(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		loadObject(request);
		TokenSmsRequest tokenSms = new TokenSmsRequest();
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		// Afiliacion afiliacion = (AfiliacionImpl)request.getSession().getAttribute("afiliacion");
		String monto = (String) request.getSession().getAttribute("monto");
		//Afiliacion datosUsuario = new AfiliacionImpl();
		
		AfiliacionBancaCelularForm frm = (AfiliacionBancaCelularForm)form;
		frm.reset(mapping, request);
		
		request.getSession().removeAttribute("exitoGeneraClvSms");
		

		try {


			Afiliacion afiliacion 	= new AfiliacionImpl(); 
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
			//afiliacion.setCodCliente(codCliente);

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
				
					tokenSms.setCodCliente(afiliacion.getCodCliente());
					tokenSms.setTipoDocumento(afiliacion.getTipoDocumento());
					tokenSms.setNroDocumento(afiliacion.getNroDocumento());
					
					tokenSms.setTypeTrans("41");
					tokenSms.setTypeOp("ADMIN");						
					tokenSms.setMsgClient("Generacion de Clave Dinamica Digital");
					tokenSms.setTypeCurrency("PEN");
					tokenSms.setNomCliDestinatario(afiliacion.getNomCliente());
					tokenSms.setCodCliDestinatario(afiliacion.getNumDocumento());	
					
					MensajesTDC resultado = UtilAction.generarClaveSms(tokenSms);
					
					
				}

				request.getSession().setAttribute("resultCoord", resultCoord);

			} else {
				throw new ArrayRuleException(ConstanteError.GENERICO, Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
			}

		} catch (ArrayRuleException e) {
			log3.error(e, Constante.ERR_LOGICA_NEGOCIO, "");

			request.setAttribute("cmbCuenta", request.getParameter("cmbCuenta"));
			request.getSession().setAttribute("cmbTransferencia", request.getParameter("cmbTransferencia"));
			loadMessage(request);
			e.setForward("verMBTransferencia");
			response.setStatus(404);
			throw e;
		}
		return mapping.findForward("inicio");

	}
	
	
	public ActionForward reGenerarClaveSms(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		loadObject(request);
		TokenSmsRequest tokenSms = new TokenSmsRequest();
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		// Afiliacion afiliacion = (AfiliacionImpl)request.getSession().getAttribute("afiliacion");
		String monto = (String) request.getSession().getAttribute("monto");
		//Afiliacion datosUsuario = new AfiliacionImpl();
		
		AfiliacionBancaCelularForm frm = (AfiliacionBancaCelularForm)form;
		frm.reset(mapping, request);
		
		request.getSession().removeAttribute("exitoGeneraClvSms");
		

		try {


			Afiliacion afiliacion 	= new AfiliacionImpl(); 
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
			//afiliacion.setCodCliente(codCliente);

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
				
					tokenSms.setCodCliente(afiliacion.getCodCliente());
					tokenSms.setTipoDocumento(afiliacion.getTipoDocumento());
					tokenSms.setNroDocumento(afiliacion.getNroDocumento());
					
					tokenSms.setTypeTrans("41");
					tokenSms.setTypeOp("ADMIN");						
					tokenSms.setMsgClient("Generacion de Clave Dinamica Digital");
					tokenSms.setTypeCurrency("PEN");
					tokenSms.setNomCliDestinatario(afiliacion.getNomCliente());
					tokenSms.setCodCliDestinatario(afiliacion.getNumDocumento());	
					
					MensajesTDC resultado = UtilAction.generarClaveSms(tokenSms);
					
					if(resultado.isExito()==false){		
						String mensaje = aplicacion.getMsjesHost("CS", resultado.getCodResult()).elementAt(2).toString();
						response.setStatus(404);
						response.getWriter().write(mensaje);
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

			request.setAttribute("cmbCuenta", request.getParameter("cmbCuenta"));
			request.getSession().setAttribute("cmbTransferencia", request.getParameter("cmbTransferencia"));
			loadMessage(request);
			e.setForward("verMBTransferencia");
			response.setStatus(404);
			throw e;
		}
		return mapping.findForward("inicio");

	}
	
	private void loadMessage(HttpServletRequest request) throws Exception{
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		request.getSession().setAttribute("mensajeAfiliacion",((Vector)aplicacion.getMensajePorCodigo("TB01","AF001")).elementAt(2).toString());
		request.getSession().setAttribute("mensajePantalla",((Vector)aplicacion.getMensajePorCodigo("TB01","00001")).elementAt(2).toString());
		request.getSession().setAttribute("mensajeExitoTranfMB",((Vector)aplicacion.getMensajePorCodigo("TB01","00005")).elementAt(2).toString());
	}
	
	
	public ActionForward cboClave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	      
		System.out.println("loginAction-cambioClaveXD");
		
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
