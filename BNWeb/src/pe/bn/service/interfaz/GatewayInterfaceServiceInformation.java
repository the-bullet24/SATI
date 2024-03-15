/**
 * GatewayInterfaceServiceInformation.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf192102.03 v11421132253
 */

package pe.bn.service.interfaz;

public class GatewayInterfaceServiceInformation implements com.ibm.ws.webservices.multiprotocol.ServiceInformation {

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

        operationDescriptions.put("GatewayInterface",inner0);
        operationDescriptions = java.util.Collections.unmodifiableMap(operationDescriptions);
    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _enviarTramaConsulta0Op() {
        com.ibm.ws.webservices.engine.description.OperationDesc enviarTramaConsulta0Op = null;
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "clave"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "base64Binary"), byte[].class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "request"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.service.bn.pe", "RequestGateway"), pe.bn.service.bean.RequestGateway.class, false, false, false, false, true, false), 
          };
        _params0[0].setOption("partName","base64Binary");
        _params0[0].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}base64Binary");
        _params0[0].setOption("inputPosition","0");
        _params0[1].setOption("partName","RequestGateway");
        _params0[1].setOption("partQNameString","{http://bean.service.bn.pe}RequestGateway");
        _params0[1].setOption("inputPosition","1");
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "enviarTramaConsultaReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.service.bn.pe", "ResponseGateway"), pe.bn.service.bean.ResponseGateway.class, true, false, false, false, true, false); 
        _returnDesc0.setOption("partName","ResponseGateway");
        _returnDesc0.setOption("partQNameString","{http://bean.service.bn.pe}ResponseGateway");
        _returnDesc0.setOption("outputPosition","0");
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        enviarTramaConsulta0Op = new com.ibm.ws.webservices.engine.description.OperationDesc("enviarTramaConsulta", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interfaz.service.bn.pe", "enviarTramaConsulta"), _params0, _returnDesc0, _faults0, null);
        enviarTramaConsulta0Op.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interfaz.service.bn.pe", "GatewayInterface"));
        enviarTramaConsulta0Op.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interfaz.service.bn.pe", "enviarTramaConsultaResponse"));
        enviarTramaConsulta0Op.setOption("ResponseLocalPart","enviarTramaConsultaResponse");
        enviarTramaConsulta0Op.setOption("targetNamespace","http://interfaz.service.bn.pe");
        enviarTramaConsulta0Op.setOption("outputName","enviarTramaConsultaResponse");
        enviarTramaConsulta0Op.setOption("ResponseNamespace","http://interfaz.service.bn.pe");
        enviarTramaConsulta0Op.setOption("buildNum","cf192102.03");
        enviarTramaConsulta0Op.setOption("usingAddressing","false");
        enviarTramaConsulta0Op.setOption("inputName","enviarTramaConsultaRequest");
        enviarTramaConsulta0Op.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interfaz.service.bn.pe", "GatewayInterfaceService"));
        enviarTramaConsulta0Op.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interfaz.service.bn.pe", "enviarTramaConsultaRequest"));
        enviarTramaConsulta0Op.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return enviarTramaConsulta0Op;

    }


    private static void initTypeMappings() {
        typeMappings = new java.util.HashMap();
        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.service.bn.pe", "ResponseGateway"),
                         pe.bn.service.bean.ResponseGateway.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.service.bn.pe", "RequestGateway"),
                         pe.bn.service.bean.RequestGateway.class);

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
