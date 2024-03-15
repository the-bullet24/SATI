/*
 * Fecha 04/06/2007
 * Creado por Wilder A. Hernandez Nuñez
 */
package pe.bn.transferencia.action;

import java.math.BigDecimal;
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
import pe.bn.cldinamica.action.form.MensajesTDC;
import pe.bn.cldinamica.action.form.ElemenSeguridad;
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
import pe.cosapi.domain.impl.OperacionImpl;

public class TransferenciaIBAction extends GrandActionAbstract {
	
	BNAplicacion aplicacion =BNAplicacion.getInstance();
	private static LoggerSati log3 = LoggerSati.getInstance(TransferenciaIBAction.class.getName());
	/* (sin Javadoc)
	 * @see pe.cosapi.action.GrandActionAbstract#iniciar(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward iniciar(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
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
		
		if(usuario.getEstadoOperaciones().getEstadoOperaciones().equals(Constante.NO_PERMITE_OPERACIONES_ATRIBUTOS))			
		{
			return mapping.findForward("operacionDesactivada");
		}
		
        String flagActdatos = usuario.getFlagActualizaDatoHost();
        if(flagActdatos.equals("1"))
        {
            return mapping.findForward("actualizarDatosPersona");
        }
		
		List listaAfiliaciones = null;
		//listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.TB_INTERBANCO,usuario.getTarjeta().getNumero(),"00004");
		Constante.TIPO_SERVICIO = Constante.TB_INTERBANCO;
		// Mensajistica para cada pantalla
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		request.getSession().setAttribute(ConstanteSesion.TITULO,Constante.TRANSFERENCIA_INTERBANCARIA_TITULO);
		request.getSession().setAttribute("TITULOS",Constante.TRANSFERENCIA_INTERBANCARIA_TITULO);
		request.getSession().setAttribute("mensajeTraInterbancaria",((Vector)aplicacion.getMensajePorCodigo("TB05","AF001")).elementAt(2).toString());
		request.getSession().setAttribute("mensajePiePagina",((Vector)aplicacion.getMensajePorCodigo("TB05","00001")).elementAt(2).toString());
		request.getSession().setAttribute("mensajeExitoTransfIB",((Vector)aplicacion.getMensajePorCodigo("TB05","00005")).elementAt(2).toString());
		request.getSession().setAttribute("listaAfiliaciones",FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.TB_INTERBANCO,usuario.getTarjeta().getNumero(),"00004"));
		request.getSession().setAttribute("mensajepieafiltransfint",((Vector)aplicacion.getMensajePorCodigo("TB05","00003")).elementAt(2).toString());
		request.getSession().setAttribute("mensajeafiliacion",((Vector)aplicacion.getMensajePorCodigo("TB05","AF002")).elementAt(2).toString());
		//request.setAttribute("listaAfiliaciones",listaAfiliaciones);
		FacadeFactory.getGeneralFacade().actualizarCuentas("LG02",usuario,request.getRemoteAddr());
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);

		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);

		ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
		
		if(!getVerificarHorario("TB05")){
			
			request.getSession().setAttribute("mensaje1Hor",((Vector)aplicacion.getMensajePorCodigo("AD01","00001")).elementAt(2).toString());
			request.getSession().setAttribute("mensaje2Hor",((Vector)aplicacion.getMensajePorCodigo("AD01","00002")).elementAt(2).toString());
			request.getSession().setAttribute("mensajeHorC",((Vector)aplicacion.getMensajePorCodigo("MH01","TB05")).elementAt(2).toString());
			return mapping.findForward("transaccion.noDisponible");
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

		return mapping.findForward("iniciar");
	}
	
	public ActionForward verTransferencia(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception{
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);		
		Afiliacion afiliacion  = new AfiliacionImpl();
		Afiliacion datosUsuario  = new AfiliacionImpl();
		
		if(request.getParameter("optCuenta").equals(Constante.NUEV_REGISTRO_TRANSAC)){
			afiliacion = new AfiliacionImpl();
			afiliacion.setNumeroServicio(request.getParameter("txtCuentaCCI"));
			
			afiliacion.setFlagRegistro(Constante.NUEV_REGISTRO_TRANSAC);
			afiliacion.setCuentaPropia(request.getParameter("rdnCuentaPropia"));
			String codCuentaCCI = request.getParameter("txtCuentaCCI").trim();
			String beneficiario = request.getParameter("txtNombreBenef").trim();
		
			afiliacion.setBeneficiario(beneficiario);
			String codBancoCCI = codCuentaCCI.substring(0, 3);
			String codCCIFormateada  = codCuentaCCI.substring(0, 3)+"-"+codCuentaCCI.substring(3, 6)+"-"+codCuentaCCI.substring(6, 18)+"-"+codCuentaCCI.substring(18, 20);
			
			ComboUtil lista = new ComboUtil();
			lista = DAOFactory.getGeneraDAO().getObjDetalleHlp("00460",codBancoCCI.trim());
			
			
			if (lista == null){
		
				throw new ArrayRuleException(ConstanteError.GENERICO,"El Nro. CCI no es valido para la Transferencia Interbancaria.");
			} else if (lista.getCodigo().trim() == ""){
			
				throw new ArrayRuleException(ConstanteError.GENERICO,"El Nro. CCI no es valido para la Transferencia Interbancaria.");
			}else{
			afiliacion.getObjBenef().setObjBanco(lista);
			}
			
			List listaAfiliaciones = null;
			listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaExiste(Constante.TB_INTERBANCO,usuario.getTarjeta().getNumero(), "00460", afiliacion.getNumeroServicio());
			
			if (listaAfiliaciones.size() > 0){
			
				throw new ArrayRuleException(ConstanteError.GENERICO,"El número de CCI: " + afiliacion.getNumeroServicio() +" ya se encuentra como frecuente.");
			}
			
			afiliacion.setCodigoServicio(codBancoCCI);
			//Obtener datos del cliente
//			datosUsuario = FacadeFactory.getGeneralFacade().obtenerDatosPersonalesAfiliacion(usuario);
//			afiliacion.setTipoDocumento("00"+Integer.parseInt(datosUsuario.getTipoDocumento()));
//			afiliacion.setNroDocumento(datosUsuario.getNroDocumento());
			String tipoDoc="";
			String numDoc="";
			
			if(usuario.getCodigoCLDI() != null){
				tipoDoc=usuario.getCodigoCLDI().substring(12,13);
				numDoc=usuario.getCodigoCLDI().substring(13);	
				numDoc= numDoc.substring(4);
			
			}
			afiliacion.setTipoDocumento(Funciones.lpad(tipoDoc,"0",3));
			afiliacion.setNroDocumento(numDoc);
			
			CuentaImpl cuenta = new CuentaImpl();
			cuenta.setNumeroProducto(codCuentaCCI);
			afiliacion.setCuenta(cuenta);
			
		}
		if(request.getParameter("optCuenta").equals(Constante.FREC_REGISTRO_TRANSAC)){
			String[] id = ObjectUtil.getArrayStrings(request.getParameter("cmbTransferencia"),"-");
			String tipAfiliacion = id[0];
			String nroTarjeta	 = id[1];
			Long nroSecuencia  	 = new Long(id[2]);
			String campoAyuda	 = "00004";
			
			afiliacion = FacadeFactory.getAfiliacionFacade().getAfiliacion(tipAfiliacion,nroTarjeta,nroSecuencia,campoAyuda);
			afiliacion.setFlagRegistro(Constante.FREC_REGISTRO_TRANSAC);
			ComboUtil lista = new ComboUtil();
			lista = DAOFactory.getGeneraDAO().getObjDetalleHlp("00460",afiliacion.getCodigoServicio());
			afiliacion.getObjBenef().setObjBanco(lista);
			request.getSession().setAttribute("CORREO_AFILIADO",afiliacion.getEmail());
			
		}
		
		
		String[] idCta = ObjectUtil.getArrayStrings(request.getParameter("cmbCuenta"),"-");
	
		//CuentaImpl cuenta = usuario.getMapCuentas().get(request.getParameter("cmbCuenta"));
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
			cta	= (CuentaImpl)usuario.getMapCuentas().get(idCta[0]);
		}else{
			for(int i=0; i<x.size();i++){
				cta=(CuentaImpl)x.get(i);
				if(!cta.getTipoProducto().equals("44")&& cta.getNumeroProducto().equals(idCta[0])){
					break;
					
				}	
			}
		
		}
		//*****************************************
		
		CuentaImpl cuentaRec = cta;
		
		// Cargar el mensaje de la Condicion
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		request.getSession().setAttribute("listaAfiliaciones",FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.TB_INTERBANCO,usuario.getTarjeta().getNumero(),"00004"));
		request.setAttribute("mensajeCondicion",((Vector)aplicacion.getMensajePorCodigo("TB05","00002")).elementAt(2).toString());
		request.setAttribute("mensajeLimite",((Vector)aplicacion.getMensajePorCodigo("TB05","00004")).elementAt(2).toString());
		request.getSession().setAttribute("afiliacion",afiliacion);
		request.setAttribute("listaMonedas",FacadeFactory.getGeneralFacade().getComboDetalleHlp(""));
		request.setAttribute("cuenta",cuentaRec);
	
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);

		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);

		ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
		
		return mapping.findForward("verTransferencia");
	}
	
	public ActionForward continuarTransferencia(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception{
	    BNAplicacion aplicacion = BNAplicacion.getInstance();
	    Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);

	    
		    try{
		    	double monto= Double.parseDouble(request.getParameter("txtMonto").toString());
		    	
			}catch(NumberFormatException e){
				log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			    request.getSession().setAttribute("listaAfiliaciones",FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.TB_INTERBANCO,usuario.getTarjeta().getNumero(),"00004"));
				request.setAttribute("mensajeCondicion",((Vector)aplicacion.getMensajePorCodigo("TB05","00002")).elementAt(2).toString());
				request.setAttribute("mensajeLimite",((Vector)aplicacion.getMensajePorCodigo("TB05","00004")).elementAt(2).toString());
				request.setAttribute("listaMonedas",FacadeFactory.getGeneralFacade().getComboDetalleHlp(""));
				
			    ArrayRuleException e1 = new ArrayRuleException(ConstanteError.GENERICO,"Verifique el monto de la Transferencia ingresado");
				e1.setForward("verTransferencia");
				throw e1;
			}

		String importeIngresado = ObjectUtil.replaceChar(request.getParameter("txtMonto").toString(),',',"");
		
		BigDecimal montoConversion = new BigDecimal(importeIngresado).setScale(2);
		//System.out.println("Importe - montoConversion"+montoConversion);
		
		
		Afiliacion af = (Afiliacion)request.getSession().getAttribute("afiliacion");
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
		
		
		Cuenta cuenta = cta;
		Transferencia tran = null;
		

			if (af.getCuentaPropia().equals("S"))
			    tran = FacadeFactory.getTransferenciaFacade().getTitularTransferenciaIB(request.getParameter("cboMoneda"),request.getParameter("txtMonto"),cuenta,af,usuario,request.getRemoteAddr());
			else if (af.getCuentaPropia().equals("N"))
			    tran = FacadeFactory.getTransferenciaFacade().getOtroTitularTransferenciaIB(request.getParameter("cboMoneda"),request.getParameter("txtMonto"),cuenta,af,usuario,request.getRemoteAddr());	
			
			try{	
			request.setAttribute("listaAfiliaciones",FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.TB_INTERBANCO,usuario.getTarjeta().getNumero(),"00004"));
			request.getSession().setAttribute("transferencia",tran);
			request.getSession().setAttribute("afiliacion",af);
			request.setAttribute("cuenta",cuenta);
			if(request.getParameter("txtMonto")!=null)
			request.setAttribute("txtMonto",montoConversion);
			request.getSession().setAttribute("cuentaTransf", cuenta);
			request.getSession().setAttribute("cboMoneda",request.getParameter("cboMoneda"));
		}
		catch (ArrayRuleException e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
		    request.getSession().setAttribute("listaAfiliaciones",FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.TB_INTERBANCO,usuario.getTarjeta().getNumero(),"00004"));
			request.setAttribute("mensajeCondicion",((Vector)aplicacion.getMensajePorCodigo("TB05","00002")).elementAt(2).toString());
			request.setAttribute("mensajeLimite",((Vector)aplicacion.getMensajePorCodigo("TB05","00004")).elementAt(2).toString());
			request.setAttribute("listaMonedas",FacadeFactory.getGeneralFacade().getComboDetalleHlp(""));
			
			e.setForward("verTransferencia");
			throw e;
		}
		
		ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
		
		//REALIZAR CONSULTA DE COORDENADAS o TOKEN
		
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
			request.setAttribute("mensajeCondicion",((Vector)aplicacion.getMensajePorCodigo("TB05","00002")).elementAt(2).toString());
			request.setAttribute("mensajeLimite",((Vector)aplicacion.getMensajePorCodigo("TB05","00004")).elementAt(2).toString());
			ar.setForward("verTransferencia");
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
				} else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS)){
						resultCoord=SolicitarServiciosTDC.solicitarTokens(param);
				} else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)){
					generarClaveSms(mapping, form, request, response);
				} 
				
				
			}catch(ArrayRuleException ar){
				log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
				ar.printStackTrace();
				request.getSession().setAttribute("listaAfiliaciones",FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.TB_INTERBANCO,usuario.getTarjeta().getNumero(),"00004"));
				request.setAttribute("mensajeCondicion",((Vector)aplicacion.getMensajePorCodigo("TB05","00002")).elementAt(2).toString());
				request.setAttribute("mensajeLimite",((Vector)aplicacion.getMensajePorCodigo("TB05","00004")).elementAt(2).toString());
				request.setAttribute("listaMonedas",FacadeFactory.getGeneralFacade().getComboDetalleHlp(""));
				ar.setForward("verTransferencia");
				throw ar;
			}
			request.getSession().setAttribute("resultCoord",resultCoord );
		}else{
			throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
		}
		
		request.setAttribute("optCuenta", af.getFlagRegistro());
		request.setAttribute("txtCuentaCCI", af.getCuenta().getNumeroProducto());
		request.setAttribute("txtNombreBenef", af.getBeneficiario());
	
		
		
		return mapping.findForward("continuarTransferencia");
	}
	
	public ActionForward transferir(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		Afiliacion afiliacion = (AfiliacionImpl)request.getSession().getAttribute("afiliacion");
		//String clave = SeguridadUtil.getClaveDesencriptada(request.getParameter("txtNumClave"),request);
		
		
		//FacadeFactory.getGeneralFacade().processValidationCardAhorro(usuario.getTarjeta().getNumero(),usuario.getTarjeta().getClave(),clave,clave);
		Afiliacion af = (Afiliacion)request.getSession().getAttribute("afiliacion");
		
		ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
		
		// Código Nuevo para TDC
		ElemenSeguridad resultCoord = null;

		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		
		ElemenSeguridad coord = (ElemenSeguridad)request.getSession().getAttribute("resultCoord");
				
		String pinblock = ObjectUtil.getClaveDesencriptada(request.getParameter("txtCoordenada"),request);
			
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

		Cuenta cuenta = cta;
		
		String  txtMonto  = request.getParameter("txtMonto");
		//String  cboMoneda = request.getParameter("cboMoneda"); De modifica por observacion Deloitte
		String  cboMoneda = (String)request.getSession().getAttribute("cboMoneda");
								
		TransferenciaImpl transferencia = getTransferencia(request,cuenta);
		transferencia.setSimboloMonedaImpNetoCargo(cta.getSimboloMonedaProducto());
		
		
		request.getSession().setAttribute("fectransib",pe.cosapi.common.ObjectUtil.getFechaActual());
		request.getSession().setAttribute("hortransib",pe.cosapi.common.ObjectUtil.getHHMMSSformateado());
		request.setAttribute("cuenta",cuenta);
		request.getSession().setAttribute("cboMoneda",request.getParameter("cboMoneda"));
		Map map = UtilAction.cargarVar(request,request.getSession().getAttribute(ConstanteSesion.TRANSFERENCIA));
		
		// CLAVE DINAMICA TOKEN Y TDC
		ParametrosElemSegTDC elementos = null;
		try{
			
			//Map map = UtilAction.cargarVar(request,request.getSession().getAttribute(ConstanteSesion.TRANSFERENCIA));
			OperacionImpl.setVariables(map);
			//FacadeFactory.getGeneralFacade().processValidationCardAhorro(usuario.getTarjeta().getNumero(),usuario.getTarjeta().getClave(),clave,clave);
			
			//REALIZAR CONSULTA TIPO CLAVE DINAMICA  --> ADD M.D.B.
			
			try{
				elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
				
				if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS) ||
				   elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
					elementos = SolicitarServiciosTDC.verificarTipoElemento(param,usuario);
				}
			}catch(ArrayRuleException ar){
				log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
				ar.printStackTrace();
				request.setAttribute("txtMonto", txtMonto);
				request.getSession().setAttribute("transferencia",request.getSession().getAttribute("transferencia"));
				ar.setForward("iniciar");
				throw ar;
			}
			
			// Código Nuevo para TDC
		
			MensajesTDC resultado = new MensajesTDC();
			
			if(param!= null && coord!=null ){
			
				try{
					
					if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC))	{
						resultado=SolicitarServiciosTDC.validarTDC(param ,coord,pinblock);
					} else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS)) {
						resultado=SolicitarServiciosTDC.validarTokens(param ,coord,pinblock);
					} else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {
						
						TokenSmsRequest tokenSms = new TokenSmsRequest();
						tokenSms.setCodCliente(usuario.getCodigoCic());
//						tokenSms.setTipoDocumento(Funciones.lpad(datosUsuario.getTipoDocumento(),"0",3));
//						tokenSms.setNroDocumento(datosUsuario.getNroDocumento());
						tokenSms.setTipoDocumento(Funciones.lpad(tipoDoc,"0",3));
						tokenSms.setNroDocumento(numDoc);	
						tokenSms.setCodeSMS(pinblock);
						tokenSms.setTypeTrans(Constante.TIP_OPER_PAGO_TRANSF_IB_DIFERIDO);
						tokenSms.setTypeOp(Constante.COD_TRANSACCION_FINANCIERA);						
						tokenSms.setAmount(txtMonto);
						tokenSms.setTypeCurrency(cboMoneda.equals("USD")?"USD":"PEN");
						
						tokenSms.setCodCliDestinatario(afiliacion.getNumeroServicio());
						tokenSms.setNomCliDestinatario(afiliacion.getBeneficiario());
						
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
					
					OperacionImpl.setVariables(map);
					Transferencia transf =  FacadeFactory.getTransferenciaFacade().transferirInterBanco(cboMoneda, transferencia,usuario,request.getRemoteAddr(),request);
					
					request.getSession().setAttribute(ConstanteSesion.TRANSFERENCIA,transf);
					
					if(usuario.getNotificacion().getEstadoNotificacion().equals("1")){
						ServiciosNotificacionPrincipal.enviarNotificacion(
								usuario,usuario.getNombre(), 
								transf.getSimboloMonedaImporte(), 
								transf.getImporte(),
								usuario.getTipoCambio().getVenta(), 
								Constante.NOTI_TRANSFERENCIA_IB, 
								usuario.getNotificacion());
					}
					
					ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_TRANSFERENCIA_IB_DIFERIDA, ConstanteSesion.TRANSFERENCIA, request);
				}
				catch (ArrayRuleException e) {
					log3.error(e,Constante.ERR_ERROR_VALIDAR_TDC,"");
					e.printStackTrace();
					request.setAttribute("txtMonto", txtMonto);
					request.setAttribute("optCuenta", transferencia.getAfiliacion().getFlagRegistro());
					request.setAttribute("txtCuentaCCI", transferencia.getAfiliacion().getCuenta().getNumeroProducto());
					request.setAttribute("txtNombreBenef", transferencia.getAfiliacion().getBeneficiario());
					request.setAttribute("txtMonto", transferencia.getImporte());
					request.getSession().setAttribute("transferencia",request.getSession().getAttribute("transferencia"));
					//resultCoord = SolicitarServiciosTDC.solicitCoordenada(param);
					
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
					e.setForward("iniciar");
					throw e;
				}
			}
			
		}
		catch (ArrayRuleException e) {
			log3.error(e,Constante.ERR_ERROR_VALIDAR_TDC,"");
			e.printStackTrace();
			request.setAttribute("txtMonto", txtMonto);
			request.getSession().setAttribute("transferencia",request.getSession().getAttribute("transferencia"));
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
			e.setForward("iniciar");
			throw e;
		}
		
		if(af.getFlagRegistro().equals(Constante.NUEV_REGISTRO_TRANSAC)){
			
			String adicionar = request.getParameter("adicionar");
			if(adicionar!=null){
				
				Afiliacion afiliacion2 = new AfiliacionImpl();
				
				afiliacion2 = transferencia.getAfiliacion();
			
				afiliacion2.setTipoAfiliacion(Constante.AFI_TRA_INTER_BANCO);
				
				try {
						Afiliacion afiliar=FacadeFactory.getAfiliacionFacade().afiliar(usuario,afiliacion2);
			
					
				} catch (ArrayRuleException e) {
					log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
					e.printStackTrace();
					e.setForward("transferir");
				}
			}
		
			
		}
		
		return mapping.findForward("transferir");
	}
	
	private TransferenciaImpl getTransferencia(HttpServletRequest request,Cuenta cuenta){
	    TransferenciaImpl transferencia = (TransferenciaImpl)request.getSession().getAttribute("transferencia");
	    transferencia.setAfiliacion((Afiliacion)request.getSession().getAttribute("afiliacion"));
	    
	  
	    String tipoAfect = "2";
	    
	    if (cuenta.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_ME)){
	    	tipoAfect = "4";
	    } else if (cuenta.getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_ME)){
	    	tipoAfect = "4";
	    }
	    
		transferencia.setTipoAfectacion(tipoAfect);
		
		if (transferencia.getAfiliacion().getCuentaPropia().equals("S")){
		    if (cuenta.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_MN)){
				if (request.getParameter("cboMoneda").equals(Constante.MONEDA_SOL)){
				    transferencia.setTipoTransferencia("1");
				    transferencia.setTipoConsulta("02");
				    transferencia.setImporteTransferido(transferencia.getImporte());
				}
				else{//if = USD
				    transferencia.setTipoTransferencia("3");
				    transferencia.setImporteTransferido(request.getParameter("txtMonto"));
				    transferencia.setImporte(new BigDecimal(request.getParameter("txtMonto")));
				    transferencia.setTipoConsulta("03");
				    transferencia.setImporteNeto(new BigDecimal(request.getParameter("txtMonto")).add(transferencia.getComisionIB())); //sumar ib + imp Ref dolares
				}
		    }else if (cuenta.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_ME)){
				if (request.getParameter("cboMoneda").equals(Constante.MONEDA_SOL)){
				    //8431
				    transferencia.setTipoTransferencia("3");
				    transferencia.setImporteTransferido(request.getParameter("txtMonto"));
				    //transferencia.setImporteTransferido(transferencia.getImporte());
				    BigDecimal b1 = new BigDecimal(transferencia.getImporte());
				    //System.out.println("Ver - getImporte():"+transferencia.getImporte());
				    //BigDecimal b1 = new BigDecimal(request.getParameter("txtMonto"));
				    //transferencia.setImporte(new BigDecimal(request.getParameter("txtMonto")));
				    transferencia.setTipoConsulta("08");
				    transferencia.setImporteNeto(b1.add(transferencia.getComisionIB())); //sumar ib + imp Ref dolares
				    //System.out.println("Ver - getImporteNeto:"+transferencia.getImporteNeto());
				    //System.out.println("Ver - getImporteTransferido:"+transferencia.getImporteTransferido());
				}
				else{//if ='USD'8411
				    transferencia.setTipoTransferencia("1");
				    transferencia.setTipoConsulta("09");
				    transferencia.setImporteTransferido(transferencia.getImporte());
				}
		   }else if (cuenta.getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_MN)){
		       //0411
				if (request.getParameter("cboMoneda").equals(Constante.MONEDA_SOL)){
				    transferencia.setTipoTransferencia("1");
				    transferencia.setTipoConsulta("11");
				    transferencia.setImporteTransferido(transferencia.getImporte());
				}
				else if (request.getParameter("cboMoneda").equals(Constante.MONEDA_DOLAR)){//if = USD
				    //0431
				    transferencia.setTipoTransferencia("3");
				    //transferencia.setImporteTransferido(transferencia.getImporte());
				    transferencia.setImporteTransferido(request.getParameter("txtMonto"));
				    transferencia.setImporte(new BigDecimal(request.getParameter("txtMonto")));
				    transferencia.setTipoConsulta("12");
				    transferencia.setImporteNeto(new BigDecimal(request.getParameter("txtMonto")).add(transferencia.getComisionIB())); //sumar ib + imp Ref dolares
				}
		   }else if (cuenta.getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_ME)){
		       //6431
				if (request.getParameter("cboMoneda").equals(Constante.MONEDA_SOL)){
				    transferencia.setTipoTransferencia("3");
				    transferencia.setImporteTransferido(request.getParameter("txtMonto"));
				    //transferencia.setImporteTransferido(transferencia.getImporte());
				    BigDecimal b = new BigDecimal(transferencia.getImporte().replace(",", ""));
				    //BigDecimal b = new BigDecimal(request.getParameter("txtMonto"));
				    //transferencia.setImporte(new BigDecimal(request.getParameter("txtMonto")));
				    //System.out.println("getImporte()AAA:"+transferencia.getImporte());
				    transferencia.setTipoConsulta("20");
				    transferencia.setImporteNeto(b.add(transferencia.getComisionIB()));
				}
				else if (request.getParameter("cboMoneda").equals(Constante.MONEDA_DOLAR)){//if = USD-6411
				    transferencia.setTipoTransferencia("1");
				    transferencia.setTipoConsulta("23");
				    transferencia.setImporteTransferido(transferencia.getImporte());
				}
		   }
		}else if (transferencia.getAfiliacion().getCuentaPropia().equals("N")){
		    if (cuenta.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_MN)){
			    if (request.getParameter("cboMoneda").equals(Constante.MONEDA_SOL)){
				    transferencia.setTipoTransferencia("2");
				    transferencia.setTipoConsulta("05");
				    transferencia.setImporteTransferido(transferencia.getImporte());
				}
				else{//if = USD
				    transferencia.setTipoTransferencia("4");
				    transferencia.setImporteTransferido(request.getParameter("txtMonto"));
				    transferencia.setImporte(new BigDecimal(request.getParameter("txtMonto")));
				    transferencia.setTipoConsulta("06");
				    transferencia.setImporteNeto(new BigDecimal(request.getParameter("txtMonto")).add(transferencia.getComisionIB())); //sumar ib + imp Ref dolares
				}
		    }else if (cuenta.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_ME)){
			    if (request.getParameter("cboMoneda").equals(Constante.MONEDA_SOL)){
				    transferencia.setTipoTransferencia("4");
				    transferencia.setTipoConsulta("14");
				    transferencia.setImporteTransferido(request.getParameter("txtMonto"));
				    //transferencia.setImporteTransferido(transferencia.getImporte());
				    //System.out.println("transferencia.getImporte():"+transferencia.getImporte());
				    BigDecimal b = new BigDecimal(transferencia.getImporte().replace(",", ""));
				    //BigDecimal b = new BigDecimal(request.getParameter("txtMonto"));
				    //transferencia.setImporte(new BigDecimal(request.getParameter("txtMonto")));
				    transferencia.setImporteNeto(b.add(transferencia.getComisionIB())); //sumar ib + imp Ref dolares
				   
				}
				else{//if = USD
				    transferencia.setTipoTransferencia("2");
				    transferencia.setImporteTransferido(transferencia.getImporte());
				    transferencia.setImporte(new BigDecimal(request.getParameter("txtMonto")));
				    transferencia.setTipoConsulta("15");
				}
		    }else if (cuenta.getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_MN)){
			    if (request.getParameter("cboMoneda").equals(Constante.MONEDA_SOL)){
				    transferencia.setTipoTransferencia("2");
				    transferencia.setTipoConsulta("17");
				    transferencia.setImporteTransferido(request.getParameter("txtMonto"));
				}
				else{//if = USD
				    transferencia.setTipoTransferencia("4");
				    transferencia.setTipoConsulta("18");
				    //transferencia.setImporteTransferido(transferencia.getImporte());
				    transferencia.setImporteTransferido(request.getParameter("txtMonto"));
				    transferencia.setImporte(new BigDecimal(request.getParameter("txtMonto")));
				    BigDecimal b = new BigDecimal(request.getParameter("txtMonto").replace(",", ""));
				    transferencia.setImporteNeto(b.add(transferencia.getComisionIB())); //sumar ib + imp Ref dolares
				}
		    }else if (cuenta.getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_ME)){
			    if (request.getParameter("cboMoneda").equals(Constante.MONEDA_SOL)){
				    transferencia.setTipoTransferencia("4");
				    transferencia.setTipoConsulta("22");
				    transferencia.setImporteTransferido(request.getParameter("txtMonto"));
				 
				    BigDecimal b = new BigDecimal(transferencia.getImporte().replace(",", ""));
				 
				    transferencia.setImporteNeto(b.add(transferencia.getComisionIB())); //sumar ib + imp Ref dolares
				 
				}
				else{//if = USD
				   transferencia.setTipoTransferencia("2");
				    transferencia.setTipoConsulta("24");
				    transferencia.setImporteTransferido(transferencia.getImporte());
				}
		    }
		}
		transferencia.setCuentaOrigen(cuenta);
	    return transferencia;
	}
	
	
	public void reGenerarClaveSms(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		String  monto  = request.getParameter("txtMonto");
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
		
		Cuenta cuenta = (CuentaImpl)request.getSession().getAttribute("cuentaTransf");
		
		String  cboMoneda = (String)request.getSession().getAttribute("cboMoneda");
		
		
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
					
					tokenSms.setTypeTrans(Constante.TIP_OPER_PAGO_TRANSF_IB_DIFERIDO);
					tokenSms.setTypeOp(Constante.COD_TRANSACCION_FINANCIERA);
					tokenSms.setAmount(monto);
					tokenSms.setTypeCurrency(cboMoneda.equals("USD")?"USD":"PEN");
					
					tokenSms.setCodCliDestinatario(afiliacion.getNumeroServicio());
					tokenSms.setNomCliDestinatario(afiliacion.getBeneficiario());

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
			//loadMessage(request);
			e.setForward("verMBTransferencia");
			throw e;
		}

		
	}
	
	
public void generarClaveSms(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		String  monto  = request.getParameter("txtMonto");
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
		
		Cuenta cuenta = (CuentaImpl)request.getSession().getAttribute("cuentaTransf");
		
		String  cboMoneda = (String)request.getSession().getAttribute("cboMoneda");
		
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
					
					tokenSms.setTypeTrans(Constante.TIP_OPER_PAGO_TRANSF_IB_DIFERIDO);
					tokenSms.setTypeOp(Constante.COD_TRANSACCION_FINANCIERA);
					tokenSms.setAmount(monto);
					tokenSms.setTypeCurrency(cboMoneda.equals("USD")?"USD":"PEN");
					
					tokenSms.setCodCliDestinatario(afiliacion.getNumeroServicio());
					tokenSms.setNomCliDestinatario(afiliacion.getBeneficiario());

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
			//loadMessage(request);
			e.setForward("verMBTransferencia");
			throw e;
		}

		
	}
}
