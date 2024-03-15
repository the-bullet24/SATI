/**
 * ServiceSixSecurityAdvanceDelegate.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.novatronic.ws;

public interface ServiceSixSecurityAdvanceDelegate extends java.rmi.Remote {
    public com.novatronic.ws.GenerarMedioAutenticacionResponse generarMedioAutenticacion(com.novatronic.ws.GenerarMedioAutenticacionRequest generarMedioAutenticacion) throws java.rmi.RemoteException;
    public com.novatronic.ws.VerificarExistenciaMedioAutenticacionResponse verificarExistenciaMedioAutenticacion(com.novatronic.ws.VerificarExistenciaMedioAutenticacionRequest verificarExistenciaMedioAutenticacion) throws java.rmi.RemoteException;
    public com.novatronic.ws.CambiarEstadoMedioAutenticacionResponse cambiarEstadoMedioAutenticacion(com.novatronic.ws.CambiarEstadoMedioAutenticacionRequest cambiarEstadoMedioAutenticacion) throws java.rmi.RemoteException;
    public com.novatronic.ws.VerificarExistenciaElementoSeguridadResponse verificarExistenciaElementoSeguridad(com.novatronic.ws.VerificarExistenciaElementoSeguridadRequest verificarExistenciaElementoSeguridad) throws java.rmi.RemoteException;
    public com.novatronic.ws.VerificarDisponibilidadElementoSeguridadResponse verificarDisponibilidadElementoSeguridad(com.novatronic.ws.VerificarDisponibilidadElementoSeguridadRequest verificarDisponibilidadElementoSeguridad) throws java.rmi.RemoteException;
    public com.novatronic.ws.ActivarElementoSeguridadResponse activarElementoSeguridad(com.novatronic.ws.ActivarElementoSeguridadRequest activarElementoSeguridad) throws java.rmi.RemoteException;
    public com.novatronic.ws.BloquearElementoSeguridadResponse bloquearElementoSeguridad(com.novatronic.ws.BloquearElementoSeguridadRequest bloquearElementoSeguridad) throws java.rmi.RemoteException;
    public com.novatronic.ws.AsignarElementoSeguridadResponse asignarElementoSeguridad(com.novatronic.ws.AsignarElementoSeguridadRequest asignarElementoSeguridad) throws java.rmi.RemoteException;
    public com.novatronic.ws.DesasignarElementoSeguridadResponse desasignarElementoSeguridad(com.novatronic.ws.DesasignarElementoSeguridadRequest desasignarElementoSeguridad) throws java.rmi.RemoteException;
    public com.novatronic.ws.ObtenerListaElementoSeguridadRequeridosResponse obtenerListaElementoSeguridadRequeridos(com.novatronic.ws.ObtenerListaElementoSeguridadRequeridosRequest obtenerListaElementoSeguridadRequeridos) throws java.rmi.RemoteException;
    public com.novatronic.ws.IniciarOperacionResponse iniciarOperacion(com.novatronic.ws.IniciarOperacionRequest iniciarOperacion) throws java.rmi.RemoteException;
    public com.novatronic.ws.ActualizarOperacionResponse actualizarOperacion(com.novatronic.ws.ActualizarOperacionRequest actualizarOperacion) throws java.rmi.RemoteException;
    public com.novatronic.ws.ObtenerResultadoOperacionResponse obtenerResultadoOperacion(com.novatronic.ws.ObtenerResultadoOperacionRequest obtenerResultadoOperacion) throws java.rmi.RemoteException;
    public com.novatronic.ws.ObtenerListaTiposElementoSeguridadResponse obtenerListaTiposElementoSeguridad(com.novatronic.ws.ObtenerListaTiposElementoSeguridadRequest obtenerListaTiposElementoSeguridad) throws java.rmi.RemoteException;
    public com.novatronic.ws.ObtenerListaTiposMedioAutenticacionResponse obtenerListaTiposMedioAutenticacion(com.novatronic.ws.ObtenerListaTiposMedioAutenticacionRequest obtenerListaTiposMedioAutenticacion) throws java.rmi.RemoteException;
    public com.novatronic.ws.ObtenerListaBinesMedioAutenticacionResponse obtenerListaBinesMedioAutenticacion(com.novatronic.ws.ObtenerListaBinesMedioAutenticacionRequest obtenerListaBinesMedioAutenticacion) throws java.rmi.RemoteException;
    public com.novatronic.ws.ObtenerListaBinesTipoElementoSeguridadResponse obtenerListaBinesTipoElementoSeguridad(com.novatronic.ws.ObtenerListaBinesTipoElementoSeguridadRequest obtenerListaBinesTipoElementoSeguridad) throws java.rmi.RemoteException;
    public com.novatronic.ws.ObtenerListaElementoSeguridadRelacionadosResponse obtenerListaElementoSeguridadRelacionados(com.novatronic.ws.ObtenerListaElementoSeguridadRelacionadosRequest obtenerListaElementoSeguridadRelacionados) throws java.rmi.RemoteException;
    public com.novatronic.ws.SincronizarTokenResponse sincronizarToken(com.novatronic.ws.SincronizarTokenRequest sincronizarToken) throws java.rmi.RemoteException;
    public com.novatronic.ws.ValidarValoresSeguridadResponse validarValoresSeguridad(com.novatronic.ws.ValidarValoresSeguridadRequest validarValoresSeguridad) throws java.rmi.RemoteException;
    public com.novatronic.ws.GenerarValoresSeguridadResponse generarValoresSeguridad(com.novatronic.ws.GenerarValoresSeguridadRequest generarValoresSeguridad) throws java.rmi.RemoteException;
    public com.novatronic.ws.CambiarValoresSeguridadResponse cambiarValoresSeguridad(com.novatronic.ws.CambiarValoresSeguridadRequest cambiarValoresSeguridad) throws java.rmi.RemoteException;
    public com.novatronic.ws.ExtornarCambioValorSeguridadResponse extornarCambioValorSeguridad(com.novatronic.ws.ExtornarCambioValorSeguridadRequest extornarCambioValorSeguridad) throws java.rmi.RemoteException;
    public com.novatronic.ws.ValidarTDCResponse validarTDC(com.novatronic.ws.ValidarTDCRequest validarTDC) throws java.rmi.RemoteException;
    public com.novatronic.ws.ConsultarCoordenadasResponse consultarCoordenadas(com.novatronic.ws.ConsultarCoordenadasRequest consultarCoordenadas) throws java.rmi.RemoteException;
    public com.novatronic.ws.ValidarImagenResponse validarImagen(com.novatronic.ws.ValidarImagenRequest validarImagen) throws java.rmi.RemoteException;
    public com.novatronic.ws.ObtenerImagenResponse obtenerImagen(com.novatronic.ws.ObtenerImagenRequest obtenerImagen) throws java.rmi.RemoteException;
    public com.novatronic.ws.ObtenerListaImagenesResponse obtenerListaImagenes(com.novatronic.ws.ObtenerListaImagenesRequest obtenerListaImagenes) throws java.rmi.RemoteException;
    public com.novatronic.ws.ValidarTokenResponse validarToken(com.novatronic.ws.ValidarTokenRequest validarToken) throws java.rmi.RemoteException;
    public com.novatronic.ws.ValidarSMSResponse validarSMS(com.novatronic.ws.ValidarSMSRequest validarSMS) throws java.rmi.RemoteException;
    public com.novatronic.ws.GenerarSMSResponse generarSMS(com.novatronic.ws.GenerarSMSRequest generarSMS) throws java.rmi.RemoteException;
}
