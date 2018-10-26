package achille.controller;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import achille.service.EmailService;

@CrossOrigin(origins = "*")
@RestController
public class EmailController {
	
	  @Autowired
	  EmailService emailService;
	
	  @RequestMapping(value = "/sendemail")
	   public String sendEmail() {
		  try {
			emailService.sendmail();
		} catch (MessagingException | IOException e) {
			e.printStackTrace();
		}
	      return "Email sent successfully";
	   }   

}
