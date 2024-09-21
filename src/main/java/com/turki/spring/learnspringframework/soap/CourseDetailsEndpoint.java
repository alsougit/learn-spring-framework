package com.turki.spring.learnspringframework.soap;


import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import myschool.courses.CourseDetails;
import myschool.courses.GetCourseDetailsRequest;
import myschool.courses.GetCourseDetailsResponse;

@Endpoint
public class CourseDetailsEndpoint {

    // input - GetCourseDetailsRequest
    // output - GetCourseDetailsResponse
    // namespace - http://mySchool/courses
    // service - GetCourseDetailsRequest
    private static final String NAMESPACE_URI= "http://mySchool/courses"; 
    // create the method process xml request and response
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetCourseDetailsRequest") 
    @ResponsePayload
    public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {
        GetCourseDetailsResponse response = new GetCourseDetailsResponse();
        CourseDetails courseDetails = new CourseDetails();
        courseDetails.setId(request.getId());
        courseDetails.setName("Soap Webservices Course");
        courseDetails.setDescription("This course would explain the basics of webservices");
        response.setCourseDetails(courseDetails);
        return response;
    }

}
