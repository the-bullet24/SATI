/**
 * BaseResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.novatronic.ws;

public abstract class BaseResponse  implements java.io.Serializable {
    private java.lang.Integer tipoFormato;

    private java.lang.String codigoComando;

    private java.lang.String codigoEmpresa;

    private java.lang.String tipoAplicacionOrigen;

    private java.lang.String codigoAplicacionOrigen;

    private java.util.Calendar fechaDate;

    private java.lang.Integer fecha;

    private java.lang.Integer hora;

    private java.lang.String usuario;

    private java.lang.Integer numeroSecuencia;

    private java.lang.String codigoComercio;

    private java.lang.Integer tipoTerminal;

    private java.lang.String numeroTerminal;

    private java.lang.String codigoZonaIntercambio;

    private java.lang.String filler;

    private java.lang.String origen;

    private java.lang.Integer codigoRespuestaPrincipal;

    private java.lang.String mensajeError;

    private java.lang.String codigoRespuestaExtendido;

    public BaseResponse() {
    }

    public BaseResponse(
           java.lang.Integer tipoFormato,
           java.lang.String codigoComando,
           java.lang.String codigoEmpresa,
           java.lang.String tipoAplicacionOrigen,
           java.lang.String codigoAplicacionOrigen,
           java.util.Calendar fechaDate,
           java.lang.Integer fecha,
           java.lang.Integer hora,
           java.lang.String usuario,
           java.lang.Integer numeroSecuencia,
           java.lang.String codigoComercio,
           java.lang.Integer tipoTerminal,
           java.lang.String numeroTerminal,
           java.lang.String codigoZonaIntercambio,
           java.lang.String filler,
           java.lang.String origen,
           java.lang.Integer codigoRespuestaPrincipal,
           java.lang.String mensajeError,
           java.lang.String codigoRespuestaExtendido) {
           this.tipoFormato = tipoFormato;
           this.codigoComando = codigoComando;
           this.codigoEmpresa = codigoEmpresa;
           this.tipoAplicacionOrigen = tipoAplicacionOrigen;
           this.codigoAplicacionOrigen = codigoAplicacionOrigen;
           this.fechaDate = fechaDate;
           this.fecha = fecha;
           this.hora = hora;
           this.usuario = usuario;
           this.numeroSecuencia = numeroSecuencia;
           this.codigoComercio = codigoComercio;
           this.tipoTerminal = tipoTerminal;
           this.numeroTerminal = numeroTerminal;
           this.codigoZonaIntercambio = codigoZonaIntercambio;
           this.filler = filler;
           this.origen = origen;
           this.codigoRespuestaPrincipal = codigoRespuestaPrincipal;
           this.mensajeError = mensajeError;
           this.codigoRespuestaExtendido = codigoRespuestaExtendido;
    }


    /**
     * Gets the tipoFormato value for this BaseResponse.
     * 
     * @return tipoFormato
     */
    public java.lang.Integer getTipoFormato() {
        return tipoFormato;
    }


    /**
     * Sets the tipoFormato value for this BaseResponse.
     * 
     * @param tipoFormato
     */
    public void setTipoFormato(java.lang.Integer tipoFormato) {
        this.tipoFormato = tipoFormato;
    }


    /**
     * Gets the codigoComando value for this BaseResponse.
     * 
     * @return codigoComando
     */
    public java.lang.String getCodigoComando() {
        return codigoComando;
    }


    /**
     * Sets the codigoComando value for this BaseResponse.
     * 
     * @param codigoComando
     */
    public void setCodigoComando(java.lang.String codigoComando) {
        this.codigoComando = codigoComando;
    }


    /**
     * Gets the codigoEmpresa value for this BaseResponse.
     * 
     * @return codigoEmpresa
     */
    public java.lang.String getCodigoEmpresa() {
        return codigoEmpresa;
    }


    /**
     * Sets the codigoEmpresa value for this BaseResponse.
     * 
     * @param codigoEmpresa
     */
    public void setCodigoEmpresa(java.lang.String codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }


    /**
     * Gets the tipoAplicacionOrigen value for this BaseResponse.
     * 
     * @return tipoAplicacionOrigen
     */
    public java.lang.String getTipoAplicacionOrigen() {
        return tipoAplicacionOrigen;
    }


    /**
     * Sets the tipoAplicacionOrigen value for this BaseResponse.
     * 
     * @param tipoAplicacionOrigen
     */
    public void setTipoAplicacionOrigen(java.lang.String tipoAplicacionOrigen) {
        this.tipoAplicacionOrigen = tipoAplicacionOrigen;
    }


    /**
     * Gets the codigoAplicacionOrigen value for this BaseResponse.
     * 
     * @return codigoAplicacionOrigen
     */
    public java.lang.String getCodigoAplicacionOrigen() {
        return codigoAplicacionOrigen;
    }


    /**
     * Sets the codigoAplicacionOrigen value for this BaseResponse.
     * 
     * @param codigoAplicacionOrigen
     */
    public void setCodigoAplicacionOrigen(java.lang.String codigoAplicacionOrigen) {
        this.codigoAplicacionOrigen = codigoAplicacionOrigen;
    }


    /**
     * Gets the fechaDate value for this BaseResponse.
     * 
     * @return fechaDate
     */
    public java.util.Calendar getFechaDate() {
        return fechaDate;
    }


    /**
     * Sets the fechaDate value for this BaseResponse.
     * 
     * @param fechaDate
     */
    public void setFechaDate(java.util.Calendar fechaDate) {
        this.fechaDate = fechaDate;
    }


    /**
     * Gets the fecha value for this BaseResponse.
     * 
     * @return fecha
     */
    public java.lang.Integer getFecha() {
        return fecha;
    }


    /**
     * Sets the fecha value for this BaseResponse.
     * 
     * @param fecha
     */
    public void setFecha(java.lang.Integer fecha) {
        this.fecha = fecha;
    }


    /**
     * Gets the hora value for this BaseResponse.
     * 
     * @return hora
     */
    public java.lang.Integer getHora() {
        return hora;
    }


    /**
     * Sets the hora value for this BaseResponse.
     * 
     * @param hora
     */
    public void setHora(java.lang.Integer hora) {
        this.hora = hora;
    }


    /**
     * Gets the usuario value for this BaseResponse.
     * 
     * @return usuario
     */
    public java.lang.String getUsuario() {
        return usuario;
    }


    /**
     * Sets the usuario value for this BaseResponse.
     * 
     * @param usuario
     */
    public void setUsuario(java.lang.String usuario) {
        this.usuario = usuario;
    }


    /**
     * Gets the numeroSecuencia value for this BaseResponse.
     * 
     * @return numeroSecuencia
     */
    public java.lang.Integer getNumeroSecuencia() {
        return numeroSecuencia;
    }


    /**
     * Sets the numeroSecuencia value for this BaseResponse.
     * 
     * @param numeroSecuencia
     */
    public void setNumeroSecuencia(java.lang.Integer numeroSecuencia) {
        this.numeroSecuencia = numeroSecuencia;
    }


    /**
     * Gets the codigoComercio value for this BaseResponse.
     * 
     * @return codigoComercio
     */
    public java.lang.String getCodigoComercio() {
        return codigoComercio;
    }


    /**
     * Sets the codigoComercio value for this BaseResponse.
     * 
     * @param codigoComercio
     */
    public void setCodigoComercio(java.lang.String codigoComercio) {
        this.codigoComercio = codigoComercio;
    }


    /**
     * Gets the tipoTerminal value for this BaseResponse.
     * 
     * @return tipoTerminal
     */
    public java.lang.Integer getTipoTerminal() {
        return tipoTerminal;
    }


    /**
     * Sets the tipoTerminal value for this BaseResponse.
     * 
     * @param tipoTerminal
     */
    public void setTipoTerminal(java.lang.Integer tipoTerminal) {
        this.tipoTerminal = tipoTerminal;
    }


    /**
     * Gets the numeroTerminal value for this BaseResponse.
     * 
     * @return numeroTerminal
     */
    public java.lang.String getNumeroTerminal() {
        return numeroTerminal;
    }


    /**
     * Sets the numeroTerminal value for this BaseResponse.
     * 
     * @param numeroTerminal
     */
    public void setNumeroTerminal(java.lang.String numeroTerminal) {
        this.numeroTerminal = numeroTerminal;
    }


    /**
     * Gets the codigoZonaIntercambio value for this BaseResponse.
     * 
     * @return codigoZonaIntercambio
     */
    public java.lang.String getCodigoZonaIntercambio() {
        return codigoZonaIntercambio;
    }


    /**
     * Sets the codigoZonaIntercambio value for this BaseResponse.
     * 
     * @param codigoZonaIntercambio
     */
    public void setCodigoZonaIntercambio(java.lang.String codigoZonaIntercambio) {
        this.codigoZonaIntercambio = codigoZonaIntercambio;
    }


    /**
     * Gets the filler value for this BaseResponse.
     * 
     * @return filler
     */
    public java.lang.String getFiller() {
        return filler;
    }


    /**
     * Sets the filler value for this BaseResponse.
     * 
     * @param filler
     */
    public void setFiller(java.lang.String filler) {
        this.filler = filler;
    }


    /**
     * Gets the origen value for this BaseResponse.
     * 
     * @return origen
     */
    public java.lang.String getOrigen() {
        return origen;
    }


    /**
     * Sets the origen value for this BaseResponse.
     * 
     * @param origen
     */
    public void setOrigen(java.lang.String origen) {
        this.origen = origen;
    }


    /**
     * Gets the codigoRespuestaPrincipal value for this BaseResponse.
     * 
     * @return codigoRespuestaPrincipal
     */
    public java.lang.Integer getCodigoRespuestaPrincipal() {
        return codigoRespuestaPrincipal;
    }


    /**
     * Sets the codigoRespuestaPrincipal value for this BaseResponse.
     * 
     * @param codigoRespuestaPrincipal
     */
    public void setCodigoRespuestaPrincipal(java.lang.Integer codigoRespuestaPrincipal) {
        this.codigoRespuestaPrincipal = codigoRespuestaPrincipal;
    }


    /**
     * Gets the mensajeError value for this BaseResponse.
     * 
     * @return mensajeError
     */
    public java.lang.String getMensajeError() {
        return mensajeError;
    }


    /**
     * Sets the mensajeError value for this BaseResponse.
     * 
     * @param mensajeError
     */
    public void setMensajeError(java.lang.String mensajeError) {
        this.mensajeError = mensajeError;
    }


    /**
     * Gets the codigoRespuestaExtendido value for this BaseResponse.
     * 
     * @return codigoRespuestaExtendido
     */
    public java.lang.String getCodigoRespuestaExtendido() {
        return codigoRespuestaExtendido;
    }


    /**
     * Sets the codigoRespuestaExtendido value for this BaseResponse.
     * 
     * @param codigoRespuestaExtendido
     */
    public void setCodigoRespuestaExtendido(java.lang.String codigoRespuestaExtendido) {
        this.codigoRespuestaExtendido = codigoRespuestaExtendido;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BaseResponse)) return false;
        BaseResponse other = (BaseResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.tipoFormato==null && other.getTipoFormato()==null) || 
             (this.tipoFormato!=null &&
              this.tipoFormato.equals(other.getTipoFormato()))) &&
            ((this.codigoComando==null && other.getCodigoComando()==null) || 
             (this.codigoComando!=null &&
              this.codigoComando.equals(other.getCodigoComando()))) &&
            ((this.codigoEmpresa==null && other.getCodigoEmpresa()==null) || 
             (this.codigoEmpresa!=null &&
              this.codigoEmpresa.equals(other.getCodigoEmpresa()))) &&
            ((this.tipoAplicacionOrigen==null && other.getTipoAplicacionOrigen()==null) || 
             (this.tipoAplicacionOrigen!=null &&
              this.tipoAplicacionOrigen.equals(other.getTipoAplicacionOrigen()))) &&
            ((this.codigoAplicacionOrigen==null && other.getCodigoAplicacionOrigen()==null) || 
             (this.codigoAplicacionOrigen!=null &&
              this.codigoAplicacionOrigen.equals(other.getCodigoAplicacionOrigen()))) &&
            ((this.fechaDate==null && other.getFechaDate()==null) || 
             (this.fechaDate!=null &&
              this.fechaDate.equals(other.getFechaDate()))) &&
            ((this.fecha==null && other.getFecha()==null) || 
             (this.fecha!=null &&
              this.fecha.equals(other.getFecha()))) &&
            ((this.hora==null && other.getHora()==null) || 
             (this.hora!=null &&
              this.hora.equals(other.getHora()))) &&
            ((this.usuario==null && other.getUsuario()==null) || 
             (this.usuario!=null &&
              this.usuario.equals(other.getUsuario()))) &&
            ((this.numeroSecuencia==null && other.getNumeroSecuencia()==null) || 
             (this.numeroSecuencia!=null &&
              this.numeroSecuencia.equals(other.getNumeroSecuencia()))) &&
            ((this.codigoComercio==null && other.getCodigoComercio()==null) || 
             (this.codigoComercio!=null &&
              this.codigoComercio.equals(other.getCodigoComercio()))) &&
            ((this.tipoTerminal==null && other.getTipoTerminal()==null) || 
             (this.tipoTerminal!=null &&
              this.tipoTerminal.equals(other.getTipoTerminal()))) &&
            ((this.numeroTerminal==null && other.getNumeroTerminal()==null) || 
             (this.numeroTerminal!=null &&
              this.numeroTerminal.equals(other.getNumeroTerminal()))) &&
            ((this.codigoZonaIntercambio==null && other.getCodigoZonaIntercambio()==null) || 
             (this.codigoZonaIntercambio!=null &&
              this.codigoZonaIntercambio.equals(other.getCodigoZonaIntercambio()))) &&
            ((this.filler==null && other.getFiller()==null) || 
             (this.filler!=null &&
              this.filler.equals(other.getFiller()))) &&
            ((this.origen==null && other.getOrigen()==null) || 
             (this.origen!=null &&
              this.origen.equals(other.getOrigen()))) &&
            ((this.codigoRespuestaPrincipal==null && other.getCodigoRespuestaPrincipal()==null) || 
             (this.codigoRespuestaPrincipal!=null &&
              this.codigoRespuestaPrincipal.equals(other.getCodigoRespuestaPrincipal()))) &&
            ((this.mensajeError==null && other.getMensajeError()==null) || 
             (this.mensajeError!=null &&
              this.mensajeError.equals(other.getMensajeError()))) &&
            ((this.codigoRespuestaExtendido==null && other.getCodigoRespuestaExtendido()==null) || 
             (this.codigoRespuestaExtendido!=null &&
              this.codigoRespuestaExtendido.equals(other.getCodigoRespuestaExtendido())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getTipoFormato() != null) {
            _hashCode += getTipoFormato().hashCode();
        }
        if (getCodigoComando() != null) {
            _hashCode += getCodigoComando().hashCode();
        }
        if (getCodigoEmpresa() != null) {
            _hashCode += getCodigoEmpresa().hashCode();
        }
        if (getTipoAplicacionOrigen() != null) {
            _hashCode += getTipoAplicacionOrigen().hashCode();
        }
        if (getCodigoAplicacionOrigen() != null) {
            _hashCode += getCodigoAplicacionOrigen().hashCode();
        }
        if (getFechaDate() != null) {
            _hashCode += getFechaDate().hashCode();
        }
        if (getFecha() != null) {
            _hashCode += getFecha().hashCode();
        }
        if (getHora() != null) {
            _hashCode += getHora().hashCode();
        }
        if (getUsuario() != null) {
            _hashCode += getUsuario().hashCode();
        }
        if (getNumeroSecuencia() != null) {
            _hashCode += getNumeroSecuencia().hashCode();
        }
        if (getCodigoComercio() != null) {
            _hashCode += getCodigoComercio().hashCode();
        }
        if (getTipoTerminal() != null) {
            _hashCode += getTipoTerminal().hashCode();
        }
        if (getNumeroTerminal() != null) {
            _hashCode += getNumeroTerminal().hashCode();
        }
        if (getCodigoZonaIntercambio() != null) {
            _hashCode += getCodigoZonaIntercambio().hashCode();
        }
        if (getFiller() != null) {
            _hashCode += getFiller().hashCode();
        }
        if (getOrigen() != null) {
            _hashCode += getOrigen().hashCode();
        }
        if (getCodigoRespuestaPrincipal() != null) {
            _hashCode += getCodigoRespuestaPrincipal().hashCode();
        }
        if (getMensajeError() != null) {
            _hashCode += getMensajeError().hashCode();
        }
        if (getCodigoRespuestaExtendido() != null) {
            _hashCode += getCodigoRespuestaExtendido().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BaseResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "baseResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoFormato");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoFormato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoComando");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoComando"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoEmpresa");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoEmpresa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoAplicacionOrigen");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoAplicacionOrigen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoAplicacionOrigen");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoAplicacionOrigen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fecha");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fecha"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hora");
        elemField.setXmlName(new javax.xml.namespace.QName("", "hora"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("usuario");
        elemField.setXmlName(new javax.xml.namespace.QName("", "usuario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroSecuencia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroSecuencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoComercio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoComercio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoTerminal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoTerminal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroTerminal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroTerminal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoZonaIntercambio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoZonaIntercambio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("filler");
        elemField.setXmlName(new javax.xml.namespace.QName("", "filler"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("origen");
        elemField.setXmlName(new javax.xml.namespace.QName("", "origen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoRespuestaPrincipal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoRespuestaPrincipal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mensajeError");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mensajeError"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoRespuestaExtendido");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoRespuestaExtendido"));
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
