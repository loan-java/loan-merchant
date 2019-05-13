package com.mod.loan.kuaiqian.schema.ma.mbrinfo.balance;

public class BalanceProxy implements Balance {
  private String _endpoint = null;
  private Balance balance = null;
  
  public BalanceProxy() {
    _initBalanceProxy();
  }
  
  public BalanceProxy(String endpoint) {
    _endpoint = endpoint;
    _initBalanceProxy();
  }
  
  private void _initBalanceProxy() {
    try {
      balance = (new BalanceServiceLocator()).getBalanceSoap11();
      if (balance != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)balance)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)balance)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (balance != null)
      ((javax.xml.rpc.Stub)balance)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public Balance getBalance() {
    if (balance == null)
      _initBalanceProxy();
    return balance;
  }
  
  public BalanceResponse balance(BalanceRequest balanceRequest) throws java.rmi.RemoteException{
    if (balance == null)
      _initBalanceProxy();
    return balance.balance(balanceRequest);
  }
  
  
}