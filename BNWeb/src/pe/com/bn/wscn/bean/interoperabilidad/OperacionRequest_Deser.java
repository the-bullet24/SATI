/**
 * OperacionRequest_Deser.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf222226.01 v62922235826
 */

package pe.com.bn.wscn.bean.interoperabilidad;

public class OperacionRequest_Deser extends com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializer {
    /**
     * Constructor
     */
    public OperacionRequest_Deser(
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType, 
           com.ibm.ws.webservices.engine.description.TypeDesc _typeDesc) {
        super(_javaType, _xmlType, _typeDesc);
    }
    /**
     * Create instance of java bean
     */
    public void createValue() {
        value = new OperacionRequest();
    }
    protected boolean tryElementSetFromString(javax.xml.namespace.QName qName, java.lang.String strValue) {
        if (qName==QName_0_21) {
          ((OperacionRequest)value).setNumCelular(strValue);
          return true;}
        else if (qName==QName_0_39) {
          ((OperacionRequest)value).setCciCliente(strValue);
          return true;}
        else if (qName==QName_0_20) {
          ((OperacionRequest)value).setNombreCliente(strValue);
          return true;}
        else if (qName==QName_0_40) {
          ((OperacionRequest)value).setCic(strValue);
          return true;}
        else if (qName==QName_0_23) {
          ((OperacionRequest)value).setTipoDocumento(strValue);
          return true;}
        else if (qName==QName_0_51) {
          ((OperacionRequest)value).setNumDcoumento(strValue);
          return true;}
        else if (qName==QName_0_33) {
          ((OperacionRequest)value).setCanal(strValue);
          return true;}
        else if (qName==QName_0_19) {
          ((OperacionRequest)value).setModeloCelular(strValue);
          return true;}
        else if (qName==QName_0_52) {
          ((OperacionRequest)value).setNumcuenta(strValue);
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
    private final static javax.xml.namespace.QName QName_0_51 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "numDcoumento");
    private final static javax.xml.namespace.QName QName_0_21 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "numCelular");
    private final static javax.xml.namespace.QName QName_0_19 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "modeloCelular");
    private final static javax.xml.namespace.QName QName_0_40 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "cic");
    private final static javax.xml.namespace.QName QName_0_33 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "canal");
    private final static javax.xml.namespace.QName QName_0_52 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "numcuenta");
    private final static javax.xml.namespace.QName QName_0_20 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "nombreCliente");
    private final static javax.xml.namespace.QName QName_0_39 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "cciCliente");
    private final static javax.xml.namespace.QName QName_0_23 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "tipoDocumento");
}
