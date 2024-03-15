/**
 * ObtenerListaTiposMedioAutenticacionRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.novatronic.ws;

public class ObtenerListaTiposMedioAutenticacionRequest  extends com.novatronic.ws.BaseRequest  implements java.io.Serializable {
    private java.lang.Integer codigoTipoMedioAutenticacion;

    private java.lang.String estadoTipoMedioAutenticacion;

    private java.lang.String flagMedioPrincipal;

    private java.lang.String nombreTipoMedioAutenticacion;

    public ObtenerListaTiposMedioAutenticacionRequest() {
    }

    public ObtenerListaTiposMedioAutenticacionRequest(
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
           java.lang.Integer codigoTipoMedioAutenticacion,
           java.lang.String estadoTipoMedioAutenticacion,
           java.lang.String flagMedioPrincipal,
           java.lang.String nombreTipoMedioAutenticacion) {
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
        this.codigoTipoMedioAutenticacion = codigoTipoMedioAutenticacion;
        this.estadoTipoMedioAutenticacion = estadoTipoMedioAutenticacion;
        this.flagMedioPrincipal = flagMedioPrincipal;
        this.nombreTipoMedioAutenticacion = nombreTipoMedioAutenticacion;
    }


    /**
     * Gets the codigoTipoMedioAutenticacion value for this ObtenerListaTiposMedioAutenticacionRequest.
     * 
     * @return codigoTipoMedioAutenticacion
     */
    public java.lang.Integer getCodigoTipoMedioAutenticacion() {
        return codigoTipoMedioAutenticacion;
    }


    /**
     * Sets the codigoTipoMedioAutenticacion value for this ObtenerListaTiposMedioAutenticacionRequest.
     * 
     * @param codigoTipoMedioAutenticacion
     */
    public void setCodigoTipoMedioAutenticacion(java.lang.Integer codigoTipoMedioAutenticacion) {
        this.codigoTipoMedioAutenticacion = codigoTipoMedioAutenticacion;
    }


    /**
     * Gets the estadoTipoMedioAutenticacion value for this ObtenerListaTiposMedioAutenticacionRequest.
     * 
     * @return estadoTipoMedioAutenticacion
     */
    public java.lang.String getEstadoTipoMedioAutenticacion() {
        return estadoTipoMedioAutenticacion;
    }


    /**
     * Sets the estadoTipoMedioAutenticacion value for this ObtenerListaTiposMedioAutenticacionRequest.
     * 
     * @param estadoTipoMedioAutenticacion
     */
    public void setEstadoTipoMedioAutenticacion(java.lang.String estadoTipoMedioAutenticacion) {
        this.estadoTipoMedioAutenticacion = estadoTipoMedioAutenticacion;
    }


    /**
     * Gets the flagMedioPrincipal value for this ObtenerListaTiposMedioAutenticacionRequest.
     * 
     * @return flagMedioPrincipal
     */
    public java.lang.String getFlagMedioPrincipal() {
        return flagMedioPrincipal;
    }


    /**
     * Sets the flagMedioPrincipal value for this ObtenerListaTiposMedioAutenticacionRequest.
     * 
     * @param flagMedioPrincipal
     */
    public void setFlagMedioPrincipal(java.lang.String flagMedioPrincipal) {
        this.flagMedioPrincipal = flagMedioPrincipal;
    }


    /**
     * Gets the nombreTipoMedioAutenticacion value for this ObtenerListaTiposMedioAutenticacionRequest.
     * 
     * @return nombreTipoMedioAutenticacion
     */
    public java.lang.String getNombreTipoMedioAutenticacion() {
        return nombreTipoMedioAutenticacion;
    }


    /**
     * Sets the nombreTipoMedioAutenticacion value for this ObtenerListaTiposMedioAutenticacionRequest.
     * 
     * @param nombreTipoMedioAutenticacion
     */
    public void setNombreTipoMedioAutenticacion(java.lang.String nombreTipoMedioAutenticacion) {
        this.nombreTipoMedioAutenticacion = nombreTipoMedioAutenticacion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ObtenerListaTiposMedioAutenticacionRequest)) return false;
        ObtenerListaTiposMedioAutenticacionRequest other = (ObtenerListaTiposMedioAutenticacionRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.codigoTipoMedioAutenticacion==null && other.getCodigoTipoMedioAutenticacion()==null) || 
             (this.codigoTipoMedioAutenticacion!=null &&
              this.codigoTipoMedioAutenticacion.equals(other.getCodigoTipoMedioAutenticacion()))) &&
            ((this.estadoTipoMedioAutenticacion==null && other.getEstadoTipoMedioAutenticacion()==null) || 
             (this.estadoTipoMedioAutenticacion!=null &&
              this.estadoTipoMedioAutenticacion.equals(other.getEstadoTipoMedioAutenticacion()))) &&
            ((this.flagMedioPrincipal==null && other.getFlagMedioPrincipal()==null) || 
             (this.flagMedioPrincipal!=null &&
              this.flagMedioPrincipal.equals(other.getFlagMedioPrincipal()))) &&
            ((this.nombreTipoMedioAutenticacion==null && other.getNombreTipoMedioAutenticacion()==null) || 
             (this.nombreTipoMedioAutenticacion!=null &&
              this.nombreTipoMedioAutenticacion.equals(other.getNombreTipoMedioAutenticacion())));
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
        if (getCodigoTipoMedioAutenticacion() != null) {
            _hashCode += getCodigoTipoMedioAutenticacion().hashCode();
        }
        if (getEstadoTipoMedioAutenticacion() != null) {
            _hashCode += getEstadoTipoMedioAutenticacion().hashCode();
        }
        if (getFlagMedioPrincipal() != null) {
            _hashCode += getFlagMedioPrincipal().hashCode();
        }
        if (getNombreTipoMedioAutenticacion() != null) {
            _hashCode += getNombreTipoMedioAutenticacion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ObtenerListaTiposMedioAutenticacionRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerListaTiposMedioAutenticacionRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoTipoMedioAutenticacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoTipoMedioAutenticacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estadoTipoMedioAutenticacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "estadoTipoMedioAutenticacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("flagMedioPrincipal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "flagMedioPrincipal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreTipoMedioAutenticacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nombreTipoMedioAutenticacion"));
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
