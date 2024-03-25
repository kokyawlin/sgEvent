package com.nus.sgevent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import com.nus.sgevent.entity.EventUser;
import com.nus.sgevent.entity.UserRole;
import com.nus.sgevent.entity.userObj;
import com.nus.sgevent.extservices.JwtUtil;
import com.nus.sgevent.repository.RoleRepository;
import com.nus.sgevent.repository.UserRepository;


@Controller	// This means that this class is a Controller
@RequestMapping(path="/v1/eventuser")
public class UserController {
	@Autowired // This means to get the bean called userRepository
			   // Which is auto-generated by Spring, we will use it to handle the data
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	@PostMapping(path="/add") // Map ONLY POST Requests
	public @ResponseBody String addNewUser (@RequestParam String name
			, @RequestParam String userid
			, @RequestParam String password
			, @RequestParam int activeflag
			, @RequestParam String email) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		if (CheckUserExist(email))
			
		{
			throw new ResponseStatusException(
					  HttpStatus.NOT_ACCEPTABLE, "Email Already Exist"
					);
		}
		else
		{
		EventUser n = new EventUser();
		n.setUserId(userid);
		n.setPassword(password);
		n.setCreateTime(new Date());
		n.setRoleId(1);
		n.setUserName(name);
		n.setEmailAddress(email);
		userRepository.save(n);
		}
		return "Saved";
	}
	
	
	@PostMapping(path="/update") // Map ONLY POST Requests
	public @ResponseBody String UpdateUser (@RequestParam String name
			, @RequestParam String userid
			, @RequestParam String password
			
			, @RequestParam int userrole
			, @RequestParam String email) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request
		
		boolean updatesuccess = userRepository.UpdateUser(userid, password, userid, email, userrole);
	
		if (updatesuccess)
		return "Updated";
		else
			{
					throw new ResponseStatusException(
					  HttpStatus.NOT_MODIFIED, "Update Error"
					);
			}	
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<EventUser> getAllUsers() {
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	}
	
	@GetMapping(path="/allwrole")
	public @ResponseBody ResponseEntity<Object> getAllUsersWithRole() {
		// This returns a JSON or XML with the users
		
		Iterable<EventUser> eventuserlist = userRepository.findAll();
		List<userObj> entityList = new ArrayList<userObj>();
		for (EventUser eU:eventuserlist)
		{
			userObj uO = new userObj();
			uO.setUserId(eU.getUserId());
			uO.setUserName(eU.getUserName());
			uO.setPassword(eU.getPassword());
			uO.setCreateTime(eU.getCreateTime());
			uO.setActiveStatus(eU.getActiveStatus());
			uO.setRoleId(eU.getRoleId());

			UserRole evrole = roleRepository.SearchUserRole(eU.getRoleId());
			uO.setRoleName(evrole.getRoleName());
			uO.setPermission(evrole.getPermission());
			entityList.add(uO);
		}
		
		if (entityList.size()>0)
		 return new ResponseEntity<Object>(entityList, HttpStatus.OK);
		else
			throw new ResponseStatusException(
					  HttpStatus.NOT_FOUND, "No User Found"
					);
		
	}
	
	@GetMapping("/{username}")  
	public ResponseEntity<?> retrieveEventUser(@PathVariable("emailaddress") String username)   
	{  
		userObj UserFound = new userObj();
		EventUser evuser = null;
		try
		{
		evuser=userRepository.SearchEventUser(username);
		
		UserFound.setUserName(evuser.getUserName());
		UserFound.setActiveStatus(evuser.getActiveStatus());
		UserFound.setCreateTime(evuser.getCreateTime());
		UserFound.setEmailAddress(evuser.getEmailAddress());
		UserFound.setPassword(evuser.getPassword());
		UserFound.setRoleId(evuser.getRoleId());
		UserFound.setUserId(evuser.getUserId());
		}
		catch(NullPointerException ex)
		{
			throw new ResponseStatusException(
					  HttpStatus.NOT_FOUND, "User Not Found!"
					);
		}
		UserRole evrole = roleRepository.SearchUserRole(evuser.getRoleId());
		UserFound.setRoleName(evrole.getRoleName());
		UserFound.setPermission(evrole.getPermission());
		// return new ResponseEntity<>(UserFound, HttpStatus.OK);
		return ResponseEntity.ok(UserFound);
	// return UserFound;  
	} 
	
	@GetMapping(path="/UserLogin")
	public String checkUserLogin(@PathVariable("userid") String userid,@PathVariable("password") String password)
	{
		if (userRepository.checkUserLogin(userid, password).size()>0)
		return "success:" + JwtUtil.generateToken(userid);
		else
		{
				throw new ResponseStatusException(
				  HttpStatus.NOT_FOUND, "Incorrect username or password"
				);
		}
		
	}
	
	public boolean CheckUserExist(String EmailAddress)
	{
		boolean found=false;
		EventUser evuser = null;
		try
		{
			evuser = userRepository.SearchEventUser(EmailAddress);
			if (evuser!=null)
			{
				found=true;
			}
		}
		catch (NullPointerException ex)
		{
			found = false;
			
		}
		return found;
	}
	
	public boolean CheckUserName (String UserName)
	{
		boolean found=false;
		EventUser evuser = null;
		try
		{
			evuser = userRepository.SearchEventUserName(UserName);
			if (evuser!=null)
			{
				found=true;
			}
		}
		catch (NullPointerException ex)
		{
			found = false;
			
		}
		return found;
	}
	
}
