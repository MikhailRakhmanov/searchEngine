package searchengine.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import searchengine.model.PageDAO;
@Repository
public interface PageRepository extends CrudRepository<PageDAO,Integer> {
}
