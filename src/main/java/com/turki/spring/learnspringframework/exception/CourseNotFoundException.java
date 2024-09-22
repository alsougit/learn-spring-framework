package com.turki.spring.learnspringframework.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;
/**
 * This class to be implemented later for exception handling of soap fault inputs
 */
@SoapFault(faultCode = FaultCode.CLIENT)
public class CourseNotFoundException extends RuntimeException {
     private static final long serialVersionUID=3518170101751491969L;
    public CourseNotFoundException(String message){
        super(message);
    }

}
