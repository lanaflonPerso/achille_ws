package achille.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import achille.auth.Authority;
import achille.dao.AdresseDAO;
import achille.dao.ConsultantDAO;
import achille.dao.FicheDAO;
import achille.dao.PartenaireDAO;
import achille.dao.SocieteDAO;
import achille.dao.TypeContratDAO;
import achille.dao.UserDAO;
import achille.exception.CampagneException;
import achille.exception.ConsultantException;
import achille.exception.ConsultantNotFound;
import achille.model.Adresse;
import achille.model.Consultant;
import achille.model.Fiche;
import achille.model.User;
import achille.password.PasswordGenerator;
import achille.password.PasswordUtils;
import achille.utils.Convert;
import achille.wrapper.ConsultantWrapper;

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
	ConsultantCampagneService consultantCampagneService;
	@Autowired
	ConsultantDAO consultantDAO;

	public int consultantExisted(String nom, String matricule) {
		List<Consultant> l_c = (List<Consultant>) consultantDAO.findAll();
		for (Consultant consultant : l_c) {
			if (consultant.getNom().equals(nom) && consultant.getMatricule().equals(matricule))
				return consultant.getId();
		}
		return 0;
	}
	public boolean createConsultant(Consultant c) throws AddressException, MessagingException, IOException {

		if (consultantService.consultantExisted(c.getNom(), c.getMatricule()) != 0) {
			return false;
		}

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

		if (c.getSociete() != null)
			c.setSociete(societeDAO.save(c.getSociete()));
		if (c.getTypeContrat() != null)
			c.setTypeContrat(typeContratDAO.save(c.getTypeContrat()));
		if (c.getPartenaire() != null)
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

		if (c.getSendMail() == null) {
			c.setSendMail(false);
			c = consultantDAO.save(c);
		}

		if (c.getSendMail()) {
			EmailService em = new EmailService();
			String content = "nom : " + c.getNom() + System.getProperty("line.separator") + "matricule : "
					+ c.getMatricule() + System.getProperty("line.separator") + "mot de passe : " + passwordGenerated;
			String subject = "[" + c.getSociete().getValue() + "- Application Payes] " + "Création de compte";
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
	public List<Consultant> getConsultantsMail(String typeMail)
			throws ConsultantException, CampagneException {
		List<Consultant> consultants = consultantDAO.findBySendMail(true);
		List<Consultant> retour = new ArrayList<>();
		if (typeMail.equals("ouverture")) {
			for (Consultant consultant : consultants) {
				if ( consultant.getCampagnePaie() ) {
					retour.add(consultant);
				};
			}
			return retour;
		} else if (typeMail.equals("relance")) {
			for (Consultant consultant : consultants) {
				if ( consultantCampagneService.getConsultantCampagneCouranteEtat(consultant.getId()) == 1 &&
						consultant.getCampagnePaie() ){
					retour.add(consultant);
				};	
			}
			return retour;
		} else {
			throw new ConsultantException ("Le type de mail" + typeMail + "n'existe pas.");
		}

	}
	

	public int updateConsultant(MultipartFile multipartfile) throws Exception {
		ConsultantWrapper cWrapper = new ConsultantWrapper();

		File file = Convert.multipartFile2file(multipartfile);
		BufferedReader csvReader = new BufferedReader(new FileReader(file));
		String UTF8_BOM="\uFEFF";
		String readLine = csvReader.readLine();
		if (readLine.startsWith(UTF8_BOM)) {
			readLine=readLine.substring(1);
		}
		String[] header = readLine.split(";");

		if (cWrapper.controleHeader(header)) {

			/*On passe tous les consultants en base a inactif*/
			List<Consultant> consultantExistant = (List<Consultant>) consultantDAO.findAll();
			for (Consultant c : consultantExistant) {
				c.setCampagnePaie(false);
				consultantDAO.save(c);
			}

			/*On crée ou update en fonction du contenu du fichier*/
			String row;
			int nbConsultant=0;

			while ((row = csvReader.readLine()) != null) {

				String[] data = row.split(";");
				Consultant c = cWrapper.createConsultant(header, data);
				int idC = this.consultantExisted(c.getNom(), c.getMatricule());
				if (idC != 0) {
					if (c.getCampagnePaie()) {
						c.setId(idC);
						consultantDAO.save(c);
						nbConsultant=nbConsultant+1;
					}
				} else {
					this.createConsultant(c);

					nbConsultant=nbConsultant+1;
				}
			}
			csvReader.close();
			return nbConsultant;
		} else {
			csvReader.close();
			throw new ConsultantException("Format de fichier incorrect. Il faut importer un .csv avec au minimum les colonnes MATRI, SOCIETE, NOM, PRENOM et CAMPAGNE-PAIE");
		}


	}
}
