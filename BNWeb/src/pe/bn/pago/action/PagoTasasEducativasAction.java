/*
 * Created on 19/02/2017
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pe.bn.pago.action;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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

import pe.bn.afiliacion.action.DesafCableAction;
import pe.bn.afiliacion.action.form.AfiliacionDatosClientesForm;
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
import pe.bn.pago.action.form.UniversidadesForm;
import pe.bn.pago.domain.PagoCuponera;
import pe.bn.pago.domain.PagoFactura;
import pe.bn.pago.domain.PagoSedapal;
import pe.bn.pago.domain.PagoTasas;
import pe.bn.pago.domain.PagoUniversidad;
import pe.bn.pago.domain.impl.PagoCuponeraImpl;
import pe.bn.pago.domain.impl.PagoSedapalImpl;
import pe.bn.pago.domain.impl.PagoTelefonoImpl;
import pe.bn.pago.domain.impl.PagoUniversidadImpl;
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
import pe.cosapi.domain.DetalleAyudaDatos;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.BuscadorComboImpl;
import pe.cosapi.domain.impl.CuentaImpl;
import pe.cosapi.domain.impl.DetalleAyudaDatosImpl;
import pe.cosapi.domain.impl.OperacionImpl;
import pe.cosapi.domain.impl.ReciboImpl;
/**
 * @author mdolores
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PagoTasasEducativasAction extends GrandActionAbstract {
	private static LoggerSati log3 = LoggerSati.getInstance(PagoTasasEducativasAction.class.getName());

	public ActionForward iniciar(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		
		PagoUniversidad pagoUniversidad = null;
		UniversidadesForm frm = (UniversidadesForm) form;
		frm.reset(mapping, request);

		String hidServicio = request.getParameter("hidServicio");
		String hidCodServ	  = request.getParameter("hidCodServ");
		String hidCodEntidad = request.getParameter("hidCodEntidad");
		String hidNomEntidad = request.getParameter("hidEntidad");
		String typeTrans = UtilAction.obtenerTipoTransaccionFinanciera(hidCodServ);
		
		pagoUniversidad = new PagoUniversidadImpl();
		pagoUniversidad.setCodUniversidad(hidCodEntidad);
		pagoUniversidad.setDescUniversidad(hidNomEntidad);
			
		String codMsg = "0000"+hidServicio;
		

		
				
		if (hidServicio.equals("9")){
				request.getSession().setAttribute("TITULO",Constante.PAGO_UNIVERSIDADES_TITULO);
				request.getSession().setAttribute(ConstanteSesion.DES_SERV_TIT, "Código Alumno:");
				request.getSession().setAttribute(ConstanteSesion.DES_SERV_AFI, "Afiliación");
				
		}
				
		FacadeFactory.getGeneralFacade().actualizarCuentas("LG02",usuario,request.getRemoteAddr());
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		
			
		
		request.getSession().setAttribute("mensajeAfiliacion",((Vector)aplicacion.getMensajePorCodigo("PS00","PU001")).elementAt(2).toString());
		request.getSession().setAttribute("mensajePantalla",((Vector)aplicacion.getMensajePorCodigo("PS00",codMsg)).elementAt(2).toString());
		request.getSession().setAttribute(ConstanteSesion.DES_SUBTITULO1,"NOMBRE");
		request.getSession().setAttribute(ConstanteSesion.DES_SUBTITULO2,"NRO. DE SERVICIO");
		request.getSession().setAttribute("TITULOS",ConstanteSesion.TITULO);
		request.getSession().setAttribute("nombreEntidad",hidNomEntidad);
		request.getSession().setAttribute("hidServicio",hidServicio);
		request.getSession().setAttribute("hidCodEntidad",hidCodEntidad);
		request.getSession().setAttribute("hidEntidad",hidNomEntidad);
		request.getSession().setAttribute("typeTrans",typeTrans);
		request.getSession().setAttribute("cdSsPT",hidServicio);
		request.getSession().setAttribute("hidCodServ",hidCodServ);
		request.getSession().setAttribute(ConstanteSesion.PAGO_UNIVERSIDADES,pagoUniversidad);
		request.getSession().setAttribute("lstDocumento",FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.UNI_TIPO_DOCUMENTO));
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
		
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
		
		return mapping.findForward("iniciar");
	}
	
	public ActionForward verConsultaAlumno(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
	
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		//Afiliacion datosUsuario = FacadeFactory.getGeneralFacade().obtenerDatosPersonalesAfiliacion(usuario);
		String tipoDocP="";
		String numDoc="";
		
		if(usuario.getCodigoCLDI() != null){
						tipoDocP=usuario.getCodigoCLDI().substring(12,13);
						numDoc=usuario.getCodigoCLDI().substring(13);	
						if(tipoDocP != "1"){
							numDoc=Util.removeZero(numDoc);
						}else{
							numDoc= numDoc.substring(4);
						}
		}
		String cuenta =request.getParameter("cmbCuenta");
		String tipoDoc  = request.getParameter("cmbTipoDoc");
		String nroDoc = request.getParameter("txtNroDoc").trim();
		String nombre = request.getParameter("txtNombres").trim();
		String importe = "";
		String hidCodEntidad = request.getSession().getAttribute("hidCodEntidad").toString();
		String hidCodServ = request.getSession().getAttribute("hidCodServ").toString();
		String typeTrans = request.getSession().getAttribute("typeTrans").toString();
		
		request.getSession().setAttribute("hidCodEntidad", hidCodEntidad);
		request.getSession().setAttribute("hidCodServ", hidCodServ);
		request.getSession().setAttribute("typeTrans", typeTrans);
		
		request.getSession().setAttribute("importe", "");
		
		PagoUniversidad pagoUniversidad = null;
		pagoUniversidad= (PagoUniversidad)request.getSession().getAttribute(ConstanteSesion.PAGO_UNIVERSIDADES);
		
		try{

			if(cuenta!=null){
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
				cta	= (CuentaImpl)usuario.getMapCuentas().get(cuenta);
				}else{
					for(int i=0; i<x.size();i++){
						cta=(CuentaImpl)x.get(i);
						if(!cta.getTipoProducto().equals("44")&& cta.getNumeroProducto().equals(cuenta)){
							break;
											
								}	
							}				
				}
				request.setAttribute("cuenta",cta);
				request.getSession().setAttribute("ctaSession",cta);
				
				loadObject(request,pagoUniversidad);
			
				pagoUniversidad.setCuenta((Cuenta)cta);
				pagoUniversidad.setNombreCompleto(nombre.toUpperCase());	
				pagoUniversidad.setTipoPersona(Constante.UNI_TIPO_PERSONA_OTROS);
				pagoUniversidad.setTipoDoc(tipoDoc);
				pagoUniversidad.setNroDoc(nroDoc);
						
				List documento = FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.UNI_TIPO_DOCUMENTO);
						
				for(int y=0; y< documento.size();y++){
					ComboUtil temp = new ComboUtil();
					temp = (ComboUtil)documento.get(y);
					if(pagoUniversidad.getTipoDoc().equals(temp.getCodigo()))
						pagoUniversidad.setTipoDocDesc(temp.getDescripcion());
				}
				request.getSession().setAttribute(ConstanteSesion.PAGO_UNIVERSIDADES,pagoUniversidad);
			}
			
			//importe = pagoUniversidad.getImportePago().toString();
			
			//REALIZAR CONSULTA TIPO CLAVE DINAMICA
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
				request.setAttribute("listaEntidades",FacadeFactory.getGeneralFacade().getComboAyudaHijo(Constante.COD_HLP_DET_ENTIDAD));
				request.setAttribute("listaTributos",FacadeFactory.getGeneralFacade().getComboDetalleHlpHijo(Constante.COD_HLP_DET_TRIBUTO,""));
				ar.printStackTrace();
				ar.setForward("iniciarPago");
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
							//generarClaveSms(mapping, form, request, response);							
						}	
						
				
					}catch(ArrayRuleException ar){
						log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
						loadObject(request, pagoUniversidad);
						FacadeFactory.getGeneralFacade().actualizarCuentas("LG02",usuario,request.getRemoteAddr());
						ar.printStackTrace();
						ar.setForward("verConsultaAlumno");
						throw ar;
					}
				request.getSession().setAttribute("resultCoord",resultCoord );
				
			}else{
				
				throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
			}

		}
		catch(ArrayRuleException e){
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			e.setForward("iniciar");
			System.out.println("!!!!!!!Entro al catch del metodo ");
			
			throw e;
		}
		
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
		
		return mapping.findForward("confirmarTasaEducativa");
	}
			
	
	public ActionForward pagar(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception{
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
		
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		
		ActionForward forward = new ActionForward();
		forward = mapping.findForward("pagarTasaEducativa");

		PagoUniversidad pagoUniversidad = null;
		pagoUniversidad= (PagoUniversidad)request.getSession().getAttribute(ConstanteSesion.PAGO_UNIVERSIDADES);
		pagoUniversidad.setFechaSistema(pe.cosapi.common.ObjectUtil.getFechaActual());
		pagoUniversidad.setHora(pe.cosapi.common.ObjectUtil.getHHMMSSformateado());
		UniversidadesForm frm = (UniversidadesForm) form;
		String importeSol = frm.getTxtImporteFlag()==null?frm.getTxtImporte().toString():frm.getTxtImporteFlag();
		String typeTrans = Constante.TIP_OPER_PAGO_UNIVERSIDADES;//request.getSession().getAttribute("typeTrans").toString();
		
		try{
			
			ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
			
			ElemenSeguridad resultCoord = null;
			ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
			ElemenSeguridad coord = (ElemenSeguridad)request.getSession().getAttribute("resultCoord");
			String pinblock = ObjectUtil.getClaveDesencriptada(request.getParameter("txtCoordenada"),request);
			ParametrosElemSegTDC elementos = null;
			
			request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
			request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
			
			try{
				elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
				
				if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS) ||
				   elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
					elementos = SolicitarServiciosTDC.verificarTipoElemento(param,usuario);
				}	
				
				
			}catch(ArrayRuleException ar){
				log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
				ar.printStackTrace();
				ar.setForward("confirmarTasaEducativa");
				throw ar;
			}
			
			
			// Código Nuevo para TDC
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
//						String nameClient = StringUtils.isNotEmpty(datosUsuario.getBeneficiario())?datosUsuario.getBeneficiario():LoadDataProperties.getInstance().getValue("bn.name.cliente");
						
						TokenSmsRequest tokenSms = new TokenSmsRequest();

						tokenSms.setCodeSMS(pinblock);
						tokenSms.setCodCliente(usuario.getCodigoCic());
						tokenSms.setTipoDocumento(documentType);
						tokenSms.setNroDocumento(nroDoc);
						tokenSms.setTypeTrans(typeTrans);
						tokenSms.setTypeOp(Constante.COD_TRANSACCION_FINANCIERA);//FIN
						tokenSms.setTypeCurrency(Constante.MONEDA_SOL_ISO);//PEN
						tokenSms.setAmount(importeSol);
						tokenSms.setMsgClient("Validacion de Clave Dinamica Digital");
						tokenSms.setNomCliDestinatario(pagoUniversidad.getNombreCompleto());
						tokenSms.setCodCliDestinatario(pagoUniversidad.getNroDoc());
						
				
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
					
				
					
					PagoUniversidad pago =setearPago(frm, usuario, pagoUniversidad, request);
					if(pagoUniversidad.getFlagImporte() == null){
						pago.setImportePago(new BigDecimal(frm.getTxtImporte()));
					}else{
						
					
						double monto= Double.parseDouble(frm.getTxtImporteFlag());
						if(monto==0){
							ArrayRuleException e2 = new ArrayRuleException(ConstanteError.GENERICO,"Verifique el monto ingresado");
							e2.setForward("confirmarTasaEducativa");
							throw e2;
						}
						
						BigDecimal nMonto;
						nMonto = new BigDecimal(ObjectUtil.replaceChar(frm.getTxtImporteFlag(),',',""));
						nMonto = nMonto.setScale(2,BigDecimal.ROUND_HALF_EVEN);
						
						pago.setImportePago(nMonto);
						
					}
					
					pago.setTipoDocDesc(pagoUniversidad.getTipoDocDesc());
					PagoUniversidadImpl pagoUni=(PagoUniversidadImpl)FacadeFactory.getPagoFacade().getPagarTasaEducativa(Constante.UNI_TRANSACION_PAGO_TASA_EDUCATIVA, pago, pagoUniversidad.getCuenta(),usuario, request.getRemoteAddr(),request);
					request.getSession().setAttribute(ConstanteSesion.PAGO_UNIVERSIDADES,pagoUni);
					Map map = UtilAction.cargarVar(request,pagoUni);
					OperacionImpl.setVariables(map);
					
					if(usuario.getNotificacion().getEstadoNotificacion().equals("1")){
						 ServiciosNotificacionPrincipal.enviarNotificacion(
								 usuario,
								 usuario.getNombre(), 
								 pagoUni.getCuenta().getSimboloMonedaProducto(), 
								 pagoUni.getImporte(),
								 usuario.getTipoCambio().getVenta() ,
								 Constante.NOTI_PAGO_TASAS, 
								 usuario.getNotificacion());
					}
					
					ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_SERVICIOS_TASAS_EDUCATIVAS, ConstanteSesion.PAGO_UNIVERSIDADES, request);
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
					e.setForward("confirmarTasaEducativa");
					throw e;
				}
			}
	
		}
		catch(ArrayRuleException e){
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			e.setForward("confirmarTasaEducativa");
			throw e;
		}
		

		return forward;
	}
	
	
	public ActionForward actualizarImporte(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
			
		UniversidadesForm frm = (UniversidadesForm) form;
		PagoUniversidad pagoUniversidad = new PagoUniversidadImpl();
		List listaImportes = null;
		
		try {
			
			PagoUniversidad datos= (PagoUniversidad)request.getSession().getAttribute(ConstanteSesion.PAGO_UNIVERSIDADES);
			
			pagoUniversidad = setearPago(frm, usuario, datos, request);
			
			listaImportes = FacadeFactory.getPagoFacade().getListaImporteTasaEducativa(pagoUniversidad.getConcepto());
			
			if(listaImportes == null || listaImportes.size() == 0){
				
				throw new ArrayRuleException(ConstanteError.GENERICO,"Tarifario de la tasa educativa NO VIGENTE ");
			}
			
			for(int y=0; y< listaImportes.size();y++){
				
				ComboUtil temp = new ComboUtil();
				
				temp = (ComboUtil)listaImportes.get(y);
							
				if(Double.parseDouble(temp.getCodigo())!=0){
				
					frm.setTxtImporte(""+temp.getCodigo());
					request.getSession().setAttribute("importe", ""+temp.getCodigo());
					
				}else{
					pagoUniversidad.setFlagImporte(Constante.UNI_TRANSACION_FLAG_IMP_READONLY);
				}
						
		  }
			
			
			//Reenviar datos 
			
			pagoUniversidad.setCuenta(datos.getCuenta());
			pagoUniversidad.setDescUniversidad(datos.getDescUniversidad());
			pagoUniversidad.setNombreCompleto(datos.getNombreCompleto());
			pagoUniversidad.setNroDoc(datos.getNroDoc());
			pagoUniversidad.setTipoDoc(datos.getTipoDoc());
			pagoUniversidad.setTipoDocDesc(datos.getTipoDocDesc());
			pagoUniversidad.setCodUniversidad(datos.getCodUniversidad());
			 
				
		} catch (ArrayRuleException e) {
			
			e.printStackTrace();
			loadObject(request, pagoUniversidad);
			throw e;
//			forward = mapping.findForward("confirmarTasaEducativa");
//			return (forward);	
			
		}
				
		
		request.getSession().setAttribute(ConstanteSesion.PAGO_UNIVERSIDADES,pagoUniversidad);
		forward = mapping.findForward("confirmarTasaEducativa");
		return (forward);	
		
	}
	
	 private PagoUniversidad setearPago(UniversidadesForm frm, Usuario usuario,PagoUniversidad universidad, HttpServletRequest request) throws Exception {
		 
		 PagoUniversidad pago = new PagoUniversidadImpl();
		 //Datos del alumno

		 pago.setFechaSistema(universidad.getFechaSistema());
		 pago.setCodUniversidad(universidad.getCodUniversidad());
		 pago.setTipoPersona(universidad.getTipoPersona());
		 pago.setTipoDoc(universidad.getTipoDoc()== null?"":universidad.getTipoDoc());
		 pago.setNroDoc(universidad.getNroDoc()== null?"":universidad.getNroDoc());
		
		 pago.setNombreCompleto(universidad.getNombreCompleto()== null?"":universidad.getNombreCompleto().toUpperCase());
		 
		 //Datos del formulario
		 pago.setConcepto(frm.getCmbConcepto());
		 pago.setCodSituacion(frm.getCmbSituacion()== null?"":frm.getCmbSituacion());
		 pago.setSede(frm.getCmbSede()== null?"":frm.getCmbSede());
		 
		 
		 return pago;
		 
	 }
	
	 private void loadObject(HttpServletRequest request, PagoUniversidad pago) throws Exception {
		        
         request.getSession().setAttribute("lstConcepto", FacadeFactory.getGeneralFacade().getComboDetalleConcepto(Constante.UNI_DETALLE_CONCEPTO,pago.getCodUniversidad()));
         request.getSession().setAttribute("lstSituación", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.UNI_DETALLE_SITUACION));
         request.getSession().setAttribute("lstSede",FacadeFactory.getGeneralFacade().getComboDetalleConcepto(Constante.UNI_SEDE_UNIVERSIDADES,pago.getCodUniversidad()));
       

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
				
				PagoUniversidad pagoUniversidad = null;
				pagoUniversidad= (PagoUniversidad)request.getSession().getAttribute(ConstanteSesion.PAGO_UNIVERSIDADES);
				
				String typeTrans = Constante.TIP_OPER_PAGO_UNIVERSIDADES;//request.getSession().getAttribute("typeTrans").toString();
				String importe = request.getParameter("importePago");

				TokenSmsRequest tokenSms = new TokenSmsRequest();
				String documentType = Funciones.lpad(tipoDoc,"0",3);
				String nroDoc = numDoc;
				
				tokenSms.setCodCliente(usuario.getCodigoCic());
				tokenSms.setTipoDocumento(documentType);
				tokenSms.setNroDocumento(nroDoc);
				tokenSms.setTypeTrans(typeTrans);
				tokenSms.setTypeOp(Constante.COD_TRANSACCION_FINANCIERA);//FIN
				tokenSms.setTypeCurrency(Constante.MONEDA_SOL_ISO);//PEN
				tokenSms.setAmount(importe);
				tokenSms.setMsgClient("Generacion de Clave Dinamica Digital");
				tokenSms.setNomCliDestinatario(pagoUniversidad.getNombreCompleto());
				tokenSms.setCodCliDestinatario(pagoUniversidad.getNroDoc());

				MensajesTDC resultado = UtilAction.generarClaveSms(tokenSms);
				BNAplicacion aplicacion = BNAplicacion.getInstance();
				
				
				
			}catch(ArrayRuleException ar){
				log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
				response.setStatus(404);
				request.setAttribute("listaEntidades",FacadeFactory.getGeneralFacade().getComboAyudaHijo(Constante.COD_HLP_DET_ENTIDAD));
				request.setAttribute("listaTributos",FacadeFactory.getGeneralFacade().getComboDetalleHlpHijo(Constante.COD_HLP_DET_TRIBUTO,""));
				FacadeFactory.getGeneralFacade().actualizarCuentas("LG02",usuario,request.getRemoteAddr());
				ar.printStackTrace();
				ar.setForward("iniciarPago");
				throw ar;
			}
			return mapping.findForward("confirmarPagoFacturas");
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
				
				PagoUniversidad pagoUniversidad = null;
				pagoUniversidad= (PagoUniversidad)request.getSession().getAttribute(ConstanteSesion.PAGO_UNIVERSIDADES);
				
				String typeTrans = Constante.TIP_OPER_PAGO_UNIVERSIDADES;//request.getSession().getAttribute("typeTrans").toString();
				String importe = request.getParameter("importePago");

				TokenSmsRequest tokenSms = new TokenSmsRequest();
				String documentType = Funciones.lpad(tipoDoc,"0",3);
				String nroDoc = numDoc;
				
				tokenSms.setCodCliente(usuario.getCodigoCic());
				tokenSms.setTipoDocumento(documentType);
				tokenSms.setNroDocumento(nroDoc);
				tokenSms.setTypeTrans(typeTrans);
				tokenSms.setTypeOp(Constante.COD_TRANSACCION_FINANCIERA);//FIN
				tokenSms.setTypeCurrency(Constante.MONEDA_SOL_ISO);//PEN
				tokenSms.setAmount(importe);
				tokenSms.setMsgClient("Generacion de Clave Dinamica Digital");
				tokenSms.setNomCliDestinatario(pagoUniversidad.getNombreCompleto());
				tokenSms.setCodCliDestinatario(pagoUniversidad.getNroDoc());

				MensajesTDC resultado = UtilAction.generarClaveSms(tokenSms);
				BNAplicacion aplicacion = BNAplicacion.getInstance();
				
				if(resultado.isExito()==false){		
					String mensaje = aplicacion.getMsjesHost("CS", resultado.getCodResult()).elementAt(2).toString();
					response.setStatus(404);
					response.getWriter().write(mensaje);
					response.getWriter().flush();
					response.getWriter().close();
				}
				
			}catch(ArrayRuleException ar){
				log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
				response.setStatus(404);
				request.setAttribute("listaEntidades",FacadeFactory.getGeneralFacade().getComboAyudaHijo(Constante.COD_HLP_DET_ENTIDAD));
				request.setAttribute("listaTributos",FacadeFactory.getGeneralFacade().getComboDetalleHlpHijo(Constante.COD_HLP_DET_TRIBUTO,""));
				FacadeFactory.getGeneralFacade().actualizarCuentas("LG02",usuario,request.getRemoteAddr());
				ar.printStackTrace();
				ar.setForward("iniciarPago");
				throw ar;
			}
			return mapping.findForward("confirmarPagoFacturas");
		}
}
