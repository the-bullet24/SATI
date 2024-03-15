/**
 * Afiliacion_Deser.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf222226.01 v62922235826
 */

package pe.com.bn.wscn.bean.interoperabilidad;

public class Afiliacion_Deser extends com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializer {
    /**
     * Constructor
     */
    public Afiliacion_Deser(
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType, 
           com.ibm.ws.webservices.engine.description.TypeDesc _typeDesc) {
        super(_javaType, _xmlType, _typeDesc);
    }
    /**
     * Create instance of java bean
     */
    public void createValue() {
        value = new Afiliacion();
    }
    protected boolean tryElementSetFromString(javax.xml.namespace.QName qName, java.lang.String strValue) {
        if (qName==QName_0_22) {
          ((Afiliacion)value).setCicCliente(strValue);
          return true;}
        else if (qName==QName_0_44) {
          ((Afiliacion)value).setFechaAfiliacion(strValue);
          return true;}
        else if (qName==QName_0_23) {
          ((Afiliacion)value).setTipoDocumento(strValue);
          return true;}
        else if (qName==QName_0_24) {
          ((Afiliacion)value).setNumDocumento(strValue);
          return true;}
        else if (qName==QName_0_33) {
          ((Afiliacion)value).setCanal(strValue);
          return true;}
        else if (qName==QName_0_45) {
          ((Afiliacion)value).setNumeroCelular(strValue);
          return true;}
        else if (qName==QName_0_19) {
          ((Afiliacion)value).setModeloCelular(strValue);
          return true;}
        else if (qName==QName_0_20) {
          ((Afiliacion)value).setNombreCliente(strValue);
          return true;}
        else if (qName==QName_0_46) {
          ((Afiliacion)value).setEstadoAfiliacion(strValue);
          return true;}
        else if (qName==QName_0_47) {
          ((Afiliacion)value).setNroCuenta(strValue);
          return true;}
        else if (qName==QName_0_48) {
          ((Afiliacion)value).setCci(strValue);
          return true;}
        else if (qName==QName_0_49) {
          ((Afiliacion)value).setTrace(strValue);
          return true;}
        else if (qName==QName_0_50) {
          ((Afiliacion)value).setIdRegistro(strValue);
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
    private final static javax.xml.namespace.QName QName_0_22 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "cicCliente");
    private final static javax.xml.namespace.QName QName_0_50 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "idRegistro");
    private final static javax.xml.namespace.QName QName_0_45 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "numeroCelular");
    private final static javax.xml.namespace.QName QName_0_46 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "estadoAfiliacion");
    private final static javax.xml.namespace.QName QName_0_19 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "modeloCelular");
    private final static javax.xml.namespace.QName QName_0_49 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "trace");
    private final static javax.xml.namespace.QName QName_0_24 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "numDocumento");
    private final static javax.xml.namespace.QName QName_0_33 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "canal");
    private final static javax.xml.namespace.QName QName_0_44 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "fechaAfiliacion");
    private final static javax.xml.namespace.QName QName_0_20 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "nombreCliente");
    private final static javax.xml.namespace.QName QName_0_48 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "cci");
    private final static javax.xml.namespace.QName QName_0_47 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "nroCuenta");
    private final static javax.xml.namespace.QName QName_0_23 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "tipoDocumento");
}
