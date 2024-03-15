/**
 * GeneraSmsAfiliacionResponse.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf222226.01 v62922235826
 */

package pe.com.bn.wscn.bean.configuracion;

public class GeneraSmsAfiliacionResponse  {
    private java.lang.String codResult;
    private pe.com.bn.wscn.bean.configuracion.DataSms data;
    private java.lang.String msg;
    private java.lang.String msgError;

    public GeneraSmsAfiliacionResponse() {
    }

    public java.lang.String getCodResult() {
        return codResult;
    }

    public void setCodResult(java.lang.String codResult) {
        this.codResult = codResult;
    }

    public pe.com.bn.wscn.bean.configuracion.DataSms getData() {
        return data;
    }

    public void setData(pe.com.bn.wscn.bean.configuracion.DataSms data) {
        this.data = data;
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

}
