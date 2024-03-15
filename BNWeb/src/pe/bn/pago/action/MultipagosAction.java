/*
 * Created on 15/11/2013
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pe.bn.pago.action;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.util.FileCopyUtils;

import pe.bn.afiliacion.action.DesafCableAction;
import pe.bn.afiliacion.domain.Afiliacion;
import pe.bn.afiliacion.domain.impl.AfiliacionImpl;
import pe.bn.cldinamica.action.SolicitarServiciosTDC;
import pe.bn.cldinamica.action.form.ElemenSeguridad;
import pe.bn.cldinamica.action.form.MensajesTDC;
import pe.bn.cldinamica.action.form.ParametrosElemSegTDC;
import pe.bn.cldinamica.action.form.ParametrosTDC;
import pe.bn.login.domain.impl.ImagenTarjetaImpl;
import pe.bn.pago.domain.DepositoJudicial;
import pe.bn.pago.domain.Multipago;
import pe.bn.pago.domain.PagoCuponera;
import pe.bn.pago.domain.PagoFactura;
import pe.bn.pago.domain.PagoSedapal;
import pe.bn.pago.domain.impl.DepositoJudicialImpl;
import pe.bn.pago.domain.impl.MultipagoImpl;
import pe.bn.pago.domain.impl.PagoCuponeraImpl;
import pe.bn.pago.domain.impl.PagoSedapalImpl;
import pe.bn.pago.domain.impl.PagoTelefonoImpl;
import pe.bn.tcredito.domain.Formato;
import pe.bn.transferencia.domain.Transferencia;
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
import pe.cosapi.domain.DetalleAyudaDatos;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.CuentaImpl;
import pe.cosapi.domain.impl.DetalleAyudaDatosImpl;
import pe.cosapi.domain.impl.OperacionImpl;
import pe.cosapi.domain.impl.ReciboImpl;
/**
 * @author Mily Dolores Bustamante
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MultipagosAction extends GrandActionAbstract {
	private static LoggerSati log3 = LoggerSati.getInstance(MultipagosAction.class.getName());
	BNAplicacion aplicacion =BNAplicacion.getInstance();

	public ActionForward iniciar(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		
	try{
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
			
			// Mensajistica para Multipagos
			BNAplicacion aplicacion = BNAplicacion.getInstance();
			request.getSession().setAttribute("mensajeConsulta",((Vector)aplicacion.getMensajePorCodigo("MP00","00001")).elementAt(2).toString());
			request.getSession().setAttribute("mensajePantalla",((Vector)aplicacion.getMensajePorCodigo("MP00","00002")).elementAt(2).toString());
			loadObject(request);
				
		}catch(ArrayRuleException e){
			e.printStackTrace();
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			e.setForward("iniciarMultipago");
			throw e;
		}
				
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);

		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		
		return mapping.findForward("iniciarMultipago");
	}
	
	public ActionForward consultarMultipagos(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		Multipago consulta = new MultipagoImpl();
		String cuenta ="";
		cuenta =request.getParameter("cmbCuenta");
		
		FacadeFactory.getGeneralFacade().actualizarCuentas("LG02",usuario,request.getRemoteAddr());
		
		//Obtener valores del formulario
		
		
		consulta.setNumUnico(request.getParameter("txtNumero").trim());
		
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
			//request.setAttribute("cuenta",cta);
			request.getSession().setAttribute("cuenta",cta);
//			System.out.println("cta.getTipoProducto():"+cta.getTipoProducto());
//			System.out.println("cta.getNumeroProducto():"+cta.getNumeroProducto());
		}else{
			request.setAttribute("cuenta", (Cuenta)request.getSession().getAttribute("cuenta"));
			
		}
			
	



		//Invocar Clave Dinamica
		
	
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
				ar.setForward("iniciarMultipago");
				throw ar;
			}
			
		// Consultar Coordenada o Token
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
					log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
					ar.printStackTrace();
					ar.setForward("iniciarMultipago");
					throw ar;
				}
				request.getSession().setAttribute("resultCoord",resultCoord );
			}
			else{
				
				throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
			}
			
			
			try{
				//Realizar Consulta de Multipagos				
			
				consulta= FacadeFactory.getPagoFacade().getConsultaMultipago(Constante.MULTIPAGOS_CONSULTA, consulta,  usuario,  request.getRemoteAddr());
				
			
				
				
				List estado = FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.MULTIPAGOS_ESTADO );
				
				for(int y=0; y< estado.size();y++){
					
					DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
					
					dHelp_ = (DetalleAyudaDatosImpl)estado.get(y);
			
								
					if(consulta.getCodEstado().equals(dHelp_.getCodigoDetalleAyuda()))
					{
						
						consulta.setDesEstado(dHelp_.getDescripcionDetalle());
						
					}
					
				}
				
				List moneda = FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.MULTIPAGOS_MONEDA );
				
				for(int z=0; z< moneda.size();z++){
					
					DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
					
					dHelp_ = (DetalleAyudaDatosImpl)moneda.get(z);
								
					if(consulta.getCodMoneda().equals(dHelp_.getCodigoDetalleAyuda()))
					{
						
						consulta.setDescMoneda(dHelp_.getDescripcionDetalle());
						
					}
					
				}
				
				request.getSession().setAttribute(ConstanteSesion.MULTIPAGO,consulta );
				
				//
//				System.out.println(consulta.getRegistro().size());
				
				List detalle = consulta.getRegistro();
				
				List nuevoDetalle = new ArrayList();
				
				List entidad = FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.MULTIPAGOS_ENTIDADES );
				
				for (int i = 0; i < detalle.size(); i++) {
					
					
					Multipago multipago = new MultipagoImpl(); 
					
					multipago = (MultipagoImpl)detalle.get(i);
					
					for(int z=0; z< entidad.size();z++){
						
						DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
						
						dHelp_ = (DetalleAyudaDatosImpl)entidad.get(z);
									
						if(multipago.getTxtEntidad().equals(dHelp_.getCodigoDetalleAyuda()))
						{
							
							multipago.setTxtEntidad(dHelp_.getDescripcionDetalle());
							
						}
						
					}
					
					nuevoDetalle.add(multipago);
					
				}
				request.getSession().setAttribute("detallePagos",nuevoDetalle );
				
				
			}catch(ArrayRuleException e){
					e.printStackTrace();
					loadObject(request);
					log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
					e.setForward("iniciarMultipago");
					throw e;
				}
	
		
			//request.getSession().setAttribute(ConstanteSesion.CONSULTA, consulta);
			return mapping.findForward("consultaMultipago");
}

	
public ActionForward pagarMultipagos(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		System.out.println("pagarMultipagos...");
	
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		CuentaImpl cta = (CuentaImpl) request.getSession().getAttribute("cuenta");
		
		MultipagoImpl consulta 	= (MultipagoImpl)request.getSession().getAttribute(ConstanteSesion.MULTIPAGO);
		
		consulta.setCuenta(cta);
		
		request.getSession().setAttribute(ConstanteSesion.MULTIPAGO,consulta);
		
		//String txtMonto = ObjectUtil.replaceChar(request.getParameter("txtMonto").trim(), ',', "");
		//consulta.setImporte(new BigDecimal(txtMonto));
		
		try{
			
				
				//REALIZAR CONSULTA TIPO CLAVE DINAMICA 
				ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
				request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
				ElemenSeguridad coord = (ElemenSeguridad)request.getSession().getAttribute("resultCoord");
		
			
				ElemenSeguridad resultCoord = null;
				String pinblock = request.getParameter("txtCoordenada");
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
					ar.setForward("consultaMultipago");
					throw ar;
				}
				
				
				
				// Código Nuevo para TDC
			
				MensajesTDC resultado = new MensajesTDC();
				Transferencia transf = null;
				
				if(param!= null && coord!=null ){
					
					
				
					try{
						
//						if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC))
//						{
//					
//							resultado=SolicitarServiciosTDC.validarTDC(param ,coord,pinblock);
//						
//						}
//						else{
//							if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS))
//							{
//							
//								resultado=SolicitarServiciosTDC.validarTokens(param ,coord,pinblock);
//							}
//							
//						}
//						
//						if(!resultado.getCodRptaPrincipal().equals(Constante.CODIGO_RESULTADO_OK) || !resultado.getCodResultOper().equals(Constante.CODIGO_OPERACION_OK)){
//							String codErrEnt="";
//							String mensaje="";
//							
//							if(!resultado.getCodRptaPrincipal().equals(Constante.CODIGO_RESULTADO_OK))	{
//								 	codErrEnt = ObjectUtil.setearCaracterLeft(resultado.getCodRptaPrincipal().toString(), '0', 4-resultado.getCodRptaPrincipal().toString().length());
//								 	mensaje=aplicacion.getMsjesHost("CD",codErrEnt).elementAt(2).toString();
//							}	
//							else{
//								if(!resultado.getCodResultOper().equals(Constante.CODIGO_OPERACION_OK)){
//									 codErrEnt = ObjectUtil.setearCaracterLeft(resultado.getCodRptaPrincipalOper().toString(), '0', 4-resultado.getCodRptaPrincipalOper().toString().length());
//									 mensaje=aplicacion.getMsjesHost("CD",codErrEnt).elementAt(2).toString();
//								}
//							}
//							
//							
//							throw new ArrayRuleException(ConstanteError.GENERICO,mensaje+" (CD-"+codErrEnt+")");
//							
//						}
												
						//request.getSession().setAttribute(ConstanteSesion.MULTIPAGO, FacadeFactory.getPagoFacade().getPagoMultipago(Constante.MULTIPAGOS_PAGO_TXN, consulta,  usuario,  request.getRemoteAddr()));
					}catch (ArrayRuleException e) {
						log3.error(e,Constante.ERR_ERROR_VALIDAR_TDC,"");
						e.printStackTrace();
						
						request.getSession().setAttribute(ConstanteSesion.CONSULTA,consulta);
						
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
						e.setForward("consultaMultipago");
						throw e;
					}
				}
				
			}catch(ArrayRuleException e){
					e.printStackTrace();
					loadObject(request);
					log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
					e.setForward("consultaMultipago");
					throw e;
			}
	
		return mapping.findForward("finMultipago");	
		
}
public ActionForward probarSQL(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
	List listaTarjeta = null;
	Usuario usuario 		= (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
	String tarjeta = request.getParameter("txtPrueba");
	
	listaTarjeta = FacadeFactory.getLoginFacade().getValidaImagenMV(tarjeta);
	
	for (int i = 0; i < listaTarjeta.size(); i++) {
		
		
		ImagenTarjetaImpl result = (ImagenTarjetaImpl)listaTarjeta.get(i);
		
//		System.out.println("result"+result);
		
	}
	
	request.setAttribute("resultado",listaTarjeta);
	return mapping.findForward("resultadoSQL");	
}

public ActionForward descargarEstadoCuenta(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
	
	
	try {
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=\"" + "documento.pdf" + "\"");
		//FacadeFactory.getTarjetaCreditoFacade().encontrarFormato2(response.getOutputStream());
	
	} catch (ArrayRuleException e) {
		e.printStackTrace();
		loadObject(request);
		e.setForward("consultaMultipago");
		throw e;
	}
	
	
	return null;	
}

public ActionForward descargarftpEstadoCuenta(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
	Usuario usuario1 		= (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
	
	try {
		
		ResourceBundle rb = ResourceBundle.getBundle("parametro");
		String usuario = rb.getString("bn.ftp.usuario.des");
		String clave = rb.getString("bn.ftp.clave.des");
		String servidor = rb.getString("bn.ftp.servidor.des");
		String remotePath = rb.getString("bn.ftp.remotePath.des");
		
		remotePath = remotePath + usuario1.getTarjeta().getNumero()+".pdf";
		
//		System.out.println("remotePath:"+remotePath);
		
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=\"" + "ejemplo.pdf" + "\"");
		
		FacadeFactory.getTarjetaCreditoFacade().encontrarEstadoFtp(usuario,clave,servidor,remotePath,response.getOutputStream());
	
	} catch (ArrayRuleException e) {
		e.printStackTrace();
		loadObject(request);
		e.setForward("consultaMultipago");
		throw e;
	}
	
	
	return null;	
}



private void loadObject(HttpServletRequest request)throws Exception {
		
		
		//request.setAttribute("lstServicio",FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.MULTIPAGOS_LISTA_SERVICIOS));
		
		
	}
	
}
