/**
 * InterfazServicios_ServiceInformation.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf021411.02 v32414172304
 */

package com.ibm.bn;

public class InterfazServicios_ServiceInformation implements com.ibm.ws.webservices.multiprotocol.ServiceInformation {

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
        inner0.put("enviarTramaConsulta", list0);

        com.ibm.ws.webservices.engine.description.OperationDesc enviarTramaConsulta0Op = _enviarTramaConsulta0Op();
        list0.add(enviarTramaConsulta0Op);

        operationDescriptions.put("InterfazServiciosSOAP",inner0);
        operationDescriptions = java.util.Collections.unmodifiableMap(operationDescriptions);
    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _enviarTramaConsulta0Op() {
        com.ibm.ws.webservices.engine.description.OperationDesc enviarTramaConsulta0Op = null;
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "codTrans"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "tramaConsulta"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "tramaRespuesta"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "codRes"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false, false, false, true, false), 
          };
        _params0[0].setOption("partName","string");
        _params0[0].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params0[0].setOption("inputPosition","0");
        _params0[1].setOption("partName","string");
        _params0[1].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params0[1].setOption("inputPosition","1");
        _params0[2].setOption("partName","string");
        _params0[2].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params0[2].setOption("outputPosition","0");
        _params0[3].setOption("partName","int");
        _params0[3].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}int");
        _params0[3].setOption("outputPosition","1");
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(null, com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://websphere.ibm.com/webservices/", "Void"), void.class, true, false, false, false, true, true); 
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        enviarTramaConsulta0Op = new com.ibm.ws.webservices.engine.description.OperationDesc("enviarTramaConsulta", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bn.ibm.com/InterfazServicios/", "enviarTramaConsulta"), _params0, _returnDesc0, _faults0, null);
        enviarTramaConsulta0Op.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bn.ibm.com/InterfazServicios/", "InterfazServicios"));
        enviarTramaConsulta0Op.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bn.ibm.com/InterfazServicios/", "enviarTramaConsultaResponse"));
        enviarTramaConsulta0Op.setOption("ResponseLocalPart","enviarTramaConsultaResponse");
        enviarTramaConsulta0Op.setOption("targetNamespace","http://bn.ibm.com/InterfazServicios/");
        enviarTramaConsulta0Op.setOption("ResponseNamespace","http://bn.ibm.com/InterfazServicios/");
        enviarTramaConsulta0Op.setOption("buildNum","cf021411.02");
        enviarTramaConsulta0Op.setOption("inoutOrderingReq","true");
        enviarTramaConsulta0Op.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bn.ibm.com/InterfazServicios/", "InterfazServicios"));
        enviarTramaConsulta0Op.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bn.ibm.com/InterfazServicios/", "enviarTramaConsultaRequest"));
        enviarTramaConsulta0Op.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return enviarTramaConsulta0Op;

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
