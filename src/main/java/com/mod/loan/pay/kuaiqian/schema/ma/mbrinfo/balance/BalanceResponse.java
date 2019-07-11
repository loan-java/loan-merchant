/**
 * BalanceResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.mod.loan.pay.kuaiqian.schema.ma.mbrinfo.balance;

import com.mod.loan.pay.kuaiqian.schema.ma.mbrinfo.MaMbrinfoResponseHead;

public class BalanceResponse  implements java.io.Serializable {
    private MaMbrinfoResponseHead responseHeader;

    private BalanceResponseBody responseBody;

    public BalanceResponse() {
    }

    public BalanceResponse(
           MaMbrinfoResponseHead responseHeader,
           BalanceResponseBody responseBody) {
           this.responseHeader = responseHeader;
           this.responseBody = responseBody;
    }


    /**
     * Gets the responseHeader value for this BalanceResponse.
     * 
     * @return responseHeader
     */
    public MaMbrinfoResponseHead getResponseHeader() {
        return responseHeader;
    }


    /**
     * Sets the responseHeader value for this BalanceResponse.
     * 
     * @param responseHeader
     */
    public void setResponseHeader(MaMbrinfoResponseHead responseHeader) {
        this.responseHeader = responseHeader;
    }


    /**
     * Gets the responseBody value for this BalanceResponse.
     * 
     * @return responseBody
     */
    public BalanceResponseBody getResponseBody() {
        return responseBody;
    }


    /**
     * Sets the responseBody value for this BalanceResponse.
     * 
     * @param responseBody
     */
    public void setResponseBody(BalanceResponseBody responseBody) {
        this.responseBody = responseBody;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof BalanceResponse)) return false;
        BalanceResponse other = (BalanceResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.responseHeader==null && other.getResponseHeader()==null) ||
             (this.responseHeader!=null &&
              this.responseHeader.equals(other.getResponseHeader()))) &&
            ((this.responseBody==null && other.getResponseBody()==null) ||
             (this.responseBody!=null &&
              this.responseBody.equals(other.getResponseBody())));
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
        if (getResponseHeader() != null) {
            _hashCode += getResponseHeader().hashCode();
        }
        if (getResponseBody() != null) {
            _hashCode += getResponseBody().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BalanceResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.99bill.com/schema/ma/mbrinfo/balance", "balanceResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("responseHeader");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.99bill.com/schema/ma/mbrinfo/balance", "response-header"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.99bill.com/schema/ma/mbrinfo", "ma-mbrinfo-response-head"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("responseBody");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.99bill.com/schema/ma/mbrinfo/balance", "response-body"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.99bill.com/schema/ma/mbrinfo/balance", "balance-response-body"));
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
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
