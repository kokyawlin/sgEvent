package com.nus.sgevent.controller;

import com.nus.sgevent.extservices.MailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // This means that this class is a Controller
@CrossOrigin(origins = "*")
@RequestMapping(path = "/v1/sendemail")
public class EmailController {

  @PostMapping(path = "/successregi")
  public void sendSuccessRegister(String EmailAddress) {
    String Subj = "Successful User Registeration";
    String strbody = "User Registeration is successful";
    MailService MailServ = new MailService();
    MailServ.sendEmail(EmailAddress, Subj, strbody);
  }
}
