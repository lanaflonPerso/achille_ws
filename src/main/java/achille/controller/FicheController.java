package achille.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import achille.dao.AdresseDAO;
import achille.dao.ConsultantDAO;
import achille.dao.FicheDAO;
import achille.model.Fiche;
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
		Fiche create(@PathVariable(value = "id", required = true) int id, 
				@RequestPart("fiche") Fiche f,
				@RequestPart("files") List<MultipartFile> files) {
			
			return ficheService.createOrUpdateFiche(id, f, files);
		}

		
		

}
