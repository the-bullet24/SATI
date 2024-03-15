package pe.bn.cldinamica.action.form;

public class SaldoResponse {

    private String codProducto;
    private String numProducto;
    private String saldoContable;
    private String saldoActual;
    private String moneda;
    private String idOperacion;
    private String codigoRetorno;
    private String mensaje;
    private String mensajeDet;
    private String saldoTangible;
    private String saldoIntangible;
    private String deudaTotal;
    private String seguroDesgravamen;
    private String interes;
    private String retencion;
	public String getCodProducto() {
		return codProducto;
	}
	public void setCodProducto(String codProducto) {
		this.codProducto = codProducto;
	}
	public String getNumProducto() {
		return numProducto;
	}
	public void setNumProducto(String numProducto) {
		this.numProducto = numProducto;
	}
	public String getSaldoContable() {
		return saldoContable;
	}
	public void setSaldoContable(String saldoContable) {
		this.saldoContable = saldoContable;
	}
	public String getSaldoActual() {
		return saldoActual;
	}
	public void setSaldoActual(String saldoActual) {
		this.saldoActual = saldoActual;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public String getIdOperacion() {
		return idOperacion;
	}
	public void setIdOperacion(String idOperacion) {
		this.idOperacion = idOperacion;
	}
	public String getCodigoRetorno() {
		return codigoRetorno;
	}
	public void setCodigoRetorno(String codigoRetorno) {
		this.codigoRetorno = codigoRetorno;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getMensajeDet() {
		return mensajeDet;
	}
	public void setMensajeDet(String mensajeDet) {
		this.mensajeDet = mensajeDet;
	}
	public String getSaldoTangible() {
		return saldoTangible;
	}
	public void setSaldoTangible(String saldoTangible) {
		this.saldoTangible = saldoTangible;
	}
	public String getSaldoIntangible() {
		return saldoIntangible;
	}
	public void setSaldoIntangible(String saldoIntangible) {
		this.saldoIntangible = saldoIntangible;
	}
	public String getDeudaTotal() {
		return deudaTotal;
	}
	public void setDeudaTotal(String deudaTotal) {
		this.deudaTotal = deudaTotal;
	}
	public String getSeguroDesgravamen() {
		return seguroDesgravamen;
	}
	public void setSeguroDesgravamen(String seguroDesgravamen) {
		this.seguroDesgravamen = seguroDesgravamen;
	}
	public String getInteres() {
		return interes;
	}
	public void setInteres(String interes) {
		this.interes = interes;
	}
	public String getRetencion() {
		return retencion;
	}
	public void setRetencion(String retencion) {
		this.retencion = retencion;
	}
	@Override
	public String toString() {
		return "SaldoResponse [codProducto=" + codProducto + ", numProducto="
				+ numProducto + ", saldoContable=" + saldoContable
				+ ", saldoActual=" + saldoActual + ", moneda=" + moneda
				+ ", idOperacion=" + idOperacion + ", codigoRetorno="
				+ codigoRetorno + ", mensaje=" + mensaje + ", mensajeDet="
				+ mensajeDet + ", saldoTangible=" + saldoTangible
				+ ", saldoIntangible=" + saldoIntangible + ", deudaTotal="
				+ deudaTotal + ", seguroDesgravamen=" + seguroDesgravamen
				+ ", interes=" + interes + ", retencion=" + retencion + "]";
	}
    
    
	
}
