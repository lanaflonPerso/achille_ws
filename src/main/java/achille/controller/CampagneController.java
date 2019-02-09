package achille.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import achille.dao.CampagneDAO;
import achille.exception.CampagneExisteDeja;
import achille.exception.CampagneNotFound;
import achille.model.Campagne;

@CrossOrigin(origins = "*")
@RestController
public class CampagneController {

	@Autowired
	CampagneDAO campagneDAO; 


	//Retourne la liste de toutes les campagnes
	@RequestMapping(value ="/campagnes")
	List<Campagne> findAll() {
		return  (List<Campagne>) campagneDAO.findAll();
	}



	
	//Insère une campagne
	@RequestMapping(value ="/campagne",  method=RequestMethod.POST)
	Campagne create( @RequestBody Campagne c) throws IOException, CampagneExisteDeja {
		return creerCampagne(c);
	}


	private Campagne creerCampagne(Campagne c) throws CampagneExisteDeja {
		if (!existeCampagne(c)) {
			campagneDAO.save(c);
		}else {
			throw new CampagneExisteDeja(c);
		}
		return c;
	}

	// Update une camapagne
	@RequestMapping(value ="/campagne",  method=RequestMethod.PUT)
	Campagne update( @RequestBody Campagne c) throws CampagneNotFound {
		if(!campagneDAO.existsById(c.getIdCampagne())) {
			throw new CampagneNotFound(c);
		}
		return campagneDAO.save(c);
	}


	private boolean existeCampagne(Campagne c) {
		List<Campagne> campagnes = (List<Campagne>) campagneDAO.findAll();
		for (Campagne camp : campagnes) {
			if (camp.getMoisCampagne()==c.getMoisCampagne() &&  camp.getAnneeCampagne()==c.getAnneeCampagne()) {
				return true;
			}
		}
		return false;
	}



}

