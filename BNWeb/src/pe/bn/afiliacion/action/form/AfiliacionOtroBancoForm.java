
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


public class AfiliacionOtroBancoForm extends AfiliacionGenericaForm{
	private static LoggerSati log3 = LoggerSati.getInstance(AfiliacionOtroBancoForm.class.getName());
	
	private String txtApellidoPaternoBenef;
	private String txtApellidoMaternoBenef;
	private String txtNombreBenef;
	private String cmbBancoDestino;
	private String cmbTipoTarjeta;
	private String txtNroTarjeta;
	private String txtNombreTransferencia;
	
	
    public String getCmbBancoDestino() {
        return cmbBancoDestino;
    }
    public void setCmbBancoDestino(String cmbBancoDestino) {
        this.cmbBancoDestino = cmbBancoDestino;
    }
    public String getCmbTipoTarjeta() {
        return cmbTipoTarjeta;
    }
    public void setCmbTipoTarjeta(String cmbTipoTarjeta) {
        this.cmbTipoTarjeta = cmbTipoTarjeta;
    }
    /**
    public Logger getLog() {
        return log;
    }
    public void setLog(Logger log) {
        this.log = log;
    }
    */
    public String getTxtNroTarjeta() {
        return txtNroTarjeta;
    }
    public void setTxtNroTarjeta(String txtNroTarjeta) {
        this.txtNroTarjeta = txtNroTarjeta;
    }
 
    public String getTxtNombreTransferencia() {
        return txtNombreTransferencia;
    }
    public void setTxtNombreTransferencia(String txtNombreTransferencia) {
        this.txtNombreTransferencia = txtNombreTransferencia;
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
			
			request.setAttribute("lstBancoDestino", FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_BANCO));
			request.setAttribute("lstTipoTarjeta", FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_TIP_TARJETA));
			
		} catch (Exception e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			System.out.println(""+e);
			request.setAttribute("lstDocumento", new ArrayList());
			request.setAttribute("lstMes", new ArrayList());
			request.setAttribute("lstBancoDestino", new ArrayList());
			request.setAttribute("lstTipoTarjeta", new ArrayList());
		}
		
	}

	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		super.reset(arg0, arg1);
		
		this.txtApellidoPaternoBenef 	= "";
		this.txtApellidoMaternoBenef	= "";
		this.txtNombreBenef				= "";
		this.cmbBancoDestino			= "";
		this.cmbTipoTarjeta				= "";
		this.txtNroTarjeta				= "";
		this.txtNombreTransferencia		= "";
	}
}
