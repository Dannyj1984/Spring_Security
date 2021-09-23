package com.example.security_demo.events;

import com.example.security_demo.member.Member;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/events")
public class EventManagementController {

    //A list of new events
    private static final List<Event> EVENTS = Arrays.asList(
            new Event(1L, "Stamford", 1L),
            new Event(2L, "Burnley", 2L),
            new Event(3L, "Disley", 3L)
    );

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EVENTADMIN')")
    public List<Event> getAllEvents() {
        System.out.println("GetAllEvents");
        return EVENTS;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('event:write')")
    public void registerNewEvent(@RequestBody Event event) {
        System.out.println("registerNewEvent");
        System.out.println(event);
        //TODO logic to register new event
    }

    @DeleteMapping(path = "{eventId}")
    @PreAuthorize("hasAuthority('event:write')")
    public void deleteEvent(@PathVariable("eventId") Integer eventId) {
        System.out.println("deleteEvent");
        System.out.println(eventId);
        //TODO logic to delete member
    }

    @PutMapping(path = "{eventId}")
    @PreAuthorize("hasAuthority('event:write')")
    public void updateEvent(@PathVariable("eventId") Integer eventId, @RequestBody Event event) {
        System.out.println("updateEvent");
        System.out.println(String.format("%s %s", eventId, event));
    }
}
