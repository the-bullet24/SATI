package pe.cosapi.domain;

import java.io.Serializable;

public interface MasterTransaction extends Serializable {

	public abstract String getCodGrupo();

	public abstract void setCodGrupo(String codGrupo);

	public abstract String getCodTransaccion();

	public abstract void setCodTransaccion(String codTransaccion);

	public abstract String getFlagActTransaccion();

	public abstract void setFlagActTransaccion(String flagActTransaccion);

	public abstract String getFlaglogOperacion();

	public abstract void setFlaglogOperacion(String flaglogOperacion);

	public abstract String getGuiaProc();

	public abstract void setGuiaProc(String guiaProc);

	public abstract String getHoraFin();

	public abstract void setHoraFin(String horaFin);

	public abstract String getHoraInicio();

	public abstract void setHoraInicio(String horaInicio);

	public abstract String getNomTransaccion();

	public abstract void setNomTransaccion(String nomTransaccion);
	
	public boolean isHoraAtencion();
}