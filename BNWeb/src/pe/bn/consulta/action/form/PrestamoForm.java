package pe.bn.consulta.action.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteError;
import pe.cosapi.common.ObjectUtil;



public class PrestamoForm extends ActionForm{
	
	private String cmbCuotas;
	private String txtImporte;
	private String flag;
	private String rdnPeriodoGracia;
	private String rdnCuotaProtegida;
	private String rdnSimular;
	private String txtCorreo;
	private String txtCorreoConf;
	

	public String getTxtCorreo() {
		return txtCorreo;
	}

	public void setTxtCorreo(String txtCorreo) {
		this.txtCorreo = txtCorreo;
	}

	public String getTxtCorreoConf() {
		return txtCorreoConf;
	}

	public void setTxtCorreoConf(String txtCorreoConf) {
		this.txtCorreoConf = txtCorreoConf;
	}

	public String getRdnSimular() {
		return rdnSimular;
	}

	public void setRdnSimular(String rdnSimular) {
		this.rdnSimular = rdnSimular;
	}

	public String getRdnCuotaProtegida() {
		return rdnCuotaProtegida;
	}

	public void setRdnCuotaProtegida(String rdnCuotaProtegida) {
		this.rdnCuotaProtegida = rdnCuotaProtegida;
	}

	public String getRdnPeriodoGracia() {
		return rdnPeriodoGracia;
	}

	public void setRdnPeriodoGracia(String rdnPeriodoGracia) {
		this.rdnPeriodoGracia = rdnPeriodoGracia;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request){
		
		cmbCuotas  = "";
		flag  = "";
		rdnPeriodoGracia = "";
		rdnCuotaProtegida = "";
		txtImporte		="";
		//rdnSimular		="";
		
	}
	
	public ActionErrors validate(ActionMapping arg0, HttpServletRequest request) {
		super.validate(arg0, request);
		ActionErrors errors = new ActionErrors();	
		String validar = request.getParameter("flag");
			
		if(validar == null)
			return null;
	
		if(validar.equals("true")){
			
			request.setAttribute("flagSim", rdnSimular);
		
			if (ObjectUtil.isStringBlank(txtImporte)){
			
				errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.GENERICO,"Ingrese el importe a solicitar"));
				return errors;
			}
			if (ObjectUtil.isStringBlank(cmbCuotas)){
				
				errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.GENERICO,"Seleccione la cantidad de cuotas"));
				return errors;
			}
			if (ObjectUtil.isStringBlank(rdnPeriodoGracia)){
				
				errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.GENERICO,"Ingrese el valor del periodo de gracia"));
				return errors;
			}
			if (ObjectUtil.isStringBlank(rdnCuotaProtegida)){
				
				errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.GENERICO,"Ingrese el valor de la cuota protegida"));
				return errors;
			}
		}else{
			
			return null;
		}

		
		
		return errors;
		
		//return null;
	}

	public String getCmbCuotas() {
		return cmbCuotas;
	}

	public void setCmbCuotas(String cmbCuotas) {
		this.cmbCuotas = cmbCuotas;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getTxtImporte() {
		return txtImporte;
	}

	public void setTxtImporte(String txtImporte) {
		this.txtImporte = txtImporte;
	}
	
	
}
