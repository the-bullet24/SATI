
package pe.bn.pago.action;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pe.bn.afiliacion.action.DesafCableAction;
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
import pe.bn.notificaciones.model.ParametrosSIAT;
import pe.bn.pago.domain.PagoFacturaWS;
import pe.bn.pago.domain.PagoSedapal;
import pe.bn.pago.domain.PagoTelefono;
import pe.bn.pago.domain.impl.PagoSedapalImpl;
import pe.bn.pago.domain.impl.PagoTelefonoImpl;
import pe.bn.tarjeta.domain.Tarjeta;
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
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.DetalleAyudaDatos;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.CuentaImpl;
import pe.cosapi.domain.impl.DetalleAyudaDatosImpl;
import pe.cosapi.domain.impl.OperacionImpl;

public class PagoTelefonoAction extends GrandActionAbstract {
	BNAplicacion aplicacion =BNAplicacion.getInstance();
	private static LoggerSati log3 = LoggerSati.getInstance(PagoTelefonoAction.class.getName());
	
	public ActionForward iniciar(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		List listaAfiliaciones = null;
		
		String hidServicio = request.getParameter("hidServicio");
		String hidCodEntidad = request.getParameter("hidCodEntidad");
		String hidCodServ	  = request.getParameter("hidCodServ");
		String typeTrans = UtilAction.obtenerTipoTransaccionFinanciera(hidCodServ);
		
//		System.out.println("***hidServicio***: " + hidServicio);
//		System.out.println("***hidCodEntidad***: " + hidCodEntidad);
//		System.out.println("***hidCodServ***: " + hidCodServ);
//		System.out.println("***typeTrans***: " + typeTrans);
		
		String codMsg = "0000"+hidServicio;
		
		
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		//request.getSession().setAttribute("mensajeAfiliacion",((Vector)aplicacion.getMensajePorCodigo("PS00","AF001")).elementAt(2).toString());
		request.getSession().setAttribute("mensajePantalla",((Vector)aplicacion.getMensajePorCodigo("PS00",codMsg)).elementAt(2).toString());
		request.getSession().setAttribute(ConstanteSesion.DES_SUBTITULO1,"NOMBRE");
		request.getSession().setAttribute(ConstanteSesion.DES_SUBTITULO2,"NRO. DE SERVICIO");
		request.getSession().setAttribute("TITULOS",ConstanteSesion.TITULO);
		
		
		if (hidServicio.equals("1")){
			//if(Constante.PAGO_TELEFONO.equals(mapping.getPath())){
				request.getSession().setAttribute("TITULO",Constante.PAGO_TELEFONO_TITULO);
				request.getSession().setAttribute(ConstanteSesion.DES_SERV_TIT, "Teléfonos Afiliados:");
				DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
				List lista0= dHelp_.getListDetalleAyuda(Constante.COD_HLP_DET_LOCAL);
				request.getSession().setAttribute("lstLocalidad", lista0);
				request.getSession().setAttribute("ejemplo",((Vector)aplicacion.getMensajePorCodigo("PS00","ETF01")).elementAt(2).toString());
				request.getSession().setAttribute("ejemplo2",((Vector)aplicacion.getMensajePorCodigo("PS00","EFF01")).elementAt(2).toString());
				request.getSession().setAttribute("lstServicio", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.COD_HLP_PGO_TELEFONO));//carga las opciones del cmbo de servicios
				request.getSession().setAttribute("mensajeAfiliacion",((Vector)aplicacion.getMensajePorCodigo("PS00","AF001")).elementAt(2).toString());
				
				listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.PAGO_TEL,usuario.getTarjeta().getNumero(), "00436");
		//	}
		}
		else if (hidServicio.equals("3")){
			//if(Constante.PAGO_TELEFONO.equals(mapping.getPath())){
				request.getSession().setAttribute("TITULO",Constante.PAGO_MOVISTAR_TITULO);
				request.getSession().setAttribute(ConstanteSesion.DES_SERV_TIT, "Celulares Afiliados:");
				request.getSession().setAttribute("lstServicio", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.COD_HLP_PGO_MOVISTAR));//carga las opciones del cmbo de servicios
				request.getSession().setAttribute(ConstanteSesion.DES_SERV_AFI, "Afiliación de Celulares");
				request.getSession().setAttribute("ejemplo",((Vector)aplicacion.getMensajePorCodigo("PS00","ETC01")).elementAt(2).toString());
				request.getSession().setAttribute("mensajeAfiliacion",((Vector)aplicacion.getMensajePorCodigo("PS00","AF002")).elementAt(2).toString());
			
				listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.PAGO_MOV,usuario.getTarjeta().getNumero(), "00437");
			//}
		}
		else if (hidServicio.equals("4")){
			//if(Constante.PAGO_TELEFONO.equals(mapping.getPath())){
				request.getSession().setAttribute("TITULO",Constante.PAGO_TERRA_TITULO);
				request.getSession().setAttribute(ConstanteSesion.DES_SERV_TIT, "Abonados Afiliados:");
				request.getSession().setAttribute("lstServicio", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.COD_HLP_PGO_TERRA));
				request.getSession().setAttribute("mensajeAfiliacion",((Vector)aplicacion.getMensajePorCodigo("PS00","AF005")).elementAt(2).toString());
				request.getSession().setAttribute("ejemplo",((Vector)aplicacion.getMensajePorCodigo("PS00","ETE01")).elementAt(2).toString());
				listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.PAGO_TER,usuario.getTarjeta().getNumero(), "00440");
			//}
		}
		else if (hidServicio.equals("7")){
			//if(Constante.PAGO_TELEFONO.equals(mapping.getPath())){
				request.getSession().setAttribute("TITULO",Constante.PAGO_CMAGICO_TITULO);
				request.getSession().setAttribute(ConstanteSesion.DES_SERV_TIT, "Códigos de Cliente Afiliados:");
				DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
				List lista0= dHelp_.getListDetalleAyuda(Constante.COD_HLP_DET_LOCAL);
				request.getSession().setAttribute("lstLocalidad", lista0);
				request.getSession().setAttribute("ejemplo",((Vector)aplicacion.getMensajePorCodigo("PS00","ECM01")).elementAt(2).toString());
				request.getSession().setAttribute("lstServicio", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.COD_HLP_PGO_CMAGICO));
				request.getSession().setAttribute("mensajeAfiliacion",((Vector)aplicacion.getMensajePorCodigo("PS00","AF003")).elementAt(2).toString());
			
				listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.PAGO_CMA,usuario.getTarjeta().getNumero(), "00439");
			//}
		}
		//request.getSession().setAttribute("TITULOS",ConstanteSesion.TITULO);
		request.setAttribute("listaAfiliaciones",listaAfiliaciones);
		request.setAttribute("hidServicio",hidServicio);
		request.getSession().setAttribute("hidServicio",hidServicio);
		request.getSession().setAttribute("cdSsPT",hidServicio);
		request.setAttribute("hidCodEntidad",hidCodEntidad);
		request.setAttribute("hidCodServ",hidCodServ);
		request.getSession().setAttribute("hidCodEntidad",hidCodEntidad);
		request.getSession().setAttribute("hidCodServ",hidCodServ);
		request.getSession().setAttribute("typeTrans",typeTrans);
		FacadeFactory.getGeneralFacade().actualizarCuentas("LG02",usuario,request.getRemoteAddr());
		
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);

		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		
		ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
 	
		ParametrosElemSegTDC elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
		request.getSession().setAttribute("tipoElemento",elementos );
		
		if(elementos.getTipoElementoSeguridad().equals("5")){
			System.out.println("Token Fisico");
			usuario.getClaveDinamica().setTipoElementoSeguridad(elementos.getTipoElementoSeguridad());
			usuario.getClaveDinamica().setTipoElementoSeguridadFinal("Token Fisico");
			
		}else if(elementos.getTipoElementoSeguridad().equals("6")){
			System.out.println("Clave Dinamica Digital");
			usuario.getClaveDinamica().setTipoElementoSeguridad(elementos.getTipoElementoSeguridad());
			usuario.getClaveDinamica().setTipoElementoSeguridadFinal("Clave Dinamica Digital");
		}
		
		return mapping.findForward("iniciar");
	}
	
	public ActionForward verPagoTelefono(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception{
		
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
	
		Tarjeta tarjeta = usuario.getTarjeta(); 
		//String pago=request.getParameter("cmbPagoTelefono");
		String cuenta =request.getParameter("cmbCuenta");
		
		String hidCodEntidad = (String) request.getSession().getAttribute("hidCodEntidad");
		String hidCodServ = (String) request.getSession().getAttribute("hidCodServ");
		String hidServicio = (String)request.getSession().getAttribute("cdSsPT");
		Afiliacion afiliacionDatos = new AfiliacionImpl();
		
		request.setAttribute("hidCodEntidad",hidCodEntidad);
		request.setAttribute("hidCodServ",hidCodServ);
		request.getSession().setAttribute("hidCodEntidad",hidCodEntidad);
		request.getSession().setAttribute("hidCodServ",hidCodServ);
	
		try{	
			
			if(request.getParameter("optCuenta").equals(Constante.NUEV_REGISTRO_TRANSAC)){
				List listaAfiliados = null;
			
				
				if (hidServicio.equals("1")){
					afiliacionDatos.setTipoAfiliacion(Constante.PAGO_TEL);
					afiliacionDatos.setCodigoLocalidad(request.getParameter("cmbLocalidad").substring(0,2));
					afiliacionDatos.setNumeroServicio(request.getParameter("cmbLocalidad").substring(2)+"-"+request.getParameter("txtNumServicio"));
					afiliacionDatos.setNroTarjeta(tarjeta.getNumero());
					afiliacionDatos.setCodigoServicio(request.getParameter("cmbServicio").trim());
					
					listaAfiliados =  FacadeFactory.getAfiliacionFacade().getAfiliaExiste(Constante.PAGO_TEL,usuario.getTarjeta().getNumero(), Constante.COD_HLP_PGO_TELEFONO, afiliacionDatos.getNumeroServicio());
					
					
					if (listaAfiliados.size() > 0){
						request.getSession().setAttribute("codServicioDet",Constante.COD_HLP_PGO_TELEFONO);
						
						throw new ArrayRuleException(ConstanteError.GENERICO,"El número de servicio: " + afiliacionDatos.getNumeroServicio() +" ya se encuentra como frecuente.");
					}
					
					request.setAttribute("cmbLocalidad", request.getParameter("cmbLocalidad"));
					
				}
				else if (hidServicio.equals("3")){
			
					afiliacionDatos.setTipoAfiliacion(Constante.PAGO_MOV);
					afiliacionDatos.setCodigoLocalidad("00");
					afiliacionDatos.setNumeroServicio(request.getParameter("txtNumServicio").trim());
					afiliacionDatos.setNroTarjeta(tarjeta.getNumero());
					afiliacionDatos.setCodigoServicio(request.getParameter("cmbServicio"));
					request.getSession().setAttribute("cmbLocalidad", "");
					//Valida si existe
					listaAfiliados =  FacadeFactory.getAfiliacionFacade().getAfiliaExiste(Constante.PAGO_MOV,usuario.getTarjeta().getNumero(), Constante.COD_HLP_PGO_MOVISTAR, afiliacionDatos.getNumeroServicio());
					
					
					if (listaAfiliados.size() > 0){
						request.getSession().setAttribute("codServicioDet",Constante.COD_HLP_PGO_MOVISTAR);
						throw new ArrayRuleException(ConstanteError.GENERICO,"El número de servicio: " + afiliacionDatos.getNumeroServicio() +" ya se encuentra como frecuente.");
					}
				}
				
				else if (hidServicio.equals("4")){
					
					afiliacionDatos.setTipoAfiliacion(Constante.PAGO_TER);
					afiliacionDatos.setCodigoLocalidad("00");
					afiliacionDatos.setNumeroServicio(request.getParameter("txtNumServicio").trim());
					afiliacionDatos.setNroTarjeta(tarjeta.getNumero());
					afiliacionDatos.setCodigoServicio(request.getParameter("cmbServicio"));
					request.getSession().setAttribute("cmbLocalidad", "");
					
					//Valida si existe
									
					listaAfiliados =  FacadeFactory.getAfiliacionFacade().getAfiliaExiste(Constante.PAGO_TER,usuario.getTarjeta().getNumero(), Constante.COD_HLP_PGO_TERRA, afiliacionDatos.getNumeroServicio());
					
					if (listaAfiliados.size() > 0){
						throw new ArrayRuleException(ConstanteError.GENERICO,"El número de servicio: " + afiliacionDatos.getNumeroServicio() +" ya se encuentra como frecuente.");
					}
			
				}
				
				
				else if (hidServicio.equals("7")){
					
					afiliacionDatos.setTipoAfiliacion(Constante.PAGO_CMA);
					afiliacionDatos.setCodigoLocalidad(request.getParameter("cmbLocalidad").substring(0,2));
					afiliacionDatos.setNumeroServicio(request.getParameter("txtNumServicio").trim());
					afiliacionDatos.setNroTarjeta(tarjeta.getNumero());
					afiliacionDatos.setCodigoServicio(request.getParameter("cmbServicio"));
					request.getSession().setAttribute("cmbLocalidad", request.getParameter("cmbLocalidad"));
					//Valida si existe
					listaAfiliados =  FacadeFactory.getAfiliacionFacade().getAfiliaExiste(Constante.PAGO_CMA,usuario.getTarjeta().getNumero(), Constante.COD_HLP_PGO_CMAGICO, afiliacionDatos.getNumeroServicio());
					
					if (listaAfiliados.size() > 0){
						
						throw new ArrayRuleException(ConstanteError.GENERICO,"El número de servicio: " + afiliacionDatos.getNumeroServicio() +" ya se encuentra como frecuente.");
					}
						
						
						
					}
				
				afiliacionDatos.setFlagRegistro(Constante.NUEV_REGISTRO_TRANSAC);	
				request.getSession().setAttribute("cmbPagoTelefono","");
				request.getSession().setAttribute("afiliacion",afiliacionDatos);
			
				}
			}
			catch (Exception e) {
				e.printStackTrace();
				String codServicio= (String)request.getSession().getAttribute("cdSsPT");
				List listaAfiliaciones 	= null;
				if(codServicio.equals("4")){
					listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.PAGO_TER,usuario.getTarjeta().getNumero(), "00440");
					}
					else if(codServicio.equals("3")){
						listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.PAGO_MOV,usuario.getTarjeta().getNumero(), "00437");
					}
					else if(codServicio.equals("7")){
						listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.PAGO_CMA,usuario.getTarjeta().getNumero(), "00439");
					}
					else if(codServicio.equals("1")){
						listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.PAGO_TEL,usuario.getTarjeta().getNumero(), "00436");
					}
					request.setAttribute("listaAfiliaciones",listaAfiliaciones);
				throw e;
			}
			
			try{
			if(request.getParameter("optCuenta").equals(Constante.FREC_REGISTRO_TRANSAC)){
				String[] id = null;
				
				if(request.getParameter("cmbPagoTelefono")!=null){
					id = ObjectUtil.getArrayStrings(request.getParameter("cmbPagoTelefono"),"-");
					
					request.getSession().setAttribute("cmbPagoTelefono",request.getParameter("cmbPagoTelefono"));
				}else{
					id = ObjectUtil.getArrayStrings(request.getSession().getAttribute("cmbPagoTelefono").toString(),"-");	
				}
				request.getSession().setAttribute("afiliacion",FacadeFactory.getAfiliacionFacade().getAfiliacion(id[0],id[1],new Long(id[2]), "00436"));
				afiliacionDatos = (Afiliacion)request.getSession().getAttribute("afiliacion");
				request.getSession().setAttribute("CORREO_AFILIADO",afiliacionDatos.getEmail());
				afiliacionDatos.setFlagRegistro(Constante.FREC_REGISTRO_TRANSAC);
				request.setAttribute("cmbLocalidad", "");
			}
			
			String localidad = "";
			String ciudad = "";
			
						
			
			if(afiliacionDatos.getCodigoLocalidad().equals("00")){
				ciudad = afiliacionDatos.getNumeroServicio().substring(0,1);
			}
			else{
				ciudad = afiliacionDatos.getNumeroServicio().substring(0,2);
			}
			
			localidad = FacadeFactory.getGeneralFacade().getDescripcionLocalidad(afiliacionDatos.getCodigoLocalidad(),ciudad);
			
			//DetalleAyudaDatos bean = new DetalleAyudaDatosImpl();
			request.getSession().setAttribute("cod_local_telefono",localidad);
		
//			if(cuenta!=null){
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
					cta	= (CuentaImpl)usuario.getMapCuentas().get(cuenta);
				}else{
					for(int i=0; i<x.size();i++){
						cta=(CuentaImpl)x.get(i);
						if(!cta.getTipoProducto().equals("44")&& cta.getNumeroProducto().equals(cuenta)){
							break;
							
						}	
					}
				
				}
				request.getSession().setAttribute("cuenta",cta);
				request.getSession().setAttribute("ctaSession",cta);
				request.getSession().setAttribute("afiliacion",afiliacionDatos);
				request.getSession().setAttribute(ConstanteSesion.CONSULTA,FacadeFactory.getPagoFacade().getReciboTelefono(request.getParameter("transaccion"), cta, afiliacionDatos, usuario, request.getRemoteAddr()));
				request.getSession().setAttribute("cmbCuenta",cuenta);
//			}else{
//				request.setAttribute("cuenta", (Cuenta)request.getSession().getAttribute("ctaSession"));
//				request.getSession().setAttribute(ConstanteSesion.CONSULTA,FacadeFactory.getPagoFacade().getReciboTelefono(request.getParameter("transaccion"),  (Cuenta)request.getSession().getAttribute("ctaSession"), afiliacionDatos, usuario, request.getRemoteAddr()));
//			}
		  
				
		}
		catch(ArrayRuleException e){
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			System.out.println("!!!!!!!Entro al catch del metodo verpagotelefono");
			e.printStackTrace();
			String codServicio= (String)request.getSession().getAttribute("cdSsPT");
			List listaAfiliaciones 	= null;
			
			if(codServicio.equals("4")){
			listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.PAGO_TER,usuario.getTarjeta().getNumero(), "00440");
			}
			else if(codServicio.equals("3")){
				listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.PAGO_MOV,usuario.getTarjeta().getNumero(), "00437");
			}
			else if(codServicio.equals("7")){
				listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.PAGO_CMA,usuario.getTarjeta().getNumero(), "00439");
			}
			else if(codServicio.equals("1")){
				listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.PAGO_TEL,usuario.getTarjeta().getNumero(), "00436");
			}
			request.setAttribute("listaAfiliaciones",listaAfiliaciones);
			e.setForward("iniciar");
			throw e;
		}

		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);

		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		
		ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
		
		return mapping.findForward("verPagoTelef");
	}
	
	public ActionForward confirmarPagoTelefono(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		///Afiliacion datosUsuario = FacadeFactory.getGeneralFacade().obtenerDatosPersonalesAfiliacion(usuario);
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
		
		String[] id = ObjectUtil.getArrayStrings(request.getParameter("optSecuencia"),"|");
		request.getSession().setAttribute("optSecuencia",request.getParameter("optSecuencia"));
		Afiliacion afiliacion = (AfiliacionImpl)request.getSession().getAttribute("afiliacion");
		
		request.getSession().setAttribute("afiliacion",afiliacion);
		request.getSession().setAttribute("nomcliente",request.getParameter("nomcliente"));
		
		String hidCodEntidad = request.getSession().getAttribute("hidCodEntidad").toString();
		String hidCodServ = request.getSession().getAttribute("hidCodServ").toString();
		//Variable que se obtiene teniendo como base el grupo y la transaccion del servicio.
		//String typetrans = request.getSession().getAttribute("typetrans").toString();
		
		request.setAttribute("hidCodEntidad",hidCodEntidad);
		request.setAttribute("hidCodServ",hidCodServ);
		request.getSession().setAttribute("hidCodEntidad",hidCodEntidad);
		request.getSession().setAttribute("hidCodServ",hidCodServ);
		
		Cuenta cuenta = (Cuenta)request.getSession().getAttribute("ctaSession");
		long time = new SimpleDateFormat("dd/MM/yyyy").parse(id[1]).getTime();
		Timestamp fecIng = new Timestamp(time);
		BigDecimal nMonto;
		
		nMonto = new BigDecimal(ObjectUtil.replaceChar(id[2].toString(),',',""));
		
		String importe = nMonto.toString();
		
		request.getSession().setAttribute("importe",importe);
		
		//DetalleAyudaDatos bean = new DetalleAyudaDatosImpl();
		request.getSession().setAttribute("cod_local_telefono",request.getSession().getAttribute("cod_local_telefono"));
		
		request.setAttribute("cmbLocalidad", request.getParameter("cmbLocalidad"));
		request.getSession().setAttribute("cmbCuenta",request.getParameter("cmbCuenta"));
		//if(Constante.VER_LOG) System.out.println("PagoTelefonoAction / confirmarPagoTelefono");
		
		try{
			request.getSession().setAttribute(ConstanteSesion.CONSULTA,FacadeFactory.getPagoFacade().getConfirmaTelefono( usuario,cuenta, afiliacion, id[0], fecIng, nMonto, id[3], id[4], id[5], ((pe.cosapi.domain.impl.UsuarioImpl)request.getSession().getAttribute(pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION)).getTipoCambio()));
			
//			PagoTelefonoImpl prueba = (PagoTelefonoImpl)request.getSession().getAttribute(ConstanteSesion.CONSULTA);
			
			
		}
		catch (ArrayRuleException e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			String codServicio= (String)request.getSession().getAttribute("hidCodServ");
			List listaAfiliaciones 	= null;
			
			if(codServicio.equals("4")){
			listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.PAGO_TER,usuario.getTarjeta().getNumero(), "00440");
			}
			else if(codServicio.equals("3")){
				listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.PAGO_MOV,usuario.getTarjeta().getNumero(), "00437");
			}
			else if(codServicio.equals("7")){
				listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.PAGO_CMA,usuario.getTarjeta().getNumero(), "00439");
			}
			else if(codServicio.equals("1")){
				listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.PAGO_TEL,usuario.getTarjeta().getNumero(), "00436");
			}
			request.setAttribute("listaAfiliaciones",listaAfiliaciones);
			e.setForward("verPagoTelefono");
			throw e;
		}
		finally{
			request.setAttribute("cuenta", (Cuenta)request.getSession().getAttribute("ctaSession"));
			request.getSession().setAttribute("cmbCuenta",request.getParameter("cmbCuenta"));
			
		}
		
		ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
		
		//REALIZAR CONSULTA DE COORDENADAS
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
			ar.printStackTrace();
			String codServicio= (String)request.getSession().getAttribute("hidCodServ");
			List listaAfiliaciones 	= null;
			
			if(codServicio.equals("4")){
			listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.PAGO_TER,usuario.getTarjeta().getNumero(), "00440");
			}
			else if(codServicio.equals("3")){
				listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.PAGO_MOV,usuario.getTarjeta().getNumero(), "00437");
			}
			else if(codServicio.equals("7")){
				listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.PAGO_CMA,usuario.getTarjeta().getNumero(), "00439");
			}
			else if(codServicio.equals("1")){
				listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.PAGO_TEL,usuario.getTarjeta().getNumero(), "00436");
			}
			request.setAttribute("listaAfiliaciones",listaAfiliaciones);
			ar.setForward("iniciar");
			throw ar;
		}
		
		ElemenSeguridad resultCoord = null;
		
		if(param!= null){
			resultCoord = new ElemenSeguridad();
			try{
				request.getSession().setAttribute("resultCoord",null );
				
				if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
					resultCoord = SolicitarServiciosTDC.solicitCoordenada(param);
				} else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS)){
					resultCoord=SolicitarServiciosTDC.solicitarTokens(param);
				} else if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS_SMS)){
					generarClaveSms(mapping, form, request, response);
				}	
				
			}catch(ArrayRuleException ar){
				log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
				ar.printStackTrace();
				
				request.setAttribute("mensajeCondicion",((Vector)aplicacion.getMensajePorCodigo("TB05","00002")).elementAt(2).toString());
				request.setAttribute("mensajeLimite",((Vector)aplicacion.getMensajePorCodigo("TB05","00004")).elementAt(2).toString());
				request.setAttribute("listaMonedas",FacadeFactory.getGeneralFacade().getComboDetalleHlp(""));
				String codServicio= (String)request.getSession().getAttribute("hidCodServ");
				List listaAfiliaciones 	= null;
				
				if(codServicio.equals("4")){
				listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.PAGO_TER,usuario.getTarjeta().getNumero(), "00440");
				}
				else if(codServicio.equals("3")){
					listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.PAGO_MOV,usuario.getTarjeta().getNumero(), "00437");
				}
				else if(codServicio.equals("7")){
					listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.PAGO_CMA,usuario.getTarjeta().getNumero(), "00439");
				}
				else if(codServicio.equals("1")){
					listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaciones(Constante.PAGO_TEL,usuario.getTarjeta().getNumero(), "00436");
				}
				request.setAttribute("listaAfiliaciones",listaAfiliaciones);
				ar.setForward("iniciar");
				throw ar;
			}
			request.getSession().setAttribute("resultCoord",resultCoord );
		}else{
			throw new ArrayRuleException(ConstanteError.GENERICO,Constante.MENSAJE_EXISTENCIA_MEDIO_AUTENTICACIÓN);
		}
		
		return mapping.findForward("confirmarPagoTelefono");
	}
	
	public ActionForward pagarReciboTelefono(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception{
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
		
		String[] id = ObjectUtil.getArrayStrings(request.getParameter("txtData"),"-");
		Afiliacion afiliacion = (AfiliacionImpl)request.getSession().getAttribute("afiliacion");
		
		String servicio=request.getParameter("codservicio");
		String hidCodEntidad = (String) request.getSession().getAttribute("hidCodEntidad");
		String hidCodServ = (String) request.getSession().getAttribute("hidCodServ");
		
		String typeTrans = Constante.TIP_OPER_PAGO_TELEF_FIJA; //4 TELEF: FIJA
		
		request.getSession().setAttribute("afiliacion",afiliacion);
		request.getSession().setAttribute("empresa",ConstanteSesion.TIT_EMPRESA);
		
		if(servicio.equals("Y")){
			request.getSession().setAttribute("servicio",ConstanteSesion.TIT_FONOFACIL);
		}
		else if(servicio.equals("T")){
			request.getSession().setAttribute("servicio",ConstanteSesion.TIT_TEL_FIJA);
			typeTrans = Constante.TIP_OPER_PAGO_TELEF_FIJA;
		}
		else if(servicio.equals("C")){
			request.getSession().setAttribute("servicio",ConstanteSesion.TIT_TEL_CELULAR);
			typeTrans = Constante.TIP_OPER_PAGO_TELEF_MOVIL;
		}
		else if(servicio.equals("L")){
			request.getSession().setAttribute("servicio",ConstanteSesion.TIT_CABLE_MAGICO);
			typeTrans = Constante.TIP_OPER_PAGO_CABLE;
		}
		else if(servicio.equals("U")){
			request.getSession().setAttribute("servicio",ConstanteSesion.TIT_TERRA);
		}
		
		//DetalleAyudaDatos bean = new DetalleAyudaDatosImpl();
		
		request.getSession().setAttribute("nomlocal",request.getSession().getAttribute("cod_local_telefono"));
		request.getSession().setAttribute("fecpago",pe.cosapi.common.ObjectUtil.getFechaActual());
		request.getSession().setAttribute("horpago",pe.cosapi.common.ObjectUtil.getHHMMSSformateado());
		request.getSession().setAttribute("nomcliente",request.getParameter("nomcliente"));
		request.getSession().setAttribute("numrecibo",request.getParameter("numrecibo"));
		request.getSession().setAttribute("importe",request.getParameter("importe"));
		request.getSession().setAttribute("fecemision",request.getParameter("fecemision"));
		request.getSession().setAttribute("abonado",afiliacion.getNumeroServicio());
		request.getSession().setAttribute("origen",request.getParameter("origen"));
		String nomabonado=request.getParameter("nomcliente");
		Cuenta cuenta;
		if (request.getParameter("cmbCuenta")!=null){
			cuenta =  (Cuenta)request.getSession().getAttribute("ctaSession");
		}else{
			cuenta =  (Cuenta)request.getSession().getAttribute("ctaSession");
		}
			
		//String transaccion = request.getParameter("transaccion");
		
		request.getSession().setAttribute("txtData",id);
		request.getSession().setAttribute("cmbCuenta",cuenta);
		
		PagoTelefonoImpl pagotelefonoimpl = new PagoTelefonoImpl();
		
		pagotelefonoimpl.setCodServEnt(hidCodServ);
		pagotelefonoimpl.setCodEntidad(hidCodEntidad);
		
		//String clave = SeguridadUtil.getClaveDesencriptada(request.getParameter("txtNumClave"),request);
		
		String nMonto = ObjectUtil.formatearMontoTrama(id[0]);
		
		String dia  = id[1].substring(0,2);
	    String mes  = id[1].substring(3,5);
		String anio = id[1].substring(6,10);
		String fecha = anio+mes+dia; 
		
		String codMoneda = id[2];
		String codSeccion = id[3];
		String numeroRecibo = id[4];
		
		String codTransaccion = "PS01";
		pe.cosapi.domain.TipoCambio tipo = ((pe.cosapi.domain.impl.UsuarioImpl)request.getSession().getAttribute(pe.cosapi.common.ConstanteSesion.USUARIO_EN_SESION)).getTipoCambio();
		if (codMoneda.equals(Constante.COD_MONEDA_SOL) && cuenta.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_MN)){
				codTransaccion = "PS01";
				pagotelefonoimpl.setImporteSol(new BigDecimal(0.00));
		}
		else if (codMoneda.equals(Constante.COD_MONEDA_SOL) && cuenta.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_ME)){
				codTransaccion = "PS02";
				pagotelefonoimpl.setImporteSol(new BigDecimal(0.00));
				pagotelefonoimpl.setImporteDol(new BigDecimal(0.00));
		}
		else if (codMoneda.equals(Constante.COD_MONEDA_SOL) && cuenta.getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_MN)){
				codTransaccion = "PS03";
				pagotelefonoimpl.setImporteSol(new BigDecimal(0.00));
		}
		else if (codMoneda.equals(Constante.COD_MONEDA_DOL) && cuenta.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_ME)){
				codTransaccion = "PS04";
				pagotelefonoimpl.setImporteDol(new BigDecimal(0.00));
		}
		else if (codMoneda.equals(Constante.COD_MONEDA_DOL) && cuenta.getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_ME)){
				pagotelefonoimpl.setImporteDol(new BigDecimal(0.00));
				codTransaccion = "PS05";
		}
		else if (codMoneda.equals(Constante.COD_MONEDA_DOL) && cuenta.getTipoProducto().equals(Constante.COD_CUENTA_AHORROS_MN)){
				pagotelefonoimpl.setImporteSol(new BigDecimal(0.00));
				pagotelefonoimpl.setImporteDol(new BigDecimal(0.00));
				codTransaccion = "PS06";
		}
		else if (codMoneda.equals(Constante.COD_MONEDA_DOL) && cuenta.getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_MN)){
				pagotelefonoimpl.setImporteSol(new BigDecimal(0.00));
				pagotelefonoimpl.setImporteDol(new BigDecimal(0.00));
				codTransaccion = "PS07";
		}
		else if (codMoneda.equals(Constante.COD_MONEDA_SOL) && cuenta.getTipoProducto().equals(Constante.COD_CUENTA_CORRIENTE_ME)){
				pagotelefonoimpl.setImporteSol(new BigDecimal(0.00));
				pagotelefonoimpl.setImporteDol(new BigDecimal(0.00));
				codTransaccion = "PS08";
		}
		
		ElemenSeguridad resultCoord = null;
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);
		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);
		
		ParametrosSIAT registro = (ParametrosSIAT) request.getSession().getAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION);
		request.getSession().setAttribute(ConstanteSesion.REGISTRO_NOTIFICACION_SESION,registro);
		
		ElemenSeguridad coord = (ElemenSeguridad)request.getSession().getAttribute("resultCoord");
		ParametrosElemSegTDC elementos = null;
		try{
			String localidad = (String)request.getSession().getAttribute("cod_local_telefono");
			String montoformat=request.getParameter("importe");
			String ctaorigen=request.getParameter("origen");
			String empresa =(String )request.getSession().getAttribute("empresa");
			String servicioTelef =(String )request.getSession().getAttribute("servicio");
		
			PagoTelefonoImpl pagoTelefonoImpl = new PagoTelefonoImpl();
			
		
			String pinblock = ObjectUtil.getClaveDesencriptada(request.getParameter("txtCoordenada"),request);			
			
			try{
				elementos = (ParametrosElemSegTDC) request.getSession().getAttribute("tipoElemento");
				
				if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TOKENS) ||
				   elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
					elementos = SolicitarServiciosTDC.verificarTipoElemento(param,usuario);
				}	
			}
			catch (ArrayRuleException ar) {
				log3.error(ar,Constante.ERR_SOLICITAR_COORDENADA,"");
				ar.printStackTrace();
				ar.setForward("verPagoTelef");
				throw ar;
			}

			
			// Código Nuevo para TDC
			MensajesTDC resultado = new MensajesTDC();
			if(param!= null && coord!=null ){
				try{
					if(elementos.getTipoElementoSeguridad().equals(Constante.CODIGO_TIPO_ELEM_TDC)){
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
//						String nameClient = StringUtils.isNotEmpty(datosUsuario.getBeneficiario())?datosUsuario.getBeneficiario():LoadDataProperties.getInstance().getValue("bn.name.cliente");
						
						TokenSmsRequest tokenSms = new TokenSmsRequest();
						
						tokenSms.setCodeSMS(pinblock);
						tokenSms.setCodCliente(usuario.getCodigoCic());
						tokenSms.setTipoDocumento(documentType);
						tokenSms.setNroDocumento(nroDoc);
						tokenSms.setTypeTrans(typeTrans);//4 TELEF: FIJA
						tokenSms.setTypeOp(Constante.COD_TRANSACCION_FINANCIERA);//FIN
						tokenSms.setTypeCurrency(Constante.MONEDA_SOL_ISO);//PEN
						tokenSms.setAmount(nMonto);
						tokenSms.setMsgClient("Validacion de Clave Dinamica Digital");
						tokenSms.setNomCliDestinatario(nomabonado);
						tokenSms.setCodCliDestinatario(afiliacion.getNumeroServicio());	
						
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
					
					pagoTelefonoImpl = (PagoTelefonoImpl)FacadeFactory.getPagoFacade().getPagoTelefono(codTransaccion,cuenta, hidCodServ, hidCodEntidad, afiliacion, numeroRecibo, fecha, nMonto, codMoneda,codSeccion, usuario,nomabonado,localidad,montoformat,ctaorigen,empresa,servicioTelef, request.getRemoteAddr());
					request.getSession().setAttribute(ConstanteSesion.PAGO_TELEFONO,pagoTelefonoImpl);
					
					//if(registro != null  && pagoTelefonoImpl.getSimboloMoneda()!= null){
					if(usuario.getNotificacion().getEstadoNotificacion().equals("1")){
						 ServiciosNotificacionPrincipal.enviarNotificacion(
								 usuario,
								 usuario.getNombre(), 
								 pagoTelefonoImpl.getSimboloMoneda(), 
								 pagoTelefonoImpl.getImporte().toString(),
								 usuario.getTipoCambio().getVenta(), 
								 Constante.NOTI_PAGO_SERVICIOS, 
								 usuario.getNotificacion());
					}
					
					ServicioNotificacionConstancia.enviarNotificacion(Constante.ID_TEMPLATE_SERVICIOS_TELEFONO, ConstanteSesion.PAGO_TELEFONO, request);
				}

				catch (ArrayRuleException e) {
					log3.error(e,Constante.ERR_ERROR_VALIDAR_TDC,"");
					e.printStackTrace();
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
					
					request.getSession().setAttribute("resultCoord",resultCoord );
					e.setForward("confirmarPagoTelefono");
					throw e;
				}
			}
			
			
		}
		catch (ArrayRuleException e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			request.getSession().setAttribute("cod_local_telefono",request.getSession().getAttribute("cod_local_telefono"));
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
			
			request.getSession().setAttribute("resultCoord",resultCoord );
			e.setForward("confirmarPagoTelefono");
			throw e;
		}
		finally{
			request.getSession().setAttribute("cuenta", (Cuenta)request.getSession().getAttribute("ctaSession"));
			request.getSession().setAttribute("cmbCuenta",request.getParameter("cmbCuenta"));
		}
		
		if(afiliacion.getFlagRegistro().equals(Constante.NUEV_REGISTRO_TRANSAC)){
			
			String adicionar = request.getParameter("adicionar");
			if(adicionar!=null){
				afiliacion.setDescripcion(request.getParameter("txtNombreAfil").trim());
								
				if(servicio.equals("Y")){
					afiliacion.setTipoAfiliacion(Constante.PAGO_TEL);
				}
				else if(servicio.equals("T")){
					afiliacion.setTipoAfiliacion(Constante.PAGO_TEL);
				}
				else if(servicio.equals("C")){
					afiliacion.setTipoAfiliacion(Constante.PAGO_MOV);
				}
				else if(servicio.equals("L")){
					afiliacion.setTipoAfiliacion(Constante.PAGO_CMA);
				}
				else if(servicio.equals("U")){
					afiliacion.setTipoAfiliacion(Constante.PAGO_TER);
				}
				
				try {
						Afiliacion afiliar=FacadeFactory.getAfiliacionFacade().afiliar(usuario,afiliacion);
						//request.getSession().setAttribute(ConstanteSesion.AFILIAR,af);
					
				} catch (ArrayRuleException e) {
					log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
					e.printStackTrace();
					//e.setForward("continuarTransferencia");
				}
			}
		
			
		}
		return mapping.findForward("pagarReciboTelefono");
	}
	
	public ActionForward transferir(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		String transaccion = request.getParameter("transaccion");
		
		request.getSession().setAttribute(ConstanteSesion.PAGO_TELEFONO,FacadeFactory.getPagoFacade().pagarPagoTelefono(transaccion,(PagoTelefonoImpl)request.getSession().getAttribute(ConstanteSesion.PAGO_TELEFONO),usuario,request.getRemoteAddr()));
		return mapping.findForward("transferir");
	}
	
	public ActionForward generarClaveSms(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Usuario usuario = (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
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
		
		try {
			//String typetrans = request.getSession().getAttribute("typetrans").toString();
			//PagoSedapalImpl pagoSedapal = (PagoSedapalImpl)request.getSession().getAttribute(ConstanteSesion.PAGO_AGUA);
			Afiliacion afiliacion = (AfiliacionImpl)request.getSession().getAttribute("afiliacion");
			request.getSession().setAttribute("afiliacion",afiliacion);
			String nomCliente = (String)request.getSession().getAttribute("nomcliente");
			
			String servicio = afiliacion.getCodigoServicio();
			String typeTrans = Constante.TIP_OPER_PAGO_TELEF_FIJA; //4 TELEF: FIJA
			
			if(servicio.equals("Y")){
				request.getSession().setAttribute("servicio",ConstanteSesion.TIT_FONOFACIL);
			}
			else if(servicio.equals("T")){
				typeTrans = Constante.TIP_OPER_PAGO_TELEF_FIJA;
			}
			else if(servicio.equals("C")){
				typeTrans = Constante.TIP_OPER_PAGO_TELEF_MOVIL;
			}
			else if(servicio.equals("L")){
				typeTrans = Constante.TIP_OPER_PAGO_CABLE;
			}
			else if(servicio.equals("U")){
				request.getSession().setAttribute("servicio",ConstanteSesion.TIT_TERRA);
			}
			
			//String typeTrans = request.getSession().getAttribute("typeTrans").toString();
						
			String importe = request.getSession().getAttribute("importe").toString();
			
			TokenSmsRequest tokenSms = new TokenSmsRequest();
			String documentType = Funciones.lpad(tipoDoc,"0",3);
			String nroDoc = numDoc;
//			String nameClient = StringUtils.isNotEmpty(datosUsuario.getBeneficiario())?
//					datosUsuario.getBeneficiario():LoadDataProperties.getInstance().getValue("bn.name.cliente");
			
			tokenSms.setCodCliente(usuario.getCodigoCic());
			tokenSms.setNroDocumento(nroDoc);
			tokenSms.setTipoDocumento(documentType); //DNI
			tokenSms.setTypeTrans(typeTrans);
			tokenSms.setTypeOp(Constante.COD_TRANSACCION_FINANCIERA);//FIN						
			tokenSms.setTypeCurrency(Constante.MONEDA_SOL_ISO);//PEN
			tokenSms.setAmount(importe);
			tokenSms.setMsgClient("Generacion de Clave Dinamica Digital");
			tokenSms.setNomCliDestinatario(nomCliente);
			tokenSms.setCodCliDestinatario(afiliacion.getNumeroServicio());	

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
		
		return mapping.findForward("confirmarPagoTelefono");
	}
	
	public ActionForward reGenerarClaveSms(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Usuario usuario = (Usuario)request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
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
		
		try {
			//String typetrans = request.getSession().getAttribute("typetrans").toString();
			//PagoSedapalImpl pagoSedapal = (PagoSedapalImpl)request.getSession().getAttribute(ConstanteSesion.PAGO_AGUA);
			Afiliacion afiliacion = (AfiliacionImpl)request.getSession().getAttribute("afiliacion");
			request.getSession().setAttribute("afiliacion",afiliacion);
			String nomCliente = (String)request.getSession().getAttribute("nomcliente");
			
			
			String typeTrans = request.getSession().getAttribute("typeTrans").toString();
			String importe = request.getSession().getAttribute("importe").toString();

			TokenSmsRequest tokenSms = new TokenSmsRequest();
			String documentType = Funciones.lpad(tipoDoc,"0",3);
			String nroDoc = numDoc;
			//String nameClient = StringUtils.isNotEmpty(datosUsuario.getBeneficiario())?
				//	datosUsuario.getBeneficiario():LoadDataProperties.getInstance().getValue("bn.name.cliente");
			
			tokenSms.setCodCliente(usuario.getCodigoCic());
			tokenSms.setNroDocumento(nroDoc);
			tokenSms.setTipoDocumento(documentType); //DNI
			tokenSms.setTypeTrans(Constante.TIP_OPER_PAGO_TELEF_FIJA);//4 TELEF: FIJA
			tokenSms.setTypeOp(Constante.COD_TRANSACCION_FINANCIERA);//FIN						
			tokenSms.setTypeCurrency(Constante.MONEDA_SOL_ISO);//PEN
			tokenSms.setAmount(importe);
			tokenSms.setMsgClient("Generacion de Clave Dinamica Digital");
			tokenSms.setNomCliDestinatario(nomCliente);
			tokenSms.setCodCliDestinatario(afiliacion.getNumeroServicio());	

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
		
		return mapping.findForward("confirmarPagoTelefono");
	}

}