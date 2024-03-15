/**
 * ValidaSmsAfiliacionRequest_Deser.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf222226.01 v62922235826
 */

package pe.com.bn.wscn.bean.configuracion;

public class ValidaSmsAfiliacionRequest_Deser extends com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializer {
    /**
     * Constructor
     */
    public ValidaSmsAfiliacionRequest_Deser(
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType, 
           com.ibm.ws.webservices.engine.description.TypeDesc _typeDesc) {
        super(_javaType, _xmlType, _typeDesc);
    }
    /**
     * Create instance of java bean
     */
    public void createValue() {
        value = new ValidaSmsAfiliacionRequest();
    }
    protected boolean tryElementSetFromString(javax.xml.namespace.QName qName, java.lang.String strValue) {
        if (qName==QName_0_30) {
          ((ValidaSmsAfiliacionRequest)value).setCodeSMS(strValue);
          return true;}
        else if (qName==QName_0_23) {
          ((ValidaSmsAfiliacionRequest)value).setTipoDocumento(strValue);
          return true;}
        else if (qName==QName_0_24) {
          ((ValidaSmsAfiliacionRequest)value).setNumDocumento(strValue);
          return true;}
        else if (qName==QName_0_22) {
          ((ValidaSmsAfiliacionRequest)value).setCicCliente(strValue);
          return true;}
        else if (qName==QName_0_21) {
          ((ValidaSmsAfiliacionRequest)value).setNumCelular(strValue);
          return true;}
        else if (qName==QName_0_19) {
          ((ValidaSmsAfiliacionRequest)value).setModeloCelular(strValue);
          return true;}
        else if (qName==QName_0_33) {
          ((ValidaSmsAfiliacionRequest)value).setCanal(strValue);
          return true;}
        return false;
    }
    protected boolean tryAttributeSetFromString(javax.xml.namespace.QName qName, java.lang.String strValue) {
        return false;
    }
    protected boolean tryElementSetFromObject(javax.xml.namespace.QName qName, java.lang.Object objValue) {
        return false;
    }
    protected boolean tryElementSetFromList(javax.xml.namespace.QName qName, java.util.List listValue) {
        return false;
    }
    private final static javax.xml.namespace.QName QName_0_30 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "codeSMS");
    private final static javax.xml.namespace.QName QName_0_33 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "canal");
    private final static javax.xml.namespace.QName QName_0_19 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "modeloCelular");
    private final static javax.xml.namespace.QName QName_0_23 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "tipoDocumento");
    private final static javax.xml.namespace.QName QName_0_24 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "numDocumento");
    private final static javax.xml.namespace.QName QName_0_22 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "cicCliente");
    private final static javax.xml.namespace.QName QName_0_21 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "numCelular");
}
