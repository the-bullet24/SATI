
package pe.bn.pago.action;

import java.math.BigDecimal;
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
import pe.bn.pago.domain.PagoTelefono;
import pe.bn.pago.domain.PagoUniversidad;
import pe.bn.pago.domain.impl.PagoSedapalImpl;
import pe.bn.pago.domain.impl.PagoTelefonoImpl;
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

public class RecargaTelefonoAction extends GrandActionAbstract {
	BNAplicacion aplicacion =BNAplicacion.getInstance();
	private static LoggerSati log3 = LoggerSati.getInstance(RecargaTelefonoAction.class.getName());
	
	public ActionForward iniciar(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		List listaAfiliaciones = null;
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		FacadeFactory.getGeneralFacade().actualizarCuentas("LG02",usuario,request.getRemoteAddr());
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		
		String hidServicio = request.getParameter("hidServicio");
		String hidCodEntidad = request.getParameter("hidCodEntidad");
		String hidCodServ = request.getParameter("hidCodServ");
		String typeTrans = UtilAction.obtenerTipoTransaccionFinanciera(hidCodServ);
		
		String codMsg = "0000"+hidServicio;
		
//		System.out.println("***hidServicio***: " + hidServicio);
//		System.out.println("***hidCodEntidad***: " + hidCodEntidad);
//		System.out.println("***hidCodServ***: " + hidCodServ);
//		System.out.println("typeTrans::::" + typeTrans);

		request.getSession().setAttribute("mensajePantalla",((Vector)aplicacion.getMensajePorCodigo("PS00",codMsg)).elementAt(2).toString());
		request.getSession().setAttribute(ConstanteSesion.DES_SUBTITULO1,"NOMBRE");
		request.getSession().setAttribute(ConstanteSesion.DES_SUBTITULO2,"NRO. DE SERVICIO");
		request.getSession().setAttribute("TITULOS",ConstanteSesion.TITULO);
		
		if (hidServicio.equals("8")){
			
			request.getSession().setAttribute("TITULO",Constante.PAGO_RECARGAS_MOVISTAR_TITULO);
			request.getSession().setAttribute(ConstanteSesion.DES_SERV_TIT, "Celulares Afiliados:");
			request.getSession().setAttribute(ConstanteSesion.DES_SERV_AFI, "Afiliación de Celulares");
			request.getSession().setAttribute("mensajeAfiliacion",((Vector)aplicacion.getMensajePorCodigo("PS00","AF002")).elementAt(2).toString()); 
	
			listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.RECARGA_MOV,usuario.getTarjeta().getNumero(), "00437");
			
		}else {
			
			if (hidServicio.equals("13")){
				request.getSession().setAttribute("TITULO",Constante.PAGO_RECARGAS_CLARO_TITULO);
				request.getSession().setAttribute(ConstanteSesion.DES_SERV_TIT, "Celulares Afiliados:");
				request.getSession().setAttribute(ConstanteSesion.DES_SERV_AFI, "Afiliación de Celulares");
				request.getSession().setAttribute("mensajeAfiliacion",((Vector)aplicacion.getMensajePorCodigo("PS00","AF002")).elementAt(2).toString()); 
		
				listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.RECARGA_CLA,usuario.getTarjeta().getNumero(), "00438");
			}
			if (hidServicio.equals("14")){
				
				request.getSession().setAttribute("TITULO",Constante.PAGO_RECARGAS_BITEL_TITULO);
				request.getSession().setAttribute(ConstanteSesion.DES_SERV_TIT, "Celulares Afiliados:");
				request.getSession().setAttribute(ConstanteSesion.DES_SERV_AFI, "Afiliación de Celulares");
				request.getSession().setAttribute("mensajeAfiliacion",((Vector)aplicacion.getMensajePorCodigo("PS00","AF002")).elementAt(2).toString()); 
		
				listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.RECARGA_BIT,usuario.getTarjeta().getNumero(), "00438");
				
			}
			
			if (hidServicio.equals("16")){
				
				request.getSession().setAttribute("TITULO",Constante.PAGO_RECARGAS_ENTEL_TITULO);
				request.getSession().setAttribute(ConstanteSesion.DES_SERV_TIT, "Celulares Afiliados:");
				request.getSession().setAttribute(ConstanteSesion.DES_SERV_AFI, "Afiliación de Celulares");
				request.getSession().setAttribute("mensajeAfiliacion",((Vector)aplicacion.getMensajePorCodigo("PS00","AF002")).elementAt(2).toString()); 
		
				listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.RECARGA_ENT,usuario.getTarjeta().getNumero(), "00438");
				
			}
		}
		
		request.setAttribute("listaAfiliaciones",listaAfiliaciones);
		request.setAttribute("hidServicio",hidServicio);
		request.getSession().setAttribute("hidServicio",hidServicio);
		request.getSession().setAttribute("cdSsPT",hidServicio);
		request.getSession().setAttribute("hidCodEntidad",hidCodEntidad);
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
		
		if (hidServicio.equals("8")){
			return mapping.findForward("iniciar");
		} else {
			return mapping.findForward("iniciarClaro");
		}
	}
	
	
	public ActionForward verRecargaMovistar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception{
		

		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		String[] id;
		
		String cuenta =request.getParameter("cmbCuenta");
		
		String hidCodEntidad = (String) request.getSession().getAttribute("hidCodEntidad");
		String hidCodServ = (String) request.getSession().getAttribute("hidCodServ");
		
		request.getSession().setAttribute("hidCodEntidad", hidCodEntidad);
		request.getSession().setAttribute("hidCodServ", hidCodServ);
		String optCuenta = request.getParameter("optCuenta");

		request.getSession().setAttribute("optCuenta", optCuenta);

		String codigoAfiliado="";
		
		if(request.getSession().getAttribute("optCuenta").equals(Constante.NUEV_REGISTRO_TRANSAC)){
						codigoAfiliado = request.getParameter("txtServicioRecargoMovistar");
						List listaAfiliaciones = null;
						listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaExiste(Constante.RECARGA_MOV,usuario.getTarjeta().getNumero(),Constante.COD_HLP_PGO_MOVISTAR, codigoAfiliado);
	                 if (listaAfiliaciones.size() > 0){
							listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.RECARGA_MOV,usuario.getTarjeta().getNumero(), "00438");
							request.setAttribute("listaAfiliaciones",listaAfiliaciones);
							throw new ArrayRuleException(ConstanteError.GENERICO,"El Codigo Afiliado: " + codigoAfiliado +" se encuentra como frecuente.");
						}

						Afiliacion afiliacion = new AfiliacionImpl();
						afiliacion.setNumeroServicio(codigoAfiliado);
						afiliacion.setTipoAfiliacion(hidCodEntidad);
						afiliacion.setCodigoLocalidad("00");
						afiliacion.setNumeroServicio(codigoAfiliado);
						afiliacion.setNroTarjeta(usuario.getTarjeta().getNumero());
						afiliacion.setFlagRegistro(Constante.NUEV_REGISTRO_TRANSAC);
						afiliacion.setCodigoServicio(Constante.SERVICIO_CELULAR);
						afiliacion.setDescripcion("aaa");
						
						request.getSession().setAttribute("afiliacion",afiliacion);
						
				}
						
					if(request.getSession().getAttribute("optCuenta").equals(Constante.FREC_REGISTRO_TRANSAC))
					{
						codigoAfiliado = request.getParameter("cmbPagoTelefono");
									
					}


					if(codigoAfiliado!=null){
						id = ObjectUtil.getArrayStrings(codigoAfiliado,"-");
						request.getSession().setAttribute("cmbPagoTelefono",codigoAfiliado);
					}else{
						id = ObjectUtil.getArrayStrings(request.getSession().getAttribute("cmbPagoTelefono").toString(),"-");
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
					request.getSession().setAttribute("cmbCuenta",cuenta);
				}else{
					request.setAttribute("cuenta", (Cuenta)request.getSession().getAttribute("ctaSession"));
				}
			}
			catch(ArrayRuleException e){
				log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
				System.out.println("!!!!!!!Entro al catch del metodo verRecargaTelefono");
				String codServicio= (String)request.getSession().getAttribute("cdSsPT");
				List listaAfiliaciones 	= null;
				listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.RECARGA_MOV,usuario.getTarjeta().getNumero(), "00437");
				request.setAttribute("listaAfiliaciones",listaAfiliaciones);
				throw e;
			}
			
			ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
	
			request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
			
			ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
			request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
	 	
			return mapping.findForward("verRecargaMovistar");
	}
	
	public ActionForward confirmarRecargaMovistar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)	throws Exception{
		System.out.println("::::confirmarRecargaMovistar()");
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
		Cuenta cuenta = (Cuenta)request.getSession().getAttribute("ctaSession");
		String abrevMoneda = cuenta.getMonedaProducto();
		String codMoneda = Constante.COD_MONEDA_SOL; //Cuenta Movistar
		
		
		String hidCodEntidad = (String) request.getSession().getAttribute("hidCodEntidad");
		String hidCodServ = (String) request.getSession().getAttribute("hidCodServ");
		String importe = request.getParameter("importe").toString();
		
		request.getSession().setAttribute("hidCodEntidad", hidCodEntidad);
		request.getSession().setAttribute("hidCodServ", hidCodServ);
	
		Afiliacion afiliacion = (AfiliacionImpl)request.getSession().getAttribute("afiliacion");
		
		try{
			double monto= Double.parseDouble(importe);
			if(monto==0){
				ArrayRuleException e2 = new ArrayRuleException(ConstanteError.GENERICO,"Verifique el monto de recarga  ingresado");
				e2.setForward("verRecargaMovistar");
				throw e2;
			}
		}catch(NumberFormatException e1){
			log3.error(e1,Constante.ERR_LOGICA_NEGOCIO,"");
			ArrayRuleException e2 = new ArrayRuleException(ConstanteError.GENERICO,"Verifique el monto de recarga  ingresado");
			e2.setForward("verRecargaMovistar");
			throw e2;
		}
		finally{
			request.setAttribute("cuenta", (Cuenta)request.getSession().getAttribute("ctaSession"));
		}
		
		request.getSession().setAttribute("afiliacion",afiliacion);
		
		BigDecimal nMonto;
		nMonto = new BigDecimal(ObjectUtil.replaceChar(request.getParameter("importe").toString(),',',""));
		nMonto = nMonto.setScale(2,BigDecimal.ROUND_HALF_EVEN);
		
		int result;
		BigDecimal importeSol = new BigDecimal(request.getParameter("importe").toString().replace(",", ""));
		BigDecimal importeMin = new BigDecimal(Constante.CONST_TELEGIRO_MONTO_MININO);
		
		result = importeSol.compareTo(importeMin);
		if (result == 1){
			ArrayRuleException e3 = new ArrayRuleException(ConstanteError.GENERICO,"El monto ingresado debe ser menor o igual a S/ 2000.00 Soles");
			e3.setForward("verRecargaMovistar");
			throw e3;
		}
		
		request.getSession().setAttribute("cmbCuenta",request.getParameter("cmbCuenta"));
		request.getSession().setAttribute("tipoCambioCompra",((pe.cosapi.domain.impl.UsuarioImpl)request.getSession().getAttribute(pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION)).getTipoCambio().getCompra());

		try{
			request.getSession().setAttribute(ConstanteSesion.CONSULTA,FacadeFactory.getPagoFacade().getConfirmaRecargaTelefono(usuario,cuenta, afiliacion, nMonto, abrevMoneda, codMoneda, ((pe.cosapi.domain.impl.UsuarioImpl)request.getSession().getAttribute(pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION)).getTipoCambio()));
		}
		catch (ArrayRuleException e4) {
			log3.error(e4,Constante.ERR_LOGICA_NEGOCIO,"");
			e4.setForward("verRecargaMovistar");
			throw e4;
		}
		finally{			
			request.getSession().setAttribute("importe",nMonto.setScale(2,BigDecimal.ROUND_HALF_EVEN));			
		}
		
		ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
		
		//REALIZAR CONSULTA DE COORDENADAS
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		ElemenSeguridad resultCoord = null;
		ParametrosElemSegTDC elementos = null;//
		//request.getSession().setAttribute("tipoElemento",null );
		try{
			elementos = (ParametrosElemSegTDC )request.getSession().getAttribute("tipoElemento"); 
		}catch(ArrayRuleException ar){
			log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
			ar.printStackTrace();
			request.setAttribute("cuenta",request.getAttribute("cuenta"));
			ar.setForward("verRecargaMovistar");
			throw ar;
		}
		
		if(param!= null){

				resultCoord = new ElemenSeguridad();//
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
					ar.setForward("verRecargaMovistar");
					throw ar;
				}
		
			request.getSession().setAttribute("resultCoord",resultCoord );
		}else{
			
			throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
		
		}
		

		return mapping.findForward("confirmarRecargaMovistar");
	}
	
	public ActionForward pagarRecargaMovistar(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception{
		System.out.println("::::pagarRecargaMovistar()");
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		Afiliacion datosUsuario = new AfiliacionImpl();
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
		
		Afiliacion afiliacionRecargaMovistar = (AfiliacionImpl)request.getSession().getAttribute("afiliacion");
		Cuenta cuenta =  (Cuenta)request.getSession().getAttribute("ctaSession");			

		String hidCodEntidad = (String) request.getSession().getAttribute("hidCodEntidad");
		String hidCodServ = (String) request.getSession().getAttribute("hidCodServ");
		//String servicio=request.getParameter("codservicio");
		String importe = request.getSession().getAttribute("importe").toString();
		String nMonto= ObjectUtil.replaceChar(importe,'.',"");
		String importeSol = request.getParameter("importeSol").toString();
		String importeDol = request.getParameter("importeDol").toString();
		String tipoCambioCompra = request.getParameter("tipoCambioCompra").toString();
		
		String codServicio = (String)request.getSession().getAttribute("hidServicio");

		request.getSession().setAttribute("cmbCuenta",cuenta);
		request.getSession().setAttribute("hidCodEntidad", hidCodEntidad);
		request.getSession().setAttribute("hidCodServ", hidCodServ);
		request.getSession().setAttribute("empresa",ConstanteSesion.TIT_EMPRESA);	
		request.getSession().setAttribute("servicio",ConstanteSesion.TIT_TEL_CELULAR_RECARGA);		

		BigDecimal montoSol = null;
		BigDecimal montoDol = null;
		BigDecimal montoTipCambio = null;
		
		java.util.Calendar cal = java.util.Calendar.getInstance();
	 	Timestamp t = new java.sql.Timestamp(cal.getTime().getTime()); 					 			
	 	Date data = new Date(t.getTime());
	 	String fecha = (ObjectUtil.replaceChar(data.toString(),'-',""));
	 	
		
		montoSol = new BigDecimal(ObjectUtil.replaceChar(importeSol,',',""));
		if(cuenta.getMonedaProducto().equals(Constante.MONEDA_DOLAR)){
			montoDol = new BigDecimal(ObjectUtil.replaceChar(importeDol,',',""));
			montoTipCambio = new BigDecimal(ObjectUtil.replaceChar(tipoCambioCompra,',',""));
		}
		
		
		request.getSession().setAttribute("fecpago",pe.cosapi.common.ObjectUtil.getFechaActual());
		request.getSession().setAttribute("horpago",pe.cosapi.common.ObjectUtil.getHHMMSSformateado());
		request.getSession().setAttribute("importe",request.getParameter("importeOriginal"));
		request.getSession().setAttribute("abonado",request.getParameter("abonado"));
		request.getSession().setAttribute("origen",request.getParameter("origen"));

		//String clave = SeguridadUtil.getClaveDesencriptada(request.getParameter("txtNumClave"),request);
		String empresa =(String )request.getSession().getAttribute("empresa");
		String servicioRec =(String )request.getSession().getAttribute("servicio");
		String ctaorigen=request.getParameter("origen");
		String codMoneda = request.getParameter("codMoneda");
		String codTransaccion = request.getParameter("transaccion");
		
		try{
			//Datos del Beneficiario y titular
			afiliacionRecargaMovistar.setTipoDocumento(Funciones.lpad(tipoDoc,"0",3));
			afiliacionRecargaMovistar.setNroDocumento(numDoc);	
			afiliacionRecargaMovistar.setCodCliente(datosUsuario.getCodCliente());
			
			PagoTelefonoImpl pagoTelefonoImpl = new PagoTelefonoImpl();
			pagoTelefonoImpl.setCodServEnt(hidCodServ);
			pagoTelefonoImpl.setCodEntidad(hidCodEntidad);
			//FacadeFactory.getGeneralFacade().processValidationCardAhorro(usuario.getTarjeta().getNumero(),usuario.getTarjeta().getClave(),clave,clave); --Se quita validacion Clave 6 (04/09/2013)
			
			ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
			request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);

			ElemenSeguridad resultCoord = null;
			ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
			ElemenSeguridad coord = (ElemenSeguridad)request.getSession().getAttribute("resultCoord");
						
			String pinblock = ObjectUtil.getClaveDesencriptada(request.getParameter("txtCoordenada"),request);					
			
			ParametrosElemSegTDC elementos = null;		
			try{
			
				elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
				
				if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS) ||
				   elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
					elementos = SolicitarServiciosTDC.verificarTipoElemento(param,usuario);
				}	
			}
			catch (ArrayRuleException ar) {
				log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
				ar.printStackTrace();
				ar.setForward("verRecargaMovistar");
				throw ar;
				/*log3.error(e,Constante.ERR_ERROR_VALIDAR_TDC,"");
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
				e.setForward("confirmarRecargaMovistar");
				throw e;*/
			}
			
			//Subiendo a session el objeto afiliacion
			request.getSession().setAttribute("afiliacion",afiliacionRecargaMovistar);
			
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
					else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {
						
						String nroDoc = numDoc;
						String documentType = Funciones.lpad(tipoDoc,"0",3);
//						String nameClient = StringUtils.isNotEmpty(datosUsuario.getBeneficiario())?datosUsuario.getBeneficiario():LoadDataProperties.getInstance().getValue("bn.name.cliente");
						String typeTrans = Constante.TIP_OPER_RECARGA;//request.getSession().getAttribute("typeTrans").toString();
						TokenSmsRequest tokenSms = new TokenSmsRequest();

						tokenSms.setCodeSMS(pinblock);
						tokenSms.setCodCliente(usuario.getCodigoCic());
						tokenSms.setTipoDocumento(documentType);
						tokenSms.setNroDocumento(nroDoc);
						tokenSms.setTypeTrans(typeTrans);//3 RECARGA
						tokenSms.setTypeOp(Constante.COD_TRANSACCION_FINANCIERA);//FIN
						tokenSms.setTypeCurrency(cuenta.getMonedaProducto().equals("USD")?"USD":"PEN");//PEN
						tokenSms.setAmount(importeSol);
						tokenSms.setMsgClient("Validacion de Clave Dinamica Digital");
						tokenSms.setNomCliDestinatario(codServicio);
						tokenSms.setCodCliDestinatario(afiliacionRecargaMovistar.getNumeroServicio());
						
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
					
					pagoTelefonoImpl = (PagoTelefonoImpl)FacadeFactory.getPagoFacade()
										.getRecargaTelefono(codTransaccion, cuenta, hidCodEntidad, 
															hidCodServ, afiliacionRecargaMovistar, fecha, 
															nMonto, codMoneda, usuario, importe, montoSol,
															montoDol, montoTipCambio, ctaorigen, empresa, 
															servicioRec,request.getRemoteAddr(), request);
					
					request.getSession().setAttribute(ConstanteSesion.PAGO_TELEFONO,pagoTelefonoImpl);
					
					if(usuario.getNotificacion().getEstadoNotificacion().equals("1")){
						ServiciosNotificacionPrincipal.enviarNotificacion(
								usuario,
								usuario.getNombre(), 
								cuenta.getSimboloMonedaProducto(), 
								pagoTelefonoImpl.getImporte().toString(),
								usuario.getTipoCambio().getVenta(), 
								Constante.NOTI_PAGO_SERVICIOS, 
								usuario.getNotificacion());
					}
					
					ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_SERVICIOS_RECARGAS_TELEFONO, ConstanteSesion.PAGO_TELEFONO, request);
				}
				catch (ArrayRuleException e) {
					log3.error(e,Constante.ERR_ERROR_VALIDAR_TDC,"");
					e.printStackTrace();
					//resultCoord = SolicitarServiciosTDC.solicitCoordenada(param);
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
					e.setForward("confirmarRecargaMovistar");
					throw e;
				}
			}
			
		}
		catch (ArrayRuleException e) {
			log3.error(e,Constante.ERR_SOLICITAR_COORDENADA,"");
			e.setForward("confirmarRecargaMovistar");
			throw e;
		}
		finally{
			request.getSession().setAttribute("cuenta", (Cuenta)request.getSession().getAttribute("ctaSession"));
			request.getSession().setAttribute("cmbCuenta",request.getParameter("cmbCuenta"));
		}
		if(afiliacionRecargaMovistar.getFlagRegistro().equals(Constante.NUEV_REGISTRO_TRANSAC)){
			
			String adicionar = request.getParameter("adicionar");
			if(adicionar!=null){
				afiliacionRecargaMovistar.setTipoAfiliacion(Constante.RECARGA_MOV);			
				afiliacionRecargaMovistar.setDescripcion(request.getParameter("txtNombreAfil").trim());
					
				try {
					Afiliacion af=FacadeFactory.getAfiliacionFacade().afiliar(usuario,afiliacionRecargaMovistar);
					request.getSession().setAttribute(ConstanteSesion.AFILIAR,af);
					
				} catch (ArrayRuleException e) {
					log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
					e.printStackTrace();
					e.setForward("pagarRecargaMovistar");
				}
			}
	}
		return mapping.findForward("pagarRecargaMovistar");
	}
	
	public ActionForward verRecargaClaro(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception{	
		
		

		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		String[] id;
		
		
		String cuenta =request.getParameter("cmbCuenta");
				
		// NUEVA VALIDACION
		CuentaImpl cuentaVal = null;
		List lista = usuario.getCuentas();
		int nFlgExiste = 0;
		for (Iterator iter = lista.iterator(); iter.hasNext();) {
			cuentaVal = (CuentaImpl) iter.next();
			
			if(cuentaVal.getNumeroProducto().equals(cuenta)){
				nFlgExiste = 1;
				break;
			}
		}
		
		if(nFlgExiste == 0)throw new ArrayRuleException(ConstanteError.GENERICO,"La cuenta no le pertenece.");
		
		String hidCodEntidad = (String) request.getSession().getAttribute("hidCodEntidad");
		String hidCodServ = (String) request.getSession().getAttribute("hidCodServ");
		
		request.getSession().setAttribute("hidCodEntidad", hidCodEntidad);
		request.getSession().setAttribute("hidCodServ", hidCodServ);
		
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		
		ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);

		
		String optCuenta = request.getParameter("optCuenta");

		request.getSession().setAttribute("optCuenta", optCuenta);

		String codigoAfiliado="";



					if(request.getSession().getAttribute("optCuenta").equals(Constante.NUEV_REGISTRO_TRANSAC)){
						codigoAfiliado = request.getParameter("txtServicioRecargoClaro");
						List listaAfiliaciones = null;
						listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaExiste(Constante.RECARGA_CLA,usuario.getTarjeta().getNumero(),Constante.COD_HLP_RECARGA_CLARO, codigoAfiliado);
						if (listaAfiliaciones.size() > 0){
							listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.RECARGA_CLA,usuario.getTarjeta().getNumero(), "00438");
							request.setAttribute("listaAfiliaciones",listaAfiliaciones);
							throw new ArrayRuleException(ConstanteError.GENERICO,"El Codigo Afiliado: " + codigoAfiliado +" se encuentra como frecuente.");
						}
						
						Afiliacion afiliacion = new AfiliacionImpl();
						
						afiliacion.setNumeroServicio(codigoAfiliado);
						
							afiliacion.setTipoAfiliacion(hidCodEntidad);
							afiliacion.setCodigoLocalidad("00");
							afiliacion.setNumeroServicio(codigoAfiliado);
							afiliacion.setNroTarjeta(usuario.getTarjeta().getNumero());
							afiliacion.setFlagRegistro(Constante.NUEV_REGISTRO_TRANSAC);
							afiliacion.setCodigoServicio(Constante.SERVICIO_CELULAR);
							request.getSession().setAttribute("afiliacion",afiliacion);
					}
					
					if(request.getSession().getAttribute("optCuenta").equals(Constante.FREC_REGISTRO_TRANSAC))
					{
						codigoAfiliado = request.getParameter("cmbPagoTelefono");
						
					
		
					}

		
					if(codigoAfiliado!=null){
						id = ObjectUtil.getArrayStrings(codigoAfiliado,"-");
						request.getSession().setAttribute("cmbPagoTelefono",codigoAfiliado);
					}else{
						id = ObjectUtil.getArrayStrings(request.getSession().getAttribute("cmbPagoTelefono").toString(),"-");
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
				request.getSession().setAttribute("cmbCuenta",cuenta);
			}else{
				request.setAttribute("cuenta", (Cuenta)request.getSession().getAttribute("ctaSession"));
			}
		}
		catch(ArrayRuleException e){
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			System.out.println("!!!!!!!Entro al catch del metodo verRecargaTelefono");
			//String codServicio= (String)request.getSession().getAttribute("cdSsPT");
			List listaAfiliaciones 	= null;
			listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.RECARGA_CLA,usuario.getTarjeta().getNumero(), "00438");
			request.setAttribute("listaAfiliaciones",listaAfiliaciones);
			throw e;
		}
		return mapping.findForward("verRecargaClaro");
	}
	
	public ActionForward confirmarRecargaClaro(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)	throws Exception{
		
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
		Cuenta cuenta = (Cuenta)request.getSession().getAttribute("ctaSession");
		String importe = request.getParameter("importe").toString();
		
		try{
		
			double monto= Double.parseDouble(importe);
			if(monto==0){
				ArrayRuleException e2 = new ArrayRuleException(ConstanteError.GENERICO,"Verifique el monto de recarga  ingresado");
				e2.setForward("verRecargaClaro");
				throw e2;
			}
		}catch(NumberFormatException e1){
			log3.error(e1,Constante.ERR_LOGICA_NEGOCIO,"");
			ArrayRuleException e2 = new ArrayRuleException(ConstanteError.GENERICO,"Verifique el monto de recarga  ingresado");
			e2.setForward("verRecargaClaro");
			throw e2;
		}
		finally{
			request.setAttribute("cuenta",cuenta);
		}
			
		Afiliacion afiliacion = (AfiliacionImpl)request.getSession().getAttribute("afiliacion");
		request.getSession().setAttribute("afiliacion",afiliacion);
		
		BigDecimal nMonto;
		nMonto = new BigDecimal(ObjectUtil.replaceChar(importe,',',""));
		nMonto = nMonto.setScale(2,BigDecimal.ROUND_HALF_EVEN);
		
		int result;
		BigDecimal importeSol = new BigDecimal(importe.replace(",", ""));
		BigDecimal importeMin = new BigDecimal(Constante.CONST_TELEGIRO_MONTO_MININO);
		
		result = importeSol.compareTo(importeMin);
		if (result == 1){
			ArrayRuleException e3 = new ArrayRuleException(ConstanteError.GENERICO,"El monto ingresado debe ser menor o igual a S/ 2000.00 Soles");
			e3.setForward("verRecargaClaro");
			throw e3;
		}
		
		String abrevMoneda = cuenta.getMonedaProducto();
		String codMoneda = Constante.COD_MONEDA_SOL; //Cuenta Movistar
		
		request.getSession().setAttribute("cmbCuenta",request.getParameter("cmbCuenta"));
		

		if(cuenta.getMonedaProducto().equals(Constante.MONEDA_DOLAR)){
			request.getSession().setAttribute("tipoCambioCompra",((pe.cosapi.domain.impl.UsuarioImpl)request.getSession().getAttribute(pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION)).getTipoCambio().getCompra().setScale(2));
		}

		try{
			request.getSession().setAttribute(ConstanteSesion.CONSULTA,FacadeFactory.getPagoFacade().getConfirmaRecargaTelefono(usuario,cuenta, afiliacion, nMonto, abrevMoneda, codMoneda, ((pe.cosapi.domain.impl.UsuarioImpl)request.getSession().getAttribute(pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION)).getTipoCambio()));
		}
		catch (ArrayRuleException e4) {
			log3.error(e4,Constante.ERR_LOGICA_NEGOCIO,"");
			e4.setForward("verRecargaClaro");
			throw e4;
		}
		finally{			
			request.getSession().setAttribute("importe",nMonto.setScale(2,BigDecimal.ROUND_HALF_EVEN));			
		}
		
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
			ar.setForward("verRecargaClaro");
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
						
				}else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS))
				{
					
					resultCoord=SolicitarServiciosTDC.solicitarTokens(param);
				
				}else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS))
				{
					generarClaveSms(mapping, form, request, response);					
				}
					
				
			}catch(ArrayRuleException ar){
				log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
				ar.printStackTrace();
				ar.setForward("verRecargaClaro");
				throw ar;
			}
			request.getSession().setAttribute("resultCoord",resultCoord );
		}
		else{
			
			throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
		}

		
		return mapping.findForward("confirmarRecargaClaro");
	}
	
	public ActionForward pagarRecargaClaro(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception{
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
		Afiliacion afiliacionRecargaClaro = (AfiliacionImpl)request.getSession().getAttribute("afiliacion");
		Cuenta cuenta =  (Cuenta)request.getSession().getAttribute("ctaSession");
		
		String codServicio = (String)request.getSession().getAttribute("hidServicio");
		
		String hidCodEntidad = request.getSession().getAttribute("hidCodEntidad").toString();
		String hidCodServ = request.getSession().getAttribute("hidCodServ").toString();
		String importe = request.getSession().getAttribute("importe").toString();
		String importeSol = request.getParameter("importeSol").toString();
		String importeDol = request.getParameter("importeDol").toString();
		String tipoCambioCompra = request.getParameter("tipoCambioCompra").toString();
		String ctaorigen=request.getParameter("origen");
		String codMoneda = request.getParameter("codMoneda");
		String transaccion = request.getParameter("transaccion");
		
		request.getSession().setAttribute("afiliacion",afiliacionRecargaClaro);
		request.getSession().setAttribute("cmbCuenta",cuenta);
		request.getSession().setAttribute("empresa",ConstanteSesion.TIT_TEL_RECARGA_CLARO);
		request.getSession().setAttribute("hidCodEntidad", hidCodEntidad);
		request.getSession().setAttribute("hidCodServ", hidCodServ);	
		
		
		BigDecimal montoSol = null;
		BigDecimal montoDol = null;
		BigDecimal montoTipCambio = null;
		
		java.util.Calendar cal = java.util.Calendar.getInstance();
	 	Timestamp t = new java.sql.Timestamp(cal.getTime().getTime()); 					 			
	 	Date data = new Date(t.getTime());
	 	String fecha = (ObjectUtil.replaceChar(data.toString(),'-',""));
		
		montoSol = new BigDecimal(ObjectUtil.replaceChar(importeSol,',',""));
		if(cuenta.getMonedaProducto().equals(Constante.MONEDA_DOLAR)){
			montoDol = new BigDecimal(ObjectUtil.replaceChar(importeDol,',',""));
			montoTipCambio = new BigDecimal(ObjectUtil.replaceChar(tipoCambioCompra,',',""));
		}

		String nMonto= (ObjectUtil.replaceChar(importe,'.',""));
		String montoformat = request.getParameter("importe");
		
		request.getSession().setAttribute("fecpago",pe.cosapi.common.ObjectUtil.getFechaActual());
		request.getSession().setAttribute("horpago",pe.cosapi.common.ObjectUtil.getHHMMSSformateado());
		request.getSession().setAttribute("importe",request.getParameter("importeOriginal"));
		request.getSession().setAttribute("abonado",request.getParameter("abonado"));
		request.getSession().setAttribute("origen",request.getParameter("origen"));

		//String clave = SeguridadUtil.getClaveDesencriptada(request.getParameter("txtNumClave"),request);
		
		
		try{
			PagoTelefonoImpl pagoTelefonoImpl = new PagoTelefonoImpl();
			pagoTelefonoImpl.setCodServEnt(hidCodServ);
			pagoTelefonoImpl.setCodEntidad(hidCodEntidad);
			
			pagoTelefonoImpl.setFecha(pe.cosapi.common.ObjectUtil.getFechaActual());
			pagoTelefonoImpl.setHora(pe.cosapi.common.ObjectUtil.getHHMMSSformateado());
			
			ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
			request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
			
//			REALIZAR CONSULTA TIPO CLAVE DINAMICA  --> ADD M.D.B.
			ParametrosElemSegTDC elementos = null;
			ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
			request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
			ElemenSeguridad coord = (ElemenSeguridad)request.getSession().getAttribute("resultCoord");
			
			String pinblock = ObjectUtil.getClaveDesencriptada(request.getParameter("txtCoordenada"),request);		

			try{
				elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
				
				if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS) ||
				   elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
					elementos = SolicitarServiciosTDC.verificarTipoElemento(param,usuario);
				}	
			}catch(ArrayRuleException ar){
				log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
				ar.printStackTrace();
				ar.setForward("verRecargaClaro");
				throw ar;
			}

			// Código Nuevo para TDC
			MensajesTDC resultado = new MensajesTDC();
			ElemenSeguridad resultCoord = null;
			
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
					else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {
						
						String nroDoc = numDoc;
						String documentType = Funciones.lpad(tipoDoc,"0",3);
						//String nameClient = StringUtils.isNotEmpty(datosUsuario.getBeneficiario())?datosUsuario.getBeneficiario():LoadDataProperties.getInstance().getValue("bn.name.cliente");
						TokenSmsRequest tokenSms = new TokenSmsRequest();

						tokenSms.setCodeSMS(pinblock);
						tokenSms.setCodCliente(usuario.getCodigoCic());
						tokenSms.setTipoDocumento(documentType);
						tokenSms.setNroDocumento(nroDoc);
						tokenSms.setTypeTrans(Constante.TIP_OPER_RECARGA);//3 RECARGA
						tokenSms.setTypeOp(Constante.COD_TRANSACCION_FINANCIERA);//FIN
						tokenSms.setTypeCurrency(cuenta.getMonedaProducto().equals("USD")?"USD":"PEN");//PEN
						tokenSms.setAmount(importeSol);
						tokenSms.setMsgClient("Validacion de Clave Dinamica Digital");
						tokenSms.setNomCliDestinatario(codServicio);
						tokenSms.setCodCliDestinatario(afiliacionRecargaClaro.getNumeroServicio());
						
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
					
					pagoTelefonoImpl = (PagoTelefonoImpl)FacadeFactory.getPagoFacade().getRecargaClaro(transaccion, pagoTelefonoImpl, cuenta, afiliacionRecargaClaro, fecha, nMonto, codMoneda, usuario, montoformat, montoSol, montoDol, montoTipCambio, ctaorigen, hidCodEntidad, request.getRemoteAddr(), request); 
					
					request.getSession().setAttribute(ConstanteSesion.PAGO_TELEFONO,pagoTelefonoImpl); 
					
					if(usuario.getNotificacion().getEstadoNotificacion().equals("1")){ 
						ServiciosNotificacionPrincipal.enviarNotificacion(
								usuario,
								usuario.getNombre(), 
								cuenta.getSimboloMonedaProducto(), 
								pagoTelefonoImpl.getImporte().toString(),
								usuario.getTipoCambio().getVenta(), 
								Constante.NOTI_PAGO_SERVICIOS, 
								usuario.getNotificacion()); 
					}
					
					ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_SERVICIOS_RECARGAS_CLARO, ConstanteSesion.PAGO_TELEFONO, request);
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
						
					}else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS))
					{
						
						resultCoord=SolicitarServiciosTDC.solicitarTokensSms(param);
					}
					
					request.getSession().setAttribute("resultCoord",resultCoord );
					e.setForward("confirmarRecargaClaro");
					throw e;
				}
			}
			
		
		}

		catch (ArrayRuleException e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			e.setForward("confirmarRecargaClaro");
			throw e;
		}
		finally{
			request.getSession().setAttribute("cuenta", (Cuenta)request.getSession().getAttribute("ctaSession"));
			request.getSession().setAttribute("cmbCuenta",request.getParameter("cmbCuenta"));
		}
		
		if(afiliacionRecargaClaro.getFlagRegistro().equals(Constante.NUEV_REGISTRO_TRANSAC)){
			
			String adicionar = request.getParameter("adicionar");
			
			if(adicionar!=null){

				afiliacionRecargaClaro.setTipoAfiliacion(Constante.RECARGA_CLA);			
				afiliacionRecargaClaro.setDescripcion(request.getParameter("txtNombreAfil").trim());
				
				try {
						Afiliacion af=FacadeFactory.getAfiliacionFacade().afiliar(usuario,afiliacionRecargaClaro);
						request.getSession().setAttribute(ConstanteSesion.AFILIAR,af);
					
				} catch (ArrayRuleException e) {
					log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
					e.printStackTrace();
					e.setForward("pagarRecargaClaro");
				}
			}
		
			
		}
		return mapping.findForward("pagarRecargaClaro");
	}
	
	
	public ActionForward verRecargaOtros(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception{	
		
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		String[] id;
		String hidServicio = request.getParameter("hidServicio");
		
		String cuenta =request.getParameter("cmbCuenta");
	
		
		// NUEVA VALIDACION
		CuentaImpl cuentaVal = null;
		List lista = usuario.getCuentas();
		int nFlgExiste = 0;
		for (Iterator iter = lista.iterator(); iter.hasNext();) {
			cuentaVal = (CuentaImpl) iter.next();
			
			if(cuentaVal.getNumeroProducto().equals(cuenta)){
				nFlgExiste = 1;
				break;
			}
		}
		
		if(nFlgExiste == 0)throw new ArrayRuleException(ConstanteError.GENERICO,"La cuenta no le pertenece.");
		
		String hidCodEntidad = (String) request.getSession().getAttribute("hidCodEntidad");
		String hidCodServ = (String) request.getSession().getAttribute("hidCodServ");
		
		request.getSession().setAttribute("hidCodEntidad", hidCodEntidad);
		request.getSession().setAttribute("hidCodServ", hidCodServ);
		
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		
		ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);

		
		String optCuenta = request.getParameter("optCuenta");

		request.getSession().setAttribute("optCuenta", optCuenta);

		String codigoAfiliado="";

					if(request.getSession().getAttribute("optCuenta").equals(Constante.NUEV_REGISTRO_TRANSAC)){
						codigoAfiliado = request.getParameter("txtServicioRecargoClaro");
						List listaAfiliaciones = null;
						if (hidServicio.equals("14")){
							listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaExiste(Constante.RECARGA_BIT,usuario.getTarjeta().getNumero(),Constante.COD_HLP_RECARGA_CLARO, codigoAfiliado);
							if (listaAfiliaciones.size() > 0){
								listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.RECARGA_BIT,usuario.getTarjeta().getNumero(), "00438");
								request.setAttribute("listaAfiliaciones",listaAfiliaciones);
								throw new ArrayRuleException(ConstanteError.GENERICO,"El Codigo Afiliado: " + codigoAfiliado +" se encuentra como frecuente.");
							}
						}
						
						if (hidServicio.equals("16")){
							
							listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaExiste(Constante.RECARGA_ENT,usuario.getTarjeta().getNumero(),Constante.COD_HLP_RECARGA_CLARO, codigoAfiliado);
							if (listaAfiliaciones.size() > 0){
								listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.RECARGA_ENT,usuario.getTarjeta().getNumero(), "00438");
								request.setAttribute("listaAfiliaciones",listaAfiliaciones);
								throw new ArrayRuleException(ConstanteError.GENERICO,"El Codigo Afiliado: " + codigoAfiliado +" se encuentra como frecuente.");
							}
							
							
						}
						
						request.setAttribute("hidServicio",hidServicio);
						Afiliacion afiliacion = new AfiliacionImpl();
						
						afiliacion.setNumeroServicio(codigoAfiliado);
						
							afiliacion.setTipoAfiliacion(hidCodEntidad);
							afiliacion.setCodigoLocalidad("00");
							afiliacion.setNumeroServicio(codigoAfiliado);
							afiliacion.setNroTarjeta(usuario.getTarjeta().getNumero());
							afiliacion.setFlagRegistro(Constante.NUEV_REGISTRO_TRANSAC);
							afiliacion.setCodigoServicio(Constante.SERVICIO_CELULAR);
							request.getSession().setAttribute("afiliacion",afiliacion);
					}
					
					if(request.getSession().getAttribute("optCuenta").equals(Constante.FREC_REGISTRO_TRANSAC))
					{
						codigoAfiliado = request.getParameter("cmbPagoTelefono");
						
					
		
					}

		
					if(codigoAfiliado!=null){
						id = ObjectUtil.getArrayStrings(codigoAfiliado,"-");
						request.getSession().setAttribute("cmbPagoTelefono",codigoAfiliado);
					}else{
						id = ObjectUtil.getArrayStrings(request.getSession().getAttribute("cmbPagoTelefono").toString(),"-");
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
				request.getSession().setAttribute("cmbCuenta",cuenta);
			}else{
				request.setAttribute("cuenta", (Cuenta)request.getSession().getAttribute("ctaSession"));
			}
			
			
		}
		catch(ArrayRuleException e){
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			System.out.println("!!!!!!!Entro al catch del metodo verRecargaTelefono");
			List listaAfiliaciones 	= null;
			listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.RECARGA_CLA,usuario.getTarjeta().getNumero(), "00438");
			request.setAttribute("listaAfiliaciones",listaAfiliaciones);
			throw e;
		}
		return mapping.findForward("verRecargaClaro");
	}
	
	
	public ActionForward confirmarRecargaOtros(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)	throws Exception{
			
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
		Cuenta cuenta = (Cuenta)request.getSession().getAttribute("ctaSession");
		String hidServicio = request.getParameter("hidServicio");
		String importe = request.getParameter("importe").toString();
		request.getSession().setAttribute("hidServicio",hidServicio);
		request.getSession().setAttribute("importe",importe);
		
		try{
		
			double monto= Double.parseDouble(importe);
			if(monto==0){
				ArrayRuleException e2 = new ArrayRuleException(ConstanteError.GENERICO,"Verifique el monto de recarga  ingresado");
				e2.setForward("verRecargaClaro");
				throw e2;
			}
		}catch(NumberFormatException e1){
			log3.error(e1,Constante.ERR_LOGICA_NEGOCIO,"");
			ArrayRuleException e2 = new ArrayRuleException(ConstanteError.GENERICO,"Verifique el monto de recarga  ingresado");
			e2.setForward("verRecargaClaro");
			throw e2;
		}
		finally{
			request.setAttribute("cuenta", (Cuenta)request.getSession().getAttribute("ctaSession"));
		}
			
		Afiliacion afiliacion = (AfiliacionImpl)request.getSession().getAttribute("afiliacion");
		request.getSession().setAttribute("afiliacion",afiliacion);
		
		BigDecimal nMonto;
		nMonto = new BigDecimal(ObjectUtil.replaceChar(importe,',',""));
		nMonto = nMonto.setScale(2,BigDecimal.ROUND_HALF_EVEN);
		
		int result;
		BigDecimal importeSol = new BigDecimal(importe.replace(",", ""));
		BigDecimal importeMin = new BigDecimal(Constante.CONST_TELEGIRO_MONTO_MININO);
		
		result = importeSol.compareTo(importeMin);
		if (result == 1){
			ArrayRuleException e3 = new ArrayRuleException(ConstanteError.GENERICO,"El monto ingresado debe ser menor o igual a S/ 2000.00 Soles");
			e3.setForward("verRecargaClaro");
			throw e3;
		}
		
		String abrevMoneda = cuenta.getMonedaProducto();
		String codMoneda = Constante.COD_MONEDA_SOL; 
		
		request.getSession().setAttribute("cmbCuenta",request.getParameter("cmbCuenta"));
		

		if(cuenta.getMonedaProducto().equals(Constante.MONEDA_DOLAR)){
			request.getSession().setAttribute("tipoCambioCompra",((pe.cosapi.domain.impl.UsuarioImpl)request.getSession().getAttribute(pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION)).getTipoCambio().getCompra().setScale(2));
		}

		try{
			PagoTelefono pagoTelefonoImpl = new PagoTelefonoImpl();
			pagoTelefonoImpl = (PagoTelefonoImpl)FacadeFactory.getPagoFacade().getConsultaRecargaOtros("PF02", pagoTelefonoImpl,  afiliacion, usuario,request.getRemoteAddr(), request,nMonto, hidServicio);
		
			request.getSession().setAttribute(ConstanteSesion.CONSULTA,pagoTelefonoImpl);
			//request.getSession().setAttribute(ConstanteSesion.CONSULTA,FacadeFactory.getPagoFacade().getConfirmaRecargaTelefono(usuario,cuenta, afiliacion, nMonto, abrevMoneda, codMoneda, ((pe.cosapi.domain.impl.UsuarioImpl)request.getSession().getAttribute(pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION)).getTipoCambio()));
		}
		catch (ArrayRuleException e4) {
			log3.error(e4,Constante.ERR_LOGICA_NEGOCIO,"");
			e4.setForward("verRecargaClaro");
			throw e4;
		}
		finally{			
			request.getSession().setAttribute("importe",nMonto.setScale(2,BigDecimal.ROUND_HALF_EVEN));			
		}
		
		ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		
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
			ar.setForward("verRecargaClaro");
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
				ar.setForward("verRecargaClaro");
				throw ar;
			}
			request.getSession().setAttribute("resultCoord",resultCoord );
		}
		else{
			
			throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
		}

		
		return mapping.findForward("confirmarRecargaClaro");
	}
	
	public ActionForward pagarRecargaOtros(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception{
	
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
		
		PagoTelefonoImpl pagoRecarga = (PagoTelefonoImpl)request.getSession().getAttribute(ConstanteSesion.CONSULTA);
		
		Afiliacion afiliacionRecargaClaro = (AfiliacionImpl)request.getSession().getAttribute("afiliacion");
		request.getSession().setAttribute("afiliacion",afiliacionRecargaClaro);
		Cuenta cuenta =  (Cuenta)request.getSession().getAttribute("ctaSession");			
		request.getSession().setAttribute("cmbCuenta",cuenta);
		
		String codServicio = (String)request.getSession().getAttribute("hidServicio");
		
		String hidCodEntidad = (String) request.getSession().getAttribute("hidCodEntidad");
		String hidCodServ = (String) request.getSession().getAttribute("hidCodServ");
		String hidServicio = request.getParameter("hidServicio");
		String importeSol = request.getParameter("importeSol").toString();
		String importeDol = request.getParameter("importeDol").toString();
		String tipoCambioCompra = request.getParameter("tipoCambioCompra").toString();
		
		request.setAttribute("hidServicio",hidServicio);
		
		if (hidServicio.equals("13")){
			request.getSession().setAttribute("empresa",ConstanteSesion.TIT_TEL_RECARGA_CLARO);
		}else if (hidServicio.equals("14")){
			request.getSession().setAttribute("empresa",ConstanteSesion.TIT_TEL_RECARGA_BITEL);
		}else{
			request.getSession().setAttribute("empresa",ConstanteSesion.TIT_TEL_RECARGA_ENTEL);
		}

		request.getSession().setAttribute("hidCodEntidad", hidCodEntidad);
		request.getSession().setAttribute("hidCodServ", hidCodServ);

		BigDecimal montoSol = null;;
		BigDecimal montoDol = null;;
		BigDecimal montoTipCambio = null;
		
		java.util.Calendar cal = java.util.Calendar.getInstance();
	 	Timestamp t = new java.sql.Timestamp(cal.getTime().getTime()); 					 			
	 	Date data = new Date(t.getTime());
	 	String fecha = (ObjectUtil.replaceChar(data.toString(),'-',""));
		
		montoSol = new BigDecimal(ObjectUtil.replaceChar(importeSol,',',""));
		if(cuenta.getMonedaProducto().equals(Constante.MONEDA_DOLAR)){
			montoDol = new BigDecimal(ObjectUtil.replaceChar(importeDol,',',""));
			montoTipCambio = new BigDecimal(ObjectUtil.replaceChar(tipoCambioCompra,',',""));
		}
		
		//String nMonto=(ObjectUtil.replaceChar(request.getParameter("importe").toString(),'.',""));  Modifcado por observación Deloitte
		String nMonto= (ObjectUtil.replaceChar(request.getSession().getAttribute("importe").toString(),'.',""));
			
						
		String montoformat = request.getParameter("importe");
		request.getSession().setAttribute("fecpago",pe.cosapi.common.ObjectUtil.getFechaActual());
		request.getSession().setAttribute("horpago",pe.cosapi.common.ObjectUtil.getHHMMSSformateado());
		request.getSession().setAttribute("importe",request.getParameter("importeOriginal"));
		request.getSession().setAttribute("abonado",request.getParameter("abonado"));
		request.getSession().setAttribute("origen",request.getParameter("origen"));

		//String clave = SeguridadUtil.getClaveDesencriptada(request.getParameter("txtNumClave"),request);
		String empresa =(String )request.getSession().getAttribute("empresa");
		String ctaorigen=request.getParameter("origen");
		String codMoneda = request.getParameter("codMoneda");
		String codTransaccion = request.getParameter("transaccion");
		
		try{
			PagoTelefonoImpl pagoTelefonoImpl = new PagoTelefonoImpl();
			pagoTelefonoImpl.setCodServEnt(hidCodServ);
			pagoTelefonoImpl.setCodEntidad(hidCodEntidad);
			pagoRecarga.setCodServEnt(hidCodServ);
			pagoRecarga.setCodEntidad(hidCodEntidad);
						
			ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
			request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
			
//			REALIZAR CONSULTA TIPO CLAVE DINAMICA  --> ADD M.D.B.
			ParametrosElemSegTDC elementos = null;
			ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
			request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
			ElemenSeguridad coord = (ElemenSeguridad)request.getSession().getAttribute("resultCoord");
	
			String pinblock = ObjectUtil.getClaveDesencriptada(request.getParameter("txtCoordenada"),request);		
			
			try{
				elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
				
				if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS) ||
				   elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
					elementos = SolicitarServiciosTDC.verificarTipoElemento(param,usuario);
				}	
				
			}catch(ArrayRuleException ar){
				log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
				ar.printStackTrace();
				ar.setForward("verRecargaClaro");
				throw ar;
			}
	
			// Código Nuevo para TDC
		
			MensajesTDC resultado = new MensajesTDC();
			ElemenSeguridad resultCoord = null;
			
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
						//String nameClient = StringUtils.isNotEmpty(datosUsuario.getBeneficiario())?datosUsuario.getBeneficiario():LoadDataProperties.getInstance().getValue("bn.name.cliente");
						
						TokenSmsRequest tokenSms = new TokenSmsRequest();

						tokenSms.setCodeSMS(pinblock);
						tokenSms.setCodCliente(usuario.getCodigoCic());
						tokenSms.setTipoDocumento(documentType);
						tokenSms.setNroDocumento(nroDoc);
						tokenSms.setTypeTrans(Constante.TIP_OPER_RECARGA);//3 RECARGA
						tokenSms.setTypeOp(Constante.COD_TRANSACCION_FINANCIERA);//FIN
						tokenSms.setTypeCurrency(cuenta.getMonedaProducto().equals("USD")?"USD":"PEN");//PEN
						tokenSms.setAmount(importeSol);
						tokenSms.setMsgClient("Validacion de Clave Dinamica Digital");
						tokenSms.setNomCliDestinatario(codServicio);
						tokenSms.setCodCliDestinatario(afiliacionRecargaClaro.getNumeroServicio());
						
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
					
					pagoTelefonoImpl = (PagoTelefonoImpl)FacadeFactory.getPagoFacade().getRecargaOtros(codTransaccion, pagoRecarga, cuenta, afiliacionRecargaClaro, fecha, nMonto, codMoneda, usuario, montoformat, montoSol, montoDol, montoTipCambio, ctaorigen, empresa,hidServicio, request.getRemoteAddr(), request);
					request.getSession().setAttribute(ConstanteSesion.PAGO_TELEFONO,pagoTelefonoImpl);
					
					if(usuario.getNotificacion().getEstadoNotificacion().equals("1")){
						ServiciosNotificacionPrincipal.enviarNotificacion(
								usuario,
								usuario.getNombre(), 
								cuenta.getSimboloMonedaProducto(), 
								pagoTelefonoImpl.getImporte().toString(),
								usuario.getTipoCambio().getVenta(), 
								Constante.NOTI_PAGO_SERVICIOS, 
								usuario.getNotificacion());
					}
					
					ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_SERVICIOS_RECARGAS_OTROS, ConstanteSesion.PAGO_TELEFONO, request);
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
					e.setForward("confirmarRecargaClaro");
					throw e;
				}
			}
			
		
		}

		catch (ArrayRuleException e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			e.setForward("confirmarRecargaClaro");
			throw e;
		}
		finally{
			request.getSession().setAttribute("cuenta", (Cuenta)request.getSession().getAttribute("ctaSession"));
			request.getSession().setAttribute("cmbCuenta",request.getParameter("cmbCuenta"));
		}
		
		if(afiliacionRecargaClaro.getFlagRegistro().equals(Constante.NUEV_REGISTRO_TRANSAC)){
			
			String adicionar = request.getParameter("adicionar");
			
			if(adicionar!=null){
				
			
				if(hidServicio.equals("14")){
					afiliacionRecargaClaro.setTipoAfiliacion(Constante.RECARGA_BIT);			
					afiliacionRecargaClaro.setDescripcion(request.getParameter("txtNombreAfil").trim());
				}else{
					afiliacionRecargaClaro.setTipoAfiliacion(Constante.RECARGA_ENT);			
					afiliacionRecargaClaro.setDescripcion(request.getParameter("txtNombreAfil").trim());
				}
				
							
				
				try {
						Afiliacion af=FacadeFactory.getAfiliacionFacade().afiliar(usuario,afiliacionRecargaClaro);
						request.getSession().setAttribute(ConstanteSesion.AFILIAR,af);
					
				} catch (ArrayRuleException e) {
					log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
					e.printStackTrace();
					e.setForward("pagarRecargaOtros");
				}
			}
		
			
		}
		return mapping.findForward("pagarRecargaOtros");
	}
	
	
	public void reGenerarClaveSms(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
		
		Cuenta cuenta =  (Cuenta)request.getSession().getAttribute("ctaSession");		
		Afiliacion afiliacionRecargaMovistar = (AfiliacionImpl)request.getSession().getAttribute("afiliacion");
		
		String codServicio = (String)request.getSession().getAttribute("hidServicio");
		
		try {
			//String typetrans = request.getSession().getAttribute("typetrans").toString();
			//PagoSedapalImpl pagoSedapal = (PagoSedapalImpl)request.getSession().getAttribute(ConstanteSesion.PAGO_AGUA);
			
			String typeTrans = Constante.TIP_OPER_RECARGA;//request.getSession().getAttribute("typeTrans").toString();
			String importe = request.getSession().getAttribute("importe").toString();

			TokenSmsRequest tokenSms = new TokenSmsRequest();
			String documentType = Funciones.lpad(tipoDoc,"0",3);
			String nroDoc = numDoc;
			
			tokenSms.setCodCliente(usuario.getCodigoCic());
			tokenSms.setNroDocumento(nroDoc);
			tokenSms.setTipoDocumento(documentType); //DNI
			tokenSms.setTypeTrans(typeTrans);//3 RECARGA
			tokenSms.setTypeOp(Constante.COD_TRANSACCION_FINANCIERA);//FIN						
			tokenSms.setTypeCurrency(cuenta.getMonedaProducto().equals("USD")?"USD":"PEN");//PEN
			tokenSms.setAmount(importe);
			
			tokenSms.setNomCliDestinatario(codServicio);
			tokenSms.setCodCliDestinatario(afiliacionRecargaMovistar.getNumeroServicio());

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
		
		//return mapping.findForward("confirmarPagoUniversidad");
	}
	
	
	public void generarClaveSms(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
		
		Cuenta cuenta =  (Cuenta)request.getSession().getAttribute("ctaSession");		
		Afiliacion afiliacionRecargaMovistar = (AfiliacionImpl)request.getSession().getAttribute("afiliacion");
		
		String codServicio = (String)request.getSession().getAttribute("hidServicio");
		
		try {
			//String typetrans = request.getSession().getAttribute("typetrans").toString();
			//PagoSedapalImpl pagoSedapal = (PagoSedapalImpl)request.getSession().getAttribute(ConstanteSesion.PAGO_AGUA);
			
			String typeTrans = Constante.TIP_OPER_RECARGA;//request.getSession().getAttribute("typeTrans").toString();
			String importe = request.getSession().getAttribute("importe").toString();

			TokenSmsRequest tokenSms = new TokenSmsRequest();
			String documentType = Funciones.lpad(tipoDoc,"0",3);
			String nroDoc = numDoc;
			
			tokenSms.setCodCliente(usuario.getCodigoCic());
			tokenSms.setNroDocumento(nroDoc);
			tokenSms.setTipoDocumento(documentType); //DNI
			tokenSms.setTypeTrans(typeTrans);//3 RECARGA
			tokenSms.setTypeOp(Constante.COD_TRANSACCION_FINANCIERA);//FIN						
			tokenSms.setTypeCurrency(cuenta.getMonedaProducto().equals("USD")?"USD":"PEN");//PEN
			tokenSms.setAmount(importe);
			
			tokenSms.setNomCliDestinatario(codServicio);
			tokenSms.setCodCliDestinatario(afiliacionRecargaMovistar.getNumeroServicio());

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
		
		//return mapping.findForward("confirmarPagoUniversidad");
	}

}