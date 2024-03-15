/*
 * Creado el 14/03/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.bn.pago.action;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pe.bn.afiliacion.action.DesafCableAction;
import pe.bn.afiliacion.domain.Afiliacion;
import pe.bn.cldinamica.action.SolicitarServiciosTDC;
import pe.bn.cldinamica.action.form.ElemenSeguridad;
import pe.bn.cldinamica.action.form.MensajesTDC;
import pe.bn.cldinamica.action.form.ParametrosElemSegTDC;
import pe.bn.cldinamica.action.form.ParametrosTDC;
import pe.bn.listener.Util;
import pe.bn.notificaciones.action.ServicioNotificacionConstancia;
import pe.bn.notificaciones.action.ServiciosNotificacionPrincipal;
import pe.bn.notificaciones.model.ParametrosSIAT;
import pe.bn.pago.domain.PagoTasas;
import pe.bn.pago.domain.impl.PagoTasasImpl;
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
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.common.SeguridadUtil;
import pe.cosapi.domain.Ciudad;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.Documento;
import pe.cosapi.domain.Entidad;
import pe.cosapi.domain.Tributo;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.CiudadImpl;
import pe.cosapi.domain.impl.CuentaImpl;
import pe.cosapi.domain.impl.DocumentoImpl;
import pe.cosapi.domain.impl.EntidadImpl;
import pe.cosapi.domain.impl.OperacionImpl;
import pe.cosapi.domain.impl.TributoImpl;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class PagoTasasAction extends GrandActionAbstract {
	BNAplicacion aplicacion =BNAplicacion.getInstance();
	private static LoggerSati log3 = LoggerSati.getInstance(PagoTasasAction.class.getName());

	
	public ActionForward iniciar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ParametrosElemSegTDC elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento"); //5,6,7
        
		if(Constante.CODIGO_SI_PENDIENTE_ENROLAMIENTO.equals(elementos.getPendienteEnrolamiento()))			
		{
			return mapping.findForward("pendienteEnrolamiento");
		}
		
		if(elementos.getPermiteOperaciones().equals(Constante.CODIGO_NO_PERMITE_OPERACIONES))			
		{
			return mapping.findForward("noPermiteOperaciones");
		}
		
        Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
        
        String flagActdatos = usuario.getFlagActualizaDatoHost();
        if(flagActdatos.equals("1"))
        {
            return mapping.findForward("actualizarDatosPersona");
        }
		
		request.setAttribute("listaEntidades",FacadeFactory.getGeneralFacade().getComboAyudaHijo(Constante.COD_HLP_DET_ENTIDAD));
		request.setAttribute("listaTributos",FacadeFactory.getGeneralFacade().getComboDetalleHlpHijo(Constante.COD_HLP_DET_TRIBUTO,""));
		FacadeFactory.getGeneralFacade().actualizarCuentas("LG02",usuario,request.getRemoteAddr());
				
		BNAplicacion aplicacion = BNAplicacion.getInstance();
	
		request.getSession().setAttribute("mensajeHorarioTasas",((Vector)aplicacion.getMensajePorCodigo("PT01","00001")).elementAt(2).toString());
		request.getSession().setAttribute("TITULOS","PAGO DE TASAS");
		
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);

		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		
		ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
		
		if(elementos.getTipoElementoSeguridad().equals("5")){
			System.out.println("Token Fisico");
			usuario.getClaveDinamica().setTipoElementoSeguridad(elementos.getTipoElementoSeguridad());
			usuario.getClaveDinamica().setTipoElementoSeguridadFinal("Token Fisico");
			
		}else if(elementos.getTipoElementoSeguridad().equals("6")){
			System.out.println("Clave Dinamica Digital");
			usuario.getClaveDinamica().setTipoElementoSeguridad(elementos.getTipoElementoSeguridad());
			usuario.getClaveDinamica().setTipoElementoSeguridadFinal("Clave Dinamica Digital");
		}
		
		return mapping.findForward("iniciarPago");
	}

	public ActionForward cargarTributo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String cboCuenta  = request.getParameter("cboCuenta");
		String cboEntidad = request.getParameter("cboEntidad");
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		
		
		request.getSession().setAttribute("mensajeavisodni",((Vector)aplicacion.getMensajePorCodigo("PT01","00003")).elementAt(2).toString());
		
		request.getSession().setAttribute("mensajeHorarioTasas",((Vector)aplicacion.getMensajePorCodigo("PT01","00001")).elementAt(2).toString());
		request.setAttribute("listaEntidades",FacadeFactory.getGeneralFacade().getComboAyudaHijo(Constante.COD_HLP_DET_ENTIDAD));
		
		Usuario usuario = (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		CuentaImpl cta	= (CuentaImpl)usuario.getMapCuentas().get(cboCuenta);
		if(cta.getMonedaProducto().equals(Constante.MONEDA_DOLAR)){
			if (cboEntidad.equals("00417")){ //Poder Judicial
				request.setAttribute("listaTributos",FacadeFactory.getGeneralFacade().getComboDetalleHlpHijo(Constante.COD_HLP_DET_TRIBUTO,""));				
				ArrayRuleException e = new  ArrayRuleException(ConstanteError.GENERICO,"Debe Seleccionar una Cuenta en Soles");
				e.setForward("iniciarPago");
				throw e;
			}
		}

		
		if(cboEntidad!=null||!cboEntidad.equals(""))
			request.setAttribute("listaTributos",FacadeFactory.getGeneralFacade().getComboAyudaHijoOrden(cboEntidad));
		request.setAttribute("cboCuenta",cboCuenta);
		request.setAttribute("cboEntidad",cboEntidad);
		
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);

		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		
		ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
		
		return mapping.findForward("iniciarPago");
	}
	
	public ActionForward reGenerarClaveSms(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Usuario usuario = (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		
		try {
			PagoTasas pagoTasas = (PagoTasas)request.getSession().getAttribute(ConstanteSesion.PAGO_TASAS);
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
			
			String txtDocumento		= request.getParameter("numeDocumento");		
			String importeTotal = request.getParameter("importeTotal").equals("0")?pagoTasas.getImporte():request.getParameter("importeTotal");
			
			String importe = "";

			if(request.getSession().getAttribute("importeTasa") != null){
				importe = request.getSession().getAttribute("importeTasa").toString();
			}
			
			List listaImportes = null;
			if(request.getSession().getAttribute("listaImporte") != null){
				//listaImportes = (List)request.getSession().getAttribute("listaImporte");
				//importe = ((ComboUtil)listaImportes.get(0)).getCodigo();
			}
			
			if(importe==null || importe.equals("") || importe.equals("0.00") || importe.equals("0"))importe=importeTotal;
			
			TokenSmsRequest tokenSms = new TokenSmsRequest();
			String documentType = Funciones.lpad(tipoDoc,"0",3);
			
			tokenSms.setCodCliente(usuario.getCodigoCic());
			tokenSms.setNroDocumento(numDoc);
			tokenSms.setTipoDocumento(documentType); //DNI
			tokenSms.setTypeTrans(Constante.TIP_OPER_PAGO_INST_PUBLICA);
			tokenSms.setTypeOp(Constante.COD_TRANSACCION_FINANCIERA);//FIN						
			tokenSms.setTypeCurrency(Constante.MONEDA_SOL_ISO);//PEN
			tokenSms.setAmount(importe);
			
			tokenSms.setCodCliDestinatario(txtDocumento);
			tokenSms.setNomCliDestinatario(pagoTasas.getEntidad().getDescripcion());
		
			MensajesTDC resultado = UtilAction.generarClaveSms(tokenSms);
			
			if(resultado.isExito()==false){		
				String mensaje = aplicacion.getMsjesHost("CS", resultado.getCodResult()).elementAt(2).toString();
				response.setStatus(404);
				response.getWriter().write(mensaje);
				response.getWriter().flush();
				response.getWriter().close();
			}
			
		}catch(ArrayRuleException ar){
			log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
			response.setStatus(404);
			request.setAttribute("listaEntidades",FacadeFactory.getGeneralFacade().getComboAyudaHijo(Constante.COD_HLP_DET_ENTIDAD));
			request.setAttribute("listaTributos",FacadeFactory.getGeneralFacade().getComboDetalleHlpHijo(Constante.COD_HLP_DET_TRIBUTO,""));
			FacadeFactory.getGeneralFacade().actualizarCuentas("LG02",usuario,request.getRemoteAddr());
			ar.printStackTrace();
			ar.setForward("iniciarPago");
			throw ar;
		}
		return mapping.findForward("confirmarPago");
	}
	
	public ActionForward generarClaveSms(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Usuario usuario = (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		
		try {
			PagoTasas pagoTasas = (PagoTasas)request.getSession().getAttribute(ConstanteSesion.PAGO_TASAS);
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
			String txtDocumento		= request.getParameter("numeDocumento");		
			String importeTotal = request.getParameter("importeTotal").equals("0")?pagoTasas.getImporte():request.getParameter("importeTotal");
			
			String importe = "";

			if(request.getSession().getAttribute("importeTasa") != null){
				importe = request.getSession().getAttribute("importeTasa").toString();
			}
			
			List listaImportes = null;
			if(request.getSession().getAttribute("listaImporte") != null){
				//listaImportes = (List)request.getSession().getAttribute("listaImporte");
				//importe = ((ComboUtil)listaImportes.get(0)).getCodigo();
			}
			
			if(importe==null || importe.equals("") || importe.equals("0.00") || importe.equals("0"))importe=importeTotal;
			
			TokenSmsRequest tokenSms = new TokenSmsRequest();
			String documentType = Funciones.lpad(tipoDoc,"0",3);
			
			tokenSms.setCodCliente(usuario.getCodigoCic());
			tokenSms.setNroDocumento(numDoc);
			tokenSms.setTipoDocumento(documentType); //DNI
			tokenSms.setTypeTrans(Constante.TIP_OPER_PAGO_INST_PUBLICA);
			tokenSms.setTypeOp(Constante.COD_TRANSACCION_FINANCIERA);//FIN						
			tokenSms.setTypeCurrency(Constante.MONEDA_SOL_ISO);//PEN
			tokenSms.setAmount(importe);
			
			tokenSms.setCodCliDestinatario(txtDocumento);
			tokenSms.setNomCliDestinatario(pagoTasas.getEntidad().getDescripcion());
		
			MensajesTDC resultado = UtilAction.generarClaveSms(tokenSms);
			
			
			
		}catch(ArrayRuleException ar){
			log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
			response.setStatus(404);
			request.setAttribute("listaEntidades",FacadeFactory.getGeneralFacade().getComboAyudaHijo(Constante.COD_HLP_DET_ENTIDAD));
			request.setAttribute("listaTributos",FacadeFactory.getGeneralFacade().getComboDetalleHlpHijo(Constante.COD_HLP_DET_TRIBUTO,""));
			FacadeFactory.getGeneralFacade().actualizarCuentas("LG02",usuario,request.getRemoteAddr());
			ar.printStackTrace();
			ar.setForward("iniciarPago");
			throw ar;
		}
		return mapping.findForward("confirmarPago");
	}
	
	public ActionForward confirmarPago(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		//System.out.println("TASAS - confirmarPago");
		request.getSession().setAttribute("mensajeavisodni",((Vector)aplicacion.getMensajePorCodigo("PT01","00003")).elementAt(2).toString());
		//System.out.println("TASAS - confirmarPago 2");
		request.getSession().setAttribute("mensajeavisoimpresion",((Vector)aplicacion.getMensajePorCodigo("PT01","00004")).elementAt(2).toString());
		request.getSession().setAttribute("TITULOS","PAGO DE TASAS");
		
		Usuario usuario = (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		String cboCuenta  = request.getParameter("cboCuenta");
		String[] arrayEntidad = ObjectUtil.getArrayStrings(request.getParameter("hidEntidad"),"|");
		String[] arrayTributo = ObjectUtil.getArrayStrings(request.getParameter("hidTributo"),"|");
		//String importe = "";
		List x=usuario.getCuentas();
		CuentaImpl cta=null;
		boolean flag= false;
		for(int i=0; i<x.size();i++){
			cta=(CuentaImpl)x.get(i);
			if(cta.getTipoProducto().equals("44")){
				flag=true;	
				//System.out.println("ENCONTRO CUENTA PRESTAMOS");
				break;
			}	
		}
		if(!flag){
			cta	= (CuentaImpl)usuario.getMapCuentas().get(cboCuenta);
		}else{
			for(int i=0; i<x.size();i++){
				cta=(CuentaImpl)x.get(i);
				if(!cta.getTipoProducto().equals("44")&& cta.getNumeroProducto().equals(cboCuenta)){
					break;
					
				}	
			}
		
		}
		
		Entidad entidad = null;
		if(arrayEntidad !=null && arrayEntidad.length > 0){
			entidad = new EntidadImpl(arrayEntidad[0],arrayEntidad[1]);
			request.getSession().setAttribute("entidad", entidad);
		}else{
			entidad = (Entidad)request.getSession().getAttribute("entidad");
		}
		
		Tributo tributo = null;
		if(arrayTributo != null && arrayTributo.length > 0){
			tributo = new TributoImpl(arrayTributo[0],arrayTributo[1]);
			request.getSession().setAttribute("tributo", tributo);
		}else{
			tributo = (Tributo)request.getSession().getAttribute("tributo");
		}
		
		
		
		request.getSession().setAttribute("nomtribcbo",tributo.getCodigo()+" - "+tributo.getDescripcion());
		
		PagoTasas pagoTasas = null;
		try{
		    pagoTasas = FacadeFactory.getPagoFacade().getPagoTasas((Cuenta)cta,entidad,tributo);
			//request.getSession().setAttribute(ConstanteSesion.PAGO_TASAS,pagoTasas);
			//pagoTasas = null;
			//pagoTasas = (PagoTasas)request.getSession().getAttribute(ConstanteSesion.PAGO_TASAS);
			
			tributo.setDescripcion((tributo.getDescripcion().replaceAll("&lt;","<")));
	
			pagoTasas.setNomtribcbo(tributo.getCodigo()+" - "+ tributo.getDescripcion());
			
			request.getSession().setAttribute(ConstanteSesion.PAGO_TASAS,pagoTasas);
		}
		catch(ArrayRuleException e){
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			request.setAttribute("listaEntidades",FacadeFactory.getGeneralFacade().getComboAyudaHijo(Constante.COD_HLP_DET_ENTIDAD));
			request.setAttribute("listaTributos",FacadeFactory.getGeneralFacade().getComboDetalleHlpHijo(Constante.COD_HLP_DET_TRIBUTO,""));
			FacadeFactory.getGeneralFacade().actualizarCuentas("LG02",usuario,request.getRemoteAddr());
			e.setForward("iniciarPago");
			throw new ArrayRuleException(ConstanteError.GENERICO,"EN ESTOS MOMENTOS NO PODEMOS ATENDER SU SOLICITUD, INTENTELO MAS TARDE");
		}
		//PagoTasas pagoTasas = (PagoTasas)request.getSession().getAttribute(ConstanteSesion.PAGO_TASAS);
		String importe = pagoTasas.getImporte();
		String codTipDocLista = ""; 

		if (pagoTasas.getFlgConfig().equals("0")){
			List listaTdoc = FacadeFactory.getGeneralFacade().getComboAyudaHijo(tributo.getCodigo());
			if(!(listaTdoc==null||listaTdoc.size()==0)){
				for(int i = 0 ; i < listaTdoc.size();i++){
					ComboUtil comboTdoc = (ComboUtil)listaTdoc.get(i);
					codTipDocLista = comboTdoc.getCodigo();
				}
			}
			List lista = FacadeFactory.getGeneralFacade().getComboDetHlp(codTipDocLista);
					
			request.setAttribute("listaTipdoc",lista);
			request.getSession().setAttribute("listaTipdoc",lista);
			
		}else{
			String codAyudaDocs = "00003";
			if (!pagoTasas.getTipoDoc().equals("1")){ 
				codAyudaDocs = pagoTasas.getTipoDoc();
			}
		    List lista = FacadeFactory.getGeneralFacade().getComboDetHlp(codAyudaDocs);
			request.setAttribute("listaTipdoc",lista);
			request.getSession().setAttribute("listaTipdoc",lista);
		}
		
		if (pagoTasas.getFlgConfig().equals("0")){
			if (entidad.getCodigo().equals("00421")){
				request.setAttribute("listaCiudad",FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_DET_LOCAL));
				request.getSession().setAttribute("listaCiudad",FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_DET_LOCAL));
			} else {
				request.setAttribute("listaCiudad",FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_DET_CIUDAD_MTC));
				request.getSession().setAttribute("listaCiudad",FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_DET_CIUDAD_MTC));
			}
		}else if (pagoTasas.getFlgCiudadVisible().equals("1")){
			if (entidad.getCodigo().equals("00421")){
				request.setAttribute("listaCiudad",FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_DET_LOCAL));
				request.getSession().setAttribute("listaCiudad",FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_DET_LOCAL));
			} else {
				request.setAttribute("listaCiudad",FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_DET_CIUDAD_MTC));
				request.getSession().setAttribute("listaCiudad",FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_DET_CIUDAD_MTC));
			}		    
		}
		List listaImportes = null;
		if (pagoTasas.getTipoImporte().equals("3")){
			listaImportes = FacadeFactory.getPagoFacade().getListaImportes(pagoTasas.getTributo().getCodigo(),(Cuenta)cta);
		}
		
	
		request.setAttribute("listaImporte",listaImportes);
		request.getSession().setAttribute("listaImporte",listaImportes);
		request.getSession().setAttribute("importeTasa",importe);
		
		
		List listaDistritos = null;
		List listaJuzgados = null;
		if (pagoTasas.getFlgDistritoJuzgadoVisible().equals("1")){
		    listaDistritos = FacadeFactory.getGeneralFacade().getComboDetHlpOrden("10000");
			ComboUtil combo = new ComboUtil();
			combo.setCodigo("");
			combo.setDescripcion(Constante.SELECCIONE);
			listaDistritos.add(0,combo);
		    
		    
			if (pagoTasas.getTributo().getCodigo().equals("03670")) {
			    listaJuzgados = FacadeFactory.getGeneralFacade().getComboDetHlp("10002");
				combo = new ComboUtil();
				combo.setCodigo("");
				combo.setDescripcion(Constante.SELECCIONE);
				listaJuzgados.add(0,combo);
			}else{
			    listaJuzgados = FacadeFactory.getGeneralFacade().getComboDetHlp("10001");
			    combo = new ComboUtil();
				combo.setCodigo("");
				combo.setDescripcion(Constante.SELECCIONE);
				listaJuzgados.add(0,combo);			    
			}
		}
		request.setAttribute("listaDistrito",listaDistritos);
		request.getSession().setAttribute("listaDistrito",listaDistritos);
		
		request.setAttribute("listaJuzgado",listaJuzgados);
		request.getSession().setAttribute("listaJuzgado",listaJuzgados);	
		
		if (pagoTasas.getTributo().getCodigo().equals("07900")){
		    request.getSession().setAttribute("leyenda_aux_tasas",((Vector)aplicacion.getMensajePorCodigo("PT01","00005")).elementAt(2).toString());
		}else{
		    request.getSession().setAttribute("leyenda_aux_tasas","");
		}
		
		ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
		
		//REALIZAR CONSULTA TIPO CLAVE DINAMICA
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
			request.setAttribute("listaEntidades",FacadeFactory.getGeneralFacade().getComboAyudaHijo(Constante.COD_HLP_DET_ENTIDAD));
			request.setAttribute("listaTributos",FacadeFactory.getGeneralFacade().getComboDetalleHlpHijo(Constante.COD_HLP_DET_TRIBUTO,""));
			ar.printStackTrace();
			ar.setForward("iniciarPago");
			throw ar;
		}
		
		//REALIZAR CONSULTA DE COORDENADAS
		ElemenSeguridad resultCoord = null;
		if(param!= null){
			resultCoord = new ElemenSeguridad();
			try{
				request.getSession().setAttribute("resultCoord",null );
				
				if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC))
				{
					resultCoord = SolicitarServiciosTDC.solicitCoordenada(param);
				}
				else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS))
				{
					
					resultCoord=SolicitarServiciosTDC.solicitarTokens(param);
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
			
		}
		else{
			
			throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
		}

	
	
		
		return mapping.findForward("confirmarPago");
	}
	
	public ActionForward actualizarImporte(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		PagoTasas pagoTasas = (PagoTasas)request.getSession().getAttribute(ConstanteSesion.PAGO_TASAS);
		String codDetalle = request.getParameter("codDetalleTributo");
		List listaImportes = null;
		if (pagoTasas.getTipoImporte().equals("3")){
			if(codDetalle!= null && !codDetalle.trim().equals("")){
				String id[] = codDetalle.split("-");
		    	listaImportes = FacadeFactory.getPagoFacade().getListaImportesDetallado(pagoTasas.getTributo().getCodigo(),id[1]);
			}
		}
		ComboUtil cu = (ComboUtil)listaImportes.get(0);
		String importeTasa = cu.getCodigo();
		request.getSession().setAttribute("importeTasa",importeTasa);
		request.setAttribute("datosTributo","si");
		request.setAttribute("listaImporte",listaImportes);
		request.setAttribute("codDetalleTributo",codDetalle);
		request.getSession().setAttribute("listaImporte",listaImportes);
		request.getSession().setAttribute(ConstanteSesion.PAGO_TASAS,pagoTasas);
		forward = mapping.findForward("confirmarPago");
		return (forward);	
		
	}
	
	public ActionForward pagar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
				
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
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
		
		PagoTasas pagoTasas = (PagoTasas)request.getSession().getAttribute(ConstanteSesion.PAGO_TASAS);	
		
		request.getSession().setAttribute("mensajeavisodni",((Vector)aplicacion.getMensajePorCodigo("PT01","00003")).elementAt(2).toString());
		
		String cboDetalle		= request.getParameter("cboDetalle");
		String tipoDocumento	= request.getParameter("cboTipodoc");
		String descripcion		= request.getParameter("cboTipDocDes");
		String txtDocumento		= request.getParameter("txtNumDoc");		
		String idCiudad			= request.getParameter("cboCiudad"); 
		String transaccion = request.getParameter("transaccion");
		if (pagoTasas.getEntidad().getCodigo().equals("00417")){ //Poder Judicial
			transaccion = "PT02";
		}
		String importe = request.getParameter("cboImporte");
		String importeLibre = request.getParameter("txtImporteLibre");
		String importeTotal = request.getParameter("hidImporteTotal");
		String cantidad = request.getParameter("txtCantidad");
		String codDependencia = request.getParameter("hidCodDependencia");
		String codDistrito = request.getParameter("hidCodDistrito");
		String codJuzgado = request.getParameter("hidCodJuzgado");
		String nroExpediente = request.getParameter("txtNumExpediente");
		
		pagoTasas.setDetalle(cboDetalle);
		pagoTasas.setCantidad("1");
		pagoTasas.setImporteTotal(new BigDecimal(0));
		
		if (pagoTasas.getFlgConfig().equals("1")){
		    if (pagoTasas.getTipoImporte().equals("3")){ //Lista
		        if (pagoTasas.getFlgCantidadVisible().equals("1")){
		            ((PagoTasasImpl)pagoTasas).setImporte(new BigDecimal(importe)); // Se envía el importe de una unidad
		            pagoTasas.setImporteTotal(new BigDecimal(importeTotal));
		            pagoTasas.setCantidad(cantidad);
		        }else{
		            ((PagoTasasImpl)pagoTasas).setImporte(new BigDecimal(importe));
		        }
		    }else if (pagoTasas.getTipoImporte().equals("2")){ //Libre
		        ((PagoTasasImpl)pagoTasas).setImporte(new BigDecimal(importeLibre));
		    }
		    if (pagoTasas.getFlgDistritoJuzgadoVisible().equals("1")){
		    	pagoTasas.setCodDependencia(codDependencia);
		    	pagoTasas.setDescripcionDependencia(getDescripcionDependencia(pagoTasas.getTributo().getCodigo(),codDistrito,codJuzgado));
		    }
		    
		    if (pagoTasas.getFlgExpedienteVisible().equals("1")){
		    	pagoTasas.setNroExpediente(nroExpediente);
		    }
		}
		
		//A veces se muestra la variable de detalle de tributo en lugar de su valor
		//Se guardará el valor directamente en sesion
		//No se pueden constantes porque en la plantilla no se soportan 
		String codDetalle = cboDetalle;
		String codTributo = pagoTasas.getTributo().getCodigo();
		
		boolean tieneDetalle = pagoTasas.getMostrarDetalleTributo();
		String detalleTributo="";

		if (tieneDetalle){
		    if (pagoTasas.getDetalle()!=null){
		        if (pagoTasas.getDetalle().getDescripcion()!=null){
		            detalleTributo=pagoTasas.getDetalle().getDescripcion();
		        }
		    }
		}
		request.getSession().setAttribute("tasasDetalleTributo",detalleTributo);
		if (detalleTributo==null){
		    detalleTributo="null!";
		}
		if (codDetalle==null){
		    codDetalle="null!";
		}

		Documento documento = new DocumentoImpl();
		documento.setCodigo(tipoDocumento);
		documento.setDescripcion(descripcion);
		documento.setNumero(txtDocumento);
		
		pagoTasas.setDocumento(documento);
		
		Ciudad ciudad = new CiudadImpl();
		if(idCiudad!=null){
			//List ciudades = FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_DET_LOCAL);
			//Se modifica para leer el pago de tasas de lima - MTC
			List ciudades = FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_DET_CIUDAD_MTC);
			for (Iterator iter = ciudades.iterator(); iter.hasNext();) {
				ComboUtil combo = (ComboUtil) iter.next();
				if(combo.getCodigo().equals(idCiudad)){
					ciudad.setCodigo(combo.getCodigo());
					ciudad.setDescripcion(combo.getDescripcion());
					break;
				}
			}			
		}
		
		pagoTasas.setCiudad(ciudad);
		request.getSession().setAttribute(ConstanteSesion.PAGO_TASAS,pagoTasas);
		//String clave = SeguridadUtil.getClaveDesencriptada(request.getParameter("txtNumClave"),request);
		
		ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
		
		//Código Nuevo para TDC
		ElemenSeguridad resultCoord = null;
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		ElemenSeguridad coord = (ElemenSeguridad)request.getSession().getAttribute("resultCoord");
		
		String pinblock = ObjectUtil.getClaveDesencriptada(request.getParameter("txtCoordenada"),request);
		
		//REALIZAR CONSULTA TIPO CLAVE DINAMICA  --> ADD M.D.B.
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
			ar.setForward("iniciarPago");
			throw ar;
		}
		
		// Código Nuevo para TDC
		
		MensajesTDC resultado = new MensajesTDC();
		
		if(param!= null && coord!=null ){
			try{

				if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC))
				{
					resultado=SolicitarServiciosTDC.validarTDC(param ,coord,pinblock);
				}
				else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS))
				{
					resultado=SolicitarServiciosTDC.validarTokens(param ,coord,pinblock);
				}
				else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS))
				{
					
					String nroDoc = numDoc;
					String documentType = Funciones.lpad(tipoDoc,"0",3);
					TokenSmsRequest tokenSms = new TokenSmsRequest();

					tokenSms.setCodeSMS(pinblock);
					tokenSms.setCodCliente(usuario.getCodigoCic());
					tokenSms.setTipoDocumento(documentType);
					tokenSms.setNroDocumento(nroDoc);
					tokenSms.setTypeTrans(Constante.TIP_OPER_PAGO_INST_PUBLICA);
					tokenSms.setTypeOp(Constante.COD_TRANSACCION_FINANCIERA);//FIN
					tokenSms.setTypeCurrency(Constante.MONEDA_SOL_ISO);//PEN
					tokenSms.setAmount(importe);
					
					tokenSms.setCodCliDestinatario(txtDocumento);
					tokenSms.setNomCliDestinatario(pagoTasas.getEntidad().getDescripcion());
					
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
				
				try{
					PagoTasas pt = FacadeFactory.getPagoFacade().pagarTasas(transaccion,pagoTasas,usuario,request.getRemoteAddr(), request);
					if(usuario.getNotificacion().getEstadoNotificacion().equals("1")){
						 ServiciosNotificacionPrincipal.enviarNotificacion(
								 usuario,
								 usuario.getNombre(), 
								 pt.getCuenta().getSimboloMonedaProducto(), 
								 pt.getImporte(),
								 usuario.getTipoCambio().getVenta(), 
								 Constante.NOTI_PAGO_TASAS, 
								 usuario.getNotificacion());
					}
					
					ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_SERVICIOS_TASAS, ConstanteSesion.PAGO_TASAS, request);
				}
				catch(ArrayRuleException ar){
					ar.printStackTrace();
					log3.error(ar,Constante.ERR_LOGICA_NEGOCIO,"");
					//FacadeFactory.getGeneralFacade().actualizarCuentas("LG02",usuario,request.getRemoteAddr());
					request.setAttribute("listaTipdoc",request.getSession().getAttribute("listaTipdoc"));
					request.setAttribute("listaCiudad",request.getSession().getAttribute("listaCiudad"));

					ar.setForward("confirmarPago");
					throw ar;
				}
								
			}
			catch (ArrayRuleException e) {
				e.printStackTrace();
				log3.error(e,Constante.ERR_ERROR_VALIDAR_TDC,"");
			
				String codTipDocLista = ""; 
				
				if (pagoTasas.getFlgConfig().equals("0")){
					List listaTdoc = FacadeFactory.getGeneralFacade().getComboAyudaHijo(pagoTasas.getTributo().getCodigo());
					ComboUtil comboTdoc = (ComboUtil)listaTdoc.get(1);
					codTipDocLista = comboTdoc.getCodigo();
					request.setAttribute("listaTipdoc",request.getSession().getAttribute("listaTipdoc"));
				}
				request.setAttribute("listaCiudad",request.getSession().getAttribute("listaCiudad"));
				request.setAttribute("listaEntidades",FacadeFactory.getGeneralFacade().getComboAyudaHijo(Constante.COD_HLP_DET_ENTIDAD));
				request.setAttribute("listaTributos",FacadeFactory.getGeneralFacade().getComboDetalleHlpHijo(Constante.COD_HLP_DET_TRIBUTO,""));
				
				request.getSession().setAttribute("mensajeHorarioTasas",((Vector)aplicacion.getMensajePorCodigo("PT01","00001")).elementAt(2).toString());
				//List lista = FacadeFactory.getGeneralFacade().getComboDetHlp(codTipDocLista);
			
				if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC))
				{
					resultCoord = SolicitarServiciosTDC.solicitCoordenada(param);
				}
				else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS))
				{
					resultCoord=SolicitarServiciosTDC.solicitarTokens(param);
				}
				else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS))
				{
					resultCoord=SolicitarServiciosTDC.solicitarTokensSms(param);
				}
					
				//resultCoord = SolicitarServiciosTDC.solicitCoordenada(param);
				request.getSession().setAttribute("resultCoord",resultCoord );
				e.setForward("confirmarPago");
				throw e;
			}
		}
		
	
		return mapping.findForward("pagar");
	}
	
	private String getDescripcionDependencia(String codTributo, String codDistrito, String codJuzgado){
		String result ="";
		String distrito = "";
		String juzgado = "";
		try{
			if (codDistrito != null){
				List distritos = FacadeFactory.getGeneralFacade().getComboDetalleHlpOrden("10000");
				for (Iterator iter = distritos.iterator(); iter.hasNext();) {
					ComboUtil combo = (ComboUtil) iter.next();
					if(combo.getCodigo().equals(codDistrito)){
						result = combo.getDescripcion();
						break;
					}
				}
			}
			
			if  (codJuzgado != null){
				if (!codTributo.equals("03670")){
					List juzgados = FacadeFactory.getGeneralFacade().getComboDetalleHlp("10001");
					for (Iterator iter = juzgados.iterator(); iter.hasNext();) {
						ComboUtil combo = (ComboUtil) iter.next();
						if(combo.getCodigo().equals(codDistrito)){
							result = combo.getDescripcion();
							break;
						}
					}
				}else{
					List juzgados = FacadeFactory.getGeneralFacade().getComboDetalleHlp("10002");
					for (Iterator iter = juzgados.iterator(); iter.hasNext();) {
						ComboUtil combo = (ComboUtil) iter.next();
						if(combo.getCodigo().equals(codDistrito)){
							result = combo.getDescripcion();
							break;
						}
					}				
				}
			}
			result = distrito + " - " + juzgado;
		}catch (Exception e){
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			System.out.println("getDescripcionDependencia: " + e.getMessage());
		}
		return result;
	}
//	                                  
	 private static final char c[] = { '<', '>', '&', '\"'};
	 private static final String expansion[] = {"&lt;", "&gt;", "&amp;",
	                                     "&quot;"};
	 
	 public static String HTMLEncode(String s) {
	      StringBuffer st = new StringBuffer();
	      for (int i = 0; i < s.length(); i++) {
	          boolean copy = true;
	          char ch = s.charAt(i);
	          for (int j = 0; j < c.length ; j++) {
	            if (c[j]==ch) {
	                st.append(expansion[j]);
	                copy = false;
	                break;
	            }
	          }
	          if (copy) st.append(ch);
	      }
	    
	      return st.toString();
	    }


}
