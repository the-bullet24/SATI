package pe.com.bn.common.sati.bean;

import java.io.Serializable;

public class TokenSmsMigracionRequest implements Serializable {

	private static final long serialVersionUID = 8770330171181271314L;
	
	private String codCliente;
	private String nroDocumento;
	private String tipoDocumento;
	private String operatorMobile;
	private String codOperatorMobile;
	private String numberMobile;
	private String typeOp;
	private String pinblock;
	private String email;
	private String flagTerminos;
	private String tipoNotificacion;
	private String nameClient;
	
	
	
	

	
	
	public String getNameClient() {
		return nameClient;
	}
	public void setNameClient(String nameClient) {
		this.nameClient = nameClient;
	}
	public String getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}
	public String getNroDocumento() {
		return nroDocumento;
	}
	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
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
	
	public String getCodOperatorMobile() {
		return codOperatorMobile;
	}
	public void setCodOperatorMobile(String codOperatorMobile) {
		this.codOperatorMobile = codOperatorMobile;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getTypeOp() {
		return typeOp;
	}
	public void setTypeOp(String typeOp) {
		this.typeOp = typeOp;
	}
	public String getPinblock() {
		return pinblock;
	}
	public void setPinblock(String pinblock) {
		this.pinblock = pinblock;
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
	public String getTipoNotificacion() {
		return tipoNotificacion;
	}
	public void setTipoNotificacion(String tipoNotificacion) {
		this.tipoNotificacion = tipoNotificacion;
	}
	
	
}
