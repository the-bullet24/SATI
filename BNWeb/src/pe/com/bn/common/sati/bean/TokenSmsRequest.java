package pe.com.bn.common.sati.bean;

import java.io.Serializable;

public class TokenSmsRequest implements Serializable {

	private static final long serialVersionUID = 5511360410447246055L;

	private String codeSMS;

	private String typeTrans;

	private String typeOp;

	private String dataClient;

	private String msgClient;

	private String amount;

	private String typeCurrency;

	private String nameBeneficiary;

	private String codeBeneficiary;

	private String codCliente;

	private String nroDocumento;

	private String tipoDocumento;
	
	private String codCliDestinatario;
	
	private String nomCliDestinatario;

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCodeBeneficiary() {
		return codeBeneficiary;
	}

	public void setCodeBeneficiary(String codeBeneficiary) {
		this.codeBeneficiary = codeBeneficiary;
	}

	public String getCodeSMS() {
		return codeSMS;
	}

	public void setCodeSMS(String codeSMS) {
		this.codeSMS = codeSMS;
	}

	public String getDataClient() {
		return dataClient;
	}

	public void setDataClient(String dataClient) {
		this.dataClient = dataClient;
	}

	public String getMsgClient() {
		return msgClient;
	}

	public void setMsgClient(String msgClient) {
		this.msgClient = msgClient;
	}

	public String getNameBeneficiary() {
		return nameBeneficiary;
	}

	public void setNameBeneficiary(String nameBeneficiary) {
		this.nameBeneficiary = nameBeneficiary;
	}

	public String getTypeCurrency() {
		return typeCurrency;
	}

	public void setTypeCurrency(String typeCurrency) {
		this.typeCurrency = typeCurrency;
	}

	public String getTypeOp() {
		return typeOp;
	}

	public void setTypeOp(String typeOp) {
		this.typeOp = typeOp;
	}

	public String getTypeTrans() {
		return typeTrans;
	}

	public void setTypeTrans(String typeTrans) {
		this.typeTrans = typeTrans;
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

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getCodCliDestinatario() {
		return codCliDestinatario;
	}

	public void setCodCliDestinatario(String codCliDestinatario) {
		this.codCliDestinatario = codCliDestinatario;
	}

	public String getNomCliDestinatario() {
		return nomCliDestinatario;
	}

	public void setNomCliDestinatario(String nomCliDestinatario) {
		this.nomCliDestinatario = nomCliDestinatario;
	}
	
	

}
