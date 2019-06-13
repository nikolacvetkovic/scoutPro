package com.riocode.scoutpro.scrape.template;

import com.riocode.scoutpro.exception.ParseException;
import com.riocode.scoutpro.model.Player;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author Nikola Cvetkovic
 */

public abstract class WebDriverAbstractScrapeTemplate implements ScrapeTemplate {
    protected Player player;
    protected String url;
    
    public WebDriverAbstractScrapeTemplate(Player player){
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
        WebDriver driver = null;
        String html = null;
        try {
            driver = new ChromeDriver();
            driver.get(url);
            Thread.sleep(5000);
            html = driver.getPageSource();
        } catch (InterruptedException e){
            
        } finally {
            if(driver != null)
                driver.quit();
        }
        return Jsoup.parse(html);
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
