package pe.bn.cldinamica.domain;



public class ParametrosCldi implements java.io.Serializable {

	
	private String codBinMA;
	private String nombreBinMA;
	private String estadoBinMA;
	
	private String codMedioAutenticacion;
	private String estadoMedioAutenticacion;
	
	private String idPrimarioES;
	private String idSecundarioES;
	private String tipoES;
	private String estadoES_MA;
	
	
	public String getEstadoES_MA() {
		return estadoES_MA;
	}
	public void setEstadoES_MA(String estadoES_MA) {
		this.estadoES_MA = estadoES_MA;
	}
	public String getIdPrimarioES() {
		return idPrimarioES;
	}
	public void setIdPrimarioES(String idPrimarioES) {
		this.idPrimarioES = idPrimarioES;
	}
	public String getIdSecundarioES() {
		return idSecundarioES;
	}
	public void setIdSecundarioES(String idSecundarioES) {
		this.idSecundarioES = idSecundarioES;
	}
	public String getTipoES() {
		return tipoES;
	}
	public void setTipoES(String tipoES) {
		this.tipoES = tipoES;
	}
	public String getCodMedioAutenticacion() {
		return codMedioAutenticacion;
	}
	public void setCodMedioAutenticacion(String codMedioAutenticacion) {
		this.codMedioAutenticacion = codMedioAutenticacion;
	}
	public String getEstadoMedioAutenticacion() {
		return estadoMedioAutenticacion;
	}
	public void setEstadoMedioAutenticacion(String estadoMedioAutenticacion) {
		this.estadoMedioAutenticacion = estadoMedioAutenticacion;
	}
	public String getCodBinMA() {
		return codBinMA;
	}
	public void setCodBinMA(String codBinMA) {
		this.codBinMA = codBinMA;
	}
	public String getEstadoBinMA() {
		return estadoBinMA;
	}
	public void setEstadoBinMA(String estadoBinMA) {
		this.estadoBinMA = estadoBinMA;
	}
	public String getNombreBinMA() {
		return nombreBinMA;
	}
	public void setNombreBinMA(String nombreBinMA) {
		this.nombreBinMA = nombreBinMA;
	}
	


	
	

}