package xyz.riocode.scoutpro.scrape.template.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import xyz.riocode.scoutpro.model.Player;
import xyz.riocode.scoutpro.model.PsmlInfo;
import xyz.riocode.scoutpro.scrape.helper.ScrapeHelper;
import xyz.riocode.scoutpro.scrape.template.WebDriverAbstractScrapeTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PsmlScrapeTemplateImpl extends WebDriverAbstractScrapeTemplate {
    @Override
    public Player scrape(Player player){
        Document page = getPage(player.getPsmlUrl());
        return scrape(player, page);
    }

    @Override
    public Player scrape(Player player, Document page) {
        PsmlInfo psmlInfo = player.getPsmlInfo();
        if(psmlInfo == null) {
            psmlInfo = new PsmlInfo();
            player.setPsmlInfo(psmlInfo);
            psmlInfo.setPlayer(player);
        }
        scrapeCoreData(page, psmlInfo);
        return player;
    }

    private void scrapeCoreData(Document doc, PsmlInfo psmlInfo){
        String teamName = ScrapeHelper.getElementData(doc, "table.innerTable tbody tr:nth-of-type(2) td:nth-of-type(2) p:nth-of-type(2) a");
        if(teamName == null){
            teamName = ScrapeHelper.getElementDataOwn(doc, "table.innerTable tbody tr:nth-of-type(2) td:nth-of-type(2) p:nth-of-type(2)");
        }
        psmlInfo.setPsmlTeam(teamName);
        String teamValue = ScrapeHelper.getElementDataOwn(doc, "table.innerTable tbody tr:nth-of-type(2) td:nth-of-type(3) p:nth-of-type(1)");
        if(!teamValue.contains(",")){
            teamValue = ScrapeHelper.getElementData(doc, "table.innerTable tbody tr:nth-of-type(2) td:nth-of-type(3) p:nth-of-type(1) span");
        }
        teamValue = teamValue.replaceAll("[^0-9,]", "").replace(",", "");
        psmlInfo.setPsmlValue(new BigDecimal(teamValue));
        psmlInfo.setLastCheck(LocalDateTime.now());
    }

    @Override
    protected Document getPage(String url){
        WebDriver driver = null;
        String html = null;
        try {
            driver = new ChromeDriver();
            driver.get(url);
            Thread.sleep(5000);
            driver.switchTo().frame("content");
            html = driver.getPageSource();
        } catch (InterruptedException e){

        } finally {
            if(driver != null)
                driver.quit();
        }
        return Jsoup.parse(html);
    }
    
}