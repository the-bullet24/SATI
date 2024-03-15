/**
 * SistemaParametro_Helper.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf222226.01 v62922235826
 */

package pe.com.bn.comp.ws.bean;

public class SistemaParametro_Helper {
    // Type metadata
    private static final com.ibm.ws.webservices.engine.description.TypeDesc typeDesc =
        new com.ibm.ws.webservices.engine.description.TypeDesc(SistemaParametro.class);

    static {
        typeDesc.setOption("buildNum","cf222226.01");
        com.ibm.ws.webservices.engine.description.FieldDesc field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("aliasSistema");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "aliasSistema"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("grupoParametro");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "grupoParametro"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.ws.comp.bn.com.pe", "ArrayOfGrupoParametro"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("proceso");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "proceso"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.ws.comp.bn.com.pe", "ReturnProceso"));
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
          new SistemaParametro_Ser(
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
          new SistemaParametro_Deser(
            javaType, xmlType, typeDesc);
    };

}
