/**
 * BarridoResponse_Helper.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf222226.01 v62922235826
 */

package pe.com.bn.wscn.bean.interoperabilidad;

public class BarridoResponse_Helper {
    // Type metadata
    private static final com.ibm.ws.webservices.engine.description.TypeDesc typeDesc =
        new com.ibm.ws.webservices.engine.description.TypeDesc(BarridoResponse.class);

    static {
        typeDesc.setOption("buildNum","cf222226.01");
        com.ibm.ws.webservices.engine.description.FieldDesc field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("codResult");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "codResult"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("msg");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "msg"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("msgError");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "msgError"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("data");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "data"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "ArrayOfBarridoData"));
        typeDesc.addFieldDesc(field);
    };

    /**
     * Return type metadata object
     */
    public static com.ibm.ws.webservices.engine.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static com.ibm.ws.webservices.engine.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class javaType,  
           javax.xml.namespace.QName xmlType) {
        return 
          new BarridoResponse_Ser(
            javaType, xmlType, typeDesc);
    };

    /**
     * Get Custom Deserializer
     */
    public static com.ibm.ws.webservices.engine.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class javaType,  
           javax.xml.namespace.QName xmlType) {
        return 
          new BarridoResponse_Deser(
            javaType, xmlType, typeDesc);
    };

}
