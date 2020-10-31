package xyz.riocode.scoutpro.scrape.template;

import org.apache.commons.lang3.math.NumberUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import xyz.riocode.scoutpro.model.Player;
import xyz.riocode.scoutpro.scrape.helper.ScrapeHelper;

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
        scrapeCoreData(page, player);
        return player;
    }

    private void scrapeCoreData(Document doc, Player player){
        String teamName = ScrapeHelper.getElementData(doc, "table.innerTable tbody tr:nth-of-type(2) td:nth-of-type(2) p:nth-of-type(2) a");
        player.setPsmlTeam(teamName != null?teamName:"Free");
        String teamValue = ScrapeHelper.getElementDataOwn(doc, "table.innerTable tbody tr:nth-of-type(2) td:nth-of-type(3) p:nth-of-type(1)");
        if(teamValue != null){
            teamValue = teamValue.replaceAll("[^0-9,]", "").replace(",", "");
        }
        player.setPsmlValue(NumberUtils.isCreatable(teamValue)?new BigDecimal(teamValue):BigDecimal.ZERO);
        player.setPsmlLastCheck(LocalDateTime.now());
    }

    @Override
    protected Document getPage(String url){
        WebDriver driver = null;
        String html = null;
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        try {
            driver = new ChromeDriver(chromeOptions);
            driver.get(url);
            driver.manage().deleteAllCookies();
            driver.manage().addCookie(new Cookie("PHPSESSID", "dd30cdbecced0f98b3483e64f89a7d27"));
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