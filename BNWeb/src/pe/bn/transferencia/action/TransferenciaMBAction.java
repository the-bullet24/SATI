/*
 * Fecha 07/08/2007
 * Creado por Wilder A. Hernandez Nuñez
 */
package pe.bn.transferencia.action;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pe.bn.afiliacion.domain.Afiliacion;
import pe.bn.afiliacion.domain.impl.AfiliacionImpl;
import pe.bn.antiphishing.action.ServiciosAntiphishing;
import pe.bn.cldinamica.action.SolicitarServiciosTDC;
import pe.bn.cldinamica.action.form.ElemenSeguridad;
import pe.bn.cldinamica.action.form.MensajesTDC;
import pe.bn.cldinamica.action.form.ParametrosElemSegTDC;
import pe.bn.cldinamica.action.form.ParametrosTDC;
import pe.bn.listener.Util;
import pe.bn.notificaciones.action.ServicioNotificacionConstancia;
import pe.bn.notificaciones.action.ServiciosNotificacionPrincipal;
import pe.bn.notificaciones.model.ParametrosSIAT;
import pe.bn.transferencia.domain.Transferencia;
import pe.bn.transferencia.domain.impl.TransferenciaImpl;
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
import pe.cosapi.domain.TipoCambio;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.CuentaImpl;
import pe.cosapi.domain.impl.OperacionImpl;

public class TransferenciaMBAction extends GrandActionAbstract {
	BNAplicacion aplicacion =BNAplicacion.getInstance();
	
	private static LoggerSati log3 = LoggerSati.getInstance(TransferenciaMBAction.class.getName());
    /* (sin Javadoc)
     * @see pe.cosapi.action.GrandActionAbstract#iniciar(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */

    public ActionForward iniciar(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		Usuario usuario 		= (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		FacadeFactory.getGeneralFacade().actualizarCuentas("LG02",usuario,request.getRemoteAddr());
		loadMessage(request);
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		
		request.getSession().setAttribute("TITULO",Constante.TRANSFERENCIA_BANCARIA_TITULO);
		request.getSession().setAttribute("listaAfiliaciones",FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.TB_MISMO_BANCO,usuario.getTarjeta().getNumero(),"00004"));
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
		
		ParametrosElemSegTDC elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento"); //5,6,7
        
		if(Constante.CODIGO_SI_PENDIENTE_ENROLAMIENTO.equals(elementos.getPendienteEnrolamiento()))			
		{
			return mapping.findForward("pendienteEnrolamiento");
		}
		
		if(elementos.getPermiteOperaciones().equals(Constante.CODIGO_NO_PERMITE_OPERACIONES))			
		{
			return mapping.findForward("noPermiteOperaciones");
		}
		
		if(usuario.getEstadoOperaciones().getEstadoOperaciones().equals(Constante.NO_PERMITE_OPERACIONES_ATRIBUTOS))			
		{
			return mapping.findForward("operacionDesactivada");
		}
        
        String flagActdatos = usuario.getFlagActualizaDatoHost();
        if(flagActdatos.equals("1"))
        {
            return mapping.findForward("actualizarDatosPersona");
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
		 
		//String ip= request.getHeader("X-FORWARDED-FOR");
		
		return mapping.findForward("iniciarMB");
    }
    
	public ActionForward verTransferencia(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception{
		loadMessage(request);
		

		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		
		//Validar IP
		ServiciosAntiphishing antiphishing = new ServiciosAntiphishing();
		int flagConsultaIP =1;
		try{
			
			flagConsultaIP = Integer.parseInt(antiphishing.validaIP(request.getRemoteAddr()));
					
			if (flagConsultaIP != Constante.FLAG_ANTIPHISHING_IP_EN_EL_RANGO && flagConsultaIP != Constante.FLAG_ANTIPHISHING_NO_VALIDA_IP){
				
				throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_VALIDA_IP_FUERA_PAIS);
			}
			
		
		}catch(ArrayRuleException ar){
			ar.printStackTrace();
			ar.setForward("iniciarMB");
			throw ar;
		}
		
		
		//Validar Cuenta si es Nuevo
		
		if(request.getParameter("optCuenta").equals(Constante.NUEV_REGISTRO_TRANSAC)){
			
			List listaAfiliaciones = null;
			listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaExiste(Constante.TB_MISMO_BANCO,usuario.getTarjeta().getNumero(), "", request.getParameter("txtNumeroCuentaDestino"));
			
			if (listaAfiliaciones.size() > 0){
			
				throw new ArrayRuleException(ConstanteError.GENERICO,"El número de Cuenta: " + request.getParameter("txtNumeroCuentaDestino") +" se encuentra como frecuente.");
			}
			
			Afiliacion afiliacion = new AfiliacionImpl();
			
			afiliacion.setNumeroServicio(request.getParameter("txtNumeroCuentaDestino").trim());
			
//			Valida si la cuenta es valida
			AfiliacionImpl afilNuevo = new AfiliacionImpl();
			
			afilNuevo.setNumeroServicio(afiliacion.getNumeroServicio());
			 FacadeFactory.getGeneralFacade().validacionCuenta(afilNuevo);
					 
		}
		
				
		String esCTS = request.getParameter("esCTS");
		

		BNAplicacion aplicacion = BNAplicacion.getInstance();
		request.getSession().setAttribute("mensajeLimite",((Vector)aplicacion.getMensajePorCodigo("TB01","00003")).elementAt(2).toString());
		try{
			Afiliacion afiliacion = null;
			
			//if(Constante.VER_LOG) 
			
			if(esCTS.equals("0")){
			
				if(request.getParameter("optCuenta").equals(Constante.FREC_REGISTRO_TRANSAC)){
					
					String[] id = ObjectUtil.getArrayStrings(request.getParameter("cmbTransferencia"),"-");

					afiliacion = FacadeFactory.getAfiliacionFacade().getAfiliacion(id[0],id[1],new Long(id[2]),"00004");
					afiliacion.setFlagRegistro(Constante.FREC_REGISTRO_TRANSAC);
					
				}			
			}
			else{
			
			
				String codigo = request.getParameter("cmbTransferencia");
		
				List x=usuario.getCuentas();
				CuentaImpl cta=null;
				boolean flag= false;
				for(int i=0; i<x.size();i++){
					cta=(CuentaImpl)x.get(i);
				
					if(cta.getTipoProducto().equals("44")){
						flag=true;	
						//System.out.println("ENCONTRO CUENTA PRESTAMOS");
						break;
					}	
				}
				if(!flag){
					cta	= (CuentaImpl)usuario.getMapCuentas().get(codigo);
				}else{
					for(int i=0; i<x.size();i++){
						cta=(CuentaImpl)x.get(i);
						if(!cta.getTipoProducto().equals("44")&& cta.getNumeroProducto().equals(codigo)){
							break;
							
						}	
					}
				
				}
				
				if(request.getParameter("optCuenta").equals(Constante.FREC_REGISTRO_TRANSAC)){
					
					CuentaImpl cuenta1 = cta;
					afiliacion 	  = new AfiliacionImpl();
					afiliacion.setCuenta(cuenta1);
					afiliacion.setTipoAfiliacion(Constante.TB_MISMO_BANCO);
					afiliacion.setCuentaPropia("S");				
					afiliacion.setNumeroServicio(codigo);
					afiliacion.setNroTarjeta(usuario.getTarjeta().getNumero());
					afiliacion.setSecuencia(new Long(0));
					afiliacion.setCodigoServicio(codigo.charAt(0)+""+codigo.charAt(1));
					afiliacion.setFlagRegistro(Constante.FREC_REGISTRO_TRANSAC);
					
				}							
	
			}
			String cmbCuenta 	= "";
			
			cmbCuenta = request.getParameter("cmbCuenta");
			if(request.getParameter("optCuenta").equals(Constante.FREC_REGISTRO_TRANSAC))
			{
			
			request.getSession().setAttribute("CORREO_AFILIADO",afiliacion.getEmail());
			}
			
			//PARA INGRESO DE REGISTROS NUEVOS
			else{
								
				afiliacion 	  = new AfiliacionImpl();
				afiliacion.setTipoAfiliacion(Constante.TB_MISMO_BANCO);
				afiliacion.setCuentaPropia("N");				
				afiliacion.setNumeroServicio(request.getParameter("txtNumeroCuentaDestino").trim());
				afiliacion.setNroTarjeta(usuario.getTarjeta().getNumero());
				afiliacion.setCodigoServicio(afiliacion.getNumeroServicio().substring(0, 2));
				afiliacion.setFlagRegistro(Constante.NUEV_REGISTRO_TRANSAC);
				
				CuentaImpl cuenta = new CuentaImpl();
				cuenta.setTipoProducto(afiliacion.getNumeroServicio().substring(0, 2));
				cuenta.setNumeroProducto(request.getParameter("txtNumeroCuentaDestino"));
			
				afiliacion.setCuenta(cuenta);
				
			}
			
			
			FacadeFactory.getTransferenciaFacade().getTitularTransferencia(request.getParameter("transaccion"),(Cuenta)usuario.getMapCuentas().get(cmbCuenta),afiliacion,usuario,request.getRemoteAddr());
						
			if(afiliacion.getDescripcion().trim().equals("") ||afiliacion.getDescripcion().trim().equals(null)){
				throw new ArrayRuleException(ConstanteError.GENERICO,"El número de Cuenta: " + afiliacion.getNumeroServicio() +" no se encuentra disponible");
			} 
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
			
			if(request.getParameter("optCuenta").equals(Constante.FREC_REGISTRO_TRANSAC)){
				
				afiliacion.getCuenta().setCuentaformateada(ObjectUtil.formatearCuenta(afiliacion.getNumeroServicio(),Constante.FORMATO_CUENTA));
			}
			else{
				
				if(request.getParameter("optCuenta").equals(Constante.NUEV_REGISTRO_TRANSAC)){
				
					afiliacion.getCuenta().setCuentaformateada(ObjectUtil.formatearCuenta(request.getParameter("txtNumeroCuentaDestino"),Constante.FORMATO_CUENTA));
				}
			}
					
		
			if(cuenta.getCuentaFormateada().equals(afiliacion.getCuenta().getCuentaformateada())){
				
				throw new ArrayRuleException(ConstanteError.GENERICO,"El número de Cuenta: " + afiliacion.getNumeroServicio() +" debe ser distinto a la cuenta de origen.");
			}
			
			request.getSession().setAttribute("cmbCuenta",cmbCuenta);
			request.getSession().setAttribute("esCTS",esCTS);
			request.getSession().setAttribute("cmbTransferencia",request.getParameter("cmbTransferencia"));
			loadMessage(request);
			ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
			request.getSession().setAttribute("afiliacion",afiliacion);
			
			request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
			
			ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
			request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);


			return mapping.findForward("verMBTransferencia");
		}
		catch(ArrayRuleException e){
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			List listaAfiliaciones 	= null;
			loadMessage(request);
			listaAfiliaciones 		=  FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.TB_MISMO_BANCO,usuario.getTarjeta().getNumero(),"00004");
			request.getSession().setAttribute("listaAfiliaciones",listaAfiliaciones);
			throw e;
		}
	}
	
	//3.- METODOS CAMBIADOS HACER CONSULTA DE COMISION
	public ActionForward confirmarTransferencia(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception{
		TokenSmsRequest tokenSms = new TokenSmsRequest();
		
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		
		TipoCambio tipoCambio = usuario.getTipoCambio();
		Afiliacion afiliacion 	= (AfiliacionImpl)request.getSession().getAttribute("afiliacion");
		//****************************************************
		List x=usuario.getCuentas();
		CuentaImpl cta=null;
		boolean flag= false;
		for(int i=0; i<x.size();i++){
			cta=(CuentaImpl)x.get(i);
		
			if(cta.getTipoProducto().equals("44")){
				flag=true;	
				//System.out.println("ENCONTRO CUENTA PRESTAMOS");
				break;
			}	
		}
		if(!flag){
			cta	= (CuentaImpl)usuario.getMapCuentas().get(request.getParameter("cmbCuenta"));
		}else{
			for(int i=0; i<x.size();i++){
				cta=(CuentaImpl)x.get(i);
				if(!cta.getTipoProducto().equals("44")&& cta.getNumeroProducto().equals(request.getParameter("cmbCuenta"))){
					break;
					
				}	
			}
		
		}
		//*****************************************
		
		Cuenta cuenta 			= cta;
		String transaccion 		= request.getParameter("transaccion");
		//String moneda 		= request.getParameter("cboMoneda");  Se modifica por observacion Deloitte
		String moneda 			= afiliacion.getCuenta().getMonedaProducto();
				
		String monto 			= request.getParameter("txtMonto"); 
		String esCTS 			= request.getParameter("esCTS");
		String simbMoneda       = "";
		String simboloMoneda       = "";
		if(moneda.equals(Constante.MONEDA_SOL)) simbMoneda = Constante.SIMBOLO_MONEDA_SOL;
		else simbMoneda = Constante.SIMBOLO_MONEDA_DOLAR;
		if(cuenta.getMonedaProducto().equals(Constante.MONEDA_SOL)) simboloMoneda = Constante.SIMBOLO_MONEDA_SOL;
		else simboloMoneda = Constante.SIMBOLO_MONEDA_DOLAR;
		TransferenciaImpl transferencia=null;
		
		request.getSession().setAttribute("monto",monto);
		
			
		try{
			if(esCTS.equals("0")){
				transferencia=new TransferenciaImpl();
				transferencia = (TransferenciaImpl)FacadeFactory.getTransferenciaFacade().getTransferencia(transaccion,cuenta,afiliacion,usuario,moneda,monto,request.getRemoteAddr());
				
				//System.out.println("***--- Importe TRANS**** - transferencia.getImporte:"+transferencia.getImporte());
			}
			else{
				transferencia=new TransferenciaImpl(); 
				transferencia.setComision(new BigDecimal(0.00));
				transferencia.setCuentaDestino(afiliacion.getCuenta());
				transferencia.setItf(new BigDecimal(0.00));
				transferencia.setImporte(new BigDecimal(monto));
				
				//System.out.println("***--- Importe TRANS - transferencia.getImporte:"+transferencia.getImporte());
				
				transferencia.setCuentaOrigen(cuenta);
				transferencia.setSimboloMonedaImporte(simbMoneda);
				transferencia.setSimboloMonedaComision(simboloMoneda);
				transferencia.setSimboloMonedaAbono(Constante.SIMBOLO_MONEDA_SOL);
				transferencia.setSimboloMonedaImpNetoCargo(simboloMoneda);
				transferencia.setSimboloMonedaItf(simboloMoneda);
				transferencia.setImporteAbonar(ObjectUtil.deFormatearMonto(transferencia.getImporte()));
				//System.out.println("***--- Importe TRANS - transferencia.getImporteAbonar:"+transferencia.getImporteAbonar());
				
				transferencia.setImporteCargoSoles(ObjectUtil.deFormatearMonto(transferencia.getImporte()));
				//System.out.println("***--- Importe TRANS - transferencia.getImporteCargoSoles:"+transferencia.getImporteCargoSoles());
				
				transferencia.setImporteConvertido(ObjectUtil.deFormatearMonto(transferencia.getImporte()));
				//System.out.println("***--- Importe TRANS - transferencia.getImporteConvertido:"+transferencia.getImporteConvertido());
				
				transferencia.setImporteCV(ObjectUtil.deFormatearMonto(transferencia.getImporte()));
				//System.out.println("***--- Importe TRANS - transferencia.getImporteCV:"+transferencia.getImporteCV());
				
				if(simboloMoneda.equals(Constante.SIMBOLO_MONEDA_DOLAR)){
					transferencia.setTipoCambio(usuario.getTipoCambio().getCompra().setScale(4));
					BigDecimal imp=new BigDecimal(monto);
					BigDecimal imp2=new BigDecimal(transferencia.getTipoCambio());
					if(moneda.equals(Constante.MONEDA_SOL)){
						transferencia.setImporteCargo(imp.divide(imp2,2, BigDecimal.ROUND_HALF_UP));
						transferencia.setImporteAbono(ObjectUtil.deFormatearMonto(transferencia.getImporte()));
						//System.out.println("***--- Importe TRANS - transferencia.getImporteAbono:"+transferencia.getImporteAbono());
						
					}
					else{
						transferencia.setImporteCargo(ObjectUtil.deFormatearMonto(transferencia.getImporte()));
						//System.out.println("***--- Importe TRANS - transferencia.getImporteCargo:"+transferencia.getImporteCargo());
						
						transferencia.setImporteAbono((imp.multiply(imp2)).divide(new BigDecimal(1),2, BigDecimal.ROUND_HALF_UP));
					}
					transferencia.setImporteNeto(transferencia.getImporteCargo());
				}
				else{
					transferencia.setImporteNeto(ObjectUtil.deFormatearMonto(transferencia.getImporte()));
					//System.out.println("***--- Importe TRANS - transferencia.getImporteNeto:"+transferencia.getImporteNeto());
					
					transferencia.setImporteCargo(ObjectUtil.deFormatearMonto(transferencia.getImporte()));
					//System.out.println("***--- Importe TRANS - transferencia.getImporteCargo:"+transferencia.getImporteCargo());
				}
				transferencia.setAfiliacion(afiliacion);
				
			}
			
			Afiliacion datosUsuario  = new AfiliacionImpl();
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
			//afiliacion.setCodCliente(codCliente);
			
			transferencia.setMoneda(moneda);
				
			request.getSession().setAttribute(ConstanteSesion.TRANSFERENCIA,transferencia);
			
		
						
			
		}catch (ArrayRuleException e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
		
			request.setAttribute("cuenta",cuenta);
			request.setAttribute("cmbCuenta",request.getParameter("cmbCuenta"));
			request.getSession().setAttribute("esCTS",esCTS);
			request.getSession().setAttribute("cmbTransferencia",request.getParameter("cmbTransferencia"));
			loadMessage(request);
			e.setForward("verMBTransferencia");
			throw e;
		}
		request.getSession().setAttribute("esCTS",esCTS);
		request.getSession().setAttribute("cmbTransferencia",request.getParameter("cmbTransferencia"));
		
		
		ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
		
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
			ar.setForward("verMBTransferencia");
			throw ar;
		}
		
//		REALIZAR CONSULTA DE COORDENADAS
		ElemenSeguridad resultCoord = null;
		
		if(param!= null){
			resultCoord = new ElemenSeguridad();
			try{
				request.getSession().setAttribute("resultCoord",null );
				
				if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
						resultCoord = SolicitarServiciosTDC.solicitCoordenada(param);
				} else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS)) {						
						resultCoord=SolicitarServiciosTDC.solicitarTokens(param);
				} else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {						
					generarClaveSms(mapping, form, request, response);		
				}
			}catch(ArrayRuleException ar){
				log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
				ar.printStackTrace();
				ar.setForward("verMBTransferencia");
				throw ar;
			}
			request.getSession().setAttribute("resultCoord",resultCoord );
		}
		else{
			
			throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
		}
		return mapping.findForward("confirmarMBTransferencia");
	}
	
	public ActionForward transferir(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		String transaccion = request.getParameter("transaccion");
		TransferenciaImpl transferencia = (TransferenciaImpl)request.getSession().getAttribute(ConstanteSesion.TRANSFERENCIA);
		Afiliacion afiliacion = (AfiliacionImpl)request.getSession().getAttribute("afiliacion");
		transferencia.setAfiliacion(afiliacion);
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		
		ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		
		ElemenSeguridad resultCoord = null;
		ParametrosElemSegTDC elementos = null;
		ElemenSeguridad coord = (ElemenSeguridad)request.getSession().getAttribute("resultCoord");
		String pinblock = ObjectUtil.getClaveDesencriptada(request.getParameter("txtCoordenada"),request);
		
		request.getSession().setAttribute("fectransmb",pe.cosapi.common.ObjectUtil.getFechaActual());
		request.getSession().setAttribute("hortransmb",pe.cosapi.common.ObjectUtil.getHHMMSSformateado());
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
		
		try{
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
					
						
			
			Map map = UtilAction.cargarVar(request,request.getSession().getAttribute(ConstanteSesion.TRANSFERENCIA));
			OperacionImpl.setVariables(map);
			elementos = (ParametrosElemSegTDC)request.getSession().getAttribute("tipoElemento");
			MensajesTDC resultado = new MensajesTDC();
			Transferencia transf = null;
			
			if(param!= null && coord!=null ){
				try{
					
					
					if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)) {
						resultado=SolicitarServiciosTDC.validarTDC(param ,coord,pinblock);
					} else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS)) {
						resultado=SolicitarServiciosTDC.validarTokens(param ,coord,pinblock);
					} else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {
						
						TokenSmsRequest tokenSms = new TokenSmsRequest();
						tokenSms.setCodCliente(usuario.getCodigoCic());
						//tokenSms.setTipoDocumento(Funciones.lpad(datosUsuario.getTipoDocumento(),"0",3));
						//tokenSms.setNroDocumento(datosUsuario.getNroDocumento());
						tokenSms.setTipoDocumento(Funciones.lpad(tipoDoc,"0",3));
						tokenSms.setNroDocumento(numDoc);	
						tokenSms.setCodeSMS(pinblock);
						tokenSms.setTypeTrans(Constante.TIP_OPER_TRANSFERENCIA);
						tokenSms.setTypeOp(Constante.COD_TRANSACCION_FINANCIERA);						
						tokenSms.setAmount(transferencia.getImporte());
						tokenSms.setTypeCurrency(transferencia.getMoneda().equals("USD")?"USD":"PEN");
						
						tokenSms.setCodCliDestinatario(afiliacion.getNumeroServicio());
						tokenSms.setNomCliDestinatario(afiliacion.getDescripcion());
						
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
					
					//Validar IP
					ServiciosAntiphishing antiphishing = new ServiciosAntiphishing();
					int flagConsultaIP =1;
					try{
						
						flagConsultaIP = Integer.parseInt(antiphishing.validaIP(request.getRemoteAddr()));
												
						if (flagConsultaIP != Constante.FLAG_ANTIPHISHING_IP_EN_EL_RANGO && flagConsultaIP != Constante.FLAG_ANTIPHISHING_NO_VALIDA_IP){	
							throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_VALIDA_IP_FUERA_PAIS);
						}
						
					
					}catch(ArrayRuleException ar){
						ar.printStackTrace();
						ar.setForward("iniciarMB");
						throw ar;
					}
					
					transf = FacadeFactory.getTransferenciaFacade().pagarTransferencia(transaccion,transferencia,usuario,request.getRemoteAddr(),request);
					
					//System.out.println("usuario.getNotificacion().toString()"+usuario.getNotificacion().toString());
					
					if(usuario.getNotificacion().getEstadoNotificacion().equals("1")){
						 ServiciosNotificacionPrincipal.enviarNotificacion(usuario,
								 usuario.getNombre(), 
								 transf.getSimboloMonedaImporte(), 
								 transf.getImporte(),
								 usuario.getTipoCambio().getVenta(), 
								 Constante.NOTI_TRANSFERENCIA_MB, 
								 usuario.getNotificacion());
					}
					request.getSession().setAttribute(ConstanteSesion.TRANSFERENCIA, transf);

					ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_TRANSFERENCIA_MISMO_BANCO, ConstanteSesion.TRANSFERENCIA, request); 				
				}
				catch (ArrayRuleException e) {
					log3.error(e,Constante.ERR_ERROR_VALIDAR_TDC,"");
					e.printStackTrace();
					request.getSession().setAttribute(ConstanteSesion.TRANSFERENCIA,transferencia);
					
					if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
							resultCoord = SolicitarServiciosTDC.solicitCoordenada(param);
					}else{
						if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS))
						{
							resultCoord=SolicitarServiciosTDC.solicitarTokens(param);
						}
					}
					
					if(resultCoord!=null)
						request.getSession().setAttribute("resultCoord",resultCoord );
					e.setForward("confirmarMBTransferencia");
					throw e;
				}
			}
		}
		catch (ArrayRuleException e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
		
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
			if(resultCoord!=null)
				request.getSession().setAttribute("resultCoord",resultCoord );
			e.setForward("confirmarMBTransferencia");
			loadMessage(request);
			throw e;
		}
		
		if(transferencia.getAfiliacion().getFlagRegistro().equals(Constante.NUEV_REGISTRO_TRANSAC)){
			String adicionar = request.getParameter("adicionar");
			if(adicionar!=null){
				try {
				String beneficiarioName = request.getParameter("txtNombreBeneficiario").toString();
				Afiliacion afil = transferencia.getAfiliacion();
				afil.setBeneficiario(beneficiarioName);
				
				Afiliacion af=FacadeFactory.getAfiliacionFacade().afiliar(usuario,afil);
				request.getSession().setAttribute(ConstanteSesion.AFILIAR,af);
					
				} catch (ArrayRuleException e) {
					log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
					e.printStackTrace();
					e.setForward("transferirMB");
				}
			}
		}

		return mapping.findForward("transferirMB");
	}

	private void loadMessage(HttpServletRequest request) throws Exception{
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		request.getSession().setAttribute("mensajeAfiliacion",((Vector)aplicacion.getMensajePorCodigo("TB01","AF001")).elementAt(2).toString());
		request.getSession().setAttribute("mensajePantalla",((Vector)aplicacion.getMensajePorCodigo("TB01","00001")).elementAt(2).toString());
		request.getSession().setAttribute("mensajeExitoTranfMB",((Vector)aplicacion.getMensajePorCodigo("TB01","00005")).elementAt(2).toString());
	}
	
	public ActionForward reGenerarClaveSms(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		String monto 			= (String)request.getSession().getAttribute("monto");
		//Afiliacion datosUsuario  = FacadeFactory.getGeneralFacade().obtenerDatosPersonalesAfiliacion(usuario);
		
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
		
		Afiliacion afiliacion = (AfiliacionImpl)request.getSession().getAttribute("afiliacion");
		
		TransferenciaImpl transferencia = (TransferenciaImpl)request.getSession().getAttribute(ConstanteSesion.TRANSFERENCIA);
		
		try{
			ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
			ParametrosElemSegTDC elementos =  (ParametrosElemSegTDC )request.getSession().getAttribute("tipoElemento"); 
			
			request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
			request.getSession().setAttribute("tipoElemento",elementos );
			
			if(param!= null){
				if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {
					
					TokenSmsRequest tokenSms = new TokenSmsRequest();
					
					tokenSms.setCodCliente(usuario.getCodigoCic());
//					tokenSms.setTipoDocumento(Funciones.lpad(datosUsuario.getTipoDocumento(),"0",3));
//					tokenSms.setNroDocumento(datosUsuario.getNroDocumento());	
					tokenSms.setTipoDocumento(Funciones.lpad(tipoDoc,"0",3));
					tokenSms.setNroDocumento(numDoc);
					tokenSms.setTypeTrans(Constante.TIP_OPER_TRANSFERENCIA);
					tokenSms.setTypeOp(Constante.COD_TRANSACCION_FINANCIERA);
					tokenSms.setAmount(monto);
					tokenSms.setTypeCurrency(transferencia.getMoneda().equals("USD")?"USD":"PEN");
					tokenSms.setCodCliDestinatario(afiliacion.getNumeroServicio());
					tokenSms.setNomCliDestinatario(afiliacion.getDescripcion());

					MensajesTDC resultado = UtilAction.generarClaveSms(tokenSms);
					
					if(resultado.isExito()==false){		
						String mensaje = aplicacion.getMsjesHost("CS", resultado.getCodResult()).elementAt(2).toString();
						response.setStatus(404);
						response.getWriter().write(mensaje);
						response.getWriter().flush();
						response.getWriter().close();
					}
				}
				
			} else {
				throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
			}
		
		}catch (ArrayRuleException e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			response.setStatus(404);
			request.setAttribute("cmbCuenta",request.getParameter("cmbCuenta"));
			request.getSession().setAttribute("cmbTransferencia",request.getParameter("cmbTransferencia"));
			loadMessage(request);
			e.setForward("verMBTransferencia");
			throw e;
		}
		return mapping.findForward("confirmarMBTransferencia");
		
	}
	
	
public ActionForward generarClaveSms(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		String monto 			= (String)request.getSession().getAttribute("monto");
		//Afiliacion datosUsuario  = FacadeFactory.getGeneralFacade().obtenerDatosPersonalesAfiliacion(usuario);
		Afiliacion afiliacion = (AfiliacionImpl)request.getSession().getAttribute("afiliacion");
		
		TransferenciaImpl transferencia = (TransferenciaImpl)request.getSession().getAttribute(ConstanteSesion.TRANSFERENCIA);
		
		try{
			ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
			ParametrosElemSegTDC elementos =  (ParametrosElemSegTDC )request.getSession().getAttribute("tipoElemento"); 
			
			request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
			request.getSession().setAttribute("tipoElemento",elementos );
			
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
						
			if(param!= null){
				if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {
					
					TokenSmsRequest tokenSms = new TokenSmsRequest();
					
					tokenSms.setCodCliente(usuario.getCodigoCic());
					
//					tokenSms.setTipoDocumento(Funciones.lpad(datosUsuario.getTipoDocumento(),"0",3));
//					tokenSms.setNroDocumento(datosUsuario.getNroDocumento());	
					tokenSms.setTipoDocumento(Funciones.lpad(tipoDoc,"0",3));
					tokenSms.setNroDocumento(numDoc);	
					System.out.println("tokenSms.getTipoDocumento():"+tokenSms.getTipoDocumento());
					System.out.println("tokenSms.getNroDocumento():"+tokenSms.getNroDocumento());
					tokenSms.setTypeTrans(Constante.TIP_OPER_TRANSFERENCIA);
					tokenSms.setTypeOp(Constante.COD_TRANSACCION_FINANCIERA);
					tokenSms.setAmount(monto);
					tokenSms.setTypeCurrency(transferencia.getMoneda().equals("USD")?"USD":"PEN");
					tokenSms.setCodCliDestinatario(afiliacion.getNumeroServicio());
					tokenSms.setNomCliDestinatario(afiliacion.getDescripcion());

					MensajesTDC resultado = UtilAction.generarClaveSms(tokenSms);
					
					
				}
				
			} else {
				throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
			}
		
		}catch (ArrayRuleException e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			response.setStatus(404);
			request.setAttribute("cmbCuenta",request.getParameter("cmbCuenta"));
			request.getSession().setAttribute("cmbTransferencia",request.getParameter("cmbTransferencia"));
			loadMessage(request);
			e.setForward("verMBTransferencia");
			throw e;
		}
		return mapping.findForward("confirmarMBTransferencia");
		
	}
}
