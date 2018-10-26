package achille.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import achille.model.TypeContrat;

@Repository
public interface TypeContratDAO extends CrudRepository<TypeContrat, Integer> {

}
