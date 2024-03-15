package pe.bn.trasferenciacontacto.action;

import java.util.ArrayList;
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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import pe.bn.afiliacion.action.form.AfiliacionBancaCelularForm;
import pe.bn.afiliacion.action.form.AfiliacionDatosClientesForm;
import pe.bn.afiliacion.action.form.AfiliacionDatosContactoForm;
import pe.bn.afiliacion.domain.Afiliacion;
import pe.bn.afiliacion.domain.impl.AfiliacionImpl;
import pe.bn.cldinamica.action.SolicitarServiciosTDC;
import pe.bn.cldinamica.action.form.DataTDC;
import pe.bn.cldinamica.action.form.ElemenSeguridad;
import pe.bn.cldinamica.action.form.MensajesActDesTDC;
import pe.bn.cldinamica.action.form.MensajesTDC;
import pe.bn.cldinamica.action.form.ParametrosElemSegTDC;
import pe.bn.cldinamica.action.form.ParametrosTDC;
import pe.bn.listener.ConstanteParametros;
import pe.bn.listener.Parametro;
import pe.bn.listener.Util;
import pe.bn.login.action.LoginAction;
import pe.bn.login.domain.Menu;
import pe.bn.notificaciones.action.ServicioNotificacionConstancia;
import pe.bn.notificaciones.model.ParametrosSIAT;
import pe.bn.tarjeta.domain.Tarjeta;
import pe.bn.transferencia.domain.Transferencia;
import pe.bn.transferencia.domain.impl.TransferenciaImpl;
import pe.bn.trasferenciacontacto.domain.TransferenciaContacto;
import pe.bn.trasferenciacontacto.domain.impl.TransferenciaContactoImpl;
import pe.com.bn.common.Funciones;
import pe.com.bn.common.LoadDataProperties;
import pe.com.bn.common.sati.bean.TokenSmsActivacionRequest;
import pe.com.bn.common.sati.bean.TokenSmsMigracionRequest;
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
import pe.cosapi.domain.impl.DetalleAyudaDatosImpl;
import pe.cosapi.domain.impl.OperacionImpl;

public class trasferenciaContactoAction extends GrandActionAbstract {

	private static final long serialVersionUID = -626785110123831683L;
	private static LoggerSati log3 = LoggerSati.getInstance(trasferenciaContactoAction.class.getName());
	BNAplicacion aplicacion =BNAplicacion.getInstance();
	
	@Override
	public ActionForward iniciar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String forward = "";		
		forward = "";		
		
		ParametrosElemSegTDC elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento"); //5,6,7
		request.getSession().setAttribute("mensajeClaveSms", null);
				
		Usuario usuario 		= (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
				
		String celular="";
		if(usuario.getCelular().length()> 9){
			celular=usuario.getCelular().trim().substring(1, 10);
		}
		else{
			celular=usuario.getCelular().trim();
		}
			
		TransferenciaContacto responseConsulta = new TransferenciaContactoImpl();
		
				
		try{
		
				
		responseConsulta=FacadeFactory.getTransferenciaContactoFacade().consultarContacto(usuario, request.getRemoteAddr());
		
		if(responseConsulta.getCodigoErr().equals(Constante.CONST_PROCESO_OK)){			
			if(usuario.getCciCliente().equals(responseConsulta.getCciContacto())){
				if(responseConsulta.getEstadoAfil().equals(Constante.CONSTANTE_AFIL_CONTACTO)){				
					
					if(elementos.getPermiteOperaciones().equals(Constante.CODIGO_NO_PERMITE_OPERACIONES)){
						request.getSession().setAttribute("flagPendOpera",elementos.getPermiteOperaciones());
					}else{
						request.getSession().setAttribute("flagPendOpera","1");
					}
					
					AfiliacionDatosContactoForm frmContacto = (AfiliacionDatosContactoForm) form;

					frmContacto.setTxtNumeroPersonal(responseConsulta.getNumeroCelularContacto());
				
					if(usuario.getCelular().length()> 9){
						celular=usuario.getCelular().trim().substring(1, 10);
						}
						else{
							celular=usuario.getCelular().trim();
						
						}
					if(celular.equals(responseConsulta.getNumeroCelularContacto()))frmContacto.setCmbOperador(usuario.getOpeCelular());
					frmContacto.setTxtNumeroPersonal(responseConsulta.getNumeroCelularContacto());
					
					request.getSession().setAttribute("lstOperador",FacadeFactory.getGeneralFacade().getComboDetalleAyuda1(Constante.DAT_CLIENTES_TIPO_OPERADOR));
					forward = "datosAfiliacion";
				}else{
					
					if(Constante.CODIGO_SI_PENDIENTE_ENROLAMIENTO.equals(elementos.getPendienteEnrolamiento()))			
					{
						return mapping.findForward("pendienteEnrolamiento");
					}
					
					if(elementos.getPermiteOperaciones().equals(Constante.CODIGO_NO_PERMITE_OPERACIONES))			
					{
						return mapping.findForward("noPermiteOperaciones");
					}	
					
					forward = "iniciarAfiliacion";	
				}				
			}else{
				if(Constante.CODIGO_SI_PENDIENTE_ENROLAMIENTO.equals(elementos.getPendienteEnrolamiento()))			
				{
					return mapping.findForward("pendienteEnrolamiento");
				}
				
				if(elementos.getPermiteOperaciones().equals(Constante.CODIGO_NO_PERMITE_OPERACIONES))			
				{
					return mapping.findForward("noPermiteOperaciones");
				}	
				forward = "iniciarAfiliacion";	
			}			
		}else{
			if(Constante.CODIGO_SI_PENDIENTE_ENROLAMIENTO.equals(elementos.getPendienteEnrolamiento()))			
			{
				return mapping.findForward("pendienteEnrolamiento");
			}
			
			if(elementos.getPermiteOperaciones().equals(Constante.CODIGO_NO_PERMITE_OPERACIONES))			
			{
				return mapping.findForward("noPermiteOperaciones");
			}	
			forward = "iniciarAfiliacion";	
		}
		
		//MGL
		/*
		if(responseConsulta.getCodigoErr().equals(Constante.CONST_PROCESO_OK) && responseConsulta.getEstadoAfil().equals(Constante.CONSTANTE_AFIL_CONTACTO)){
			AfiliacionDatosContactoForm frmContacto = (AfiliacionDatosContactoForm) form;

			frmContacto.setTxtNumeroPersonal(responseConsulta.getNumeroCelularContacto());
		
			if(usuario.getCelular().length()> 9){
				celular=usuario.getCelular().trim().substring(1, 10);
				}
				else{
					celular=usuario.getCelular().trim();
				
				}
			if(celular.equals(responseConsulta.getNumeroCelularContacto()))frmContacto.setCmbOperador(usuario.getOpeCelular());
			frmContacto.setTxtNumeroPersonal(responseConsulta.getNumeroCelularContacto());
			
			request.getSession().setAttribute("lstOperador",FacadeFactory.getGeneralFacade().getComboDetalleAyuda1(Constante.DAT_CLIENTES_TIPO_OPERADOR));
			forward = "datosAfiliacion";
		}else if(responseConsulta.getEstadoAfil().equals(Constante.CONSTANTE_SIN_AFIL_CONTACTO)){
			forward = "iniciarAfiliacion";	
		}
		else{
			forward = "iniciarAfiliacion";		
		}
		*/

		request.getSession().setAttribute("celular",celular );
		
		
		responseConsulta.setDescEntidad(usuario.getEntidadCuentaDesc());
		responseConsulta.setCodigoEntidad(usuario.getEntidadCuenta());
		
		request.getSession().setAttribute(ConstanteSesion.TRANSF_CONTACTO,responseConsulta);
		
		request.setAttribute("lstOperador",FacadeFactory.getGeneralFacade().getComboDetalleAyuda1(Constante.DAT_CLIENTES_TIPO_OPERADOR));
		
		}catch (ArrayRuleException ar) {
			ar.printStackTrace();
			ar.setForward("iniciarAfiliacion");
			throw ar;
		}
		
		return mapping.findForward(forward);		
	}
		

	public ActionForward cuentaEnlazar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Usuario usuario 		= (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		
		AfiliacionDatosContactoForm frmContacto = (AfiliacionDatosContactoForm) form;

		String celular="";
				
		if(usuario.getCelular().length()> 9){
					celular=usuario.getCelular().trim().substring(1, 10);
		}
		else{
					celular=usuario.getCelular().trim();
					
		}
		
		frmContacto.setTxtNumeroPersonal(celular);
		frmContacto.setCmbOperador(usuario.getOpeCelular());
		
		
		request.getSession().setAttribute("celular",celular );
		
		request.setAttribute("lstOperador",FacadeFactory.getGeneralFacade().getComboDetalleAyuda1(Constante.DAT_CLIENTES_TIPO_OPERADOR));
		
		String forward = "";
		forward = "iniciarCuentaEnlazar";
		return mapping.findForward(forward);
	}
	
	
	public ActionForward confirmarCambioCelular(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		
		String forward = "";
		
		TransferenciaContactoImpl transferenciaContacto = new TransferenciaContactoImpl();
		
		AfiliacionDatosContactoForm frmContacto = (AfiliacionDatosContactoForm) form;
		
		try {
		transferenciaContacto.setCuenta(request.getParameter("cmbCuenta"));
		transferenciaContacto.setNumeroCelularCambio(request.getParameter("txtNumeroPersonal"));
		transferenciaContacto.setOperadorCelularCambio(request.getParameter("cmbOperador"));
		transferenciaContacto.setIndCambioCelular(request.getParameter("indCambioCelular"));
		
			
		}catch (ArrayRuleException ar) {
			ar.printStackTrace();
			ar.setForward("iniciarCuentaEnlazar");
			throw ar;
		}
		
		String celular="";
		
		if(transferenciaContacto.getIndCambioCelular().equals("1")){
			celular=transferenciaContacto.getNumeroCelularCambio();
		}else{
			celular=frmContacto.getTxtNumeroPersonal();
		}
		
		request.getSession().setAttribute("celular",celular );
		
		request.getSession().setAttribute(ConstanteSesion.TRANSF_CONTACTO, transferenciaContacto);
		
		forward = "confirmarCambioCelular";
		return mapping.findForward(forward);
	}
	
	public ActionForward verificarCelular(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub		
		String forward = "";
		
		Usuario usuario 		= (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		TransferenciaContactoImpl transferenciaContacto = (TransferenciaContactoImpl)request.getSession().getAttribute(ConstanteSesion.TRANSF_CONTACTO);

		//parametro de longitud del sms
		List lista = null;
		String valor=""; 
		lista = DAOFactory.getGeneraDAO().getComboDetalleHlpHijo("02001","1");
		if(lista!=null){
			for (Iterator iter = lista.iterator(); iter.hasNext();) {
				ComboUtil element = (ComboUtil) iter.next();
				if(element.getCodigo().equals("1")){
					valor=element.getDescripcion();
					request.getSession().setAttribute("longitudSmsTCAfi",valor);
					System.out.println("longitudSmsTCAfi:::"+valor);
				}	
			}
		}

		
		try {
		
			transferenciaContacto.setCuenta(request.getParameter("cmbCuenta"));
			transferenciaContacto.setNumeroCelularCambio(request.getParameter("txtNumeroPersonal"));
			transferenciaContacto.setOperadorCelularCambio(request.getParameter("cmbOperador"));
			transferenciaContacto.setIndCambioCelular(request.getParameter("indCambioCelular"));
			
			System.out.println("transferenciaContacto.getNumeroCelularCambio():"+transferenciaContacto.getNumeroCelularCambio());
			System.out.println("transferenciaContacto.getOperadorCelularCambio():"+transferenciaContacto.getOperadorCelularCambio());
			System.out.println("transferenciaContacto.getIndCambioCelular():"+transferenciaContacto.getIndCambioCelular());
	
		
		FacadeFactory.getTransferenciaContactoFacade().enviaSmsCelularContacto( usuario,transferenciaContacto, request.getRemoteAddr());
		
		}catch (ArrayRuleException ar) {
			ar.printStackTrace();
			ar.setForward("iniciarCuentaEnlazar");
			throw ar;
		}
		
		String celular="";
		
		if(transferenciaContacto.getIndCambioCelular().equals("1")){
			celular=transferenciaContacto.getNumeroCelularCambio();
		}else{
			celular=usuario.getCelularFormat();
		}
		
		request.getSession().setAttribute("celular",celular );
		
		Cuenta cuentaCont 		= (Cuenta) request.getSession().getAttribute("ctaContactoSession");
		request.getSession().setAttribute("cuenta",cuentaCont);
		
		request.getSession().setAttribute(ConstanteSesion.TRANSF_CONTACTO, transferenciaContacto);
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		forward = "verificarCelular";
		
		return mapping.findForward(forward);		
	}
	
	public ActionForward confirmarAfiliacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
		String codeSMS="";
		
		try {
			
			
			Usuario usuario 		= (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
			TransferenciaContactoImpl trans = (TransferenciaContactoImpl)request.getSession().getAttribute(ConstanteSesion.TRANSF_CONTACTO);
			
			
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
				cta	= (CuentaImpl)usuario.getMapCuentas().get(trans.getCuenta());
			}else{
				for(int i=0; i<x.size();i++){
					cta=(CuentaImpl)x.get(i);
					if(!cta.getTipoProducto().equals("44")&& cta.getNumeroProducto().equals(trans.getCuenta())){
						break;
						
					}	
				}}
			
			Cuenta cuenta = cta;
			trans.setCuentaOrigen(cuenta);
			request.getSession().setAttribute(ConstanteSesion.TRANSF_CONTACTO, trans);
			request.getSession().setAttribute("ctaContactoSession",cuenta);
			
			codeSMS=ObjectUtil.getClaveDesencriptada(request.getParameter("txtCodigo"),request);
			
			
			BNAplicacion aplicacion = BNAplicacion.getInstance();
			request.getSession().setAttribute("mensajeCondicion",((Vector)aplicacion.getMensajePorCodigo("BC01","00014")).elementAt(2).toString().trim());
				
			String forward = "";
			
			FacadeFactory.getTransferenciaContactoFacade().validaCelularContacto( usuario, codeSMS,trans ,request.getRemoteAddr());
			

			
//			REALIZAR CONSULTA TIPO CLAVE DINAMICA  --> ADD M.D.B.
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
				ar.setForward("verificarCelular");
				throw ar;
			}
			
//			REALIZAR CONSULTA DE COORDENADAS
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
					ar.setForward("verificarCelular");
					throw ar;
				}
				request.getSession().setAttribute("resultCoord",resultCoord );
			}
			else{
				
				throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
			}

			
		} catch (ArrayRuleException ar) {
			ar.printStackTrace();
			ar.setForward("verificarCelular");
			throw ar;
		}
		
		request.getSession().setAttribute("fectranscon",pe.cosapi.common.ObjectUtil.getFechaActual());
		
		Cuenta cuentaCont 		= (Cuenta) request.getSession().getAttribute("ctaContactoSession");
			
		request.getSession().setAttribute("cuenta",cuentaCont);

				
		return mapping.findForward("confirmarAfiliacion");
			
	}
	
	public ActionForward constanciaAfiliacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		String forward = "";
		forward = "constanciaAfiliacion";
		ParametrosTDC param = (ParametrosTDC) request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		TokenSmsRequest tokenSms = new TokenSmsRequest();
		
		try {
			
			Usuario usuario 		= (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
			Cuenta cuentaCont 		= (Cuenta) request.getSession().getAttribute("ctaContactoSession");
			
			TransferenciaContactoImpl transferencia = (TransferenciaContactoImpl)request.getSession().getAttribute(ConstanteSesion.TRANSF_CONTACTO);
			
			TransferenciaContacto responseRegistro = new TransferenciaContactoImpl();
			
			ElemenSeguridad resultCoord = null;
			ElemenSeguridad coord = (ElemenSeguridad) request.getSession().getAttribute("resultCoord");
			
			String pinblock = ObjectUtil.getClaveDesencriptada(request.getParameter("txtCoordenada"), request);
			ParametrosElemSegTDC elementos = null;
			MensajesTDC resultado = new MensajesTDC();
			try {

				elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");


			} catch (ArrayRuleException ar) {
				log3.error(ar, Constante.ERR_SOLICITAR_COORDENADA, "");
				ar.printStackTrace();
				ar.setForward("confirmarAfiliacion");
				throw ar;
			}
			
			if (elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)) {
				resultado = SolicitarServiciosTDC.validarTDC(param, coord,pinblock);
			} else if (elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS)) {
				resultado = SolicitarServiciosTDC.validarTokens(param,coord, pinblock);
			} else if (elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {
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
				
				tokenSms.setCodCliente(usuario.getCodigoCic());
				tokenSms.setTipoDocumento(Funciones.lpad(tipoDoc,"0",3));
				tokenSms.setNroDocumento(numDoc);
				tokenSms.setCodeSMS(pinblock);
				tokenSms.setTypeTrans(Constante.TIP_OPER_CONFIGURACION_AF_CONTACTOS);
				tokenSms.setTypeOp(Constante.COD_TRANSACCION_ADMINISTRATIVA);						
				tokenSms.setMsgClient("Validacion de Clave Dinamica Digital");
				tokenSms.setTypeCurrency("PEN");
				
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
					
			//Registrar Afiliacion a contactos
			responseRegistro=FacadeFactory.getTransferenciaContactoFacade().registroContacto( usuario,cuentaCont, transferencia, request.getRemoteAddr(),request);
			responseRegistro.setCodigoEntidad(usuario.getEntidadCuenta());
			responseRegistro.setCciContacto(usuario.getCciCliente());
			responseRegistro.setDescEntidad(usuario.getEntidadCuentaDesc());
			
			Map map = UtilAction.cargarVar(request,responseRegistro);
			OperacionImpl.setVariables(map);
			
			request.getSession().setAttribute("cuenta",cuentaCont);
			request.getSession().setAttribute(ConstanteSesion.TRANSF_CONTACTO,responseRegistro);
			ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_AFILIACION_CONTACTO, ConstanteSesion.TRANSF_CONTACTO, request);
			
			//Actualizar celular del cliente en BDUC
			if(transferencia.getIndCambioCelular().equals("1")){
			
			List listOperador = new ArrayList();
			listOperador = FacadeFactory.getGeneralFacade().getComboDetalleAyuda1(Constante.DAT_CLIENTES_TIPO_OPERADOR);
			String nombreOperador="";

				for (int i = 0; i < listOperador.size(); i++) {
	                DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
	                dHelp_ = (DetalleAyudaDatosImpl) listOperador.get(i);
	                if (transferencia.getOperadorCelularCambio().equals(dHelp_.getCodigoDetalleAyuda())) {
	                       if (transferencia.getOperadorCelularCambio().equals(Constante.DAT_CLIENTES_OPERADOR_POR_DEFECTO)) {
	                    	   nombreOperador= Constante.DAT_CLIENTES_SIN_DESCRIPCION;
	                             break;
	                       } else {
	                    	   nombreOperador =dHelp_.getDescripcionDetalle().toUpperCase();
	                       }
	                }
	            }
			
				//Consultar cuenta
				List x=usuario.getCuentas();
				Afiliacion afiliaCliente = new AfiliacionImpl();
                CuentaImpl cuenta1 = (CuentaImpl) x.get(0);
                afiliaCliente.setCuenta(cuenta1);
                
				String celularBDUC= transferencia.getOperadorCelularCambio().concat(transferencia.getNumeroCelularCambio());
				Afiliacion afil = FacadeFactory.getAfiliacionFacade().getActualizaCelularCliente(Constante.DAT_CLIENTES_ACTUALIZACION_CEL, usuario,request.getRemoteAddr(),celularBDUC ,nombreOperador,afiliaCliente ,transferencia,request);
								
				request.getSession().setAttribute(ConstanteSesion.AFILIAR,afil);
				ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_ACTUALIZACION_DATOS_CLIENTE_CEL, ConstanteSesion.AFILIAR, request);
				
				usuario.setCelular(celularBDUC);
				usuario.setOpeCelular(transferencia.getOperadorCelularCambio());
	          	usuario.setCelularFormat(transferencia.getNumeroCelularCambio());
	          	request.getSession().setAttribute(ConstanteSesion.USUARIO_EN_SESION,usuario);
			}
			
	
          	
          	
		
		
		
			
			
		} catch (ArrayRuleException ar) {
			ar.printStackTrace();
			ar.setForward("confirmarAfiliacion");
			throw ar;
		}
		
		
		return mapping.findForward(forward);
	}
	
	
	
	public ActionForward datosAfiliacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String forward = "";
		
		System.out.println("datosAfiliacion.....");
		
		Usuario usuario 		= (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		
		AfiliacionDatosContactoForm frmContacto = (AfiliacionDatosContactoForm) form;
		TransferenciaContacto responseConsulta = new TransferenciaContactoImpl();
		String celular ="";
		
		try{
				
		if(usuario.getCelular().length()> 9){
					celular=usuario.getCelular().trim().substring(1, 10);
		}
		else{
						celular=usuario.getCelular().trim();
					
					}
		System.out.println("celular:"+celular);
		System.out.println("usuario.getOpeCelular():"+usuario.getOpeCelular());
		System.out.println("usuario.getEntidadCuenta():"+usuario.getEntidadCuenta());
		System.out.println("usuario.getEntidadCuentaDesc():"+usuario.getEntidadCuentaDesc());
			
		responseConsulta=FacadeFactory.getTransferenciaContactoFacade().consultarContacto(usuario, request.getRemoteAddr());
		responseConsulta.setCodigoEntidad(usuario.getEntidadCuenta());
		responseConsulta.setCciContacto(usuario.getCciCliente());
		responseConsulta.setDescEntidad(usuario.getEntidadCuentaDesc());
		
		}catch (ArrayRuleException ar) {
			ar.printStackTrace();
			ar.setForward("datosAfiliacion");
			throw ar;
		}
		
		if(celular.equals(responseConsulta.getNumeroCelularContacto()))frmContacto.setCmbOperador(usuario.getOpeCelular());
		frmContacto.setTxtNumeroPersonal(responseConsulta.getNumeroCelularContacto());
		
		request.setAttribute("lstOperador",FacadeFactory.getGeneralFacade().getComboDetalleAyuda1(Constante.DAT_CLIENTES_TIPO_OPERADOR));
		request.getSession().setAttribute(ConstanteSesion.TRANSF_CONTACTO,responseConsulta);
		forward = "datosAfiliacion";
		return mapping.findForward(forward);
	}
	
	public ActionForward iniciarDesafiliacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		String forward = "";
		forward = "iniciarDesafiliacion";
		
		TransferenciaContacto transferenciaContacto = new TransferenciaContactoImpl();
		
		Usuario usuario 		= (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		java.util.List listMotivos = new java.util.ArrayList();
		
	
		transferenciaContacto=(TransferenciaContacto)request.getSession().getAttribute(ConstanteSesion.TRANSF_CONTACTO);
		
		try {
			listMotivos=FacadeFactory.getTransferenciaContactoFacade().obtenerMotivoDes(usuario, request.getRemoteAddr());

		} catch (ArrayRuleException ar) {
			ar.printStackTrace();
			ar.setForward("confirmarDesafiliacion");
			throw ar;
		}
		
		request.getSession().setAttribute("lstMotivos",listMotivos);
		request.getSession().setAttribute("TRANSF_CONTACTO",transferenciaContacto);
		
		
		return mapping.findForward(forward);
	}
	
	public ActionForward confirmarDesafiliacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		String forward = "";
		forward = "confirmarDesafiliacion";
		TransferenciaContacto transferenciaContacto = new TransferenciaContactoImpl();
		
		transferenciaContacto=(TransferenciaContacto)request.getSession().getAttribute(ConstanteSesion.TRANSF_CONTACTO);
		
		try {
			String motivo = request.getParameter("cmbMotivo");
			String comentario = request.getParameter("txaComentario");
			String codMotivo="";
			String desMotivo="";
			
			if(motivo != null && !motivo.trim().equals("")){
				
				String[] var = motivo.split("-");
				codMotivo=var[0];
				desMotivo=var[1];
			}
			
			transferenciaContacto.setCodMotivoDesf(codMotivo);
			transferenciaContacto.setDescripMotivoDesf(desMotivo);
			transferenciaContacto.setComentarioDesf(comentario == null?"-":comentario);	
			
			
			request.getSession().setAttribute("transferenciaContacto", transferenciaContacto);
			
		}catch (ArrayRuleException ar) {
			ar.printStackTrace();
			ar.setForward("iniciarCuentaEnlazar");
			throw ar;
		}
		
		
		return mapping.findForward(forward);
	}
	
	public ActionForward constanciaDesafiliacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		String forward = "";
		forward = "constanciaDesafiliacion";
		
		Usuario usuario 		= (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		TransferenciaContacto responseDes = new TransferenciaContactoImpl();
		TransferenciaContactoImpl transferencia = (TransferenciaContactoImpl)request.getSession().getAttribute("transferenciaContacto");
		
		
		try {
			
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
				cta	= (CuentaImpl)usuario.getMapCuentas().get(transferencia.getNroCuentaContacto());
			}else{
				for(int i=0; i<x.size();i++){
					cta=(CuentaImpl)x.get(i);
					if(!cta.getTipoProducto().equals("44")&& cta.getNumeroProducto().equals(transferencia.getNroCuentaContacto())){
						break;
						
					}	
				}}
			
			Cuenta cuenta = cta;
			transferencia.setCuentaOrigen(cuenta);
			responseDes=FacadeFactory.getTransferenciaContactoFacade().eliminarContacto(usuario, transferencia,request.getRemoteAddr(),request);
			responseDes.setComentarioDesf(transferencia.getComentarioDesf());
			responseDes.setCodigoEntidad(usuario.getEntidadCuenta());
			responseDes.setDescEntidad(usuario.getEntidadCuentaDesc());
			responseDes.setCciContacto(usuario.getCciCliente());
			responseDes.setCuentaOrigen(cuenta);
			responseDes.setDescripMotivoDesf(transferencia.getDescripMotivoDesf());
			
			request.getSession().setAttribute(ConstanteSesion.TRANSF_CONTACTO,responseDes);
			
			ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_DESAFILIACION_CONTACTO, ConstanteSesion.TRANSF_CONTACTO, request);
			
			Map map = UtilAction.cargarVar(request,responseDes);
			OperacionImpl.setVariables(map);
		
		
			request.getSession().setAttribute("cuenta",cuenta);
			

		} catch (ArrayRuleException ar) {
			ar.printStackTrace();
			ar.setForward("confirmarDesafiliacion");
			throw ar;
		}
		
		Map map = UtilAction.cargarVar(request,responseDes);
		OperacionImpl.setVariables(map);
			
		request.getSession().setAttribute("fectranscon",pe.cosapi.common.ObjectUtil.getFechaActual());
		request.getSession().setAttribute("hortranscon",pe.cosapi.common.ObjectUtil.getHHMMSSformateado());
		
		request.getSession().setAttribute(ConstanteSesion.TRANSF_CONTACTO,responseDes);
		
		
		return mapping.findForward(forward);
	}
	
	
	public ActionForward iniciarCambio(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		String forward = "";
		forward = "iniciarCambio";
		TransferenciaContactoImpl transferenciaContacto = (TransferenciaContactoImpl)request.getSession().getAttribute(ConstanteSesion.TRANSF_CONTACTO);
		
		transferenciaContacto.setIndCambioCelular(request.getParameter("indCambioCelular"));
				
		
		request.getSession().setAttribute("TRANSF_CONTACTO",transferenciaContacto);

		
		return mapping.findForward(forward);
	}
	
	
	public ActionForward confirmarCambio(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		Usuario usuario 		= (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);		
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		//TransferenciaContactoImpl transferenciaContacto = new TransferenciaContactoImpl();
		TransferenciaContactoImpl transferenciaContacto = (TransferenciaContactoImpl)request.getSession().getAttribute(ConstanteSesion.TRANSF_CONTACTO);
		
		//parametro de longitud del sms
		List lista = null;
		String valor=""; 
		lista = DAOFactory.getGeneraDAO().getComboDetalleHlpHijo("02001","1");
		if(lista!=null){
			for (Iterator iter = lista.iterator(); iter.hasNext();) {
				ComboUtil element = (ComboUtil) iter.next();
				if(element.getCodigo().equals("1")){
					valor=element.getDescripcion();
					request.getSession().setAttribute("longitudSmsTCAct",valor);
					System.out.println("longitudSmsTCAct:::"+valor);
				}	
			}
		}
		
		try {	
	
			request.setAttribute("lstOperador",FacadeFactory.getGeneralFacade().getComboDetalleAyuda1(Constante.DAT_CLIENTES_TIPO_OPERADOR));
			AfiliacionDatosContactoForm frmContacto = (AfiliacionDatosContactoForm) form;
			frmContacto.setTxtNumeroPersonal(transferenciaContacto.getNumeroCelularContacto());
			frmContacto.setCmbOperador("0");
			
			transferenciaContacto.setCuenta(request.getParameter("cmbCuenta"));			
			//FacadeFactory.getTransferenciaContactoFacade().enviaSmsCelularContacto( usuario,transferenciaContacto ,request.getRemoteAddr());
			
			}catch (ArrayRuleException ar) {
				ar.printStackTrace();
				ar.setForward("iniciarCuentaEnlazar");
				throw ar;
			}
		
		Cuenta cuentaCont 		= (Cuenta) request.getSession().getAttribute("ctaContactoSession");
		request.getSession().setAttribute("cuenta",cuentaCont);
		
		request.getSession().setAttribute(ConstanteSesion.TRANSF_CONTACTO, transferenciaContacto);
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		
		String forward = "";
		forward = "confirmarCambio";
		return mapping.findForward(forward);
	}
	
	
	public ActionForward verificarCambio(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("verificarCambio....");
		String codeSMS="";
		AfiliacionDatosContactoForm frmContacto = (AfiliacionDatosContactoForm) form;
		
		try {
			
			
			Usuario usuario 		= (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
			TransferenciaContactoImpl trans = (TransferenciaContactoImpl)request.getSession().getAttribute("TRANSF_CONTACTO");
			
			String celular="";
			
			celular=frmContacto.getTxtNumeroPersonal();
			trans.setNumeroCelularCambio(celular);
			trans.setOperadorCelularCambio(frmContacto.getCmbOperador());
			
			//request.getSession().setAttribute("celular",celular );
			
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
				cta	= (CuentaImpl)usuario.getMapCuentas().get(trans.getNroCuentaContacto());
			}else{
				for(int i=0; i<x.size();i++){
					cta=(CuentaImpl)x.get(i);
					if(!cta.getTipoProducto().equals("44")&& cta.getNumeroProducto().equals(trans.getCuenta())){
						break;
						
					}	
				}}
			
	
			
			Cuenta cuenta = cta;
			trans.setCuentaOrigen(cuenta);
			
			request.getSession().setAttribute("ctaContactoSession",cuenta);
			
			codeSMS=ObjectUtil.getClaveDesencriptada(request.getParameter("txtCodigo"),request);
			
			
			BNAplicacion aplicacion = BNAplicacion.getInstance();
			request.getSession().setAttribute("mensajeCondicion",((Vector)aplicacion.getMensajePorCodigo("BC01","00014")).elementAt(2).toString().trim());
				
			String forward = "";
			
			FacadeFactory.getTransferenciaContactoFacade().validaCelularContacto( usuario, codeSMS,trans, request.getRemoteAddr());
					
			request.getSession().setAttribute("celular",celular );
			request.getSession().setAttribute(ConstanteSesion.TRANSF_CONTACTO, trans);
			

			
//			REALIZAR CONSULTA TIPO CLAVE DINAMICA  --> ADD M.D.B.
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
				ar.setForward("confirmarCambio");
				throw ar;
			}
			
//			REALIZAR CONSULTA DE COORDENADAS
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
					ar.setForward("confirmarCambio");
					throw ar;
				}
				request.getSession().setAttribute("resultCoord",resultCoord );
			}
			else{
				
				throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
			}

			
		} catch (ArrayRuleException ar) {
			ar.printStackTrace();
			ar.setForward("confirmarCambio");
			throw ar;
		}

		request.getSession().setAttribute("fectranscon",pe.cosapi.common.ObjectUtil.getFechaActual());
		
		Cuenta cuentaCont 		= (Cuenta) request.getSession().getAttribute("ctaContactoSession");
			
		request.getSession().setAttribute("cuenta",cuentaCont);

				
		return mapping.findForward("verificarCambio");
		
		
	}
	
	
	
	public ActionForward constanciaCambio(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		String forward = "";
		
		TokenSmsRequest tokenSms = new TokenSmsRequest();
		Usuario usuario 		= (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		TransferenciaContacto responseDes = new TransferenciaContactoImpl();
		TransferenciaContactoImpl transferencia = (TransferenciaContactoImpl)request.getSession().getAttribute(ConstanteSesion.TRANSF_CONTACTO);
		
		try{
			ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
     		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
     		             
     		String pinblock = ObjectUtil.getClaveDesencriptada(request.getParameter("txtCoordenada"),request);
     		     		
     		ParametrosElemSegTDC elementos = null;
     		
     		Afiliacion afiliacion = new AfiliacionImpl();
     		
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
			afiliacion.setCodCliente(usuario.getCodigoCic());					
			//afiliacion.setCodCliente(codCliente);
			afiliacion.setTipoDocumento(Funciones.lpad(tipoDoc,"0",3));
			afiliacion.setNroDocumento(numDoc);
			
			try{
				elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
				
				if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS) ||
				   elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
					elementos = SolicitarServiciosTDC.verificarTipoElemento(param,usuario);
				}
			
				
			}catch(ArrayRuleException ar){
				log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
				ar.printStackTrace();
				ar.setForward("inicioValida");
				throw ar;
			}
		
			ElemenSeguridad coord = (ElemenSeguridad)request.getSession().getAttribute("resultCoord");	

			if(coord == null){
				
				ElemenSeguridad resultCoord = null;
    			if(param!= null){
    				resultCoord = new ElemenSeguridad();
    				try{
    					request.getSession().setAttribute("resultCoord",null );
    					
    					if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)) {
    						resultCoord = SolicitarServiciosTDC.solicitCoordenada(param);
    					} else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS)) {
    							resultCoord=SolicitarServiciosTDC.solicitarTokens(param);
    					} else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {
    						generarClaveSms(mapping, form, request, response);		
    					}
    					
    				}catch(ArrayRuleException ar){
    					log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
    					ar.printStackTrace();
    					ar.setForward("inicioValida");
    					throw ar;
    				}
    				request.getSession().setAttribute("resultCoord",resultCoord );
    			}
    			else{
    				
    				throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
    			}
    			
    		coord = (ElemenSeguridad)request.getSession().getAttribute("resultCoord");
				
			}
		
			// Código Nuevo para TDC
			
			MensajesTDC resultado = new MensajesTDC();
			Transferencia transf = null;
			
			if(param!= null && coord!=null ){
				
				if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
					resultado=SolicitarServiciosTDC.validarTDC(param ,coord,pinblock);
				} else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS)) {
					resultado=SolicitarServiciosTDC.validarTokens(param ,coord,pinblock);
				} else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {
					
					tokenSms.setCodCliente(afiliacion.getCodCliente());
					tokenSms.setTipoDocumento(afiliacion.getTipoDocumento());
					tokenSms.setNroDocumento(afiliacion.getNroDocumento());
					
					tokenSms.setCodeSMS(pinblock);
					tokenSms.setTypeTrans(Constante.TIP_OPER_ACTUALIZACION_DATOS);
					tokenSms.setTypeOp("ADMIN");						
					tokenSms.setMsgClient("Validacion de Clave Dinamica Digital");
					tokenSms.setTypeCurrency("PEN");
					
					resultado = UtilAction.validarClaveSms(tokenSms);		
				}
				

				if (!resultado.getCodRptaPrincipal().equals(Constante.CODIGO_RESULTADO_OK) || !resultado.getCodResultOper().equals(Constante.CODIGO_OPERACION_OK)) {

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
			}
			
			
			try{
				
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
					cta	= (CuentaImpl)usuario.getMapCuentas().get(transferencia.getNroCuentaContacto());
				}else{
					for(int i=0; i<x.size();i++){
						cta=(CuentaImpl)x.get(i);
						if(!cta.getTipoProducto().equals("44")&& cta.getNumeroProducto().equals(transferencia.getNroCuentaContacto())){
							break;
							
						}	
					}}
				
				Cuenta cuenta = cta;
				transferencia.setCuentaOrigen(cuenta);
			
				//Actualizar Celular de Trans. Contactos
	          	responseDes=FacadeFactory.getTransferenciaContactoFacade().actualizarContacto(usuario, transferencia,request.getRemoteAddr(), request);
				
				responseDes.setCodigoEntidad(usuario.getEntidadCuenta());
				responseDes.setDescEntidad(usuario.getEntidadCuentaDesc());
				responseDes.setCciContacto(usuario.getCciCliente());
				responseDes.setCuentaOrigen(cuenta);
				request.getSession().setAttribute("cuenta",cuenta);
				request.getSession().setAttribute(ConstanteSesion.TRANSF_CONTACTO,responseDes);
				
				ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_ACTUALIZACION_CONTACTO, ConstanteSesion.TRANSF_CONTACTO, request);
				
				//Actualizar celular del cliente en BDUC
				String celularBDUC= transferencia.getOperadorCelularCambio().concat(transferencia.getNumeroCelularCambio());
				List listOperador = new ArrayList();
				listOperador = FacadeFactory.getGeneralFacade().getComboDetalleAyuda1(Constante.DAT_CLIENTES_TIPO_OPERADOR);
				String nombreOperador="";

				for (int i = 0; i < listOperador.size(); i++) {
	                DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
	                dHelp_ = (DetalleAyudaDatosImpl) listOperador.get(i);
	                if (transferencia.getOperadorCelularCambio().equals(dHelp_.getCodigoDetalleAyuda())) {
	                       if (transferencia.getOperadorCelularCambio().equals(Constante.DAT_CLIENTES_OPERADOR_POR_DEFECTO)) {
	                    	   nombreOperador= Constante.DAT_CLIENTES_SIN_DESCRIPCION;
	                             break;
	                       } else {
	                    	   nombreOperador =dHelp_.getDescripcionDetalle().toUpperCase();
	                       }
	                }
	            }
				
				//Consultar cuenta
				Afiliacion afiliaCliente = new AfiliacionImpl();
                CuentaImpl cuenta1 = (CuentaImpl) x.get(0);
                afiliaCliente.setCuenta(cuenta1);
                
				Afiliacion afil = FacadeFactory.getAfiliacionFacade().getActualizaCelularCliente(Constante.DAT_CLIENTES_ACTUALIZACION_CEL, usuario,request.getRemoteAddr(),celularBDUC ,nombreOperador, afiliaCliente,transferencia,request);
			

				request.getSession().setAttribute(ConstanteSesion.AFILIAR,afil);
				usuario.setCelular(celularBDUC);
				usuario.setOpeCelular(transferencia.getOperadorCelularCambio());
	          	usuario.setCelularFormat(transferencia.getNumeroCelularCambio());
	          	request.getSession().setAttribute(ConstanteSesion.USUARIO_EN_SESION,usuario);
	          	
	          	ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_ACTUALIZACION_DATOS_CLIENTE_CEL, ConstanteSesion.AFILIAR, request);
				
			
		
			} catch (ArrayRuleException ar) {
					ar.printStackTrace();
					ar.setForward("verificarCambio");
					throw ar;
			}
			
			Map map = UtilAction.cargarVar(request,responseDes);
			OperacionImpl.setVariables(map);
			
			forward = "constanciaCambio";
		
		} catch (ArrayRuleException e) {
                e.printStackTrace();
                log3.error(e, Constante.ERR_LOGICA_NEGOCIO, "");

                e.setForward("verificarCambio");
                throw e;
        }
		
		
		
		return mapping.findForward(forward);
	}


public ActionForward reenviarVerificaCelular(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception {
	// TODO Auto-generated method stub		
	System.out.println("reenviarVerificaCelular...");
	String forward = "";
	
	Usuario usuario 		= (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
	ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
	TransferenciaContactoImpl transferenciaContacto = (TransferenciaContactoImpl)request.getSession().getAttribute(ConstanteSesion.TRANSF_CONTACTO);
	
	//TransferenciaContactoImpl transferenciaContacto = new TransferenciaContactoImpl();
	
	try {
	System.out.println("transferenciaContacto.getIndCambioCelular():"+transferenciaContacto.getIndCambioCelular());
	Cuenta cuentaCont 		= (Cuenta) request.getSession().getAttribute("ctaContactoSession");
	request.getSession().setAttribute("cuenta",cuentaCont);
	//transferenciaContacto.setCuenta(request.getParameter("cmbCuenta"));
	if(transferenciaContacto.getIndCambioCelular().equals("1")){
		transferenciaContacto.setNumeroCelularCambio(transferenciaContacto.getNumeroCelularCambio());
		transferenciaContacto.setOperadorCelularCambio(transferenciaContacto.getOperadorCelularCambio());
	}else{
		transferenciaContacto.setNumeroCelularCambio(request.getParameter("txtNumeroPersonal"));
		transferenciaContacto.setOperadorCelularCambio(request.getParameter("cmbOperador"));
	}
	
	System.out.println("transferenciaContacto.getNumeroCelularCambio():"+transferenciaContacto.getNumeroCelularCambio());
	System.out.println("transferenciaContacto.getOperadorCelularCambio():"+transferenciaContacto.getOperadorCelularCambio());
		
//	if(transferenciaContacto.getIndCambioCelular().equals("1")){
//		celular=transferenciaContacto.getNumeroCelularCambio();
//	}else{
//		celular=frmContacto.getTxtNumeroPersonal();
//	}
	
	FacadeFactory.getTransferenciaContactoFacade().enviaSmsCelularContacto( usuario,transferenciaContacto , request.getRemoteAddr());
	
	}catch (ArrayRuleException ar) {
		ar.printStackTrace();
		ar.setForward("iniciarCuentaEnlazar");
		throw ar;
	}
	request.getSession().setAttribute("transferenciaContacto", transferenciaContacto);
	request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
	forward = "verificarCelular";
	
	return mapping.findForward(forward);		
}

public ActionForward reenviarVerificaCelularAct(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception {
	// TODO Auto-generated method stub		
	System.out.println("reenviarVerificaCelularAct...");
	String forward = "";
	
	Usuario usuario 		= (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
	ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
	TransferenciaContactoImpl transferenciaContacto = (TransferenciaContactoImpl)request.getSession().getAttribute(ConstanteSesion.TRANSF_CONTACTO);
	
	//TransferenciaContactoImpl transferenciaContacto = new TransferenciaContactoImpl();
	
	try {
	System.out.println("transferenciaContacto.getIndCambioCelular():"+transferenciaContacto.getIndCambioCelular());
	Cuenta cuentaCont 		= (Cuenta) request.getSession().getAttribute("ctaContactoSession");
	request.getSession().setAttribute("cuenta",cuentaCont);

	transferenciaContacto.setNumeroCelularCambio(request.getParameter("txtNumeroPersonal"));
	transferenciaContacto.setOperadorCelularCambio(request.getParameter("cmbOperador"));
	
	

	FacadeFactory.getTransferenciaContactoFacade().enviaSmsCelularContacto( usuario,transferenciaContacto , request.getRemoteAddr());
	
	}catch (ArrayRuleException ar) {
		ar.printStackTrace();
		ar.setForward("iniciarCuentaEnlazar");
		throw ar;
	}
	request.getSession().setAttribute("transferenciaContacto", transferenciaContacto);
	request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
	forward = "verificarCelular";
	
	return mapping.findForward(forward);		
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
				tokenSms.setTipoDocumento(Funciones.lpad(tipoDoc,"0",3));
				tokenSms.setNroDocumento(numDoc);	
				tokenSms.setTypeTrans(Constante.TIP_OPER_CONFIGURACION_AF_CONTACTOS);
				tokenSms.setTypeOp(Constante.COD_TRANSACCION_ADMINISTRATIVA);
				tokenSms.setTypeCurrency("PEN");
		
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



private void loadMessage(HttpServletRequest request) throws Exception{
	BNAplicacion aplicacion = BNAplicacion.getInstance();
	request.getSession().setAttribute("mensajeAfiliacion",((Vector)aplicacion.getMensajePorCodigo("TB01","AF001")).elementAt(2).toString());
	request.getSession().setAttribute("mensajePantalla",((Vector)aplicacion.getMensajePorCodigo("TB01","00001")).elementAt(2).toString());
	request.getSession().setAttribute("mensajeExitoTranfMB",((Vector)aplicacion.getMensajePorCodigo("TB01","00005")).elementAt(2).toString());
}





public ActionForward reGenerarClaveSms(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	BNAplicacion aplicacion = BNAplicacion.getInstance();
	//loadObject(request);
	TokenSmsRequest tokenSms = new TokenSmsRequest();
	Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
	// Afiliacion afiliacion = (AfiliacionImpl)request.getSession().getAttribute("afiliacion");
	String monto = (String) request.getSession().getAttribute("monto");
	//Afiliacion datosUsuario = new AfiliacionImpl();
	
	AfiliacionDatosContactoForm frm = (AfiliacionDatosContactoForm)form;
	frm.reset(mapping, request);
	
	request.getSession().removeAttribute("exitoGeneraClvSms");
	

	try {


		Afiliacion afiliacion 	= new AfiliacionImpl(); 
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
			
				tokenSms.setCodCliente(afiliacion.getCodCliente());
				tokenSms.setTipoDocumento(afiliacion.getTipoDocumento());
				tokenSms.setNroDocumento(afiliacion.getNroDocumento());
				
				tokenSms.setCodCliente(usuario.getCodigoCic());
				tokenSms.setTipoDocumento(Funciones.lpad(tipoDoc,"0",3));
				tokenSms.setNroDocumento(numDoc);	
				tokenSms.setTypeTrans(Constante.TIP_OPER_CONFIGURACION_AF_CONTACTOS);
				tokenSms.setTypeOp(Constante.COD_TRANSACCION_ADMINISTRATIVA);
				tokenSms.setTypeCurrency("PEN");
					
				
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

		request.setAttribute("cmbCuenta", request.getParameter("cmbCuenta"));
		request.getSession().setAttribute("cmbTransferencia", request.getParameter("cmbTransferencia"));
		loadMessage(request);
		e.setForward("verMBTransferencia");
		response.setStatus(404);
		throw e;
	}
	return mapping.findForward("inicio");

}



}
