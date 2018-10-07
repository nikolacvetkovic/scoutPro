package com.riocode.scoutpro.scraper.template;

import com.riocode.scoutpro.exception.GetDocumentConnectionException;
import com.riocode.scoutpro.exception.ParseException;
import com.riocode.scoutpro.model.Player;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author Nikola Cvetkovic
 */

public abstract class CoreAbstractScrapeTemplate implements ScrapeTemplate {
    
    protected Player player;
    protected String url;
    
    public CoreAbstractScrapeTemplate(Player player){
        this.player = player;
    }
    
    public Player start(){
        try{
           return scrape(getDocument(this.url));
        } catch (NullPointerException | NumberFormatException ex){
            throw new ParseException("Bad regex");
        }
        
    }
        
    protected Document getDocument(String url){
        try {
            return Jsoup.connect(url).get();
        } catch (IOException ex) {
            throw new GetDocumentConnectionException(ex);
        }
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
