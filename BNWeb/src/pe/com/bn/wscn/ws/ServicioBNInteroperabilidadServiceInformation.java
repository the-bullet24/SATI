/**
 * ServicioBNInteroperabilidadServiceInformation.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf222226.01 v62922235826
 */

package pe.com.bn.wscn.ws;

public class ServicioBNInteroperabilidadServiceInformation implements com.ibm.ws.webservices.multiprotocol.ServiceInformation {

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
        inner0.put("opActualizarCliente", list0);

        com.ibm.ws.webservices.engine.description.OperationDesc opActualizarCliente0Op = _opActualizarCliente0Op();
        list0.add(opActualizarCliente0Op);

        java.util.List list1 = new java.util.ArrayList();
        inner0.put("opBarridoClientes", list1);

        com.ibm.ws.webservices.engine.description.OperationDesc opBarridoClientes1Op = _opBarridoClientes1Op();
        list1.add(opBarridoClientes1Op);

        java.util.List list2 = new java.util.ArrayList();
        inner0.put("opConsultaAfilicion", list2);

        com.ibm.ws.webservices.engine.description.OperationDesc opConsultaAfilicion2Op = _opConsultaAfilicion2Op();
        list2.add(opConsultaAfilicion2Op);

        java.util.List list3 = new java.util.ArrayList();
        inner0.put("opEliminarCliente", list3);

        com.ibm.ws.webservices.engine.description.OperationDesc opEliminarCliente3Op = _opEliminarCliente3Op();
        list3.add(opEliminarCliente3Op);

        java.util.List list4 = new java.util.ArrayList();
        inner0.put("opObtenerMotivDesaInteroperabilidad", list4);

        com.ibm.ws.webservices.engine.description.OperationDesc opObtenerMotivDesaInteroperabilidad4Op = _opObtenerMotivDesaInteroperabilidad4Op();
        list4.add(opObtenerMotivDesaInteroperabilidad4Op);

        java.util.List list5 = new java.util.ArrayList();
        inner0.put("opRegistroContacto", list5);

        com.ibm.ws.webservices.engine.description.OperationDesc opRegistroContacto5Op = _opRegistroContacto5Op();
        list5.add(opRegistroContacto5Op);

        operationDescriptions.put("ServicioBNInteroperabilidad",inner0);
        operationDescriptions = java.util.Collections.unmodifiableMap(operationDescriptions);
    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _opActualizarCliente0Op() {
        com.ibm.ws.webservices.engine.description.OperationDesc opActualizarCliente0Op = null;
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "request"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "ActualizacionRequest"), pe.com.bn.wscn.bean.interoperabilidad.ActualizacionRequest.class, false, false, false, false, true, false), 
          };
        _params0[0].setOption("partName","ActualizacionRequest");
        _params0[0].setOption("partQNameString","{http://interoperabilidad.bean.wscn.bn.com.pe}ActualizacionRequest");
        _params0[0].setOption("inputPosition","0");
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "opActualizarClienteReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "OperacionResponse"), pe.com.bn.wscn.bean.interoperabilidad.OperacionResponse.class, true, false, false, false, true, false); 
        _returnDesc0.setOption("partName","OperacionResponse");
        _returnDesc0.setOption("partQNameString","{http://interoperabilidad.bean.wscn.bn.com.pe}OperacionResponse");
        _returnDesc0.setOption("outputPosition","0");
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        opActualizarCliente0Op = new com.ibm.ws.webservices.engine.description.OperationDesc("opActualizarCliente", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "opActualizarCliente"), _params0, _returnDesc0, _faults0, null);
        opActualizarCliente0Op.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "ServicioBNInteroperabilidad"));
        opActualizarCliente0Op.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "opActualizarClienteResponse"));
        opActualizarCliente0Op.setOption("ResponseLocalPart","opActualizarClienteResponse");
        opActualizarCliente0Op.setOption("targetNamespace","http://ws.wscn.bn.com.pe");
        opActualizarCliente0Op.setOption("outputName","opActualizarClienteResponse");
        opActualizarCliente0Op.setOption("ResponseNamespace","http://ws.wscn.bn.com.pe");
        opActualizarCliente0Op.setOption("buildNum","cf222226.01");
        opActualizarCliente0Op.setOption("usingAddressing","false");
        opActualizarCliente0Op.setOption("inoutOrderingReq","false");
        opActualizarCliente0Op.setOption("inputName","opActualizarClienteRequest");
        opActualizarCliente0Op.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "ServicioBNInteroperabilidadService"));
        opActualizarCliente0Op.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "opActualizarClienteRequest"));
        opActualizarCliente0Op.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return opActualizarCliente0Op;

    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _opBarridoClientes1Op() {
        com.ibm.ws.webservices.engine.description.OperationDesc opBarridoClientes1Op = null;
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "request"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "BarridoRequest"), pe.com.bn.wscn.bean.interoperabilidad.BarridoRequest.class, false, false, false, false, true, false), 
          };
        _params0[0].setOption("partName","BarridoRequest");
        _params0[0].setOption("partQNameString","{http://interoperabilidad.bean.wscn.bn.com.pe}BarridoRequest");
        _params0[0].setOption("inputPosition","0");
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "opBarridoClientesReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "BarridoResponse"), pe.com.bn.wscn.bean.interoperabilidad.BarridoResponse.class, true, false, false, false, true, false); 
        _returnDesc0.setOption("partName","BarridoResponse");
        _returnDesc0.setOption("partQNameString","{http://interoperabilidad.bean.wscn.bn.com.pe}BarridoResponse");
        _returnDesc0.setOption("outputPosition","0");
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        opBarridoClientes1Op = new com.ibm.ws.webservices.engine.description.OperationDesc("opBarridoClientes", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "opBarridoClientes"), _params0, _returnDesc0, _faults0, null);
        opBarridoClientes1Op.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "ServicioBNInteroperabilidad"));
        opBarridoClientes1Op.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "opBarridoClientesResponse"));
        opBarridoClientes1Op.setOption("ResponseLocalPart","opBarridoClientesResponse");
        opBarridoClientes1Op.setOption("targetNamespace","http://ws.wscn.bn.com.pe");
        opBarridoClientes1Op.setOption("outputName","opBarridoClientesResponse");
        opBarridoClientes1Op.setOption("ResponseNamespace","http://ws.wscn.bn.com.pe");
        opBarridoClientes1Op.setOption("buildNum","cf222226.01");
        opBarridoClientes1Op.setOption("usingAddressing","false");
        opBarridoClientes1Op.setOption("inoutOrderingReq","false");
        opBarridoClientes1Op.setOption("inputName","opBarridoClientesRequest");
        opBarridoClientes1Op.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "ServicioBNInteroperabilidadService"));
        opBarridoClientes1Op.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "opBarridoClientesRequest"));
        opBarridoClientes1Op.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return opBarridoClientes1Op;

    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _opConsultaAfilicion2Op() {
        com.ibm.ws.webservices.engine.description.OperationDesc opConsultaAfilicion2Op = null;
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "request"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "AfilicionRequest"), pe.com.bn.wscn.bean.interoperabilidad.AfilicionRequest.class, false, false, false, false, true, false), 
          };
        _params0[0].setOption("partName","AfilicionRequest");
        _params0[0].setOption("partQNameString","{http://interoperabilidad.bean.wscn.bn.com.pe}AfilicionRequest");
        _params0[0].setOption("inputPosition","0");
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "opConsultaAfilicionReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "AfilicionResponse"), pe.com.bn.wscn.bean.interoperabilidad.AfilicionResponse.class, true, false, false, false, true, false); 
        _returnDesc0.setOption("partName","AfilicionResponse");
        _returnDesc0.setOption("partQNameString","{http://interoperabilidad.bean.wscn.bn.com.pe}AfilicionResponse");
        _returnDesc0.setOption("outputPosition","0");
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        opConsultaAfilicion2Op = new com.ibm.ws.webservices.engine.description.OperationDesc("opConsultaAfilicion", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "opConsultaAfilicion"), _params0, _returnDesc0, _faults0, null);
        opConsultaAfilicion2Op.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "ServicioBNInteroperabilidad"));
        opConsultaAfilicion2Op.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "opConsultaAfilicionResponse"));
        opConsultaAfilicion2Op.setOption("ResponseLocalPart","opConsultaAfilicionResponse");
        opConsultaAfilicion2Op.setOption("targetNamespace","http://ws.wscn.bn.com.pe");
        opConsultaAfilicion2Op.setOption("outputName","opConsultaAfilicionResponse");
        opConsultaAfilicion2Op.setOption("ResponseNamespace","http://ws.wscn.bn.com.pe");
        opConsultaAfilicion2Op.setOption("buildNum","cf222226.01");
        opConsultaAfilicion2Op.setOption("usingAddressing","false");
        opConsultaAfilicion2Op.setOption("inoutOrderingReq","false");
        opConsultaAfilicion2Op.setOption("inputName","opConsultaAfilicionRequest");
        opConsultaAfilicion2Op.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "ServicioBNInteroperabilidadService"));
        opConsultaAfilicion2Op.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "opConsultaAfilicionRequest"));
        opConsultaAfilicion2Op.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return opConsultaAfilicion2Op;

    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _opEliminarCliente3Op() {
        com.ibm.ws.webservices.engine.description.OperationDesc opEliminarCliente3Op = null;
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
        opEliminarCliente3Op = new com.ibm.ws.webservices.engine.description.OperationDesc("opEliminarCliente", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "opEliminarCliente"), _params0, _returnDesc0, _faults0, null);
        opEliminarCliente3Op.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "ServicioBNInteroperabilidad"));
        opEliminarCliente3Op.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "opEliminarClienteResponse"));
        opEliminarCliente3Op.setOption("ResponseLocalPart","opEliminarClienteResponse");
        opEliminarCliente3Op.setOption("targetNamespace","http://ws.wscn.bn.com.pe");
        opEliminarCliente3Op.setOption("outputName","opEliminarClienteResponse");
        opEliminarCliente3Op.setOption("ResponseNamespace","http://ws.wscn.bn.com.pe");
        opEliminarCliente3Op.setOption("buildNum","cf222226.01");
        opEliminarCliente3Op.setOption("usingAddressing","false");
        opEliminarCliente3Op.setOption("inoutOrderingReq","false");
        opEliminarCliente3Op.setOption("inputName","opEliminarClienteRequest");
        opEliminarCliente3Op.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "ServicioBNInteroperabilidadService"));
        opEliminarCliente3Op.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "opEliminarClienteRequest"));
        opEliminarCliente3Op.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return opEliminarCliente3Op;

    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _opObtenerMotivDesaInteroperabilidad4Op() {
        com.ibm.ws.webservices.engine.description.OperationDesc opObtenerMotivDesaInteroperabilidad4Op = null;
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
          };
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "opObtenerMotivDesaInteroperabilidadReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "MotivoDesafilaicionResponse"), pe.com.bn.wscn.bean.interoperabilidad.MotivoDesafilaicionResponse.class, true, false, false, false, true, false); 
        _returnDesc0.setOption("partName","MotivoDesafilaicionResponse");
        _returnDesc0.setOption("partQNameString","{http://interoperabilidad.bean.wscn.bn.com.pe}MotivoDesafilaicionResponse");
        _returnDesc0.setOption("outputPosition","0");
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        opObtenerMotivDesaInteroperabilidad4Op = new com.ibm.ws.webservices.engine.description.OperationDesc("opObtenerMotivDesaInteroperabilidad", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "opObtenerMotivDesaInteroperabilidad"), _params0, _returnDesc0, _faults0, null);
        opObtenerMotivDesaInteroperabilidad4Op.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "ServicioBNInteroperabilidad"));
        opObtenerMotivDesaInteroperabilidad4Op.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "opObtenerMotivDesaInteroperabilidadResponse"));
        opObtenerMotivDesaInteroperabilidad4Op.setOption("ResponseLocalPart","opObtenerMotivDesaInteroperabilidadResponse");
        opObtenerMotivDesaInteroperabilidad4Op.setOption("targetNamespace","http://ws.wscn.bn.com.pe");
        opObtenerMotivDesaInteroperabilidad4Op.setOption("outputName","opObtenerMotivDesaInteroperabilidadResponse");
        opObtenerMotivDesaInteroperabilidad4Op.setOption("ResponseNamespace","http://ws.wscn.bn.com.pe");
        opObtenerMotivDesaInteroperabilidad4Op.setOption("buildNum","cf222226.01");
        opObtenerMotivDesaInteroperabilidad4Op.setOption("usingAddressing","false");
        opObtenerMotivDesaInteroperabilidad4Op.setOption("inputName","opObtenerMotivDesaInteroperabilidadRequest");
        opObtenerMotivDesaInteroperabilidad4Op.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "ServicioBNInteroperabilidadService"));
        opObtenerMotivDesaInteroperabilidad4Op.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "opObtenerMotivDesaInteroperabilidadRequest"));
        opObtenerMotivDesaInteroperabilidad4Op.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return opObtenerMotivDesaInteroperabilidad4Op;

    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _opRegistroContacto5Op() {
        com.ibm.ws.webservices.engine.description.OperationDesc opRegistroContacto5Op = null;
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "request"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "OperacionRequest"), pe.com.bn.wscn.bean.interoperabilidad.OperacionRequest.class, false, false, false, false, true, false), 
          };
        _params0[0].setOption("partName","OperacionRequest");
        _params0[0].setOption("partQNameString","{http://interoperabilidad.bean.wscn.bn.com.pe}OperacionRequest");
        _params0[0].setOption("inputPosition","0");
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "opRegistroContactoReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "OperacionResponse"), pe.com.bn.wscn.bean.interoperabilidad.OperacionResponse.class, true, false, false, false, true, false); 
        _returnDesc0.setOption("partName","OperacionResponse");
        _returnDesc0.setOption("partQNameString","{http://interoperabilidad.bean.wscn.bn.com.pe}OperacionResponse");
        _returnDesc0.setOption("outputPosition","0");
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        opRegistroContacto5Op = new com.ibm.ws.webservices.engine.description.OperationDesc("opRegistroContacto", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "opRegistroContacto"), _params0, _returnDesc0, _faults0, null);
        opRegistroContacto5Op.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "ServicioBNInteroperabilidad"));
        opRegistroContacto5Op.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "opRegistroContactoResponse"));
        opRegistroContacto5Op.setOption("ResponseLocalPart","opRegistroContactoResponse");
        opRegistroContacto5Op.setOption("targetNamespace","http://ws.wscn.bn.com.pe");
        opRegistroContacto5Op.setOption("outputName","opRegistroContactoResponse");
        opRegistroContacto5Op.setOption("ResponseNamespace","http://ws.wscn.bn.com.pe");
        opRegistroContacto5Op.setOption("buildNum","cf222226.01");
        opRegistroContacto5Op.setOption("usingAddressing","false");
        opRegistroContacto5Op.setOption("inoutOrderingReq","false");
        opRegistroContacto5Op.setOption("inputName","opRegistroContactoRequest");
        opRegistroContacto5Op.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "ServicioBNInteroperabilidadService"));
        opRegistroContacto5Op.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://ws.wscn.bn.com.pe", "opRegistroContactoRequest"));
        opRegistroContacto5Op.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return opRegistroContacto5Op;

    }


    private static void initTypeMappings() {
        typeMappings = new java.util.HashMap();
        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "DesafiliacionRequest"),
                         pe.com.bn.wscn.bean.interoperabilidad.DesafiliacionRequest.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "OperacionResponse"),
                         pe.com.bn.wscn.bean.interoperabilidad.OperacionResponse.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "OperacionData"),
                         pe.com.bn.wscn.bean.interoperabilidad.OperacionData.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "ActualizacionRequest"),
                         pe.com.bn.wscn.bean.interoperabilidad.ActualizacionRequest.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "AfilicionRequest"),
                         pe.com.bn.wscn.bean.interoperabilidad.AfilicionRequest.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "AfilicionResponse"),
                         pe.com.bn.wscn.bean.interoperabilidad.AfilicionResponse.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "Afiliacion"),
                         pe.com.bn.wscn.bean.interoperabilidad.Afiliacion.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "OperacionRequest"),
                         pe.com.bn.wscn.bean.interoperabilidad.OperacionRequest.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "BarridoRequest"),
                         pe.com.bn.wscn.bean.interoperabilidad.BarridoRequest.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "BarridoResponse"),
                         pe.com.bn.wscn.bean.interoperabilidad.BarridoResponse.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "ArrayOfBarridoData"),
                         pe.com.bn.wscn.bean.interoperabilidad.BarridoData[].class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "BarridoData"),
                         pe.com.bn.wscn.bean.interoperabilidad.BarridoData.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "MotivoDesafilaicionResponse"),
                         pe.com.bn.wscn.bean.interoperabilidad.MotivoDesafilaicionResponse.class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "ArrayOfMotivo"),
                         pe.com.bn.wscn.bean.interoperabilidad.Motivo[].class);

        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://interoperabilidad.bean.wscn.bn.com.pe", "Motivo"),
                         pe.com.bn.wscn.bean.interoperabilidad.Motivo.class);

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
