package com.nus.sgevent;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nus.sgevent.extservices.MailService;
@Controller	// This means that this class is a Controller
@RequestMapping(path="/v1/sendemail")
public class EmailController {

	@PostMapping(path="/successregi")
	public void sendSuccessRegister(String EmailAddress)
	{
		String Subj = "Successful User Registeration";
		String strbody = "User Registeration is successful";
		MailService MailServ = new MailService();
		MailServ.sendEmail(EmailAddress, Subj, strbody);
		
	}
	
}
