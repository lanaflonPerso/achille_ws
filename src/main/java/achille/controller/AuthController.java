package achille.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import achille.dao.ConsultantDAO;
import achille.dao.UserDAO;
import achille.model.Consultant;
import achille.model.User;
import achille.password.PasswordGenerator;
import achille.password.PasswordUtils;
import achille.service.EmailService;

@CrossOrigin(origins = "*")
@RestController
public class AuthController {
	
	@Autowired
	ConsultantDAO consultantDAO;
	@Autowired
	UserDAO userDAO;
	
	@RequestMapping(value ="/reset-ident",  method=RequestMethod.POST)
	Boolean resetIdent(@RequestBody Consultant c_mail) throws AddressException, MessagingException, IOException {
		
		Consultant c = consultantDAO.findByEmail(c_mail.getEmail());
		
		System.out.println(c_mail.getEmail());
		
		String salt = PasswordUtils.getSalt(30);
		PasswordGenerator pwdGen = new PasswordGenerator(4, 12);
		String passwordGenerated = pwdGen.generatePassword().toString();
		String password = PasswordUtils.generateSecurePassword(passwordGenerated, salt);

		Optional<User> u = userDAO.findById(c.getId());
		if(u.isPresent()) {
			u.get().setPassword(password);
			u.get().setSalt(salt);
			userDAO.save(u.get());
		}
		
		if (c.getSendMail()) {
			EmailService em = new EmailService();
			String content = "nom : " + c.getNom() + System.getProperty("line.separator") + "matricule : "
					+ c.getMatricule() + System.getProperty("line.separator") + "mot de passe : " + passwordGenerated;
			String subject = "Création de compte";
			String recipient = c.getEmail();

			em.sendMail(subject, content, recipient);
			
		}
		
		return true;
		
	}
}
