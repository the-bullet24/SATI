/**
 * WSCertiDatosCuentaInformation.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf192102.03 v11421132253
 */

package _11._106._7._10;

public class WSCertiDatosCuentaInformation implements com.ibm.ws.webservices.multiprotocol.ServiceInformation {

    private static java.util.Map operationDescriptions;
    private static java.util.Map typeMappings;

    static {
         initOperationDescriptions();
         initTypeMappings();
    }

    private static void initOperationDescriptions() { 
        operationDescriptions = new java.util.HashMap();

        java.util.Map inner0 = new java.util.HashMap();

        java.util.List list0 = new java.util.ArrayList();
        inner0.put("operacionesDatosCuenta", list0);

        com.ibm.ws.webservices.engine.description.OperationDesc operacionesDatosCuenta0Op = _operacionesDatosCuenta0Op();
        list0.add(operacionesDatosCuenta0Op);

        operationDescriptions.put("WSCertiDatosCuentaSoap",inner0);
        operationDescriptions = java.util.Collections.unmodifiableMap(operationDescriptions);
    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _operacionesDatosCuenta0Op() {
        com.ibm.ws.webservices.engine.description.OperationDesc operacionesDatosCuenta0Op = null;
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.106.11/WSCertiDatosCuenta/WSCertiDatosCuenta", "wNroCuenta"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, true, true, false), 
          };
        _params0[0].setOption("partName","string");
        _params0[0].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params0[0].setOption("inputPosition","0");
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.106.11/WSCertiDatosCuenta/WSCertiDatosCuenta", "OperacionesDatosCuentaResult"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, true, false, false, true, true, false); 
        _returnDesc0.setOption("partName","string");
        _returnDesc0.setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _returnDesc0.setOption("outputPosition","0");
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        operacionesDatosCuenta0Op = new com.ibm.ws.webservices.engine.description.OperationDesc("operacionesDatosCuenta", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.106.11/WSCertiDatosCuenta/WSCertiDatosCuenta", "OperacionesDatosCuenta"), _params0, _returnDesc0, _faults0, null);
        operacionesDatosCuenta0Op.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.106.11/WSCertiDatosCuenta/WSCertiDatosCuenta", "WSCertiDatosCuentaSoap"));
        operacionesDatosCuenta0Op.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.106.11/WSCertiDatosCuenta/WSCertiDatosCuenta", "OperacionesDatosCuentaSoapOut"));
        operacionesDatosCuenta0Op.setOption("ResponseLocalPart","OperacionesDatosCuentaResponse");
        operacionesDatosCuenta0Op.setOption("targetNamespace","http://10.7.106.11/WSCertiDatosCuenta/WSCertiDatosCuenta");
        operacionesDatosCuenta0Op.setOption("ResponseNamespace","http://10.7.106.11/WSCertiDatosCuenta/WSCertiDatosCuenta");
        operacionesDatosCuenta0Op.setOption("buildNum","cf192102.03");
        operacionesDatosCuenta0Op.setOption("inoutOrderingReq","false");
        operacionesDatosCuenta0Op.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.106.11/WSCertiDatosCuenta/WSCertiDatosCuenta", "WSCertiDatosCuenta"));
        operacionesDatosCuenta0Op.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.106.11/WSCertiDatosCuenta/WSCertiDatosCuenta", "OperacionesDatosCuentaSoapIn"));
        operacionesDatosCuenta0Op.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return operacionesDatosCuenta0Op;

    }


    private static void initTypeMappings() {
        typeMappings = new java.util.HashMap();
        typeMappings = java.util.Collections.unmodifiableMap(typeMappings);
    }

    public java.util.Map getTypeMappings() {
        return typeMappings;
    }

    public Class getJavaType(javax.xml.namespace.QName xmlName) {
        return (Class) typeMappings.get(xmlName);
    }

    public java.util.Map getOperationDescriptions(String portName) {
        return (java.util.Map) operationDescriptions.get(portName);
    }

    public java.util.List getOperationDescriptions(String portName, String operationName) {
        java.util.Map map = (java.util.Map) operationDescriptions.get(portName);
        if (map != null) {
            return (java.util.List) map.get(operationName);
        }
        return null;
    }

}
