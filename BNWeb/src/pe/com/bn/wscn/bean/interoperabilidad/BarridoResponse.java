/**
 * BarridoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf222226.01 v62922235826
 */

package pe.com.bn.wscn.bean.interoperabilidad;

public class BarridoResponse  {
    private java.lang.String codResult;
    private java.lang.String msg;
    private java.lang.String msgError;
    private pe.com.bn.wscn.bean.interoperabilidad.BarridoData[] data;

    public BarridoResponse() {
    }

    public java.lang.String getCodResult() {
        return codResult;
    }

    public void setCodResult(java.lang.String codResult) {
        this.codResult = codResult;
    }

    public java.lang.String getMsg() {
        return msg;
    }

    public void setMsg(java.lang.String msg) {
        this.msg = msg;
    }

    public java.lang.String getMsgError() {
        return msgError;
    }

    public void setMsgError(java.lang.String msgError) {
        this.msgError = msgError;
    }

    public pe.com.bn.wscn.bean.interoperabilidad.BarridoData[] getData() {
        return data;
    }

    public void setData(pe.com.bn.wscn.bean.interoperabilidad.BarridoData[] data) {
        this.data = data;
    }

}
