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
import pe.bn.login.domain.Menu;
import pe.bn.tarjeta.domain.Tarjeta;
import pe.bn.tarjeta.domain.impl.TarjetaImpl;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.action.GrandActionAbstract;
import pe.cosapi.action.UtilAction;
import pe.cosapi.common.BNAplicacion;
import pe.cosapi.common.Constante;
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
public class BloquearTarjetaDniAction extends GrandActionAbstract{
	private static LoggerSati log3 = LoggerSati.getInstance(BloquearTarjetaDniAction.class.getName());

	
	public ActionForward iniciar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {		
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		request.getSession().setAttribute("mensajebloqueopie",((Vector)aplicacion.getMensajePorCodigo("GC03","00002")).elementAt(2).toString());
		request.getSession().setAttribute("mensajebloqueocabecera",((Vector)aplicacion.getMensajePorCodigo("GC03","00003")).elementAt(2).toString());
		String MSGBLOQUEOINFO = ((Vector)aplicacion.getMensajePorCodigo("GC03","00001")).elementAt(2).toString(); 
		request.getSession().setAttribute("mensajebloqueoinfo",Constante.MSGBLOQUEOINFO);
		request.getSession().setAttribute("mensajebloqueoexito",((Vector)aplicacion.getMensajePorCodigo("GC03","00004")).elementAt(2).toString());
		String MSGBLOQUEOATTE = ((Vector)aplicacion.getMensajePorCodigo("GC03","00005")).elementAt(2).toString();
		request.getSession().setAttribute("mensajebloqueoatte",Constante.MSGBLOQUEOATTE);
		request.getSession().setAttribute("mensajeaprobloqtarj",((Vector)aplicacion.getMensajePorCodigo("GC03","00006")).elementAt(2).toString());
		request.getSession().setAttribute("mensajebloqueoInf",((Vector)aplicacion.getMensajePorCodigo("GC03","00007")).elementAt(2).toString());
		
		return mapping.findForward("iniciaBloqueoDni");
	}
	
	public ActionForward AprobarBloqueoTarjeta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Usuario usuario 		= (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		String tarformat= ObjectUtil.formatearCuenta(usuario.getTarjeta().getNumero(),Constante.FORMATO_TARJETA);
		String mod1= tarformat.substring(0,4);
		String mod2= tarformat.substring(15,19), xx="-****-****-";
		tarformat=mod1+xx+mod2;
		request.getSession().setAttribute("tarformat",tarformat);
		return mapping.findForward("confirmarBloqueo");	
	}
	
	public ActionForward confirmarBloqueoTarjeta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {				
		
       String transaccion		= request.getParameter("transaccion"); //GC03
              
	    Usuario usuario 		= (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
	    Map map = UtilAction.cargarVar(request,request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION));
	    map.put("tarformat",request.getSession().getAttribute("tarformat"));
		OperacionImpl.setVariables(map);
	    Tarjeta tarjeta= FacadeFactory.getTarjetaFacade().bloquearTarjetaDni(usuario,transaccion,request.getRemoteAddr(),request); //Descomentar
		
		tarjeta.setNumero(ObjectUtil.formatearCuenta(tarjeta.getNumero(),Constante.FORMATO_TARJETA));
		request.getSession().setAttribute(ConstanteSesion.TARJETA,tarjeta);
		
		return mapping.findForward("finBloqueo");	
		
	}
	

}
