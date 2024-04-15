package com.nus.sgevent.controller;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import com.nus.sgevent.repository.EventRegisterRepository;
import com.nus.sgevent.repository.EventRepository;
import com.nus.sgevent.repository.EventReviewRepository;
import com.nus.sgevent.repository.NotificationRepository;
import com.nus.sgevent.repository.RoleRepository;
import com.nus.sgevent.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nus.sgevent.entity.*;
import org.junit.Test;

@WebMvcTest(value = UserController.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class EventControllerIntegrationTest {

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
	 public void givenEvent_thenReturnJsonArray()
	   throws Exception {
		 UUID mokid = UUID.randomUUID();
	     Event mokevent = new Event();
	     mokevent.setEventCapacity(200);
	     mokevent.setEventCreateDt(new Date());
	     mokevent.setEventId(mokid);
	     mokevent.setEventDesc("TestEvent");
	     mokevent.setEventPlace("Singapore");
	     mokevent.setEventStatus("Open");
	     List<Event> allEvent = Arrays.asList(mokevent);
	    

	     when(eventRepository.findAll()).thenReturn(allEvent);
	     RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
					"/v1/event/all").accept(
					MediaType.APPLICATION_JSON);
	     MvcResult result = mvc.perform(requestBuilder).andReturn();
	     assertNotNull(result);
	     
	     mvc.perform(MockMvcRequestBuilders.get("/v1/event/a"))
	     .andExpect(MockMvcResultMatchers.status().isNotFound());
 }
	 @Test
	 public void EventPostDelTest() throws Exception
	 {
		 UUID mokid = UUID.randomUUID();
		   
		     mvc.perform(MockMvcRequestBuilders.delete("/v1/event/delete/", mokid))
		                .andExpect(status().isNotFound());
		 
	 }
	 
	 public static String asJsonString(final Object obj) {
		    try {
		        return new ObjectMapper().writeValueAsString(obj);
		    } catch (Exception e) {
		        throw new RuntimeException(e);
		    }
	 }

}
