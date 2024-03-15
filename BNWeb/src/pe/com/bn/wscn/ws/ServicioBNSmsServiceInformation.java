/**
 * ServicioBNSmsServiceInformation.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf222226.01 v62922235826
 */

package pe.com.bn.wscn.ws;

public class ServicioBNSmsServiceInformation implements com.ibm.ws.webservices.multiprotocol.ServiceInformation {

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
        inner0.put("opGenerarSmsAfiliacion", list0);

        com.ibm.ws.webservices.engine.description.OperationDesc opGenerarSmsAfiliacion0Op = _opGenerarSmsAfiliacion0Op();
        list0.add(opGenerarSmsAfiliacion0Op);

        java.util.List list1 = new java.util.ArrayList();
        inner0.put("opValidarSMSAfliacion", list1);

        com.ibm.ws.webservices.engine.description.OperationDesc opValidarSMSAfliacion1Op = _opValidarSMSAfliacion1Op();
        list1.add(opValidarSMSAfliacion1Op);

        operationDescriptions.put("ServicioBNSms",inner0);
        operationDescriptions = java.util.Collections.unmodifiableMap(operationDescriptions);
    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _opGenerarSmsAfiliacion0Op() {
        com.ibm.ws.webservices.engine.description.OperationDesc opGenerarSmsAfiliacion0Op = null;
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "request"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://configuracion.bean.wscn.bn.com.pe", "GeneraSmsAfiliacionRequest"), pe.com.bn.wscn.bean.configuracion.GeneraSmsAfiliacionRequest.class, false, false, false, false, true, false), 
          };
        _params0[0].setOption("partName","GeneraSmsAfiliacionRequest");
        _params0[0].setOption("partQNameString","{http://configuracion.bean.wscn.bn.com.pe}GeneraSmsAfiliacionRequest");
        _params0[0].setOption("inputPosition","0");
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "opGenerarSmsAfiliacionReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://configuracion.bean.wscn.bn.com.pe", "GeneraSmsAfiliacionResponse"), pe.com.bn.wscn.bean.configuracion.GeneraSmsAfiliacionResponse.class, true, false, false, false, true, false); 
        _returnDesc0.setOption("partName","GeneraSmsAfiliacionResponse");
        _returnDesc0.setOption("partQNameString","{http://configuracion.bean.wscn.bn.com.pe}GeneraSmsAfiliacionResponse");
        _returnDesc0.setOption("outputPosition","0");
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        opGenerarSmsAfiliacion0Op = new com.ibm.ws.webservices.engine.description.OperationDesc("opGenerarSmsAfiliacion", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "opGenerarSmsAfiliacion"), _params0, _returnDesc0, _faults0, null);
        opGenerarSmsAfiliacion0Op.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "ServicioBNSms"));
        opGenerarSmsAfiliacion0Op.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "opGenerarSmsAfiliacionResponse"));
        opGenerarSmsAfiliacion0Op.setOption("ResponseLocalPart","opGenerarSmsAfiliacionResponse");
        opGenerarSmsAfiliacion0Op.setOption("targetNamespace","http://ws.wscn.bn.com.pe");
        opGenerarSmsAfiliacion0Op.setOption("outputName","opGenerarSmsAfiliacionResponse");
        opGenerarSmsAfiliacion0Op.setOption("ResponseNamespace","http://ws.wscn.bn.com.pe");
        opGenerarSmsAfiliacion0Op.setOption("buildNum","cf222226.01");
        opGenerarSmsAfiliacion0Op.setOption("usingAddressing","false");
        opGenerarSmsAfiliacion0Op.setOption("inoutOrderingReq","false");
        opGenerarSmsAfiliacion0Op.setOption("inputName","opGenerarSmsAfiliacionRequest");
        opGenerarSmsAfiliacion0Op.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "ServicioBNSmsService"));
        opGenerarSmsAfiliacion0Op.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "opGenerarSmsAfiliacionRequest"));
        opGenerarSmsAfiliacion0Op.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return opGenerarSmsAfiliacion0Op;

    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _opValidarSMSAfliacion1Op() {
        com.ibm.ws.webservices.engine.description.OperationDesc opValidarSMSAfliacion1Op = null;
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "request"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://configuracion.bean.wscn.bn.com.pe", "ValidaSmsAfiliacionRequest"), pe.com.bn.wscn.bean.configuracion.ValidaSmsAfiliacionRequest.class, false, false, false, false, true, false), 
          };
        _params0[0].setOption("partName","ValidaSmsAfiliacionRequest");
        _params0[0].setOption("partQNameString","{http://configuracion.bean.wscn.bn.com.pe}ValidaSmsAfiliacionRequest");
        _params0[0].setOption("inputPosition","0");
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "opValidarSMSAfliacionReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://configuracion.bean.wscn.bn.com.pe", "ValidaSmsAfiliacionResponse"), pe.com.bn.wscn.bean.configuracion.ValidaSmsAfiliacionResponse.class, true, false, false, false, true, false); 
        _returnDesc0.setOption("partName","ValidaSmsAfiliacionResponse");
        _returnDesc0.setOption("partQNameString","{http://configuracion.bean.wscn.bn.com.pe}ValidaSmsAfiliacionResponse");
        _returnDesc0.setOption("outputPosition","0");
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        opValidarSMSAfliacion1Op = new com.ibm.ws.webservices.engine.description.OperationDesc("opValidarSMSAfliacion", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "opValidarSMSAfliacion"), _params0, _returnDesc0, _faults0, null);
        opValidarSMSAfliacion1Op.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "ServicioBNSms"));
        opValidarSMSAfliacion1Op.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "opValidarSMSAfliacionResponse"));
        opValidarSMSAfliacion1Op.setOption("ResponseLocalPart","opValidarSMSAfliacionResponse");
        opValidarSMSAfliacion1Op.setOption("targetNamespace","http://ws.wscn.bn.com.pe");
        opValidarSMSAfliacion1Op.setOption("outputName","opValidarSMSAfliacionResponse");
        opValidarSMSAfliacion1Op.setOption("ResponseNamespace","http://ws.wscn.bn.com.pe");
        opValidarSMSAfliacion1Op.setOption("buildNum","cf222226.01");
        opValidarSMSAfliacion1Op.setOption("usingAddressing","false");
        opValidarSMSAfliacion1Op.setOption("inoutOrderingReq","false");
        opValidarSMSAfliacion1Op.setOption("inputName","opValidarSMSAfliacionRequest");
        opValidarSMSAfliacion1Op.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "ServicioBNSmsService"));
        opValidarSMSAfliacion1Op.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "opValidarSMSAfliacionRequest"));
        opValidarSMSAfliacion1Op.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return opValidarSMSAfliacion1Op;

    }


    private static void initTypeMappings() {
        typeMappings = new java.util.HashMap();
        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://configuracion.bean.wscn.bn.com.pe", "GeneraSmsAfiliacionRequest"),
                         pe.com.bn.wscn.bean.configuracion.GeneraSmsAfiliacionRequest.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://configuracion.bean.wscn.bn.com.pe", "GeneraSmsAfiliacionResponse"),
                         pe.com.bn.wscn.bean.configuracion.GeneraSmsAfiliacionResponse.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://configuracion.bean.wscn.bn.com.pe", "DataSms"),
                         pe.com.bn.wscn.bean.configuracion.DataSms.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://configuracion.bean.wscn.bn.com.pe", "ValidaSmsAfiliacionRequest"),
                         pe.com.bn.wscn.bean.configuracion.ValidaSmsAfiliacionRequest.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://configuracion.bean.wscn.bn.com.pe", "ValidaSmsAfiliacionResponse"),
                         pe.com.bn.wscn.bean.configuracion.ValidaSmsAfiliacionResponse.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://configuracion.bean.wscn.bn.com.pe", "DataValidacion"),
                         pe.com.bn.wscn.bean.configuracion.DataValidacion.class);

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
