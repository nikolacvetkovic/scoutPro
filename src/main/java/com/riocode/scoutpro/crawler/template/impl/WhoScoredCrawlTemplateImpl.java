package com.riocode.scoutpro.crawler.template.impl;

import com.riocode.scoutpro.crawler.helper.CrawlHelper;
import com.riocode.scoutpro.crawler.template.WebDriverAbstractCrawlTemplate;
import com.riocode.scoutpro.model.Characteristic;
import com.riocode.scoutpro.model.CoreStats;
import com.riocode.scoutpro.model.Game;
import com.riocode.scoutpro.model.Player;
import com.riocode.scoutpro.model.PositionPlayedStats;
import com.riocode.scoutpro.model.WhoScoredInfo;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Nikola Cvetkovic
 */

public class WhoScoredCrawlTemplateImpl extends WebDriverAbstractCrawlTemplate{
    
    private final WhoScoredInfo whoScoredInfo;

    public WhoScoredCrawlTemplateImpl(Player player) {
        super(player);
        this.url = player.getWhoScoredUrl();
        this.whoScoredInfo = new WhoScoredInfo();
        this.whoScoredInfo.setPlayer(this.player);
        this.player.getWhoscoredInfoList().add(this.whoScoredInfo);
        this.whoScoredInfo.setCharacteristic(new Characteristic());
    }
    
    public WhoScoredCrawlTemplateImpl(WhoScoredInfo whoScoredInfo){
        super(whoScoredInfo.getPlayer());
        this.url = this.player.getWhoScoredUrl();
        this.whoScoredInfo = whoScoredInfo;
    }

    @Override
    public Player crawl(Document document) throws IOException {
        crawlCoreData(document);
        crawlPositionPlayedStats(document);
        crawlCharacteristic(document);
        crawlGames(document);
        
        return this.player;
    }
    
    private void crawlCoreData(Document doc){
        whoScoredInfo.setSeason(defineDate());
        Elements table = CrawlHelper.getElements(doc, "div#statistics-table-summary tbody#player-table-statistics-body tr");
        for (int i = 0; i < table.size(); i++) {
            CoreStats cs = null;
            if ((i+1) != table.size()){
                cs = extractCoreStats(table.get(i));
            } else {
                cs = extractCoreStatsAverage(table.get(i));
            }
            cs.setWhoScoredInfo(whoScoredInfo);
            whoScoredInfo.getCoreStatsList().add(cs);
        }
        whoScoredInfo.setLastMeasured(LocalDateTime.now());
    }
    
    private void crawlPositionPlayedStats(Document doc){
        Elements table = CrawlHelper.getElements(doc, "div#player-positional-statistics tbody tr");
        for (Element row : table) {
            PositionPlayedStats pps = new PositionPlayedStats();
            pps.setPosition(CrawlHelper.getElementData(row, "td.position-name", true));
            pps.setApps(Integer.parseInt(CrawlHelper.getElementData(row, "td:nth-of-type(2)", false)));
            pps.setGoals(Integer.parseInt(CrawlHelper.getElementData(row, "td:nth-of-type(3)", false)));
            pps.setAssists(Integer.parseInt(CrawlHelper.getElementData(row, "td:nth-of-type(4)", false)));
            pps.setRating(new BigDecimal(CrawlHelper.getElementData(row, "td.rating", false)));
            pps.setWhoScoredInfo(whoScoredInfo);
            whoScoredInfo.getPositionPlayedStatsList().add(pps);
        }
    }
    
    private void crawlCharacteristic(Document doc){
        List<String> strengths = new ArrayList<>();
        Elements el1 = CrawlHelper.getElements(doc, "div.character-card div.strengths tr");
        for(Element e : el1){
            String s = CrawlHelper.getElementData(e, "td:nth-of-type(1) div", true) + " - " + CrawlHelper.getElementData(e, "td:nth-of-type(2) span", false);
            strengths.add(s);
        }
        whoScoredInfo.getCharacteristic().setStrengths(strengths);
        
        List<String> weaknesses = new ArrayList<>();
        Elements el2 = CrawlHelper.getElements(doc, "div.character-card div.weaknesses tr");
        for (Element e : el2) {
            String s = CrawlHelper.getElementData(e, "td:nth-of-type(1) div", true) + " - " + CrawlHelper.getElementData(e, "td:nth-of-type(2) span", false);
            weaknesses.add(s);
        }
        whoScoredInfo.getCharacteristic().setWeaknesses(weaknesses);
        
        List<String> stylesOfPlay = new ArrayList<>();
        Elements el3 = CrawlHelper.getElements(doc, "div.style li");
        for (Element e : el3) {
            String s = e.ownText();
            stylesOfPlay.add(s);
        }
        whoScoredInfo.getCharacteristic().setStyleOfPlay(stylesOfPlay);
        whoScoredInfo.getCharacteristic().setWhoscoredinfo(whoScoredInfo);
    }
    
    private void crawlGames(Document doc){
        List<Game> games = new ArrayList<>();
        Elements elements = CrawlHelper.getElements(doc, "table#player-fixture tbody tr");
        for (Element e : elements) {
            Game g = new Game();
            g.setCompetition(CrawlHelper.getAttributeValue(e, "td.tournament span a", "title"));
            g.setDateOfGame(LocalDate.parse(CrawlHelper.getElementData(e, "td.date", false), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            g.setTeam1(CrawlHelper.getElementData(e, "td.home a", false));
            g.setTeam2(CrawlHelper.getElementData(e, "td.away a", false));
            g.setResult(CrawlHelper.getElementData(e, "td.result a", false));
            g.setMinutesPlayed(CrawlHelper.getElementData(e, "td.info", false));
            g.setRating(CrawlHelper.getElementData(e, "td.rating", false));
            g.setManOfTheMatch(isManOfTheMatch(e));
            
            Element el = CrawlHelper.getElement(e, "td:nth-of-type(7)");
            g.setGoals(extractAchievements(el, "Goal"));
            g.setAssists(extractAchievements(el, "Assist"));
            g.setYellowCard(extractYellowCard(el));
            g.setRedCard(extractRedCard(el));
            g.setWhoScoredInfo(whoScoredInfo);
            whoScoredInfo.getGameList().add(g);
        }
    }
    
    private String defineDate(){
        LocalDate date = LocalDate.now();
        if(date.getMonthValue() >= 7){
            return String.valueOf(date.getYear() + "/" + (date.getYear()+1));
        } else {
            return String.valueOf((date.getYear()-1) + "/" + date.getYear());
        }
    }
    
    private CoreStats extractCoreStats(Element e){
        CoreStats cs = new CoreStats();
        cs.setCompetition(CrawlHelper.getElementData(e, "td.tournament a.tournament-link", true));
        String apps = CrawlHelper.getElementData(e, "td:nth-of-type(2)", false);
        if(apps.contains("(")){
            apps = apps.replace(")", "");
            cs.setStartedApps(apps.split("\\(")[0]);
            cs.setSubApps(apps.split("\\(")[1]);
        } else {
            cs.setStartedApps(apps);
            cs.setSubApps("0");
        }
        cs.setMins(CrawlHelper.getElementData(e, "td[class*=minsPlayed]", false));
        cs.setGoals(CrawlHelper.getElementData(e, "td[class*=goal]", false));
        cs.setAssists(CrawlHelper.getElementData(e, "td[class*=assistTotal]", false));
        cs.setYellowCards(CrawlHelper.getElementData(e, "td[class*=yellowCard]", false));
        cs.setRedCards(CrawlHelper.getElementData(e, "td[class*=redCard]", false));
        cs.setShotsPerGame(CrawlHelper.getElementData(e, "td[class*=shotsPerGame]", false));
        cs.setPassSuccess(CrawlHelper.getElementData(e, "td[class*=passSuccess]", false));
        cs.setAerialsWon(CrawlHelper.getElementData(e, "td[class*=aerialWonPerGame]", false));
        cs.setManOfTheMatch(CrawlHelper.getElementData(e, "td[class*=manOfTheMatch]", false));
        cs.setRating(CrawlHelper.getElementData(e, "td[class*=rating]", false));
        
        return cs;
    }
    
    private CoreStats extractCoreStatsAverage(Element e){
        CoreStats cs = new CoreStats();
        Elements elements = CrawlHelper.getElements(e, "td strong");
        cs.setCompetition(elements.get(0).text());
        cs.setStartedApps(elements.get(1).text());
        cs.setSubApps("0");
        cs.setMins(elements.get(2).text());
        cs.setGoals(elements.get(3).text());
        cs.setAssists(elements.get(4).text());
        cs.setYellowCards(elements.get(5).text());
        cs.setRedCards(elements.get(6).text());
        cs.setShotsPerGame(elements.get(7).text());
        cs.setPassSuccess(elements.get(8).text());
        cs.setAerialsWon(elements.get(9).text());
        cs.setManOfTheMatch(elements.get(10).text());
        cs.setRating(elements.get(11).text());
        
        return cs;
    }
    
    private String extractAchievements(Element e, String achievement){
        Elements achievements = CrawlHelper.getElements(e, "span span[title="+ achievement +"]");
        
        return String.valueOf(achievements.size());
    }
    
    private boolean isManOfTheMatch(Element e){
        return CrawlHelper.getElement(e, "td:nth-of-type(6) span") != null;
    }
    
    private boolean extractYellowCard(Element e){
        return CrawlHelper.getElement(e, "span span[title=Yellow Card]") != null;
    }

    private boolean extractRedCard(Element e){
        return CrawlHelper.getElement(e, "span span[title=Red Card]") != null;
    }
    
}
