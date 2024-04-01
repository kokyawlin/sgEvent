package com.nus.sgevent.controller;

import java.util.Date;
import java.util.UUID;

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
	
	@PostMapping(path="/create") // Map ONLY POST Requests
	public @ResponseBody String addNewNotification(@RequestParam UUID event_id
			, @RequestParam UUID userid
			, @RequestParam String notimessage) {


		Notification noti = new Notification();
		noti.setUserId(userid);
		noti.setEventId(event_id);
		noti.setNotificationDt(new Date());
		noti.setNotiMessage(notimessage);
		
		notiRepository.save(noti);
		return "Saved";
	}
	
}
