
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
import pe.bn.cldinamica.action.SolicitarServiciosTDC;
import pe.bn.cldinamica.action.form.ElemenSeguridad;
import pe.bn.cldinamica.action.form.MensajesTDC;
import pe.bn.cldinamica.action.form.ParametrosElemSegTDC;
import pe.bn.cldinamica.action.form.ParametrosTDC;
import pe.bn.pago.domain.PagoPrestamoMultired;
import pe.bn.pago.domain.impl.PagoPrestamoMultiredImpl;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.action.GrandActionAbstract;
import pe.cosapi.action.UtilAction;
import pe.cosapi.common.ArrayRuleException;
import pe.cosapi.common.BNAplicacion;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteError;
import pe.cosapi.common.ConstanteSesion;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.SeguridadUtil;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.CuentaImpl;
import pe.cosapi.domain.impl.OperacionImpl;
import pe.cosapi.common.ObjectUtil;


public class PagoPrestamoMultiredAction extends GrandActionAbstract {
	BNAplicacion aplicacion =BNAplicacion.getInstance();
	private static LoggerSati log3 = LoggerSati.getInstance(PagoPrestamoMultiredAction.class.getName());

		
	
	public ActionForward iniciar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {		
		request.getSession().setAttribute(ConstanteSesion.PAGO_MULTIRED,null);
		request.getSession().setAttribute("TITULOS","PAGO DE CUOTA PRESTAMO MULTIRED");
		
		Usuario usuario = (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		//FacadeFactory.getGeneralFacade().actualizarCuentas("LG02",usuario,request.getRemoteAddr());
		
		CuentaImpl cuentaPrestamo = null;
		String cPres = "0";
		List lista = usuario.getCuentas();
		for (Iterator iter = lista.iterator(); iter.hasNext();) {
			cuentaPrestamo = (CuentaImpl) iter.next();
			if(cuentaPrestamo.getTipoProducto().equals(Constante.COD_CUENTA_PRESTAMOS)){
				cPres = "1";
			}
		}
		
		if (cPres.equals("0")){
			return mapping.findForward("inicioDefault");
		}
		
		String transaccion = "PP00";
		
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		
		if(!getVerificarHorario("3024")){
			request.getSession().setAttribute("mensaje1Hor",((Vector)aplicacion.getMensajePorCodigo("MH01","MH001")).elementAt(2).toString());
			request.getSession().setAttribute("mensaje2Hor",((Vector)aplicacion.getMensajePorCodigo("MH01","MH002")).elementAt(2).toString());
			request.getSession().setAttribute("mensajeHorC",((Vector)aplicacion.getMensajePorCodigo("MH01","3024"==null?"0112":"3024")).elementAt(2).toString());
			
			
			
			return mapping.findForward("transaccion.noDisponible");
		}
		
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
		
		request.getSession().setAttribute("mensajeAfiliacion",((Vector)aplicacion.getMensajePorCodigo("PP00","AF001")).elementAt(2).toString());
		request.getSession().setAttribute("mensajePantalla",((Vector)aplicacion.getMensajePorCodigo("PP00","00001")).elementAt(2).toString());
		request.getSession().setAttribute("mensajeLimite",((Vector)aplicacion.getMensajePorCodigo("PP00","00002")).elementAt(2).toString());
		request.getSession().setAttribute("mensajeMonto",((Vector)aplicacion.getMensajePorCodigo("PP00","00004")).elementAt(2).toString());//PagoPrestamo
		request.getSession().setAttribute("mensajePrestamo",((Vector)aplicacion.getMensajePorCodigo("PP00","00008")).elementAt(2).toString());
		request.getSession().setAttribute("mensajePrestamo2",((Vector)aplicacion.getMensajePorCodigo("PP00","00009")).elementAt(2).toString());
		request.getSession().setAttribute("mensajePrestamoCuotaVenc",((Vector)aplicacion.getMensajePorCodigo("PP00","00010")).elementAt(2).toString());
		request.getSession().setAttribute("mensajePrestamoITF",((Vector)aplicacion.getMensajePorCodigo("PP00","00011")).elementAt(2).toString());
		
		request.getSession().setAttribute("mensajeIntereses",((Vector)aplicacion.getMensajePorCodigo("PP00","00013")).elementAt(2).toString());//ConsultaPrestamo
		request.getSession().setAttribute("mensajeViñeta",((Vector)aplicacion.getMensajePorCodigo("PP00","00014")).elementAt(2).toString());//PagoPrestamo
		request.getSession().setAttribute("mensajeRetencion",((Vector)aplicacion.getMensajePorCodigo("PP00","00005")).elementAt(2).toString());//ConsultaPrestamo
		request.getSession().setAttribute("mensajeRetencion1",((Vector)aplicacion.getMensajePorCodigo("PP00","00006")).elementAt(2).toString());
		request.getSession().setAttribute("mensajeRetencion2",((Vector)aplicacion.getMensajePorCodigo("PP00","00007")).elementAt(2).toString());
		request.getSession().setAttribute("mensajePrestamo3",((Vector)aplicacion.getMensajePorCodigo("PP00","00015")).elementAt(2).toString());
		
		FacadeFactory.getGeneralFacade().actualizarCuentas("LG02",usuario,request.getRemoteAddr());
		
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);

		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		
		return mapping.findForward("iniciarPago");
		
	}

	public ActionForward nuevoPago(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {		
		request.getSession().setAttribute(ConstanteSesion.PAGO_MULTIRED,null);
		request.getSession().setAttribute("TITULOS","PAGO PRESTAMO MULTIRED");
		Usuario usuario = (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		CuentaImpl cuentaPrestamo = null;
		

		String cNroCta_Des = request.getParameter("rbCuenta").trim();
		
		String[] id;
		id = ObjectUtil.getArrayStrings(cNroCta_Des,"-");
		
		String cNroCtaSel = id[0];
		String cNroDesSel = id[1];
		
		String cPres = "0";
		List lista = usuario.getCuentas();
	
		for (Iterator iter = lista.iterator(); iter.hasNext();) {
			cuentaPrestamo = (CuentaImpl) iter.next();
			if(cuentaPrestamo.getTipoProducto().equals(Constante.COD_CUENTA_PRESTAMOS)){
				cPres = "1";
			}
		}
		
		if (cPres.equals("0")){
			return mapping.findForward("inicioDefault");
		}
		
		String transaccion = "PP00";
		if(!getVerificarHorario("3024")){
			return mapping.findForward("transaccion.noDisponible");
		}
		
		
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		request.getSession().setAttribute("mensajeAfiliacion",((Vector)aplicacion.getMensajePorCodigo("PP00","AF001")).elementAt(2).toString());
		request.getSession().setAttribute("mensajePantalla",((Vector)aplicacion.getMensajePorCodigo("PP00","00001")).elementAt(2).toString());
		request.getSession().setAttribute("mensajeLimite",((Vector)aplicacion.getMensajePorCodigo("PP00","00002")).elementAt(2).toString());
		request.getSession().setAttribute("mensajeMonto",((Vector)aplicacion.getMensajePorCodigo("PP00","00004")).elementAt(2).toString());
		
		request.getSession().setAttribute("mensajeCuotaRetenida",((Vector)aplicacion.getMensajePorCodigo("PP00","00012")).elementAt(2).toString());
		
		
		request.getSession().setAttribute("mensajeRetencion",((Vector)aplicacion.getMensajePorCodigo("PP00","00005")).elementAt(2).toString());
		request.getSession().setAttribute("mensajeRetencion1",((Vector)aplicacion.getMensajePorCodigo("PP00","00006")).elementAt(2).toString());
		request.getSession().setAttribute("mensajeRetencion2",((Vector)aplicacion.getMensajePorCodigo("PP00","00007")).elementAt(2).toString());
		request.getSession().setAttribute("mensajeIntereses",((Vector)aplicacion.getMensajePorCodigo("PP00","00013")).elementAt(2).toString());
		
			
		PagoPrestamoMultired pagoPrestamoMultired = FacadeFactory.getPagoFacade().getPagoPrestamoMultired(cNroCtaSel, cNroDesSel, transaccion,usuario,request.getRemoteAddr());
		request.getSession().setAttribute(ConstanteSesion.PAGO_MULTIRED,pagoPrestamoMultired);
		
		
//		REALIZAR CONSULTA TIPO CLAVE DINAMICA  --> ADD M.D.B.
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
				else{
					if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS))
					{
						
						resultCoord=SolicitarServiciosTDC.solicitarTokens(param);
					}
					
				}
					
				
			}catch(ArrayRuleException ar){
				log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
				FacadeFactory.getGeneralFacade().actualizarCuentas("LG02",usuario,request.getRemoteAddr());
				ar.printStackTrace();
				ar.setForward("iniciarPago");
				throw ar;
			}
			request.getSession().setAttribute("resultCoord",resultCoord );
		}
		else{
			
			throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
		}

		
		
		return mapping.findForward("siguientePago");
	
				
	}

	public ActionForward pagar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=new ActionForward();
		Usuario usuario = (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		String transaccion	= request.getParameter("transaccion");
		boolean pagoTotal	= new Boolean(request.getParameter("optImporte")).booleanValue();
		request.getSession().setAttribute("mensajeMonto",request.getSession().getAttribute("mensajeMonto"));
		PagoPrestamoMultiredImpl pagoPrestamoMultired = (PagoPrestamoMultiredImpl)request.getSession().getAttribute(ConstanteSesion.PAGO_MULTIRED);
		pagoPrestamoMultired.setEsPagoTotal(pagoTotal);
		
		String nrocuo= pagoPrestamoMultired.getNroCuota();
		String importecuo= pagoPrestamoMultired.getImporte();
		String nrodesemb= pagoPrestamoMultired.getNroDesembolso();///////// new
		
		if(pagoTotal){		
			pagoPrestamoMultired.setNroCuota("99");
			pagoPrestamoMultired.setImporte(ObjectUtil.deFormatearMonto(pagoPrestamoMultired.getDeuda()));
		}
		
		request.getSession().setAttribute("fecpago",pe.cosapi.common.ObjectUtil.getFechaActual());
		request.getSession().setAttribute("horpago",pe.cosapi.common.ObjectUtil.getHHMMSSformateado());
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		
		try{
			
			//String clave 		= SeguridadUtil.getClaveDesencriptada(request.getParameter("txtNumClave"),request);
			//FacadeFactory.getGeneralFacade().processValidationCardAhorro(usuario.getTarjeta().getNumero(),usuario.getTarjeta().getClave(),clave,clave);
			
			
//			REALIZAR CONSULTA TIPO CLAVE DINAMICA  --> ADD M.D.B.
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
				ar.setForward("iniciarPago");
				throw ar;
			}
			
			
				MensajesTDC resultado = new MensajesTDC();
				ElemenSeguridad resultCoord = null;
				ElemenSeguridad coord = (ElemenSeguridad)request.getSession().getAttribute("resultCoord");
				
				String pinblock = request.getParameter("txtCoordenada");
			
			if(param!= null && coord!=null ){
				
				
			
				try{
					
					if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC))
					{
				
						resultado=SolicitarServiciosTDC.validarTDC(param ,coord,pinblock);
					
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
					
					Map map = UtilAction.cargarVar(request,request.getSession().getAttribute(ConstanteSesion.PAGO_MULTIRED));
					OperacionImpl.setVariables(map);
					FacadeFactory.getPagoFacade().pagarPrestamoMultired(transaccion,usuario,request.getRemoteAddr(),(PagoPrestamoMultired)pagoPrestamoMultired);
					
					
									
				}
				catch (ArrayRuleException e) {
					log3.error(e,Constante.ERR_ERROR_VALIDAR_TDC,"");
					e.printStackTrace();
					pagoPrestamoMultired.setNroCuota(nrocuo);
					pagoPrestamoMultired.setImporte(new BigDecimal(ObjectUtil.replaceChar(importecuo,',',"")));
					//pagoPrestamoMultired.setNroDesembolso(nrodesemb);//////// new
					BNAplicacion aplicacion = BNAplicacion.getInstance();
					request.setAttribute("mensajeAfiliacion",((Vector)aplicacion.getMensajePorCodigo("PP00","AF001")).elementAt(2).toString());
					request.setAttribute("mensajePantalla",((Vector)aplicacion.getMensajePorCodigo("PP00","00001")).elementAt(2).toString());
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
					e.setForward("siguientePago");
					throw e;
				}
			}
			
		

			forward = mapping.findForward("confirmacionPago");
		}	
		
		catch(ArrayRuleException ar){
			log3.error(ar,Constante.ERR_LOGICA_NEGOCIO,"");
				
			pagoPrestamoMultired.setNroCuota(nrocuo);
			pagoPrestamoMultired.setImporte(new BigDecimal(ObjectUtil.replaceChar(importecuo,',',"")));
			//pagoPrestamoMultired.setNroDesembolso(nrodesemb);//////// new
			BNAplicacion aplicacion = BNAplicacion.getInstance();
			request.setAttribute("mensajeAfiliacion",((Vector)aplicacion.getMensajePorCodigo("PP00","AF001")).elementAt(2).toString());
			request.setAttribute("mensajePantalla",((Vector)aplicacion.getMensajePorCodigo("PP00","00001")).elementAt(2).toString());
			forward = mapping.findForward("siguientePago");

			throw ar;
			
			
		}
		
		BigDecimal nDeuda = new BigDecimal(ObjectUtil.replaceChar(pagoPrestamoMultired.getDeuda().toString(),',',""));
		nDeuda = nDeuda.add(new BigDecimal(ObjectUtil.replaceChar(pagoPrestamoMultired.getItf().toString(),',',"")));
		pagoPrestamoMultired.setDeuda(nDeuda);
	
		////
		
		request.getSession().setAttribute(ConstanteSesion.PAGO_MULTIRED,pagoPrestamoMultired);
		return forward;
		

		
	}

}

	