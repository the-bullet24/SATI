
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


public class AfiliacionBancaCelularForm extends AfiliacionGenericaForm{
	private static LoggerSati log3 = LoggerSati.getInstance(AfiliacionGenericaForm.class.getName());
//	


	private String cmbEmpresa;
	private String cmbServicio;

	private String txtNumeroCuentaDestino;
	private String cmbTipoCuentaDestino;
	private String txtNumeroCelularDest;
	private String cmbOperador;
	private String txtAlias;
	private String txtNumeroServicio;
	private String cmbLocalidad;
	private String cmbEntidad;
	private String txtServidorMail;
	private String cmbServicioAfiliacion;
	private String flagRegistro;
	private String flagLocalidad;
	private String flagTipoLocalidad;
	private String txtNumeroSuministro;
	private String cmbOperacion;
	
	

	public String getCmbOperacion() {
		return cmbOperacion;
	}


	public void setCmbOperacion(String cmbOperacion) {
		this.cmbOperacion = cmbOperacion;
	}


	public String getFlagTipoLocalidad() {
		return flagTipoLocalidad;
	}


	public void setFlagTipoLocalidad(String flagTipoLocalidad) {
		this.flagTipoLocalidad = flagTipoLocalidad;
	}


	public String getFlagLocalidad() {
		return flagLocalidad;
	}


	public void setFlagLocalidad(String flagLocalidad) {
		this.flagLocalidad = flagLocalidad;
	}


	public String getFlagRegistro() {
		return flagRegistro;
	}


	public void setFlagRegistro(String flagRegistro) {
		this.flagRegistro = flagRegistro;
	}


	public String getCmbServicioAfiliacion() {
		return cmbServicioAfiliacion;
	}


	public void setCmbServicioAfiliacion(String cmbServicioAfiliacion) {
		this.cmbServicioAfiliacion = cmbServicioAfiliacion;
	}


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
			loadObject(request);
			return errors;
		}
		
				
		if (ObjectUtil.isStringBlank(txtNumDoc)){
			errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.GENERICO,"Ingrese Número de Documento"));
			loadObject(request);
			return errors;
		}
		
//		if (!ObjectUtil.isNumeric(txtNumDoc)){			
//			errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.GENERICO,"El Número de Documento debe ser numérico"));
//			this.txtNumClave 			=	"";
//			loadObject(request);
//			return errors;
//		}

		if (ObjectUtil.isStringBlank(txtDia)){
			errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.GENERICO,"Ingrese día de Nacimiento"));
			loadObject(request);
			return errors;
		}

		if (!ObjectUtil.isNumeric(txtDia)){			
			errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.GENERICO,"El día de nacimiento debe ser numérico"));
			loadObject(request);
			return errors;
		}
		
		if (ObjectUtil.isStringBlank(txtAnio)){
			errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.GENERICO,"Ingrese Año de Nacimiento"));
			loadObject(request);
			return errors;
		}
		
		if (!ObjectUtil.isNumeric(txtAnio)){			
			errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.GENERICO,"El Año de Nacimiento debe ser Numérico"));
			loadObject(request);
			return errors;
		}
		
		if (ObjectUtil.isStringBlank(cmbMes)){
			errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.GENERICO,"Ingrese el mes de Nacimiento"));
			loadObject(request);
			return errors;
		}
		
		if (!ObjectUtil.isNumeric(cmbMes)){
			errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.GENERICO,"El mes de Nacimiento es numérico."));
			loadObject(request);
			return errors;
		}
		
	
		
		
		return errors;		
	}
	
	
	private void loadObject(HttpServletRequest request) {
		try {
		request.setAttribute("lstDocumento", FacadeFactory.getGeneralFacade().getComboDetalleHlp(Constante.COD_HLP_DET_DOCU_OTRO));
		request.setAttribute("lstTipoCuentaBancaCel", FacadeFactory.getGeneralFacade().getComboTipoCuenta());
		request.setAttribute("lstMes", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.COD_HLP_DET_MES));
		request.setAttribute("lstAfiliaciones", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.BAN_CELULAR_AFILIACIONES));
		request.setAttribute("lstLocalidad", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.COD_HLP_DET_LOCAL));
		request.setAttribute("lstOperador", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.BAN_CELULAR_OPERADOR));
		request.setAttribute("lstPago", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.BAN_CELULAR_PAGO));
		request.setAttribute("lstPagoServicio", null);
		request.setAttribute("lstOperacion", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.BAN_CELULAR_TIPO_OPERACION));
		request.setAttribute("lstEmpresa", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.DEB_AUTOMATICO_EMPRESA));
		request.setAttribute("lstServicio", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.DEB_AUTOMATICO_SERVICIO));
	
			
		} catch (Exception e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			e.printStackTrace();
			request.setAttribute("lstDocumento", new ArrayList());
			request.setAttribute("lstTipoCuentaBancaCel", new ArrayList());
			request.setAttribute("lstMes", new ArrayList());
			request.setAttribute("lstAfiliaciones",new ArrayList());
			request.setAttribute("lstLocalidad",new ArrayList());
			
			request.setAttribute("lstEmpresa", new ArrayList());
			request.setAttribute("lstServicio", new ArrayList());
		
		}
	}
	

	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		//super.reset(arg0, arg1);
		txtNumeroCuentaDestino      = "";
		cmbTipoCuentaDestino      = "";
		txtNumeroCelularDest      = "";
		cmbOperador      = "";
		txtAlias      = "";
		txtNumeroServicio      = "";
		cmbLocalidad      = "";
		cmbEntidad      = "";
		cmbServicio      = "";
		txtNumeroSuministro= "";
		
	}
	
	public void resetTotal(ActionMapping arg0, HttpServletRequest arg1) {
		super.reset(arg0, arg1);
		txtNumeroCuentaDestino      = "";
		cmbServicioAfiliacion		="";
		cmbTipoCuentaDestino      = "";
		txtNumeroCelularDest      = "";
		cmbOperador      = "";
		txtAlias      = "";
		txtNumeroServicio      = "";
		cmbLocalidad      = "";
		cmbEntidad      = "";
		cmbServicio      = "";
		txtNumeroSuministro= "";
		cmbOperacion      ="";
		
	}


	public String getCmbEmpresa() {
		return cmbEmpresa;
	}


	public void setCmbEmpresa(String cmbEmpresa) {
		this.cmbEmpresa = cmbEmpresa;
	}


	public String getCmbEntidad() {
		return cmbEntidad;
	}


	public void setCmbEntidad(String cmbEntidad) {
		this.cmbEntidad = cmbEntidad;
	}


	public String getCmbLocalidad() {
		return cmbLocalidad;
	}


	public void setCmbLocalidad(String cmbLocalidad) {
		this.cmbLocalidad = cmbLocalidad;
	}


	public String getCmbOperador() {
		return cmbOperador;
	}


	public void setCmbOperador(String cmbOperador) {
		this.cmbOperador = cmbOperador;
	}


	public String getCmbServicio() {
		return cmbServicio;
	}


	public void setCmbServicio(String cmbServicio) {
		this.cmbServicio = cmbServicio;
	}


	public String getCmbTipoCuentaDestino() {
		return cmbTipoCuentaDestino;
	}


	public void setCmbTipoCuentaDestino(String cmbTipoCuentaDestino) {
		this.cmbTipoCuentaDestino = cmbTipoCuentaDestino;
	}


	public String getTxtAlias() {
		return txtAlias;
	}


	public void setTxtAlias(String txtAlias) {
		this.txtAlias = txtAlias;
	}


	public String getTxtNumeroCelularDest() {
		return txtNumeroCelularDest;
	}


	public void setTxtNumeroCelularDest(String txtNumeroCelularDest) {
		this.txtNumeroCelularDest = txtNumeroCelularDest;
	}


	public String getTxtNumeroCuentaDestino() {
		return txtNumeroCuentaDestino;
	}


	public void setTxtNumeroCuentaDestino(String txtNumeroCuentaDestino) {
		this.txtNumeroCuentaDestino = txtNumeroCuentaDestino;
	}


	public String getTxtNumeroServicio() {
		return txtNumeroServicio;
	}


	public void setTxtNumeroServicio(String txtNumeroServicio) {
		this.txtNumeroServicio = txtNumeroServicio;
	}


	public String getTxtServidorMail() {
		return txtServidorMail;
	}


	public void setTxtServidorMail(String txtServidorMail) {
		this.txtServidorMail = txtServidorMail;
	}


	public String getTxtNumeroSuministro() {
		return txtNumeroSuministro;
	}


	public void setTxtNumeroSuministro(String txtNumeroSuministro) {
		this.txtNumeroSuministro = txtNumeroSuministro;
	}

}
