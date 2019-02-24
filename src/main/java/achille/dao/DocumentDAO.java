package achille.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import achille.model.Document;

@Repository
public interface DocumentDAO extends CrudRepository<Document, Integer> {

	Document findByName(String name);

}
