package searchengine.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import searchengine.model.IndexDAO;

@Repository
public interface IndexRepository extends CrudRepository<IndexDAO, Integer> {
}