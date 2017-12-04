package com.riocode.scoutpro.crawler.template;

import com.riocode.scoutpro.model.Player;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author Nikola Cvetkovic
 */

public abstract class WebDriverAbstractCrawlTemplate implements CrawlTemplate{
    protected Player player;
    protected String url;
    
    public WebDriverAbstractCrawlTemplate(Player player){
        this.player = player;
    }
    
    public Player start() throws IOException{
        return crawl(getDocument(this.url));
    }
        
    protected Document getDocument(String url) throws IOException{
        System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
        WebDriver driver = null;
        String html = null;
        try {
            driver = new ChromeDriver();
            driver.get(url);
            Thread.sleep(5000);
            html = driver.getPageSource();
        } catch (Exception e){
            
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
