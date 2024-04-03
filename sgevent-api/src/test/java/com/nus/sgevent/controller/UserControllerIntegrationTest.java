package com.nus.sgevent.controller;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.nus.sgevent.repository.EventRegisterRepository;
import com.nus.sgevent.repository.EventRepository;
import com.nus.sgevent.repository.EventReviewRepository;
import com.nus.sgevent.repository.NotificationRepository;
import com.nus.sgevent.repository.RoleRepository;
import com.nus.sgevent.repository.UserRepository;
import com.nus.sgevent.entity.*;
import org.junit.Test;

@WebMvcTest(value = UserController.class)
@RunWith(SpringRunner.class)
public class UserControllerIntegrationTest {

	 @Autowired
	 private MockMvc mvc;
	 @MockBean
	 private UserRepository userRepository;
	 
	 @MockBean
	 private EventRepository eventRepository;
	 
	 @MockBean
	 private EventRegisterRepository eventRegisterRepository;
	 
	 @MockBean
	 private EventReviewRepository eventReviewRepository;
	 
	 @MockBean
	 private RoleRepository roleRepository;
	 
	 @MockBean
	 private NotificationRepository notificationRepository;
	 
	 @MockBean
	 private JavaMailSender javaMailSender;
	
	 @Test
	 public void givenEventUser_thenReturnJsonArray()
	   throws Exception {
	     
	     EventUser mokuser = new EventUser();
	     mokuser.setActiveStatus(1);
	     mokuser.setCreateTime(new Date());
	     mokuser.setEmailAddress("a@b.com");
	     mokuser.setUserName("a");
	     mokuser.setRoleId(1);
	     List<EventUser> allEventUser = Arrays.asList(mokuser);
	    

	     when(userRepository.findAll()).thenReturn(allEventUser);

	     RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
					"/v1/eventuser/all").accept(
					MediaType.APPLICATION_JSON);
	     MvcResult result = mvc.perform(requestBuilder).andReturn();
	     
	    assertNotNull(result);
	    
 }

}
