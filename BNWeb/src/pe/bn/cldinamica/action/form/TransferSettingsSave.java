package pe.bn.cldinamica.action.form;

import com.fasterxml.jackson.annotation.JsonView;

import java.io.Serializable;

/**
 * Contiene las configuraciones para transferencias, se encuentra
 * anidado a {@link CardResponse}
 *
 * @author prov_destrada
 * @created 12/11/2020 - 04:04 p. m.
 * @project MSCardConfigurationManagement
 */

public class TransferSettingsSave implements Serializable
{
	
	private String status;
//	private String available;
	private String typeMoney;
	private String amount;
	private String typeAmount;
    	
//	public String getAvailable() {
//		return available;
//	}
//	public void setAvailable(String available) {
//		this.available = available;
//	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getTypeMoney() {
		return typeMoney;
	}
	public void setTypeMoney(String typeMoney) {
		this.typeMoney = typeMoney;
	}
	
	
}
