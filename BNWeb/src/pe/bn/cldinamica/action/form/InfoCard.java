package pe.bn.cldinamica.action.form;

import java.io.Serializable;

/**
 * @author prov_destrada
 * @created 12/11/2020 - 02:44 p. m.
 * @project MSCardConfigurationManagement
 */

public class InfoCard implements Serializable
{
	 private String cardType;
	 private String clientTypeDesc;
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
	public String getClientTypeDesc() {
		return clientTypeDesc;
	}
	public void setClientTypeDesc(String clientTypeDesc) {
		this.clientTypeDesc = clientTypeDesc;
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
	public String getClientType() {
		return clientType;
	}
	public void setClientType(String clientType) {
		this.clientType = clientType;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	 
	 
    
    
    
}
