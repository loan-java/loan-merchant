/**
 * BalanceRequest.java
 * <p>
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.mod.loan.pay.kuaiqian.schema.ma.mbrinfo.balance;

import com.mod.loan.pay.kuaiqian.schema.ma.mbrinfo.MaMbrinfoRequestHead;

public class BalanceRequest implements java.io.Serializable {
    private MaMbrinfoRequestHead requestHeader;

    private BalanceRequestBody requestBody;

    public BalanceRequest() {
    }

    public BalanceRequest(
            MaMbrinfoRequestHead requestHeader,
            BalanceRequestBody requestBody) {
        this.requestHeader = requestHeader;
        this.requestBody = requestBody;
    }


    /**
     * Gets the requestHeader value for this BalanceRequest.
     *
     * @return requestHeader
     */
    public MaMbrinfoRequestHead getRequestHeader() {
        return requestHeader;
    }


    /**
     * Sets the requestHeader value for this BalanceRequest.
     *
     * @param requestHeader
     */
    public void setRequestHeader(MaMbrinfoRequestHead requestHeader) {
        this.requestHeader = requestHeader;
    }


    /**
     * Gets the requestBody value for this BalanceRequest.
     *
     * @return requestBody
     */
    public BalanceRequestBody getRequestBody() {
        return requestBody;
    }


    /**
     * Sets the requestBody value for this BalanceRequest.
     *
     * @param requestBody
     */
    public void setRequestBody(BalanceRequestBody requestBody) {
        this.requestBody = requestBody;
    }

    private Object __equalsCalc = null;

    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof BalanceRequest)) return false;
        BalanceRequest other = (BalanceRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
                ((this.requestHeader == null && other.getRequestHeader() == null) ||
                        (this.requestHeader != null &&
                                this.requestHeader.equals(other.getRequestHeader()))) &&
                ((this.requestBody == null && other.getRequestBody() == null) ||
                        (this.requestBody != null &&
                                this.requestBody.equals(other.getRequestBody())));
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
        if (getRequestHeader() != null) {
            _hashCode += getRequestHeader().hashCode();
        }
        if (getRequestBody() != null) {
            _hashCode += getRequestBody().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
            new org.apache.axis.description.TypeDesc(BalanceRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.99bill.com/schema/ma/mbrinfo/balance", "balanceRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestHeader");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.99bill.com/schema/ma/mbrinfo/balance", "request-header"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.99bill.com/schema/ma/mbrinfo", "ma-mbrinfo-request-head"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestBody");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.99bill.com/schema/ma/mbrinfo/balance", "request-body"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.99bill.com/schema/ma/mbrinfo/balance", "balance-request-body"));
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
                new org.apache.axis.encoding.ser.BeanSerializer(
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
                new org.apache.axis.encoding.ser.BeanDeserializer(
                        _javaType, _xmlType, typeDesc);
    }

}
