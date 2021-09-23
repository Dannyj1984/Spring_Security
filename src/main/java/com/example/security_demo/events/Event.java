package com.example.security_demo.events;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
            name = "id",
            updatable = false
    )
    private Long eventId;

    @Column(
            name = "eventName",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String eventName;

    @Column(
            name = "courseId",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private Long courseId;

    public Event(Long eventId, String eventName, Long courseId) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.courseId = courseId;
    }
}
