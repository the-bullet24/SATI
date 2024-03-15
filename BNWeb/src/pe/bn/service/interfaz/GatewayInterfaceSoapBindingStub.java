/**
 * GatewayInterfaceSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf192102.03 v11421132253
 */

package pe.bn.service.interfaz;

public class GatewayInterfaceSoapBindingStub extends com.ibm.ws.webservices.engine.client.Stub implements pe.bn.service.interfaz.GatewayInterface {
    public GatewayInterfaceSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws com.ibm.ws.webservices.engine.WebServicesFault {
        if (service == null) {
            super.service = new com.ibm.ws.webservices.engine.client.Service();
        }
        else {
            super.service = service;
        }
        super.engine = ((com.ibm.ws.webservices.engine.client.Service) super.service).getEngine();
        super.messageContexts = new com.ibm.ws.webservices.engine.MessageContext[1];
        java.lang.String theOption = (java.lang.String)super._getProperty("lastStubMapping");
        if (theOption == null || !theOption.equals("pe.bn.service.interfaz.GatewayInterfaceSoapBinding")) {
                initTypeMapping();
                super._setProperty("lastStubMapping","pe.bn.service.interfaz.GatewayInterfaceSoapBinding");
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
        javaType = pe.bn.service.bean.ResponseGateway.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.service.bn.pe", "ResponseGateway");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = pe.bn.service.bean.RequestGateway.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.service.bn.pe", "RequestGateway");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _enviarTramaConsultaOperation0 = null;
    private static com.ibm.ws.webservices.engine.description.OperationDesc _getenviarTramaConsultaOperation0() {
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
        _enviarTramaConsultaOperation0 = new com.ibm.ws.webservices.engine.description.OperationDesc("enviarTramaConsulta", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interfaz.service.bn.pe", "enviarTramaConsulta"), _params0, _returnDesc0, _faults0, "enviarTramaConsulta");
        _enviarTramaConsultaOperation0.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interfaz.service.bn.pe", "GatewayInterface"));
        _enviarTramaConsultaOperation0.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interfaz.service.bn.pe", "enviarTramaConsultaResponse"));
        _enviarTramaConsultaOperation0.setOption("ResponseLocalPart","enviarTramaConsultaResponse");
        _enviarTramaConsultaOperation0.setOption("targetNamespace","http://interfaz.service.bn.pe");
        _enviarTramaConsultaOperation0.setOption("outputName","enviarTramaConsultaResponse");
        _enviarTramaConsultaOperation0.setOption("ResponseNamespace","http://interfaz.service.bn.pe");
        _enviarTramaConsultaOperation0.setOption("buildNum","cf192102.03");
        _enviarTramaConsultaOperation0.setOption("usingAddressing","false");
        _enviarTramaConsultaOperation0.setOption("inputName","enviarTramaConsultaRequest");
        _enviarTramaConsultaOperation0.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interfaz.service.bn.pe", "GatewayInterfaceService"));
        _enviarTramaConsultaOperation0.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interfaz.service.bn.pe", "enviarTramaConsultaRequest"));
        _enviarTramaConsultaOperation0.setUse(com.ibm.ws.webservices.engine.enumtype.Use.LITERAL);
        _enviarTramaConsultaOperation0.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return _enviarTramaConsultaOperation0;

    }

    private int _enviarTramaConsultaIndex0 = 0;
    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _getenviarTramaConsultaInvoke0(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault  {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_enviarTramaConsultaIndex0];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(GatewayInterfaceSoapBindingStub._enviarTramaConsultaOperation0);
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("enviarTramaConsulta");
            mc.setEncodingStyle(com.ibm.ws.webservices.engine.Constants.URI_LITERAL_ENC);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.SEND_TYPE_ATTR_PROPERTY, Boolean.FALSE);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.ENGINE_DO_MULTI_REFS_PROPERTY, Boolean.FALSE);
            super.primeMessageContext(mc);
            super.messageContexts[_enviarTramaConsultaIndex0] = mc;
        }
        try {
            mc = (com.ibm.ws.webservices.engine.MessageContext) mc.clone();
        }
        catch (CloneNotSupportedException cnse) {
            throw com.ibm.ws.webservices.engine.WebServicesFault.makeFault(cnse);
        }
        return new com.ibm.ws.webservices.engine.client.Stub.Invoke(connection, mc, parameters);
    }

    public pe.bn.service.bean.ResponseGateway enviarTramaConsulta(byte[] clave, pe.bn.service.bean.RequestGateway request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        java.util.Vector _resp = null;
        try {
            _resp = _getenviarTramaConsultaInvoke0(new java.lang.Object[] {clave, request}).invoke();

        } catch (com.ibm.ws.webservices.engine.WebServicesFault wsf) {
            Exception e = wsf.getUserException();
            throw wsf;
        } 
        try {
            return (pe.bn.service.bean.ResponseGateway) ((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue();
        } catch (java.lang.Exception _exception) {
            return (pe.bn.service.bean.ResponseGateway) super.convert(((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue(), pe.bn.service.bean.ResponseGateway.class);
        }
    }

    private static void _staticInit() {
        _enviarTramaConsultaOperation0 = _getenviarTramaConsultaOperation0();
    }

    static {
       _staticInit();
    }
}
