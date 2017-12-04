package com.riocode.scoutpro.crawler.template;

import com.riocode.scoutpro.model.Player;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author Nikola Cvetkovic
 */

public abstract class CoreAbstractCrawlTemplate implements CrawlTemplate{
    
    protected Player player;
    protected String url;
    
    public CoreAbstractCrawlTemplate(Player player){
        this.player = player;
    }
    
    public Player start() throws IOException{
        return crawl(getDocument(this.url));
    }
        
    protected Document getDocument(String url) throws IOException{
        return Jsoup.connect(url).get();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
        
    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
            
}
