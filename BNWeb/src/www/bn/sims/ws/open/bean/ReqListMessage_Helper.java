/**
 * ReqListMessage_Helper.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf192102.03 v11421132253
 */

package www.bn.sims.ws.open.bean;

public class ReqListMessage_Helper {
    // Type metadata
    private static final com.ibm.ws.webservices.engine.description.TypeDesc typeDesc =
        new com.ibm.ws.webservices.engine.description.TypeDesc(ReqListMessage.class);

    static {
        typeDesc.setOption("buildNum","cf192102.03");
        com.ibm.ws.webservices.engine.description.FieldDesc field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("datosParametro");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.open.ws.sims.bn.www", "datosParametro"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.open.ws.sims.bn.www", "DatosParametro"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("datosSms");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.open.ws.sims.bn.www", "datosSms"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.open.ws.sims.bn.www", "DatosSms"));
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
          new ReqListMessage_Ser(
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
          new ReqListMessage_Deser(
            javaType, xmlType, typeDesc);
    };

}
