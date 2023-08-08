package searchengine.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import searchengine.model.SiteDAO;
import searchengine.repositories.SiteRepository;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Component
public class SitesList {

    private final SiteRepository siteRepository;

    @Autowired
    public SitesList(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    public List<SiteDAO> getAllSites() {
        List<SiteDAO> sites = new ArrayList<>();
        Iterable<SiteDAO> siteIterable = siteRepository.findAll();
        for(SiteDAO siteDAO:siteIterable){
            sites.add(siteDAO);
        }
        return sites;
    }
}
