/*
 * Fecha 14/07/2014
 * 
 */
package pe.bn.transferencia.action;

import java.math.BigDecimal;
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
import pe.bn.pago.domain.impl.PagoTarjetaImpl;
import pe.bn.transferencia.domain.Transferencia;
import pe.bn.transferencia.domain.impl.TransferenciaImpl;
import pe.bn.trasferenciacontacto.domain.TransferenciaContacto;
import pe.bn.trasferenciacontacto.domain.impl.TransferenciaContactoImpl;
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

public class TransferenciaIBLineaAction extends GrandActionAbstract {
	
	BNAplicacion aplicacion =BNAplicacion.getInstance();
	private static LoggerSati log3 = LoggerSati.getInstance(TransferenciaIBLineaAction.class.getName());
	
	public ActionForward iniciar(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
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

      //validacion de interoperabilidad
        TransferenciaContacto responseConsulta = new TransferenciaContactoImpl();
        responseConsulta=FacadeFactory.getTransferenciaContactoFacade().consultarContacto(usuario, request.getRemoteAddr());
		
        /*MGL*/
        if(responseConsulta.getNumeroCelularContacto()==null){
			request.getSession().setAttribute("CelularAfi","0");		
		}else{
			request.getSession().setAttribute("CelularAfi",responseConsulta.getNumeroCelularContacto());	
	        
		}
        
		if(responseConsulta.getCodigoErr().equals(Constante.CONST_PROCESO_OK) && responseConsulta.getEstadoAfil().equals(Constante.CONSTANTE_AFIL_CONTACTO)){
			responseConsulta.setIndTransferenciaContacto(ConstanteSesion.CODIGO_AFILIADO);
			
			//request.getSession().setAttribute("flagAfiliado",(ConstanteSesion.CODIGO_AFILIADO));
		}else{
			responseConsulta.setIndTransferenciaContacto(ConstanteSesion.CODIGO_NO_AFILIADO);
			//request.getSession().setAttribute("flagAfiliado",(ConstanteSesion.CODIGO_NO_AFILIADO));
		}
		
		request.getSession().setAttribute(ConstanteSesion.TRANSF_CONTACTO, responseConsulta);
		
		List listaAfiliaciones = null;
		
		
		Constante.TIPO_SERVICIO = Constante.TB_INTERBANCO;

		BNAplicacion aplicacion = BNAplicacion.getInstance();
		request.getSession().setAttribute(ConstanteSesion.TITULO,Constante.TRANSFERENCIA_INTERBANCARIA_TITULO);
		request.getSession().setAttribute("TITULOS",Constante.TRANSFERENCIA_INTERBANCARIA_TITULO);
										  
		request.getSession().setAttribute("mensajeTraInterbancaria",((Vector)aplicacion.getMensajePorCodigo("TB05","AF001")).elementAt(2).toString());
		
		//request.getSession().setAttribute("mensajePiePagina",((Vector)aplicacion.getMensajePorCodigo("TB05","00007")).elementAt(2).toString());
		
		request.getSession().setAttribute("mensajeListaBanco",((Vector)aplicacion.getMensajePorCodigo("TB05","00011")).elementAt(2).toString());
		
		request.getSession().setAttribute("mensajeExitoTransfIB",((Vector)aplicacion.getMensajePorCodigo("TB05","00005")).elementAt(2).toString());
		request.getSession().setAttribute("mensajeRechazoTransfIB",((Vector)aplicacion.getMensajePorCodigo("TB05","00008")).elementAt(2).toString());
		request.getSession().setAttribute("listaAfiliaciones",FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.TB_INTERBANCO_LINEA,usuario.getTarjeta().getNumero(),"00004"));
		//request.getSession().setAttribute("mensajepieafiltransfint",((Vector)aplicacion.getMensajePorCodigo("TB05","00006")).elementAt(2).toString());
		request.getSession().setAttribute("mensajeafiliacion",((Vector)aplicacion.getMensajePorCodigo("TB05","AF003")).elementAt(2).toString());
		request.getSession().setAttribute("motivo",((Vector)aplicacion.getMensajePorCodigo("TB05","00009")).elementAt(2).toString());
	
		
		List lstDocumentoBen =  FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_DOCU_GIROS);	
		request.getSession().setAttribute("lstDocumentoBen",lstDocumentoBen);
		
		FacadeFactory.getGeneralFacade().actualizarCuentas("LG02",usuario,request.getRemoteAddr());
		
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);

		ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
		
		if(!getVerificarHorario("TB10")){
			
			request.getSession().setAttribute("mensaje1Hor",((Vector)aplicacion.getMensajePorCodigo("AD01","00001")).elementAt(2).toString());
			request.getSession().setAttribute("mensaje2Hor",((Vector)aplicacion.getMensajePorCodigo("AD01","00002")).elementAt(2).toString());
			request.getSession().setAttribute("mensajeHorC",((Vector)aplicacion.getMensajePorCodigo("MH01","TB10")).elementAt(2).toString());
			return mapping.findForward("transaccion.noDisponible");
		}
		
		if(elementos.getTipoElementoSeguridad().equals("5")){
			
			usuario.getClaveDinamica().setTipoElementoSeguridad(elementos.getTipoElementoSeguridad());
			usuario.getClaveDinamica().setTipoElementoSeguridadFinal("Token Fisico");
			
		}else if(elementos.getTipoElementoSeguridad().equals("6")){
			
			usuario.getClaveDinamica().setTipoElementoSeguridad(elementos.getTipoElementoSeguridad());
			usuario.getClaveDinamica().setTipoElementoSeguridadFinal("Clave Dinamica Digital");
		}
		
				
		ResourceBundle rb = ResourceBundle.getBundle("parametro");		
		String ips = rb.getString("bn.ip.usuarios");
		
				
		if(ips.equals("all") || ips.indexOf(request.getRemoteAddr()) >= 0 ){
			return mapping.findForward("iniciarContacto");			
		}else{
			return mapping.findForward("restriccionPiloto");
		}	
			//return mapping.findForward("iniciar");
			
	}
	
	public ActionForward verTransferencia(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception{
		
		
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);		
		Afiliacion afiliacion  = new AfiliacionImpl();
		Afiliacion datosUsuario  = new AfiliacionImpl();
		String codCuentaCCI = request.getParameter("txtCuentaCCI").trim();
		
		List listaMin = null;
		String valorMin=""; 
		listaMin = DAOFactory.getGeneraDAO().getComboDetalleHlpHijo("02000","7");
		if(listaMin!=null){
			for (Iterator iter = listaMin.iterator(); iter.hasNext();) {
				ComboUtil element = (ComboUtil) iter.next();
				if(element.getCodigo().equals("7")){
					valorMin=element.getDescripcion();
					request.getSession().setAttribute("valorMinimoCCI",valorMin);
					
				}	
			}
		}
		
		String email = usuario.getEmail();
		//System.out.println("email:"+email);
	
		if(request.getParameter("optCuenta").equals(Constante.NUEV_REGISTRO_TRANSAC)){
			
			afiliacion = new AfiliacionImpl();
			afiliacion.setNumeroServicio(request.getParameter("txtCuentaCCI").trim());
			
			afiliacion.setFlagRegistro(Constante.NUEV_REGISTRO_TRANSAC);
			
			afiliacion.setEmail(email);
			String correo = afiliacion.getEmail();
			String[] var = correo.split(Constante.SEP_ALPHANUMERIC);
			afiliacion.setCorreo(var[0]);
			afiliacion.setServidorCorreo(var[1]);
			
			String tipoDoc="";
			String numDoc="";
			
			if(usuario.getCodigoCLDI() != null){
				tipoDoc=usuario.getCodigoCLDI().substring(12,13);
				numDoc=usuario.getCodigoCLDI().substring(13);	
				numDoc= numDoc.substring(4);
			
			}
			afiliacion.setTipoDocumento(Funciones.lpad(tipoDoc,"0",3));
			afiliacion.setNroDocumento(numDoc);
			

			String codBancoCCI = codCuentaCCI.substring(0, 3);
			String codCCIFormateada  = codCuentaCCI.substring(0, 3)+"-"+codCuentaCCI.substring(3, 6)+"-"+codCuentaCCI.substring(6, 18)+"-"+codCuentaCCI.substring(18, 20);
			
			ComboUtil lista = new ComboUtil();
			lista = DAOFactory.getGeneraDAO().getObjDetalleHlp(Constante.TRANSF_INTERB_LINEA_COD_HLP_BANCO,codBancoCCI.trim());
			
			
			if (lista == null){
								
				throw new ArrayRuleException(ConstanteError.GENERICO,"La Entidad Financiera a la cual desea Transferir no participa de este servicio");
			} else if (lista.getCodigo().trim() == ""){
								
				throw new ArrayRuleException(ConstanteError.GENERICO,"El Nro. CCI no es valido para la Transferencia Interbancaria Inmediata.");
			}else{
			afiliacion.getObjBenef().setObjBanco(lista);
			}
			
			List listaAfiliaciones = null;
			listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaExiste(Constante.TB_INTERBANCO_LINEA,usuario.getTarjeta().getNumero(), Constante.TRANSF_INTERB_LINEA_COD_HLP_BANCO, afiliacion.getNumeroServicio());
			
			if (listaAfiliaciones.size() > 0){
			
				throw new ArrayRuleException(ConstanteError.GENERICO,"El número de CCI: " + afiliacion.getNumeroServicio() +" ya se encuentra como frecuente.");
			}
			
			afiliacion.setCodigoServicio(codBancoCCI);
	

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
			lista = DAOFactory.getGeneraDAO().getObjDetalleHlp(Constante.TRANSF_INTERB_LINEA_COD_HLP_BANCO,afiliacion.getCodigoServicio());
			afiliacion.getObjBenef().setObjBanco(lista);
			
			String emailCCI = usuario.getEmail();
			afiliacion.setEmail(emailCCI);
			String correo = afiliacion.getEmail();
			String[] var = correo.split(Constante.SEP_ALPHANUMERIC);
			afiliacion.setCorreo(var[0]);
			afiliacion.setServidorCorreo(var[1]);
			
			request.getSession().setAttribute("CORREO_AFILIADO",afiliacion.getEmail());
			
		}
		
		
		String[] idCta = ObjectUtil.getArrayStrings(request.getParameter("cmbCuenta"),"-");
	
		
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
			cta	= (CuentaImpl)usuario.getMapCuentas().get(idCta[0]);
		}else{
			for(int i=0; i<x.size();i++){
				cta=(CuentaImpl)x.get(i);
				if(!cta.getTipoProducto().equals("44")&& cta.getNumeroProducto().equals(idCta[0])){
					break;
					
				}	
			}
		
		}
	
		
		CuentaImpl cuentaRec = cta;
		
		// Cargar el mensaje de la Condicion
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		request.getSession().setAttribute("listaAfiliaciones",FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.TB_INTERBANCO_LINEA,usuario.getTarjeta().getNumero(),"00004"));
		request.setAttribute("mensajeCondicion",((Vector)aplicacion.getMensajePorCodigo("BC01","00015")).elementAt(2).toString());
		request.setAttribute("mensajeLimite",((Vector)aplicacion.getMensajePorCodigo("TB05","00004")).elementAt(2).toString());
		request.getSession().setAttribute("afiliacion",afiliacion);
		request.setAttribute("listaMonedas",FacadeFactory.getGeneralFacade().getComboDetalleHlp(""));
		request.setAttribute("cuenta",cuentaRec);

		List lstDocumentoBen =  FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_DOCU_GIROS);	
		request.setAttribute("lstDocumentoBen",lstDocumentoBen);
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);

		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);

		ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
		
		return mapping.findForward("verTransferencia");
	}
	
	
	
	
	public ActionForward verTransferenciaContacto(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception{
	
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);		
		Afiliacion afiliacion  = new AfiliacionImpl();
		String email = usuario.getEmail();
		String numeroCel = request.getParameter("txtCuentaCCI").trim();
		/*MGL*/ 
		request.getSession().setAttribute("CelularTrans",numeroCel);
		
		
		//valor minimo
				List listaMin = null;
				String valorMin=""; 
				listaMin = DAOFactory.getGeneraDAO().getComboDetalleHlpHijo("02000","8");
				if(listaMin!=null){
					for (Iterator iter1 = listaMin.iterator(); iter1.hasNext();) {
						ComboUtil element1 = (ComboUtil) iter1.next();
						if(element1.getCodigo().equals("8")){
							valorMin=element1.getDescripcion();
							request.getSession().setAttribute("valorMinimoYP",valorMin);
							
						}	
					}
				}
		
		
		TransferenciaContactoImpl transferenciaContacto = (TransferenciaContactoImpl)request.getSession().getAttribute(ConstanteSesion.TRANSF_CONTACTO);
		request.getSession().setAttribute(ConstanteSesion.TRANSF_CONTACTO, transferenciaContacto);
		//MGL - se comento porque si se puede enviar a contacto sin estar afiliado
//		if(transferenciaContacto.getIndTransferenciaContacto().equals(ConstanteSesion.CODIGO_NO_AFILIADO)){
//			throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_VALIDA_TRANSFERENCIA_CONTACTO);
//		}
		
		/*Maximo valor*/
		List lista = null;
		String valor=""; 
		lista = DAOFactory.getGeneraDAO().getComboDetalleHlpHijo("02000","6");
		if(lista!=null){
			for (Iterator iter = lista.iterator(); iter.hasNext();) {
				ComboUtil element = (ComboUtil) iter.next();
				if(element.getCodigo().equals("6")){
					valor=element.getDescripcion();
					request.getSession().setAttribute("valorLimiteYP",valor);
					
				}	
			}
		}
		
		
		
		
		
	
		if(request.getParameter("optCuenta").equals(Constante.NUEV_REGISTRO_TRANSAC)){
			
			afiliacion = new AfiliacionImpl();
			usuario.setCelular(numeroCel);
			afiliacion.setNumeroServicio(numeroCel);
			
			List listaAfiliaciones = null;
			listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaExiste(Constante.TB_INTERBANCO_LINEA,usuario.getTarjeta().getNumero(), Constante.TRANSF_INTERB_LINEA_COD_HLP_BANCO, afiliacion.getNumeroServicio());
			
			if (listaAfiliaciones.size() > 0){
			
				throw new ArrayRuleException(ConstanteError.GENERICO,"El número de Contacto: " + afiliacion.getNumeroServicio() +" ya se encuentra como frecuente.");
			}
			
			afiliacion.setFlagRegistro(Constante.NUEV_REGISTRO_TRANSAC);
			
			afiliacion.setEmail(email);
			String correo = afiliacion.getEmail();
			String[] var = correo.split(Constante.SEP_ALPHANUMERIC);
			afiliacion.setCorreo(var[0]);
			afiliacion.setServidorCorreo(var[1]);
			
			String tipoDoc="";
			String numDoc="";
			
			if(usuario.getCodigoCLDI() != null){
				tipoDoc=usuario.getCodigoCLDI().substring(12,13);
				numDoc=usuario.getCodigoCLDI().substring(13);	
				numDoc= numDoc.substring(4);
			
			}
			afiliacion.setTipoDocumento(Funciones.lpad(tipoDoc,"0",3));
			afiliacion.setNroDocumento(numDoc);
			
			
		}
		if(request.getParameter("optCuenta").equals(Constante.FREC_REGISTRO_TRANSAC)){
			String[] id = ObjectUtil.getArrayStrings(request.getParameter("cmbTransferencia"),"-");
			String tipAfiliacion = id[0];
			String nroTarjeta	 = id[1];
			Long nroSecuencia  	 = new Long(id[2]);
			String numeroCelular = id[3];
			String campoAyuda	 = "00004";
			//afiliacion = FacadeFactory.getAfiliacionFacade().getAfiliacion(tipAfiliacion,nroTarjeta,nroSecuencia,campoAyuda);
			afiliacion.setFlagRegistro(Constante.FREC_REGISTRO_TRANSAC);
			afiliacion.setNumeroServicio(numeroCelular);
				
			//ComboUtil lista = new ComboUtil();
			//lista = DAOFactory.getGeneraDAO().getObjDetalleHlp(Constante.TRANSF_INTERB_LINEA_COD_HLP_BANCO,afiliacion.getCodigoServicio());
			//afiliacion.getObjBenef().setObjBanco(lista);
			
			String emailCCI = usuario.getEmail();
			afiliacion.setEmail(emailCCI);
			String correo = afiliacion.getEmail();
			String[] var = correo.split(Constante.SEP_ALPHANUMERIC);
			afiliacion.setCorreo(var[0]);
			afiliacion.setServidorCorreo(var[1]);
			
			String tipoDoc="";
			String numDoc="";
			
			if(usuario.getCodigoCLDI() != null){
				tipoDoc=usuario.getCodigoCLDI().substring(12,13);
				numDoc=usuario.getCodigoCLDI().substring(13);	
				numDoc= numDoc.substring(4);
			
			}
			afiliacion.setTipoDocumento(Funciones.lpad(tipoDoc,"0",3));
			afiliacion.setNroDocumento(numDoc);
			
			request.getSession().setAttribute("CORREO_AFILIADO",afiliacion.getEmail());
			
		}
		
		String[] idCta = ObjectUtil.getArrayStrings(request.getParameter("cmbCuenta"),"-");
				
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
			cta	= (CuentaImpl)usuario.getMapCuentas().get(idCta[0]);
		}else{
			for(int i=0; i<x.size();i++){
				cta=(CuentaImpl)x.get(i);
				if(!cta.getTipoProducto().equals("44")&& cta.getNumeroProducto().equals(idCta[0])){
					break;
					
				}	
			}
		
		}	
		
		CuentaImpl cuentaRec = cta;
		
		java.util.List listBarrido = new java.util.ArrayList();
		
		listBarrido=FacadeFactory.getTransferenciaContactoFacade().barridoClientes(usuario,afiliacion.getNumeroServicio(), request.getRemoteAddr());
		request.setAttribute("listBarrido",listBarrido);
		
		// Cargar el mensaje de la Condicion
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		request.setAttribute("mensajeCondicion",((Vector)aplicacion.getMensajePorCodigo("BC01","00015")).elementAt(2).toString());
		request.setAttribute("cuenta",cuentaRec);
		request.getSession().setAttribute("afiliacion",afiliacion);
		
		return mapping.findForward("verTransferenciaContacto");
	}
	
	
	public ActionForward celularNoAsociado(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception{
						
		return mapping.findForward("celularNoAsociado");
	}	
	
	
	public ActionForward continuarTransferencia(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception{
	    
		BNAplicacion aplicacion = BNAplicacion.getInstance();
	    Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);

	    
		    try{
		    	double monto= Double.parseDouble(request.getParameter("txtMonto").toString());
		    	
			}catch(NumberFormatException e){
				log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			    request.getSession().setAttribute("listaAfiliaciones",FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.TB_INTERBANCO_LINEA,usuario.getTarjeta().getNumero(),"00004"));
				request.setAttribute("mensajeCondicion",((Vector)aplicacion.getMensajePorCodigo("TB05","00002")).elementAt(2).toString());
				request.setAttribute("mensajeLimite",((Vector)aplicacion.getMensajePorCodigo("TB05","00004")).elementAt(2).toString());
				request.setAttribute("listaMonedas",FacadeFactory.getGeneralFacade().getComboDetalleHlp(""));
				
			    ArrayRuleException e1 = new ArrayRuleException(ConstanteError.GENERICO,"Verifique el monto de la Transferencia ingresado");
				e1.setForward("verTransferencia");
				throw e1;
			}

		String importeIngresado = ObjectUtil.replaceChar(request.getParameter("txtMonto").toString(),',',"");
		
		BigDecimal montoConversion = new BigDecimal(importeIngresado).setScale(2);
	
		
		
		Afiliacion af = (Afiliacion)request.getSession().getAttribute("afiliacion");
	
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
		
		//Consultar CCI 
		
		String codConsulta="03";
		String numeroCuenta=request.getParameter("cmbCuenta");
		String codMoneda=request.getParameter("cboMoneda");
		
		Cuenta cuentaImpl = new CuentaImpl();
		cuentaImpl=(Cuenta)FacadeFactory.getConsultaFacade().consultar(codConsulta,numeroCuenta,codMoneda,"", request.getRemoteAddr(),usuario,request);
		String cciOrigen=cuentaImpl.getNroCuentaCci().replaceAll("-", "");
	
		Cuenta cuenta = cta;
		Transferencia tran = null;
					
			tran = FacadeFactory.getTransferenciaFacade().getConsultaTransferenciaIBLinea(codMoneda,request.getParameter("txtMonto"),cuenta,af,usuario,cciOrigen,request.getRemoteAddr());	
					
			try{	
			request.setAttribute("listaAfiliaciones",FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.TB_INTERBANCO_LINEA,usuario.getTarjeta().getNumero(),"00004"));
			
			tran.setCodMoneda(request.getParameter("cboMoneda"));
			af.setTipoDocumentoBenef(tran.getAfiliacion().getTipoDocumentoBenef());
			af.setNroDocumentoBenef(tran.getAfiliacion().getNroDocumentoBenef());
			
			
			
			if(tran.getCuentaPropia().equals(Constante.TRANSF_INTERB_LINEA_CONST_CUENTA_PROPIA_MISMO)){
				tran.setCuentaPropiaDes(Constante.TRANSF_INTERB_LINEA_CONST_CUENTA_PROPIA_SI_DES);		
			}else{
				if(tran.getCuentaPropia().equals(Constante.TRANSF_INTERB_LINEA_CONST_CUENTA_PROPIA_OTRO)){
					tran.setCuentaPropiaDes(Constante.TRANSF_INTERB_LINEA_CONST_CUENTA_PROPIA_NO_DES);		
				}
			}
			
				
			
			request.getSession().setAttribute("transferencia",tran);
			request.getSession().setAttribute("afiliacion",af);
			request.setAttribute("cuenta",cuenta);
			if(request.getParameter("txtMonto")!=null)
			request.setAttribute("txtMonto",montoConversion);
			
			request.getSession().setAttribute("cuentaTransf", cuenta);
					
			
			request.setAttribute("cboMoneda",tran.getCodMoneda());
		}
		catch (ArrayRuleException e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
		    request.getSession().setAttribute("listaAfiliaciones",FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.TB_INTERBANCO_LINEA,usuario.getTarjeta().getNumero(),"00004"));
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
				}else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS)){
						resultCoord=SolicitarServiciosTDC.solicitarTokens(param);
				} else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {						
					generarClaveSms(mapping, form, request, response);		
				}
			}catch(ArrayRuleException ar){
				log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
				ar.printStackTrace();
				request.getSession().setAttribute("listaAfiliaciones",FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.TB_INTERBANCO_LINEA,usuario.getTarjeta().getNumero(),"00004"));
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
		//request.setAttribute("txtNombreBenef", af.getBeneficiario());
	
		
		return mapping.findForward("continuarTransferencia");
	}
	
	
	public ActionForward continuarTransferenciaContacto(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception{
	    
		BNAplicacion aplicacion = BNAplicacion.getInstance();
	    Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		TransferenciaContactoImpl transferenciaContacto = (TransferenciaContactoImpl)request.getSession().getAttribute(ConstanteSesion.TRANSF_CONTACTO);
		
		
	    String entidadCCE ="";
	    String entidadDesCCE ="";
	    String[] listaDir = request.getParameter("cboEntidadDestino").toString().split(Constante.SEP_LIST);
	    entidadCCE = (listaDir[0].substring(1, 4)).concat("99999999999999999");
	    entidadDesCCE = listaDir[1];
	  
		    try{
		    	double monto= Double.parseDouble(request.getParameter("txtMonto").toString());
		    	
			}catch(NumberFormatException e){
				log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			    request.getSession().setAttribute("listaAfiliaciones",FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.TB_INTERBANCO_LINEA,usuario.getTarjeta().getNumero(),"00004"));
				request.setAttribute("mensajeCondicion",((Vector)aplicacion.getMensajePorCodigo("TB05","00002")).elementAt(2).toString());
				request.setAttribute("mensajeLimite",((Vector)aplicacion.getMensajePorCodigo("TB05","00004")).elementAt(2).toString());
				//request.setAttribute("listaMonedas",FacadeFactory.getGeneralFacade().getComboDetalleHlp(""));
				
			    ArrayRuleException e1 = new ArrayRuleException(ConstanteError.GENERICO,"Verifique el monto de la Transferencia ingresado");
				e1.setForward("verTransferenciaContacto");
				throw e1;
			}

		String importeIngresado = ObjectUtil.replaceChar(request.getParameter("txtMonto").toString(),',',"");
		
		BigDecimal montoConversion = new BigDecimal(importeIngresado).setScale(2);
			
		Afiliacion af = (Afiliacion)request.getSession().getAttribute("afiliacion");
		
		//MGL - consultar a Mily
		af.setCodigoServicio(listaDir[0].toString().trim());
		
		if(transferenciaContacto.getEstadoAfil().equals(Constante.CONSTANTE_AFIL_CONTACTO)){
			af.setNumeroCelular(transferenciaContacto.getNumeroCelularContacto());
		}else{
			af.setNumeroCelular("");
		}
		
		//MGL - seteo de codigo y entidad del banco
				ComboUtil lista = new ComboUtil();
				lista.setCodigo(entidadCCE.substring(1,3));
				lista.setDescripcion(entidadDesCCE);
				af.getObjBenef().setObjBanco(lista);		
		
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
			cta	= (CuentaImpl)usuario.getMapCuentas().get(request.getParameter("cmbCuenta"));
		}else{
			for(int i=0; i<x.size();i++){
				cta=(CuentaImpl)x.get(i);
				if(!cta.getTipoProducto().equals("44")&& cta.getNumeroProducto().equals(request.getParameter("cmbCuenta"))){
					break;
					
				}	
			}
		
		}
	
		//Consultar CCI 
		
		String codConsulta="03";
		String numeroCuenta=request.getParameter("cmbCuenta");
		String codMoneda=request.getParameter("cboMoneda");
				
		Cuenta cuentaImpl = new CuentaImpl();
		cuentaImpl=(Cuenta)FacadeFactory.getConsultaFacade().consultar(codConsulta,numeroCuenta,codMoneda,"", request.getRemoteAddr(),usuario,request);
		String cciOrigen=cuentaImpl.getNroCuentaCci().replaceAll("-", "");
		
		Cuenta cuenta = cta;
		Transferencia tran = null;		
			
			
			tran = FacadeFactory.getTransferenciaFacade().getConsultaTransferenciaCelIBLinea(codMoneda,request.getParameter("txtMonto"),cuenta,af,usuario,cciOrigen,entidadCCE,request.getRemoteAddr());
		
						
			try{	
			request.setAttribute("listaAfiliaciones",FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.TB_INTERBANCO_LINEA,usuario.getTarjeta().getNumero(),"00004"));
			tran.setEntidadDesc(entidadDesCCE);
			tran.setCodMoneda(codMoneda);
			tran.setCciOrigen(cciOrigen);
			af.setTipoDocumentoBenef(tran.getAfiliacion().getTipoDocumentoBenef());
			af.setNroDocumentoBenef(tran.getAfiliacion().getNroDocumentoBenef());
			
			
			
			if(tran.getCuentaPropia().equals(Constante.TRANSF_INTERB_LINEA_CONST_CUENTA_PROPIA_MISMO)){
				tran.setCuentaPropiaDes(Constante.TRANSF_INTERB_LINEA_CONST_CUENTA_PROPIA_SI_DES);		
			}else{
				if(tran.getCuentaPropia().equals(Constante.TRANSF_INTERB_LINEA_CONST_CUENTA_PROPIA_OTRO)){
					tran.setCuentaPropiaDes(Constante.TRANSF_INTERB_LINEA_CONST_CUENTA_PROPIA_NO_DES);		
				}
			}
			
				
			
			request.getSession().setAttribute("transferencia",tran);
			request.getSession().setAttribute("afiliacion",af);
			request.setAttribute("cuenta",cuenta);
			if(request.getParameter("txtMonto")!=null)
			request.setAttribute("txtMonto",montoConversion);
			
			request.getSession().setAttribute("cuentaTransf", cuenta);
					
			
			request.setAttribute("cboMoneda",tran.getCodMoneda());
		}
		catch (ArrayRuleException e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
		    request.getSession().setAttribute("listaAfiliaciones",FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.TB_INTERBANCO_LINEA,usuario.getTarjeta().getNumero(),"00004"));
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
				}else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS)){
						resultCoord=SolicitarServiciosTDC.solicitarTokens(param);
				} else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {						
					generarClaveSms(mapping, form, request, response);		
				}
			}catch(ArrayRuleException ar){
				log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
				ar.printStackTrace();
				request.getSession().setAttribute("listaAfiliaciones",FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.TB_INTERBANCO_LINEA,usuario.getTarjeta().getNumero(),"00004"));
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
		//request.setAttribute("txtCuentaCCI", af.getCuenta().getNumeroProducto());
		//request.setAttribute("txtNombreBenef", af.getBeneficiario());		
		
		return mapping.findForward("continuarTransferenciaContacto");
	}
	
	public ActionForward transferir(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		TransferenciaImpl transferencia = null;
		Afiliacion af = (Afiliacion)request.getSession().getAttribute("afiliacion");
		transferencia = (TransferenciaImpl)request.getSession().getAttribute("transferencia");
		Transferencia transferenciaFinal = null;
		
		
				
		// Código Nuevo para TDC
		ElemenSeguridad resultCoord = null;

		ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
		
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		
		ElemenSeguridad coord = (ElemenSeguridad)request.getSession().getAttribute("resultCoord");
		
	
		String pinblock = ObjectUtil.getClaveDesencriptada(request.getParameter("txtCoordenada"),request);
			
		
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
		String  cboMoneda = transferencia.getCodMoneda();
		
//		System.out.println("transferencia.getImporte():"+transferencia.getImporte());
		
		//transferencia.setImporteConvertido(new BigDecimal(transferencia.getImporte()));
		transferencia.setImporteTransferido(request.getParameter("txtMonto"));
		transferencia.setImporte(new BigDecimal(request.getParameter("txtMonto")));

		transferencia.setImporteNeto(ObjectUtil.deFormatearMonto(transferencia.getImporte()).add(transferencia.getComisionIBConvertido())); //sumar ib + imp Ref dolares
		transferencia.setCuentaOrigen(cuenta);
		af.setIdentificadorCCE1(transferencia.getAfiliacion().getIdentificadorCCE1());
		af.setIdentificadorCCE2(transferencia.getAfiliacion().getIdentificadorCCE2());
		af.setCciOrigen(transferencia.getAfiliacion().getCciOrigen());
		transferencia.setAfiliacion(af);
		
		request.getSession().setAttribute("fectransib",pe.cosapi.common.ObjectUtil.getFechaActual());
		request.getSession().setAttribute("hortransib",pe.cosapi.common.ObjectUtil.getHHMMSSformateado());
		request.setAttribute("cuenta",cuenta);
		request.getSession().setAttribute("cboMoneda",transferencia.getCodMoneda());
		
		TokenSmsRequest tokenSms = new TokenSmsRequest();
		Map map = UtilAction.cargarVar(request,request.getSession().getAttribute(ConstanteSesion.TRANSFERENCIA));
		
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
		
		ParametrosElemSegTDC elementos = null;
		try{
						
			OperacionImpl.setVariables(map);
						
					
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
				ar.setForward("iniciarContacto");
				throw ar;
			}
					
		
			MensajesTDC resultado = new MensajesTDC();
			
			if(param!= null && coord!=null ){
			
				try{
					
					if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
						resultado=SolicitarServiciosTDC.validarTDC(param ,coord,pinblock);
					} else if (elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {
						
						tokenSms.setCodCliente(usuario.getCodigoCic());
						tokenSms.setTipoDocumento(Funciones.lpad(tipoDoc,"0",3));
						tokenSms.setNroDocumento(numDoc);
						tokenSms.setCodeSMS(pinblock);
						tokenSms.setTypeTrans(Constante.TIP_OPER_PAGO_TRANSF_IB);
						tokenSms.setTypeOp(Constante.COD_TRANSACCION_FINANCIERA);						
						tokenSms.setAmount(txtMonto);
						tokenSms.setTypeCurrency(cuenta.getMonedaProducto().equals("USD")?"USD":"PEN");
						tokenSms.setCodCliDestinatario(transferencia.getCuentaPropiaDes());
						tokenSms.setNomCliDestinatario(transferencia.getBeneficiario());
						
						resultado = UtilAction.validarClaveSms(tokenSms);
					} else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS)){						
							resultado=SolicitarServiciosTDC.validarTokens(param ,coord,pinblock);
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
					
					
					//-------------------------TRANSFERIR----------------------------------//
					OperacionImpl.setVariables(map);
					transferenciaFinal = FacadeFactory.getTransferenciaFacade().transferirInterBancoLinea(cboMoneda, transferencia,usuario,request.getRemoteAddr(),request);
					request.getSession().setAttribute(ConstanteSesion.TRANSFERENCIA,transferenciaFinal);
					request.getSession().setAttribute("tdDestino","Nro. CCI Destino:");
					
					
					//-------------------GUARDAR FRECUENTE----------------------------------//
					if(af.getFlagRegistro().equals(Constante.NUEV_REGISTRO_TRANSAC)){
						
						String adicionar = request.getParameter("adicionar");
						if(adicionar!=null){
							
							Afiliacion afiliacion = new AfiliacionImpl();
							
							afiliacion = transferencia.getAfiliacion();
							afiliacion.setBeneficiario(transferencia.getBeneficiario());
							afiliacion.setTipoAfiliacion(Constante.TB_INTERBANCO_LINEA);
							
						
							
							try {
									Afiliacion afiliar=FacadeFactory.getAfiliacionFacade().afiliar(usuario,afiliacion);
						
								 
							} catch (ArrayRuleException e) {
								log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
								e.printStackTrace();
								e.setForward("transferir");
							}
						}
					
						
					}
					
					if(usuario.getNotificacion().getEstadoNotificacion().equals("1")){
						 ServiciosNotificacionPrincipal.enviarNotificacion(
								 usuario,usuario.getNombre(), 
								 transferenciaFinal.getCuenta().getSimboloMonedaProducto(), 
								 transferencia.getImporte(),
								 usuario.getTipoCambio().getVenta() ,
								 Constante.NOTI_TRANSFERENCIA_IB_LINEA, 
								 usuario.getNotificacion());
					}
					
					if(transferenciaFinal.getFlagRefrendo().equals(Constante.TRANSF_INTERB_LINEA_CONST_COD_REFRENDO)){
						ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_TRANSFERENCIA_IB_LINEA_RECHAZO, ConstanteSesion.TRANSFERENCIA, request);
					}else{
						ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_TRANSFERENCIA_IB_LINEA, ConstanteSesion.TRANSFERENCIA, request);
					}
				}
				catch (ArrayRuleException e) {
					log3.error(e,Constante.ERR_ERROR_VALIDAR_TDC,"");
					e.printStackTrace();
					request.setAttribute("txtMonto", txtMonto);
					request.setAttribute("optCuenta", transferencia.getAfiliacion().getFlagRegistro());
					request.setAttribute("txtCuentaCCI", transferencia.getAfiliacion().getCuenta().getNumeroProducto());
					request.setAttribute("txtNombreBenef", transferencia.getBeneficiario());
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
					e.setForward("iniciarContacto");
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
			e.setForward("iniciarContacto");
			throw e;
		}
	
		if(transferenciaFinal.getFlagRefrendo().equals(Constante.TRANSF_INTERB_LINEA_CONST_COD_REFRENDO)){
			return mapping.findForward("transferirRechazo");
		}else{
			return mapping.findForward("transferir");
		}
		
	}
	
public ActionForward transferirContacto(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		TransferenciaImpl transferencia = null;
		Afiliacion af = (Afiliacion)request.getSession().getAttribute("afiliacion");
		transferencia = (TransferenciaImpl)request.getSession().getAttribute("transferencia");
		Transferencia transferenciaFinal = null;
		
		
		
		// Código Nuevo para TDC
		ElemenSeguridad resultCoord = null;

		ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
		
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		
		ElemenSeguridad coord = (ElemenSeguridad)request.getSession().getAttribute("resultCoord");
		
	
		String pinblock = ObjectUtil.getClaveDesencriptada(request.getParameter("txtCoordenada"),request);
			
		
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
		String  cboMoneda = transferencia.getCodMoneda();
		
//		System.out.println("transferencia.getImporte():"+transferencia.getImporte());
		
		//transferencia.setImporteConvertido(new BigDecimal(transferencia.getImporte()));
		transferencia.setImporteTransferido(request.getParameter("txtMonto"));
		transferencia.setImporte(new BigDecimal(request.getParameter("txtMonto")));

		transferencia.setImporteNeto(ObjectUtil.deFormatearMonto(transferencia.getImporte()).add(transferencia.getComisionIBConvertido())); //sumar ib + imp Ref dolares
		transferencia.setCuentaOrigen(cuenta);
		af.setIdentificadorCCE1(transferencia.getAfiliacion().getIdentificadorCCE1());
		af.setIdentificadorCCE2(transferencia.getAfiliacion().getIdentificadorCCE2());
		af.setCciOrigen(transferencia.getAfiliacion().getCciOrigen());
		transferencia.setAfiliacion(af);
		
		request.getSession().setAttribute("fectransib",pe.cosapi.common.ObjectUtil.getFechaActual());
		request.getSession().setAttribute("hortransib",pe.cosapi.common.ObjectUtil.getHHMMSSformateado());
		request.setAttribute("cuenta",cuenta);
		request.getSession().setAttribute("cboMoneda",transferencia.getCodMoneda());
		
		TokenSmsRequest tokenSms = new TokenSmsRequest();
		Map map = UtilAction.cargarVar(request,request.getSession().getAttribute(ConstanteSesion.TRANSFERENCIA));
		
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
		
		ParametrosElemSegTDC elementos = null;
		try{
						
			OperacionImpl.setVariables(map);
						
					
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
				ar.setForward("iniciarContacto");
				throw ar;
			}
					
		
			MensajesTDC resultado = new MensajesTDC();
			
			if(param!= null && coord!=null ){
			
				try{
					
					if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
						resultado=SolicitarServiciosTDC.validarTDC(param ,coord,pinblock);
					} else if (elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {
						
						tokenSms.setCodCliente(usuario.getCodigoCic());
						tokenSms.setTipoDocumento(Funciones.lpad(tipoDoc,"0",3));
						tokenSms.setNroDocumento(numDoc);
						tokenSms.setCodeSMS(pinblock);
						tokenSms.setTypeTrans(Constante.TIP_OPER_PAGO_TRANSF_IB);
						tokenSms.setTypeOp(Constante.COD_TRANSACCION_FINANCIERA);						
						tokenSms.setAmount(txtMonto);
						tokenSms.setTypeCurrency(cuenta.getMonedaProducto().equals("USD")?"USD":"PEN");
						tokenSms.setCodCliDestinatario(transferencia.getCuentaPropiaDes());
						tokenSms.setNomCliDestinatario(transferencia.getBeneficiario());
						
						resultado = UtilAction.validarClaveSms(tokenSms);
					} else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS)){						
							resultado=SolicitarServiciosTDC.validarTokens(param ,coord,pinblock);
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
					

					
					//---------------------------------TRANSFERIR CONTACTO----------------------------------//
					OperacionImpl.setVariables(map);
					transferenciaFinal = FacadeFactory.getTransferenciaFacade().transferirInterBancoCelLinea(cboMoneda, transferencia,usuario,request.getRemoteAddr(),request);
					request.getSession().setAttribute(ConstanteSesion.TRANSFERENCIA,transferenciaFinal);
					request.getSession().setAttribute("tdDestino","Nro. Celular de cuenta destino:");
					
					
					
					
					
					//---------------------------------GUARDAR FRECUENTE CONTACTO----------------------------------//
					
					
					if(af.getFlagRegistro().equals(Constante.NUEV_REGISTRO_TRANSAC)){
						String adicionar = request.getParameter("adicionar");	
						if(adicionar!=null){							
							Afiliacion afiliacion = new AfiliacionImpl();							
							afiliacion = transferencia.getAfiliacion();
							afiliacion.setBeneficiario(transferencia.getBeneficiario());
							afiliacion.setTipoAfiliacion(Constante.TB_INTERBANCO_LINEA);
							afiliacion.setCodigoServicio(afiliacion.getCodigoServicio().substring(1, 4));	
							
							try {							
								Afiliacion afiliar=FacadeFactory.getAfiliacionFacade().afiliar(usuario,afiliacion);
							} catch (ArrayRuleException e) {
								log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
								e.printStackTrace();
								e.setForward("transferir");
							}
						}
					
						
					}
					
					if(usuario.getNotificacion().getEstadoNotificacion().equals("1")){
						 ServiciosNotificacionPrincipal.enviarNotificacion(
								 usuario,usuario.getNombre(), 
								 transferenciaFinal.getCuenta().getSimboloMonedaProducto(), 
								 transferencia.getImporte(),
								 usuario.getTipoCambio().getVenta() ,
								 Constante.NOTI_TRANSFERENCIA_IB_LINEA, 
								 usuario.getNotificacion());
					}
					
					if(transferenciaFinal.getFlagRefrendo().equals(Constante.TRANSF_INTERB_LINEA_CONST_COD_REFRENDO)){
						ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_TRANSFERENCIA_IB_LINEA_RECHAZO, ConstanteSesion.TRANSFERENCIA, request);
					}else{
						ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_TRANSFERENCIA_IB_LINEA, ConstanteSesion.TRANSFERENCIA, request);
					}
				}
				catch (ArrayRuleException e) {
					log3.error(e,Constante.ERR_ERROR_VALIDAR_TDC,"");
					e.printStackTrace();
					request.setAttribute("txtMonto", txtMonto);
					request.setAttribute("optCuenta", transferencia.getAfiliacion().getFlagRegistro());
					request.setAttribute("txtCuentaCCI", transferencia.getAfiliacion().getNumeroServicio());
					request.setAttribute("txtNombreBenef", transferencia.getBeneficiario());
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
					e.setForward("iniciarContacto");
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
			e.setForward("iniciarContacto");
			throw e;
		}
		

		
		if(transferenciaFinal.getFlagRefrendo().equals(Constante.TRANSF_INTERB_LINEA_CONST_COD_REFRENDO)){
			return mapping.findForward("transferirRechazo");
		}else{
			return mapping.findForward("transferir");
		}
		
	}
	
	
	public ActionForward finTransferenciaContacto(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception{
		
		return mapping.findForward("finTransferenciaContacto");
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
	
	
	public ActionForward reGenerarClaveSms(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		TokenSmsRequest tokenSms = new TokenSmsRequest();
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		// Afiliacion afiliacion = (AfiliacionImpl)request.getSession().getAttribute("afiliacion");
		//String monto = (String) request.getSession().getAttribute("monto");
		Afiliacion datosUsuario = new AfiliacionImpl();
		PagoTarjetaImpl pago = (PagoTarjetaImpl)request.getSession().getAttribute(ConstanteSesion.PAGO_TARJETA);
		
		Transferencia transferencia = (TransferenciaImpl)request.getSession().getAttribute("transferencia");
		
		Cuenta cuenta = (CuentaImpl)request.getSession().getAttribute("cuentaTransf");
		
		try {

			Afiliacion afiliacion = (Afiliacion) request.getSession().getAttribute("afiliacion");
			//afiliacion.setDescripcionTipoDocumento(NomTipoDocBenef(afiliacion.getCodigoServicio(), Constante.COD_HLP_DET_DOCU_GIRO));
			request.getSession().setAttribute("afiliacion", afiliacion);
			request.setAttribute("optTelegiro", afiliacion.getFlagRegistro());
			
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
//            afiliacion.setTipoDocumento(Funciones.lpad(datosUsuario.getTipoDocumento(),"0",3));
//			afiliacion.setNroDocumento(datosUsuario.getNroDocumento());
			afiliacion.setTipoDocumento(Funciones.lpad(tipoDoc,"0",3));
			afiliacion.setNroDocumento(numDoc);
			afiliacion.setCodCliente(usuario.getCodigoCic());
			

			// REALIZAR CONSULTA TIPO CLAVE DINAMICA --> ADD M.D.B.
			ParametrosTDC param = (ParametrosTDC) request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
			request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION, param);
			ParametrosElemSegTDC elementos = null;

			elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
			request.getSession().setAttribute("tipoElemento", elementos);

			String  txtMonto  = request.getParameter("txtMonto");
			//REALIZAR CONSULTA DE COORDENADAS
			ElemenSeguridad resultCoord = null;

			if (param != null) {
				resultCoord = new ElemenSeguridad();
				request.getSession().setAttribute("resultCoord", null);

				if (elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {
					
					tokenSms.setCodCliente(usuario.getCodigoCic());
//					tokenSms.setTipoDocumento(Funciones.lpad(datosUsuario.getTipoDocumento(),"0",3));
//					tokenSms.setNroDocumento(datosUsuario.getNroDocumento());
					tokenSms.setTipoDocumento(Funciones.lpad(tipoDoc,"0",3));
					tokenSms.setNroDocumento(numDoc);
					tokenSms.setTypeTrans(Constante.TIP_OPER_PAGO_TRANSF_IB);
					tokenSms.setTypeOp(Constante.COD_TRANSACCION_FINANCIERA);						
					tokenSms.setAmount(transferencia.getImporte());
					tokenSms.setTypeCurrency(cuenta.getMonedaProducto().equals("USD")?"USD":"PEN");		
					tokenSms.setMsgClient("Generacion de Clave Dinamica Digital");
					tokenSms.setCodCliDestinatario(transferencia.getCuentaPropiaDes());
					tokenSms.setNomCliDestinatario(transferencia.getBeneficiario());

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
			e.setForward("verMBTransferencia");
			throw e;
		}
		return mapping.findForward("confirmaPagoTCredito");

	}
	
	
	public ActionForward generarClaveSms(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		TokenSmsRequest tokenSms = new TokenSmsRequest();
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		// Afiliacion afiliacion = (AfiliacionImpl)request.getSession().getAttribute("afiliacion");
		//String monto = (String) request.getSession().getAttribute("monto");
		//Afiliacion datosUsuario = new AfiliacionImpl();
		PagoTarjetaImpl pago = (PagoTarjetaImpl)request.getSession().getAttribute(ConstanteSesion.PAGO_TARJETA);
		
		Transferencia transferencia = (TransferenciaImpl)request.getSession().getAttribute("transferencia");
		
		Cuenta cuenta = (CuentaImpl)request.getSession().getAttribute("cuentaTransf");
		
		try {

			Afiliacion afiliacion = (Afiliacion) request.getSession().getAttribute("afiliacion");
			//afiliacion.setDescripcionTipoDocumento(NomTipoDocBenef(afiliacion.getCodigoServicio(), Constante.COD_HLP_DET_DOCU_GIRO));
			request.getSession().setAttribute("afiliacion", afiliacion);
			request.setAttribute("optTelegiro", afiliacion.getFlagRegistro());
			
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

			String  txtMonto  = request.getParameter("txtMonto");
			//REALIZAR CONSULTA DE COORDENADAS
			ElemenSeguridad resultCoord = null;

			if (param != null) {
				resultCoord = new ElemenSeguridad();
				request.getSession().setAttribute("resultCoord", null);

				if (elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {
					
					tokenSms.setCodCliente(usuario.getCodigoCic());
//					tokenSms.setTipoDocumento(Funciones.lpad(datosUsuario.getTipoDocumento(),"0",3));
//					tokenSms.setNroDocumento(datosUsuario.getNroDocumento());
					tokenSms.setTipoDocumento(Funciones.lpad(tipoDoc,"0",3));
					tokenSms.setNroDocumento(numDoc);
					tokenSms.setTypeTrans(Constante.TIP_OPER_PAGO_TRANSF_IB);
					tokenSms.setTypeOp(Constante.COD_TRANSACCION_FINANCIERA);						
					tokenSms.setAmount(transferencia.getImporte());
					tokenSms.setTypeCurrency(cuenta.getMonedaProducto().equals("USD")?"USD":"PEN");		
					tokenSms.setMsgClient("Generacion de Clave Dinamica Digital");
					tokenSms.setCodCliDestinatario(transferencia.getCuentaPropiaDes());
					tokenSms.setNomCliDestinatario(transferencia.getBeneficiario());

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
			e.setForward("verMBTransferencia");
			throw e;
		}
		return mapping.findForward("confirmaPagoTCredito");

	}
}
