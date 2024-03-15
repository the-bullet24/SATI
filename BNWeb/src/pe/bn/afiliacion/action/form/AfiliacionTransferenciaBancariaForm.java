
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


public class AfiliacionTransferenciaBancariaForm extends AfiliacionGenericaForm{
	private static LoggerSati log3 = LoggerSati.getInstance(AfiliacionGenericaForm.class.getName());
	
	private String cmbTipoCuentaDestino;
	private String txtNumeroCuentaDestino;	
	private String rdnCuentaPropia;
	private String txtNombreBeneficiario;
	
	

	public ActionErrors validate(ActionMapping arg0, HttpServletRequest request) {
		super.validate(arg0, request);
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
			errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.GENERICO,"Ingrese Número de Documento"));
			this.txtNumClave 			=	"";
			loadObject(request);
			return errors;
		}
		
		if (!ObjectUtil.isNumeric(txtNumDoc)){			
			errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.GENERICO,"El Número de Documento debe ser numérico"));
			this.txtNumClave 			=	"";
			loadObject(request);
			return errors;
		}

		if (ObjectUtil.isStringBlank(txtDia)){
			errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.GENERICO,"Ingrese día de Nacimiento"));
			this.txtNumClave 			=	"";
			loadObject(request);
			return errors;
		}

		if (!ObjectUtil.isNumeric(txtDia)){			
			errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.GENERICO,"El día de nacimiento debe ser numérico"));
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
		
		if (!ObjectUtil.isNumeric(txtAnio)){			
			errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.GENERICO,"El Año de Nacimiento debe ser Numérico"));
			this.txtNumClave 			=	"";
			loadObject(request);
			return errors;
		}
		
		if (ObjectUtil.isStringBlank(cmbMes)){
			errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.GENERICO,"Ingrese el mes de Nacimiento"));
			this.txtNumClave 			=	"";
			loadObject(request);
			return errors;
		}
		
		if (!ObjectUtil.isNumeric(cmbMes)){
			errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.GENERICO,"El mes de Nacimiento es numérico."));
			this.txtNumClave 			=	"";
			loadObject(request);
			return errors;
		}
		
		if (ObjectUtil.isStringBlank(this.cmbTipoCuentaDestino)){
			errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.GENERICO,"Seleccione un Tipo de Cuenta Destino."));
			this.txtNumClave 			=	"";
			loadObject(request);
			return errors;
		}
		
		if (!esCuentaDestino()){
		    errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.GENERICO,"Ingrese una Cuenta Destino Valida."));
			this.txtNumClave 			=	"";
			loadObject(request);
			return errors;
		}
		
		return errors;		
	}
	
	private boolean esCuentaDestino(){
	    boolean flag 		= false;
	    String tipoProducto = this.txtNumeroCuentaDestino.substring(0,2);
	    
	    if (this.cmbTipoCuentaDestino.equals(Constante.COD_CUENTA_AHORROS_MN)){
	        if (tipoProducto.equals(Constante.COD_CUENTA_AHORROS_MN))
	            flag = true;
	    }
	    else if (this.cmbTipoCuentaDestino.equals(Constante.COD_CUENTA_AHORROS_ME)){
	        if (tipoProducto.equals(Constante.COD_CUENTA_AHORROS_ME))
	            flag = true;
	    }
	    else if (this.cmbTipoCuentaDestino.equals(Constante.COD_CUENTA_CORRIENTE_MN)){
	        if (tipoProducto.equals(Constante.COD_CUENTA_CORRIENTE_MN))
	            flag = true;
	    }
	    else if (this.cmbTipoCuentaDestino.equals(Constante.COD_CUENTA_CORRIENTE_ME)){
	        if (tipoProducto.equals(Constante.COD_CUENTA_CORRIENTE_ME))
	            flag = true;
	    }
	    else if (this.cmbTipoCuentaDestino.equals(Constante.COD_CUENTA_CTS_MN)){
	        if (tipoProducto.equals(Constante.COD_CUENTA_CTS_MN))
	            flag = true;
	    }
	    else if (this.cmbTipoCuentaDestino.equals(Constante.COD_CUENTA_CTS_ME)){
	        if (tipoProducto.equals(Constante.COD_CUENTA_CTS_ME))
	            flag = true;
	    }
	    return flag;
	}
	private void loadObject(HttpServletRequest request) {
		try {
			request.setAttribute("lstDocumento", FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_DET_DOCU_OTRO));
			request.setAttribute("lstTipoCuenta", FacadeFactory.getGeneralFacade().getComboTipoCuenta());
			request.setAttribute("lstMes", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.COD_HLP_DET_MES));
		} catch (Exception e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			System.out.println(""+e);
			request.setAttribute("lstDocumento", new ArrayList());
			request.setAttribute("lstTipoCuenta", new ArrayList());
			request.setAttribute("lstMes", new ArrayList());
		}
	}
	

	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		super.reset(arg0, arg1);
		cmbTipoCuentaDestino		= "";
		txtNumeroCuentaDestino		= "";	
		rdnCuentaPropia				= "";
		txtNombreBeneficiario		= "";		
	}

	public String getCmbTipoCuentaDestino() {
		return cmbTipoCuentaDestino;
	}
	
	public void setCmbTipoCuentaDestino(String cmbTipoCuentaDestino) {
		this.cmbTipoCuentaDestino = cmbTipoCuentaDestino;
	}

	public String getRdnCuentaPropia() {
		return rdnCuentaPropia;
	}

	public void setRdnCuentaPropia(String rdCuentaPropia) {
		this.rdnCuentaPropia = rdCuentaPropia;
	}

	public String getTxtNombreBeneficiario() {
		return txtNombreBeneficiario;
	}

	public void setTxtNombreBeneficiario(String txtNombreBeneficiario) {
		this.txtNombreBeneficiario = txtNombreBeneficiario;
	}

	public String getTxtNumeroCuentaDestino() {
		return txtNumeroCuentaDestino;
	}

	public void setTxtNumeroCuentaDestino(String txtNumeroCuentaDestino) {
		this.txtNumeroCuentaDestino = txtNumeroCuentaDestino;
	}
}
