package com.riocode.scoutpro.crawler.template;

import com.riocode.scoutpro.model.Player;
import org.jsoup.nodes.Document;

/**
 *
 * @author Nikola Cvetkovic
 */

public abstract class AbstractCrawlTemplate implements CrawlTemplate{
    
    protected Player player;
    protected String url;
    
    public AbstractCrawlTemplate(Player player){
        this.player = player;
    }
    
    @Override
    public abstract void crawl(Document document);

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
