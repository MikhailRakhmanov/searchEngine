package searchengine.services.parses;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ForkJoinPool;

public class SiteParserImpl implements SiteParser {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        MySiteParser parser = new MySiteParser(null, "https://google.com/");
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("data.txt"));
            bufferedWriter.write(forkJoinPool.invoke(parser).toString());
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Duration: " + (System.currentTimeMillis() - start + " ms."));
    }

    @Override
    public boolean parse(String url) {
        long start = System.currentTimeMillis();
        MySiteParser parser = new MySiteParser(null, "https://google.com/");
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("data.txt"));
            bufferedWriter.write(forkJoinPool.invoke(parser).toString());
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Duration: " + (System.currentTimeMillis() - start + " ms."));
        return true;
    }

}
