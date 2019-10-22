package achille.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import achille.dao.AdresseDAO;
import achille.dao.ConsultantDAO;
import achille.dao.FicheDAO;
import achille.model.Adresse;
import achille.model.Consultant;
import achille.model.Fiche;
import achille.service.ConsultantService;
import achille.service.FicheService;


@CrossOrigin(origins = "*")
@RestController
public class FicheController {

	public FicheController() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	FicheDAO ficheDAO;
	@Autowired
	AdresseDAO adresseDAO;
	@Autowired
	FicheService ficheService;

	@Autowired
	ConsultantDAO consultantDAO;
	
	//Insère une fiche
		@RequestMapping(value ="/fiche/{id}",  method=RequestMethod.POST)
		Fiche create(@PathVariable(value = "id", required = true) int id, @RequestBody Fiche f) {
			
			return ficheService.createOrUpdateFiche(id, f);
		}

		
		

}
