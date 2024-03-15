/**
 * ServiceMessageServiceInformation.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf192102.03 v11421132253
 */

package www.bn.simm.ws.open.service;

public class ServiceMessageServiceInformation implements com.ibm.ws.webservices.multiprotocol.ServiceInformation {

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
        inner0.put("sendMessage", list0);

        com.ibm.ws.webservices.engine.description.OperationDesc sendMessage0Op = _sendMessage0Op();
        list0.add(sendMessage0Op);

        operationDescriptions.put("ServiceMessage",inner0);
        operationDescriptions = java.util.Collections.unmodifiableMap(operationDescriptions);
    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _sendMessage0Op() {
        com.ibm.ws.webservices.engine.description.OperationDesc sendMessage0Op = null;
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.open.ws.simm.bn.www", "requestMessage"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.open.ws.simm.bn.www", "RequestMessage"), www.bn.simm.ws.open.bean.RequestMessage.class, false, false, false, false, true, false), 
          };
        _params0[0].setOption("partName","RequestMessage");
        _params0[0].setOption("partQNameString","{http://bean.open.ws.simm.bn.www}RequestMessage");
        _params0[0].setOption("inputPosition","0");
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.open.ws.simm.bn.www", "sendMessageReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.open.ws.simm.bn.www", "ResponseMessage"), www.bn.simm.ws.open.bean.ResponseMessage.class, true, false, false, false, true, false); 
        _returnDesc0.setOption("partName","ResponseMessage");
        _returnDesc0.setOption("partQNameString","{http://bean.open.ws.simm.bn.www}ResponseMessage");
        _returnDesc0.setOption("outputPosition","0");
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        sendMessage0Op = new com.ibm.ws.webservices.engine.description.OperationDesc("sendMessage", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.open.ws.simm.bn.www", "sendMessage"), _params0, _returnDesc0, _faults0, null);
        sendMessage0Op.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.open.ws.simm.bn.www", "ServiceMessage"));
        sendMessage0Op.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.open.ws.simm.bn.www", "sendMessageResponse"));
        sendMessage0Op.setOption("ResponseLocalPart","sendMessageResponse");
        sendMessage0Op.setOption("targetNamespace","http://service.open.ws.simm.bn.www");
        sendMessage0Op.setOption("outputName","sendMessageResponse");
        sendMessage0Op.setOption("ResponseNamespace","http://service.open.ws.simm.bn.www");
        sendMessage0Op.setOption("buildNum","cf192102.03");
        sendMessage0Op.setOption("inoutOrderingReq","false");
        sendMessage0Op.setOption("inputName","sendMessageRequest");
        sendMessage0Op.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.open.ws.simm.bn.www", "ServiceMessageService"));
        sendMessage0Op.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.open.ws.simm.bn.www", "sendMessageRequest"));
        sendMessage0Op.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return sendMessage0Op;

    }


    private static void initTypeMappings() {
        typeMappings = new java.util.HashMap();
        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.open.ws.simm.bn.www", "RequestMessage"),
                         www.bn.simm.ws.open.bean.RequestMessage.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.open.ws.simm.bn.www", "ArrayOf_tns1_ReqListMessage"),
                         www.bn.simm.ws.open.bean.ReqListMessage[].class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.open.ws.simm.bn.www", "ReqListMessage"),
                         www.bn.simm.ws.open.bean.ReqListMessage.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.open.ws.simm.bn.www", "ResponseMessage"),
                         www.bn.simm.ws.open.bean.ResponseMessage.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.open.ws.simm.bn.www", "ArrayOf_tns1_RptaListaEnvioCorreos"),
                         www.bn.simm.ws.open.bean.RptaListaEnvioCorreos[].class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.open.ws.simm.bn.www", "RptaListaEnvioCorreos"),
                         www.bn.simm.ws.open.bean.RptaListaEnvioCorreos.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.open.ws.simm.bn.www", "Adjunto"),
                         www.bn.simm.ws.open.bean.Adjunto.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.open.ws.simm.bn.www", "DatosCorreo"),
                         www.bn.simm.ws.open.bean.DatosCorreo.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.open.ws.simm.bn.www", "DatosParametro"),
                         www.bn.simm.ws.open.bean.DatosParametro.class);

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
