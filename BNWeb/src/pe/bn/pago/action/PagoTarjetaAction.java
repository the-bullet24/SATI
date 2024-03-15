
package pe.bn.pago.action;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pe.bn.afiliacion.action.DesafCableAction;
import pe.bn.afiliacion.domain.Afiliacion;
import pe.bn.afiliacion.domain.impl.AfiliacionImpl;
import pe.bn.cldinamica.action.SolicitarServiciosTDC;
import pe.bn.cldinamica.action.form.ElemenSeguridad;
import pe.bn.cldinamica.action.form.MensajesTDC;
import pe.bn.cldinamica.action.form.ParametrosElemSegTDC;
import pe.bn.cldinamica.action.form.ParametrosTDC;
import pe.bn.listener.Util;
import pe.bn.notificaciones.action.ServicioNotificacionConstancia;
import pe.bn.notificaciones.action.ServiciosNotificacionPrincipal;
import pe.bn.notificaciones.model.ParametrosSIAT;
import pe.bn.pago.domain.PagoTarjeta;
import pe.bn.pago.domain.impl.PagoTarjetaImpl;
import pe.bn.telegiro.domain.impl.TelegiroImpl;
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
import pe.cosapi.domain.impl.CuentaImpl;
import pe.cosapi.domain.impl.OperacionImpl;
import pe.cosapi.dto.PersonaDTO;


public class PagoTarjetaAction extends GrandActionAbstract {
	BNAplicacion aplicacion =BNAplicacion.getInstance();
	private static LoggerSati log3 = LoggerSati.getInstance(PagoTarjetaAction.class.getName());


	public ActionForward iniciar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ParametrosElemSegTDC elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento"); //5,6,7
        Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
        
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
		
		List listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.PAGO_TARJETA,usuario.getTarjeta().getNumero(),"00420");
		Constante.TIPO_SERVICIO = "00420";
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		request.setAttribute("mensajeAfiliacion",((Vector)aplicacion.getMensajePorCodigo("PG01","AF001")).elementAt(2).toString());		
		request.getSession().setAttribute("mensajecaracteresafiliaciontarjetasotrosbancos",((Vector)aplicacion.getMensajePorCodigo("PG01","AF003")).elementAt(2).toString());
		request.setAttribute("mensajePantalla",((Vector)aplicacion.getMensajePorCodigo("PG01","00001")).elementAt(2).toString());
		request.getSession().setAttribute("TITULOS","PAGO DE TARJETAS OTRO BANCO");
		request.setAttribute("listaAfiliaciones",listaAfiliaciones);
		FacadeFactory.getGeneralFacade().actualizarCuentas("LG02",usuario,request.getRemoteAddr());
		request.getSession().setAttribute("mensajeExitoPagoOB",((Vector)aplicacion.getMensajePorCodigo("TB05","00005")).elementAt(2).toString());
		request.setAttribute("lstBancoDestino", FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_BANCO));
		request.setAttribute("lstTipoTarjeta", FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_TIP_TARJETA));
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);

		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		
		ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
		
		if(!getVerificarHorario("PG01")){
			
			request.getSession().setAttribute("mensaje1Hor",((Vector)aplicacion.getMensajePorCodigo("AD01","00001")).elementAt(2).toString());
			request.getSession().setAttribute("mensaje2Hor",((Vector)aplicacion.getMensajePorCodigo("AD01","00002")).elementAt(2).toString());
			request.getSession().setAttribute("mensajeHorC",((Vector)aplicacion.getMensajePorCodigo("MH01","PG01")).elementAt(2).toString());
			return mapping.findForward("transaccion.noDisponible");
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
		
		return mapping.findForward("iniciar");
	}
	
	public ActionForward verPago(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {		
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);		
		Afiliacion afiliacionDatos = new AfiliacionImpl();
		if(request.getParameter("TipoTransf").equals(Constante.NUEV_REGISTRO_TRANSAC)){
		
			afiliacionDatos.setCodigoServicio(request.getParameter("cmbBancoDestino"));
			afiliacionDatos.setNumeroServicio(request.getParameter("txtNroTarjeta"));
			
			List listaAfiliaciones = null;
			listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaExiste(Constante.PAGO_TARJETA,usuario.getTarjeta().getNumero(), "", afiliacionDatos.getNumeroServicio());
			
			if (listaAfiliaciones.size() > 0){
				request.setAttribute("mensajeAfiliacion",((Vector)aplicacion.getMensajePorCodigo("PG01","AF001")).elementAt(2).toString());		
				request.setAttribute("mensajePantalla",((Vector)aplicacion.getMensajePorCodigo("PG01","00001")).elementAt(2).toString());
				request.setAttribute("lstBancoDestino", FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_BANCO));
				request.setAttribute("lstTipoTarjeta", FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_TIP_TARJETA));
				throw new ArrayRuleException(ConstanteError.GENERICO,"El número de tarjeta: " + afiliacionDatos.getNumeroServicio() +" ya se encuentra afiliada.");
			}
			PersonaDTO datos = new PersonaDTO();
			datos.setApellidoPaterno(request.getParameter("txtAppBenef").trim());
			datos.setApellidoMaterno(request.getParameter("txtApmBenef").trim());
			datos.setNombre(request.getParameter("txtNombreBenef").trim());
			afiliacionDatos.setObjBenef(datos);
			afiliacionDatos.setBeneficiario(datos.getApellidoPaterno()+" "+datos.getApellidoMaterno()+" "+datos.getNombre());
			afiliacionDatos.setFlagRegistro(Constante.NUEV_REGISTRO_TRANSAC);
			afiliacionDatos.setTipoTarjeta(request.getParameter("cmbTipoTarjeta"));
			
			
						
		}
		else{
			String[] id = ObjectUtil.getArrayStrings(request.getParameter("cmbTarjeta"),"-");
			request.getSession().setAttribute("afiliacion",FacadeFactory.getAfiliacionFacade().getAfiliacion(id[0],id[1],new Long(id[2]),"00420"));
			 afiliacionDatos = (Afiliacion)request.getSession().getAttribute("afiliacion");
			 afiliacionDatos.setFlagRegistro(Constante.FREC_REGISTRO_TRANSAC);
		}
		
		afiliacionDatos.setDescripcionCodigoServicio(tipoBancoDestino(afiliacionDatos.getCodigoServicio(), Constante.COD_HLP_BANCO));
		request.getSession().setAttribute("afiliacion",afiliacionDatos);
		request.getSession().setAttribute("CORREO_AFILIADO",afiliacionDatos.getEmail());
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		request.setAttribute("mensajeCondicion",((Vector)aplicacion.getMensajePorCodigo("PG01","00002")).elementAt(2).toString());
		request.setAttribute("mensajeLimite",((Vector)aplicacion.getMensajePorCodigo("PG01","00003")).elementAt(2).toString());
		//request.setAttribute("cuenta",usuario.getMapCuentas().get(request.getParameter("cmbCuenta")));
		//CuentaImpl cuenta = (CuentaImpl)usuario.getMapCuentas().get(request.getParameter("cmbCuenta"));
		String cboCuenta=request.getParameter("cmbCuenta");
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
			cta	= (CuentaImpl)usuario.getMapCuentas().get(cboCuenta);
		}else{
			for(int i=0; i<x.size();i++){
				cta=(CuentaImpl)x.get(i);
				if(!cta.getTipoProducto().equals("44")&& cta.getNumeroProducto().equals(cboCuenta)){
					break;
				}	
			}
		}
		request.setAttribute("cuenta",cta);
		request.getSession().setAttribute("cmbTarjeta",request.getParameter("cmbTarjeta"));
		request.getSession().setAttribute("cmbCuenta",request.getParameter("cmbCuenta"));
		
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);

		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		
		ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
		
		return mapping.findForward("verPago");
	}
	
	public ActionForward confirmarPago(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		//Afiliacion datosUsuario = FacadeFactory.getGeneralFacade().obtenerDatosPersonalesAfiliacion(usuario);
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
		
		Afiliacion afiliacion = (Afiliacion)request.getSession().getAttribute("afiliacion");
		request.getSession().setAttribute("afiliacion",afiliacion);
		request.setAttribute("TipoTransf",afiliacion.getFlagRegistro());
		//Cuenta cuenta = (Cuenta)usuario.getMapCuentas().get(request.getParameter("cmbCuenta"));
		String cboCuenta=request.getParameter("cmbCuenta");
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
			cta	= (CuentaImpl)usuario.getMapCuentas().get(cboCuenta);
		}else{
			for(int i=0; i<x.size();i++){
				cta=(CuentaImpl)x.get(i);
				if(!cta.getTipoProducto().equals("44")&& cta.getNumeroProducto().equals(cboCuenta)){
					break;
				}	
			}
		}
		String monto = request.getParameter("txtMonto");
		String moneda = request.getParameter("cmbMoneda");
		String transaccion = request.getParameter("transaccion");
		
		Cuenta cuenta = cta;
		request.getSession().setAttribute("cuentaTransf", cuenta);
		request.getSession().setAttribute("moneda", moneda);
		
		request.getSession().setAttribute("moneda", moneda);
		
		try{
			request.getSession().setAttribute(ConstanteSesion.PAGO_TARJETA,FacadeFactory.getPagoFacade().getPagoTarjeta(transaccion,(Cuenta)cta,afiliacion,usuario,moneda,monto,request.getRemoteAddr()));
		}
		catch(ArrayRuleException e){
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			request.setAttribute("mensajeAfiliacion",((Vector)aplicacion.getMensajePorCodigo("PG01","AF001")).elementAt(2).toString());		
			request.setAttribute("mensajePantalla",((Vector)aplicacion.getMensajePorCodigo("PG01","00001")).elementAt(2).toString());
			request.setAttribute("lstBancoDestino", FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_BANCO));
			List listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.PAGO_TARJETA,usuario.getTarjeta().getNumero(),"00420");		
			request.setAttribute("listaAfiliaciones",listaAfiliaciones);
			throw e;
		}
		
		ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
		
		//REALIZAR CONSULTA TIPO CLAVE DINAMICA
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		ParametrosElemSegTDC elementos = null;
		try{
			request.getSession().setAttribute("monto",monto);
			elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
		}catch(ArrayRuleException ar){
			log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
			ar.printStackTrace();
			loadObject(request);
			ar.setForward("iniciar");
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
				}else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS)){
						resultCoord=SolicitarServiciosTDC.solicitarTokens(param);
				}else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {				
					generarClaveSms(mapping, form, request, response);						
				}	
				
			}catch(ArrayRuleException ar){
				log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
				ar.printStackTrace();
				loadObject(request);
				ar.setForward("iniciar");
				throw ar;
			}
			request.getSession().setAttribute("resultCoord",resultCoord );
		}
		else{
			
			throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
		}

		
		return mapping.findForward("confirmarPago");
	}

	public ActionForward pagar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		TokenSmsRequest tokenSms = new TokenSmsRequest();
		
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);		
		Afiliacion afiliacion = (Afiliacion)request.getSession().getAttribute("afiliacion");
		String cFechaPago = pe.cosapi.common.ObjectUtil.getFechaActual();
		String cHoraPago  = pe.cosapi.common.ObjectUtil.getHHMMSSformateado();
		if (cFechaPago == null){
		    cFechaPago = pe.cosapi.common.ObjectUtil.getFechaActual();
		    
		}
		
		if (cHoraPago == null){
		    cHoraPago  = pe.cosapi.common.ObjectUtil.getHHMMSSformateado();
		    
		}
		
		String tipoMoneda = (String)request.getSession().getAttribute("moneda");

		request.getSession().setAttribute("fecpago",cFechaPago);
		request.getSession().setAttribute("horpago",cHoraPago);
		String tipoCard = afiliacion.getTipoTarjeta();
		int tt= Integer.parseInt(tipoCard);
		List lista = null;
		if(tipoCard!=null)
			lista = DAOFactory.getGeneraDAO().getComboDetalleHlpHijo("00435",tipoCard);
		if(lista!=null){
			for (Iterator iter = lista.iterator(); iter.hasNext();) {
				ComboUtil element = (ComboUtil) iter.next();
				if(element.getCodigo().equals(tt+"")){
				    afiliacion.setDescripcionTipoTarjeta(element.getDescripcion());
				}	
			}
		}
		Cuenta cuenta = (Cuenta)usuario.getMapCuentas().get(request.getParameter("cmbCuenta"));
		PagoTarjetaImpl pago = (PagoTarjetaImpl)request.getSession().getAttribute(ConstanteSesion.PAGO_TARJETA);
		pago.setAfiliacion(afiliacion);
		String transaccion = request.getParameter("transaccion");
		
		//Afiliacion datosUsuario = FacadeFactory.getGeneralFacade().obtenerDatosPersonalesAfiliacion(usuario);
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
		
		
		Map map = UtilAction.cargarVar(request,request.getSession().getAttribute(ConstanteSesion.PAGO_TARJETA));
		OperacionImpl.setVariables(map);
		try{
		
			ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
			request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);	
			
			//REALIZAR CONSULTA TIPO CLAVE DINAMICA
			ElemenSeguridad resultCoord = null;
			ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
			request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
			ElemenSeguridad coord = (ElemenSeguridad)request.getSession().getAttribute("resultCoord");
			
			String pinblock = ObjectUtil.getClaveDesencriptada(request.getParameter("txtCoordenada"),request);
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
				loadObject(request);
				ar.setForward("verPago");
				throw ar;
			}
			
			
			
			// Código Nuevo para TDC
		
			MensajesTDC resultado = new MensajesTDC();
			
			if(param!= null && coord!=null ){
				
				try{
					Cuenta cuentaG = (CuentaImpl)request.getSession().getAttribute("cuentaTransf");
					if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){				
						resultado=SolicitarServiciosTDC.validarTDC(param ,coord,pinblock);					
					}else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS)){						
						resultado=SolicitarServiciosTDC.validarTokens(param ,coord,pinblock);						
					}else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)){
					
						String nroDoc = numDoc;
						String documentType = Funciones.lpad(tipoDoc,"0",3);
//						String nameClient = StringUtils.isNotEmpty(datosUsuario.getBeneficiario())?datosUsuario.getBeneficiario():LoadDataProperties.getInstance().getValue("bn.name.cliente");
						
						tokenSms.setCodCliente(usuario.getCodigoCic());
						tokenSms.setTipoDocumento(documentType);
						tokenSms.setNroDocumento(nroDoc);

						tokenSms.setCodeSMS(pinblock);
						tokenSms.setTypeTrans(Constante.TIP_OPER_PAGO_PAGO_TARJETA_DIFERIDO);						
						tokenSms.setTypeOp(Constante.COD_TRANSACCION_FINANCIERA);
						tokenSms.setMsgClient("Validacion de Clave Dinamica Digital");
						tokenSms.setAmount(pago.getImporte());
						tokenSms.setTypeCurrency(tipoMoneda.equals("USD")?"USD":"PEN");
						tokenSms.setCodCliDestinatario(pago.getAfiliacion().getNumeroServicio());
						tokenSms.setNomCliDestinatario(pago.getAfiliacion().getBeneficiario());
						
						
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
					
					PagoTarjeta pt = FacadeFactory.getPagoFacade().pagarTarjeta(transaccion,pago,usuario,request.getRemoteAddr(),request);
					request.getSession().setAttribute(ConstanteSesion.PAGO_TARJETA, pt);					
				
					if(usuario.getNotificacion().getEstadoNotificacion().equals("1")){
						 ServiciosNotificacionPrincipal.enviarNotificacion(
								 usuario,
								 usuario.getNombre(), 
								 pt.getCuenta().getSimboloMonedaProducto(), 
								 pago.getImporte(),
								 usuario.getTipoCambio().getVenta(), 
								 Constante.NOTI_PAGO_TC_OTRO_BANCO, 
								 usuario.getNotificacion());
					}
					
					ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_TARJETA_CREDITO_OB_DIFERIDA, ConstanteSesion.PAGO_TARJETA, request);
				}
				catch (ArrayRuleException e) {
					log3.error(e,Constante.ERR_ERROR_VALIDAR_TDC,"");
					e.printStackTrace();
					PagoTarjetaImpl pg = (PagoTarjetaImpl)request.getSession().getAttribute(ConstanteSesion.PAGO_TARJETA);
					request.setAttribute("TipoTransf",pg.getAfiliacion().getFlagRegistro());
					
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
					e.setForward("confirmarPago");
					throw e;
				}
			}
		
		}
		catch (ArrayRuleException e) {
			loadObject(request);
			e.setForward("confirmarPago");
			throw e;
		}
		
		if(afiliacion.getFlagRegistro().equals(Constante.NUEV_REGISTRO_TRANSAC)){
			
			String adicionar = request.getParameter("adicionar");
			if(adicionar!=null){
				
					
				afiliacion.setDescripcion(request.getParameter("txtNombreAfil"));
				afiliacion.setTipoAfiliacion(Constante.PAGO_TARJETA);
				
				
				try {
						Afiliacion afiliar=FacadeFactory.getAfiliacionFacade().afiliar(usuario,afiliacion);
						//request.getSession().setAttribute(ConstanteSesion.AFILIAR,af);
					
				} catch (ArrayRuleException e) {
					log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
					e.printStackTrace();
					//e.setForward("continuarTransferencia");
				}
			}
		
			
		}
		
		return mapping.findForward("pagar");
	}
	
	public String tipoBancoDestino(String tipodoc, String campoasoc){
		int td= Integer.parseInt(tipodoc);
		List lista = null;
		if(tipodoc!=null&&campoasoc!=null)
			lista = DAOFactory.getGeneraDAO().getComboDetalleHlpHijoMod(campoasoc,tipodoc);
		if(lista!=null){
			for (Iterator iter = lista.iterator(); iter.hasNext();) {
				ComboUtil element = (ComboUtil) iter.next();
				
				if(element.getCodigo().trim().equals(tipodoc+"")){
					return element.getDescripcion();
				}	
			}
		}
		
		return "";
	}
	
	private void loadObject(HttpServletRequest request)throws Exception {
		
		request.setAttribute("lstBancoDestino", FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_BANCO));
		request.setAttribute("lstTipoTarjeta", FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_TIP_TARJETA));
		request.setAttribute("mensajeAfiliacion",((Vector)aplicacion.getMensajePorCodigo("PG01","AF001")).elementAt(2).toString());		
		request.setAttribute("mensajePantalla",((Vector)aplicacion.getMensajePorCodigo("PG01","00001")).elementAt(2).toString());
		request.getSession().setAttribute("TITULOS","PAGO DE TARJETAS OTRO BANCO");
		
			
	}
	
	public ActionForward generarClaveSms(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		TokenSmsRequest tokenSms = new TokenSmsRequest();
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		// Afiliacion afiliacion = (AfiliacionImpl)request.getSession().getAttribute("afiliacion");
		String monto = (String) request.getSession().getAttribute("monto");
		PagoTarjetaImpl pago = (PagoTarjetaImpl)request.getSession().getAttribute(ConstanteSesion.PAGO_TARJETA);

		Cuenta cuenta = (CuentaImpl)request.getSession().getAttribute("cuentaTransf");
		String tipoMoneda = (String)request.getSession().getAttribute("moneda");
		
		
		
		
		try {

			
			Afiliacion afiliacion = new AfiliacionImpl();						
			//Afiliacion datosUsuario = new AfiliacionImpl();
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
			//String nameClient = StringUtils.isNotEmpty(datosUsuario.getBeneficiario())?datosUsuario.getBeneficiario():LoadDataProperties.getInstance().getValue("bn.name.cliente");
			//String nroDoc = datosUsuario.getNroDocumento();
			if (param != null) {
				resultCoord = new ElemenSeguridad();
				request.getSession().setAttribute("resultCoord", null);

				if (elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {
									
					tokenSms.setCodCliente(afiliacion.getCodCliente());
					tokenSms.setTipoDocumento(Funciones.lpad(tipoDoc,"0",3));
					tokenSms.setNroDocumento(numDoc);

					tokenSms.setTypeTrans(Constante.TIP_OPER_PAGO_PAGO_TARJETA_DIFERIDO);
					tokenSms.setTypeOp("FIN");
					tokenSms.setMsgClient("Generacion de Clave Dinamica Digital");
					tokenSms.setAmount(monto);
					tokenSms.setTypeCurrency(tipoMoneda.equals("USD")?"USD":"PEN");
					tokenSms.setCodCliDestinatario(pago.getAfiliacion().getNumeroServicio());
					tokenSms.setNomCliDestinatario(pago.getAfiliacion().getBeneficiario());

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
			loadMessage(request);
			e.setForward("verMBTransferencia");
			throw e;
		}
		return mapping.findForward("confirmarTelegiro");

	}
	
	public ActionForward reGenerarClaveSms(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		TokenSmsRequest tokenSms = new TokenSmsRequest();
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		// Afiliacion afiliacion = (AfiliacionImpl)request.getSession().getAttribute("afiliacion");
		String monto = (String) request.getSession().getAttribute("monto");
		PagoTarjetaImpl pago = (PagoTarjetaImpl)request.getSession().getAttribute(ConstanteSesion.PAGO_TARJETA);

		Cuenta cuenta = (CuentaImpl)request.getSession().getAttribute("cuentaTransf");
		String tipoMoneda = (String)request.getSession().getAttribute("moneda");
		
		
		
		
		try {

			
			Afiliacion afiliacion = new AfiliacionImpl();						
//			Afiliacion datosUsuario = new AfiliacionImpl();
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
			//String nameClient = StringUtils.isNotEmpty(datosUsuario.getBeneficiario())?datosUsuario.getBeneficiario():LoadDataProperties.getInstance().getValue("bn.name.cliente");
			//String nroDoc = datosUsuario.getNroDocumento();
			if (param != null) {
				resultCoord = new ElemenSeguridad();
				request.getSession().setAttribute("resultCoord", null);

				if (elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {
										
					tokenSms.setCodCliente(afiliacion.getCodCliente());
					tokenSms.setTipoDocumento(Funciones.lpad(tipoDoc,"0",3));
					tokenSms.setNroDocumento(numDoc);

					tokenSms.setTypeTrans(Constante.TIP_OPER_PAGO_PAGO_TARJETA_DIFERIDO);
					tokenSms.setTypeOp("FIN");
					tokenSms.setMsgClient("Generacion de Clave Dinamica Digital");
					tokenSms.setAmount(monto);
					tokenSms.setTypeCurrency(tipoMoneda.equals("USD")?"USD":"PEN");
					tokenSms.setCodCliDestinatario(pago.getAfiliacion().getNumeroServicio());
					tokenSms.setNomCliDestinatario(pago.getAfiliacion().getBeneficiario());

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
			loadMessage(request);
			e.setForward("verMBTransferencia");
			throw e;
		}
		return mapping.findForward("confirmarTelegiro");

	}
	
	private void loadMessage(HttpServletRequest request) throws Exception{
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		request.getSession().setAttribute("mensajeAfiliacion",((Vector)aplicacion.getMensajePorCodigo("TB01","AF001")).elementAt(2).toString());
		request.getSession().setAttribute("mensajePantalla",((Vector)aplicacion.getMensajePorCodigo("TB01","00001")).elementAt(2).toString());
		request.getSession().setAttribute("mensajeExitoTranfMB",((Vector)aplicacion.getMensajePorCodigo("TB01","00005")).elementAt(2).toString());
	}
}