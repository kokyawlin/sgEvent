package com.nus.sgevent.controller;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.nus.sgevent.repository.UserRepository;
import com.nus.sgevent.entity.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = UserController.class)


public class UserControllerTest {

	 @Autowired
	 private MockMvc mockMvc;
	 
	 @MockBean
	    private UserRepository userRepository;
	 
	 EventUser mockUser = new EventUser();//need to initialize
	 
	 String mockuserJson ="";
	 
	@Test 
	public void RetrieveUsersDetail() throws Exception {

			Mockito.when(userRepository.SearchEventUser("a")).thenReturn(mockUser);

			RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
					"http://localhost:8080/sgevent/v1/eventuser/a").accept(
					MediaType.APPLICATION_JSON);

			MvcResult result = mockMvc.perform(requestBuilder).andReturn();

			System.out.println(result.getResponse());
			String expected = "{\"userId\":\"4d778041-714a-4b0d-b037-6e5a14449d9f\",\"userName\":\"a\",\"password\":\"abcdefg\",\"emailAddress\":\"a@nus.com\",\"activeStatus\":1,\"roleId\":1,\"createTime\":\"2024-03-31T09:49:40.677+00:00\",\"roleName\":\"User\",\"permission\":\"regist\"}";
			
			JSONAssert.assertEquals(expected, result.getResponse()
					.getContentAsString(), false);
		}


}
