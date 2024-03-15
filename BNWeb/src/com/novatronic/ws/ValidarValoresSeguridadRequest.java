/**
 * ValidarValoresSeguridadRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.novatronic.ws;

public class ValidarValoresSeguridadRequest  extends com.novatronic.ws.BaseRequest  implements java.io.Serializable {
    private java.lang.String binMedioAutenticacion;

    private java.lang.String codigoMedioAutenticacion;

    private java.lang.Integer fechaExpiracion;

    private java.lang.Integer flagValidacionCVV;

    private java.lang.Integer flagValidacionCVV2;

    private java.lang.Integer flagValidacionOffset;

    private java.lang.Integer flagValidacionPVV;

    private java.lang.Integer flagValidacionPassword;

    private java.lang.String hashcodePassword;

    private java.lang.String identificadorOperacion;

    private java.lang.Integer indicadorLecturaPAN;

    private java.lang.Integer indicadorUbicacionOffset;

    private java.lang.Integer indicadorUbicacionPVV;

    private java.lang.String pinblockOffset;

    private java.lang.String pinblockPVV;

    private java.lang.Integer pvkiOffset;

    private java.lang.Integer pvkiPVV;

    private java.lang.Integer tipoElementoSeguridadCVV;

    private java.lang.Integer tipoElementoSeguridadCVV2;

    private java.lang.Integer tipoElementoSeguridadOffset;

    private java.lang.Integer tipoElementoSeguridadPVV;

    private java.lang.Integer tipoElementoSeguridadPassword;

    private java.lang.String tipoValidacion;

    private java.lang.String track2;

    private java.lang.String valorCVV;

    private java.lang.String valorCVV2;

    private java.lang.String valorSeguridadOffset;

    private java.lang.String valorSeguridadPVV;

    public ValidarValoresSeguridadRequest() {
    }

    public ValidarValoresSeguridadRequest(
           java.lang.String codigoAplicacionOrigen,
           java.lang.String codigoComercio,
           java.lang.String codigoEmpresa,
           java.lang.String codigoZonaIntercambio,
           java.lang.String numeroTerminal,
           java.lang.String tipoAplicacionOrigen,
           java.lang.Integer tipoTerminal,
           java.lang.String usuario,
           java.util.Calendar fechaDate,
           java.lang.Integer numeroSecuencia,
           java.lang.String binMedioAutenticacion,
           java.lang.String codigoMedioAutenticacion,
           java.lang.Integer fechaExpiracion,
           java.lang.Integer flagValidacionCVV,
           java.lang.Integer flagValidacionCVV2,
           java.lang.Integer flagValidacionOffset,
           java.lang.Integer flagValidacionPVV,
           java.lang.Integer flagValidacionPassword,
           java.lang.String hashcodePassword,
           java.lang.String identificadorOperacion,
           java.lang.Integer indicadorLecturaPAN,
           java.lang.Integer indicadorUbicacionOffset,
           java.lang.Integer indicadorUbicacionPVV,
           java.lang.String pinblockOffset,
           java.lang.String pinblockPVV,
           java.lang.Integer pvkiOffset,
           java.lang.Integer pvkiPVV,
           java.lang.Integer tipoElementoSeguridadCVV,
           java.lang.Integer tipoElementoSeguridadCVV2,
           java.lang.Integer tipoElementoSeguridadOffset,
           java.lang.Integer tipoElementoSeguridadPVV,
           java.lang.Integer tipoElementoSeguridadPassword,
           java.lang.String tipoValidacion,
           java.lang.String track2,
           java.lang.String valorCVV,
           java.lang.String valorCVV2,
           java.lang.String valorSeguridadOffset,
           java.lang.String valorSeguridadPVV) {
        super(
            codigoAplicacionOrigen,
            codigoComercio,
            codigoEmpresa,
            codigoZonaIntercambio,
            numeroTerminal,
            tipoAplicacionOrigen,
            tipoTerminal,
            usuario,
            fechaDate,
            numeroSecuencia);
        this.binMedioAutenticacion = binMedioAutenticacion;
        this.codigoMedioAutenticacion = codigoMedioAutenticacion;
        this.fechaExpiracion = fechaExpiracion;
        this.flagValidacionCVV = flagValidacionCVV;
        this.flagValidacionCVV2 = flagValidacionCVV2;
        this.flagValidacionOffset = flagValidacionOffset;
        this.flagValidacionPVV = flagValidacionPVV;
        this.flagValidacionPassword = flagValidacionPassword;
        this.hashcodePassword = hashcodePassword;
        this.identificadorOperacion = identificadorOperacion;
        this.indicadorLecturaPAN = indicadorLecturaPAN;
        this.indicadorUbicacionOffset = indicadorUbicacionOffset;
        this.indicadorUbicacionPVV = indicadorUbicacionPVV;
        this.pinblockOffset = pinblockOffset;
        this.pinblockPVV = pinblockPVV;
        this.pvkiOffset = pvkiOffset;
        this.pvkiPVV = pvkiPVV;
        this.tipoElementoSeguridadCVV = tipoElementoSeguridadCVV;
        this.tipoElementoSeguridadCVV2 = tipoElementoSeguridadCVV2;
        this.tipoElementoSeguridadOffset = tipoElementoSeguridadOffset;
        this.tipoElementoSeguridadPVV = tipoElementoSeguridadPVV;
        this.tipoElementoSeguridadPassword = tipoElementoSeguridadPassword;
        this.tipoValidacion = tipoValidacion;
        this.track2 = track2;
        this.valorCVV = valorCVV;
        this.valorCVV2 = valorCVV2;
        this.valorSeguridadOffset = valorSeguridadOffset;
        this.valorSeguridadPVV = valorSeguridadPVV;
    }


    /**
     * Gets the binMedioAutenticacion value for this ValidarValoresSeguridadRequest.
     * 
     * @return binMedioAutenticacion
     */
    public java.lang.String getBinMedioAutenticacion() {
        return binMedioAutenticacion;
    }


    /**
     * Sets the binMedioAutenticacion value for this ValidarValoresSeguridadRequest.
     * 
     * @param binMedioAutenticacion
     */
    public void setBinMedioAutenticacion(java.lang.String binMedioAutenticacion) {
        this.binMedioAutenticacion = binMedioAutenticacion;
    }


    /**
     * Gets the codigoMedioAutenticacion value for this ValidarValoresSeguridadRequest.
     * 
     * @return codigoMedioAutenticacion
     */
    public java.lang.String getCodigoMedioAutenticacion() {
        return codigoMedioAutenticacion;
    }


    /**
     * Sets the codigoMedioAutenticacion value for this ValidarValoresSeguridadRequest.
     * 
     * @param codigoMedioAutenticacion
     */
    public void setCodigoMedioAutenticacion(java.lang.String codigoMedioAutenticacion) {
        this.codigoMedioAutenticacion = codigoMedioAutenticacion;
    }


    /**
     * Gets the fechaExpiracion value for this ValidarValoresSeguridadRequest.
     * 
     * @return fechaExpiracion
     */
    public java.lang.Integer getFechaExpiracion() {
        return fechaExpiracion;
    }


    /**
     * Sets the fechaExpiracion value for this ValidarValoresSeguridadRequest.
     * 
     * @param fechaExpiracion
     */
    public void setFechaExpiracion(java.lang.Integer fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }


    /**
     * Gets the flagValidacionCVV value for this ValidarValoresSeguridadRequest.
     * 
     * @return flagValidacionCVV
     */
    public java.lang.Integer getFlagValidacionCVV() {
        return flagValidacionCVV;
    }


    /**
     * Sets the flagValidacionCVV value for this ValidarValoresSeguridadRequest.
     * 
     * @param flagValidacionCVV
     */
    public void setFlagValidacionCVV(java.lang.Integer flagValidacionCVV) {
        this.flagValidacionCVV = flagValidacionCVV;
    }


    /**
     * Gets the flagValidacionCVV2 value for this ValidarValoresSeguridadRequest.
     * 
     * @return flagValidacionCVV2
     */
    public java.lang.Integer getFlagValidacionCVV2() {
        return flagValidacionCVV2;
    }


    /**
     * Sets the flagValidacionCVV2 value for this ValidarValoresSeguridadRequest.
     * 
     * @param flagValidacionCVV2
     */
    public void setFlagValidacionCVV2(java.lang.Integer flagValidacionCVV2) {
        this.flagValidacionCVV2 = flagValidacionCVV2;
    }


    /**
     * Gets the flagValidacionOffset value for this ValidarValoresSeguridadRequest.
     * 
     * @return flagValidacionOffset
     */
    public java.lang.Integer getFlagValidacionOffset() {
        return flagValidacionOffset;
    }


    /**
     * Sets the flagValidacionOffset value for this ValidarValoresSeguridadRequest.
     * 
     * @param flagValidacionOffset
     */
    public void setFlagValidacionOffset(java.lang.Integer flagValidacionOffset) {
        this.flagValidacionOffset = flagValidacionOffset;
    }


    /**
     * Gets the flagValidacionPVV value for this ValidarValoresSeguridadRequest.
     * 
     * @return flagValidacionPVV
     */
    public java.lang.Integer getFlagValidacionPVV() {
        return flagValidacionPVV;
    }


    /**
     * Sets the flagValidacionPVV value for this ValidarValoresSeguridadRequest.
     * 
     * @param flagValidacionPVV
     */
    public void setFlagValidacionPVV(java.lang.Integer flagValidacionPVV) {
        this.flagValidacionPVV = flagValidacionPVV;
    }


    /**
     * Gets the flagValidacionPassword value for this ValidarValoresSeguridadRequest.
     * 
     * @return flagValidacionPassword
     */
    public java.lang.Integer getFlagValidacionPassword() {
        return flagValidacionPassword;
    }


    /**
     * Sets the flagValidacionPassword value for this ValidarValoresSeguridadRequest.
     * 
     * @param flagValidacionPassword
     */
    public void setFlagValidacionPassword(java.lang.Integer flagValidacionPassword) {
        this.flagValidacionPassword = flagValidacionPassword;
    }


    /**
     * Gets the hashcodePassword value for this ValidarValoresSeguridadRequest.
     * 
     * @return hashcodePassword
     */
    public java.lang.String getHashcodePassword() {
        return hashcodePassword;
    }


    /**
     * Sets the hashcodePassword value for this ValidarValoresSeguridadRequest.
     * 
     * @param hashcodePassword
     */
    public void setHashcodePassword(java.lang.String hashcodePassword) {
        this.hashcodePassword = hashcodePassword;
    }


    /**
     * Gets the identificadorOperacion value for this ValidarValoresSeguridadRequest.
     * 
     * @return identificadorOperacion
     */
    public java.lang.String getIdentificadorOperacion() {
        return identificadorOperacion;
    }


    /**
     * Sets the identificadorOperacion value for this ValidarValoresSeguridadRequest.
     * 
     * @param identificadorOperacion
     */
    public void setIdentificadorOperacion(java.lang.String identificadorOperacion) {
        this.identificadorOperacion = identificadorOperacion;
    }


    /**
     * Gets the indicadorLecturaPAN value for this ValidarValoresSeguridadRequest.
     * 
     * @return indicadorLecturaPAN
     */
    public java.lang.Integer getIndicadorLecturaPAN() {
        return indicadorLecturaPAN;
    }


    /**
     * Sets the indicadorLecturaPAN value for this ValidarValoresSeguridadRequest.
     * 
     * @param indicadorLecturaPAN
     */
    public void setIndicadorLecturaPAN(java.lang.Integer indicadorLecturaPAN) {
        this.indicadorLecturaPAN = indicadorLecturaPAN;
    }


    /**
     * Gets the indicadorUbicacionOffset value for this ValidarValoresSeguridadRequest.
     * 
     * @return indicadorUbicacionOffset
     */
    public java.lang.Integer getIndicadorUbicacionOffset() {
        return indicadorUbicacionOffset;
    }


    /**
     * Sets the indicadorUbicacionOffset value for this ValidarValoresSeguridadRequest.
     * 
     * @param indicadorUbicacionOffset
     */
    public void setIndicadorUbicacionOffset(java.lang.Integer indicadorUbicacionOffset) {
        this.indicadorUbicacionOffset = indicadorUbicacionOffset;
    }


    /**
     * Gets the indicadorUbicacionPVV value for this ValidarValoresSeguridadRequest.
     * 
     * @return indicadorUbicacionPVV
     */
    public java.lang.Integer getIndicadorUbicacionPVV() {
        return indicadorUbicacionPVV;
    }


    /**
     * Sets the indicadorUbicacionPVV value for this ValidarValoresSeguridadRequest.
     * 
     * @param indicadorUbicacionPVV
     */
    public void setIndicadorUbicacionPVV(java.lang.Integer indicadorUbicacionPVV) {
        this.indicadorUbicacionPVV = indicadorUbicacionPVV;
    }


    /**
     * Gets the pinblockOffset value for this ValidarValoresSeguridadRequest.
     * 
     * @return pinblockOffset
     */
    public java.lang.String getPinblockOffset() {
        return pinblockOffset;
    }


    /**
     * Sets the pinblockOffset value for this ValidarValoresSeguridadRequest.
     * 
     * @param pinblockOffset
     */
    public void setPinblockOffset(java.lang.String pinblockOffset) {
        this.pinblockOffset = pinblockOffset;
    }


    /**
     * Gets the pinblockPVV value for this ValidarValoresSeguridadRequest.
     * 
     * @return pinblockPVV
     */
    public java.lang.String getPinblockPVV() {
        return pinblockPVV;
    }


    /**
     * Sets the pinblockPVV value for this ValidarValoresSeguridadRequest.
     * 
     * @param pinblockPVV
     */
    public void setPinblockPVV(java.lang.String pinblockPVV) {
        this.pinblockPVV = pinblockPVV;
    }


    /**
     * Gets the pvkiOffset value for this ValidarValoresSeguridadRequest.
     * 
     * @return pvkiOffset
     */
    public java.lang.Integer getPvkiOffset() {
        return pvkiOffset;
    }


    /**
     * Sets the pvkiOffset value for this ValidarValoresSeguridadRequest.
     * 
     * @param pvkiOffset
     */
    public void setPvkiOffset(java.lang.Integer pvkiOffset) {
        this.pvkiOffset = pvkiOffset;
    }


    /**
     * Gets the pvkiPVV value for this ValidarValoresSeguridadRequest.
     * 
     * @return pvkiPVV
     */
    public java.lang.Integer getPvkiPVV() {
        return pvkiPVV;
    }


    /**
     * Sets the pvkiPVV value for this ValidarValoresSeguridadRequest.
     * 
     * @param pvkiPVV
     */
    public void setPvkiPVV(java.lang.Integer pvkiPVV) {
        this.pvkiPVV = pvkiPVV;
    }


    /**
     * Gets the tipoElementoSeguridadCVV value for this ValidarValoresSeguridadRequest.
     * 
     * @return tipoElementoSeguridadCVV
     */
    public java.lang.Integer getTipoElementoSeguridadCVV() {
        return tipoElementoSeguridadCVV;
    }


    /**
     * Sets the tipoElementoSeguridadCVV value for this ValidarValoresSeguridadRequest.
     * 
     * @param tipoElementoSeguridadCVV
     */
    public void setTipoElementoSeguridadCVV(java.lang.Integer tipoElementoSeguridadCVV) {
        this.tipoElementoSeguridadCVV = tipoElementoSeguridadCVV;
    }


    /**
     * Gets the tipoElementoSeguridadCVV2 value for this ValidarValoresSeguridadRequest.
     * 
     * @return tipoElementoSeguridadCVV2
     */
    public java.lang.Integer getTipoElementoSeguridadCVV2() {
        return tipoElementoSeguridadCVV2;
    }


    /**
     * Sets the tipoElementoSeguridadCVV2 value for this ValidarValoresSeguridadRequest.
     * 
     * @param tipoElementoSeguridadCVV2
     */
    public void setTipoElementoSeguridadCVV2(java.lang.Integer tipoElementoSeguridadCVV2) {
        this.tipoElementoSeguridadCVV2 = tipoElementoSeguridadCVV2;
    }


    /**
     * Gets the tipoElementoSeguridadOffset value for this ValidarValoresSeguridadRequest.
     * 
     * @return tipoElementoSeguridadOffset
     */
    public java.lang.Integer getTipoElementoSeguridadOffset() {
        return tipoElementoSeguridadOffset;
    }


    /**
     * Sets the tipoElementoSeguridadOffset value for this ValidarValoresSeguridadRequest.
     * 
     * @param tipoElementoSeguridadOffset
     */
    public void setTipoElementoSeguridadOffset(java.lang.Integer tipoElementoSeguridadOffset) {
        this.tipoElementoSeguridadOffset = tipoElementoSeguridadOffset;
    }


    /**
     * Gets the tipoElementoSeguridadPVV value for this ValidarValoresSeguridadRequest.
     * 
     * @return tipoElementoSeguridadPVV
     */
    public java.lang.Integer getTipoElementoSeguridadPVV() {
        return tipoElementoSeguridadPVV;
    }


    /**
     * Sets the tipoElementoSeguridadPVV value for this ValidarValoresSeguridadRequest.
     * 
     * @param tipoElementoSeguridadPVV
     */
    public void setTipoElementoSeguridadPVV(java.lang.Integer tipoElementoSeguridadPVV) {
        this.tipoElementoSeguridadPVV = tipoElementoSeguridadPVV;
    }


    /**
     * Gets the tipoElementoSeguridadPassword value for this ValidarValoresSeguridadRequest.
     * 
     * @return tipoElementoSeguridadPassword
     */
    public java.lang.Integer getTipoElementoSeguridadPassword() {
        return tipoElementoSeguridadPassword;
    }


    /**
     * Sets the tipoElementoSeguridadPassword value for this ValidarValoresSeguridadRequest.
     * 
     * @param tipoElementoSeguridadPassword
     */
    public void setTipoElementoSeguridadPassword(java.lang.Integer tipoElementoSeguridadPassword) {
        this.tipoElementoSeguridadPassword = tipoElementoSeguridadPassword;
    }


    /**
     * Gets the tipoValidacion value for this ValidarValoresSeguridadRequest.
     * 
     * @return tipoValidacion
     */
    public java.lang.String getTipoValidacion() {
        return tipoValidacion;
    }


    /**
     * Sets the tipoValidacion value for this ValidarValoresSeguridadRequest.
     * 
     * @param tipoValidacion
     */
    public void setTipoValidacion(java.lang.String tipoValidacion) {
        this.tipoValidacion = tipoValidacion;
    }


    /**
     * Gets the track2 value for this ValidarValoresSeguridadRequest.
     * 
     * @return track2
     */
    public java.lang.String getTrack2() {
        return track2;
    }


    /**
     * Sets the track2 value for this ValidarValoresSeguridadRequest.
     * 
     * @param track2
     */
    public void setTrack2(java.lang.String track2) {
        this.track2 = track2;
    }


    /**
     * Gets the valorCVV value for this ValidarValoresSeguridadRequest.
     * 
     * @return valorCVV
     */
    public java.lang.String getValorCVV() {
        return valorCVV;
    }


    /**
     * Sets the valorCVV value for this ValidarValoresSeguridadRequest.
     * 
     * @param valorCVV
     */
    public void setValorCVV(java.lang.String valorCVV) {
        this.valorCVV = valorCVV;
    }


    /**
     * Gets the valorCVV2 value for this ValidarValoresSeguridadRequest.
     * 
     * @return valorCVV2
     */
    public java.lang.String getValorCVV2() {
        return valorCVV2;
    }


    /**
     * Sets the valorCVV2 value for this ValidarValoresSeguridadRequest.
     * 
     * @param valorCVV2
     */
    public void setValorCVV2(java.lang.String valorCVV2) {
        this.valorCVV2 = valorCVV2;
    }


    /**
     * Gets the valorSeguridadOffset value for this ValidarValoresSeguridadRequest.
     * 
     * @return valorSeguridadOffset
     */
    public java.lang.String getValorSeguridadOffset() {
        return valorSeguridadOffset;
    }


    /**
     * Sets the valorSeguridadOffset value for this ValidarValoresSeguridadRequest.
     * 
     * @param valorSeguridadOffset
     */
    public void setValorSeguridadOffset(java.lang.String valorSeguridadOffset) {
        this.valorSeguridadOffset = valorSeguridadOffset;
    }


    /**
     * Gets the valorSeguridadPVV value for this ValidarValoresSeguridadRequest.
     * 
     * @return valorSeguridadPVV
     */
    public java.lang.String getValorSeguridadPVV() {
        return valorSeguridadPVV;
    }


    /**
     * Sets the valorSeguridadPVV value for this ValidarValoresSeguridadRequest.
     * 
     * @param valorSeguridadPVV
     */
    public void setValorSeguridadPVV(java.lang.String valorSeguridadPVV) {
        this.valorSeguridadPVV = valorSeguridadPVV;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ValidarValoresSeguridadRequest)) return false;
        ValidarValoresSeguridadRequest other = (ValidarValoresSeguridadRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.binMedioAutenticacion==null && other.getBinMedioAutenticacion()==null) || 
             (this.binMedioAutenticacion!=null &&
              this.binMedioAutenticacion.equals(other.getBinMedioAutenticacion()))) &&
            ((this.codigoMedioAutenticacion==null && other.getCodigoMedioAutenticacion()==null) || 
             (this.codigoMedioAutenticacion!=null &&
              this.codigoMedioAutenticacion.equals(other.getCodigoMedioAutenticacion()))) &&
            ((this.fechaExpiracion==null && other.getFechaExpiracion()==null) || 
             (this.fechaExpiracion!=null &&
              this.fechaExpiracion.equals(other.getFechaExpiracion()))) &&
            ((this.flagValidacionCVV==null && other.getFlagValidacionCVV()==null) || 
             (this.flagValidacionCVV!=null &&
              this.flagValidacionCVV.equals(other.getFlagValidacionCVV()))) &&
            ((this.flagValidacionCVV2==null && other.getFlagValidacionCVV2()==null) || 
             (this.flagValidacionCVV2!=null &&
              this.flagValidacionCVV2.equals(other.getFlagValidacionCVV2()))) &&
            ((this.flagValidacionOffset==null && other.getFlagValidacionOffset()==null) || 
             (this.flagValidacionOffset!=null &&
              this.flagValidacionOffset.equals(other.getFlagValidacionOffset()))) &&
            ((this.flagValidacionPVV==null && other.getFlagValidacionPVV()==null) || 
             (this.flagValidacionPVV!=null &&
              this.flagValidacionPVV.equals(other.getFlagValidacionPVV()))) &&
            ((this.flagValidacionPassword==null && other.getFlagValidacionPassword()==null) || 
             (this.flagValidacionPassword!=null &&
              this.flagValidacionPassword.equals(other.getFlagValidacionPassword()))) &&
            ((this.hashcodePassword==null && other.getHashcodePassword()==null) || 
             (this.hashcodePassword!=null &&
              this.hashcodePassword.equals(other.getHashcodePassword()))) &&
            ((this.identificadorOperacion==null && other.getIdentificadorOperacion()==null) || 
             (this.identificadorOperacion!=null &&
              this.identificadorOperacion.equals(other.getIdentificadorOperacion()))) &&
            ((this.indicadorLecturaPAN==null && other.getIndicadorLecturaPAN()==null) || 
             (this.indicadorLecturaPAN!=null &&
              this.indicadorLecturaPAN.equals(other.getIndicadorLecturaPAN()))) &&
            ((this.indicadorUbicacionOffset==null && other.getIndicadorUbicacionOffset()==null) || 
             (this.indicadorUbicacionOffset!=null &&
              this.indicadorUbicacionOffset.equals(other.getIndicadorUbicacionOffset()))) &&
            ((this.indicadorUbicacionPVV==null && other.getIndicadorUbicacionPVV()==null) || 
             (this.indicadorUbicacionPVV!=null &&
              this.indicadorUbicacionPVV.equals(other.getIndicadorUbicacionPVV()))) &&
            ((this.pinblockOffset==null && other.getPinblockOffset()==null) || 
             (this.pinblockOffset!=null &&
              this.pinblockOffset.equals(other.getPinblockOffset()))) &&
            ((this.pinblockPVV==null && other.getPinblockPVV()==null) || 
             (this.pinblockPVV!=null &&
              this.pinblockPVV.equals(other.getPinblockPVV()))) &&
            ((this.pvkiOffset==null && other.getPvkiOffset()==null) || 
             (this.pvkiOffset!=null &&
              this.pvkiOffset.equals(other.getPvkiOffset()))) &&
            ((this.pvkiPVV==null && other.getPvkiPVV()==null) || 
             (this.pvkiPVV!=null &&
              this.pvkiPVV.equals(other.getPvkiPVV()))) &&
            ((this.tipoElementoSeguridadCVV==null && other.getTipoElementoSeguridadCVV()==null) || 
             (this.tipoElementoSeguridadCVV!=null &&
              this.tipoElementoSeguridadCVV.equals(other.getTipoElementoSeguridadCVV()))) &&
            ((this.tipoElementoSeguridadCVV2==null && other.getTipoElementoSeguridadCVV2()==null) || 
             (this.tipoElementoSeguridadCVV2!=null &&
              this.tipoElementoSeguridadCVV2.equals(other.getTipoElementoSeguridadCVV2()))) &&
            ((this.tipoElementoSeguridadOffset==null && other.getTipoElementoSeguridadOffset()==null) || 
             (this.tipoElementoSeguridadOffset!=null &&
              this.tipoElementoSeguridadOffset.equals(other.getTipoElementoSeguridadOffset()))) &&
            ((this.tipoElementoSeguridadPVV==null && other.getTipoElementoSeguridadPVV()==null) || 
             (this.tipoElementoSeguridadPVV!=null &&
              this.tipoElementoSeguridadPVV.equals(other.getTipoElementoSeguridadPVV()))) &&
            ((this.tipoElementoSeguridadPassword==null && other.getTipoElementoSeguridadPassword()==null) || 
             (this.tipoElementoSeguridadPassword!=null &&
              this.tipoElementoSeguridadPassword.equals(other.getTipoElementoSeguridadPassword()))) &&
            ((this.tipoValidacion==null && other.getTipoValidacion()==null) || 
             (this.tipoValidacion!=null &&
              this.tipoValidacion.equals(other.getTipoValidacion()))) &&
            ((this.track2==null && other.getTrack2()==null) || 
             (this.track2!=null &&
              this.track2.equals(other.getTrack2()))) &&
            ((this.valorCVV==null && other.getValorCVV()==null) || 
             (this.valorCVV!=null &&
              this.valorCVV.equals(other.getValorCVV()))) &&
            ((this.valorCVV2==null && other.getValorCVV2()==null) || 
             (this.valorCVV2!=null &&
              this.valorCVV2.equals(other.getValorCVV2()))) &&
            ((this.valorSeguridadOffset==null && other.getValorSeguridadOffset()==null) || 
             (this.valorSeguridadOffset!=null &&
              this.valorSeguridadOffset.equals(other.getValorSeguridadOffset()))) &&
            ((this.valorSeguridadPVV==null && other.getValorSeguridadPVV()==null) || 
             (this.valorSeguridadPVV!=null &&
              this.valorSeguridadPVV.equals(other.getValorSeguridadPVV())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getBinMedioAutenticacion() != null) {
            _hashCode += getBinMedioAutenticacion().hashCode();
        }
        if (getCodigoMedioAutenticacion() != null) {
            _hashCode += getCodigoMedioAutenticacion().hashCode();
        }
        if (getFechaExpiracion() != null) {
            _hashCode += getFechaExpiracion().hashCode();
        }
        if (getFlagValidacionCVV() != null) {
            _hashCode += getFlagValidacionCVV().hashCode();
        }
        if (getFlagValidacionCVV2() != null) {
            _hashCode += getFlagValidacionCVV2().hashCode();
        }
        if (getFlagValidacionOffset() != null) {
            _hashCode += getFlagValidacionOffset().hashCode();
        }
        if (getFlagValidacionPVV() != null) {
            _hashCode += getFlagValidacionPVV().hashCode();
        }
        if (getFlagValidacionPassword() != null) {
            _hashCode += getFlagValidacionPassword().hashCode();
        }
        if (getHashcodePassword() != null) {
            _hashCode += getHashcodePassword().hashCode();
        }
        if (getIdentificadorOperacion() != null) {
            _hashCode += getIdentificadorOperacion().hashCode();
        }
        if (getIndicadorLecturaPAN() != null) {
            _hashCode += getIndicadorLecturaPAN().hashCode();
        }
        if (getIndicadorUbicacionOffset() != null) {
            _hashCode += getIndicadorUbicacionOffset().hashCode();
        }
        if (getIndicadorUbicacionPVV() != null) {
            _hashCode += getIndicadorUbicacionPVV().hashCode();
        }
        if (getPinblockOffset() != null) {
            _hashCode += getPinblockOffset().hashCode();
        }
        if (getPinblockPVV() != null) {
            _hashCode += getPinblockPVV().hashCode();
        }
        if (getPvkiOffset() != null) {
            _hashCode += getPvkiOffset().hashCode();
        }
        if (getPvkiPVV() != null) {
            _hashCode += getPvkiPVV().hashCode();
        }
        if (getTipoElementoSeguridadCVV() != null) {
            _hashCode += getTipoElementoSeguridadCVV().hashCode();
        }
        if (getTipoElementoSeguridadCVV2() != null) {
            _hashCode += getTipoElementoSeguridadCVV2().hashCode();
        }
        if (getTipoElementoSeguridadOffset() != null) {
            _hashCode += getTipoElementoSeguridadOffset().hashCode();
        }
        if (getTipoElementoSeguridadPVV() != null) {
            _hashCode += getTipoElementoSeguridadPVV().hashCode();
        }
        if (getTipoElementoSeguridadPassword() != null) {
            _hashCode += getTipoElementoSeguridadPassword().hashCode();
        }
        if (getTipoValidacion() != null) {
            _hashCode += getTipoValidacion().hashCode();
        }
        if (getTrack2() != null) {
            _hashCode += getTrack2().hashCode();
        }
        if (getValorCVV() != null) {
            _hashCode += getValorCVV().hashCode();
        }
        if (getValorCVV2() != null) {
            _hashCode += getValorCVV2().hashCode();
        }
        if (getValorSeguridadOffset() != null) {
            _hashCode += getValorSeguridadOffset().hashCode();
        }
        if (getValorSeguridadPVV() != null) {
            _hashCode += getValorSeguridadPVV().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ValidarValoresSeguridadRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "validarValoresSeguridadRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("binMedioAutenticacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "binMedioAutenticacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoMedioAutenticacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoMedioAutenticacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaExpiracion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaExpiracion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("flagValidacionCVV");
        elemField.setXmlName(new javax.xml.namespace.QName("", "flagValidacionCVV"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("flagValidacionCVV2");
        elemField.setXmlName(new javax.xml.namespace.QName("", "flagValidacionCVV2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("flagValidacionOffset");
        elemField.setXmlName(new javax.xml.namespace.QName("", "flagValidacionOffset"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("flagValidacionPVV");
        elemField.setXmlName(new javax.xml.namespace.QName("", "flagValidacionPVV"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("flagValidacionPassword");
        elemField.setXmlName(new javax.xml.namespace.QName("", "flagValidacionPassword"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hashcodePassword");
        elemField.setXmlName(new javax.xml.namespace.QName("", "hashcodePassword"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificadorOperacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "identificadorOperacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indicadorLecturaPAN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "indicadorLecturaPAN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indicadorUbicacionOffset");
        elemField.setXmlName(new javax.xml.namespace.QName("", "indicadorUbicacionOffset"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indicadorUbicacionPVV");
        elemField.setXmlName(new javax.xml.namespace.QName("", "indicadorUbicacionPVV"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pinblockOffset");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pinblockOffset"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pinblockPVV");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pinblockPVV"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pvkiOffset");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pvkiOffset"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pvkiPVV");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pvkiPVV"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoElementoSeguridadCVV");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoElementoSeguridadCVV"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoElementoSeguridadCVV2");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoElementoSeguridadCVV2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoElementoSeguridadOffset");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoElementoSeguridadOffset"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoElementoSeguridadPVV");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoElementoSeguridadPVV"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoElementoSeguridadPassword");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoElementoSeguridadPassword"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoValidacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoValidacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("track2");
        elemField.setXmlName(new javax.xml.namespace.QName("", "track2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorCVV");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorCVV"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorCVV2");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorCVV2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorSeguridadOffset");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorSeguridadOffset"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorSeguridadPVV");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorSeguridadPVV"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
