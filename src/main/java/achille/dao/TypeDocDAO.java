package achille.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import achille.model.TypeDoc;

@Repository
public interface TypeDocDAO extends CrudRepository<TypeDoc, Integer> {

}
