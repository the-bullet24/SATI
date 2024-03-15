/**
 * ObtenerListaElementoSeguridadRelacionadosResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.novatronic.ws;

public class ObtenerListaElementoSeguridadRelacionadosResponse  extends com.novatronic.ws.BaseResponse  implements java.io.Serializable {
    private java.lang.String binMedioAutenticacion;

    private java.lang.String codigoMedioAutenticacion;

    private java.lang.String[][] listaElementosSeguridad;

    private java.lang.Integer numeroElementosSeguridad;

    public ObtenerListaElementoSeguridadRelacionadosResponse() {
    }

    public ObtenerListaElementoSeguridadRelacionadosResponse(
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
           java.lang.String codigoRespuestaExtendido,
           java.lang.String binMedioAutenticacion,
           java.lang.String codigoMedioAutenticacion,
           java.lang.String[][] listaElementosSeguridad,
           java.lang.Integer numeroElementosSeguridad) {
        super(
            tipoFormato,
            codigoComando,
            codigoEmpresa,
            tipoAplicacionOrigen,
            codigoAplicacionOrigen,
            fechaDate,
            fecha,
            hora,
            usuario,
            numeroSecuencia,
            codigoComercio,
            tipoTerminal,
            numeroTerminal,
            codigoZonaIntercambio,
            filler,
            origen,
            codigoRespuestaPrincipal,
            mensajeError,
            codigoRespuestaExtendido);
        this.binMedioAutenticacion = binMedioAutenticacion;
        this.codigoMedioAutenticacion = codigoMedioAutenticacion;
        this.listaElementosSeguridad = listaElementosSeguridad;
        this.numeroElementosSeguridad = numeroElementosSeguridad;
    }


    /**
     * Gets the binMedioAutenticacion value for this ObtenerListaElementoSeguridadRelacionadosResponse.
     * 
     * @return binMedioAutenticacion
     */
    public java.lang.String getBinMedioAutenticacion() {
        return binMedioAutenticacion;
    }


    /**
     * Sets the binMedioAutenticacion value for this ObtenerListaElementoSeguridadRelacionadosResponse.
     * 
     * @param binMedioAutenticacion
     */
    public void setBinMedioAutenticacion(java.lang.String binMedioAutenticacion) {
        this.binMedioAutenticacion = binMedioAutenticacion;
    }


    /**
     * Gets the codigoMedioAutenticacion value for this ObtenerListaElementoSeguridadRelacionadosResponse.
     * 
     * @return codigoMedioAutenticacion
     */
    public java.lang.String getCodigoMedioAutenticacion() {
        return codigoMedioAutenticacion;
    }


    /**
     * Sets the codigoMedioAutenticacion value for this ObtenerListaElementoSeguridadRelacionadosResponse.
     * 
     * @param codigoMedioAutenticacion
     */
    public void setCodigoMedioAutenticacion(java.lang.String codigoMedioAutenticacion) {
        this.codigoMedioAutenticacion = codigoMedioAutenticacion;
    }


    /**
     * Gets the listaElementosSeguridad value for this ObtenerListaElementoSeguridadRelacionadosResponse.
     * 
     * @return listaElementosSeguridad
     */
    public java.lang.String[][] getListaElementosSeguridad() {
        return listaElementosSeguridad;
    }


    /**
     * Sets the listaElementosSeguridad value for this ObtenerListaElementoSeguridadRelacionadosResponse.
     * 
     * @param listaElementosSeguridad
     */
    public void setListaElementosSeguridad(java.lang.String[][] listaElementosSeguridad) {
        this.listaElementosSeguridad = listaElementosSeguridad;
    }

    public java.lang.String[] getListaElementosSeguridad(int i) {
        return this.listaElementosSeguridad[i];
    }

    public void setListaElementosSeguridad(int i, java.lang.String[] _value) {
        this.listaElementosSeguridad[i] = _value;
    }


    /**
     * Gets the numeroElementosSeguridad value for this ObtenerListaElementoSeguridadRelacionadosResponse.
     * 
     * @return numeroElementosSeguridad
     */
    public java.lang.Integer getNumeroElementosSeguridad() {
        return numeroElementosSeguridad;
    }


    /**
     * Sets the numeroElementosSeguridad value for this ObtenerListaElementoSeguridadRelacionadosResponse.
     * 
     * @param numeroElementosSeguridad
     */
    public void setNumeroElementosSeguridad(java.lang.Integer numeroElementosSeguridad) {
        this.numeroElementosSeguridad = numeroElementosSeguridad;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ObtenerListaElementoSeguridadRelacionadosResponse)) return false;
        ObtenerListaElementoSeguridadRelacionadosResponse other = (ObtenerListaElementoSeguridadRelacionadosResponse) obj;
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
            ((this.listaElementosSeguridad==null && other.getListaElementosSeguridad()==null) || 
             (this.listaElementosSeguridad!=null &&
              java.util.Arrays.equals(this.listaElementosSeguridad, other.getListaElementosSeguridad()))) &&
            ((this.numeroElementosSeguridad==null && other.getNumeroElementosSeguridad()==null) || 
             (this.numeroElementosSeguridad!=null &&
              this.numeroElementosSeguridad.equals(other.getNumeroElementosSeguridad())));
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
        if (getListaElementosSeguridad() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaElementosSeguridad());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaElementosSeguridad(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getNumeroElementosSeguridad() != null) {
            _hashCode += getNumeroElementosSeguridad().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ObtenerListaElementoSeguridadRelacionadosResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "obtenerListaElementoSeguridadRelacionadosResponse"));
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
        elemField.setFieldName("listaElementosSeguridad");
        elemField.setXmlName(new javax.xml.namespace.QName("", "listaElementosSeguridad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://jaxb.dev.java.net/array", "stringArray"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroElementosSeguridad");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroElementosSeguridad"));
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
