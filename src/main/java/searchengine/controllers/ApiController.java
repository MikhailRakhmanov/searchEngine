package searchengine.controllers;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import searchengine.dto.statistics.StatisticsResponse;
import searchengine.model.PageDAO;
import searchengine.model.SiteDAO;
import searchengine.model.Status;
import searchengine.repositories.PageRepository;
import searchengine.repositories.SiteRepository;
import searchengine.services.StatisticsService;

import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class ApiController {
    private final StatisticsService statisticsService;
    private final SiteRepository siteRepository;
    private final PageRepository pageRepository;


    @Autowired
    public ApiController(PageRepository pageRepository,StatisticsService statisticsService, SiteRepository siteRepository) {
        this.statisticsService = statisticsService;
        this.siteRepository = siteRepository;
        this.pageRepository = pageRepository;
    }

    @GetMapping("/statistics")
    public ResponseEntity<StatisticsResponse> statistics() {
        return ResponseEntity.ok(statisticsService.getStatistics());
    }

    @PostMapping("/indexPage")
    public ResponseEntity<Boolean> indexingPage(@RequestParam String url) {
        SiteDAO siteDAO = new SiteDAO();
        siteDAO.setStatus(Status.INDEXING);
        siteDAO.setStatusTime(LocalDateTime.now());
        siteDAO.setUrl(url);
        siteRepository.save(siteDAO);
        try {
            Connection connection = Jsoup.connect(url);
            Document document = connection.get();
            PageDAO pageDAO = new PageDAO();
            pageDAO.setPath("/");
            pageDAO.setContent(document.toString());
            pageDAO.setSite(siteDAO);
            siteDAO.setName(document.title());
            pageRepository.save(pageDAO);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        siteDAO.setStatusTime(LocalDateTime.now());

        siteDAO.setStatus(Status.INDEXED);
        siteRepository.save(siteDAO);
        return ResponseEntity.ok(true);
    }
}
