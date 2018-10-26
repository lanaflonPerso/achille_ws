package achille.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import achille.dao.AdresseDAO;
import achille.dao.FicheDAO;
import achille.model.Fiche;


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
	
	//Insère une fiche
		@RequestMapping(value ="/fiche",  method=RequestMethod.POST)
		Fiche create( @RequestBody Fiche f) { 
			f.setAdresse(adresseDAO.save(f.getAdresse()));
			return ficheDAO.save(f);
		}
		

}
