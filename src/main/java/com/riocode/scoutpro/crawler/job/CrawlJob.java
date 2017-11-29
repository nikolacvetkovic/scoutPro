package com.riocode.scoutpro.crawler.job;

import com.riocode.scoutpro.crawler.template.AbstractCrawlTemplate;
import com.riocode.scoutpro.model.Player;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author Nikola Cvetkovic
 */

public class CrawlJob {
    
    private final AbstractCrawlTemplate crawlTemplate;
        
    public CrawlJob(AbstractCrawlTemplate crawlTemplate){
        this.crawlTemplate = crawlTemplate;
    }
    
    public Player startJob() throws IOException{
        Document doc = getDocument(crawlTemplate.getUrl());
        
        crawlTemplate.crawl(doc);
        
        return crawlTemplate.getPlayer();
    }
    
    private Document getDocument(String url) throws IOException{
        
        return Jsoup.connect(url).get();
    }
}
