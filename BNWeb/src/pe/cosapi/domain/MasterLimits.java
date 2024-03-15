package pe.cosapi.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public interface MasterLimits extends Serializable{

	public abstract String getCodMoneda();
	public abstract void setCodMoneda(String codMoneda);
	public abstract String getCodTransaccion();
	public abstract void setCodTransaccion(String codTransaccion);
	public abstract BigDecimal getLimInfDiario();
	public abstract void setLimInfDiario(BigDecimal limInfDiario);
	public abstract BigDecimal getLimInfOperacion();
	public abstract void setLimInfOperacion(BigDecimal limInfOperacion);
	public abstract BigDecimal getLimSupDiario();
	public abstract void setLimSupDiario(BigDecimal limSupDiario);
	public abstract BigDecimal getLimSupOperacion();
	public abstract void setLimSupOperacion(BigDecimal limSupOperacion);
	public abstract String getTipoPersona();
	public abstract void setTipoPersona(String tipoPersona);
	
}