/**
 * CambiarValoresSeguridadRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.novatronic.ws;

public class CambiarValoresSeguridadRequest  extends com.novatronic.ws.BaseRequest  implements java.io.Serializable {
    private java.lang.String binMedioAutenticacion;

    private java.lang.String codigoMedioAutenticacion;

    private java.lang.Integer flagCambioOffset;

    private java.lang.Integer flagCambioPVV;

    private java.lang.Integer flagCambioPassword;

    private java.lang.String hashcodePassword;

    private java.lang.String hashcodePasswordNuevo;

    private java.lang.Integer indicadorLecturaPAN;

    private java.lang.Integer indicadorUbicacionOffset;

    private java.lang.Integer indicadorUbicacionPVV;

    private java.lang.String pinblockOffset;

    private java.lang.String pinblockOffsetNuevo;

    private java.lang.String pinblockPVV;

    private java.lang.String pinblockPVVNuevo;

    private java.lang.Integer pvkiOffset;

    private java.lang.Integer pvkiPVV;

    private java.lang.Integer tipoElementoSeguridadOffset;

    private java.lang.Integer tipoElementoSeguridadPVV;

    private java.lang.Integer tipoElementoSeguridadPassword;

    private java.lang.String tipoValidacion;

    private java.lang.String track2;

    private java.lang.String valorSeguridadOffset;

    private java.lang.String valorSeguridadPVV;

    public CambiarValoresSeguridadRequest() {
    }

    public CambiarValoresSeguridadRequest(
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
           java.lang.Integer flagCambioOffset,
           java.lang.Integer flagCambioPVV,
           java.lang.Integer flagCambioPassword,
           java.lang.String hashcodePassword,
           java.lang.String hashcodePasswordNuevo,
           java.lang.Integer indicadorLecturaPAN,
           java.lang.Integer indicadorUbicacionOffset,
           java.lang.Integer indicadorUbicacionPVV,
           java.lang.String pinblockOffset,
           java.lang.String pinblockOffsetNuevo,
           java.lang.String pinblockPVV,
           java.lang.String pinblockPVVNuevo,
           java.lang.Integer pvkiOffset,
           java.lang.Integer pvkiPVV,
           java.lang.Integer tipoElementoSeguridadOffset,
           java.lang.Integer tipoElementoSeguridadPVV,
           java.lang.Integer tipoElementoSeguridadPassword,
           java.lang.String tipoValidacion,
           java.lang.String track2,
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
        this.flagCambioOffset = flagCambioOffset;
        this.flagCambioPVV = flagCambioPVV;
        this.flagCambioPassword = flagCambioPassword;
        this.hashcodePassword = hashcodePassword;
        this.hashcodePasswordNuevo = hashcodePasswordNuevo;
        this.indicadorLecturaPAN = indicadorLecturaPAN;
        this.indicadorUbicacionOffset = indicadorUbicacionOffset;
        this.indicadorUbicacionPVV = indicadorUbicacionPVV;
        this.pinblockOffset = pinblockOffset;
        this.pinblockOffsetNuevo = pinblockOffsetNuevo;
        this.pinblockPVV = pinblockPVV;
        this.pinblockPVVNuevo = pinblockPVVNuevo;
        this.pvkiOffset = pvkiOffset;
        this.pvkiPVV = pvkiPVV;
        this.tipoElementoSeguridadOffset = tipoElementoSeguridadOffset;
        this.tipoElementoSeguridadPVV = tipoElementoSeguridadPVV;
        this.tipoElementoSeguridadPassword = tipoElementoSeguridadPassword;
        this.tipoValidacion = tipoValidacion;
        this.track2 = track2;
        this.valorSeguridadOffset = valorSeguridadOffset;
        this.valorSeguridadPVV = valorSeguridadPVV;
    }


    /**
     * Gets the binMedioAutenticacion value for this CambiarValoresSeguridadRequest.
     * 
     * @return binMedioAutenticacion
     */
    public java.lang.String getBinMedioAutenticacion() {
        return binMedioAutenticacion;
    }


    /**
     * Sets the binMedioAutenticacion value for this CambiarValoresSeguridadRequest.
     * 
     * @param binMedioAutenticacion
     */
    public void setBinMedioAutenticacion(java.lang.String binMedioAutenticacion) {
        this.binMedioAutenticacion = binMedioAutenticacion;
    }


    /**
     * Gets the codigoMedioAutenticacion value for this CambiarValoresSeguridadRequest.
     * 
     * @return codigoMedioAutenticacion
     */
    public java.lang.String getCodigoMedioAutenticacion() {
        return codigoMedioAutenticacion;
    }


    /**
     * Sets the codigoMedioAutenticacion value for this CambiarValoresSeguridadRequest.
     * 
     * @param codigoMedioAutenticacion
     */
    public void setCodigoMedioAutenticacion(java.lang.String codigoMedioAutenticacion) {
        this.codigoMedioAutenticacion = codigoMedioAutenticacion;
    }


    /**
     * Gets the flagCambioOffset value for this CambiarValoresSeguridadRequest.
     * 
     * @return flagCambioOffset
     */
    public java.lang.Integer getFlagCambioOffset() {
        return flagCambioOffset;
    }


    /**
     * Sets the flagCambioOffset value for this CambiarValoresSeguridadRequest.
     * 
     * @param flagCambioOffset
     */
    public void setFlagCambioOffset(java.lang.Integer flagCambioOffset) {
        this.flagCambioOffset = flagCambioOffset;
    }


    /**
     * Gets the flagCambioPVV value for this CambiarValoresSeguridadRequest.
     * 
     * @return flagCambioPVV
     */
    public java.lang.Integer getFlagCambioPVV() {
        return flagCambioPVV;
    }


    /**
     * Sets the flagCambioPVV value for this CambiarValoresSeguridadRequest.
     * 
     * @param flagCambioPVV
     */
    public void setFlagCambioPVV(java.lang.Integer flagCambioPVV) {
        this.flagCambioPVV = flagCambioPVV;
    }


    /**
     * Gets the flagCambioPassword value for this CambiarValoresSeguridadRequest.
     * 
     * @return flagCambioPassword
     */
    public java.lang.Integer getFlagCambioPassword() {
        return flagCambioPassword;
    }


    /**
     * Sets the flagCambioPassword value for this CambiarValoresSeguridadRequest.
     * 
     * @param flagCambioPassword
     */
    public void setFlagCambioPassword(java.lang.Integer flagCambioPassword) {
        this.flagCambioPassword = flagCambioPassword;
    }


    /**
     * Gets the hashcodePassword value for this CambiarValoresSeguridadRequest.
     * 
     * @return hashcodePassword
     */
    public java.lang.String getHashcodePassword() {
        return hashcodePassword;
    }


    /**
     * Sets the hashcodePassword value for this CambiarValoresSeguridadRequest.
     * 
     * @param hashcodePassword
     */
    public void setHashcodePassword(java.lang.String hashcodePassword) {
        this.hashcodePassword = hashcodePassword;
    }


    /**
     * Gets the hashcodePasswordNuevo value for this CambiarValoresSeguridadRequest.
     * 
     * @return hashcodePasswordNuevo
     */
    public java.lang.String getHashcodePasswordNuevo() {
        return hashcodePasswordNuevo;
    }


    /**
     * Sets the hashcodePasswordNuevo value for this CambiarValoresSeguridadRequest.
     * 
     * @param hashcodePasswordNuevo
     */
    public void setHashcodePasswordNuevo(java.lang.String hashcodePasswordNuevo) {
        this.hashcodePasswordNuevo = hashcodePasswordNuevo;
    }


    /**
     * Gets the indicadorLecturaPAN value for this CambiarValoresSeguridadRequest.
     * 
     * @return indicadorLecturaPAN
     */
    public java.lang.Integer getIndicadorLecturaPAN() {
        return indicadorLecturaPAN;
    }


    /**
     * Sets the indicadorLecturaPAN value for this CambiarValoresSeguridadRequest.
     * 
     * @param indicadorLecturaPAN
     */
    public void setIndicadorLecturaPAN(java.lang.Integer indicadorLecturaPAN) {
        this.indicadorLecturaPAN = indicadorLecturaPAN;
    }


    /**
     * Gets the indicadorUbicacionOffset value for this CambiarValoresSeguridadRequest.
     * 
     * @return indicadorUbicacionOffset
     */
    public java.lang.Integer getIndicadorUbicacionOffset() {
        return indicadorUbicacionOffset;
    }


    /**
     * Sets the indicadorUbicacionOffset value for this CambiarValoresSeguridadRequest.
     * 
     * @param indicadorUbicacionOffset
     */
    public void setIndicadorUbicacionOffset(java.lang.Integer indicadorUbicacionOffset) {
        this.indicadorUbicacionOffset = indicadorUbicacionOffset;
    }


    /**
     * Gets the indicadorUbicacionPVV value for this CambiarValoresSeguridadRequest.
     * 
     * @return indicadorUbicacionPVV
     */
    public java.lang.Integer getIndicadorUbicacionPVV() {
        return indicadorUbicacionPVV;
    }


    /**
     * Sets the indicadorUbicacionPVV value for this CambiarValoresSeguridadRequest.
     * 
     * @param indicadorUbicacionPVV
     */
    public void setIndicadorUbicacionPVV(java.lang.Integer indicadorUbicacionPVV) {
        this.indicadorUbicacionPVV = indicadorUbicacionPVV;
    }


    /**
     * Gets the pinblockOffset value for this CambiarValoresSeguridadRequest.
     * 
     * @return pinblockOffset
     */
    public java.lang.String getPinblockOffset() {
        return pinblockOffset;
    }


    /**
     * Sets the pinblockOffset value for this CambiarValoresSeguridadRequest.
     * 
     * @param pinblockOffset
     */
    public void setPinblockOffset(java.lang.String pinblockOffset) {
        this.pinblockOffset = pinblockOffset;
    }


    /**
     * Gets the pinblockOffsetNuevo value for this CambiarValoresSeguridadRequest.
     * 
     * @return pinblockOffsetNuevo
     */
    public java.lang.String getPinblockOffsetNuevo() {
        return pinblockOffsetNuevo;
    }


    /**
     * Sets the pinblockOffsetNuevo value for this CambiarValoresSeguridadRequest.
     * 
     * @param pinblockOffsetNuevo
     */
    public void setPinblockOffsetNuevo(java.lang.String pinblockOffsetNuevo) {
        this.pinblockOffsetNuevo = pinblockOffsetNuevo;
    }


    /**
     * Gets the pinblockPVV value for this CambiarValoresSeguridadRequest.
     * 
     * @return pinblockPVV
     */
    public java.lang.String getPinblockPVV() {
        return pinblockPVV;
    }


    /**
     * Sets the pinblockPVV value for this CambiarValoresSeguridadRequest.
     * 
     * @param pinblockPVV
     */
    public void setPinblockPVV(java.lang.String pinblockPVV) {
        this.pinblockPVV = pinblockPVV;
    }


    /**
     * Gets the pinblockPVVNuevo value for this CambiarValoresSeguridadRequest.
     * 
     * @return pinblockPVVNuevo
     */
    public java.lang.String getPinblockPVVNuevo() {
        return pinblockPVVNuevo;
    }


    /**
     * Sets the pinblockPVVNuevo value for this CambiarValoresSeguridadRequest.
     * 
     * @param pinblockPVVNuevo
     */
    public void setPinblockPVVNuevo(java.lang.String pinblockPVVNuevo) {
        this.pinblockPVVNuevo = pinblockPVVNuevo;
    }


    /**
     * Gets the pvkiOffset value for this CambiarValoresSeguridadRequest.
     * 
     * @return pvkiOffset
     */
    public java.lang.Integer getPvkiOffset() {
        return pvkiOffset;
    }


    /**
     * Sets the pvkiOffset value for this CambiarValoresSeguridadRequest.
     * 
     * @param pvkiOffset
     */
    public void setPvkiOffset(java.lang.Integer pvkiOffset) {
        this.pvkiOffset = pvkiOffset;
    }


    /**
     * Gets the pvkiPVV value for this CambiarValoresSeguridadRequest.
     * 
     * @return pvkiPVV
     */
    public java.lang.Integer getPvkiPVV() {
        return pvkiPVV;
    }


    /**
     * Sets the pvkiPVV value for this CambiarValoresSeguridadRequest.
     * 
     * @param pvkiPVV
     */
    public void setPvkiPVV(java.lang.Integer pvkiPVV) {
        this.pvkiPVV = pvkiPVV;
    }


    /**
     * Gets the tipoElementoSeguridadOffset value for this CambiarValoresSeguridadRequest.
     * 
     * @return tipoElementoSeguridadOffset
     */
    public java.lang.Integer getTipoElementoSeguridadOffset() {
        return tipoElementoSeguridadOffset;
    }


    /**
     * Sets the tipoElementoSeguridadOffset value for this CambiarValoresSeguridadRequest.
     * 
     * @param tipoElementoSeguridadOffset
     */
    public void setTipoElementoSeguridadOffset(java.lang.Integer tipoElementoSeguridadOffset) {
        this.tipoElementoSeguridadOffset = tipoElementoSeguridadOffset;
    }


    /**
     * Gets the tipoElementoSeguridadPVV value for this CambiarValoresSeguridadRequest.
     * 
     * @return tipoElementoSeguridadPVV
     */
    public java.lang.Integer getTipoElementoSeguridadPVV() {
        return tipoElementoSeguridadPVV;
    }


    /**
     * Sets the tipoElementoSeguridadPVV value for this CambiarValoresSeguridadRequest.
     * 
     * @param tipoElementoSeguridadPVV
     */
    public void setTipoElementoSeguridadPVV(java.lang.Integer tipoElementoSeguridadPVV) {
        this.tipoElementoSeguridadPVV = tipoElementoSeguridadPVV;
    }


    /**
     * Gets the tipoElementoSeguridadPassword value for this CambiarValoresSeguridadRequest.
     * 
     * @return tipoElementoSeguridadPassword
     */
    public java.lang.Integer getTipoElementoSeguridadPassword() {
        return tipoElementoSeguridadPassword;
    }


    /**
     * Sets the tipoElementoSeguridadPassword value for this CambiarValoresSeguridadRequest.
     * 
     * @param tipoElementoSeguridadPassword
     */
    public void setTipoElementoSeguridadPassword(java.lang.Integer tipoElementoSeguridadPassword) {
        this.tipoElementoSeguridadPassword = tipoElementoSeguridadPassword;
    }


    /**
     * Gets the tipoValidacion value for this CambiarValoresSeguridadRequest.
     * 
     * @return tipoValidacion
     */
    public java.lang.String getTipoValidacion() {
        return tipoValidacion;
    }


    /**
     * Sets the tipoValidacion value for this CambiarValoresSeguridadRequest.
     * 
     * @param tipoValidacion
     */
    public void setTipoValidacion(java.lang.String tipoValidacion) {
        this.tipoValidacion = tipoValidacion;
    }


    /**
     * Gets the track2 value for this CambiarValoresSeguridadRequest.
     * 
     * @return track2
     */
    public java.lang.String getTrack2() {
        return track2;
    }


    /**
     * Sets the track2 value for this CambiarValoresSeguridadRequest.
     * 
     * @param track2
     */
    public void setTrack2(java.lang.String track2) {
        this.track2 = track2;
    }


    /**
     * Gets the valorSeguridadOffset value for this CambiarValoresSeguridadRequest.
     * 
     * @return valorSeguridadOffset
     */
    public java.lang.String getValorSeguridadOffset() {
        return valorSeguridadOffset;
    }


    /**
     * Sets the valorSeguridadOffset value for this CambiarValoresSeguridadRequest.
     * 
     * @param valorSeguridadOffset
     */
    public void setValorSeguridadOffset(java.lang.String valorSeguridadOffset) {
        this.valorSeguridadOffset = valorSeguridadOffset;
    }


    /**
     * Gets the valorSeguridadPVV value for this CambiarValoresSeguridadRequest.
     * 
     * @return valorSeguridadPVV
     */
    public java.lang.String getValorSeguridadPVV() {
        return valorSeguridadPVV;
    }


    /**
     * Sets the valorSeguridadPVV value for this CambiarValoresSeguridadRequest.
     * 
     * @param valorSeguridadPVV
     */
    public void setValorSeguridadPVV(java.lang.String valorSeguridadPVV) {
        this.valorSeguridadPVV = valorSeguridadPVV;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CambiarValoresSeguridadRequest)) return false;
        CambiarValoresSeguridadRequest other = (CambiarValoresSeguridadRequest) obj;
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
            ((this.flagCambioOffset==null && other.getFlagCambioOffset()==null) || 
             (this.flagCambioOffset!=null &&
              this.flagCambioOffset.equals(other.getFlagCambioOffset()))) &&
            ((this.flagCambioPVV==null && other.getFlagCambioPVV()==null) || 
             (this.flagCambioPVV!=null &&
              this.flagCambioPVV.equals(other.getFlagCambioPVV()))) &&
            ((this.flagCambioPassword==null && other.getFlagCambioPassword()==null) || 
             (this.flagCambioPassword!=null &&
              this.flagCambioPassword.equals(other.getFlagCambioPassword()))) &&
            ((this.hashcodePassword==null && other.getHashcodePassword()==null) || 
             (this.hashcodePassword!=null &&
              this.hashcodePassword.equals(other.getHashcodePassword()))) &&
            ((this.hashcodePasswordNuevo==null && other.getHashcodePasswordNuevo()==null) || 
             (this.hashcodePasswordNuevo!=null &&
              this.hashcodePasswordNuevo.equals(other.getHashcodePasswordNuevo()))) &&
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
            ((this.pinblockOffsetNuevo==null && other.getPinblockOffsetNuevo()==null) || 
             (this.pinblockOffsetNuevo!=null &&
              this.pinblockOffsetNuevo.equals(other.getPinblockOffsetNuevo()))) &&
            ((this.pinblockPVV==null && other.getPinblockPVV()==null) || 
             (this.pinblockPVV!=null &&
              this.pinblockPVV.equals(other.getPinblockPVV()))) &&
            ((this.pinblockPVVNuevo==null && other.getPinblockPVVNuevo()==null) || 
             (this.pinblockPVVNuevo!=null &&
              this.pinblockPVVNuevo.equals(other.getPinblockPVVNuevo()))) &&
            ((this.pvkiOffset==null && other.getPvkiOffset()==null) || 
             (this.pvkiOffset!=null &&
              this.pvkiOffset.equals(other.getPvkiOffset()))) &&
            ((this.pvkiPVV==null && other.getPvkiPVV()==null) || 
             (this.pvkiPVV!=null &&
              this.pvkiPVV.equals(other.getPvkiPVV()))) &&
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
        if (getFlagCambioOffset() != null) {
            _hashCode += getFlagCambioOffset().hashCode();
        }
        if (getFlagCambioPVV() != null) {
            _hashCode += getFlagCambioPVV().hashCode();
        }
        if (getFlagCambioPassword() != null) {
            _hashCode += getFlagCambioPassword().hashCode();
        }
        if (getHashcodePassword() != null) {
            _hashCode += getHashcodePassword().hashCode();
        }
        if (getHashcodePasswordNuevo() != null) {
            _hashCode += getHashcodePasswordNuevo().hashCode();
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
        if (getPinblockOffsetNuevo() != null) {
            _hashCode += getPinblockOffsetNuevo().hashCode();
        }
        if (getPinblockPVV() != null) {
            _hashCode += getPinblockPVV().hashCode();
        }
        if (getPinblockPVVNuevo() != null) {
            _hashCode += getPinblockPVVNuevo().hashCode();
        }
        if (getPvkiOffset() != null) {
            _hashCode += getPvkiOffset().hashCode();
        }
        if (getPvkiPVV() != null) {
            _hashCode += getPvkiPVV().hashCode();
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
        new org.apache.axis.description.TypeDesc(CambiarValoresSeguridadRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "cambiarValoresSeguridadRequest"));
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
        elemField.setFieldName("flagCambioOffset");
        elemField.setXmlName(new javax.xml.namespace.QName("", "flagCambioOffset"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("flagCambioPVV");
        elemField.setXmlName(new javax.xml.namespace.QName("", "flagCambioPVV"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("flagCambioPassword");
        elemField.setXmlName(new javax.xml.namespace.QName("", "flagCambioPassword"));
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
        elemField.setFieldName("hashcodePasswordNuevo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "hashcodePasswordNuevo"));
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
        elemField.setFieldName("pinblockOffsetNuevo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pinblockOffsetNuevo"));
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
        elemField.setFieldName("pinblockPVVNuevo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pinblockPVVNuevo"));
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
