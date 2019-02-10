package achille.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import achille.auth.Authority;
import achille.dao.AdresseDAO;
import achille.dao.ConsultantDAO;
import achille.dao.FicheDAO;
import achille.dao.PartenaireDAO;
import achille.dao.SocieteDAO;
import achille.dao.TypeContratDAO;
import achille.dao.UserDAO;
import achille.exception.ConsultantNotFound;
import achille.model.Adresse;
import achille.model.Consultant;
import achille.model.Fiche;
import achille.model.User;
import achille.password.PasswordGenerator;
import achille.password.PasswordUtils;

@Service
public class ConsultantService {

	@Autowired
	AdresseDAO adresseDAO;
	@Autowired
	FicheDAO ficheDAO;
	@Autowired
	TypeContratDAO typeContratDAO;
	@Autowired
	PartenaireDAO partenaireDAO;
	@Autowired
	SocieteDAO societeDAO;
	@Autowired
	UserDAO userDAO;
	@Autowired
	ConsultantService consultantService;
	@Autowired
	ConsultantDAO consultantDAO;

	public Boolean consultantExisted(String nom, String matricule) {
		List<Consultant> l_c = (List<Consultant>) consultantDAO.findAll();
		for (Consultant consultant : l_c) {
			if (consultant.getNom().equals(nom) && consultant.getMatricule().equals(matricule))
				return true;
		}
		return false;
	}

	public boolean createConsultant(Consultant c) throws AddressException, MessagingException, IOException {

		if (consultantService.consultantExisted(c.getNom(), c.getMatricule()))
			return false;

		Date insertDate = new Date();

		if (c.getFiche() != null) {
			Fiche f = c.getFiche();
			f.setInsertionDate(insertDate);
			if (f.getAdresse() != null) {
				Adresse a = f.getAdresse();
				a.setInsertionDate(f.getInsertionDate());
				f.setAdresse(adresseDAO.save(a));
			}
			c.setFiche(ficheDAO.save(f));
		}

		c.setSociete(societeDAO.save(c.getSociete()));
		c.setTypeContrat(typeContratDAO.save(c.getTypeContrat()));
		c.setPartenaire(partenaireDAO.save(c.getPartenaire()));

		c.setInsertionDate(insertDate);

		c = consultantDAO.save(c);

		String salt = PasswordUtils.getSalt(30);
		PasswordGenerator pwdGen = new PasswordGenerator(4, 12);
		String passwordGenerated = pwdGen.generatePassword().toString();
		String password = PasswordUtils.generateSecurePassword(passwordGenerated, salt);

		List<Authority> la = new ArrayList<Authority>();
		la.add(new Authority("CONSULTANT"));
		User u = new User(c.getId(), c.getNom() + c.getMatricule(), password, salt, la);
		userDAO.save(u);
		
		if (c.getSendMail()) {
			EmailService em = new EmailService();
			String content = "nom : " + c.getNom() + System.getProperty("line.separator") + "matricule : "
					+ c.getMatricule() + System.getProperty("line.separator") + "mot de passe : " + passwordGenerated;
			String subject = "Création de compte";
			String recipient = c.getEmail();

			em.sendMail(subject, content, recipient);
		}

		return true;

	}


	public Consultant getConsultantById(int idConsultant) {
		 Optional<Consultant> consultant = consultantDAO.findById(idConsultant);
		 if(consultant.isPresent()) {
			 return consultant.get();
		 }else {
			 throw new ConsultantNotFound(idConsultant);
		 }
		
	
	}


}
