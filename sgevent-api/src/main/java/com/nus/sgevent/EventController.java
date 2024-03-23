package com.nus.sgevent;

import com.nus.sgevent.entity.Event;
import com.nus.sgevent.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/v1/event")
public class EventController {

  @Autowired
  private EventRepository eventRepository;

  @GetMapping(path = "/all")
  public @ResponseBody Iterable<Event> getAllEvents() {
    // This returns a JSON or XML with the users
    return eventRepository.findAll();
  }
}
