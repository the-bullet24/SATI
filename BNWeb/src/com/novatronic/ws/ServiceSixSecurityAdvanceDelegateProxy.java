package com.novatronic.ws;

public class ServiceSixSecurityAdvanceDelegateProxy implements com.novatronic.ws.ServiceSixSecurityAdvanceDelegate {
  private String _endpoint = null;
  private com.novatronic.ws.ServiceSixSecurityAdvanceDelegate serviceSixSecurityAdvanceDelegate = null;
  
  public ServiceSixSecurityAdvanceDelegateProxy() {
    _initServiceSixSecurityAdvanceDelegateProxy();
  }
  
  public ServiceSixSecurityAdvanceDelegateProxy(String endpoint) {
    _endpoint = endpoint;
    _initServiceSixSecurityAdvanceDelegateProxy();
  }
  
  private void _initServiceSixSecurityAdvanceDelegateProxy() {
    try {
      serviceSixSecurityAdvanceDelegate = (new com.novatronic.ws.ServiceSixSecurityAdvanceServiceLocator()).getServiceSixSecurityAdvancePort();
      if (serviceSixSecurityAdvanceDelegate != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)serviceSixSecurityAdvanceDelegate)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)serviceSixSecurityAdvanceDelegate)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (serviceSixSecurityAdvanceDelegate != null)
      ((javax.xml.rpc.Stub)serviceSixSecurityAdvanceDelegate)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.novatronic.ws.ServiceSixSecurityAdvanceDelegate getServiceSixSecurityAdvanceDelegate() {
    if (serviceSixSecurityAdvanceDelegate == null)
      _initServiceSixSecurityAdvanceDelegateProxy();
    return serviceSixSecurityAdvanceDelegate;
  }
  
  public com.novatronic.ws.GenerarMedioAutenticacionResponse generarMedioAutenticacion(com.novatronic.ws.GenerarMedioAutenticacionRequest generarMedioAutenticacion) throws java.rmi.RemoteException{
    if (serviceSixSecurityAdvanceDelegate == null)
      _initServiceSixSecurityAdvanceDelegateProxy();
    return serviceSixSecurityAdvanceDelegate.generarMedioAutenticacion(generarMedioAutenticacion);
  }
  
  public com.novatronic.ws.VerificarExistenciaMedioAutenticacionResponse verificarExistenciaMedioAutenticacion(com.novatronic.ws.VerificarExistenciaMedioAutenticacionRequest verificarExistenciaMedioAutenticacion) throws java.rmi.RemoteException{
    if (serviceSixSecurityAdvanceDelegate == null)
      _initServiceSixSecurityAdvanceDelegateProxy();
    return serviceSixSecurityAdvanceDelegate.verificarExistenciaMedioAutenticacion(verificarExistenciaMedioAutenticacion);
  }
  
  public com.novatronic.ws.CambiarEstadoMedioAutenticacionResponse cambiarEstadoMedioAutenticacion(com.novatronic.ws.CambiarEstadoMedioAutenticacionRequest cambiarEstadoMedioAutenticacion) throws java.rmi.RemoteException{
    if (serviceSixSecurityAdvanceDelegate == null)
      _initServiceSixSecurityAdvanceDelegateProxy();
    return serviceSixSecurityAdvanceDelegate.cambiarEstadoMedioAutenticacion(cambiarEstadoMedioAutenticacion);
  }
  
  public com.novatronic.ws.VerificarExistenciaElementoSeguridadResponse verificarExistenciaElementoSeguridad(com.novatronic.ws.VerificarExistenciaElementoSeguridadRequest verificarExistenciaElementoSeguridad) throws java.rmi.RemoteException{
    if (serviceSixSecurityAdvanceDelegate == null)
      _initServiceSixSecurityAdvanceDelegateProxy();
    return serviceSixSecurityAdvanceDelegate.verificarExistenciaElementoSeguridad(verificarExistenciaElementoSeguridad);
  }
  
  public com.novatronic.ws.VerificarDisponibilidadElementoSeguridadResponse verificarDisponibilidadElementoSeguridad(com.novatronic.ws.VerificarDisponibilidadElementoSeguridadRequest verificarDisponibilidadElementoSeguridad) throws java.rmi.RemoteException{
    if (serviceSixSecurityAdvanceDelegate == null)
      _initServiceSixSecurityAdvanceDelegateProxy();
    return serviceSixSecurityAdvanceDelegate.verificarDisponibilidadElementoSeguridad(verificarDisponibilidadElementoSeguridad);
  }
  
  public com.novatronic.ws.ActivarElementoSeguridadResponse activarElementoSeguridad(com.novatronic.ws.ActivarElementoSeguridadRequest activarElementoSeguridad) throws java.rmi.RemoteException{
    if (serviceSixSecurityAdvanceDelegate == null)
      _initServiceSixSecurityAdvanceDelegateProxy();
    return serviceSixSecurityAdvanceDelegate.activarElementoSeguridad(activarElementoSeguridad);
  }
  
  public com.novatronic.ws.BloquearElementoSeguridadResponse bloquearElementoSeguridad(com.novatronic.ws.BloquearElementoSeguridadRequest bloquearElementoSeguridad) throws java.rmi.RemoteException{
    if (serviceSixSecurityAdvanceDelegate == null)
      _initServiceSixSecurityAdvanceDelegateProxy();
    return serviceSixSecurityAdvanceDelegate.bloquearElementoSeguridad(bloquearElementoSeguridad);
  }
  
  public com.novatronic.ws.AsignarElementoSeguridadResponse asignarElementoSeguridad(com.novatronic.ws.AsignarElementoSeguridadRequest asignarElementoSeguridad) throws java.rmi.RemoteException{
    if (serviceSixSecurityAdvanceDelegate == null)
      _initServiceSixSecurityAdvanceDelegateProxy();
    return serviceSixSecurityAdvanceDelegate.asignarElementoSeguridad(asignarElementoSeguridad);
  }
  
  public com.novatronic.ws.DesasignarElementoSeguridadResponse desasignarElementoSeguridad(com.novatronic.ws.DesasignarElementoSeguridadRequest desasignarElementoSeguridad) throws java.rmi.RemoteException{
    if (serviceSixSecurityAdvanceDelegate == null)
      _initServiceSixSecurityAdvanceDelegateProxy();
    return serviceSixSecurityAdvanceDelegate.desasignarElementoSeguridad(desasignarElementoSeguridad);
  }
  
  public com.novatronic.ws.ObtenerListaElementoSeguridadRequeridosResponse obtenerListaElementoSeguridadRequeridos(com.novatronic.ws.ObtenerListaElementoSeguridadRequeridosRequest obtenerListaElementoSeguridadRequeridos) throws java.rmi.RemoteException{
    if (serviceSixSecurityAdvanceDelegate == null)
      _initServiceSixSecurityAdvanceDelegateProxy();
    return serviceSixSecurityAdvanceDelegate.obtenerListaElementoSeguridadRequeridos(obtenerListaElementoSeguridadRequeridos);
  }
  
  public com.novatronic.ws.IniciarOperacionResponse iniciarOperacion(com.novatronic.ws.IniciarOperacionRequest iniciarOperacion) throws java.rmi.RemoteException{
    if (serviceSixSecurityAdvanceDelegate == null)
      _initServiceSixSecurityAdvanceDelegateProxy();
    return serviceSixSecurityAdvanceDelegate.iniciarOperacion(iniciarOperacion);
  }
  
  public com.novatronic.ws.ActualizarOperacionResponse actualizarOperacion(com.novatronic.ws.ActualizarOperacionRequest actualizarOperacion) throws java.rmi.RemoteException{
    if (serviceSixSecurityAdvanceDelegate == null)
      _initServiceSixSecurityAdvanceDelegateProxy();
    return serviceSixSecurityAdvanceDelegate.actualizarOperacion(actualizarOperacion);
  }
  
  public com.novatronic.ws.ObtenerResultadoOperacionResponse obtenerResultadoOperacion(com.novatronic.ws.ObtenerResultadoOperacionRequest obtenerResultadoOperacion) throws java.rmi.RemoteException{
    if (serviceSixSecurityAdvanceDelegate == null)
      _initServiceSixSecurityAdvanceDelegateProxy();
    return serviceSixSecurityAdvanceDelegate.obtenerResultadoOperacion(obtenerResultadoOperacion);
  }
  
  public com.novatronic.ws.ObtenerListaTiposElementoSeguridadResponse obtenerListaTiposElementoSeguridad(com.novatronic.ws.ObtenerListaTiposElementoSeguridadRequest obtenerListaTiposElementoSeguridad) throws java.rmi.RemoteException{
    if (serviceSixSecurityAdvanceDelegate == null)
      _initServiceSixSecurityAdvanceDelegateProxy();
    return serviceSixSecurityAdvanceDelegate.obtenerListaTiposElementoSeguridad(obtenerListaTiposElementoSeguridad);
  }
  
  public com.novatronic.ws.ObtenerListaTiposMedioAutenticacionResponse obtenerListaTiposMedioAutenticacion(com.novatronic.ws.ObtenerListaTiposMedioAutenticacionRequest obtenerListaTiposMedioAutenticacion) throws java.rmi.RemoteException{
    if (serviceSixSecurityAdvanceDelegate == null)
      _initServiceSixSecurityAdvanceDelegateProxy();
    return serviceSixSecurityAdvanceDelegate.obtenerListaTiposMedioAutenticacion(obtenerListaTiposMedioAutenticacion);
  }
  
  public com.novatronic.ws.ObtenerListaBinesMedioAutenticacionResponse obtenerListaBinesMedioAutenticacion(com.novatronic.ws.ObtenerListaBinesMedioAutenticacionRequest obtenerListaBinesMedioAutenticacion) throws java.rmi.RemoteException{
    if (serviceSixSecurityAdvanceDelegate == null)
      _initServiceSixSecurityAdvanceDelegateProxy();
    return serviceSixSecurityAdvanceDelegate.obtenerListaBinesMedioAutenticacion(obtenerListaBinesMedioAutenticacion);
  }
  
  public com.novatronic.ws.ObtenerListaBinesTipoElementoSeguridadResponse obtenerListaBinesTipoElementoSeguridad(com.novatronic.ws.ObtenerListaBinesTipoElementoSeguridadRequest obtenerListaBinesTipoElementoSeguridad) throws java.rmi.RemoteException{
    if (serviceSixSecurityAdvanceDelegate == null)
      _initServiceSixSecurityAdvanceDelegateProxy();
    return serviceSixSecurityAdvanceDelegate.obtenerListaBinesTipoElementoSeguridad(obtenerListaBinesTipoElementoSeguridad);
  }
  
  public com.novatronic.ws.ObtenerListaElementoSeguridadRelacionadosResponse obtenerListaElementoSeguridadRelacionados(com.novatronic.ws.ObtenerListaElementoSeguridadRelacionadosRequest obtenerListaElementoSeguridadRelacionados) throws java.rmi.RemoteException{
    if (serviceSixSecurityAdvanceDelegate == null)
      _initServiceSixSecurityAdvanceDelegateProxy();
    return serviceSixSecurityAdvanceDelegate.obtenerListaElementoSeguridadRelacionados(obtenerListaElementoSeguridadRelacionados);
  }
  
  public com.novatronic.ws.SincronizarTokenResponse sincronizarToken(com.novatronic.ws.SincronizarTokenRequest sincronizarToken) throws java.rmi.RemoteException{
    if (serviceSixSecurityAdvanceDelegate == null)
      _initServiceSixSecurityAdvanceDelegateProxy();
    return serviceSixSecurityAdvanceDelegate.sincronizarToken(sincronizarToken);
  }
  
  public com.novatronic.ws.ValidarValoresSeguridadResponse validarValoresSeguridad(com.novatronic.ws.ValidarValoresSeguridadRequest validarValoresSeguridad) throws java.rmi.RemoteException{
    if (serviceSixSecurityAdvanceDelegate == null)
      _initServiceSixSecurityAdvanceDelegateProxy();
    return serviceSixSecurityAdvanceDelegate.validarValoresSeguridad(validarValoresSeguridad);
  }
  
  public com.novatronic.ws.GenerarValoresSeguridadResponse generarValoresSeguridad(com.novatronic.ws.GenerarValoresSeguridadRequest generarValoresSeguridad) throws java.rmi.RemoteException{
    if (serviceSixSecurityAdvanceDelegate == null)
      _initServiceSixSecurityAdvanceDelegateProxy();
    return serviceSixSecurityAdvanceDelegate.generarValoresSeguridad(generarValoresSeguridad);
  }
  
  public com.novatronic.ws.CambiarValoresSeguridadResponse cambiarValoresSeguridad(com.novatronic.ws.CambiarValoresSeguridadRequest cambiarValoresSeguridad) throws java.rmi.RemoteException{
    if (serviceSixSecurityAdvanceDelegate == null)
      _initServiceSixSecurityAdvanceDelegateProxy();
    return serviceSixSecurityAdvanceDelegate.cambiarValoresSeguridad(cambiarValoresSeguridad);
  }
  
  public com.novatronic.ws.ExtornarCambioValorSeguridadResponse extornarCambioValorSeguridad(com.novatronic.ws.ExtornarCambioValorSeguridadRequest extornarCambioValorSeguridad) throws java.rmi.RemoteException{
    if (serviceSixSecurityAdvanceDelegate == null)
      _initServiceSixSecurityAdvanceDelegateProxy();
    return serviceSixSecurityAdvanceDelegate.extornarCambioValorSeguridad(extornarCambioValorSeguridad);
  }
  
  public com.novatronic.ws.ValidarTDCResponse validarTDC(com.novatronic.ws.ValidarTDCRequest validarTDC) throws java.rmi.RemoteException{
    if (serviceSixSecurityAdvanceDelegate == null)
      _initServiceSixSecurityAdvanceDelegateProxy();
    return serviceSixSecurityAdvanceDelegate.validarTDC(validarTDC);
  }
  
  public com.novatronic.ws.ConsultarCoordenadasResponse consultarCoordenadas(com.novatronic.ws.ConsultarCoordenadasRequest consultarCoordenadas) throws java.rmi.RemoteException{
    if (serviceSixSecurityAdvanceDelegate == null)
      _initServiceSixSecurityAdvanceDelegateProxy();
    return serviceSixSecurityAdvanceDelegate.consultarCoordenadas(consultarCoordenadas);
  }
  
  public com.novatronic.ws.ValidarImagenResponse validarImagen(com.novatronic.ws.ValidarImagenRequest validarImagen) throws java.rmi.RemoteException{
    if (serviceSixSecurityAdvanceDelegate == null)
      _initServiceSixSecurityAdvanceDelegateProxy();
    return serviceSixSecurityAdvanceDelegate.validarImagen(validarImagen);
  }
  
  public com.novatronic.ws.ObtenerImagenResponse obtenerImagen(com.novatronic.ws.ObtenerImagenRequest obtenerImagen) throws java.rmi.RemoteException{
    if (serviceSixSecurityAdvanceDelegate == null)
      _initServiceSixSecurityAdvanceDelegateProxy();
    return serviceSixSecurityAdvanceDelegate.obtenerImagen(obtenerImagen);
  }
  
  public com.novatronic.ws.ObtenerListaImagenesResponse obtenerListaImagenes(com.novatronic.ws.ObtenerListaImagenesRequest obtenerListaImagenes) throws java.rmi.RemoteException{
    if (serviceSixSecurityAdvanceDelegate == null)
      _initServiceSixSecurityAdvanceDelegateProxy();
    return serviceSixSecurityAdvanceDelegate.obtenerListaImagenes(obtenerListaImagenes);
  }
  
  public com.novatronic.ws.ValidarTokenResponse validarToken(com.novatronic.ws.ValidarTokenRequest validarToken) throws java.rmi.RemoteException{
    if (serviceSixSecurityAdvanceDelegate == null)
      _initServiceSixSecurityAdvanceDelegateProxy();
    return serviceSixSecurityAdvanceDelegate.validarToken(validarToken);
  }
  
  public com.novatronic.ws.ValidarSMSResponse validarSMS(com.novatronic.ws.ValidarSMSRequest validarSMS) throws java.rmi.RemoteException{
    if (serviceSixSecurityAdvanceDelegate == null)
      _initServiceSixSecurityAdvanceDelegateProxy();
    return serviceSixSecurityAdvanceDelegate.validarSMS(validarSMS);
  }
  
  public com.novatronic.ws.GenerarSMSResponse generarSMS(com.novatronic.ws.GenerarSMSRequest generarSMS) throws java.rmi.RemoteException{
    if (serviceSixSecurityAdvanceDelegate == null)
      _initServiceSixSecurityAdvanceDelegateProxy();
    return serviceSixSecurityAdvanceDelegate.generarSMS(generarSMS);
  }
  
  
}