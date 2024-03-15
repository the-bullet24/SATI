package pe.bn.cldinamica.action.form;

import java.io.Serializable;

/**
 * @author prov_destrada
 * @created 12/11/2020 - 02:44 p. m.
 * @project MSCardConfigurationManagement
 */

public class CardRequestSave implements Serializable
{
	private String typeCard;
    private String numberCard;
    
    private String typeDocument;
    private String numberDocument;
    private String typeClient;
    
    private String typeToken;
        
    //private String obfuscatedNumberCard;
    
    private ShoppingInternetSettingsSave shoppingInternetSettings;
    
    private CashDispositionSettingsSave cashDispositionSettings; 
    
    private NotificationSettingsSave notificationSettings;
    
    private ShoppingAbroadSettingsSave shoppingAbroadSettings;
    
    private TransferSettingsSave transferSettings;    
   
    
    private String email;
    private String operatorType;
    private String cellNumber;
    
    private Long numLog;
    

	public NotificationSettingsSave getNotificationSettings() {
		return notificationSettings;
	}


	public void setNotificationSettings(NotificationSettingsSave notificationSettings) {
		this.notificationSettings = notificationSettings;
	}


	public String getNumberCard() {
		return numberCard;
	}


	public void setNumberCard(String numberCard) {
		this.numberCard = numberCard;
	}

	public ShoppingAbroadSettingsSave getShoppingAbroadSettings() {
		return shoppingAbroadSettings;
	}


	public void setShoppingAbroadSettings(
			ShoppingAbroadSettingsSave shoppingAbroadSettings) {
		this.shoppingAbroadSettings = shoppingAbroadSettings;
	}


	public ShoppingInternetSettingsSave getShoppingInternetSettings() {
		return shoppingInternetSettings;
	}


	public void setShoppingInternetSettings(
			ShoppingInternetSettingsSave shoppingInternetSettings) {
		this.shoppingInternetSettings = shoppingInternetSettings;
	}


	public TransferSettingsSave getTransferSettings() {
		return transferSettings;
	}


	public void setTransferSettings(TransferSettingsSave transferSettings) {
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

	public CashDispositionSettingsSave getCashDispositionSettings() {
		return cashDispositionSettings;
	}


	public void setCashDispositionSettings(
			CashDispositionSettingsSave cashDispositionSettings) {
		this.cashDispositionSettings = cashDispositionSettings;
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


	public String getCellNumber() {
		return cellNumber;
	}


	public void setCellNumber(String cellNumber) {
		this.cellNumber = cellNumber;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getOperatorType() {
		return operatorType;
	}


	public void setOperatorType(String operatorType) {
		this.operatorType = operatorType;
	}
	
	


	public Long getNumLog() {
		return numLog;
	}


	public void setNumLog(Long numLog) {
		this.numLog = numLog;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CardRequestSave [typeCard=");
		builder.append(typeCard);
		builder.append(", numberCard=");
		builder.append(numberCard);
		builder.append(", typeDocument=");
		builder.append(typeDocument);
		builder.append(", numberDocument=");
		builder.append(numberDocument);
		builder.append(", typeClient=");
		builder.append(typeClient);
		builder.append(", typeToken=");
		builder.append(typeToken);
		builder.append(", shoppingInternetSettings=");
		builder.append(shoppingInternetSettings);
		builder.append(", cashDispositionSettings=");
		builder.append(cashDispositionSettings);
		builder.append(", notificationSettings=");
		builder.append(notificationSettings);
		builder.append(", shoppingAbroadSettings=");
		builder.append(shoppingAbroadSettings);
		builder.append(", transferSettings=");
		builder.append(transferSettings);
		builder.append(", email=");
		builder.append(email);
		builder.append(", operatorType=");
		builder.append(operatorType);
		builder.append(", cellNumber=");
		builder.append(cellNumber);
		builder.append("]");
		return builder.toString();
	}
    
    
    
}
