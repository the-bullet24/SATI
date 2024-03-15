/**
 * ServiceSixSecurityAdvancePortBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.novatronic.ws;

public class ServiceSixSecurityAdvancePortBindingStub extends org.apache.axis.client.Stub implements com.novatronic.ws.ServiceSixSecurityAdvanceDelegate {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[31];
        _initOperationDesc1();
        _initOperationDesc2();
        _initOperationDesc3();
        _initOperationDesc4();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("generarMedioAutenticacion");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.novatronic.com/", "generarMedioAutenticacion"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://ws.novatronic.com/", "generarMedioAutenticacionRequest"), com.novatronic.ws.GenerarMedioAutenticacionRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "generarMedioAutenticacionResponse"));
        oper.setReturnClass(com.novatronic.ws.GenerarMedioAutenticacionResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.novatronic.com/", "generarMedioAutenticacionResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("verificarExistenciaMedioAutenticacion");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.novatronic.com/", "verificarExistenciaMedioAutenticacion"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://ws.novatronic.com/", "verificarExistenciaMedioAutenticacionRequest"), com.novatronic.ws.VerificarExistenciaMedioAutenticacionRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "verificarExistenciaMedioAutenticacionResponse"));
        oper.setReturnClass(com.novatronic.ws.VerificarExistenciaMedioAutenticacionResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.novatronic.com/", "verificarExistenciaMedioAutenticacionResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("cambiarEstadoMedioAutenticacion");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.novatronic.com/", "cambiarEstadoMedioAutenticacion"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://ws.novatronic.com/", "cambiarEstadoMedioAutenticacionRequest"), com.novatronic.ws.CambiarEstadoMedioAutenticacionRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "cambiarEstadoMedioAutenticacionResponse"));
        oper.setReturnClass(com.novatronic.ws.CambiarEstadoMedioAutenticacionResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.novatronic.com/", "cambiarEstadoMedioAutenticacionResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("verificarExistenciaElementoSeguridad");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.novatronic.com/", "verificarExistenciaElementoSeguridad"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://ws.novatronic.com/", "verificarExistenciaElementoSeguridadRequest"), com.novatronic.ws.VerificarExistenciaElementoSeguridadRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "verificarExistenciaElementoSeguridadResponse"));
        oper.setReturnClass(com.novatronic.ws.VerificarExistenciaElementoSeguridadResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.novatronic.com/", "verificarExistenciaElementoSeguridadResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("verificarDisponibilidadElementoSeguridad");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.novatronic.com/", "verificarDisponibilidadElementoSeguridad"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://ws.novatronic.com/", "verificarDisponibilidadElementoSeguridadRequest"), com.novatronic.ws.VerificarDisponibilidadElementoSeguridadRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "verificarDisponibilidadElementoSeguridadResponse"));
        oper.setReturnClass(com.novatronic.ws.VerificarDisponibilidadElementoSeguridadResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.novatronic.com/", "verificarDisponibilidadElementoSeguridadResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("activarElementoSeguridad");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.novatronic.com/", "activarElementoSeguridad"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://ws.novatronic.com/", "activarElementoSeguridadRequest"), com.novatronic.ws.ActivarElementoSeguridadRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "activarElementoSeguridadResponse"));
        oper.setReturnClass(com.novatronic.ws.ActivarElementoSeguridadResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.novatronic.com/", "activarElementoSeguridadResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("bloquearElementoSeguridad");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.novatronic.com/", "bloquearElementoSeguridad"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://ws.novatronic.com/", "bloquearElementoSeguridadRequest"), com.novatronic.ws.BloquearElementoSeguridadRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "bloquearElementoSeguridadResponse"));
        oper.setReturnClass(com.novatronic.ws.BloquearElementoSeguridadResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.novatronic.com/", "bloquearElementoSeguridadResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("asignarElementoSeguridad");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.novatronic.com/", "asignarElementoSeguridad"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://ws.novatronic.com/", "asignarElementoSeguridadRequest"), com.novatronic.ws.AsignarElementoSeguridadRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "asignarElementoSeguridadResponse"));
        oper.setReturnClass(com.novatronic.ws.AsignarElementoSeguridadResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.novatronic.com/", "asignarElementoSeguridadResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("desasignarElementoSeguridad");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.novatronic.com/", "desasignarElementoSeguridad"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://ws.novatronic.com/", "desasignarElementoSeguridadRequest"), com.novatronic.ws.DesasignarElementoSeguridadRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "desasignarElementoSeguridadResponse"));
        oper.setReturnClass(com.novatronic.ws.DesasignarElementoSeguridadResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.novatronic.com/", "desasignarElementoSeguridadResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerListaElementoSeguridadRequeridos");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerListaElementoSeguridadRequeridos"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerListaElementoSeguridadRequeridosRequest"), com.novatronic.ws.ObtenerListaElementoSeguridadRequeridosRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerListaElementoSeguridadRequeridosResponse"));
        oper.setReturnClass(com.novatronic.ws.ObtenerListaElementoSeguridadRequeridosResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerListaElementoSeguridadRequeridosResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("iniciarOperacion");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.novatronic.com/", "iniciarOperacion"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://ws.novatronic.com/", "iniciarOperacionRequest"), com.novatronic.ws.IniciarOperacionRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "iniciarOperacionResponse"));
        oper.setReturnClass(com.novatronic.ws.IniciarOperacionResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.novatronic.com/", "iniciarOperacionResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("actualizarOperacion");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.novatronic.com/", "actualizarOperacion"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://ws.novatronic.com/", "actualizarOperacionRequest"), com.novatronic.ws.ActualizarOperacionRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "actualizarOperacionResponse"));
        oper.setReturnClass(com.novatronic.ws.ActualizarOperacionResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.novatronic.com/", "actualizarOperacionResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerResultadoOperacion");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerResultadoOperacion"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerResultadoOperacionRequest"), com.novatronic.ws.ObtenerResultadoOperacionRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerResultadoOperacionResponse"));
        oper.setReturnClass(com.novatronic.ws.ObtenerResultadoOperacionResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerResultadoOperacionResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[12] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerListaTiposElementoSeguridad");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerListaTiposElementoSeguridad"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerListaTiposElementoSeguridadRequest"), com.novatronic.ws.ObtenerListaTiposElementoSeguridadRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerListaTiposElementoSeguridadResponse"));
        oper.setReturnClass(com.novatronic.ws.ObtenerListaTiposElementoSeguridadResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerListaTiposElementoSeguridadResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[13] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerListaTiposMedioAutenticacion");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerListaTiposMedioAutenticacion"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerListaTiposMedioAutenticacionRequest"), com.novatronic.ws.ObtenerListaTiposMedioAutenticacionRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerListaTiposMedioAutenticacionResponse"));
        oper.setReturnClass(com.novatronic.ws.ObtenerListaTiposMedioAutenticacionResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerListaTiposMedioAutenticacionResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[14] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerListaBinesMedioAutenticacion");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerListaBinesMedioAutenticacion"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerListaBinesMedioAutenticacionRequest"), com.novatronic.ws.ObtenerListaBinesMedioAutenticacionRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerListaBinesMedioAutenticacionResponse"));
        oper.setReturnClass(com.novatronic.ws.ObtenerListaBinesMedioAutenticacionResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerListaBinesMedioAutenticacionResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[15] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerListaBinesTipoElementoSeguridad");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerListaBinesTipoElementoSeguridad"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerListaBinesTipoElementoSeguridadRequest"), com.novatronic.ws.ObtenerListaBinesTipoElementoSeguridadRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerListaBinesTipoElementoSeguridadResponse"));
        oper.setReturnClass(com.novatronic.ws.ObtenerListaBinesTipoElementoSeguridadResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerListaBinesTipoElementoSeguridadResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[16] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerListaElementoSeguridadRelacionados");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerListaElementoSeguridadRelacionados"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerListaElementoSeguridadRelacionadosRequest"), com.novatronic.ws.ObtenerListaElementoSeguridadRelacionadosRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerListaElementoSeguridadRelacionadosResponse"));
        oper.setReturnClass(com.novatronic.ws.ObtenerListaElementoSeguridadRelacionadosResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerListaElementoSeguridadRelacionadosResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[17] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("validarValoresSeguridad");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.novatronic.com/", "validarValoresSeguridad"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://ws.novatronic.com/", "validarValoresSeguridadRequest"), com.novatronic.ws.ValidarValoresSeguridadRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "validarValoresSeguridadResponse"));
        oper.setReturnClass(com.novatronic.ws.ValidarValoresSeguridadResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.novatronic.com/", "validarValoresSeguridadResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[18] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("generarValoresSeguridad");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.novatronic.com/", "generarValoresSeguridad"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://ws.novatronic.com/", "generarValoresSeguridadRequest"), com.novatronic.ws.GenerarValoresSeguridadRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "generarValoresSeguridadResponse"));
        oper.setReturnClass(com.novatronic.ws.GenerarValoresSeguridadResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.novatronic.com/", "generarValoresSeguridadResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[19] = oper;

    }

    private static void _initOperationDesc3(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("cambiarValoresSeguridad");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.novatronic.com/", "cambiarValoresSeguridad"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://ws.novatronic.com/", "cambiarValoresSeguridadRequest"), com.novatronic.ws.CambiarValoresSeguridadRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "cambiarValoresSeguridadResponse"));
        oper.setReturnClass(com.novatronic.ws.CambiarValoresSeguridadResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.novatronic.com/", "cambiarValoresSeguridadResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[20] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("extornarCambioValorSeguridad");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.novatronic.com/", "extornarCambioValorSeguridad"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://ws.novatronic.com/", "extornarCambioValorSeguridadRequest"), com.novatronic.ws.ExtornarCambioValorSeguridadRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "extornarCambioValorSeguridadResponse"));
        oper.setReturnClass(com.novatronic.ws.ExtornarCambioValorSeguridadResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.novatronic.com/", "extornarCambioValorSeguridadResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[21] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("validarTDC");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.novatronic.com/", "validarTDC"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://ws.novatronic.com/", "validarTDCRequest"), com.novatronic.ws.ValidarTDCRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "validarTDCResponse"));
        oper.setReturnClass(com.novatronic.ws.ValidarTDCResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.novatronic.com/", "validarTDCResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[22] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("consultarCoordenadas");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.novatronic.com/", "consultarCoordenadas"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://ws.novatronic.com/", "consultarCoordenadasRequest"), com.novatronic.ws.ConsultarCoordenadasRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "consultarCoordenadasResponse"));
        oper.setReturnClass(com.novatronic.ws.ConsultarCoordenadasResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.novatronic.com/", "consultarCoordenadasResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[23] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("validarImagen");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.novatronic.com/", "validarImagen"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://ws.novatronic.com/", "validarImagenRequest"), com.novatronic.ws.ValidarImagenRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "validarImagenResponse"));
        oper.setReturnClass(com.novatronic.ws.ValidarImagenResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.novatronic.com/", "validarImagenResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[24] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerImagen");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerImagen"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerImagenRequest"), com.novatronic.ws.ObtenerImagenRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerImagenResponse"));
        oper.setReturnClass(com.novatronic.ws.ObtenerImagenResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerImagenResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[25] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("obtenerListaImagenes");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerListaImagenes"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerListaImagenesRequest"), com.novatronic.ws.ObtenerListaImagenesRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerListaImagenesResponse"));
        oper.setReturnClass(com.novatronic.ws.ObtenerListaImagenesResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerListaImagenesResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[26] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("validarToken");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.novatronic.com/", "validarToken"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://ws.novatronic.com/", "validarTokenRequest"), com.novatronic.ws.ValidarTokenRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "validarTokenResponse"));
        oper.setReturnClass(com.novatronic.ws.ValidarTokenResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.novatronic.com/", "validarTokenResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[27] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("sincronizarToken");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.novatronic.com/", "sincronizarToken"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://ws.novatronic.com/", "sincronizarTokenRequest"), com.novatronic.ws.SincronizarTokenRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "sincronizarTokenResponse"));
        oper.setReturnClass(com.novatronic.ws.SincronizarTokenResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.novatronic.com/", "sincronizarTokenResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[28] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("validarSMS");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.novatronic.com/", "validarSMS"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://ws.novatronic.com/", "validarSMSRequest"), com.novatronic.ws.ValidarSMSRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "validarSMSResponse"));
        oper.setReturnClass(com.novatronic.ws.ValidarSMSResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.novatronic.com/", "validarSMSResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[29] = oper;

    }

    private static void _initOperationDesc4(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("generarSMS");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.novatronic.com/", "generarSMS"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://ws.novatronic.com/", "generarSMSRequest"), com.novatronic.ws.GenerarSMSRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "generarSMSResponse"));
        oper.setReturnClass(com.novatronic.ws.GenerarSMSResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.novatronic.com/", "generarSMSResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[30] = oper;

    }

    public ServiceSixSecurityAdvancePortBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public ServiceSixSecurityAdvancePortBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public ServiceSixSecurityAdvancePortBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://jaxb.dev.java.net/array", "stringArray");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = new javax.xml.namespace.QName("", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "activarElementoSeguridadRequest");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.ActivarElementoSeguridadRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "activarElementoSeguridadResponse");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.ActivarElementoSeguridadResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "actualizarOperacionRequest");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.ActualizarOperacionRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "actualizarOperacionResponse");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.ActualizarOperacionResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "asignarElementoSeguridadRequest");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.AsignarElementoSeguridadRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "asignarElementoSeguridadResponse");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.AsignarElementoSeguridadResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "baseRequest");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.BaseRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "baseResponse");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.BaseResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "bloquearElementoSeguridadRequest");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.BloquearElementoSeguridadRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "bloquearElementoSeguridadResponse");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.BloquearElementoSeguridadResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "cambiarEstadoMedioAutenticacionRequest");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.CambiarEstadoMedioAutenticacionRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "cambiarEstadoMedioAutenticacionResponse");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.CambiarEstadoMedioAutenticacionResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "cambiarValoresSeguridadRequest");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.CambiarValoresSeguridadRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "cambiarValoresSeguridadResponse");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.CambiarValoresSeguridadResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "consultarCoordenadasRequest");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.ConsultarCoordenadasRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "consultarCoordenadasResponse");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.ConsultarCoordenadasResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "desasignarElementoSeguridadRequest");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.DesasignarElementoSeguridadRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "desasignarElementoSeguridadResponse");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.DesasignarElementoSeguridadResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "extornarCambioValorSeguridadRequest");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.ExtornarCambioValorSeguridadRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "extornarCambioValorSeguridadResponse");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.ExtornarCambioValorSeguridadResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "generarMedioAutenticacionRequest");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.GenerarMedioAutenticacionRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "generarMedioAutenticacionResponse");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.GenerarMedioAutenticacionResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "generarSMSRequest");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.GenerarSMSRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "generarSMSResponse");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.GenerarSMSResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "generarValoresSeguridadRequest");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.GenerarValoresSeguridadRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "generarValoresSeguridadResponse");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.GenerarValoresSeguridadResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "iniciarOperacionRequest");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.IniciarOperacionRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "iniciarOperacionResponse");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.IniciarOperacionResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerImagenRequest");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.ObtenerImagenRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerImagenResponse");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.ObtenerImagenResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerListaBinesMedioAutenticacionRequest");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.ObtenerListaBinesMedioAutenticacionRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerListaBinesMedioAutenticacionResponse");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.ObtenerListaBinesMedioAutenticacionResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerListaBinesTipoElementoSeguridadRequest");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.ObtenerListaBinesTipoElementoSeguridadRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerListaBinesTipoElementoSeguridadResponse");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.ObtenerListaBinesTipoElementoSeguridadResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerListaElementoSeguridadRelacionadosRequest");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.ObtenerListaElementoSeguridadRelacionadosRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerListaElementoSeguridadRelacionadosResponse");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.ObtenerListaElementoSeguridadRelacionadosResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerListaElementoSeguridadRequeridosRequest");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.ObtenerListaElementoSeguridadRequeridosRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerListaElementoSeguridadRequeridosResponse");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.ObtenerListaElementoSeguridadRequeridosResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerListaImagenesRequest");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.ObtenerListaImagenesRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerListaImagenesResponse");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.ObtenerListaImagenesResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerListaTiposElementoSeguridadRequest");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.ObtenerListaTiposElementoSeguridadRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerListaTiposElementoSeguridadResponse");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.ObtenerListaTiposElementoSeguridadResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerListaTiposMedioAutenticacionRequest");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.ObtenerListaTiposMedioAutenticacionRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerListaTiposMedioAutenticacionResponse");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.ObtenerListaTiposMedioAutenticacionResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerResultadoOperacionRequest");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.ObtenerResultadoOperacionRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerResultadoOperacionResponse");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.ObtenerResultadoOperacionResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "sincronizarTokenRequest");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.SincronizarTokenRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "sincronizarTokenResponse");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.SincronizarTokenResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "validarImagenRequest");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.ValidarImagenRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "validarImagenResponse");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.ValidarImagenResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "validarSMSRequest");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.ValidarSMSRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "validarSMSResponse");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.ValidarSMSResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "validarTDCRequest");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.ValidarTDCRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "validarTDCResponse");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.ValidarTDCResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "validarTokenRequest");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.ValidarTokenRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "validarTokenResponse");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.ValidarTokenResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "validarValoresSeguridadRequest");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.ValidarValoresSeguridadRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "validarValoresSeguridadResponse");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.ValidarValoresSeguridadResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "verificarDisponibilidadElementoSeguridadRequest");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.VerificarDisponibilidadElementoSeguridadRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "verificarDisponibilidadElementoSeguridadResponse");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.VerificarDisponibilidadElementoSeguridadResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "verificarExistenciaElementoSeguridadRequest");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.VerificarExistenciaElementoSeguridadRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "verificarExistenciaElementoSeguridadResponse");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.VerificarExistenciaElementoSeguridadResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "verificarExistenciaMedioAutenticacionRequest");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.VerificarExistenciaMedioAutenticacionRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.novatronic.com/", "verificarExistenciaMedioAutenticacionResponse");
            cachedSerQNames.add(qName);
            cls = com.novatronic.ws.VerificarExistenciaMedioAutenticacionResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public com.novatronic.ws.GenerarMedioAutenticacionResponse generarMedioAutenticacion(com.novatronic.ws.GenerarMedioAutenticacionRequest generarMedioAutenticacion) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "generarMedioAutenticacion"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {generarMedioAutenticacion});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.novatronic.ws.GenerarMedioAutenticacionResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.novatronic.ws.GenerarMedioAutenticacionResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.novatronic.ws.GenerarMedioAutenticacionResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.novatronic.ws.VerificarExistenciaMedioAutenticacionResponse verificarExistenciaMedioAutenticacion(com.novatronic.ws.VerificarExistenciaMedioAutenticacionRequest verificarExistenciaMedioAutenticacion) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "verificarExistenciaMedioAutenticacion"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {verificarExistenciaMedioAutenticacion});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.novatronic.ws.VerificarExistenciaMedioAutenticacionResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.novatronic.ws.VerificarExistenciaMedioAutenticacionResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.novatronic.ws.VerificarExistenciaMedioAutenticacionResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.novatronic.ws.CambiarEstadoMedioAutenticacionResponse cambiarEstadoMedioAutenticacion(com.novatronic.ws.CambiarEstadoMedioAutenticacionRequest cambiarEstadoMedioAutenticacion) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "cambiarEstadoMedioAutenticacion"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {cambiarEstadoMedioAutenticacion});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.novatronic.ws.CambiarEstadoMedioAutenticacionResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.novatronic.ws.CambiarEstadoMedioAutenticacionResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.novatronic.ws.CambiarEstadoMedioAutenticacionResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.novatronic.ws.VerificarExistenciaElementoSeguridadResponse verificarExistenciaElementoSeguridad(com.novatronic.ws.VerificarExistenciaElementoSeguridadRequest verificarExistenciaElementoSeguridad) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "verificarExistenciaElementoSeguridad"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {verificarExistenciaElementoSeguridad});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.novatronic.ws.VerificarExistenciaElementoSeguridadResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.novatronic.ws.VerificarExistenciaElementoSeguridadResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.novatronic.ws.VerificarExistenciaElementoSeguridadResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.novatronic.ws.VerificarDisponibilidadElementoSeguridadResponse verificarDisponibilidadElementoSeguridad(com.novatronic.ws.VerificarDisponibilidadElementoSeguridadRequest verificarDisponibilidadElementoSeguridad) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "verificarDisponibilidadElementoSeguridad"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {verificarDisponibilidadElementoSeguridad});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.novatronic.ws.VerificarDisponibilidadElementoSeguridadResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.novatronic.ws.VerificarDisponibilidadElementoSeguridadResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.novatronic.ws.VerificarDisponibilidadElementoSeguridadResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.novatronic.ws.ActivarElementoSeguridadResponse activarElementoSeguridad(com.novatronic.ws.ActivarElementoSeguridadRequest activarElementoSeguridad) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "activarElementoSeguridad"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {activarElementoSeguridad});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.novatronic.ws.ActivarElementoSeguridadResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.novatronic.ws.ActivarElementoSeguridadResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.novatronic.ws.ActivarElementoSeguridadResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.novatronic.ws.BloquearElementoSeguridadResponse bloquearElementoSeguridad(com.novatronic.ws.BloquearElementoSeguridadRequest bloquearElementoSeguridad) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "bloquearElementoSeguridad"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {bloquearElementoSeguridad});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.novatronic.ws.BloquearElementoSeguridadResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.novatronic.ws.BloquearElementoSeguridadResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.novatronic.ws.BloquearElementoSeguridadResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.novatronic.ws.AsignarElementoSeguridadResponse asignarElementoSeguridad(com.novatronic.ws.AsignarElementoSeguridadRequest asignarElementoSeguridad) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "asignarElementoSeguridad"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {asignarElementoSeguridad});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.novatronic.ws.AsignarElementoSeguridadResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.novatronic.ws.AsignarElementoSeguridadResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.novatronic.ws.AsignarElementoSeguridadResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.novatronic.ws.DesasignarElementoSeguridadResponse desasignarElementoSeguridad(com.novatronic.ws.DesasignarElementoSeguridadRequest desasignarElementoSeguridad) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "desasignarElementoSeguridad"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {desasignarElementoSeguridad});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.novatronic.ws.DesasignarElementoSeguridadResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.novatronic.ws.DesasignarElementoSeguridadResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.novatronic.ws.DesasignarElementoSeguridadResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.novatronic.ws.ObtenerListaElementoSeguridadRequeridosResponse obtenerListaElementoSeguridadRequeridos(com.novatronic.ws.ObtenerListaElementoSeguridadRequeridosRequest obtenerListaElementoSeguridadRequeridos) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "obtenerListaElementoSeguridadRequeridos"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {obtenerListaElementoSeguridadRequeridos});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.novatronic.ws.ObtenerListaElementoSeguridadRequeridosResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.novatronic.ws.ObtenerListaElementoSeguridadRequeridosResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.novatronic.ws.ObtenerListaElementoSeguridadRequeridosResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.novatronic.ws.IniciarOperacionResponse iniciarOperacion(com.novatronic.ws.IniciarOperacionRequest iniciarOperacion) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "iniciarOperacion"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {iniciarOperacion});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.novatronic.ws.IniciarOperacionResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.novatronic.ws.IniciarOperacionResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.novatronic.ws.IniciarOperacionResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.novatronic.ws.ActualizarOperacionResponse actualizarOperacion(com.novatronic.ws.ActualizarOperacionRequest actualizarOperacion) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "actualizarOperacion"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {actualizarOperacion});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.novatronic.ws.ActualizarOperacionResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.novatronic.ws.ActualizarOperacionResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.novatronic.ws.ActualizarOperacionResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.novatronic.ws.ObtenerResultadoOperacionResponse obtenerResultadoOperacion(com.novatronic.ws.ObtenerResultadoOperacionRequest obtenerResultadoOperacion) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "obtenerResultadoOperacion"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {obtenerResultadoOperacion});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.novatronic.ws.ObtenerResultadoOperacionResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.novatronic.ws.ObtenerResultadoOperacionResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.novatronic.ws.ObtenerResultadoOperacionResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.novatronic.ws.ObtenerListaTiposElementoSeguridadResponse obtenerListaTiposElementoSeguridad(com.novatronic.ws.ObtenerListaTiposElementoSeguridadRequest obtenerListaTiposElementoSeguridad) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[13]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "obtenerListaTiposElementoSeguridad"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {obtenerListaTiposElementoSeguridad});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.novatronic.ws.ObtenerListaTiposElementoSeguridadResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.novatronic.ws.ObtenerListaTiposElementoSeguridadResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.novatronic.ws.ObtenerListaTiposElementoSeguridadResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.novatronic.ws.ObtenerListaTiposMedioAutenticacionResponse obtenerListaTiposMedioAutenticacion(com.novatronic.ws.ObtenerListaTiposMedioAutenticacionRequest obtenerListaTiposMedioAutenticacion) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[14]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "obtenerListaTiposMedioAutenticacion"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {obtenerListaTiposMedioAutenticacion});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.novatronic.ws.ObtenerListaTiposMedioAutenticacionResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.novatronic.ws.ObtenerListaTiposMedioAutenticacionResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.novatronic.ws.ObtenerListaTiposMedioAutenticacionResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.novatronic.ws.ObtenerListaBinesMedioAutenticacionResponse obtenerListaBinesMedioAutenticacion(com.novatronic.ws.ObtenerListaBinesMedioAutenticacionRequest obtenerListaBinesMedioAutenticacion) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[15]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "obtenerListaBinesMedioAutenticacion"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {obtenerListaBinesMedioAutenticacion});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.novatronic.ws.ObtenerListaBinesMedioAutenticacionResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.novatronic.ws.ObtenerListaBinesMedioAutenticacionResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.novatronic.ws.ObtenerListaBinesMedioAutenticacionResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.novatronic.ws.ObtenerListaBinesTipoElementoSeguridadResponse obtenerListaBinesTipoElementoSeguridad(com.novatronic.ws.ObtenerListaBinesTipoElementoSeguridadRequest obtenerListaBinesTipoElementoSeguridad) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[16]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "obtenerListaBinesTipoElementoSeguridad"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {obtenerListaBinesTipoElementoSeguridad});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.novatronic.ws.ObtenerListaBinesTipoElementoSeguridadResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.novatronic.ws.ObtenerListaBinesTipoElementoSeguridadResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.novatronic.ws.ObtenerListaBinesTipoElementoSeguridadResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.novatronic.ws.ObtenerListaElementoSeguridadRelacionadosResponse obtenerListaElementoSeguridadRelacionados(com.novatronic.ws.ObtenerListaElementoSeguridadRelacionadosRequest obtenerListaElementoSeguridadRelacionados) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[17]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "obtenerListaElementoSeguridadRelacionados"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {obtenerListaElementoSeguridadRelacionados});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.novatronic.ws.ObtenerListaElementoSeguridadRelacionadosResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.novatronic.ws.ObtenerListaElementoSeguridadRelacionadosResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.novatronic.ws.ObtenerListaElementoSeguridadRelacionadosResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.novatronic.ws.ValidarValoresSeguridadResponse validarValoresSeguridad(com.novatronic.ws.ValidarValoresSeguridadRequest validarValoresSeguridad) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[18]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "validarValoresSeguridad"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {validarValoresSeguridad});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.novatronic.ws.ValidarValoresSeguridadResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.novatronic.ws.ValidarValoresSeguridadResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.novatronic.ws.ValidarValoresSeguridadResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.novatronic.ws.GenerarValoresSeguridadResponse generarValoresSeguridad(com.novatronic.ws.GenerarValoresSeguridadRequest generarValoresSeguridad) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[19]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "generarValoresSeguridad"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {generarValoresSeguridad});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.novatronic.ws.GenerarValoresSeguridadResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.novatronic.ws.GenerarValoresSeguridadResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.novatronic.ws.GenerarValoresSeguridadResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.novatronic.ws.CambiarValoresSeguridadResponse cambiarValoresSeguridad(com.novatronic.ws.CambiarValoresSeguridadRequest cambiarValoresSeguridad) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[20]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "cambiarValoresSeguridad"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {cambiarValoresSeguridad});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.novatronic.ws.CambiarValoresSeguridadResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.novatronic.ws.CambiarValoresSeguridadResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.novatronic.ws.CambiarValoresSeguridadResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.novatronic.ws.ExtornarCambioValorSeguridadResponse extornarCambioValorSeguridad(com.novatronic.ws.ExtornarCambioValorSeguridadRequest extornarCambioValorSeguridad) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[21]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "extornarCambioValorSeguridad"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {extornarCambioValorSeguridad});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.novatronic.ws.ExtornarCambioValorSeguridadResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.novatronic.ws.ExtornarCambioValorSeguridadResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.novatronic.ws.ExtornarCambioValorSeguridadResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.novatronic.ws.ValidarTDCResponse validarTDC(com.novatronic.ws.ValidarTDCRequest validarTDC) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[22]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "validarTDC"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {validarTDC});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.novatronic.ws.ValidarTDCResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.novatronic.ws.ValidarTDCResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.novatronic.ws.ValidarTDCResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.novatronic.ws.ConsultarCoordenadasResponse consultarCoordenadas(com.novatronic.ws.ConsultarCoordenadasRequest consultarCoordenadas) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[23]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "consultarCoordenadas"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {consultarCoordenadas});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.novatronic.ws.ConsultarCoordenadasResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.novatronic.ws.ConsultarCoordenadasResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.novatronic.ws.ConsultarCoordenadasResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.novatronic.ws.ValidarImagenResponse validarImagen(com.novatronic.ws.ValidarImagenRequest validarImagen) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[24]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "validarImagen"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {validarImagen});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.novatronic.ws.ValidarImagenResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.novatronic.ws.ValidarImagenResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.novatronic.ws.ValidarImagenResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.novatronic.ws.ObtenerImagenResponse obtenerImagen(com.novatronic.ws.ObtenerImagenRequest obtenerImagen) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[25]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "obtenerImagen"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {obtenerImagen});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.novatronic.ws.ObtenerImagenResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.novatronic.ws.ObtenerImagenResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.novatronic.ws.ObtenerImagenResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.novatronic.ws.ObtenerListaImagenesResponse obtenerListaImagenes(com.novatronic.ws.ObtenerListaImagenesRequest obtenerListaImagenes) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[26]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "obtenerListaImagenes"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {obtenerListaImagenes});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.novatronic.ws.ObtenerListaImagenesResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.novatronic.ws.ObtenerListaImagenesResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.novatronic.ws.ObtenerListaImagenesResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.novatronic.ws.ValidarTokenResponse validarToken(com.novatronic.ws.ValidarTokenRequest validarToken) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[27]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "validarToken"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {validarToken});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.novatronic.ws.ValidarTokenResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.novatronic.ws.ValidarTokenResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.novatronic.ws.ValidarTokenResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.novatronic.ws.SincronizarTokenResponse sincronizarToken(com.novatronic.ws.SincronizarTokenRequest sincronizarToken) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[28]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "sincronizarToken"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {sincronizarToken});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.novatronic.ws.SincronizarTokenResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.novatronic.ws.SincronizarTokenResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.novatronic.ws.SincronizarTokenResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.novatronic.ws.ValidarSMSResponse validarSMS(com.novatronic.ws.ValidarSMSRequest validarSMS) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[29]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "validarSMS"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {validarSMS});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.novatronic.ws.ValidarSMSResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.novatronic.ws.ValidarSMSResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.novatronic.ws.ValidarSMSResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.novatronic.ws.GenerarSMSResponse generarSMS(com.novatronic.ws.GenerarSMSRequest generarSMS) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[30]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "generarSMS"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {generarSMS});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.novatronic.ws.GenerarSMSResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.novatronic.ws.GenerarSMSResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.novatronic.ws.GenerarSMSResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
