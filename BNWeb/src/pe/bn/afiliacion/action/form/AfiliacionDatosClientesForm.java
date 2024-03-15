
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


public class AfiliacionDatosClientesForm extends AfiliacionGenericaForm{
	private static LoggerSati log3 = LoggerSati.getInstance(AfiliacionGenericaForm.class.getName());
//	


	private String cmbTipoVia;
	private String cmbOcupacion;	
	private String cmbDepartamento;
	private String cmbProvincia;
	private String cmbDistrito;
	private String cmbLocalidad;
	private String txtNombreVia= "";
	private String txtNumeroVia= "";
	private String txtManzana = "";
	private String txtPiso= "";
	private String txtBloque= "";
	private String txtReferencia= "";
	private String cmbZonaTelFijo;
	private String txtTelFijo= "";
	private String cmbZonaTelFijoLab;
	private String txtTelFijoLab= "";
	private String txtAnexo= "";
	private String txtCelular= "";
	private String txtCorreo= "";
	private String txtAdicional= "";
	private String txtLote= "";
	private String txtInterior= "";
	private String codCliente;
	private String cmbMedioEnvio;
	private String cmbDireccionCorresp;
	private String cmbCuenta;
	private String cmbCorreoCorresp;
	private String cmbOperador;
	
	//Nuevo Core - Actualizacion de datos
	
	 private String cmbPrefTelefonoDomic;
     private String txtTelefono;
     private String txtExtTelefono;
     private String cmbCodOperTelefono;
     private String cmbTipoTelefono;
     private String numeroDireccion;
     private String codigoDirElectronica;
     
     private String cmbTipoEmail;
     private String txtCodigoDirElectronica;
     private String txtDireccionElectronica;
     private String txtNumeroDireccion;
     
     private String cmbCodigoDomicilioTipoVia;
     //private String txtNombreVia;
     private String txtNumeroDomicilio;
     private String txtDomicilioPortal;
     private String txtDomicilioEscalera;
     private String txtDomicilioPlanta;
     private String txtDomicilioPuerta;
     private String txtBloqueDireccion;
     private String txtFaseDireccion;
     private String txtEdificionDireccion;
     private String txtBarrioDireccion;
     private String txtUrbanizacionDireccion;
     private String txtPoligonoDireccion;
     private String txtParcelaDireccion;
     private String txtIdInternoPe;
     private String cmbConsentimiento;
     private String cbxTipoDomicilio;
     private String cbxTipoDomicilioHabitual;
     private String cbxTipoDomicilioFiscal;
     private String cbxTipoDomicilioPostal;
     private String rdnExtranjero;
     private String txtDepartamento;
     private String txtProvincia;
     private String txtDistrito;
     private String txtLocalidad;
     private String cmbPais;
 	 private String txtTelFijoExt;
 	 private String cmbZonaTelFijoExt;
 	 private String txtExtTelefonoLab;
 	 private String txtExtTelefonoExt;
 	 
 	 

	public String getTxtExtTelefonoExt() {
		return txtExtTelefonoExt;
	}

	public void setTxtExtTelefonoExt(String txtExtTelefonoExt) {
		this.txtExtTelefonoExt = txtExtTelefonoExt;
	}

	public String getTxtExtTelefonoLab() {
		return txtExtTelefonoLab;
	}

	public void setTxtExtTelefonoLab(String txtExtTelefonoLab) {
		this.txtExtTelefonoLab = txtExtTelefonoLab;
	}

	public String getCmbZonaTelFijoExt() {
		return cmbZonaTelFijoExt;
	}

	public void setCmbZonaTelFijoExt(String cmbZonaTelFijoExt) {
		this.cmbZonaTelFijoExt = cmbZonaTelFijoExt;
	}

	public static LoggerSati getLog3() {
		return log3;
	}

	public static void setLog3(LoggerSati log3) {
		AfiliacionDatosClientesForm.log3 = log3;
	}

	public String getTxtTelFijoExt() {
		return txtTelFijoExt;
	}

	public void setTxtTelFijoExt(String txtTelFijoExt) {
		this.txtTelFijoExt = txtTelFijoExt;
	}

	public String getCmbPais() {
		return cmbPais;
	}

	public void setCmbPais(String cmbPais) {
		this.cmbPais = cmbPais;
	}

	public String getTxtDepartamento() {
		return txtDepartamento;
	}

	public void setTxtDepartamento(String txtDepartamento) {
		this.txtDepartamento = txtDepartamento;
	}

	public String getTxtDistrito() {
		return txtDistrito;
	}

	public void setTxtDistrito(String txtDistrito) {
		this.txtDistrito = txtDistrito;
	}

	public String getTxtLocalidad() {
		return txtLocalidad;
	}

	public void setTxtLocalidad(String txtLocalidad) {
		this.txtLocalidad = txtLocalidad;
	}

	public String getTxtProvincia() {
		return txtProvincia;
	}

	public void setTxtProvincia(String txtProvincia) {
		this.txtProvincia = txtProvincia;
	}

	public String getRdnExtranjero() {
		return rdnExtranjero;
	}

	public void setRdnExtranjero(String rdnExtranjero) {
		this.rdnExtranjero = rdnExtranjero;
	}

	public String getCbxTipoDomicilioFiscal() {
		return cbxTipoDomicilioFiscal;
	}

	public void setCbxTipoDomicilioFiscal(String cbxTipoDomicilioFiscal) {
		this.cbxTipoDomicilioFiscal = cbxTipoDomicilioFiscal;
	}

	public String getCbxTipoDomicilioHabitual() {
		return cbxTipoDomicilioHabitual;
	}

	public void setCbxTipoDomicilioHabitual(String cbxTipoDomicilioHabitual) {
		this.cbxTipoDomicilioHabitual = cbxTipoDomicilioHabitual;
	}

	public String getCbxTipoDomicilioPostal() {
		return cbxTipoDomicilioPostal;
	}

	public void setCbxTipoDomicilioPostal(String cbxTipoDomicilioPostal) {
		this.cbxTipoDomicilioPostal = cbxTipoDomicilioPostal;
	}

	public String getCbxTipoDomicilio() {
		return cbxTipoDomicilio;
	}

	public void setCbxTipoDomicilio(String cbxTipoDomicilio) {
		this.cbxTipoDomicilio = cbxTipoDomicilio;
	}

	public String getTxtBarrioDireccion() {
		return txtBarrioDireccion;
	}
	
	public void setTxtBarrioDireccion(String txtBarrioDireccion) {
		this.txtBarrioDireccion = txtBarrioDireccion;
	}


	public String getTxtBloqueDireccion() {
		return txtBloqueDireccion;
	}


	public void setTxtBloqueDireccion(String txtBloqueDireccion) {
		this.txtBloqueDireccion = txtBloqueDireccion;
	}


	public String getTxtDomicilioEscalera() {
		return txtDomicilioEscalera;
	}


	public void setTxtDomicilioEscalera(String txtDomicilioEscalera) {
		this.txtDomicilioEscalera = txtDomicilioEscalera;
	}


	public String getTxtDomicilioPlanta() {
		return txtDomicilioPlanta;
	}


	public void setTxtDomicilioPlanta(String txtDomicilioPlanta) {
		this.txtDomicilioPlanta = txtDomicilioPlanta;
	}


	public String getTxtDomicilioPortal() {
		return txtDomicilioPortal;
	}


	public void setTxtDomicilioPortal(String txtDomicilioPortal) {
		this.txtDomicilioPortal = txtDomicilioPortal;
	}


	public String getTxtDomicilioPuerta() {
		return txtDomicilioPuerta;
	}


	public void setTxtDomicilioPuerta(String txtDomicilioPuerta) {
		this.txtDomicilioPuerta = txtDomicilioPuerta;
	}


	public String getTxtEdificionDireccion() {
		return txtEdificionDireccion;
	}


	public void setTxtEdificionDireccion(String txtEdificionDireccion) {
		this.txtEdificionDireccion = txtEdificionDireccion;
	}


	public String getTxtFaseDireccion() {
		return txtFaseDireccion;
	}


	public void setTxtFaseDireccion(String txtFaseDireccion) {
		this.txtFaseDireccion = txtFaseDireccion;
	}


	public String getTxtNumeroDomicilio() {
		return txtNumeroDomicilio;
	}


	public void setTxtNumeroDomicilio(String txtNumeroDomicilio) {
		this.txtNumeroDomicilio = txtNumeroDomicilio;
	}


	public String getTxtParcelaDireccion() {
		return txtParcelaDireccion;
	}


	public void setTxtParcelaDireccion(String txtParcelaDireccion) {
		this.txtParcelaDireccion = txtParcelaDireccion;
	}


	public String getTxtPoligonoDireccion() {
		return txtPoligonoDireccion;
	}


	public void setTxtPoligonoDireccion(String txtPoligonoDireccion) {
		this.txtPoligonoDireccion = txtPoligonoDireccion;
	}


	public String getTxtUrbanizacionDireccion() {
		return txtUrbanizacionDireccion;
	}


	public void setTxtUrbanizacionDireccion(String txtUrbanizacionDireccion) {
		this.txtUrbanizacionDireccion = txtUrbanizacionDireccion;
	}


	public String getTxtNumeroDireccion() {
		return txtNumeroDireccion;
	}


	public void setTxtNumeroDireccion(String txtNumeroDireccion) {
		this.txtNumeroDireccion = txtNumeroDireccion;
	}


	public String getCmbTipoEmail() {
		return cmbTipoEmail;
	}


	public void setCmbTipoEmail(String cmbTipoEmail) {
		this.cmbTipoEmail = cmbTipoEmail;
	}


	public String getTxtCodigoDirElectronica() {
		return txtCodigoDirElectronica;
	}


	public void setTxtCodigoDirElectronica(String txtCodigoDirElectronica) {
		this.txtCodigoDirElectronica = txtCodigoDirElectronica;
	}


	public String getTxtDireccionElectronica() {
		return txtDireccionElectronica;
	}


	public void setTxtDireccionElectronica(String txtDireccionElectronica) {
		this.txtDireccionElectronica = txtDireccionElectronica;
	}


	public String getCodigoDirElectronica() {
		return codigoDirElectronica;
	}


	public void setCodigoDirElectronica(String codigoDirElectronica) {
		this.codigoDirElectronica = codigoDirElectronica;
	}


	public String getNumeroDireccion() {
		return numeroDireccion;
	}


	public void setNumeroDireccion(String numeroDireccion) {
		this.numeroDireccion = numeroDireccion;
	}


	public String getCmbTipoTelefono() {
		return cmbTipoTelefono;
	}


	public void setCmbTipoTelefono(String cmbTipoTelefono) {
		this.cmbTipoTelefono = cmbTipoTelefono;
	}


	public String getCmbCodOperTelefono() {
		return cmbCodOperTelefono;
	}


	public void setCmbCodOperTelefono(String cmbCodOperTelefono) {
		this.cmbCodOperTelefono = cmbCodOperTelefono;
	}


	public String getCmbPrefTelefonoDomic() {
		return cmbPrefTelefonoDomic;
	}


	public void setCmbPrefTelefonoDomic(String cmbPrefTelefonoDomic) {
		this.cmbPrefTelefonoDomic = cmbPrefTelefonoDomic;
	}


	public String getTxtExtTelefono() {
		return txtExtTelefono;
	}


	public void setTxtExtTelefono(String txtExtTelefono) {
		this.txtExtTelefono = txtExtTelefono;
	}


	

	public String getTxtTelefono() {
		return txtTelefono;
	}


	public void setTxtTelefono(String txtTelefono) {
		this.txtTelefono = txtTelefono;
	}


	public String getCmbOperador() {
		return cmbOperador;
	}


	public void setCmbOperador(String cmbOperador) {
		this.cmbOperador = cmbOperador;
	}


	public String getCmbCorreoCorresp() {
		return cmbCorreoCorresp;
	}


	public void setCmbCorreoCorresp(String cmbCorreoCorresp) {
		this.cmbCorreoCorresp = cmbCorreoCorresp;
	}


	public String getCmbDireccionCorresp() {
		return cmbDireccionCorresp;
	}


	public void setCmbDireccionCorresp(String cmbDireccionCorresp) {
		this.cmbDireccionCorresp = cmbDireccionCorresp;
	}


	public String getCmbMedioEnvio() {
		return cmbMedioEnvio;
	}


	public void setCmbMedioEnvio(String cmbMedioEnvio) {
		this.cmbMedioEnvio = cmbMedioEnvio;
	}


	public String getCodCliente() {
		return codCliente;
	}


	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}


	public String getTxtInterior() {
		return txtInterior;
	}


	public void setTxtInterior(String txtInterior) {
		this.txtInterior = txtInterior;
	}


	public String getTxtLote() {
		return txtLote;
	}


	public void setTxtLote(String txtLote) {
		this.txtLote = txtLote;
	}


	public String getTxtBloque() {
		return txtBloque;
	}


	public void setTxtBloque(String txtBloque) {
		this.txtBloque = txtBloque;
	}


	public String getCmbDepartamento() {
		return cmbDepartamento;
	}


	public void setCmbDepartamento(String cmbDepartamento) {
		this.cmbDepartamento = cmbDepartamento;
	}


	public String getCmbDistrito() {
		return cmbDistrito;
	}


	public void setCmbDistrito(String cmbDistrito) {
		this.cmbDistrito = cmbDistrito;
	}


	public String getCmbProvincia() {
		return cmbProvincia;
	}


	public void setCmbProvincia(String cmbProvincia) {
		this.cmbProvincia = cmbProvincia;
	}


	public String getCmbTipoVia() {
		return cmbTipoVia;
	}


	public void setCmbTipoVia(String cmbTipoVia) {
		this.cmbTipoVia = cmbTipoVia;
	}


	public String getCmbZonaTelFijo() {
		return cmbZonaTelFijo;
	}


	public void setCmbZonaTelFijo(String cmbZonaTelFijo) {
		this.cmbZonaTelFijo = cmbZonaTelFijo;
	}


	public String getCmbZonaTelFijoLab() {
		return cmbZonaTelFijoLab;
	}


	public void setCmbZonaTelFijoLab(String cmbZonaTelFijoLab) {
		this.cmbZonaTelFijoLab = cmbZonaTelFijoLab;
	}


	public String getTxtAdicional() {
		return txtAdicional;
	}


	public void setTxtAdicional(String txtAdicional) {
		this.txtAdicional = txtAdicional;
	}


	public String getTxtAnexo() {
		return txtAnexo;
	}


	public void setTxtAnexo(String txtAnexo) {
		this.txtAnexo = txtAnexo;
	}


	public String getTxtCelular() {
		return txtCelular;
	}


	public void setTxtCelular(String txtCelular) {
		this.txtCelular = txtCelular;
	}


	public String getTxtCorreo() {
		return txtCorreo;
	}


	public void setTxtCorreo(String txtCorreo) {
		this.txtCorreo = txtCorreo;
	}


	public String getTxtManzana() {
		return txtManzana;
	}


	public void setTxtManzana(String txtManzana) {
		this.txtManzana = txtManzana;
	}


	public String getTxtNombreVia() {
		return txtNombreVia;
	}


	public void setTxtNombreVia(String txtNombreVia) {
		this.txtNombreVia = txtNombreVia;
	}


	public String getTxtNumeroVia() {
		return txtNumeroVia;
	}


	public void setTxtNumeroVia(String txtNumeroVia) {
		this.txtNumeroVia = txtNumeroVia;
	}


	public String getTxtPiso() {
		return txtPiso;
	}


	public void setTxtPiso(String txtPiso) {
		this.txtPiso = txtPiso;
	}


	public String getTxtReferencia() {
		return txtReferencia;
	}


	public void setTxtReferencia(String txtReferencia) {
		this.txtReferencia = txtReferencia;
	}


	public String getTxtTelFijo() {
		return txtTelFijo;
	}


	public void setTxtTelFijo(String txtTelFijo) {
		this.txtTelFijo = txtTelFijo;
	}


	public String getTxtTelFijoLab() {
		return txtTelFijoLab;
	}


	public void setTxtTelFijoLab(String txtTelFijoLab) {
		this.txtTelFijoLab = txtTelFijoLab;
	}


	public ActionErrors validate(ActionMapping arg0, HttpServletRequest request) {
		super.validate(arg0, request);
		ActionErrors errors = new ActionErrors();	
		String validar = request.getParameter("validar");
		
		if(validar == null)
			return null;
		
		if(validar!=null && validar.equals("false"))
			return errors;
		
		if (ObjectUtil.isStringBlank(cmbTipoVia)){
			errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.GENERICO,"Seleccione el Tipo de Via"));
			loadObject(request);
			return errors;
		}
		
				
	
		
		
		return errors;		
	}
	
	
	private void loadObject(HttpServletRequest request) {
		try {
		request.setAttribute("lstTipoVia", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.DAT_CLIENTES_TIPO_VIA));
		request.setAttribute("lstDepartamento", FacadeFactory.getGeneralFacade().getComboDepartamento());
		request.setAttribute("lstProvincia", FacadeFactory.getGeneralFacade().getComboProvincia("00"));
		request.setAttribute("lstDistrito", FacadeFactory.getGeneralFacade().getComboDistrito("00"));
		

			
		} catch (Exception e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			e.printStackTrace();
			request.setAttribute("lstTipoVia", new ArrayList());
			request.setAttribute("lstProvincia", new ArrayList());
			request.setAttribute("lstDistrito", new ArrayList());
		
		
		}
	}
	

	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		//super.reset(arg0, arg1);
		cmbMedioEnvio		        = "";
		cmbCorreoCorresp		    = "";
		cmbDireccionCorresp		    = "";
		cmbDepartamento	   			 = "";
		cmbProvincia	    		= "";
		cmbDistrito	    			= "";
		txtNombreVia	    		= "";
		txtNumeroVia				= "";
		txtManzana 					= "";
		txtPiso						= "";
		txtBloque					= "";
		txtReferencia				= "";
		txtInterior					= "";
		cmbTipoVia					= "";
		txtLote						= "";
		cmbPrefTelefonoDomic		= "";
	    txtTelefono					= "";
	    txtExtTelefono				= "";
	    cmbCodOperTelefono			= "";
	    cmbTipoTelefono				= "";
	    numeroDireccion				= "";
	    codigoDirElectronica		= "";
	    cmbTipoEmail				= "";
	    txtCodigoDirElectronica		= "";
	    txtDireccionElectronica		= "";
	    txtNumeroDireccion			= "";
	    txtTelFijo					= "";
	    txtTelFijoLab				= "";
	    cmbZonaTelFijo				= "";
	    cmbZonaTelFijoLab			= "";
	    txtCelular					= "";
	    cmbCodOperTelefono			= "";
	    cbxTipoDomicilioHabitual	= "";
	    cbxTipoDomicilioFiscal		= "";
	    cbxTipoDomicilioPostal		= "";
	    
	    cmbConsentimiento			= "";
	    cbxTipoDomicilio			= "";
	    cbxTipoDomicilioHabitual	= "";
	    cbxTipoDomicilioFiscal		= "";
	    cbxTipoDomicilioPostal		= "";
	    rdnExtranjero				= "";
	    txtDepartamento				= "";
	    txtProvincia				= "";
	    txtDistrito					= "";
	    txtLocalidad				= "";
	    cmbPais						= "";
	    txtNumeroDomicilio			= "";
	    txtTelFijoExt				= "";
	    cmbZonaTelFijoExt			= "";
	    cmbCodigoDomicilioTipoVia	= "";
	    txtNombreVia				= "";
	    txtNumeroDomicilio			= "";
	    txtDomicilioPortal			= "";
	    txtDomicilioEscalera		= "";
	    txtDomicilioPlanta			= "";
	    txtDomicilioPuerta			= "";
	    txtBloqueDireccion			= "";
	    txtEdificionDireccion		= "";
	    txtFaseDireccion			= "";
	    txtPoligonoDireccion		= "";
	    txtParcelaDireccion			= "";
	    txtBarrioDireccion			= "";
	    txtReferencia				= "";
	    txtUrbanizacionDireccion	= "";
	    
	}


	public String getCmbCuenta() {
		return cmbCuenta;
	}


	public void setCmbCuenta(String cmbCuenta) {
		this.cmbCuenta = cmbCuenta;
	}

	public String getCmbCodigoDomicilioTipoVia() {
		return cmbCodigoDomicilioTipoVia;
	}

	public void setCmbCodigoDomicilioTipoVia(String cmbCodigoDomicilioTipoVia) {
		this.cmbCodigoDomicilioTipoVia = cmbCodigoDomicilioTipoVia;
	}

	public String getCmbLocalidad() {
		return cmbLocalidad;
	}

	public void setCmbLocalidad(String cmbLocalidad) {
		this.cmbLocalidad = cmbLocalidad;
	}

	public String getTxtIdInternoPe() {
		return txtIdInternoPe;
	}

	public void setTxtIdInternoPe(String txtIdInternoPe) {
		this.txtIdInternoPe = txtIdInternoPe;
	}

	public String getCmbConsentimiento() {
		return cmbConsentimiento;
	}

	public void setCmbConsentimiento(String cmbConsentimiento) {
		this.cmbConsentimiento = cmbConsentimiento;
	}

	public String getCmbOcupacion() {
		return cmbOcupacion;
	}

	public void setCmbOcupacion(String cmbOcupacion) {
		this.cmbOcupacion = cmbOcupacion;
	}
	
}
