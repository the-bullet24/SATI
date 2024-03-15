/**
 * GrupoParametro_Deser.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf222226.01 v62922235826
 */

package pe.com.bn.comp.ws.bean;

public class GrupoParametro_Deser extends com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializer {
    /**
     * Constructor
     */
    public GrupoParametro_Deser(
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType, 
           com.ibm.ws.webservices.engine.description.TypeDesc _typeDesc) {
        super(_javaType, _xmlType, _typeDesc);
    }
    /**
     * Create instance of java bean
     */
    public void createValue() {
        value = new GrupoParametro();
    }
    protected boolean tryElementSetFromString(javax.xml.namespace.QName qName, java.lang.String strValue) {
        if (qName==QName_0_11) {
          ((GrupoParametro)value).setTipoGrupo(strValue);
          return true;}
        else if (qName==QName_0_12) {
          ((GrupoParametro)value).setAliasDescripGrupo(strValue);
          return true;}
        else if (qName==QName_0_13) {
          ((GrupoParametro)value).setAliasGrupo(strValue);
          return true;}
        return false;
    }
    protected boolean tryAttributeSetFromString(javax.xml.namespace.QName qName, java.lang.String strValue) {
        return false;
    }
    protected boolean tryElementSetFromObject(javax.xml.namespace.QName qName, java.lang.Object objValue) {
        if (qName==QName_0_14) {
          if (objValue instanceof java.util.List) {
            pe.com.bn.comp.ws.bean.Parametro[] array = new pe.com.bn.comp.ws.bean.Parametro[((java.util.List)objValue).size()];
            ((java.util.List)objValue).toArray(array);
            ((GrupoParametro)value).setParametro(array);
          } else { 
            ((GrupoParametro)value).setParametro((pe.com.bn.comp.ws.bean.Parametro[])objValue);}
          return true;}
        return false;
    }
    protected boolean tryElementSetFromList(javax.xml.namespace.QName qName, java.util.List listValue) {
        return false;
    }
    private final static javax.xml.namespace.QName QName_0_12 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "aliasDescripGrupo");
    private final static javax.xml.namespace.QName QName_0_11 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "tipoGrupo");
    private final static javax.xml.namespace.QName QName_0_13 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "aliasGrupo");
    private final static javax.xml.namespace.QName QName_0_14 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "parametro");
}
