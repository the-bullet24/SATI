/**
 * SistemaParametro_Deser.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf222226.01 v62922235826
 */

package pe.com.bn.comp.ws.bean;

public class SistemaParametro_Deser extends com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializer {
    /**
     * Constructor
     */
    public SistemaParametro_Deser(
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType, 
           com.ibm.ws.webservices.engine.description.TypeDesc _typeDesc) {
        super(_javaType, _xmlType, _typeDesc);
    }
    /**
     * Create instance of java bean
     */
    public void createValue() {
        value = new SistemaParametro();
    }
    protected boolean tryElementSetFromString(javax.xml.namespace.QName qName, java.lang.String strValue) {
        if (qName==QName_0_0) {
          ((SistemaParametro)value).setAliasSistema(strValue);
          return true;}
        return false;
    }
    protected boolean tryAttributeSetFromString(javax.xml.namespace.QName qName, java.lang.String strValue) {
        return false;
    }
    protected boolean tryElementSetFromObject(javax.xml.namespace.QName qName, java.lang.Object objValue) {
        if (qName==QName_0_1) {
          if (objValue instanceof java.util.List) {
            pe.com.bn.comp.ws.bean.GrupoParametro[] array = new pe.com.bn.comp.ws.bean.GrupoParametro[((java.util.List)objValue).size()];
            ((java.util.List)objValue).toArray(array);
            ((SistemaParametro)value).setGrupoParametro(array);
          } else { 
            ((SistemaParametro)value).setGrupoParametro((pe.com.bn.comp.ws.bean.GrupoParametro[])objValue);}
          return true;}
        else if (qName==QName_0_2) {
          ((SistemaParametro)value).setProceso((pe.com.bn.comp.ws.bean.ReturnProceso)objValue);
          return true;}
        return false;
    }
    protected boolean tryElementSetFromList(javax.xml.namespace.QName qName, java.util.List listValue) {
        return false;
    }
    private final static javax.xml.namespace.QName QName_0_1 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "grupoParametro");
    private final static javax.xml.namespace.QName QName_0_2 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "proceso");
    private final static javax.xml.namespace.QName QName_0_0 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "aliasSistema");
}
