package achille.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import achille.dao.PartenaireDAO;
import achille.model.Partenaire;


@CrossOrigin(origins = "*")
@RestController
public class PartenaireController {

	public PartenaireController() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	PartenaireDAO partenaireDAO;

	@RequestMapping(value ="/partenaires",  method=RequestMethod.GET)
	List<Partenaire> findAll() {
		return  (List<Partenaire>) partenaireDAO.findAll();
	}
		

}
