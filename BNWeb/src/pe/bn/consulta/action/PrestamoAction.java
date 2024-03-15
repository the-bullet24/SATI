package pe.bn.consulta.action;


import java.io.InputStream;
import java.math.BigDecimal;

import java.util.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import pe.bn.consulta.action.form.PrestamoForm;

import pe.bn.consulta.domain.impl.PrestamoImpl;

import pe.bn.listener.Util;
import pe.bn.notificaciones.action.ServicioNotificacionConstancia;
import pe.bn.transferencia.domain.Transferencia;
import pe.com.bn.common.Funciones;
import pe.com.bn.common.LoadDataProperties;
import pe.com.bn.common.sati.bean.TokenSmsRequest;
import pe.com.bn.sati.common.LoggerSati;
import pe.com.bn.wsen.envio.request.EnvioNotificacionRequest;
import pe.com.bn.wsen.envio.service.ServicioNotificacionProxy;
import pe.com.bn.wsen.envio.service.ServicioNotificacionServiceLocator;
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

import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.CuentaImpl;

import pe.cosapi.domain.impl.OperacionImpl;
import pe.cosapi.domain.impl.UsuarioImpl;
import pe.cosapi.system.Key4Digits;

public class PrestamoAction extends GrandActionAbstract{
	private static LoggerSati log3 = LoggerSati.getInstance(PrestamoAction.class.getName());
	BNAplicacion aplicacion = BNAplicacion.getInstance();
	
	

	
	public ActionForward consultarSimulador(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int contador = 0;
				
		
		
		try {
			
			
			PrestamoForm frm = (PrestamoForm)form;
			Usuario usuario 		= (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
			
			
			PrestamoImpl prestamo = (PrestamoImpl)request.getSession().getAttribute(ConstanteSesion.PRESTAMO_SIM);
					
			List cuotas = FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.PRESTAMO_CONST_CUOTAS );
			request.getSession().setAttribute("listCuotas", cuotas);
			
			List x=usuario.getCuentas();
			CuentaImpl cuenta = (CuentaImpl)x.get(0);
			String codProducto="";
			String indRenov = "";
			
			for(int i=0;i<x.size();i++){
				
				CuentaImpl cuenta1 = (CuentaImpl)x.get(i);
				
				if(cuenta1.getTipoProducto().equals(Constante.COD_CUENTA_PRESTAMOS)){
					codProducto=cuenta1.getTipoProducto();
					indRenov=cuenta1.getFlagPrestamo();
				}
			
			}
			
		
			
			if(codProducto.equals(Constante.COD_CUENTA_PRESTAMOS) && (indRenov == null || indRenov.trim().equals(""))){
				
							
				int numero;
				ArrayList numeros = new ArrayList();
				String valorAleatorio = "";
				
				ArrayList<String> numerosCel = new ArrayList<String>();
				numerosCel.add("51942896863");
				numerosCel.add("51942896489");

				//Genera aleatorio 
				for (int i = 1; i <= 1; i++) {
				    numero = (int) (Math.random() * 2 + 1);
				    if (numeros.contains(numero)) {
				        i--;
				    } else {
				        numeros.add(numero);
				    }
				}

				int num=0;
				for(int i=0;i<numeros.size();i++){
				
					 num =Integer.parseInt(""+numeros.get(i))-1;
				
				    
				}
				
				valorAleatorio = numerosCel.get(num);

			
				request.setAttribute("celular", valorAleatorio);
				return mapping.findForward("noCalificaPrest");
			}

			String condicinesPrestamo = FacadeFactory.getGeneralFacade().getUrlPrestamos(Constante.URL_PRESTAMOS_GRUPO, Constante.URL_CONDICIONES_PRESTAMOS);
			String creditoPrestamo = 	FacadeFactory.getGeneralFacade().getUrlPrestamos(Constante.URL_PRESTAMOS_GRUPO, Constante.URL_CONTRATO_CREDITO);
			String polizaPrestamo = 	FacadeFactory.getGeneralFacade().getUrlPrestamos(Constante.URL_PRESTAMOS_GRUPO, Constante.URL_POLIZA_SEGURO);
			String solicitudPrestamo = 	FacadeFactory.getGeneralFacade().getUrlPrestamos(Constante.URL_PRESTAMOS_GRUPO, Constante.URL_SOLICITUD);
			String tarifarioPrestamo = 	FacadeFactory.getGeneralFacade().getUrlPrestamos(Constante.URL_PRESTAMOS_GRUPO, Constante.URL_TARIFARIO);
			String tasas = 				FacadeFactory.getGeneralFacade().getUrlPrestamos(Constante.URL_PRESTAMOS_GRUPO, Constante.URL_TASAS);
						
			if(frm.getRdnSimular()!= null && prestamo!=null){
				
				if(frm.getRdnSimular().equals("S")){
					
					frm.setRdnSimular(frm.getRdnSimular());
					request.setAttribute("flagSim", frm.getRdnSimular());
					
					int result;
					BigDecimal importeSol = new BigDecimal(frm.getTxtImporte().replace(",", ""));
					BigDecimal importeMin = new BigDecimal(prestamo.getSaldoDeuda().replace(",", ""));
				
					result = importeMin.compareTo(importeSol);
					if (result == 1){
						
						throw new ArrayRuleException(ConstanteError.GENERICO,"El monto solicitado es menor a la deuda actual");
					}
					PrestamoImpl simulador = new PrestamoImpl();
					
					BigDecimal importeRedondeado= importeSol.setScale(0,BigDecimal.ROUND_DOWN);
					simulador.setMontoSolicitado(importeRedondeado.toString());
					simulador.setNroCuotas(frm.getCmbCuotas());
					simulador.setFlagPeriodoGracia(frm.getRdnPeriodoGracia());
					simulador.setFlagCuotaProtegida(frm.getRdnCuotaProtegida());
					simulador.setFlagEndoso(Constante.PRESTAMO_CONST_FLAG_NO_MV);
					simulador.setTipoPrestamo(Constante.PRESTAMO_CONST_TIPO_PRESTAMO);
					simulador.setTipoOperacion(Constante.PRESTAMO_CONST_TIPO_OPERACION);
					simulador.setTipoEnvio(Constante.PRESTAMO_CONST_TIPO_ENVIO_VIRTUAL);
					
				
					
					simulador.setCuenta(cuenta.getNumeroProducto());
					simulador.setTarjeta(usuario.getTarjeta().getNumero());
					simulador=FacadeFactory.getPrestamoFacade().simularPrestamo(simulador, request.getRemoteAddr(), usuario, request);
					simulador.setCliente(usuario.getNombre());
				
					simulador.setUrl_condicinesPrestamo(condicinesPrestamo);
					simulador.setUrl_creditoPrestamo(creditoPrestamo);
					simulador.setUrl_polizaPrestamo(polizaPrestamo);
					simulador.setUrl_solicitudPrestamo(solicitudPrestamo);
					simulador.setUrl_tarifarioPrestamo(tarifarioPrestamo);
					simulador.setUrl_tasas(tasas);
					
					BigDecimal importeDif = new BigDecimal(301);
					BigDecimal importeMinReq = new BigDecimal(simulador.getSaldoDeuda().replace(",", ""));
					BigDecimal total = importeDif.add(importeMinReq);
										
					simulador.setMontoMinimo(total.setScale(0,BigDecimal.ROUND_DOWN).toString());
					
					
					request.getSession().setAttribute(ConstanteSesion.PRESTAMO_SIM,simulador);
					}
			}else{
					
				
					PrestamoImpl simuladorDefault = new PrestamoImpl();
					simuladorDefault.setNroCuotas(Constante.PRESTAMO_CONST_CUOTA_DEFAULT);
					simuladorDefault.setFlagPeriodoGracia(Constante.PRESTAMO_CONST_FLAG_NO);
					simuladorDefault.setFlagCuotaProtegida(Constante.PRESTAMO_CONST_FLAG_NO);
					simuladorDefault.setFlagEndoso(Constante.PRESTAMO_CONST_FLAG_NO_MV);
					simuladorDefault.setTipoPrestamo(Constante.PRESTAMO_CONST_TIPO_PRESTAMO);
					simuladorDefault.setTipoOperacion(Constante.PRESTAMO_CONST_TIPO_OPERACION);
					simuladorDefault.setTipoEnvio(Constante.PRESTAMO_CONST_TIPO_ENVIO_VIRTUAL);
					simuladorDefault.setMontoSolicitado(Constante.PRESTAMO_CONST_MONTO_DEFAULT);
					
					
					simuladorDefault.setCuenta(cuenta.getNumeroProducto());
					simuladorDefault.setTarjeta(usuario.getTarjeta().getNumero());
						
					
					simuladorDefault=FacadeFactory.getPrestamoFacade().simularPrestamo( simuladorDefault, request.getRemoteAddr(), usuario, request);
					simuladorDefault.setCliente(usuario.getNombre());
				
					simuladorDefault.setUrl_condicinesPrestamo(condicinesPrestamo);
					simuladorDefault.setUrl_creditoPrestamo(creditoPrestamo);
					simuladorDefault.setUrl_polizaPrestamo(polizaPrestamo);
					simuladorDefault.setUrl_solicitudPrestamo(solicitudPrestamo);
					simuladorDefault.setUrl_tarifarioPrestamo(tarifarioPrestamo);
					simuladorDefault.setUrl_tasas(tasas);
			
					frm.setRdnSimular(Constante.PRESTAMO_CONST_FLAG_NO);
					BigDecimal importeDif = new BigDecimal(301);
					BigDecimal importeMin = new BigDecimal(simuladorDefault.getSaldoDeuda().replace(",", ""));
					BigDecimal total = importeDif.add(importeMin);
					
					frm.setTxtImporte(total.setScale(0,BigDecimal.ROUND_DOWN).toString());
					simuladorDefault.setMontoMinimo(frm.getTxtImporte());
					frm.setRdnCuotaProtegida(Constante.PRESTAMO_CONST_FLAG_NO);
					frm.setRdnPeriodoGracia(Constante.PRESTAMO_CONST_FLAG_NO);
					frm.setCmbCuotas(Constante.PRESTAMO_CONST_CUOTA_DEFAULT);
					frm.setRdnSimular(frm.getRdnSimular());
					request.setAttribute("flagSim", frm.getRdnSimular());
					request.getSession().setAttribute(ConstanteSesion.PRESTAMO_SIM,simuladorDefault);
				//request.getSession().setAttribute(ConstanteSesion.PRESTAMO_SIM,null);
			}
		
		} catch (ArrayRuleException e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			e.printStackTrace();
			List cuotas = FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.PRESTAMO_CONST_CUOTAS );
			
			request.getSession().setAttribute("listCuotas", cuotas);
			e.setForward("consultarSimuladorRenov");
			throw e;
		}
		
		
		return mapping.findForward("consultarSimuladorRenov");
	}
	
public ActionForward consultarNuevoPres(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int contador = 0;
		
	
		int numero;
		ArrayList numeros = new ArrayList();
		String valorAleatorio = "";
		
		ArrayList<String> numerosCel = new ArrayList<String>();
		numerosCel.add("51942896863");
		numerosCel.add("51942896489");

		//Genera aleatorio 
		for (int i = 1; i <= 1; i++) {
		    numero = (int) (Math.random() * 2 + 1);
		    if (numeros.contains(numero)) {
		        i--;
		    } else {
		        numeros.add(numero);
		    }
		}

		int num=0;
		for(int i=0;i<numeros.size();i++){
		
			 num =Integer.parseInt(""+numeros.get(i))-1;
	
		    
		}
		
		valorAleatorio = numerosCel.get(num);


		request.setAttribute("celular", valorAleatorio);
		return mapping.findForward("nuevoPrestamo");
	}
	
	public ActionForward confirmarPrestamo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
				
		Usuario usuario 		= (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		
		PrestamoImpl prestamo = (PrestamoImpl)request.getSession().getAttribute(ConstanteSesion.PRESTAMO_SIM);
						
		request.getSession().setAttribute(ConstanteSesion.PRESTAMO_SIM,prestamo);
		
		PrestamoForm frm = (PrestamoForm)form;
		frm.setRdnSimular(null);
		
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
	
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
			ar.setForward("confirmarPrestamoRenov");
			throw ar;
		}
		
			ElemenSeguridad resultCoord = null;
				
				if(param!= null){
					resultCoord = new ElemenSeguridad();
					try{
						request.getSession().setAttribute("resultCoord",null );
						
						if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC))
						{
							resultCoord = SolicitarServiciosTDC.solicitCoordenada(param);
						} else if (elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS)) {
							resultCoord = SolicitarServiciosTDC.solicitarTokens(param);
						} else if (elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {
							generarClaveSms(mapping,form, request, response);
						}
					}catch(ArrayRuleException ar){
						log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
						ar.printStackTrace();
						ar.setForward("confirmarPrestamoRenov");
						throw ar;
					}
					request.getSession().setAttribute("resultCoord",resultCoord );
				}
				else{
					
					throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
				}
		
		//
		
		return mapping.findForward("confirmarPrestamoRenov");
	}	
	public ActionForward finalizarPrestamo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
			
		TokenSmsRequest tokenSms = new TokenSmsRequest();
		
			try {
				
				
				PrestamoForm frm = (PrestamoForm)form;
				Usuario usuario 		= (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
				PrestamoImpl result = new PrestamoImpl();
				PrestamoImpl prestamo = (PrestamoImpl)request.getSession().getAttribute(ConstanteSesion.PRESTAMO_SIM);
				ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
				request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);	
				
				ElemenSeguridad coord = (ElemenSeguridad)request.getSession().getAttribute("resultCoord");
				
				String pinblock = ObjectUtil.getClaveDesencriptada(request.getParameter("txtCoordenada"),request);
				ParametrosElemSegTDC elementos = null;
				
				//Afiliacion afiliacion = (Afiliacion) request.getSession().getAttribute("afiliacion");
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

				//afiliacion.setCodCliente(usuario.getCodigoCic());
				//afiliacion.setTipoDocumento(Funciones.lpad(datosUsuario.getTipoDocumento(), "0", 3));
				//afiliacion.setNroDocumento(datosUsuario.getNroDocumento());
				
				try{
							
					elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
					
					if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS) ||
					   elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
						elementos = SolicitarServiciosTDC.verificarTipoElemento(param,usuario);
					}
					
					
				}catch(ArrayRuleException ar){
					log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
					ar.printStackTrace();
					ar.setForward("verMBTransferencia");
					throw ar;
				}
				
				//Código Nuevo para TDC
				
				MensajesTDC resultado = new MensajesTDC();
				Transferencia transf = null;
				
				if(param!= null ){
					if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
						resultado=SolicitarServiciosTDC.validarTDC(param ,coord,pinblock);
					} else if (elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS)) {
							resultado=SolicitarServiciosTDC.validarTokens(param ,coord,pinblock);
					} else if (elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {
						
						tokenSms.setCodCliente(usuario.getCodigoCic());
						tokenSms.setTipoDocumento(Funciones.lpad(tipoDoc,"0",3));
						tokenSms.setNroDocumento(numDoc);

						tokenSms.setTypeTrans(Constante.TIP_OPER_PAGO_PRESTAMO);
						tokenSms.setTypeOp("FIN");
						tokenSms.setAmount(prestamo.getMontoPrestamo());
						tokenSms.setTypeCurrency("PEN");
						tokenSms.setCodeSMS(pinblock);
						
						tokenSms.setCodCliDestinatario(prestamo.getTarjeta());
						tokenSms.setNomCliDestinatario(prestamo.getCliente());
						
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
					
					request.getSession().setAttribute(ConstanteSesion.PRESTAMO_SIM,prestamo);

					result=FacadeFactory.getPrestamoFacade().confirmarPrestamo( prestamo,request.getRemoteAddr(), usuario, request);
				
					if(elementos.getTipoElementoSeguridad().equals("5")){
						System.out.println("Token Fisico");
						usuario.getClaveDinamica().setTipoElementoSeguridad(elementos.getTipoElementoSeguridad());
						usuario.getClaveDinamica().setTipoElementoSeguridadFinal("Token Fisico");
						
					}else if(elementos.getTipoElementoSeguridad().equals("6")){
						System.out.println("Clave Dinamica Digital");
						usuario.getClaveDinamica().setTipoElementoSeguridad(elementos.getTipoElementoSeguridad());
						usuario.getClaveDinamica().setTipoElementoSeguridadFinal("Clave Dinamica Digital");
					}
					
					
					try {
				
						ServicioNotificacionProxy proxy = new ServicioNotificacionProxy();
						
						EnvioNotificacionRequest requestNotif = new EnvioNotificacionRequest();
						requestNotif.setCliente(ObjectUtil.convierteMayusculaPriLetra(usuario.getNombre().toLowerCase()).trim());
						requestNotif.setEmail(frm.getTxtCorreo().trim());
						requestNotif.setNroPrestamo(result.getNumPrestamo().trim());

						proxy.opEnviarNotificacionDoc(requestNotif);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				
			
				request.getSession().setAttribute(ConstanteSesion.PRESTAMO_SIM,result);
				
				Map map = UtilAction.cargarVar(request,result);
				OperacionImpl.setVariables(map);
				
				ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_PRESTAMOS, ConstanteSesion.PRESTAMO_SIM, request);
				
			} catch (ArrayRuleException ar) {
				//log3.error(ar,Constante.,"");
				ar.printStackTrace();
				ar.setForward("confirmarPrestamoRenov");
				throw ar;
			}
			
			
			return mapping.findForward("finalizarPrestamoRenov");
	}
	
	public ActionForward consultarEstadoSimulacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PrestamoForm frm = (PrestamoForm)form;
		PrestamoImpl prestamo = (PrestamoImpl)request.getSession().getAttribute(ConstanteSesion.PRESTAMO_SIM);
		Usuario usuario 		= (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		if(prestamo!=null){
			BigDecimal importeDif = new BigDecimal(301);
			BigDecimal importeMin = new BigDecimal(prestamo.getSaldoDeuda().replace(",", ""));
			BigDecimal total = importeDif.add(importeMin);
			frm.setTxtImporte(total.setScale(0,BigDecimal.ROUND_DOWN).toString());
		}
	
		frm.setRdnCuotaProtegida(Constante.PRESTAMO_CONST_FLAG_NO);
		frm.setRdnPeriodoGracia(Constante.PRESTAMO_CONST_FLAG_NO);
		frm.setCmbCuotas(Constante.PRESTAMO_CONST_CUOTA_DEFAULT);
		
		if(frm.getRdnSimular().equals("N")){
			
			PrestamoImpl simuladorDefault = new PrestamoImpl();
			simuladorDefault.setNroCuotas(Constante.PRESTAMO_CONST_CUOTA_DEFAULT);
			simuladorDefault.setFlagPeriodoGracia(Constante.PRESTAMO_CONST_FLAG_NO);
			simuladorDefault.setFlagCuotaProtegida(Constante.PRESTAMO_CONST_FLAG_NO);
			simuladorDefault.setFlagEndoso(Constante.PRESTAMO_CONST_FLAG_NO_MV);
			simuladorDefault.setTipoPrestamo(Constante.PRESTAMO_CONST_TIPO_PRESTAMO);
			simuladorDefault.setTipoOperacion(Constante.PRESTAMO_CONST_TIPO_OPERACION);
			simuladorDefault.setTipoEnvio(Constante.PRESTAMO_CONST_TIPO_ENVIO_VIRTUAL);
			simuladorDefault.setMontoSolicitado(Constante.PRESTAMO_CONST_MONTO_DEFAULT);
			List x=usuario.getCuentas();
			CuentaImpl cuenta = (CuentaImpl)x.get(0);
			
			simuladorDefault.setCuenta(cuenta.getNumeroProducto());
			simuladorDefault.setTarjeta(usuario.getTarjeta().getNumero());
				
			
			simuladorDefault=FacadeFactory.getPrestamoFacade().simularPrestamo( simuladorDefault, request.getRemoteAddr(), usuario, request);
			simuladorDefault.setCliente(usuario.getNombre());
				
			BigDecimal importeDif = new BigDecimal(301);
			BigDecimal importeMin = new BigDecimal(simuladorDefault.getSaldoDeuda().replace(",", ""));
			BigDecimal total = importeDif.add(importeMin);
			
			frm.setTxtImporte(total.setScale(0,BigDecimal.ROUND_DOWN).toString());
			simuladorDefault.setMontoMinimo(frm.getTxtImporte());
			frm.setRdnCuotaProtegida(Constante.PRESTAMO_CONST_FLAG_NO);
			frm.setRdnPeriodoGracia(Constante.PRESTAMO_CONST_FLAG_NO);
			frm.setCmbCuotas(Constante.PRESTAMO_CONST_CUOTA_DEFAULT);
			frm.setRdnSimular(frm.getRdnSimular());
			request.setAttribute("flagSim", frm.getRdnSimular());
			request.getSession().setAttribute(ConstanteSesion.PRESTAMO_SIM,simuladorDefault);
			
		}
		
		request.setAttribute("flagSim", frm.getRdnSimular());
		return mapping.findForward("consultarSimuladorRenov");
	}
	
	public ActionForward descargarDocumento(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		String formato = "";
		String desFormato="";
	
		try {
			formato = request.getParameter("formato");
												
			if(formato != null){
				
				String nombreFormato = formato.substring(0, 7);
			
				if(!nombreFormato.equals(Constante.PRESTAMO_CONST_FORMATO)){
					throw new ArrayRuleException(ConstanteError.GENERICO,"Formato a descargar no válido");
				}
				int tipoFormato = Integer.parseInt(formato.substring(7));
				
			   	switch(tipoFormato){
				case 1:desFormato=Constante.PRESTAMO_CONST_FORMATO1;break;
				case 2:desFormato=Constante.PRESTAMO_CONST_FORMATO2;break;
				case 3:desFormato=Constante.PRESTAMO_CONST_FORMATO3;break;
				default:throw new ArrayRuleException(ConstanteError.GENERICO,"Formato a descargar no válido");
					
			   	}
			   	
			   	
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition", "attachment;filename=\"" + desFormato+".pdf" + "\"");
				String ruta = "/Reportes/"+formato+".pdf";
				InputStream documento = getServlet().getServletConfig().getServletContext().getResourceAsStream(ruta);
				FacadeFactory.getPrestamoFacade().descargarDocumento(documento,response.getOutputStream());
			   	
			   	
			}else{
				throw new ArrayRuleException(ConstanteError.GENERICO,"Formato a descargar no válido");
			}

		
		
		} catch (ArrayRuleException e) {
			e.printStackTrace();
			e.setForward("confirmarPrestamoRenov");
			throw e;
		}
		
	
		
		

		
		
		return null;	
	}
	
	
	public ActionForward generarClaveSms(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		TokenSmsRequest tokenSms = new TokenSmsRequest();
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		
		
		//Afiliacion datosUsuario = new AfiliacionImpl();
		Afiliacion afiliacion = new AfiliacionImpl();
		PrestamoImpl prestamo = (PrestamoImpl)request.getSession().getAttribute(ConstanteSesion.PRESTAMO_SIM);

		try {

			
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

					tokenSms.setTypeTrans(Constante.TIP_OPER_PAGO_PRESTAMO);
					tokenSms.setTypeOp("FIN");
					tokenSms.setAmount(prestamo.getMontoPrestamo());
					tokenSms.setTypeCurrency("PEN");
					
					tokenSms.setCodCliDestinatario(prestamo.getTarjeta());
					tokenSms.setNomCliDestinatario(prestamo.getCliente());

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
			//loadMessage(request);
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
		
		
		//Afiliacion datosUsuario = new AfiliacionImpl();
		Afiliacion afiliacion = new AfiliacionImpl();
		PrestamoImpl prestamo = (PrestamoImpl)request.getSession().getAttribute(ConstanteSesion.PRESTAMO_SIM);

		try {

			
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

					tokenSms.setTypeTrans(Constante.TIP_OPER_PAGO_PRESTAMO);
					tokenSms.setTypeOp("FIN");
					tokenSms.setAmount(prestamo.getMontoPrestamo());
					tokenSms.setTypeCurrency("PEN");
					
					tokenSms.setCodCliDestinatario(prestamo.getTarjeta());
					tokenSms.setNomCliDestinatario(prestamo.getCliente());

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
			//loadMessage(request);
			e.setForward("verMBTransferencia");
			throw e;
		}
		return mapping.findForward("confirmarTelegiro");

	}
	
	public String NomTipoDocBenef(String tipodoc, String campoasoc) {
		int td = Integer.parseInt(tipodoc);
		List lista = null;
		if (tipodoc != null && campoasoc != null)
			lista = DAOFactory.getGeneraDAO().getComboDetalleHlpHijoMod(
					campoasoc, tipodoc);
		if (lista != null) {
			for (Iterator iter = lista.iterator(); iter.hasNext();) {
				ComboUtil element = (ComboUtil) iter.next();

				if (element.getCodigo().trim().equals(tipodoc + "")) {
					return element.getDescripcion();
				}
			}
		}

		return "";
	}

	@Override
	public ActionForward iniciar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Apéndice de método generado automáticamente
		
		PrestamoForm frm = (PrestamoForm)form;
		frm.setRdnSimular(Constante.PRESTAMO_CONST_FLAG_NO);
		frm.reset(mapping, request);
		
		return null;
	}


} 
