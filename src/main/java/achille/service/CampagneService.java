package achille.service;

import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import achille.dao.CampagneDAO;
import achille.exception.CampagneException;
import achille.model.Campagne;

@Service
public class CampagneService {

	@Autowired
	CampagneDAO campagneDAO;

	public Campagne creerNouvelleCampagne() throws CampagneException {
		Campagne c = new Campagne();
		// On vérifie qu'il n'y en a aucune d'ouverte
		if (campagneDAO.findByEtat("O").isEmpty()) {
			Optional<Campagne> derniereCampagne = 
					((Collection<Campagne>) campagneDAO.findAll()).stream()
					.max(Comparator.comparing(
							(camp) -> 100*camp.getAnneeCampagne()+camp.getMoisCampagne())
							);

			c.setDateOuverture(new Date());
			c.setEtat("O");
			if (derniereCampagne.get() != null) {
				Campagne campagnePrecedente = derniereCampagne.get();

				if (campagnePrecedente.getMoisCampagne()<12) {
					c.setAnneeCampagne(campagnePrecedente.getAnneeCampagne());
					c.setMoisCampagne(campagnePrecedente.getMoisCampagne()+1);
				} else if (campagnePrecedente.getMoisCampagne()==12) {
					c.setAnneeCampagne(campagnePrecedente.getAnneeCampagne()+1);
					c.setMoisCampagne(1);
				}
			}else {
				c.setAnneeCampagne(2018);
				c.setMoisCampagne(1);				
			}
			c.setIdCampagne(c.getAnneeCampagne()*100+c.getMoisCampagne());

		}else {
			throw new CampagneException("Il y a déjà une campagne d'ouverte");
		}
		return campagneDAO.save(c);
	}

	public Campagne getCampagneOuverte() throws CampagneException {
		List<Campagne> campagnesOuverte = campagneDAO.findByEtat("O");
		//Normalement il n'y a qu'une seule campagne d'ouverte à la fois, on prend la première
		if (campagnesOuverte.isEmpty()) {
			throw new CampagneException("Il n'y a pas de campagne ouverte");
		}else {
			if (campagnesOuverte.size()>1) {
				throw new CampagneException("Il y a plus d'une campagne ouverte");
			}			
		}
		return campagnesOuverte.get(0);
	}

	public Campagne getCampagne(int idCampagne) throws CampagneException {
		Optional<Campagne> campagne = campagneDAO.findById(idCampagne);
		if (campagne.get()== null) {
			throw new CampagneException("La campagne " + idCampagne +" n'existe pas.");
		}
		return campagne.get();
	}
	
	


}
