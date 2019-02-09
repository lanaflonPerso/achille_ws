package achille.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;

import achille.dao.CampagneDAO;
import achille.model.Campagne;

@Service
public class CampagneService {

	
	@Autowired
	CampagneDAO campagneDAO;
	
	public Iterable<Campagne> get(Predicate predicate){
		  return campagneDAO.findAll(predicate);
		}
	
}
