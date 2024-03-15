package pe.cosapi.domain;

import java.io.Serializable;

public interface DetalleAyudaDatos extends Serializable {

	public abstract String getCodigoAyuda();

	public abstract void setCodigoAyuda(String codigoAyuda);

	public abstract String getCodigoDetalleAyuda();

	public abstract void setCodigoDetalleAyuda(String codigoDetalleAyuda);
	
	public abstract String getCodigoDetalleAyuda1();

	public abstract void setCodigoDetalleAyuda1(String codigoDetalleAyuda1);

	public abstract String getDescripcionDetalle();

	public abstract void setDescripcionDetalle(String descripcionDetalle);

	public abstract Long getNumeroSequencial();

	public abstract void setNumeroSequencial(Long numeroSequencial);

}