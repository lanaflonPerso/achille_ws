package achille.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import achille.auth.AuthUtils;
import achille.exception.CampagneException;
import achille.exception.ConsultantCampagneException;
import achille.model.ConsultantCampagne;
import achille.service.ConsultantCampagneService;

@CrossOrigin(origins = "*")
@RestController
public class ConsultantCampagneController {

	@Autowired
	ConsultantCampagneService consultantCampagneService;

	//Retourne la campagne en cours si elle existe pour un consultant donné
	@RequestMapping(value ="/consultant-campagne/{idConsultant}")
	ConsultantCampagne findConsultantCampagneCourante(@PathVariable(value = "idConsultant", required = true) int idConsultant, Authentication authentication) throws ConsultantCampagneException, CampagneException {
		AuthUtils.consultantAutorise(idConsultant, authentication);
		return consultantCampagneService.getConsultantCampagneCourante(idConsultant);
	}

	//Crée un consultant campagne à partir des informations d'un consultant
	// La campagne n'est pas indispensable car surchargée par la dernière campagne ouverte.
	@RequestMapping(value ="/consultant-campagne", method=RequestMethod.POST)
	ConsultantCampagne createConsultantCampagneCourante(@RequestBody ConsultantCampagne cc, Authentication authentication ) throws ConsultantCampagneException, CampagneException {
		AuthUtils.consultantAutorise(cc.getConsultant().getId(), authentication);
		return consultantCampagneService.creerConsultantCampagne(cc);
	}
	
	//Update les informations d'un consultant campagne existant
	@RequestMapping(value ="/consultant-campagne", method=RequestMethod.PUT)
	ConsultantCampagne updateConsultantCampagneCourante(@RequestBody ConsultantCampagne cc, Authentication authentication ) throws ConsultantCampagneException, CampagneException {
		AuthUtils.consultantAutorise(cc.getConsultant().getId(), authentication);
		return consultantCampagneService.modifierConsultantCampagne(cc);
	}



}
