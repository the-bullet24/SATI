package pe.bn.listener;



public class ParametroResponse {
	
	private String aliasSistema;
	private String codigoRetorno;
	private String mensaje;
	private GrupoParametro[] grupoParametro;
	
	public String getAliasSistema() {
		return aliasSistema;
	}
	public void setAliasSistema(String aliasSistema) {
		this.aliasSistema = aliasSistema;
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
	public GrupoParametro[] getGrupoParametro() {
		return grupoParametro;
	}
	public void setGrupoParametro(GrupoParametro[] grupoParametro) {
		this.grupoParametro = grupoParametro;
	}

	
}
