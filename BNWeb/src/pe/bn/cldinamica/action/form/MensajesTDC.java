package pe.bn.cldinamica.action.form;

import java.util.List;

public class MensajesTDC {

	private Integer codRptaPrincipal;

	private String codAutorizacion;

	private String codElemSeg;

	private String codResultOper;

	private Integer codRptaPrincipalOper;

	private String codResult;

	private String msg;

	private String msgError;
	
	private boolean exito;
	
	private List<DataListas> dataListas;

	public String getCodResultOper() {
		return codResultOper;
	}

	public void setCodResultOper(String codResultOper) {
		this.codResultOper = codResultOper;
	}

	public String getCodElemSeg() {
		return codElemSeg;
	}

	public void setCodElemSeg(String codElemSeg) {
		this.codElemSeg = codElemSeg;
	}

	public String getCodAutorizacion() {
		return codAutorizacion;
	}

	public void setCodAutorizacion(String codAutorizacion) {
		this.codAutorizacion = codAutorizacion;
	}

	public Integer getCodRptaPrincipal() {
		return codRptaPrincipal;
	}

	public void setCodRptaPrincipal(Integer codRptaPrincipal) {
		this.codRptaPrincipal = codRptaPrincipal;
	}

	public Integer getCodRptaPrincipalOper() {
		return codRptaPrincipalOper;
	}

	public void setCodRptaPrincipalOper(Integer codRptaPrincipalOper) {
		this.codRptaPrincipalOper = codRptaPrincipalOper;
	}

	public String getCodResult() {
		return codResult;
	}

	public void setCodResult(String codResult) {
		this.codResult = codResult;
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

	public boolean isExito() {
		return exito;
	}

	public void setExito(boolean exito) {
		this.exito = exito;
	}

	public List<DataListas> getDataListas() {
		return dataListas;
	}

	public void setDataListas(List<DataListas> dataListas) {
		this.dataListas = dataListas;
	}


	

}
