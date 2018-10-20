package achille.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import achille.model.Doc;

@Repository
public interface DocumentDAO extends CrudRepository<Doc, Integer> {

}
