/**
 * ObtenerListaBinesMedioAutenticacionRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.novatronic.ws;

public class ObtenerListaBinesMedioAutenticacionRequest  extends com.novatronic.ws.BaseRequest  implements java.io.Serializable {
    private java.lang.String binOriginalMedioAutenticacion;

    private java.lang.String codigoBinMedioAutenticacion;

    private java.lang.Integer codigoTipoMedioAutenticacionPadre;

    private java.lang.String estadoBinMedioAutenticacion;

    private java.lang.String nombreBinMedioAutenticacion;

    public ObtenerListaBinesMedioAutenticacionRequest() {
    }

    public ObtenerListaBinesMedioAutenticacionRequest(
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
           java.lang.String binOriginalMedioAutenticacion,
           java.lang.String codigoBinMedioAutenticacion,
           java.lang.Integer codigoTipoMedioAutenticacionPadre,
           java.lang.String estadoBinMedioAutenticacion,
           java.lang.String nombreBinMedioAutenticacion) {
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
        this.binOriginalMedioAutenticacion = binOriginalMedioAutenticacion;
        this.codigoBinMedioAutenticacion = codigoBinMedioAutenticacion;
        this.codigoTipoMedioAutenticacionPadre = codigoTipoMedioAutenticacionPadre;
        this.estadoBinMedioAutenticacion = estadoBinMedioAutenticacion;
        this.nombreBinMedioAutenticacion = nombreBinMedioAutenticacion;
    }


    /**
     * Gets the binOriginalMedioAutenticacion value for this ObtenerListaBinesMedioAutenticacionRequest.
     * 
     * @return binOriginalMedioAutenticacion
     */
    public java.lang.String getBinOriginalMedioAutenticacion() {
        return binOriginalMedioAutenticacion;
    }


    /**
     * Sets the binOriginalMedioAutenticacion value for this ObtenerListaBinesMedioAutenticacionRequest.
     * 
     * @param binOriginalMedioAutenticacion
     */
    public void setBinOriginalMedioAutenticacion(java.lang.String binOriginalMedioAutenticacion) {
        this.binOriginalMedioAutenticacion = binOriginalMedioAutenticacion;
    }


    /**
     * Gets the codigoBinMedioAutenticacion value for this ObtenerListaBinesMedioAutenticacionRequest.
     * 
     * @return codigoBinMedioAutenticacion
     */
    public java.lang.String getCodigoBinMedioAutenticacion() {
        return codigoBinMedioAutenticacion;
    }


    /**
     * Sets the codigoBinMedioAutenticacion value for this ObtenerListaBinesMedioAutenticacionRequest.
     * 
     * @param codigoBinMedioAutenticacion
     */
    public void setCodigoBinMedioAutenticacion(java.lang.String codigoBinMedioAutenticacion) {
        this.codigoBinMedioAutenticacion = codigoBinMedioAutenticacion;
    }


    /**
     * Gets the codigoTipoMedioAutenticacionPadre value for this ObtenerListaBinesMedioAutenticacionRequest.
     * 
     * @return codigoTipoMedioAutenticacionPadre
     */
    public java.lang.Integer getCodigoTipoMedioAutenticacionPadre() {
        return codigoTipoMedioAutenticacionPadre;
    }


    /**
     * Sets the codigoTipoMedioAutenticacionPadre value for this ObtenerListaBinesMedioAutenticacionRequest.
     * 
     * @param codigoTipoMedioAutenticacionPadre
     */
    public void setCodigoTipoMedioAutenticacionPadre(java.lang.Integer codigoTipoMedioAutenticacionPadre) {
        this.codigoTipoMedioAutenticacionPadre = codigoTipoMedioAutenticacionPadre;
    }


    /**
     * Gets the estadoBinMedioAutenticacion value for this ObtenerListaBinesMedioAutenticacionRequest.
     * 
     * @return estadoBinMedioAutenticacion
     */
    public java.lang.String getEstadoBinMedioAutenticacion() {
        return estadoBinMedioAutenticacion;
    }


    /**
     * Sets the estadoBinMedioAutenticacion value for this ObtenerListaBinesMedioAutenticacionRequest.
     * 
     * @param estadoBinMedioAutenticacion
     */
    public void setEstadoBinMedioAutenticacion(java.lang.String estadoBinMedioAutenticacion) {
        this.estadoBinMedioAutenticacion = estadoBinMedioAutenticacion;
    }


    /**
     * Gets the nombreBinMedioAutenticacion value for this ObtenerListaBinesMedioAutenticacionRequest.
     * 
     * @return nombreBinMedioAutenticacion
     */
    public java.lang.String getNombreBinMedioAutenticacion() {
        return nombreBinMedioAutenticacion;
    }


    /**
     * Sets the nombreBinMedioAutenticacion value for this ObtenerListaBinesMedioAutenticacionRequest.
     * 
     * @param nombreBinMedioAutenticacion
     */
    public void setNombreBinMedioAutenticacion(java.lang.String nombreBinMedioAutenticacion) {
        this.nombreBinMedioAutenticacion = nombreBinMedioAutenticacion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ObtenerListaBinesMedioAutenticacionRequest)) return false;
        ObtenerListaBinesMedioAutenticacionRequest other = (ObtenerListaBinesMedioAutenticacionRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.binOriginalMedioAutenticacion==null && other.getBinOriginalMedioAutenticacion()==null) || 
             (this.binOriginalMedioAutenticacion!=null &&
              this.binOriginalMedioAutenticacion.equals(other.getBinOriginalMedioAutenticacion()))) &&
            ((this.codigoBinMedioAutenticacion==null && other.getCodigoBinMedioAutenticacion()==null) || 
             (this.codigoBinMedioAutenticacion!=null &&
              this.codigoBinMedioAutenticacion.equals(other.getCodigoBinMedioAutenticacion()))) &&
            ((this.codigoTipoMedioAutenticacionPadre==null && other.getCodigoTipoMedioAutenticacionPadre()==null) || 
             (this.codigoTipoMedioAutenticacionPadre!=null &&
              this.codigoTipoMedioAutenticacionPadre.equals(other.getCodigoTipoMedioAutenticacionPadre()))) &&
            ((this.estadoBinMedioAutenticacion==null && other.getEstadoBinMedioAutenticacion()==null) || 
             (this.estadoBinMedioAutenticacion!=null &&
              this.estadoBinMedioAutenticacion.equals(other.getEstadoBinMedioAutenticacion()))) &&
            ((this.nombreBinMedioAutenticacion==null && other.getNombreBinMedioAutenticacion()==null) || 
             (this.nombreBinMedioAutenticacion!=null &&
              this.nombreBinMedioAutenticacion.equals(other.getNombreBinMedioAutenticacion())));
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
        if (getBinOriginalMedioAutenticacion() != null) {
            _hashCode += getBinOriginalMedioAutenticacion().hashCode();
        }
        if (getCodigoBinMedioAutenticacion() != null) {
            _hashCode += getCodigoBinMedioAutenticacion().hashCode();
        }
        if (getCodigoTipoMedioAutenticacionPadre() != null) {
            _hashCode += getCodigoTipoMedioAutenticacionPadre().hashCode();
        }
        if (getEstadoBinMedioAutenticacion() != null) {
            _hashCode += getEstadoBinMedioAutenticacion().hashCode();
        }
        if (getNombreBinMedioAutenticacion() != null) {
            _hashCode += getNombreBinMedioAutenticacion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ObtenerListaBinesMedioAutenticacionRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerListaBinesMedioAutenticacionRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("binOriginalMedioAutenticacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "binOriginalMedioAutenticacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoBinMedioAutenticacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoBinMedioAutenticacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoTipoMedioAutenticacionPadre");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codigoTipoMedioAutenticacionPadre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estadoBinMedioAutenticacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "estadoBinMedioAutenticacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreBinMedioAutenticacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nombreBinMedioAutenticacion"));
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
