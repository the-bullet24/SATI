/*
 * Created on 20/03/2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pe.bn.pago.action;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.sql.Date;
import java.sql.Timestamp;
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
import pe.bn.pago.domain.PagoFacturaWS;
import pe.bn.pago.domain.impl.PagoFacturaWSImpl;
import pe.bn.pago.domain.impl.PagoTelefonoImpl;
import pe.bn.transferencia.domain.Transferencia;
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
import pe.cosapi.common.SeguridadUtil;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.CuentaImpl;
import pe.cosapi.domain.impl.DetalleAyudaDatosImpl;
import pe.cosapi.domain.impl.FacturaImpl;
import pe.cosapi.domain.impl.OperacionImpl;
/**
 * @author 2424012
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PagoFacturasWSAction extends GrandActionAbstract {
	private static LoggerSati log3 = LoggerSati.getInstance(PagoFacturasWSAction.class.getName());

	public ActionForward iniciar(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		BNAplicacion aplicacion = BNAplicacion.getInstance();
					
		String hidServicio = request.getParameter("hidServicio");
		String hidCodServ	  = request.getParameter("hidCodServ");
		String hidCodEntidad = request.getParameter("hidCodEntidad");
		String hidNomEntidad = request.getParameter("hidEntidad");
		String typeTrans = UtilAction.obtenerTipoTransaccionFinanciera(hidCodServ);
		
//		System.out.println("***hidServicio***: " + hidServicio);
//		System.out.println("***hidCodServ***: " + hidCodServ);
//		System.out.println("***hidCodEntidad***: " + hidCodEntidad);
//		System.out.println("***hidNomEntidad***: " + hidNomEntidad);
//		System.out.println("***typeTrans***: " + typeTrans);
			
		String codMsg = "000"+hidServicio;
		
		
		if(!getVerificarHorario("PF00")){
			request.getSession().setAttribute("mensaje1Hor",((Vector)aplicacion.getMensajePorCodigo("MH01","MH001")).elementAt(2).toString());
			request.getSession().setAttribute("mensaje2Hor",((Vector)aplicacion.getMensajePorCodigo("MH01","MH002")).elementAt(2).toString());
			request.getSession().setAttribute("mensajeHorC",((Vector)aplicacion.getMensajePorCodigo("MH01","3244"==null?"0112":"3244")).elementAt(2).toString());
			return mapping.findForward("transaccion.noDisponible");
		}
		
				
		if (hidServicio.equals("12") || hidServicio.equals("14") ){
				request.getSession().setAttribute("TITULO",Constante.PAGO_FACTURAS_TITULO);
				request.getSession().setAttribute(ConstanteSesion.DES_SERV_TIT, "Código Afiliado:");
				request.getSession().setAttribute(ConstanteSesion.DES_SERV_AFI, "Afiliación");
				//POR DEFINIR
				request.getSession().setAttribute("mensajeAfiliacion",((Vector)aplicacion.getMensajePorCodigo("PS00","AF007")).elementAt(2).toString()); 
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
		request.getSession().setAttribute("cdSsPT",hidServicio);
		request.getSession().setAttribute("hidCodServ",hidCodServ);
		request.getSession().setAttribute("typeTrans",typeTrans);
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
		
		return mapping.findForward("iniciarWS");
	}
	
	public ActionForward verPagoFacturas(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
	
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		String numReferencia="";
		String cuenta ="";
		String hidCodEntidad="";
		String hidCodServ ="";
		
		numReferencia = request.getParameter("txtNumDoc");
		cuenta =request.getParameter("cmbCuenta");
		hidCodEntidad = (String) request.getSession().getAttribute("hidCodEntidad");
		hidCodServ = (String) request.getSession().getAttribute("hidCodServ");
		
		request.getSession().setAttribute("hidCodEntidad", hidCodEntidad);
		request.getSession().setAttribute("hidCodServ", hidCodServ);
				
		request.getSession().setAttribute("hidCodEntidad", hidCodEntidad);
		
		DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
	    List list= dHelp_.getListDetalleAyudaHijo("09044", hidCodEntidad.trim());
	    
		PagoFacturaWS pagoFactura = null;
		String codEntidad = hidCodEntidad.trim();
		FacturaImpl pagos = null;
		
		Iterator it = list.iterator();
	    while (it.hasNext()){
	        DetalleAyudaDatosImpl dHelp = (DetalleAyudaDatosImpl)it.next();
	        codEntidad = dHelp.getCodigoDetalleAyuda1().trim();
	    }
	    
	    request.getSession().setAttribute("hidCodEntidadReal", codEntidad.trim());
	    
		try{	

			request.getSession().setAttribute("CORREO_AFILIADO","");
			
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
				
				//Completar digitos - Para Academia de la magistratura
				
				if(codEntidad.equals("1060")||codEntidad.equals("2000")){
					
					numReferencia = Funciones.lpad(numReferencia, "0", 20);
					
				}
			
				pagoFactura = FacadeFactory.getPagoFacade().getConsultaFacturaOnline(request.getParameter("transaccion"), cta, numReferencia, codEntidad, usuario, request.getRemoteAddr());
				pagoFactura.setCodServicio(hidCodServ);
				pagoFactura.setCodEntidad(hidCodEntidad);
				
							
				if(pagoFactura.getFacturas().size()>0){
					
					for(int i=0;i<pagoFactura.getFacturas().size();i++)
					{
						 pagos = (FacturaImpl)pagoFactura.getFacturas().get(i);
						 						
						if(Integer.parseInt(pagos.getSecuencia().trim())>0 && Integer.parseInt(pagos.getSecuencia().trim())<2){
							pagos.setSecuenciaMenor(""+Integer.parseInt(pagos.getSecuencia().trim()));
											
							pagoFactura.setFactura(pagos);
						
						}
					
						
					}
					
				}
				
				
				request.getSession().setAttribute(ConstanteSesion.CONSULTA, pagoFactura);
				
				String cCLiente = "";
				if (pagoFactura.getFacturas().size()>0) {
					cCLiente = pagoFactura.getNomEntidad();
				} else{
					throw new ArrayRuleException(ConstanteError.GENERICO,"UD. NO TIENE FACTURAS PENDIENTES DE PAGO");
				}
					
				
				request.getSession().setAttribute("nomCliente", cCLiente);
				
				request.getSession().setAttribute("cmbCuenta",cuenta);
			}else{
				request.setAttribute("cuenta", (Cuenta)request.getSession().getAttribute("ctaSession"));
				pagoFactura = FacadeFactory.getPagoFacade().getConsultaFacturaOnline(request.getParameter("transaccion"), (Cuenta)request.getSession().getAttribute("ctaSession"), numReferencia, codEntidad, usuario, request.getRemoteAddr());
				String cCLiente = "";
				
				if (pagoFactura.getFacturas().size()>0) {
					cCLiente = pagoFactura.getNomEntidad();
				} else{
					throw new ArrayRuleException(ConstanteError.GENERICO,"UD. NO TIENE FACTURAS PENDIENTES DE PAGO");
				}
				
				pagoFactura.setCodServicio(hidCodServ);
				pagoFactura.setCodEntidad(hidCodEntidad);
				
				request.getSession().setAttribute(ConstanteSesion.CONSULTA, pagoFactura);
				request.getSession().setAttribute("nomCliente", cCLiente);
			}
				
		}
		catch(ArrayRuleException e){
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			System.out.println("!!!!!!!Entro al catch del metodo verPagoFacturas");
			e.printStackTrace();
			String codServicio= (String)request.getSession().getAttribute("cdSsPT");
			throw e;
		}
		
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);

		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		
		ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
		
		return mapping.findForward("verPagoFacturasWS");
	}
	
		
	public ActionForward confirmarPagoFacturas(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)	throws Exception{
		
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


		ActionForward forward = new ActionForward();

		String[] id = ObjectUtil.getArrayStrings(request.getParameter("optSecuencia"),"-");
		String hidCodEntidad = request.getSession().getAttribute("hidCodEntidad").toString();
		String hidCodEntidadReal = (String) request.getSession().getAttribute("hidCodEntidadReal");
		String typeTrans = request.getSession().getAttribute("typeTrans").toString();
		String importe = "";
		
		CuentaImpl cta = (CuentaImpl) request.getSession().getAttribute("cuenta");

		
		
		String cCodSec = null;
		String cNumRef = null;
		if(hidCodEntidad.equals("2040")){
			cCodSec = id[2].trim();
			cNumRef = id[0]+"-"+id[1].trim();
		} else 	cCodSec = id[1].trim();
		
		PagoFacturaWS pagoFactura = (PagoFacturaWS)request.getSession().getAttribute(ConstanteSesion.CONSULTA);
						
		List facturas = pagoFactura.getFacturas();
		for (Iterator iter = facturas.iterator(); iter.hasNext();) {
			FacturaImpl factura = (FacturaImpl) iter.next();
		
			if(cCodSec.equals(factura.getSecuencia().trim())){
				if(hidCodEntidad.equals("2040")){
					pagoFactura.setNroReferencia(cNumRef);
					importe = pagoFactura.getFactura().getImporte();
				}
				pagoFactura.setFactura(factura);
				break;
			}
		}
		
		ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		
		request.getSession().setAttribute("hidCodEntidad",hidCodEntidad);
		request.getSession().setAttribute("hidCodEntidadReal", hidCodEntidadReal);
		request.getSession().setAttribute("typeTrans", typeTrans);
		request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);	
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
			if(hidCodEntidad.equals(Constante.gBN_CONST_PAGO_FACTURAS_UNIQUE)){
				ar.setForward("verPagoFacturasWS");
			}else{
				ar.setForward("verPagoFacturasWS");
			}
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
				if(hidCodEntidad.equals(Constante.gBN_CONST_PAGO_FACTURAS_UNIQUE)){
					ar.setForward("confirmarPagoFacturasWSP");
				}else{
					ar.setForward("confirmarPagoFacturasWS");
				}
				throw ar;
			}
			request.getSession().setAttribute("resultCoord",resultCoord );
		}
		else{
			
			throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
		}
		
		request.setAttribute("cuenta",cta);
		
		if(hidCodEntidad.equals(Constante.gBN_CONST_PAGO_FACTURAS_UNIQUE)){
			forward = mapping.findForward("confirmarPagoFacturasWSP");
		}
		else{
			forward = mapping.findForward("confirmarPagoFacturasWS");
		}
		return  (forward);
	}
	
	public ActionForward pagarFacturaOnline(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception{
		
		ActionForward forward = new ActionForward();
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

		Cuenta cuenta =  (Cuenta)request.getSession().getAttribute("ctaSession");
		
		
		String cNomEmpresa = request.getSession().getAttribute("nombreEntidad").toString();
		String hidCodEntidad = request.getSession().getAttribute("hidCodEntidad").toString();
		String hidCodServ = request.getSession().getAttribute("hidCodServ").toString();
		String typeTrans = Constante.TIP_OPER_PAGO_CUPON_SERVICIOS;//request.getSession().getAttribute("typeTrans").toString();
		String ctaorigen=request.getParameter("origen");
		String codMoneda = request.getParameter("codMoneda");
		String codTransaccion = request.getParameter("transaccion");
		String importe = request.getParameter("importeOriginal").toString();
		String importeOriginal = request.getParameter("importeOriginal").toString();
		String abonado = request.getParameter("abonado").toString();
		//String origen = request.getParameter("origen").toString();
		String empresa = cNomEmpresa+ConstanteSesion.TIT_PAGO_FACTURA_ONLINE;

		BigDecimal montoSol = null;
		BigDecimal montoDol = null;
		BigDecimal montoTipCambio = null;
		BigDecimal cTipoCambio = null;
		
		PagoFacturaWSImpl pagoFactura = (PagoFacturaWSImpl)request.getSession().getAttribute(ConstanteSesion.CONSULTA);
		String cNumReferencia = pagoFactura.getNroReferencia();
		
		
		//String cCodCliente = pagoFactura.getFactura().getCodClient();
		//String cNroCuota   = pagoFactura.getFactura().getSecuencia();
		Timestamp t        = pagoFactura.getFactura().getFecha(); 					 			
	 	Date data          = new Date(t.getTime());
		String fecha       = (ObjectUtil.replaceChar(data.toString(),'-',""));
		
		
		
		pagoFactura.setCodServicio(hidCodServ);
		pagoFactura.setCodEntidad(hidCodEntidad);
		pagoFactura.setNomEntidad(cNomEmpresa);
		
		if(hidCodEntidad.equals(Constante.gBN_CONST_PAGO_FACTURAS_UNIQUE)){
			pagoFactura.getFactura().setImpCuota(new BigDecimal(ObjectUtil.replaceChar(importeOriginal,',',"")));
			pagoFactura.getFactura().setImporte(new BigDecimal(ObjectUtil.replaceChar(importe,',',"")));
		}
		
		request.getSession().setAttribute("hidCodEntidad", hidCodEntidad);
		request.getSession().setAttribute("cmbCuenta",cuenta);
		request.getSession().setAttribute("empresa",empresa);
		
		log3.debug("pagarFacturaOnline", ""+usuario.getTipoCambio().getCompra(),Constante.LOGGER_DEBUG_NIVEL_1);
		
		if(hidCodEntidad.equals("3080") ){
			String importeSol = request.getParameter("importeSol").toString();
			cTipoCambio = usuario.getTipoCambio().getCompra().setScale(2,RoundingMode.HALF_UP);
		 	log3.debug("pagarFacturaOnline - cTipoCambio", ""+cTipoCambio,Constante.LOGGER_DEBUG_NIVEL_1);
		 	montoSol = new BigDecimal(ObjectUtil.replaceChar(importeSol,',',""));
			
			if(cuenta.getMonedaProducto().equals(Constante.MONEDA_DOLAR)){
				String importeDol = request.getParameter("importeDol").toString();
				montoDol = new BigDecimal(ObjectUtil.replaceChar(importeDol,',',""));
				montoTipCambio = new BigDecimal(ObjectUtil.replaceChar(cTipoCambio.toString(),',',""));
			}
		
		}
		
		//String nMonto=(ObjectUtil.replaceChar(importe,'.',""));
		//String montoformat = request.getParameter("importe");

		request.getSession().setAttribute("fecpago",pe.cosapi.common.ObjectUtil.getFechaActual());
		request.getSession().setAttribute("horpago",pe.cosapi.common.ObjectUtil.getHHMMSSformateado());
		if(hidCodEntidad.equals(Constante.gBN_CONST_PAGO_FACTURAS_UNIQUE)){
			request.getSession().setAttribute("importeOriginal",importeOriginal);
			request.getSession().setAttribute("importe",importe);
		}else{
			request.getSession().setAttribute("importe",importeOriginal);
		}
		
		request.getSession().setAttribute("abonado",abonado);
		//request.getSession().setAttribute("origen",origen);

		//String clave = SeguridadUtil.getClaveDesencriptada(request.getParameter("txtNumClave"),request);
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		
		request.getSession().setAttribute("mensajeFactWS1", " ");
		request.getSession().setAttribute("mensajeFactWS2", " ");
		request.getSession().setAttribute("mensajeFactWS3", " ");
		request.getSession().setAttribute("mensajeFactWS4", " ");
		request.getSession().setAttribute("mensajeFactWS5", " ");
		
		if (((Vector)aplicacion.getMensajePorCodigo("PF00",hidCodEntidad.concat("1"))).elementAt(2) == null){
			request.getSession().setAttribute("mensajeFactWS1", " ");
		} else {
			request.getSession().setAttribute("mensajeFactWS1", ((Vector)aplicacion.getMensajePorCodigo("PF00",hidCodEntidad.concat("1"))).elementAt(2).toString());
		}
		
		if (((Vector)aplicacion.getMensajePorCodigo("PF00",hidCodEntidad.concat("2"))).elementAt(2) == null){
			request.getSession().setAttribute("mensajeFactWS2", " ");
		} else {
			request.getSession().setAttribute("mensajeFactWS2", ((Vector)aplicacion.getMensajePorCodigo("PF00",hidCodEntidad.concat("2"))).elementAt(2).toString());
		}
		
		if (((Vector)aplicacion.getMensajePorCodigo("PF00",hidCodEntidad.concat("3"))).elementAt(2) == null){
			request.getSession().setAttribute("mensajeFactWS3", " ");
		} else {
			request.getSession().setAttribute("mensajeFactWS3", ((Vector)aplicacion.getMensajePorCodigo("PF00",hidCodEntidad.concat("3"))).elementAt(2).toString());
		}
		
		if (((Vector)aplicacion.getMensajePorCodigo("PF00",hidCodEntidad.concat("4"))).elementAt(2) == null){
			request.getSession().setAttribute("mensajeFactWS4", " ");
		} else {
			request.getSession().setAttribute("mensajeFactWS4", ((Vector)aplicacion.getMensajePorCodigo("PF00",hidCodEntidad.concat("4"))).elementAt(2).toString());
		}
		
		if (((Vector)aplicacion.getMensajePorCodigo("PF00",hidCodEntidad.concat("5"))).elementAt(2) == null){
			request.getSession().setAttribute("mensajeFactWS5", " ");
		} else {
			request.getSession().setAttribute("mensajeFactWS5", ((Vector)aplicacion.getMensajePorCodigo("PF00",hidCodEntidad.concat("5"))).elementAt(2).toString());
		}
		
		try{
			
			
			ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
			request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
			
			
			//REALIZAR CONSULTA TIPO CLAVE DINAMICA  --> ADD M.D.B.
			ParametrosElemSegTDC elementos = null;
			ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
			request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
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
				if(hidCodEntidad.equals(Constante.gBN_CONST_PAGO_FACTURAS_UNIQUE)){
					ar.setForward("verPagoFacturasWS");
					}
				else{
					
					ar.setForward("verPagoFacturasWS");
					
				}
				throw ar;
			}
			
			// Código Nuevo para TDC
			MensajesTDC resultado = new MensajesTDC();
			Transferencia transf = null;
			ElemenSeguridad resultCoord = null;
		
			String pinblock = ObjectUtil.getClaveDesencriptada(request.getParameter("txtCoordenada"),request);
			
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
						tokenSms.setAmount(importe);
						tokenSms.setMsgClient("Validacion de Clave Dinamica Digital");
						tokenSms.setNomCliDestinatario(pagoFactura.getCliente());
						tokenSms.setCodCliDestinatario(pagoFactura.getNroReferencia());
						
						resultado = UtilAction.validarClaveSms(tokenSms);
					}	
					
					
					if(!resultado.getCodRptaPrincipal().equals(Constante.CODIGO_RESULTADO_OK) || 
							!resultado.getCodResultOper().equals(Constante.CODIGO_OPERACION_OK)){
															
						String codErrEnt="";
						String mensaje="";
						
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
							if(!resultado.getCodResultOper().equals(Constante.CODIGO_OPERACION_OK)){
								 codErrEnt = ObjectUtil.setearCaracterLeft(resultado.getCodRptaPrincipalOper().toString(), '0', 4-resultado.getCodRptaPrincipalOper().toString().length());
								 mensaje=aplicacion.getMsjesHost("CD",codErrEnt).elementAt(2).toString();
							}
						}
						throw new ArrayRuleException(ConstanteError.GENERICO,mensaje+" (CD-"+codErrEnt+")");
						
					}
					

					PagoFacturaWS pfws = FacadeFactory.getPagoFacade().getPagoFacturaOnline(
							codTransaccion, cuenta, pagoFactura, cNumReferencia, hidCodEntidad, fecha, 
							codMoneda, usuario, importe, montoSol, montoDol, montoTipCambio, 
							ctaorigen, empresa, request.getRemoteAddr(), request);
					Map map = UtilAction.cargarVar(request,pagoFactura);
					map.put("fecpago",request.getSession().getAttribute("fecpago"));
					map.put("horpago",request.getSession().getAttribute("horpago"));
					OperacionImpl.setVariables(map);
					
					if(usuario.getNotificacion().getEstadoNotificacion().equals("1")){
						 ServiciosNotificacionPrincipal.enviarNotificacion(
								 usuario,
								 usuario.getNombre(), 
								 pfws.getCuenta().getSimboloMonedaProducto(), 
								 pagoFactura.getImporte(),
								 usuario.getTipoCambio().getVenta(),
								 Constante.NOTI_PAGO_SERVICIOS, 
								 usuario.getNotificacion());
					}

					if(hidCodEntidad.equals(Constante.gBN_CONST_PAGO_FACTURAS_AMAG)){
						ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_SERVICIOS_FACTURAS_LINEA_MOD, ConstanteSesion.CONSULTA, request);
					}else{
						if(hidCodEntidad.equals(Constante.gBN_CONST_PAGO_FACTURAS_UNIQUE)){
							ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_SERVICIOS_FACTURAS_LINEA_PARCIAL, ConstanteSesion.CONSULTA, request);
						}
						else if(hidCodEntidad.equals(Constante.gBN_CONST_PAGO_3PLAY_CLARO)){
							ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_SERVICIOS_FACTURAS_LINEA_CLARO, ConstanteSesion.CONSULTA, request);
						}
						else{
							ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_SERVICIOS_FACTURAS_LINEA, ConstanteSesion.CONSULTA, request);
						}
					}				
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
					if(hidCodEntidad.equals(Constante.gBN_CONST_PAGO_FACTURAS_UNIQUE)){
						e.setForward("confirmarPagoFacturasWSP");
					}else{
						e.setForward("confirmarPagoFacturasWS");
					}
					throw e;
				}
			}
	
		
		}
		catch (ArrayRuleException e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			if(hidCodEntidad.equals(Constante.gBN_CONST_PAGO_FACTURAS_UNIQUE)){
				e.setForward("confirmarPagoFacturasWSP");
			}else{
				e.setForward("confirmarPagoFacturasWS");
			}
			throw e;
		}
		finally{
			request.getSession().setAttribute("cuenta", (Cuenta)request.getSession().getAttribute("ctaSession"));
			request.getSession().setAttribute("cmbCuenta",request.getParameter("cmbCuenta"));
		}
		
		if(hidCodEntidad.equals(Constante.gBN_CONST_PAGO_FACTURAS_AMAG)){
			forward = mapping.findForward("pagarFacturasWSMod");
		}else{
			if(hidCodEntidad.equals(Constante.gBN_CONST_PAGO_FACTURAS_UNIQUE)){
				forward = mapping.findForward("pagarFacturasWSParcial");
			}
			else if(hidCodEntidad.equals(Constante.gBN_CONST_PAGO_3PLAY_CLARO)){
				forward = mapping.findForward("pagarFacturasWSClaro");
			}
			else{
				forward = mapping.findForward("pagarFacturasWS");
			}
		}

		return  (forward);
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
		ActionForward forward = new ActionForward();
		String hidCodEntidad = request.getSession().getAttribute("hidCodEntidad").toString();
		try {
			
			PagoFacturaWSImpl pagoFactura = (PagoFacturaWSImpl)request.getSession().getAttribute(ConstanteSesion.CONSULTA);
			//PagoFactura pagoFactura = (PagoFactura)request.getSession().getAttribute(ConstanteSesion.CONSULTA);
			
			String typeTrans = Constante.TIP_OPER_PAGO_CUPON_SERVICIOS;//request.getSession().getAttribute("typeTrans").toString();
			
			String importe = pagoFactura.getFactura().getImporte();
			
			TokenSmsRequest tokenSms = new TokenSmsRequest();
			String documentType = Funciones.lpad(tipoDoc,"0",3);
			String nroDoc = numDoc;
			//String nameClient = StringUtils.isNotEmpty(datosUsuario.getBeneficiario())?
			//		datosUsuario.getBeneficiario():LoadDataProperties.getInstance().getValue("bn.name.cliente");
			
			tokenSms.setCodCliente(usuario.getCodigoCic());
			tokenSms.setNroDocumento(nroDoc);
			tokenSms.setTipoDocumento(documentType);			
			tokenSms.setTypeTrans(typeTrans);
			tokenSms.setTypeOp(Constante.COD_TRANSACCION_FINANCIERA);//FIN
			tokenSms.setTypeCurrency(Constante.MONEDA_SOL_ISO);//PEN
			tokenSms.setAmount(importe);
			tokenSms.setMsgClient("Validacion de Clave Dinamica Digital");
			tokenSms.setNomCliDestinatario(pagoFactura.getCliente());
			tokenSms.setCodCliDestinatario(pagoFactura.getNroReferencia());

			MensajesTDC resultado = UtilAction.generarClaveSms(tokenSms);
			BNAplicacion aplicacion = BNAplicacion.getInstance();
			
			
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
		
		if(hidCodEntidad.equals(Constante.gBN_CONST_PAGO_FACTURAS_UNIQUE)){
			forward = mapping.findForward("confirmarPagoFacturasWSP");
		}
		else{
			forward = mapping.findForward("confirmarPagoFacturasWS");
		}
		return  (forward);
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
		ActionForward forward = new ActionForward();
		String hidCodEntidad = request.getSession().getAttribute("hidCodEntidad").toString();
		try {
			
			PagoFacturaWSImpl pagoFactura = (PagoFacturaWSImpl)request.getSession().getAttribute(ConstanteSesion.CONSULTA);
			//PagoFactura pagoFactura = (PagoFactura)request.getSession().getAttribute(ConstanteSesion.CONSULTA);
			
			String typeTrans = Constante.TIP_OPER_PAGO_CUPON_SERVICIOS;//request.getSession().getAttribute("typeTrans").toString();
			
			String importe = pagoFactura.getFactura().getImporte();
			
			TokenSmsRequest tokenSms = new TokenSmsRequest();
			String documentType = Funciones.lpad(tipoDoc,"0",3);
			String nroDoc = numDoc;
			//String nameClient = StringUtils.isNotEmpty(datosUsuario.getBeneficiario())?
			//		datosUsuario.getBeneficiario():LoadDataProperties.getInstance().getValue("bn.name.cliente");
			
			tokenSms.setCodCliente(usuario.getCodigoCic());
			tokenSms.setNroDocumento(nroDoc);
			tokenSms.setTipoDocumento(documentType);			
			tokenSms.setTypeTrans(typeTrans);
			tokenSms.setTypeOp(Constante.COD_TRANSACCION_FINANCIERA);//FIN
			tokenSms.setTypeCurrency(Constante.MONEDA_SOL_ISO);//PEN
			tokenSms.setAmount(importe);
			tokenSms.setMsgClient("Validacion de Clave Dinamica Digital");
			tokenSms.setNomCliDestinatario(pagoFactura.getCliente());
			tokenSms.setCodCliDestinatario(pagoFactura.getNroReferencia());

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
			request.setAttribute("listaEntidades",FacadeFactory.getGeneralFacade().getComboAyudaHijo(Constante.COD_HLP_DET_ENTIDAD));
			request.setAttribute("listaTributos",FacadeFactory.getGeneralFacade().getComboDetalleHlpHijo(Constante.COD_HLP_DET_TRIBUTO,""));
			FacadeFactory.getGeneralFacade().actualizarCuentas("LG02",usuario,request.getRemoteAddr());
			response.setStatus(404);
			ar.printStackTrace();
			ar.setForward("iniciarPago");
			throw ar;
		}
		
		if(hidCodEntidad.equals(Constante.gBN_CONST_PAGO_FACTURAS_UNIQUE)){
			forward = mapping.findForward("confirmarPagoFacturasWSP");
		}
		else{
			forward = mapping.findForward("confirmarPagoFacturasWS");
		}
		return  (forward);
	}
}
