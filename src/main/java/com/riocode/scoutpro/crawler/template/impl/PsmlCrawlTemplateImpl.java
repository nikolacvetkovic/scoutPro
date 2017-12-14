package com.riocode.scoutpro.crawler.template.impl;

import com.riocode.scoutpro.crawler.helper.CrawlHelper;
import com.riocode.scoutpro.crawler.template.WebDriverAbstractCrawlTemplate;
import com.riocode.scoutpro.model.Player;
import com.riocode.scoutpro.model.PsmlInfo;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author Nikola Cvetkovic
 */

public class PsmlCrawlTemplateImpl extends WebDriverAbstractCrawlTemplate{
    
    private final PsmlInfo psmlInfo;

    public PsmlCrawlTemplateImpl(Player player) {
        super(player);
        this.url = player.getPsmlUrl();
        this.psmlInfo = new PsmlInfo();
        this.psmlInfo.setPlayer(player);
        this.player.getPsmlInfoList().add(this.psmlInfo);
    }
    
    public PsmlCrawlTemplateImpl(PsmlInfo psmlInfo){
        super(psmlInfo.getPlayer());
        this.url = this.player.getPsmlUrl();
        this.psmlInfo = psmlInfo;
    }
    
    @Override
    public Player crawl(Document document) throws IOException {
        crawlCoreData(document);
                
        return this.player;
    }

    private void crawlCoreData(Document doc){
        String teamName = CrawlHelper.getElementData(doc, "table.innerTable tbody tr:nth-of-type(2) td:nth-of-type(2) p:nth-of-type(2) a", false);
        psmlInfo.setTeamName(teamName);
        String teamValue = CrawlHelper.getElementData(doc, "table.innerTable tbody tr:nth-of-type(2) td:nth-of-type(3) p:nth-of-type(1)", true);
        teamValue = teamValue.replaceAll("[^0-9,]", "").replace(",", "");
        psmlInfo.setTeamValue(new BigDecimal(teamValue));
        psmlInfo.setLastMeasured(LocalDateTime.now());
    }
    
    @Override
    protected Document getDocument(String url) throws IOException {
        System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
        WebDriver driver = null;
        String html = null;
        try {
            driver = new ChromeDriver();
            driver.get(url);
            Thread.sleep(5000);
            driver.switchTo().frame("content");
            html = driver.getPageSource();
        } catch (Exception e){
            
        } finally {
            if(driver != null)
                driver.quit();
        }
        return Jsoup.parse(html);
    }
    
}
