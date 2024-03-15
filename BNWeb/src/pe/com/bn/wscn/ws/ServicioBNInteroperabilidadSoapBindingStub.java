/**
 * ServicioBNInteroperabilidadSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf222226.01 v62922235826
 */

package pe.com.bn.wscn.ws;

public class ServicioBNInteroperabilidadSoapBindingStub extends com.ibm.ws.webservices.engine.client.Stub implements pe.com.bn.wscn.ws.ServicioBNInteroperabilidad {
    public ServicioBNInteroperabilidadSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws com.ibm.ws.webservices.engine.WebServicesFault {
        if (service == null) {
            super.service = new com.ibm.ws.webservices.engine.client.Service();
        }
        else {
            super.service = service;
        }
        super.engine = ((com.ibm.ws.webservices.engine.client.Service) super.service).getEngine();
        super.messageContexts = new com.ibm.ws.webservices.engine.MessageContext[6];
        java.lang.String theOption = (java.lang.String)super._getProperty("lastStubMapping");
        if (theOption == null || !theOption.equals("pe.com.bn.wscn.ws.ServicioBNInteroperabilidadSoapBinding")) {
                initTypeMapping();
                super._setProperty("lastStubMapping","pe.com.bn.wscn.ws.ServicioBNInteroperabilidadSoapBinding");
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
        javaType = pe.com.bn.wscn.bean.interoperabilidad.DesafiliacionRequest.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "DesafiliacionRequest");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = pe.com.bn.wscn.bean.interoperabilidad.OperacionResponse.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "OperacionResponse");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = pe.com.bn.wscn.bean.interoperabilidad.OperacionData.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "OperacionData");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = pe.com.bn.wscn.bean.interoperabilidad.ActualizacionRequest.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "ActualizacionRequest");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = pe.com.bn.wscn.bean.interoperabilidad.AfilicionRequest.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "AfilicionRequest");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = pe.com.bn.wscn.bean.interoperabilidad.AfilicionResponse.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "AfilicionResponse");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = pe.com.bn.wscn.bean.interoperabilidad.Afiliacion.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "Afiliacion");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = pe.com.bn.wscn.bean.interoperabilidad.OperacionRequest.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "OperacionRequest");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = pe.com.bn.wscn.bean.interoperabilidad.BarridoRequest.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "BarridoRequest");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = pe.com.bn.wscn.bean.interoperabilidad.BarridoResponse.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "BarridoResponse");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = pe.com.bn.wscn.bean.interoperabilidad.BarridoData[].class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "ArrayOfBarridoData");
        compQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "BarridoData");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "BarridoData");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.ArraySerializerFactory.class, javaType, xmlType, compQName, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.ArrayDeserializerFactory.class, javaType, xmlType, compQName, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = pe.com.bn.wscn.bean.interoperabilidad.BarridoData.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "BarridoData");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = pe.com.bn.wscn.bean.interoperabilidad.MotivoDesafilaicionResponse.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "MotivoDesafilaicionResponse");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = pe.com.bn.wscn.bean.interoperabilidad.Motivo[].class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "ArrayOfMotivo");
        compQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "Motivo");
        compTypeQName = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "Motivo");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.ArraySerializerFactory.class, javaType, xmlType, compQName, compTypeQName);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.ArrayDeserializerFactory.class, javaType, xmlType, compQName, compTypeQName);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

        javaType = pe.com.bn.wscn.bean.interoperabilidad.Motivo.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "Motivo");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _opEliminarClienteOperation0 = null;
    private static com.ibm.ws.webservices.engine.description.OperationDesc _getopEliminarClienteOperation0() {
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "request"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "DesafiliacionRequest"), pe.com.bn.wscn.bean.interoperabilidad.DesafiliacionRequest.class, false, false, false, false, true, false), 
          };
        _params0[0].setOption("partName","DesafiliacionRequest");
        _params0[0].setOption("partQNameString","{http://interoperabilidad.bean.wscn.bn.com.pe}DesafiliacionRequest");
        _params0[0].setOption("inputPosition","0");
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "opEliminarClienteReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "OperacionResponse"), pe.com.bn.wscn.bean.interoperabilidad.OperacionResponse.class, true, false, false, false, true, false); 
        _returnDesc0.setOption("partName","OperacionResponse");
        _returnDesc0.setOption("partQNameString","{http://interoperabilidad.bean.wscn.bn.com.pe}OperacionResponse");
        _returnDesc0.setOption("outputPosition","0");
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        _opEliminarClienteOperation0 = new com.ibm.ws.webservices.engine.description.OperationDesc("opEliminarCliente", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "opEliminarCliente"), _params0, _returnDesc0, _faults0, "opEliminarCliente");
        _opEliminarClienteOperation0.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "ServicioBNInteroperabilidad"));
        _opEliminarClienteOperation0.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "opEliminarClienteResponse"));
        _opEliminarClienteOperation0.setOption("ResponseLocalPart","opEliminarClienteResponse");
        _opEliminarClienteOperation0.setOption("targetNamespace","http://ws.wscn.bn.com.pe");
        _opEliminarClienteOperation0.setOption("outputName","opEliminarClienteResponse");
        _opEliminarClienteOperation0.setOption("ResponseNamespace","http://ws.wscn.bn.com.pe");
        _opEliminarClienteOperation0.setOption("buildNum","cf222226.01");
        _opEliminarClienteOperation0.setOption("usingAddressing","false");
        _opEliminarClienteOperation0.setOption("inoutOrderingReq","false");
        _opEliminarClienteOperation0.setOption("inputName","opEliminarClienteRequest");
        _opEliminarClienteOperation0.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "ServicioBNInteroperabilidadService"));
        _opEliminarClienteOperation0.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "opEliminarClienteRequest"));
        _opEliminarClienteOperation0.setUse(com.ibm.ws.webservices.engine.enumtype.Use.LITERAL);
        _opEliminarClienteOperation0.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return _opEliminarClienteOperation0;

    }

    private int _opEliminarClienteIndex0 = 0;
    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _getopEliminarClienteInvoke0(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault  {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_opEliminarClienteIndex0];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(ServicioBNInteroperabilidadSoapBindingStub._opEliminarClienteOperation0);
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("opEliminarCliente");
            mc.setEncodingStyle(com.ibm.ws.webservices.engine.Constants.URI_LITERAL_ENC);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.SEND_TYPE_ATTR_PROPERTY, Boolean.FALSE);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.ENGINE_DO_MULTI_REFS_PROPERTY, Boolean.FALSE);
            super.primeMessageContext(mc);
            super.messageContexts[_opEliminarClienteIndex0] = mc;
        }
        try {
            mc = (com.ibm.ws.webservices.engine.MessageContext) mc.clone();
        }
        catch (CloneNotSupportedException cnse) {
            throw com.ibm.ws.webservices.engine.WebServicesFault.makeFault(cnse);
        }
        return new com.ibm.ws.webservices.engine.client.Stub.Invoke(connection, mc, parameters);
    }

    public pe.com.bn.wscn.bean.interoperabilidad.OperacionResponse opEliminarCliente(pe.com.bn.wscn.bean.interoperabilidad.DesafiliacionRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        java.util.Vector _resp = null;
        try {
            _resp = _getopEliminarClienteInvoke0(new java.lang.Object[] {request}).invoke();

        } catch (com.ibm.ws.webservices.engine.WebServicesFault wsf) {
            Exception e = wsf.getUserException();
            throw wsf;
        } 
        try {
            return (pe.com.bn.wscn.bean.interoperabilidad.OperacionResponse) ((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue();
        } catch (java.lang.Exception _exception) {
            return (pe.com.bn.wscn.bean.interoperabilidad.OperacionResponse) super.convert(((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue(), pe.com.bn.wscn.bean.interoperabilidad.OperacionResponse.class);
        }
    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _opActualizarClienteOperation1 = null;
    private static com.ibm.ws.webservices.engine.description.OperationDesc _getopActualizarClienteOperation1() {
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params1 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "request"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "ActualizacionRequest"), pe.com.bn.wscn.bean.interoperabilidad.ActualizacionRequest.class, false, false, false, false, true, false), 
          };
        _params1[0].setOption("partName","ActualizacionRequest");
        _params1[0].setOption("partQNameString","{http://interoperabilidad.bean.wscn.bn.com.pe}ActualizacionRequest");
        _params1[0].setOption("inputPosition","0");
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc1 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "opActualizarClienteReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "OperacionResponse"), pe.com.bn.wscn.bean.interoperabilidad.OperacionResponse.class, true, false, false, false, true, false); 
        _returnDesc1.setOption("partName","OperacionResponse");
        _returnDesc1.setOption("partQNameString","{http://interoperabilidad.bean.wscn.bn.com.pe}OperacionResponse");
        _returnDesc1.setOption("outputPosition","0");
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults1 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        _opActualizarClienteOperation1 = new com.ibm.ws.webservices.engine.description.OperationDesc("opActualizarCliente", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "opActualizarCliente"), _params1, _returnDesc1, _faults1, "opActualizarCliente");
        _opActualizarClienteOperation1.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "ServicioBNInteroperabilidad"));
        _opActualizarClienteOperation1.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "opActualizarClienteResponse"));
        _opActualizarClienteOperation1.setOption("ResponseLocalPart","opActualizarClienteResponse");
        _opActualizarClienteOperation1.setOption("targetNamespace","http://ws.wscn.bn.com.pe");
        _opActualizarClienteOperation1.setOption("outputName","opActualizarClienteResponse");
        _opActualizarClienteOperation1.setOption("ResponseNamespace","http://ws.wscn.bn.com.pe");
        _opActualizarClienteOperation1.setOption("buildNum","cf222226.01");
        _opActualizarClienteOperation1.setOption("usingAddressing","false");
        _opActualizarClienteOperation1.setOption("inoutOrderingReq","false");
        _opActualizarClienteOperation1.setOption("inputName","opActualizarClienteRequest");
        _opActualizarClienteOperation1.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "ServicioBNInteroperabilidadService"));
        _opActualizarClienteOperation1.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "opActualizarClienteRequest"));
        _opActualizarClienteOperation1.setUse(com.ibm.ws.webservices.engine.enumtype.Use.LITERAL);
        _opActualizarClienteOperation1.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return _opActualizarClienteOperation1;

    }

    private int _opActualizarClienteIndex1 = 1;
    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _getopActualizarClienteInvoke1(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault  {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_opActualizarClienteIndex1];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(ServicioBNInteroperabilidadSoapBindingStub._opActualizarClienteOperation1);
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("opActualizarCliente");
            mc.setEncodingStyle(com.ibm.ws.webservices.engine.Constants.URI_LITERAL_ENC);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.SEND_TYPE_ATTR_PROPERTY, Boolean.FALSE);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.ENGINE_DO_MULTI_REFS_PROPERTY, Boolean.FALSE);
            super.primeMessageContext(mc);
            super.messageContexts[_opActualizarClienteIndex1] = mc;
        }
        try {
            mc = (com.ibm.ws.webservices.engine.MessageContext) mc.clone();
        }
        catch (CloneNotSupportedException cnse) {
            throw com.ibm.ws.webservices.engine.WebServicesFault.makeFault(cnse);
        }
        return new com.ibm.ws.webservices.engine.client.Stub.Invoke(connection, mc, parameters);
    }

    public pe.com.bn.wscn.bean.interoperabilidad.OperacionResponse opActualizarCliente(pe.com.bn.wscn.bean.interoperabilidad.ActualizacionRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        java.util.Vector _resp = null;
        try {
            _resp = _getopActualizarClienteInvoke1(new java.lang.Object[] {request}).invoke();

        } catch (com.ibm.ws.webservices.engine.WebServicesFault wsf) {
            Exception e = wsf.getUserException();
            throw wsf;
        } 
        try {
            return (pe.com.bn.wscn.bean.interoperabilidad.OperacionResponse) ((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue();
        } catch (java.lang.Exception _exception) {
            return (pe.com.bn.wscn.bean.interoperabilidad.OperacionResponse) super.convert(((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue(), pe.com.bn.wscn.bean.interoperabilidad.OperacionResponse.class);
        }
    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _opConsultaAfilicionOperation2 = null;
    private static com.ibm.ws.webservices.engine.description.OperationDesc _getopConsultaAfilicionOperation2() {
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params2 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "request"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "AfilicionRequest"), pe.com.bn.wscn.bean.interoperabilidad.AfilicionRequest.class, false, false, false, false, true, false), 
          };
        _params2[0].setOption("partName","AfilicionRequest");
        _params2[0].setOption("partQNameString","{http://interoperabilidad.bean.wscn.bn.com.pe}AfilicionRequest");
        _params2[0].setOption("inputPosition","0");
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc2 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "opConsultaAfilicionReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "AfilicionResponse"), pe.com.bn.wscn.bean.interoperabilidad.AfilicionResponse.class, true, false, false, false, true, false); 
        _returnDesc2.setOption("partName","AfilicionResponse");
        _returnDesc2.setOption("partQNameString","{http://interoperabilidad.bean.wscn.bn.com.pe}AfilicionResponse");
        _returnDesc2.setOption("outputPosition","0");
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults2 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        _opConsultaAfilicionOperation2 = new com.ibm.ws.webservices.engine.description.OperationDesc("opConsultaAfilicion", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "opConsultaAfilicion"), _params2, _returnDesc2, _faults2, "opConsultaAfilicion");
        _opConsultaAfilicionOperation2.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "ServicioBNInteroperabilidad"));
        _opConsultaAfilicionOperation2.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "opConsultaAfilicionResponse"));
        _opConsultaAfilicionOperation2.setOption("ResponseLocalPart","opConsultaAfilicionResponse");
        _opConsultaAfilicionOperation2.setOption("targetNamespace","http://ws.wscn.bn.com.pe");
        _opConsultaAfilicionOperation2.setOption("outputName","opConsultaAfilicionResponse");
        _opConsultaAfilicionOperation2.setOption("ResponseNamespace","http://ws.wscn.bn.com.pe");
        _opConsultaAfilicionOperation2.setOption("buildNum","cf222226.01");
        _opConsultaAfilicionOperation2.setOption("usingAddressing","false");
        _opConsultaAfilicionOperation2.setOption("inoutOrderingReq","false");
        _opConsultaAfilicionOperation2.setOption("inputName","opConsultaAfilicionRequest");
        _opConsultaAfilicionOperation2.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "ServicioBNInteroperabilidadService"));
        _opConsultaAfilicionOperation2.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "opConsultaAfilicionRequest"));
        _opConsultaAfilicionOperation2.setUse(com.ibm.ws.webservices.engine.enumtype.Use.LITERAL);
        _opConsultaAfilicionOperation2.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return _opConsultaAfilicionOperation2;

    }

    private int _opConsultaAfilicionIndex2 = 2;
    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _getopConsultaAfilicionInvoke2(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault  {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_opConsultaAfilicionIndex2];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(ServicioBNInteroperabilidadSoapBindingStub._opConsultaAfilicionOperation2);
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("opConsultaAfilicion");
            mc.setEncodingStyle(com.ibm.ws.webservices.engine.Constants.URI_LITERAL_ENC);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.SEND_TYPE_ATTR_PROPERTY, Boolean.FALSE);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.ENGINE_DO_MULTI_REFS_PROPERTY, Boolean.FALSE);
            super.primeMessageContext(mc);
            super.messageContexts[_opConsultaAfilicionIndex2] = mc;
        }
        try {
            mc = (com.ibm.ws.webservices.engine.MessageContext) mc.clone();
        }
        catch (CloneNotSupportedException cnse) {
            throw com.ibm.ws.webservices.engine.WebServicesFault.makeFault(cnse);
        }
        return new com.ibm.ws.webservices.engine.client.Stub.Invoke(connection, mc, parameters);
    }

    public pe.com.bn.wscn.bean.interoperabilidad.AfilicionResponse opConsultaAfilicion(pe.com.bn.wscn.bean.interoperabilidad.AfilicionRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        java.util.Vector _resp = null;
        try {
            _resp = _getopConsultaAfilicionInvoke2(new java.lang.Object[] {request}).invoke();

        } catch (com.ibm.ws.webservices.engine.WebServicesFault wsf) {
            Exception e = wsf.getUserException();
            throw wsf;
        } 
        try {
            return (pe.com.bn.wscn.bean.interoperabilidad.AfilicionResponse) ((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue();
        } catch (java.lang.Exception _exception) {
            return (pe.com.bn.wscn.bean.interoperabilidad.AfilicionResponse) super.convert(((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue(), pe.com.bn.wscn.bean.interoperabilidad.AfilicionResponse.class);
        }
    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _opRegistroContactoOperation3 = null;
    private static com.ibm.ws.webservices.engine.description.OperationDesc _getopRegistroContactoOperation3() {
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params3 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "request"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "OperacionRequest"), pe.com.bn.wscn.bean.interoperabilidad.OperacionRequest.class, false, false, false, false, true, false), 
          };
        _params3[0].setOption("partName","OperacionRequest");
        _params3[0].setOption("partQNameString","{http://interoperabilidad.bean.wscn.bn.com.pe}OperacionRequest");
        _params3[0].setOption("inputPosition","0");
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc3 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "opRegistroContactoReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "OperacionResponse"), pe.com.bn.wscn.bean.interoperabilidad.OperacionResponse.class, true, false, false, false, true, false); 
        _returnDesc3.setOption("partName","OperacionResponse");
        _returnDesc3.setOption("partQNameString","{http://interoperabilidad.bean.wscn.bn.com.pe}OperacionResponse");
        _returnDesc3.setOption("outputPosition","0");
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults3 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        _opRegistroContactoOperation3 = new com.ibm.ws.webservices.engine.description.OperationDesc("opRegistroContacto", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "opRegistroContacto"), _params3, _returnDesc3, _faults3, "opRegistroContacto");
        _opRegistroContactoOperation3.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "ServicioBNInteroperabilidad"));
        _opRegistroContactoOperation3.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "opRegistroContactoResponse"));
        _opRegistroContactoOperation3.setOption("ResponseLocalPart","opRegistroContactoResponse");
        _opRegistroContactoOperation3.setOption("targetNamespace","http://ws.wscn.bn.com.pe");
        _opRegistroContactoOperation3.setOption("outputName","opRegistroContactoResponse");
        _opRegistroContactoOperation3.setOption("ResponseNamespace","http://ws.wscn.bn.com.pe");
        _opRegistroContactoOperation3.setOption("buildNum","cf222226.01");
        _opRegistroContactoOperation3.setOption("usingAddressing","false");
        _opRegistroContactoOperation3.setOption("inoutOrderingReq","false");
        _opRegistroContactoOperation3.setOption("inputName","opRegistroContactoRequest");
        _opRegistroContactoOperation3.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "ServicioBNInteroperabilidadService"));
        _opRegistroContactoOperation3.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "opRegistroContactoRequest"));
        _opRegistroContactoOperation3.setUse(com.ibm.ws.webservices.engine.enumtype.Use.LITERAL);
        _opRegistroContactoOperation3.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return _opRegistroContactoOperation3;

    }

    private int _opRegistroContactoIndex3 = 3;
    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _getopRegistroContactoInvoke3(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault  {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_opRegistroContactoIndex3];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(ServicioBNInteroperabilidadSoapBindingStub._opRegistroContactoOperation3);
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("opRegistroContacto");
            mc.setEncodingStyle(com.ibm.ws.webservices.engine.Constants.URI_LITERAL_ENC);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.SEND_TYPE_ATTR_PROPERTY, Boolean.FALSE);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.ENGINE_DO_MULTI_REFS_PROPERTY, Boolean.FALSE);
            super.primeMessageContext(mc);
            super.messageContexts[_opRegistroContactoIndex3] = mc;
        }
        try {
            mc = (com.ibm.ws.webservices.engine.MessageContext) mc.clone();
        }
        catch (CloneNotSupportedException cnse) {
            throw com.ibm.ws.webservices.engine.WebServicesFault.makeFault(cnse);
        }
        return new com.ibm.ws.webservices.engine.client.Stub.Invoke(connection, mc, parameters);
    }

    public pe.com.bn.wscn.bean.interoperabilidad.OperacionResponse opRegistroContacto(pe.com.bn.wscn.bean.interoperabilidad.OperacionRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        java.util.Vector _resp = null;
        try {
            _resp = _getopRegistroContactoInvoke3(new java.lang.Object[] {request}).invoke();

        } catch (com.ibm.ws.webservices.engine.WebServicesFault wsf) {
            Exception e = wsf.getUserException();
            throw wsf;
        } 
        try {
            return (pe.com.bn.wscn.bean.interoperabilidad.OperacionResponse) ((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue();
        } catch (java.lang.Exception _exception) {
            return (pe.com.bn.wscn.bean.interoperabilidad.OperacionResponse) super.convert(((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue(), pe.com.bn.wscn.bean.interoperabilidad.OperacionResponse.class);
        }
    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _opBarridoClientesOperation4 = null;
    private static com.ibm.ws.webservices.engine.description.OperationDesc _getopBarridoClientesOperation4() {
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params4 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "request"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "BarridoRequest"), pe.com.bn.wscn.bean.interoperabilidad.BarridoRequest.class, false, false, false, false, true, false), 
          };
        _params4[0].setOption("partName","BarridoRequest");
        _params4[0].setOption("partQNameString","{http://interoperabilidad.bean.wscn.bn.com.pe}BarridoRequest");
        _params4[0].setOption("inputPosition","0");
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc4 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "opBarridoClientesReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "BarridoResponse"), pe.com.bn.wscn.bean.interoperabilidad.BarridoResponse.class, true, false, false, false, true, false); 
        _returnDesc4.setOption("partName","BarridoResponse");
        _returnDesc4.setOption("partQNameString","{http://interoperabilidad.bean.wscn.bn.com.pe}BarridoResponse");
        _returnDesc4.setOption("outputPosition","0");
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults4 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        _opBarridoClientesOperation4 = new com.ibm.ws.webservices.engine.description.OperationDesc("opBarridoClientes", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "opBarridoClientes"), _params4, _returnDesc4, _faults4, "opBarridoClientes");
        _opBarridoClientesOperation4.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "ServicioBNInteroperabilidad"));
        _opBarridoClientesOperation4.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "opBarridoClientesResponse"));
        _opBarridoClientesOperation4.setOption("ResponseLocalPart","opBarridoClientesResponse");
        _opBarridoClientesOperation4.setOption("targetNamespace","http://ws.wscn.bn.com.pe");
        _opBarridoClientesOperation4.setOption("outputName","opBarridoClientesResponse");
        _opBarridoClientesOperation4.setOption("ResponseNamespace","http://ws.wscn.bn.com.pe");
        _opBarridoClientesOperation4.setOption("buildNum","cf222226.01");
        _opBarridoClientesOperation4.setOption("usingAddressing","false");
        _opBarridoClientesOperation4.setOption("inoutOrderingReq","false");
        _opBarridoClientesOperation4.setOption("inputName","opBarridoClientesRequest");
        _opBarridoClientesOperation4.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "ServicioBNInteroperabilidadService"));
        _opBarridoClientesOperation4.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "opBarridoClientesRequest"));
        _opBarridoClientesOperation4.setUse(com.ibm.ws.webservices.engine.enumtype.Use.LITERAL);
        _opBarridoClientesOperation4.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return _opBarridoClientesOperation4;

    }

    private int _opBarridoClientesIndex4 = 4;
    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _getopBarridoClientesInvoke4(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault  {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_opBarridoClientesIndex4];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(ServicioBNInteroperabilidadSoapBindingStub._opBarridoClientesOperation4);
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("opBarridoClientes");
            mc.setEncodingStyle(com.ibm.ws.webservices.engine.Constants.URI_LITERAL_ENC);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.SEND_TYPE_ATTR_PROPERTY, Boolean.FALSE);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.ENGINE_DO_MULTI_REFS_PROPERTY, Boolean.FALSE);
            super.primeMessageContext(mc);
            super.messageContexts[_opBarridoClientesIndex4] = mc;
        }
        try {
            mc = (com.ibm.ws.webservices.engine.MessageContext) mc.clone();
        }
        catch (CloneNotSupportedException cnse) {
            throw com.ibm.ws.webservices.engine.WebServicesFault.makeFault(cnse);
        }
        return new com.ibm.ws.webservices.engine.client.Stub.Invoke(connection, mc, parameters);
    }

    public pe.com.bn.wscn.bean.interoperabilidad.BarridoResponse opBarridoClientes(pe.com.bn.wscn.bean.interoperabilidad.BarridoRequest request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        java.util.Vector _resp = null;
        try {
            _resp = _getopBarridoClientesInvoke4(new java.lang.Object[] {request}).invoke();

        } catch (com.ibm.ws.webservices.engine.WebServicesFault wsf) {
            Exception e = wsf.getUserException();
            throw wsf;
        } 
        try {
            return (pe.com.bn.wscn.bean.interoperabilidad.BarridoResponse) ((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue();
        } catch (java.lang.Exception _exception) {
            return (pe.com.bn.wscn.bean.interoperabilidad.BarridoResponse) super.convert(((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue(), pe.com.bn.wscn.bean.interoperabilidad.BarridoResponse.class);
        }
    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _opObtenerMotivDesaInteroperabilidadOperation5 = null;
    private static com.ibm.ws.webservices.engine.description.OperationDesc _getopObtenerMotivDesaInteroperabilidadOperation5() {
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params5 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
          };
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc5 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "opObtenerMotivDesaInteroperabilidadReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "MotivoDesafilaicionResponse"), pe.com.bn.wscn.bean.interoperabilidad.MotivoDesafilaicionResponse.class, true, false, false, false, true, false); 
        _returnDesc5.setOption("partName","MotivoDesafilaicionResponse");
        _returnDesc5.setOption("partQNameString","{http://interoperabilidad.bean.wscn.bn.com.pe}MotivoDesafilaicionResponse");
        _returnDesc5.setOption("outputPosition","0");
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults5 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        _opObtenerMotivDesaInteroperabilidadOperation5 = new com.ibm.ws.webservices.engine.description.OperationDesc("opObtenerMotivDesaInteroperabilidad", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "opObtenerMotivDesaInteroperabilidad"), _params5, _returnDesc5, _faults5, "opObtenerMotivDesaInteroperabilidad");
        _opObtenerMotivDesaInteroperabilidadOperation5.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "ServicioBNInteroperabilidad"));
        _opObtenerMotivDesaInteroperabilidadOperation5.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "opObtenerMotivDesaInteroperabilidadResponse"));
        _opObtenerMotivDesaInteroperabilidadOperation5.setOption("ResponseLocalPart","opObtenerMotivDesaInteroperabilidadResponse");
        _opObtenerMotivDesaInteroperabilidadOperation5.setOption("targetNamespace","http://ws.wscn.bn.com.pe");
        _opObtenerMotivDesaInteroperabilidadOperation5.setOption("outputName","opObtenerMotivDesaInteroperabilidadResponse");
        _opObtenerMotivDesaInteroperabilidadOperation5.setOption("ResponseNamespace","http://ws.wscn.bn.com.pe");
        _opObtenerMotivDesaInteroperabilidadOperation5.setOption("buildNum","cf222226.01");
        _opObtenerMotivDesaInteroperabilidadOperation5.setOption("usingAddressing","false");
        _opObtenerMotivDesaInteroperabilidadOperation5.setOption("inputName","opObtenerMotivDesaInteroperabilidadRequest");
        _opObtenerMotivDesaInteroperabilidadOperation5.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "ServicioBNInteroperabilidadService"));
        _opObtenerMotivDesaInteroperabilidadOperation5.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "opObtenerMotivDesaInteroperabilidadRequest"));
        _opObtenerMotivDesaInteroperabilidadOperation5.setUse(com.ibm.ws.webservices.engine.enumtype.Use.LITERAL);
        _opObtenerMotivDesaInteroperabilidadOperation5.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return _opObtenerMotivDesaInteroperabilidadOperation5;

    }

    private int _opObtenerMotivDesaInteroperabilidadIndex5 = 5;
    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _getopObtenerMotivDesaInteroperabilidadInvoke5(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault  {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_opObtenerMotivDesaInteroperabilidadIndex5];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(ServicioBNInteroperabilidadSoapBindingStub._opObtenerMotivDesaInteroperabilidadOperation5);
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("opObtenerMotivDesaInteroperabilidad");
            mc.setEncodingStyle(com.ibm.ws.webservices.engine.Constants.URI_LITERAL_ENC);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.SEND_TYPE_ATTR_PROPERTY, Boolean.FALSE);
            mc.setProperty(com.ibm.wsspi.webservices.Constants.ENGINE_DO_MULTI_REFS_PROPERTY, Boolean.FALSE);
            super.primeMessageContext(mc);
            super.messageContexts[_opObtenerMotivDesaInteroperabilidadIndex5] = mc;
        }
        try {
            mc = (com.ibm.ws.webservices.engine.MessageContext) mc.clone();
        }
        catch (CloneNotSupportedException cnse) {
            throw com.ibm.ws.webservices.engine.WebServicesFault.makeFault(cnse);
        }
        return new com.ibm.ws.webservices.engine.client.Stub.Invoke(connection, mc, parameters);
    }

    public pe.com.bn.wscn.bean.interoperabilidad.MotivoDesafilaicionResponse opObtenerMotivDesaInteroperabilidad() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        java.util.Vector _resp = null;
        try {
            _resp = _getopObtenerMotivDesaInteroperabilidadInvoke5(new java.lang.Object[] {}).invoke();

        } catch (com.ibm.ws.webservices.engine.WebServicesFault wsf) {
            Exception e = wsf.getUserException();
            throw wsf;
        } 
        try {
            return (pe.com.bn.wscn.bean.interoperabilidad.MotivoDesafilaicionResponse) ((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue();
        } catch (java.lang.Exception _exception) {
            return (pe.com.bn.wscn.bean.interoperabilidad.MotivoDesafilaicionResponse) super.convert(((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue(), pe.com.bn.wscn.bean.interoperabilidad.MotivoDesafilaicionResponse.class);
        }
    }

    private static void _staticInit() {
        _opActualizarClienteOperation1 = _getopActualizarClienteOperation1();
        _opRegistroContactoOperation3 = _getopRegistroContactoOperation3();
        _opBarridoClientesOperation4 = _getopBarridoClientesOperation4();
        _opConsultaAfilicionOperation2 = _getopConsultaAfilicionOperation2();
        _opObtenerMotivDesaInteroperabilidadOperation5 = _getopObtenerMotivDesaInteroperabilidadOperation5();
        _opEliminarClienteOperation0 = _getopEliminarClienteOperation0();
    }

    static {
       _staticInit();
    }
}
