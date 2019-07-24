package xyz.riocode.scoutpro.scrape.template;

import org.jsoup.nodes.Document;
import xyz.riocode.scoutpro.model.Player;

public class TMAllScrapeTemplateImpl extends SimpleAbstractScrapeTemplate {

    @Override
    public Player scrape(Player player){
        Document page = getPage(player.getTransfermarktUrl());
        TMCoreScrapeTemplateImpl tmCoreScrapeTemplate = new TMCoreScrapeTemplateImpl();
        TMMarketValueScrapeTemplateImpl tmMarketValueScrapeTemplate = new TMMarketValueScrapeTemplateImpl();
        TMTransferScrapeTemplateImpl tmTransferScrapeTemplate = new TMTransferScrapeTemplateImpl();
        tmCoreScrapeTemplate.scrape(player, page);
        tmMarketValueScrapeTemplate.scrape(player, page);
        tmTransferScrapeTemplate.scrape(player, page);
        return player;
    }

    @Override
    public Player scrape(Player player, Document page) {
        throw new UnsupportedOperationException();
    }
}
