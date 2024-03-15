/*
 * Creado el 12/02/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de codigo - Plantillas de código
 */
package pe.cosapi.domain.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import pe.bn.cldinamica.action.form.ParametrosTDC;
import pe.bn.consulta.domain.impl.PrestamoImpl;
import pe.bn.login.domain.IngresoTarjeta;
import pe.bn.tarjeta.domain.Tarjeta;
import pe.bn.tarjeta.domain.impl.TarjetaImpl;
import pe.bn.telegiro.action.TelegiroAction;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.common.ArrayRuleException;
import pe.cosapi.common.Constante;
import pe.cosapi.common.ConstanteError;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.TipoCambio;
import pe.cosapi.domain.Usuario;
import pe.cosapi.domain.UsuarioCLDI;
import pe.cosapi.sarabank.bean.Transaction;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class UsuarioCLDIImpl extends OperacionImplNueva implements UsuarioCLDI, Serializable{
	private static LoggerSati log3 = LoggerSati.getInstance(UsuarioCLDIImpl.class.getName());
 
	
	private String codPerfil;
	private String montoLimite;
	private String codigoCLDI;
	private String codMontoLimite;
	private String codigoCic;
	
	
	
    public String getCodigoCic() {
		return codigoCic;
	}

	public void setCodigoCic(String codigoCic) {
		this.codigoCic = codigoCic;
	}
	
	
    public String getCodMontoLimite() {
		return codMontoLimite;
	}

	public void setCodMontoLimite(String codMontoLimite) {
		this.codMontoLimite = codMontoLimite;
	}

	public String getCodigoCLDI() {
		return codigoCLDI;
	}

	public void setCodigoCLDI(String codigoCLDI) {
		this.codigoCLDI = codigoCLDI;
	}

	public String getMontoLimite() {
		return montoLimite;
	}

	public void setMontoLimite(String montoLimite) {
		this.montoLimite = montoLimite;
	}

	public String getCodPerfil() {
		return codPerfil;
	}

	public void setCodPerfil(String codPerfil) {
		this.codPerfil = codPerfil;
	}

}