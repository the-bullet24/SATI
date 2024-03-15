
package pe.bn.pago.action;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
import pe.bn.notificaciones.action.ServicioNotificacionConstancia;
import pe.bn.notificaciones.action.ServiciosNotificacionPrincipal;
import pe.bn.notificaciones.model.ParametrosSIAT;
import pe.bn.pago.domain.PagoFactura;
import pe.bn.pago.domain.PagoSedapal;
import pe.bn.pago.domain.impl.PagoSedapalImpl;
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
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.OperacionImpl;
import pe.cosapi.domain.impl.ReciboImpl;

/**
 * @author cosapi_ati02
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class PagoSedapalAction extends GrandActionAbstract {
	BNAplicacion aplicacion = BNAplicacion.getInstance();
	private static LoggerSati log3 = LoggerSati.getInstance(PagoSedapalAction.class.getName());


	public ActionForward iniciar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {	
		
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		Usuario usuario = (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);		
		Constante.TIPO_SERVICIO = "00441";
		String hidServicio = request.getParameter("hidServicio");
		String hidCodEntidad = request.getParameter("hidCodEntidad");
		String hidCodServ = request.getParameter("hidCodServ");
		String typeTrans = UtilAction.obtenerTipoTransaccionFinanciera(hidCodServ);
		
//		System.out.println("***hidServicio***: " + hidServicio);
//		System.out.println("***hidCodEntidad***: " + hidCodEntidad);
//		System.out.println("***hidCodServ***: " + hidCodServ);
//		System.out.println("typeTrans::::" + typeTrans);
		
		
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		List listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.PAGO_SED,usuario.getTarjeta().getNumero(),"00441");

		request.setAttribute("listaAfiliaciones",listaAfiliaciones);
		request.getSession().setAttribute("listaAfiliaciones",listaAfiliaciones);
		request.getSession().setAttribute(ConstanteSesion.PAGO_AGUA,FacadeFactory.getPagoFacade().getPagoSedapal(usuario));
		FacadeFactory.getGeneralFacade().actualizarCuentas("LG02",usuario,request.getRemoteAddr());
		request.getSession().setAttribute(ConstanteSesion.TITULO,"PAGO DE SERVICIO DE SEDAPAL");
		request.getSession().setAttribute("TITULOS","PAGO DE SERVICIO DE SEDAPAL");
		request.getSession().setAttribute("mensajeAfiliacion",((Vector)aplicacion.getMensajePorCodigo("PS00","AF004")).elementAt(2).toString());
		request.getSession().setAttribute("mensajePantalla",((Vector)aplicacion.getMensajePorCodigo("PS00","00002")).elementAt(2).toString());
		request.getSession().setAttribute("hidCodEntidad",hidCodEntidad);
		request.getSession().setAttribute("hidCodServ",hidCodServ);
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
		request.setAttribute("hidServicio",hidServicio);
		request.getSession().setAttribute("hidServicio",hidServicio);
		request.getSession().setAttribute("hidCodEntidad",hidCodEntidad);
		request.getSession().setAttribute("typeTrans",typeTrans);
		
		ParametrosElemSegTDC elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
		request.getSession().setAttribute("tipoElemento",elementos );
		
		
		if(elementos.getTipoElementoSeguridad().equals("5")){
			System.out.println("Token Fisico");
			usuario.getClaveDinamica().setTipoElementoSeguridad(elementos.getTipoElementoSeguridad());
			usuario.getClaveDinamica().setTipoElementoSeguridadFinal("Token Fisico");
			
		}else if(elementos.getTipoElementoSeguridad().equals("6")){
			System.out.println("Clave Dinamica Digital");
			usuario.getClaveDinamica().setTipoElementoSeguridad(elementos.getTipoElementoSeguridad());
			usuario.getClaveDinamica().setTipoElementoSeguridadFinal("Clave Dinamica Digital");
		}
		
		return mapping.findForward("inicio");
	}

	public ActionForward verPago(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String transaccion = request.getParameter("transaccion");
		//String nroSuministro = request.getParameter("cmbSuministro");
		String nroSuministro="";
		String digitoChequeo="";
		
		String hidCodEntidad = (String) request.getSession().getAttribute("hidCodEntidad");
		String hidCodServ = (String) request.getSession().getAttribute("hidCodServ");
		
		request.getSession().setAttribute("hidCodEntidad", hidCodEntidad);
		request.getSession().setAttribute("hidCodServ", hidCodServ);
			
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		
		Afiliacion x=null;
		String optCuenta = request.getParameter("optCuenta");

		request.getSession().setAttribute("optCuenta", optCuenta);
		
		if(request.getSession().getAttribute("optCuenta").equals(Constante.NUEV_REGISTRO_TRANSAC)){
			Afiliacion datosUsuario  = new AfiliacionImpl();
			nroSuministro = request.getParameter("txtServicioSedapal").trim();
			digitoChequeo = request.getParameter("txtDigitoChequeo").trim();
			
			List listaAfiliaciones = null;
			
			listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaExiste(Constante.PAGO_SED,usuario.getTarjeta().getNumero(), Constante.COD_HLP_PGO_SEDAPAL, nroSuministro.concat(digitoChequeo));
			
			if (listaAfiliaciones.size() > 0){
			
				throw new ArrayRuleException(ConstanteError.GENERICO,"El suministro: " + nroSuministro +" se encuentra como frecuente.");
			}
				Afiliacion afiliacion = null;
				afiliacion 	  = new AfiliacionImpl();
				
				afiliacion.setTipoAfiliacion(hidCodEntidad);
				afiliacion.setNumeroServicio(nroSuministro.concat(digitoChequeo));
				afiliacion.setNroTarjeta(usuario.getTarjeta().getNumero());
				afiliacion.setCodigoServicio(Constante.SERVICIO_SEDAPAL);
				afiliacion.setFlagRegistro(Constante.NUEV_REGISTRO_TRANSAC);
				afiliacion.setCodigoLocalidad(Constante.CODIGO_LOCALIDAD_FREC);
				
				String tipoDoc="";
				String numDoc="";
				
				if(usuario.getCodigoCLDI() != null){
					tipoDoc=usuario.getCodigoCLDI().substring(12,13);
					numDoc=usuario.getCodigoCLDI().substring(13);	
					numDoc= numDoc.substring(4);
				}
				afiliacion.setTipoDocumento(Funciones.lpad(tipoDoc,"0",3));
				afiliacion.setNroDocumento(numDoc);

				request.getSession().setAttribute("afiliacion",afiliacion);
			
			
		}

		if(request.getSession().getAttribute("optCuenta").equals(Constante.FREC_REGISTRO_TRANSAC))
		{
			nroSuministro = request.getParameter("cmbSuministro");
			Afiliacion afil = new AfiliacionImpl();
			afil.setFlagRegistro(Constante.FREC_REGISTRO_TRANSAC);
			request.getSession().setAttribute("afiliacion",afil);
			
			

		List listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.PAGO_SED,usuario.getTarjeta().getNumero(),"00441");
	
		for(int i=0; i<listaAfiliaciones.size();i++){
			x = (Afiliacion)listaAfiliaciones.get(i);
			if(x.getNumeroServicio().equals(nroSuministro)){
			    //System.out.println("ok! NUMERO DE SUMINISTRO AFILIACION: " + x.getNumeroServicio());
				break;
			}
		}
		
		}
	
		PagoSedapalImpl pagoSedapal = (PagoSedapalImpl)request.getSession().getAttribute(ConstanteSesion.PAGO_AGUA);
		if(request.getSession().getAttribute("optCuenta").equals(Constante.FREC_REGISTRO_TRANSAC)){
			pagoSedapal.setAfiliacion(x);
		}
		else{
			Afiliacion afiliacion = (AfiliacionImpl)request.getSession().getAttribute("afiliacion");
			pagoSedapal.setAfiliacion(afiliacion);
		}
		
		request.getSession().setAttribute(ConstanteSesion.PAGO_AGUA,pagoSedapal);
		String nroSuministroDigito = nroSuministro.concat(digitoChequeo);
		
		//try{
		
	
							//transaccion,pagoSedapal,nroSuministro,usuario,request.getRemoteAddr()PA00-pe.bn.pago.domain.impl.PagoSedapalImpl@13641364--pe.cosapi.domain.impl.UsuarioImpl@733a733a-127.0.0.1
			PagoSedapal psed=	FacadeFactory.getPagoFacade().getPagoSedapal(transaccion,pagoSedapal,nroSuministroDigito,usuario,request.getRemoteAddr());
			
			if(psed.getRecibos().size()==0){
				throw new ArrayRuleException(ConstanteError.GENERICO,"UD. NO TIENE RECIBOS PENDIENTES DE PAGO");
			}
		/*}	
		catch(ArrayRuleException e){
			listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.PAGO_SED,usuario.getTarjeta().getNumero(),"00420");		
			request.setAttribute("listaAfiliaciones",listaAfiliaciones);
			e.setForward("inicio");
		}*/
			
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);

		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		
		ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
		
		return mapping.findForward("elegirRecibo");
		
		
	
	}

	public ActionForward confirmar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		String reciboX = request.getParameter("rbRecibo");
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
		String transaccion = request.getParameter("transaccion");
		String nroSuministro = request.getParameter("cmbSuministro");
		
		String hidCodEntidad = request.getSession().getAttribute("hidCodEntidad").toString();
		String hidCodServ = request.getSession().getAttribute("hidCodServ").toString();
		String typeTrans = Constante.TIP_OPER_PAGO_AGUA;//request.getSession().getAttribute("typeTrans").toString();
		
		String importe = "";
		PagoSedapal pagoSedapal = (PagoSedapal)request.getSession().getAttribute(ConstanteSesion.PAGO_AGUA);
		List recibos = pagoSedapal.getRecibos();
		for (Iterator iter = recibos.iterator(); iter.hasNext();) {
			ReciboImpl recibo = (ReciboImpl) iter.next();
			if(reciboX.equals(recibo.getNumRecibo())){
				pagoSedapal.setRecibo(recibo);
				importe = recibo.getImporte();
				break;
			}
		}
		ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
		request.getSession().setAttribute("importe",importe);
		
		
//		REALIZAR CONSULTA TIPO CLAVE DINAMICA  --> ADD M.D.B.
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
			ar.setForward("elegirRecibo");
			throw ar;
		}

		//REALIZAR CONSULTA DE COORDENADAS
		ElemenSeguridad resultCoord = null;
		if(param!= null){
			resultCoord = new ElemenSeguridad();
			try{
				request.getSession().setAttribute("resultCoord",null );
				
				if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC))
				{
						resultCoord = SolicitarServiciosTDC.solicitCoordenada(param);
				}
				else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS))
				{
					resultCoord=SolicitarServiciosTDC.solicitarTokens(param);
				}
				else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS))
				{
					generarClaveSms(mapping, form, request, response);
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
		
		return mapping.findForward("confirmarPago");
	}

	public ActionForward pagar(ActionMapping mapping, ActionForm form,
		   HttpServletRequest request, HttpServletResponse response) throws Exception {
		
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
		String transaccion = request.getParameter("transaccion");
		//String clave = SeguridadUtil.getClaveDesencriptada(request.getParameter("txtNumClave"),request);
		String hidCodEntidad = request.getSession().getAttribute("hidCodEntidad").toString();
		String hidCodServ = request.getSession().getAttribute("hidCodServ").toString();
		String typeTrans = request.getSession().getAttribute("typeTrans").toString();
		String importeSol = request.getSession().getAttribute("importe").toString();
		
		PagoSedapalImpl pagoSedapal = (PagoSedapalImpl)request.getSession().getAttribute(ConstanteSesion.PAGO_AGUA);
		pagoSedapal.setFecha(pe.cosapi.common.ObjectUtil.getFechaActual());
		pagoSedapal.setHora(pe.cosapi.common.ObjectUtil.getHHMMSSformateado());
		pagoSedapal.setCodServicio(hidCodServ);
		pagoSedapal.setCodEntidad(hidCodEntidad);
		
		try{
					
			// Código Nuevo para TDC
			ElemenSeguridad resultCoord = null;
			ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
			ElemenSeguridad coord = (ElemenSeguridad)request.getSession().getAttribute("resultCoord");
			ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
			String pinblock = ObjectUtil.getClaveDesencriptada(request.getParameter("txtCoordenada"),request);		
		
			request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
			request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
			
			//REALIZAR CONSULTA TIPO CLAVE DINAMICA  --> ADD M.D.B.
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
				ar.setForward("elegirRecibo");
				throw ar;
			}
	
			//Código Nuevo para TDC
			MensajesTDC resultado = new MensajesTDC();
		
			if(param!= null && coord!=null ){
				
				try{
					
					if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC))
					{
						resultado=SolicitarServiciosTDC.validarTDC(param ,coord,pinblock);
					
					}
					else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS))
					{
						resultado=SolicitarServiciosTDC.validarTokens(param ,coord,pinblock);
					}
					else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS))
					{
						
						String nroDoc = numDoc;
						String documentType = Funciones.lpad(tipoDoc,"0",3);
						
						TokenSmsRequest tokenSms = new TokenSmsRequest();
						tokenSms.setCodeSMS(pinblock);
						tokenSms.setCodCliente(usuario.getCodigoCic());
						tokenSms.setTipoDocumento(documentType);
						tokenSms.setNroDocumento(nroDoc);
						tokenSms.setTypeTrans(typeTrans);
						tokenSms.setTypeOp(Constante.COD_TRANSACCION_FINANCIERA);//FIN
						tokenSms.setTypeCurrency(Constante.MONEDA_SOL_ISO);//PEN
						tokenSms.setAmount(importeSol);
						
						tokenSms.setCodCliDestinatario(pagoSedapal.getNroSuministro());
						tokenSms.setNomCliDestinatario(pagoSedapal.getCliente());
						
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
					PagoSedapal ps = FacadeFactory.getPagoFacade().pagarSedapal(transaccion,pagoSedapal,usuario,request.getRemoteAddr());
					
					if(usuario.getNotificacion().getEstadoNotificacion().equals("1")){
						ServiciosNotificacionPrincipal.enviarNotificacion(
								usuario,
								usuario.getNombre(), 
								ps.getCuenta().getSimboloMonedaProducto(), 
								pagoSedapal.getRecibo().getImporte().toString(),
								usuario.getTipoCambio().getVenta(), 
								Constante.NOTI_PAGO_SERVICIOS, 
								usuario.getNotificacion());
					}
					
					Map map = UtilAction.cargarVar(request,pagoSedapal);
					map.put("fecpago",request.getSession().getAttribute("fecpago"));
					map.put("horpago",request.getSession().getAttribute("horpago"));
					OperacionImpl.setVariables(map);
			
					
					ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_SERVICIOS_SEDAPAL, ConstanteSesion.PAGO_AGUA, request);			
				}
				catch (ArrayRuleException e) {
					log3.error(e,Constante.ERR_ERROR_VALIDAR_TDC,"");
					e.printStackTrace();
					if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC))
					{
						resultCoord = SolicitarServiciosTDC.solicitCoordenada(param);
					}
					else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS))
					{
						resultCoord=SolicitarServiciosTDC.solicitarTokens(param);
					}
					else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS))
					{
						resultCoord=SolicitarServiciosTDC.solicitarTokensSms(param);
					}
					
					request.getSession().setAttribute("resultCoord",resultCoord );
					e.setForward("confirmarPago");
					throw e;
				}
			}
		
		}
		catch(ArrayRuleException e){
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			e.setForward("confirmarPago");
			throw e;
		}
		Afiliacion pagoSedapali = (AfiliacionImpl)request.getSession().getAttribute("afiliacion");
		if(pagoSedapali.getFlagRegistro().equals(Constante.NUEV_REGISTRO_TRANSAC)){
			
			String adicionar = request.getParameter("adicionar");
			
			if(adicionar!=null){
				
				pagoSedapali.setTipoAfiliacion(Constante.PAGO_SED);			
				pagoSedapali.setDescripcion(request.getParameter("txtNombreAfil").trim());
				
				try {
						Afiliacion af=FacadeFactory.getAfiliacionFacade().afiliar(usuario,pagoSedapali);
						request.getSession().setAttribute(ConstanteSesion.AFILIAR,af);
					
				} catch (ArrayRuleException e) {
					log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
					e.printStackTrace();
					e.setForward("pagar");
				}
			}
		}
		
		
		return mapping.findForward("pagar");
	}
	
	public ActionForward generarClaveSms(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Usuario usuario = (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
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
		
		try {
			
			PagoSedapalImpl pagoSedapal = (PagoSedapalImpl)request.getSession().getAttribute(ConstanteSesion.PAGO_AGUA);
			
			
			String typeTrans = Constante.TIP_OPER_PAGO_AGUA;//request.getSession().getAttribute("typeTrans").toString();
			String importe = request.getSession().getAttribute("importe").toString();

			TokenSmsRequest tokenSms = new TokenSmsRequest();
			String documentType =Funciones.lpad(tipoDoc,"0",3);
			String nroDoc = numDoc;
//			String nameClient = StringUtils.isNotEmpty(datosUsuario.getBeneficiario())?
//					datosUsuario.getBeneficiario():LoadDataProperties.getInstance().getValue("bn.name.cliente");
//			
			tokenSms.setCodCliente(usuario.getCodigoCic());
			tokenSms.setTipoDocumento(documentType);		
			tokenSms.setNroDocumento(nroDoc);				
			tokenSms.setTypeTrans(typeTrans);
			tokenSms.setTypeOp(Constante.COD_TRANSACCION_FINANCIERA);//FIN
			tokenSms.setTypeCurrency(Constante.MONEDA_SOL_ISO);//PEN
			tokenSms.setAmount(importe);
			tokenSms.setMsgClient("Validacion de Clave Dinamica Digital");
			tokenSms.setCodCliDestinatario(pagoSedapal.getNroSuministro());
			tokenSms.setNomCliDestinatario(pagoSedapal.getCliente());

			MensajesTDC resultado = UtilAction.generarClaveSms(tokenSms);
			
			
			
		}catch(ArrayRuleException ar){
			log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
			request.setAttribute("listaEntidades",FacadeFactory.getGeneralFacade().getComboAyudaHijo(Constante.COD_HLP_DET_ENTIDAD));
			request.setAttribute("listaTributos",FacadeFactory.getGeneralFacade().getComboDetalleHlpHijo(Constante.COD_HLP_DET_TRIBUTO,""));
			FacadeFactory.getGeneralFacade().actualizarCuentas("LG02",usuario,request.getRemoteAddr());
			response.setStatus(404);
			ar.printStackTrace();
			ar.setForward("iniciarPago");
			throw ar;
		}
		return mapping.findForward("confirmarPago");
	}
	
	public ActionForward reGenerarClaveSms(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Usuario usuario = (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
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
		
		try {
			
			PagoSedapalImpl pagoSedapal = (PagoSedapalImpl)request.getSession().getAttribute(ConstanteSesion.PAGO_AGUA);
			
			String typeTrans = Constante.TIP_OPER_PAGO_AGUA;//request.getSession().getAttribute("typeTrans").toString();
			String importe = request.getSession().getAttribute("importe").toString();

			TokenSmsRequest tokenSms = new TokenSmsRequest();
			String documentType =Funciones.lpad(tipoDoc,"0",3);
			String nroDoc = numDoc;
//			String nameClient = StringUtils.isNotEmpty(datosUsuario.getBeneficiario())?
//					datosUsuario.getBeneficiario():LoadDataProperties.getInstance().getValue("bn.name.cliente");
//			
			tokenSms.setCodCliente(usuario.getCodigoCic());
			tokenSms.setTipoDocumento(documentType);		
			tokenSms.setNroDocumento(nroDoc);				
			tokenSms.setTypeTrans(typeTrans);
			tokenSms.setTypeOp(Constante.COD_TRANSACCION_FINANCIERA);//FIN
			tokenSms.setTypeCurrency(Constante.MONEDA_SOL_ISO);//PEN
			tokenSms.setAmount(importe);
			tokenSms.setMsgClient("Validacion de Clave Dinamica Digital");
			tokenSms.setCodCliDestinatario(pagoSedapal.getNroSuministro());
			tokenSms.setNomCliDestinatario(pagoSedapal.getCliente());

			MensajesTDC resultado = UtilAction.generarClaveSms(tokenSms);
			
			if(resultado.isExito()==false){		
				String mensaje = aplicacion.getMsjesHost("CS", resultado.getCodResult()).elementAt(2).toString();
				response.setStatus(404);
				response.getWriter().write(mensaje);
				response.getWriter().flush();
				response.getWriter().close();
			}
			
		}catch(ArrayRuleException ar){
			log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
			request.setAttribute("listaEntidades",FacadeFactory.getGeneralFacade().getComboAyudaHijo(Constante.COD_HLP_DET_ENTIDAD));
			request.setAttribute("listaTributos",FacadeFactory.getGeneralFacade().getComboDetalleHlpHijo(Constante.COD_HLP_DET_TRIBUTO,""));
			FacadeFactory.getGeneralFacade().actualizarCuentas("LG02",usuario,request.getRemoteAddr());
			response.setStatus(404);
			ar.printStackTrace();
			ar.setForward("iniciarPago");
			throw ar;
		}
		return mapping.findForward("confirmarPago");
	}
}
