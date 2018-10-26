package achille.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import achille.model.Fiche;

@Repository
public interface FicheDAO extends CrudRepository<Fiche, Integer> {

}
