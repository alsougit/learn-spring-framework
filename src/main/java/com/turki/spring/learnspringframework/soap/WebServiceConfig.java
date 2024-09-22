package com.turki.spring.learnspringframework.soap;

import java.util.Collections;
import java.util.List;

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

//Enabling Spring Web Services
@EnableWs
// Spring Configurations
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter{
    private static String securementAction = "UsernameToken";

    @Bean // this servletRegistration to map the dispatcherServlet to the URL
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext context) {
        MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
        messageDispatcherServlet.setApplicationContext(context);
        messageDispatcherServlet.setTransformWsdlLocations(true);

        return new ServletRegistrationBean<>(messageDispatcherServlet, "/ws/*");

    }
    // /ws/courses.wsdl
    @Bean(name = "courses")
    public DefaultWsdl11Definition getDefaultWsdl11Definition(XsdSchema coursesSchema){
        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setPortTypeName("CoursePort");
        definition.setSchema(coursesSchema);
        definition.setTargetNamespace("http://mySchool/courses");
        definition.setLocationUri("/ws");

        return definition;
    }

    @Bean
    public XsdSchema coursesSchema(){
        return new SimpleXsdSchema(new ClassPathResource("course-details.xsd")); //course-details.xsd
    }

//#- below required to set up security in our webservices:
    //Callback Handler -> SimplePasswordValidationCallbackHandler (Handler to give what to be done when interceptor catch request sth like check usr/pwd)
    //add Wss4jSecurityInterceptor (this interceptor intercept the request)
    // Interceptor.add -> XwsSecurityInterceptor (add our security interceptor to the WsConfigurerAdaptor interceptors)
   
   //add Wss4jSecurityInterceptor 
    @Bean
    public Wss4jSecurityInterceptor securityInterceptor(){
        Wss4jSecurityInterceptor interceptor = new Wss4jSecurityInterceptor();
        interceptor.setSecurementActions("UsernameToken");
        interceptor.setSecurementUsername("user");
        interceptor.setSecurementPassword("password");
        interceptor.setValidationCallbackHandler(pwdCallBackHandler());
        
        return interceptor;
    } 

    @Bean
    public SimplePasswordValidationCallbackHandler pwdCallBackHandler(){
        SimplePasswordValidationCallbackHandler handler = new SimplePasswordValidationCallbackHandler();
        handler.setUsersMap(Collections.singletonMap("user", "password"));
        
        return handler;
    }
    // Interceptor.add -> Wss4jSecurityInterceptor 
    @Override
    public void addInterceptors(List<EndpointInterceptor> interceptors) {
        interceptors.add(securityInterceptor());
    }
    


}
