package com.riocode.scoutpro.scrape.template.impl;

/**
 *
 * @author Nikola Cvetkovic
 */

public class PesDbScrapeTemplateImpl /*extends CoreAbstractScrapeTemplate*/ {

//    private final PesDbInfo pesDbInfo;
//
//    public PesDbScrapeTemplateImpl(Player player) {
//        super(player);
//        this.url = player.getPesDbUrl();
//        this.pesDbInfo = new PesDbInfo();
//        this.pesDbInfo.setPlayer(this.player);
//        this.player.getPesDbInfoList().add(this.pesDbInfo);
//    }
//
//    public PesDbScrapeTemplateImpl(PesDbInfo pesDbInfo){
//        super(pesDbInfo.getPlayer());
//        this.url = this.player.getPesDbUrl();
//        this.pesDbInfo = pesDbInfo;
//    }
//
//    @Override
//    public Player scrape(Document document){
//        scrapeCoreData(document);
//        scrapeRatings(document);
//        scrapeAdditionalData(document);

//        return this.player;
//    }
//
//    public void scrapeCoreData(Document doc){
//        String season = this.url.split("/")[3].replaceAll("[^0-9]", "").trim();
//        pesDbInfo.setSeason(season);
//        if(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(1) table tr:nth-of-type(3) td a", false).equals("Free Agent")){
//            extractCoreDataFreePlayer(doc);
//        } else {
//            extractCoreDataStandard(doc);
//        }
//    }
//
//    public void scrapeRatings(Document doc){
//        pesDbInfo.setAttackingProwess(
//                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(2) table tr:nth-of-type(1) td", false)));
//        pesDbInfo.setBallControl(
//                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(2) table tr:nth-of-type(2) td", false)));
//        pesDbInfo.setDribbling(
//                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(2) table tr:nth-of-type(3) td", false)));
//        pesDbInfo.setLowPass(
//                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(2) table tr:nth-of-type(4) td", false)));
//        pesDbInfo.setLoftedPass(
//                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(2) table tr:nth-of-type(5) td", false)));
//        pesDbInfo.setFinishing(
//                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(2) table tr:nth-of-type(6) td", false)));
//        pesDbInfo.setPlaceKicking(
//                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(2) table tr:nth-of-type(7) td", false)));
//        pesDbInfo.setSwerve(
//                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(2) table tr:nth-of-type(8) td", false)));
//        pesDbInfo.setHeader(
//                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(2) table tr:nth-of-type(9) td", false)));
//        pesDbInfo.setDefensiveProwess(
//                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(2) table tr:nth-of-type(10) td", false)));
//        pesDbInfo.setBallWinning(
//                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(2) table tr:nth-of-type(11) td", false)));
//        pesDbInfo.setKickingPower(
//                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(2) table tr:nth-of-type(12) td", false)));
//        pesDbInfo.setSpeed(
//                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(2) table tr:nth-of-type(13) td", false)));
//        pesDbInfo.setExplosivePower(
//                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(2) table tr:nth-of-type(14) td", false)));
//        pesDbInfo.setBodyControl(
//                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(2) table tr:nth-of-type(15) td", false)));
//        pesDbInfo.setPhysicalContact(
//                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(3) table tr:nth-of-type(1) td", false)));
//        pesDbInfo.setJump(
//                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(3) table tr:nth-of-type(2) td", false)));
//        pesDbInfo.setGoalkeeping(
//                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(3) table tr:nth-of-type(3) td", false)));
//        pesDbInfo.setCatching(
//                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(3) table tr:nth-of-type(4) td", false)));
//        pesDbInfo.setClearing(
//                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(3) table tr:nth-of-type(5) td", false)));
//        pesDbInfo.setReflexes(
//                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(3) table tr:nth-of-type(6) td", false)));
//        pesDbInfo.setCoverage(
//                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(3) table tr:nth-of-type(7) td", false)));
//        pesDbInfo.setStamina(
//                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(3) table tr:nth-of-type(8) td", false)));
//        pesDbInfo.setWeakFootUsage(
//                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(3) table tr:nth-of-type(9) td", false)));
//        pesDbInfo.setWeakFootAccuracy(
//                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(3) table tr:nth-of-type(10) td", false)));
//        pesDbInfo.setForm(
//                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(3) table tr:nth-of-type(11) td", false)));
//        pesDbInfo.setInjuryResistance(
//                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(3) table tr:nth-of-type(12) td", false)));
//        pesDbInfo.setOverallRating(
//                Integer.parseInt(ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(3) table tr:nth-of-type(14) td", false)));
//    }
//
//    private void scrapeAdditionalData(Document doc){
//        String playingStyles = null;
//        List<String> playerSkills = new ArrayList<>();
//        List<String> comPlayingStyles = new ArrayList<>();
//        Elements addInfo = ScrapeHelper.getElements(doc, "table.playing_styles tr");
//        int counter = 0;
//        for (Element e : addInfo) {
//            if(e.select("th").first() != null){
//                counter++;
//                continue;
//            }
//            switch(counter){
//                case 1:
//                    playingStyles = ScrapeHelper.getElementData(e, "td", false);
//                break;
//                case 2:
//                    playerSkills.add(ScrapeHelper.getElementData(e, "td", false));
//                break;
//                case 3:
//                    comPlayingStyles.add(ScrapeHelper.getElementData(e, "td", false));
//                break;
//            }
//        }
//        pesDbInfo.setPlayingStyle(playingStyles);
//        pesDbInfo.setPlayerSkills(playerSkills);
//        pesDbInfo.setComPlayingStyles(comPlayingStyles);
//    }
//
//    private void extractCoreDataStandard(Document doc){
//        String pesDbName = ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(1) table tr:nth-of-type(1) td", false);
//        pesDbInfo.setPesDbName(pesDbName);
//        String teamName = ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(1) table tr:nth-of-type(3) td a", false);
//        pesDbInfo.setTeamName(teamName);
//        String foot = ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(1) table tr:nth-of-type(10) td", false);
//        foot = foot.split(" ")[0];
//        pesDbInfo.setFoot(foot);
//        String weekCondition = ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(1) table tr:nth-of-type(11) td", false);
//        pesDbInfo.setWeekCondition(weekCondition);
//        String primaryPosition = ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(1) table tr:nth-of-type(12) td div", false);
//        for (Positions p : Positions.values()) {
//            if(p.toString().equals(primaryPosition)){
//                pesDbInfo.setPositionNumberValue(p.getNumberValue());
//            }
//        }
//        pesDbInfo.setPrimaryPosition(primaryPosition);
//        pesDbInfo.setOtherStrongPositions(extractOtherStrongPositions(doc));
//        pesDbInfo.setOtherWeakPositions(extractOtherWeakPositions(doc));
//        pesDbInfo.setLastMeasured(LocalDateTime.now());
//    }
//
//    private void extractCoreDataFreePlayer(Document doc){
//        String pesDbName = ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(1) table tr:nth-of-type(1) td", false);
//        pesDbInfo.setPesDbName(pesDbName);
//        String teamName = ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(1) table tr:nth-of-type(2) td a", false);
//        pesDbInfo.setTeamName(teamName);
//        String foot = ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(1) table tr:nth-of-type(9) td", false);
//        foot = foot.split(" ")[0];
//        pesDbInfo.setFoot(foot);
//        String weekCondition = ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(1) table tr:nth-of-type(10) td", false);
//        pesDbInfo.setWeekCondition(weekCondition);
//        String primaryPosition = ScrapeHelper.getElementData(doc, "table.player tbody tr:nth-of-type(1) td:nth-of-type(1) table tr:nth-of-type(11) td div", false);
//        for (Positions p : Positions.values()) {
//            if(p.toString().equals(primaryPosition)){
//                pesDbInfo.setPositionNumberValue(p.getNumberValue());
//            }
//        }
//        pesDbInfo.setPrimaryPosition(primaryPosition);
//        pesDbInfo.setOtherStrongPositions(extractOtherStrongPositions(doc));
//        pesDbInfo.setOtherWeakPositions(extractOtherWeakPositions(doc));
//        pesDbInfo.setLastMeasured(LocalDateTime.now());
//    }
//
//    private List<String> extractOtherWeakPositions(Element e){
//        List<String> positions = new ArrayList<>();
//        Elements weakerPositions = ScrapeHelper.getElements(e, "table.player tbody table tr td.positions div span.pos1");
//        String s = null;
//        if(weakerPositions.size() > 0){
//            for(Element el : weakerPositions){
//                s = el.text();
//                positions.add(s);
//            }
//        }
//
//        return positions;
//    }
//    private List<String> extractOtherStrongPositions(Element e){
//        List<String> positions = new ArrayList<>();
//        Elements strongerPositions = ScrapeHelper.getElements(e, "table.player tbody table tr td.positions div span.pos2");
//        String s = null;
//        if(strongerPositions.size() > 0){
//            for(Element el : strongerPositions){
//                s = el.text();
//                if(!s.equals(pesDbInfo.getPrimaryPosition()))
//                    positions.add(s);
//            }
//        }
//
//        return positions;
//    }
//
//    private enum Positions {
//        GK(1), CB(2), LB(3), RB(4), DMF(5), CMF(6), LMF(7), RMF(8), AMF(9), LWF(10), RWF(11), SS(12), CF(13);
//
//        private int numberValue;
//
//        private Positions(int numberValue) {
//            this.numberValue = numberValue;
//        }
//
//        public int getNumberValue(){
//            return this.numberValue;
//        }
//
//    }
    
}
