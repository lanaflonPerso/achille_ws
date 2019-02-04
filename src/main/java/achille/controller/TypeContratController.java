package achille.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import achille.dao.TypeContratDAO;
import achille.model.TypeContrat;


@CrossOrigin(origins = "*")
@RestController
public class TypeContratController {

	public TypeContratController() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	TypeContratDAO typeContratDAO;

	
	//Insère une fiche
		@RequestMapping(value ="/typeContrat",  method=RequestMethod.GET)
		List<TypeContrat> findAll() {
			return  (List<TypeContrat>) typeContratDAO.findAll();
		}
		

}
