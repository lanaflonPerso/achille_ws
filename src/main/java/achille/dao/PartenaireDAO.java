package achille.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import achille.model.Partenaire;

@Repository
public interface PartenaireDAO extends CrudRepository<Partenaire, Integer> {

}
