package xyz.riocode.scoutpro.scrape.template.impl;

/**
 *
 * @author Nikola Cvetkovic
 */

public class WhoScoredScrapeTemplateImpl /*extends WebDriverAbstractScrapeTemplate*/ {
    
//    private final WhoScoredInfo whoScoredInfo;
//
//    public WhoScoredScrapeTemplateImpl(Player player) {
//        super(player);
//        this.url = player.getWhoScoredUrl();
//        this.whoScoredInfo = new WhoScoredInfo();
//        this.whoScoredInfo.setPlayer(this.player);
//        this.player.getWhoscoredInfoList().add(this.whoScoredInfo);
//    }
//
//    public WhoScoredScrapeTemplateImpl(WhoScoredInfo whoScoredInfo){
//        super(whoScoredInfo.getPlayer());
//        this.url = this.player.getWhoScoredUrl();
//        this.whoScoredInfo = whoScoredInfo;
//    }
//
//    @Override
//    public Player scrape(Document document){
//        scrapeCoreData(document);
//        scrapePositionPlayedStats(document);
//        scrapeCharacteristic(document);
//        scrapeGames(document);
//
//        return this.player;
//    }
    
//    private void scrapeCoreData(Document doc){
//        whoScoredInfo.setSeason(defineDate());
//        Elements table = ScrapeHelper.getElements(doc, "div#statistics-table-summary tbody#player-table-statistics-body tr");
//        for (int i = 0; i < table.size(); i++) {
//            CoreStats cs = null;
//            if ((i+1) != table.size()){
//                cs = extractCoreStats(table.get(i));
//            } else {
//                cs = extractCoreStatsAverage(table.get(i));
//            }
//            cs.setWhoScoredInfo(whoScoredInfo);
//            whoScoredInfo.getCoreStatsList().add(cs);
//        }
//        whoScoredInfo.setLastMeasured(LocalDateTime.now());
//    }
    
//    private void scrapePositionPlayedStats(Document doc){
//        Elements table = ScrapeHelper.getElements(doc, "div#player-positional-statistics tbody tr");
//        for (Element row : table) {
//            PositionPlayedStats pps = new PositionPlayedStats();
//            pps.setPosition(ScrapeHelper.getElementData(row, "td.position-name", true));
//            pps.setApps(Integer.parseInt(ScrapeHelper.getElementData(row, "td:nth-of-type(2)", false)));
//            pps.setGoals(Integer.parseInt(ScrapeHelper.getElementData(row, "td:nth-of-type(3)", false)));
//            pps.setAssists(Integer.parseInt(ScrapeHelper.getElementData(row, "td:nth-of-type(4)", false)));
//            pps.setRating(new BigDecimal(ScrapeHelper.getElementData(row, "td.rating", false)));
//            pps.setWhoScoredInfo(whoScoredInfo);
//            whoScoredInfo.getPositionPlayedStatsList().add(pps);
//        }
//    }
//
//    private void scrapeCharacteristic(Document doc){
//        List<String> strengths = new ArrayList<>();
//        Elements el1 = ScrapeHelper.getElements(doc, "div.character-card div.strengths tr");
//        if(el1.size() > 0){
//            for(Element e : el1){
//                String s1 = ScrapeHelper.getElementData(e, "td:nth-of-type(1) div", true);
//                String s2 = ScrapeHelper.getElementData(e, "td:nth-of-type(2) span", false);
//                if(s1 != null && s2 != null) strengths.add(s1 + " - " + s2);
//            }
//        }
//        List<String> weaknesses = new ArrayList<>();
//        Elements el2 = ScrapeHelper.getElements(doc, "div.character-card div.weaknesses tr");
//        if(el2.size() > 0){
//            for (Element e : el2) {
//                String s1 = ScrapeHelper.getElementData(e, "td:nth-of-type(1) div", true);
//                String s2 = ScrapeHelper.getElementData(e, "td:nth-of-type(2) span", false);
//                if(s1 != null && s2 != null) weaknesses.add(s1 + " - " + s2);
//            }
//        }
//
//        List<String> stylesOfPlay = new ArrayList<>();
//        Elements el3 = ScrapeHelper.getElements(doc, "div.style li");
//        if(el3.size() > 0){
//            for (Element e : el3) {
//                String s = e.ownText();
//                if(s != null) stylesOfPlay.add(s);
//            }
//        }
//
//        if(strengths.size() > 0 || weaknesses.size() > 0 || stylesOfPlay.size() > 0){
//            if(whoScoredInfo.getCharacteristic() == null) whoScoredInfo.setCharacteristic(new Characteristic());
//            whoScoredInfo.getCharacteristic().setWhoscoredinfo(whoScoredInfo);
//            whoScoredInfo.getCharacteristic().setStrengths(strengths);
//            whoScoredInfo.getCharacteristic().setWeaknesses(weaknesses);
//            whoScoredInfo.getCharacteristic().setStyleOfPlay(stylesOfPlay);
//        }
//    }
//
//    private void scrapeGames(Document doc){
//        Elements elements = ScrapeHelper.getElements(doc, "table#player-fixture tbody tr");
//        for (Element e : elements) {
//            Game g = new Game();
//            g.setCompetition(ScrapeHelper.getAttributeValue(e, "td.tournament span a", "title"));
//            g.setDateOfGame(LocalDate.parse(ScrapeHelper.getElementData(e, "td.date", false), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
//            g.setTeam1(ScrapeHelper.getElementData(e, "td.home a", false));
//            g.setTeam2(ScrapeHelper.getElementData(e, "td.away a", false));
//            String result = ScrapeHelper.getElementData(e, "td.result a", false);
//            g.setResult(result.replaceAll("[^\\d:]", ""));
//            g.setMinutesPlayed(ScrapeHelper.getElementData(e, "td.info", false));
//            g.setRating(ScrapeHelper.getElementData(e, "td.rating", false));
//            g.setManOfTheMatch(isManOfTheMatch(e));
//
//            Element el = ScrapeHelper.getElement(e, "td:nth-of-type(7)");
//            g.setGoals(extractAchievements(el, "Goal"));
//            g.setAssists(extractAchievements(el, "Assist"));
//            g.setYellowCard(extractYellowCard(el));
//            g.setRedCard(extractRedCard(el));
//            g.setWhoScoredInfo(whoScoredInfo);
//            whoScoredInfo.getGameList().add(g);
//        }
//    }
//
//    private String defineDate(){
//        LocalDate date = LocalDate.now();
//        if(date.getMonthValue() >= 7){
//            return String.valueOf(date.getYear() + "/" + (date.getYear()+1));
//        } else {
//            return String.valueOf((date.getYear()-1) + "/" + date.getYear());
//        }
//    }
    
//    private CoreStats extractCoreStats(Element e){
//        CoreStats cs = new CoreStats();
//        cs.setCompetition(ScrapeHelper.getElementData(e, "td.tournament a.tournament-link", true));
//        String apps = ScrapeHelper.getElementData(e, "td:nth-of-type(2)", false);
//        if(apps.contains("(")){
//            apps = apps.replace(")", "");
//            cs.setStartedApps(apps.split("\\(")[0]);
//            cs.setSubApps(apps.split("\\(")[1]);
//        } else {
//            cs.setStartedApps(apps);
//            cs.setSubApps("0");
//        }
//        cs.setMins(ScrapeHelper.getElementData(e, "td[class*=minsPlayed]", false));
//        cs.setGoals(ScrapeHelper.getElementData(e, "td[class*=goal]", false));
//        cs.setAssists(ScrapeHelper.getElementData(e, "td[class*=assistTotal]", false));
//        cs.setYellowCards(ScrapeHelper.getElementData(e, "td[class*=yellowCard]", false));
//        cs.setRedCards(ScrapeHelper.getElementData(e, "td[class*=redCard]", false));
//        cs.setShotsPerGame(ScrapeHelper.getElementData(e, "td[class*=shotsPerGame]", false));
//        cs.setPassSuccess(ScrapeHelper.getElementData(e, "td[class*=passSuccess]", false));
//        cs.setAerialsWon(ScrapeHelper.getElementData(e, "td[class*=aerialWonPerGame]", false));
//        cs.setManOfTheMatch(ScrapeHelper.getElementData(e, "td[class*=manOfTheMatch]", false));
//        cs.setRating(ScrapeHelper.getElementData(e, "td[class*=rating]", false));
//
//        return cs;
//    }
//
//    private CoreStats extractCoreStatsAverage(Element e){
//        CoreStats cs = new CoreStats();
//        Elements elements = ScrapeHelper.getElements(e, "td strong");
//        cs.setCompetition(elements.get(0).text());
//        cs.setStartedApps(elements.get(1).text());
//        cs.setSubApps("0");
//        cs.setMins(elements.get(2).text());
//        cs.setGoals(elements.get(3).text());
//        cs.setAssists(elements.get(4).text());
//        cs.setYellowCards(elements.get(5).text());
//        cs.setRedCards(elements.get(6).text());
//        cs.setShotsPerGame(elements.get(7).text());
//        cs.setPassSuccess(elements.get(8).text());
//        cs.setAerialsWon(elements.get(9).text());
//        cs.setManOfTheMatch(elements.get(10).text());
//        cs.setRating(elements.get(11).text());
//
//        return cs;
//    }
    
//    private String extractAchievements(Element e, String achievement){
//        Elements achievements = ScrapeHelper.getElements(e, "span span[title="+ achievement +"]");
//
//        return String.valueOf(achievements.size());
//    }
//
//    private boolean isManOfTheMatch(Element e){
//        return ScrapeHelper.getElement(e, "td:nth-of-type(6) span") != null;
//    }
//
//    private boolean extractYellowCard(Element e){
//        return ScrapeHelper.getElement(e, "span span[title=Yellow Card]") != null;
//    }
//
//    private boolean extractRedCard(Element e){
//        return ScrapeHelper.getElement(e, "span span[title=Red Card]") != null;
//    }
    
}
