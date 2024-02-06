package com.nus.sgevent;

import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;



@Controller	// This means that this class is a Controller
@RequestMapping(path="/v1") // This means URL's start with /demo (after Application path)
public class MainController {
	@Autowired // This means to get the bean called userRepository
			   // Which is auto-generated by Spring, we will use it to handle the data
	private UserRepository userRepository;

	@PostMapping(path="/add") // Map ONLY POST Requests
	public @ResponseBody String addNewUser (@RequestParam String name
			, @RequestParam String userid
			, @RequestParam String userrole
			, @RequestParam String password
			, @RequestParam int activeflag
			, @RequestParam String email) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		EventUser n = new EventUser();
		n.setUserId(userid);
		n.setPassword(password);
		n.setCreateTime(new Date());
		n.setUserRole(userrole);
		n.setUserName(name);
		n.setEmailAddress(email);
		userRepository.save(n);
		return "Saved";
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<EventUser> getAllUsers() {
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	}
	
	@GetMapping("/all/{userid}")  
	private EventUser getBooks(@PathVariable("userid") String userid)   
	{  
		EventUser evuser=userRepository.SearchEventUser(userid);
	return evuser;  
	} 
}
