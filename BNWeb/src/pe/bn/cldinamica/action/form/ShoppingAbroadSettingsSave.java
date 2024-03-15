package pe.bn.cldinamica.action.form;

import java.io.Serializable;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Contiene las configuraciones para consumos en el extranjero, se encuentra
 * anidado a {@link CardResponse}
 *
 * @author prov_destrada
 * @created 12/11/2020 - 04:05 p. m.
 * @project MSCardConfigurationManagement
 */
public class ShoppingAbroadSettingsSave implements Serializable
{
	private String status;
	private String typeMoney;
	private String amount;
	private String typeAmount;
	private String dateDeparture;
	private String dateReturn;
	private String[] country;
	
	
//	private String available;
//	private String lblActivado;
//	private String countriesStr;
	
	
//	public String getAvailable() {
//		return available;
//	}
//	public void setAvailable(String available) {
//		this.available = available;
//	}
	public String getDateDeparture() {
		return dateDeparture;
	}
	public void setDateDeparture(String dateDeparture) {
		this.dateDeparture = dateDeparture;
	}
	public String getDateReturn() {
		return dateReturn;
	}
	public void setDateReturn(String dateReturn) {
		this.dateReturn = dateReturn;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String[] getCountry() {
		return country;
	}
	public void setCountry(String[] country) {
		this.country = country;
	}
//	public String getLblActivado() {
//		return lblActivado;
//	}
//	public void setLblActivado(String lblActivado) {
//		this.lblActivado = lblActivado;
//	}
//	public String getCountriesStr() {
//		return countriesStr;
//	}
//	public void setCountriesStr(String countriesStr) {
//		this.countriesStr = countriesStr;
//	}
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
