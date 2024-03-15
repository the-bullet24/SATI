/**
 * ResponseMessage_Deser.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf192102.03 v11421132253
 */

package www.bn.sims.ws.open.bean;

public class ResponseMessage_Deser extends com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializer {
    /**
     * Constructor
     */
    public ResponseMessage_Deser(
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType, 
           com.ibm.ws.webservices.engine.description.TypeDesc _typeDesc) {
        super(_javaType, _xmlType, _typeDesc);
    }
    /**
     * Create instance of java bean
     */
    public void createValue() {
        value = new ResponseMessage();
    }
    protected boolean tryElementSetFromString(javax.xml.namespace.QName qName, java.lang.String strValue) {
        if (qName==QName_3_32) {
          ((ResponseMessage)value).setCodComunicacion(strValue);
          return true;}
        else if (qName==QName_3_33) {
          ((ResponseMessage)value).setCodRetorno(strValue);
          return true;}
        else if (qName==QName_3_34) {
          ((ResponseMessage)value).setNroRegistrosError(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializer.parseInteger(strValue));
          return true;}
        else if (qName==QName_3_35) {
          ((ResponseMessage)value).setNroRegistrosOk(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializer.parseInteger(strValue));
          return true;}
        return false;
    }
    protected boolean tryAttributeSetFromString(javax.xml.namespace.QName qName, java.lang.String strValue) {
        return false;
    }
    protected boolean tryElementSetFromObject(javax.xml.namespace.QName qName, java.lang.Object objValue) {
        if (qName==QName_3_36) {
          if (objValue instanceof java.util.List) {
            www.bn.sims.ws.open.bean.RptaListaEnvioSmss[] array = new www.bn.sims.ws.open.bean.RptaListaEnvioSmss[((java.util.List)objValue).size()];
            ((java.util.List)objValue).toArray(array);
            ((ResponseMessage)value).setRptaListaEnvioSmss(array);
          } else { 
            ((ResponseMessage)value).setRptaListaEnvioSmss((www.bn.sims.ws.open.bean.RptaListaEnvioSmss[])objValue);}
          return true;}
        return false;
    }
    protected boolean tryElementSetFromList(javax.xml.namespace.QName qName, java.util.List listValue) {
        return false;
    }
    private final static javax.xml.namespace.QName QName_3_34 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "http://bean.open.ws.sims.bn.www",
                  "nroRegistrosError");
    private final static javax.xml.namespace.QName QName_3_35 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "http://bean.open.ws.sims.bn.www",
                  "nroRegistrosOk");
    private final static javax.xml.namespace.QName QName_3_32 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "http://bean.open.ws.sims.bn.www",
                  "codComunicacion");
    private final static javax.xml.namespace.QName QName_3_36 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "http://bean.open.ws.sims.bn.www",
                  "rptaListaEnvioSmss");
    private final static javax.xml.namespace.QName QName_3_33 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "http://bean.open.ws.sims.bn.www",
                  "codRetorno");
}
