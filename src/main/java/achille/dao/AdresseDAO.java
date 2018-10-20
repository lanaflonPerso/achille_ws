package achille.dao;

import org.springframework.stereotype.Repository;

import achille.model.Adresse;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface AdresseDAO extends CrudRepository<Adresse, Integer> {

}
