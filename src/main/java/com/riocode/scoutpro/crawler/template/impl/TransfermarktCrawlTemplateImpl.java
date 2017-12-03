package com.riocode.scoutpro.crawler.template.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.riocode.scoutpro.crawler.helper.CrawlHelper;
import com.riocode.scoutpro.crawler.template.AbstractCrawlTemplate;
import com.riocode.scoutpro.model.MarketValue;
import com.riocode.scoutpro.model.Player;
import com.riocode.scoutpro.model.Transfer;
import com.riocode.scoutpro.model.TransfermarktInfo;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Nikola Cvetkovic
 */

public class TransfermarktCrawlTemplateImpl extends AbstractCrawlTemplate{
    
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
        String clubTeam = CrawlHelper.getElementData(doc, "div.premium-profil-text:contains(Current club) a", false);
        transfermarktInfo.setClubTeam(clubTeam);
        String contractUntil = CrawlHelper.getElementData(doc, "div.premium-profil-text:contains(Contract until) a", false);
        transfermarktInfo.setContractUntil(contractUntil);
        String nationality = CrawlHelper.getElementData(doc, "div.premium-profil-text:contains(Nationality) a", false);
        transfermarktInfo.setNationality(nationality);
        String position = CrawlHelper.getElementData(doc, "div.premium-profil-text:contains(Position) a", false);
        transfermarktInfo.setPosition(position);
        String birthDate = CrawlHelper.getElementData(doc, "span[itemprop=birthDate]", false);
        birthDate = birthDate.substring(0, birthDate.indexOf("("));
        transfermarktInfo.setDateOfBirth(birthDate);
        int age = Integer.parseInt(CrawlHelper.getElementData(doc, "table.auflistung tr:has(th:contains(Age)) td", false));
        transfermarktInfo.setAge(age);
        String nationalTeam = CrawlHelper.getElementData(doc, "div.dataDaten p:has(span:contains(Current international)) span.dataValue a", false);
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
                script = script.replaceAll("\\\\x20", " ");
                script = script.replaceAll("\\\\x23", "#");
                script = script.replaceAll("\\\\x28", "(");
                script = script.replaceAll("\\\\x3A", ":");
                script = script.replaceAll("\\\\x2F", "/");
                script = script.replaceAll("\\\\x3F", "?");
                script = script.replaceAll("\\\\x3D", "=");
                script = script.replaceAll("\\\\x29", ")");
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
