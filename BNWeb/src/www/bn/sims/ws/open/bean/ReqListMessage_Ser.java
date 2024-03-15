/**
 * ReqListMessage_Ser.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf192102.03 v11421132253
 */

package www.bn.sims.ws.open.bean;

public class ReqListMessage_Ser extends com.ibm.ws.webservices.engine.encoding.ser.BeanSerializer {
    /**
     * Constructor
     */
    public ReqListMessage_Ser(
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
           elemQName = QName_3_28;
           context.qName2String(elemQName, true);
           elemQName = QName_3_29;
           context.qName2String(elemQName, true);
        return attributes;
    }
    protected void addElements(
        java.lang.Object value,
        com.ibm.ws.webservices.engine.encoding.SerializationContext context)
        throws java.io.IOException
    {
        ReqListMessage bean = (ReqListMessage) value;
        java.lang.Object propValue;
        javax.xml.namespace.QName propQName;
        {
          propQName = QName_3_28;
          propValue = bean.getDatosParametro();
          serializeChild(propQName, null, 
              propValue, 
              QName_3_30,
              true,null,context);
          propQName = QName_3_29;
          propValue = bean.getDatosSms();
          serializeChild(propQName, null, 
              propValue, 
              QName_3_31,
              true,null,context);
        }
    }
    private final static javax.xml.namespace.QName QName_3_30 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "http://bean.open.ws.sims.bn.www",
                  "DatosParametro");
    private final static javax.xml.namespace.QName QName_3_31 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "http://bean.open.ws.sims.bn.www",
                  "DatosSms");
    private final static javax.xml.namespace.QName QName_3_28 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "http://bean.open.ws.sims.bn.www",
                  "datosParametro");
    private final static javax.xml.namespace.QName QName_3_29 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "http://bean.open.ws.sims.bn.www",
                  "datosSms");
}
