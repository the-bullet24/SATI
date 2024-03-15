package pe.bn.afiliacion.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.iterators.ArrayListIterator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pe.bn.afiliacion.action.form.AfiliacionPgServiciosForm;
import pe.bn.afiliacion.domain.Afiliacion;
import pe.bn.afiliacion.domain.impl.AfiliacionImpl;
import pe.bn.cldinamica.action.form.ParametrosTDC;
import pe.bn.tarjeta.domain.Tarjeta;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.action.GrandActionAbstract;
import pe.cosapi.common.ArrayRuleException;
import pe.cosapi.common.BNAplicacion;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteError;
import pe.cosapi.common.ConstanteSesion;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.common.SeguridadUtil;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.impl.DetalleAyudaDatosImpl;


public class PagoServiciosAction extends GrandActionAbstract {
	private static LoggerSati log3 = LoggerSati.getInstance(PagoServiciosAction.class.getName());
	
	public ActionForward iniciar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);

		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);


		DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
		AfiliacionPgServiciosForm frm = (AfiliacionPgServiciosForm)form;
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		frm.reset(mapping, request);
		Constante.TIPO_SERVICIO="00441";
		request.getSession().setAttribute("codServicioDet","00441");
		request.getSession().setAttribute("tituloAfiliacion","AFILIACION DE SERVICIOS: AGUA - SEDAPAL");
		request.getSession().setAttribute("mensajeAfiliacion",((Vector)aplicacion.getMensajePorCodigo("AS01","00002")).elementAt(2).toString());
		request.getSession().setAttribute("nomafil","Nro. Suministro");
		request.getSession().setAttribute("lstServicio", FacadeFactory.getGeneralFacade().getComboDetalleAyuda("00441"));
		request.getSession().setAttribute("ejemplo2",((Vector)aplicacion.getMensajePorCodigo("PS00","ESE01")).elementAt(2).toString());
		request.getSession().setAttribute("lstMes", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.COD_HLP_DET_MES));
		FacadeFactory.getGeneralFacade().actualizarCuentas("LG02",usuario,request.getRemoteAddr());
		request.getSession().setAttribute("lstLocalidad", dHelp_.getListDetalleAyuda(Constante.COD_HLP_CIUDAD_SEDAPAL));
		List lista= FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_DET_DOCU_OTRO);
		lista.remove(0);
		request.getSession().setAttribute("lstDocumento",lista);
		return mapping.findForward("inicioAfSedapal");
	}
	public ActionForward inicioTBasica(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);

		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);


		AfiliacionPgServiciosForm frm = (AfiliacionPgServiciosForm)form;
		frm.reset(mapping, request);
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		Constante.TIPO_SERVICIO=Constante.COD_HLP_PGO_TELEFONO;
		request.getSession().setAttribute("mensajeAfiliacion",((Vector)aplicacion.getMensajePorCodigo("AS01","00002")).elementAt(2).toString());
		request.getSession().setAttribute("codServicioDet",Constante.COD_HLP_PGO_TELEFONO);
		request.getSession().setAttribute("tituloAfiliacion","AFILIACION DE SERVICIOS: TELEFONIA FIJA");
		request.getSession().setAttribute("lstServicio", FacadeFactory.getGeneralFacade().getComboDetalleAyuda("00436"));//carga las opciones del cmbo de servicios
		request.getSession().setAttribute("lstMes", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.COD_HLP_DET_MES));
		request.getSession().setAttribute("nomafil","Nro. Teléfono");
		request.getSession().setAttribute("ejemplo",((Vector)aplicacion.getMensajePorCodigo("PS00","ETF01")).elementAt(2).toString());
		request.getSession().setAttribute("ejemplo2",((Vector)aplicacion.getMensajePorCodigo("PS00","EFF01")).elementAt(2).toString());
		FacadeFactory.getGeneralFacade().actualizarCuentas("LG02",usuario,request.getRemoteAddr());
		DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
		List lista0= dHelp_.getListDetalleAyuda(Constante.COD_HLP_DET_LOCAL);
		List temp= new ArrayList();
		//saco el ultimo y lo almaceno en un temporal
		temp.add(0,lista0.get(lista0.size()-1));
		temp.add(0,lista0.get(0));
		//elimino el ultimo alemento q ya alamcene antes en otra lista
		lista0.remove(0);
		lista0.remove(lista0.size()-1);
		temp.addAll(lista0);
		/*for(int i=0;i<lista0.size();i++){
			temp.add(i+1,lista0.get(i));
		}*/
		lista0=temp;
		request.getSession().setAttribute("lstLocalidad", lista0);
		List lista= FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_DET_DOCU_OTRO);
		lista.remove(0);
		request.getSession().setAttribute("lstDocumento",lista);
		return mapping.findForward("inicioTBasica");
	}
	
	public ActionForward inicioTCelular(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);

		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);


		AfiliacionPgServiciosForm frm = (AfiliacionPgServiciosForm)form;
		frm.reset(mapping, request);
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		request.getSession().setAttribute("mensajeAfiliacion",((Vector)aplicacion.getMensajePorCodigo("AS01","00002")).elementAt(2).toString());
		Constante.TIPO_SERVICIO=Constante.COD_HLP_PGO_MOVISTAR;
		request.getSession().setAttribute("codServicioDet",Constante.COD_HLP_PGO_MOVISTAR);
		request.getSession().setAttribute("tituloAfiliacion","AFILIACION DE SERVICIOS: TELEFONIA CELULAR");
		request.getSession().setAttribute("lstServicio", FacadeFactory.getGeneralFacade().getComboDetalleAyuda("00437"));
		request.getSession().setAttribute("nomafil","Nro. Celular");
		request.getSession().setAttribute("lstMes", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.COD_HLP_DET_MES));
		request.getSession().setAttribute("ejemplo",((Vector)aplicacion.getMensajePorCodigo("PS00","ETC01")).elementAt(2).toString());
		List lista= FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_DET_DOCU_OTRO);
		lista.remove(0);
		request.getSession().setAttribute("lstDocumento",lista);
		DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
		List lista0= dHelp_.getListDetalleAyuda(Constante.COD_HLP_DET_LOCAL);
		List temp= new ArrayList();
		//saco el ultimo y lo almaceno en un temporal
		temp.add(0,lista0.get(lista0.size()-1));
		temp.add(0,lista0.get(0));
		//elimino el ultimo alemento q ya alamcene antes en otra lista
		lista0.remove(0);
		lista0.remove(lista0.size()-1);
		temp.addAll(lista0);
		/*for(int i=0;i<lista0.size();i++){
			temp.add(i+1,lista0.get(i));
		}*/
		lista0=temp;
		request.getSession().setAttribute("lstLocalidad", lista0);
		FacadeFactory.getGeneralFacade().actualizarCuentas("LG02",usuario,request.getRemoteAddr());
		return mapping.findForward("inicioTCelular");
	}
	
	public ActionForward inicioTerra(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);

		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);


		AfiliacionPgServiciosForm frm = (AfiliacionPgServiciosForm)form;
		frm.reset(mapping, request);
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		Constante.TIPO_SERVICIO=Constante.COD_HLP_PGO_TERRA;
		request.getSession().setAttribute("mensajeAfiliacion",((Vector)aplicacion.getMensajePorCodigo("AS01","00002")).elementAt(2).toString());
		request.setAttribute("codServicioDet",Constante.COD_HLP_PGO_TERRA);
		request.getSession().setAttribute("lstMes", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.COD_HLP_DET_MES));
		request.getSession().setAttribute("tituloAfiliacion","AFILIACION DE SERVICIOS: TERRA");
		request.getSession().setAttribute("trans","terra");
		List lista= FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_DET_DOCU_OTRO);
		lista.remove(0);
		request.getSession().setAttribute("lstDocumento",lista);
		request.getSession().setAttribute("nomafil","Nro. Abonado");
		request.getSession().setAttribute("ejemplo",((Vector)aplicacion.getMensajePorCodigo("PS00","ETE01")).elementAt(2).toString());
		DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
		request.getSession().setAttribute("lstLocalidad", dHelp_.getListDetalleAyuda(Constante.COD_HLP_CIUDAD_TERRA));
		request.getSession().setAttribute("lstServicio", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.COD_HLP_PGO_TERRA));
		
		FacadeFactory.getGeneralFacade().actualizarCuentas("LG02",usuario,request.getRemoteAddr());
		return mapping.findForward("inicioTerra");
	}
	
	
	public ActionForward inicioCMagico(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Usuario usuario = (Usuario) request.getSession().getAttribute(ConstanteSesion.USUARIO_EN_SESION);
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);

		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);


		AfiliacionPgServiciosForm frm = (AfiliacionPgServiciosForm)form;
		frm.reset(mapping, request);
		request.getSession().setAttribute("lstMes", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.COD_HLP_DET_MES));
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		request.getSession().setAttribute("mensajeAfiliacion",((Vector)aplicacion.getMensajePorCodigo("AS01","00002")).elementAt(2).toString());
		Constante.TIPO_SERVICIO=Constante.COD_HLP_PGO_CMAGICO;
		request.getSession().setAttribute("codServicioDet",Constante.COD_HLP_PGO_CMAGICO);
		request.getSession().setAttribute("tituloAfiliacion","AFILIACION DE SERVICIOS: CABLE MAGICO");
		request.getSession().setAttribute("lstServicio", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.COD_HLP_PGO_CMAGICO));
		
		request.getSession().setAttribute("nomafil","Código de cliente");
		request.getSession().setAttribute("ejemplo",((Vector)aplicacion.getMensajePorCodigo("PS00","ECM01")).elementAt(2).toString());
		DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
		List lista0= dHelp_.getListDetalleAyuda(Constante.COD_HLP_DET_LOCAL);
		List temp= new ArrayList();
		//saco el ultimo y lo almaceno en un temporal
		temp.add(0,lista0.get(lista0.size()-1));
		temp.add(0,lista0.get(0));
		//elimino el ultimo alemento q ya alamcene antes en otra lista
		lista0.remove(0);
		lista0.remove(lista0.size()-1);
		temp.addAll(lista0);
		/*for(int i=0;i<lista0.size();i++){
			temp.add(i+1,lista0.get(i));
		}*/
		lista0=temp;
		request.getSession().setAttribute("lstLocalidad",lista0);
		List lista= FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_DET_DOCU_OTRO);
		lista.remove(0);
		request.getSession().setAttribute("lstDocumento",lista);
		FacadeFactory.getGeneralFacade().actualizarCuentas("LG02",usuario,request.getRemoteAddr());
		return mapping.findForward("inicioAfCable");
	}
	
	public ActionForward afiliar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		AfiliacionPgServiciosForm frm = (AfiliacionPgServiciosForm)form;
		Usuario usuario = (Usuario)SeguridadUtil.getUsuarioSession(request);
		ParametrosTDC param = (ParametrosTDC)request.getSession().getAttribute(ConstanteSesion.PARAMETROS_TDC_SESION);

		request.getSession().setAttribute(ConstanteSesion.PARAMETROS_TDC_SESION,param);


		Tarjeta tarjeta = usuario.getTarjeta(); 
		Afiliacion afiliacion = setearAfiliacion(frm,tarjeta);
		List listaAfiliaciones = null;
		
		if(request.getParameter("codAf")!=null)
		Constante.TIPO_SERVICIO=request.getParameter("codAf");
		if (Constante.TIPO_SERVICIO.equals("00436")) {
			listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaExiste(Constante.PAGO_TEL,usuario.getTarjeta().getNumero(), Constante.TIPO_SERVICIO, afiliacion.getNumeroServicio());
			request.getSession().setAttribute("tituloAfiliacion","AFILIACION DE SERVICIOS: TELEFONIA FIJA");
			request.setAttribute("nomafil","Nro. Teléfono");
			
		}
		

		else if (Constante.TIPO_SERVICIO.equals("00437")) {
			listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaExiste(Constante.PAGO_MOV,usuario.getTarjeta().getNumero(), Constante.TIPO_SERVICIO, afiliacion.getNumeroServicio());
			request.getSession().setAttribute("tituloAfiliacion","AFILIACION DE SERVICIOS: TELEFONIA CELULAR");
			request.setAttribute("nomafil","Nro. Celular:");
			//System.out.println("TITULO:AFILIACION DE SERVICIOS: TELEFONIA CELULAR");
		}
		

		else if (Constante.TIPO_SERVICIO.equals("00439")) {
			listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaExiste(Constante.PAGO_CMA,usuario.getTarjeta().getNumero(), Constante.TIPO_SERVICIO, afiliacion.getNumeroServicio());
			request.getSession().setAttribute("tituloAfiliacion","AFILIACION DE SERVICIOS: CABLE MAGICO");
			request.setAttribute("nomafil","Código de Cliente");
			//System.out.println("TITULO:AFILIACION DE SERVICIOS: CABLE MAGICO");
		}
		

		else if (Constante.TIPO_SERVICIO.equals("00440")) {
			listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaExiste(Constante.PAGO_TER,usuario.getTarjeta().getNumero(), Constante.TIPO_SERVICIO, afiliacion.getNumeroServicio());
			request.getSession().setAttribute("tituloAfiliacion","AFILIACION DE SERVICIOS: TERRA ");
			request.setAttribute("nomafil","Nro. Abonado");
			//System.out.println("TITULO:AFILIACION DE SERVICIOS: TERRA ");
		}
		
		

		else if (Constante.TIPO_SERVICIO.equals("00441")) {
			listaAfiliaciones =  FacadeFactory.getAfiliacionFacade().getAfiliaExiste(Constante.PAGO_SED,usuario.getTarjeta().getNumero(), Constante.TIPO_SERVICIO, afiliacion.getNumeroServicio());
			request.getSession().setAttribute("tituloAfiliacion","AFILIACION DE SERVICIOS: AGUA - SEDAPAL");
			request.setAttribute("nomafil","Nro. de Suministro");
			//System.out.println("TITULO:AFILIACION DE SERVICIOS: AGUA - SEDAPAL");
		}
		
		request.getSession().setAttribute("CORREO_AFILIADO",afiliacion.getEmail());
		
		if (listaAfiliaciones.size() > 0){
			request.getSession().setAttribute("codServicioDet",Constante.TIPO_SERVICIO);
			loadObject(request);
			frm.setTxtClave("");
			throw new ArrayRuleException(ConstanteError.GENERICO,"El número de servicio: " + afiliacion.getNumeroServicio() +" ya se encuentra afiliado.");
		}
				
		//---
		String transaccion = request.getParameter("transaccion");
		//afiliacion.setClave6Digitos(SeguridadUtil.getClaveDesencriptada(frm.getTxtClave(),request));
		java.util.Vector valores = new java.util.Vector();
		java.util.Vector ctas=new java.util.Vector();
		java.util.Vector val = new java.util.Vector();
		val = new Vector();
		val.addElement("id");
		val.addElement(request.getSession(false).getId());
		valores.addElement(val);
		
		val = ObjectUtil.getVectorComponent("cmbTipoDoc",frm.getCmbTipoDoc());
		valores.addElement(val);	
		val = ObjectUtil.getVectorComponent("txtNumDoc",frm.getTxtNumDoc());
		valores.addElement(val);
		val = ObjectUtil.getVectorComponent("txtDia",frm.getTxtDia());
		valores.addElement(val);
		val = ObjectUtil.getVectorComponent("cmbMes",frm.getCmbMes());
		valores.addElement(val);
		val = ObjectUtil.getVectorComponent("txtAnio",frm.getTxtAnio());
		valores.addElement(val);
		val = ObjectUtil.getVectorComponent("rdnSexo",frm.getRdnSexo());
		valores.addElement(val);
		val = ObjectUtil.getVectorComponent("txtMail",frm.getTxtMail());
		valores.addElement(val);
		
		Vector v = ObjectUtil.getVectorBlankCuentas(request);
		ctas.addElement(v);
	
		try {
			request.getSession().setAttribute(ConstanteSesion.AFILIAR_SERVICIOS,FacadeFactory.getAfiliacionFacade().afiliar(usuario,afiliacion));
		} catch (ArrayRuleException e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			BNAplicacion aplicacion = BNAplicacion.getInstance();
			if (Constante.TIPO_SERVICIO.equals("00436")){ 
				request.setAttribute("nomafil","Nro. Teléfono");
				request.setAttribute("ejemplo",((Vector)aplicacion.getMensajePorCodigo("PS00","ETF01")).elementAt(2).toString());
				request.setAttribute("ejemplo2",((Vector)aplicacion.getMensajePorCodigo("PS00","EFF01")).elementAt(2).toString());
			}
			else if (Constante.TIPO_SERVICIO.equals("00437")){ 
				request.setAttribute("nomafil","Nro. Celular");
				request.setAttribute("ejemplo",((Vector)aplicacion.getMensajePorCodigo("PS00","ETC01")).elementAt(2).toString());
				}
			else if (Constante.TIPO_SERVICIO.equals("00439")){ 
				request.setAttribute("nomafil","Código de cliente");
				request.setAttribute("ejemplo",((Vector)aplicacion.getMensajePorCodigo("PS00","ECM01")).elementAt(2).toString());}
			else if (Constante.TIPO_SERVICIO.equals("00440")){ 
				request.setAttribute("nomafil","Nro. Abonado");
				request.getSession().setAttribute("tituloAfiliacion","AFILIACION DE SERVICIOS: TERRA ");}
			else if (Constante.TIPO_SERVICIO.equals("00441")){ 
				request.setAttribute("nomafil","Nro. Suministro");
				request.setAttribute("ejemplo2",((Vector)aplicacion.getMensajePorCodigo("PS00","ESE01")).elementAt(2).toString());}
			
			loadObject(request);
			frm.setTxtClave("");
			throw e;
		}
		if(Constante.TIPO_SERVICIO.equals("00441"))
			return mapping.findForward("finAfSedapal");
		else if(Constante.TIPO_SERVICIO.equals("00439"))
			return mapping.findForward("finAfCable");
		
		return mapping.findForward("afiliar");
	}
	
	private void loadObject(HttpServletRequest request)throws Exception {
		BNAplicacion aplicacion = BNAplicacion.getInstance();
		List lista= FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_DET_DOCU_OTRO);
		lista.remove(0);
		request.setAttribute("codServicioDet",Constante.TIPO_SERVICIO);
		request.setAttribute("lstDocumento",lista);
		if (Constante.TIPO_SERVICIO.equals("00440")) {
			DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
			request.setAttribute("lstLocalidad", dHelp_.getListDetalleAyuda(Constante.COD_HLP_CIUDAD_TERRA));
		} 
		else if (Constante.TIPO_SERVICIO.equals("00441")) {
			DetalleAyudaDatosImpl dHelp_ = new DetalleAyudaDatosImpl();
			request.setAttribute("lstLocalidad", dHelp_.getListDetalleAyuda(Constante.COD_HLP_CIUDAD_SEDAPAL));
		} else {
			request.setAttribute("lstLocalidad", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.COD_HLP_DET_LOCAL));
		}
		if (Constante.TIPO_SERVICIO.equals("00436")){ 
			request.setAttribute("nomafil","Nro. Teléfono");
			request.setAttribute("ejemplo",((Vector)aplicacion.getMensajePorCodigo("PS00","ETF01")).elementAt(2).toString());
			request.setAttribute("ejemplo2",((Vector)aplicacion.getMensajePorCodigo("PS00","EFF01")).elementAt(2).toString());
	}
		else if (Constante.TIPO_SERVICIO.equals("00437")){ 
			request.setAttribute("nomafil","Nro. Celular");
			request.setAttribute("ejemplo",((Vector)aplicacion.getMensajePorCodigo("PS00","ETC01")).elementAt(2).toString());
			}
		else if (Constante.TIPO_SERVICIO.equals("00439")){ 
			request.setAttribute("nomafil","Código de cliente");
			request.setAttribute("ejemplo",((Vector)aplicacion.getMensajePorCodigo("PS00","ECM01")).elementAt(2).toString());}
		else if (Constante.TIPO_SERVICIO.equals("00440")){ 
			request.setAttribute("nomafil","Nro. Abonado");
			request.getSession().setAttribute("tituloAfiliacion","AFILIACION DE SERVICIOS: TERRA ");
			request.setAttribute("ejemplo",((Vector)aplicacion.getMensajePorCodigo("PS00","ETE01")).elementAt(2).toString());
			}
		else if (Constante.TIPO_SERVICIO.equals("00441")){ 
			request.setAttribute("nomafil","Nro. Suministro");
			request.setAttribute("ejemplo2",((Vector)aplicacion.getMensajePorCodigo("PS00","ESE01")).elementAt(2).toString());}

		request.setAttribute("lstServicio", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.TIPO_SERVICIO));
		request.setAttribute("lstMes", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.COD_HLP_DET_MES));
	}
	
	private Afiliacion setearAfiliacion(AfiliacionPgServiciosForm frm,Tarjeta tarjeta) throws Exception {
		Afiliacion afiliacion = new AfiliacionImpl();
		if (Constante.TIPO_SERVICIO.equals("00436")) {
			afiliacion.setTipoAfiliacion(Constante.PAGO_TEL);
			afiliacion.setCodigoLocalidad(frm.getCmbLocalidad().substring(0,2));
			afiliacion.setNumeroServicio(frm.getCmbLocalidad().substring(2)+"-"+frm.getTxtNumServicio());
		}
		
		else if (Constante.TIPO_SERVICIO.equals("00437")) {
			afiliacion.setTipoAfiliacion(Constante.PAGO_MOV);
			afiliacion.setCodigoLocalidad(frm.getCmbLocalidad().substring(0,2));
			afiliacion.setNumeroServicio(frm.getCmbLocalidad().substring(2)+"-"+frm.getTxtNumServicio());
		}
		
		else if (Constante.TIPO_SERVICIO.equals("00439")) {
			afiliacion.setTipoAfiliacion(Constante.PAGO_CMA);
			afiliacion.setCodigoLocalidad(frm.getCmbLocalidad().substring(0,2));
			afiliacion.setNumeroServicio(frm.getTxtNumServicio());
		}
		
		else if (Constante.TIPO_SERVICIO.equals("00440")) {
			afiliacion.setTipoAfiliacion(Constante.PAGO_TER);
			afiliacion.setCodigoLocalidad(frm.getCmbLocalidad().substring(0,2));
			afiliacion.setNumeroServicio(frm.getTxtNumServicio());
		}
		
		else if (Constante.TIPO_SERVICIO.equals("00441")) {
			afiliacion.setTipoAfiliacion(Constante.PAGO_SED);
			afiliacion.setCodigoLocalidad(frm.getCmbLocalidad().substring(0,2));
			afiliacion.setNumeroServicio(frm.getTxtNumServicio());
		}
		

		afiliacion.setNroTarjeta(tarjeta.getNumero());
		afiliacion.setTarjetaOculta(ObjectUtil.ocultarCuenta(tarjeta.getNumero(),Constante.FORMATO_TARJETA));
		afiliacion.setTipoDocumento(frm.getCmbTipoDoc());
		afiliacion.setNroDocumento(frm.getTxtNumDoc());
		afiliacion.setFechaNacimiento(ObjectUtil.stringToTimestamp(frm.getTxtDia(),frm.getCmbMes(),frm.getTxtAnio()));
		afiliacion.setSexo(frm.getRdnSexo());
		afiliacion.setEmail(frm.getTxtMail());
		afiliacion.setCodigoServicio(frm.getCmbServicio());
		
		afiliacion.setDescripcion(frm.getTxtNombreServicio());
		return afiliacion;
	}
	
}
