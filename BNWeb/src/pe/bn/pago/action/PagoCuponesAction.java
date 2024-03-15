/*
 * Created on 19/08/2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
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
import pe.bn.pago.domain.PagoCuponera;
import pe.bn.pago.domain.impl.PagoCuponeraImpl;
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
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.CuentaImpl;
import pe.cosapi.domain.impl.OperacionImpl;
import pe.cosapi.domain.impl.ReciboImpl;
/**
 * @author 2424010
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PagoCuponesAction extends GrandActionAbstract {
	private static LoggerSati log3 = LoggerSati.getInstance(PagoCuponesAction.class.getName());

	public ActionForward iniciar(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		List listaAfiliaciones = null;
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		
		String hidServicio = request.getParameter("hidServicio");
		String hidCodEntidad = request.getParameter("hidCodEntidad");
		String hidNomEntidad = request.getParameter("hidEntidad");
		String hidCodServ	  = request.getParameter("hidCodServ");
		String typeTrans = UtilAction.obtenerTipoTransaccionFinanciera(hidCodServ);
		String codMsg 		= "0000"+hidServicio;
		
//		System.out.println("***hidServicio***: " + hidServicio);
//		System.out.println("***hidCodEntidad***: " + hidCodEntidad);
//		System.out.println("***hidCodServ***: " + hidCodServ);
//		System.out.println("***typeTrans***: " + typeTrans);
		
		if(!getVerificarHorario("PC00")){
			return mapping.findForward("transaccion.noDisponible");
		}
				
		if (hidServicio.equals("9")){
			request.getSession().setAttribute("TITULO",Constante.PAGO_CUPONES_TITULO);
			request.getSession().setAttribute(ConstanteSesion.DES_SERV_TIT, "Código Afiliado:");
			request.getSession().setAttribute(ConstanteSesion.DES_SERV_AFI, "Afiliación");
			//POR DEFINIR
			request.getSession().setAttribute("mensajeAfiliacion",((Vector)aplicacion.getMensajePorCodigo("PS00","AF006")).elementAt(2).toString()); 
			listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(hidCodEntidad,usuario.getTarjeta().getNumero(), "09043");
		}

		FacadeFactory.getGeneralFacade().actualizarCuentas("LG02",usuario,request.getRemoteAddr());
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);

		
		request.getSession().setAttribute("mensajePantalla",((Vector)aplicacion.getMensajePorCodigo("PS00",codMsg)).elementAt(2).toString());
		request.getSession().setAttribute(ConstanteSesion.DES_SUBTITULO1,"NOMBRE");
		request.getSession().setAttribute(ConstanteSesion.DES_SUBTITULO2,"NRO. DE SERVICIO");
		request.getSession().setAttribute("TITULOS",ConstanteSesion.TITULO);
		request.getSession().setAttribute("nombreEntidad",hidNomEntidad);
		request.setAttribute("hidServicio",hidServicio);
		request.getSession().setAttribute("hidServicio",hidServicio);
		request.getSession().setAttribute("hidCodEntidad",hidCodEntidad);
		request.getSession().setAttribute("typeTrans",typeTrans);
		request.getSession().setAttribute("cdSsPT",hidServicio);
		request.getSession().setAttribute("hidCodServ",hidCodServ);
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
		request.getSession().setAttribute("listaAfiliaciones",listaAfiliaciones);
		
		ParametrosElemSegTDC elementos = null;
		
		elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
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
	
	public ActionForward verPagoCupones(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		String[] id;
		
		//String pago=request.getParameter("cmbPagoCupones");
		String cuenta =request.getParameter("cmbCuenta");
		String hidCodEntidad = (String) request.getSession().getAttribute("hidCodEntidad");
		String hidCodServ = (String) request.getSession().getAttribute("hidCodServ");
		
		request.getSession().setAttribute("hidCodEntidad", hidCodEntidad);
		request.getSession().setAttribute("hidCodServ", hidCodServ);
		
		PagoCuponera pagoCupon = null;
		String optCuenta = request.getParameter("optCuenta");

		request.getSession().setAttribute("optCuenta", optCuenta);

		String codigoAfiliado="";
		if(request.getSession().getAttribute("optCuenta").equals(Constante.NUEV_REGISTRO_TRANSAC)){
			codigoAfiliado = request.getParameter("txtServicioCupones").trim();
												 
			List listaAfiliaciones = null;
				
			listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaExiste(hidCodEntidad,usuario.getTarjeta().getNumero(),Constante.COD_HLP_PGO_CUPONES, codigoAfiliado);
			
			if (listaAfiliaciones.size() > 0){
			
				throw new ArrayRuleException(ConstanteError.GENERICO,"El Codigo Afiliado: " + codigoAfiliado +" se encuentra como frecuente.");
			}

			Afiliacion afiliacion = new AfiliacionImpl();
			
			afiliacion.setFlagRegistro(Constante.NUEV_REGISTRO_TRANSAC);
					
			afiliacion.setNumeroServicio(codigoAfiliado);
	
							
			afiliacion.setTipoAfiliacion(hidCodEntidad);
			afiliacion.setCodigoLocalidad(Constante.CODIGO_LOCALIDAD_FREC);
			afiliacion.setNumeroServicio(codigoAfiliado);
			afiliacion.setNroTarjeta(usuario.getTarjeta().getNumero());
			afiliacion.setCodigoServicio(Constante.CODIGO_SERVICIO_FREC);
			
			
			request.getSession().setAttribute("afiliacion",afiliacion);
				
		}
		
		if(request.getSession().getAttribute("optCuenta").equals(Constante.FREC_REGISTRO_TRANSAC))
		{
			codigoAfiliado = request.getParameter("cmbPagoCupones");

		
		}
				
		if(codigoAfiliado!=null){
			id = ObjectUtil.getArrayStrings(codigoAfiliado,"-");
			request.getSession().setAttribute("cmbPagoCupones",codigoAfiliado);
		}else{
			id = ObjectUtil.getArrayStrings(request.getSession().getAttribute("cmbPagoCupones").toString(),"-");	
		}
		try{
			if(request.getSession().getAttribute("optCuenta").equals(Constante.FREC_REGISTRO_TRANSAC)){
				Afiliacion afil = new AfiliacionImpl();
				afil = FacadeFactory.getAfiliacionFacade().getAfiliacion(id[0],id[1],new Long(id[2]), "00436");
				afil.setFlagRegistro(Constante.FREC_REGISTRO_TRANSAC);
				request.getSession().setAttribute("afiliacion",afil);
							
		}
			Afiliacion afiliacionDatos = (Afiliacion)request.getSession().getAttribute("afiliacion");
			request.getSession().setAttribute("CORREO_AFILIADO",afiliacionDatos.getEmail());
			
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
				pagoCupon = FacadeFactory.getPagoFacade().getConsultaCupon(request.getParameter("transaccion"), cta, (Afiliacion)request.getSession().getAttribute("afiliacion"), (String)request.getSession().getAttribute("hidCodEntidad"), usuario, request.getRemoteAddr());
				pagoCupon.setCodServicio(hidCodServ);
				pagoCupon.setCodEntidad(hidCodEntidad);
				request.getSession().setAttribute(ConstanteSesion.CONSULTA, pagoCupon);
				request.getSession().setAttribute("cmbCuenta",cuenta);
			}else{
				request.setAttribute("cuenta", (Cuenta)request.getSession().getAttribute("ctaSession"));
				pagoCupon = FacadeFactory.getPagoFacade().getConsultaCupon(request.getParameter("transaccion"),  (Cuenta)request.getSession().getAttribute("ctaSession"), (Afiliacion)request.getSession().getAttribute("afiliacion"), request.getParameter("hidCodEntidad"), usuario, request.getRemoteAddr());
				pagoCupon.setCodServicio(hidCodServ);
				pagoCupon.setCodEntidad(hidCodEntidad);
				request.getSession().setAttribute(ConstanteSesion.CONSULTA, pagoCupon);
			}
				
		}
		catch(ArrayRuleException e){
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			System.out.println("!!!!!!!Entro al catch del metodo verPagoCupones");
			String codServicio= (String)request.getSession().getAttribute("cdSsPT");
			List listaAfiliaciones 	= null;			
			listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(hidCodEntidad,usuario.getTarjeta().getNumero(), "09043");
			request.setAttribute("listaAfiliaciones",listaAfiliaciones);
			throw e;
		}
		
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);

		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		
		ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
		
		return mapping.findForward("verPagoCupones");
	}
	
		
	public ActionForward confirmarPagoCupones(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)	throws Exception{
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		CuentaImpl cta = (CuentaImpl) request.getSession().getAttribute("cuenta");
		PagoCuponera pagoCuponera = (PagoCuponera)request.getSession().getAttribute(ConstanteSesion.CONSULTA);
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
		String reciboX = null;
		String importe = null;
		
		if(request.getParameter("rbRecibo") == null){
			reciboX = ((ReciboImpl)pagoCuponera.getRecibos().get(0)).getNumRecibo();
		}else{
			reciboX = request.getParameter("rbRecibo");
		}
		
		String hidCodEntidad = request.getSession().getAttribute("hidCodEntidad").toString();
		String hidCodServ = request.getSession().getAttribute("hidCodServ").toString();
		//Variable que se obtiene teniendo como base el grupo y la transaccion del servicio.
		String typeTrans = request.getSession().getAttribute("typeTrans").toString();
		
		List recibos = pagoCuponera.getRecibos();
		for (Iterator iter = recibos.iterator(); iter.hasNext();) {
			ReciboImpl recibo = (ReciboImpl) iter.next();
			if(reciboX.equals(recibo.getNumRecibo())){
				pagoCuponera.setRecibo(recibo);
				importe = recibo.getImporte();
				break;
			}
		}
		
		request.getSession().setAttribute("hidCodEntidad", hidCodEntidad);
		request.getSession().setAttribute("hidCodServ", hidCodServ);
		request.getSession().setAttribute("importe", importe);
		
		ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
		
		//REALIZAR CONSULTA TIPO CLAVE DINAMICA  --> ADD M.D.B.
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
			ar.setForward("verPagoCupones");
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
					
				}else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)){
					
//					resultCoord=SolicitarServiciosTDC.solicitarTokensSms(param);
//					
//					String urlGenerarClave = LoadDataProperties.getInstance().getValue("bn.url.rest.service.genera.token");
//					String nroDoc = datosUsuario.getNroDocumento();
//					String documentType = Funciones.lpad(datosUsuario.getTipoDocumento(), "0", 3);
//					TokenSmsRequest tokenSms = new TokenSmsRequest();
//					
//					tokenSms.setCodCliente(usuario.getCodigoCic());
//					tokenSms.setNroDocumento(nroDoc);
//					tokenSms.setTipoDocumento(documentType); //DNI
//					tokenSms.setTypeTrans(typeTrans);
//					//SOLO ESTA FUNCIONANDO PARA ADMIN
//					tokenSms.setTypeOp(Constante.COD_TRANSACCION_FINANCIERA);//FIN						
//					tokenSms.setTypeCurrency(Constante.MONEDA_SOL_ISO);//PEN
//					tokenSms.setAmount(importe);
//					
//					tokenSms.setCodCliDestinatario(pagoCuponera.getNroReferencia());
//					tokenSms.setNomCliDestinatario(pagoCuponera.getCliente());
					
					generarClaveSms(mapping, form, request, response);
					
					
				}
		
			}catch(ArrayRuleException ar){
				log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
				ar.printStackTrace();
				ar.setForward("verPagoCupones");
				throw ar;
			}
			request.getSession().setAttribute("resultCoord",resultCoord );
		}
		else{
			throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
		}

		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		request.setAttribute("cuenta",cta);
		return mapping.findForward("confirmarPagoCupones");
	}
	
	
	public ActionForward pagarCupones(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception{

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
		String nombreEntidadCupon = request.getSession().getAttribute("nombreEntidad").toString();
		String hidCodEntidad = request.getSession().getAttribute("hidCodEntidad").toString();
		String hidCodServ = request.getSession().getAttribute("hidCodServ").toString();
		String importeSol = request.getSession().getAttribute("importe").toString();
		//Variable que se obtiene teniendo como base el grupo y la transaccion del servicio.
		String typeTrans = Constante.TIP_OPER_PAGO_CUPON_SERVICIOS;//request.getSession().getAttribute("typeTrans").toString();
		
		PagoCuponeraImpl pagoCuponera = (PagoCuponeraImpl)request.getSession().getAttribute(ConstanteSesion.CONSULTA);
		pagoCuponera.setNomEntidad(nombreEntidadCupon);
		pagoCuponera.setFecha(pe.cosapi.common.ObjectUtil.getFechaActual());
		pagoCuponera.setHora(pe.cosapi.common.ObjectUtil.getHHMMSSformateado());
		
		pagoCuponera.setCodServicio(hidCodServ);
		pagoCuponera.setCodEntidad(hidCodEntidad);
		
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		
		if (((Vector)aplicacion.getMensajePorCodigo("PC00",pagoCuponera.getCodEntidad().concat("1"))).elementAt(2) == null){
			request.getSession().setAttribute("mensajeCupo1", " ");
		} else {
			request.getSession().setAttribute("mensajeCupo1", ((Vector)aplicacion.getMensajePorCodigo("PC00",pagoCuponera.getCodEntidad().concat("1"))).elementAt(2).toString());
		}
		
		if (((Vector)aplicacion.getMensajePorCodigo("PC00",pagoCuponera.getCodEntidad().concat("2"))).elementAt(2) == null){
			request.getSession().setAttribute("mensajeCupo2", " ");
		} else {
			request.getSession().setAttribute("mensajeCupo2", ((Vector)aplicacion.getMensajePorCodigo("PC00",pagoCuponera.getCodEntidad().concat("2"))).elementAt(2).toString());
		}
		
		if (((Vector)aplicacion.getMensajePorCodigo("PC00",pagoCuponera.getCodEntidad().concat("3"))).elementAt(2) == null){
			request.getSession().setAttribute("mensajeCupo3", " ");
		} else {
			request.getSession().setAttribute("mensajeCupo3", ((Vector)aplicacion.getMensajePorCodigo("PC00",pagoCuponera.getCodEntidad().concat("3"))).elementAt(2).toString());
		}
		
		if (((Vector)aplicacion.getMensajePorCodigo("PC00",pagoCuponera.getCodEntidad().concat("4"))).elementAt(2) == null){
			request.getSession().setAttribute("mensajeCupo4", " ");
		} else {
			request.getSession().setAttribute("mensajeCupo4", ((Vector)aplicacion.getMensajePorCodigo("PC00",pagoCuponera.getCodEntidad().concat("4"))).elementAt(2).toString());
		}
		
		if (((Vector)aplicacion.getMensajePorCodigo("PC00",pagoCuponera.getCodEntidad().concat("5"))).elementAt(2) == null){
			request.getSession().setAttribute("mensajeCupo5", " ");
		} else {
			request.getSession().setAttribute("mensajeCupo5", ((Vector)aplicacion.getMensajePorCodigo("PC00",pagoCuponera.getCodEntidad().concat("5"))).elementAt(2).toString());
		}
		
		try{
			ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
			request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
			
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
				ar.setForward("verPagoCupones");
				throw ar;
			}

			// Código Nuevo para TDC
			MensajesTDC resultado = new MensajesTDC();
			
			if(param!= null && coord!=null ){

				try{
					
					if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC))
					{
						resultado=SolicitarServiciosTDC.validarTDC(param ,coord,pinblock);
					
					}else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS))
					{
						resultado=SolicitarServiciosTDC.validarTokens(param ,coord,pinblock);
						
					}else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {
						
						
						String nroDoc = numDoc;
						String documentType = Funciones.lpad(tipoDoc,"0",3);
						
						TokenSmsRequest tokenSms = new TokenSmsRequest();
						tokenSms.setCodeSMS(pinblock);
						tokenSms.setCodCliente(usuario.getCodigoCic());
						tokenSms.setTipoDocumento(documentType);
						tokenSms.setNroDocumento(nroDoc);
						tokenSms.setTypeTrans(typeTrans);
						tokenSms.setTypeOp(Constante.COD_TRANSACCION_FINANCIERA);//FIN
						tokenSms.setTypeCurrency(pagoCuponera.getCuenta().getMonedaProducto().equals("USD")?"USD":"PEN");//PEN  
						tokenSms.setAmount(importeSol);
						
						tokenSms.setCodCliDestinatario(pagoCuponera.getNroReferencia());
						tokenSms.setNomCliDestinatario(pagoCuponera.getCliente());
						
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
					
					PagoCuponera pc = FacadeFactory.getPagoFacade().pagarCuponera(transaccion,pagoCuponera,(String)request.getSession().getAttribute("hidCodEntidad"), usuario,request.getRemoteAddr());
									
					Map map = UtilAction.cargarVar(request,pagoCuponera);
				
					map.put("fecpago",request.getSession().getAttribute("fecpago"));
					map.put("horpago",request.getSession().getAttribute("horpago"));
					OperacionImpl.setVariables(map);
					
					if(usuario.getNotificacion().getEstadoNotificacion().equals("1")){
						 ServiciosNotificacionPrincipal.enviarNotificacion(
								 usuario,
								 usuario.getNombre(), 
								 pc.getCuenta().getSimboloMonedaProducto(), 
								 pagoCuponera.getImporte(),
								 usuario.getTipoCambio().getVenta() ,
								 Constante.NOTI_PAGO_SERVICIOS, 
								 usuario.getNotificacion());
					}
					
					ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_SERVICIOS_CUPONES, ConstanteSesion.CONSULTA, request);
				}
				catch (ArrayRuleException e) {
					
					log3.error(e,Constante.ERR_ERROR_VALIDAR_TDC,"");
					e.printStackTrace();
					if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC))
					{
							resultCoord = SolicitarServiciosTDC.solicitCoordenada(param);
							
					}else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS))
					{
						
						resultCoord=SolicitarServiciosTDC.solicitarTokens(param);
						
					}else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS))
					{
						
						resultCoord=SolicitarServiciosTDC.solicitarTokensSms(param);
					}
					
					request.getSession().setAttribute("resultCoord",resultCoord );
					e.setForward("confirmarPagoCupones");
					throw e;
				}
			}
			
		
		
		}
		catch(ArrayRuleException e){
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			e.setForward("confirmarPagoCupones");
			throw e;
		}
		
		Afiliacion afiliacionPagoCupones = (AfiliacionImpl)request.getSession().getAttribute("afiliacion");
		if(afiliacionPagoCupones.getFlagRegistro().equals(Constante.NUEV_REGISTRO_TRANSAC)){
			
			String adicionar = request.getParameter("adicionar");
			
			if(adicionar!=null){
			
				afiliacionPagoCupones.setDescripcion(request.getParameter("txtNombreAfil").trim());
				
				try {
						Afiliacion af=FacadeFactory.getAfiliacionFacade().afiliar(usuario,afiliacionPagoCupones);
						request.getSession().setAttribute(ConstanteSesion.AFILIAR,af);
					
				} catch (ArrayRuleException e) {
					log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
					e.printStackTrace();
					e.setForward("pagarCupones");
				}
			}
		
			
		}
		return mapping.findForward("pagarCupones");
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
		
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		try {
			
			PagoCuponera pagoCuponera = (PagoCuponera)request.getSession().getAttribute(ConstanteSesion.CONSULTA);
			
			String typeTrans = Constante.TIP_OPER_PAGO_CUPON_SERVICIOS;//request.getSession().getAttribute("typeTrans").toString();
			String importe = request.getSession().getAttribute("importe").toString();

			TokenSmsRequest tokenSms = new TokenSmsRequest();
			String documentType = Funciones.lpad(tipoDoc,"0",3);
			String nroDoc = numDoc;
//			String nameClient = StringUtils.isNotEmpty(datosUsuario.getBeneficiario())?
//					datosUsuario.getBeneficiario():LoadDataProperties.getInstance().getValue("bn.name.cliente");
//			
			tokenSms.setCodCliente(usuario.getCodigoCic());
//			System.out.println("usuario.getCodigoCic():::"+usuario.getCodigoCic()); 
			
			tokenSms.setNroDocumento(nroDoc);
//			System.out.println("nroDoc::::"+nroDoc);
			
			tokenSms.setTipoDocumento(documentType); //DNI
//			System.out.println("documentType:::"+documentType);
			
			tokenSms.setTypeTrans(typeTrans);
			//SOLO ESTA FUNCIONANDO PARA ADMIN
			tokenSms.setTypeOp(Constante.COD_TRANSACCION_FINANCIERA);//FIN						
			tokenSms.setTypeCurrency(pagoCuponera.getCuenta().getMonedaProducto().equals("USD")?"USD":"PEN");//PEN
			tokenSms.setAmount(importe);
			tokenSms.setMsgClient("Generacion de Clave Dinamica Digital");
			
			tokenSms.setCodCliDestinatario(pagoCuponera.getNroReferencia());
			tokenSms.setNomCliDestinatario(pagoCuponera.getCliente());
			
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
		return mapping.findForward("confirmarPagoCupones");
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
		
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		try {
			
			PagoCuponera pagoCuponera = (PagoCuponera)request.getSession().getAttribute(ConstanteSesion.CONSULTA);
			
			
			String typeTrans = Constante.TIP_OPER_PAGO_CUPON_SERVICIOS;//request.getSession().getAttribute("typeTrans").toString();
			String importe = request.getSession().getAttribute("importe").toString();

			TokenSmsRequest tokenSms = new TokenSmsRequest();
			String documentType =Funciones.lpad(tipoDoc,"0",3);
			String nroDoc = numDoc;
//			String nameClient = StringUtils.isNotEmpty(datosUsuario.getBeneficiario())?
//					datosUsuario.getBeneficiario():LoadDataProperties.getInstance().getValue("bn.name.cliente");
//			
			tokenSms.setCodCliente(usuario.getCodigoCic());
//			System.out.println("usuario.getCodigoCic():::"+usuario.getCodigoCic()); 
			
			tokenSms.setNroDocumento(nroDoc);
//			System.out.println("nroDoc::::"+nroDoc);
			
			tokenSms.setTipoDocumento(documentType); //DNI
//			System.out.println("documentType:::"+documentType);
			
			tokenSms.setTypeTrans(typeTrans);
			//SOLO ESTA FUNCIONANDO PARA ADMIN
			tokenSms.setTypeOp(Constante.COD_TRANSACCION_FINANCIERA);//FIN						
			tokenSms.setTypeCurrency(pagoCuponera.getCuenta().getMonedaProducto().equals("USD")?"USD":"PEN");//PEN
			tokenSms.setAmount(importe);
			tokenSms.setMsgClient("Generacion de Clave Dinamica Digital");
			
			tokenSms.setCodCliDestinatario(pagoCuponera.getNroReferencia());
			tokenSms.setNomCliDestinatario(pagoCuponera.getCliente());
			
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
		return mapping.findForward("confirmarPagoCupones");
	}
}
