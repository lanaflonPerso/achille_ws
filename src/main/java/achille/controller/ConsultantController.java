package achille.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
import achille.model.Fiche.Sexe;
import achille.password.PasswordGenerator;
import achille.password.PasswordUtils;
import achille.service.EmailService;
import achille.model.User;


@CrossOrigin(origins = "*")
@RestController
public class ConsultantController {
	
	@Autowired
	ConsultantDAO consultantDAO;
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
	
	//Retourne la liste de tous les consultants
	@RequestMapping(value ="/consultants")
	List<Consultant> findAll() {
		return  (List<Consultant>) consultantDAO.findAll();
	}
	
	
	//Insère un consultant
	@RequestMapping(value ="/consultant",  method=RequestMethod.POST)
	Boolean create( @RequestBody Consultant c) throws AddressException, MessagingException, IOException {
		
		return create1consultant(c);
		
	}
	//Insère une liste de consultants
	@RequestMapping(value ="/consultant/list",  method=RequestMethod.POST)
	Boolean create( @RequestBody List<Consultant> l_c) throws AddressException, MessagingException, IOException {
		
		Date insertDate = new Date();
		
		Boolean allSave = true;
		Boolean currentSave = true;
		
		for(Consultant c : l_c) {
			
			currentSave = create1consultant(c);
			
			allSave = allSave && currentSave;
			
		}
		
		return allSave;
		
	}
	
	//Retourne un consultant
	@RequestMapping(value ="/consultant/{id}",  method=RequestMethod.GET)
	Optional<Consultant> find(@PathVariable(value="id", required=true) int id) {
		if(!consultantDAO.existsById(id)) {
			throw new ConsultantNotFound(id);
		}
		return consultantDAO.findById(id);
	}
	
	Boolean consultantExisted(String nom, String matricule) {
		
		List<Consultant> l_c = (List<Consultant>) consultantDAO.findAll();
		
		for (Consultant consultant : l_c) {
			
			if (consultant.getNom().equals(nom) && consultant.getMatricule().equals(matricule)) 
				return true;
			
		}
		
		return false;
	}
	
	// Update un consultant
	@RequestMapping(value ="/consultant",  method=RequestMethod.PUT)
	Consultant update( @RequestBody Consultant c) {
		if(!consultantDAO.existsById(c.getId())) {
			throw new ConsultantNotFound(c.getId());
		}
		c.setInsertionDate(new Date());
		return consultantDAO.save(c);
		
	}
	
	//Renvoie un modèle de consultant
	@RequestMapping(value ="/consultant/exemple",  method=RequestMethod.GET)
	Consultant exemple() {
		Consultant c= new Consultant();
		Adresse a = new Adresse();
		Fiche f = new Fiche();
		a.setRue("6 Villa Leblanc");
		a.setCodePostal(92120);
		a.setVille("Montrouge");
		
		f.setAdresse(a);
		f.setDateNaissance(new Date(2017,07,24));
		f.setSexe(Sexe.Homme);
		
		c.setNom("BURBAN");
		c.setFiche(f);
		c.setPrenom("Eugène");
		
		return c;
	}
	
	private boolean create1consultant(Consultant c) throws AddressException, MessagingException, IOException {
		
		if (consultantExisted(c.getNom(), c.getMatricule()))
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
		
		User u = new User(c.getNom() + c.getMatricule(), password, salt);
		userDAO.save(u);

		EmailService em = new EmailService();
		String content = "nom : " + c.getNom() + System.getProperty("line.separator") +
							"matricule : " + c.getMatricule() + System.getProperty("line.separator") + 
							"mot de passe : " + passwordGenerated;
		String subject = "Création de compte";
		String recipient = c.getEmail();
		
		em.sendMail(subject, content, recipient);
		
		consultantDAO.save(c);
		
		return true;
		
	}
	

}
