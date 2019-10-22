package achille.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import achille.dao.AdresseDAO;
import achille.dao.ConsultantDAO;
import achille.dao.FicheDAO;
import achille.model.Adresse;
import achille.model.Consultant;
import achille.model.Fiche;

@Service
public class FicheService {

	@Autowired
	AdresseDAO adresseDAO;
	@Autowired
	FicheDAO ficheDAO;

	@Autowired
	ConsultantService consultantService;
	
	@Autowired
	ConsultantDAO consultantDAO;

	
	public Fiche createOrUpdateFiche(int id, Fiche f) {
		Consultant c = consultantService.getConsultantById(id);		
		
		if (f.getAdresse() != null) {
			Adresse a = f.getAdresse();
			a.setInsertionDate(f.getInsertionDate());	
			f.setAdresse(adresseDAO.save(a));
		}
		if (c.getFiche()!=null) {
			f.setId(c.getFiche().getId());
		}

		Fiche fSave= ficheDAO.save(f);
		if (c.getFiche()==null) {
			c.setFiche(fSave);
			consultantDAO.save(c);				
		}
		return fSave;
	}
}
