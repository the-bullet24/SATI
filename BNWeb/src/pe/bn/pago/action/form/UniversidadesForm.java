
package pe.bn.pago.action.form;

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


public class UniversidadesForm extends ActionForm{
	

	private String cmbConcepto;
	private String cmbSituacion;
	private String cmbSede;
	private String txtImporte;
	private String txtImporteFlag;
	
	

	public String getTxtImporteFlag() {
		return txtImporteFlag;
	}


	public void setTxtImporteFlag(String txtImporteFlag) {
		this.txtImporteFlag = txtImporteFlag;
	}


	public String getTxtImporte() {
		return txtImporte;
	}


	public void setTxtImporte(String txtImporte) {
		this.txtImporte = txtImporte;
	}


	public String getCmbConcepto() {
		return cmbConcepto;
	}


	public void setCmbConcepto(String cmbConcepto) {
		this.cmbConcepto = cmbConcepto;
	}


	public String getCmbSede() {
		return cmbSede;
	}


	public void setCmbSede(String cmbSede) {
		this.cmbSede = cmbSede;
	}


	public String getCmbSituacion() {
		return cmbSituacion;
	}


	public void setCmbSituacion(String cmbSituacion) {
		this.cmbSituacion = cmbSituacion;
	}


	public ActionErrors validate(ActionMapping arg0, HttpServletRequest request) {
		super.validate(arg0, request);
		ActionErrors errors = new ActionErrors();	
		String validar = request.getParameter("validar");
		
		if(validar == null)
			return null;
		
		if(validar!=null && validar.equals("false"))
			return errors;
		
		if (ObjectUtil.isStringBlank(cmbSituacion)){
			errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.GENERICO,"Ingrese la situación del alumno"));
			
			return errors;
		}
		
		if (ObjectUtil.isStringBlank(cmbSede)){
			errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.GENERICO,"Ingrese la sede de la universidad"));
			
			return errors;
		}
		
		if (ObjectUtil.isStringBlank(cmbConcepto)){
			errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.GENERICO,"Ingrese el concepto a pagar"));
			
			return errors;
		}
		
	
		return errors;		
	}
	

	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		
		cmbConcepto      = "";
		cmbSede      	 = "";
		cmbSituacion     = "";
		txtImporte		 = "";
		txtImporteFlag   = "";
	
		
	}
	


}
