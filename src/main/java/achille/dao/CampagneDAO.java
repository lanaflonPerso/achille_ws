package achille.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import achille.model.Campagne;

@Repository
public interface CampagneDAO extends CrudRepository<Campagne, Integer> {

	List<Campagne> findByEtat(String c);

}
