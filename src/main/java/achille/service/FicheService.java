package achille.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import achille.dao.AdresseDAO;
import achille.dao.ConsultantDAO;
import achille.dao.FicheDAO;
import achille.model.Adresse;
import achille.model.Consultant;
import achille.model.Document;
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
	DocumentService documentService;
	@Autowired
	ConsultantDAO consultantDAO;

	
	public Fiche createOrUpdateFiche(int id, Fiche f) {
		
		Consultant c = consultantService.getConsultantById(id);		
			
		return this.createOrUpdateFiche(c, id, f);
		
	}
	
	public Fiche createOrUpdateFiche(Consultant c, int id, Fiche f) {
		
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
	
	public Fiche createOrUpdateFiche(int id, Fiche f, List<MultipartFile> files) {
		
		Consultant c = consultantService.getConsultantById(id);
		
		for (int i = 0; i < files.size(); i++) {
			Document doc = documentService.saveFileAndBdd(files.get(i), c.getNom()+c.getMatricule());
			switch (doc.getTypeDoc()) {
				case "rib":
					f.setRib(doc); break;
				case "carteGrise":
					f.setCarteGrise(doc); break;
				case "bulletinDePrevoyance":
					f.setPrevoyance(doc); break;
				case "conventionDAdhésion":
					f.setConventionAdhesion(doc); break;
				case "titreSejour":
					f.setTitreSejour(doc); break;
			}
		}
		
		return this.createOrUpdateFiche(c, id, f);

	}
}
