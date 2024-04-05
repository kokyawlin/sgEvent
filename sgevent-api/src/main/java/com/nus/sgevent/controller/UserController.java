package com.nus.sgevent.controller;


import com.nus.sgevent.entity.EventUser;
import com.nus.sgevent.entity.JsonResponse;
import com.nus.sgevent.entity.UserRole;
import com.nus.sgevent.entity.userObj;
import com.nus.sgevent.repository.RoleRepository;
import com.nus.sgevent.repository.UserRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/v1/eventuser")
public class UserController {

  @Autowired 
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  @PostMapping(path = "/add") // Map ONLY POST Requests
  public @ResponseBody ResponseEntity<Object> addNewUser(
    @RequestBody EventUser user
  ) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request

    user.setCreateTime(new Date());

    userRepository.save(user);

	return ResponseEntity.ok(new JsonResponse(true, "Add user successful."));

  }

  @DeleteMapping(path = "/delete/{id}") // Map ONLY DELETE Requests
  public @ResponseBody ResponseEntity<Object> deleteUser(
    @PathVariable("id") UUID id
  ) {

    userRepository.deleteById(id);

	return ResponseEntity.ok(new JsonResponse(true, "Delete user successful."));

  }

  @PostMapping(path = "/update") // Map ONLY POST Requests
  public @ResponseBody ResponseEntity<Object> UpdateUser(
    @RequestBody EventUser user
  ) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestBody means it is a payload from POST request

    int updatestatus = userRepository.UpdateUser(
      user.getUserName(),
      user.getEmailAddress(),
      user.getRoleId(),
      user.getUserId()
    );

    if (updatestatus == 1) {
      return ResponseEntity
        .status(HttpStatus.OK)
        .body(new JsonResponse(true, "Update user successful."));
   
    } else {
      throw new ResponseStatusException(
        HttpStatus.NOT_MODIFIED,
        "Update Error"
      );
    }
  }

  @GetMapping(path = "/all")
  public @ResponseBody Iterable<EventUser> getAllUsers() {
    // This returns a JSON or XML with the users
    return userRepository.findAll();
  }

  @GetMapping(path = "/allwrole")
  public @ResponseBody ResponseEntity<Object> getAllUsersWithRole() {
    // This returns a JSON or XML with the users

    Iterable<EventUser> eventuserlist = userRepository.findAll();
    List<userObj> entityList = new ArrayList<userObj>();
    for (EventUser eU : eventuserlist) {
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

    if (entityList.size() > 0) return new ResponseEntity<Object>(
      entityList,
      HttpStatus.OK
    ); else throw new ResponseStatusException(
      HttpStatus.NOT_FOUND,
      "No User Found"
    );
  }

  @GetMapping("/{username}")
  public ResponseEntity<?> retrieveEventUser(
    @PathVariable("username") String username
  ) {
    userObj UserFound = new userObj();
    EventUser evuser = null;
    try {
      evuser = userRepository.SearchEventUserName(username);

      UserFound.setUserName(evuser.getUserName());
      UserFound.setActiveStatus(evuser.getActiveStatus());
      UserFound.setCreateTime(evuser.getCreateTime());
      UserFound.setEmailAddress(evuser.getEmailAddress());
      UserFound.setPassword(evuser.getPassword());
      UserFound.setRoleId(evuser.getRoleId());
      UserFound.setUserId(evuser.getUserId());
    } catch (NullPointerException ex) {
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND,
        "User Not Found!"
      );
    }

    UserRole evrole = roleRepository.SearchUserRole(evuser.getRoleId());
    UserFound.setRoleName(evrole.getRoleName());
    UserFound.setPermission(evrole.getPermission());
    // return new ResponseEntity<>(UserFound, HttpStatus.OK);
    return ResponseEntity.ok(UserFound);
  }


  
  @PostMapping(path = "/UserLogin")
  public ResponseEntity<?> checkUserLogin(@RequestBody EventUser user) {
      Optional<EventUser> optionalUser = userRepository.checkUserLogin(user.getEmailAddress(), user.getPassword());
      if (optionalUser.isPresent()) {
          EventUser loggedInUser = optionalUser.get();
          return ResponseEntity.ok().body(loggedInUser); // 直接返回用户信息
      } else {
          return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Incorrect username or password"));
      }
  }
  
  
    
  @PostMapping(path = "/chpassword") // Map ONLY POST Requests
  public @ResponseBody String ChangePassword(
    @RequestParam String username,
    @RequestParam String password,
    @RequestParam int userrole,
    @RequestParam String email
  ) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request

    boolean updatesuccess = userRepository.UpdatePassword(username, password);

    if (updatesuccess) return "Updated"; else {
      throw new ResponseStatusException(
        HttpStatus.NOT_MODIFIED,
        "Update Error"
      );
    }
  }

  public boolean CheckUserExist(String EmailAddress) {
    boolean found = false;
    EventUser evuser = null;
    try {
      evuser = userRepository.SearchEventUser(EmailAddress);
      if (evuser != null) {
        found = true;
      }
    } catch (NullPointerException ex) {
      found = false;
    }
    return found;
  }

  public boolean CheckUserName(String UserName) {
    boolean found = false;
    EventUser evuser = null;
    try {
      evuser = userRepository.SearchEventUserName(UserName);
      if (evuser != null) {
        found = true;
      }
    } catch (NullPointerException ex) {
      found = false;
    }
    return found;
  }
}
