package pe.bn.cldinamica.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pe.bn.afiliacion.domain.Afiliacion;
import pe.bn.afiliacion.domain.impl.AfiliacionImpl;
import pe.bn.clavesms.action.ClaveSMSAction;
import pe.bn.cldinamica.action.form.MensajesTDC;
import pe.bn.cldinamica.action.form.ParametrosElemSegTDC;
import pe.bn.cldinamica.action.form.ParametrosTDC;
import pe.bn.login.action.LoginAction;
import pe.bn.login.action.form.ClaveDinamicaForm;
import pe.bn.login.domain.Menu;
import pe.bn.notificaciones.action.ServicioNotificacionConstancia;
import pe.bn.tarjeta.domain.Tarjeta;
import pe.bn.tarjeta.domain.impl.TarjetaImpl;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.action.GrandActionAbstract;
import pe.cosapi.common.ArrayRuleException;
import pe.cosapi.common.BNAplicacion;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteError;
import pe.cosapi.common.ConstanteSesion;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.common.SeguridadUtil;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.Usuario;

/**
 * @author Mily Dolores Bustamante
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */

public class ClaveDinamicaAction extends GrandActionAbstract{
	
	private static LoggerSati log3 = LoggerSati.getInstance(ClaveDinamicaAction.class.getName());


	public ActionForward iniciar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		return mapping.findForward("iniciar");
	}
	
	public ActionForward iniciarVinculacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BNAplicacion aplicacion = BNAplicacion.getInstance();	
		request.setAttribute("lstDocumentoBen",FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_DOCU_GIROS)	);
		List lista= FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_DET_DOCU_OTRO);
		lista.remove(0);

		request.setAttribute("lstDocumento", lista);
		request.setAttribute("lstMes", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.COD_HLP_DET_MES));
		request.getSession().setAttribute("cuenta", (Cuenta)request.getSession().getAttribute("ctaSession"));
		request.getSession().setAttribute("mensajeSolcCabecera",((Vector)aplicacion.getMensajePorCodigo("CD01","00006")).elementAt(2).toString());
		request.getSession().setAttribute("mensajeSolcCabeceraInfo",((Vector)aplicacion.getMensajePorCodigo("CD01","00007")).elementAt(2).toString());
		request.getSession().setAttribute("mensajeSolcCabeceraInfo1",((Vector)aplicacion.getMensajePorCodigo("CD01","00008")).elementAt(2).toString());

				
		return mapping.findForward("iniciarVinculacion");
	}
	
	public ActionForward iniciarActiv(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Usuario usuario 		= (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		
		ActionForward forward = new ActionForward();
		
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		if(request.getSession().getAttribute("mensajeActivarCabeceraVal")!=null){
			request.getSession().setAttribute("mensajeActivarCabeceraVal","");	
		}
		
		if(request.getSession().getAttribute("mensajeActivarCabecera")!=null){
			request.getSession().setAttribute("mensajeActivarCabecera","");	
		}
		
		
		
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
	
		ParametrosElemSegTDC elementos = null;
		
		try{
			
			
			request.getSession().setAttribute("mensajeActivarCabeceraInf",((Vector)aplicacion.getMensajePorCodigo("GC02","00002")).elementAt(2).toString());
			
			if(param==null ){
				
				throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
			}
			
			if(param.getTipoElementoSeguridad()==null ){
							
				throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
			}
			
			
			elementos = SolicitarServiciosTDC.verificarBloqueo(param);


//					
			for(int i=0;i<param.getElemetosRel().size();i++){
				
				ParametrosElemSegTDC paramElemSeg = (ParametrosElemSegTDC)param.getElemetosRel().get(i);
				if(paramElemSeg.getCodEstObtenerListaESRelac().equals(Constante.CODIGO_TDC_REGISTRADO)  ){
										
					if(paramElemSeg.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
						request.getSession().setAttribute("mensajeActivarCabecera",((Vector)aplicacion.getMensajePorCodigo("CD01","00001")).elementAt(2).toString());
						request.getSession().setAttribute("mensajeActivarCabeceraVal",((Vector)aplicacion.getMensajePorCodigo("CD01","00002")).elementAt(2).toString());
						request.getSession().setAttribute("mensajeActivarCabeceraInf",((Vector)aplicacion.getMensajePorCodigo("GC02","00002")).elementAt(2).toString());
					} else if(paramElemSeg.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS)){
						request.getSession().setAttribute("mensajeActivarCabecera",((Vector)aplicacion.getMensajePorCodigo("CD01","00014")).elementAt(2).toString());
						request.getSession().setAttribute("mensajeActivarCabeceraVal",((Vector)aplicacion.getMensajePorCodigo("CD01","00015")).elementAt(2).toString());
						request.getSession().setAttribute("mensajeActivarCabeceraInf",((Vector)aplicacion.getMensajePorCodigo("GC02","00002")).elementAt(2).toString());
					}
				
			  }
				else{
						request.getSession().setAttribute("mensajeActivarCabeceraInf",((Vector)aplicacion.getMensajePorCodigo("GC02","00002")).elementAt(2).toString());
					
				}
				
			}
		
					
		    forward = mapping.findForward("iniciarActiv");
			 
			
		}catch (ArrayRuleException e) {
			e.printStackTrace();
			log3.error(e,Constante.ERR_SOLICITAR_COORDENADA,"");
			e.setForward("noActivo");
			throw e;
		}
	
		
		return forward;
			
	}
	
	public ActionForward iniciarBloqueo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		ActionForward forward = new ActionForward();
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		ParametrosElemSegTDC elementos = null;
		
		   if(request.getSession().getAttribute("mensajeBloquearCabeceraCD")!=null){
			request.getSession().setAttribute("mensajeBloquearCabeceraCD","");	
		}
		try{
			
			if(param==null ){
				
				throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
			}
			
			if(param.getTipoElementoSeguridad()==null || param.getTipoElementoSeguridad().equals("6") ){
				throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
			}
			
			//elementos = SolicitarServiciosTDC.verificarBloqueo(param);
			
			elementos = SolicitarServiciosTDC.verificarTipoElementoAct(param);
			request.getSession().setAttribute("mensajebloqueopieCD",((Vector)aplicacion.getMensajePorCodigo("CD01","00010")).elementAt(2).toString());
			request.getSession().setAttribute("mensajebloqueopiedetCD",((Vector)aplicacion.getMensajePorCodigo("CD01","00011")).elementAt(2).toString());
			
			if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
				request.getSession().setAttribute("mensajeBloquearCabeceraCD",((Vector)aplicacion.getMensajePorCodigo("CD01","00003")).elementAt(2).toString());
			} else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS)){
				request.getSession().setAttribute("mensajeBloquearCabeceraCD",((Vector)aplicacion.getMensajePorCodigo("CD01","00016")).elementAt(2).toString());
			}
			
			request.getSession().setAttribute("mensajebloqueoInfCD",((Vector)aplicacion.getMensajePorCodigo("GC03","00007")).elementAt(2).toString());
			forward = mapping.findForward("iniciarBloqueo");
		}
		catch (ArrayRuleException e) {
			// TODO: handle exception
			e.printStackTrace();
			log3.error(e,Constante.ERR_SOLICITAR_COORDENADA,"");
			e.setForward("bloqueado");
			throw e;
		}
	
		
	
		return forward;
	}
	
	
	public ActionForward generarClaveValidacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		String claveInternet = SeguridadUtil.getClaveDesencriptada(request.getParameter("txtClaveInternet"),request);
		Usuario usuario 		= (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		MensajesTDC rpta = new MensajesTDC();
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		
		try{
			//FacadeFactory.getGeneralFacade().processValidationCardAhorro(usuario.getTarjeta().getNumero(),usuario.getTarjeta().getClave(),claveInternet,claveInternet);
		}
		catch (ArrayRuleException e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			e.setForward("iniciarActiv");
			throw e;
		}
		//Validar TDC		
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		try{
			
			List resultAct = new ArrayList();
			rpta = SolicitarServiciosTDC.activarTDC(param);
			
			if(rpta.getCodRptaPrincipal() == 0){
				
				for(int i=0;i<param.getElemetosRel().size();i++){
					
					ParametrosElemSegTDC paramElemSeg = (ParametrosElemSegTDC)param.getElemetosRel().get(i);
					if(paramElemSeg.getCodEstObtenerListaESRelac().equals(Constante.CODIGO_TDC_REGISTRADO) && rpta.getCodElemSeg().equals(paramElemSeg.getCodElementoSeguridad())){
						paramElemSeg.setCodEstObtenerListaESRelac(Constante.CODIGO_TDC_ACTIVADO);
						
					}
					resultAct.add(paramElemSeg);
				}
				
				param.setElemetosRel(resultAct);
				
				request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION , param);
			}
			
			if(!rpta.getCodRptaPrincipal().equals(Constante.CODIGO_RESULTADO_OK) ){
				String codErrEnt="";
				String mensaje="";
				
				if(!rpta.getCodRptaPrincipal().equals(Constante.CODIGO_RESULTADO_OK))	{
					 	codErrEnt = ObjectUtil.setearCaracterLeft(rpta.getCodRptaPrincipal().toString(), '0', 4-rpta.getCodRptaPrincipal().toString().length());
					 	mensaje=aplicacion.getMsjesHost("CD",codErrEnt).elementAt(2).toString();
				}	
		
				
				
				throw new ArrayRuleException(ConstanteError.GENERICO,mensaje+" (CD-"+codErrEnt+")");
				
			}
			
			
					
		}catch(ArrayRuleException ar){
				log3.error(ar,Constante.ERR_LOGICA_NEGOCIO,"");
				ar.printStackTrace();
				ar.setForward("iniciarActiv");
				throw ar;
		}
	
		TarjetaImpl tarjeta = new TarjetaImpl(usuario.getTarjeta().getNumero());
		tarjeta.setCodigoOperacion(""+rpta.getCodAutorizacion());
		request.getSession().setAttribute(ConstanteSesion.TARJETA,tarjeta);
		
		ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_SEGURIDAD_CLAVE_DINAMICA_ACTIVACION, ConstanteSesion.TARJETA, request);
		refreshMenu(mapping, form, request, response);
		return mapping.findForward("generarClave");
	}
	

	
	public ActionForward aprobarbloqueoTDC(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
				
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		
		String tipoBloqueo = request.getParameter("rdnMotPerdida");
		
		String rptaConsulta = "";
		
		  if(request.getSession().getAttribute("mensajeBloquearCDCab")!=null){
				request.getSession().setAttribute("mensajeBloquearCDCab","");	
				
		  }
		
		//Validar datos de TDC
		try{
			rptaConsulta = SolicitarServiciosTDC.solicitarNumTDC(param);
			
			param.setTipoBloqueo(tipoBloqueo);
			request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
			
			if(rptaConsulta==null ||rptaConsulta.equals("")){
				throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_ERROR_CONSULTAR_TARJETA_COORDENADAS);
				
			}
			
		}catch(ArrayRuleException ar){
			log3.error(ar,Constante.ERR_LOGICA_NEGOCIO,"");

			ar.printStackTrace();
			ar.setForward("iniciarBloqueo");
				throw ar;
		}
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		ParametrosElemSegTDC elementos = null;
		
		elementos = SolicitarServiciosTDC.verificarTipoElementoAct(param);
		
		
		if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
			request.getSession().setAttribute("mensajeBloquearCDCab",((Vector)aplicacion.getMensajePorCodigo("CD01","00004")).elementAt(2).toString());
		} else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS)){
			request.getSession().setAttribute("mensajeBloquearCDCab",((Vector)aplicacion.getMensajePorCodigo("CD01","00017")).elementAt(2).toString());
		}
		request.getSession().setAttribute("mensajeBloquearCDInf",((Vector)aplicacion.getMensajePorCodigo("GC02","00002")).elementAt(2).toString());
		
		request.getSession().setAttribute("tarformat",rptaConsulta);
		
			
		return mapping.findForward("aprobarbloqueoTDC");
	}
	
	public ActionForward confirmarBloqueoTDC(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		MensajesTDC rptaBloqueo = new MensajesTDC();
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION , param);
		
		String claveInternet = SeguridadUtil.getClaveDesencriptada(request.getParameter("txtClaveInternet"),request);
		Usuario usuario 		= (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		String numTDC 		= request.getParameter("numTDC");
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		
		  if(request.getSession().getAttribute("mensajeBloqueoCDExito")!=null){
				request.getSession().setAttribute("mensajeBloqueoCDExito","");	
				
		  }
		
		//Validar Clave de 6 dígitos.
		try{
			FacadeFactory.getGeneralFacade().processValidationCardAhorro(usuario.getTarjeta().getNumero(),usuario.getTarjeta().getClave(),claveInternet,claveInternet);
		}
		catch (ArrayRuleException e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");

			e.setForward("aprobarbloqueoTDC");
			throw e;
		}
		
		//Bloquear TDC		
		try{
			
			List resultBloq = new ArrayList();
						
			rptaBloqueo = SolicitarServiciosTDC.bloquearTDC(param,numTDC);
			
		
			
			if(rptaBloqueo.getCodRptaPrincipal() == 0){
				
					for(int i=0;i<param.getElemetosRel().size();i++){
					
					ParametrosElemSegTDC paramElemSeg = (ParametrosElemSegTDC)param.getElemetosRel().get(i);
					if(paramElemSeg.getCodEstObtenerListaESRelac().equals(Constante.CODIGO_TDC_ACTIVADO) ){
						paramElemSeg.setCodEstObtenerListaESRelac(Constante.CODIGO_TDC_BLOQUEADO);
						
						if(paramElemSeg.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
						request.getSession().setAttribute("mensajeBloqueoCDExito",((Vector)aplicacion.getMensajePorCodigo("CD01","00005")).elementAt(2).toString());
						//request.getSession().setAttribute("mensajeBloqueoCDExito",((Vector)aplicacion.getMensajePorCodigo("CD01","00018")).elementAt(2).toString());
						} else if(paramElemSeg.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS)){
						request.getSession().setAttribute("mensajeBloqueoCDExito",((Vector)aplicacion.getMensajePorCodigo("CD01","00018")).elementAt(2).toString());
						
						}
					resultBloq.add(paramElemSeg);
				  }
					
				}
				
				param.setElemetosRel(resultBloq);
				
				
			}
			
			else{
				if(!rptaBloqueo.getCodRptaPrincipal().equals(Constante.CODIGO_RESULTADO_OK) ){
					String codErrEnt="";
					String mensaje="";
					
					if(!rptaBloqueo.getCodRptaPrincipal().equals(Constante.CODIGO_RESULTADO_OK))	{
						 	codErrEnt = ObjectUtil.setearCaracterLeft(rptaBloqueo.getCodRptaPrincipal().toString(), '0', 4-rptaBloqueo.getCodRptaPrincipal().toString().length());
						 	mensaje=aplicacion.getMsjesHost("CD",codErrEnt).elementAt(2).toString();
					}	
			
					
					
					throw new ArrayRuleException(ConstanteError.GENERICO,mensaje+" (CD-"+codErrEnt+")");
					
				}
				
			}
					
		}catch(ArrayRuleException ar){
				log3.error(ar,Constante.ERR_LOGICA_NEGOCIO,"");

				ar.printStackTrace();
				ar.setForward("aprobarbloqueoTDC");
				throw ar;
		}

		TarjetaImpl tarjeta = new TarjetaImpl(usuario.getTarjeta().getNumero());
		tarjeta.setCodigoOperacion(rptaBloqueo.getCodAutorizacion());
		request.getSession().setAttribute(ConstanteSesion.TARJETA,tarjeta);
	
		ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_SEGURIDAD_CLAVE_DINAMICA_BLOQUEO, ConstanteSesion.TARJETA, request);
			
		return mapping.findForward("confirmarBloqueoTDC");
	}
	
	

	
	private Afiliacion setearAfiliacion(ClaveDinamicaForm frm,Tarjeta tarjeta) throws Exception {
		Afiliacion afiliacion = new AfiliacionImpl();
		
			afiliacion.setTipoAfiliacion(Constante.SERVICIOS);
			afiliacion.setNroTarjeta(tarjeta.getNumero());
			afiliacion.setTarjetaOculta(ObjectUtil.ocultarCuenta(tarjeta.getNumero(),Constante.FORMATO_TARJETA));
			afiliacion.setTipoDocumento(frm.getCmbTipoDocIdent());
			afiliacion.setNroDocumento(frm.getTxtNumDoc());
			afiliacion.setFechaNacimiento(ObjectUtil.stringToTimestamp(frm.getTxtDia(),frm.getCmbMes(),frm.getTxtAnio()));
			afiliacion.setSexo(frm.getRdnSexo());
			afiliacion.setEmail(frm.getTxtMail().trim());
			afiliacion.setBeneficiario(frm.getTxtNombre().trim()+"-"+frm.getTxtApellidoPaterno().trim()+"-"+frm.getTxtApellidoMaterno().trim());
		
		return afiliacion;
	}
		
	private void loadObject(HttpServletRequest request)throws Exception {
	
		List lista= FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_DET_DOCU_OTRO);
		lista.remove(0);
		List lstDocumentoBen =  FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_DOCU_GIROS);	
		request.setAttribute("lstDocumentoBen",lstDocumentoBen);
		
		request.setAttribute("lstDocumento",lista);
		
			request.setAttribute("lstLocalidad", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.COD_HLP_DET_LOCAL));
				request.setAttribute("lstMes", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.COD_HLP_DET_MES));
	}
	
	
	public void refreshMenu(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Usuario usuario = (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);

		System.out.println(":::::::::refreshMenu FROM CLAVESMS::::::::::::::::::::::");
		String tipoPersona 		= usuario.getTipoPersona();
		String imgCerrarSession = "";
		
		Menu[] arrMenu = null;
		String ip = request.getRemoteAddr();
		if (Constante.COD_PERSON_NAT.equals(tipoPersona)){
			imgCerrarSession = Constante.IMG_CLOSE_SESSION_PN;
			arrMenu = FacadeFactory.getLoginFacade().getMenuByCode1(Constante.COD_PERSON_NAT,usuario.getTipoTarjeta(),ip);
		}else{
			arrMenu = FacadeFactory.getLoginFacade().getMenuByCode1(tipoPersona,usuario.getTipoTarjeta(),ip);
			imgCerrarSession = Constante.IMG_CLOSE_SESSION_PJ;
		}
		
		String cNumTarjeta = usuario.getCodigoCLDI();
		ParametrosTDC parametros = new ParametrosTDC();
		parametros = CargarParametrosTDC.mostrarParamentroTDC(cNumTarjeta);
		usuario.setClaveDinamica(parametros);																
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,parametros);	
		
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		ParametrosElemSegTDC elementos = null;
		String replaceMsg = "";
		LoginAction loginAction = new LoginAction();
		String flagSms = Constante.REDIRECT_BASE; //-> /claveSMS
		
		try{
			elementos = loginAction.setTipoElementoSeguridad(param, usuario, request);
			request.getSession().setAttribute("tipoElemento",elementos);
		} catch (Exception e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		
		
		replaceMenuClaveSms(elementos, arrMenu);
		replaceMenuTokenFisico(elementos, arrMenu);
		
		request.setAttribute("arrMenu",arrMenu);
		request.setAttribute("imgCerrarSession",imgCerrarSession);
	}
	
	private Menu[] replaceMenuClaveSms(ParametrosElemSegTDC elementos, Menu[] arrMenu){
		String replaceMsg = "";
		String flagSms = Constante.REDIRECT_BASE; //-> /claveSMS
		String strReplace = "";
		
		if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_MIGRACION))
		{
			replaceMsg = Constante.DESC_MIGRACION;
			//flagSms = flagSms+Constante.REDIRECT_MIGRACION;
			flagSms = flagSms+Constante.REDIRECT_AFILIACION;
		}
		else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS))
		{
			replaceMsg = StringUtils.EMPTY;
			flagSms = flagSms+Constante.REDIRECT_DESAFILIACION;

		}
		else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_ACTIVACION))
		{
			replaceMsg = Constante.DESC_ACTIVACION;
			flagSms = flagSms+Constante.REDIRECT_ACTIVACION;
		}
		else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_AFILIADO_DESAFILIADO))
		{
			replaceMsg = Constante.DESC_AFILIACION;
			flagSms = flagSms+Constante.REDIRECT_AFILIACION;
		}
		else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_AFILIACION))
		{
			replaceMsg = Constante.DESC_AFILIACION;
			flagSms = flagSms+Constante.REDIRECT_AFILIACION;
		}
		
		for (int i = 0; i < arrMenu.length; i++){
		    if(arrMenu[i].getSubMenu() != null && arrMenu[i].getSubMenu().length > 0){
		    	strReplace = arrMenu[i].getDescriptionOption().replace("[notaClaveSMS]", replaceMsg);
		    	arrMenu[i].setDescriptionOption(strReplace);
		    	if(strReplace.contains("SMS")) arrMenu[i].setMessageOption(flagSms);
//		    	System.out.println("menu.getDescriptionOption()::::::::: " + arrMenu[i].getDescriptionOption());
		    	
		    	Menu[] sb1 = arrMenu[i].getSubMenu();
//		    	System.out.println("sb1.length:::: "+sb1.length);
		    	for (int j = 0; j < sb1.length; j++){
		    		
		    		if(sb1[j].getSubMenu2() != null && sb1[j].getSubMenu2().length > 0){
		    			strReplace = arrMenu[i].getSubMenu()[j].getDescriptionOption().replace("[notaClaveSMS]", replaceMsg);
		    			arrMenu[i].getSubMenu()[j].setDescriptionOption(strReplace);
		    			if(strReplace.contains("SMS")) arrMenu[i].getSubMenu()[j].setMessageOption(flagSms);
//			    		System.out.println("sb1.getDescriptionOption():::::::::::: " + arrMenu[i].getSubMenu()[j].getDescriptionOption());
			    		
		    			Menu[] sb2 = sb1[j].getSubMenu2();
//		    			System.out.println("sb2.length:::: "+sb2.length);
		    			for(int k = 0; k < sb2.length; k++){
		    				strReplace = arrMenu[i].getSubMenu()[j].getSubMenu2()[k].getDescriptionOption().replace("[notaClaveSMS]", replaceMsg);
		    				arrMenu[i].getSubMenu()[j].getSubMenu2()[k].setDescriptionOption(strReplace);
		    				if(strReplace.contains("SMS")) arrMenu[i].getSubMenu()[j].getSubMenu2()[k].setMessageOption(flagSms);
//		    				System.out.println("sb2.getDescriptionOption()::::::::::::::: " + arrMenu[i].getSubMenu()[j].getSubMenu2()[k].getDescriptionOption());
		    			}	
		    			
		    		}else{
		    			strReplace = arrMenu[i].getSubMenu()[j].getDescriptionOption().replace("[notaClaveSMS]", replaceMsg);
		    			arrMenu[i].getSubMenu()[j].setDescriptionOption(strReplace);
		    			if(strReplace.contains("SMS")) arrMenu[i].getSubMenu()[j].setMessageOption(flagSms);
//			    		System.out.println("sb1.getDescriptionOption():::::::::::: " + arrMenu[i].getSubMenu()[j].getDescriptionOption());
		    		}
		    		
		    	}

		    }else{
		    	strReplace = arrMenu[i].getDescriptionOption().replace("[notaClaveSMS]", replaceMsg);
		    	arrMenu[i].setDescriptionOption(strReplace);
//		    	System.out.println("menu.getDescriptionOption()::::::::: " + arrMenu[i].getDescriptionOption());
		    }
		}
		
		return arrMenu;
	}
	
	private Menu[] replaceMenuTokenFisico(ParametrosElemSegTDC elementos, Menu[] arrMenu){
		
		String replaceMsg = "";
		//String flagSms = Constante.REDIRECT_BASE; //-> /claveSMS
		String strReplace = "";
		
		if(elementos.getEstadoToken().equals(Constante.CODIGO_ESTADO_TOKENS_FISICO))
		{
			replaceMsg = Constante.DESC_TOKEN_FISICO_ACTIVACION;
		} else {
			replaceMsg = Constante.DESC_TOKEN_FISICO_BLANCO;
		}
		
		for (int i = 0; i < arrMenu.length; i++){
		    if(arrMenu[i].getSubMenu() != null && arrMenu[i].getSubMenu().length > 0){
		    	strReplace = arrMenu[i].getDescriptionOption().replace("[notaTokenFisico]", replaceMsg);
		    	arrMenu[i].setDescriptionOption(strReplace);
		    	//if(strReplace.contains("Token Físico")) arrMenu[i].setMessageOption(flagSms);
//		    	System.out.println("menu.getDescriptionOption()::::::::: " + arrMenu[i].getDescriptionOption());
		    	
		    	Menu[] sb1 = arrMenu[i].getSubMenu();
//		    	System.out.println("sb1.length:::: "+sb1.length);
		    	for (int j = 0; j < sb1.length; j++){
		    		
		    		if(sb1[j].getSubMenu2() != null && sb1[j].getSubMenu2().length > 0){
		    			strReplace = arrMenu[i].getSubMenu()[j].getDescriptionOption().replace("[notaTokenFisico]", replaceMsg);
		    			arrMenu[i].getSubMenu()[j].setDescriptionOption(strReplace);
		    			//if(strReplace.contains("Token Físico")) arrMenu[i].getSubMenu()[j].setMessageOption(flagSms);
//			    		System.out.println("sb1.getDescriptionOption():::::::::::: " + arrMenu[i].getSubMenu()[j].getDescriptionOption());
			    		
		    			Menu[] sb2 = sb1[j].getSubMenu2();
//		    			System.out.println("sb2.length:::: "+sb2.length);
		    			for(int k = 0; k < sb2.length; k++){
		    				strReplace = arrMenu[i].getSubMenu()[j].getSubMenu2()[k].getDescriptionOption().replace("[notaTokenFisico]", replaceMsg);
		    				arrMenu[i].getSubMenu()[j].getSubMenu2()[k].setDescriptionOption(strReplace);
		    				//if(strReplace.contains("Token Físico")) arrMenu[i].getSubMenu()[j].getSubMenu2()[k].setMessageOption(flagSms);
//		    				System.out.println("sb2.getDescriptionOption()::::::::::::::: " + arrMenu[i].getSubMenu()[j].getSubMenu2()[k].getDescriptionOption());
		    			}	
		    			
		    		}else{
		    			strReplace = arrMenu[i].getSubMenu()[j].getDescriptionOption().replace("[notaTokenFisico]", replaceMsg);
		    			arrMenu[i].getSubMenu()[j].setDescriptionOption(strReplace);
		    			//if(strReplace.contains("Token Físico")) arrMenu[i].getSubMenu()[j].setMessageOption(flagSms);
//			    		System.out.println("sb1.getDescriptionOption():::::::::::: " + arrMenu[i].getSubMenu()[j].getDescriptionOption());
		    		}
		    		
		    	}

		    }else{
		    	strReplace = arrMenu[i].getDescriptionOption().replace("[notaTokenFisico]", replaceMsg);
		    	arrMenu[i].setDescriptionOption(strReplace);
//		    	System.out.println("menu.getDescriptionOption()::::::::: " + arrMenu[i].getDescriptionOption());
		    }
		}
		
		return arrMenu;
	}
	
}