/**
 * BalanceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.mod.loan.pay.kuaiqian.schema.ma.mbrinfo.balance;

public class BalanceServiceLocator extends org.apache.axis.client.Service implements BalanceService {

    public BalanceServiceLocator() {
    }


    public BalanceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public BalanceServiceLocator(String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for BalanceSoap11
    private String BalanceSoap11_address = "http://www.99bill.com:80/mbrinfo/services/balance";

    public String getBalanceSoap11Address() {
        return BalanceSoap11_address;
    }

    // The WSDD service name defaults to the port name.
    private String BalanceSoap11WSDDServiceName = "BalanceSoap11";

    public String getBalanceSoap11WSDDServiceName() {
        return BalanceSoap11WSDDServiceName;
    }

    public void setBalanceSoap11WSDDServiceName(String name) {
        BalanceSoap11WSDDServiceName = name;
    }

    public Balance getBalanceSoap11() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(BalanceSoap11_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getBalanceSoap11(endpoint);
    }

    public Balance getBalanceSoap11(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            BalanceSoap11Stub _stub = new BalanceSoap11Stub(portAddress, this);
            _stub.setPortName(getBalanceSoap11WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setBalanceSoap11EndpointAddress(String address) {
        BalanceSoap11_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (Balance.class.isAssignableFrom(serviceEndpointInterface)) {
                BalanceSoap11Stub _stub = new BalanceSoap11Stub(new java.net.URL(BalanceSoap11_address), this);
                _stub.setPortName(getBalanceSoap11WSDDServiceName());
                return _stub;
            }
        }
        catch (Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        String inputPortName = portName.getLocalPart();
        if ("BalanceSoap11".equals(inputPortName)) {
            return getBalanceSoap11();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.99bill.com/schema/ma/mbrinfo/balance", "BalanceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.99bill.com/schema/ma/mbrinfo/balance", "BalanceSoap11"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(String portName, String address) throws javax.xml.rpc.ServiceException {

if ("BalanceSoap11".equals(portName)) {
            setBalanceSoap11EndpointAddress(address);
        }
        else
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
