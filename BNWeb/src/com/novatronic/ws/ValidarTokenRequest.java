/**
 * ValidarTokenRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.novatronic.ws;

public class ValidarTokenRequest  extends com.novatronic.ws.BaseRequest  implements java.io.Serializable {
    private java.lang.String medioSeguridad;

    private java.lang.String elementoSeguridad;

    private java.lang.String identificadorOperacion;

    private java.lang.Integer tipoElementoSeguridad;

    private java.lang.String valorSeguridad;

    public ValidarTokenRequest() {
    }

    public ValidarTokenRequest(
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
           java.lang.String medioSeguridad,
           java.lang.String elementoSeguridad,
           java.lang.String identificadorOperacion,
           java.lang.Integer tipoElementoSeguridad,
           java.lang.String valorSeguridad) {
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
        this.medioSeguridad = medioSeguridad;
        this.elementoSeguridad = elementoSeguridad;
        this.identificadorOperacion = identificadorOperacion;
        this.tipoElementoSeguridad = tipoElementoSeguridad;
        this.valorSeguridad = valorSeguridad;
    }


    /**
     * Gets the medioSeguridad value for this ValidarTokenRequest.
     * 
     * @return medioSeguridad
     */
    public java.lang.String getMedioSeguridad() {
        return medioSeguridad;
    }


    /**
     * Sets the medioSeguridad value for this ValidarTokenRequest.
     * 
     * @param medioSeguridad
     */
    public void setMedioSeguridad(java.lang.String medioSeguridad) {
        this.medioSeguridad = medioSeguridad;
    }


    /**
     * Gets the elementoSeguridad value for this ValidarTokenRequest.
     * 
     * @return elementoSeguridad
     */
    public java.lang.String getElementoSeguridad() {
        return elementoSeguridad;
    }


    /**
     * Sets the elementoSeguridad value for this ValidarTokenRequest.
     * 
     * @param elementoSeguridad
     */
    public void setElementoSeguridad(java.lang.String elementoSeguridad) {
        this.elementoSeguridad = elementoSeguridad;
    }


    /**
     * Gets the identificadorOperacion value for this ValidarTokenRequest.
     * 
     * @return identificadorOperacion
     */
    public java.lang.String getIdentificadorOperacion() {
        return identificadorOperacion;
    }


    /**
     * Sets the identificadorOperacion value for this ValidarTokenRequest.
     * 
     * @param identificadorOperacion
     */
    public void setIdentificadorOperacion(java.lang.String identificadorOperacion) {
        this.identificadorOperacion = identificadorOperacion;
    }


    /**
     * Gets the tipoElementoSeguridad value for this ValidarTokenRequest.
     * 
     * @return tipoElementoSeguridad
     */
    public java.lang.Integer getTipoElementoSeguridad() {
        return tipoElementoSeguridad;
    }


    /**
     * Sets the tipoElementoSeguridad value for this ValidarTokenRequest.
     * 
     * @param tipoElementoSeguridad
     */
    public void setTipoElementoSeguridad(java.lang.Integer tipoElementoSeguridad) {
        this.tipoElementoSeguridad = tipoElementoSeguridad;
    }


    /**
     * Gets the valorSeguridad value for this ValidarTokenRequest.
     * 
     * @return valorSeguridad
     */
    public java.lang.String getValorSeguridad() {
        return valorSeguridad;
    }


    /**
     * Sets the valorSeguridad value for this ValidarTokenRequest.
     * 
     * @param valorSeguridad
     */
    public void setValorSeguridad(java.lang.String valorSeguridad) {
        this.valorSeguridad = valorSeguridad;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ValidarTokenRequest)) return false;
        ValidarTokenRequest other = (ValidarTokenRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.medioSeguridad==null && other.getMedioSeguridad()==null) || 
             (this.medioSeguridad!=null &&
              this.medioSeguridad.equals(other.getMedioSeguridad()))) &&
            ((this.elementoSeguridad==null && other.getElementoSeguridad()==null) || 
             (this.elementoSeguridad!=null &&
              this.elementoSeguridad.equals(other.getElementoSeguridad()))) &&
            ((this.identificadorOperacion==null && other.getIdentificadorOperacion()==null) || 
             (this.identificadorOperacion!=null &&
              this.identificadorOperacion.equals(other.getIdentificadorOperacion()))) &&
            ((this.tipoElementoSeguridad==null && other.getTipoElementoSeguridad()==null) || 
             (this.tipoElementoSeguridad!=null &&
              this.tipoElementoSeguridad.equals(other.getTipoElementoSeguridad()))) &&
            ((this.valorSeguridad==null && other.getValorSeguridad()==null) || 
             (this.valorSeguridad!=null &&
              this.valorSeguridad.equals(other.getValorSeguridad())));
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
        if (getMedioSeguridad() != null) {
            _hashCode += getMedioSeguridad().hashCode();
        }
        if (getElementoSeguridad() != null) {
            _hashCode += getElementoSeguridad().hashCode();
        }
        if (getIdentificadorOperacion() != null) {
            _hashCode += getIdentificadorOperacion().hashCode();
        }
        if (getTipoElementoSeguridad() != null) {
            _hashCode += getTipoElementoSeguridad().hashCode();
        }
        if (getValorSeguridad() != null) {
            _hashCode += getValorSeguridad().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ValidarTokenRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "validarTokenRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("medioSeguridad");
        elemField.setXmlName(new javax.xml.namespace.QName("", "medioSeguridad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("elementoSeguridad");
        elemField.setXmlName(new javax.xml.namespace.QName("", "elementoSeguridad"));
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
        elemField.setFieldName("tipoElementoSeguridad");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoElementoSeguridad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valorSeguridad");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valorSeguridad"));
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
