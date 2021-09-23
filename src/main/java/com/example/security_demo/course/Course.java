package com.example.security_demo.course;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Data
@NoArgsConstructor
@Entity(name = "course")
@Table(
        name = "course", //give table a name
        uniqueConstraints = {
                @UniqueConstraint(name = "course_courseName_unique", columnNames = "courseName") // name unique constraint
        }
)
public class Course {

    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1 //How much will the sequence increase each time. 1 = 1,2,3 etc
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "course_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long courseId;

    @Column(
            name = "courseName",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String courseName;

    @Column(
            name = "courseRating",
            nullable = false,
            columnDefinition = "DECIMAL"
    )
    private double courseRating;

    @Column(
            name = "courseSlope",
            nullable = false,
            columnDefinition = "NUMERIC"
    )
    private int courseSlope;

    @Column(
            name = "coursePar",
            nullable = false,
            columnDefinition = "NUMERIC"
    )
    private int coursePar;


    public Course(Long courseId, String courseName, double courseRating, int courseSlope, int coursePar) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseRating = courseRating;
        this.courseSlope = courseSlope;
        this.coursePar = coursePar;
    }
}




