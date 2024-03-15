package pe.bn.tcredito.domain;



public class Formato implements java.io.Serializable {

	// Fields


	private String numSec;
	private String numDocumento;
	private String periodo;
	private String fecha;
	private byte[] contenido;
	

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getNumSec() {
		return numSec;
	}

	public void setNumSec(String numSec) {
		this.numSec = numSec;
	}

	

	public String getNumDocumento() {
		return numDocumento;
	}

	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public byte[] getContenido() {
		return contenido;
	}

	public void setContenido(byte[] contenido) {
		this.contenido = contenido;
	}

}