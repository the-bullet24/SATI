/*
 * Creado el 27/02/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.bn.tarjeta.action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import pe.bn.tarjeta.domain.Tarjeta;
import pe.bn.transferencia.domain.impl.TransferenciaImpl;
import pe.com.bn.common.Funciones;
import pe.com.bn.common.sati.bean.TokenSmsRequest;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.action.GrandActionAbstract;
import pe.cosapi.action.UtilAction;
import pe.cosapi.common.ArrayRuleException;
import pe.cosapi.common.BNAplicacion;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteError;
import pe.cosapi.common.ConstanteSesion;
import pe.cosapi.common.DAOFactory;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.common.SeguridadUtil;
import pe.cosapi.domain.GeneracionLog;
import pe.cosapi.domain.GeneracionLogResultado;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.GeneracionLogImpl;
import pe.cosapi.domain.impl.OperacionImpl;
import pe.cosapi.system.GeneratorKeys;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */

public class CambiarClaveAction extends GrandActionAbstract{
	private static LoggerSati log3 = LoggerSati.getInstance(CambiarClaveAction.class.getName());

		
	public ActionForward iniciar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		request.getSession().setAttribute("mensajecambiopie",((Vector)aplicacion.getMensajePorCodigo("GC02","00002")).elementAt(2).toString());
		request.getSession().setAttribute("mensajecambiocabecera",((Vector)aplicacion.getMensajePorCodigo("GC02","00003")).elementAt(2).toString());
		request.getSession().setAttribute("mensajecambioexito",((Vector)aplicacion.getMensajePorCodigo("GC02","00004")).elementAt(2).toString());
		request.getSession().setAttribute("mensajecambioatte",((Vector)aplicacion.getMensajePorCodigo("GC02","00005")).elementAt(2).toString());
		request.getSession().setAttribute("mensajecambioInf",((Vector)aplicacion.getMensajePorCodigo("GC02","00006")).elementAt(2).toString());
	

		
//		REALIZAR CONSULTA TIPO CLAVE DINAMICA  --> ADD M.D.B.
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		ParametrosElemSegTDC elementos = null;

		
//		GeneracionLog lista = null;
//		GeneracionLog generacionLog= null;
//		
//		lista = DAOFactory.getGeneracionLogDAO().getGeneracionLog(generacionLog);
		
		
//		GeneracionLogImpl generacionLogImpl = new GeneracionLogImpl();
//		GeneracionLogResultado a = null;
//		long b = 0;
//		long c = 0;
//		
//		
//		
//		Timestamp fechaActual = new Timestamp(Calendar.getInstance().getTime().getTime());
////		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
////		String fechaComoCadena = sdf.format(new Date());
////		
//				
//		generacionLogImpl.setP_F32_FECHA_CRONO_OP(fechaActual);
//		generacionLogImpl.setP_F32_CANAL("WEB");
//		generacionLogImpl.setP_F32_IP_DIRECCION("11.11.11.11");
//		generacionLogImpl.setP_F32_MAC("12.34.12.12");
//		generacionLogImpl.setP_F32_NUM_TRANS("L03");
//		generacionLogImpl.setP_F32_CIC_CLTE(b);
//		generacionLogImpl.setP_F32_TIPO_DOCUMENTO("1");
//		generacionLogImpl.setP_F32_NUM_DOCUMENTO("42033831");
//		generacionLogImpl.setP_F32_TIPO_TARJETA("3");
//		generacionLogImpl.setP_F32_NUM_TARJETA("4214100010432559");
//		generacionLogImpl.setP_F32_ESTADO("1");
//		generacionLogImpl.setP_F32_FLAG_ERROR("1");
//		generacionLogImpl.setP_F32_COD_APP("1");
//		generacionLogImpl.setP_F32_COD_RET("1");
//		generacionLogImpl.setP_F32_TOKEN("1");		
//		generacionLogImpl.setP_F32_EMAIL_OUT("1");
//		generacionLogImpl.setP_F32_BENEF_OUT("1");
//		generacionLogImpl.setP_F32_TIPO_TOKEN("1");		
//		generacionLogImpl.setP_F32_NUMLOG(c);
//		generacionLogImpl.setPc_coderr("1");
//		generacionLogImpl.setPc_msgerr("1");
//			
//		int valor = DAOFactory.getGeneracionLogDAO().insertLog(generacionLogImpl);
//		
//		Timestamp fechaActual2 = new Timestamp(Calendar.getInstance().getTime().getTime());
//		
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//		
//		GeneracionLogImpl actualizaLogImpl = new GeneracionLogImpl();
//		actualizaLogImpl.setP_F32_NUMLOG((long)valor);
//		actualizaLogImpl.setP_F32_FECHA_CRONO_OP(fechaActual2);
//		actualizaLogImpl.setP_F32_FLAG_ERROR("2");
//		actualizaLogImpl.setP_F32_COD_APP("2");
//		actualizaLogImpl.setP_F32_COD_RET("2");
//		actualizaLogImpl.setP_F32_BENEF_OUT("2");
//		actualizaLogImpl.setP_F32_FECHA_LOGICA(sdf.format(fechaActual2));
//		actualizaLogImpl.setP_F32_CONSTANCIA_SATI("");
//		
//		actualizaLogImpl.setPc_coderr("2");
//		actualizaLogImpl.setPc_msgerr("2");
//		
//		DAOFactory.getGeneracionLogDAO().updateLog(actualizaLogImpl);
		
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
		
//		REALIZAR CONSULTA DE COORDENADAS
//		ElemenSeguridad resultCoord = null;
//		
//		if(param!= null){
//			resultCoord = new ElemenSeguridad();
//			try{
//				request.getSession().setAttribute("resultCoord",null );
//				
//				if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
//						resultCoord = SolicitarServiciosTDC.solicitCoordenada(param);
//				} else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS)) {						
//						resultCoord=SolicitarServiciosTDC.solicitarTokens(param);
//				} else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {						
//					generarClaveSms(mapping, form, request, response);		
//				}
//			}catch(ArrayRuleException ar){
//				log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
//				ar.printStackTrace();
//				ar.setForward("verificarCelular");
//				throw ar;
//			}
//			request.getSession().setAttribute("resultCoord",resultCoord );
//		}
//		else{
//			
//			throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
//		}
//		
		
		
		//REALIZAR CONSULTA TIPO CLAVE DINAMICA
		
		//request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		
	
		return mapping.findForward("iniciarCambiarClaveInternet");
	}
	
	
	public ActionForward confirmarCambioClaveInternet(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		TokenSmsRequest tokenSms = new TokenSmsRequest();
				
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		
		request.setAttribute("mensajePantalla",((Vector)aplicacion.getMensajePorCodigo("GC02","00001")).elementAt(2).toString());
		
		request.getSession().setAttribute("mensajeCabecera",((Vector) aplicacion.getMensajePorCodigo("DC00", "DC01")).elementAt(2).toString());
        request.getSession().setAttribute("mensajeYaActualizado",((Vector) aplicacion.getMensajePorCodigo("DC00", "DC02")).elementAt(2).toString());
       		
		try{
			
			ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
			request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
									
			String pinblock = ObjectUtil.getClaveDesencriptadaCDD(request.getParameter("txtCoordenada"), request);
			
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
			if(elementos.getTipoElementoSeguridad()=="99"){
				System.out.println("es 99");
			}else{
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
			e.setForward("iniciarCambiarClaveInternet");
			throw e;
		}

		return mapping.findForward("confirmarCambiarClaveInternet");
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
			e.setForward("iniciarCambiarClaveInternet");
			throw e;
		}
		return mapping.findForward("confirmarCambiarClaveInternet");
		
	}
	
	private void loadMessage(HttpServletRequest request) throws Exception{
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		request.getSession().setAttribute("mensajeAfiliacion",((Vector)aplicacion.getMensajePorCodigo("TB01","AF001")).elementAt(2).toString());
		request.getSession().setAttribute("mensajePantalla",((Vector)aplicacion.getMensajePorCodigo("TB01","00001")).elementAt(2).toString());
		request.getSession().setAttribute("mensajeExitoTranfMB",((Vector)aplicacion.getMensajePorCodigo("TB01","00005")).elementAt(2).toString());
	}

}
