/**
 * CustomerAuthenticationSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf021411.02 v32414172304
 */

package com.app.sarawebmanager.ws.bean;

public class CustomerAuthenticationSoapBindingStub extends com.ibm.ws.webservices.engine.client.Stub implements com.app.sarawebmanager.ws.bean.CustomerAuthentication {
    public CustomerAuthenticationSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws com.ibm.ws.webservices.engine.WebServicesFault {
        if (service == null) {
            super.service = new com.ibm.ws.webservices.engine.client.Service();
        }
        else {
            super.service = service;
        }
        super.engine = ((com.ibm.ws.webservices.engine.client.Service) super.service).getEngine();
        super.messageContexts = new com.ibm.ws.webservices.engine.MessageContext[2];
        java.lang.String theOption = (java.lang.String)super._getProperty("lastStubMapping");
        if (theOption == null || !theOption.equals("com.app.sarawebmanager.ws.bean.CustomerAuthenticationSoapBinding")) {
                initTypeMapping();
                super._setProperty("lastStubMapping","com.app.sarawebmanager.ws.bean.CustomerAuthenticationSoapBinding");
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

    private static com.ibm.ws.webservices.engine.description.OperationDesc _autenticarUsuariosOperation0 = null;
    private static com.ibm.ws.webservices.engine.description.OperationDesc _getautenticarUsuariosOperation0() {
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "trama"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false), 
          };
        _params0[0].setOption("partName","string");
        _params0[0].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params0[0].setOption("inputPosition","0");
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "autenticarUsuariosReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, true, false, false, false, true, false); 
        _returnDesc0.setOption("partName","string");
        _returnDesc0.setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _returnDesc0.setOption("outputPosition","0");
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        _autenticarUsuariosOperation0 = new com.ibm.ws.webservices.engine.description.OperationDesc("autenticarUsuarios", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.ws.sarawebmanager.app.com", "autenticarUsuarios"), _params0, _returnDesc0, _faults0, "");
        _autenticarUsuariosOperation0.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.ws.sarawebmanager.app.com", "CustomerAuthentication"));
        _autenticarUsuariosOperation0.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.ws.sarawebmanager.app.com", "autenticarUsuariosResponse"));
        _autenticarUsuariosOperation0.setOption("ResponseLocalPart","autenticarUsuariosResponse");
        _autenticarUsuariosOperation0.setOption("targetNamespace","http://bean.ws.sarawebmanager.app.com");
        _autenticarUsuariosOperation0.setOption("outputName","autenticarUsuariosResponse");
        _autenticarUsuariosOperation0.setOption("ResponseNamespace","http://bean.ws.sarawebmanager.app.com");
        _autenticarUsuariosOperation0.setOption("buildNum","cf021411.02");
        _autenticarUsuariosOperation0.setOption("inputName","autenticarUsuariosRequest");
        _autenticarUsuariosOperation0.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.ws.sarawebmanager.app.com", "CustomerAuthenticationService"));
        _autenticarUsuariosOperation0.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.ws.sarawebmanager.app.com", "autenticarUsuariosRequest"));
        _autenticarUsuariosOperation0.setUse(com.ibm.ws.webservices.engine.enumtype.Use.LITERAL);
        _autenticarUsuariosOperation0.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return _autenticarUsuariosOperation0;

    }

    private int _autenticarUsuariosIndex0 = 0;
    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _getautenticarUsuariosInvoke0(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault  {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_autenticarUsuariosIndex0];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(CustomerAuthenticationSoapBindingStub._autenticarUsuariosOperation0);
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("");
            mc.setEncodingStyle(com.ibm.ws.webservices.engine.Constants.URI_LITERAL_ENC);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.SEND_TYPE_ATTR_PROPERTY, Boolean.FALSE);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.ENGINE_DO_MULTI_REFS_PROPERTY, Boolean.FALSE);
            super.primeMessageContext(mc);
            super.messageContexts[_autenticarUsuariosIndex0] = mc;
        }
        try {
            mc = (com.ibm.ws.webservices.engine.MessageContext) mc.clone();
        }
        catch (CloneNotSupportedException cnse) {
            throw com.ibm.ws.webservices.engine.WebServicesFault.makeFault(cnse);
        }
        return new com.ibm.ws.webservices.engine.client.Stub.Invoke(connection, mc, parameters);
    }

    public java.lang.String autenticarUsuarios(java.lang.String trama) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        java.util.Vector _resp = null;
        try {
            _resp = _getautenticarUsuariosInvoke0(new java.lang.Object[] {trama}).invoke();

        } catch (com.ibm.ws.webservices.engine.WebServicesFault wsf) {
            Exception e = wsf.getUserException();
            throw wsf;
        } 
        try {
            return (java.lang.String) ((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue();
        } catch (java.lang.Exception _exception) {
            return (java.lang.String) super.convert(((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue(), java.lang.String.class);
        }
    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _retornaCajeroPorUsuarioOperation1 = null;
    private static com.ibm.ws.webservices.engine.description.OperationDesc _getretornaCajeroPorUsuarioOperation1() {
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params1 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "codUsuario"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "codModulo"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false), 
          };
        _params1[0].setOption("partName","string");
        _params1[0].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params1[0].setOption("inputPosition","0");
        _params1[1].setOption("partName","string");
        _params1[1].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params1[1].setOption("inputPosition","1");
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc1 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "retornaCajeroPorUsuarioReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, true, false, false, false, true, false); 
        _returnDesc1.setOption("partName","string");
        _returnDesc1.setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _returnDesc1.setOption("outputPosition","0");
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults1 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        _retornaCajeroPorUsuarioOperation1 = new com.ibm.ws.webservices.engine.description.OperationDesc("retornaCajeroPorUsuario", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.ws.sarawebmanager.app.com", "retornaCajeroPorUsuario"), _params1, _returnDesc1, _faults1, "");
        _retornaCajeroPorUsuarioOperation1.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.ws.sarawebmanager.app.com", "CustomerAuthentication"));
        _retornaCajeroPorUsuarioOperation1.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.ws.sarawebmanager.app.com", "retornaCajeroPorUsuarioResponse"));
        _retornaCajeroPorUsuarioOperation1.setOption("ResponseLocalPart","retornaCajeroPorUsuarioResponse");
        _retornaCajeroPorUsuarioOperation1.setOption("targetNamespace","http://bean.ws.sarawebmanager.app.com");
        _retornaCajeroPorUsuarioOperation1.setOption("outputName","retornaCajeroPorUsuarioResponse");
        _retornaCajeroPorUsuarioOperation1.setOption("ResponseNamespace","http://bean.ws.sarawebmanager.app.com");
        _retornaCajeroPorUsuarioOperation1.setOption("buildNum","cf021411.02");
        _retornaCajeroPorUsuarioOperation1.setOption("inputName","retornaCajeroPorUsuarioRequest");
        _retornaCajeroPorUsuarioOperation1.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.ws.sarawebmanager.app.com", "CustomerAuthenticationService"));
        _retornaCajeroPorUsuarioOperation1.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.ws.sarawebmanager.app.com", "retornaCajeroPorUsuarioRequest"));
        _retornaCajeroPorUsuarioOperation1.setUse(com.ibm.ws.webservices.engine.enumtype.Use.LITERAL);
        _retornaCajeroPorUsuarioOperation1.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return _retornaCajeroPorUsuarioOperation1;

    }

    private int _retornaCajeroPorUsuarioIndex1 = 1;
    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _getretornaCajeroPorUsuarioInvoke1(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault  {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_retornaCajeroPorUsuarioIndex1];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(CustomerAuthenticationSoapBindingStub._retornaCajeroPorUsuarioOperation1);
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("");
            mc.setEncodingStyle(com.ibm.ws.webservices.engine.Constants.URI_LITERAL_ENC);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.SEND_TYPE_ATTR_PROPERTY, Boolean.FALSE);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.ENGINE_DO_MULTI_REFS_PROPERTY, Boolean.FALSE);
            super.primeMessageContext(mc);
            super.messageContexts[_retornaCajeroPorUsuarioIndex1] = mc;
        }
        try {
            mc = (com.ibm.ws.webservices.engine.MessageContext) mc.clone();
        }
        catch (CloneNotSupportedException cnse) {
            throw com.ibm.ws.webservices.engine.WebServicesFault.makeFault(cnse);
        }
        return new com.ibm.ws.webservices.engine.client.Stub.Invoke(connection, mc, parameters);
    }

    public java.lang.String retornaCajeroPorUsuario(java.lang.String codUsuario, java.lang.String codModulo) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        java.util.Vector _resp = null;
        try {
            _resp = _getretornaCajeroPorUsuarioInvoke1(new java.lang.Object[] {codUsuario, codModulo}).invoke();

        } catch (com.ibm.ws.webservices.engine.WebServicesFault wsf) {
            Exception e = wsf.getUserException();
            throw wsf;
        } 
        try {
            return (java.lang.String) ((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue();
        } catch (java.lang.Exception _exception) {
            return (java.lang.String) super.convert(((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue(), java.lang.String.class);
        }
    }

    private static void _staticInit() {
        _autenticarUsuariosOperation0 = _getautenticarUsuariosOperation0();
        _retornaCajeroPorUsuarioOperation1 = _getretornaCajeroPorUsuarioOperation1();
    }

    static {
       _staticInit();
    }
}
