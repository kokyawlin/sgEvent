package com.nus.sgevent.controller;

import com.nus.sgevent.entity.UserRole;
import com.nus.sgevent.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // This means that this class is a Controller
@CrossOrigin(origins = "*")
@RequestMapping(path = "/v1/eventrole")
public class RoleController {

  @Autowired
  private RoleRepository roleRepository;

  @GetMapping(path = "/all")
  public @ResponseBody ResponseEntity<Object> getAllRoles() {
    // This returns a JSON or XML with the roles
    Iterable<UserRole> roleList = roleRepository.findAll();
    return new ResponseEntity<Object>(roleList, HttpStatus.OK);
  }
}
