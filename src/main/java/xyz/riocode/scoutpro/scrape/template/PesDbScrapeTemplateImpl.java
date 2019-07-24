package xyz.riocode.scoutpro.scrape.template;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import xyz.riocode.scoutpro.enums.Foot;
import xyz.riocode.scoutpro.model.PesDbInfo;
import xyz.riocode.scoutpro.model.Player;
import xyz.riocode.scoutpro.scrape.helper.ScrapeHelper;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class PesDbScrapeTemplateImpl extends SimpleAbstractScrapeTemplate {

    @Override
    public Player scrape(Player player){
        Document page = getPage(player.getPesDbUrl());
        return scrape(player, page);
    }

    @Override
    public Player scrape(Player player, Document page) {
        PesDbInfo pesDbInfo= player.getPesDbInfo();
        if(pesDbInfo == null) {
            pesDbInfo = new PesDbInfo();
            pesDbInfo.setPlayer(player);
            player.setPesDbInfo(pesDbInfo);
        }
        // todo implement async
        scrapeCoreData(page, pesDbInfo);
        scrapeRatings(page, pesDbInfo);
        scrapeAdditionalData(page, pesDbInfo);
        return player;
    }

    private void scrapeCoreData(Document doc, PesDbInfo pesDbInfo){
        if(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(1) table tr:nth-of-type(3) td a").equals("Free Agent")){
            extractCoreDataFreePlayer(doc, pesDbInfo);
        } else {
            extractCoreDataStandard(doc, pesDbInfo);
        }
    }

    private void scrapeRatings(Document doc, PesDbInfo pesDbInfo){
        pesDbInfo.setAttackingProwess(
                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(2) table tr:nth-of-type(1) td")));
        pesDbInfo.setBallControl(
                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(2) table tr:nth-of-type(2) td")));
        pesDbInfo.setDribbling(
                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(2) table tr:nth-of-type(3) td")));
        pesDbInfo.setLowPass(
                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(2) table tr:nth-of-type(4) td")));
        pesDbInfo.setLoftedPass(
                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(2) table tr:nth-of-type(5) td")));
        pesDbInfo.setFinishing(
                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(2) table tr:nth-of-type(6) td")));
        pesDbInfo.setPlaceKicking(
                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(2) table tr:nth-of-type(7) td")));
        pesDbInfo.setSwerve(
                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(2) table tr:nth-of-type(8) td")));
        pesDbInfo.setHeader(
                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(2) table tr:nth-of-type(9) td")));
        pesDbInfo.setDefensiveProwess(
                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(2) table tr:nth-of-type(10) td")));
        pesDbInfo.setBallWinning(
                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(2) table tr:nth-of-type(11) td")));
        pesDbInfo.setKickingPower(
                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(2) table tr:nth-of-type(12) td")));
        pesDbInfo.setSpeed(
                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(2) table tr:nth-of-type(13) td")));
        pesDbInfo.setExplosivePower(
                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(2) table tr:nth-of-type(14) td")));
        pesDbInfo.setUnwaveringBalance(
                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(2) table tr:nth-of-type(15) td")));
        pesDbInfo.setPhysicalContact(
                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(3) table tr:nth-of-type(1) td")));
        pesDbInfo.setJump(
                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(3) table tr:nth-of-type(2) td")));
        pesDbInfo.setGoalkeeping(
                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(3) table tr:nth-of-type(3) td")));
        pesDbInfo.setGkCatch(
                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(3) table tr:nth-of-type(4) td")));
        pesDbInfo.setClearing(
                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(3) table tr:nth-of-type(5) td")));
        pesDbInfo.setReflexes(
                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(3) table tr:nth-of-type(6) td")));
        pesDbInfo.setCoverage(
                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(3) table tr:nth-of-type(7) td")));
        pesDbInfo.setStamina(
                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(3) table tr:nth-of-type(8) td")));
        pesDbInfo.setWeakFootUsage(
                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(3) table tr:nth-of-type(9) td")));
        pesDbInfo.setWeakFootAccuracy(
                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(3) table tr:nth-of-type(10) td")));
        pesDbInfo.setForm(
                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(3) table tr:nth-of-type(11) td")));
        pesDbInfo.setInjuryResistance(
                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(3) table tr:nth-of-type(12) td")));
        pesDbInfo.setOverallRating(
                Integer.parseInt(ScrapeHelper.getElementDataOwn(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(3) table tr:nth-of-type(14) td")));
    }

    private void scrapeAdditionalData(Document doc, PesDbInfo pesDbInfo){
        String playingStyles = null;
        Set<String> playerSkills = new HashSet<>();
        Set<String> comPlayingStyles = new HashSet<>();
        Elements addInfo = ScrapeHelper.getElements(doc, "table.playing_styles tr");
        int counter = 0;
        String value;
        for (Element e : addInfo) {
            if(ScrapeHelper.getElement(e, "th") != null){
                counter++;
                continue;
            }
            value = ScrapeHelper.getElementData(e, "td");
            switch(counter){
                case 1:
                    playingStyles = value;
                break;
                case 2:
                    if(!value.equals("-")) playerSkills.add(value);
                break;
                case 3:
                    if(!value.equals("-")) comPlayingStyles.add(value);
                break;
            }
        }
        pesDbInfo.setPlayingStyle(playingStyles);
        pesDbInfo.setPlayerSkills(playerSkills);
        pesDbInfo.setComPlayingStyles(comPlayingStyles);
    }

    private void extractCoreDataStandard(Document doc, PesDbInfo pesDbInfo){
        String pesDbName = ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(1) table tr:nth-of-type(1) td");
        pesDbInfo.setPlayerName(pesDbName);
        String teamName = ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(1) table tr:nth-of-type(3) td a");
        pesDbInfo.setTeamName(teamName);
        String foot = ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(1) table tr:nth-of-type(10) td");
        foot = foot.split(" ")[0];
        // todo
        pesDbInfo.setFoot(Foot.valueOf(foot.toUpperCase()));
        String weekCondition = ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(1) table tr:nth-of-type(11) td");
        pesDbInfo.setWeekCondition(weekCondition!=null?weekCondition.charAt(0):null);
        String primaryPosition = ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(1) table tr:nth-of-type(12) td div");
        //todo
//        for (Positions p : Positions.values()) {
//            if(p.toString().equals(primaryPosition)){
//                pesDbInfo.setPositionNumberValue(p.getNumberValue());
//            }
//        }
        pesDbInfo.setPrimaryPosition(primaryPosition);
        pesDbInfo.setOtherStrongPositions(extractOtherStrongPositions(doc, pesDbInfo));
        pesDbInfo.setOtherWeakPositions(extractOtherWeakPositions(doc));
        pesDbInfo.setLastCheck(LocalDateTime.now());
    }

    private void extractCoreDataFreePlayer(Document doc, PesDbInfo pesDbInfo){
        String pesDbName = ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(1) table tr:nth-of-type(1) td");
        pesDbInfo.setPlayerName(pesDbName);
        String teamName = ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(1) table tr:nth-of-type(2) td a");
        pesDbInfo.setTeamName(teamName);
        String foot = ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(1) table tr:nth-of-type(9) td");
        foot = foot.split(" ")[0];
        //todo
        pesDbInfo.setFoot(Foot.valueOf(foot.toUpperCase()));
        String weekCondition = ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(1) table tr:nth-of-type(10) td");
        pesDbInfo.setWeekCondition(weekCondition!=null?weekCondition.charAt(0):null);
        String primaryPosition = ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(1) table tr:nth-of-type(11) td div");
        //todo
//        for (Positions p : Positions.values()) {
//            if(p.toString().equals(primaryPosition)){
//                pesDbInfo.setPositionNumberValue(p.getNumberValue());
//            }
//        }
        pesDbInfo.setPrimaryPosition(primaryPosition);
        pesDbInfo.setOtherStrongPositions(extractOtherStrongPositions(doc, pesDbInfo));
        pesDbInfo.setOtherWeakPositions(extractOtherWeakPositions(doc));
        pesDbInfo.setLastCheck(LocalDateTime.now());
    }

    private Set<String> extractOtherWeakPositions(Element e){
        Set<String> positions = new HashSet<>();
        Elements weakerPositions = ScrapeHelper.getElements(e, "table.player tbody table tr td.positions div span.pos1");
        String s;
        if(weakerPositions.size() > 0){
            for(Element el : weakerPositions){
                s = el.text();
                positions.add(s);
            }
        }

        return positions;
    }
    private Set<String> extractOtherStrongPositions(Element e, PesDbInfo pesDbInfo){
        Set<String> positions = new HashSet<>();
        Elements strongerPositions = ScrapeHelper.getElements(e, "table.player tbody table tr td.positions div span.pos2");
        String s;
        if(strongerPositions.size() > 0){
            for(Element el : strongerPositions){
                s = el.text();
                if(!s.equals(pesDbInfo.getPrimaryPosition()))
                    positions.add(s);
            }
        }

        return positions;
    }

    private enum Positions {
        GK(1), CB(2), LB(3), RB(4), DMF(5), CMF(6), LMF(7), RMF(8), AMF(9), LWF(10), RWF(11), SS(12), CF(13);

        private int numberValue;

        Positions(int numberValue) {
            this.numberValue = numberValue;
        }

        public int getNumberValue(){
            return this.numberValue;
        }

    }
    
}
