package achille.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import achille.model.Campagne;
import achille.model.Consultant;
import achille.model.ConsultantCampagne;

@Repository
public interface ConsultantCampagneDAO extends CrudRepository<ConsultantCampagne, Integer> {

	List<Campagne> findByCampagne(String c);

	
	List<ConsultantCampagne> findByCampagneAndConsultant(Campagne campagneCourante, Consultant consultant);


	List<ConsultantCampagne> findByCampagneIdCampagneAndConsultantId(int i,
			int j);


	List<ConsultantCampagne> findAllByCampagne(Campagne campagneCourante);





}
