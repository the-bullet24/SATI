/*
 * Creado el 12/02/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package pe.cosapi.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import pe.bn.cldinamica.action.form.ParametrosTDC;
import pe.bn.login.domain.IngresoTarjeta;
import pe.bn.tarjeta.domain.Tarjeta;
import pe.cosapi.domain.impl.CuentaImpl;
import pe.cosapi.sarabank.bean.Transaction;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public interface UsuarioCLDI extends Serializable {

	
	public void setCodPerfil(String codPerfil);
	public String getCodPerfil();
	
	public void setMontoLimite(String montoLimite);
	public String getMontoLimite();
	
	public void setCodMontoLimite(String codMontoLimite);
	public String getCodMontoLimite();
	
	public void setCodigoCLDI(String codigoCLDI);
	public String getCodigoCLDI();
	

	public void setCodigoCic(String codigoCic);
	public String getCodigoCic();
}
