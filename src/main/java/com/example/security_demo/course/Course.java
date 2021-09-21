package com.example.security_demo.course;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Course {

    private Integer courseId;

    private String courseName;

    private double courseRating;

    public Course(Integer courseId, String courseName, double courseRating) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseRating = courseRating;
    }
}




