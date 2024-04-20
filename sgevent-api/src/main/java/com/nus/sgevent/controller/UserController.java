package com.nus.sgevent.controller;

import com.nus.sgevent.entity.EventUser;
import com.nus.sgevent.entity.JsonResponse;
import com.nus.sgevent.entity.UserRole;
import com.nus.sgevent.entity.userObj;
import com.nus.sgevent.extservices.MailService;
import com.nus.sgevent.repository.RoleRepository;
import com.nus.sgevent.repository.UserRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller // This means that this class is a Controller
@CrossOrigin(origins = "*")
@RequestMapping(path = "/v1/eventuser")
public class UserController {

  private static final Logger logger = LoggerFactory.getLogger(
    UserController.class
  );

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
      userObj uO = new userObj(eU);

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

  @GetMapping("/{userId}")
  public ResponseEntity<?> retrieveEventUser(
    @PathVariable("userId") String userId
  ) {
    Optional<EventUser> evtUser = null;
    try {
      UUID uuid = UUID.fromString(userId);
      evtUser = userRepository.findById(uuid);
      EventUser userEntity = evtUser.get();
      userObj UserFound = new userObj(userEntity);
      UserRole evrole = roleRepository.SearchUserRole(userEntity.getRoleId());
      UserFound.setRoleName(evrole.getRoleName());
      UserFound.setPermission(evrole.getPermission());
      return ResponseEntity.ok(UserFound);
    } catch (NullPointerException ex) {
      throw new ResponseStatusException(
        HttpStatus.NOT_FOUND,
        "User Not Found!"
      );
    }
  }

  @PostMapping(path = "/login")
  public ResponseEntity<?> checkUserLogin(@RequestBody EventUser user) {
    Optional<EventUser> optionalUser = userRepository.checkUserLogin(
      user.getEmailAddress(),
      user.getPassword()
    );
    if (optionalUser.isPresent()) {
      EventUser loggedInUser = optionalUser.get();
      return ResponseEntity.ok().body(loggedInUser); // 直接返回用户信息
    } else {
      return ResponseEntity
        .status(HttpStatus.UNAUTHORIZED)
        .body(Map.of("error", "Incorrect username or password"));
    }
  }

  @Autowired
  private MailService mailService;

  @PostMapping(path = "/UserSignup") // Map ONLY POST Requests
  public ResponseEntity<?> UserSignup(@RequestBody EventUser user) {
    // 检查邮箱地址是否已被注册

    if (CheckUserExist(user.getEmailAddress())) {
      return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(Map.of("error", "Email address is already registered."));
    }

    // 设置创建时间
    user.setCreateTime(new Date());

    // 设置活动状态为1
    user.setActiveStatus(1);

    // 设置活动状态为1
    user.setRoleId(1);

    // 保存新用户到数据库
    userRepository.save(user);

    // 尝试发送欢迎邮件
    try {
      String subject = "Welcome to SGeventhub！";
      String body =
        "Dear " +
        user.getUserName() +
        ",\n\n\nThanks for signing up our website. \n\nWe are happy you joined our community! \n\n\nSGeventhub Team";
      mailService.sendEmail(user.getEmailAddress(), subject, body);
      logger.info(
        "Welcome email sent successfully to {}",
        user.getEmailAddress()
      );
    } catch (Exception e) {
      logger.error(
        "Failed to send welcome email to {}: {}",
        user.getEmailAddress(),
        e.getMessage()
      );
    }

    // 返回成功响应
    return ResponseEntity.ok(user); // 直接返回注册的用户信息
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
