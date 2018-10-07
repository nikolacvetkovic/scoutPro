package com.riocode.scoutpro.scraper.job;

import com.riocode.scoutpro.exception.DuplicatePlayerException;
import com.riocode.scoutpro.model.Player;
import com.riocode.scoutpro.scraper.helper.ScrapeHelper;
import com.riocode.scoutpro.service.PlayerService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ScrapeFreePlayers {

    private static final String PESDB_BASE_URL = "http://pesdb.net/pes2019";
    private static final String PSML_SEARCH_BASE_URL = "http://psml.rs/index.php?action=aps&x=0&y=0&q=";
    private static final String PSML_BASE_URL = "http://psml.rs/index.php";
    private static final String WS_SEARCH_BASE_URL = "https://www.whoscored.com/Search/?t=";
    private static final String WS_BASE_URL = "https://www.whoscored.com";
    private static final int overallimit = 80;

    @Autowired
    private PlayerService playerService;


    public void start() throws IOException, InterruptedException {
        int page = 1;
        Document document = Jsoup.connect(PESDB_BASE_URL + "/?page=3").get();
        Map<String, String> playersData = scrapeTableByOverall(document);
        if(playersData.isEmpty())
            System.exit(0);
        for (Map.Entry<String, String> e : playersData.entrySet()) {
            Thread.sleep(10000);
            Document psmlSearchResult = document = getDocumentByWebDriver(PSML_SEARCH_BASE_URL + e.getKey());
            Element psmlPlayer = ScrapeHelper.getElement(psmlSearchResult,"table.style2 tr:nth-of-type(2)");
            if(ScrapeHelper.getElement(psmlPlayer, "td:nth-of-type(1) a") == null) continue;
            if(ScrapeHelper.getElement(psmlPlayer, "td:nth-of-type(8) a") != null) continue;
            String psmlQueryUrl = ScrapeHelper.getAttributeValue(psmlPlayer, "td:nth-of-type(1) a", "href");
            String transfermarktUrl = ScrapeHelper.getAttributeValue(psmlPlayer, "td:nth-of-type(3) a", "href");
            Document tmPlayerPage = Jsoup.connect(transfermarktUrl).get();
            String playerName = ScrapeHelper.getElementData(tmPlayerPage, "h1[itemprop=name]", false);
            Document wsSearchResult = Jsoup.connect(WS_SEARCH_BASE_URL + playerName.replaceAll(" ", "+")).get();
            String wsQueryUrl = ScrapeHelper.getAttributeValue(wsSearchResult, "div.search-result tr:nth-of-type(2) td:nth-of-type(1) a", "href");
            if(wsQueryUrl == null) continue;
            if(!(isWSNameContainsPesDbName(ScrapeHelper.getElementData(wsSearchResult, "div.search-result tr:nth-of-type(2) td:nth-of-type(1) a", true), e.getKey())))continue;
            Thread.sleep(5000);
            Player player = new Player();
            player.setTransfermarktUrl(transfermarktUrl);
            player.setWhoScoredUrl(WS_BASE_URL + wsQueryUrl);
            player.setPesDbUrl(PESDB_BASE_URL + e.getValue());
            player.setPsmlUrl(PSML_BASE_URL + psmlQueryUrl);
            try {
                playerService.create(player);
            } catch (Exception ex){
                ex.printStackTrace();
                continue;
            }
        }

    }

    private static Map<String, String> scrapeTableByOverall(Document doc){
        Elements players = ScrapeHelper.getElements(doc, "table.players tr:not(:first-child)");
        Map<String, String> namesAndIds = players.stream()
                            .filter(e -> Integer.parseInt(ScrapeHelper.getElementData(e, "td:nth-of-type(9)", false)) > overallimit)
                            .collect(Collectors.toMap(e -> extractPesDbName(ScrapeHelper.getElementData(e, "td:nth-of-type(2) a", false)), e -> ScrapeHelper.getAttributeValue(e, "td:nth-of-type(2) a", "href").replaceAll("\\.", "")));

        return namesAndIds;
    }


    private static Document getDocumentByWebDriver(String url){
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

    private static boolean isWSNameContainsPesDbName(String wsName, String pesDbName){
        String pesDbNameShort = extractPesDbName(pesDbName);
        return wsName.toUpperCase().contains(pesDbNameShort);
    }

    private static String extractPesDbName(String name){
        String pesDbNameShort = name.toUpperCase();
        if(name.contains("."))
            pesDbNameShort = name.split("\\.")[1].trim();
        return pesDbNameShort;
    }
}
