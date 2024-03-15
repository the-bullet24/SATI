/**
 * ResponseMessage_Helper.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf192102.03 v11421132253
 */

package www.bn.sims.ws.open.bean;

public class ResponseMessage_Helper {
    // Type metadata
    private static final com.ibm.ws.webservices.engine.description.TypeDesc typeDesc =
        new com.ibm.ws.webservices.engine.description.TypeDesc(ResponseMessage.class);

    static {
        typeDesc.setOption("buildNum","cf192102.03");
        com.ibm.ws.webservices.engine.description.FieldDesc field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("codComunicacion");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.open.ws.sims.bn.www", "codComunicacion"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("codRetorno");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.open.ws.sims.bn.www", "codRetorno"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("nroRegistrosError");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.open.ws.sims.bn.www", "nroRegistrosError"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("nroRegistrosOk");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.open.ws.sims.bn.www", "nroRegistrosOk"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "int"));
        typeDesc.addFieldDesc(field);
        field = new com.ibm.ws.webservices.engine.description.ElementDesc();
        field.setFieldName("rptaListaEnvioSmss");
        field.setXmlName(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.open.ws.sims.bn.www", "rptaListaEnvioSmss"));
        field.setXmlType(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.open.ws.sims.bn.www", "ArrayOf_tns1_RptaListaEnvioSmss"));
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
          new ResponseMessage_Ser(
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
          new ResponseMessage_Deser(
            javaType, xmlType, typeDesc);
    };

}
