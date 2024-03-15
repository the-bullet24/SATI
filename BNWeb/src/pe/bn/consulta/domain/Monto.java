package pe.bn.consulta.domain;

import java.io.Serializable;

import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.Operacion;
import pe.cosapi.domain.impl.CuentaImpl;

public interface Monto extends Operacion, Serializable{
	public abstract void setMontoMaximo(String montoMaximo);
	public abstract String getMontoMaximo();
	public abstract void setTipoCanal(String tipoCanal);
	public abstract String getTipoCanal();
	public abstract String getNroCuenta();
	public abstract void setTipoElemento(String tipoElemento);
	public abstract String getTipoElemento();
	public abstract String getNroTarjeta();
	public abstract String getTipoCliente();
	public abstract void setCuenta(CuentaImpl cuenta);
	public abstract CuentaImpl getCuenta();
	public abstract void setCodigoElemento(String codigoElemento);
	public abstract String getCodigoElemento();
	public abstract void setFecha(String fecha);
	public abstract String getFecha();
	public abstract void setHora(String hora);
	public abstract String getHora();
	public abstract void setDesMontoMaximo(String desMontoMaximo);
	public abstract String getDesMontoMaximo();
}
