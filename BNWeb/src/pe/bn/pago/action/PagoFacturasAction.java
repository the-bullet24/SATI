/*
 * Created on 20/03/2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pe.bn.pago.action;

import java.math.BigDecimal;
import java.util.Iterator;
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
import pe.bn.notificaciones.action.ServicioNotificacionConstancia;
import pe.bn.notificaciones.action.ServiciosNotificacionPrincipal;
import pe.bn.notificaciones.model.ParametrosSIAT;
import pe.bn.pago.domain.PagoFactura;
import pe.bn.pago.domain.impl.PagoFacturaImpl;
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
import pe.cosapi.domain.Recibo;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.CuentaImpl;
import pe.cosapi.domain.impl.DetalleAyudaDatosImpl;
import pe.cosapi.domain.impl.OperacionImpl;
import pe.cosapi.domain.impl.ReciboImpl;


/**
 * @author 2424012
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PagoFacturasAction extends GrandActionAbstract {
	private static LoggerSati log3 = LoggerSati.getInstance(PagoFacturasAction.class.getName());

	public ActionForward iniciar(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		List listaAfiliaciones = null;
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		BNAplicacion aplicacion = BNAplicacion.getInstance();

		String hidServicio = request.getParameter("hidServicio");
		String hidCodServ	  = request.getParameter("hidCodServ");
		String hidCodEntidad = request.getParameter("hidCodEntidad");
		String hidNomEntidad = request.getParameter("hidEntidad");
		String typeTrans = UtilAction.obtenerTipoTransaccionFinanciera(hidCodServ);
		String codMsg 		= "000"+hidServicio;
		ParametrosElemSegTDC elementos = null;
		elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
		
		
		if(!getVerificarHorario("PF00")){
			request.getSession().setAttribute("mensaje1Hor",((Vector)aplicacion.getMensajePorCodigo("MH01","MH001")).elementAt(2).toString());
			request.getSession().setAttribute("mensaje2Hor",((Vector)aplicacion.getMensajePorCodigo("MH01","MH002")).elementAt(2).toString());
			request.getSession().setAttribute("mensajeHorC",((Vector)aplicacion.getMensajePorCodigo("MH01","3244"==null?"0112":"3244")).elementAt(2).toString());
			return mapping.findForward("transaccion.noDisponible");
		}
				
		if (hidServicio.equals("11")){
			listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(hidCodEntidad,usuario.getTarjeta().getNumero(), "09044");
			request.getSession().setAttribute("TITULO",Constante.PAGO_FACTURAS_TITULO);
			request.getSession().setAttribute(ConstanteSesion.DES_SERV_TIT, "Código Afiliado:");
			request.getSession().setAttribute(ConstanteSesion.DES_SERV_AFI, "Afiliación");
			request.getSession().setAttribute("mensajeAfiliacion",((Vector)aplicacion.getMensajePorCodigo("PS00","AF007")).elementAt(2).toString()); 
			request.getSession().setAttribute("listaAfiliaciones",listaAfiliaciones);	
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
		request.getSession().setAttribute("hidCodServ",hidCodServ);
		request.getSession().setAttribute("typeTrans",typeTrans);
		request.getSession().setAttribute("cdSsPT",hidServicio);
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
		
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
	
	public ActionForward verPagoFacturas(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		String[] id;
		
		//String pago=request.getParameter("cmbPagoFacturas");
		String cuenta =request.getParameter("cmbCuenta");//04037908725
		String hidCodEntidad = request.getSession().getAttribute("hidCodEntidad").toString();//0089
		String hidCodServ = request.getSession().getAttribute("hidCodServ").toString();//1109
		String typeTrans = request.getSession().getAttribute("typeTrans").toString();
		
		
		DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
	    List list= dHelp_.getListDetalleAyudaHijo("09044", hidCodEntidad.trim());
	    
		PagoFactura pagoFactura = null;
		String codigoAfiliado="";
		Afiliacion t = null;
		String optCuenta = request.getParameter("optCuenta");


		request.getSession().setAttribute("hidCodEntidad", hidCodEntidad);
		request.getSession().setAttribute("hidCodServ", hidCodServ);
		request.getSession().setAttribute("typeTrans", typeTrans);
		request.getSession().setAttribute("optCuenta", optCuenta);


			if(request.getSession().getAttribute("optCuenta").equals(Constante.NUEV_REGISTRO_TRANSAC)){
				codigoAfiliado = request.getParameter("txtServicioFactura");
				List listaAfiliaciones = null;
				listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaExiste(hidCodEntidad,usuario.getTarjeta().getNumero(),Constante.COD_HLP_PGO_FACTURAS, codigoAfiliado);
			
				if (listaAfiliaciones.size() > 0){
					throw new ArrayRuleException(ConstanteError.GENERICO,"El Codigo Afiliado: " + codigoAfiliado +" se encuentra como frecuente.");
				}
				
				Afiliacion afiliacion = new AfiliacionImpl();
				
				afiliacion.setNumeroServicio(codigoAfiliado);
				
				afiliacion.setTipoAfiliacion(hidCodEntidad);
				afiliacion.setCodigoLocalidad(Constante.CODIGO_LOCALIDAD_FREC);
				afiliacion.setFlagRegistro(Constante.NUEV_REGISTRO_TRANSAC);
				afiliacion.setNumeroServicio(codigoAfiliado);
				afiliacion.setNroTarjeta(usuario.getTarjeta().getNumero());
				afiliacion.setCodigoServicio(Constante.CODIGO_SERVICIO_FREC);
				request.getSession().setAttribute("afiliacion",afiliacion);
					
			}
			
			if(request.getSession().getAttribute("optCuenta").equals(Constante.FREC_REGISTRO_TRANSAC))
			{
				codigoAfiliado = request.getParameter("cmbPagoFacturas").trim();
	
			}
	
		String codEntidad = hidCodEntidad.trim();
		
		Iterator it = list.iterator();
	    while (it.hasNext()){
	        DetalleAyudaDatosImpl dHelp = (DetalleAyudaDatosImpl)it.next();
	        codEntidad = dHelp.getCodigoDetalleAyuda1().trim();
	    }
	    
	    request.getSession().setAttribute("hidCodEntidadReal", codEntidad.trim());//3056023
	        

		
		if(codigoAfiliado!=null){
			
			id = ObjectUtil.getArrayStrings(codigoAfiliado,"-");

			request.getSession().setAttribute("cmbPagoFacturas",codigoAfiliado);
		}else{

			id = ObjectUtil.getArrayStrings(request.getSession().getAttribute("cmbPagoFacturas").toString(),"-");	
		}
		try{	


			if(request.getSession().getAttribute("optCuenta").equals(Constante.FREC_REGISTRO_TRANSAC)){
				Afiliacion afil = new AfiliacionImpl();
				afil = FacadeFactory.getAfiliacionFacade().getAfiliacion(id[0].trim(),id[1].trim(),new Long(id[2].trim()), "00436");
				
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
				pagoFactura = FacadeFactory.getPagoFacade().getConsultaFactura(request.getParameter("transaccion"), cta,  (Afiliacion)request.getSession().getAttribute("afiliacion"), codEntidad, usuario, request.getRemoteAddr());
				pagoFactura.setCodServicio(hidCodServ);
				pagoFactura.setCodEntidad(hidCodEntidad);
				request.getSession().setAttribute(ConstanteSesion.CONSULTA, pagoFactura);
				
				String cCLiente = "";
				if (pagoFactura.getRecibos().size()>0) {
					Recibo nuevoRec = (Recibo) pagoFactura.getRecibos().get(0);
					//cCLiente = nuevoRec.getGlosa();
					cCLiente =pagoFactura.getCliente();
				} else{
					throw new ArrayRuleException(ConstanteError.GENERICO,"UD. NO TIENE FACTURAS PENDIENTES DE PAGO");
				}
					
				
				request.getSession().setAttribute("nomCliente", cCLiente);
				request.getSession().setAttribute("cmbCuenta",cuenta);
			}else{
				request.setAttribute("cuenta", (Cuenta)request.getSession().getAttribute("ctaSession"));
				
				pagoFactura=FacadeFactory.getPagoFacade().getConsultaFactura(request.getParameter("transaccion"),  (Cuenta)request.getSession().getAttribute("ctaSession"), (Afiliacion)request.getSession().getAttribute("afiliacion"), codEntidad, usuario, request.getRemoteAddr());
				
				String cCLiente = "";
				
				if (pagoFactura.getRecibos().size()>0) {
					Recibo nuevoRec = (Recibo) pagoFactura.getRecibos().get(0);
					//cCLiente = nuevoRec.getGlosa();
					cCLiente =pagoFactura.getCliente();
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
			String codServicio= (String)request.getSession().getAttribute("cdSsPT");
			List listaAfiliaciones 	= null;			
			listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(hidCodEntidad,usuario.getTarjeta().getNumero(), "09044");
			request.setAttribute("listaAfiliaciones",listaAfiliaciones);
			throw e;
		}
		
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);

		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
		
		return mapping.findForward("verPagoFacturas");
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
			
			PagoFacturaImpl pagoFactura = (PagoFacturaImpl)request.getSession().getAttribute(ConstanteSesion.CONSULTA);
		
			String typeTrans = Constante.TIP_OPER_PAGO_CUPON_SERVICIOS; //request.getSession().getAttribute("typeTrans").toString();
			String importe = request.getSession().getAttribute("importe").toString();

			TokenSmsRequest tokenSms = new TokenSmsRequest();
			String documentType = Funciones.lpad(tipoDoc,"0",3);
			
			tokenSms.setCodCliente(usuario.getCodigoCic());
			tokenSms.setNroDocumento(numDoc);
			tokenSms.setTipoDocumento(documentType); //DNI
			tokenSms.setTypeTrans("30");
			tokenSms.setTypeOp(Constante.COD_TRANSACCION_FINANCIERA);//FIN						
			tokenSms.setTypeCurrency(Constante.MONEDA_SOL_ISO);//PEN
			importe = importe.replace(",", "");
			tokenSms.setAmount(pagoFactura.getRecibo().getImporte());
			
			tokenSms.setCodCliDestinatario(pagoFactura.getNroReferencia());
			tokenSms.setNomCliDestinatario(pagoFactura.getCliente());

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
			
			PagoFacturaImpl pagoFactura = (PagoFacturaImpl)request.getSession().getAttribute(ConstanteSesion.CONSULTA);
			
			String typeTrans = Constante.TIP_OPER_PAGO_CUPON_SERVICIOS; //request.getSession().getAttribute("typeTrans").toString();
			String importe = request.getSession().getAttribute("importe").toString();

			TokenSmsRequest tokenSms = new TokenSmsRequest();
			String documentType = Funciones.lpad(tipoDoc,"0",3);
			
			tokenSms.setCodCliente(usuario.getCodigoCic());
			tokenSms.setNroDocumento(numDoc);
			tokenSms.setTipoDocumento(documentType); //DNI
			tokenSms.setTypeTrans("30");
			tokenSms.setTypeOp(Constante.COD_TRANSACCION_FINANCIERA);//FIN						
			tokenSms.setTypeCurrency(Constante.MONEDA_SOL_ISO);//PEN
			importe = importe.replace(",", "");
			tokenSms.setAmount(pagoFactura.getRecibo().getImporte());
			
			tokenSms.setCodCliDestinatario(pagoFactura.getNroReferencia());
			tokenSms.setNomCliDestinatario(pagoFactura.getCliente());

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
		return mapping.findForward("confirmarPagoFacturas");
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
		
		String[] id = ObjectUtil.getArrayStrings(request.getParameter("optSecuencia"),"-");
		String cCodSec = id[1].trim();
		CuentaImpl cta = (CuentaImpl) request.getSession().getAttribute("cuenta");//ctaSession
		
		String hidCodEntidad = request.getSession().getAttribute("hidCodEntidad").toString();
		String hidCodServ = request.getSession().getAttribute("hidCodServ").toString();
		String typeTrans = request.getSession().getAttribute("typeTrans").toString();
		String hidCodEntidadReal = (String) request.getSession().getAttribute("hidCodEntidadReal");
		String importe = "";
		
		PagoFactura pagoFactura = (PagoFactura)request.getSession().getAttribute(ConstanteSesion.CONSULTA);
		List recibos = pagoFactura.getRecibos();
		for (Iterator iter = recibos.iterator(); iter.hasNext();) {
			ReciboImpl recibo = (ReciboImpl) iter.next();
			if(cCodSec.equals(recibo.getSecuencia().trim())){
				pagoFactura.setRecibo(recibo);
				importe = recibo.getImporte();
				break;
			}
		}
		
		request.getSession().setAttribute("hidCodEntidad", hidCodEntidad);
		request.getSession().setAttribute("hidCodServ", hidCodServ);
		request.getSession().setAttribute("typeTrans", typeTrans);
		request.getSession().setAttribute("hidCodEntidadReal", hidCodEntidadReal);
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
				ar.setForward("verPagoFacturas");
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
					ar.setForward("confirmarPagoFacturas");
					throw ar;
				}
				request.getSession().setAttribute("resultCoord",resultCoord );
			}
			else{
				
				throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
			}

		request.setAttribute("cuenta",cta);
		return mapping.findForward("confirmarPagoFacturas");
	}
	
	
	public ActionForward pagarFacturas(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception{
		BNAplicacion aplicacion = BNAplicacion.getInstance();
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
		String nombreEntidadCupon = request.getSession().getAttribute("nombreEntidad").toString();
		String importeSol = request.getSession().getAttribute("importe").toString();
		String txtMonto = ObjectUtil.replaceChar(request.getParameter("txtImporteHide").trim(), ',', "");
		String txtMontoReal = ObjectUtil.replaceChar(request.getParameter("txtImporteHide").trim(), ',', "");
		String hidCodEntidad = (String) request.getSession().getAttribute("hidCodEntidad");
		String hidCodServ = (String) request.getSession().getAttribute("hidCodServ");
		String nomCliente = (String) request.getSession().getAttribute("nomCliente");
		String codEntidad = request.getSession().getAttribute("hidCodEntidadReal").toString();
		String typeTrans = Constante.TIP_OPER_PAGO_CUPON_SERVICIOS; //request.getSession().getAttribute("typeTrans").toString();
		
		ActionForward forward = new ActionForward();

		
		float nImporte = Float.parseFloat(txtMonto);
		float nImporteReal = Float.parseFloat(ObjectUtil.replaceChar(txtMontoReal, ',', ""));
		
		if (nImporte > nImporteReal) {
			throw new ArrayRuleException(ConstanteError.GENERICO,"El importe a Cancelar no puede ser mayor al importe de la factura...");
		}

		
		PagoFacturaImpl pagoFactura = (PagoFacturaImpl)request.getSession().getAttribute(ConstanteSesion.CONSULTA);
		pagoFactura.setCodServicio(hidCodServ);
		pagoFactura.setCodEntidad(hidCodEntidad);
		pagoFactura.setNomEntidad(nombreEntidadCupon);
		pagoFactura.setNomCliente(nomCliente);
		pagoFactura.setFecha(pe.cosapi.common.ObjectUtil.getFechaActual());
		pagoFactura.setHora(pe.cosapi.common.ObjectUtil.getHHMMSSformateado());
		pagoFactura.getRecibo().setImporte(new BigDecimal(txtMonto));
		
		request.getSession().setAttribute("hidCodEntidad", codEntidad);
		request.getSession().setAttribute("mensajeFact1", " ");
		request.getSession().setAttribute("mensajeFact2", " ");
		request.getSession().setAttribute("mensajeFact3", " ");
		request.getSession().setAttribute("mensajeFact4", " ");
		request.getSession().setAttribute("mensajeFact5", " ");
		
		if (((Vector)aplicacion.getMensajePorCodigo("PF00",hidCodEntidad.concat("1"))).elementAt(2) == null){
			request.getSession().setAttribute("mensajeFact1", " ");
		} else {
			request.getSession().setAttribute("mensajeFact1", ((Vector)aplicacion.getMensajePorCodigo("PF00",hidCodEntidad.concat("1"))).elementAt(2).toString());
		}
		
		if (((Vector)aplicacion.getMensajePorCodigo("PF00",hidCodEntidad.concat("2"))).elementAt(2) == null){
			request.getSession().setAttribute("mensajeFact2", " ");
		} else {
			request.getSession().setAttribute("mensajeFact2", ((Vector)aplicacion.getMensajePorCodigo("PF00",hidCodEntidad.concat("2"))).elementAt(2).toString());
		}
		
		if (((Vector)aplicacion.getMensajePorCodigo("PF00",hidCodEntidad.concat("3"))).elementAt(2) == null){
			request.getSession().setAttribute("mensajeFact3", " ");
		} else {
			request.getSession().setAttribute("mensajeFact3", ((Vector)aplicacion.getMensajePorCodigo("PF00",hidCodEntidad.concat("3"))).elementAt(2).toString());
		}
		
		if (((Vector)aplicacion.getMensajePorCodigo("PF00",hidCodEntidad.concat("4"))).elementAt(2) == null){
			request.getSession().setAttribute("mensajeFact4", " ");
		} else {
			request.getSession().setAttribute("mensajeFact4", ((Vector)aplicacion.getMensajePorCodigo("PF00",hidCodEntidad.concat("4"))).elementAt(2).toString());
		}
		
		if (((Vector)aplicacion.getMensajePorCodigo("PF00",hidCodEntidad.concat("5"))).elementAt(2) == null){
			request.getSession().setAttribute("mensajeFact5", " ");
		} else {
			request.getSession().setAttribute("mensajeFact5", ((Vector)aplicacion.getMensajePorCodigo("PF00",hidCodEntidad.concat("5"))).elementAt(2).toString());
		}
		
		try{
			
			ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
			request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
			
			ElemenSeguridad resultCoord = null;
			ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
			request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
			ElemenSeguridad coord = (ElemenSeguridad)request.getSession().getAttribute("resultCoord");
						
			String pinblock = ObjectUtil.getClaveDesencriptada(request.getParameter("txtCoordenada"),request);
//			System.out.println("pinblock:::: " + pinblock);
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
				ar.setForward("verPagoFacturas");
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
						
						String documentType =Funciones.lpad(tipoDoc,"0",3);
						
						TokenSmsRequest tokenSms = new TokenSmsRequest();

						tokenSms.setCodeSMS(pinblock);
						tokenSms.setCodCliente(usuario.getCodigoCic());
						tokenSms.setTipoDocumento(documentType);
						tokenSms.setNroDocumento(numDoc);
						tokenSms.setTypeTrans("30");
						tokenSms.setTypeOp(Constante.COD_TRANSACCION_FINANCIERA);//FIN
						tokenSms.setTypeCurrency(Constante.MONEDA_SOL_ISO);//PEN
						importeSol = importeSol.replace(",", "");
						tokenSms.setAmount(importeSol);
						
						tokenSms.setCodCliDestinatario(pagoFactura.getNroReferencia());
						tokenSms.setNomCliDestinatario(pagoFactura.getCliente());
						
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
					
					PagoFactura pf = FacadeFactory.getPagoFacade().pagarFactura(transaccion,pagoFactura,codEntidad, usuario,request.getRemoteAddr());
					Map map = UtilAction.cargarVar(request,pagoFactura);
					map.put("fecpago",request.getSession().getAttribute("fecpago"));
					map.put("horpago",request.getSession().getAttribute("horpago"));
					OperacionImpl.setVariables(map);
						
					if(usuario.getNotificacion().getEstadoNotificacion().equals("1")){
						ServiciosNotificacionPrincipal.enviarNotificacion(
								usuario,
								usuario.getNombre(), 
								pf.getCuenta().getSimboloMonedaProducto(), 
								pagoFactura.getImporte(),
								usuario.getTipoCambio().getVenta(), 
								Constante.NOTI_PAGO_SERVICIOS, 
								usuario.getNotificacion());
					}
					
					if(codEntidad.equals(Constante.CODIGO_CREDISCOTIA)){
						ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_SERVICIOS_FACTURAS_ESP, ConstanteSesion.CONSULTA, request);
					}else{
						ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_SERVICIOS_FACTURAS, ConstanteSesion.CONSULTA, request);
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
					e.setForward("confirmarPagoFacturas");
					throw e;
				}
			}
						
		}
		catch(ArrayRuleException e){
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			e.setForward("confirmarPagoFacturas");
			throw e;
		}
		
		Afiliacion afiliacionPagoFactura = (AfiliacionImpl)request.getSession().getAttribute("afiliacion");
		if(afiliacionPagoFactura.getFlagRegistro().equals(Constante.NUEV_REGISTRO_TRANSAC)){
			
			String adicionar = request.getParameter("adicionar");
		
			if(adicionar!=null){
				afiliacionPagoFactura.setDescripcion(request.getParameter("txtNombreAfil").trim());			

				try {
					Afiliacion af=FacadeFactory.getAfiliacionFacade().afiliar(usuario,afiliacionPagoFactura);
					request.getSession().setAttribute(ConstanteSesion.AFILIAR,af);
					
				} catch (ArrayRuleException e) {
					log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
					e.printStackTrace();
					e.setForward("pagarFacturas");
				}
			}
		}

		
		if(codEntidad.equals(Constante.CODIGO_CREDISCOTIA)){//0095
			forward=mapping.findForward("pagarFacturasEsp");
		}else{
			forward=mapping.findForward("pagarFacturas");
		}
		
		return forward;
	}
}
