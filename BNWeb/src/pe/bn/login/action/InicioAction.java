/*
 * Creado el 12/02/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.bn.login.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pe.bn.cldinamica.action.form.ParametrosElemSegTDC;
import pe.bn.cldinamica.action.form.ParametrosTDC;
import pe.bn.listener.ConstanteParametros;
import pe.bn.listener.Parametro;
import pe.bn.login.domain.Menu;
import pe.bn.login.domain.impl.MenuImpl;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.action.GrandActionAbstract;
import pe.cosapi.common.ArrayRuleException;
import pe.cosapi.common.BNAplicacion;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteError;
import pe.cosapi.common.ConstanteSesion;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.domain.Branch;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.CuentaImpl;
import pe.cosapi.system.GeneratorKeys;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class InicioAction extends GrandActionAbstract {
	private static LoggerSati log3 = LoggerSati.getInstance(InicioAction.class.getName());
	BNAplicacion aplicacion = BNAplicacion.getInstance();
	
	/**
	 * Metodo default de todos los actions
	 * @throws Exception
	 */
	
	
	public ActionForward iniciar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	    System.out.println("begin method iniciar");
		
		return null;
	}
	
	public ActionForward cargarCabecera(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Usuario usuario = (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		//aplicacion.getMensajes();
		String imgTipoPersona 		= "";
		String imgTipoPersonaFecha 	= "";
		
		String tipoPersona = usuario.getTipoPersona();
		Branch branch 	   = BNAplicacion.getInstance().getBranch();
		Calendar c 	= Calendar.getInstance();
		Timestamp t = new Timestamp(c.getTime().getTime());
		imgTipoPersonaFecha = branch.getBandef();
		//System.out.println("banner es..............."+imgTipoPersonaFecha);
		request.setAttribute("imgTipoPersona", imgTipoPersona);
		request.setAttribute("imgTipoPersonaFecha", imgTipoPersonaFecha);
		request.setAttribute("imgLogoBN",Constante.IMG_LOGO_BN);
		
		return mapping.findForward("cabecera");
	}
	
	
	public void cargarParametrosTDC(Usuario usuario, HttpServletRequest request){
//		REALIZAR CONSULTA TIPO CLAVE DINAMICA
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		ParametrosElemSegTDC elementos = null;
		//request.getSession().setAttribute("tipoElemento",null );
		try{
			//elementos = SolicitarServiciosTDC.verificarTipoElemento(param, usuario);
			elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
			request.getSession().setAttribute("tipoElemento",elementos );			
		}catch(ArrayRuleException ar){
			log3.error(ar,Constante.ERR_CARGA_PARAMETROS_TDC,"");
			ar.printStackTrace();
			ar.setForward("menu");
			throw ar;
		} catch (Exception e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		
		
	}
	
	public ActionForward cargarMenu(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(":::::::::cargarMenu::::::::::::::::::::::");
		
		Usuario usuario = (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);		
		String tipoPersona 		= usuario.getTipoPersona();
		Menu[] arrMenu = null;
		String ip = request.getRemoteAddr();
		String imgCerrarSession = "";

		String inter = Parametro.getString(ConstanteParametros.BN_PARAM_PARAMETRO_SATI_INTEROPERABILIDAD);

		if (Constante.COD_PERSON_NAT.equals(tipoPersona)){
			imgCerrarSession = Constante.IMG_CLOSE_SESSION_PN;
			arrMenu = FacadeFactory.getLoginFacade().getMenuByCode1(Constante.COD_PERSON_NAT,usuario.getTipoTarjeta(),ip);
		}else{
			arrMenu = FacadeFactory.getLoginFacade().getMenuByCode1(tipoPersona,usuario.getTipoTarjeta(),ip);
			imgCerrarSession = Constante.IMG_CLOSE_SESSION_PJ;
		}
		
//		try{
//			ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
//			//elementos = (ParametrosElemSegTDC)request.getSession().getAttribute("tipoElemento");
//			LoginAction loginAction = new LoginAction();
//			ParametrosElemSegTDC elementos = loginAction.setTipoElementoSeguridad(param, usuario, request);
//			request.getSession().setAttribute("tipoElemento",elementos);
//			replaceMenuClaveSms(elementos, arrMenu);
//			replaceMenuTokenFisico(elementos, arrMenu);
//		} catch (Exception e) {
//			// TODO Bloque catch generado automáticamente
//			e.printStackTrace();
//		}
		ParametrosElemSegTDC elementos = null;
		String replaceMsg = "";
		
		String flagSms = Constante.REDIRECT_BASE; //-> /claveSMS
		
		
		try{
			//elementos = SolicitarServiciosTDC.verificarTipoElemento(param, usuario); //5
			elementos = (ParametrosElemSegTDC)request.getSession().getAttribute("tipoElemento");
			/** ELIMINAR **/
			//elementos.setTipoElementoSeguridad("7"); //7
			//elementos.setTipoElementoSeguridad("5"); //5
			//elementos.setTipoElementoSeguridad("6"); //6
			//request.getSession().setAttribute("tipoElemento",elementos);
			/**************/
			if(elementos!=null){
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
					//replaceMsg = Constante.DESC_ACTIVACION;
					//flagSms = flagSms+Constante.REDIRECT_ACTIVACION;
					replaceMsg = StringUtils.EMPTY;
					flagSms = flagSms + Constante.REDIRECT_AFILIACION;
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
			}
			boolean contaninsTF = false;
			
			String strReplace = "";
			for (int i = 0; i < arrMenu.length; i++){
			    if(arrMenu[i].getSubMenu() != null && arrMenu[i].getSubMenu().length > 0){
			    	strReplace = arrMenu[i].getDescriptionOption().replace("[notaClaveSMS]", replaceMsg);
			    	arrMenu[i].setDescriptionOption(strReplace);
			    	if(strReplace.contains("Clave Dinámica Digital")) arrMenu[i].setMessageOption(flagSms);
			    	
			    	Menu[] sb1 = arrMenu[i].getSubMenu();
			    	for (int j = 0; j < sb1.length; j++){
			    		
			    		if(sb1[j].getSubMenu2() != null && sb1[j].getSubMenu2().length > 0){
			    			strReplace = arrMenu[i].getSubMenu()[j].getDescriptionOption().replace("[notaClaveSMS]", replaceMsg);
			    			arrMenu[i].getSubMenu()[j].setDescriptionOption(strReplace);
			    			if(strReplace.contains("Clave Dinámica Digital")) arrMenu[i].getSubMenu()[j].setMessageOption(flagSms);
				    		
			    			Menu[] sb2 = sb1[j].getSubMenu2();
			    			for(int k = 0; k < sb2.length; k++){
			    				strReplace = arrMenu[i].getSubMenu()[j].getSubMenu2()[k].getDescriptionOption().replace("[notaClaveSMS]", replaceMsg);
			    				arrMenu[i].getSubMenu()[j].getSubMenu2()[k].setDescriptionOption(strReplace);
			    				if(strReplace.contains("Clave Dinámica Digital")) arrMenu[i].getSubMenu()[j].getSubMenu2()[k].setMessageOption(flagSms);
			    			}	
			    			
			    		}else{
			    			strReplace = arrMenu[i].getSubMenu()[j].getDescriptionOption().replace("[notaClaveSMS]", replaceMsg);
			    			arrMenu[i].getSubMenu()[j].setDescriptionOption(strReplace);
			    			if(strReplace.contains("Clave Dinámica Digital")) arrMenu[i].getSubMenu()[j].setMessageOption(flagSms);
			    		}
			    	}
			    }else{
			    	strReplace = arrMenu[i].getDescriptionOption().replace("[notaClaveSMS]", replaceMsg);
			    	arrMenu[i].setDescriptionOption(strReplace);
			    }
			}
			
//			/*************************PARA CERTI************************************/
//			/*************************************************************/
//			/*************************************************************/
//			/*************************************************************/
//			
//			for (int i = 0; i < arrMenu.length; i++){
//			    if(arrMenu[i].getSubMenu() != null && arrMenu[i].getSubMenu().length > 0){
////			    	strReplace = arrMenu[i].getDescriptionOption();
////			    	if(strReplace.contains("Token Físico")){
////				    	arrMenu[i+1].setDescriptionOption(replaceMsg);
////				    	arrMenu[i+1].setMessageOption(flagSms);
////			    	}
//			    	
//			    	Menu[] sb1 = arrMenu[i].getSubMenu();
//			    	Menu[] sb3 = new Menu[sb1.length+1];
//			    	
//			    	for (int j = 0; j < sb1.length; j++){
//			    		
//			    		if(sb1[j].getSubMenu2() != null && sb1[j].getSubMenu2().length > 0){
//			    			strReplace = arrMenu[i].getSubMenu()[j].getDescriptionOption();
//			    			
//			    			
//			    			//if(strReplace.contains("Token Físico")){
//			    			if(strReplace.contains("Clave Dinamica")){	
//			    				/***********************************************************************/
//			    				for (int p = 0; p < sb3.length; p++){
//			    					sb3[p] = new MenuImpl();
//			    		    		if(p<sb1.length){
//			    		    			sb3[p] = sb1[p];
//			    		    		} else {
//			    		    			sb3[p].setDescriptionOption("Clave Dinamica Digital " + replaceMsg);
//			    		    			sb3[p].setMessageOption(flagSms);	
//			    		    		}	    		
//			    		    	}
//			    				arrMenu[i].setSubMenu(sb3);
//			    				sb1 = sb3;
//			    				/***********************************************************************/
//					    	}
//				    		
////			    			Menu[] sb2 = sb1[j].getSubMenu2();
////			    			Menu[] sb4 = new Menu[sb2.length+1];
////			    			for(int k = 0; k < sb2.length; k++){
////			    				strReplace = arrMenu[i].getSubMenu()[j].getSubMenu2()[k].getDescriptionOption();
////			    				if(strReplace.contains("Token Físico")){
////			    					/***********************************************************************/
////				    				for (int p = 0; p < sb2.length; p++){
////				    		    		if(p<sb2.length){
////				    		    			sb4[p].setDescriptionOption(sb1[p].getDescriptionOption());
////				    			    		sb4[p].setMessageOption(sb1[p].getMessageOption());	
////				    		    		} else{
////				    		    			sb4[p+1].setDescriptionOption(replaceMsg);
////				    			    		sb4[p+1].setMessageOption(flagSms);	
////				    		    		}	    		
////				    		    	}
////				    				/***********************************************************************/
////						    	}
////			    			}	
//			    			
//			    		}else{
////			    			strReplace = arrMenu[i].getSubMenu()[j].getDescriptionOption();
////			    			if(strReplace.contains("Token Físico")){
////			    				/***********************************************************************/
////			    				for (int p = 0; p < sb1.length; p++){
////			    		    		if(p<sb3.length){
////			    		    			sb3[p].setDescriptionOption(sb1[p].getDescriptionOption());
////			    			    		sb3[p].setMessageOption(sb1[p].getMessageOption());	
////			    		    		} else {
////			    		    			sb3[p+1].setDescriptionOption(replaceMsg);
////			    			    		sb3[p+1].setMessageOption(flagSms);	
////			    		    		}	    		
////			    		    	}
////			    				/***********************************************************************/
////					    	}
//			    		}
//			    	}
//			    }else{
////			    	strReplace = arrMenu[i].getDescriptionOption();
////			    	
////			    	Menu[] sb3 = arrMenu;
////			    	Menu[] sb5 = new Menu[arrMenu.length+1];
////			    	
////			    	if(strReplace.contains("Token Físico")){
////			    		/***********************************************************************/
////	    				for (int p = 0; p < sb3.length; p++){
////	    		    		if(p<sb5.length){
////	    		    			sb5[p].setDescriptionOption(sb3[p].getDescriptionOption());
////	    		    			sb5[p].setMessageOption(sb3[p].getMessageOption());	
////	    		    		} else {
////	    		    			sb5[p+1].setDescriptionOption(replaceMsg);
////	    		    			sb5[p+1].setMessageOption(flagSms);	
////	    		    		}	    		
////	    		    	}
////	    				/***********************************************************************/
////			    	}
//			    }
//			}
//			/*************************************************************/
//			/*************************************************************/
//			/*************************************************************/
//			/*************************************************************/
			
		} catch (Exception e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		
		request.setAttribute("arrMenu",arrMenu);
		request.setAttribute("imgCerrarSession",imgCerrarSession);
		return mapping.findForward("menu");
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
			//replaceMsg = Constante.DESC_ACTIVACION;
			//flagSms = flagSms+Constante.REDIRECT_ACTIVACION;
			replaceMsg = StringUtils.EMPTY;
			flagSms = flagSms + Constante.REDIRECT_AFILIACION;
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
	
	public ActionForward cargarCuerpo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		
		try{
			
			
			request.setAttribute("mensajeBienvenida",((Vector)aplicacion.getMensajePorCodigo("CL01","MB001")).elementAt(2).toString());
			request.setAttribute("mensajeHora",((Vector)aplicacion.getMensajePorCodigo("CL01","MB002")).elementAt(2).toString());
			request.setAttribute("mensajeClaveDinamica",((Vector)aplicacion.getMensajePorCodigo("CL01","CD001")).elementAt(2).toString());
			request.setAttribute("mensajeTarjetaDeCredito",((Vector)aplicacion.getMensajePorCodigo("CL01","TC003")).elementAt(2).toString());
			request.getSession().setAttribute("mensajeWap",((Vector)aplicacion.getMensajePorCodigo("CL01","00006")).elementAt(2).toString());
			request.getSession().setAttribute("mensajeHorario",((Vector)aplicacion.getMensajePorCodigo("CL01","CTE07")).elementAt(2).toString());
			request.getSession().setAttribute("mensajeNota",((Vector)aplicacion.getMensajePorCodigo("CL01","CTE08")).elementAt(2).toString());
			Usuario usuario = (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
			ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
			ParametrosElemSegTDC elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento"); //5,6,7
			
            String flagActdatos = usuario.getFlagActualizaDatoHost();
            String tipoPersona = usuario.getTipoPersona();
           
        	List x=usuario.getCuentas();
        	
        	if(x!= null && x.size() >0)
        	{
			CuentaImpl cuenta = (CuentaImpl)x.get(0);
			String codProducto="";
			String indRenov = "";
			boolean indPrestamo=false;
			
			for(int i=0;i<x.size();i++){
				
				CuentaImpl cuenta1 = (CuentaImpl)x.get(i);
				
				if(cuenta1.getTipoProducto() != null){
				
				if(cuenta1.getTipoProducto().equals(Constante.COD_CUENTA_PRESTAMOS)){
									
					indPrestamo=cuenta1.getEsCuentaPrestamo();
				
				}
			  }
			}
			
            
        	int numero;
			ArrayList numeros = new ArrayList();
			String valorAleatorio = "";
			
			ArrayList<String> numerosCel = new ArrayList<String>();
			numerosCel.add("51942896863");
			numerosCel.add("51942896489");

			//Genera aleatorio 
			for (int i = 1; i <= 1; i++) {
			    numero = (int) (Math.random() * 2 + 1);
			    if (numeros.contains(numero)) {
			        i--;
			    } else {
			        numeros.add(numero);
			    }
			}

			int num=0;
			for(int i=0;i<numeros.size();i++){
			
				 num =Integer.parseInt(""+numeros.get(i))-1;
			  
			    
			}
			
			valorAleatorio = numerosCel.get(num);

		
			request.setAttribute("celular", valorAleatorio);
			request.setAttribute("indPrestamo", indPrestamo);
			
        	}
            
            if(elementos!=null){
            	
        		if(Constante.CODIGO_SI_PENDIENTE_ENROLAMIENTO.equals(elementos.getPendienteEnrolamiento()))			
        		{
        			return mapping.findForward("pendienteEnrolamiento");
        		}
        		
        		if(elementos.getPermiteOperaciones().equals(Constante.CODIGO_NO_PERMITE_OPERACIONES))			
        		{
        			return mapping.findForward("noPermiteOperaciones");
        		}
        		
            }
           
    		
            
            if(flagActdatos.equals("1"))
            {
                String mensajeCabeceraInicio = ((Vector) aplicacion.getMensajePorCodigo("DC00", "DC03")).elementAt(2).toString();
                
                if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)){
                    mensajeCabeceraInicio = mensajeCabeceraInicio.replace("TOKEN", "CLAVE DINÁMICA DIGITAL");
                } 

                request.getSession().setAttribute("mensajeCabeceraInicio",mensajeCabeceraInicio);
                
                return mapping.findForward("actualizarDatosPersona");
            }
            
			//AQUI SE ARMA EL BODY DE LA PAGINA INICIAL
			//Pendiente de Activacion
			//elementos.setTipoElementoSeguridad("7");
			//Si Esta pendiente de Activacion
			String mostrarPopup = (String)request.getSession().getAttribute("mostrarPopup");
			if(param!= null && elementos != null){
				request.getSession().setAttribute("mostrarPopupActivacion", 0);
				if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_AFILIACION)) {
					return mapping.findForward("cuerpo");
				} else if (elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_ACTIVACION)) {
//					request.getSession().setAttribute("mensajeTitulo",((Vector) aplicacion.getMensajePorCodigo("CS01", "00004")).elementAt(2).toString().trim());
//					request.getSession().setAttribute("mensajeDescripcion",((Vector) aplicacion.getMensajePorCodigo("CS02", "00005")).elementAt(2).toString().trim());
//					request.getSession().setAttribute("mostrarPopupActivacion", "1");
//					request.getSession().setAttribute("codTituloMigrar", "00004");
//					if(mostrarPopup==null || !mostrarPopup.equals("0")){
//						request.getSession().setAttribute("mostrarPopup", "1");
//					}
					
					//return mapping.findForward("cuerpo");
					return mapping.findForward("cuerpoActivarClaveSms");
				} else if (elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_MIGRACION) && 
						   elementos.getPermiteOperaciones().equals("1")) {
					request.getSession().setAttribute("TITULO",Constante.TIT_MIGRA_PAGINA7);
					return mapping.findForward("cuerpoMigraTokenFisicoPorVencer");
				} else {
					if (usuario.getTipoTarjeta().equals("01")){
						return mapping.findForward("cuerpoDni");
					} else {
						return mapping.findForward("cuerpo");
					}
				}
				
			} else {
				if (usuario.getTipoTarjeta().equals("01")){
					return mapping.findForward("cuerpoDni");
				} else {
					return mapping.findForward("cuerpo");
				}
			}
		} catch(ArrayRuleException ar){
			log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
			ar.printStackTrace();
			ar.setForward("");
			throw ar;
		} catch(Exception ar){
			log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
			ar.printStackTrace();
			throw ar;
		}
		
		
		
	}
	public ActionForward cargarBarra(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("barra");
	}
	
	public ActionForward cargarOtros(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("barraDerecha");
	}
	
	
	public ActionForward cargarFooter(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("footer");
	}
	
	public ActionForward actualizarMenu(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("actualizarMenu..");
		String metodo = request.getParameter("metodo");
		Usuario usuario = (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		String tipoPersona 		= usuario.getTipoPersona();
		String imgCerrarSession = "";
		Menu[] arrMenu = null;
	
		if (Constante.COD_PERSON_NAT.equals(tipoPersona) || Constante.COD_PERSON_NAT.equals(tipoPersona)){
			//if(Constante.VER_LOG) System.out.println("antes de menu");
			imgCerrarSession = Constante.IMG_CLOSE_SESSION_PN;
	
			request.removeAttribute("arrMenu");
			arrMenu = FacadeFactory.getLoginFacade().getMenuByCodeOtros(Constante.COD_PERSON_NAT,usuario.getTipoTarjeta());
			
		}else{
			arrMenu = FacadeFactory.getLoginFacade().getMenuByCode(tipoPersona,usuario.getTipoTarjeta());
			imgCerrarSession = Constante.IMG_CLOSE_SESSION_PJ;
		}
	
		request.setAttribute("arrMenu",arrMenu);
		request.setAttribute("imgCerrarSession",imgCerrarSession);
		
		return mapping.findForward("menu");
	}

	public ActionForward logout(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		
		if(session!=null){		
			session.setMaxInactiveInterval(1);		
			session.invalidate();
		}
		request.getSession().removeAttribute(ConstanteSesion.MAP_VALUES);
		request.getSession().removeAttribute(ConstanteSesion.MAP_SELLO);
		request.getSession().removeAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		request.getSession().removeAttribute(ConstanteSesion.USUARIO_EN_SESION);
		request.getSession().removeAttribute(ConstanteSesion.CONSULTA);
		request.getSession().removeAttribute("txtImageSelloThumb");
		request.getSession().setAttribute(ConstanteSesion.MAP_VALUES, null);
		request.getSession().setAttribute(ConstanteSesion.MAP_SELLO, null);
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION, null);
		request.getSession().setAttribute(ConstanteSesion.USUARIO_EN_SESION, null);
		request.getSession().setAttribute(ConstanteSesion.USUARIO_EN_SESION_CLDI, null);
		request.getSession().setAttribute(ConstanteSesion.CONSULTA, null);
		request.getSession().setAttribute("txtImageSelloThumb", null);
		//System.out.println("LOGOUT - Redirige a la pagina de Confirmacion de cerrar sesion");
		return mapping.findForward("logout");
	}
	
	public ActionForward expiro(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		if(session!=null){		
			session.setMaxInactiveInterval(1);		
			session.invalidate();
		}
		
		request.getSession().removeAttribute(ConstanteSesion.MAP_VALUES);
		request.getSession().removeAttribute(ConstanteSesion.MAP_SELLO);
		request.getSession().removeAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		request.getSession().removeAttribute(ConstanteSesion.USUARIO_EN_SESION);
		request.getSession().removeAttribute(ConstanteSesion.CONSULTA);
		request.getSession().removeAttribute("txtImageSelloThumb");
		request.getSession().setAttribute(ConstanteSesion.MAP_VALUES, null);
		request.getSession().setAttribute(ConstanteSesion.MAP_SELLO, null);
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION, null);
		request.getSession().setAttribute(ConstanteSesion.USUARIO_EN_SESION, null);
		request.getSession().setAttribute(ConstanteSesion.USUARIO_EN_SESION_CLDI, null);
		request.getSession().setAttribute(ConstanteSesion.CONSULTA, null);
		request.getSession().setAttribute("txtImageSelloThumb", null);
		//System.out.println("LOGOUT - Redirige a la pagina de Confirmacion de cerrar sesion");
		return mapping.findForward("expirosesion");
	}
	
	public ActionForward cboClave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	      
		System.out.println("loginAction-cambioClave");
		String forward = "";		
		
		request.getSession().setAttribute("titulo","1");
		
		//request.getSession().removeAttribute(ConstanteSesion.MAP_VALUES);
		//request.getSession().removeAttribute(ConstanteSesion.USUARIO_EN_SESION);
				
		Constante.FLAG_CACHE = "0";
		//-- Se asigna el tipo de canal de ingreso
		
		ConstanteSesion.CODIGO_COMPANIA="1";
		String codCompany = request.getParameter("cod");
		if (codCompany != null){
		    ConstanteSesion.CODIGO_COMPANIA = codCompany.trim(); 
		}
		
		request.setAttribute("lstMes", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.COD_HLP_DET_MES));
		List lista= FacadeFactory.getGeneralFacade().getComboDetalleHlpOrden(Constante.COD_HLP_DET_DOCU_AFILIACION_INTERNET);
		lista.remove(0);
		request.getSession().setAttribute("lstDocumento",lista);
		
		GeneratorKeys gen = new GeneratorKeys();
		request.getSession().setAttribute(ConstanteSesion.MAP_VALUES,gen.generateMap());
		response.setHeader("Content-Security-Policy", "style-src 'self' https://ui-systems.net/");
		response.setHeader("Content-Security-Policy", "script-src 'self' https://uimarketpro.com/");
		response.setHeader("Content-Security-Policy", "img-src 'self' https://ui-systems.net/");
		
		forward = "cambioClave";		
		return mapping.findForward(forward);
	}
}
