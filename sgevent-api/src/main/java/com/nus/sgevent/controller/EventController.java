package com.nus.sgevent.controller;

import com.nus.sgevent.entity.Event;
import com.nus.sgevent.entity.EventRegistration;
import com.nus.sgevent.entity.EventUser;
import com.nus.sgevent.entity.JsonResponse;
import com.nus.sgevent.entity.eventObj;
import com.nus.sgevent.repository.EventRegisterRepository;
import com.nus.sgevent.repository.EventRepository;
import com.nus.sgevent.repository.UserRepository;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/v1/event")
public class EventController {

  @Autowired
  private EventRepository eventRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private EventRegisterRepository eventregisterRepository;

  @GetMapping(path = "/all")
  public @ResponseBody ResponseEntity<?> getAllEvents(
    @RequestHeader Map<String, String> header
  ) {
    // Getting event registration list
    UUID userid = UUID.fromString(header.get("userid"));
    Iterable<EventRegistration> ERegList = eventregisterRepository.findAll();
    Supplier<Stream<EventRegistration>> streamEvtRegList = () ->
      StreamSupport.stream(ERegList.spliterator(), false);
    //Getting all events
    Iterable<Event> evtList = eventRepository.findAll();
    Map<UUID, eventObj> map = new HashMap<>();
    for (Event event : evtList) {
      map.put(event.getEventId(), new eventObj(event));
      eventObj existingEvent = map.get(event.getEventId());
      //check whether current user has registred the event
      boolean isRegistered =
        streamEvtRegList
          .get()
          .filter(evt ->
            evt.getEventId().equals(event.getEventId()) &&
            evt.getUserId().equals(userid)
          )
          .count() >
        0;
      //get number of users who has registered to the event
      long regCount = streamEvtRegList
        .get()
        .filter(evt -> evt.getEventId().equals(event.getEventId()))
        .count();
      existingEvent.setRegistrationCount(regCount);
      existingEvent.setRegistered(isRegistered);
    }
    List<eventObj> mergedArray = new ArrayList<eventObj>(map.values());
    return ResponseEntity.ok(mergedArray);
  }

  @PostMapping(path = "/create") // Map ONLY POST Requests
  public @ResponseBody ResponseEntity<Object> addNewEvent(
    @RequestBody Event event
  ) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request
    event.setEventCreateDt(new Date());
    event.setEventStatus("Open");
    eventRepository.save(event);
    return ResponseEntity.ok(new JsonResponse(true, "Add Event successful."));
  }

  @DeleteMapping(path = "/delete/{id}") // Map ONLY DELETE Requests
  public @ResponseBody ResponseEntity<Object> deleteEvent(
    @PathVariable("id") UUID id
  ) {
    eventRepository.deleteById(id);

    return ResponseEntity.ok(
      new JsonResponse(true, "Delete event successful.")
    );
  }

  @GetMapping("/details/{eventid}")
  public ResponseEntity<?> retrieveEventDetails(
    @PathVariable("eventid") String eventid
  ) {
    try {
      UUID uuid = UUID.fromString(eventid);
      List<EventUser> userList = queryEventUsersById(uuid);
      Optional<Event> evt = eventRepository.findById(uuid);
      eventObj evtDetails = new eventObj(evt.get());
      evtDetails.setUserList(userList);
      evtDetails.setRegistrationCount(userList.size());

      return ResponseEntity.ok(evtDetails);
    } catch (NullPointerException ex) {
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND,
        "Event Not Found!"
      );
    }
  }

  @PostMapping(path = "/update") // Map ONLY POST Requests
  public @ResponseBody ResponseEntity<Object> UpdateStatus(
    @RequestBody Event event
  ) {
    int updatestatus = eventRepository.UpdateEvent(
      event.getEventId(),
      event.getEventTitle(),
      event.getEventDesc(),
      event.getEventCover(),
      event.getEventPlace(),
      event.getEventStartDt(),
      event.getEventEndDt(),
      event.getEventCapacity()
    );

    if (updatestatus == 1) {
      return ResponseEntity
        .status(HttpStatus.OK)
        .body(new JsonResponse(true, "Update event successful."));
    } else {
      throw new ResponseStatusException(
        HttpStatus.NOT_MODIFIED,
        "Update Error"
      );
    }
  }

  @GetMapping(path = "/register/{eventid}/{userid}") // Map ONLY POST Requests
  public ResponseEntity<?> RegisterEvent(
    @PathVariable("eventid") String eventid,
    @PathVariable("userid") String userid
  ) {
    try {
      EventRegistration EReg = new EventRegistration();
      EReg.setEventId(UUID.fromString(eventid));
      EReg.setRegisterDt(new Date());
      EReg.setRegisterStatus("Registered");
      EReg.setUserId(UUID.fromString(userid));
      eventregisterRepository.save(EReg);

      return ResponseEntity.ok(
        new JsonResponse(true, "register event successful.")
      );
    } catch (NullPointerException ex) {
      return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(new JsonResponse(false, "register event failed."));
    }
  }

  @GetMapping(path = "/unregister/{eventid}/{userid}") // Map ONLY POST Requests
  public ResponseEntity<?> UnregisterEvent(
    @PathVariable("eventid") String eventid,
    @PathVariable("userid") String userid
  ) {
    try {
      eventregisterRepository.deleteByIds(
        UUID.fromString(eventid),
        UUID.fromString(userid)
      );
      return ResponseEntity.ok(
        new JsonResponse(true, "Unregister event successful.")
      );
    } catch (NullPointerException ex) {
      return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(new JsonResponse(false, "Unregister event failed."));
    }
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

  private List<EventUser> queryEventUsersById(UUID eventid) {
    try {
      Iterable<EventRegistration> ERegList = eventregisterRepository.SearchEventRegister(
        eventid
      );
      Supplier<Stream<EventRegistration>> streamEvtRegList = () ->
        StreamSupport.stream(ERegList.spliterator(), false);
      List<UUID> userIds = streamEvtRegList
        .get()
        .map(u -> u.getUserId())
        .collect(Collectors.toList());
      return userRepository.findByUserIds(userIds);
    } catch (Exception ex) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No User Found");
    }
  }
}
