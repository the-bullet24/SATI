package pe.bn.cldinamica.action.form;

import java.util.List;

public class ProductoResponse {
	
	private String codigoRetorno;
	private String mensaje;
	private String idOperacion;
	
	private String f09_cinterno_cic;
	private String f09_tdocmto_id;
	private String f09_tdocmto_id_des;
	private String f09_ndocmto_id;
	private String f09_nombre_clte;
	private String f09_cantidad_cta;
	private String f09_ultima_cta;
	
	private List<Cuentas> f09_cuentas;

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

	public String getIdOperacion() {
		return idOperacion;
	}

	public void setIdOperacion(String idOperacion) {
		this.idOperacion = idOperacion;
	}

	public String getF09_cinterno_cic() {
		return f09_cinterno_cic;
	}

	public void setF09_cinterno_cic(String f09_cinterno_cic) {
		this.f09_cinterno_cic = f09_cinterno_cic;
	}

	public String getF09_tdocmto_id() {
		return f09_tdocmto_id;
	}

	public void setF09_tdocmto_id(String f09_tdocmto_id) {
		this.f09_tdocmto_id = f09_tdocmto_id;
	}

	public String getF09_tdocmto_id_des() {
		return f09_tdocmto_id_des;
	}

	public void setF09_tdocmto_id_des(String f09_tdocmto_id_des) {
		this.f09_tdocmto_id_des = f09_tdocmto_id_des;
	}

	public String getF09_ndocmto_id() {
		return f09_ndocmto_id;
	}

	public void setF09_ndocmto_id(String f09_ndocmto_id) {
		this.f09_ndocmto_id = f09_ndocmto_id;
	}

	public String getF09_nombre_clte() {
		return f09_nombre_clte;
	}

	public void setF09_nombre_clte(String f09_nombre_clte) {
		this.f09_nombre_clte = f09_nombre_clte;
	}

	public String getF09_cantidad_cta() {
		return f09_cantidad_cta;
	}

	public void setF09_cantidad_cta(String f09_cantidad_cta) {
		this.f09_cantidad_cta = f09_cantidad_cta;
	}

	public String getF09_ultima_cta() {
		return f09_ultima_cta;
	}

	public void setF09_ultima_cta(String f09_ultima_cta) {
		this.f09_ultima_cta = f09_ultima_cta;
	}

	public List<Cuentas> getF09_cuentas() {
		return f09_cuentas;
	}

	public void setF09_cuentas(List<Cuentas> f09_cuentas) {
		this.f09_cuentas = f09_cuentas;
	}

	@Override
	public String toString() {
		return "ProductoResponse [codigoRetorno=" + codigoRetorno
				+ ", mensaje=" + mensaje + ", idOperacion=" + idOperacion
				+ ", f09_cinterno_cic=" + f09_cinterno_cic
				+ ", f09_tdocmto_id=" + f09_tdocmto_id
				+ ", f09_tdocmto_id_des=" + f09_tdocmto_id_des
				+ ", f09_ndocmto_id=" + f09_ndocmto_id + ", f09_nombre_clte="
				+ f09_nombre_clte + ", f09_cantidad_cta=" + f09_cantidad_cta
				+ ", f09_ultima_cta=" + f09_ultima_cta + ", f09_cuentas="
				+ f09_cuentas + "]";
	}

	
	

}
