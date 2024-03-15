package pe.bn.pago.domain;

import java.math.BigDecimal;
import java.util.List;

import pe.cosapi.domain.Cuenta;

public interface Multipago {
	
	public abstract String getHora();
	
	public abstract void setHora(String hora);
	
	public abstract void setFecha(String fecha);
	
	public abstract String getFecha();
	
	public abstract void setNumero(String numero);
	
	public abstract String getNumero();
	
	public abstract void setNumUnico(String numUnico);
	
	public abstract String getNumUnico();
		
	public abstract void setTipoDocumento(String tipoDocumento);
	
	public abstract String getTipoDocumento();
	
	public abstract void setNumDocumento(String numDocumento);
	
	public abstract String getNumDocumento();
		
	public abstract void setDescMoneda(String descMoneda);
	
	public abstract String getDescMoneda();
	
	public abstract void setCodMoneda(String codMoneda);
	
	public abstract String getCodMoneda();
	
	public abstract void setDescTipoDocumento(String descTipoDocumento);
	
	public abstract String getDescTipoDocumento();
	
	public abstract void setCodServicio(String codServicio);
	
	public abstract String getCodServicio();
	
	public abstract void setDesServicio(String desServicio);
	
	public abstract String getDesServicio();
	
	public abstract void setFechaCaducidad(String fechaCaducidad);
	
	public abstract String getFechaCaducidad();
	
	public abstract Cuenta getCuenta();

	public abstract void setCuenta(Cuenta cuenta);	
	
	public abstract void setCodEstado(String codEstado);
	
	public abstract String getCodEstado();
	
	public abstract void setDesEstado(String desEstado);
	
	public abstract String getDesEstado();
	
	public abstract void setRegistro(List registro);
	public abstract List getRegistro();
	
	public abstract void setTxtTicket(String txtTicket);
	
	public abstract String getTxtTicket();

	public abstract void setTxtEntidad(String txtEntidad);
	
	public abstract String getTxtEntidad();
	
	public abstract void setTxtImporte(String txtImporte);
	
	public abstract String getTxtImporte();
	
	public abstract void setNumRegistros(String numRegistros);
	
	public abstract String getNumRegistros();
	
	
}
