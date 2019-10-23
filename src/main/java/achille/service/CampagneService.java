package achille.service;

import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import achille.dao.CampagneDAO;
import achille.exception.CampagneException;
import achille.exception.ConsultantException;
import achille.model.Campagne;
import achille.model.Consultant;

@Service
public class CampagneService {

	@Autowired
	CampagneDAO campagneDAO;
	@Autowired
	ConsultantService consultantService;
	@Autowired
	DocumentService documentService;
	
	
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
		if (!campagne.isPresent()) {
			throw new CampagneException("La campagne " + idCampagne +" n'existe pas.");
		}
		return campagne.get();
	}

	public String sendMail(int idCampagne, String typeMail) throws ConsultantException, CampagneException {
		Optional<Campagne> campagne = campagneDAO.findById(idCampagne);
		String retour="";
		if (campagne.isPresent()) {
			List<Consultant> consultants = consultantService.getConsultantsMail(typeMail.toLowerCase());
			EmailService em = new EmailService();
			
			try {
				for (Consultant consultant : consultants) {

					String subject ="[" + consultant.getSociete().getValue() + "] "+ typeMail + " campagne de paie de "
							+ campagne.get().getMoisCampagne() + "/" +campagne.get().getAnneeCampagne();
					
					String content = "Bonjour " + consultant.getPrenom() + " " + consultant.getNom() + " ," + System.getProperty("line.separator") +
							System.getProperty("line.separator") +"La campagne de paie " + campagne.get().getMoisCampagne() + "/" +campagne.get().getAnneeCampagne() +
							 " est ouverte. Vous pouvez dès maintenant renseigner vos informations. " + System.getProperty("line.separator") +
							 "Merci de vous connecter sur payes.intervia.fr avec les identifiants qui vous ont été envoyés précédemment.";
					
					String recipient = consultant.getEmail();
					em.sendMail(subject, content, recipient);
				}
			} catch (MessagingException | IOException e) {
				retour ="Un problème est survenu pendant l'envoi des emails.";
			}
			
			retour = consultants.size()+" mails envoyés.";
		}
		return retour;
	}

	public int genererListeConsultantPourCampagneOuverte(MultipartFile files) throws Exception {
		int campagneId = this.getCampagneOuverte().getIdCampagne();
		documentService.saveFileAndBdd(files, "liste-consultants", campagneId);		
		return consultantService.updateConsultant(files);		
		
	}
	


}




