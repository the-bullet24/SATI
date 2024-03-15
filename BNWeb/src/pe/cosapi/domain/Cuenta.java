/*
 * Creado el 23/02/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.cosapi.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import pe.cosapi.sarabank.bean.Transaction;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public interface Cuenta extends Operacion, Serializable{
	/**
	 * @return Devuelve monedaProducto.
	 */
	public abstract String getMonedaProducto();

	/**
	 * @return Devuelve numeroProducto.
	 */
	public abstract String getNumeroProducto();

	/**
	 * @return Devuelve saldo.
	 */
	public abstract String getSaldo();

	/**
	 * @return Devuelve tipoProducto.
	 */
	public abstract String getTipoProducto();
	
	public abstract String getNombreCuenta();
	
	public abstract List getMovimientos();
	
	public abstract String getSaldoContable();
	
	public abstract String getNombreMonedaProducto();
	
	public abstract String getNombreMonedaProductoMail();
	
	public abstract String getEstado();
	
	public String getCuentaFormateada();

	public String getCuentaOculta();
	
	public abstract String getTipoCuenta();
	
	public abstract String getSituacionCuenta();
	
	public abstract String getDescripcionCuenta();
	
	public abstract String getIndicadorCtaCte();
	
	public abstract String getIndicadorSaldo();
	
	public abstract String getIndicadorItf();
	
	public abstract String getNroCuentaCci();
	
	public abstract BigDecimal getSaldoBloqueado(); 
	public void setNumeroProducto(String numeroProducto) ;
	public void getConsultaCtaCteCci(Transaction t, Usuario usuario) throws Exception;
	public abstract String getNumeroDesembolso();
	public void setNumeroDesembolso(String numeroDesembolso);
	public abstract String getTipoTarjeta();
	public void setTipoTarjeta(String tipoTarjeta);
	public abstract List getMovimientosTC();
	public abstract String getTitular();
	public void setTitular(String titular);
	public abstract String getSimboloMonedaProducto();
	public abstract String getFlagPrestamo();
	
	public String getNombreCliente();
	
}