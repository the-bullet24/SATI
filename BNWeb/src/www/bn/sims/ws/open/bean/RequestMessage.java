/**
 * RequestMessage.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf10631.06 v81706232132
 */

package www.bn.sims.ws.open.bean;

public class RequestMessage  {
    private java.lang.Integer codRequerimiento;
    private www.bn.sims.ws.open.bean.ReqListMessage[] reqListMessage;

    public RequestMessage() {
    }

    public java.lang.Integer getCodRequerimiento() {
        return codRequerimiento;
    }

    public void setCodRequerimiento(java.lang.Integer codRequerimiento) {
        this.codRequerimiento = codRequerimiento;
    }

    public www.bn.sims.ws.open.bean.ReqListMessage[] getReqListMessage() {
        return reqListMessage;
    }

    public void setReqListMessage(www.bn.sims.ws.open.bean.ReqListMessage[] reqListMessage) {
        this.reqListMessage = reqListMessage;
    }

}
