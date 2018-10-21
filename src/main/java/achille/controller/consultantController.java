package achille.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import achille.dao.ConsultantDAO;
import achille.model.Adresse;
import achille.model.Consultant;


@CrossOrigin(origins = "*")
@RestController
public class consultantController {
	@Autowired
	ConsultantDAO consultantDAO;
	
	//Retourne la liste de tous les consultants
	@RequestMapping(value ="/consultants")
	List<Consultant> findAll() {
		return  (List<Consultant>) consultantDAO.findAll();
	}
	
	
	//Insère un consultant
	@RequestMapping(value ="/consultant",  method=RequestMethod.POST)
	Consultant create( @RequestBody Consultant c) {
		System.out.println("toto");
		return consultantDAO.save(c);
	}
	
	//Renvoie un modèle de consultant
	@RequestMapping(value ="/consultant/exemple",  method=RequestMethod.GET)
	Consultant exemple() {
		Consultant c= new Consultant();
		Adresse a = new Adresse();
		a.setNumeros(6);
		a.setRue("Villa Leblanc");
		a.setCodePostal(92120);
		a.setVille("Montrouge");
		c.setNom("BURBAN");
		c.setAdresse(a);
		c.setPrenom("Eugène");
		c.setDateNaissance(new Date(2017,07,24));
		return c;
	}

	

}
