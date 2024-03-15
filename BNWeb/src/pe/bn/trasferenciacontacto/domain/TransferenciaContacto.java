package pe.bn.trasferenciacontacto.domain;

import java.io.Serializable;

import pe.cosapi.domain.Cuenta;

public interface TransferenciaContacto extends Serializable {
	
	public abstract Cuenta getCuentaOrigen();
	public abstract Cuenta setCuentaOrigen(Cuenta cuentaOrigen);
	public abstract String getCuenta();
	public abstract String setCuenta(String cuenta);
	public abstract String getNumOperacion();
	public abstract String setNumOperacion(String numOperacion);
	public abstract String getFechaAfiliacion();
	public abstract String setFechaAfiliacion(String fechaAfiliacion);
	public abstract String getNombreClienteContacto();
	public abstract String setNombreClienteContacto(String nombreClienteContacto);
	public abstract String getNroCuentaContacto();
	public abstract String setNroCuentaContacto(String nroCuentaContacto);
	public abstract String getNumeroCelularContacto();
	public abstract String setNumeroCelularContacto(String numeroCelularContacto);
	public abstract String getEstadoAfil();
	public abstract String setEstadoAfil(String estadoAfil);
	public abstract String getCodigoErr();
	public abstract String setCodigoErr(String codigoErr);
	public abstract String getCciContacto();
	public abstract String setCciContacto(String cciContacto);
	public abstract String getCodigoEntidad();
	public abstract String setCodigoEntidad(String codigoEntidad);
	public abstract String getDescEntidad();
	public abstract String setDescEntidad(String descEntidad);
	public abstract String getCodMotivoDesf();
	public abstract String setCodMotivoDesf(String codMotivoDesf);
	public abstract String getComentarioDesf();
	public abstract String setComentarioDesf(String comentarioDesf);
	public abstract String getNumeroCelularCambio();
	public abstract String setNumeroCelularCambio(String numeroCelularCambio);
	public abstract String getFechaDesafiliacion();
	public abstract String setFechaDesafiliacion(String fechaDesafiliacion);
	public abstract String getDescripMotivoDesf();
	public abstract String setDescripMotivoDesf(String descripMotivoDesf);
	public abstract String getOperadorCelularCambio();
	public abstract String setOperadorCelularCambio(String operadorCelularCambio);
	public abstract String getIndCambioCelular();
	public abstract String setIndCambioCelular(String indCambioCelular);
	public abstract String getIndTransferenciaContacto();
	public abstract String setIndTransferenciaContacto(String indTransferenciaContacto);
	
	
	
}
