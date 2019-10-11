package achille.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import achille.auth.AuthUtils;
import achille.dao.AdresseDAO;
import achille.dao.ConsultantDAO;
import achille.dao.FicheDAO;
import achille.dao.PartenaireDAO;
import achille.dao.SocieteDAO;
import achille.dao.TypeContratDAO;
import achille.dao.UserDAO;
import achille.exception.ConsultantNotFound;
import achille.model.Consultant;
import achille.service.ConsultantService;
import achille.wrapper.ConsultantWrapper;

@CrossOrigin(origins = "*")
@RestController
public class ConsultantController {

	@Autowired
	ConsultantDAO consultantDAO;
	@Autowired
	AdresseDAO adresseDAO;
	@Autowired
	FicheDAO ficheDAO;
	@Autowired
	TypeContratDAO typeContratDAO;
	@Autowired
	PartenaireDAO partenaireDAO;
	@Autowired
	SocieteDAO societeDAO;
	@Autowired
	UserDAO userDAO;
	@Autowired
	ConsultantService consultantService;

	// Retourne la liste de tous les consultants : ADMIN
	@RequestMapping(value = "/consultants")
	List<Consultant> findAll() {
		return (List<Consultant>) consultantDAO.findAll();
	}

	// Insère un consultant : ADMIN
	@RequestMapping(value = "/consultant", method = RequestMethod.POST)
	Boolean create(@RequestBody Consultant c) throws AddressException, MessagingException, IOException {
		return consultantService.createConsultant(c);
	}

	// Insère une liste de consultants : ADMIN
	@RequestMapping(value = "/consultant/list", method = RequestMethod.POST)
	Boolean create(@RequestBody List<Consultant> l_c) throws AddressException, MessagingException, IOException {
		Boolean allSave = true;
		Boolean currentSave = true;
		for (Consultant c : l_c) {
			currentSave = consultantService.createConsultant(c);
			allSave = allSave && currentSave;
		}
		return allSave;
	}

	// Retourne un consultant : ADMIN ou CONSULTANT
	@RequestMapping(value = "/consultant/{id}", method = RequestMethod.GET)
	Optional<Consultant> find(@PathVariable(value = "id", required = true) int id, Authentication authentication) {
		AuthUtils.consultantAutorise(id, authentication);
		if (!consultantDAO.existsById(id)) {
			throw new ConsultantNotFound(id);
		}
		return consultantDAO.findById(id);
	}

	// Update un consultant : ADMIN ou CONSULTANT
	@RequestMapping(value = "/consultant", method = RequestMethod.PUT)
	Consultant update(@RequestBody Consultant c, Authentication authentication) {
		AuthUtils.consultantAutorise(c.getId(), authentication);
		if (!consultantDAO.existsById(c.getId())) {
			throw new ConsultantNotFound(c.getId());
		}
		c.setInsertionDate(new Date());
		return consultantDAO.save(c);

	}
	
	


}
