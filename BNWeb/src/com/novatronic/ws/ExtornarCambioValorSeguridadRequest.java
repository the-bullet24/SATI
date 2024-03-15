/**
 * ExtornarCambioValorSeguridadRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.novatronic.ws;

public class ExtornarCambioValorSeguridadRequest  extends com.novatronic.ws.BaseRequest  implements java.io.Serializable {
    private java.lang.String binMedioAutenticacion;

    private java.lang.String codigoMedioAutenticacion;

    private java.lang.Integer flagExtornoOffset;

    private java.lang.Integer flagExtornoPVV;

    private java.lang.Integer flagExtornoPassword;

    private java.lang.Integer tipoElementoSeguridad;

    public ExtornarCambioValorSeguridadRequest() {
    }

    public ExtornarCambioValorSeguridadRequest(
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
           java.lang.Integer flagExtornoOffset,
           java.lang.Integer flagExtornoPVV,
           java.lang.Integer flagExtornoPassword,
           java.lang.Integer tipoElementoSeguridad) {
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
        this.flagExtornoOffset = flagExtornoOffset;
        this.flagExtornoPVV = flagExtornoPVV;
        this.flagExtornoPassword = flagExtornoPassword;
        this.tipoElementoSeguridad = tipoElementoSeguridad;
    }


    /**
     * Gets the binMedioAutenticacion value for this ExtornarCambioValorSeguridadRequest.
     * 
     * @return binMedioAutenticacion
     */
    public java.lang.String getBinMedioAutenticacion() {
        return binMedioAutenticacion;
    }


    /**
     * Sets the binMedioAutenticacion value for this ExtornarCambioValorSeguridadRequest.
     * 
     * @param binMedioAutenticacion
     */
    public void setBinMedioAutenticacion(java.lang.String binMedioAutenticacion) {
        this.binMedioAutenticacion = binMedioAutenticacion;
    }


    /**
     * Gets the codigoMedioAutenticacion value for this ExtornarCambioValorSeguridadRequest.
     * 
     * @return codigoMedioAutenticacion
     */
    public java.lang.String getCodigoMedioAutenticacion() {
        return codigoMedioAutenticacion;
    }


    /**
     * Sets the codigoMedioAutenticacion value for this ExtornarCambioValorSeguridadRequest.
     * 
     * @param codigoMedioAutenticacion
     */
    public void setCodigoMedioAutenticacion(java.lang.String codigoMedioAutenticacion) {
        this.codigoMedioAutenticacion = codigoMedioAutenticacion;
    }


    /**
     * Gets the flagExtornoOffset value for this ExtornarCambioValorSeguridadRequest.
     * 
     * @return flagExtornoOffset
     */
    public java.lang.Integer getFlagExtornoOffset() {
        return flagExtornoOffset;
    }


    /**
     * Sets the flagExtornoOffset value for this ExtornarCambioValorSeguridadRequest.
     * 
     * @param flagExtornoOffset
     */
    public void setFlagExtornoOffset(java.lang.Integer flagExtornoOffset) {
        this.flagExtornoOffset = flagExtornoOffset;
    }


    /**
     * Gets the flagExtornoPVV value for this ExtornarCambioValorSeguridadRequest.
     * 
     * @return flagExtornoPVV
     */
    public java.lang.Integer getFlagExtornoPVV() {
        return flagExtornoPVV;
    }


    /**
     * Sets the flagExtornoPVV value for this ExtornarCambioValorSeguridadRequest.
     * 
     * @param flagExtornoPVV
     */
    public void setFlagExtornoPVV(java.lang.Integer flagExtornoPVV) {
        this.flagExtornoPVV = flagExtornoPVV;
    }


    /**
     * Gets the flagExtornoPassword value for this ExtornarCambioValorSeguridadRequest.
     * 
     * @return flagExtornoPassword
     */
    public java.lang.Integer getFlagExtornoPassword() {
        return flagExtornoPassword;
    }


    /**
     * Sets the flagExtornoPassword value for this ExtornarCambioValorSeguridadRequest.
     * 
     * @param flagExtornoPassword
     */
    public void setFlagExtornoPassword(java.lang.Integer flagExtornoPassword) {
        this.flagExtornoPassword = flagExtornoPassword;
    }


    /**
     * Gets the tipoElementoSeguridad value for this ExtornarCambioValorSeguridadRequest.
     * 
     * @return tipoElementoSeguridad
     */
    public java.lang.Integer getTipoElementoSeguridad() {
        return tipoElementoSeguridad;
    }


    /**
     * Sets the tipoElementoSeguridad value for this ExtornarCambioValorSeguridadRequest.
     * 
     * @param tipoElementoSeguridad
     */
    public void setTipoElementoSeguridad(java.lang.Integer tipoElementoSeguridad) {
        this.tipoElementoSeguridad = tipoElementoSeguridad;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ExtornarCambioValorSeguridadRequest)) return false;
        ExtornarCambioValorSeguridadRequest other = (ExtornarCambioValorSeguridadRequest) obj;
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
            ((this.flagExtornoOffset==null && other.getFlagExtornoOffset()==null) || 
             (this.flagExtornoOffset!=null &&
              this.flagExtornoOffset.equals(other.getFlagExtornoOffset()))) &&
            ((this.flagExtornoPVV==null && other.getFlagExtornoPVV()==null) || 
             (this.flagExtornoPVV!=null &&
              this.flagExtornoPVV.equals(other.getFlagExtornoPVV()))) &&
            ((this.flagExtornoPassword==null && other.getFlagExtornoPassword()==null) || 
             (this.flagExtornoPassword!=null &&
              this.flagExtornoPassword.equals(other.getFlagExtornoPassword()))) &&
            ((this.tipoElementoSeguridad==null && other.getTipoElementoSeguridad()==null) || 
             (this.tipoElementoSeguridad!=null &&
              this.tipoElementoSeguridad.equals(other.getTipoElementoSeguridad())));
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
        if (getFlagExtornoOffset() != null) {
            _hashCode += getFlagExtornoOffset().hashCode();
        }
        if (getFlagExtornoPVV() != null) {
            _hashCode += getFlagExtornoPVV().hashCode();
        }
        if (getFlagExtornoPassword() != null) {
            _hashCode += getFlagExtornoPassword().hashCode();
        }
        if (getTipoElementoSeguridad() != null) {
            _hashCode += getTipoElementoSeguridad().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ExtornarCambioValorSeguridadRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "extornarCambioValorSeguridadRequest"));
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
        elemField.setFieldName("flagExtornoOffset");
        elemField.setXmlName(new javax.xml.namespace.QName("", "flagExtornoOffset"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("flagExtornoPVV");
        elemField.setXmlName(new javax.xml.namespace.QName("", "flagExtornoPVV"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("flagExtornoPassword");
        elemField.setXmlName(new javax.xml.namespace.QName("", "flagExtornoPassword"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoElementoSeguridad");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoElementoSeguridad"));
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
