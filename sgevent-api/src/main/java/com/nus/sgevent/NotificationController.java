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
import com.nus.sgevent.entity.Notification;
import com.nus.sgevent.repository.EventRepository;
import com.nus.sgevent.repository.NotificationRepository;
import com.nus.sgevent.repository.RoleRepository;



@Controller	// This means that this class is a Controller
@RequestMapping(path="/v1/noti")
public class NotificationController {

	@Autowired
	private NotificationRepository notiRepository;
	
	@PostMapping(path="/add") // Map ONLY POST Requests
	public @ResponseBody String addNewUser (@RequestParam String event_id
			, @RequestParam String userid
			, @RequestParam String password
			, @RequestParam int activeflag
			, @RequestParam String email) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		Notification n = new Notification();
		n.setUserId(userid);
		
		notiRepository.save(n);
		return "Saved";
	}
	
}
