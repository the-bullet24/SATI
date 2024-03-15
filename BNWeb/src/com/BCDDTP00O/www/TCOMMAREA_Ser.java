/**
 * TCOMMAREA_Ser.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * o0444.10 v11404193627
 */

package com.BCDDTP00O.www;

public class TCOMMAREA_Ser extends com.ibm.ws.webservices.engine.encoding.ser.BeanSerializer {
    /**
     * Constructor
     */
    public TCOMMAREA_Ser(
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
        return attributes;
    }
    protected void addElements(
        java.lang.Object value,
        com.ibm.ws.webservices.engine.encoding.SerializationContext context)
        throws java.io.IOException
    {
        TCOMMAREA bean = (TCOMMAREA) value;
        java.lang.Object propValue;
        javax.xml.namespace.QName propQName;
        {
          propQName = QName_0_0;
          propValue = bean.getTrama();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_2_1,
              true,null,context);
          }
          propQName = QName_0_2;
          propValue = new java.lang.Short(bean.getCodres());
          serializeChild(propQName, null, 
              propValue, 
              QName_2_3,
              true,null,context);
        }
    }
    private final static javax.xml.namespace.QName QName_2_1 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "http://www.BCDDTP00O.com/schemas/BCDDTP00OInterface",
                  ">TCOMMAREA>trama");
    private final static javax.xml.namespace.QName QName_2_3 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "http://www.BCDDTP00O.com/schemas/BCDDTP00OInterface",
                  ">TCOMMAREA>codres");
    private final static javax.xml.namespace.QName QName_0_2 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "codres");
    private final static javax.xml.namespace.QName QName_0_0 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "trama");
}
