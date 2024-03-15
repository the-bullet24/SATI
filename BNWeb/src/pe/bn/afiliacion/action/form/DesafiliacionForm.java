package pe.bn.afiliacion.action.form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import pe.bn.afiliacion.action.DesafCableAction;
import pe.bn.afiliacion.domain.impl.AfiliacionImpl;
import pe.bn.tarjeta.domain.Tarjeta;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteError;
import pe.cosapi.common.FacadeFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.common.SeguridadUtil;
import pe.cosapi.domain.Usuario;

public class DesafiliacionForm extends ActionForm{
	private static LoggerSati log3 = LoggerSati.getInstance(DesafiliacionForm.class.getName());
	
	private String cboAfiliacion;
	private String cboNombre;
	private String hidDescAfiliacion;
	private String hidDescNombre;
	
	public String getCboAfiliacion() {
		return cboAfiliacion;
	}
	public void setCboAfiliacion(String cboAfiliacion) {
		this.cboAfiliacion = cboAfiliacion;
	}
	public String getCboNombre() {
		return cboNombre;
	}
	public void setCboNombre(String cboNombre) {
		this.cboNombre = cboNombre;
	}
	
	public String getHidDescAfiliacion() {
		return hidDescAfiliacion;
	}
	public void setHidDescAfiliacion(String hidDescAfiliacion) {
		this.hidDescAfiliacion = hidDescAfiliacion;
	}
	public String getHidDescNombre() {
		return hidDescNombre;
	}
	public void setHidDescNombre(String hidDescNombre) {
		this.hidDescNombre = hidDescNombre;
	}
	

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		
		ActionErrors errors = new ActionErrors();
		String validar = request.getParameter("validar");
		
		if(validar == null)
			return null;
		
		if(validar!=null && validar.equals("false"))
			return errors;
		
		if (ObjectUtil.isStringBlank(cboAfiliacion)){
			errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.MODULO_LOGIN_CAMPO_OBLIGATORIO,"Tipo de Afiliacion"));
			loadInicio(request);
			return errors;
		}
		
		if (cboNombre.equals(Constante.COD_INT_BLANK)){
			errors.add(Constante.GLOBAL_MESSAGE, new ActionError(ConstanteError.MODULO_LOGIN_CAMPO_OBLIGATORIO,"Nombre"));
			loadProcess(request);
			return errors;
		}
		
		return errors;
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		this.cboAfiliacion 			= "";
		this.cboNombre	   			= ""; 
		this.hidDescAfiliacion		= "";
		this.hidDescNombre			= "";
	}
	
	private void loadInicio(HttpServletRequest request) {
		try {
			List lstNombre = new ArrayList();
			lstNombre.add(0,getAfliciacionImpl("0","Seleccionar..."));
			request.setAttribute("lstAfiliacion", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.TIPO_SERVICIO));
			request.setAttribute("lstNombre", lstNombre);
		} catch (Exception e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			request.setAttribute("lstAfiliacion", new ArrayList());
			request.setAttribute("lstNombre", new ArrayList());
		}
		
	}
	
	private AfiliacionImpl getAfliciacionImpl(String secuencia,String Descripcion){
		AfiliacionImpl af = new AfiliacionImpl();
		af.setSecuencia(new Long(secuencia));
		af.setDescripcion("Seleccione...");
		return af;
   }
   
	private void loadProcess(HttpServletRequest request) {
		try {
			Usuario usuario  = SeguridadUtil.getUsuarioSession(request);
			Tarjeta tarjeta = usuario.getTarjeta();
			List lstNombre = FacadeFactory.getAfiliacionFacade().getAfiliacionByValues(Constante.TIPO_SERVICIO,this.cboAfiliacion, tarjeta.getNumero());
			lstNombre.add(0,getAfliciacionImpl("0","Seleccionar..."));
			
			request.setAttribute("lstAfiliacion", FacadeFactory.getGeneralFacade().getComboDetalleAyuda(Constante.TIPO_SERVICIO));
			request.setAttribute("lstNombre", lstNombre);
		} catch (Exception e) {
			log3.error(e,Constante.ERR_LOGICA_NEGOCIO,"");
			request.setAttribute("lstAfiliacion", new ArrayList());
			request.setAttribute("lstNombre", new ArrayList());
		}
		
	}
}
