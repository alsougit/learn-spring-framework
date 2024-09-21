package com.turki.spring.learnspringframework.soap;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import myschool.students.GetStudentDetailsRequest;
import myschool.students.GetStudentDetailsResponse;
import myschool.students.Grade;
import myschool.students.StudentDetails;

@Endpoint
public class StudentDetailsEndpoint {

    private final String NAMESPACE_URI = "http://mySchool/students";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetStudentDetailsRequest")
    @ResponsePayload
    public GetStudentDetailsResponse processStudentDetailsRequest(@RequestPayload GetStudentDetailsRequest request){
        GetStudentDetailsResponse response = new GetStudentDetailsResponse();
        StudentDetails studentDetails = new StudentDetails();
        studentDetails.setId(request.getId());
        studentDetails.setName("Turki AKA AboSaud");
        studentDetails.setAge(31);
        studentDetails.setGrade(Grade.A);
        response.setStudentDetails(studentDetails);
        return response;
    }

}
