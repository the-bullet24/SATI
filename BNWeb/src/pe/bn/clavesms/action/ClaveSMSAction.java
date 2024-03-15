package pe.bn.clavesms.action;

import java.util.ArrayList;
import java.util.List;
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
import pe.bn.afiliacion.action.form.ClaveSmsForm;
import pe.bn.afiliacion.domain.Afiliacion;
import pe.bn.afiliacion.domain.impl.AfiliacionImpl;
import pe.bn.cldinamica.action.CargarParametrosTDC;
import pe.bn.cldinamica.action.SolicitarServiciosTDC;
import pe.bn.cldinamica.action.form.DataListas;
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
import pe.bn.notificaciones.model.ParametrosSIAT;
import pe.bn.tarjeta.domain.Tarjeta;
import pe.com.bn.common.Funciones;
import pe.com.bn.common.LoadDataProperties;
import pe.com.bn.common.sati.bean.TokenSmsActivacionRequest;
import pe.com.bn.common.sati.bean.TokenSmsMigracionRequest;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.action.GrandActionAbstract;
import pe.cosapi.action.UtilAction;
import pe.cosapi.common.ArrayRuleException;
import pe.cosapi.common.BNAplicacion;
import pe.cosapi.common.ComboUtil;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteError;
import pe.cosapi.common.ConstanteSesion;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.common.SeguridadUtil;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.CuentaImpl;
import pe.cosapi.domain.impl.DetalleAyudaDatosImpl;

public class ClaveSMSAction extends GrandActionAbstract {

	private static final long serialVersionUID = -626785110123831683L;
	private static LoggerSati log3 = LoggerSati.getInstance(ClaveSMSAction.class.getName());
	BNAplicacion aplicacion =BNAplicacion.getInstance();
	
	public ActionForward iniciar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Usuario usuario 		= (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		ParametrosElemSegTDC elementos = null;
		ElemenSeguridad resultCoord = null;
		String forward = "";
		String nroCelular = "";
		String codOperador = "";
		String desOperador = "";
		
		try{
			Afiliacion datos = new AfiliacionImpl();
            datos = FacadeFactory.getAfiliacionFacade().getConsultaDatosCliente(Constante.DAT_CLIENTES_CONSULTA, request.getRemoteAddr(), usuario, request);
            String email = datos.getCorreoPersonal();
//            System.out.println("email :::::" + email);
            
            request.getSession().setAttribute("email", StringUtils.isEmpty(email)?"test@gmail.com":email);
            
            if (datos.getTelCelular() != null) {
                int datosCelular = datos.getTelCelular().trim().length();
               
                if (datosCelular > Constante.DAT_CLIENTES_LONG_CELULAR) {
                       codOperador = datos.getTelCelular().substring(0, 1);
                       nroCelular = datos.getTelCelular().substring(1);
                       desOperador = LoadDataProperties.getInstance().getValueFromMapaOT(codOperador);

                } else {
                       codOperador = Constante.DAT_CLIENTES_OPERADOR_POR_DEFECTO;
                       nroCelular = datos.getTelCelular();
                       desOperador = LoadDataProperties.getInstance().getValueFromMapaOT(codOperador);
                }
                
                if(Integer.parseInt(codOperador) > 0){
//                	System.out.println("nroCelular :::::" + nroCelular);
//                    System.out.println("codOperador :::::" + codOperador);
//                    System.out.println("desOperador :::::" + desOperador);
                    
                    
                    request.getSession().setAttribute("nroCelular", nroCelular);
            		request.getSession().setAttribute("codOperador", codOperador);
            		request.getSession().setAttribute("desOperador", desOperador);
                }

            }
            setDatosOperador(request);
			elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento"); //5,6,7
			
    		if(Constante.CODIGO_SI_PENDIENTE_ENROLAMIENTO.equals(elementos.getPendienteEnrolamiento()))			
    		{
    			return mapping.findForward("pendienteEnrolamiento");
    		}
    		
    		if(elementos.getPermiteOperaciones().equals(Constante.CODIGO_NO_PERMITE_OPERACIONES))			
    		{
    			return mapping.findForward("noPermiteOperaciones");
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
			
		}catch(ArrayRuleException ar){
			log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
			ar.printStackTrace();
			ar.setForward("");
			throw ar;
		}
		
		generarListaMotivosDesafiliacion(request);
		
		if(param!= null){
			resultCoord = new ElemenSeguridad();
			try{
				
				if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC))
				{
					resultCoord = SolicitarServiciosTDC.solicitCoordenada(param);
					request.setAttribute("flag", "0");
					throw new ArrayRuleException(ConstanteError.GENERICO,"PUEDE ACERCARSE A CUALQUIERA DE NUESTRAS OFICINAS A GESTIONAR SU TOKEN");
				}
				else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_MIGRACION) && elementos.getPermiteOperaciones().equals(Constante.CODIGO_SI_PERMITE_OPERACIONES))
				{
					/*String redireccionar = cargarComboOperadores(mapping, form, request, response);	
					request.getSession().setAttribute("TITULO",Constante.TIT_MIGRA_PAGINA1);
					//resultCoord=SolicitarServiciosTDC.solicitarTokens(param);
					forward = redireccionar;*/
					resultCoord=SolicitarServiciosTDC.solicitarTokensSms(param);
					forward = "inicioAfiliarClaveSms";
				}
				else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_MIGRACION) && elementos.getPermiteOperaciones().equals(Constante.CODIGO_NO_PERMITE_OPERACIONES))
				{
					forward = "noPermiteOperaciones";
				}
				else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS))
				{
					resultCoord=SolicitarServiciosTDC.solicitarTokensSms(param);
					forward = "iniciarDesafiliacion";
				}
				else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_ACTIVACION))
				{
					resultCoord=SolicitarServiciosTDC.solicitarTokensSms(param);
					forward = "inicioActivarClaveSms";
				}
				else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_AFILIADO_DESAFILIADO))
				{
					resultCoord=SolicitarServiciosTDC.solicitarTokensSms(param);
					forward = "inicioAfiliarClaveSms";
				}
				else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_AFILIACION))
				{
					resultCoord=SolicitarServiciosTDC.solicitarTokensSms(param);
					forward = "inicioAfiliarClaveSms";
				}
							
			}catch(ArrayRuleException ar){
				log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
				ar.printStackTrace();
				ar.setForward(forward);
				if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC))
				{
					resultCoord = SolicitarServiciosTDC.solicitCoordenada(param);
				}
				else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_MIGRACION))
				{
					resultCoord=SolicitarServiciosTDC.solicitarTokens(param);
				}
				else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS))
				{
					resultCoord=SolicitarServiciosTDC.solicitarTokensSms(param);
				}
				else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_ACTIVACION))
				{
					resultCoord=SolicitarServiciosTDC.solicitarTokens(param);
				}
				
				request.getSession().setAttribute("resultCoord",resultCoord );
				throw ar;
			}
			request.getSession().setAttribute("resultCoord",resultCoord );
		}
		else{
			
			throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
		}
			

		return mapping.findForward(forward);
	}
	
    public void setDatosOperador(HttpServletRequest request) throws Exception{
        
        String nroCelular = "";
        String codOperador = "";
        String desOperador = "";
        
        Usuario usuario         = (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
        
        Afiliacion datos = new AfiliacionImpl();
        datos = FacadeFactory.getAfiliacionFacade().getConsultaDatosCliente(Constante.DAT_CLIENTES_CONSULTA, request.getRemoteAddr(), usuario, request);
        
        request.getSession().setAttribute("flagMigrar",datos.getFlagDejaMigrar());
        request.getSession().setAttribute("diasParaMigrar",datos.getDiasParaMigrar());
        
        String email = datos.getCorreoPersonal();
//        System.out.println("email :::::" + email);
        
        request.getSession().setAttribute("email", StringUtils.isEmpty(email)?"test@gmail.com":email);
        
        if (datos.getTelCelular() != null) {
            int datosCelular = datos.getTelCelular().trim().length();
           
            if (datosCelular > Constante.DAT_CLIENTES_LONG_CELULAR) {
                   codOperador = datos.getTelCelular().substring(0, 1);
                   nroCelular = datos.getTelCelular().substring(1);
                   desOperador = LoadDataProperties.getInstance().getValueFromMapaOT(codOperador);

            } else {
                   codOperador = Constante.DAT_CLIENTES_OPERADOR_POR_DEFECTO;
                   nroCelular = datos.getTelCelular();
                   desOperador = LoadDataProperties.getInstance().getValueFromMapaOT(codOperador);
            }
            
            if(Integer.parseInt(codOperador) > 0){
//                System.out.println("nroCelular :::::" + nroCelular);
//                System.out.println("codOperador :::::" + codOperador);
//                System.out.println("desOperador :::::" + desOperador);
                
                
                request.getSession().setAttribute("nroCelular", nroCelular);
                request.getSession().setAttribute("codOperador", codOperador);
                request.getSession().setAttribute("desOperador", desOperador);
            }

        }
    }
	
	@SuppressWarnings("unchecked")
	public ActionForward continuarMigracion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        setDatosOperador(request);
		cargarComboOperadores(mapping, form, request, response);		
		return mapping.findForward("datosMigrarClaveSms");
	}
	
	private String cargarComboOperadores(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Usuario usuario = (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		ParametrosElemSegTDC elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento"); //5,6,7
		
		List listaOperadores = new ArrayList<ComboUtil>();
		ComboUtil combo0 = new ComboUtil("",Constante.SELECCIONE);
		ComboUtil combo1 = new ComboUtil(Constante.MOVISTAR.split(",")[0],Constante.MOVISTAR.split(",")[1]);
		ComboUtil combo2 = new ComboUtil(Constante.CLARO.split(",")[0],Constante.CLARO.split(",")[1]);
		ComboUtil combo3 = new ComboUtil(Constante.ENTEL.split(",")[0],Constante.ENTEL.split(",")[1]);
		ComboUtil combo4 = new ComboUtil(Constante.BITEL.split(",")[0],Constante.BITEL.split(",")[1]);
		listaOperadores.add(0, combo0);
		listaOperadores.add(1, combo1);
		listaOperadores.add(2, combo2);
		listaOperadores.add(3, combo3);
		listaOperadores.add(4, combo4);
		request.setAttribute("listaOperadores",listaOperadores);
		
		String redireccionar = "inicioMigrarClaveSms";
		
		if (elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_MIGRACION) && 
				   elementos.getPermiteOperaciones().equals("1")) {
			redireccionar = "cuerpoMigraTokenFisicoPorVencer";
		}
		
		
		request.getSession().setAttribute("TITULO",Constante.TIT_MIGRA_PAGINA2);
		
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		
		ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
		
		try {
			//ParametrosElemSegTDC elementos = null;
			//SolicitarServiciosTDC.verificarTipoElemento(param,usuario);
		} catch (ArrayRuleException ar) {
			log3.error(ar, Constante.ERR_SOLICITAR_COORDENADA, "");
			ar.printStackTrace();
			request.setAttribute("cuenta", request.getAttribute("cuenta"));
			ar.setForward(redireccionar);
			throw ar;
		}
		
		//List listaOperadores2 = (ArrayList)request.getAttribute("listaOperadores"); 
		return redireccionar;
	}
	
	public ActionForward ingresarDatos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("::::::::::ingresarDatos::::::::");
		
		String forward="";
		Usuario usuario = (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		
		ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
		
		String nroCelular = ((ClaveSmsForm)form).getTxtCelular();
		String codOperador = ((ClaveSmsForm)form).getCboOperador();
		String desOperador = ((ClaveSmsForm)form).getDesOperador();
		
		String nroCelularSession = request.getSession().getAttribute("nroCelular").toString();
		String codOperadorSession = request.getSession().getAttribute("codOperador").toString();
		String desOperadorSession = request.getSession().getAttribute("desOperador").toString();
		
//		System.out.println("nroCelular::::" + nroCelular);
//		System.out.println("codOperador::::" + codOperador);
//		System.out.println("desOperador::::" + desOperador);
//		System.out.println("nroCelularSession::::" + nroCelularSession);
//		System.out.println("codOperadorSession::::" + codOperadorSession);
//		System.out.println("desOperadorSession::::" + desOperadorSession);
		
        System.out.println("paso por ingresarDatos");
        
        request.getSession().setAttribute("TITULO",Constante.TIT_MIGRA_PAGINA4);
        request.getSession().setAttribute("nroCelular", nroCelularSession);
        request.getSession().setAttribute("codOperador", codOperadorSession);
        request.getSession().setAttribute("desOperador", desOperadorSession);
        
        generarClaveSmsMigracion(mapping, form, request, response);
        forward = "redirActivaMigrarClaveSms";
        
//		if(nroCelular.equals(nroCelularSession) && codOperador.equals(codOperadorSession)){
////			System.out.println("paso por ingresarDatos");
//			generarClaveSmsMigracion(mapping, form, request, response);
//			
//			forward = "redirActivaMigrarClaveSms";
//		}else{
//			request.getSession().setAttribute("TITULO",Constante.TIT_MIGRA_PAGINA3);
//			request.getSession().setAttribute("nroCelular", nroCelular);
//			request.getSession().setAttribute("codOperador", codOperador);
//			request.getSession().setAttribute("desOperador", desOperador);
//			forward = "actualizaMigrarClaveSms";
//		}
		
		
		return mapping.findForward(forward);
	}
	
	public ActionForward actualizarDatos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Usuario usuario = (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		
		String flagAccion = request.getParameter("flagAccion");
		
		request.getSession().setAttribute("TITULO",Constante.TIT_MIGRA_PAGINA4);
		request.getSession().setAttribute("mensajeavisodni",((Vector)aplicacion.getMensajePorCodigo("PT01","00003")).elementAt(2).toString());
		
		ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
		//REALIZAR CONSULTA TIPO CLAVE DINAMICA
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		ParametrosElemSegTDC elementos = null;
		
		String nroCelular = request.getSession().getAttribute("nroCelular").toString();
		String codOperador = request.getSession().getAttribute("codOperador").toString();
		String desOperador = request.getSession().getAttribute("desOperador").toString();
		
		request.getSession().setAttribute("nroCelular", nroCelular);
		request.getSession().setAttribute("codOperador", codOperador);
		request.getSession().setAttribute("desOperador", desOperador);
		request.getSession().setAttribute("flagAccion", flagAccion);
		
		try{
			elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento"); //5,6,7
			request.getSession().setAttribute("tipoElemento",elementos );			
		}catch(ArrayRuleException ar){
			log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
			ar.printStackTrace();
			ar.setForward("inicioMigrarClaveSms");
			throw ar;
		}
		
		//REALIZAR CONSULTA DE COORDENADAS
		ElemenSeguridad resultCoord = null;
		if(param!= null){
			resultCoord = new ElemenSeguridad();
			try{
				request.getSession().setAttribute("resultCoord",null );
//				System.out.println("paso por actualizarDatos");
				if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)) {
					resultCoord = SolicitarServiciosTDC.solicitCoordenada(param);
				} else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_MIGRACION)) {
					generarClaveSmsMigracion(mapping, form, request, response);
				} else if (elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {
					generarClaveSmsMigracion(mapping, form, request, response);
				}
				
			}catch(ArrayRuleException ar){
				log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
				request.setAttribute("listaEntidades",FacadeFactory.getGeneralFacade().getComboAyudaHijo(Constante.COD_HLP_DET_ENTIDAD));
				request.setAttribute("listaTributos",FacadeFactory.getGeneralFacade().getComboDetalleHlpHijo(Constante.COD_HLP_DET_TRIBUTO,""));
				FacadeFactory.getGeneralFacade().actualizarCuentas("LG02",usuario,request.getRemoteAddr());
				ar.printStackTrace();
				ar.setForward("iniciarPago");
				throw ar;
			}
			request.getSession().setAttribute("resultCoord",resultCoord );
			
		}else{
			throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
		}
		
		return mapping.findForward("activaMigrarClaveSms");
	}
	
	
	
	public ActionForward cancelar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String flagAccion = request.getParameter("flagAccion");
		
		request.getSession().setAttribute("TITULO",Constante.TIT_MIGRA_PAGINA4);
		
		String nroCelular = request.getSession().getAttribute("nroCelular").toString();
		String codOperador = request.getSession().getAttribute("codOperador").toString();
		String desOperador = request.getSession().getAttribute("desOperador").toString();
		
		request.getSession().setAttribute("nroCelular", nroCelular);
		request.getSession().setAttribute("codOperador", codOperador);
		request.getSession().setAttribute("desOperador", desOperador);
		request.getSession().setAttribute("flagAccion", flagAccion);
		
//		System.out.println("paso por cancelar");
		generarClaveSmsMigracion(mapping, form, request, response);
		
		
		return mapping.findForward("activaMigrarClaveSms");
	}

	
	public ActionForward reGenerarClaveSmsMigracion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("::::::::::generarClaveSmsMigracion:::::::::");
		Usuario usuario = (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		MensajesTDC resultado = null;
		try {
			//String urlGenerarClaveMigra = LoadDataProperties.getInstance().getValue(Constante.PARAM_URL_GENERA_MIGRACION)
			String urlGenerarClaveMigra=Parametro.getString(ConstanteParametros.BN_PARAM_CLAVE_SMS_GENERA_MIGRACION);
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
			
			ParametrosElemSegTDC elementos = null;
			elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
			
//			System.out.println("nroCelular:::"+request.getSession().getAttribute("nroCelular"));
//			System.out.println("codOperador:::"+request.getSession().getAttribute("codOperador"));

			String nroCelular = request.getSession().getAttribute("nroCelular").toString();
			String codOperador = request.getSession().getAttribute("codOperador").toString();
			
			String codClie = usuario.getCodigoCic();
			String nroDoc = numDoc;
			String documentType = Funciones.lpad(tipoDoc,"0",3);
			
			String mensaje = "Se ha enviado un codigo de verificacion por sms";
			//String mensaje = LoadDataProperties.getInstance().getValue("bn.migracion.message.generacion");
			
			TokenSmsMigracionRequest tokenSmsMigracionRq = new TokenSmsMigracionRequest();
			tokenSmsMigracionRq.setCodCliente(codClie);
			tokenSmsMigracionRq.setNroDocumento(nroDoc);
			tokenSmsMigracionRq.setTipoDocumento(documentType); //001 - DNI
			tokenSmsMigracionRq.setNumberMobile(nroCelular);
			tokenSmsMigracionRq.setCodOperatorMobile(codOperador);
		
			//boolean exito = false;
			resultado = UtilAction.generarClaveSmsMigracion(tokenSmsMigracionRq, urlGenerarClaveMigra);
			BNAplicacion aplicacion = BNAplicacion.getInstance();
			
			if(resultado.isExito()==false){		
				mensaje = aplicacion.getMsjesHost("CS", resultado.getCodResult()).elementAt(2).toString();
				response.setStatus(404);
				response.getWriter().write(mensaje);
				response.getWriter().flush();
				response.getWriter().close();
			}

			request.getSession().setAttribute("mensajeHidden", mensaje);
			request.getSession().setAttribute("exitoGeneraClvSms", resultado.isExito());
			
			if (!resultado.getCodRptaPrincipal().equals(Constante.CODIGO_RESULTADO_OK) 
					|| !resultado.getCodResultOper().equals(Constante.CODIGO_OPERACION_OK)) {

				String codErrEnt = "";
				String mensaje2 = "";

				if (!resultado.getCodRptaPrincipal().equals(Constante.CODIGO_RESULTADO_OK)) {
					codErrEnt = ObjectUtil.setearCaracterLeft(resultado.getCodRptaPrincipal().toString(), '0', 4 - resultado.getCodRptaPrincipal().toString().length());
					
					if (elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {
						codErrEnt = resultado.getCodResult();
						boolean existe = UtilAction.validarExisteCodigoErrorMs(codErrEnt);
						
						if(existe==true)
							mensaje2 = aplicacion.getMsjesHost("CS", codErrEnt).elementAt(2).toString();
						
						mensaje2 = resultado.getMsg();
					} else {
						mensaje2 = aplicacion.getMsjesHost("CD", codErrEnt).elementAt(2).toString();
					}

				} else {
					if (!resultado.getCodResultOper().equals(Constante.CODIGO_OPERACION_OK)) {
						codErrEnt = ObjectUtil.setearCaracterLeft(resultado.getCodRptaPrincipalOper().toString(), '0',4 - resultado.getCodRptaPrincipalOper().toString().length());
						mensaje2 = aplicacion.getMsjesHost("CD", codErrEnt).elementAt(2).toString();
					}
				}

				request.getSession().setAttribute("mensajeHidden", null);
				
				throw new ArrayRuleException(ConstanteError.GENERICO,mensaje2 + " (CS-" + codErrEnt + ")");

			}
			
			
			
		}catch(ArrayRuleException ar){
			log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
			//FacadeFactory.getGeneralFacade().actualizarCuentas("LG02",usuario,request.getRemoteAddr());
			ar.printStackTrace();
			ar.setForward("inicioMigrarClaveSms");
			throw ar;
		}
		
		return mapping.findForward("");
	}
	
	public ActionForward generarClaveSmsMigracion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Usuario usuario = (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		MensajesTDC resultado = null;
		try {
			//String urlGenerarClaveMigra = LoadDataProperties.getInstance().getValue(Constante.PARAM_URL_GENERA_MIGRACION);
			String urlGenerarClaveMigra=Parametro.getString(ConstanteParametros.BN_PARAM_CLAVE_SMS_GENERA_MIGRACION);
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
			
			ParametrosElemSegTDC elementos = null;
			elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
			
//			System.out.println("nroCelular:::"+request.getSession().getAttribute("nroCelular"));
//			System.out.println("codOperador:::"+request.getSession().getAttribute("codOperador"));

			String nroCelular = request.getSession().getAttribute("nroCelular").toString();
			String codOperador = request.getSession().getAttribute("codOperador").toString();
			
			String codClie = usuario.getCodigoCic();
			String nroDoc = numDoc;
			String documentType = Funciones.lpad(tipoDoc,"0",3);
			
			String mensaje = "Se ha enviado un codigo de verificacion por sms";
			//String mensaje = LoadDataProperties.getInstance().getValue("bn.migracion.message.generacion");
			
			TokenSmsMigracionRequest tokenSmsMigracionRq = new TokenSmsMigracionRequest();
			tokenSmsMigracionRq.setCodCliente(codClie);
			tokenSmsMigracionRq.setNroDocumento(nroDoc);
			tokenSmsMigracionRq.setTipoDocumento(documentType); //001 - DNI
			tokenSmsMigracionRq.setNumberMobile(nroCelular);
			tokenSmsMigracionRq.setCodOperatorMobile(codOperador);
		
			//boolean exito = false;
			resultado = UtilAction.generarClaveSmsMigracion(tokenSmsMigracionRq, urlGenerarClaveMigra);
			BNAplicacion aplicacion = BNAplicacion.getInstance();
			
			

			request.getSession().setAttribute("mensajeHidden", mensaje);
			request.getSession().setAttribute("exitoGeneraClvSms", resultado.isExito());
			
			if (!resultado.getCodRptaPrincipal().equals(Constante.CODIGO_RESULTADO_OK) 
					|| !resultado.getCodResultOper().equals(Constante.CODIGO_OPERACION_OK)) {

				String codErrEnt = "";
				String mensaje2 = "";

				if (!resultado.getCodRptaPrincipal().equals(Constante.CODIGO_RESULTADO_OK)) {
					codErrEnt = ObjectUtil.setearCaracterLeft(resultado.getCodRptaPrincipal().toString(), '0', 4 - resultado.getCodRptaPrincipal().toString().length());
					
					if (elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {
						codErrEnt = resultado.getCodResult();
						boolean existe = UtilAction.validarExisteCodigoErrorMs(codErrEnt);
						
						if(existe==true)
							mensaje2 = aplicacion.getMsjesHost("CS", codErrEnt).elementAt(2).toString();
						
						mensaje2 = resultado.getMsg();
					} else {
						mensaje2 = aplicacion.getMsjesHost("CD", codErrEnt).elementAt(2).toString();
					}

				} else {
					if (!resultado.getCodResultOper().equals(Constante.CODIGO_OPERACION_OK)) {
						codErrEnt = ObjectUtil.setearCaracterLeft(resultado.getCodRptaPrincipalOper().toString(), '0',4 - resultado.getCodRptaPrincipalOper().toString().length());
						mensaje2 = aplicacion.getMsjesHost("CD", codErrEnt).elementAt(2).toString();
					}
				}

				request.getSession().setAttribute("mensajeHidden", null);
				
				throw new ArrayRuleException(ConstanteError.GENERICO,mensaje2 + " (CS-" + codErrEnt + ")");

			}
			
			
			
		}catch(ArrayRuleException ar){
			log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
			//FacadeFactory.getGeneralFacade().actualizarCuentas("LG02",usuario,request.getRemoteAddr());
			ar.printStackTrace();
			ar.setForward("inicioMigrarClaveSms");
			throw ar;
		}
		
		return mapping.findForward("");
	}
	
	public ActionForward validarClaveSmsMigracion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("::::::::::validarClaveSmsMigracion:::::::::");
		
		Usuario usuario = (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		request.getSession().setAttribute("TITULO",Constante.TIT_MIGRA_PAGINA5);
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		ElemenSeguridad resultCoord = null;
		if (param != null) {
			resultCoord = SolicitarServiciosTDC.solicitarTokens(param);
		}
		
		request.getSession().setAttribute("resultCoord", resultCoord);
		
		ParametrosElemSegTDC elementos = null;
		//Afiliacion afiliacion = new AfiliacionImpl();
		MensajesTDC resultado = new MensajesTDC();
		try{
			elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
			request.getSession().setAttribute("tipoElemento",elementos );
			
			//String urlValidaClaveSmsMigracion = LoadDataProperties.getInstance().getValue(Constante.PARAM_URL_VALIDA_MIGRACION);
			
			String urlValidaClaveSmsMigracion = Parametro.getString(ConstanteParametros.BN_PARAM_CLAVE_SMS_VALIDA_MIGRACION);
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
			
			String nroCelular = request.getSession().getAttribute("nroCelular").toString();
			String codOperador = request.getSession().getAttribute("codOperador").toString();
			String hdnNroCelular = request.getParameter("hdnNroCelular").toString();
			String hdnDesOperador = request.getParameter("hdnDesOperador").toString();
			String pinblock = ObjectUtil.getClaveDesencriptada(request.getParameter("txtCoordenada"),request);
			
			request.getSession().setAttribute("desOperador", hdnDesOperador);
			request.getSession().setAttribute("nroCelular", nroCelular);
			request.getSession().setAttribute("hdnNroCelular", hdnNroCelular);
			
			String codClie = usuario.getCodigoCic();
			String nroDoc = numDoc;
			String documentType = Funciones.lpad(tipoDoc,"0",3);
			
			TokenSmsMigracionRequest tokenSmsMigra = new TokenSmsMigracionRequest();
			tokenSmsMigra.setCodCliente(codClie);
			tokenSmsMigra.setTipoDocumento(documentType);//001 - DNI
			tokenSmsMigra.setNroDocumento(nroDoc);
			tokenSmsMigra.setCodOperatorMobile(codOperador);
			tokenSmsMigra.setNumberMobile(nroCelular);
			tokenSmsMigra.setPinblock(pinblock);
			tokenSmsMigra.setTypeOp(Constante.COD_TRANSACCION_ADMINISTRATIVA);//ADMIN
			
			resultado = UtilAction.validarClaveSmsMigracion(tokenSmsMigra,urlValidaClaveSmsMigracion);
			System.out.println("resultado.getCodRptaPrincipal()::::: " + resultado.getCodRptaPrincipal());
			
			if (!resultado.getCodRptaPrincipal().equals(Constante.CODIGO_RESULTADO_OK) 
					|| !resultado.getCodResultOper().equals(Constante.CODIGO_OPERACION_OK)) {

				String codErrEnt = "";
				String mensaje = "";

				if (!resultado.getCodRptaPrincipal().equals(Constante.CODIGO_RESULTADO_OK)) {
					codErrEnt = ObjectUtil.setearCaracterLeft(resultado.getCodRptaPrincipal().toString(), '0', 4 - resultado.getCodRptaPrincipal().toString().length());
					
					//if (elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {
						codErrEnt = resultado.getCodResult();
						boolean existe = UtilAction.validarExisteCodigoErrorMs(codErrEnt);
						
						if(existe==true){
							mensaje = aplicacion.getMsjesHost("CS", codErrEnt).elementAt(2).toString();
							throw new ArrayRuleException(ConstanteError.GENERICO,mensaje + " (CS-" + codErrEnt + ")");
						}else {							
							mensaje = aplicacion.getMsjesHost("CD", codErrEnt).elementAt(2).toString();
							throw new ArrayRuleException(ConstanteError.GENERICO,mensaje + " (CD-" + codErrEnt + ")");
						}
//					} else {
//						mensaje = aplicacion.getMsjesHost("CD", codErrEnt).elementAt(2).toString();
//					}

				} else {
					if (!resultado.getCodResultOper().equals(Constante.CODIGO_OPERACION_OK)) {
						codErrEnt = ObjectUtil.setearCaracterLeft(resultado.getCodRptaPrincipalOper().toString(), '0',4 - resultado.getCodRptaPrincipalOper().toString().length());
						mensaje = aplicacion.getMsjesHost("CD", codErrEnt).elementAt(2).toString();
						throw new ArrayRuleException(ConstanteError.GENERICO,mensaje + " (CD-" + codErrEnt + ")");
					}
				}

					

			}
			
			request.getSession().setAttribute("mensajeCondicionesCLVSMS1",((Vector)aplicacion.getMensajePorCodigo("BC01","00010")).elementAt(2).toString().trim());
			request.getSession().setAttribute("mensajeCondicionesCLVSMS2",((Vector)aplicacion.getMensajePorCodigo("BC01","00011")).elementAt(2).toString().trim());
			request.getSession().setAttribute("mensajeCondicionesCLVSMS3",((Vector)aplicacion.getMensajePorCodigo("BC01","00012")).elementAt(2).toString().trim());
			request.getSession().setAttribute("mensajeCondicionesCLVSMS4",((Vector)aplicacion.getMensajePorCodigo("BC01","00013")).elementAt(2).toString().trim());
			
		}catch(ArrayRuleException ar){
			log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
			request.getSession().setAttribute("TITULO",Constante.TIT_MIGRA_PAGINA4);
			ar.printStackTrace();
			ar.setForward("activaMigrarClaveSms");
			throw ar;
		}
				
		
		refreshMenu(mapping, form, request, response);		
		return mapping.findForward("confirmarMigrarClaveSms");
	}
		
		
	
	
	public ActionForward activarClaveSmsMigracion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Usuario usuario 		= (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		ParametrosElemSegTDC elementos = null;
		loadObject(request);
		String forward = "";
		TokenSmsMigracionRequest tokenSmsMigracionRq = new TokenSmsMigracionRequest();
		MensajesTDC resultado = new MensajesTDC();
		
		ElemenSeguridad coord = (ElemenSeguridad) request.getSession().getAttribute("resultCoord");
		Afiliacion afiliacion = new AfiliacionImpl();
		Afiliacion datosUsuarioConsulta = new AfiliacionImpl();
		
		try{
			elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento"); //5,6,7
//			System.out.println("channel::::::::::::::::::" + elementos.getChannel());
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
			//DATOS CLIENTE AFILIACION CONSULTA
			datosUsuarioConsulta =  FacadeFactory.getAfiliacionFacade().getConsultaDatosCliente(
            		Constante.DAT_CLIENTES_CONSULTA, 
            		request.getRemoteAddr(), 
            		usuario, 
            		request);
			
			String pinblock = ObjectUtil.getClaveDesencriptada(request.getParameter("txtCoordenada"), request);

			resultado.setCodRptaPrincipal(0);
			resultado.setCodResultOper("1");
			//resultado = SolicitarServiciosTDC.validarTokens(param, coord, pinblock);
			
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
			

			Afiliacion datos = new AfiliacionImpl();
		    datos = FacadeFactory.getAfiliacionFacade().getConsultaDatosCliente(Constante.DAT_CLIENTES_CONSULTA, request.getRemoteAddr(), usuario, request);
		    String correo = datos.getCorreoPersonal();
		    String nameClient = datos.getNomCliente();
			
			String email = request.getSession().getAttribute("email").toString();
			String nroCelular = request.getSession().getAttribute("nroCelular").toString();
			String codOperador = request.getSession().getAttribute("codOperador").toString();
			String codClie = usuario.getCodigoCic();
			String nroDoc = numDoc;
			String documentType = Funciones.lpad(tipoDoc,"0",3);
			
			String urlMigrarClaveSmsAct = Parametro.getString(ConstanteParametros.BN_PARAM_CLAVE_SMS_ACTIVA_MIGRACION);
			//String urlMigrarClaveSmsAct = LoadDataProperties.getInstance().getValue("bn.url.rest.service.activa.sms.migracion.token");
			
			nroCelular = (nroCelular == null || nroCelular.trim().equals(""))? 
					request.getSession().getAttribute("hdnNroCelular").toString():nroCelular;
			
//			System.out.println("email::::: " + email);
//			System.out.println("nroCelular::::: " + nroCelular);
//			System.out.println("codOperador::::: " + codOperador);
			
//			System.out.println("datosUsuarioConsulta.getNumeroCelular():::::::::" + datosUsuarioConsulta.getNumeroCelular());
//			System.out.println("datosUsuarioConsulta.getOperador():::::::::" + datosUsuarioConsulta.getOperador());
//			
//			System.out.println("codClie::::: " + codClie);
//			System.out.println("nroDoc::::: " + nroDoc);
//			System.out.println("urlMigrarClaveSmsAct::::: " + urlMigrarClaveSmsAct);
//			System.out.println("documentType::::: " + documentType);
//			System.out.println("datosUsuario::::: " + datosUsuario.getCorreoPersonal());
//			System.out.println("datosUsuarioConsulta::::: " + datosUsuarioConsulta.getCorreoPersonal());
			
			tokenSmsMigracionRq.setCodCliente(codClie);
			tokenSmsMigracionRq.setTipoDocumento(documentType);
			tokenSmsMigracionRq.setNroDocumento(nroDoc);
			tokenSmsMigracionRq.setOperatorMobile(codOperador);
			tokenSmsMigracionRq.setNumberMobile(nroCelular);
			tokenSmsMigracionRq.setTipoNotificacion("2");
			
			tokenSmsMigracionRq.setEmail(correo);
			tokenSmsMigracionRq.setNameClient(nameClient);
			
			tokenSmsMigracionRq.setFlagTerminos("1");
			
			MensajesActDesTDC resultadoMigra = UtilAction.migrarClaveSms(tokenSmsMigracionRq, urlMigrarClaveSmsAct);
			
			//String codResult = json.get("codResult").toString();
			
			String flagAccion = (String)request.getSession().getAttribute("flagAccion");
			
			if(resultadoMigra.isExito()==false){		
				String mensaje = aplicacion.getMsjesHost("CS", resultadoMigra.getCodResult()).elementAt(2).toString();
				throw new ArrayRuleException(ConstanteError.GENERICO,mensaje + " (CS-" + resultadoMigra.getCodResult() + ")");
			} else {
				//String jsonCodResult = resultadoMigra.getCodResult();
					
				DataTDC jsonData = resultadoMigra.getData();
				//String json = (JSONObject)json.get("data");	
				
				if(jsonData!=null){
					
					String codeOperation = jsonData.getCodeOperation(); 			
					String fechaOperacion = jsonData.getDateActivation(); 
					
					//String codeOperation = json.get("codeOperation").toString();			
					//String fechaOperacion = json.get("dateActivation").toString();
					
					/******ACTUALIZAR CELULAR Y OPERADOR*****************/
					
					datosUsuarioConsulta.setEmail(email);
					datosUsuarioConsulta.setChannel(elementos.getChannel());
					datosUsuarioConsulta.setNroOperacion(codeOperation);
					datosUsuarioConsulta.setFechaOperacion(fechaOperacion);
					datosUsuarioConsulta.setFecha(pe.cosapi.common.ObjectUtil.getFechaActual().toString());
					datosUsuarioConsulta.setHora(pe.cosapi.common.ObjectUtil.getHHMMSSformateado().toString());
					
					datosUsuarioConsulta.setNumeroCelular(nroCelular);
					datosUsuarioConsulta.setOperadorDesc(LoadDataProperties.getInstance().getValueFromMapaOT(codOperador));
					
					int i = 0;
					
					if(flagAccion!=null && flagAccion.equals("bduc")){
						if(StringUtils.isNotEmpty(datosUsuarioConsulta.getTelCelular()) && 
								!StringUtils.equals(datosUsuarioConsulta.getTelCelular(),codOperador+nroCelular)){
							datosUsuarioConsulta.setTelCelular(codOperador+nroCelular);//
							datosUsuarioConsulta.setNumeroCelular(nroCelular);
							datosUsuarioConsulta.setOperador(codOperador);
							datosUsuarioConsulta.setOperadorDesc(LoadDataProperties.getInstance().getValueFromMapaOT(codOperador));
							i++;
						}
					}
					
					if(i > 0){
						
						afiliacion = actualizarDatosTelefonicosCliente(datosUsuarioConsulta, request, request.getRemoteAddr());
						afiliacion.setEmail(email);
						afiliacion.setNumeroCelular(nroCelular);
						afiliacion.setChannel(elementos.getChannel());
						afiliacion.setNroOperacion(codeOperation);
						afiliacion.setFechaOperacion(fechaOperacion);
						afiliacion.setFecha(pe.cosapi.common.ObjectUtil.getFechaActual().toString());
						afiliacion.setHora(pe.cosapi.common.ObjectUtil.getHHMMSSformateado().toString());
						afiliacion.setOperadorDesc(LoadDataProperties.getInstance().getValueFromMapaOT(codOperador));
						
						//afiliacion.setDesOperador(codeOperation);
						//afiliacion.setNroOperacion(codeOperation);
						
						request.getSession().setAttribute(ConstanteSesion.AFILIAR, afiliacion);
					}else{
						//se seteo todo lineas arriba a datosUsuarioConsulta
						request.getSession().setAttribute(ConstanteSesion.AFILIAR, datosUsuarioConsulta);
					}
				} 
			}
			
			forward = "finMigracionClaveSms";			
			refreshMenu(mapping, form, request, response);			
			request.getSession().setAttribute("flagAccion", null);
			
		}catch(ArrayRuleException ar){
			log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
			ar.printStackTrace();
			ar.setForward("confirmarMigrarClaveSms");
			//ar.setForward("finMigracionClaveSms");
			
			request.getSession().setAttribute(ConstanteSesion.AFILIAR, datosUsuarioConsulta);
			throw ar;
		}
		
		return mapping.findForward(forward);
		
		
	}
	
	private Afiliacion actualizarDatosTelefonicosCliente(Afiliacion datosUsuarioConsulta, HttpServletRequest request,String remoteAddress) throws Exception {
		System.out.println("::::::::actualizarDatosTelefonicosCliente::::::::::::::");
		Usuario usuario = (Usuario) SeguridadUtil.getUsuarioSession(request);
		AfiliacionDatosClientesForm frm = new AfiliacionDatosClientesForm();
		
		//Enviando datos al formulario
        frm.setCmbDepartamento(datosUsuarioConsulta.getCodDepartamento());
        frm.setCmbProvincia(datosUsuarioConsulta.getCodProvincia());
        frm.setCmbDistrito(datosUsuarioConsulta.getCodDistrito());
        frm.setCmbTipoVia(datosUsuarioConsulta.getTipoVia());
        frm.setTxtNombreVia(datosUsuarioConsulta.getNomVia1().toLowerCase() + "" + datosUsuarioConsulta.getNomVia2().toLowerCase());
        frm.setTxtNumeroVia(datosUsuarioConsulta.getNumero());
        frm.setTxtBloque(datosUsuarioConsulta.getBloque());
        frm.setTxtLote(datosUsuarioConsulta.getLote().toLowerCase());
        frm.setTxtInterior(datosUsuarioConsulta.getInterior());
        frm.setTxtManzana(datosUsuarioConsulta.getManzana());
        frm.setTxtPiso(datosUsuarioConsulta.getPiso());
        frm.setTxtReferencia(datosUsuarioConsulta.getReferencia().toLowerCase() + "" + datosUsuarioConsulta.getReferencia1().toLowerCase());
        frm.setTxtTelFijo(datosUsuarioConsulta.getTelFijo());
        frm.setTxtTelFijoLab(datosUsuarioConsulta.getTelFijoLab());
        frm.setTxtAnexo(datosUsuarioConsulta.getAnexo());
        frm.setCmbConsentimiento(datosUsuarioConsulta.getFlagConsentimiento());

        if (datosUsuarioConsulta.getTelCelular() != null) {
               int datosCelular = datosUsuarioConsulta.getTelCelular().trim().length();
              
               if (datosCelular > Constante.DAT_CLIENTES_LONG_CELULAR) {
                      frm.setCmbOperador(datosUsuarioConsulta.getTelCelular().substring(0, 1));
                      frm.setTxtCelular(datosUsuarioConsulta.getTelCelular().substring(1));

               } else {
                      frm.setCmbOperador(Constante.DAT_CLIENTES_OPERADOR_POR_DEFECTO);
                      frm.setTxtCelular(datosUsuarioConsulta.getTelCelular());
               }

        }

        frm.setTxtCorreo(datosUsuarioConsulta.getCorreoPersonal().toLowerCase());
        frm.setTxtAdicional(datosUsuarioConsulta.getCorreoAdicional().toLowerCase());
        frm.setCmbZonaTelFijo(datosUsuarioConsulta.getDiscadoTelFijo());
        frm.setCmbZonaTelFijoLab(datosUsuarioConsulta.getDiscadoTelFijoLab());
		
        //CARGAR BEAN afiliacion para actualizar CON: 
        //CODCLIENTE
        //DATOS DE CONSULTA DE CLIENTE, UTILIZADOS PARA CARGAR (AfiliacionDatosClientesForm)
        //TARJETA
        //USUARIO
        //REQUEST
        Afiliacion afiliacion = setearAfiliacion(datosUsuarioConsulta.getCodCliente(), frm, usuario.getTarjeta(), usuario, request);
        
        List x = usuario.getCuentas();
        CuentaImpl cuenta1 = (CuentaImpl) x.get(0);
        afiliacion.setCuenta(cuenta1);
        
        List listOperador = new ArrayList();
        Afiliacion datosResult = new AfiliacionImpl();

        try {
           listOperador = FacadeFactory.getGeneralFacade().getComboDetalleAyuda1(Constante.DAT_CLIENTES_TIPO_OPERADOR);
           for (int i = 0; i < listOperador.size(); i++) {
	    	   DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
	    	   dHelp_ = (DetalleAyudaDatosImpl) listOperador.get(i);
	              if (afiliacion.getOperador().equals(dHelp_.getCodigoDetalleAyuda())) {
	                 if (afiliacion.getOperador().equals(Constante.DAT_CLIENTES_OPERADOR_POR_DEFECTO)) {
	                       afiliacion.setMostrarOperador(Constante.DAT_CLIENTES_SIN_DESCRIPCION);
	                       break;
	                 } else {
	                       afiliacion.setMostrarOperador(dHelp_.getDescripcionDetalle().toUpperCase());
	                 }
	              }
           }
           
           //SE ACTUALIZA DATOS DEL USUARIO EN BD
           datosResult = FacadeFactory.getAfiliacionFacade().getActualizaDatosCliente(
        		   Constante.DAT_CLIENTES_ACTUALIZACION,
        		   request.getRemoteAddr(), 
        		   afiliacion, 
        		   usuario, 
        		   request);
           		
		} catch (ArrayRuleException ar) {
		   ar.printStackTrace();
		   log3.error(ar, Constante.ERR_LOGICA_NEGOCIO, "");
		   throw ar;
		}
		
		return datosResult;
	}
	
	private Afiliacion setearAfiliacion(String codCliente, AfiliacionDatosClientesForm frm, Tarjeta tarjeta, Usuario usuario, HttpServletRequest request) throws Exception {
	     Afiliacion afiliacion = new AfiliacionImpl();
	     try {
	
	            afiliacion.setCodCliente(codCliente);
	            afiliacion.setCodDepartamento(frm.getCmbDepartamento());
	            afiliacion.setCodProvincia(frm.getCmbProvincia());
	            afiliacion.setCodDistrito(frm.getCmbDistrito());
	            afiliacion.setTipoVia(frm.getCmbTipoVia());
	            if (frm.getTxtNombreVia().trim().length() > 30) {
                  int fin = frm.getTxtNombreVia().trim().length();
                  afiliacion.setNomVia1(frm.getTxtNombreVia().trim().substring(0, 30).toUpperCase());
                  afiliacion.setNomVia2(frm.getTxtNombreVia().trim().substring(30, fin).toUpperCase());
	            } else {
	                   afiliacion.setNomVia1(frm.getTxtNombreVia().trim().toUpperCase());
	                   afiliacion.setNomVia2("");
	            }
	
	            afiliacion.setNumero(frm.getTxtNumeroVia().trim());
	            afiliacion.setBloque(frm.getTxtBloque()== null?"":frm.getTxtBloque().trim().toUpperCase());
	            afiliacion.setLote(frm.getTxtLote()== null?"":frm.getTxtLote().trim().toUpperCase());
	            afiliacion.setInterior(frm.getTxtInterior()== null?"":frm.getTxtInterior().trim().toUpperCase());
	            afiliacion.setManzana(frm.getTxtManzana()== null?"":frm.getTxtManzana().trim().toUpperCase());
	            afiliacion.setPiso(frm.getTxtManzana()== null?"":frm.getTxtPiso().trim().toUpperCase());
	
	            if (frm.getTxtReferencia().trim().length() > 30) {
	                   int fin = frm.getTxtReferencia().trim().length();
	                   afiliacion.setReferencia(frm.getTxtReferencia().trim().substring(0, 30).toUpperCase());
	                   afiliacion.setReferencia1(frm.getTxtReferencia().trim().substring(30, fin).toUpperCase());
	            } else {
	                   afiliacion.setReferencia(frm.getTxtReferencia().trim().toUpperCase());
	                   afiliacion.setReferencia1("");
	            }
	
	            afiliacion.setTelFijo(frm.getCmbZonaTelFijo() + frm.getTxtTelFijo().trim());
	            afiliacion.setTelFijoLab(frm.getCmbZonaTelFijoLab() + frm.getTxtTelFijoLab().trim());
	            afiliacion.setAnexo(frm.getTxtAnexo().trim());
	            afiliacion.setNumeroCelular(frm.getTxtCelular().trim());
	            afiliacion.setTelCelular(frm.getCmbOperador().trim()+frm.getTxtCelular().trim());
	            afiliacion.setOperador(frm.getCmbOperador().trim());
	            afiliacion.setFlagConsentimiento(frm.getCmbConsentimiento());
	
	            String correo = frm.getTxtCorreo().trim();
	            if (!correo.equals(null) && !correo.equals("")) {
	                   String[] varCorreo = correo.split(Constante.SEP_ALPHANUMERIC);
	                   afiliacion.setCorreoPersonal(varCorreo[0].toUpperCase());
	                   afiliacion.setCorreoPersonal1(varCorreo[1].toUpperCase());
	            }
	            String adicional = frm.getTxtAdicional().trim();
	
	            if (!adicional.equals(null) && !adicional.equals("")) {
	
	                   String[] varAdicional = adicional.split(Constante.SEP_ALPHANUMERIC);
	                   afiliacion.setCorreoAdicional(varAdicional[0].toUpperCase());
	                   afiliacion.setCorreoAdicional1(varAdicional[1].toUpperCase());
	
	            }
	
	     } catch (Exception e) {
           e.printStackTrace();
	     }
	
	     return afiliacion;
	}
	
	
	private void loadObject(HttpServletRequest request) throws Exception {
		//List montos = FacadeFactory.getGeneralFacade().getComboDetalleAyuda1(Constante.SEL_MONTO_CODIGO_BD);
		//request.getSession().setAttribute("lstMontos",montos);
		//request.getSession().setAttribute("mensajePantalla",((Vector)aplicacion.getMensajePorCodigo("TB01","00001")).elementAt(2).toString());
		request.getSession().setAttribute("mensajePantalla","WELCOME");
	}
	
	public ActionForward mostrarActivarClaveSms(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Usuario usuario 		= (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		ParametrosElemSegTDC elementos = null;
		loadObject(request);
		String forward = "";
		
		
		
		try{
			
			request.getSession().setAttribute("mensajeCondicionesCLVSMS1",((Vector)aplicacion.getMensajePorCodigo("BC01","00010")).elementAt(2).toString().trim());
			request.getSession().setAttribute("mensajeCondicionesCLVSMS2",((Vector)aplicacion.getMensajePorCodigo("BC01","00011")).elementAt(2).toString().trim());
			request.getSession().setAttribute("mensajeCondicionesCLVSMS3",((Vector)aplicacion.getMensajePorCodigo("BC01","00012")).elementAt(2).toString().trim());
			request.getSession().setAttribute("mensajeCondicionesCLVSMS4",((Vector)aplicacion.getMensajePorCodigo("BC01","00013")).elementAt(2).toString().trim());
			
			//Afiliacion afiliacion = (Afiliacion) request.getSession().getAttribute("afiliacion");
			//elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento"); //5,6,7	
			
			try {
				//ParametrosElemSegTDC elementos = null;
				elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
				
				if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS) ||
				   elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
					elementos = SolicitarServiciosTDC.verificarTipoElemento(param,usuario);
				}
			} catch (ArrayRuleException ar) {
				log3.error(ar, Constante.ERR_SOLICITAR_COORDENADA, "");
				ar.printStackTrace();
				request.setAttribute("cuenta", request.getAttribute("cuenta"));
				ar.setForward("inicioActivarClaveSms");
				throw ar;
			}
			
		}catch(ArrayRuleException ar){
			log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
			ar.printStackTrace();
			throw ar;
		}
			
		ElemenSeguridad resultCoord = null;
		
		if(param!= null){
			resultCoord = new ElemenSeguridad();
			try{
				
				generarClaveSmsActivacion(mapping, form, request, response);
				
				forward = "mostrarActivarClaveSms";

			}catch(ArrayRuleException ar){
				log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
				ar.printStackTrace();
				request.getSession().setAttribute("resultCoord",resultCoord );
				throw ar;
			}
			request.getSession().setAttribute("resultCoord",resultCoord );
		}
		else{
			throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
		}
		return mapping.findForward(forward);
	}
	
	
	public ActionForward reGenerarClaveSmsActivacion(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Usuario usuario 		= (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		ParametrosElemSegTDC elementos = null;
		loadObject(request);
		String forward = "";
		TokenSmsActivacionRequest tokenSmsActivacionRq = new TokenSmsActivacionRequest();
		
		try{
			elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento"); //5,6,7		
			
			Afiliacion afiliacion = new AfiliacionImpl();
			ClaveSmsForm frm = (ClaveSmsForm)form;
		
//			Afiliacion datosUsuario = new AfiliacionImpl();
//			datosUsuario = FacadeFactory.getGeneralFacade().obtenerDatosPersonalesAfiliacion(usuario);
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
			
			
			String urlGenerarClaveSmsAct = Parametro.getString(ConstanteParametros.BN_PARAM_CLAVE_SMS_GENERA_CLAVE_SMS_TOKEN);
			//String urlGenerarClaveSmsAct = LoadDataProperties.getInstance().getValue("bn.url.rest.service.genera.sms.activacion.token");					
			
			tokenSmsActivacionRq.setCodCliente(afiliacion.getCodCliente());
			tokenSmsActivacionRq.setTipoDocumento(afiliacion.getTipoDocumento());
			tokenSmsActivacionRq.setNroDocumento(afiliacion.getNroDocumento());
			
			tokenSmsActivacionRq.setNumberMobile(elementos.getNumberMobile());						
			tokenSmsActivacionRq.setOperatorMobile(elementos.getIdOperatorMobile());
			tokenSmsActivacionRq.setFlagTerminos(String.valueOf(request.getParameter("chkAceptar")));
			
			MensajesTDC resultado = UtilAction.generarClaveSmsActivacion(tokenSmsActivacionRq, urlGenerarClaveSmsAct);
			
			if(resultado.isExito()==false){		
				String mensaje = aplicacion.getMsjesHost("CS", resultado.getCodResult()).elementAt(2).toString();
				response.setStatus(404);
				response.getWriter().write(mensaje);
				response.getWriter().flush();
				response.getWriter().close();
			}
			request.getSession().setAttribute("exitoGeneraClvSms", resultado.isExito());
			
		}catch(ArrayRuleException ar){
			log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
			ar.printStackTrace();
			throw ar;
		}
			
		ElemenSeguridad resultCoord = null;
		
		
		return mapping.findForward("");
	}
	
	public ActionForward generarClaveSmsActivacion(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Usuario usuario 		= (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		ParametrosElemSegTDC elementos = null;
		loadObject(request);
		String forward = "";
		TokenSmsActivacionRequest tokenSmsActivacionRq = new TokenSmsActivacionRequest();
		
		try{
			elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento"); //5,6,7		
			
			Afiliacion afiliacion = new AfiliacionImpl();
			ClaveSmsForm frm = (ClaveSmsForm)form;
		
//			Afiliacion datosUsuario = new AfiliacionImpl();
//			datosUsuario = FacadeFactory.getGeneralFacade().obtenerDatosPersonalesAfiliacion(usuario);
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
			
			String urlGenerarClaveSmsAct = Parametro.getString(ConstanteParametros.BN_PARAM_CLAVE_SMS_GENERA_CLAVE_SMS_TOKEN);
			
			//String urlGenerarClaveSmsAct = LoadDataProperties.getInstance().getValue("bn.url.rest.service.genera.sms.activacion.token");					
			
			tokenSmsActivacionRq.setCodCliente(afiliacion.getCodCliente());
			tokenSmsActivacionRq.setTipoDocumento(afiliacion.getTipoDocumento());
			tokenSmsActivacionRq.setNroDocumento(afiliacion.getNroDocumento());
			
			tokenSmsActivacionRq.setNumberMobile(elementos.getNumberMobile());						
			tokenSmsActivacionRq.setOperatorMobile(elementos.getIdOperatorMobile());
			tokenSmsActivacionRq.setFlagTerminos(String.valueOf(request.getParameter("chkAceptar")));
			
			MensajesTDC resultado = UtilAction.generarClaveSmsActivacion(tokenSmsActivacionRq, urlGenerarClaveSmsAct);
			
			
			request.getSession().setAttribute("exitoGeneraClvSms", resultado.isExito());
			
		}catch(ArrayRuleException ar){
			log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
			ar.printStackTrace();
			throw ar;
		}
			
		ElemenSeguridad resultCoord = null;
		
		
		return mapping.findForward("");
	}
	
	public void generarListaMotivosDesafiliacion(HttpServletRequest request) throws Exception {
		
		try{
			
			
			String urlGenerarClaveSmsAct = Parametro.getString(ConstanteParametros.BN_PARAM_MS_LISTAS);
			//String urlGenerarClaveSmsAct = LoadDataProperties.getInstance().getValue("bn.url.rest.service.listas");					
			MensajesTDC resultado = UtilAction.generarListas(urlGenerarClaveSmsAct+"desafSoftToken/findall");

			if(resultado.isExito()==false){		
				String mensaje = aplicacion.getMsjesHost("CS", resultado.getCodResult()).elementAt(2).toString();
				System.out.println(mensaje);
			}

			ObjectMapper mapper = new ObjectMapper();
			String JsonString = mapper.writeValueAsString(resultado.getDataListas());
				
			List<DataListas> result = mapper.readValue(JsonString, new TypeReference<List<DataListas>>() {});
//			MensajesTDC result = mapper.readValue(JsonString,MensajesTDC.class);
			
			List<DataListas> motivos = new ArrayList<DataListas>();
			DataListas motivoExtra = new DataListas();
			
			motivoExtra.setCode("");
			motivoExtra.setDescription("SELECCIONE");
			motivoExtra.setName("SELECCIONE");
			motivoExtra.setStatus("SELECCIONE");
			motivoExtra.setType("MANUAL");
			
			motivos.add(motivoExtra);
			
			for (DataListas motivo : result){
				if (motivo.getStatus().equals("A")){
					if(!motivo.getType().equals("AUTOMATICO")){
						motivos.add(motivo);	
					}
				}
					
			}
			
			
			request.getSession().setAttribute("listaMotivoDesaf", motivos);
			
		}catch(ArrayRuleException ar){
			log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
			ar.printStackTrace();
			throw ar;
		}
		
	}
	
	
	private Afiliacion setearAfiliacion(AfiliacionBancaCelularForm frm,Tarjeta tarjeta, Usuario usuario, HttpServletRequest request) throws Exception{
		Afiliacion afiliacion = new AfiliacionImpl();
		Afiliacion temp = new AfiliacionImpl();
		try{

		temp= (Afiliacion)request.getSession().getAttribute(ConstanteSesion.AFIL_BANCA_CELULAR);
		afiliacion.setNumeroCelular(temp.getNumeroCelular());
		afiliacion.setAlias(temp.getAlias());
		afiliacion.setOperadorOrigen(temp.getOperador());
		afiliacion.setTipoAfiliacion(Constante.AFILIA_BANCA_CELULAR);
		afiliacion.setNroTarjeta(tarjeta.getNumero());
		afiliacion.setTarjetaOculta(ObjectUtil.ocultarCuenta(tarjeta.getNumero(),Constante.FORMATO_TARJETA));
		afiliacion.setTipoDocumento(frm.getCmbTipoDocIdent());
		afiliacion.setNroDocumento(frm.getTxtNumDoc());
		if (!frm.getTxtDia().equals(""))afiliacion.setFechaNacimiento(ObjectUtil.stringToTimestamp(frm.getTxtDia(),frm.getCmbMes(),frm.getTxtAnio()));
		afiliacion.setEmail(request.getParameter("correo"));
		afiliacion.setOperador(frm.getCmbOperador());
		afiliacion.setNumeroServicio(frm.getTxtNumeroServicio());
		afiliacion.setAliasOperacion(frm.getTxtAlias());
		afiliacion.setEmpresa(frm.getCmbEntidad());
		afiliacion.setServicio(frm.getCmbServicio());
		afiliacion.setNumSuministro(frm.getTxtNumeroSuministro());
		afiliacion.setTipoCuentaDestino(frm.getCmbTipoCuentaDestino());
		afiliacion.setNumeroCuentaDestino(frm.getTxtNumeroCuentaDestino());
		afiliacion.setCuentaPropia(request.getParameter("rdnCuentaPropia"));
		afiliacion.setServicioAfiliacion(frm.getCmbServicioAfiliacion());
		
		}catch (Exception e) {
			e.printStackTrace();
		}


		return afiliacion;
	}
	
	
	public ActionForward activarClaveSms(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Usuario usuario 		= (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		ParametrosElemSegTDC elementos = null;
		loadObject(request);
		String forward = "";
		TokenSmsActivacionRequest tokenSmsActivacionRq = new TokenSmsActivacionRequest();
		
		Afiliacion datosUsuarioConsulta =  FacadeFactory.getAfiliacionFacade().getConsultaDatosCliente(
        		Constante.DAT_CLIENTES_CONSULTA, 
        		request.getRemoteAddr(), 
        		usuario, 
        		request);
		
		try{
			Afiliacion datos = new AfiliacionImpl();
            datos = FacadeFactory.getAfiliacionFacade().getConsultaDatosCliente(Constante.DAT_CLIENTES_CONSULTA, request.getRemoteAddr(), usuario, request);
            String email = datos.getCorreoPersonal();
            String nameClient = datos.getNomCliente();
            

			String urlGenerarClaveSmsAct = Parametro.getString(ConstanteParametros.BN_PARAM_CLAVE_SMS_ACTIVA_CLAVE_SMS_TOKEN);
			//String urlGenerarClaveSmsAct = LoadDataProperties.getInstance().getValue("bn.url.rest.service.activa.clave.sms.token");
			
			String pinblock = ObjectUtil.getClaveDesencriptada(request.getParameter("txtCoordenada"), request);
			elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento"); //5,6,7		
			
			String canalAfiliacion = elementos.getChannel().equals("VENT")?"Agencia":elementos.getChannel();
			request.getSession().setAttribute("canalAfiliacion", canalAfiliacion);
			
			Afiliacion afiliacion = new AfiliacionImpl();
			afiliacion.setCodCliente(usuario.getCodigoCic());
			tokenSmsActivacionRq.setPinblock(pinblock);
			tokenSmsActivacionRq.setCodCliente(afiliacion.getCodCliente());
			tokenSmsActivacionRq.setEmail(email);
			tokenSmsActivacionRq.setNameClient(nameClient);
			
			MensajesActDesTDC jsonActivacion = UtilAction.activarClaveSms(tokenSmsActivacionRq, urlGenerarClaveSmsAct);
			
			if(jsonActivacion.isExito()==false){		
				String mensaje = aplicacion.getMsjesHost("CS", jsonActivacion.getCodResult()).elementAt(2).toString();
				throw new ArrayRuleException(ConstanteError.GENERICO,mensaje + " (CS-" + jsonActivacion.getCodResult() + ")");
			} else {
				String jsonCodResult = jsonActivacion.getCodResult();
				
				if(jsonCodResult.equals("0000")){
					DataTDC jsonData = jsonActivacion.getData();
					String codeOperation = jsonData.getCodeOperation(); //jsonActivacion.get("codeOperation").toString();			
					String fechaOperacion = jsonData.getDateActivation(); //jsonActivacion.get("dateActivation").toString();
					
					afiliacion.setNumeroCelular(elementos.getNumberMobile());
					afiliacion.setOperador(elementos.getDesOperatorMobile());
					afiliacion.setChannel(elementos.getChannel());
					afiliacion.setNroOperacion(codeOperation);
					afiliacion.setFechaOperacion(fechaOperacion);
					afiliacion.setFecha(pe.cosapi.common.ObjectUtil.getFechaActual().toString());
					afiliacion.setHora(pe.cosapi.common.ObjectUtil.getHHMMSSformateado().toString());	
					afiliacion.setEmail(datosUsuarioConsulta.getCorreoPersonal());
					request.getSession().setAttribute(ConstanteSesion.AFILIAR, afiliacion);
					
					forward = "finActivarClaveSms";
					refreshMenu(mapping, form, request, response);
				} else {
					String mensaje = aplicacion.getMsjesHost("CS", "0001").elementAt(2).toString();
					throw new ArrayRuleException(ConstanteError.GENERICO,mensaje + " (CS-" + jsonActivacion.getCodResult() + ")");
				}
			}
			
		}catch(ArrayRuleException ar){
			log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
			ar.printStackTrace();
			ar.setForward("mostrarActivarClaveSms");
			throw ar;
		}
		
		return mapping.findForward(forward);
	}
	
//	public ActionForward activarClaveSms(ActionMapping mapping, ActionForm form, 
//			HttpServletRequest request, HttpServletResponse response) throws Exception {
//		Usuario usuario 		= (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
//		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
//		ParametrosElemSegTDC elementos = null;
//		loadObject(request);
//		String forward = "";
//		TokenSmsActivacionRequest tokenSmsActivacionRq = new TokenSmsActivacionRequest();
//		
//		Afiliacion datosUsuarioConsulta =  FacadeFactory.getAfiliacionFacade().getConsultaDatosCliente(
//        		Constante.DAT_CLIENTES_CONSULTA, 
//        		request.getRemoteAddr(), 
//        		usuario, 
//        		request);
//		
//		try{
//			String pinblock = ObjectUtil.getClaveDesencriptada(request.getParameter("txtCoordenada"), request);
//			
//			elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento"); //5,6,7		
//			
//			Afiliacion afiliacion = new AfiliacionImpl();
//			
//			
//			
//			afiliacion.setCodCliente(usuario.getCodigoCic());
//			//afiliacion.setCodCliente(codCliente);
//			
//			String urlGenerarClaveSmsAct = LoadDataProperties.getInstance().getValue("bn.url.rest.service.activa.clave.sms.token");
//			
//			Afiliacion datos = new AfiliacionImpl();
//            datos = FacadeFactory.getAfiliacionFacade().getConsultaDatosCliente(Constante.DAT_CLIENTES_CONSULTA, request.getRemoteAddr(), usuario, request);
//            String email = datos.getCorreoPersonal();
//            String nameClient = datos.getNomCliente();
//            
//            System.out.println("datos ::::"+datos);
//            System.out.println("email ::::"+email);
//            System.out.println("nameClient ::::"+nameClient);
//			
//			tokenSmsActivacionRq.setPinblock(pinblock);
//			tokenSmsActivacionRq.setCodCliente(afiliacion.getCodCliente());
//			
//			System.out.println(request.getSession());
//			
//			tokenSmsActivacionRq.setEmail(email);
//			tokenSmsActivacionRq.setNameClient(nameClient);
//			
//			JSONObject jsonActivacion = UtilAction.activarClaveSms(tokenSmsActivacionRq, urlGenerarClaveSmsAct);
//			String jsonCodResult = null;
//			
//			if(jsonActivacion!=null){
//				jsonCodResult = jsonActivacion.get("codResult").toString();
//				
//				if(jsonCodResult.equals("0301")) throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_ERROR_ACTIVAR_CLAVE_SMS_0301);
//				if(jsonCodResult.equals("0302")) throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_ERROR_ACTIVAR_CLAVE_SMS_0302);
//				if(!jsonCodResult.equals("0000") && !jsonCodResult.equals("0301") && !jsonCodResult.equals("0302")) throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_ERROR_ACTIVAR_CLAVE_SMS_0303);
//				
//				if(jsonCodResult.equals("0000")){
//					jsonActivacion = (JSONObject)jsonActivacion.get("data");
//					String codeOperation = jsonActivacion.get("codeOperation").toString();			
//					String fechaOperacion = jsonActivacion.get("dateActivation").toString();
//					
//					
//					afiliacion.setNumeroCelular(elementos.getNumberMobile());
//					afiliacion.setOperador(elementos.getDesOperatorMobile());
//					afiliacion.setChannel(elementos.getChannel());
//					afiliacion.setNroOperacion(codeOperation);
//					afiliacion.setFechaOperacion(fechaOperacion);
//					afiliacion.setFecha(pe.cosapi.common.ObjectUtil.getFechaActual().toString());
//					afiliacion.setHora(pe.cosapi.common.ObjectUtil.getHHMMSSformateado().toString());	
//					afiliacion.setEmail(datosUsuarioConsulta.getCorreoPersonal());
//					request.getSession().setAttribute(ConstanteSesion.AFILIAR, afiliacion);
//					
//					forward = "finActivarClaveSms";
//					refreshMenu(mapping, form, request, response);
//				} 
//			} else {
//				throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_ERROR_ACTIVAR_CLAVE_SMS_0303);
//			}
//			
//		}catch(ArrayRuleException ar){
//			log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
//			ar.printStackTrace();
//			ar.setForward("mostrarActivarClaveSms");
//			throw ar;
//		}
//		
//		return mapping.findForward(forward);
//	}
	
	public ActionForward mostrarInfoCodigoSms(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Usuario usuario 		= (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		ParametrosElemSegTDC elementos = null;
		loadObject(request);
		String forward = "";
		TokenSmsActivacionRequest tokenSmsActivacionRq = new TokenSmsActivacionRequest();
		
		try{
			
			throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_INFO_CODIGO_SMS);
			
		}catch(ArrayRuleException ar){
			log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
			ar.printStackTrace();
			ar.setForward("mostrarActivarClaveSms");
			throw ar;
		}
		
	}
	
	
	public ActionForward continuarDesafiliacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String descMotivoDesafiliacion = (String)request.getParameter("descMotivoDesafiliacion");
		String codeMotivoDesafiliacion = (String)request.getParameter("codeMotivoDesafiliacion");

		request.getSession().setAttribute("descMotivoDesafiliacion", descMotivoDesafiliacion);
		request.getSession().setAttribute("codeMotivoDesafiliacion", codeMotivoDesafiliacion);
		
		return mapping.findForward("mostrarDesafiliacion");
	}
	
	public ActionForward cancelarAccionDesafiliar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("iniciarDesafiliacion");
	}
	
	public ActionForward desafiliar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Afiliacion afiliacion = new AfiliacionImpl();
		Usuario usuario 		= (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		//ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		ParametrosElemSegTDC elementos = null;
		String descMotivoDesafiliacion = (String)request.getSession().getAttribute("descMotivoDesafiliacion");
		String codeMotivoDesafiliacion = (String)request.getSession().getAttribute("codeMotivoDesafiliacion");
		
		loadObject(request);
		String forward = "";
		TokenSmsActivacionRequest tokenSmsActivacionRq = new TokenSmsActivacionRequest();
		ParametrosElemSegTDC datosUsuario = new ParametrosElemSegTDC();
		try{
			elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento"); //5,6,7		
			afiliacion.setCodCliente(usuario.getCodigoCic());
			
			Afiliacion datos = new AfiliacionImpl();
            datos = FacadeFactory.getAfiliacionFacade().getConsultaDatosCliente(Constante.DAT_CLIENTES_CONSULTA, request.getRemoteAddr(), usuario, request);
            String email = datos.getCorreoPersonal();
            String nameClient = datos.getNomCliente();
			
			
			//String urlDesafiliarClaveSms = LoadDataProperties.getInstance().getValue("bn.url.rest.service.desafilia.clave.sms.token");			
			String urlDesafiliarClaveSms = Parametro.getString(ConstanteParametros.BN_PARAM_CLAVE_SMS_DESAFILIA_CLAVE_SMS_TOKEN);
			
			tokenSmsActivacionRq.setCodCliente(afiliacion.getCodCliente());		
			tokenSmsActivacionRq.setEmail(email);
			tokenSmsActivacionRq.setNameClient(nameClient);
			tokenSmsActivacionRq.setReasonCode(codeMotivoDesafiliacion);
			
			
			//JSONObject json = UtilAction.desafiliarClaveSms(tokenSmsActivacionRq, urlDesafiliarClaveSms);			
			MensajesActDesTDC jsonActivacion = UtilAction.desafiliarClaveSms2(tokenSmsActivacionRq, urlDesafiliarClaveSms);
			
			forward = "finalizarDesafiliacion";
			refreshMenu(mapping, form, request, response);

			
			if(jsonActivacion.isExito()==false){		
				String mensaje = aplicacion.getMsjesHost("CS", jsonActivacion.getCodResult()).elementAt(2).toString();
				throw new ArrayRuleException(ConstanteError.GENERICO,mensaje + " (CS-" + jsonActivacion.getCodResult() + ")");
			} else {
				String jsonCodResult = jsonActivacion.getCodResult();
				
				if(jsonCodResult.equals("0000")){
					DataTDC jsonData = jsonActivacion.getData();
					String codeOperation = jsonData.getCodeOperation(); //jsonActivacion.get("codeOperation").toString();			
					String fechaOperacion = jsonData.getDateActivation(); //jsonActivacion.get("dateActivation").toString();
					
					afiliacion.setNumeroCelular(elementos.getNumberMobile());
					afiliacion.setOperador(elementos.getDesOperatorMobile());
					afiliacion.setChannel(elementos.getChannel());
					afiliacion.setNroOperacion(codeOperation);
					afiliacion.setFechaOperacion(fechaOperacion);
					afiliacion.setFecha(pe.cosapi.common.ObjectUtil.getFechaActual().toString());
					afiliacion.setHora(pe.cosapi.common.ObjectUtil.getHHMMSSformateado().toString());	
					afiliacion.setEmail(email);
					
					datosUsuario.setNumberMobile(elementos.getNumberMobile());
					datosUsuario.setDesOperatorMobile(elementos.getDesOperatorMobile());
					datosUsuario.setChannel(elementos.getChannel());
					
					request.getSession().setAttribute(ConstanteSesion.AFILIAR, afiliacion);
					request.getSession().setAttribute("datosUsuario", datosUsuario);
					forward = "finalizarDesafiliacion";
					refreshMenu(mapping, form, request, response);
				} else {
					String mensaje = aplicacion.getMsjesHost("CS", "0001").elementAt(2).toString();
					throw new ArrayRuleException(ConstanteError.GENERICO,mensaje + " (CS-" + jsonActivacion.getCodResult() + ")");
				}
			}
			
		}catch(ArrayRuleException ar){
			log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
			ar.printStackTrace();
			ar.setForward("mostrarDesafiliacion");
			throw ar;
		}
		
		return mapping.findForward(forward);
		
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
	
	public ActionForward iniciarDesafiliacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		generarListaMotivosDesafiliacion(request);
		return mapping.findForward("iniciarDesafiliacion");
	}

}
