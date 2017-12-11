package com.riocode.scoutpro.crawler.template.impl;

import com.riocode.scoutpro.crawler.helper.CrawlHelper;
import com.riocode.scoutpro.crawler.template.CoreAbstractCrawlTemplate;
import com.riocode.scoutpro.model.PesDbInfo;
import com.riocode.scoutpro.model.Player;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Nikola Cvetkovic
 */

public class PesDbCrawlTemplateImpl extends CoreAbstractCrawlTemplate{

    private final PesDbInfo pesDbInfo;

    public PesDbCrawlTemplateImpl(Player player) {
        super(player);
        this.url = player.getPesDbUrl();
        this.pesDbInfo = new PesDbInfo();
    }      
    
    @Override
    public Player crawl(Document document) throws IOException {
        crawlCoreData(document);
        crawlRatings(document);
        crawlAdditionalData(document);
        this.pesDbInfo.setPlayer(this.player);
        this.player.getPesDbInfoList().add(this.pesDbInfo);
        
        return this.player;
    }
    
    public void crawlCoreData(Document doc){
        String season = this.url.split("/")[3].replaceAll("[^0-9]", "").trim();
        pesDbInfo.setSeason(season);
        String teamName = CrawlHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(1) table tr:nth-of-type(3) td a", false);
        pesDbInfo.setTeamName(teamName);
        String foot = CrawlHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(1) table tr:nth-of-type(10) td", false);
        foot = foot.split(" ")[0];
        pesDbInfo.setFoot(foot);
        String weekCondition = CrawlHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(1) table tr:nth-of-type(11) td", false);
        pesDbInfo.setWeekCondition(weekCondition);
        String primaryPosition = CrawlHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(1) table tr:nth-of-type(12) td div", false);
        pesDbInfo.setPrimaryPosition(primaryPosition);
        pesDbInfo.setOtherPositions(extractOtherPositions(doc));
        pesDbInfo.setLastMeasured(LocalDateTime.now());
    }
    
    public void crawlRatings(Document doc){
        pesDbInfo.setAttackingProwess(
                Integer.parseInt(CrawlHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(2) table tr:nth-of-type(1) td", false)));
        pesDbInfo.setBallControl(
                Integer.parseInt(CrawlHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(2) table tr:nth-of-type(2) td", false)));
        pesDbInfo.setDribbling(
                Integer.parseInt(CrawlHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(2) table tr:nth-of-type(3) td", false)));
        pesDbInfo.setLowPass(
                Integer.parseInt(CrawlHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(2) table tr:nth-of-type(4) td", false)));
        pesDbInfo.setLoftedPass(
                Integer.parseInt(CrawlHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(2) table tr:nth-of-type(5) td", false)));
        pesDbInfo.setFinishing(
                Integer.parseInt(CrawlHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(2) table tr:nth-of-type(6) td", false)));
        pesDbInfo.setPlaceKicking(
                Integer.parseInt(CrawlHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(2) table tr:nth-of-type(7) td", false)));
        pesDbInfo.setSwerve(
                Integer.parseInt(CrawlHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(2) table tr:nth-of-type(8) td", false)));
        pesDbInfo.setHeader(
                Integer.parseInt(CrawlHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(2) table tr:nth-of-type(9) td", false)));
        pesDbInfo.setDefensiveProwess(
                Integer.parseInt(CrawlHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(2) table tr:nth-of-type(10) td", false)));
        pesDbInfo.setBallWinning(
                Integer.parseInt(CrawlHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(2) table tr:nth-of-type(11) td", false)));
        pesDbInfo.setKickingPower(
                Integer.parseInt(CrawlHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(2) table tr:nth-of-type(12) td", false)));
        pesDbInfo.setSpeed(
                Integer.parseInt(CrawlHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(2) table tr:nth-of-type(13) td", false)));
        pesDbInfo.setExplosivePower(
                Integer.parseInt(CrawlHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(2) table tr:nth-of-type(14) td", false)));
        pesDbInfo.setBodyControl(
                Integer.parseInt(CrawlHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(2) table tr:nth-of-type(15) td", false)));
        pesDbInfo.setPhysicalContact(
                Integer.parseInt(CrawlHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(3) table tr:nth-of-type(1) td", false)));
        pesDbInfo.setJump(
                Integer.parseInt(CrawlHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(3) table tr:nth-of-type(2) td", false)));
        pesDbInfo.setStamina(
                Integer.parseInt(CrawlHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(3) table tr:nth-of-type(3) td", false)));
        pesDbInfo.setGoalkeeping(
                Integer.parseInt(CrawlHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(3) table tr:nth-of-type(4) td", false)));
        pesDbInfo.setCatching(
                Integer.parseInt(CrawlHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(3) table tr:nth-of-type(5) td", false)));
        pesDbInfo.setClearing(
                Integer.parseInt(CrawlHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(3) table tr:nth-of-type(6) td", false)));
        pesDbInfo.setReflexes(
                Integer.parseInt(CrawlHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(3) table tr:nth-of-type(7) td", false)));
        pesDbInfo.setCoverage(
                Integer.parseInt(CrawlHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(3) table tr:nth-of-type(8) td", false)));
        pesDbInfo.setForm(
                Integer.parseInt(CrawlHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(3) table tr:nth-of-type(9) td", false)));
        pesDbInfo.setInjuryResistance(
                Integer.parseInt(CrawlHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(3) table tr:nth-of-type(10) td", false)));
        pesDbInfo.setWeakFootUsage(
                Integer.parseInt(CrawlHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(3) table tr:nth-of-type(11) td", false)));
        pesDbInfo.setWeakFootAccuracy(
                Integer.parseInt(CrawlHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(3) table tr:nth-of-type(12) td", false)));
        pesDbInfo.setOverallRating(
                Integer.parseInt(CrawlHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(3) table tr:nth-of-type(14) td", false)));
    }
    
    private void crawlAdditionalData(Document doc){
        String playingStyles = null;
        List<String> playerSkills = new ArrayList<>();
        List<String> comPlayingStyles = new ArrayList<>();
        Elements addInfo = CrawlHelper.getElements(doc, "table.playing_styles tr");
        int counter = 0;
        for (Element e : addInfo) {
            if(e.select("th").first() != null){
                counter++;
                continue;
            }
            switch(counter){
                case 1:
                    playingStyles = CrawlHelper.getElementData(e, "td", false);
                break;
                case 2:
                    playerSkills.add(CrawlHelper.getElementData(e, "td", false));
                break;
                case 3:
                    comPlayingStyles.add(CrawlHelper.getElementData(e, "td", false));
                break;
            }
        }
        pesDbInfo.setPlayingStyle(playingStyles);
        pesDbInfo.setPlayerSkills(playerSkills);
        pesDbInfo.setComPlayingStyles(comPlayingStyles);
    }

    private List<String> extractOtherPositions(Element e){
        List<String> positions = new ArrayList<>();
        Elements weakerPositions = CrawlHelper.getElements(e, "table.player tbody table tr td.positions div span.pos1");
        String s = null;
        for(Element el : weakerPositions){
            s = el.text() + "(w)";
            positions.add(s);
        }
        Elements strongerPositions = CrawlHelper.getElements(e, "table.player tbody table tr td.positions div span.pos2");
        for(Element el : strongerPositions){
            s = el.text() + "(s)";
            positions.add(s);
        }
        
        return positions;
    }
    
}
