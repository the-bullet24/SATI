/*
 * Created on 19/08/2013
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
import pe.bn.pago.domain.DepositoJudicial;
import pe.bn.pago.domain.PagoCuponera;
import pe.bn.pago.domain.PagoFactura;
import pe.bn.pago.domain.PagoSedapal;
import pe.bn.pago.domain.impl.DepositoJudicialImpl;
import pe.bn.pago.domain.impl.PagoCuponeraImpl;
import pe.bn.pago.domain.impl.PagoSedapalImpl;
import pe.bn.pago.domain.impl.PagoTelefonoImpl;
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
public class DepositosJudicialesAction extends GrandActionAbstract {
	private static LoggerSati log3 = LoggerSati.getInstance(DepositosJudicialesAction.class.getName());

	public ActionForward iniciarConsignacion(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		
	try{
			
			Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
			FacadeFactory.getGeneralFacade().actualizarCuentas("LG02",usuario,request.getRemoteAddr());
			
			// Mensajistica para Depositos Judiciales
			BNAplicacion aplicacion = BNAplicacion.getInstance();
			request.getSession().setAttribute("mensajeConsulta",((Vector)aplicacion.getMensajePorCodigo("DJ00","00001")).elementAt(2).toString());
			request.getSession().setAttribute("mensajePantalla",((Vector)aplicacion.getMensajePorCodigo("DJ00","00002")).elementAt(2).toString());
		
				
		}catch(ArrayRuleException e){
			e.printStackTrace();
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			e.setForward("iniciarConsignacion");
			throw e;
		}
				
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);

		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		
		return mapping.findForward("iniciarConsignacion");
	}
	
	public ActionForward consultarConsignacion(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		DepositoJudicial dep = new DepositoJudicialImpl();
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		
		//Obtenemos la cuenta de origen
		
		String cmbCuenta 	= "";
		
		cmbCuenta = request.getParameter("cmbCuenta");
		
		//****************************************************
		
		
		List x=usuario.getCuentas();
		CuentaImpl cta=null;
		boolean flag= false;
		for(int i=0; i<x.size();i++){
			cta=(CuentaImpl)x.get(i);
		
			if(cta.getTipoProducto().equals("44")){
				flag=true;	
		//		System.out.println("ENCONTRO CUENTA PRESTAMOS");
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
		
		Cuenta cuenta 		= cta;
	
		request.getSession().setAttribute("cuenta",cuenta);
		
		//Obtenemos el número de expediente
		String expediente = request.getParameter("txtNumeroExpediente").trim();
		dep.setNumero(expediente);
		
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
				
				
			}catch(ArrayRuleException ar){
				log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
				ar.printStackTrace();
				ar.setForward("iniciarConsignacion");
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
					ar.setForward("iniciarConsignacion");
					throw ar;
				}
				request.getSession().setAttribute("resultCoord",resultCoord );
			}
			else{
				
				throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
			}
			
			
			try{
				
				dep=FacadeFactory.getPagoFacade().getConsultaConsignacion(Constante.DEP_JUDICIALES_CONSULTA,Constante.DEP_JUDICIALES_IND_CONSULTA_CONSIG, dep,  usuario,  request.getRemoteAddr());
				
				//Actualizando la consulta
				
				dep.setNombreDemandado1(dep.getNombreDemandado1()+dep.getNombreDemandado2());
				dep.setNombreDemandante1(dep.getNombreDemandante1()+dep.getNombreDemandante2());
				dep.setNombreCorte1(dep.getNombreCorte1()+dep.getNombreCorte2());
						
				
				request.getSession().setAttribute(ConstanteSesion.CONSULTA, dep);
				}catch(ArrayRuleException e){
					e.printStackTrace();
					log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
					e.setForward("iniciarConsignacion");
					throw e;
				}
	
		
			
			return mapping.findForward("mostrarConsignacion");
		}

	public ActionForward iniciarLiquidacion(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		try{
				
				Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
				
				
				// Mensajistica para Depositos Judiciales
				BNAplicacion aplicacion = BNAplicacion.getInstance();
				request.getSession().setAttribute("mensajeConsulta",((Vector)aplicacion.getMensajePorCodigo("DJ00","00003")).elementAt(2).toString());
				request.getSession().setAttribute("mensajePantalla",((Vector)aplicacion.getMensajePorCodigo("DJ00","00002")).elementAt(2).toString());
			
					
			}catch(ArrayRuleException e){
				e.printStackTrace();
				log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
				e.setForward("iniciarLiquidacion");
				throw e;
			}
					
			ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);

			request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
			
			return mapping.findForward("iniciarLiquidacion");
		}
	
	public ActionForward consultarLiquidacion(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		DepositoJudicial dep = new DepositoJudicialImpl();
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		
	
		
		//Obtenemos el número de orden de pago
		String expediente = request.getParameter("txtNumeroOrden").trim();
		dep.setNumero(expediente);
		
		try{
//			REALIZAR CONSULTA TIPO CLAVE DINAMICA  --> ADD M.D.B.
			ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
			request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
			ParametrosElemSegTDC elementos = null;
			request.getSession().setAttribute("tipoElemento",null );
			try{
			
				
				elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
				
				if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS) ||
				   elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
					elementos = SolicitarServiciosTDC.verificarTipoElemento(param,usuario);
				}
				
				
			}catch(ArrayRuleException ar){
				log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
				ar.printStackTrace();
				ar.setForward("iniciarLiquidacion");
				throw ar;
			}
			
//			REALIZAR CONSULTA DE COORDENADAS
			

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
					ar.setForward("iniciarLiquidacion");
					throw ar;
				}
				request.getSession().setAttribute("resultCoord",resultCoord );
			}
			else{
				
				throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
			}
			
			dep=FacadeFactory.getPagoFacade().getConsultaLiquidacion(Constante.DEP_JUDICIALES_CONSULTA,Constante.DEP_JUDICIALES_IND_CONSULTA_LIQUI, dep,  usuario,  request.getRemoteAddr());
			
			//Actualizando la consulta
			
			dep.setNombreBeneficiario1(dep.getNombreBeneficiario1()+dep.getNombreBeneficiario2());
			dep.setNumUnico(expediente);		
			
			request.getSession().setAttribute(ConstanteSesion.CONSULTA, dep);
			}catch(ArrayRuleException e){
				e.printStackTrace();
				log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
				e.setForward("iniciarLiquidacion");
				throw e;
			}
					
			ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);

			request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
			
			return mapping.findForward("mostrarLiquidacion");
		}
	
	
	
	@Override
	public ActionForward iniciar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Apéndice de método generado automáticamente
		return null;
	}
	
	
}
