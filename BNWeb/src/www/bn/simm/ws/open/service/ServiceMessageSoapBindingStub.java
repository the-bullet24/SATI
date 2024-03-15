/**
 * ServiceMessageSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf192102.03 v11421132253
 */

package www.bn.simm.ws.open.service;

public class ServiceMessageSoapBindingStub extends com.ibm.ws.webservices.engine.client.Stub implements www.bn.simm.ws.open.service.ServiceMessage {
    public ServiceMessageSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws com.ibm.ws.webservices.engine.WebServicesFault {
        if (service == null) {
            super.service = new com.ibm.ws.webservices.engine.client.Service();
        }
        else {
            super.service = service;
        }
        super.engine = ((com.ibm.ws.webservices.engine.client.Service) super.service).getEngine();
        super.messageContexts = new com.ibm.ws.webservices.engine.MessageContext[1];
        java.lang.String theOption = (java.lang.String)super._getProperty("lastStubMapping");
        if (theOption == null || !theOption.equals("www.bn.simm.ws.open.service.ServiceMessageSoapBinding")) {
                initTypeMapping();
                super._setProperty("lastStubMapping","www.bn.simm.ws.open.service.ServiceMessageSoapBinding");
        }
        super.cachedEndpoint = endpointURL;
        super.connection = ((com.ibm.ws.webservices.engine.client.Service) super.service).getConnection(endpointURL);
    }

    private void initTypeMapping() {
        javax.xml.rpc.encoding.TypeMapping tm = super.getTypeMapping(com.ibm.ws.webservices.engine.Constants.URI_LITERAL_ENC);
        java.lang.Class javaType = null;
        javax.xml.namespace.QName xmlType = null;
        javax.xml.namespace.QName compQName = null;
        javax.xml.namespace.QName compTypeQName = null;
        com.ibm.ws.webservices.engine.encoding.SerializerFactory sf = null;
        com.ibm.ws.webservices.engine.encoding.DeserializerFactory df = null;
        javaType = www.bn.simm.ws.open.bean.RequestMessage.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.open.ws.simm.bn.www", "RequestMessage");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = www.bn.simm.ws.open.bean.ReqListMessage[].class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.open.ws.simm.bn.www", "ArrayOf_tns1_ReqListMessage");
        compQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.open.ws.simm.bn.www", "item");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.open.ws.simm.bn.www", "ReqListMessage");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.ArraySerializerFactory.class, javaType, xmlType, compQName, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.ArrayDeserializerFactory.class, javaType, xmlType, compQName, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = www.bn.simm.ws.open.bean.ReqListMessage.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.open.ws.simm.bn.www", "ReqListMessage");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = www.bn.simm.ws.open.bean.ResponseMessage.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.open.ws.simm.bn.www", "ResponseMessage");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = www.bn.simm.ws.open.bean.RptaListaEnvioCorreos[].class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.open.ws.simm.bn.www", "ArrayOf_tns1_RptaListaEnvioCorreos");
        compQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.open.ws.simm.bn.www", "item");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.open.ws.simm.bn.www", "RptaListaEnvioCorreos");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.ArraySerializerFactory.class, javaType, xmlType, compQName, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.ArrayDeserializerFactory.class, javaType, xmlType, compQName, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = www.bn.simm.ws.open.bean.RptaListaEnvioCorreos.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.open.ws.simm.bn.www", "RptaListaEnvioCorreos");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = www.bn.simm.ws.open.bean.Adjunto.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.open.ws.simm.bn.www", "Adjunto");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = www.bn.simm.ws.open.bean.DatosCorreo.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.open.ws.simm.bn.www", "DatosCorreo");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = www.bn.simm.ws.open.bean.DatosParametro.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.open.ws.simm.bn.www", "DatosParametro");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _sendMessageOperation0 = null;
    private static com.ibm.ws.webservices.engine.description.OperationDesc _getsendMessageOperation0() {
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
        _sendMessageOperation0 = new com.ibm.ws.webservices.engine.description.OperationDesc("sendMessage", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.open.ws.simm.bn.www", "sendMessage"), _params0, _returnDesc0, _faults0, "");
        _sendMessageOperation0.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.open.ws.simm.bn.www", "ServiceMessage"));
        _sendMessageOperation0.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.open.ws.simm.bn.www", "sendMessageResponse"));
        _sendMessageOperation0.setOption("ResponseLocalPart","sendMessageResponse");
        _sendMessageOperation0.setOption("targetNamespace","http://service.open.ws.simm.bn.www");
        _sendMessageOperation0.setOption("outputName","sendMessageResponse");
        _sendMessageOperation0.setOption("ResponseNamespace","http://service.open.ws.simm.bn.www");
        _sendMessageOperation0.setOption("buildNum","cf192102.03");
        _sendMessageOperation0.setOption("inoutOrderingReq","false");
        _sendMessageOperation0.setOption("inputName","sendMessageRequest");
        _sendMessageOperation0.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.open.ws.simm.bn.www", "ServiceMessageService"));
        _sendMessageOperation0.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://service.open.ws.simm.bn.www", "sendMessageRequest"));
        _sendMessageOperation0.setUse(com.ibm.ws.webservices.engine.enumtype.Use.LITERAL);
        _sendMessageOperation0.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return _sendMessageOperation0;

    }

    private int _sendMessageIndex0 = 0;
    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _getsendMessageInvoke0(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault  {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_sendMessageIndex0];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(ServiceMessageSoapBindingStub._sendMessageOperation0);
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("");
            mc.setEncodingStyle(com.ibm.ws.webservices.engine.Constants.URI_LITERAL_ENC);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.SEND_TYPE_ATTR_PROPERTY, Boolean.FALSE);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.ENGINE_DO_MULTI_REFS_PROPERTY, Boolean.FALSE);
            super.primeMessageContext(mc);
            super.messageContexts[_sendMessageIndex0] = mc;
        }
        try {
            mc = (com.ibm.ws.webservices.engine.MessageContext) mc.clone();
        }
        catch (CloneNotSupportedException cnse) {
            throw com.ibm.ws.webservices.engine.WebServicesFault.makeFault(cnse);
        }
        return new com.ibm.ws.webservices.engine.client.Stub.Invoke(connection, mc, parameters);
    }

    public www.bn.simm.ws.open.bean.ResponseMessage sendMessage(www.bn.simm.ws.open.bean.RequestMessage requestMessage) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        java.util.Vector _resp = null;
        try {
            _resp = _getsendMessageInvoke0(new java.lang.Object[] {requestMessage}).invoke();

        } catch (com.ibm.ws.webservices.engine.WebServicesFault wsf) {
            Exception e = wsf.getUserException();
            throw wsf;
        } 
        try {
            return (www.bn.simm.ws.open.bean.ResponseMessage) ((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue();
        } catch (java.lang.Exception _exception) {
            return (www.bn.simm.ws.open.bean.ResponseMessage) super.convert(((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue(), www.bn.simm.ws.open.bean.ResponseMessage.class);
        }
    }

    private static void _staticInit() {
        _sendMessageOperation0 = _getsendMessageOperation0();
    }

    static {
       _staticInit();
    }
}
