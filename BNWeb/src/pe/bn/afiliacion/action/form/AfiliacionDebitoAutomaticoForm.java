
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


public class AfiliacionDebitoAutomaticoForm extends AfiliacionGenericaForm{
	private static LoggerSati log3 = LoggerSati.getInstance(AfiliacionGenericaForm.class.getName());
//	

	private String cmbTipoTelefono;
	private String txtNumeroTelefono;	
	private String cmbEmpresa;
	private String cmbServicio;
	private String txtNumSuministro;
	private String txtMaximo;
	private String rdnMontoMaximoDebito;
	private String txtServidorMail;
	private String txtTipoOperacion;
	private String cmbConfirmacion;
	private String cmbDiscado;
	
	
	//Modificacion de Debito Automatico -- MDB
	
	private String codEntidad;
	private String codServicio;
	private String codSuministro;
	
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
		
	
		if (ObjectUtil.isStringBlank(cmbEmpresa)){
			errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.GENERICO,"Seleccione una Empresa"));
			this.txtNumClave 			=	"";
			loadObject(request);
			return errors;
		}
		if (ObjectUtil.isStringBlank(cmbServicio)){
			errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.GENERICO,"Seleccione un Servicio"));
			this.txtNumClave 			=	"";
			loadObject(request);
			return errors;
		}
		if (ObjectUtil.isStringBlank(cmbCodigoUsuarioSumi)){
			errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.GENERICO,"Ingrese un Codigo de Usuario o Suministro"));
			this.txtNumClave 			=	"";
			loadObject(request);
			return errors;
		}
//		if (ObjectUtil.isStringBlank(txtMaximo)){
//			errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.GENERICO,"Ingrese Monto Maximo"));
//			this.txtNumClave 			=	"";
//			loadObject(request);
//			return errors;
//		}
		
		return errors;		
	}
	
	
	private void loadObject(HttpServletRequest request) {
		try {
		request.setAttribute("lstDocumento", FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_DET_DOCU_OTRO));
		request.setAttribute("lstTipoCuenta", FacadeFactory.getGeneralFacade().getComboTipoCuenta());
		request.setAttribute("lstMes", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.COD_HLP_DET_MES));
			
			request.setAttribute("lstEmpresa", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.DEB_AUTOMATICO_EMPRESA));
			request.setAttribute("lstServicio", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.DEB_AUTOMATICO_SERVICIO));
			request.setAttribute("lstTipoTelefono", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.DEB_AUTOMATICO_TIPO_TELEFONO));
			request.setAttribute("lstDiscado",FacadeFactory.getGeneralFacade().getComboDetalleAyudaDiscado(Constante.DEB_AUTOMATICO_DISCADO));
			
		} catch (Exception e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			System.out.println(""+e);
			request.setAttribute("lstDocumento", new ArrayList());
			request.setAttribute("lstTipoCuenta", new ArrayList());
			request.setAttribute("lstMes", new ArrayList());
			
			request.setAttribute("lstEmpresa", new ArrayList());
			request.setAttribute("lstServicio", new ArrayList());
			request.setAttribute("lstTipoTelefono", new ArrayList());	
			request.setAttribute("lstDiscado", new ArrayList());	
		}
	}
	

	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		super.reset(arg0, arg1);
		cmbTipoTelefono		        = "";
		txtNumeroTelefono		    = "";
		cmbEmpresa					= "";
		cmbServicio					= "";
		cmbConfirmacion				= "";
		cmbCodigoUsuarioSumi		= "";
		txtMaximo					= "";
		rdnMontoMaximoDebito        = "";
		rdnMontoMaximoDebito        = "";
		txtNombreBeneficiario       = "";
		txtServidorMail      		= "";
	}

	public String getCmbTipoTelefono() {
		return cmbTipoTelefono;
	}

	public void setCmbTipoTelefono(String cmbTipoTelefono) {
		this.cmbTipoTelefono = cmbTipoTelefono;
	}

	public String getTxtNumeroTelefono() {
		
		return txtNumeroTelefono;
		
	}

	public void setTxtNumeroTelefono(String txtNumeroTelefono) {
		this.txtNumeroTelefono = txtNumeroTelefono;
	}

	public String getCmbEmpresa() {
		return cmbEmpresa;
	}

	public void setCmbEmpresa(String cmbEmpresa) {
		this.cmbEmpresa = cmbEmpresa;
	}

	public String getCmbServicio() {
		return cmbServicio;
	}

	public void setCmbServicio(String cmbServicio) {
		this.cmbServicio = cmbServicio;
	}

	public String getTxtMaximo() {
		return txtMaximo;
	}

	public void setTxtMaximo(String txtMaximo) {
		this.txtMaximo = txtMaximo;
	}

	public String getRdnMontoMaximoDebito() {
		return rdnMontoMaximoDebito;
	}

	public void setRdnMontoMaximoDebito(String rdnMontoMaximoDebito) {
		this.rdnMontoMaximoDebito = rdnMontoMaximoDebito;
	}


	public String getTxtNombreBeneficiario() {
		return txtNombreBeneficiario;
	}

	public void setTxtNombreBeneficiario(String txtNombreBeneficiario) {
		this.txtNombreBeneficiario = txtNombreBeneficiario;
	}


	public String getTxtServidorMail() {
		return txtServidorMail;
	}


	public void setTxtServidorMail(String txtServidorMail) {
		this.txtServidorMail = txtServidorMail;
	}


	public String getTxtTipoOperacion() {
		return txtTipoOperacion;
	}


	public void setTxtTipoOperacion(String txtTipoOperacion) {
		this.txtTipoOperacion = txtTipoOperacion;
	}


	public String getTxtNumSuministro() {
		return txtNumSuministro;
	}


	public void setTxtNumSuministro(String txtNumSuministro) {
		this.txtNumSuministro = txtNumSuministro;
	}


	public String getCmbConfirmacion() {
		return cmbConfirmacion;
	}


	public void setCmbConfirmacion(String cmbConfirmacion) {
		this.cmbConfirmacion = cmbConfirmacion;
	}


	public String getCmbDiscado() {
		return cmbDiscado;
	}


	public void setCmbDiscado(String cmbDiscado) {
		this.cmbDiscado = cmbDiscado;
	}


	public String getCodEntidad() {
		return codEntidad;
	}


	public void setCodEntidad(String codEntidad) {
		this.codEntidad = codEntidad;
	}


	public String getCodServicio() {
		return codServicio;
	}


	public void setCodServicio(String codServicio) {
		this.codServicio = codServicio;
	}


	public String getCodSuministro() {
		return codSuministro;
	}


	public void setCodSuministro(String codSuministro) {
		this.codSuministro = codSuministro;
	}

}
