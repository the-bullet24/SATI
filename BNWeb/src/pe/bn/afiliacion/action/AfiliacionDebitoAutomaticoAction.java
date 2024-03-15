
package pe.bn.afiliacion.action;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

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
import pe.cosapi.domain.impl.AfiliacionesImpl;
import pe.cosapi.domain.impl.CuentaImpl;
import pe.cosapi.domain.impl.DetalleAyudaDatosImpl;
import pe.cosapi.domain.impl.OperacionImpl;


public class AfiliacionDebitoAutomaticoAction extends GrandActionAbstract {
	BNAplicacion aplicacion =BNAplicacion.getInstance();

	private static LoggerSati log3 = LoggerSati.getInstance(AfiliacionDebitoAutomaticoAction.class.getName());

	
	
	
	public ActionForward verCondiciones(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		loadObject(request);
		
		Usuario usuario = (Usuario)SeguridadUtil.getUsuarioSession(request);
		
		ParametrosElemSegTDC elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento"); //5,6,7
		
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
		
		if(!getVerificarHorario("AD01")){
			request.getSession().setAttribute("mensaje1Hor",((Vector)aplicacion.getMensajePorCodigo("AD01","00001")).elementAt(2).toString());
			request.getSession().setAttribute("mensaje2Hor",((Vector)aplicacion.getMensajePorCodigo("AD01","00002")).elementAt(2).toString());
			request.getSession().setAttribute("mensajeHorC",((Vector)aplicacion.getMensajePorCodigo("MH01","AD01")).elementAt(2).toString());
			return mapping.findForward("transaccion.noDisponible");
		}
		
		
		List x=usuario.getCuentas();
		CuentaImpl cuenta1 = (CuentaImpl)x.get(0);
			
		request.getSession().setAttribute("mensajeCondicion",((Vector)aplicacion.getMensajePorCodigo("DA01","00001")).elementAt(2).toString().trim());
		
		List  afil = null;
		
		try{
			afil=FacadeFactory.getAfiliacionFacade().validacionRestric("VR00",  cuenta1.getNumeroProducto(), request.getRemoteAddr(),usuario, request);
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
	
	
	public ActionForward iniciar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		loadObject(request);
		Usuario usuario = (Usuario)SeguridadUtil.getUsuarioSession(request);
		AfiliacionDebitoAutomaticoForm frm = (AfiliacionDebitoAutomaticoForm)form;
		frm.reset(mapping, request);
		request.getSession().setAttribute("mensajeafiliaciondebito",((Vector)aplicacion.getMensajePorCodigo("AD01","AF001")).elementAt(2).toString());
		Afiliacion afiliacion 	= new AfiliacionImpl(); 
		TokenSmsRequest tokenSms = new TokenSmsRequest();
		
//		REALIZAR CONSULTA TIPO CLAVE DINAMICA  --> ADD M.D.B.
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		ParametrosElemSegTDC elementos = null;
		//request.getSession().setAttribute("tipoElemento",null );	
		
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
		afiliacion.setTipoDocumento(Funciones.lpad(tipoDoc,"0",3));
		afiliacion.setNroDocumento(numDoc);	
		afiliacion.setCodCliente(usuario.getCodigoCic());
		//afiliacion.setCodCliente(codCliente);
		
		
		try{
			
			
			elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
			
			if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS) ||
			   elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
				elementos = SolicitarServiciosTDC.verificarTipoElemento(param,usuario);
			}
			
		}catch(ArrayRuleException ar){
			log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
			ar.printStackTrace();
			ar.setForward("verCondiciones");
			throw ar;
		}
		
		if(elementos.getTipoElementoSeguridad().equals("5")){
			System.out.println("Token Fisico");
			usuario.getClaveDinamica().setTipoElementoSeguridad(elementos.getTipoElementoSeguridad());
			usuario.getClaveDinamica().setTipoElementoSeguridadFinal("Token Fisico");
			
		}else if(elementos.getTipoElementoSeguridad().equals("6")){
			System.out.println("Clave Dinamica Digital");
			usuario.getClaveDinamica().setTipoElementoSeguridad(elementos.getTipoElementoSeguridad());
			usuario.getClaveDinamica().setTipoElementoSeguridadFinal("Clave Dinamica Digital");
		}
		
		
//		REALIZAR CONSULTA DE COORDENADAS
		

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
		
		
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);


		return mapping.findForward("inicio");
	}
	

	public ActionForward afiliar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
				
		TokenSmsRequest tokenSms = new TokenSmsRequest();
		
		AfiliacionDebitoAutomaticoForm frm = (AfiliacionDebitoAutomaticoForm)form;
		Usuario usuario = (Usuario)SeguridadUtil.getUsuarioSession(request);
		Tarjeta tarjeta = usuario.getTarjeta(); 
		Afiliacion afiliacion = setearAfiliacion(frm,tarjeta, usuario, request);

		//NOMBRE DEL BENEFICIARIO
		if (usuario.getNombre().length() > 30){
			afiliacion.setBeneficiario(usuario.getNombre().substring(0, 29).trim());
		} else {
			afiliacion.setBeneficiario(usuario.getNombre().trim());
		}
		
		afiliacion.setFecha(pe.cosapi.common.ObjectUtil.getFechaActual());
		afiliacion.setHora(pe.cosapi.common.ObjectUtil.getHHMMSSformateado());
		
		//afiliacion.setClave6Digitos(SeguridadUtil.getClaveDesencriptada(frm.getTxtNumClave(),request));
		
		
		request.getSession().setAttribute("CORREO_AFILIADO",afiliacion.getEmail().trim());
		
		// VERIFICA LAS AFILIACIONES EN BD
		
		List listaAfiliaciones = null;
		
		
		listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaExisteDebito(Constante.AFI_DEBITO_AUTOMATICO,usuario.getTarjeta().getNumero(), Constante.COD_HLP_DET_ENTIDADES_PAGOS_SERVICIOS, afiliacion.getNumSuministro(),afiliacion.getServicio().substring(0, 2));
		request.getSession().setAttribute("tituloAfiliacion","AFILIACION DE SERVICIOS: DEBITO AUTOMATICO");
		request.setAttribute("nomafil","Código de Cliente");
			
	
		request.getSession().setAttribute("CORREO_AFILIADO",afiliacion.getEmail());
		
	
	
		AfiliacionImpl afil_ = new AfiliacionImpl(afiliacion);
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		ElemenSeguridad coord = (ElemenSeguridad)request.getSession().getAttribute("resultCoord");
		
		ElemenSeguridad resultCoord = null;
		
		String pinblock = ObjectUtil.getClaveDesencriptada(request.getParameter("txtCoordenada"),request);
		ParametrosElemSegTDC elementos = null;
		
		//REALIZAR CONSULTA TIPO CLAVE DINAMICA  --> ADD M.D.B.
		
		
		
		//Afiliacion afiliacion = (Afiliacion)request.getSession().getAttribute("afiliacion");
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
		
		try{
			
			
			elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
			
			if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS) ||
			   elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
				elementos = SolicitarServiciosTDC.verificarTipoElemento(param,usuario);
			}
			
			
		}catch(ArrayRuleException ar){
			log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
			ar.printStackTrace();
			ar.setForward("inicio");
			throw ar;
		}
		
		try{
					
			FacadeFactory.getGeneralFacade().validarDatosPersonalesAfiliacion(afiliacion,usuario);
		}
		catch (ArrayRuleException e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			frm.setTxtNumClave("");
			frm.setCmbEmpresa("");
			frm.setTxtNumClave("");
			loadObject(request);
			if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)) {
				resultCoord = SolicitarServiciosTDC.solicitCoordenada(param);
			} else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS)){
				resultCoord=SolicitarServiciosTDC.solicitarTokens(param);
			} 
		
		
			request.getSession().setAttribute("resultCoord",resultCoord );
			e.setForward("inicio");
			throw e;
		}
	
		
		//Verifica Tarjeta de Coordenadas
		
		try {
		
			try{
				
				MensajesTDC resultado = new MensajesTDC();
				
				if(param!= null && coord!=null ){
						
						if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
							resultado=SolicitarServiciosTDC.validarTDC(param ,coord,pinblock);
						} else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS)){
							resultado=SolicitarServiciosTDC.validarTokens(param ,coord,pinblock);
						} else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)){
						
							tokenSms.setCodCliente(afiliacion.getCodCliente());
							tokenSms.setTipoDocumento(afiliacion.getTipoDocumento());
							tokenSms.setNroDocumento(afiliacion.getNroDocumento());
							
							tokenSms.setCodeSMS(pinblock);
							tokenSms.setTypeTrans("40");
							tokenSms.setTypeOp("ADMIN");						
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
				frm.setCmbEmpresa("");
				frm.setCmbServicio("");
				loadObject(request);
				e.setForward("inicio");
				throw e;
			}
			
			
			
			List x=usuario.getCuentas();
			CuentaImpl cuenta1 = (CuentaImpl)x.get(0);
			afiliacion.setCuenta(cuenta1);
			
			String correo = afiliacion.getEmail();
			
			String[] var = correo.split(Constante.SEP_ALPHANUMERIC);
			
			afiliacion.setCorreo(var[0]);
			afiliacion.setServidorCorreo(var[1]);
			
			
			//Verifica Nombre de Empresa y Servicio
			
			List empresa = FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.DEB_AUTOMATICO_EMPRESA );
			
			for(int y=0; y< empresa.size();y++){
				
				DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
				
				dHelp_ = (DetalleAyudaDatosImpl)empresa.get(y);
							
				if(afiliacion.getEmpresa().equals(dHelp_.getCodigoDetalleAyuda()))
				{
					
					afiliacion.setEmpresaMostrar(dHelp_.getDescripcionDetalle());
					
				}
				
			}

			
			List servicio = FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.DEB_AUTOMATICO_SERVICIO );
			
			for(int z=0; z< servicio.size();z++){
				
				DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
				
				dHelp_ = (DetalleAyudaDatosImpl)servicio.get(z);
				
							
				if(afiliacion.getServicio().equals(dHelp_.getCodigoDetalleAyuda1()))
				{
					
					afiliacion.setServicioMostrar(dHelp_.getDescripcionDetalle());
					
				}
				
			}
			
			
			List doc = FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_DET_DOCU_OTRO);
			
			for(int a=0; a< doc.size();a++){
				
				ComboUtil combo = new ComboUtil();
				
				combo = (ComboUtil)doc.get(a);
				
					
				if(afiliacion.getTipoDocumento().equals(combo.getCodigo()))
				{
					
					afiliacion.setDocMostrar(combo.getDescripcion());
										
				}
				
			}
			
			List via = FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.DEB_AUTOMATICO_CONF_VIA );
			
			for(int w=0; w< via.size();w++){
				
				DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
				
				dHelp_ = (DetalleAyudaDatosImpl)via.get(w);
				
							
				if(afiliacion.getViaConfirmacion().equals(dHelp_.getCodigoDetalleAyuda()))
				{
					
					afiliacion.setViaConfMostrar(dHelp_.getDescripcionDetalle());
					
				}
				
			}
			
			//Mostrar datos del telefono de contacto
			
			
			if(!afiliacion.getTipoTelefono().trim().equals("")){
				List contacto = FacadeFactory.getGeneralFacade().getComboDetalleHlpHijoMod(Constante.DEB_AUTOMATICO_TIPO_TELEFONO,afiliacion.getTipoTelefono().trim());
				
				
				if(contacto.size()>0){
					ComboUtil combo = new ComboUtil();
					combo = (ComboUtil)contacto.get(0);
					
					afiliacion.setTelContactoMostrar(combo.getDescripcion());
										
				}
				
			}
			else{
				afiliacion.setTelContactoMostrar("------");
			}
			
			
			
			if(!afiliacion.getNumeroTelefono().trim().equals("")){
				
				afiliacion.setTelNumMostrar(afiliacion.getNumeroTelefono());
			}
			else{
				afiliacion.setTelNumMostrar("------");
				
			}
			
		
			
			if(afiliacion.getMontoMaximoDebito().equals("M")){
				afiliacion.setTope(Constante.DEB_CONSTANTE_DES_CON_TOPE);
								
			}
			else{
				afiliacion.setTope(Constante.DEB_CONSTANTE_DES_SIN_TOPE);
				
			}
			
//			Verifica el monto de Debito
			
			if(afiliacion.getMontoMaximoDebito().equals(Constante.DEB_CONSTANTE_SIN_TOPE)){
				
				afiliacion.setMontoDebito("------");
				
			}else{
				afiliacion.setMontoDebito(""+(ObjectUtil.tramaToBigDecimal(afiliacion.getMaximo())));
				
			}
		
			
			// AFILIA VIA TRAMA
			
			Afiliacion  afil = null;
			
			afil=FacadeFactory.getAfiliacionFacade().getAfiliacionDA("AD01", afiliacion, "0", request.getRemoteAddr(),usuario, request);
			afiliacion.setNroOperacion(afil.getNroOperacion());
			
			// GUERDAR EN LA BD
			Afiliacion af=FacadeFactory.getAfiliacionFacade().afiliar(usuario,afiliacion);
			afiliacion.setCuenta(af.getCuenta());
			
			
			
			request.getSession().setAttribute(ConstanteSesion.AFILIAR,afiliacion);
			Map map = UtilAction.cargarVar(request,afiliacion);
			OperacionImpl.setVariables(map);
		
			
			loadObject(request);
			
			ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_AFILIACION_DEBITO_AUTO_AFI, ConstanteSesion.AFILIAR, request);
		}
		catch (ArrayRuleException e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			loadObject(request);
			frm.setTxtNumClave("");
			frm.setCmbEmpresa("");
			frm.setTxtNumClave("");
			throw e;
		}
		
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		return mapping.findForward("afiliar");
	}
	
	
	public ActionForward iniciarDesafiliacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		loadObject(request);
		AfiliacionDebitoAutomaticoForm frm = (AfiliacionDebitoAutomaticoForm)form;
		frm.reset(mapping, request);
		Usuario usuario = (Usuario)SeguridadUtil.getUsuarioSession(request);
		
		ParametrosElemSegTDC elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento"); //5,6,7
        
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
		
		if(!getVerificarHorario("AD01")){
			request.getSession().setAttribute("mensaje1Hor",((Vector)aplicacion.getMensajePorCodigo("AD01","00001")).elementAt(2).toString());
			request.getSession().setAttribute("mensaje2Hor",((Vector)aplicacion.getMensajePorCodigo("AD01","00002")).elementAt(2).toString());
			request.getSession().setAttribute("mensajeHorC",((Vector)aplicacion.getMensajePorCodigo("MH01","AD01")).elementAt(2).toString());
			return mapping.findForward("transaccion.noDisponible");
		}
		
		List  afil = null;
		
		//Trae datos del usuario
		request.getSession().setAttribute("mensajeCondicion",((Vector)aplicacion.getMensajePorCodigo("DA01","00001")).elementAt(2).toString().trim());
		
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
			afil=FacadeFactory.getAfiliacionFacade().validacionRestric("VR00", cuenta1.getNumeroProducto(), request.getRemoteAddr(),usuario, request);
		}
		catch (ArrayRuleException e) {
			e.printStackTrace();
			request.setAttribute("flag", "0");
			e.setForward("iniciarDesafiliacion");
			throw e;
		}
		

		return mapping.findForward("iniciarDesafiliacion");
	}
	
	public ActionForward consultaAfiliacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		

		AfiliacionDebitoAutomaticoForm frm = (AfiliacionDebitoAutomaticoForm)form;
		Usuario usuario = (Usuario)SeguridadUtil.getUsuarioSession(request);
		Tarjeta tarjeta = usuario.getTarjeta(); 
		Afiliacion afiliacion = setearAfiliacion(frm,tarjeta, usuario, request);
		afiliacion.setBeneficiario(usuario.getNombre());
		
	
		
		try {
			
	
			if(request.getSession().getAttribute("afiliaciones") != null){
				
				request.getSession().setAttribute("afiliaciones",null);
				
			}
			
//			REALIZAR CONSULTA TIPO CLAVE DINAMICA  --> ADD M.D.B.
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
				ar.setForward("iniciarDesafiliacion");
				throw ar;
			}
			
			
//			REALIZAR CONSULTA DE COORDENADAS
			

			ElemenSeguridad resultCoord = null;
			
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
					ar.setForward("iniciarDesafiliacion");
					throw ar;
				}
				request.getSession().setAttribute("resultCoord",resultCoord );
			}
			else{
				
				throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
			}

			


			request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
			
			List x=usuario.getCuentas();
			CuentaImpl cuenta1 = (CuentaImpl)x.get(0);
			afiliacion.setCuenta(cuenta1);
			
			List afil = null;
			List reg = null;
			List tramite = null;

			
					try{
						//CONSULTA VIA TRAMA - REGISTRADOS
							reg = FacadeFactory.getAfiliacionFacade().getConsultaDA("AD02", afiliacion, "8", request.getRemoteAddr());
					}
					
					catch (ArrayRuleException e) {
						log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
						e.printStackTrace();
						e.setForward("consultaAfiliacion");
						//throw e;
					}
			
					try {
						
						//CONSULTA VIA TRAMA - EN TRAMITE
						tramite = FacadeFactory.getAfiliacionFacade().getConsultaDA("AD02", afiliacion, "6", request.getRemoteAddr());
							
					} catch (ArrayRuleException e) {
						log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
						e.printStackTrace();
						e.setForward("consultaAfiliacion");
					}
		
					try {
						//CONSULTA VIA TRAMA - AFILIADOS
							afil = FacadeFactory.getAfiliacionFacade().getConsultaDA("AD02", afiliacion, "9", request.getRemoteAddr());
		
						
					} catch (ArrayRuleException e) {
						log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
						e.printStackTrace();
						e.setForward("consultaAfiliacion");
					}

			
			//SE OBTIENE LOS DATOS DE LA EMPRESA/SERVICIO
			
				
			List empresa = FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.DEB_AUTOMATICO_EMPRESA );
			List servicio = FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.DEB_AUTOMATICO_SERVICIO );
			
			
			//MOSTRAR INFORMACION DE AFILIADOS POR DESCRIPCION		
			if(afil!= null){
			for(int i=0; i< afil.size();i++){
			
				AfiliacionesImpl afiliaciones = new AfiliacionesImpl();
				afiliaciones = (AfiliacionesImpl)afil.get(i);
						
				
				if(afiliaciones.getFlagEstado().equals(Constante.DEB_CONSTANTE_FLAG_PRE_AFIL)){
					
					afiliaciones.setFlagMostrarEstado(Constante.DEB_CONSTANTE_DES_FLAG_PRE_AFIL);
				}
				else{
					if(afiliaciones.getFlagEstado().equals(Constante.DEB_CONSTANTE_FLAG_EN_TRAM)){
						afiliaciones.setFlagMostrarEstado(Constante.DEB_CONSTANTE_DES_EN_TRAM);
					}
					else{
						afiliaciones.setFlagMostrarEstado(Constante.DEB_CONSTANTE_DES_FLAG_AFIL);
					}
					
					
					
				}
						
				
				for(int y=0; y< empresa.size();y++){
					
					DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
					
					dHelp_ = (DetalleAyudaDatosImpl)empresa.get(y);
								
					if(afiliaciones.getEmpresa().trim().equals(dHelp_.getCodigoDetalleAyuda()))
					{
						
						afiliaciones.setEmpresaMostrar(dHelp_.getDescripcionDetalle());
					
						
					}
					
				}
				
				
				for(int z=0; z< servicio.size();z++){
					
					DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
					afiliaciones = (AfiliacionesImpl)afil.get(i);
					
					dHelp_ = (DetalleAyudaDatosImpl)servicio.get(z);
					
								
					if(afiliaciones.getServicio().trim().equals(dHelp_.getCodigoDetalleAyuda1()))
					{
						
						afiliaciones.setServiciomostrar(dHelp_.getDescripcionDetalle());
						
					}
					
				}
						
			}
		}
			
			//MOSTRAR INFORMACION EN TRAMITE POR DESCRIPCION	
			
			if(tramite!= null){
				
				
			
			
			for(int i=0; i< tramite.size();i++){
			
				AfiliacionesImpl afiliaciones = new AfiliacionesImpl();
				afiliaciones = (AfiliacionesImpl)tramite.get(i);
						
				
				if(afiliaciones.getFlagEstado().equals(Constante.DEB_CONSTANTE_FLAG_PRE_AFIL)){
					
					afiliaciones.setFlagMostrarEstado(Constante.DEB_CONSTANTE_DES_FLAG_PRE_AFIL);
				}
				else{
					if(afiliaciones.getFlagEstado().equals(Constante.DEB_CONSTANTE_FLAG_EN_TRAM)){
						afiliaciones.setFlagMostrarEstado(Constante.DEB_CONSTANTE_DES_EN_TRAM);
					}
					else{
						afiliaciones.setFlagMostrarEstado(Constante.DEB_CONSTANTE_DES_FLAG_AFIL);
					}
					
					
					
				}
						
				
				for(int y=0; y< empresa.size();y++){
					
					DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
					afiliaciones = (AfiliacionesImpl)tramite.get(i);
					dHelp_ = (DetalleAyudaDatosImpl)empresa.get(y);
								
					if(afiliaciones.getEmpresa().trim().equals(dHelp_.getCodigoDetalleAyuda()))
					{
						
						afiliaciones.setEmpresaMostrar(dHelp_.getDescripcionDetalle());
					
						
					}
					
				}
				
				
				for(int z=0; z< servicio.size();z++){
					
					DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
					afiliaciones = (AfiliacionesImpl)tramite.get(i);
					
					dHelp_ = (DetalleAyudaDatosImpl)servicio.get(z);
					
								
					if(afiliaciones.getServicio().trim().equals(dHelp_.getCodigoDetalleAyuda1()))
					{
						
						afiliaciones.setServiciomostrar(dHelp_.getDescripcionDetalle());
						
					}
					
				}
			  }	
			}
			
			
			//MOSTRAR INFORMACION REGISTRADOS POR DESCRIPCION	
			
			if(reg!= null){
				
				
			
			
			for(int i=0; i< reg.size();i++){
			
				AfiliacionesImpl afiliaciones = new AfiliacionesImpl();
				afiliaciones = (AfiliacionesImpl)reg.get(i);
						
				
				if(afiliaciones.getFlagEstado().equals(Constante.DEB_CONSTANTE_FLAG_PRE_AFIL)){
					
					afiliaciones.setFlagMostrarEstado(Constante.DEB_CONSTANTE_DES_FLAG_PRE_AFIL);
				}
				else{
					if(afiliaciones.getFlagEstado().equals(Constante.DEB_CONSTANTE_FLAG_EN_TRAM)){
						afiliaciones.setFlagMostrarEstado(Constante.DEB_CONSTANTE_DES_EN_TRAM);
					}
					else{
						afiliaciones.setFlagMostrarEstado(Constante.DEB_CONSTANTE_DES_FLAG_AFIL);
					}
					
					
					
				}
						
				
				for(int y=0; y< empresa.size();y++){
					
					DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
					afiliaciones = (AfiliacionesImpl)reg.get(i);
					dHelp_ = (DetalleAyudaDatosImpl)empresa.get(y);
								
					if(afiliaciones.getEmpresa().trim().equals(dHelp_.getCodigoDetalleAyuda()))
					{
						
						afiliaciones.setEmpresaMostrar(dHelp_.getDescripcionDetalle());
					
						
					}
					
				}
				
				
				for(int z=0; z< servicio.size();z++){
					
					DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
					afiliaciones = (AfiliacionesImpl)reg.get(i);
					
					dHelp_ = (DetalleAyudaDatosImpl)servicio.get(z);
					
								
					if(afiliaciones.getServicio().trim().equals(dHelp_.getCodigoDetalleAyuda1()))
					{
						
						afiliaciones.setServiciomostrar(dHelp_.getDescripcionDetalle());
						
					}
					
				}
			  }	
			}
			
			
			
			request.getSession().setAttribute("afiliaciones", afil);
			request.getSession().setAttribute("afiliacionesTram", tramite);
			request.getSession().setAttribute("afiliacionesReg", reg);
			

		}
		
		catch (ArrayRuleException e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			loadObject(request);
			e.setForward("iniciarDesafiliacion");
			throw e;
			
		}
			

		return mapping.findForward("consultaAfiliacion");
	}
	
	
	public ActionForward comprobarDesafil(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		

		AfiliacionDebitoAutomaticoForm frm = (AfiliacionDebitoAutomaticoForm)form;
		Usuario usuario = (Usuario)SeguridadUtil.getUsuarioSession(request);
		Tarjeta tarjeta = usuario.getTarjeta(); 
		ActionForward forward = new ActionForward();
		loadObject(request);
		Afiliacion afiliacion = new AfiliacionImpl();
		
		try {
			
			
			String optSecuencia = request.getParameter("optSecuencia");
			
			request.getSession().setAttribute("optSecuencia", optSecuencia);
			
			
			
			String[] id = ObjectUtil.getArrayStrings(request.getParameter("optSecuencia"),"-");
			
			if(id.length==0){
			
				throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_NO_HAY_LISTA);
				
			}
			else{
				
				afiliacion.setEmpresa(id[0].trim());
				afiliacion.setServicio(id[1].trim());
				afiliacion.setNumSuministro(id[2].trim());
				afiliacion.setFlagEstado(id[3].trim());
				afiliacion.setMontoMaximoDebito(id[10].trim());
				afiliacion.setMaximo(id[11].trim());
				
					
				if(afiliacion.getMontoMaximoDebito().equals(Constante.DEB_CONSTANTE_SIN_TOPE_HOST)){
					
					afiliacion.setMontoMaxMostrar(Constante.DEB_CONSTANTE_DES_SIN_TOPE);
					
				}else{
					
					if(afiliacion.getMontoMaximoDebito().equals(Constante.DEB_CONSTANTE_CON_TOPE_HOST)){
						
						afiliacion.setMontoMaxMostrar(Constante.DEB_CONSTANTE_DES_CON_TOPE+"/"+afiliacion.getMaximo());
						
					}
				}
		
												
			}
			
			List empresa = FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.DEB_AUTOMATICO_EMPRESA );
			List servicio = FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.DEB_AUTOMATICO_SERVICIO );


					for(int y=0; y< empresa.size();y++){
							
							DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
							
							dHelp_ = (DetalleAyudaDatosImpl)empresa.get(y);
										
							if(afiliacion.getEmpresa().trim().equals(dHelp_.getCodigoDetalleAyuda()))
							{
								
								afiliacion.setEmpresaMostrar(dHelp_.getDescripcionDetalle());
							
								
							}
							
						}

					for(int z=0; z< servicio.size();z++){
							
							DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
						
							
							dHelp_ = (DetalleAyudaDatosImpl)servicio.get(z);
							
										
							if(afiliacion.getServicio().trim().equals(dHelp_.getCodigoDetalleAyuda1()))
							{
								
								afiliacion.setServicioMostrar(dHelp_.getDescripcionDetalle());
								
							}
							
						}
			
			
			request.getSession().setAttribute("afiliacion",afiliacion);
			
//			REALIZAR CONSULTA TIPO CLAVE DINAMICA  --> ADD M.D.B.
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
				ar.setForward("iniciarDesafiliacion");
				throw ar;
			}
			
			
//			REALIZAR CONSULTA DE COORDENADAS
			

			ElemenSeguridad resultCoord = null;
			
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
					ar.setForward("iniciarDesafiliacion");
					throw ar;
				}
				request.getSession().setAttribute("resultCoord",resultCoord );
			}
			else{
				
				throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
			}

			


			request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		
		
			

		}
		
		catch (ArrayRuleException e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			loadObject(request);
			e.setForward("iniciarDesafiliacion");
			throw e;
			
		}
		
		if(afiliacion.getFlagEstado().equals("8"))
		{
			forward = mapping.findForward("comprobarAnula");			
		}
		else{
			forward = mapping.findForward("comprobarDesafilia");
		}
			
	
		return  (forward);
	}
	
	
	
	public ActionForward desafiliacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		TokenSmsRequest tokenSms = new TokenSmsRequest();
		
		AfiliacionDebitoAutomaticoForm frm = (AfiliacionDebitoAutomaticoForm)form;
		ActionForward forward = new ActionForward();

	
		Usuario usuario = (Usuario)SeguridadUtil.getUsuarioSession(request);
		Tarjeta tarjeta = usuario.getTarjeta(); 
		//Afiliacion afiliacion = setearAfiliacion(frm,tarjeta, usuario, request);
		
		Afiliacion afiliacion = new AfiliacionImpl();
		
		
		
		
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
		
		
		
		afiliacion.setTipoAfiliacion(Constante.AFI_DEBITO_AUTOMATICO);
		afiliacion.setNroTarjeta(tarjeta.getNumero());
		afiliacion.setTarjetaOculta(ObjectUtil.ocultarCuenta(tarjeta.getNumero(),Constante.FORMATO_TARJETA));
		afiliacion.setBeneficiario(usuario.getNombre());
		afiliacion.setFecha(pe.cosapi.common.ObjectUtil.getFechaActual());
		afiliacion.setHora(pe.cosapi.common.ObjectUtil.getHHMMSSformateado());
		
		String[] id = ObjectUtil.getArrayStrings(request.getParameter("optSecuencia"),"-");
		
		if(id.length>0){
		
			//Setear los valores de la consulta	
			afiliacion.setEmpresa(id[0].trim());
		
			afiliacion.setServicio(id[1].trim());
			afiliacion.setNumSuministro(id[2].trim());
			afiliacion.setFlagEstado(id[3].trim());
		
				
			afiliacion.setTipoDocumento(Funciones.lpad(id[4].trim(),"0",3));
			afiliacion.setNroDocumento(id[5].trim());
			
			
			if(afiliacion.getFlagEstado().equals("8")){
				afiliacion.setTipoTelefono(Funciones.lpad(id[6].trim(),"0",2));
				afiliacion.setNumeroTelefono(""+Long.parseLong(id[7].trim()));
				afiliacion.setEmail(id[8].trim());
				afiliacion.setViaConfirmacion(id[9].trim());
		
				
			}else{
				afiliacion.setTipoTelefono(frm.getCmbTipoTelefono());
				afiliacion.setEmail(frm.getTxtMail());
				afiliacion.setViaConfirmacion(frm.getCmbConfirmacion());
				String dis = "";
				
				if(afiliacion.getTipoTelefono().equals("01")){
					if(frm.getCmbDiscado() == null) dis = "";
					else dis = frm.getCmbDiscado().trim();
					
					afiliacion.setNumeroTelefono(dis+frm.getTxtNumeroTelefono());
					
				}
				
				else{
					
					afiliacion.setNumeroTelefono(frm.getTxtNumeroTelefono());
				}
				
			}
			
		String correo = afiliacion.getEmail();
			
			String[] var = correo.split(Constante.SEP_ALPHANUMERIC);
			
			afiliacion.setCorreo(var[0]);
			afiliacion.setServidorCorreo(var[1]);
		}
		
		try{
		
	
		
				request.getSession().setAttribute("CORREO_AFILIADO",afiliacion.getEmail().toLowerCase());
		
				
				ElemenSeguridad resultCoord = null;
				
				String pinblock = ObjectUtil.getClaveDesencriptada(request.getParameter("txtCoordenada"),request);
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
					ar.setForward("comprobarDesafilia");
					throw ar;
				}
			
				
				MensajesTDC resultado = new MensajesTDC();
				
				if(param!= null && coord!=null ){
					
					
					
					try{
						
						if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){					
							resultado=SolicitarServiciosTDC.validarTDC(param ,coord,pinblock);						
						}else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS)){
							resultado=SolicitarServiciosTDC.validarTokens(param ,coord,pinblock);
						}else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)){
						
							tokenSms.setCodCliente(afiliacion.getCodCliente());
							tokenSms.setTipoDocumento(afiliacion.getTipoDocumento());
							tokenSms.setNroDocumento(afiliacion.getNroDocumento());

							tokenSms.setCodeSMS(pinblock);
							tokenSms.setTypeTrans("40");
							tokenSms.setTypeOp("ADMIN");
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

						if(afiliacion.getFlagEstado().equals("8"))
						{
							e.setForward("comprobarAnula");
						}
						else{
							e.setForward("comprobarDesafilia");
						}
						throw e;
					}
				}
		
				
			//Enviar Mail
			
			request.getSession().setAttribute("CORREO_AFILIADO",afiliacion.getEmail().toLowerCase());
		
		
		List x=usuario.getCuentas();
		CuentaImpl cuenta1 = (CuentaImpl)x.get(0);
		afiliacion.setCuenta(cuenta1);
		
		
		//MOSTRAR VALORES
		
	List empresa = FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.DEB_AUTOMATICO_EMPRESA );
		
		for(int y=0; y< empresa.size();y++){
			
			DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
			
			dHelp_ = (DetalleAyudaDatosImpl)empresa.get(y);
			
									
			if(afiliacion.getEmpresa().trim().equals(dHelp_.getCodigoDetalleAyuda()))
			{
				
				afiliacion.setEmpresaMostrar(dHelp_.getDescripcionDetalle());
				
			}
			
		}

		
		List servicio = FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.DEB_AUTOMATICO_SERVICIO );
		
		for(int z=0; z< servicio.size();z++){
			
			DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
			
			dHelp_ = (DetalleAyudaDatosImpl)servicio.get(z);
					
						
			if(afiliacion.getServicio().trim().equals(dHelp_.getCodigoDetalleAyuda1()))
			{
				
				afiliacion.setServicioMostrar(dHelp_.getDescripcionDetalle());
				
			}
			
		}
		
		
		List doc = FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_DET_DOCU_OTRO);
		
		for(int a=0; a< doc.size();a++){
			
			ComboUtil combo = new ComboUtil();
			
			combo = (ComboUtil)doc.get(a);
			
				
			if(afiliacion.getTipoDocumento().equals(combo.getCodigo()))
			{
				
				afiliacion.setDocMostrar(combo.getDescripcion());
									
			}
			
		}
		
		List via = FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.DEB_AUTOMATICO_CONF_VIA );
		
		for(int w=0; w< via.size();w++){
			
			DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
			
			dHelp_ = (DetalleAyudaDatosImpl)via.get(w);
			
						
			if(afiliacion.getViaConfirmacion().equals(dHelp_.getCodigoDetalleAyuda()))
			{
				
				afiliacion.setViaConfMostrar(dHelp_.getDescripcionDetalle());
				
			}
			
		}
		
		
		if(!afiliacion.getTipoTelefono().trim().equals("00")){
			List contacto = FacadeFactory.getGeneralFacade().getComboDetalleHlpHijoMod(Constante.DEB_AUTOMATICO_TIPO_TELEFONO,afiliacion.getTipoTelefono().trim());
			
			
			if(contacto.size()>0){
				ComboUtil combo = new ComboUtil();
				combo = (ComboUtil)contacto.get(0);
				
				afiliacion.setTelContactoMostrar(combo.getDescripcion());
									
			}
			
		}
		else{
			afiliacion.setTelContactoMostrar("------");
		}
		
		
		
		if(!afiliacion.getNumeroTelefono().trim().equals("0") && afiliacion.getNumeroTelefono().trim() != ""){
			
			afiliacion.setTelNumMostrar(afiliacion.getNumeroTelefono());
		}
		else{
			afiliacion.setTelNumMostrar("------");
			
		}
		
		
	
		//DESAFILIAR VIA TRAMA
	
		Afiliacion  afil = null;
		
		
		afil=FacadeFactory.getAfiliacionFacade().getDesafiliacionDA("AD03", afiliacion, "2", request.getRemoteAddr(),usuario, request); 
		afiliacion.setNroOperacion(afil.getNroOperacion());
		
	
		
//		TRAER DATOS DE BD
		List listaAfiliaciones = null;
		
		listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaExisteDebito(Constante.AFI_DEBITO_AUTOMATICO,usuario.getTarjeta().getNumero(), Constante.COD_HLP_DET_ENTIDADES_PAGOS_SERVICIOS, afiliacion.getNumSuministro(),afiliacion.getServicio().substring(0, 2));
		
		
		if(listaAfiliaciones.size()>0)
		{
			AfiliacionImpl result = (AfiliacionImpl)listaAfiliaciones.get(0);
			afiliacion.setSecuencia(result.getSecuencia());
			
			//DESAFILIAR EN BD

			FacadeFactory.getAfiliacionFacade().desafiliarDebito(afiliacion);

		}
		
		
		if(afiliacion.getFlagEstado().equals(Constante.DEB_CONSTANTE_FLAG_PRE_AFIL)){
			forward = mapping.findForward("finAnular");
		}
		else{
			forward = mapping.findForward("finDesafiliar");
		}
		
		request.setAttribute("flagEstado",afiliacion.getFlagEstado());
			
	
		request.getSession().setAttribute(ConstanteSesion.AFILIAR,afiliacion);
		Map map = UtilAction.cargarVar(request,afiliacion);
		OperacionImpl.setVariables(map);
		
		if(afiliacion.getFlagEstado().equals(Constante.DEB_CONSTANTE_FLAG_PRE_AFIL)){
			ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_AFILIACION_DEBITO_AUTO_ANU, ConstanteSesion.AFILIAR, request);
		}
		else{
			ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_AFILIACION_DEBITO_AUTO_DES, ConstanteSesion.AFILIAR, request);
		}
		
		}catch (ArrayRuleException e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			loadObject(request);
			frm.setTxtNumClave("");

			if(afiliacion.getFlagEstado().equals("8"))
			{
				e.setForward("comprobarAnula");
			}
			else{
				e.setForward("comprobarDesafilia");
			}
			
			throw e;
		}
	


		return forward;
	}

	private void loadObject(HttpServletRequest request)throws Exception {
		List lista= FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_DET_DOCU_OTRO);
	
		
		lista.remove(0);
		request.setAttribute("lstDocumento",lista);
		
		request.setAttribute("lstTipoCuenta", FacadeFactory.getGeneralFacade().getComboTipoCuenta());
		request.setAttribute("lstMes", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.COD_HLP_DET_MES));
		
		request.setAttribute("lstTipoTelefono", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.DEB_AUTOMATICO_TIPO_TELEFONO));
		request.setAttribute("lstEmpresa", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.DEB_AUTOMATICO_EMPRESA ));
		request.setAttribute("lstServicio",FacadeFactory.getGeneralFacade().getComboDetalleAyuda1(Constante.DEB_AUTOMATICO_SERVICIO));
		request.setAttribute("lstConfirmacion",FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.DEB_AUTOMATICO_CONF_VIA));
		request.setAttribute("lstDiscado",FacadeFactory.getGeneralFacade().getComboDetalleAyudaDiscado(Constante.DEB_AUTOMATICO_DISCADO));
		
		
			
	}
	
	private Afiliacion setearAfiliacion(AfiliacionDebitoAutomaticoForm frm,Tarjeta tarjeta, Usuario usuario, HttpServletRequest request) throws Exception{
		Afiliacion afiliacion = new AfiliacionImpl();
		try{
	
		
		
		afiliacion.setTipoAfiliacion(Constante.AFI_DEBITO_AUTOMATICO);
		afiliacion.setNroTarjeta(tarjeta.getNumero());
		afiliacion.setTarjetaOculta(ObjectUtil.ocultarCuenta(tarjeta.getNumero(),Constante.FORMATO_TARJETA));
		afiliacion.setTipoDocumento(frm.getCmbTipoDocIdent());
		afiliacion.setViaConfirmacion(frm.getCmbConfirmacion());
		afiliacion.setNroDocumento(frm.getTxtNumDoc());
		if (!frm.getTxtDia().equals(""))afiliacion.setFechaNacimiento(ObjectUtil.stringToTimestamp(frm.getTxtDia(),frm.getCmbMes(),frm.getTxtAnio()));
		afiliacion.setSexo(frm.getRdnSexo().trim());
		afiliacion.setEmail(frm.getTxtMail());
		afiliacion.setNumSuministro(frm.getCmbCodigoUsuarioSumi());
		afiliacion.setEmpresa(frm.getCmbEmpresa());
		afiliacion.setServicio(frm.getCmbServicio());
		
		
		afiliacion.setTipoTelefono(frm.getCmbTipoTelefono());
		afiliacion.setMontoMaximoDebito(frm.getRdnMontoMaximoDebito());
		
	
		if(afiliacion.getMontoMaximoDebito().equals(Constante.DEB_CONSTANTE_SIN_TOPE)){
			
			afiliacion.setMaximo(Constante.DEB_CONSTANTE_MONTO_MAXIMO);
			
		}else{
			if(frm.getTxtMaximo()!=null && !frm.getTxtMaximo().equals("")){
				BigDecimal monto = new BigDecimal(frm.getTxtMaximo());
				afiliacion.setMaximo(pe.cosapi.common.ObjectUtil.formatearMontoTrama(monto));
			}
					
		}
	
				
		String dis = "";
		
		if(afiliacion.getTipoTelefono().equals("01")){
			if(frm.getCmbDiscado() == null) dis = "";
			else dis = frm.getCmbDiscado().trim();
			
			afiliacion.setNumeroTelefono(dis+frm.getTxtNumeroTelefono());
			
		}
		
		else{
			
			afiliacion.setNumeroTelefono(frm.getTxtNumeroTelefono());
		}
		
			
	
		afiliacion.setServidorEmail(frm.getTxtServidorMail());
		afiliacion.setTipoOperacion(frm.getTxtTipoOperacion());
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
	
	public ActionForward iniciarMod(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		loadObject(request);
		AfiliacionDebitoAutomaticoForm frm = (AfiliacionDebitoAutomaticoForm)form;
		frm.reset(mapping, request);
        Usuario usuario = (Usuario)SeguridadUtil.getUsuarioSession(request);
        
		ParametrosElemSegTDC elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento"); //5,6,7
        
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
		
		if(!getVerificarHorario("AD01")){
			request.getSession().setAttribute("mensaje1Hor",((Vector)aplicacion.getMensajePorCodigo("AD01","00001")).elementAt(2).toString());
			request.getSession().setAttribute("mensaje2Hor",((Vector)aplicacion.getMensajePorCodigo("AD01","00002")).elementAt(2).toString());
			request.getSession().setAttribute("mensajeHorC",((Vector)aplicacion.getMensajePorCodigo("MH01","AD01")).elementAt(2).toString());
			return mapping.findForward("transaccion.noDisponible");
		}
		
		List  afil = null;
		
		//Trae datos del usuario
		request.getSession().setAttribute("mensajeCondicion",((Vector)aplicacion.getMensajePorCodigo("DA01","00001")).elementAt(2).toString().trim());
		
		//Trae el número de la cuenta
		List x=usuario.getCuentas();
		CuentaImpl cuenta1 = (CuentaImpl)x.get(0);
		
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
			afil=FacadeFactory.getAfiliacionFacade().validacionRestric("VR00", cuenta1.getNumeroProducto(), request.getRemoteAddr(),usuario, request);
		}
		catch (ArrayRuleException e) {
			e.printStackTrace();
			request.setAttribute("flagMod", "0");
			e.setForward("iniciarModificacion");
			throw e;
		}
		

		return mapping.findForward("iniciarModificacion");
	}
	
	
	
	public ActionForward consultaModificacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		

		AfiliacionDebitoAutomaticoForm frm = (AfiliacionDebitoAutomaticoForm)form;
		Usuario usuario = (Usuario)SeguridadUtil.getUsuarioSession(request);
		Tarjeta tarjeta = usuario.getTarjeta(); 
		Afiliacion afiliacion = setearAfiliacion(frm,tarjeta, usuario, request);
		afiliacion.setBeneficiario(usuario.getNombre());
		
	
		
		try {
						
		
			List x=usuario.getCuentas();
			CuentaImpl cuenta1 = (CuentaImpl)x.get(0);
			afiliacion.setCuenta(cuenta1);
			
			List afil = null;
			List reg = null;
			List tramite = null;

			
					try{
						// CONSULTA VIA TRAMA - REGISTRADOS
					
						reg = FacadeFactory.getAfiliacionFacade().getConsultaDA(Constante.CONSULT_DEBITO_AUTOMATICO, afiliacion, "8", request.getRemoteAddr());
								
								}
				
					catch (ArrayRuleException e) {
						log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
						
				
						request.getSession().setAttribute("afiliacionesReg", "");
						e.printStackTrace();
					}

			
					try{
							
						// CONSULTA VIA TRAMA - AFILIADOS
					
						afil = FacadeFactory.getAfiliacionFacade().getConsultaDA(Constante.CONSULT_DEBITO_AUTOMATICO, afiliacion, "9", request.getRemoteAddr());

					}
			
					catch (ArrayRuleException e) {
						log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
						e.printStackTrace();
						request.getSession().setAttribute("afiliaciones", "");
				
					}

			
		
			
			//SE OBTIENE LOS DATOS DE LA EMPRESA/SERVICIO
			
				
			List empresa = FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.DEB_AUTOMATICO_EMPRESA );
			List servicio = FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.DEB_AUTOMATICO_SERVICIO );
			
			
			//MOSTRAR INFORMACION DE AFILIADOS POR DESCRIPCION		
			if(afil!= null){
			for(int i=0; i< afil.size();i++){
			
				AfiliacionesImpl afiliaciones = new AfiliacionesImpl();
				afiliaciones = (AfiliacionesImpl)afil.get(i);
						
				
				if(afiliaciones.getFlagEstado().equals(Constante.DEB_CONSTANTE_FLAG_PRE_AFIL)){
					
					afiliaciones.setFlagMostrarEstado(Constante.DEB_CONSTANTE_DES_FLAG_PRE_AFIL);
				}
				else{
					if(afiliaciones.getFlagEstado().equals(Constante.DEB_CONSTANTE_FLAG_EN_TRAM)){
						afiliaciones.setFlagMostrarEstado(Constante.DEB_CONSTANTE_DES_EN_TRAM);
					}
					else{
						afiliaciones.setFlagMostrarEstado(Constante.DEB_CONSTANTE_DES_FLAG_AFIL);
					}
				
				}
						
				
				for(int y=0; y< empresa.size();y++){
					
					DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
					
					dHelp_ = (DetalleAyudaDatosImpl)empresa.get(y);
								
					if(afiliaciones.getEmpresa().trim().equals(dHelp_.getCodigoDetalleAyuda()))
					{
						
						afiliaciones.setEmpresaMostrar(dHelp_.getDescripcionDetalle());
					
						
					}
					
				}
				
				
				for(int z=0; z< servicio.size();z++){
					
					DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
					afiliaciones = (AfiliacionesImpl)afil.get(i);
					
					dHelp_ = (DetalleAyudaDatosImpl)servicio.get(z);
					
								
					if(afiliaciones.getServicio().trim().equals(dHelp_.getCodigoDetalleAyuda1()))
					{
						
						afiliaciones.setServiciomostrar(dHelp_.getDescripcionDetalle());
						
					}
					
				}
						
			}
		}
				
			//MOSTRAR INFORMACION REGISTRADOS POR DESCRIPCION	
			
			if(reg!= null){
				
			for(int i=0; i< reg.size();i++){
			
				AfiliacionesImpl afiliaciones = new AfiliacionesImpl();
				afiliaciones = (AfiliacionesImpl)reg.get(i);
						
				
				if(afiliaciones.getFlagEstado().equals(Constante.DEB_CONSTANTE_FLAG_PRE_AFIL)){
					
					afiliaciones.setFlagMostrarEstado(Constante.DEB_CONSTANTE_DES_FLAG_PRE_AFIL);
				}
				else{
					if(afiliaciones.getFlagEstado().equals(Constante.DEB_CONSTANTE_FLAG_EN_TRAM)){
						afiliaciones.setFlagMostrarEstado(Constante.DEB_CONSTANTE_DES_EN_TRAM);
					}
					else{
						afiliaciones.setFlagMostrarEstado(Constante.DEB_CONSTANTE_DES_FLAG_AFIL);
					}
					
					
					
				}
						
				
				for(int y=0; y< empresa.size();y++){
					
					DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
					afiliaciones = (AfiliacionesImpl)reg.get(i);
					dHelp_ = (DetalleAyudaDatosImpl)empresa.get(y);
								
					if(afiliaciones.getEmpresa().trim().equals(dHelp_.getCodigoDetalleAyuda()))
					{
						
						afiliaciones.setEmpresaMostrar(dHelp_.getDescripcionDetalle());
					
						
					}
					
				}
				
				
				for(int z=0; z< servicio.size();z++){
					
					DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
					afiliaciones = (AfiliacionesImpl)reg.get(i);
					
					dHelp_ = (DetalleAyudaDatosImpl)servicio.get(z);
					
								
					if(afiliaciones.getServicio().trim().equals(dHelp_.getCodigoDetalleAyuda1()))
					{
						
						afiliaciones.setServiciomostrar(dHelp_.getDescripcionDetalle());
						
					}
					
				}
			  }	
			}
			
			
			
			request.getSession().setAttribute("afiliaciones", afil);
			request.getSession().setAttribute("afiliacionesReg", reg);
			

		}
		
		catch (ArrayRuleException e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			loadObject(request);
			e.setForward("iniciarModificacion");
			throw e;
			
		}
			

		return mapping.findForward("consultaModificacion");
	}
	
	
	public ActionForward comprobarModific(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		

		AfiliacionDebitoAutomaticoForm frm = (AfiliacionDebitoAutomaticoForm)form;
		Usuario usuario = (Usuario)SeguridadUtil.getUsuarioSession(request);
		Tarjeta tarjeta = usuario.getTarjeta(); 
		ActionForward forward = new ActionForward();
		loadObject(request);
		Afiliacion afiliacion = new AfiliacionImpl();
		
		try {
			
				afiliacion.setEmpresa(request.getParameter("codEntidad"));
				afiliacion.setServicio(request.getParameter("codServicio"));
				afiliacion.setNumSuministro(request.getParameter("numSuministro"));
				afiliacion.setEmail(request.getParameter("email"));
				afiliacion.setTipoDocumento(Funciones.lpad(request.getParameter("tipoDoc").trim(),"0",3));
				afiliacion.setNroDocumento(request.getParameter("numDoc"));
				afiliacion.setFlagEstado(request.getParameter("flagEstado").trim());
				afiliacion.setTipoTelefono(Funciones.lpad(request.getParameter("tipoTel").trim(),"0",2));
				afiliacion.setNumeroTelefono(""+Long.parseLong(request.getParameter("numTel").trim()));
				afiliacion.setViaConfirmacion(request.getParameter("via"));
				afiliacion.setMontoMaximoDebito(frm.getRdnMontoMaximoDebito());
			
				if(afiliacion.getMontoMaximoDebito().equals(Constante.DEB_CONSTANTE_SIN_TOPE)){
					
					afiliacion.setMaximo(Constante.DEB_CONSTANTE_MONTO_MAXIMO);
					
				}else{
					
					if(request.getParameter("txtMaximo")!=null ){
						BigDecimal monto = new BigDecimal(request.getParameter("txtMaximo"));
						
						afiliacion.setMaximo(pe.cosapi.common.ObjectUtil.formatearMontoTrama(monto));
					}
							
				}
				
			
				if(afiliacion.getMontoMaximoDebito().equals(Constante.DEB_CONSTANTE_SIN_TOPE)){
					
					afiliacion.setMontoMaxMostrar(Constante.DEB_CONSTANTE_DES_SIN_TOPE);
					
				}else{
					
					if(afiliacion.getMontoMaximoDebito().equals(Constante.DEB_CONSTANTE_MONTO)){
						
						afiliacion.setMontoMaxMostrar(Constante.DEB_CONSTANTE_DES_CON_TOPE+"/"+ObjectUtil.tramaToBigDecimal(afiliacion.getMaximo(),2));
						
					}
				}
			
			List empresa = FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.DEB_AUTOMATICO_EMPRESA );
			List servicio = FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.DEB_AUTOMATICO_SERVICIO );


					for(int y=0; y< empresa.size();y++){
							
							DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
							
							dHelp_ = (DetalleAyudaDatosImpl)empresa.get(y);
										
							if(afiliacion.getEmpresa().trim().equals(dHelp_.getCodigoDetalleAyuda()))
							{
								
								afiliacion.setEmpresaMostrar(dHelp_.getDescripcionDetalle());
							
								
							}
							
						}

					for(int z=0; z< servicio.size();z++){
							
							DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
						
							
							dHelp_ = (DetalleAyudaDatosImpl)servicio.get(z);
							
										
							if(afiliacion.getServicio().trim().equals(dHelp_.getCodigoDetalleAyuda1()))
							{
								
								afiliacion.setServicioMostrar(dHelp_.getDescripcionDetalle());
								
							}
							
						}
			
			
			request.getSession().setAttribute("modifDA",afiliacion);
			
//			REALIZAR CONSULTA TIPO CLAVE DINAMICA  --> ADD M.D.B.
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
				ar.setForward("comprobarModificacion");
				throw ar;
			}
			
			
//			REALIZAR CONSULTA DE COORDENADAS
			

			ElemenSeguridad resultCoord = null;
			
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
					ar.setForward("comprobarModificacion");
					throw ar;
				}
				request.getSession().setAttribute("resultCoord",resultCoord );
			}
			else{
				
				throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
			}

			


			request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		
		
			

		}
		
		catch (ArrayRuleException e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			loadObject(request);
			e.setForward("comprobarModificacion");
			throw e;
			
		}
		
			forward = mapping.findForward("comprobarModificacion");
	
	
		return  (forward);
	}
	
	
	
	public ActionForward modificar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		AfiliacionDebitoAutomaticoForm frm = (AfiliacionDebitoAutomaticoForm)form;
		ActionForward forward = new ActionForward();
	
	
		Usuario usuario = (Usuario)SeguridadUtil.getUsuarioSession(request);
		Tarjeta tarjeta = usuario.getTarjeta(); 
		Afiliacion afiliacion = new AfiliacionImpl();//setearAfiliacion(frm,tarjeta, usuario, request);
	
		TokenSmsRequest tokenSms = new TokenSmsRequest();
		
		try{
		
			
				

		
				afiliacion = (AfiliacionImpl)request.getSession().getAttribute("modifDA");
				
				afiliacion.setBeneficiario(usuario.getNombre());
				afiliacion.setFecha(pe.cosapi.common.ObjectUtil.getFechaActual());
				afiliacion.setHora(pe.cosapi.common.ObjectUtil.getHHMMSSformateado());
				
				
				afiliacion.setTipoTelefono(frm.getCmbTipoTelefono());
				afiliacion.setEmail(frm.getTxtMail());
				afiliacion.setViaConfirmacion(frm.getCmbConfirmacion());
				String dis = "";
				
				if(afiliacion.getTipoTelefono().equals("01")){
					if(frm.getCmbDiscado() == null) dis = "";
					else dis = frm.getCmbDiscado().trim();
					
					afiliacion.setNumeroTelefono(dis+frm.getTxtNumeroTelefono());
					
				}
				
				else{
					
					afiliacion.setNumeroTelefono(frm.getTxtNumeroTelefono());
				}
				
				String correo = afiliacion.getEmail();
				
				String[] var = correo.split(Constante.SEP_ALPHANUMERIC);
				
				afiliacion.setCorreo(var[0]);
				afiliacion.setServidorCorreo(var[1]);
				
				request.getSession().setAttribute("CORREO_AFILIADO",afiliacion.getEmail());
		
				
				ElemenSeguridad resultCoord = null;
				
				String pinblock = ObjectUtil.getClaveDesencriptada(request.getParameter("txtCoordenada"),request);
				ParametrosElemSegTDC elementos = null;
				ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
				request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
				ElemenSeguridad coord = (ElemenSeguridad)request.getSession().getAttribute("resultCoord");
	
				
			//REALIZAR CONSULTA TIPO CLAVE DINAMICA  --> ADD M.D.B.
				
				
			
//				Afiliacion datosUsuario = new AfiliacionImpl();
//				datosUsuario = FacadeFactory.getGeneralFacade().obtenerDatosPersonalesAfiliacion(usuario);
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
				
				try{
					
					
					elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
					
					if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS) ||
					   elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
						elementos = SolicitarServiciosTDC.verificarTipoElemento(param,usuario);
					}
					
					
				}catch(ArrayRuleException ar){
					log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
					ar.printStackTrace();
					ar.setForward("comprobarModificacion");
					throw ar;
				}
			
				
				MensajesTDC resultado = new MensajesTDC();
				
				if(param!= null && coord!=null ){
					
					
					
					try{
						
						if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){					
							resultado=SolicitarServiciosTDC.validarTDC(param ,coord,pinblock);						
						} else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS)) {							
							resultado=SolicitarServiciosTDC.validarTokens(param ,coord,pinblock);							
						} else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {							
							
							tokenSms.setCodCliente(afiliacion.getCodCliente());
							tokenSms.setTipoDocumento(afiliacion.getTipoDocumento());
							tokenSms.setNroDocumento(afiliacion.getNroDocumento());

							tokenSms.setCodeSMS(pinblock);
							tokenSms.setTypeTrans("40");
							tokenSms.setTypeOp("ADMIN");
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
						
						request.getSession().setAttribute("afiliaciones", request.getSession().getAttribute("afiliaciones"));
						request.getSession().setAttribute("afiliacionesReg",  request.getSession().getAttribute("afiliacionesReg"));
						frm.reset(mapping, request);
						e.setForward("comprobarModificacion");
						throw e;
					}
				}


			//Enviar Mail
			
			request.getSession().setAttribute("CORREO_AFILIADO",afiliacion.getEmail().toLowerCase());
		
		
			List x=usuario.getCuentas();
			CuentaImpl cuenta1 = (CuentaImpl)x.get(0);
			afiliacion.setCuenta(cuenta1);
		
		
		//MOSTRAR VALORES
		
	List empresa = FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.DEB_AUTOMATICO_EMPRESA );
		
		for(int y=0; y< empresa.size();y++){
			
			DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
			
			dHelp_ = (DetalleAyudaDatosImpl)empresa.get(y);
			
									
			if(afiliacion.getEmpresa().trim().equals(dHelp_.getCodigoDetalleAyuda()))
			{
				
				afiliacion.setEmpresaMostrar(dHelp_.getDescripcionDetalle());
				
			}
			
		}

	
		List servicio = FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.DEB_AUTOMATICO_SERVICIO );
		
		for(int z=0; z< servicio.size();z++){
			
			DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
			
			dHelp_ = (DetalleAyudaDatosImpl)servicio.get(z);
					
						
			if(afiliacion.getServicio().trim().equals(dHelp_.getCodigoDetalleAyuda1()))
			{
				
				afiliacion.setServicioMostrar(dHelp_.getDescripcionDetalle());
				
			}
			
		}
		
	
		List doc = FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_DET_DOCU_OTRO);
		
		for(int a=0; a< doc.size();a++){
			
			ComboUtil combo = new ComboUtil();
			
			combo = (ComboUtil)doc.get(a);
			
				
			if(afiliacion.getTipoDocumento().equals(combo.getCodigo()))
			{
				
				afiliacion.setDocMostrar(combo.getDescripcion());
									
			}
			
		}
		
		List via = FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.DEB_AUTOMATICO_CONF_VIA );
		
		for(int w=0; w< via.size();w++){
			
			DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
			
			dHelp_ = (DetalleAyudaDatosImpl)via.get(w);
			
						
			if(afiliacion.getViaConfirmacion().equals(dHelp_.getCodigoDetalleAyuda()))
			{
				
				afiliacion.setViaConfMostrar(dHelp_.getDescripcionDetalle());
				
			}
			
		}
//		
//		
		if(!afiliacion.getTipoTelefono().trim().equals("00")){
			List contacto = FacadeFactory.getGeneralFacade().getComboDetalleHlpHijoMod(Constante.DEB_AUTOMATICO_TIPO_TELEFONO,afiliacion.getTipoTelefono().trim());
			
			
			if(contacto.size()>0){
				ComboUtil combo = new ComboUtil();
				combo = (ComboUtil)contacto.get(0);
				
				afiliacion.setTelContactoMostrar(combo.getDescripcion());
									
			}
			
		}
		else{
			afiliacion.setTelContactoMostrar("------");
		}
		
		
		if(!afiliacion.getNumeroTelefono().trim().equals("0") && afiliacion.getNumeroTelefono().trim() != ""){
			
			afiliacion.setTelNumMostrar(afiliacion.getNumeroTelefono());
		}
		else{
		
			afiliacion.setTelNumMostrar("------");
			
		}
		
		
		// CON TOPE Y SIN TOPE
		if(afiliacion.getMontoMaximoDebito().equals("M")){
				afiliacion.setTope(Constante.DEB_CONSTANTE_DES_CON_TOPE);
								
			}
			else{
				afiliacion.setTope(Constante.DEB_CONSTANTE_DES_SIN_TOPE);
				
			}
		
		//Verifica el monto de Debito
		
			
			if(afiliacion.getMontoMaximoDebito().equals(Constante.DEB_CONSTANTE_SIN_TOPE)){
				
				afiliacion.setMontoDebito("------");
				
			}else{
				afiliacion.setMontoDebito(""+(ObjectUtil.tramaToBigDecimal(afiliacion.getMaximo())));
				
			}
			
		
	
//		//MODIFICAR VIA TRAMA
	
		Afiliacion  afil = null;
			
			try{
				afil=FacadeFactory.getAfiliacionFacade().getModificacionDA(Constante.DESAFI_DEBITO_AUTOMATICO, afiliacion, "3", request.getRemoteAddr(),usuario, request); 
				afiliacion.setNroOperacion(afil.getNroOperacion());
			}
			catch (ArrayRuleException e) {
				e.printStackTrace();
				log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
				e.setForward("comprobarModificacion");
				throw e;
			}
			
			
			request.getSession().setAttribute(ConstanteSesion.AFILIAR,afiliacion);
			
			forward = mapping.findForward("finModificar");	
			
			ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_AFILIACION_DEBITO_AUTO_MOD, ConstanteSesion.AFILIAR, request);
		}
		catch (ArrayRuleException e) {
			
			e.printStackTrace();
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			loadObject(request);
			frm.setTxtNumClave("");
			e.setForward("comprobarModificacion");
			throw e;
		}
		return forward;
	}
	
	
	public ActionForward generarClaveSms(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//System.out.println("ENTROOOOOOOOOOO");
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		loadObject(request);
		TokenSmsRequest tokenSms = new TokenSmsRequest();
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		// Afiliacion afiliacion = (AfiliacionImpl)request.getSession().getAttribute("afiliacion");
		String monto = (String) request.getSession().getAttribute("monto");
		//Afiliacion datosUsuario = new AfiliacionImpl();
		
		AfiliacionDebitoAutomaticoForm frm = (AfiliacionDebitoAutomaticoForm)form;
		frm.reset(mapping, request);

		try {

			

			Afiliacion afiliacion 	= new AfiliacionImpl(); 
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
			//afiliacion.setCodCliente(codCliente);

			// REALIZAR CONSULTA TIPO CLAVE DINAMICA --> ADD M.D.B.
			ParametrosTDC param = (ParametrosTDC) request.getSession()
					.getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
			request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION, param);
			ParametrosElemSegTDC elementos = null;

			elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
			request.getSession().setAttribute("tipoElemento", elementos);

			//REALIZAR CONSULTA DE COORDENADAS
			ElemenSeguridad resultCoord = null;
			request.getSession().removeAttribute("exitoGeneraClvSms");  
			
			if (param != null) {
				resultCoord = new ElemenSeguridad();
				request.getSession().setAttribute("resultCoord", null);

				if (elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {
					
					tokenSms.setCodCliente(afiliacion.getCodCliente());
					tokenSms.setTipoDocumento(afiliacion.getTipoDocumento());
					tokenSms.setNroDocumento(afiliacion.getNroDocumento());
					
					tokenSms.setTypeTrans("40");
					tokenSms.setTypeOp("ADMIN");						
					tokenSms.setMsgClient("Generacion de Clave Dinamica Digital");
					tokenSms.setTypeCurrency("PEN");
					
					MensajesTDC resultado = UtilAction.generarClaveSms(tokenSms);
					
					
			
					request.getSession().setAttribute("exitoGeneraClvSms", resultado.isExito());  
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
			
			throw e;
		}
		//System.out.println(request.getSession().getAttribute("exitoGeneraClvSms"));
		return mapping.findForward("inicio");

	}
	
	public ActionForward reGenerarClaveSms(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//System.out.println("ENTROOOOOOOOOOO");
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		loadObject(request);
		TokenSmsRequest tokenSms = new TokenSmsRequest();
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		// Afiliacion afiliacion = (AfiliacionImpl)request.getSession().getAttribute("afiliacion");
		String monto = (String) request.getSession().getAttribute("monto");
		//Afiliacion datosUsuario = new AfiliacionImpl();
		
		AfiliacionDebitoAutomaticoForm frm = (AfiliacionDebitoAutomaticoForm)form;
		frm.reset(mapping, request);

		try {

			

			Afiliacion afiliacion 	= new AfiliacionImpl(); 
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
			//afiliacion.setCodCliente(codCliente);

			// REALIZAR CONSULTA TIPO CLAVE DINAMICA --> ADD M.D.B.
			ParametrosTDC param = (ParametrosTDC) request.getSession()
					.getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
			request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION, param);
			ParametrosElemSegTDC elementos = null;

			elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
			request.getSession().setAttribute("tipoElemento", elementos);

			//REALIZAR CONSULTA DE COORDENADAS
			ElemenSeguridad resultCoord = null;
			request.getSession().removeAttribute("exitoGeneraClvSms");  
			
			if (param != null) {
				resultCoord = new ElemenSeguridad();
				request.getSession().setAttribute("resultCoord", null);

				if (elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {
					
					tokenSms.setCodCliente(afiliacion.getCodCliente());
					tokenSms.setTipoDocumento(afiliacion.getTipoDocumento());
					tokenSms.setNroDocumento(afiliacion.getNroDocumento());
					
					tokenSms.setTypeTrans("40");
					tokenSms.setTypeOp("ADMIN");						
					tokenSms.setMsgClient("Generacion de Clave Dinamica Digital");
					tokenSms.setTypeCurrency("PEN");
					
					MensajesTDC resultado = UtilAction.generarClaveSms(tokenSms);
					
					if(resultado.isExito()==false){		
						String mensaje = aplicacion.getMsjesHost("CS", resultado.getCodResult()).elementAt(2).toString();
						response.setStatus(404);
						response.getWriter().write(mensaje);
						response.getWriter().flush();
						response.getWriter().close();
					}
			
					request.getSession().setAttribute("exitoGeneraClvSms", resultado.isExito());  
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
			
			throw e;
		}
		//System.out.println(request.getSession().getAttribute("exitoGeneraClvSms"));
		return mapping.findForward("inicio");

	}
	
	private void loadMessage(HttpServletRequest request) throws Exception{
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		request.getSession().setAttribute("mensajeAfiliacion",((Vector)aplicacion.getMensajePorCodigo("TB01","AF001")).elementAt(2).toString());
		request.getSession().setAttribute("mensajePantalla",((Vector)aplicacion.getMensajePorCodigo("TB01","00001")).elementAt(2).toString());
		request.getSession().setAttribute("mensajeExitoTranfMB",((Vector)aplicacion.getMensajePorCodigo("TB01","00005")).elementAt(2).toString());
	}
	
}