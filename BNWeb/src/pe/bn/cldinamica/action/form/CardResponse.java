package pe.bn.cldinamica.action.form;

import java.io.Serializable;

/**
 * @author prov_destrada
 * @created 12/11/2020 - 02:44 p. m.
 * @project MSCardConfigurationManagement
 */

public class CardResponse implements Serializable
{
	private String typeCard;
    private String numberCard;
    private String typeToken;
    
    private String typeClient;
    private String typeDocument;
    private String numberDocument;

    
    private CashDispositionSettings cashDispositionSettings;

    
    private TransferSettings transferSettings;

    
    private ShoppingInternetSettings shoppingInternetSettings;

    
    private NotificationSettings notificationSettings;

    
    private ShoppingAbroadSettings shoppingAbroadSettings;

    
    private SendStatusAccountSettings sendStatusAccountSettings;


	public CashDispositionSettings getCashDispositionSettings() {
		return cashDispositionSettings;
	}


	public void setCashDispositionSettings(
			CashDispositionSettings cashDispositionSettings) {
		this.cashDispositionSettings = cashDispositionSettings;
	}


	public NotificationSettings getNotificationSettings() {
		return notificationSettings;
	}


	public void setNotificationSettings(NotificationSettings notificationSettings) {
		this.notificationSettings = notificationSettings;
	}


	public String getNumberCard() {
		return numberCard;
	}


	public void setNumberCard(String numberCard) {
		this.numberCard = numberCard;
	}


	public SendStatusAccountSettings getSendStatusAccountSettings() {
		return sendStatusAccountSettings;
	}


	public void setSendStatusAccountSettings(
			SendStatusAccountSettings sendStatusAccountSettings) {
		this.sendStatusAccountSettings = sendStatusAccountSettings;
	}


	public ShoppingAbroadSettings getShoppingAbroadSettings() {
		return shoppingAbroadSettings;
	}


	public void setShoppingAbroadSettings(
			ShoppingAbroadSettings shoppingAbroadSettings) {
		this.shoppingAbroadSettings = shoppingAbroadSettings;
	}


	public ShoppingInternetSettings getShoppingInternetSettings() {
		return shoppingInternetSettings;
	}


	public void setShoppingInternetSettings(
			ShoppingInternetSettings shoppingInternetSettings) {
		this.shoppingInternetSettings = shoppingInternetSettings;
	}


	public TransferSettings getTransferSettings() {
		return transferSettings;
	}


	public void setTransferSettings(TransferSettings transferSettings) {
		this.transferSettings = transferSettings;
	}


	public String getTypeCard() {
		return typeCard;
	}


	public void setTypeCard(String typeCard) {
		this.typeCard = typeCard;
	}


	public String getTypeToken() {
		return typeToken;
	}


	public void setTypeToken(String typeToken) {
		this.typeToken = typeToken;
	}


	public String getNumberDocument() {
		return numberDocument;
	}


	public void setNumberDocument(String numberDocument) {
		this.numberDocument = numberDocument;
	}


	public String getTypeClient() {
		return typeClient;
	}


	public void setTypeClient(String typeClient) {
		this.typeClient = typeClient;
	}


	public String getTypeDocument() {
		return typeDocument;
	}


	public void setTypeDocument(String typeDocument) {
		this.typeDocument = typeDocument;
	}
    
    
    
}
