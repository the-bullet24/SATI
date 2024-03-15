/*
 * Creado el 27/02/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.bn.tarjeta.action;

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
import pe.bn.login.domain.Menu;
import pe.bn.notificaciones.action.ServicioNotificacionConstancia;
import pe.bn.tarjeta.domain.Tarjeta;
import pe.bn.tarjeta.domain.impl.TarjetaImpl;
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
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.OperacionImpl;
/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class BloquearTarjetaAction extends GrandActionAbstract{
	private static LoggerSati log3 = LoggerSati.getInstance(BloquearTarjetaAction.class.getName());

	
	public ActionForward iniciar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {		
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		request.getSession().setAttribute("mensajebloqueocabeceraTDC",null);
		request.getSession().setAttribute("mensajebloqueopie",((Vector)aplicacion.getMensajePorCodigo("GC03","00002")).elementAt(2).toString());
		request.getSession().setAttribute("mensajebloqueocabecera",((Vector)aplicacion.getMensajePorCodigo("GC03","00003")).elementAt(2).toString());
		
		String MSGBLOQUEOINFO = ((Vector)aplicacion.getMensajePorCodigo("GC03","00001")).elementAt(2).toString(); 
		request.getSession().setAttribute("mensajebloqueoinfo",Constante.MSGBLOQUEOINFO);
		request.getSession().setAttribute("mensajebloqueoexito",((Vector)aplicacion.getMensajePorCodigo("GC03","00004")).elementAt(2).toString());
		String MSGBLOQUEOATTE = ((Vector)aplicacion.getMensajePorCodigo("GC03","00005")).elementAt(2).toString();
		request.getSession().setAttribute("mensajebloqueoatte",Constante.MSGBLOQUEOATTE);
		request.getSession().setAttribute("mensajeaprobloqtarj",((Vector)aplicacion.getMensajePorCodigo("GC03","00006")).elementAt(2).toString());
		request.getSession().setAttribute("mensajebloqueoInf",((Vector)aplicacion.getMensajePorCodigo("GC03","00007")).elementAt(2).toString());
		request.getSession().setAttribute("mensajebloqueopieCD",((Vector)aplicacion.getMensajePorCodigo("CD01","00010")).elementAt(2).toString());
		request.getSession().setAttribute("mensajebloqueopiedetCD",((Vector)aplicacion.getMensajePorCodigo("CD01","00011")).elementAt(2).toString());
		
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);

		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		
		return mapping.findForward("iniciarBloqueoTarjeta");
	}
	
	public ActionForward AprobarBloqueoTarjeta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Usuario usuario 		= (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		String tarformat= ObjectUtil.formatearCuenta(usuario.getTarjeta().getNumero(),Constante.FORMATO_TARJETA);
		String mod1= tarformat.substring(0,4);
		String mod2= tarformat.substring(15,19), xx="-****-****-";
		tarformat=mod1+xx+mod2;
		request.getSession().setAttribute("tarformat",tarformat);
				
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);

		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);


		return mapping.findForward("AprobarBloqueoTarjeta");	
	}
	
	public ActionForward confirmarBloqueoTarjeta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {				
		BNAplicacion aplicacion = BNAplicacion.getInstance();
       String transaccion		= request.getParameter("transaccion"); //GC03
	    Usuario usuario 		= (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
	    Map map = UtilAction.cargarVar(request,request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION));
	    map.put("tarformat",request.getSession().getAttribute("tarformat"));
		OperacionImpl.setVariables(map);
		
		 
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);

		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
					
				try{
					
						Tarjeta tarjeta= FacadeFactory.getTarjetaFacade().bloquearTarjeta(usuario,transaccion,request.getRemoteAddr(),request);
					
					    tarjeta.setNumero(ObjectUtil.formatearCuenta(tarjeta.getNumero(),Constante.FORMATO_TARJETA));
						request.getSession().setAttribute(ConstanteSesion.TARJETA,tarjeta);
						request.getSession().setAttribute("mensajebloqueoexitoCD",((Vector)aplicacion.getMensajePorCodigo("CD01","00013")).elementAt(2).toString());

						ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_BLOQUEO_TARJETA, ConstanteSesion.TARJETA, request);
									
				}
				catch (ArrayRuleException e) {
					log3.error(e,Constante.ERR_ERROR_VALIDAR_TDC,"");
					e.printStackTrace();
					
					e.setForward("AprobarBloqueoTarjeta");
					throw e;
				}
		return mapping.findForward("confirmarBloqueoTarjeta");	
		
	}
	

}
