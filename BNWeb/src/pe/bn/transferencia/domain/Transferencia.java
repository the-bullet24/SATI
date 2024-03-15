/*
 * Creado el 23/03/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.bn.transferencia.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import pe.bn.afiliacion.domain.Afiliacion;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.Operacion;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public interface Transferencia extends Operacion, Serializable{
	/**
	 * @return Devuelve afiliacion.
	 */
	public abstract Afiliacion getAfiliacion();

	/**
	 * @return Devuelve comision.
	 */
	public abstract String getComision();

	/**
	 * @return Devuelve cuenta.
	 */
	public abstract Cuenta getCuenta();

	/**
	 * @return Devuelve importe.
	 */
	public abstract String getImporte();

	/**
	 * @return Devuelve itf.
	 */
	public abstract String getItf();

	/**
	 * @return Devuelve total.
	 */
	public abstract String getTotal();
	
	
	public abstract Cuenta getCuentaDestino();
	public abstract void setCuentaDestino(Cuenta cuentaDestino);
	
	public abstract Cuenta getCuentaOrigen();
	
	public abstract String getTipoAfectacion();
    public abstract void setTipoAfectacion(String tipoAfectacion);
    
    public abstract BigDecimal getComisionIB();
    public abstract void setComisionIB(BigDecimal comisionIB);
     
    public abstract String getImporteTransferido();
    public abstract void setImporteTransferido(String importeTransferido);
     
    public abstract boolean isInterbancaria();
    public abstract void setInterbancaria(boolean interbancaria);
    
    public abstract boolean isInterbancariaLinea();
    public abstract void setInterbancariaLinea(boolean interbancariaLinea);
    
	public abstract BigDecimal getImporteCargo();
	public abstract void setImporteCargo(BigDecimal importeCargo);
	
	public abstract BigDecimal getImporteConvertido();
	public abstract void setImporteConvertido(BigDecimal importeConvertido);
	
	public abstract BigDecimal getComisionAbono();
	public abstract void setComisionAbono(BigDecimal comisionAbono);
	
	public abstract BigDecimal getImporteAbonar();
	public abstract void setImporteAbonar(BigDecimal importeAbonar);
	
	public abstract BigDecimal getItfAbono();
	public abstract void setItfAbono(BigDecimal itfAbono);
	
	 public abstract String getSimboloMonedaAbono();
	 public abstract void setSimboloMonedaAbono(String simboloMonedaAbono);
	     
	 public abstract String getSimboloMonedaComision();
	 public abstract void setSimboloMonedaComision(String simboloMonedaComision);
	 public abstract String getSimboloMonedaImpNetoCargo();
	 public abstract void setSimboloMonedaImpNetoCargo(String simboloMonedaImpNetoCargo);
	 public abstract String getSimboloMonedaImporte();
	 public abstract void setSimboloMonedaImporte(String simboloMonedaImporte);
	 public abstract String getSimboloMonedaItf();
	 public abstract void setSimboloMonedaItf(String simboloMonedaItf);
	 public abstract String getSimboloMonedaTipoCambio();
	 public abstract void setSimboloMonedaTipoCambio(String simboloMonedaTipoCambio);
	 public abstract String getBeneficiario();
	 public abstract void setBeneficiario(String beneficiario);
	 public abstract String getNombreMonedaMail();
	 public abstract String getCodMoneda();
	 public abstract void setCodMoneda(String codMoneda);
	 public abstract String getCuentaPropia();
	 public abstract void setCuentaPropia(String cuentaPropia);
	 public abstract String getCuentaPropiaDes();
	 public abstract void setCuentaPropiaDes(String cuentaPropiaDes);
	 public abstract String getTipoPlaza();
	 public abstract void setTipoPlaza(String tipoPlaza);
	 public abstract BigDecimal getComisionIBConvertido();
	 public abstract void setComisionIBConvertido(BigDecimal comisionIBConvertido);
	 
	 public abstract String getMotivo();
	 public abstract void setMotivo(String motivo) ;
	 public abstract String getFlagRefrendo() ;
	 public abstract void setFlagRefrendo(String flagRefrendo);
	 public abstract String getEntidadDesc() ;
	 public abstract void setEntidadDesc(String entidadDesc);
	 public abstract String getCciContacto() ;
	 public abstract void setCciContacto(String cciContacto);
	 public abstract String getCciOrigen() ;
	 public abstract void setCciOrigen(String cciOrigen);
	 
}