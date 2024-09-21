package com.turki.spring.learnspringframework.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.turki.spring.learnspringframework.Model.Course;
import com.turki.spring.learnspringframework.Service.CourseDetailsService;
import com.turki.spring.learnspringframework.Service.CourseDetailsService.StatusEnum;
import com.turki.spring.learnspringframework.exception.CourseNotFoundException;

import myschool.courses.CourseDetails;
import myschool.courses.DeleteCourseDetailsRequest;
import myschool.courses.DeleteCourseDetailsResponse;
import myschool.courses.GetAllCourseDetailsRequest;
import myschool.courses.GetAllCourseDetailsResponse;
import myschool.courses.GetCourseDetailsRequest;
import myschool.courses.GetCourseDetailsResponse;
import myschool.courses.Status;

@Endpoint
public class CourseDetailsEndpoint {

    @Autowired
    CourseDetailsService service;

    // input - GetCourseDetailsRequest
    // output - GetCourseDetailsResponse
    // namespace - http://mySchool/courses
    // service - GetCourseDetailsRequest
    private static final String NAMESPACE_URI = "http://mySchool/courses";

    // create the method process xml request and response
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetCourseDetailsRequest")
    @ResponsePayload
    public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {
        Course course = service.findById(request.getId());
       
        return mapCourseDetails(course);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAllCourseDetailsRequest")
    @ResponsePayload
    public GetAllCourseDetailsResponse processAllCourseDetailsRequest(
            @RequestPayload GetAllCourseDetailsRequest request) {
        List<Course> courses = service.findAll();
        return mapAllCourseDetails(courses);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "DeleteCourseDetailsRequest")
    @ResponsePayload
    public DeleteCourseDetailsResponse deleteCourseDetailsRequest(@RequestPayload DeleteCourseDetailsRequest request) {
        StatusEnum status = service.deleteById(request.getId());
        DeleteCourseDetailsResponse deleteResponse = new DeleteCourseDetailsResponse();
        deleteResponse.setStatus(mapStatus(status));
        return deleteResponse;

    }

    public GetAllCourseDetailsResponse mapAllCourseDetails(List<Course> courses) {
        GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
        for (Course course : courses) {
            CourseDetails courseDetails = mapCourse(course);
            response.getCourseDetails().add(courseDetails);
        }
        return response;

    }

    public GetCourseDetailsResponse mapCourseDetails(Course course) {
        GetCourseDetailsResponse response = new GetCourseDetailsResponse();

        CourseDetails courseDetails = mapCourse(course);

        response.setCourseDetails(courseDetails);
        return response;
    }

    public CourseDetails mapCourse(Course course) {
        CourseDetails courseDetails = new CourseDetails();
        courseDetails.setId(course.getId());
        courseDetails.setName(course.getName());
        courseDetails.setDescription(course.getDescription());
        return courseDetails;
    }

    public myschool.courses.Status mapStatus(StatusEnum status) {
        if (status == StatusEnum.SUCCESS)
            return Status.SUCCESS;

        return Status.FAILURE;
    }

}
