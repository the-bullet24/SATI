/*
 * Creado el 26/02/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.bn.tarjeta.action;

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
import pe.bn.login.domain.impl.ImagenTarjetaImpl;
import pe.bn.notificaciones.action.ServicioNotificacionConstancia;
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
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.OperacionImpl;
import pe.cosapi.system.GeneratorKeys;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class DesafiliarClaveAction extends GrandActionAbstract{
	private static LoggerSati log3 = LoggerSati.getInstance(DesafiliarClaveAction.class.getName());
	
	
	public ActionForward iniciar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {		
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		request.getSession().setAttribute("mensajedesafiliacioninfo",((Vector)aplicacion.getMensajePorCodigo("GC04","00001")).elementAt(2).toString());
		request.getSession().setAttribute("mensajedesafiliacionexito",((Vector)aplicacion.getMensajePorCodigo("GC04","00002")).elementAt(2).toString());
		request.getSession().setAttribute("mensajedesafiliacionpie",((Vector)aplicacion.getMensajePorCodigo("GC04","00003")).elementAt(2).toString());
		request.getSession().setAttribute("mensajedesafiliacionatte",((Vector)aplicacion.getMensajePorCodigo("GC04","00004")).elementAt(2).toString());
		request.getSession().setAttribute("mensajedesafiliacionInf",((Vector)aplicacion.getMensajePorCodigo("GC04","00005")).elementAt(2).toString());
		
		
		//REALIZAR CONSULTA DE COORDENADAS
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);

		return mapping.findForward("iniciarDesafiliarClave");
	}

	public ActionForward desafiliarClaveInternet(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {		
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		String claveTarjeta		= request.getParameter("txtClaveInternet"); 
		String claveInternet	= request.getParameter("txtClaveInternet");
		String claveInternet_	= request.getParameter("txtClaveInternet_");
		String transaccion		= request.getParameter("transaccion");
		
		String dencriptClaveTarjeta 	= getClaveDesencriptada(claveTarjeta,request);
		String dencriptclaveInternet 	= getClaveDesencriptada(claveInternet,request);
		String dencriptclaveInternet_ 	= getClaveDesencriptada(claveInternet_,request);
		
		//if(Constante.VER_LOG)System.out.println("dencriptClaveTarjeta:"+dencriptClaveTarjeta);
		//if(Constante.VER_LOG)System.out.println("dencriptclaveInternet:"+dencriptclaveInternet);
		//if(Constante.VER_LOG)System.out.println("dencriptclaveInternet_:"+dencriptclaveInternet_);
		
		// Para poder cargar los datos de la mensajistica
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		request.setAttribute("mensajePantalla",((Vector)aplicacion.getMensajePorCodigo("GC01","00001")).elementAt(2).toString());
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		ParametrosElemSegTDC elementos = null;
		
		
		try{
			
		
			
			FacadeFactory.getGeneralFacade().processValidationCardAhorro(usuario.getTarjeta().getNumero(),usuario.getTarjeta().getClave(),dencriptClaveTarjeta,dencriptClaveTarjeta);
		
				
			Map map = UtilAction.cargarVar(request,request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION));
			OperacionImpl.setVariables(map);
			request.getSession().setAttribute(ConstanteSesion.TARJETA,FacadeFactory.getTarjetaFacade().desafiliarClaveInternet(usuario,transaccion,dencriptClaveTarjeta,dencriptclaveInternet,dencriptclaveInternet_, request.getRemoteAddr(),request));
			
			ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_SEGURIDAD_CLAVE_INTERNET_DESAFILIACION, ConstanteSesion.TARJETA, request);
		}
		
		catch (ArrayRuleException e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			e.printStackTrace();
			e.setForward("iniciarDesafiliarClave");
			throw e;
		}
		
		return mapping.findForward("confirmarDesafiliarClave");	
	}
	
	private String  getClaveDesencriptada(String passEncript, HttpServletRequest request){
		GeneratorKeys gen = new GeneratorKeys();
		Map mapa = (Map) SeguridadUtil.getObjectSession(ConstanteSesion.MAP_VALUES,request);
		//if(Constante.VER_LOG)System.out.println("request.getParameter('hdnValue')="+request.getParameter("hdnValue"));
		String  valorClave = gen.getClave(mapa,request.getParameter("hdnValue"),passEncript);
		return valorClave;
	}
	
}
