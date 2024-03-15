/**
 * WSPINBLOCKInformation.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf192102.03 v11421132253
 */

package _11._11._7._10;

public class WSPINBLOCKInformation implements com.ibm.ws.webservices.multiprotocol.ServiceInformation {

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
        inner0.put("operacionesPINBLOCKAhorros", list0);

        com.ibm.ws.webservices.engine.description.OperationDesc operacionesPINBLOCKAhorros0Op = _operacionesPINBLOCKAhorros0Op();
        list0.add(operacionesPINBLOCKAhorros0Op);

        java.util.List list1 = new java.util.ArrayList();
        inner0.put("operacionesPINBLOCKCtasCtes", list1);

        com.ibm.ws.webservices.engine.description.OperationDesc operacionesPINBLOCKCtasCtes1Op = _operacionesPINBLOCKCtasCtes1Op();
        list1.add(operacionesPINBLOCKCtasCtes1Op);

        operationDescriptions.put("WSPINBLOCKSoap",inner0);
        operationDescriptions = java.util.Collections.unmodifiableMap(operationDescriptions);
    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _operacionesPINBLOCKAhorros0Op() {
        com.ibm.ws.webservices.engine.description.OperationDesc operacionesPINBLOCKAhorros0Op = null;
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.11.11/WSDesaPINBLOCK/WSPINBLOCK", "wcOperacion"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, true, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.11.11/WSDesaPINBLOCK/WSPINBLOCK", "NroTarjeta"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, true, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.11.11/WSDesaPINBLOCK/WSPINBLOCK", "wPINEncrypt"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, true, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.11.11/WSDesaPINBLOCK/WSPINBLOCK", "wPINBLOCKNew"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, true, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.11.11/WSDesaPINBLOCK/WSPINBLOCK", "wPINBLOCKConf"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, true, true, false), 
          };
        _params0[0].setOption("partName","string");
        _params0[0].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params0[0].setOption("inputPosition","0");
        _params0[1].setOption("partName","string");
        _params0[1].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params0[1].setOption("inputPosition","1");
        _params0[2].setOption("partName","string");
        _params0[2].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params0[2].setOption("inputPosition","2");
        _params0[3].setOption("partName","string");
        _params0[3].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params0[3].setOption("inputPosition","3");
        _params0[4].setOption("partName","string");
        _params0[4].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params0[4].setOption("inputPosition","4");
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.11.11/WSDesaPINBLOCK/WSPINBLOCK", "OperacionesPINBLOCKAhorrosResult"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, true, false, false, true, true, false); 
        _returnDesc0.setOption("partName","string");
        _returnDesc0.setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _returnDesc0.setOption("outputPosition","0");
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        operacionesPINBLOCKAhorros0Op = new com.ibm.ws.webservices.engine.description.OperationDesc("operacionesPINBLOCKAhorros", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.11.11/WSDesaPINBLOCK/WSPINBLOCK", "OperacionesPINBLOCKAhorros"), _params0, _returnDesc0, _faults0, null);
        operacionesPINBLOCKAhorros0Op.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.11.11/WSDesaPINBLOCK/WSPINBLOCK", "WSPINBLOCKSoap"));
        operacionesPINBLOCKAhorros0Op.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.11.11/WSDesaPINBLOCK/WSPINBLOCK", "OperacionesPINBLOCKAhorrosSoapOut"));
        operacionesPINBLOCKAhorros0Op.setOption("ResponseLocalPart","OperacionesPINBLOCKAhorrosResponse");
        operacionesPINBLOCKAhorros0Op.setOption("targetNamespace","http://10.7.11.11/WSDesaPINBLOCK/WSPINBLOCK");
        operacionesPINBLOCKAhorros0Op.setOption("ResponseNamespace","http://10.7.11.11/WSDesaPINBLOCK/WSPINBLOCK");
        operacionesPINBLOCKAhorros0Op.setOption("buildNum","cf192102.03");
        operacionesPINBLOCKAhorros0Op.setOption("inoutOrderingReq","false");
        operacionesPINBLOCKAhorros0Op.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.11.11/WSDesaPINBLOCK/WSPINBLOCK", "WSPINBLOCK"));
        operacionesPINBLOCKAhorros0Op.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.11.11/WSDesaPINBLOCK/WSPINBLOCK", "OperacionesPINBLOCKAhorrosSoapIn"));
        operacionesPINBLOCKAhorros0Op.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return operacionesPINBLOCKAhorros0Op;

    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _operacionesPINBLOCKCtasCtes1Op() {
        com.ibm.ws.webservices.engine.description.OperationDesc operacionesPINBLOCKCtasCtes1Op = null;
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.11.11/WSDesaPINBLOCK/WSPINBLOCK", "wcOperacion"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, true, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.11.11/WSDesaPINBLOCK/WSPINBLOCK", "wTipoDocu"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, true, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.11.11/WSDesaPINBLOCK/WSPINBLOCK", "wNroDocu"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, true, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.11.11/WSDesaPINBLOCK/WSPINBLOCK", "wPINEncrypt"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, true, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.11.11/WSDesaPINBLOCK/WSPINBLOCK", "wPINBLOCKNew"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, true, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.11.11/WSDesaPINBLOCK/WSPINBLOCK", "wPINBLOCKConf"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, true, true, false), 
          };
        _params0[0].setOption("partName","string");
        _params0[0].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params0[0].setOption("inputPosition","0");
        _params0[1].setOption("partName","string");
        _params0[1].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params0[1].setOption("inputPosition","1");
        _params0[2].setOption("partName","string");
        _params0[2].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params0[2].setOption("inputPosition","2");
        _params0[3].setOption("partName","string");
        _params0[3].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params0[3].setOption("inputPosition","3");
        _params0[4].setOption("partName","string");
        _params0[4].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params0[4].setOption("inputPosition","4");
        _params0[5].setOption("partName","string");
        _params0[5].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params0[5].setOption("inputPosition","5");
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.11.11/WSDesaPINBLOCK/WSPINBLOCK", "OperacionesPINBLOCKCtasCtesResult"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, true, false, false, true, true, false); 
        _returnDesc0.setOption("partName","string");
        _returnDesc0.setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _returnDesc0.setOption("outputPosition","0");
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        operacionesPINBLOCKCtasCtes1Op = new com.ibm.ws.webservices.engine.description.OperationDesc("operacionesPINBLOCKCtasCtes", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.11.11/WSDesaPINBLOCK/WSPINBLOCK", "OperacionesPINBLOCKCtasCtes"), _params0, _returnDesc0, _faults0, null);
        operacionesPINBLOCKCtasCtes1Op.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.11.11/WSDesaPINBLOCK/WSPINBLOCK", "WSPINBLOCKSoap"));
        operacionesPINBLOCKCtasCtes1Op.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.11.11/WSDesaPINBLOCK/WSPINBLOCK", "OperacionesPINBLOCKCtasCtesSoapOut"));
        operacionesPINBLOCKCtasCtes1Op.setOption("ResponseLocalPart","OperacionesPINBLOCKCtasCtesResponse");
        operacionesPINBLOCKCtasCtes1Op.setOption("targetNamespace","http://10.7.11.11/WSDesaPINBLOCK/WSPINBLOCK");
        operacionesPINBLOCKCtasCtes1Op.setOption("ResponseNamespace","http://10.7.11.11/WSDesaPINBLOCK/WSPINBLOCK");
        operacionesPINBLOCKCtasCtes1Op.setOption("buildNum","cf192102.03");
        operacionesPINBLOCKCtasCtes1Op.setOption("inoutOrderingReq","false");
        operacionesPINBLOCKCtasCtes1Op.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.11.11/WSDesaPINBLOCK/WSPINBLOCK", "WSPINBLOCK"));
        operacionesPINBLOCKCtasCtes1Op.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://10.7.11.11/WSDesaPINBLOCK/WSPINBLOCK", "OperacionesPINBLOCKCtasCtesSoapIn"));
        operacionesPINBLOCKCtasCtes1Op.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return operacionesPINBLOCKCtasCtes1Op;

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
