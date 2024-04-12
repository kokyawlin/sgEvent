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
		 UUID mokid = UUID.randomUUID();
	     EventUser mokuser = new EventUser();
	     mokuser.setUserId(mokid);
	     mokuser.setActiveStatus(1);
	     mokuser.setPassword("a");
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
	    
	     
	     mvc.perform(MockMvcRequestBuilders.get("/v1/eventuser/a"))
	     .andExpect(MockMvcResultMatchers.status().isNotFound());
	     
	     mvc.perform( MockMvcRequestBuilders
	   	      .post("/v1/eventuser/add")
	   	      .content(asJsonString(mokuser))
	   	      .contentType(MediaType.APPLICATION_JSON)
	   	      .accept(MediaType.APPLICATION_JSON))
	         .andExpect(status().isOk());
	     
	    
	     mvc.perform(MockMvcRequestBuilders.delete("/v1/eventuser/delete/", mokid))
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
