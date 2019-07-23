package xyz.riocode.scoutpro.scrape.template.impl;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import xyz.riocode.scoutpro.model.Characteristic;
import xyz.riocode.scoutpro.model.Player;
import xyz.riocode.scoutpro.scrape.helper.ScrapeHelper;
import xyz.riocode.scoutpro.scrape.template.WebDriverAbstractScrapeTemplate;

public class CharacteristicsScrapeTemplateImpl extends WebDriverAbstractScrapeTemplate {

    @Override
    public Player scrape(Player player){
        Document page = getPage(player.getWhoScoredUrl());
        return scrape(player, page);
    }

    @Override
    public Player scrape(Player player, Document page) {
        Characteristic characteristic = player.getCharacteristic();
        if(characteristic == null){
            characteristic = new Characteristic();
            characteristic.setPlayer(player);
            player.setCharacteristic(characteristic);
        }
        scrapeCharacteristic(page, characteristic);

        return player;
    }


    private void scrapeCharacteristic(Document doc, Characteristic characteristic){
        Elements el1 = ScrapeHelper.getElements(doc, "div.character-card div.strengths tr");
        if(el1.size() > 0){
            for(Element e : el1){
                String s1 = ScrapeHelper.getElementDataOwn(e, "td:nth-of-type(1) div");
                String s2 = ScrapeHelper.getElementData(e, "td:nth-of-type(2) span");
                if(s1 != null && s2 != null) characteristic.getStrengths().add(s1 + " - " + s2);
            }
        }

        Elements el2 = ScrapeHelper.getElements(doc, "div.character-card div.weaknesses tr");
        if(el2.size() > 0){
            for (Element e : el2) {
                String s1 = ScrapeHelper.getElementDataOwn(e, "td:nth-of-type(1) div");
                String s2 = ScrapeHelper.getElementData(e, "td:nth-of-type(2) span");
                if(s1 != null && s2 != null) characteristic.getWeaknesses().add(s1 + " - " + s2);
            }
        }

        Elements el3 = ScrapeHelper.getElements(doc, "div.style li");
        if(el3.size() > 0){
            for (Element e : el3) {
                String s = e.ownText();
                if(s != null) characteristic.getStylesOfPlay().add(s);
            }
        }

    }
}
