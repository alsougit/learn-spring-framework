package com.turki.spring.learnspringframework.soap;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

//Enabling Spring Web Services
@EnableWs
// Spring Configurations
@Configuration
public class WebServiceConfig {

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

}
