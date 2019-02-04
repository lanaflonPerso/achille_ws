package achille.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import achille.model.Campagne;

@Repository
public interface CampagneDAO extends CrudRepository<Campagne, Integer> {

}
