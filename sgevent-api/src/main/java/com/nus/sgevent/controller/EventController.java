package com.nus.sgevent.controller;

import com.nus.sgevent.model.Event;
import com.nus.sgevent.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    // Publish a new event
    @PostMapping("/publish")
    public Event publishEvent(@RequestBody Event event) {
        return eventRepository.save(event);
    }

    // Register for an event
    @PostMapping("/{eventId}/register")
    public ResponseEntity<?> registerForEvent(@PathVariable Long eventId, @RequestParam Long userId) {
        // Implement registration logic here
        // This could involve updating an EventRegistration entity linking users and events
        return ResponseEntity.ok().body("Registered successfully");
    }

    // Cancel event registration
    @PostMapping("/{eventId}/cancel")
    public ResponseEntity<?> cancelRegistration(@PathVariable Long eventId, @RequestParam Long userId) {
        // Implement cancellation logic here
        // This might require finding the EventRegistration by eventId and userId, then deleting it
        return ResponseEntity.ok().body("Registration cancelled successfully");
    }

    // Review an event
    @PostMapping("/{eventId}/review")
    public ResponseEntity<?> reviewEvent(@PathVariable Long eventId, @RequestParam Long userId, @RequestParam int rating, @RequestParam String comment) {
        // Implement review logic here
        // This could involve creating or updating a Review entity associated with the event
        return ResponseEntity.ok().body("Reviewed successfully");
    }

    // Retrieve all events
    @GetMapping
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // Delete an event
    @DeleteMapping("/{eventId}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long eventId) {
        if (!eventRepository.existsById(eventId)) {
            return ResponseEntity.notFound().build();
        }
        eventRepository.deleteById(eventId);
        return ResponseEntity.ok().body("Event deleted successfully");
    }

    // Manage event registration approval or rejection
    @PostMapping("/{eventId}/registration/{action}")
    public ResponseEntity<?> manageRegistration(@PathVariable Long eventId, @RequestParam Long userId, @PathVariable String action) {
        // Implement approval or rejection logic here
        // This might involve updating the status of an EventRegistration entity
        if ("accept".equals(action)) {
            // Update registration to accepted
            return ResponseEntity.ok().body("Registration accepted");
        } else if ("reject".equals(action)) {
            // Update registration to rejected
            return ResponseEntity.ok().body("Registration rejected");
        } else {
            return ResponseEntity.badRequest().body("Invalid action");
        }
    }
}
