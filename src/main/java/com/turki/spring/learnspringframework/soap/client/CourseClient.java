package com.turki.spring.learnspringframework.soap.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class CourseClient extends WebServiceGatewaySupport {

    @Autowired
    private WebServiceTemplate template;
    
    public Object callWebService(String url, Object requesst){
        return template.marshalSendAndReceive(url, requesst);
    }
}
