package com.turki.spring.learnspringframework.soap;

import java.util.Collections;
import java.util.List;

import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;
import org.springframework.ws.soap.security.wss4j2.callback.SimplePasswordValidationCallbackHandler;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter{

    private static final String NAMESPACE_URI = "http://mySchool/students";
    private static final String URI_MAPPING = "/ws/*";
    private static final String URI_STRING = "/ws";
    private String studentSchemaName = "student-details.xsd";
    private String studentPortType = "StudentPort";
    private String tokenSecurement = "UsernameToken";

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> servletRegistrationBean(ApplicationContext context){
        MessageDispatcherServlet dispatcher = new MessageDispatcherServlet();
        dispatcher.setApplicationContext(context);
        dispatcher.setTransformWsdlLocations(true);
        

        return new ServletRegistrationBean<MessageDispatcherServlet>(dispatcher, URI_MAPPING);
    }

    @Bean(name = "students")
    public DefaultWsdl11Definition getDefaultWsdl11Definition(){
        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setPortTypeName(studentPortType);
        definition.setLocationUri(URI_STRING);
        definition.setSchema(getStudentSchema());
        definition.setTargetNamespace(NAMESPACE_URI);


        return definition;
    }

    @Bean
    public XsdSchema getStudentSchema(){
        return new SimpleXsdSchema(new ClassPathResource(studentSchemaName));
    }

    @Bean
    public Wss4jSecurityInterceptor securityInterceptor(){

        Wss4jSecurityInterceptor interceptor = new Wss4jSecurityInterceptor();
        interceptor.setSecurementActions(tokenSecurement);
        interceptor.setSecurementUsername("Turki");
        interceptor.setSecurementPassword("password");
        // interceptor.setValidationCallbackHandler(pwdCallBackHandler());

        return interceptor;
    }

    // @Bean
    // public SimplePasswordValidationCallbackHandler pwdCallBackHandler(){
    //     SimplePasswordValidationCallbackHandler handler = new SimplePasswordValidationCallbackHandler();
    //     handler.setUsersMap(Collections.singletonMap("Turki", "password"));
    // }

    @Override
    public void addInterceptors(List<EndpointInterceptor> interceptors) {
        interceptors.add(securityInterceptor());
    }

    

}
