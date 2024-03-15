package pe.cosapi.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public interface Banner extends Serializable {

	public abstract Timestamp getDatbeg();

	public abstract void setDatbeg(Timestamp datbeg);

	public abstract Timestamp getDatend();

	public abstract void setDatend(Timestamp datend);

	public abstract String getFilnam();

	public abstract void setFilnam(String filnam);

	public abstract String getTypper();

	public abstract void setTypper(String typper);

}