package xyz.riocode.scoutpro.scrape.template.impl;

/**
 *
 * @author Nikola Cvetkovic
 */

public class PsmlScrapeTemplateImpl /*extends WebDriverAbstractScrapeTemplate*/ {
    
//    private final PsmlInfo psmlInfo;
//
//    public PsmlScrapeTemplateImpl(Player player) {
//        super(player);
//        this.url = player.getPsmlUrl();
//        this.psmlInfo = new PsmlInfo();
//        this.psmlInfo.setPlayer(player);
//        this.player.getPsmlInfoList().add(this.psmlInfo);
//    }
//
//    public PsmlScrapeTemplateImpl(PsmlInfo psmlInfo){
//        super(null);
//        this.url = this.player.getPsmlUrl();
//        this.psmlInfo = psmlInfo;
//    }
//
//    @Override
//    public Player scrape(Document document){
//        scrapeCoreData(document);
//
//        return this.player;
//    }
//
//    private void scrapeCoreData(Document doc){
//        String teamName = ScrapeHelper.getElementData(doc, "table.innerTable tbody tr:nth-of-type(2) td:nth-of-type(2) p:nth-of-type(2) a", false);
//        if(teamName == null){
//            teamName = ScrapeHelper.getElementData(doc, "table.innerTable tbody tr:nth-of-type(2) td:nth-of-type(2) p:nth-of-type(2)", true);
//        }
//        psmlInfo.setTeamName(teamName);
//        String teamValue = ScrapeHelper.getElementData(doc, "table.innerTable tbody tr:nth-of-type(2) td:nth-of-type(3) p:nth-of-type(1)", true);
//        if(!teamValue.contains(",")){
//            teamValue = ScrapeHelper.getElementData(doc, "table.innerTable tbody tr:nth-of-type(2) td:nth-of-type(3) p:nth-of-type(1) span", false);
//        }
//        teamValue = teamValue.replaceAll("[^0-9,]", "").replace(",", "");
//        psmlInfo.setTeamValue(new BigDecimal(teamValue));
//        psmlInfo.setLastMeasured(LocalDateTime.now());
//    }
//
//    @Override
//    protected Document getDocument(String url){
//        WebDriver driver = null;
//        String html = null;
//        try {
//            driver = new ChromeDriver();
//            driver.get(url);
//            Thread.sleep(5000);
//            driver.switchTo().frame("content");
//            html = driver.getPageSource();
//        } catch (InterruptedException e){
//
//        } finally {
//            if(driver != null)
//                driver.quit();
//        }
//        return Jsoup.parse(html);
//    }
    
}
