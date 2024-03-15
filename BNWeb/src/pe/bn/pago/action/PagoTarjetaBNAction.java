
package pe.bn.pago.action;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pe.bn.afiliacion.domain.Afiliacion;
import pe.bn.afiliacion.domain.impl.AfiliacionImpl;
import pe.bn.cldinamica.action.SolicitarServiciosTDC;
import pe.bn.cldinamica.action.form.ElemenSeguridad;
import pe.bn.cldinamica.action.form.MensajesTDC;
import pe.bn.cldinamica.action.form.ParametrosElemSegTDC;
import pe.bn.cldinamica.action.form.ParametrosTDC;
import pe.bn.listener.Util;
import pe.bn.login.domain.IngresoTarjeta;
import pe.bn.notificaciones.action.ServicioNotificacionConstancia;
import pe.bn.notificaciones.action.ServiciosNotificacionPrincipal;
import pe.bn.notificaciones.model.ParametrosSIAT;
import pe.bn.tcredito.domain.Pago;
import pe.bn.tcredito.domain.impl.PagoImpl;
import pe.com.bn.common.Funciones;
import pe.com.bn.common.LoadDataProperties;
import pe.com.bn.common.sati.bean.TokenSmsRequest;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.action.GrandActionAbstract;
import pe.cosapi.action.UtilAction;
import pe.cosapi.common.ArrayRuleException;
import pe.cosapi.common.BNAplicacion;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteError;
import pe.cosapi.common.ConstanteSesion;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.AtriTransferenciaImpl;
import pe.cosapi.domain.impl.CuentaImpl;
import pe.cosapi.domain.impl.NotificacionImpl;
import pe.cosapi.domain.impl.OperacionImpl;


public class PagoTarjetaBNAction extends GrandActionAbstract {
	BNAplicacion aplicacion =BNAplicacion.getInstance();
	private static LoggerSati log3 = LoggerSati.getInstance(PagoTarjetaBNAction.class.getName());


	public ActionForward iniciar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {		
						
        Usuario usuario = (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		request.getSession().setAttribute("mensajeTarjetaCredito",((Vector)aplicacion.getMensajePorCodigo("CL01","TC005")).elementAt(2).toString());
		
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
        
		if(!getVerificarHorario("TC00")){
			
			request.getSession().setAttribute("mensaje1Hor",((Vector)aplicacion.getMensajePorCodigo("AD01","00001")).elementAt(2).toString());
			request.getSession().setAttribute("mensaje2Hor",((Vector)aplicacion.getMensajePorCodigo("AD01","00002")).elementAt(2).toString());
			request.getSession().setAttribute("mensajeHorC",((Vector)aplicacion.getMensajePorCodigo("MH01","TC00")).elementAt(2).toString());
			return mapping.findForward("transaccion.noDisponible");
		}
		
		String	transaccion = "";
			
		CuentaImpl cuentaImpl = new CuentaImpl();
		CuentaImpl consulta = new CuentaImpl();
		cuentaImpl.setFecha(pe.cosapi.common.ObjectUtil.getFechaActual());
		cuentaImpl.setHora(pe.cosapi.common.ObjectUtil.getHHMMSSformateado());
		
		try {
			//Consultar Productos del Cliente
			
	    	
	       	Vector valores = new Vector();
				
			valores.addElement(ObjectUtil.getVectorComponent("transaccion","LG06"));
			valores.addElement(ObjectUtil.getVectorComponent("cboTipoPersona",usuario.getTipoPersona()));
			valores.addElement(ObjectUtil.getVectorComponent("cboTipoTarjeta",usuario.getTarjeta().getTipo()));
			valores.addElement(ObjectUtil.getVectorComponent("txtNumeroTarjeta",usuario.getTarjeta().getNumero()));
				
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
			v.addElement(request.getRemoteAddr());
			ctas.addElement(v);
			
			String cic = usuario.getCodigoCic();
			String codigoCLDI = usuario.getCodigoCLDI();
			String montoLimite = usuario.getMontoLimite();
			IngresoTarjeta ingreso = usuario.getIngreso();
	    	ParametrosTDC parametro = usuario.getClaveDinamica();
			String flagActuaHost = usuario.getFlagActualizaDatoHost();
			
	        String estadoOperaciones = usuario.getEstadoOperaciones().getEstadoOperaciones();
	        String estadoNotificacion = usuario.getNotificacion().getEstadoNotificacion();
	        String medioNotificacion = usuario.getNotificacion().getMedioNotificacion();
	        String montoNotificacion = usuario.getNotificacion().getMontoNotificacion();
	        String tipoMonedaNotificacion = usuario.getNotificacion().getTipoMonedaNotificacion();
			
			usuario = FacadeFactory.getLoginFacade().autenticar("LG06"+usuario.getCodigoCLDI(),"1",valores,ctas, usuario.getTipoDocumento(), usuario.getNumDocumento());
			
			NotificacionImpl noti = new NotificacionImpl();	
			AtriTransferenciaImpl operaciones = new AtriTransferenciaImpl();
			
			operaciones.setEstadoOperaciones(estadoOperaciones);
			noti.setEstadoNotificacion(estadoNotificacion);
			noti.setMedioNotificacion(medioNotificacion);
			noti.setMontoNotificacion(montoNotificacion);
			noti.setTipoMonedaNotificacion(tipoMonedaNotificacion);
			
			usuario.setEstadoOperaciones(operaciones);
			usuario.setNotificacion(noti);
			
			usuario.setFlagActualizaDatoHost(flagActuaHost);
			
			usuario.setClaveDinamica(parametro);
			
			usuario.setCodigoCic(cic);
			usuario.setCodigoCLDI(codigoCLDI);
			usuario.setMontoLimite(montoLimite);
			usuario.setIngreso(ingreso);
	    	
			usuario.setCodigoCic(cic);
			usuario.setCodigoCLDI(codigoCLDI);
			usuario.setMontoLimite(montoLimite);
			usuario.setIngreso(ingreso);
			
			usuario.setTipoTarjeta(usuario.getTarjeta().getTipo());
			
			//Obteniendo las cuentas del cliente
			List x=usuario.getCuentas();
			CuentaImpl cta=null;
		
			for(int i=0; i<x.size();i++){
				cta=(CuentaImpl)x.get(i);
			
				if(cta.getTipoProducto().equals(Constante.COD_TARJETA_CREDITO)){
					
						cuentaImpl.setTipoProducto(cta.getTipoProducto());
						cuentaImpl.setNumeroProducto(cta.getNumeroProducto());
						cuentaImpl.setTipoTarjeta(cta.getTipoTarjeta());
						cuentaImpl.setSaldo(ObjectUtil.deFormatearMonto(cta.getSaldo()));
									
				}	
			}

			String tipoConsulta = Constante.TARJETA_CREDITO_CONSULTA_SALDO_VALOR_TRAMA;
			transaccion = Constante.TARJETA_CREDITO_CONSULTA_SALDO;
					
			if( cuentaImpl.getNumeroProducto()== null || cuentaImpl.getNumeroProducto().trim().equals("") 
							|| Long.parseLong(cuentaImpl.getNumeroProducto())==0){
									
					request.getSession().setAttribute("mensajeHorC",((Vector)aplicacion.getMensajePorCodigo("MH01","TC01")).elementAt(2).toString());
					
					return mapping.findForward("transaccion.noDisponible");
			}
				
					//if()
			consulta =FacadeFactory.getTarjetaCreditoFacade().consultar(transaccion,tipoConsulta, cuentaImpl.getNumeroProducto(), request.getRemoteAddr(), usuario, request);
			consulta.setFecha(pe.cosapi.common.ObjectUtil.getFechaActual());
			consulta.setHora(pe.cosapi.common.ObjectUtil.getHHMMSSformateado());	
					
					
			request.getSession().setAttribute("nrotc",cuentaImpl.getNumeroProducto());
			request.getSession().setAttribute(ConstanteSesion.CONSULTA,consulta);
		} catch (ArrayRuleException e) {
			e.setForward("iniciar");
			throw e;
		}
		
	
		request.getSession().setAttribute(ConstanteSesion.USUARIO_EN_SESION,usuario);
			
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
			
		ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
				
		request.getSession().setAttribute("mensajeArriba",((Vector)aplicacion.getMensajePorCodigo("CL01","TC006")).elementAt(2).toString());
		request.getSession().setAttribute("mensajeCondiciones",((Vector)aplicacion.getMensajePorCodigo("CL01","TC008")).elementAt(2).toString());
		
		if(elementos.getTipoElementoSeguridad().equals("5")){
			System.out.println("Token Fisico");
			usuario.getClaveDinamica().setTipoElementoSeguridad(elementos.getTipoElementoSeguridad());
			usuario.getClaveDinamica().setTipoElementoSeguridadFinal("Token Fisico");
			
		}else if(elementos.getTipoElementoSeguridad().equals("6")){
			System.out.println("Clave Dinamica Digital");
			usuario.getClaveDinamica().setTipoElementoSeguridad(elementos.getTipoElementoSeguridad());
			usuario.getClaveDinamica().setTipoElementoSeguridadFinal("Clave Dinamica Digital");
		}
		
		return mapping.findForward("inicioPagoTCredito");
	
	}
	
	
	
	public ActionForward confirmaPagoTCredito(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		
	
		CuentaImpl cuentaImpl = (CuentaImpl) request.getSession().getAttribute(ConstanteSesion.CONSULTA);
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		String tipoImporte =(String) request.getParameter("tipoImporte");
	
		String cmbCuenta 	= "";
		
		cmbCuenta = request.getParameter("cmbCuenta");
		
		
		List x=usuario.getCuentas();
		CuentaImpl cta=null;
		boolean flag= false;
		for(int i=0; i<x.size();i++){
			cta=(CuentaImpl)x.get(i);		
			if(cta.getTipoProducto().equals("44")){
				flag=true;	
				break;
			}	
		}
		if(!flag){
			cta	= (CuentaImpl)usuario.getMapCuentas().get(cmbCuenta);
		}else{
			for(int i=0; i<x.size();i++){
				cta=(CuentaImpl)x.get(i);
				if(!cta.getTipoProducto().equals("44")&& cta.getNumeroProducto().equals(cmbCuenta)){			
					break;					
				}	
			}		
		}
		//*****************************************
		
		
		Cuenta cuenta 		= cta;
		String importePagado = request.getParameter("importeNuevo").replace(",", "");
		PagoImpl pago = null;
		pago = new PagoImpl();
		
		pago.setCuentaCargada(cuenta);
		pago.setCuentaCredito(cuentaImpl);
		pago.setMontoPagado(new BigDecimal(importePagado));
		pago.setNroCuentaAhorros(usuario.getTarjeta().getNumero());
		pago.setDatos(cuentaImpl);
		request.getSession().setAttribute("tipoImporte", tipoImporte);
		request.getSession().setAttribute(ConstanteSesion.PAGO_TC,pago);
		request.getSession().setAttribute("cuentaTransf", cuenta);
		
		ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
		
//		REALIZAR CONSULTA TIPO CLAVE DINAMICA  --> ADD M.D.B.
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		ParametrosElemSegTDC elementos = null;
		try{
		
			request.getSession().setAttribute("tipoImporte",tipoImporte );
			elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
			
			if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS) ||
			   elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
				elementos = SolicitarServiciosTDC.verificarTipoElemento(param,usuario);
			}	
			
			
		}catch(ArrayRuleException ar){
			
			log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
			ar.printStackTrace();
			ar.setForward("inicioPagoTCredito");
			throw ar;
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
				}else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {				
					generarClaveSms(mapping, form, request, response);						
				}

			}catch(ArrayRuleException ar){
			
				request.getSession().setAttribute("tipoImporte",tipoImporte );
				
				log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
				ar.printStackTrace();
				ar.setForward("inicioPagoTCredito");
				throw ar;
			}
			request.getSession().setAttribute("resultCoord",resultCoord );
		}
		else{
			
			request.getSession().setAttribute("tipoImporte",tipoImporte );
			throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
		}
		

		return mapping.findForward("confirmaPagoTCredito");
		
	}
	public ActionForward pagarTCredito(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		String transaccion = request.getParameter("transaccion");
		String tipoConsulta= "";
		Cuenta cuenta = (CuentaImpl)request.getSession().getAttribute("cuentaTransf");
		
		if(transaccion.equals(Constante.TARJETA_CREDITO_PAGO_TC)) tipoConsulta = Constante.TARJETA_CREDITO_PAGO_TC_VALOR_TRAMA;
		
		PagoImpl pago = (PagoImpl) request.getSession().getAttribute(ConstanteSesion.PAGO_TC);
		
		ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
		
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		
		ElemenSeguridad coord = (ElemenSeguridad)request.getSession().getAttribute("resultCoord");
	
		TokenSmsRequest tokenSms = new TokenSmsRequest();
		
		ElemenSeguridad resultCoord = null;
		//String pinblock = request.getParameter("txtCoordenada");
		String pinblock = ObjectUtil.getClaveDesencriptada(request.getParameter("txtCoordenada"),request);
		ParametrosElemSegTDC elementos = null;
		

		//Afiliacion afiliacion = (Afiliacion) request.getSession().getAttribute("afiliacion");
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
		//afiliacion.setCodCliente(usuario.getCodigoCic());
		//afiliacion.setTipoDocumento(Funciones.lpad(datosUsuario.getTipoDocumento(), "0", 3));
		//afiliacion.setNroDocumento(datosUsuario.getNroDocumento());

		try{
			Map map = UtilAction.cargarVar(request,request.getSession().getAttribute(ConstanteSesion.PAGO_TC));
			OperacionImpl.setVariables(map);			
			
			//REALIZAR CONSULTA TIPO CLAVE DINAMICA  --> ADD M.D.B.
			
			try{
				elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
				
				if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS) ||
				   elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
					elementos = SolicitarServiciosTDC.verificarTipoElemento(param,usuario);
				}	
				
				elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
//				System.out.println("tipoElemento::::" + elementos);
			}catch(ArrayRuleException ar){
				log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
				ar.printStackTrace();
				ar.setForward("inicioPagoTCredito");
				throw ar;
			}
			
			// Código Nuevo para TDC
		
			MensajesTDC resultado = new MensajesTDC();
			Pago pg = null;
			
			
			if(param!= null && coord!=null ){
				try{
					if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
						resultado=SolicitarServiciosTDC.validarTDC(param ,coord,pinblock);					
					} else if (elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {
						
						tokenSms.setCodCliente(usuario.getCodigoCic());
						tokenSms.setTipoDocumento(Funciones.lpad(tipoDoc,"0",3));
						tokenSms.setNroDocumento(numDoc);
						tokenSms.setCodeSMS(pinblock);
						tokenSms.setTypeTrans(Constante.TIP_OPER_PAGO_TARJETA_MISMO_TITULAR);
						tokenSms.setTypeOp("FIN");
						tokenSms.setAmount(pago.getMontoPagado());
						tokenSms.setTypeCurrency(cuenta.getMonedaProducto().equals("USD")?"USD":"PEN");
						
						tokenSms.setCodCliDestinatario(pago.getCuentaCredito().getNumeroProducto());
						tokenSms.setNomCliDestinatario(pago.getCuentaCredito().getNombreCliente());
						
						resultado = UtilAction.validarClaveSms(tokenSms);
					} else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS)){						
							resultado=SolicitarServiciosTDC.validarTokens(param ,coord,pinblock);
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
					String tipo = Constante.TC_TIPO_SERVICIO_TITULAR;
					pg =FacadeFactory.getTarjetaCreditoFacade().pagarTCredito(transaccion, tipoConsulta, pago, request.getRemoteAddr(), usuario,tipo, request);
					
					if(usuario.getNotificacion().getEstadoNotificacion().equals("1")){
						 ServiciosNotificacionPrincipal.enviarNotificacion(
								 usuario,
								 usuario.getNombre(), 
								 pg.getCuentaCargada().getSimboloMonedaProducto(), 
								 pago.getMontoPagado(),
								 usuario.getTipoCambio().getVenta(),
								 Constante.NOTI_PAGO_TC_BN, 
								 usuario.getNotificacion());
					}
					request.getSession().setAttribute(ConstanteSesion.PAGO_TC, pg);	
					
					ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_TARJETA_CREDITO_BN_MISMO_TITULAR, ConstanteSesion.PAGO_TC, request);
					
				}catch (ArrayRuleException e) {
					log3.error(e,Constante.ERR_ERROR_VALIDAR_TDC,"");
					e.printStackTrace();					
					request.getSession().setAttribute(ConstanteSesion.PAGO_TC,pago);
					
					if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
							resultCoord = SolicitarServiciosTDC.solicitCoordenada(param);
					}
					else{
						if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS)){
							
							resultCoord=SolicitarServiciosTDC.solicitarTokens(param);
						}
					}
					
					request.getSession().setAttribute("resultCoord",resultCoord );
					e.setForward("confirmaPagoTCredito");
					throw e;
				}
			}		
		}
		catch (ArrayRuleException e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
					resultCoord = SolicitarServiciosTDC.solicitCoordenada(param);
			}
			else{
				if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS)){					
					resultCoord=SolicitarServiciosTDC.solicitarTokens(param);
				}
			}
			request.getSession().setAttribute("resultCoord",resultCoord );
			e.setForward("confirmaPagoTCredito");
			throw e;
		}
		
		return mapping.findForward("pagarTCredito");
		
	}
	
	public ActionForward generarClaveSms(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		TokenSmsRequest tokenSms = new TokenSmsRequest();
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		// Afiliacion afiliacion = (AfiliacionImpl)request.getSession().getAttribute("afiliacion");
		String monto = (String) request.getSession().getAttribute("monto");
		//Afiliacion datosUsuario = new AfiliacionImpl();
		PagoImpl pago = (PagoImpl) request.getSession().getAttribute(ConstanteSesion.PAGO_TC);
		
		Cuenta cuenta = (CuentaImpl)request.getSession().getAttribute("cuentaTransf");
		
		try {

			//Afiliacion afiliacion = (Afiliacion) request.getSession().getAttribute("afiliacion");
			//afiliacion.setDescripcionTipoDocumento(NomTipoDocBenef(afiliacion.getCodigoServicio(), Constante.COD_HLP_DET_DOCU_GIRO));
			//request.getSession().setAttribute("afiliacion", afiliacion);
			//request.setAttribute("optTelegiro", afiliacion.getFlagRegistro());
			
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
            //afiliacion.setTipoDocumento(Funciones.lpad(datosUsuario.getTipoDocumento(),"0",3));
			//afiliacion.setNroDocumento(datosUsuario.getNroDocumento());
			//afiliacion.setCodCliente(usuario.getCodigoCic());

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
					tokenSms.setTypeTrans(Constante.TIP_OPER_PAGO_TARJETA_MISMO_TITULAR);
					tokenSms.setTypeOp("FIN");
					tokenSms.setAmount(pago.getMontoPagado());
					tokenSms.setTypeCurrency(cuenta.getMonedaProducto().equals("USD")?"USD":"PEN");
					tokenSms.setMsgClient("Generacion de Clave Dinamica Digital");
					tokenSms.setCodCliDestinatario(pago.getCuentaCredito().getNumeroProducto());
					tokenSms.setNomCliDestinatario(pago.getCuentaCredito().getNombreCliente());

					MensajesTDC resultado = UtilAction.generarClaveSms(tokenSms);
					
					
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
			e.setForward("verMBTransferencia");
			throw e;
		}
		return mapping.findForward("confirmaPagoTCredito");

	}
	
	public ActionForward reGenerarClaveSms(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		TokenSmsRequest tokenSms = new TokenSmsRequest();
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		// Afiliacion afiliacion = (AfiliacionImpl)request.getSession().getAttribute("afiliacion");
		String monto = (String) request.getSession().getAttribute("monto");
		//Afiliacion datosUsuario = new AfiliacionImpl();
		PagoImpl pago = (PagoImpl) request.getSession().getAttribute(ConstanteSesion.PAGO_TC);
		
		Cuenta cuenta = (CuentaImpl)request.getSession().getAttribute("cuentaTransf");
		
		try {

			//Afiliacion afiliacion = (Afiliacion) request.getSession().getAttribute("afiliacion");
			//afiliacion.setDescripcionTipoDocumento(NomTipoDocBenef(afiliacion.getCodigoServicio(), Constante.COD_HLP_DET_DOCU_GIRO));
			//request.getSession().setAttribute("afiliacion", afiliacion);
			//request.setAttribute("optTelegiro", afiliacion.getFlagRegistro());
			
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
            //afiliacion.setTipoDocumento(Funciones.lpad(datosUsuario.getTipoDocumento(),"0",3));
			//afiliacion.setNroDocumento(datosUsuario.getNroDocumento());
			//afiliacion.setCodCliente(usuario.getCodigoCic());

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
					tokenSms.setTypeTrans(Constante.TIP_OPER_PAGO_TARJETA_MISMO_TITULAR);
					tokenSms.setTypeOp("FIN");
					tokenSms.setAmount(pago.getMontoPagado());
					tokenSms.setTypeCurrency(cuenta.getMonedaProducto().equals("USD")?"USD":"PEN");
					tokenSms.setMsgClient("Generacion de Clave Dinamica Digital");
					tokenSms.setCodCliDestinatario(pago.getCuentaCredito().getNumeroProducto());
					tokenSms.setNomCliDestinatario(pago.getCuentaCredito().getNombreCliente());

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
			response.setStatus(404);
			request.setAttribute("cmbCuenta", request.getParameter("cmbCuenta"));
			request.getSession().setAttribute("cmbTransferencia", request.getParameter("cmbTransferencia"));
			e.setForward("verMBTransferencia");
			throw e;
		}
		return mapping.findForward("confirmaPagoTCredito");

	}
}