package com.riocode.scoutpro.crawler.template.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.riocode.scoutpro.crawler.helper.CrawlHelper;
import com.riocode.scoutpro.crawler.template.CoreAbstractCrawlTemplate;
import com.riocode.scoutpro.model.MarketValue;
import com.riocode.scoutpro.model.Player;
import com.riocode.scoutpro.model.Transfer;
import com.riocode.scoutpro.model.TransfermarktInfo;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.text.StringEscapeUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Nikola Cvetkovic
 */

public class TransfermarktCrawlTemplateImpl extends CoreAbstractCrawlTemplate{
    
    private final TransfermarktInfo transfermarktInfo;
        
    public TransfermarktCrawlTemplateImpl(Player player){
        super(player);
        this.url = player.getTransfermarktUrl();
        this.transfermarktInfo = new TransfermarktInfo();
    }
    
    @Override
    public Player crawl(Document document) throws IOException{
        crawlCoreData(document);
        crawlMarketValues(document);
        crawlTransfers(document);
        this.transfermarktInfo.setPlayer(this.player);
        this.player.setTransfermarktInfo(this.transfermarktInfo);
        
        return player;
    }
    
    private void crawlCoreData(Document doc){
        String name = CrawlHelper.getElementData(doc, "h1[itemprop=name]", false);
        transfermarktInfo.setPlayerName(name);
        String clubTeam = CrawlHelper.getElementData(doc, "table.auflistung tr:has(th:contains(Current club)) td a:nth-of-type(2)", false);
        transfermarktInfo.setClubTeam(clubTeam);
        String contractUntil = CrawlHelper.getElementData(doc, "table.auflistung tr:has(th:contains(Contract until)) td", false);
        transfermarktInfo.setContractUntil(contractUntil);
        String nationality = CrawlHelper.getElementData(doc, "table.auflistung tr:has(th:contains(Nationality)) td", false);
        transfermarktInfo.setNationality(nationality);
        String position = CrawlHelper.getElementData(doc, "table.auflistung tr:has(th:contains(Position)) td", false);
        transfermarktInfo.setPosition(position);
        String birthDate = CrawlHelper.getElementData(doc, "span[itemprop=birthDate]", false);
        birthDate = birthDate.substring(0, birthDate.indexOf("("));
        transfermarktInfo.setDateOfBirth(birthDate);
        int age = Integer.parseInt(CrawlHelper.getElementData(doc, "table.auflistung tr:has(th:contains(Age)) td", false));
        transfermarktInfo.setAge(age);
        String nationalTeam = CrawlHelper.getElementData(doc, "div.dataContent div.dataDaten:nth-of-type(3) p:nth-of-type(1) span.dataValue", false);
        transfermarktInfo.setNationalTeam(nationalTeam);
        transfermarktInfo.setLastChange(LocalDateTime.now());
    }
    
    private void crawlMarketValues(Document doc){
        Elements scripts = doc.select("script[type=text/javascript]");
        String script = null;
        for (Element s : scripts) {
            script = s.toString();
            if (script.contains("Highcharts.setOptions([])")) {
                script = script.substring(script.indexOf("new Highcharts.Chart(") + 21);
                script = script.substring(0, script.indexOf(",'tooltip':{"));
                script = script + "}";
                script = StringEscapeUtils.unescapeJava(script.replaceAll("(?i)\\\\x([0-9a-f]{2})", "\\\\u00$1"));
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
                break;
            }
        }
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        JsonObject jsonObject = gson.fromJson(script, JsonObject.class);
        JsonArray jsonArray = jsonObject.getAsJsonArray("series").get(0).getAsJsonObject().getAsJsonArray("data");
        List<MarketValue> marketValues = new ArrayList<>();
        for(JsonElement e : jsonArray){
            MarketValue mv = new MarketValue();
            JsonObject o = e.getAsJsonObject();
            String worth = o.get("y").getAsString().trim();
            mv.setWorth(new BigDecimal(worth));
            String date = o.get("datum_mw").getAsString().trim();
            mv.setDatePoint(extractDate(date));
            String clubTeam = o.get("verein").getAsString().trim();
            mv.setClubTeam(clubTeam);
            mv.setTransfermarktInfo(this.transfermarktInfo);
            marketValues.add(mv);
        }
        transfermarktInfo.setMarketValueList(marketValues);
    }
    
    private void crawlTransfers(Document doc){
        Elements elements = CrawlHelper.getElements(doc, "div.responsive-table tr.zeile-transfer");
        List<Transfer> transfers = new ArrayList<>();
        for(Element e : elements){
            Transfer t = new Transfer();
            String date = CrawlHelper.getElementData(e, "td:nth-of-type(2)", false);
            LocalDate d = extractDate(date);
            t.setDateOfTransfer(d);
            String fromTeam = CrawlHelper.getElementData(e, "td:nth-of-type(6)", false);
            t.setFromTeam(fromTeam);
            String toTeam = CrawlHelper.getElementData(e, "td:nth-of-type(10)", false);
            t.setToTeam(toTeam);
            String marketValue = CrawlHelper.getElementData(e, "td.zelle-mw", false);
            t.setMarketValue(marketValue);
            String transferFee = CrawlHelper.getElementData(e, "td.zelle-abloese", false);
            t.setTransferFee(transferFee);
            t.setTransfermarktInfo(this.transfermarktInfo);
            transfers.add(t);
        }
        transfermarktInfo.setTransferList(transfers);
    }
    
    private LocalDate extractDate(String d){
        String dt = d.replace(",", "");
        String[] dta = dt.split(" ");
        String month = dta[0];
        LocalDate date = null;
        for(Month mc : Month.values()){
            if(mc.toString().toLowerCase().contains(month.toLowerCase())){
                date = LocalDate.of(Integer.parseInt(dta[2]), mc, Integer.parseInt(dta[1]));
                break;
            }
        }
        return date;
    }

}
