package com.example.security_demo.events;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Event {

    private Integer eventId;

    private String eventName;

    public Event(Integer eventId, String eventName) {
        this.eventId = eventId;
        this.eventName = eventName;
    }
}
