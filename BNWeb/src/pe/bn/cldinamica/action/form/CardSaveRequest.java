package pe.bn.cldinamica.action.form;

import java.io.Serializable;

/**
 * @author prov_destrada
 * @created 12/11/2020 - 02:44 p. m.
 * @project MSCardConfigurationManagement
 */

public class CardSaveRequest implements Serializable
{
	private String typeCard;
    private String numberCard;
    private String typeToken;
    private String obfuscatedNumberCard;
    
    private CashDispositionSettings cashDispositionSettings;    
    private TransferSettings transferSettings;    
    private ShoppingInternetSettings shoppingInternetSettings;
    private NotificationSettings notificationSettings;
    private ShoppingAbroadSettings shoppingAbroadSettings;
    
    private Long nroLog;
    private String hora;
    private String fecha;

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


//	public SendStatusAccountSettings getSendStatusAccountSettings() {
//		return sendStatusAccountSettings;
//	}
//
//
//	public void setSendStatusAccountSettings(
//			SendStatusAccountSettings sendStatusAccountSettings) {
//		this.sendStatusAccountSettings = sendStatusAccountSettings;
//	}


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


	public String getObfuscatedNumberCard() {
		return obfuscatedNumberCard;
	}


	public void setObfuscatedNumberCard(String obfuscatedNumberCard) {
		this.obfuscatedNumberCard = obfuscatedNumberCard;
	}


	public CashDispositionSettings getCashDispositionSettings() {
		return cashDispositionSettings;
	}


	public void setCashDispositionSettings(
			CashDispositionSettings cashDispositionSettings) {
		this.cashDispositionSettings = cashDispositionSettings;
	}


	public Long getNroLog() {
		return nroLog;
	}


	public void setNroLog(Long nroLog) {
		this.nroLog = nroLog;
	}


	public String getHora() {
		return hora;
	}


	public void setHora(String hora) {
		this.hora = hora;
	}


	public String getFecha() {
		return fecha;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

    
    
    
}
