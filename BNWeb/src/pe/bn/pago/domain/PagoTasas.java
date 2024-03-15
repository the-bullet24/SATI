/*
 * Creado el 22/06/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.bn.pago.domain;

import java.math.BigDecimal;
import java.util.List;

import pe.cosapi.domain.Ciudad;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.DetalleTributo;
import pe.cosapi.domain.Documento;
import pe.cosapi.domain.Entidad;
import pe.cosapi.domain.Pago;
import pe.cosapi.domain.Tributo;

/**
 * @author cosapi_ati02
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public interface PagoTasas extends Pago{
	/**
	 * @return Devuelve ciudad.
	 */
	public abstract Ciudad getCiudad();

	/**
	 * @param ciudad El ciudad a establecer.
	 */
	public abstract void setCiudad(Ciudad ciudad);

	/**
	 * @return Devuelve cuenta.
	 */
	public abstract Cuenta getCuenta();

	/**
	 * @param cuenta El cuenta a establecer.
	 */
	public abstract void setCuenta(Cuenta cuenta);

	/**
	 * @return Devuelve documento.
	 */
	public abstract Documento getDocumento();

	/**
	 * @param documento El documento a establecer.
	 */
	public abstract void setDocumento(Documento documento);

	/**
	 * @return Devuelve entidad.
	 */
	public abstract Entidad getEntidad();

	/**
	 * @param entidad El entidad a establecer.
	 */
	public abstract void setEntidad(Entidad entidad);

	/**
	 * @return Devuelve tributo.
	 */
	public abstract Tributo getTributo();

	/**
	 * @param tributo El tributo a establecer.
	 */
	public abstract void setTributo(Tributo tributo);
	
	public boolean getMostrarDetalleTributo();
	
	public boolean getMostrarCiudad();
	
	public List getDocumentos(List lista);
	
	public void setDetalle(String codigo);

	public DetalleTributo getDetalle();
	
	public BigDecimal getTipoCambio();
	
	public abstract String getCodTransaccion();
	
	public void setCodTransaccion(String codTransaccion);
	
	public  String getCodUsuario();
	
	public  void setCodUsuario(String codUsuario);
	
	public  String getCodAgencia();
	
	public  void setCodAgencia(String codAgencia);
	
	public  String getFecha();
	
	public  void setFecha(String fecha);
	
	public  String getHora();
	
	public  void setHora(String hora);
	
	public  String getFechaOpe();
	
	public  void setFechaOpe(String fechaOpe);
	
	public  String getHoraOpe();
	
	public  void setHoraOpe(String horaOpe);
	
	public  String getAdministradorTrib();
	
	public  void setAdministradorTrib(String administradorTrib);
	
	public  String getNumeroSecuencia();
	
	public  void setNumeroSecuencia(String numeroSecuencia);
	
	public  String getNomtribcbo();
	
	public  void setNomtribcbo(String nomtribcbo);
	
	public  String getDigitoControl();
	
	public  void setDigitoControl(String digitoControl);
	
	
	public abstract String getTipoDoc();
	
	public abstract void setTipoDoc(String value);
	
	public abstract String getFlgDetalleTributoVisible();
	
	public abstract void setFlgDetalleTributoVisible(String value);

	public abstract String getFlgCiudadVisible();
	
	public abstract void setFlgCiudadVisible(String value);

	public abstract String getFlgDistritoJuzgadoVisible();
	
	public abstract void setFlgDistritoJuzgadoVisible(String value);

	public abstract String getFlgExpedienteVisible();
	
	public abstract void setFlgExpedienteVisible(String value);
	
	public abstract String getTipoImporte();
	
	public abstract void setTipoImporte(String value);
	
	public abstract String getFlgConfig();
	
	public abstract void setFlgConfig(String value);
	
	public abstract String getFlgCantidadVisible();
	
	public abstract void setFlgCantidadVisible(String value);
	
	public abstract String getCantidad();
	
	public abstract void setCantidad(String value);
	
	public abstract String getCodDependencia();
	
	public abstract void setCodDependencia(String value);
	
	public abstract String getDescripcionDependencia();
	
	public abstract void setDescripcionDependencia(String value);
	
	public abstract String getPlantilla();
	
	public abstract void setPlantilla(String value);
	
	public abstract String getNroExpediente();
	
	public abstract void setNroExpediente(String value);

	public abstract String getImporteTotal();
	
	public void setImporteTotal(BigDecimal value);

	public String getImporteTotalAlCambio();
	
	public void setImporteTotalAlCambio(BigDecimal value);
	
}