/**
 * DesafiliacionRequest_Deser.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf222226.01 v62922235826
 */

package pe.com.bn.wscn.bean.interoperabilidad;

public class DesafiliacionRequest_Deser extends com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializer {
    /**
     * Constructor
     */
    public DesafiliacionRequest_Deser(
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType, 
           com.ibm.ws.webservices.engine.description.TypeDesc _typeDesc) {
        super(_javaType, _xmlType, _typeDesc);
    }
    /**
     * Create instance of java bean
     */
    public void createValue() {
        value = new DesafiliacionRequest();
    }
    protected boolean tryElementSetFromString(javax.xml.namespace.QName qName, java.lang.String strValue) {
        if (qName==QName_0_36) {
          ((DesafiliacionRequest)value).setMotivoDesafiliacion(strValue);
          return true;}
        else if (qName==QName_0_33) {
          ((DesafiliacionRequest)value).setCanal(strValue);
          return true;}
        else if (qName==QName_0_22) {
          ((DesafiliacionRequest)value).setCicCliente(strValue);
          return true;}
        else if (qName==QName_0_21) {
          ((DesafiliacionRequest)value).setNumCelular(strValue);
          return true;}
        else if (qName==QName_0_37) {
          ((DesafiliacionRequest)value).setCciOrigen(strValue);
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
    private final static javax.xml.namespace.QName QName_0_33 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "canal");
    private final static javax.xml.namespace.QName QName_0_37 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "cciOrigen");
    private final static javax.xml.namespace.QName QName_0_22 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "cicCliente");
    private final static javax.xml.namespace.QName QName_0_21 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "numCelular");
    private final static javax.xml.namespace.QName QName_0_36 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "motivoDesafiliacion");
}
