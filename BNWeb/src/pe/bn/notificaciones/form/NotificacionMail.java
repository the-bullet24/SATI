package pe.bn.notificaciones.form;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import pe.cosapi.common.Constante;

public class NotificacionMail {
	private String ncliente;
	private String ntarjeta;
	private String canal;
	private String establecimiento;
	private String toperacion;
	private String moneda;
	private String importe;
	private String foperacion;
	private String hoperacion;
	
	private String asunto;
	private String cdestinatario;
	
	public NotificacionMail(String ncliente, String ntarjeta, String moneda, String importe, String toperacion, String cdestinatario){
		this.ncliente=ncliente;
		this.ntarjeta = ntarjeta;
		this.moneda = moneda;
		this.importe = importe;
		this.toperacion = toperacion;
		this.cdestinatario = cdestinatario;		
	}
	public String getCanal() {
		this.canal = Constante.NOTI_CANAL;
		return canal;
	}
	public void setCanal(String canal) {
		this.canal = canal;
	}
	public String getEstablecimiento() {
		this.establecimiento=Constante.NOTI_ESTABLECIMIENTO;
		return establecimiento;
	}
	public void setEstablecimiento(String establecimiento) {
		this.establecimiento = establecimiento;
	}
	public String getFoperacion() {
		SimpleDateFormat formatter;
		Timestamp fechaActual = new Timestamp(Calendar.getInstance().getTime().getTime());
		formatter = new SimpleDateFormat("dd/MM/yyyy");			
		Date data = new Date(fechaActual.getTime());
		this.foperacion = formatter.format(data);
		return foperacion;
	}
	public void setFoperacion(String foperacion) {
		this.foperacion = foperacion;
	}
	public String getHoperacion() {
		SimpleDateFormat formatter;
		Timestamp fechaActual = new Timestamp(Calendar.getInstance().getTime().getTime());
		formatter = new SimpleDateFormat("HH:mm:ss");
		Date data = new Date(fechaActual.getTime());
		this.hoperacion = formatter.format(data);
		return hoperacion;
	}
	public void setHoperacion(String hoperacion) {
		this.hoperacion = hoperacion;
	}
	public String getImporte() {
		return importe;
	}
	public void setImporte(String importe) {
		this.importe = importe;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public String getNcliente() {
		return ncliente;
	}
	public void setNcliente(String ncliente) {
		this.ncliente = ncliente;
	}
	public String getNtarjeta() {
		return ntarjeta;
	}
	public void setNtarjeta(String ntarjeta) {
		this.ntarjeta = ntarjeta;
	}
	public String getToperacion() {
		return toperacion;
	}
	public void setToperacion(String toperacion) {
		this.toperacion = toperacion;
	}
	public String getAsunto() {
		this.asunto=Constante.NOTI_ASUNTO;
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getCdestinatario() {
		return cdestinatario;
	}
	public void setCdestinatario(String cdestinatario) {
		this.cdestinatario = cdestinatario;
	}	
	
}
