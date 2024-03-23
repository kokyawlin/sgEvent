package com.nus.sgevent;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.nus.sgevent.entity.Event;
import com.nus.sgevent.entity.EventUser;
import com.nus.sgevent.repository.EventRepository;



@Controller	// This means that this class is a Controller
@RequestMapping(path="/v1/event")
public class EventController {
	@Autowired 
	private EventRepository eventRepository;


	@GetMapping(path="/all")
	public @ResponseBody Iterable<Event> getAllEvents() {
		// This returns a JSON or XML with the users
		return eventRepository.findAll();
	}
	
	@PostMapping(path="/create") // Map ONLY POST Requests
	public @ResponseBody String addNewUser (@RequestParam String EventCapicity
			, @RequestParam String EventTitle
			, @RequestParam String EventDescription
			, @RequestParam String OwnerId
			, @RequestParam String EventPlace
			, @RequestParam Date EventStartDate
			, @RequestParam Date EventEndDate) {
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
	
	@PostMapping(path="/updatestatus") // Map ONLY POST Requests
	public @ResponseBody String UpdateStatus (
			 @RequestParam String EventTitle
			, @RequestParam String OwnerId
			, @RequestParam String EventStatus
			) {
		
		//eventRepository.save(n);
		return "Updated";
	}
	
}
