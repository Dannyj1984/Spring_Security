package com.example.security_demo.events;

import com.example.security_demo.member.Member;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/events")
public class EventController {


    //A list of new eventd
    private static final List<Event> EVENTS = Arrays.asList(
            new Event(1, "Stamford"),
            new Event(2, "Burnley"),
            new Event(3, "Disley")
    );

    @GetMapping
    public List<Event> getAllEvents() {
        System.out.println("GetAllEvents");
        return EVENTS;
    }

    //path the view event details after the original path in requestmapping
    @GetMapping(path = "{eventId}")
    public Event getEvent(@PathVariable("eventId") Integer eventId){
        return EVENTS.stream().filter(event -> eventId.equals(event.getEventId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Event " + eventId   + " does not exist" ));
    }
}
