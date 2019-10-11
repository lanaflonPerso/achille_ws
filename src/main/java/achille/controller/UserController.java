package achille.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import achille.dao.UserDAO;
import achille.exception.ConsultantNotFound;
import achille.model.Consultant;
import achille.model.User;

@CrossOrigin(origins = "*")
@RestController
public class UserController {

	@Autowired
	UserDAO userDAO;

	//Retourne l'user authentifié
	@RequestMapping(value ="/user/auth/{username}",  method=RequestMethod.GET)
	// TODO : controler que l'user nam correspond au token d'authentification
	User  consultantAuth(@PathVariable(value="username", required=true) String username) {
		if(!userDAO.existsByUsername(username)) {
			throw new ConsultantNotFound(username);
		}
		return userDAO.findByUsername(username);
	}



}
