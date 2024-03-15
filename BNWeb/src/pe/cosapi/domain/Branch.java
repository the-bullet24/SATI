package pe.cosapi.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public interface Branch extends Serializable {

	public abstract String getBandef();

	public abstract void setBandef(String bandef);

	public abstract String getBanpju();

	public abstract void setBanpju(String banpju);

	public abstract String getBanpna();

	public abstract void setBanpna(String banpna);

	public abstract String getCodbra();

	public abstract void setCodbra(String codbra);

	public abstract BigDecimal getDayprgtra();

	public abstract void setDayprgtra(BigDecimal dayprgtra);

	public abstract String getFlgblkbra();

	public abstract void setFlgblkbra(String flgblkbra);

	public abstract String getHoufin();

	public abstract void setHoufin(String houfin);

	public abstract String getHouini();

	public abstract void setHouini(String houini);

	public abstract String getNumsum();

	public abstract void setNumsum(String numsum);

	public abstract boolean isBlockAgency();
	
}