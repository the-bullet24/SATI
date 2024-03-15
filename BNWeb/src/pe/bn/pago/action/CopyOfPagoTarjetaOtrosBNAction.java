
package pe.bn.pago.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
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
import pe.bn.notificaciones.action.ServiciosNotificacionPrincipal;
import pe.bn.notificaciones.model.ParametrosSIAT;
import pe.bn.pago.domain.PagoTarjeta;
import pe.bn.pago.domain.impl.PagoTarjetaImpl;
import pe.bn.tcredito.domain.Pago;
import pe.bn.tcredito.domain.impl.PagoImpl;
import pe.bn.telegiro.domain.impl.TelegiroImpl;
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
import pe.cosapi.domain.impl.MovimientoImpl;
import pe.cosapi.domain.impl.OperacionImpl;
import pe.cosapi.dto.PersonaDTO;


public class CopyOfPagoTarjetaOtrosBNAction extends GrandActionAbstract {
	BNAplicacion aplicacion =BNAplicacion.getInstance();
	private static LoggerSati log3 = LoggerSati.getInstance(CopyOfPagoTarjetaOtrosBNAction.class.getName());


	public ActionForward iniciar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {		
	
		Usuario usuario = (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		request.getSession().setAttribute("mensajeTarjetaCredito",((Vector)aplicacion.getMensajePorCodigo("CL01","TC005")).elementAt(2).toString());
		
		if(!getVerificarHorario("TC00")){
			
			request.getSession().setAttribute("mensaje1Hor",((Vector)aplicacion.getMensajePorCodigo("AD01","00001")).elementAt(2).toString());
			request.getSession().setAttribute("mensaje2Hor",((Vector)aplicacion.getMensajePorCodigo("AD01","00002")).elementAt(2).toString());
			request.getSession().setAttribute("mensajeHorC",((Vector)aplicacion.getMensajePorCodigo("MH01","TC00")).elementAt(2).toString());
			return mapping.findForward("transaccion.noDisponible");
		}
		
		
		request.getSession().setAttribute(ConstanteSesion.USUARIO_EN_SESION,usuario);
			
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
			
		ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
				
		request.getSession().setAttribute("mensajeArriba",((Vector)aplicacion.getMensajePorCodigo("CL01","TC006")).elementAt(2).toString());
		request.getSession().setAttribute("mensajeCondiciones",((Vector)aplicacion.getMensajePorCodigo("CL01","TC008")).elementAt(2).toString());
			
		
		return mapping.findForward("inicioPagoTCredito");
	
	}
	
	
	
	public ActionForward confirmaPagoTCredito(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
	
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		String tipoImporte =(String) request.getParameter("tipoImporte");
		String numTarjeta =(String) request.getParameter("txtNroTarjeta");
		CuentaImpl consulta = new CuentaImpl();
		String cmbCuenta 	= "";
		cmbCuenta = request.getParameter("cmbCuenta");
		String tipoConsulta = Constante.TARJETA_CREDITO_CONSULTA_SALDO_VALOR_TRAMA;
		String transaccion = Constante.TARJETA_CREDITO_CONSULTA_SALDO;
		
		//Obteniendo las cuentas del cliente
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

		consulta =FacadeFactory.getTarjetaCreditoFacade().consultar(transaccion,tipoConsulta,numTarjeta, request.getRemoteAddr(), usuario, request);
		consulta.setFecha(pe.cosapi.common.ObjectUtil.getFechaActual());
		consulta.setHora(pe.cosapi.common.ObjectUtil.getHHMMSSformateado());	
			
				
		Cuenta cuenta 		= cta;
		String importePagado = request.getParameter("importeNuevo").replace(",", "");
		PagoImpl pago = null;
		pago = new PagoImpl();
		
		pago.setCuentaCargada(cuenta);
		pago.setCuentaCredito(consulta);
		pago.setMontoPagado(new BigDecimal(importePagado));
		pago.setNroCuentaAhorros(usuario.getTarjeta().getNumero());
		pago.setDatos(consulta);
		request.getSession().setAttribute("tipoImporte", tipoImporte);
		request.getSession().setAttribute(ConstanteSesion.PAGO_TC,pago);
		
		ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
		
//		REALIZAR CONSULTA TIPO CLAVE DINAMICA  --> ADD M.D.B.
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		ParametrosElemSegTDC elementos = null;
		//request.getSession().setAttribute("tipoElemento",null );
		try{
		
			request.getSession().setAttribute("tipoImporte",tipoImporte );
			//elementos = SolicitarServiciosTDC.verificarTipoElemento(param, usuario);
			elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
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
		if(transaccion.equals(Constante.TARJETA_CREDITO_PAGO_TC)) tipoConsulta = Constante.TARJETA_CREDITO_PAGO_TC_VALOR_TRAMA;
		
		PagoImpl pago = (PagoImpl) request.getSession().getAttribute(ConstanteSesion.PAGO_TC);
		
		ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
		
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		
		ElemenSeguridad coord = (ElemenSeguridad)request.getSession().getAttribute("resultCoord");
	
		
		ElemenSeguridad resultCoord = null;
		//String pinblock = request.getParameter("txtCoordenada");
		String pinblock = ObjectUtil.getClaveDesencriptada(request.getParameter("txtCoordenada"),request);
		ParametrosElemSegTDC elementos = null;
		try{
			Map map = UtilAction.cargarVar(request,request.getSession().getAttribute(ConstanteSesion.PAGO_TC));
			OperacionImpl.setVariables(map);			
			
			//REALIZAR CONSULTA TIPO CLAVE DINAMICA  --> ADD M.D.B.
			
			try{
				elementos = SolicitarServiciosTDC.verificarTipoElemento(param, usuario);
				request.getSession().setAttribute("tipoElemento",elementos );
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
					}
					else{
						if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS)){						
							resultado=SolicitarServiciosTDC.validarTokens(param ,coord,pinblock);
						}						
					}
					
					if(!resultado.getCodRptaPrincipal().equals(Constante.CODIGO_RESULTADO_OK) || !resultado.getCodResultOper().equals(Constante.CODIGO_OPERACION_OK)){
						String codErrEnt="";
						String mensaje="";
						
						if(!resultado.getCodRptaPrincipal().equals(Constante.CODIGO_RESULTADO_OK))	{
							 	codErrEnt = ObjectUtil.setearCaracterLeft(resultado.getCodRptaPrincipal().toString(), '0', 4-resultado.getCodRptaPrincipal().toString().length());
							 	mensaje=aplicacion.getMsjesHost("CD",codErrEnt).elementAt(2).toString();
						}	
						else{
							if(!resultado.getCodResultOper().equals(Constante.CODIGO_OPERACION_OK)){
								 codErrEnt = ObjectUtil.setearCaracterLeft(resultado.getCodRptaPrincipalOper().toString(), '0', 4-resultado.getCodRptaPrincipalOper().toString().length());
								 mensaje=aplicacion.getMsjesHost("CD",codErrEnt).elementAt(2).toString();
							}
						}
												
						throw new ArrayRuleException(ConstanteError.GENERICO,mensaje+" (CD-"+codErrEnt+")");						
					}
					String tipo = Constante.TC_TIPO_SERVICIO_TERCEROS;
					
					pg =FacadeFactory.getTarjetaCreditoFacade().pagarTCredito(transaccion, tipoConsulta, pago, request.getRemoteAddr(), usuario, tipo,request);
					
					if(usuario.getNotificacion().getEstadoNotificacion().equals("1")){
						 ServiciosNotificacionPrincipal.enviarNotificacion(
								 usuario,
								 usuario.getNombre(), 
								 pg.getCuentaCargada().getSimboloMonedaProducto(), 
								 pago.getMontoPagado(),
								 usuario.getTipoCambio().getVenta() ,
								 Constante.NOTI_PAGO_TC_BN , 
								 usuario.getNotificacion());
					 }
					
									
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
			
			request.getSession().setAttribute(ConstanteSesion.PAGO_TC,pg);			
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
}