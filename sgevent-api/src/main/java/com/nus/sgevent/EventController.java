package com.nus.sgevent;

import com.nus.sgevent.entity.Event;
import com.nus.sgevent.entity.EventRegistration;
import com.nus.sgevent.repository.EventRegisterRepository;
import com.nus.sgevent.repository.EventRepository;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/v1/event")
public class EventController {

  @Autowired
  private EventRepository eventRepository;

  @Autowired
  private EventRegisterRepository eventregisterRepository;

  @GetMapping(path = "/all")
  public @ResponseBody Iterable<Event> getAllEvents() {
    // This returns a JSON or XML with the users
    return eventRepository.findAll();
  }

  @PostMapping(path = "/create") // Map ONLY POST Requests
  public @ResponseBody String addNewUser(
    @RequestParam String EventCapicity,
    @RequestParam String EventTitle,
    @RequestParam String EventDescription,
    @RequestParam String OwnerId,
    @RequestParam String EventPlace,
    @RequestParam Date EventStartDate,
    @RequestParam Date EventEndDate
  ) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request

    Event n = new Event();
    n.setEventCapicity(EventCapicity);
    n.setEventCreateDt(new Date());
    n.setEventDesc(EventDescription);
    n.setEventOwnerId(OwnerId);
    n.setEventPlace(EventPlace);
    n.setEventStartDt(EventStartDate);
    n.setEventEndDt(EventEndDate);
    n.setEventStatus("Created");
    n.setEventTitle(EventTitle);
    eventRepository.save(n);
    return "Saved";
  }

  @PostMapping(path = "/updatestatus") // Map ONLY POST Requests
  public @ResponseBody String UpdateStatus(
    @RequestParam String EventTitle,
    @RequestParam String OwnerId,
    @RequestParam String EventStatus
  ) {
    eventRepository.UpdateEvent(EventTitle, OwnerId, EventStatus);

    //eventRepository.save(n);
    return "Updated";
  }

  @PostMapping(path = "/register") // Map ONLY POST Requests
  public @ResponseBody String RegisterEvent(
    @RequestParam String MemberId,
    @RequestParam int EventId
  ) {
    EventRegistration EReg = new EventRegistration();
    EReg.setEventId(EventId);
    EReg.setRegisterDt(new Date());
    EReg.setRegisterStatus("Registered");
    EReg.setUserId(MemberId);
    eventregisterRepository.save(EReg);

    return "Updated";
  }

  @GetMapping("/{title}")
  public ResponseEntity<?> searchEventByTitle(
    @PathVariable("title") String title
  ) {
    Event evnt = null;
    try {
      evnt = eventRepository.SearchEventByTitle(title);

      return ResponseEntity.ok(evnt);
      // return UserFound;
    } catch (Exception ex) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Event Found");
    }
  }
}
