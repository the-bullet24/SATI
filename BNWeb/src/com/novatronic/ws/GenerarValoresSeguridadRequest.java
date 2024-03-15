/**
 * GenerarValoresSeguridadRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.novatronic.ws;

public class GenerarValoresSeguridadRequest  extends com.novatronic.ws.BaseRequest  implements java.io.Serializable {
    private java.lang.String binMedioAutenticacion;

    private java.lang.String codigoMedioAutenticacion;

    private java.lang.String codigoServicioCVV;

    private java.lang.String codigoServicioCVV2;

    private java.lang.Integer fechaExpiracion;

    private java.lang.Integer flagGeneracionAlmacenamientoCVV;

    private java.lang.Integer flagGeneracionAlmacenamientoCVV2;

    private java.lang.Integer flagGeneracionAlmacenamientoOffsetImpresionPIN;

    private java.lang.Integer flagGeneracionAlmacenamientoPPVImpresionPIN;

    private java.lang.Integer flagGeneracionAlmacenamientoPassword;

    private java.lang.String hashcodePassword;

    private java.lang.String nombreApellidoTarjetaHabiente;

    private java.lang.String pinblockOffset;

    private java.lang.String pinblockPVV;

    private java.lang.Integer tipoElementoSeguridadCVV;

    private java.lang.Integer tipoElementoSeguridadCVV2;

    private java.lang.Integer tipoElementoSeguridadOffset;

    private java.lang.Integer tipoElementoSeguridadPVV;

    private java.lang.Integer tipoElementoSeguridadPassword;

    public GenerarValoresSeguridadRequest() {
    }

    public GenerarValoresSeguridadRequest(
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
           java.lang.String codigoServicioCVV,
           java.lang.String codigoServicioCVV2,
           java.lang.Integer fechaExpiracion,
           java.lang.Integer flagGeneracionAlmacenamientoCVV,
           java.lang.Integer flagGeneracionAlmacenamientoCVV2,
           java.lang.Integer flagGeneracionAlmacenamientoOffsetImpresionPIN,
           java.lang.Integer flagGeneracionAlmacenamientoPPVImpresionPIN,
           java.lang.Integer flagGeneracionAlmacenamientoPassword,
           java.lang.String hashcodePassword,
           java.lang.String nombreApellidoTarjetaHabiente,
           java.lang.String pinblockOffset,
           java.lang.String pinblockPVV,
           java.lang.Integer tipoElementoSeguridadCVV,
           java.lang.Integer tipoElementoSeguridadCVV2,
           java.lang.Integer tipoElementoSeguridadOffset,
           java.lang.Integer tipoElementoSeguridadPVV,
           java.lang.Integer tipoElementoSeguridadPassword) {
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
        this.codigoServicioCVV = codigoServicioCVV;
        this.codigoServicioCVV2 = codigoServicioCVV2;
        this.fechaExpiracion = fechaExpiracion;
        this.flagGeneracionAlmacenamientoCVV = flagGeneracionAlmacenamientoCVV;
        this.flagGeneracionAlmacenamientoCVV2 = flagGeneracionAlmacenamientoCVV2;
        this.flagGeneracionAlmacenamientoOffsetImpresionPIN = flagGeneracionAlmacenamientoOffsetImpresionPIN;
        this.flagGeneracionAlmacenamientoPPVImpresionPIN = flagGeneracionAlmacenamientoPPVImpresionPIN;
        this.flagGeneracionAlmacenamientoPassword = flagGeneracionAlmacenamientoPassword;
        this.hashcodePassword = hashcodePassword;
        this.nombreApellidoTarjetaHabiente = nombreApellidoTarjetaHabiente;
        this.pinblockOffset = pinblockOffset;
        this.pinblockPVV = pinblockPVV;
        this.tipoElementoSeguridadCVV = tipoElementoSeguridadCVV;
        this.tipoElementoSeguridadCVV2 = tipoElementoSeguridadCVV2;
        this.tipoElementoSeguridadOffset = tipoElementoSeguridadOffset;
        this.tipoElementoSeguridadPVV = tipoElementoSeguridadPVV;
        this.tipoElementoSeguridadPassword = tipoElementoSeguridadPassword;
    }


    /**
     * Gets the binMedioAutenticacion value for this GenerarValoresSeguridadRequest.
     * 
     * @return binMedioAutenticacion
     */
    public java.lang.String getBinMedioAutenticacion() {
        return binMedioAutenticacion;
    }


    /**
     * Sets the binMedioAutenticacion value for this GenerarValoresSeguridadRequest.
     * 
     * @param binMedioAutenticacion
     */
    public void setBinMedioAutenticacion(java.lang.String binMedioAutenticacion) {
        this.binMedioAutenticacion = binMedioAutenticacion;
    }


    /**
     * Gets the codigoMedioAutenticacion value for this GenerarValoresSeguridadRequest.
     * 
     * @return codigoMedioAutenticacion
     */
    public java.lang.String getCodigoMedioAutenticacion() {
        return codigoMedioAutenticacion;
    }


    /**
     * Sets the codigoMedioAutenticacion value for this GenerarValoresSeguridadRequest.
     * 
     * @param codigoMedioAutenticacion
     */
    public void setCodigoMedioAutenticacion(java.lang.String codigoMedioAutenticacion) {
        this.codigoMedioAutenticacion = codigoMedioAutenticacion;
    }


    /**
     * Gets the codigoServicioCVV value for this GenerarValoresSeguridadRequest.
     * 
     * @return codigoServicioCVV
     */
    public java.lang.String getCodigoServicioCVV() {
        return codigoServicioCVV;
    }


    /**
     * Sets the codigoServicioCVV value for this GenerarValoresSeguridadRequest.
     * 
     * @param codigoServicioCVV
     */
    public void setCodigoServicioCVV(java.lang.String codigoServicioCVV) {
        this.codigoServicioCVV = codigoServicioCVV;
    }


    /**
     * Gets the codigoServicioCVV2 value for this GenerarValoresSeguridadRequest.
     * 
     * @return codigoServicioCVV2
     */
    public java.lang.String getCodigoServicioCVV2() {
        return codigoServicioCVV2;
    }


    /**
     * Sets the codigoServicioCVV2 value for this GenerarValoresSeguridadRequest.
     * 
     * @param codigoServicioCVV2
     */
    public void setCodigoServicioCVV2(java.lang.String codigoServicioCVV2) {
        this.codigoServicioCVV2 = codigoServicioCVV2;
    }


    /**
     * Gets the fechaExpiracion value for this GenerarValoresSeguridadRequest.
     * 
     * @return fechaExpiracion
     */
    public java.lang.Integer getFechaExpiracion() {
        return fechaExpiracion;
    }


    /**
     * Sets the fechaExpiracion value for this GenerarValoresSeguridadRequest.
     * 
     * @param fechaExpiracion
     */
    public void setFechaExpiracion(java.lang.Integer fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }


    /**
     * Gets the flagGeneracionAlmacenamientoCVV value for this GenerarValoresSeguridadRequest.
     * 
     * @return flagGeneracionAlmacenamientoCVV
     */
    public java.lang.Integer getFlagGeneracionAlmacenamientoCVV() {
        return flagGeneracionAlmacenamientoCVV;
    }


    /**
     * Sets the flagGeneracionAlmacenamientoCVV value for this GenerarValoresSeguridadRequest.
     * 
     * @param flagGeneracionAlmacenamientoCVV
     */
    public void setFlagGeneracionAlmacenamientoCVV(java.lang.Integer flagGeneracionAlmacenamientoCVV) {
        this.flagGeneracionAlmacenamientoCVV = flagGeneracionAlmacenamientoCVV;
    }


    /**
     * Gets the flagGeneracionAlmacenamientoCVV2 value for this GenerarValoresSeguridadRequest.
     * 
     * @return flagGeneracionAlmacenamientoCVV2
     */
    public java.lang.Integer getFlagGeneracionAlmacenamientoCVV2() {
        return flagGeneracionAlmacenamientoCVV2;
    }


    /**
     * Sets the flagGeneracionAlmacenamientoCVV2 value for this GenerarValoresSeguridadRequest.
     * 
     * @param flagGeneracionAlmacenamientoCVV2
     */
    public void setFlagGeneracionAlmacenamientoCVV2(java.lang.Integer flagGeneracionAlmacenamientoCVV2) {
        this.flagGeneracionAlmacenamientoCVV2 = flagGeneracionAlmacenamientoCVV2;
    }


    /**
     * Gets the flagGeneracionAlmacenamientoOffsetImpresionPIN value for this GenerarValoresSeguridadRequest.
     * 
     * @return flagGeneracionAlmacenamientoOffsetImpresionPIN
     */
    public java.lang.Integer getFlagGeneracionAlmacenamientoOffsetImpresionPIN() {
        return flagGeneracionAlmacenamientoOffsetImpresionPIN;
    }


    /**
     * Sets the flagGeneracionAlmacenamientoOffsetImpresionPIN value for this GenerarValoresSeguridadRequest.
     * 
     * @param flagGeneracionAlmacenamientoOffsetImpresionPIN
     */
    public void setFlagGeneracionAlmacenamientoOffsetImpresionPIN(java.lang.Integer flagGeneracionAlmacenamientoOffsetImpresionPIN) {
        this.flagGeneracionAlmacenamientoOffsetImpresionPIN = flagGeneracionAlmacenamientoOffsetImpresionPIN;
    }


    /**
     * Gets the flagGeneracionAlmacenamientoPPVImpresionPIN value for this GenerarValoresSeguridadRequest.
     * 
     * @return flagGeneracionAlmacenamientoPPVImpresionPIN
     */
    public java.lang.Integer getFlagGeneracionAlmacenamientoPPVImpresionPIN() {
        return flagGeneracionAlmacenamientoPPVImpresionPIN;
    }


    /**
     * Sets the flagGeneracionAlmacenamientoPPVImpresionPIN value for this GenerarValoresSeguridadRequest.
     * 
     * @param flagGeneracionAlmacenamientoPPVImpresionPIN
     */
    public void setFlagGeneracionAlmacenamientoPPVImpresionPIN(java.lang.Integer flagGeneracionAlmacenamientoPPVImpresionPIN) {
        this.flagGeneracionAlmacenamientoPPVImpresionPIN = flagGeneracionAlmacenamientoPPVImpresionPIN;
    }


    /**
     * Gets the flagGeneracionAlmacenamientoPassword value for this GenerarValoresSeguridadRequest.
     * 
     * @return flagGeneracionAlmacenamientoPassword
     */
    public java.lang.Integer getFlagGeneracionAlmacenamientoPassword() {
        return flagGeneracionAlmacenamientoPassword;
    }


    /**
     * Sets the flagGeneracionAlmacenamientoPassword value for this GenerarValoresSeguridadRequest.
     * 
     * @param flagGeneracionAlmacenamientoPassword
     */
    public void setFlagGeneracionAlmacenamientoPassword(java.lang.Integer flagGeneracionAlmacenamientoPassword) {
        this.flagGeneracionAlmacenamientoPassword = flagGeneracionAlmacenamientoPassword;
    }


    /**
     * Gets the hashcodePassword value for this GenerarValoresSeguridadRequest.
     * 
     * @return hashcodePassword
     */
    public java.lang.String getHashcodePassword() {
        return hashcodePassword;
    }


    /**
     * Sets the hashcodePassword value for this GenerarValoresSeguridadRequest.
     * 
     * @param hashcodePassword
     */
    public void setHashcodePassword(java.lang.String hashcodePassword) {
        this.hashcodePassword = hashcodePassword;
    }


    /**
     * Gets the nombreApellidoTarjetaHabiente value for this GenerarValoresSeguridadRequest.
     * 
     * @return nombreApellidoTarjetaHabiente
     */
    public java.lang.String getNombreApellidoTarjetaHabiente() {
        return nombreApellidoTarjetaHabiente;
    }


    /**
     * Sets the nombreApellidoTarjetaHabiente value for this GenerarValoresSeguridadRequest.
     * 
     * @param nombreApellidoTarjetaHabiente
     */
    public void setNombreApellidoTarjetaHabiente(java.lang.String nombreApellidoTarjetaHabiente) {
        this.nombreApellidoTarjetaHabiente = nombreApellidoTarjetaHabiente;
    }


    /**
     * Gets the pinblockOffset value for this GenerarValoresSeguridadRequest.
     * 
     * @return pinblockOffset
     */
    public java.lang.String getPinblockOffset() {
        return pinblockOffset;
    }


    /**
     * Sets the pinblockOffset value for this GenerarValoresSeguridadRequest.
     * 
     * @param pinblockOffset
     */
    public void setPinblockOffset(java.lang.String pinblockOffset) {
        this.pinblockOffset = pinblockOffset;
    }


    /**
     * Gets the pinblockPVV value for this GenerarValoresSeguridadRequest.
     * 
     * @return pinblockPVV
     */
    public java.lang.String getPinblockPVV() {
        return pinblockPVV;
    }


    /**
     * Sets the pinblockPVV value for this GenerarValoresSeguridadRequest.
     * 
     * @param pinblockPVV
     */
    public void setPinblockPVV(java.lang.String pinblockPVV) {
        this.pinblockPVV = pinblockPVV;
    }


    /**
     * Gets the tipoElementoSeguridadCVV value for this GenerarValoresSeguridadRequest.
     * 
     * @return tipoElementoSeguridadCVV
     */
    public java.lang.Integer getTipoElementoSeguridadCVV() {
        return tipoElementoSeguridadCVV;
    }


    /**
     * Sets the tipoElementoSeguridadCVV value for this GenerarValoresSeguridadRequest.
     * 
     * @param tipoElementoSeguridadCVV
     */
    public void setTipoElementoSeguridadCVV(java.lang.Integer tipoElementoSeguridadCVV) {
        this.tipoElementoSeguridadCVV = tipoElementoSeguridadCVV;
    }


    /**
     * Gets the tipoElementoSeguridadCVV2 value for this GenerarValoresSeguridadRequest.
     * 
     * @return tipoElementoSeguridadCVV2
     */
    public java.lang.Integer getTipoElementoSeguridadCVV2() {
        return tipoElementoSeguridadCVV2;
    }


    /**
     * Sets the tipoElementoSeguridadCVV2 value for this GenerarValoresSeguridadRequest.
     * 
     * @param tipoElementoSeguridadCVV2
     */
    public void setTipoElementoSeguridadCVV2(java.lang.Integer tipoElementoSeguridadCVV2) {
        this.tipoElementoSeguridadCVV2 = tipoElementoSeguridadCVV2;
    }


    /**
     * Gets the tipoElementoSeguridadOffset value for this GenerarValoresSeguridadRequest.
     * 
     * @return tipoElementoSeguridadOffset
     */
    public java.lang.Integer getTipoElementoSeguridadOffset() {
        return tipoElementoSeguridadOffset;
    }


    /**
     * Sets the tipoElementoSeguridadOffset value for this GenerarValoresSeguridadRequest.
     * 
     * @param tipoElementoSeguridadOffset
     */
    public void setTipoElementoSeguridadOffset(java.lang.Integer tipoElementoSeguridadOffset) {
        this.tipoElementoSeguridadOffset = tipoElementoSeguridadOffset;
    }


    /**
     * Gets the tipoElementoSeguridadPVV value for this GenerarValoresSeguridadRequest.
     * 
     * @return tipoElementoSeguridadPVV
     */
    public java.lang.Integer getTipoElementoSeguridadPVV() {
        return tipoElementoSeguridadPVV;
    }


    /**
     * Sets the tipoElementoSeguridadPVV value for this GenerarValoresSeguridadRequest.
     * 
     * @param tipoElementoSeguridadPVV
     */
    public void setTipoElementoSeguridadPVV(java.lang.Integer tipoElementoSeguridadPVV) {
        this.tipoElementoSeguridadPVV = tipoElementoSeguridadPVV;
    }


    /**
     * Gets the tipoElementoSeguridadPassword value for this GenerarValoresSeguridadRequest.
     * 
     * @return tipoElementoSeguridadPassword
     */
    public java.lang.Integer getTipoElementoSeguridadPassword() {
        return tipoElementoSeguridadPassword;
    }


    /**
     * Sets the tipoElementoSeguridadPassword value for this GenerarValoresSeguridadRequest.
     * 
     * @param tipoElementoSeguridadPassword
     */
    public void setTipoElementoSeguridadPassword(java.lang.Integer tipoElementoSeguridadPassword) {
        this.tipoElementoSeguridadPassword = tipoElementoSeguridadPassword;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GenerarValoresSeguridadRequest)) return false;
        GenerarValoresSeguridadRequest other = (GenerarValoresSeguridadRequest) obj;
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
            ((this.codigoServicioCVV==null && other.getCodigoServicioCVV()==null) || 
             (this.codigoServicioCVV!=null &&
              this.codigoServicioCVV.equals(other.getCodigoServicioCVV()))) &&
            ((this.codigoServicioCVV2==null && other.getCodigoServicioCVV2()==null) || 
             (this.codigoServicioCVV2!=null &&
              this.codigoServicioCVV2.equals(other.getCodigoServicioCVV2()))) &&
            ((this.fechaExpiracion==null && other.getFechaExpiracion()==null) || 
             (this.fechaExpiracion!=null &&
              this.fechaExpiracion.equals(other.getFechaExpiracion()))) &&
            ((this.flagGeneracionAlmacenamientoCVV==null && other.getFlagGeneracionAlmacenamientoCVV()==null) || 
             (this.flagGeneracionAlmacenamientoCVV!=null &&
              this.flagGeneracionAlmacenamientoCVV.equals(other.getFlagGeneracionAlmacenamientoCVV()))) &&
            ((this.flagGeneracionAlmacenamientoCVV2==null && other.getFlagGeneracionAlmacenamientoCVV2()==null) || 
             (this.flagGeneracionAlmacenamientoCVV2!=null &&
              this.flagGeneracionAlmacenamientoCVV2.equals(other.getFlagGeneracionAlmacenamientoCVV2()))) &&
            ((this.flagGeneracionAlmacenamientoOffsetImpresionPIN==null && other.getFlagGeneracionAlmacenamientoOffsetImpresionPIN()==null) || 
             (this.flagGeneracionAlmacenamientoOffsetImpresionPIN!=null &&
              this.flagGeneracionAlmacenamientoOffsetImpresionPIN.equals(other.getFlagGeneracionAlmacenamientoOffsetImpresionPIN()))) &&
            ((this.flagGeneracionAlmacenamientoPPVImpresionPIN==null && other.getFlagGeneracionAlmacenamientoPPVImpresionPIN()==null) || 
             (this.flagGeneracionAlmacenamientoPPVImpresionPIN!=null &&
              this.flagGeneracionAlmacenamientoPPVImpresionPIN.equals(other.getFlagGeneracionAlmacenamientoPPVImpresionPIN()))) &&
            ((this.flagGeneracionAlmacenamientoPassword==null && other.getFlagGeneracionAlmacenamientoPassword()==null) || 
             (this.flagGeneracionAlmacenamientoPassword!=null &&
              this.flagGeneracionAlmacenamientoPassword.equals(other.getFlagGeneracionAlmacenamientoPassword()))) &&
            ((this.hashcodePassword==null && other.getHashcodePassword()==null) || 
             (this.hashcodePassword!=null &&
              this.hashcodePassword.equals(other.getHashcodePassword()))) &&
            ((this.nombreApellidoTarjetaHabiente==null && other.getNombreApellidoTarjetaHabiente()==null) || 
             (this.nombreApellidoTarjetaHabiente!=null &&
              this.nombreApellidoTarjetaHabiente.equals(other.getNombreApellidoTarjetaHabiente()))) &&
            ((this.pinblockOffset==null && other.getPinblockOffset()==null) || 
             (this.pinblockOffset!=null &&
              this.pinblockOffset.equals(other.getPinblockOffset()))) &&
            ((this.pinblockPVV==null && other.getPinblockPVV()==null) || 
             (this.pinblockPVV!=null &&
              this.pinblockPVV.equals(other.getPinblockPVV()))) &&
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
              this.tipoElementoSeguridadPassword.equals(other.getTipoElementoSeguridadPassword())));
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
        if (getCodigoServicioCVV() != null) {
            _hashCode += getCodigoServicioCVV().hashCode();
        }
        if (getCodigoServicioCVV2() != null) {
            _hashCode += getCodigoServicioCVV2().hashCode();
        }
        if (getFechaExpiracion() != null) {
            _hashCode += getFechaExpiracion().hashCode();
        }
        if (getFlagGeneracionAlmacenamientoCVV() != null) {
            _hashCode += getFlagGeneracionAlmacenamientoCVV().hashCode();
        }
        if (getFlagGeneracionAlmacenamientoCVV2() != null) {
            _hashCode += getFlagGeneracionAlmacenamientoCVV2().hashCode();
        }
        if (getFlagGeneracionAlmacenamientoOffsetImpresionPIN() != null) {
            _hashCode += getFlagGeneracionAlmacenamientoOffsetImpresionPIN().hashCode();
        }
        if (getFlagGeneracionAlmacenamientoPPVImpresionPIN() != null) {
            _hashCode += getFlagGeneracionAlmacenamientoPPVImpresionPIN().hashCode();
        }
        if (getFlagGeneracionAlmacenamientoPassword() != null) {
            _hashCode += getFlagGeneracionAlmacenamientoPassword().hashCode();
        }
        if (getHashcodePassword() != null) {
            _hashCode += getHashcodePassword().hashCode();
        }
        if (getNombreApellidoTarjetaHabiente() != null) {
            _hashCode += getNombreApellidoTarjetaHabiente().hashCode();
        }
        if (getPinblockOffset() != null) {
            _hashCode += getPinblockOffset().hashCode();
        }
        if (getPinblockPVV() != null) {
            _hashCode += getPinblockPVV().hashCode();
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GenerarValoresSeguridadRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "generarValoresSeguridadRequest"));
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
        elemField.setFieldName("codigoServicioCVV");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoServicioCVV"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoServicioCVV2");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoServicioCVV2"));
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
        elemField.setFieldName("flagGeneracionAlmacenamientoCVV");
        elemField.setXmlName(new javax.xml.namespace.QName("", "flagGeneracionAlmacenamientoCVV"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("flagGeneracionAlmacenamientoCVV2");
        elemField.setXmlName(new javax.xml.namespace.QName("", "flagGeneracionAlmacenamientoCVV2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("flagGeneracionAlmacenamientoOffsetImpresionPIN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "flagGeneracionAlmacenamientoOffsetImpresionPIN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("flagGeneracionAlmacenamientoPPVImpresionPIN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "flagGeneracionAlmacenamientoPPVImpresionPIN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("flagGeneracionAlmacenamientoPassword");
        elemField.setXmlName(new javax.xml.namespace.QName("", "flagGeneracionAlmacenamientoPassword"));
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
        elemField.setFieldName("nombreApellidoTarjetaHabiente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nombreApellidoTarjetaHabiente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
