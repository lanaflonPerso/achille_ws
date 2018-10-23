package achille.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import achille.dao.AdresseDAO;
import achille.dao.ConsultantDAO;
import achille.model.Adresse;
import achille.model.Consultant;
import ch.qos.logback.core.net.SyslogOutputStream;


@CrossOrigin(origins = "*")
@RestController
public class consultantController {
	@Autowired
	ConsultantDAO consultantDAO;
	@Autowired
	AdresseDAO adresseDAO;
	
	//Retourne la liste de tous les consultants
	@RequestMapping(value ="/consultants")
	List<Consultant> findAll() {
		return  (List<Consultant>) consultantDAO.findAll();
	}
	
	
	//Insère un consultant
	@RequestMapping(value ="/consultant",  method=RequestMethod.POST)
	Consultant create( @RequestBody Consultant c) { 
		System.out.println("tot");
		c.setAdresse(adresseDAO.save(c.getAdresse()));
		return consultantDAO.save(c);
	}
	
	
	

}
