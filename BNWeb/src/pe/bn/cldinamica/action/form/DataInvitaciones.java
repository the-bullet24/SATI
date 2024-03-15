package pe.bn.cldinamica.action.form;

public class DataInvitaciones {


	private int idclient;

	private String invitationDate;
	private String intrusiveInvitation;
	private String nonIntrusiveInvitation;
	private String smsInvitation;
	
	
	public DataInvitaciones (){
		
	}
	
	public DataInvitaciones (int idClient, String invitationDate, String intrusiveInvitation, String nonIntrusiveInvitation, String smsInvitation){
		this.idclient = idClient;
		this.invitationDate = invitationDate;
		this.intrusiveInvitation = intrusiveInvitation;
		this.nonIntrusiveInvitation = nonIntrusiveInvitation;
		this.smsInvitation = smsInvitation;
	}
	
	public int getIdclient() {
		return idclient;
	}
	public void setIdclient(int idclient) {
		this.idclient = idclient;
	}
	public String getIntrusiveInvitation() {
		return intrusiveInvitation;
	}
	public void setIntrusiveInvitation(String intrusiveInvitation) {
		this.intrusiveInvitation = intrusiveInvitation;
	}
	public String getInvitationDate() {
		return invitationDate;
	}
	public void setInvitationDate(String invitationDate) {
		this.invitationDate = invitationDate;
	}
	public String getNonIntrusiveInvitation() {
		return nonIntrusiveInvitation;
	}
	public void setNonIntrusiveInvitation(String nonIntrusiveInvitation) {
		this.nonIntrusiveInvitation = nonIntrusiveInvitation;
	}
	public String getSmsInvitation() {
		return smsInvitation;
	}
	public void setSmsInvitation(String smsInvitation) {
		this.smsInvitation = smsInvitation;
	}
	
		

}
