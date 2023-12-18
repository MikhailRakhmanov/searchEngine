package searchengine.services.parses;

import lombok.NonNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class MySiteParser extends RecursiveTask<Link> {
    private final Link currentLink;

    public MySiteParser(Link parent, @NonNull String URL) {
        boolean isDuplicate = false;
        Link original = null;
        for(Link link:Link.allLinks){
            if (link.getValue().equals(URL)) {
                isDuplicate=true;
                original = link;
                break;
            }
        }
        if(isDuplicate) {
            currentLink = original;
        }
        else {
            currentLink = new Link(parent, URL);
        }
    }


    public void getFilteredPages() {
        try {
            Document doc = Jsoup.connect(currentLink.getValue()).timeout(20000).get();
            Elements elements = doc.select("a");
            String url = currentLink.getValue();
            for (Element element : elements) {
                String text = element.attr("abs:href");
                boolean isExist = false;
                synchronized (MySiteParser.class) {
                    for (Link link : Link.allLinks) {
                        if (link.getValue().equals(text)) {
                            isExist = true;
                            break;
                        }
                    }
                }
                if (!isExist) {
                    if (text.startsWith(url) && !text.contains("#")) {
                        synchronized (MySiteParser.class) {
                            new Link(currentLink, text);
                            System.out.println("Link added");
                        }
                    }
                }

            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }


    @Override
    protected Link compute() {
        {
            List<MySiteParser> parserTasks = Collections.synchronizedList(new ArrayList<>());
            this.getFilteredPages();
            for (Link child : currentLink.getChildren()) {
                MySiteParser parser = new MySiteParser(this.currentLink, child.getValue());
                parser.fork();
                parserTasks.add(parser);
            }
            for (MySiteParser parser : parserTasks) {
                parser.join();
            }
            return currentLink;
        }
    }
}
