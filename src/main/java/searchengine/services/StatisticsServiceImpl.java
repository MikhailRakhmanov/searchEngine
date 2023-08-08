package searchengine.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import searchengine.config.SitesList;
import searchengine.dto.statistics.DetailedStatisticsItem;
import searchengine.dto.statistics.StatisticsData;
import searchengine.dto.statistics.StatisticsResponse;
import searchengine.dto.statistics.TotalStatistics;
import searchengine.model.SiteDAO;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final SitesList sites;

    @Override
    public StatisticsResponse getStatistics() {
//        String[] statuses = { "INDEXED", "FAILED", "INDEXING" };
//        String[] errors = {
//                "Ошибка индексации: главная страница сайта не доступна",
//                "Ошибка индексации: сайт не доступен",
//                ""
//        };
        TotalStatistics total = new TotalStatistics();
        total.setSites(sites.getAllSites().size());
        total.setIndexing(true);

        List<DetailedStatisticsItem> detailed = new ArrayList<>();
        List<SiteDAO> sitesList = sites.getAllSites();
        for (SiteDAO site : sitesList) {
            DetailedStatisticsItem item = new DetailedStatisticsItem();
            int pages = site.getPages().size();
            int lemmas = 0;
            item.setName(site.getName());
            item.setUrl(site.getUrl());

            item.setStatus(String.valueOf(site.getStatus()));
            item.setPages(pages);
            item.setLemmas(lemmas);
            item.setStatusTime(site.getStatusTime().toInstant(ZoneOffset.UTC).toEpochMilli());
            item.setError("");
            total.setPages(total.getPages() + pages);
            total.setLemmas(total.getLemmas() + lemmas);
            detailed.add(item);
        }
        StatisticsResponse response = new StatisticsResponse();
        StatisticsData data = new StatisticsData();
        data.setTotal(total);
        data.setDetailed(detailed);
        response.setStatistics(data);
        response.setResult(true);
        return response;
    }
}
