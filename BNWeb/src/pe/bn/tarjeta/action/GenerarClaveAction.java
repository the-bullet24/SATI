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
import pe.bn.cldinamica.action.CargarParametrosTDC;
import pe.bn.cldinamica.action.form.ParametrosTDC;
import pe.bn.login.domain.impl.ImagenTarjetaImpl;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.action.GrandActionAbstract;
import pe.cosapi.action.UtilAction;
import pe.cosapi.common.ArrayRuleException;
import pe.cosapi.common.BNAplicacion;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteSesion;
import pe.cosapi.common.FacadeFactory;
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
public class GenerarClaveAction extends GrandActionAbstract{
	private static LoggerSati log3 = LoggerSati.getInstance(GenerarClaveAction.class.getName());
	
	
	public ActionForward iniciar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {		
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		request.getSession().setAttribute("mensajegeneracioncabecera",((Vector)aplicacion.getMensajePorCodigo("GC01","00002")).elementAt(2).toString());
		request.getSession().setAttribute("mensajegeneracionpie",((Vector)aplicacion.getMensajePorCodigo("GC01","00003")).elementAt(2).toString());
		request.getSession().setAttribute("mensajegeneracionatte",((Vector)aplicacion.getMensajePorCodigo("GC01","00004")).elementAt(2).toString());
		request.getSession().setAttribute("mensajegeneracionexito",((Vector)aplicacion.getMensajePorCodigo("GC01","00005")).elementAt(2).toString());
		request.getSession().setAttribute("mensajegeneracionInf",((Vector)aplicacion.getMensajePorCodigo("GC01","00006")).elementAt(2).toString());
	
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);

		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);

		return mapping.findForward("iniciarGeneracionClaveInternet");
	}

	public ActionForward generarClaveInternet(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {		
		String claveTarjeta		= request.getParameter("txtClaveTarjeta"); 
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
		
		Usuario usuario 		= (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		  Map map = UtilAction.cargarVar(request,request.getSession().getAttribute(ConstanteSesion.TARJETA));
			OperacionImpl.setVariables(map);
		request.getSession().setAttribute(ConstanteSesion.TARJETA,FacadeFactory.getTarjetaFacade().generarClaveInternet(usuario,transaccion,dencriptClaveTarjeta,dencriptclaveInternet,dencriptclaveInternet_, request.getRemoteAddr()));
		//ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		
		List listaTarjeta = null;
		ParametrosTDC parametros = new ParametrosTDC();
		try{
			
			parametros = CargarParametrosTDC.mostrarParamentroTDC(usuario.getTarjeta().getNumero());
			request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,parametros);
			
			listaTarjeta = FacadeFactory.getLoginFacade().getValidaImagenMV(usuario.getTarjeta().getNumero());
			
			if(listaTarjeta != null){
				
				ImagenTarjetaImpl imagenMV = (ImagenTarjetaImpl) listaTarjeta.get(0);
				request.getSession().setAttribute("txtImageSelloThumb", "/imagenes/sello/thumb/"+imagenMV.getCodCat()+ "Tarjeta"+imagenMV.getNomValorImg().substring(6)+".png");
			
			}
			else{
				request.getSession().setAttribute("txtImageSelloThumb", null);
			}
						
			
		}catch(ArrayRuleException ar){
				log3.error(ar,Constante.ERR_CARGA_PARAMETROS_TDC,"");
				ar.printStackTrace();
				ar.setForward("iniciar");
				throw ar;
		}

		//request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);


		return mapping.findForward("confirmarGeneracionClaveInternet");	
	}
	
	private String  getClaveDesencriptada(String passEncript, HttpServletRequest request){
		GeneratorKeys gen = new GeneratorKeys();
		Map mapa = (Map) SeguridadUtil.getObjectSession(ConstanteSesion.MAP_VALUES,request);
		//if(Constante.VER_LOG)System.out.println("request.getParameter('hdnValue')="+request.getParameter("hdnValue"));
		String  valorClave = gen.getClave(mapa,request.getParameter("hdnValue"),passEncript);
		return valorClave;
	}
	
}
