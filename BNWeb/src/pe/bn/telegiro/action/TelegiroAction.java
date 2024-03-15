package pe.bn.telegiro.action;

import java.math.BigDecimal;
import java.util.HashMap;
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
import org.json.simple.JSONObject;


import pe.bn.afiliacion.domain.Afiliacion;
import pe.bn.afiliacion.domain.impl.AfiliacionImpl;
import pe.bn.cldinamica.action.SolicitarServiciosTDC;
import pe.bn.cldinamica.action.form.ElemenSeguridad;
import pe.bn.cldinamica.action.form.MensajesTDC;
import pe.bn.cldinamica.action.form.ParametrosElemSegTDC;
import pe.bn.cldinamica.action.form.ParametrosTDC;
import pe.bn.listener.Util;
import pe.bn.notificaciones.action.ServicioNotificacionConstancia;
import pe.bn.notificaciones.action.ServiciosNotificacionPrincipal;
//import pe.bn.notificaciones.model.ParametrosSIAT;
import pe.bn.telegiro.domain.Telegiro;
import pe.bn.telegiro.domain.impl.TelegiroImpl;
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
import pe.cosapi.domain.Agencia;
import pe.cosapi.domain.AtriTransferencia;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.AgenciaImpl;
import pe.cosapi.domain.impl.AtriTransferenciaImpl;
import pe.cosapi.domain.impl.CuentaImpl;
import pe.cosapi.domain.impl.OperacionImpl;
import pe.cosapi.dto.PersonaDTO;

/**
 * @author cosapi_ati04
 * 
 * TODO Para cambiar la plantilla de este comentario generado, vaya a Ventana -
 * Preferencias - Java - Estilo de código - Plantillas de código
 */
public class TelegiroAction extends GrandActionAbstract {
	BNAplicacion aplicacion = BNAplicacion.getInstance();

	private static LoggerSati log3 = LoggerSati.getInstance(TelegiroAction.class.getName());

	public ActionForward iniciar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			
            Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
            
			ParametrosElemSegTDC elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento"); //5,6,7
			request.getSession().setAttribute("mensajeClaveSms", null);
            
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
                //generarClaveSms(mapping, form, request, response);    
                return mapping.findForward("actualizarDatosPersona");
            }
			
			BNAplicacion aplicacion = BNAplicacion.getInstance();
			FacadeFactory.getGeneralFacade().actualizarCuentas("LG02", usuario,request.getRemoteAddr());

			List listaAfiliaciones = FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.TELEGIRO_NACIONAL,usuario.getTarjeta().getNumero(), "00004");
			listaAfiliaciones.addAll(FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.TELEGIRO_INTERNACIONAL,usuario.getTarjeta().getNumero(), "00004"));
			Constante.TIPO_SERVICIO = Constante.TELEGIRO_NACIONAL;
			List lstDocumentoBen = FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_DOCU_GIROS);
			
			String hidServicio = request.getParameter("hidServicio");
			String hidCodEntidad = request.getParameter("hidCodEntidad");
			String hidCodServ = request.getParameter("hidCodServ");
			//String typeTrans = UtilAction.obtenerTipoTransaccionFinanciera(hidCodServ);
			
//			System.out.println("***hidServicio***: " + hidServicio);
//			System.out.println("***hidCodEntidad***: " + hidCodEntidad);
//			System.out.println("***hidCodServ***: " + hidCodServ);
			//System.out.println("typeTrans::::" + typeTrans);
			

			request.setAttribute("listaAfiliaciones", listaAfiliaciones);
			request.getSession().setAttribute("mensajeAfiliacion",((Vector) aplicacion.getMensajePorCodigo("TL01", "AF001")).elementAt(2).toString());
			request.getSession().setAttribute("mensajePantalla",((Vector) aplicacion.getMensajePorCodigo("TL01", "00001")).elementAt(2).toString());
			request.getSession().setAttribute("mensajepiePagoGiro",((Vector) aplicacion.getMensajePorCodigo("TL01", "00003")).elementAt(2).toString());
			request.getSession().setAttribute("TITULOS", "EMISION DE GIROS");
			request.getSession().setAttribute("lstDocumentoBen",lstDocumentoBen);
			
			
			System.out.println("elementos.getTipoElementoSeguridad()::::"+elementos.getTipoElementoSeguridad());
			
			if(elementos.getTipoElementoSeguridad().equals("5")){
				System.out.println("Token Fisico");
				usuario.getClaveDinamica().setTipoElementoSeguridad(elementos.getTipoElementoSeguridad());
				usuario.getClaveDinamica().setTipoElementoSeguridadFinal("Token Fisico");
				
			}else if(elementos.getTipoElementoSeguridad().equals("6")){
				System.out.println("Clave Dinamica Digital");
				usuario.getClaveDinamica().setTipoElementoSeguridad(elementos.getTipoElementoSeguridad());
				usuario.getClaveDinamica().setTipoElementoSeguridadFinal("Clave Dinamica Digital");
			}
			
			

		} catch (ArrayRuleException e) {
			log3.error(e, Constante.ERR_LOGICA_NEGOCIO, "");
			e.setForward("iniciarTelegiro");

			throw e;
		}
		ParametrosTDC param = (ParametrosTDC) request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);

		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION, param);

		//ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		//request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION, registro);

		return mapping.findForward("iniciarTelegiro");
	}

	public ActionForward verTelegiro(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {

			Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
			String[] id = ObjectUtil.getArrayStrings(request.getParameter("cmbTelegiro"), "-");
			// Cargar el mensaje de la Condicion
			BNAplicacion aplicacion = BNAplicacion.getInstance();
			request.getSession().setAttribute("mensajeCondicion",((Vector) aplicacion.getMensajePorCodigo("TL01", "00002")).elementAt(2).toString().trim());
			request.getSession().setAttribute("mensajepiePagoGiro",((Vector) aplicacion.getMensajePorCodigo("TL01", "00003")).elementAt(2).toString());
			Afiliacion afiliacionDatos = new AfiliacionImpl();

			if (request.getParameter("optTelegiro").equals(Constante.NUEV_REGISTRO_TRANSAC)) {

				afiliacionDatos.setCodigoServicio(request.getParameter("cmbTipoDocBenef"));
				afiliacionDatos.setNumeroServicio(request.getParameter("txtNroDocBenef"));
				PersonaDTO datos = new PersonaDTO();
				datos.setApellidoPaterno(request.getParameter("txtAppBenef").trim().toUpperCase());
				datos.setApellidoMaterno(request.getParameter("txtApmBenef").trim().toUpperCase());
				datos.setNombre(request.getParameter("txtNombreBenef").trim().toUpperCase());
				datos.setTipoDocumento(request.getParameter("cmbTipoDocBenef"));
				datos.setNumeroDocumento(request.getParameter("txtNroDocBenef"));
				afiliacionDatos.setObjBenef(datos);
				afiliacionDatos.setBeneficiario(datos.getApellidoPaterno()+ " " + datos.getApellidoMaterno() + " "+ datos.getNombre());
				afiliacionDatos.setFlagRegistro(Constante.NUEV_REGISTRO_TRANSAC);
				request.getSession().setAttribute("CORREO_AFILIADO", "");
			} else {
				request.getSession().setAttribute("afiliacion",FacadeFactory.getAfiliacionFacade().getAfiliacion(id[0], id[1], new Long(id[2]), "00004"));
				afiliacionDatos = (Afiliacion) request.getSession().getAttribute("afiliacion");
				afiliacionDatos.setFlagRegistro(Constante.FREC_REGISTRO_TRANSAC);
				if (afiliacionDatos.getEmail() != null) {
					request.getSession().setAttribute("CORREO_AFILIADO","" + afiliacionDatos.getEmail());
				} else {
					request.getSession().setAttribute("CORREO_AFILIADO", "");
				}

			}

			afiliacionDatos.setDescripcionTipoDocumento(NomTipoDocBenef(afiliacionDatos.getCodigoServicio(),Constante.COD_HLP_DOCU_GIROS));
			request.getSession().setAttribute("afiliacion", afiliacionDatos);

			String cboCuenta = request.getParameter("cmbCuenta");
			// CuentaImpl cuenta =
			// (CuentaImpl)usuario.getMapCuentas().get(cboCuenta);
			List x = usuario.getCuentas();
			CuentaImpl cta = null;
			boolean flag = false;
			for (int i = 0; i < x.size(); i++) {
				cta = (CuentaImpl) x.get(i);
				if (cta.getTipoProducto().equals("44")) {
					flag = true;
					// System.out.println("ENCONTRO CUENTA PRESTAMOS");
					break;
				}
			}
			if (!flag) {
				cta = (CuentaImpl) usuario.getMapCuentas().get(cboCuenta);
			} else {
				for (int i = 0; i < x.size(); i++) {
					cta = (CuentaImpl) x.get(i);
					if (!cta.getTipoProducto().equals("44") && cta.getNumeroProducto().equals(cboCuenta)) {
						break;

					}
				}

			}
			request.setAttribute("cuenta", cta);
			List agencias = FacadeFactory.getGeneralFacade().getAgenciasSoles();

			if (cta.getMonedaProducto().equals(Constante.MONEDA_SOL)) {
				request.getSession().setAttribute("listaAgencias",FacadeFactory.getGeneralFacade().getAgenciasSoles());
			} else if (cta.getMonedaProducto().equals(Constante.MONEDA_DOLAR)) {
				request.getSession().setAttribute("listaAgencias",FacadeFactory.getGeneralFacade().getAgenciasDolares());
			}
			List departamentos = FacadeFactory.getGeneralFacade().getDepartamentos();

			request.getSession().setAttribute("listDeptos", departamentos);
			request.getSession().setAttribute("cmbCuenta",request.getParameter("cmbCuenta"));
			request.getSession().setAttribute("cuenta",usuario.getMapCuentas().get(request.getParameter("cmbCuenta")));

		} catch (ArrayRuleException e) {
			log3.error(e, Constante.ERR_LOGICA_NEGOCIO, "");
			Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
			FacadeFactory.getGeneralFacade().actualizarCuentas("LG02", usuario,request.getRemoteAddr());
			List listaAfiliaciones = FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.TELEGIRO_NACIONAL,usuario.getTarjeta().getNumero(), "00004");
			listaAfiliaciones.addAll(FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.TELEGIRO_INTERNACIONAL,usuario.getTarjeta().getNumero(), "00004"));
			request.setAttribute("listaAfiliaciones", listaAfiliaciones);
			Constante.TIPO_SERVICIO = Constante.TELEGIRO_NACIONAL;

			e.setForward("iniciarTelegiro");
			throw e;
		}
		ParametrosTDC param = (ParametrosTDC) request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		//ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);

		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION, param);
		//request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION, registro);

		return mapping.findForward("verTelegiro");
	}

	public ActionForward confirmarTelegiro(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		JSONObject jsonConsultaTipoToken = null;
		Map<String, String> mapParametros = new HashMap<String, String>();
		TokenSmsRequest tokenSms = new TokenSmsRequest();

		//Afiliacion datosUsuario = new AfiliacionImpl();
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		System.out.println("usuario:::"+usuario);
		int diferencia;
		String cboCuenta = request.getParameter("cmbCuenta");
		CuentaImpl cta = null;
		
		try {
			CuentaImpl cuenta = (CuentaImpl) usuario.getMapCuentas().get(cboCuenta);
			List x = usuario.getCuentas();

			boolean flag = false;
			for (int i = 0; i < x.size(); i++) {
				cta = (CuentaImpl) x.get(i);
				if (cta.getTipoProducto().equals("44")) {
					flag = true;
					// System.out.println("ENCONTRO CUENTA PRESTAMOS");
					break;
				}
			}
			if (!flag) {
				cta = (CuentaImpl) usuario.getMapCuentas().get(cboCuenta);
			} else {
				for (int i = 0; i < x.size(); i++) {
					cta = (CuentaImpl) x.get(i);
					if (!cta.getTipoProducto().equals("44")
							&& cta.getNumeroProducto().equals(cboCuenta)) {
						break;
					}
				}
			}

			request.setAttribute("cuenta", cta);

			if (cta.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_MN)) {
				List listaAfiliaciones = FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.TELEGIRO_NACIONAL,usuario.getTarjeta().getNumero(), "00004");
				double number = Double.parseDouble(request.getParameter("txtMonto"));
				long iPart = (long) number;
				double fPart = number - iPart;

				if (fPart == 0.0) {
					int monto = (int) iPart;
					diferencia = monto % 10;
					if (diferencia != 0) {
						listaAfiliaciones.addAll(FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.TELEGIRO_INTERNACIONAL,usuario.getTarjeta().getNumero(),"00004"));
						request.setAttribute("listaAfiliaciones",listaAfiliaciones);
						throw new ArrayRuleException(ConstanteError.GENERICO,"El monto ingresado debe ser multiplo de 10 Soles.");

					}
				} else {

					listaAfiliaciones.addAll(FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.TELEGIRO_INTERNACIONAL,usuario.getTarjeta().getNumero(), "00004"));
					request.setAttribute("listaAfiliaciones",listaAfiliaciones);
					throw new ArrayRuleException(ConstanteError.GENERICO,"El monto ingresado no debe tener decimales.");

				}

				if (iPart == 0) {

					listaAfiliaciones.addAll(FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.TELEGIRO_INTERNACIONAL,usuario.getTarjeta().getNumero(), "00004"));
					request.setAttribute("listaAfiliaciones",listaAfiliaciones);
					throw new ArrayRuleException(ConstanteError.GENERICO,"El monto ingresado debe ser diferente de cero.");
				}

			}

		} catch (NumberFormatException e) {
			log3.error(e, Constante.ERR_LOGICA_NEGOCIO, "");
			List listaAfiliaciones = FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.TELEGIRO_NACIONAL,usuario.getTarjeta().getNumero(), "00004");
			listaAfiliaciones.addAll(FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.TELEGIRO_INTERNACIONAL,usuario.getTarjeta().getNumero(), "00004"));
			request.setAttribute("listaAfiliaciones", listaAfiliaciones);
			List lstDocumentoBen = FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_DOCU_GIROS);
			request.setAttribute("lstDocumentoBen", lstDocumentoBen);
			ArrayRuleException e1 = new ArrayRuleException(ConstanteError.GENERICO,"Verifique el monto del giro ingresado");
			e1.setForward("verTelegiro");
			throw e1;
		} catch (ArrayRuleException e) {
			log3.error(e, Constante.ERR_LOGICA_NEGOCIO, "");
			//List listaAfiliaciones = FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.TELEGIRO_NACIONAL,usuario.getTarjeta().getNumero(), "00004");
			//listaAfiliaciones.addAll(FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.TELEGIRO_INTERNACIONAL,usuario.getTarjeta().getNumero(), "00004"));
			//request.setAttribute("listaAfiliaciones", listaAfiliaciones);
			//List lstDocumentoBen = FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_DOCU_GIROS);
			//request.setAttribute("lstDocumentoBen", lstDocumentoBen);
			//ArrayRuleException e1 = new ArrayRuleException(ConstanteError.GENERICO,"Verifique el monto del giro ingresado");
			e.setForward("verTelegiro");
			throw e;
		}

		Afiliacion afiliacion = (Afiliacion) request.getSession().getAttribute("afiliacion");
		afiliacion.setDescripcionTipoDocumento(NomTipoDocBenef(afiliacion.getCodigoServicio(), Constante.COD_HLP_DET_DOCU_GIRO));
		request.getSession().setAttribute("afiliacion", afiliacion);
		request.setAttribute("optTelegiro", afiliacion.getFlagRegistro());

		// Datos del Beneficiario y titular
		if (afiliacion.getFlagRegistro().equals(Constante.NUEV_REGISTRO_TRANSAC)) {
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
		}

		String codDepartamento = request.getParameter("cboDepartamento");
		String codAgencia = request.getParameter("cboAgencia");
		String monto = request.getParameter("txtMonto");

		int result;
		BigDecimal importeSol = new BigDecimal(monto.replace(",", ""));
		BigDecimal importeMin = new BigDecimal(Constante.CONST_TELEGIRO_MONTO_MININO);

		try{
		result = importeSol.compareTo(importeMin);
		if (result == 1) {

			throw new ArrayRuleException(ConstanteError.GENERICO,"El monto ingresado debe ser menor o igual a S/ 2000.00 Soles");
		}
		
		} catch (ArrayRuleException e) {
			log3.error(e, Constante.ERR_LOGICA_NEGOCIO, "");
			//List listaAfiliaciones = FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.TELEGIRO_NACIONAL,usuario.getTarjeta().getNumero(), "00004");
			//listaAfiliaciones.addAll(FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.TELEGIRO_INTERNACIONAL,usuario.getTarjeta().getNumero(), "00004"));
			//request.setAttribute("listaAfiliaciones", listaAfiliaciones);
			//List lstDocumentoBen = FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_DOCU_GIROS);
			//request.setAttribute("lstDocumentoBen", lstDocumentoBen);
			//ArrayRuleException e1 = new ArrayRuleException(ConstanteError.GENERICO,"Verifique el monto del giro ingresado");
			e.setForward("verTelegiro");
			throw e;
		}	
		String transaccion = request.getParameter("transaccion");
		Agencia agencia1 = null;
		List lista = (List) request.getSession().getAttribute("listaAgencias");

		for (int i = 0; i < lista.size(); i++) {
			AgenciaImpl agenc = (AgenciaImpl) lista.get(i);
			if (agenc.getCodigo().equals(codAgencia) && agenc.getDepartamento().getCodigo().equals(codDepartamento)) {
				agencia1 = agenc;
				if (Integer.parseInt(codAgencia) < 100) {
					agencia1.setCodigo("0000");
				}

				break;
			}
		}

		//ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		//request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION, registro);

		// REALIZAR CONSULTA DE COORDENADAS o TOKEN
		ParametrosTDC param = (ParametrosTDC) request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		ParametrosElemSegTDC elementos = null;

		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION, param);
		// request.getSession().setAttribute("tipoElemento",null );

		request.getSession().setAttribute("monto",monto);
		
		try {
			elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
			
			if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS) ||
			   elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
				elementos = SolicitarServiciosTDC.verificarTipoElemento(param,usuario);
			}
			
		} catch (ArrayRuleException ar) {
			log3.error(ar, Constante.ERR_SOLICITAR_COORDENADA, "");
			ar.printStackTrace();
			request.setAttribute("cuenta", request.getAttribute("cuenta"));
			ar.setForward("verTelegiro");
			throw ar;
		}
		try {

			ElemenSeguridad resultCoord = null;
			if (param != null) {
				resultCoord = new ElemenSeguridad();
				try {
					
					request.getSession().setAttribute(ConstanteSesion.PAGO_TELEGIRO,FacadeFactory.getTelegiroFacade().getTelegiro(
							transaccion, usuario, (Cuenta) cta,afiliacion, monto, agencia1,request.getRemoteAddr()));
					
					request.getSession().setAttribute("resultCoord", null);

					if (elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)) {
						resultCoord = SolicitarServiciosTDC.solicitCoordenada(param);
					} else if (elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS)) {
						resultCoord = SolicitarServiciosTDC.solicitarTokens(param);
					} else if (elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {
						generarClaveSms(mapping,form, request, response);
					} 
					
					request.setAttribute("elementos", elementos.getTipoElementoSeguridad());

					System.out.println("elementos:::"+elementos);


					request.getSession().setAttribute(ConstanteSesion.PAGO_TELEGIRO,FacadeFactory.getTelegiroFacade().getTelegiro(
									transaccion, usuario, (Cuenta) cta,afiliacion, monto, agencia1,request.getRemoteAddr() ));


					System.out.println("request::"+request);
					
				} catch (ArrayRuleException ar) {
					log3.error(ar, Constante.ERR_SOLICITAR_COORDENADA, "");
					ar.printStackTrace();
					request.setAttribute("cuenta", request.getAttribute("cuenta"));
					ar.setForward("verTelegiro");
					throw ar;
				}
				System.out.println("resultCoord::"+resultCoord);
				request.getSession().setAttribute("resultCoord", resultCoord);
				
				
			} else {
				throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
			}
			// --------------------------------------------------------

		} catch (ArrayRuleException e) {
			log3.error(e, Constante.ERR_LOGICA_NEGOCIO, "");
			List lstDocumentoBen = FacadeFactory.getGeneralFacade()
					.getComboDetalleHlp(Constante.COD_HLP_DOCU_GIROS);
			request.setAttribute("lstDocumentoBen", lstDocumentoBen);
			List listaAfiliaciones = FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.TELEGIRO_NACIONAL,
							usuario.getTarjeta().getNumero(), "00004");
			/*listaAfiliaciones.addAll(FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.TELEGIRO_INTERNACIONAL,
							usuario.getTarjeta().getNumero(), "00004"));*/
			request.setAttribute("listaAfiliaciones", listaAfiliaciones);
			request.setAttribute("afiliacion", request.getSession().getAttribute("afiliacion"));

			request.setAttribute("cuenta", request.getSession().getAttribute("cuenta"));
			BNAplicacion aplicacion = BNAplicacion.getInstance();
			request.getSession().setAttribute("mensajeCondicion",((Vector) aplicacion.getMensajePorCodigo("TL01", "00002")).elementAt(2).toString().trim());

			e.setForward("verTelegiro");
			throw e;
		}

		return mapping.findForward("confirmarTelegiro");
	}

	public ActionForward telegirar(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {

		TokenSmsRequest tokenSms = new TokenSmsRequest();

		ActionForward forward = new ActionForward();
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		
		String transaccion = request.getParameter("transaccion");

		ParametrosTDC param = (ParametrosTDC) request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION, param);

		//ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		//request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION, registro);

		CuentaImpl cuenta = (CuentaImpl)request.getSession().getAttribute("cuenta");
		
		try {
			Afiliacion afiliacion = (Afiliacion) request.getSession().getAttribute("afiliacion");
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
			afiliacion.setTipoDocumento(Funciones.lpad(tipoDoc,"0",3));
			afiliacion.setNroDocumento(numDoc);

			ElemenSeguridad resultCoord = null;
			ElemenSeguridad coord = (ElemenSeguridad) request.getSession().getAttribute("resultCoord");
			String pinblock = ObjectUtil.getClaveDesencriptada(request.getParameter("txtCoordenada"), request);
			ParametrosElemSegTDC elementos = null;
			try {

				elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
//				System.out.println("tipoElemento::::" + elementos);

			} catch (ArrayRuleException ar) {
				log3.error(ar, Constante.ERR_SOLICITAR_COORDENADA, "");
				ar.printStackTrace();
				ar.setForward("confirmarTelegiro");
				throw ar;
			}

			MensajesTDC resultado = new MensajesTDC();
			try {
				TelegiroImpl tg = (TelegiroImpl) request.getSession().getAttribute(ConstanteSesion.PAGO_TELEGIRO);

				if (elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)) {
					resultado = SolicitarServiciosTDC.validarTDC(param, coord,pinblock);
				} else if (elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS)) {
					resultado = SolicitarServiciosTDC.validarTokens(param,coord, pinblock);
				} else if (elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {
					
					tokenSms.setCodCliente(afiliacion.getCodCliente());
					tokenSms.setTipoDocumento(afiliacion.getTipoDocumento());
					tokenSms.setNroDocumento(afiliacion.getNroDocumento());
					tokenSms.setCodeSMS(pinblock);
					tokenSms.setTypeTrans(Constante.TIP_OPER_GIRO);
					tokenSms.setTypeOp("FIN");
					tokenSms.setAmount(tg.getImporte());
					tokenSms.setTypeCurrency(cuenta.getMonedaProducto().equals("USD")?"USD":"PEN");
					
					tokenSms.setCodCliDestinatario(afiliacion.getNumeroServicio());
					tokenSms.setNomCliDestinatario(afiliacion.getBeneficiario());
					
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

				// --------------------------------------------------------

				Telegiro tele = FacadeFactory.getTelegiroFacade().pagarTelegiro(transaccion, tg, request.getRemoteAddr(), usuario, request);
				
				request.getSession().setAttribute(ConstanteSesion.PAGO_TELEGIRO, tele);
				
				Map map = UtilAction.cargarVar(request, request.getSession().getAttribute(ConstanteSesion.PAGO_TELEGIRO));
				
				OperacionImpl.setVariables(map);
				
				
				if(usuario.getNotificacion().getEstadoNotificacion().equals("1")){
					ServiciosNotificacionPrincipal.enviarNotificacion(
							usuario,
							usuario.getNombre(), 
							tele.getCuenta().getSimboloMonedaProducto(), 
							tg.getImporte(), 
							usuario.getTipoCambio().getVenta(), 
							Constante.NOTI_EMISION_GIROS , 
							usuario.getNotificacion()
							);
				}
				
//				if (registro != null) {
//					
//				}

				if (tg.getCuenta().getMonedaProducto().equals(Constante.MONEDA_SOL)) {

					if (!tg.getAfiliacion().getCodigoServicio().equals(Constante.DNI)) {
						ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_TELEGIRO_ME, ConstanteSesion.PAGO_TELEGIRO, request);
						// telegirarME no muestra la clave de cobro
						forward = mapping.findForward("telegirarME");
					} else {
						ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_TELEGIRO_MN, ConstanteSesion.PAGO_TELEGIRO, request);
						// telegirarMN si muestra la clave de cobro
						forward = mapping.findForward("telegirarMN");
					}

				} else {
					ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_TELEGIRO_ME, ConstanteSesion.PAGO_TELEGIRO, request);
					
					forward = mapping.findForward("telegirarME");
				}
			} catch (ArrayRuleException e) {
				log3.error(e, Constante.ERR_ERROR_VALIDAR_TDC, "");
				e.printStackTrace();
				TelegiroImpl tel = (TelegiroImpl) request.getSession().getAttribute(ConstanteSesion.PAGO_TELEGIRO);
				request.setAttribute("optTelegiro", tel.getAfiliacion().getFlagRegistro());

				if (elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)) {
					resultCoord = SolicitarServiciosTDC.solicitCoordenada(param);
				} else if (elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS)) {
					resultCoord = SolicitarServiciosTDC.solicitarTokens(param);
				} else if (elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {
					resultCoord = SolicitarServiciosTDC.solicitarTokensSms(param);
				}

				request.getSession().setAttribute("resultCoord", resultCoord);
				e.setForward("confirmarTelegiro");
				throw e;
			}
			// }

		} catch (ArrayRuleException e) {
			log3.error(e, Constante.ERR_LOGICA_NEGOCIO, "");
			e.setForward("confirmarTelegiro");
			throw e;
		}

		TelegiroImpl tel = (TelegiroImpl) request.getSession().getAttribute(ConstanteSesion.PAGO_TELEGIRO);

		if (tel.getAfiliacion().getFlagRegistro().equals(Constante.NUEV_REGISTRO_TRANSAC)) {
			String adicionar = request.getParameter("adicionar");
			
			if (adicionar != null) {
				Afiliacion afiliacion = new AfiliacionImpl();
				afiliacion = tel.getAfiliacion();
				afiliacion.setBeneficiario(request.getParameter("txtNombreBeneficiario").toUpperCase());
				afiliacion.setTipoAfiliacion(Constante.AFI_TELEGIRO);

				try {
					Afiliacion afiliar = FacadeFactory.getAfiliacionFacade().afiliar(usuario, afiliacion);

				} catch (ArrayRuleException e) {
					log3.error(e, Constante.ERR_LOGICA_NEGOCIO, "");
					e.printStackTrace();

				}
			}

		}

		return forward;
	}

	public String NomTipoDocBenef(String tipodoc, String campoasoc) {
		int td = Integer.parseInt(tipodoc);
		List lista = null;
		if (tipodoc != null && campoasoc != null)
			lista = DAOFactory.getGeneraDAO().getComboDetalleHlpHijoMod(
					campoasoc, tipodoc);
		if (lista != null) {
			for (Iterator iter = lista.iterator(); iter.hasNext();) {
				ComboUtil element = (ComboUtil) iter.next();

				if (element.getCodigo().trim().equals(tipodoc + "")) {
					return element.getDescripcion();
				}
			}
		}

		return "";
	}

	public ActionForward generarClaveSms(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		TokenSmsRequest tokenSms = new TokenSmsRequest();
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		// Afiliacion afiliacion = (AfiliacionImpl)request.getSession().getAttribute("afiliacion");
		String monto = (String) request.getSession().getAttribute("monto");
		//Afiliacion datosUsuario = new AfiliacionImpl();
		
		CuentaImpl cuenta = (CuentaImpl)request.getSession().getAttribute("cuenta");

		try {

			Afiliacion afiliacion = (Afiliacion) request.getSession().getAttribute("afiliacion");
			afiliacion.setDescripcionTipoDocumento(NomTipoDocBenef(afiliacion.getCodigoServicio(), Constante.COD_HLP_DET_DOCU_GIRO));
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

			//REALIZAR CONSULTA DE COORDENADAS
			ElemenSeguridad resultCoord = null;
			
			
			if (param != null) {
				resultCoord = new ElemenSeguridad();
				request.getSession().setAttribute("resultCoord", null);

				if (elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {
					
					tokenSms.setCodCliente(usuario.getCodigoCic());
					tokenSms.setTipoDocumento(Funciones.lpad(tipoDoc,"0",3));
					tokenSms.setNroDocumento(numDoc);

					tokenSms.setTypeTrans(Constante.TIP_OPER_GIRO);
					tokenSms.setTypeOp("FIN");
					tokenSms.setAmount(monto);
					tokenSms.setTypeCurrency(cuenta.getMonedaProducto().equals("USD")?"USD":"PEN");
					
					tokenSms.setCodCliDestinatario(afiliacion.getNumeroServicio());
					tokenSms.setNomCliDestinatario(afiliacion.getBeneficiario());

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
			loadMessage(request);
			e.setForward("verMBTransferencia");
			throw e;
		}
		return mapping.findForward("confirmarTelegiro");

	}
	
	public ActionForward reGenerarClaveSms(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		TokenSmsRequest tokenSms = new TokenSmsRequest();
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		// Afiliacion afiliacion = (AfiliacionImpl)request.getSession().getAttribute("afiliacion");
		String monto = (String) request.getSession().getAttribute("monto");
		//Afiliacion datosUsuario = new AfiliacionImpl();
		
		CuentaImpl cuenta = (CuentaImpl)request.getSession().getAttribute("cuenta");

		try {

			Afiliacion afiliacion = (Afiliacion) request.getSession().getAttribute("afiliacion");
			afiliacion.setDescripcionTipoDocumento(NomTipoDocBenef(afiliacion.getCodigoServicio(), Constante.COD_HLP_DET_DOCU_GIRO));
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

			//REALIZAR CONSULTA DE COORDENADAS
			ElemenSeguridad resultCoord = null;
			
			
			if (param != null) {
				resultCoord = new ElemenSeguridad();
				request.getSession().setAttribute("resultCoord", null);

				if (elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)) {
					
					tokenSms.setCodCliente(usuario.getCodigoCic());
					tokenSms.setTipoDocumento(Funciones.lpad(tipoDoc,"0",3));
					tokenSms.setNroDocumento(numDoc);

					tokenSms.setTypeTrans(Constante.TIP_OPER_GIRO);
					tokenSms.setTypeOp("FIN");
					tokenSms.setAmount(monto);
					tokenSms.setTypeCurrency(cuenta.getMonedaProducto().equals("USD")?"USD":"PEN");
					
					tokenSms.setCodCliDestinatario(afiliacion.getNumeroServicio());
					tokenSms.setNomCliDestinatario(afiliacion.getBeneficiario());

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
			loadMessage(request);
			e.setForward("verMBTransferencia");
			throw e;
		}
		return mapping.findForward("confirmarTelegiro");

	}
	
	private void loadMessage(HttpServletRequest request) throws Exception{
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		request.getSession().setAttribute("mensajeAfiliacion",((Vector)aplicacion.getMensajePorCodigo("TB01","AF001")).elementAt(2).toString());
		request.getSession().setAttribute("mensajePantalla",((Vector)aplicacion.getMensajePorCodigo("TB01","00001")).elementAt(2).toString());
		request.getSession().setAttribute("mensajeExitoTranfMB",((Vector)aplicacion.getMensajePorCodigo("TB01","00005")).elementAt(2).toString());
	}

}