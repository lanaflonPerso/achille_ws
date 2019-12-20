package achille.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import achille.model.Consultant;

@Repository
public interface ConsultantDAO extends CrudRepository<Consultant, Integer> {

	Consultant findByEmail(String mail);

	List<Consultant> findBySendMail(boolean b);
	
	List<Consultant> findByCampagnePaie(boolean b);

}

