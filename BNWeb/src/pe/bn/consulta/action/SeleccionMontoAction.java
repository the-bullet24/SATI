package pe.bn.consulta.action;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pe.bn.cldinamica.action.SolicitarServiciosTDC;
import pe.bn.cldinamica.action.form.ElemenSeguridad;
import pe.bn.cldinamica.action.form.MensajesTDC;
import pe.bn.cldinamica.action.form.ParametrosElemSegTDC;
import pe.bn.cldinamica.action.form.ParametrosTDC;
import pe.bn.consulta.dao.impl.MontoImpl;
import pe.bn.consulta.domain.Monto;
import pe.bn.transferencia.action.TransferenciaMBAction;
import pe.bn.transferencia.domain.Transferencia;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.action.GrandActionAbstract;
import pe.cosapi.common.ArrayRuleException;
import pe.cosapi.common.BNAplicacion;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteError;
import pe.cosapi.common.ConstanteSesion;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.CuentaImpl;
import pe.cosapi.domain.impl.DetalleAyudaDatosImpl;

public class SeleccionMontoAction extends GrandActionAbstract {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7139501302887885957L;
	private static LoggerSati log3 = LoggerSati.getInstance(SeleccionMontoAction.class.getName());
	BNAplicacion aplicacion =BNAplicacion.getInstance();
	
	public ActionForward iniciar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Usuario usuario 		= (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		
		ParametrosElemSegTDC elementos = null;
		
		loadObject(request);
		
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
				ar.setForward("iniciarSeleccionMonto");
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
						
						request.setAttribute("flag", "0");
						
						throw new ArrayRuleException(ConstanteError.GENERICO,"PUEDE ACERCARSE A CUALQUIERA DE NUESTRAS OFICINAS A GESTIONAR SU TOKEN");
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
					ar.setForward("iniciarSeleccionMonto");
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
					throw ar;
				}
				request.getSession().setAttribute("resultCoord",resultCoord );
			}
			else{
				
				throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
			}
			

		return mapping.findForward("iniciarSeleccionMonto");
	}
	
	public ActionForward guardarMontoMaximo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		
		String transaccion = "SM00";
			
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		ParametrosElemSegTDC elementos = null;
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		ElemenSeguridad coord = (ElemenSeguridad)request.getSession().getAttribute("resultCoord");
		ElemenSeguridad resultCoord = null;
		String pinblock = request.getParameter("txtCoordenada");
		
		
		Monto seleccionMonto = new MontoImpl();
		
		try {
//			System.out.println("request.getParameter(cmbMontos).trim(): "+request.getParameter("cmbMontos").trim());
						
			elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
			
			if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS) ||
			   elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
				elementos = SolicitarServiciosTDC.verificarTipoElemento(param,usuario);
			}
			
			if(request.getParameter("cmbMontos").trim().equals("")){
				throw new ArrayRuleException(ConstanteError.GENERICO,"FAVOR DE SELECCIONAR UN MONTO");
			}
			
			seleccionMonto.setMontoMaximo(request.getParameter("cmbMontos").trim());
			
			seleccionMonto.setTipoElemento(elementos.getTipoElementoSeguridad());
			seleccionMonto.setCodigoElemento(elementos.getCodElementoSeguridad());
			
	
			
			List x=usuario.getCuentas();
			CuentaImpl cuenta = (CuentaImpl)x.get(0);
			seleccionMonto.setCuenta(cuenta);
			
			List lista = FacadeFactory.getGeneralFacade().getComboDetalleAyuda1(Constante.SEL_MONTO_CODIGO_BD );
			
			for(int z=0; z< lista.size();z++){
				
				DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
				
				dHelp_ = (DetalleAyudaDatosImpl)lista.get(z);
				
							
				if(seleccionMonto.getMontoMaximo().equals(dHelp_.getCodigoDetalleAyuda()))
				{
					
					seleccionMonto.setDesMontoMaximo(dHelp_.getDescripcionDetalle());
					
				}
				
			}
			
			
			try{
						
				elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
				
				if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS) ||
				   elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
					elementos = SolicitarServiciosTDC.verificarTipoElemento(param,usuario);
				}
				
				
			}catch(ArrayRuleException ar){
				log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
				ar.printStackTrace();
				ar.setForward("iniciarSeleccionMonto");
				throw ar;
			}
			
			
			MensajesTDC resultado = new MensajesTDC();
			Transferencia transf = null;
			
			if(param!= null && coord!=null ){
				
				try{
					
					if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC))
					{
				
						resultCoord = SolicitarServiciosTDC.solicitCoordenada(param);
						
						request.setAttribute("flag", "0");
						
						throw new ArrayRuleException(ConstanteError.GENERICO,"PUEDE ACERCARSE A CUALQUIERA DE NUESTRAS OFICINAS A GESTIONAR SU TOKEN");
					
					}
					else{
						if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS))
						{
						
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
				}catch (ArrayRuleException ar) {
					log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
					ar.printStackTrace();
					ar.setForward("iniciarSeleccionMonto");
					
					throw ar;
				}
			}
			
			seleccionMonto = FacadeFactory.getSeleccionMontoFacade().guardarMontoMaximo(transaccion,seleccionMonto, request.getRemoteAddr(), usuario, request);
			
			seleccionMonto.setFecha(pe.cosapi.common.ObjectUtil.getFechaActual());
			seleccionMonto.setHora(pe.cosapi.common.ObjectUtil.getHHMMSSformateado());
			
			request.getSession().setAttribute(ConstanteSesion.SELECCION_MONTO,seleccionMonto);
			
			request.getSession().setAttribute("montoMaximo",seleccionMonto.getDesMontoMaximo());
			
		} catch (ArrayRuleException e) {
			e.printStackTrace();
			e.setForward("iniciarSeleccionMonto");
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
			throw e;
			
		}
		
		forward = mapping.findForward("finSeleccionMonto");
		
		return forward;
		
	}
	
	private void loadObject(HttpServletRequest request)throws Exception {
		
		List montos = FacadeFactory.getGeneralFacade().getComboDetalleAyuda1(Constante.SEL_MONTO_CODIGO_BD);
	
		
		request.getSession().setAttribute("lstMontos",montos);
			
			
	}

}
