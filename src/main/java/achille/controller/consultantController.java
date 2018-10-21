package achille.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import achille.dao.ConsultantDAO;
import achille.model.Consultant;


@CrossOrigin(origins = "*")
@RestController
public class consultantController {
	@Autowired
	ConsultantDAO consultantDAO;
	
	//Retourne la liste de tous les consultants
	@RequestMapping(value ="/consultants")
	List<Consultant> findAll() {
		return  (List<Consultant>) consultantDAO.findAll();
	}
	
	
	//Insère un consultant
	@RequestMapping(value ="/consultant",  method=RequestMethod.POST)
	Consultant create( @RequestBody Consultant c) {
		System.out.println("toto");
		return consultantDAO.save(c);
	}
	
	
	

}
