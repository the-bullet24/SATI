/**
 * RequestMessage_Ser.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf192102.03 v11421132253
 */

package www.bn.sims.ws.open.bean;

public class RequestMessage_Ser extends com.ibm.ws.webservices.engine.encoding.ser.BeanSerializer {
    /**
     * Constructor
     */
    public RequestMessage_Ser(
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType, 
           com.ibm.ws.webservices.engine.description.TypeDesc _typeDesc) {
        super(_javaType, _xmlType, _typeDesc);
    }
    public void serialize(
        javax.xml.namespace.QName name,
        org.xml.sax.Attributes attributes,
        java.lang.Object value,
        com.ibm.ws.webservices.engine.encoding.SerializationContext context)
        throws java.io.IOException
    {
        context.startElement(name, addAttributes(attributes, value, context));
        addElements(value, context);
        context.endElement();
    }
    protected org.xml.sax.Attributes addAttributes(
        org.xml.sax.Attributes attributes,
        java.lang.Object value,
        com.ibm.ws.webservices.engine.encoding.SerializationContext context)
        throws java.io.IOException
    {
           javax.xml.namespace.QName
           elemQName = QName_3_24;
           context.qName2String(elemQName, true);
           elemQName = QName_3_25;
           context.qName2String(elemQName, true);
        return attributes;
    }
    protected void addElements(
        java.lang.Object value,
        com.ibm.ws.webservices.engine.encoding.SerializationContext context)
        throws java.io.IOException
    {
        RequestMessage bean = (RequestMessage) value;
        java.lang.Object propValue;
        javax.xml.namespace.QName propQName;
        {
          propQName = QName_3_24;
          propValue = bean.getCodRequerimiento();
          serializeChild(propQName, null, 
              propValue, 
              QName_1_26,
              true,null,context);
          propQName = QName_3_25;
          propValue = bean.getReqListMessage();
          serializeChild(propQName, null, 
              propValue, 
              QName_4_27,
              true,null,context);
        }
    }
    private final static javax.xml.namespace.QName QName_4_27 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "http://service.open.ws.sims.bn.www",
                  "ArrayOf_tns1_ReqListMessage");
    private final static javax.xml.namespace.QName QName_3_25 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "http://bean.open.ws.sims.bn.www",
                  "reqListMessage");
    private final static javax.xml.namespace.QName QName_1_26 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "http://www.w3.org/2001/XMLSchema",
                  "int");
    private final static javax.xml.namespace.QName QName_3_24 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "http://bean.open.ws.sims.bn.www",
                  "codRequerimiento");
}
