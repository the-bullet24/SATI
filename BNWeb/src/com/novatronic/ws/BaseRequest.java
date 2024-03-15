/**
 * BaseRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.novatronic.ws;

public abstract class BaseRequest  implements java.io.Serializable {
    private java.lang.String codigoAplicacionOrigen;

    private java.lang.String codigoComercio;

    private java.lang.String codigoEmpresa;

    private java.lang.String codigoZonaIntercambio;

    private java.lang.String numeroTerminal;

    private java.lang.String tipoAplicacionOrigen;

    private java.lang.Integer tipoTerminal;

    private java.lang.String usuario;

    private java.util.Calendar fechaDate;

    private java.lang.Integer numeroSecuencia;

    public BaseRequest() {
    }

    public BaseRequest(
           java.lang.String codigoAplicacionOrigen,
           java.lang.String codigoComercio,
           java.lang.String codigoEmpresa,
           java.lang.String codigoZonaIntercambio,
           java.lang.String numeroTerminal,
           java.lang.String tipoAplicacionOrigen,
           java.lang.Integer tipoTerminal,
           java.lang.String usuario,
           java.util.Calendar fechaDate,
           java.lang.Integer numeroSecuencia) {
           this.codigoAplicacionOrigen = codigoAplicacionOrigen;
           this.codigoComercio = codigoComercio;
           this.codigoEmpresa = codigoEmpresa;
           this.codigoZonaIntercambio = codigoZonaIntercambio;
           this.numeroTerminal = numeroTerminal;
           this.tipoAplicacionOrigen = tipoAplicacionOrigen;
           this.tipoTerminal = tipoTerminal;
           this.usuario = usuario;
           this.fechaDate = fechaDate;
           this.numeroSecuencia = numeroSecuencia;
    }


    /**
     * Gets the codigoAplicacionOrigen value for this BaseRequest.
     * 
     * @return codigoAplicacionOrigen
     */
    public java.lang.String getCodigoAplicacionOrigen() {
        return codigoAplicacionOrigen;
    }


    /**
     * Sets the codigoAplicacionOrigen value for this BaseRequest.
     * 
     * @param codigoAplicacionOrigen
     */
    public void setCodigoAplicacionOrigen(java.lang.String codigoAplicacionOrigen) {
        this.codigoAplicacionOrigen = codigoAplicacionOrigen;
    }


    /**
     * Gets the codigoComercio value for this BaseRequest.
     * 
     * @return codigoComercio
     */
    public java.lang.String getCodigoComercio() {
        return codigoComercio;
    }


    /**
     * Sets the codigoComercio value for this BaseRequest.
     * 
     * @param codigoComercio
     */
    public void setCodigoComercio(java.lang.String codigoComercio) {
        this.codigoComercio = codigoComercio;
    }


    /**
     * Gets the codigoEmpresa value for this BaseRequest.
     * 
     * @return codigoEmpresa
     */
    public java.lang.String getCodigoEmpresa() {
        return codigoEmpresa;
    }


    /**
     * Sets the codigoEmpresa value for this BaseRequest.
     * 
     * @param codigoEmpresa
     */
    public void setCodigoEmpresa(java.lang.String codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }


    /**
     * Gets the codigoZonaIntercambio value for this BaseRequest.
     * 
     * @return codigoZonaIntercambio
     */
    public java.lang.String getCodigoZonaIntercambio() {
        return codigoZonaIntercambio;
    }


    /**
     * Sets the codigoZonaIntercambio value for this BaseRequest.
     * 
     * @param codigoZonaIntercambio
     */
    public void setCodigoZonaIntercambio(java.lang.String codigoZonaIntercambio) {
        this.codigoZonaIntercambio = codigoZonaIntercambio;
    }


    /**
     * Gets the numeroTerminal value for this BaseRequest.
     * 
     * @return numeroTerminal
     */
    public java.lang.String getNumeroTerminal() {
        return numeroTerminal;
    }


    /**
     * Sets the numeroTerminal value for this BaseRequest.
     * 
     * @param numeroTerminal
     */
    public void setNumeroTerminal(java.lang.String numeroTerminal) {
        this.numeroTerminal = numeroTerminal;
    }


    /**
     * Gets the tipoAplicacionOrigen value for this BaseRequest.
     * 
     * @return tipoAplicacionOrigen
     */
    public java.lang.String getTipoAplicacionOrigen() {
        return tipoAplicacionOrigen;
    }


    /**
     * Sets the tipoAplicacionOrigen value for this BaseRequest.
     * 
     * @param tipoAplicacionOrigen
     */
    public void setTipoAplicacionOrigen(java.lang.String tipoAplicacionOrigen) {
        this.tipoAplicacionOrigen = tipoAplicacionOrigen;
    }


    /**
     * Gets the tipoTerminal value for this BaseRequest.
     * 
     * @return tipoTerminal
     */
    public java.lang.Integer getTipoTerminal() {
        return tipoTerminal;
    }


    /**
     * Sets the tipoTerminal value for this BaseRequest.
     * 
     * @param tipoTerminal
     */
    public void setTipoTerminal(java.lang.Integer tipoTerminal) {
        this.tipoTerminal = tipoTerminal;
    }


    /**
     * Gets the usuario value for this BaseRequest.
     * 
     * @return usuario
     */
    public java.lang.String getUsuario() {
        return usuario;
    }


    /**
     * Sets the usuario value for this BaseRequest.
     * 
     * @param usuario
     */
    public void setUsuario(java.lang.String usuario) {
        this.usuario = usuario;
    }


    /**
     * Gets the fechaDate value for this BaseRequest.
     * 
     * @return fechaDate
     */
    public java.util.Calendar getFechaDate() {
        return fechaDate;
    }


    /**
     * Sets the fechaDate value for this BaseRequest.
     * 
     * @param fechaDate
     */
    public void setFechaDate(java.util.Calendar fechaDate) {
        this.fechaDate = fechaDate;
    }


    /**
     * Gets the numeroSecuencia value for this BaseRequest.
     * 
     * @return numeroSecuencia
     */
    public java.lang.Integer getNumeroSecuencia() {
        return numeroSecuencia;
    }


    /**
     * Sets the numeroSecuencia value for this BaseRequest.
     * 
     * @param numeroSecuencia
     */
    public void setNumeroSecuencia(java.lang.Integer numeroSecuencia) {
        this.numeroSecuencia = numeroSecuencia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BaseRequest)) return false;
        BaseRequest other = (BaseRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codigoAplicacionOrigen==null && other.getCodigoAplicacionOrigen()==null) || 
             (this.codigoAplicacionOrigen!=null &&
              this.codigoAplicacionOrigen.equals(other.getCodigoAplicacionOrigen()))) &&
            ((this.codigoComercio==null && other.getCodigoComercio()==null) || 
             (this.codigoComercio!=null &&
              this.codigoComercio.equals(other.getCodigoComercio()))) &&
            ((this.codigoEmpresa==null && other.getCodigoEmpresa()==null) || 
             (this.codigoEmpresa!=null &&
              this.codigoEmpresa.equals(other.getCodigoEmpresa()))) &&
            ((this.codigoZonaIntercambio==null && other.getCodigoZonaIntercambio()==null) || 
             (this.codigoZonaIntercambio!=null &&
              this.codigoZonaIntercambio.equals(other.getCodigoZonaIntercambio()))) &&
            ((this.numeroTerminal==null && other.getNumeroTerminal()==null) || 
             (this.numeroTerminal!=null &&
              this.numeroTerminal.equals(other.getNumeroTerminal()))) &&
            ((this.tipoAplicacionOrigen==null && other.getTipoAplicacionOrigen()==null) || 
             (this.tipoAplicacionOrigen!=null &&
              this.tipoAplicacionOrigen.equals(other.getTipoAplicacionOrigen()))) &&
            ((this.tipoTerminal==null && other.getTipoTerminal()==null) || 
             (this.tipoTerminal!=null &&
              this.tipoTerminal.equals(other.getTipoTerminal()))) &&
            ((this.usuario==null && other.getUsuario()==null) || 
             (this.usuario!=null &&
              this.usuario.equals(other.getUsuario()))) &&
            ((this.fechaDate==null && other.getFechaDate()==null) || 
             (this.fechaDate!=null &&
              this.fechaDate.equals(other.getFechaDate()))) &&
            ((this.numeroSecuencia==null && other.getNumeroSecuencia()==null) || 
             (this.numeroSecuencia!=null &&
              this.numeroSecuencia.equals(other.getNumeroSecuencia())));
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
        if (getCodigoAplicacionOrigen() != null) {
            _hashCode += getCodigoAplicacionOrigen().hashCode();
        }
        if (getCodigoComercio() != null) {
            _hashCode += getCodigoComercio().hashCode();
        }
        if (getCodigoEmpresa() != null) {
            _hashCode += getCodigoEmpresa().hashCode();
        }
        if (getCodigoZonaIntercambio() != null) {
            _hashCode += getCodigoZonaIntercambio().hashCode();
        }
        if (getNumeroTerminal() != null) {
            _hashCode += getNumeroTerminal().hashCode();
        }
        if (getTipoAplicacionOrigen() != null) {
            _hashCode += getTipoAplicacionOrigen().hashCode();
        }
        if (getTipoTerminal() != null) {
            _hashCode += getTipoTerminal().hashCode();
        }
        if (getUsuario() != null) {
            _hashCode += getUsuario().hashCode();
        }
        if (getFechaDate() != null) {
            _hashCode += getFechaDate().hashCode();
        }
        if (getNumeroSecuencia() != null) {
            _hashCode += getNumeroSecuencia().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BaseRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "baseRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoAplicacionOrigen");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoAplicacionOrigen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("codigoEmpresa");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoEmpresa"));
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
        elemField.setFieldName("numeroTerminal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroTerminal"));
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
        elemField.setFieldName("tipoTerminal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoTerminal"));
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
        elemField.setFieldName("fechaDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
