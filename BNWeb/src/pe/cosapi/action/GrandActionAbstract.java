package pe.cosapi.action;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.soap.SOAPException;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.log4j.NDC;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import pe.bn.afiliacion.action.DesafCableAction;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.common.ArrayRuleException;
import pe.cosapi.common.BNAplicacion;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteError;
import pe.cosapi.common.ConstanteSesion;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.SeguridadUtil;

public abstract class GrandActionAbstract extends Action implements Serializable{
	private static LoggerSati log3 = LoggerSati.getInstance(GrandActionAbstract.class.getName());
	
	protected Class clazz = this.getClass();
	
	protected Class types[] = {
		ActionMapping.class, ActionForm.class,
		HttpServletRequest.class, HttpServletResponse.class};
	
	protected HashMap methods = new HashMap();
	
	//public Logger logger = Logger.getLogger(this.getClass());

	public static String PREFIX_COMBO = "cbo";
	
	public static String PREFIX_TEXT = "txt";

	public static String PREFIX_HIDDEN = "hdn";
		
	public GrandActionAbstract(){
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		//logger = 	Logger.getLogger(this.getClass());
		request.getSession().setAttribute("mensaje1Hor",null);
		request.getSession().setAttribute("mensaje2Hor",null);
		request.getSession().setAttribute("mensajeHorC",null);
		
		
		if(!SeguridadUtil.isConsulta(this)) {
		//if (Constante.FLAG_CACHE.equals("0")){
		    //System.out.println("SE ACTIVO CONTROL CACHE");
		    response.setHeader("Pragma", "no-cache" );
			response.addHeader("Cache-Control", "must-revalidate" );
			response.addHeader("Cache-Control", "no-cache" );
			response.addHeader("Cache-Control", "no-store" );
			response.setDateHeader("Expires", 0);
		//} else {
		  //  Constante.FLAG_CACHE = "0";
		}
		//}
		
		//*******************************************************************************
		request.getSession().setAttribute(ConstanteSesion.CARPETA,Constante.COD_PERSON_NAT);
		if(!FacadeFactory.getGeneralFacade().getEstadoAplicacion())
		{	System.out.println("sistema.inactivo");
			return mapping.findForward("sistema.inactivo");
		}
		//*******************************************************************************
		String transaccion = request.getParameter("transaccion");
		
		/**   INICIO VALIDACION DE HORARIO DE LAS TRANSACCIONES         **/
		//*******************************************************************************

		BNAplicacion aplicacion = BNAplicacion.getInstance();
		request.getSession().setAttribute("mensajeHorC",null);
		String hrtrx=request.getParameter("HrTrx");
		//System.out.println("hrtrx:"+hrtrx);
		
//			if(!getVerificarHorario(hrtrx==null?"0000":hrtrx)){
//				try {
//				
//					request.getSession().setAttribute("mensaje1Hor",((Vector)aplicacion.getMensajePorCodigo("MH01","MH001")).elementAt(2).toString());
//					request.getSession().setAttribute("mensaje2Hor",((Vector)aplicacion.getMensajePorCodigo("MH01","MH002")).elementAt(2).toString());
//					request.getSession().setAttribute("mensajeHorC",((Vector)aplicacion.getMensajePorCodigo("MH01",hrtrx==null?"0112":hrtrx)).elementAt(2).toString());
//				} catch (Exception e1) {
//					log3.error(e1,Constante.ERR_LOGICA_NEGOCIO,"");
//					e1.printStackTrace();
//				}
//				return mapping.findForward("transaccion.noDisponible");
//			}

		/**   FIN VALIDACION DE HORARIO DE LAS TRANSACCIONES         **/
		
		
		StringBuffer sb = new StringBuffer();

		sb.delete(0,sb.length());
		sb.append("[remoteIP=").append(request.getRemoteAddr()).append("]");
		NDC.push(sb.toString());

		ActionForward forward = null;
		
		if(!SeguridadUtil.isPaginaInicio(this)) {
			/*
			if(Constante.HOST_ERROR_DD_3024){
				request.getSession(true).setAttribute(ConstanteSesion.CARPETA,Constante.COD_PERSON_NAT);
				return mapping.findForward("sesion.expirada");
			}*/
			
			response.setStatus(HttpStatus.SC_OK);
			if(!SeguridadUtil.existeUsuarioSesion(request)) {
				if(Constante.VER_LOG) System.out.println("No hay usuario en sesion");
				HttpSession session = request.getSession();
				response.setStatus(HttpStatus.SC_REQUEST_TIMEOUT);
				//System.out.println("ID SESSION:"+session.getId());
				//session.setMaxInactiveInterval(20);
				
				long duracion = request.getSession().getLastAccessedTime()-request.getSession().getCreationTime();
				
				
				System.out.println("***--- Fin Session - Duración:"+duracion);
				
				Date dcreate = new Date(request.getSession().getCreationTime());
				Date dfinal = new Date(request.getSession().getLastAccessedTime());
				
				System.out.println("***--- Fin Session - Creación - dcreate:"+dcreate);
				System.out.println("***--- Fin Session - Final - dfinal:"+dfinal);
				
				
				log3.debug(null, "***--- Fin Session - Creación - dcreate:",dcreate.toString());
				
				log3.debug(null, "***--- Fin Session - Final - dfinal:",dfinal.toString());
				
				
				session.invalidate();
				request.getSession(true).setAttribute(ConstanteSesion.CARPETA,Constante.COD_PERSON_NAT);
				return mapping.findForward("sesion.expirada");
			}
		}				
		
		
		
		try {

			/**
			 * ******************
			 * validacion de los ActionForms
			 * ******************
			 **/
			if( form != null) {
				ActionErrors errors = form.validate(mapping, request);
				if( errors != null && errors.size() > 0){				
					this.saveErrors(request,errors);					
					return mapping.getInputForward();
				}
			}

			/**
			 * ******************
			 * procesasmiento del metodo pedido por el usuario
			 * ******************
			 **/

			String metodoPedido = request.getParameter("metodo");
			
			//if(Constante.VER_LOG) System.out.println("metodo pedido = [" + metodoPedido +"]");
			if (metodoPedido==null || metodoPedido.equalsIgnoreCase("execute")||metodoPedido.equals(""))
				metodoPedido="iniciar";
			
			Method method = null;
			try{
				method = getMethod(metodoPedido);
			}catch(java.lang.NoSuchMethodException sme){
				log3.error(sme,Constante.ERR_LOGICA_NEGOCIO,"");
				return mapping.findForward("sesion.invalida");
			}

			Object args[] = { mapping, form, request, response};
			forward = (ActionForward) method.invoke(this, args);

			/**
			 * ******************
			 * código POST-ACTION
			 * ******************
			 **/
		}
		catch (InvocationTargetException ite) {
			//log3.error(ite,Constante.ERR_LOGICA_NEGOCIO,""); //MGL
			Throwable te = ite.getTargetException().getCause();
			
			if(te == null) 
				te = ite.getTargetException();
			

			if (te instanceof ArrayRuleException)
			{
				//if(Constante.VER_LOG)
				  //  System.out.println("Error Esperado : [ dentro del bloque InvocationTargetException: Una exception de la Clase ArrayRuleException.] ");
				ActionErrors errors = new ActionErrors();
				ArrayRuleException re = (ArrayRuleException)te;
				ActionError[] arrAE = re.getActionErrorsArray();				
				for (int i = 0; i < arrAE.length; i++){					
					errors.add(Constante.GLOBAL_MESSAGE,arrAE[i]);
				}
				//if(Constante.VER_LOG)
				  //  System.out.println("NUM ERRORS: [" + errors.size() + "]");				
				this.saveErrors(request, errors);		
				if(re.getForward()!=null){
					forward = mapping.findForward(re.getForward());
				}				
				else if (mapping.getInputForward() != null){					
					forward = mapping.getInputForward();
				}
				else {
					throw new RuntimeException("NO EXISTE EL INPUT FORWARD");
				}
			}
			else if(te instanceof SOAPException){				
				ActionErrors errors = new ActionErrors();
				String error = "ERROR: NO DETERMINADO";				
				try{
					error = BNAplicacion.getInstance().getMensajePorCodigo(ConstanteError.CODIGO_GRUPO_ERROR_WEB_SERVICE,"01524").elementAt(2).toString();
				}
				catch (Exception e) {
					log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
				}			
				errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.GENERICO,error));
				this.saveErrors(request, errors);
				if (mapping.getInputForward() != null){					
					forward = mapping.getInputForward();
				}
				else {
					throw new RuntimeException("NO EXISTE EL INPUT FORWARD");
				}
			}
			else{
					//if(Constante.VER_LOG)
						//System.out.println("Error Inesperado en el Sistema : [ Error dentro del bloque InvocationTargetException ]");
				forward = mapping.findForward("error.generico");
				request.setAttribute("objetoError", ite);
				//if(Constante.VER_LOG)
					//System.out.println(""+te);
				ite.printStackTrace();
			}			
		}
		catch (ArrayRuleException are) {
			log3.error(are,Constante.ERR_LOGICA_NEGOCIO,"");
			//if(Constante.VER_LOG)
				//	System.out.println("Error Esperado : [ Error dentro del bloque ArrayRuleException ]");
			ActionErrors errors = new ActionErrors();
			ActionError[] arrAE = are.getActionErrorsArray();
		
			//if(Constante.VER_LOG)
				//System.out.println("NUM ERRORS: [" + arrAE.length + "]");
		
			for (int i = 0; i < arrAE.length; i++)
			{
				errors.add(Constante.GLOBAL_MESSAGE, arrAE[i]);
			}
			this.saveErrors(request, errors);
		
			if(are.getForward()!=null){
				forward = mapping.findForward(are.getForward());
			}				
			else if (mapping.getInputForward() != null){
				forward = mapping.getInputForward();
			}
			else {
				throw new RuntimeException("NO EXISTE EL INPUT FORWARD");
			}
		}
		catch (Exception e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			//if(Constante.VER_LOG)System.out.println("Error Inesperado en el Sistema : [ Error dentro del bloque Exception ] : " + e.toString());
			request.setAttribute("objetoError", e);
			forward = mapping.findForward("error.generico");		
		}
		finally	{
			NDC.remove();
		}
		
		return forward;
	}

	protected Method getMethod(String name)
		throws NoSuchMethodException
		{
		//buscar si el metodo requerido existe en la clase
		//primero se busca en hashmap (cache) para optimizar performance
		synchronized (methods) {
			Method method = (Method) methods.get(name);
			if (method == null)
			{
				method = clazz.getMethod(name, types);
				methods.put(name, method);
			}
			return (method);
		}
	}
	
	public boolean getVerificarHorario(String transac) throws Exception{
			//if(Constante.VER_LOG)
	
		if(transac==null)return true;
		if(transac.equals(null))return true;
		if(transac.equals("null"))return true;
		if(transac.equals("0000"))return true;
		if(transac.equals(""))return true;
		return FacadeFactory.getGeneralFacade().getVerificarHorario(transac);
	}

	public abstract ActionForward iniciar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception;

}
	