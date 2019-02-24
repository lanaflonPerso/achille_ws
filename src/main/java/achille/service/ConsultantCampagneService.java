package achille.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import achille.dao.ConsultantCampagneDAO;
import achille.exception.CampagneException;
import achille.exception.ConsultantCampagneException;
import achille.model.Campagne;
import achille.model.Consultant;
import achille.model.ConsultantCampagne;
import achille.model.Document;

@Service
public class ConsultantCampagneService {

	@Autowired
	ConsultantService consultantService;
	@Autowired
	ConsultantCampagneDAO consultantCampagneDAO;
	@Autowired
	CampagneService campagneService;
	@Autowired
	DocumentService documentService;


	public ConsultantCampagne  getConsultantCampagneCourante(int idConsultant) throws ConsultantCampagneException, CampagneException {
	
		List<ConsultantCampagne> listConsultantCampagne = getConsultantCampagneCouranteSuivi(idConsultant);
		// Si il existe  :
		if (listConsultantCampagne.size()>0) {
			//On récupère la version la plus récente de consultant campagne
			ConsultantCampagne retour = listConsultantCampagne.stream().max((c1,c2) -> c1.getDate().compareTo(c2.getDate())).get();
			return retour;
		}else {		
			throw new ConsultantCampagneException("Il n'y a pas de campagne ouverte associée à ce consultant");
		}
	}

	public List<ConsultantCampagne>  getConsultantCampagneCouranteHistorique(int idConsultant) throws ConsultantCampagneException, CampagneException {
		// 0 - On récupère le consultant
		Consultant consultant= consultantService.getConsultantById(idConsultant);
		// 1 - On recherche la campagne courante
		Campagne campagneCourante = campagneService.getCampagneOuverte();
		// 2 - On cherche en base de donnée le consultantCampagne correspondant
		List<ConsultantCampagne> listConsultantCampagne = consultantCampagneDAO.findByCampagneAndConsultant(campagneCourante,consultant);
		// Si il existe  :
		if (listConsultantCampagne.size()>0) {
			return listConsultantCampagne;
		}else {		
			throw new ConsultantCampagneException("Il n'y a pas de campagne ouverte associée à ce consultant");
		}
	}


	public Map<Integer, Integer> getMapConsultantCampagneCouranteEtat() throws CampagneException {
		Campagne campagneCourante = campagneService.getCampagneOuverte();
		List<ConsultantCampagne> list = consultantCampagneDAO.findAllByCampagne(campagneCourante);
		Map<Consultant, List<ConsultantCampagne>> lignesParConsultant = list.stream().collect(Collectors.groupingBy(ConsultantCampagne::getConsultant));		
		Map<Integer, Integer> retour = new HashMap<>();
		for (Map.Entry<Consultant, List<ConsultantCampagne>> entry : lignesParConsultant.entrySet()){
			int lastEtat = entry.getValue().stream().max((cc1,cc2) -> cc1.getDate().compareTo(cc2.getDate())).get().getEtat();
			retour.put(entry.getKey().getId(), lastEtat);
		}

		return retour;
	}


	public ConsultantCampagne creerConsultantCampagne(ConsultantCampagne cc, List<MultipartFile> files) throws CampagneException {
		cc.setCampagne(campagneService.getCampagneOuverte());
		cc.setConsultant(consultantService.getConsultantById(cc.getConsultant().getId()));	
		if (consultantCampagneDAO.findByCampagneAndConsultant(cc.getCampagne(),cc.getConsultant()).isEmpty()) {
			cc.setEtat(2);
		}else {
			cc.setEtat(0);
		}
		//Gestion des documents envoyés
		for (int i = 0; i < files.size(); i++) {
			Document doc = documentService.saveFileAndBdd(files.get(i), cc.getConsultant().getNom()+cc.getConsultant().getMatricule(), cc.getCampagne().getIdCampagne());
			cc.setDocument(doc);				
		}
		cc.setDate(new Date());		
		return consultantCampagneDAO.save(cc);
	}

	/**
	 * 
	 * @param idConsultant
	 * @param etat
	 * @return le consultant modifié
	 * Update l'état de la ligne la plus récente de campagne consultant.
	 * @throws CampagneException 
	 * @throws ConsultantCampagneException 
	 */
	public ConsultantCampagne updateEtatConsultantCampagne(int idConsultant, int etat) throws ConsultantCampagneException, CampagneException {
		ConsultantCampagne consultantCampagne= getConsultantCampagneCourante(idConsultant);
		consultantCampagne.setEtat(etat);
		return consultantCampagneDAO.save(consultantCampagne);

	}

	public List<ConsultantCampagne> getConsultantCampagneCouranteSuivi(int idConsultant) throws CampagneException {
		// 0 - On récupère le consultant
		Consultant consultant= consultantService.getConsultantById(idConsultant);
		// 1 - On recherche la campagne courante
		Campagne campagneCourante = campagneService.getCampagneOuverte();
		// 2 - On cherche en base de donnée le consultantCampagne correspondant
		return consultantCampagneDAO.findByCampagneAndConsultant(campagneCourante,consultant);
		// Si il existe  :
	}

	public Integer getConsultantCampagneCouranteEtat(int idConsultant) throws CampagneException {
		Campagne campagneCourante = campagneService.getCampagneOuverte();
		List<ConsultantCampagne> consultantCampagne = consultantCampagneDAO.findByCampagneIdCampagneAndConsultantId(campagneCourante.getIdCampagne(), idConsultant);
		if (consultantCampagne.isEmpty()) {
			return 1;
		}else {
			return consultantCampagne.stream().max((cc1,cc2) -> cc1.getDate().compareTo(cc2.getDate())).get().getEtat();
		}
		
	}



}
