package achille.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

	//Retourne la campagne demandée si elle existe pour un consultant donné
		@RequestMapping(value ="/consultant-campagne/{idCampagne}/{idConsultant}")
		List<ConsultantCampagne> findConsultantCampagne(@PathVariable(value = "idConsultant", required = true) int idConsultant,
												  @PathVariable(value = "idCampagne", required = true) int idCampagne,
				Authentication authentication) 
						throws ConsultantCampagneException, CampagneException {
			AuthUtils.consultantAutorise(idConsultant, authentication);
			return consultantCampagneService.getConsultantCampagne(idConsultant, idCampagne);
		}


	//Crée un consultant campagne à partir des informations d'un consultant et de la liste des fichiers associés
	@RequestMapping(value ="/consultant-campagne/update", method=RequestMethod.POST)
	ConsultantCampagne createConsultantCampagneCouranteFiles(
			@RequestPart("consultantCampagne") ConsultantCampagne cc,
			@RequestPart("files") List<MultipartFile> files,
			Authentication authentication
			) throws ConsultantCampagneException, CampagneException {
		AuthUtils.consultantAutorise(cc.getConsultant().getId(), authentication);
		return consultantCampagneService.creerConsultantCampagne(cc, files);
	}


	// Serivce qui met à jour l'état de la ligne la plus récente de la campagne (ADMIN)
	@RequestMapping(value ="/consultant-campagne/etat/{idConsultant}/{etat}", method=RequestMethod.PUT)
	ConsultantCampagne updateEtatConsultantCampagne(@PathVariable(value = "idConsultant", required = true) int idConsultant, @PathVariable(value= "etat", required=true) int etat) throws CampagneException, ConsultantCampagneException{
		return consultantCampagneService.updateEtatConsultantCampagne(idConsultant,etat);
	}

	// Serivce qui renvoie l'etat d'un consultant pour la ligne la plus récente de la campagne courante.
	@RequestMapping(value ="/consultant-campagne/etat/{idConsultant}")
		Integer getConsultantCampagneEtat(@PathVariable(value = "idConsultant", required = true) int idConsultant) throws CampagneException{
			return consultantCampagneService.getConsultantCampagneCouranteEtat(idConsultant);
		}

	// Serivce qui renvoie une map avec l'idconsultant et l'état de la campagne courante
	@RequestMapping(value ="/consultant-campagne/map-consultant-etat")
	Map<Integer,Integer> getMapConsultantCampagne() throws CampagneException{
		return consultantCampagneService.getMapConsultantCampagneCouranteEtat();
	}

    // Serivce qui renvoie la liste des modifications effectuées par un consultant pour la campagne courante
	@RequestMapping(value ="/consultant-campagne/{idConsultant}/suivi")
	List<ConsultantCampagne> findConsultantCampagneCouranteSuivi(@PathVariable(value = "idConsultant", required = true) int idConsultant, Authentication authentication) throws ConsultantCampagneException, CampagneException {
		AuthUtils.consultantAutorise(idConsultant, authentication);
		return consultantCampagneService.getConsultantCampagneCouranteSuivi(idConsultant);
	}



}
