package searchengine.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import searchengine.model.LemmaDAO;

@Repository
public interface LemmaRepository extends CrudRepository<LemmaDAO, Integer> {
}