package pe.bn.cldinamica.action.form;

import java.io.Serializable;



/**
 * Contiene las configuraciones para envío de estado de cuenta, se encuentra
 * anidado a {@link CardResponse}
 *
 * @author prov_destrada
 * @created 12/11/2020 - 04:05 p. m.
 * @project MSCardConfigurationManagement
 */

public class SendStatusAccountSettings implements Serializable
{
	private String status;
	private String available;
	private String meansOfSending;
	private String meansOfSendingDesc;
	private String associatedEmail;
	private String associatedAddress;
	public String getAssociatedAddress() {
		return associatedAddress;
	}
	public void setAssociatedAddress(String associatedAddress) {
		this.associatedAddress = associatedAddress;
	}
	public String getAssociatedEmail() {
		return associatedEmail;
	}
	public void setAssociatedEmail(String associatedEmail) {
		this.associatedEmail = associatedEmail;
	}
	public String getAvailable() {
		return available;
	}
	public void setAvailable(String available) {
		this.available = available;
	}
	public String getMeansOfSending() {
		return meansOfSending;
	}
	public void setMeansOfSending(String meansOfSending) {
		this.meansOfSending = meansOfSending;
	}
	public String getMeansOfSendingDesc() {
		return meansOfSendingDesc;
	}
	public void setMeansOfSendingDesc(String meansOfSendingDesc) {
		this.meansOfSendingDesc = meansOfSendingDesc;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
