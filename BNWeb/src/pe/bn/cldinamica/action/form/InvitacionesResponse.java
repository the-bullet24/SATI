package pe.bn.cldinamica.action.form;

public class InvitacionesResponse {

	private String codResult;
	private String msg;
	private String msgError;
	private DataInvitaciones dataInvitaciones;
	
	public String getCodResult() {
		return codResult;
	}
	public void setCodResult(String codResult) {
		this.codResult = codResult;
	}
	public DataInvitaciones getDataInvitaciones() {
		return dataInvitaciones;
	}
	public void setDataInvitaciones(DataInvitaciones dataInvitaciones) {
		this.dataInvitaciones = dataInvitaciones;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getMsgError() {
		return msgError;
	}
	public void setMsgError(String msgError) {
		this.msgError = msgError;
	}

	
}
