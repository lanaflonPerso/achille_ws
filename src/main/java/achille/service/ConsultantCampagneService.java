package achille.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import achille.dao.ConsultantCampagneDAO;
import achille.exception.CampagneException;
import achille.exception.ConsultantCampagneException;
import achille.model.Campagne;
import achille.model.Consultant;
import achille.model.ConsultantCampagne;

@Service
public class ConsultantCampagneService {

	@Autowired
	ConsultantService consultantService;
	@Autowired
	ConsultantCampagneDAO consultantCampagneDAO;
	@Autowired
	CampagneService campagneService;

	public ConsultantCampagne  getConsultantCampagneCourante(int idConsultant) throws ConsultantCampagneException, CampagneException {
		// 0 - On récupère le consultant
		Consultant consultant= consultantService.getConsultantById(idConsultant);
		// 1 - On recherche la campagne courante
		Campagne campagneCourante = campagneService.getCampagneOuverte();
		// 2 - On cherche en base de donnée le consultantCampagne correspondant
		Optional<ConsultantCampagne> consultantCampagne = consultantCampagneDAO.findByCampagneIdCampagneAndConsultantId(campagneCourante.getIdCampagne(),consultant.getId());
		// Si il existe  :
		if (consultantCampagne.isPresent()) {
			ConsultantCampagne retour = consultantCampagne.get();
			// on récupère la liste de documents associés				
			// on l'enrichi avec les trois booleans calculé
			return retour;
		}else {		
			throw new ConsultantCampagneException("Il n'y a pas de campagne ouverte associée à ce consultant");
		}
	}

	public ConsultantCampagne creerConsultantCampagne(ConsultantCampagne cc) throws ConsultantCampagneException, CampagneException {
		if (consultantCampagneDAO.findByCampagneAndConsultant(cc.getCampagne(),cc.getConsultant()).isPresent()) {
			throw new ConsultantCampagneException("La campagne existe déjà pour ce consultant");
		}
		cc.setCampagne(campagneService.getCampagneOuverte());	
		cc.setConsultant(consultantService.getConsultantById(cc.getConsultant().getId()));
		consultantCampagneDAO.save(cc);
		return consultantCampagneDAO.findByCampagneAndConsultant(cc.getCampagne(),cc.getConsultant()).get();
	}

	public ConsultantCampagne modifierConsultantCampagne(ConsultantCampagne cc) throws ConsultantCampagneException, CampagneException {
		if (!consultantCampagneDAO.findByCampagneAndConsultant(cc.getCampagne(),cc.getConsultant()).isPresent()) {
			throw new ConsultantCampagneException("Modification impossible : La campagne n'existe pas pour ce consultant");
		}
		//TODO : Si tous les updates ne sont pas autorisés, à mettre ici les contrôles
		//TODO : Vérifier que l'on garde bien l'historique des documents
		cc.setCampagne(campagneService.getCampagneOuverte());	
		cc.setConsultant(consultantService.getConsultantById(cc.getConsultant().getId()));
		consultantCampagneDAO.save(cc);
		return consultantCampagneDAO.findByCampagneAndConsultant(cc.getCampagne(),cc.getConsultant()).get();

	}

	public Map<Integer, String> getMapConsultantCampagneCouranteEtat() throws CampagneException {
		Campagne campagneCourante = campagneService.getCampagneOuverte();
		List<ConsultantCampagne> list = consultantCampagneDAO.findAllByCampagne(campagneCourante);
		Map<Integer,String> retour = new HashMap<>();
		for (ConsultantCampagne consultantCampagne : list) {
			retour.put(consultantCampagne.getConsultant().getId(), consultantCampagne.getEtat());
			
		}
		return retour;
	}

	
		
}
