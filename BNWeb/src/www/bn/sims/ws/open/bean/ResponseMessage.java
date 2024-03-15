/**
 * ResponseMessage.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf10631.06 v81706232132
 */

package www.bn.sims.ws.open.bean;

public class ResponseMessage  {
    private java.lang.String codComunicacion;
    private java.lang.String codRetorno;
    private java.lang.Integer nroRegistrosError;
    private java.lang.Integer nroRegistrosOk;
    private www.bn.sims.ws.open.bean.RptaListaEnvioSmss[] rptaListaEnvioSmss;

    public ResponseMessage() {
    }

    public java.lang.String getCodComunicacion() {
        return codComunicacion;
    }

    public void setCodComunicacion(java.lang.String codComunicacion) {
        this.codComunicacion = codComunicacion;
    }

    public java.lang.String getCodRetorno() {
        return codRetorno;
    }

    public void setCodRetorno(java.lang.String codRetorno) {
        this.codRetorno = codRetorno;
    }

    public java.lang.Integer getNroRegistrosError() {
        return nroRegistrosError;
    }

    public void setNroRegistrosError(java.lang.Integer nroRegistrosError) {
        this.nroRegistrosError = nroRegistrosError;
    }

    public java.lang.Integer getNroRegistrosOk() {
        return nroRegistrosOk;
    }

    public void setNroRegistrosOk(java.lang.Integer nroRegistrosOk) {
        this.nroRegistrosOk = nroRegistrosOk;
    }

    public www.bn.sims.ws.open.bean.RptaListaEnvioSmss[] getRptaListaEnvioSmss() {
        return rptaListaEnvioSmss;
    }

    public void setRptaListaEnvioSmss(www.bn.sims.ws.open.bean.RptaListaEnvioSmss[] rptaListaEnvioSmss) {
        this.rptaListaEnvioSmss = rptaListaEnvioSmss;
    }

}
