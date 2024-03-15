package pe.bn.cldinamica.action.form;



import java.io.Serializable;

/**
 * Contiene las configuraciones para enviar notificaciones, se encuentra
 * anidado a {@link CardResponse}
 *
 * @author prov_destrada
 * @created 12/11/2020 - 04:04 p. m.
 * @project MSCardConfigurationManagement
 */

public class NotificationSettings implements Serializable
{
	
	
	
	private String status;
	
	private String available;
	
	private String typeMoney;
	
	private String amount;
	
	//private String amountToReceiveNoFormat;
	
	private String meansNotification;
	
	private String associatedEmail;
	
	private String associatedPhone;
	
	private String codeOperator;

	private String lblActivado;
	
	private String typeAmount;
	
	private String desOperator;
	
	private String amountToReceiveNo;
	

	

	public String getAmountToReceiveNo() {
		return amountToReceiveNo;
	}

	public void setAmountToReceiveNo(String amountToReceiveNo) {
		this.amountToReceiveNo = amountToReceiveNo;
	}

	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

	public String getMeansNotification() {
		return meansNotification;
	}

	public void setMeansNotification(String meansNotification) {
		this.meansNotification = meansNotification;
	}

	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTypeMoney() {
		return typeMoney;
	}

	public void setTypeMoney(String typeMoney) {
		this.typeMoney = typeMoney;
	}

	public String getAssociatedEmail() {
		return associatedEmail;
	}

	public void setAssociatedEmail(String associatedEmail) {
		this.associatedEmail = associatedEmail;
	}

	public String getAssociatedPhone() {
		return associatedPhone;
	}

	public void setAssociatedPhone(String associatedPhone) {
		this.associatedPhone = associatedPhone;
	}

	public String getCodeOperator() {
		return codeOperator;
	}

	public void setCodeOperator(String codeOperator) {
		this.codeOperator = codeOperator;
	}

	public String getLblActivado() {
		return lblActivado;
	}
	public void setLblActivado(String lblActivado) {
		this.lblActivado = lblActivado;
	}

	public String getDesOperator() {
		return desOperator;
	}

	public void setDesOperator(String desOperator) {
		this.desOperator = desOperator;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getTypeAmount() {
		return typeAmount;
	}

	public void setTypeAmount(String typeAmount) {
		this.typeAmount = typeAmount;
	}

}
