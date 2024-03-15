
package pe.bn.afiliacion.action.form;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import pe.bn.afiliacion.action.DesafCableAction;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteError;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.ObjectUtil;


public class AfiliacionTelegiroForm extends AfiliacionGenericaForm{
	private static LoggerSati log3 = LoggerSati.getInstance(AfiliacionTelegiroForm.class.getName());
	
	private String txtApellidoPaternoBenef;
	private String txtApellidoMaternoBenef;
	private String txtNombreBenef;
	private String cmbTipoDocBenef;
	private String txtNroDocBenef;	
	private String txtdestipdoc;
	
	
	public String getCmbTipoDocBenef() {
		return cmbTipoDocBenef;
	}
	public void setCmbTipoDocBenef(String cmbTipoDocBenef) {
		this.cmbTipoDocBenef = cmbTipoDocBenef;
	}
	public String getTxtApellidoMaternoBenef() {
		return txtApellidoMaternoBenef;
	}
	public void setTxtApellidoMaternoBenef(String txtApellidoMaternoBenef) {
		this.txtApellidoMaternoBenef = txtApellidoMaternoBenef;
	}
	public String getTxtApellidoPaternoBenef() {
		return txtApellidoPaternoBenef;
	}
	public void setTxtApellidoPaternoBenef(String txtApellidoPaternoBenef) {
		this.txtApellidoPaternoBenef = txtApellidoPaternoBenef;
	}
	public String getTxtNombreBenef() {
		return txtNombreBenef;
	}
	public void setTxtNombreBenef(String txtNombreBenef) {
		this.txtNombreBenef = txtNombreBenef;
	}

	public String getTxtNroDocBenef() {
		return txtNroDocBenef;
	}
	
	public void setTxtNroDocBenef(String txtNroDocBenef) {
		this.txtNroDocBenef = txtNroDocBenef;
	}
	
	/**
	 * @return Returns the txtdestipdoc.
	 */
	public String getTxtdestipdoc() {
		return txtdestipdoc;
	}
	/**
	 * @param txtdestipdoc The txtdestipdoc to set.
	 */
	public void setTxtdestipdoc(String txtdestipdoc) {
		this.txtdestipdoc = txtdestipdoc;
	}
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		super.validate(mapping, request);
		
		
		ActionErrors errors = new ActionErrors();	
		String validar = request.getParameter("validar");
		
		if(validar == null)
			return null;
		
		if(validar!=null && validar.equals("false"))
			return errors;
		
		if (ObjectUtil.isStringBlank(cmbTipoDocIdent)){
			errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.GENERICO,"Ingrese Tipo de Documento"));
			this.txtNumClave 			=	"";
			loadObject(request);
			return errors;
		}
		
		if (ObjectUtil.isStringBlank(txtNumDoc)){
			errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.GENERICO,"Ingrese Numero de Documento"));
			this.txtNumClave 			=	"";
			loadObject(request);
			return errors;
		}
		
		if (ObjectUtil.isStringBlank(txtDia)){
			errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.GENERICO,"Ingrese Dia de Nacimiento"));
			this.txtNumClave 			=	"";
			loadObject(request);
			return errors;
		}
		
		if (ObjectUtil.isStringBlank(txtAnio)){
			errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.GENERICO,"Ingrese Año de Nacimiento"));
			this.txtNumClave 			=	"";
			loadObject(request);
			return errors;
		}
		
		if (ObjectUtil.isStringBlank(cmbMes)){
			errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.GENERICO,"Ingrese Fecha de Nacimiento"));
			this.txtNumClave 			=	"";
			loadObject(request);
			return errors;
		}
		
		return errors;
	}
	
	private void loadObject(HttpServletRequest request) {
		try {
			request.setAttribute("lstDocumento", FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_DET_DOCU_OTRO));
			request.setAttribute("lstDocumentoBen", FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_DET_DOCU));
			request.setAttribute("lstLocalidad", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.COD_HLP_DET_LOCAL));
			request.setAttribute("lstMes", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.COD_HLP_DET_MES));
		} catch (Exception e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			System.out.println(""+e);
			request.setAttribute("lstDocumento", new ArrayList());
			request.setAttribute("lstDocumentoBen", new ArrayList());
			request.setAttribute("lstLocalidad", new ArrayList());
			request.setAttribute("lstMes", new ArrayList());
		}
		
	}
	
	
	/* (sin Javadoc)
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		super.reset(arg0, arg1);
		
		this.txtApellidoPaternoBenef 	= "";
		this.txtApellidoMaternoBenef	= "";
		this.txtNombreBenef				= "";
		this.cmbTipoDocBenef			= "";
		this.txtNroDocBenef				= "";			
	}
	
}
