/**
 * RequestMessage_Deser.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf192102.03 v11421132253
 */

package www.bn.sims.ws.open.bean;

public class RequestMessage_Deser extends com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializer {
    /**
     * Constructor
     */
    public RequestMessage_Deser(
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType, 
           com.ibm.ws.webservices.engine.description.TypeDesc _typeDesc) {
        super(_javaType, _xmlType, _typeDesc);
    }
    /**
     * Create instance of java bean
     */
    public void createValue() {
        value = new RequestMessage();
    }
    protected boolean tryElementSetFromString(javax.xml.namespace.QName qName, java.lang.String strValue) {
        if (qName==QName_3_24) {
          ((RequestMessage)value).setCodRequerimiento(com.ibm.ws.webservices.engine.encoding.ser.SimpleDeserializer.parseInteger(strValue));
          return true;}
        return false;
    }
    protected boolean tryAttributeSetFromString(javax.xml.namespace.QName qName, java.lang.String strValue) {
        return false;
    }
    protected boolean tryElementSetFromObject(javax.xml.namespace.QName qName, java.lang.Object objValue) {
        if (qName==QName_3_25) {
          if (objValue instanceof java.util.List) {
            www.bn.sims.ws.open.bean.ReqListMessage[] array = new www.bn.sims.ws.open.bean.ReqListMessage[((java.util.List)objValue).size()];
            ((java.util.List)objValue).toArray(array);
            ((RequestMessage)value).setReqListMessage(array);
          } else { 
            ((RequestMessage)value).setReqListMessage((www.bn.sims.ws.open.bean.ReqListMessage[])objValue);}
          return true;}
        return false;
    }
    protected boolean tryElementSetFromList(javax.xml.namespace.QName qName, java.util.List listValue) {
        return false;
    }
    private final static javax.xml.namespace.QName QName_3_25 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "http://bean.open.ws.sims.bn.www",
                  "reqListMessage");
    private final static javax.xml.namespace.QName QName_3_24 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "http://bean.open.ws.sims.bn.www",
                  "codRequerimiento");
}
