package pe.com.bn.common.sati.bean;

public class TokenSmsActivacionRequest {

	
	
	private String numberMobile;
	private String operatorMobile;
	private String flagTerminos;
	private String codCliente;
	private String nroDocumento;
	private String tipoDocumento;
	private String pinblock;
	private String reasonCode;
	
	
	/*Campos JSON Activar Migracion*/
	private String typeDoc;
	private String numberDoc;
	//private String operatorMobile;
	private String typeNotification;
	//private String numberMobile;
	private String email;
	private String nameClient;
	//private String flagTerminos;
	public String getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFlagTerminos() {
		return flagTerminos;
	}
	public void setFlagTerminos(String flagTerminos) {
		this.flagTerminos = flagTerminos;
	}
	public String getNameClient() {
		return nameClient;
	}
	public void setNameClient(String nameClient) {
		this.nameClient = nameClient;
	}
	public String getNroDocumento() {
		return nroDocumento;
	}
	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}
	public String getNumberDoc() {
		return numberDoc;
	}
	public void setNumberDoc(String numberDoc) {
		this.numberDoc = numberDoc;
	}
	public String getNumberMobile() {
		return numberMobile;
	}
	public void setNumberMobile(String numberMobile) {
		this.numberMobile = numberMobile;
	}
	public String getOperatorMobile() {
		return operatorMobile;
	}
	public void setOperatorMobile(String operatorMobile) {
		this.operatorMobile = operatorMobile;
	}
	public String getPinblock() {
		return pinblock;
	}
	public void setPinblock(String pinblock) {
		this.pinblock = pinblock;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getTypeDoc() {
		return typeDoc;
	}
	public void setTypeDoc(String typeDoc) {
		this.typeDoc = typeDoc;
	}
	public String getTypeNotification() {
		return typeNotification;
	}
	public void setTypeNotification(String typeNotification) {
		this.typeNotification = typeNotification;
	}
	public String getReasonCode() {
		return reasonCode;
	}
	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}
	
	
	
	
	
	
}
