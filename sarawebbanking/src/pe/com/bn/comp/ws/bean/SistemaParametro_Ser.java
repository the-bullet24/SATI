/**
 * SistemaParametro_Ser.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf222226.01 v62922235826
 */

package pe.com.bn.comp.ws.bean;

public class SistemaParametro_Ser extends com.ibm.ws.webservices.engine.encoding.ser.BeanSerializer {
    /**
     * Constructor
     */
    public SistemaParametro_Ser(
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
        SistemaParametro bean = (SistemaParametro) value;
        java.lang.Object propValue;
        javax.xml.namespace.QName propQName;
        {
          propQName = QName_0_0;
          propValue = bean.getAliasSistema();
          if (propValue != null && !context.shouldSendXSIType()) {
            context.simpleElement(propQName, null, propValue.toString()); 
          } else {
            serializeChild(propQName, null, 
              propValue, 
              QName_1_3,
              true,null,context);
          }
          propQName = QName_0_1;
          propValue = bean.getGrupoParametro();
          serializeChild(propQName, null, 
              propValue, 
              QName_3_63,
              true,null,context);
          propQName = QName_0_2;
          propValue = bean.getProceso();
          serializeChild(propQName, null, 
              propValue, 
              QName_3_5,
              true,null,context);
        }
    }
    private final static javax.xml.namespace.QName QName_3_5 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "http://bean.ws.comp.bn.com.pe",
                  "ReturnProceso");
    private final static javax.xml.namespace.QName QName_1_3 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "http://www.w3.org/2001/XMLSchema",
                  "string");
    private final static javax.xml.namespace.QName QName_0_1 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "grupoParametro");
    private final static javax.xml.namespace.QName QName_0_2 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "proceso");
    private final static javax.xml.namespace.QName QName_0_0 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "aliasSistema");
    private final static javax.xml.namespace.QName QName_3_63 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "http://bean.ws.comp.bn.com.pe",
                  "ArrayOfGrupoParametro");
}
