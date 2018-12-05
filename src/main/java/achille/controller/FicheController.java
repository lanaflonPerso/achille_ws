package achille.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import achille.dao.AdresseDAO;
import achille.dao.FicheDAO;
import achille.model.Adresse;
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
			
			if (f.getAdresse() != null) {
				Adresse a = f.getAdresse();
				a.setInsertionDate(f.getInsertionDate());	
				f.setAdresse(adresseDAO.save(a));
			}

			return ficheDAO.save(f);
		}
		

}
