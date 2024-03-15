package pe.bn.cldinamica.action.form;
/**
 * @author Mily Dolores Bustamante
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class ElemenSeguridad {
	
	private String coordConcat;
	private String[] panVirtual;
	private String panVirtualToken;
	private Integer rptaConsultCoord;
	private String[][] rptaCoordenada;
	private String idOperacion;
	
	
	public String[][] getRptaCoordenada() {
		return rptaCoordenada;
	}
	public void setRptaCoordenada(String[][] rptaCoordenada) {
		this.rptaCoordenada = rptaCoordenada;
	}
	public String getCoordConcat() {
		return coordConcat;
	}
	public void setCoordConcat(String coordConcat) {
		this.coordConcat = coordConcat;
	}

	public Integer getRptaConsultCoord() {
		return rptaConsultCoord;
	}
	public void setRptaConsultCoord(Integer rptaConsultCoord) {
		this.rptaConsultCoord = rptaConsultCoord;
	}
	public String[] getPanVirtual() {
		return panVirtual;
	}
	public void setPanVirtual(String[] panVirtual) {
		this.panVirtual = panVirtual;
	}
	public String getIdOperacion() {
		return idOperacion;
	}
	public void setIdOperacion(String idOperacion) {
		this.idOperacion = idOperacion;
	}
	public String getPanVirtualToken() {
		return panVirtualToken;
	}
	public void setPanVirtualToken(String panVirtualToken) {
		this.panVirtualToken = panVirtualToken;
	}

}
