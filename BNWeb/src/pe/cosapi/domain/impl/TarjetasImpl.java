/*
 * Creado el 23/02/2007
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de c?digo - Plantillas de c?digo
 */
package pe.cosapi.domain.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;


import pe.bn.afiliacion.action.DesafCableAction;
import pe.bn.transferencia.domain.impl.TransferenciaImpl;
import pe.com.bn.sati.common.LoggerSati;
import pe.cosapi.common.ArrayRuleException;
import pe.cosapi.common.Constante;
import pe.cosapi.common.DAOFactory;
import pe.cosapi.common.ObjectUtil;
import pe.cosapi.domain.Cuenta;
import pe.cosapi.domain.Movimiento;
import pe.cosapi.domain.Usuario;
import pe.cosapi.sarabank.bean.Transaction;

/**
 * @author cosapi_ati04
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de c?digo - Plantillas de c?digo
 */
public class TarjetasImpl extends OperacionImplNueva implements Serializable, Cloneable {
	private static LoggerSati log3 = LoggerSati.getInstance(TarjetasImpl.class.getName());
    
	private String cardType;
	 private String clientType;
	 private String cardName;
	 private String numCard;
	 private String numAccount;
	 private String associatedEntity;
	 private String provider;
	public String getAssociatedEntity() {
		return associatedEntity;
	}
	public void setAssociatedEntity(String associatedEntity) {
		this.associatedEntity = associatedEntity;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getClientType() {
		return clientType;
	}
	public void setClientType(String clientType) {
		this.clientType = clientType;
	}
	public String getNumAccount() {
		return numAccount;
	}
	public void setNumAccount(String numAccount) {
		this.numAccount = numAccount;
	}
	public String getNumCard() {
		return numCard;
	}
	public void setNumCard(String numCard) {
		this.numCard = numCard;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	
	
	
	
	
	
	
	
	
}

