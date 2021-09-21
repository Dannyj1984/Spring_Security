package com.example.security_demo.course;

import com.example.security_demo.member.Member;
import org.checkerframework.checker.units.qual.C;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/courses")
public class CourseManagementController {

    //A list of new members
    private static final List<Course> COURSES = Arrays.asList(
            new Course(1, "Stamford - white", 68.9f),
            new Course(2, "Burnley - white", 70.2f),
            new Course(3, "Disley - white", 69.5f)
    );

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EVENTADMIN')")
    public List<Course> getAllCourses() {
        return COURSES;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('course:write')")
    public void registerNewCourse(@RequestBody Course course) {
        System.out.println(course);
        //TODO logic to register new course
    }

    @DeleteMapping(path = "{courseId}")
    @PreAuthorize("hasAuthority('course:write')")
    public void deleteCourse(@PathVariable("courseId") Integer courseId) {
        System.out.println(courseId);
        //TODO logic to delete course
    }

    @PutMapping(path = "{courseId}")
    @PreAuthorize("hasAuthority('course:write')")
    public void updateCourse(@PathVariable("courseId") Integer courseId, @RequestBody Course course) {
        System.out.println(String.format("%s %s", courseId, course));
    }


}
