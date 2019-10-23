package achille.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import achille.model.ValueUtile;


@CrossOrigin(origins = "*")
@RestController
public class ValueUtileController {

	public ValueUtileController() {
		// TODO Auto-generated constructor stub
	}
	
	//Insère une fiche
	@RequestMapping(value ="/sexe",  method=RequestMethod.GET)
	List<ValueUtile> findAllGenres() {
		List<ValueUtile> l = new ArrayList<>(); 
		l.add(new ValueUtile("Homme"));l.add((new ValueUtile("Femme")));
		return  l;
	}
	@RequestMapping(value ="/situationFamiliale",  method=RequestMethod.GET)
	List<ValueUtile> findAllSituationFamiliales() {
		List<ValueUtile> l = new ArrayList<>(); 
		l.add(new ValueUtile("Marie"));l.add((new ValueUtile("PACS"))); l.add((new ValueUtile("Celibataire")));
		return  l;
	}	
	@RequestMapping(value ="/nationalite",  method=RequestMethod.GET)
	List<ValueUtile> findAllNationalites() {
		List<ValueUtile> l = new ArrayList<>(); 
		l.add(new ValueUtile("FR"));l.add((new ValueUtile("EEE"))); l.add((new ValueUtile("HORS_EEE")));
		return  l;
	}
}
