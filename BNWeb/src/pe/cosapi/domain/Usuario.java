/*
 * Creado el 12/02/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.cosapi.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import pe.bn.cldinamica.action.form.ParametrosTDC;
import pe.bn.login.domain.IngresoTarjeta;
import pe.bn.tarjeta.domain.Tarjeta;
import pe.cosapi.domain.impl.CuentaImpl;
import pe.cosapi.sarabank.bean.Transaction;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public interface Usuario extends Serializable {

	public List getCuentas();
	/**
	 * @return Devuelve nombre.
	 */
	public String getNombre();
	
	public String getNumDocumento();
	public String getTipoDocumento();
	public String getFechaNacimiento();
	/**
	 * @return Devuelve tipoPersona.
	 */
	public String getTipoPersona();

	/**
	 * @return Devuelve tipoIngreso.
	 */
	public String getTipoIngreso();
	
	public Tarjeta getTarjeta();
	
	public Notificacion getNotificacion();
	public void setNotificacion(Notificacion notificacion);
	
	public NotificacionClave getNotificacionClave();
	public void setNotificacionClave(NotificacionClave notificacionClave);
	
	public AtriTransferencia getEstadoOperaciones();
	public void setEstadoOperaciones(AtriTransferencia estadoOperaciones);
	
	public Map getMapCuentas();
	public List getListCuentas();
	
	public abstract CuentaImpl getCuentaAhorro();
	public abstract CuentaImpl getCuentaCorriente();
	 
	public String getNombreTipoPersona();
	
	public void setCuentas(List cuentas);
	public void setListCuentas(List cuentas);
	public void setNombre(String nombre);
	public void setNumDocumento(String numDocumento);
	public void setTipoDocumento(String tipoDocumento);
	public void setFechaNacimiento(String fechaNacimiento);
	public void setTipoPersona(String tipoPersona);
	public void setTipoIngreso(String tipoIngreso);
	public void setMapCuentas(Map mapCuentas);
	public void setNombreTipoPersona(String nombreTipoPersona);
	public void setTarjeta(Tarjeta tarjeta);
	public void setTipoCambio(TipoCambio tipoCambio);
	public TipoCambio getTipoCambio();
	
	public abstract String getTipoTarjeta();
    public abstract void setTipoTarjeta(String tipoTarjeta);
    
    public abstract void actualizarCuentas(Transaction t) throws Exception;
    
    public abstract void validarMontoHost(Transaction t) throws Exception;
    public abstract void ValidaCtaCte(Transaction t) throws Exception;
    
    public void setFechaIngreso(String fechaIngreso);
	public String getFechaIngreso();
	
	//Se agrega campo para guardar datos del ingreso
	
	public void setIngreso(IngresoTarjeta ingreso);
	public IngresoTarjeta getIngreso();
	public void  setClaveDinamica(ParametrosTDC claveDinamica);
	public  ParametrosTDC getClaveDinamica();
	
	public void setFlagAfiliacion(String flagAfiliacion);
	public String getFlagAfiliacion();
	
	public void setEmail(String email);
	public String getEmail();
	
	public void setCelular(String celular);
	public String getCelular();
	
	public void setOpeCelular(String opeCelular);
	public String getOpeCelular();
	
	public void setCelularFormat(String celularFormat);
	public String getCelularFormat();
	
	
	public void setCodPerfil(String codPerfil);
	public String getCodPerfil();
	
	public void setMontoLimite(String montoLimite);
	public String getMontoLimite();
	
	public void setCodMontoLimite(String codMontoLimite);
	public String getCodMontoLimite();
	
	public void setCodigoCLDI(String codigoCLDI);
	public String getCodigoCLDI();
	
	
	public void setCodigoCic(String codigoCic);
	public String getCodigoCic();
	
	public void setFlagBannerPrestamo(String flagBannerPrestamo);
	public String getFlagBannerPrestamo();
	
	public void setMontoMaxPrestamo(String montoMaxPrestamo);
	public String getMontoMaxPrestamo();
	
	public void setFlagActualizaDato(Boolean flagActualizaDato);
	public Boolean getFlagActualizaDato();
	
	public void setFlagActualizaDatoHost(String flagActualizaDatoHost);
	public String getFlagActualizaDatoHost();
	
	public List getTarjetas();
	public void setTarjetas(List tarjetas);
	
	
	public void setCodCliente(String codcliente);
	public String getCodCliente();
	public void setCciCliente(String cciCliente);
	public String getCciCliente();
	public void setEntidadCuenta(String entidadCuenta);
	public String getEntidadCuenta();
	public void setEntidadCuentaDesc(String entidadCuentaDesc);
	public String getEntidadCuentaDesc();
	public void setIndTransfContacto(String indTransfContacto);
	public String getIndTransfContacto();
	
}
