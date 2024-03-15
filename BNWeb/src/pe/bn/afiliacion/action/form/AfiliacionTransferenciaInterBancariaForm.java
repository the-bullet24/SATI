
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


public class AfiliacionTransferenciaInterBancariaForm extends AfiliacionGenericaForm{
	private static LoggerSati log3 = LoggerSati.getInstance(AfiliacionTransferenciaInterBancariaForm.class.getName());
	
	private String txtCuentaCCI;
	private String rdnCuentaPropia;
	private String txtNombreBenef;
	
	
	
    public String getRdnCuentaPropia() {
        return rdnCuentaPropia;
    }
    public void setRdnCuentaPropia(String rdnCuentaPropia) {
        this.rdnCuentaPropia = rdnCuentaPropia;
    }
    public String getTxtCuentaCCI() {
        return txtCuentaCCI;
    }
    public void setTxtCuentaCCI(String txtCuentaCCI) {
        this.txtCuentaCCI = txtCuentaCCI;
    }
    public String getTxtNombreBenef() {
        return txtNombreBenef;
    }
    public void setTxtNombreBenef(String txtNombreBenef) {
        this.txtNombreBenef = txtNombreBenef;
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
			request.setAttribute("lstMes", FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_DET_MES));
			
		} catch (Exception e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			System.out.println(""+e);
			request.setAttribute("lstDocumento", new ArrayList());
			request.setAttribute("lstMes", new ArrayList());
		}
		
	}
	
	
	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		super.reset(arg0, arg1);
		
		this.txtCuentaCCI 	 = "";
		this.rdnCuentaPropia = "";
		this.txtNombreBenef	 = "";
	}
}
