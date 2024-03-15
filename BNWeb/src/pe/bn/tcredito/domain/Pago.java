package pe.bn.tcredito.domain;

import java.io.Serializable;

import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.Operacion;

public interface Pago extends Operacion, Serializable{
	public abstract String getNombreCliente();
	public abstract String getComision();
	public abstract String getItf();
	public abstract String getMontoPagado();
	public abstract String getNroTarjetaCredito();
	public abstract String getNroTarjetaDebito();
	public abstract String getCodigoAutorizacion();
	public abstract String getNroCuentaAhorros();
	public abstract Cuenta getCuentaCargada();
	public abstract Cuenta getCuentaCredito();
	public abstract String getFecha();
	public abstract String getFechaFacturacion();
	public abstract String getHora();
	public abstract String getNroOperacion();
	public abstract String getTarjetaFormateada();
}
