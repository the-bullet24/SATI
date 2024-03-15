package pe.bn.afiliacion.action.form;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import pe.bn.afiliacion.action.DesafCableAction;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteError;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.ObjectUtil;


public class AfiliacionPgServiciosForm extends ActionForm{
	private static LoggerSati log3 = LoggerSati.getInstance(AfiliacionPgServiciosForm.class.getName());
	
	private String cmbTipoDoc;
	private String txtNumDoc;
	private String txtDia;
	private String cmbMes;
	private String txtAnio;
	private String rdnSexo;
	private String txtMail;
	private String cmbServicio;
	private String cmbLocalidad;
	private String txtNumServicio;
	private String txtNombreServicio;
	private String txtClave;
	

	
	public String getCmbLocalidad() {
		return cmbLocalidad;
	}
	public void setCmbLocalidad(String cmbLocalidad) {
		this.cmbLocalidad = cmbLocalidad;
	}
	public String getCmbMes() {
		return cmbMes;
	}
	public void setCmbMes(String cmbMes) {
		this.cmbMes = cmbMes;
	}
	public String getCmbServicio() {
		return cmbServicio;
	}
	public void setCmbServicio(String cmbServicio) {
		this.cmbServicio = cmbServicio;
	}
	public String getCmbTipoDoc() {
		return cmbTipoDoc;
	}
	public void setCmbTipoDoc(String cmbTipoDoc) {
		this.cmbTipoDoc = cmbTipoDoc;
	}
	public String getRdnSexo() {
		return rdnSexo;
	}
	public void setRdnSexo(String rdnSexo) {
		this.rdnSexo = rdnSexo;
	}
	public String getTxtAnio() {
		return txtAnio;
	}
	public void setTxtAnio(String txtAnio) {
		this.txtAnio = txtAnio;
	}
	public String getTxtClave() {
		return txtClave;
	}
	public void setTxtClave(String txtClave) {
		this.txtClave = txtClave;
	}
	public String getTxtDia() {
		return txtDia;
	}
	public void setTxtDia(String txtDia) {
		this.txtDia = txtDia;
	}
	public String getTxtMail() {
		return txtMail;
	}
	public void setTxtMail(String txtMail) {
		this.txtMail = txtMail;
	}
	public String getTxtNombreServicio() {
		return txtNombreServicio;
	}
	public void setTxtNombreServicio(String txtNombreServicio) {
		this.txtNombreServicio = txtNombreServicio;
	}
	public String getTxtNumDoc() {
		return txtNumDoc;
	}
	public void setTxtNumDoc(String txtNumDoc) {
		this.txtNumDoc = txtNumDoc;
	}
	public String getTxtNumServicio() {
		return txtNumServicio;
	}
	public void setTxtNumServicio(String txtNumServicio) {
		this.txtNumServicio = txtNumServicio;
	}
	
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		
		
		ActionErrors errors = new ActionErrors();	
		
		
		String validar = request.getParameter("validar");
		
		if(validar == null)
			return null;
		
		if(validar!=null && validar.equals("false"))
			return errors;
		
	
		if (ObjectUtil.isStringBlank(cmbTipoDoc)){
			errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.MODULO_LOGIN_CAMPO_OBLIGATORIO,"Tipo de Documento"));
			this.txtClave 			=	"";
			loadObject(request);
			return errors;
		}
		if (ObjectUtil.isStringBlank(txtNumDoc)){
			errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.MODULO_LOGIN_CAMPO_OBLIGATORIO,"Número de Documento"));
			this.txtClave 			=	"";
			loadObject(request);
			return errors;
		}
		if (ObjectUtil.isStringBlank(txtDia)){
			errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.MODULO_LOGIN_CAMPO_OBLIGATORIO,"Dia de Nacimiento"));
			this.txtClave 			=	"";
			loadObject(request);
			return errors;
		}
		if (ObjectUtil.isStringBlank(cmbMes)){
			errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.MODULO_LOGIN_CAMPO_OBLIGATORIO,"Mes de Nacimiento"));
			this.txtClave 			=	"";
			loadObject(request);
			return errors;
		}
		if (ObjectUtil.isStringBlank(txtAnio)){
			errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.MODULO_LOGIN_CAMPO_OBLIGATORIO,"Año de Nacimiento"));
			this.txtClave 			=	"";
			loadObject(request);
			return errors;
		}
		if (ObjectUtil.isStringBlank(rdnSexo)){
			errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.MODULO_LOGIN_CAMPO_OBLIGATORIO,"Sexo"));
			this.txtClave 			=	"";
			loadObject(request);
			return errors;
		}
		if (ObjectUtil.isStringBlank(txtMail)){
			errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.MODULO_LOGIN_CAMPO_OBLIGATORIO,"E-Mail"));
			this.txtClave 			=	"";
			loadObject(request);
			return errors;
		}
//		if (ObjectUtil.isStringBlank(cmbServicio)){
//				errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.MODULO_LOGIN_CAMPO_OBLIGATORIO,"Servicio para Afiliar"));
//				this.txtClave 			=	"";
//				loadObject(request);
//				return errors;
//		}
		/**
		if (!Constante.TIPO_SERVICIO.equals("00440")) {
			if (ObjectUtil.isStringBlank(cmbLocalidad)){
				errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.MODULO_LOGIN_CAMPO_OBLIGATORIO,"Localidad"));
				this.txtClave 			=	"";
				loadObject(request);
				return errors;
			}
		}
		**/
		if (ObjectUtil.isStringBlank(txtNumServicio)){
			errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.MODULO_LOGIN_CAMPO_OBLIGATORIO,"Numero de Servicio"));
			this.txtClave 			=	"";
			loadObject(request);
			return errors;
		}
		if (ObjectUtil.isStringBlank(txtNombreServicio)){
			errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.MODULO_LOGIN_CAMPO_OBLIGATORIO,"Nombre de Servicio"));
			this.txtClave 			=	"";
			loadObject(request);
			return errors;
		}
		if (ObjectUtil.isStringBlank(txtClave)){
			errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.MODULO_LOGIN_CAMPO_OBLIGATORIO,"Numero de Tarjeta"));
			this.txtClave 			=	"";
			loadObject(request);
			return errors;
		}
		
		return errors;
	}
	
	private void loadObject(HttpServletRequest req){
		try {
			req.setAttribute("lstDocumento", FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_DET_DOCU_OTRO));
			req.setAttribute("lstLocalidad", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.COD_HLP_DET_LOCAL));
			req.setAttribute("lstServicio", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.TIPO_SERVICIO));
			req.setAttribute("lstMes", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.COD_HLP_DET_MES));
		} catch (Exception e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			System.out.println(""+e);
			req.setAttribute("lstDocumento", new ArrayList());
			req.setAttribute("lstLocalidad", new ArrayList());
			req.setAttribute("lstServicio", new ArrayList());
			req.setAttribute("lstMes", new ArrayList());
		}
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		this.cmbTipoDoc 		= 	"";
		this.txtNumDoc 			=	"";
		this.txtDia 			=	"";
		this.cmbMes 			=	"";
		this.txtAnio 			=	"";
		this.rdnSexo 			=	"";
		this.txtMail 			=	"";
		this.cmbServicio 		=	"";
		this.cmbLocalidad 		=	"";
		this.txtNumServicio 	=	"";
		this.txtNombreServicio 	=	"";
		this.txtClave 			=	"";
	}
}
