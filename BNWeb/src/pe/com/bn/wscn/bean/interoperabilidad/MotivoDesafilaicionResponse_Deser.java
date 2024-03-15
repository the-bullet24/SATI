/**
 * MotivoDesafilaicionResponse_Deser.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf222226.01 v62922235826
 */

package pe.com.bn.wscn.bean.interoperabilidad;

public class MotivoDesafilaicionResponse_Deser extends com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializer {
    /**
     * Constructor
     */
    public MotivoDesafilaicionResponse_Deser(
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType, 
           com.ibm.ws.webservices.engine.description.TypeDesc _typeDesc) {
        super(_javaType, _xmlType, _typeDesc);
    }
    /**
     * Create instance of java bean
     */
    public void createValue() {
        value = new MotivoDesafilaicionResponse();
    }
    protected boolean tryElementSetFromString(javax.xml.namespace.QName qName, java.lang.String strValue) {
        if (qName==QName_0_25) {
          ((MotivoDesafilaicionResponse)value).setCodResult(strValue);
          return true;}
        else if (qName==QName_0_27) {
          ((MotivoDesafilaicionResponse)value).setMsg(strValue);
          return true;}
        else if (qName==QName_0_28) {
          ((MotivoDesafilaicionResponse)value).setMsgError(strValue);
          return true;}
        return false;
    }
    protected boolean tryAttributeSetFromString(javax.xml.namespace.QName qName, java.lang.String strValue) {
        return false;
    }
    protected boolean tryElementSetFromObject(javax.xml.namespace.QName qName, java.lang.Object objValue) {
        if (qName==QName_0_58) {
          if (objValue instanceof java.util.List) {
            pe.com.bn.wscn.bean.interoperabilidad.Motivo[] array = new pe.com.bn.wscn.bean.interoperabilidad.Motivo[((java.util.List)objValue).size()];
            ((java.util.List)objValue).toArray(array);
            ((MotivoDesafilaicionResponse)value).setMotivos(array);
          } else { 
            ((MotivoDesafilaicionResponse)value).setMotivos((pe.com.bn.wscn.bean.interoperabilidad.Motivo[])objValue);}
          return true;}
        return false;
    }
    protected boolean tryElementSetFromList(javax.xml.namespace.QName qName, java.util.List listValue) {
        return false;
    }
    private final static javax.xml.namespace.QName QName_0_58 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "motivos");
    private final static javax.xml.namespace.QName QName_0_25 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "codResult");
    private final static javax.xml.namespace.QName QName_0_28 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "msgError");
    private final static javax.xml.namespace.QName QName_0_27 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "msg");
}
