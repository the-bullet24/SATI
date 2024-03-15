/**
 * SistemaParametro.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf021411.02 v32414172304
 */

package pe.com.bn.comp.ws.bean;

public class SistemaParametro  {
    private java.lang.String aliasSistema;
    private pe.com.bn.comp.ws.bean.GrupoParametro[] grupoParametro;
    private pe.com.bn.comp.ws.bean.ReturnProceso proceso;

    public SistemaParametro() {
    }

    public java.lang.String getAliasSistema() {
        return aliasSistema;
    }

    public void setAliasSistema(java.lang.String aliasSistema) {
        this.aliasSistema = aliasSistema;
    }

    public pe.com.bn.comp.ws.bean.GrupoParametro[] getGrupoParametro() {
        return grupoParametro;
    }

    public void setGrupoParametro(pe.com.bn.comp.ws.bean.GrupoParametro[] grupoParametro) {
        this.grupoParametro = grupoParametro;
    }

    public pe.com.bn.comp.ws.bean.ReturnProceso getProceso() {
        return proceso;
    }

    public void setProceso(pe.com.bn.comp.ws.bean.ReturnProceso proceso) {
        this.proceso = proceso;
    }

}
