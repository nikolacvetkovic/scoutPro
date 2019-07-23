package xyz.riocode.scoutpro.scrape.template.impl;

import org.jsoup.nodes.Document;
import xyz.riocode.scoutpro.model.Player;
import xyz.riocode.scoutpro.scrape.template.WebDriverAbstractScrapeTemplate;

public class WSAllScrapeTemplateImpl extends WebDriverAbstractScrapeTemplate {

    @Override
    public Player scrape(Player player) {
        Document page = getPage(player.getWhoScoredUrl());
        StatisticsScrapeTemplateImpl statisticsScrapeTemplate = new StatisticsScrapeTemplateImpl();
        CharacteristicsScrapeTemplateImpl characteristicsScrapeTemplate = new CharacteristicsScrapeTemplateImpl();
        statisticsScrapeTemplate.scrape(player, page);
        characteristicsScrapeTemplate.scrape(player, page);
        return player;
    }

    @Override
    public Player scrape(Player player, Document page) {
        throw new UnsupportedOperationException();
    }
}
