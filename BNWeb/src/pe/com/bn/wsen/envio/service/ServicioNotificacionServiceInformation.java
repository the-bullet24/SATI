/**
 * ServicioNotificacionServiceInformation.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf192102.03 v11421132253
 */

package pe.com.bn.wsen.envio.service;

public class ServicioNotificacionServiceInformation implements com.ibm.ws.webservices.multiprotocol.ServiceInformation {

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
        inner0.put("opAcuseRecibo", list0);

        com.ibm.ws.webservices.engine.description.OperationDesc opAcuseRecibo0Op = _opAcuseRecibo0Op();
        list0.add(opAcuseRecibo0Op);

        java.util.List list1 = new java.util.ArrayList();
        inner0.put("opEnviarNotificacionDoc", list1);

        com.ibm.ws.webservices.engine.description.OperationDesc opEnviarNotificacionDoc1Op = _opEnviarNotificacionDoc1Op();
        list1.add(opEnviarNotificacionDoc1Op);

        java.util.List list2 = new java.util.ArrayList();
        inner0.put("opLogo", list2);

        com.ibm.ws.webservices.engine.description.OperationDesc opLogo2Op = _opLogo2Op();
        list2.add(opLogo2Op);

        operationDescriptions.put("ServicioNotificacion",inner0);
        operationDescriptions = java.util.Collections.unmodifiableMap(operationDescriptions);
    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _opAcuseRecibo0Op() {
        com.ibm.ws.webservices.engine.description.OperationDesc opAcuseRecibo0Op = null;
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "request"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://request.envio.wsen.bn.com.pe", "AcuseReciboRequest"), pe.com.bn.wsen.envio.request.AcuseReciboRequest.class, false, false, false, false, true, false), 
          };
        _params0[0].setOption("partName","AcuseReciboRequest");
        _params0[0].setOption("partQNameString","{http://request.envio.wsen.bn.com.pe}AcuseReciboRequest");
        _params0[0].setOption("inputPosition","0");
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "opAcuseReciboReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://response.envio.wsen.bn.com.pe", "AcuseReciboResponse"), pe.com.bn.wsen.envio.response.AcuseReciboResponse.class, true, false, false, false, true, false); 
        _returnDesc0.setOption("partName","AcuseReciboResponse");
        _returnDesc0.setOption("partQNameString","{http://response.envio.wsen.bn.com.pe}AcuseReciboResponse");
        _returnDesc0.setOption("outputPosition","0");
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        opAcuseRecibo0Op = new com.ibm.ws.webservices.engine.description.OperationDesc("opAcuseRecibo", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.envio.wsen.bn.com.pe", "opAcuseRecibo"), _params0, _returnDesc0, _faults0, null);
        opAcuseRecibo0Op.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.envio.wsen.bn.com.pe", "ServicioNotificacion"));
        opAcuseRecibo0Op.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.envio.wsen.bn.com.pe", "opAcuseReciboResponse"));
        opAcuseRecibo0Op.setOption("ResponseLocalPart","opAcuseReciboResponse");
        opAcuseRecibo0Op.setOption("targetNamespace","http://service.envio.wsen.bn.com.pe");
        opAcuseRecibo0Op.setOption("outputName","opAcuseReciboResponse");
        opAcuseRecibo0Op.setOption("ResponseNamespace","http://service.envio.wsen.bn.com.pe");
        opAcuseRecibo0Op.setOption("buildNum","cf192102.03");
        opAcuseRecibo0Op.setOption("usingAddressing","false");
        opAcuseRecibo0Op.setOption("inoutOrderingReq","false");
        opAcuseRecibo0Op.setOption("inputName","opAcuseReciboRequest");
        opAcuseRecibo0Op.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.envio.wsen.bn.com.pe", "ServicioNotificacionService"));
        opAcuseRecibo0Op.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.envio.wsen.bn.com.pe", "opAcuseReciboRequest"));
        opAcuseRecibo0Op.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return opAcuseRecibo0Op;

    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _opEnviarNotificacionDoc1Op() {
        com.ibm.ws.webservices.engine.description.OperationDesc opEnviarNotificacionDoc1Op = null;
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "request"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://request.envio.wsen.bn.com.pe", "EnvioNotificacionRequest"), pe.com.bn.wsen.envio.request.EnvioNotificacionRequest.class, false, false, false, false, true, false), 
          };
        _params0[0].setOption("partName","EnvioNotificacionRequest");
        _params0[0].setOption("partQNameString","{http://request.envio.wsen.bn.com.pe}EnvioNotificacionRequest");
        _params0[0].setOption("inputPosition","0");
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "opEnviarNotificacionDocReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://response.envio.wsen.bn.com.pe", "EnvioNotificacionResponse"), pe.com.bn.wsen.envio.response.EnvioNotificacionResponse.class, true, false, false, false, true, false); 
        _returnDesc0.setOption("partName","EnvioNotificacionResponse");
        _returnDesc0.setOption("partQNameString","{http://response.envio.wsen.bn.com.pe}EnvioNotificacionResponse");
        _returnDesc0.setOption("outputPosition","0");
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        opEnviarNotificacionDoc1Op = new com.ibm.ws.webservices.engine.description.OperationDesc("opEnviarNotificacionDoc", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.envio.wsen.bn.com.pe", "opEnviarNotificacionDoc"), _params0, _returnDesc0, _faults0, null);
        opEnviarNotificacionDoc1Op.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.envio.wsen.bn.com.pe", "ServicioNotificacion"));
        opEnviarNotificacionDoc1Op.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.envio.wsen.bn.com.pe", "opEnviarNotificacionDocResponse"));
        opEnviarNotificacionDoc1Op.setOption("ResponseLocalPart","opEnviarNotificacionDocResponse");
        opEnviarNotificacionDoc1Op.setOption("targetNamespace","http://service.envio.wsen.bn.com.pe");
        opEnviarNotificacionDoc1Op.setOption("outputName","opEnviarNotificacionDocResponse");
        opEnviarNotificacionDoc1Op.setOption("ResponseNamespace","http://service.envio.wsen.bn.com.pe");
        opEnviarNotificacionDoc1Op.setOption("buildNum","cf192102.03");
        opEnviarNotificacionDoc1Op.setOption("usingAddressing","false");
        opEnviarNotificacionDoc1Op.setOption("inoutOrderingReq","false");
        opEnviarNotificacionDoc1Op.setOption("inputName","opEnviarNotificacionDocRequest");
        opEnviarNotificacionDoc1Op.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.envio.wsen.bn.com.pe", "ServicioNotificacionService"));
        opEnviarNotificacionDoc1Op.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.envio.wsen.bn.com.pe", "opEnviarNotificacionDocRequest"));
        opEnviarNotificacionDoc1Op.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return opEnviarNotificacionDoc1Op;

    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _opLogo2Op() {
        com.ibm.ws.webservices.engine.description.OperationDesc opLogo2Op = null;
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
          };
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "opLogoReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://response.envio.wsen.bn.com.pe", "ImagenResponse"), pe.com.bn.wsen.envio.response.ImagenResponse.class, true, false, false, false, true, false); 
        _returnDesc0.setOption("partName","ImagenResponse");
        _returnDesc0.setOption("partQNameString","{http://response.envio.wsen.bn.com.pe}ImagenResponse");
        _returnDesc0.setOption("outputPosition","0");
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        opLogo2Op = new com.ibm.ws.webservices.engine.description.OperationDesc("opLogo", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.envio.wsen.bn.com.pe", "opLogo"), _params0, _returnDesc0, _faults0, null);
        opLogo2Op.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.envio.wsen.bn.com.pe", "ServicioNotificacion"));
        opLogo2Op.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.envio.wsen.bn.com.pe", "opLogoResponse"));
        opLogo2Op.setOption("ResponseLocalPart","opLogoResponse");
        opLogo2Op.setOption("targetNamespace","http://service.envio.wsen.bn.com.pe");
        opLogo2Op.setOption("outputName","opLogoResponse");
        opLogo2Op.setOption("ResponseNamespace","http://service.envio.wsen.bn.com.pe");
        opLogo2Op.setOption("buildNum","cf192102.03");
        opLogo2Op.setOption("usingAddressing","false");
        opLogo2Op.setOption("inputName","opLogoRequest");
        opLogo2Op.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.envio.wsen.bn.com.pe", "ServicioNotificacionService"));
        opLogo2Op.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.envio.wsen.bn.com.pe", "opLogoRequest"));
        opLogo2Op.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return opLogo2Op;

    }


    private static void initTypeMappings() {
        typeMappings = new java.util.HashMap();
        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://request.envio.wsen.bn.com.pe", "EnvioNotificacionRequest"),
                         pe.com.bn.wsen.envio.request.EnvioNotificacionRequest.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://request.envio.wsen.bn.com.pe", "AcuseReciboRequest"),
                         pe.com.bn.wsen.envio.request.AcuseReciboRequest.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://response.envio.wsen.bn.com.pe", "EnvioNotificacionResponse"),
                         pe.com.bn.wsen.envio.response.EnvioNotificacionResponse.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://response.envio.wsen.bn.com.pe", "AcuseReciboResponse"),
                         pe.com.bn.wsen.envio.response.AcuseReciboResponse.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://response.envio.wsen.bn.com.pe", "ImagenResponse"),
                         pe.com.bn.wsen.envio.response.ImagenResponse.class);

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
