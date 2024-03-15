package pe.bn.listener;

public class GrupoParametro {

	protected String aliasGrupo;
	protected String aliasDescripGrupo;
	protected String tipoGrupo;
	protected Parametro[] parametro;
	

	public String getTipoGrupo() {
		return tipoGrupo;
	}
	public void setTipoGrupo(String tipoGrupo) {
		this.tipoGrupo = tipoGrupo;
	}
	public String getAliasDescripGrupo() {
		return aliasDescripGrupo;
	}
	public void setAliasDescripGrupo(String aliasDescripGrupo) {
		this.aliasDescripGrupo = aliasDescripGrupo;
	}
	public String getAliasGrupo() {
		return aliasGrupo;
	}
	public void setAliasGrupo(String aliasGrupo) {
		this.aliasGrupo = aliasGrupo;
	}
	public Parametro[] getParametro() {
		return parametro;
	}
	public void setParametro(Parametro[] parametro) {
		this.parametro = parametro;
	}
	

	
}
