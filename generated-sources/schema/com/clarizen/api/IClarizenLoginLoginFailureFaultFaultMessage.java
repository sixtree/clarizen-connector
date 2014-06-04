
package com.clarizen.api;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.5.1
 * 2014-04-10T08:40:54.386-05:00
 * Generated source version: 2.5.1
 */

@WebFault(name = "LoginFailure", targetNamespace = "http://clarizen.com/api/faults")
public class IClarizenLoginLoginFailureFaultFaultMessage extends Exception {
    
    private com.clarizen.api.faults.LoginFailure loginFailure;

    public IClarizenLoginLoginFailureFaultFaultMessage() {
        super();
    }
    
    public IClarizenLoginLoginFailureFaultFaultMessage(String message) {
        super(message);
    }
    
    public IClarizenLoginLoginFailureFaultFaultMessage(String message, Throwable cause) {
        super(message, cause);
    }

    public IClarizenLoginLoginFailureFaultFaultMessage(String message, com.clarizen.api.faults.LoginFailure loginFailure) {
        super(message);
        this.loginFailure = loginFailure;
    }

    public IClarizenLoginLoginFailureFaultFaultMessage(String message, com.clarizen.api.faults.LoginFailure loginFailure, Throwable cause) {
        super(message, cause);
        this.loginFailure = loginFailure;
    }

    public com.clarizen.api.faults.LoginFailure getFaultInfo() {
        return this.loginFailure;
    }
}