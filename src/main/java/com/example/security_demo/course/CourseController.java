package com.example.security_demo.course;

import com.example.security_demo.course.Course;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/courses")
public class CourseController {


    //A list of new courses
    private static final List<Course> COURSES = Arrays.asList(
            new Course(1L, "Stamford - white", 68.9, 128, 70),
            new Course(2L, "Burnley - white", 70.2, 135, 70),
            new Course(3L, "Disley - white", 69.5, 129, 70)
    );

    @GetMapping
    public List<Course> getAllCourses() {
        return COURSES;
    }

    //path the view course details after the original path in requestmapping
    @GetMapping(path = "{courseId}")
    public Course getCourse(@PathVariable("courseId") Integer courseId){
        return COURSES.stream().filter(course -> courseId.equals(course.getCourseId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Course " + courseId   + " does not exist" ));
    }
}
