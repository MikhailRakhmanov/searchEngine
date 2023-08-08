package searchengine.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import searchengine.model.SiteDAO;

@Repository
public interface SiteRepository extends CrudRepository<SiteDAO,Integer> {
}
