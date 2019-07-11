package xyz.riocode.scoutpro.scrape.template.impl;

/**
 *
 * @author Nikola Cvetkovic
 */

public class TransfermarktScrapeTemplateImpl /*extends CoreAbstractScrapeTemplate*/ {
    
//    private final TransfermarktInfo transfermarktInfo;
//
//    public TransfermarktScrapeTemplateImpl(Player player){
//        super(player);
//        this.url = player.getTransfermarktUrl();
//        this.transfermarktInfo = new TransfermarktInfo();
//        this.transfermarktInfo.setPlayer(this.player);
//        this.player.setTransfermarktInfo(this.transfermarktInfo);
//    }
//
//    public TransfermarktScrapeTemplateImpl(TransfermarktInfo transfermarktInfo){
//        super(transfermarktInfo.getPlayer());
//        this.url = this.player.getTransfermarktUrl();
//        this.transfermarktInfo = transfermarktInfo;
//    }
//
//    @Override
//    public Player scrape(Document document){
//        scrapeCoreData(document);
//        scrapeCurrentValue(document);
//        scrapeMarketValues(document);
//        scrapeTransfers(document);
//
//        return player;
//    }
//
//    private void scrapeCoreData(Document doc){
//        String name = ScrapeHelper.getElementData(doc, "h1[itemprop=name]", false);
//        transfermarktInfo.setPlayerName(name);
//        String clubTeam = ScrapeHelper.getElementData(doc, "table.auflistung tr:has(th:contains(Current club)) td a:nth-of-type(2)", false);
//        transfermarktInfo.setClubTeam(clubTeam);
//        String contractUntil = ScrapeHelper.getElementData(doc, "table.auflistung tr:has(th:contains(Contract until)) td", false);
//        transfermarktInfo.setContractUntil(contractUntil);
//        String nationality = ScrapeHelper.getElementData(doc, "table.auflistung tr:has(th:contains(Nationality)) td", false);
//        transfermarktInfo.setNationality(nationality);
//        String position = ScrapeHelper.getElementData(doc, "table.auflistung tr:has(th:contains(Position)) td", false);
//        transfermarktInfo.setPosition(position);
//        String birthDate = ScrapeHelper.getElementData(doc, "span[itemprop=birthDate]", false);
//        birthDate = birthDate.substring(0, birthDate.indexOf("("));
//        transfermarktInfo.setDateOfBirth(birthDate);
//        int age = Integer.parseInt(ScrapeHelper.getElementData(doc, "table.auflistung tr:has(th:contains(Age)) td", false));
//        transfermarktInfo.setAge(age);
//        String nationalTeam = ScrapeHelper.getElementData(doc, "div.dataContent div.dataDaten:nth-of-type(3) p:nth-of-type(1) span.dataValue", false);
//        transfermarktInfo.setNationalTeam(nationalTeam);
//        transfermarktInfo.setLastMeasured(LocalDateTime.now());
//    }
//
//    private void scrapeCurrentValue(Document doc){
//        Elements scripts = ScrapeHelper.getElements(doc, "script");
//        String script = null;
//        for (Element s : scripts) {
//            script = s.toString();
//            if (script.contains("gaOptout")) {
//                script = script.substring(script.indexOf("ga('require', 'ec')"));
//                script = script.substring(script.indexOf("price\":\"") + 8, script.indexOf("\"});")).trim();
//                break;
//            }
//        }
//        transfermarktInfo.setCurrentValue(new BigDecimal(script));
//        String lastChangeCurrentValue = ScrapeHelper.getElementData(doc, "div.dataMarktwert a p", false);
//        lastChangeCurrentValue = lastChangeCurrentValue.split(":")[1].trim();
//        transfermarktInfo.setLastChangedCurrentValue(extractDate(lastChangeCurrentValue));
//    }
    
//    private void scrapeMarketValues(Document doc){
//        Elements scripts = doc.select("script[type=text/javascript]");
//        String script = null;
//        for (Element s : scripts) {
//            script = s.toString();
//            if (script.contains("Highcharts.setOptions([])")) {
//                script = script.substring(script.indexOf("new Highcharts.Chart(") + 21);
//                script = script.substring(0, script.indexOf(",'tooltip':{"));
//                script = script + "}";
//                script = StringEscapeUtils.unescapeJava(script.replaceAll("(?i)\\\\x([0-9a-f]{2})", "\\\\u00$1"));
//                script = script.replaceAll("\\\\x", "");
//                script = script.replaceAll("\\\\x20", " ");
//                script = script.replaceAll("\\\\x23", "#");
//                script = script.replaceAll("\\\\x28", "(");
//                script = script.replaceAll("\\\\x3A", ":");
//                script = script.replaceAll("\\\\x2F", "/");
//                script = script.replaceAll("\\\\x3F", "?");
//                script = script.replaceAll("\\\\x3D", "=");
//                script = script.replaceAll("\\\\x29", ")");
//                script = script.replaceAll("\\\\x2D", "-");
//                break;
//            }
//        }
//        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
//        JsonObject jsonObject = gson.fromJson(script, JsonObject.class);
//        JsonArray jsonArray = jsonObject.getAsJsonArray("series").get(0).getAsJsonObject().getAsJsonArray("data");
//
//        for(JsonElement e : jsonArray){
//            MarketValue mv = new MarketValue();
//            JsonObject o = e.getAsJsonObject();
//            String worth = o.get("y").getAsString().trim();
//            mv.setWorth(new BigDecimal(worth));
//            String date = o.get("datum_mw").getAsString().trim();
//            mv.setDatePoint(extractDate(date));
//            String clubTeam = o.get("verein").getAsString().trim();
//            mv.setClubTeam(clubTeam);
//            mv.setTransfermarktInfo(this.transfermarktInfo);
//            transfermarktInfo.getMarketValueList().add(mv);
//        }
//    }
    
//    private void scrapeTransfers(Document doc){
//        Elements elements = ScrapeHelper.getElements(doc, "div.responsive-table tr.zeile-transfer");
//
//        for(Element e : elements){
//            Transfer t = new Transfer();
//            String date = ScrapeHelper.getElementData(e, "td:nth-of-type(2)", false);
//            LocalDate d = extractDate(date);
//            t.setDateOfTransfer(d);
//            String fromTeam = ScrapeHelper.getElementData(e, "td:nth-of-type(6)", false);
//            t.setFromTeam(fromTeam);
//            String toTeam = ScrapeHelper.getElementData(e, "td:nth-of-type(10)", false);
//            t.setToTeam(toTeam);
//            String marketValue = ScrapeHelper.getElementData(e, "td.zelle-mw", false);
//            t.setMarketValue(marketValue);
//            String transferFee = ScrapeHelper.getElementData(e, "td.zelle-abloese", false);
//            t.setTransferFee(transferFee);
////            t.setTransfermarktInfo(this.transfermarktInfo);
////            transfermarktInfo.getTransferList().add(t);
//        }
//    }
//
//    private LocalDate extractDate(String d){
//        String dt = d.replace(",", "");
//        String[] dta = dt.split(" ");
//        String month = dta[0];
//        LocalDate date = null;
//        for(Month mc : Month.values()){
//            if(mc.toString().toLowerCase().contains(month.toLowerCase())){
//                date = LocalDate.of(Integer.parseInt(dta[2]), mc, Integer.parseInt(dta[1]));
//                break;
//            }
//        }
//        return date;
//    }

}
