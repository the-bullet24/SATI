/**
 * CustomerAuthenticationServiceInformation.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf021411.02 v32414172304
 */

package com.app.sarawebmanager.ws.bean;

public class CustomerAuthenticationServiceInformation implements com.ibm.ws.webservices.multiprotocol.ServiceInformation {

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
        inner0.put("autenticarUsuarios", list0);

        com.ibm.ws.webservices.engine.description.OperationDesc autenticarUsuarios0Op = _autenticarUsuarios0Op();
        list0.add(autenticarUsuarios0Op);

        java.util.List list1 = new java.util.ArrayList();
        inner0.put("retornaCajeroPorUsuario", list1);

        com.ibm.ws.webservices.engine.description.OperationDesc retornaCajeroPorUsuario1Op = _retornaCajeroPorUsuario1Op();
        list1.add(retornaCajeroPorUsuario1Op);

        operationDescriptions.put("CustomerAuthentication",inner0);
        operationDescriptions = java.util.Collections.unmodifiableMap(operationDescriptions);
    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _autenticarUsuarios0Op() {
        com.ibm.ws.webservices.engine.description.OperationDesc autenticarUsuarios0Op = null;
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
        autenticarUsuarios0Op = new com.ibm.ws.webservices.engine.description.OperationDesc("autenticarUsuarios", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.ws.sarawebmanager.app.com", "autenticarUsuarios"), _params0, _returnDesc0, _faults0, null);
        autenticarUsuarios0Op.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.ws.sarawebmanager.app.com", "CustomerAuthentication"));
        autenticarUsuarios0Op.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.ws.sarawebmanager.app.com", "autenticarUsuariosResponse"));
        autenticarUsuarios0Op.setOption("ResponseLocalPart","autenticarUsuariosResponse");
        autenticarUsuarios0Op.setOption("targetNamespace","http://bean.ws.sarawebmanager.app.com");
        autenticarUsuarios0Op.setOption("outputName","autenticarUsuariosResponse");
        autenticarUsuarios0Op.setOption("ResponseNamespace","http://bean.ws.sarawebmanager.app.com");
        autenticarUsuarios0Op.setOption("buildNum","cf021411.02");
        autenticarUsuarios0Op.setOption("inputName","autenticarUsuariosRequest");
        autenticarUsuarios0Op.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.ws.sarawebmanager.app.com", "CustomerAuthenticationService"));
        autenticarUsuarios0Op.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.ws.sarawebmanager.app.com", "autenticarUsuariosRequest"));
        autenticarUsuarios0Op.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return autenticarUsuarios0Op;

    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _retornaCajeroPorUsuario1Op() {
        com.ibm.ws.webservices.engine.description.OperationDesc retornaCajeroPorUsuario1Op = null;
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "codUsuario"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "codModulo"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false), 
          };
        _params0[0].setOption("partName","string");
        _params0[0].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params0[0].setOption("inputPosition","0");
        _params0[1].setOption("partName","string");
        _params0[1].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params0[1].setOption("inputPosition","1");
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "retornaCajeroPorUsuarioReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, true, false, false, false, true, false); 
        _returnDesc0.setOption("partName","string");
        _returnDesc0.setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _returnDesc0.setOption("outputPosition","0");
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        retornaCajeroPorUsuario1Op = new com.ibm.ws.webservices.engine.description.OperationDesc("retornaCajeroPorUsuario", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.ws.sarawebmanager.app.com", "retornaCajeroPorUsuario"), _params0, _returnDesc0, _faults0, null);
        retornaCajeroPorUsuario1Op.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.ws.sarawebmanager.app.com", "CustomerAuthentication"));
        retornaCajeroPorUsuario1Op.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.ws.sarawebmanager.app.com", "retornaCajeroPorUsuarioResponse"));
        retornaCajeroPorUsuario1Op.setOption("ResponseLocalPart","retornaCajeroPorUsuarioResponse");
        retornaCajeroPorUsuario1Op.setOption("targetNamespace","http://bean.ws.sarawebmanager.app.com");
        retornaCajeroPorUsuario1Op.setOption("outputName","retornaCajeroPorUsuarioResponse");
        retornaCajeroPorUsuario1Op.setOption("ResponseNamespace","http://bean.ws.sarawebmanager.app.com");
        retornaCajeroPorUsuario1Op.setOption("buildNum","cf021411.02");
        retornaCajeroPorUsuario1Op.setOption("inputName","retornaCajeroPorUsuarioRequest");
        retornaCajeroPorUsuario1Op.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.ws.sarawebmanager.app.com", "CustomerAuthenticationService"));
        retornaCajeroPorUsuario1Op.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://bean.ws.sarawebmanager.app.com", "retornaCajeroPorUsuarioRequest"));
        retornaCajeroPorUsuario1Op.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return retornaCajeroPorUsuario1Op;

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
