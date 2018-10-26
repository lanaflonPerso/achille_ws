package achille.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import achille.model.Societe;

@Repository
public interface SocieteDAO extends CrudRepository<Societe, Integer> {

}
