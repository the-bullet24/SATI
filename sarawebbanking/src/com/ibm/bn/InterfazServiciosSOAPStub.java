/**
 * InterfazServiciosSOAPStub.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf021411.02 v32414172304
 */

package com.ibm.bn;

public class InterfazServiciosSOAPStub extends com.ibm.ws.webservices.engine.client.Stub implements com.ibm.bn.InterfazServicios_PortType {
    public InterfazServiciosSOAPStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws com.ibm.ws.webservices.engine.WebServicesFault {
        if (service == null) {
            super.service = new com.ibm.ws.webservices.engine.client.Service();
        }
        else {
            super.service = service;
        }
        super.engine = ((com.ibm.ws.webservices.engine.client.Service) super.service).getEngine();
        super.messageContexts = new com.ibm.ws.webservices.engine.MessageContext[1];
        java.lang.String theOption = (java.lang.String)super._getProperty("lastStubMapping");
        if (theOption == null || !theOption.equals("com.ibm.bn.InterfazServiciosSOAP")) {
                initTypeMapping();
                super._setProperty("lastStubMapping","com.ibm.bn.InterfazServiciosSOAP");
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
    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _enviarTramaConsultaOperation0 = null;
    private static com.ibm.ws.webservices.engine.description.OperationDesc _getenviarTramaConsultaOperation0() {
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
        _enviarTramaConsultaOperation0 = new com.ibm.ws.webservices.engine.description.OperationDesc("enviarTramaConsulta", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bn.ibm.com/InterfazServicios/", "enviarTramaConsulta"), _params0, _returnDesc0, _faults0, "http://bn.ibm.com/InterfazServicios/NewOperation");
        _enviarTramaConsultaOperation0.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bn.ibm.com/InterfazServicios/", "InterfazServicios"));
        _enviarTramaConsultaOperation0.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bn.ibm.com/InterfazServicios/", "enviarTramaConsultaResponse"));
        _enviarTramaConsultaOperation0.setOption("ResponseLocalPart","enviarTramaConsultaResponse");
        _enviarTramaConsultaOperation0.setOption("targetNamespace","http://bn.ibm.com/InterfazServicios/");
        _enviarTramaConsultaOperation0.setOption("ResponseNamespace","http://bn.ibm.com/InterfazServicios/");
        _enviarTramaConsultaOperation0.setOption("buildNum","cf021411.02");
        _enviarTramaConsultaOperation0.setOption("inoutOrderingReq","true");
        _enviarTramaConsultaOperation0.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bn.ibm.com/InterfazServicios/", "InterfazServicios"));
        _enviarTramaConsultaOperation0.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bn.ibm.com/InterfazServicios/", "enviarTramaConsultaRequest"));
        _enviarTramaConsultaOperation0.setUse(com.ibm.ws.webservices.engine.enumtype.Use.LITERAL);
        _enviarTramaConsultaOperation0.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return _enviarTramaConsultaOperation0;

    }

    private int _enviarTramaConsultaIndex0 = 0;
    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _getenviarTramaConsultaInvoke0(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault  {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_enviarTramaConsultaIndex0];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(InterfazServiciosSOAPStub._enviarTramaConsultaOperation0);
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("http://bn.ibm.com/InterfazServicios/NewOperation");
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

    public void enviarTramaConsulta(java.lang.String codTrans, java.lang.String tramaConsulta, javax.xml.rpc.holders.StringHolder tramaRespuesta, javax.xml.rpc.holders.IntHolder codRes) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        java.util.Vector _resp = null;
        try {
            _resp = _getenviarTramaConsultaInvoke0(new java.lang.Object[] {codTrans, tramaConsulta}).invoke();

        } catch (com.ibm.ws.webservices.engine.WebServicesFault wsf) {
            Exception e = wsf.getUserException();
            throw wsf;
        } 
        for (int _i = 0; _i < _resp.size(); ++_i) {
            com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue _param = (com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(_i);
            if (com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "tramaRespuesta").equals(_param.getQName())) {
                try {
                    tramaRespuesta.value = (java.lang.String) _param.getValue();
                } catch (java.lang.Exception _exception) {
                    tramaRespuesta.value = (java.lang.String) super.convert(_param.getValue(), java.lang.String.class);
                }
            }
            else if (com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "codRes").equals(_param.getQName())) {
                try {
                    codRes.value = ((java.lang.Integer) _param.getValue()).intValue();
                } catch (java.lang.Exception _exception) {
                    codRes.value = ((java.lang.Integer) super.convert(_param.getValue(), int.class)).intValue();
                }
            }
        }
    }

    private static void _staticInit() {
        _enviarTramaConsultaOperation0 = _getenviarTramaConsultaOperation0();
    }

    static {
       _staticInit();
    }
}
