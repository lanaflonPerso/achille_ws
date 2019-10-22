package achille.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import achille.dao.CampagneDAO;
import achille.exception.CampagneException;
import achille.exception.ConsultantException;
import achille.model.Campagne;
import achille.service.CampagneService;

@CrossOrigin(origins = "*")
@RestController
public class CampagneController {

	@Autowired
	CampagneDAO campagneDAO; 
	@Autowired
	CampagneService campagneService; 

	//Retourne la liste de toutes les campagnes
	@RequestMapping(value ="/campagne/liste")
	List<Campagne> findAll() {
		return  (List<Campagne>) campagneDAO.findAll();
	}


	//Retourne la campagne en cours si elle existe
	@RequestMapping(value ="/campagne/courante")
	Campagne findCampagneCourante() throws CampagneException {
		if( campagneDAO.findByEtat("O").isEmpty()) {
			throw new CampagneException("Il n'y a pas de campagne ouverte actuellement.");
		}
		return  campagneDAO.findByEtat("O").get(0);
	}


	//Cloture la campagne 
	@RequestMapping(value ="/campagne/close/{idCampagne}",  method=RequestMethod.PUT)
	Campagne closeCampagne(@PathVariable(value = "idCampagne", required = true) int idCampagne) throws CampagneException {
		if (!campagneDAO.existsById(idCampagne)) {
			throw new CampagneException("La campagne "+idCampagne+" n'existe pas.");
		}
		Campagne c = campagneDAO.findById(idCampagne).get();
		if (c.getEtat().equals("F")) {
			throw new CampagneException("La campagne "+c.getIdCampagne()+" est déjà cloturée.");
		}
		c.setEtat("F");
		return campagneDAO.save(c);
	}

	//Ré-ouverture de la campagne 
	@RequestMapping(value ="/campagne/open/{idCampagne}",  method=RequestMethod.PUT)
	Campagne reOpenCampagne(@PathVariable(value = "idCampagne", required = true) int idCampagne) throws CampagneException {
		if (!campagneDAO.existsById(idCampagne)) {
			throw new CampagneException("La campagne "+idCampagne+" n'existe pas.");
		}
		Campagne c = campagneDAO.findById(idCampagne).get();
		if (c.getEtat().equals("O")) {
			throw new CampagneException("La campagne "+c.getIdCampagne()+" est déjà cloturée.");
		}
		c.setEtat("O");
		return campagneDAO.save(c);
	}


	//Insère une novuelle campagne
	@RequestMapping(value ="/campagne/nouvelle",  method=RequestMethod.POST)
	Campagne create( ) throws IOException, CampagneException {
		return campagneService.creerNouvelleCampagne();
	}

	//Envoie un mail d'ouverture de campagne à tous les consultants avec la propriété sendMail à true
	@RequestMapping(value ="/campagne/mail/{typeMail}/{idCampagne}")
	String sendMailOuverture(@PathVariable(value = "idCampagne", required = true) int idCampagne,
			@PathVariable(value = "typeMail", required = true)  String typeMail
			) throws IOException, CampagneException, ConsultantException {
		return campagneService.sendMail(idCampagne, typeMail);
	}
	
	@RequestMapping(value ="/campagne/update-liste-consultant", method=RequestMethod.POST)
	int createFromFiles(@RequestPart("files") MultipartFile files,
			Authentication authentication) 
			throws Exception {		
		return campagneService.genererListeConsultantPourCampagneOuverte(files);
		
	}







}

