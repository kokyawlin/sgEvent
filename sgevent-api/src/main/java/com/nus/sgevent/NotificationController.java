package com.nus.sgevent;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;


import com.nus.sgevent.entity.Event;


import com.nus.sgevent.repository.EventRepository;



@Controller	// This means that this class is a Controller
@RequestMapping(path="/v1/noti")
public class NotificationController {

	
}