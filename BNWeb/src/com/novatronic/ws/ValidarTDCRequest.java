/**
 * ValidarTDCRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.novatronic.ws;

public class ValidarTDCRequest  extends com.novatronic.ws.BaseRequest  implements java.io.Serializable {
    private java.lang.String identificadorOperacion;

    private java.lang.String identificadorTDC;

    private java.lang.String[][] listaCoordenadas;

    private java.lang.Integer tipoElementoSeguridad;

    private java.lang.String[] valoresValidarConcatenados;

    public ValidarTDCRequest() {
    }

    public ValidarTDCRequest(
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
           java.lang.String identificadorOperacion,
           java.lang.String identificadorTDC,
           java.lang.String[][] listaCoordenadas,
           java.lang.Integer tipoElementoSeguridad,
           java.lang.String[] valoresValidarConcatenados) {
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
        this.identificadorOperacion = identificadorOperacion;
        this.identificadorTDC = identificadorTDC;
        this.listaCoordenadas = listaCoordenadas;
        this.tipoElementoSeguridad = tipoElementoSeguridad;
        this.valoresValidarConcatenados = valoresValidarConcatenados;
    }


    /**
     * Gets the identificadorOperacion value for this ValidarTDCRequest.
     * 
     * @return identificadorOperacion
     */
    public java.lang.String getIdentificadorOperacion() {
        return identificadorOperacion;
    }


    /**
     * Sets the identificadorOperacion value for this ValidarTDCRequest.
     * 
     * @param identificadorOperacion
     */
    public void setIdentificadorOperacion(java.lang.String identificadorOperacion) {
        this.identificadorOperacion = identificadorOperacion;
    }


    /**
     * Gets the identificadorTDC value for this ValidarTDCRequest.
     * 
     * @return identificadorTDC
     */
    public java.lang.String getIdentificadorTDC() {
        return identificadorTDC;
    }


    /**
     * Sets the identificadorTDC value for this ValidarTDCRequest.
     * 
     * @param identificadorTDC
     */
    public void setIdentificadorTDC(java.lang.String identificadorTDC) {
        this.identificadorTDC = identificadorTDC;
    }


    /**
     * Gets the listaCoordenadas value for this ValidarTDCRequest.
     * 
     * @return listaCoordenadas
     */
    public java.lang.String[][] getListaCoordenadas() {
        return listaCoordenadas;
    }


    /**
     * Sets the listaCoordenadas value for this ValidarTDCRequest.
     * 
     * @param listaCoordenadas
     */
    public void setListaCoordenadas(java.lang.String[][] listaCoordenadas) {
        this.listaCoordenadas = listaCoordenadas;
    }

    public java.lang.String[] getListaCoordenadas(int i) {
        return this.listaCoordenadas[i];
    }

    public void setListaCoordenadas(int i, java.lang.String[] _value) {
        this.listaCoordenadas[i] = _value;
    }


    /**
     * Gets the tipoElementoSeguridad value for this ValidarTDCRequest.
     * 
     * @return tipoElementoSeguridad
     */
    public java.lang.Integer getTipoElementoSeguridad() {
        return tipoElementoSeguridad;
    }


    /**
     * Sets the tipoElementoSeguridad value for this ValidarTDCRequest.
     * 
     * @param tipoElementoSeguridad
     */
    public void setTipoElementoSeguridad(java.lang.Integer tipoElementoSeguridad) {
        this.tipoElementoSeguridad = tipoElementoSeguridad;
    }


    /**
     * Gets the valoresValidarConcatenados value for this ValidarTDCRequest.
     * 
     * @return valoresValidarConcatenados
     */
    public java.lang.String[] getValoresValidarConcatenados() {
        return valoresValidarConcatenados;
    }


    /**
     * Sets the valoresValidarConcatenados value for this ValidarTDCRequest.
     * 
     * @param valoresValidarConcatenados
     */
    public void setValoresValidarConcatenados(java.lang.String[] valoresValidarConcatenados) {
        this.valoresValidarConcatenados = valoresValidarConcatenados;
    }

    public java.lang.String getValoresValidarConcatenados(int i) {
        return this.valoresValidarConcatenados[i];
    }

    public void setValoresValidarConcatenados(int i, java.lang.String _value) {
        this.valoresValidarConcatenados[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ValidarTDCRequest)) return false;
        ValidarTDCRequest other = (ValidarTDCRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.identificadorOperacion==null && other.getIdentificadorOperacion()==null) || 
             (this.identificadorOperacion!=null &&
              this.identificadorOperacion.equals(other.getIdentificadorOperacion()))) &&
            ((this.identificadorTDC==null && other.getIdentificadorTDC()==null) || 
             (this.identificadorTDC!=null &&
              this.identificadorTDC.equals(other.getIdentificadorTDC()))) &&
            ((this.listaCoordenadas==null && other.getListaCoordenadas()==null) || 
             (this.listaCoordenadas!=null &&
              java.util.Arrays.equals(this.listaCoordenadas, other.getListaCoordenadas()))) &&
            ((this.tipoElementoSeguridad==null && other.getTipoElementoSeguridad()==null) || 
             (this.tipoElementoSeguridad!=null &&
              this.tipoElementoSeguridad.equals(other.getTipoElementoSeguridad()))) &&
            ((this.valoresValidarConcatenados==null && other.getValoresValidarConcatenados()==null) || 
             (this.valoresValidarConcatenados!=null &&
              java.util.Arrays.equals(this.valoresValidarConcatenados, other.getValoresValidarConcatenados())));
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
        if (getIdentificadorOperacion() != null) {
            _hashCode += getIdentificadorOperacion().hashCode();
        }
        if (getIdentificadorTDC() != null) {
            _hashCode += getIdentificadorTDC().hashCode();
        }
        if (getListaCoordenadas() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getListaCoordenadas());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getListaCoordenadas(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTipoElementoSeguridad() != null) {
            _hashCode += getTipoElementoSeguridad().hashCode();
        }
        if (getValoresValidarConcatenados() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getValoresValidarConcatenados());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getValoresValidarConcatenados(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ValidarTDCRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.novatronic.com/", "validarTDCRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificadorOperacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "identificadorOperacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificadorTDC");
        elemField.setXmlName(new javax.xml.namespace.QName("", "identificadorTDC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaCoordenadas");
        elemField.setXmlName(new javax.xml.namespace.QName("", "listaCoordenadas"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://jaxb.dev.java.net/array", "stringArray"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoElementoSeguridad");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoElementoSeguridad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valoresValidarConcatenados");
        elemField.setXmlName(new javax.xml.namespace.QName("", "valoresValidarConcatenados"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
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
