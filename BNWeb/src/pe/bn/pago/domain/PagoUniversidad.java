/*
 * Creado el 12/03/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.bn.pago.domain;

import java.util.List;

import java.math.BigDecimal;

import pe.bn.afiliacion.domain.Afiliacion;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.Operacion;
import pe.cosapi.domain.impl.ReciboImpl;

/**
 * @author cosapi_ati02
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public interface PagoUniversidad extends Operacion {
	
	public abstract String getHora();
	
	public abstract void setHora(String hora);
	
	public abstract void setFecha(String fecha);
	
	public abstract String getFecha();
	
	public abstract void setFechaSistema(String fechaSistema);
	
	public abstract String getFechaSistema();
	
	/**
	 * @return Devuelve cuenta.
	 */
	public abstract Cuenta getCuenta();

	/**
	 * @param cuenta El cuenta a establecer.
	 */
	public abstract void setCuenta(Cuenta cuenta);

	public abstract String getCodUniversidad();
	
	public abstract void setCodUniversidad(String codUniversidad);
	
	public abstract String getDescUniversidad();
	
	public abstract void setDescUniversidad(String descUniversidad);
	
	public abstract String getConcepto();
	
	public abstract void setConcepto(String concepto);
	
	public abstract String getTipoPersona();
	
	public abstract void setTipoPersona(String tipoPersona);
	
	public abstract String getCodSituacion();
	
	public abstract void setCodSituacion(String codSituacion);
	
	public abstract String getSede();
	
	public abstract void setSede(String sede);
	
	public abstract String getNombreCompleto();
	
	public abstract void setNombreCompleto(String nombreCompleto);
	
	public abstract BigDecimal getImportePago();
	
	public abstract void setImportePago(BigDecimal importePago);
	
	public abstract String getOpcionPago();
	
	public abstract void setOpcionPago(String opcionPago);
	
	public abstract String getTipoDoc();
	
	public abstract void setTipoDoc(String tipoDoc);
	
	public abstract String getNroDoc();
	
	public abstract void setNroDoc(String nroDoc);
	
	public abstract String getTipoDocDesc();
	
	public abstract void setTipoDocDesc(String tipoDocDesc);
	
	public abstract String getCodAlumno();
	
	public abstract void setCodAlumno(String codAlumno);
	
	public abstract String getSedeDesc();
	
	public abstract void setSedeDesc(String sedeDesc);
	
	public abstract String getSituacionDesc();
	
	public abstract void setSituacionDesc(String situacionDesc);

	public abstract String getConceptoDesc();
	
	public abstract void setConceptoDesc(String conceptoDesc);
	

	public abstract String getItf();
	
	public abstract void setItf(String itf);
	

	public abstract String getTotal();
	
	public abstract void setTotal(String total);
	
	public abstract String getDigitoChequeo();
	
	public abstract void setDigitoChequeo(String digitoChequeo);
	
	public abstract String getFlagImporte();
	
	public abstract void setFlagImporte(String flagImporte);
	
	public abstract String getCodTransaccion();
	
	public abstract void setCodTransaccion(String codTransaccion);
	
	public abstract String getCodUsuario();
	
	public abstract void setCodUsuario(String codUsuario);
	
	public abstract String getCodAgencia();
	
	public abstract void setCodAgencia(String codAgencia);
	
	public abstract String getNumeroSecuencia();
	
	public abstract void setNumeroSecuencia(String numeroSecuencia);
	
	public abstract String getDigitoControl();
	
	public abstract void setDigitoControl(String digitoControl);
}