package com.turki.spring.learnspringframework.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.turki.spring.learnspringframework.Model.Course;
import com.turki.spring.learnspringframework.Service.CourseService;
import com.turki.spring.learnspringframework.Service.CourseService.StatusEnum;

import myschool.courses.AddCourseDetailsRequest;
import myschool.courses.AddCourseDetailsResponse;
import myschool.courses.CourseDetails;
import myschool.courses.DeleteCourseDetailsRequest;
import myschool.courses.DeleteCourseDetailsResponse;
import myschool.courses.GetAllCoursesDetailsRequest;
import myschool.courses.GetAllCoursesDetailsResponse;
import myschool.courses.GetCourseDetailsRequest;
import myschool.courses.GetCourseDetailsResponse;
import myschool.courses.Status;

@Endpoint
public class CourseDetailsEndpoint {

    @Autowired
    CourseService service;

    // input - GetCourseDetailsRequest
    // output - GetCourseDetailsResponse
    // namespace - http://mySchool/courses
    // service - GetCourseDetailsRequest
    private static final String NAMESPACE_URI = "http://mySchool/courses";
    @Autowired
    private CourseService courseService;

    // create the method process xml request and response
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetCourseDetailsRequest")
    @ResponsePayload
    public GetCourseDetailsResponse getCourseDetails(@RequestPayload GetCourseDetailsRequest request) {

        Course course = courseService.getCourse(request.getId());
        CourseDetails courseDetails = mapCourseDetails(course);
        GetCourseDetailsResponse response = new GetCourseDetailsResponse();
        response.setCourseDetails(courseDetails);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAllCoursesDetailsRequest")
    @ResponsePayload
    public GetAllCoursesDetailsResponse getAllCoursesDetails(
            @RequestPayload GetAllCoursesDetailsRequest request) {
        List<Course> courses = courseService.getAllCourses();

        GetAllCoursesDetailsResponse response = new GetAllCoursesDetailsResponse();
        for(Course course:courses){
            CourseDetails details = mapCourseDetails(course);
            response.getCourseDetails().add(details);
        }
        
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "AddCourseDetailsRequest")
    @ResponsePayload
    public AddCourseDetailsResponse addCourseDetails(@RequestPayload AddCourseDetailsRequest request) {
        CourseDetails courseDetails = request.getCourseDetails();

        Status status = mapStatus(addCourseDetails(courseDetails));
        AddCourseDetailsResponse response = new AddCourseDetailsResponse();
        response.setStatus(status);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "DeleteCourseDetailsRequest")
    @ResponsePayload
    public DeleteCourseDetailsResponse deleteCourseDetailsRequest(@RequestPayload DeleteCourseDetailsRequest request) {

        DeleteCourseDetailsResponse deleteResponse = new DeleteCourseDetailsResponse();

        if(request.getId() != null){
            StatusEnum statusEnum = courseService.deleteCourse(request.getId());
            deleteResponse.setStatus(mapStatus(statusEnum));
        }else if(!request.getName().equals(null)){
            StatusEnum statusEnum = courseService.deleteCourse(request.getName());
            deleteResponse.setStatus(mapStatus(statusEnum));
        }else{
            deleteResponse.setStatus(Status.FAILURE);
        }
        return deleteResponse;

    }

    public CourseDetails mapCourseDetails(Course course) {
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

    public StatusEnum addCourseDetails(CourseDetails details) {
        String courseName = details.getName();
        String courseDesc = details.getDescription();
        Course newCourse = new Course(courseName, courseDesc);

        return courseService.addCourse(newCourse);
    }

    

}
