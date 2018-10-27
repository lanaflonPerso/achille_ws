package achille.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import achille.dao.PartenaireDAO;
import achille.dao.SocieteDAO;
import achille.model.Partenaire;
import achille.model.Societe;


@CrossOrigin(origins = "*")
@RestController
public class SocieteController {

	public SocieteController() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	SocieteDAO societeDAO;

	
	//Insère une fiche
		@RequestMapping(value ="/societe",  method=RequestMethod.GET)
		List<Societe> findAll() {
			return  (List<Societe>) societeDAO.findAll();
		}
		

}
