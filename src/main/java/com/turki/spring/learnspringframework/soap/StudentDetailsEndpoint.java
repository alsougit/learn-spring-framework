package com.turki.spring.learnspringframework.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.turki.spring.learnspringframework.Model.Student;
import com.turki.spring.learnspringframework.service.StudentService;

import myschool.students.DeleteStudentDetailsRequest;
import myschool.students.DeleteStudentDetailsResponse;
import myschool.students.GetAllStudentDetailsRequest;
import myschool.students.GetAllStudentDetailsResponse;
import myschool.students.GetStudentDetailsRequest;
import myschool.students.GetStudentDetailsResponse;
import myschool.students.Grade;
import myschool.students.StudentDetails;
import myschool.students.UpdateStudentDetailsRequest;
import myschool.students.UpdateStudentDetailsResponse;

@Endpoint
public class StudentDetailsEndpoint  {

    private static final String NAMESPACE_URI = "http://mySchool/students";
    @Autowired
    StudentService studentService;

    @PayloadRoot(namespace = NAMESPACE_URI ,localPart ="GetStudentDetailsRequest")
    @ResponsePayload
    GetStudentDetailsResponse getStudentDetailsResponse(@RequestPayload GetStudentDetailsRequest request){
        GetStudentDetailsResponse response = new GetStudentDetailsResponse();
        Student student = new Student(0, "Turki", 30, "A");
        //Student student = studentService.getStudentById(request.getId());
        response.setStudentDetails(mapStudentDetails(student));

        return response;
    }

    /**
    @PayloadRoot(namespace = NAMESPACE_URI,localPart = "GetAllStudentDetailsRequest")
    @ResponsePayload
    public GetAllStudentDetailsResponse getAllStudentDetailsResponse(@RequestPayload GetAllStudentDetailsRequest request){
        GetAllStudentDetailsResponse response = new GetAllStudentDetailsResponse();
        
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "DeleteStudentDetailsRequest")
    @ResponsePayload
    public DeleteStudentDetailsResponse deleteStudentDetailsResponse(@RequestPayload DeleteStudentDetailsRequest request){
        DeleteStudentDetailsResponse response = new DeleteStudentDetailsResponse();

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "UpdateStudentDetailsRequest")
    @ResponsePayload
    UpdateStudentDetailsResponse updateStudentDetailsResponse(@RequestPayload UpdateStudentDetailsRequest request){
        UpdateStudentDetailsResponse response = new UpdateStudentDetailsResponse();

        return response;
    }

 */
    public StudentDetails mapStudentDetails(Student student){
        StudentDetails studentDetails = new StudentDetails();
        studentDetails.setId(student.getId());
        studentDetails.setName(student.getName());
        studentDetails.setAge(student.getAge());
        
        myschool.students.Grade grade = mapStudentGrades(student.getStudentGrade());
        if(grade != null)
        studentDetails.setGrade(grade);
        
        return studentDetails;
    }

    public myschool.students.Grade mapStudentGrades(String studentGrade){
        for(Grade grade : myschool.students.Grade.values()){
            if(studentGrade.equalsIgnoreCase(grade.value()))
            return grade;
        }
        return null;
    }
}
