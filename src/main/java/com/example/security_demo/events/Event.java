package com.example.security_demo.events;

import com.example.security_demo.course.Course;
import com.example.security_demo.member.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

import static javax.persistence.GenerationType.SEQUENCE;

@Data
@NoArgsConstructor
@Entity(name = "event")
@Table(
        name = "event", //give table a name
        uniqueConstraints = {
                @UniqueConstraint(name = "event_name_unique", columnNames = "eventName") // name unique constraint
        }
)
public class Event {

    @Id
    @SequenceGenerator(
            name = "event_sequence",
            sequenceName = "event_sequence",
            allocationSize = 1 //How much will the sequence increase each time. 1 = 1,2,3 etc
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "event_sequence"
    )
    @Column(
            name = "eventid",
            updatable = false
    )
    private Long eventId;

    @Column(
            name = "eventname",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String eventName;

    @Column(
            name = "eventdate",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private LocalDateTime eventDate;

    @Column(
            name = "courseid",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private Long courseId;

//    @ManyToMany
//    private Set<Member> attendees;
//
//    @OneToOne
//    private Course course;

    public Event(Long eventId, String eventName, Long courseId) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.courseId = courseId;
    }
}
