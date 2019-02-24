package achille.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import achille.model.Consultant;

@Repository
public interface ConsultantDAO extends CrudRepository<Consultant, Integer> {

	Consultant findByEmail(String mail);


}

