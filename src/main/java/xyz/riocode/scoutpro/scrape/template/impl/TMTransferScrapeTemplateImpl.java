package xyz.riocode.scoutpro.scrape.template.impl;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import xyz.riocode.scoutpro.model.Player;
import xyz.riocode.scoutpro.model.Transfer;
import xyz.riocode.scoutpro.scrape.helper.ScrapeHelper;
import xyz.riocode.scoutpro.scrape.template.SimpleAbstractScrapeTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TMTransferScrapeTemplateImpl extends SimpleAbstractScrapeTemplate {

    @Override
    public Player scrape(Player player) {
        Document page = getPage(player.getTransfermarktUrl());
        return scrape(player, page);
    }

    @Override
    public Player scrape(Player player, Document page) {
        return scrapeTransfers(page, player);
    }


    private Player scrapeTransfers(Document doc, Player player){
        Elements elements = ScrapeHelper.getElements(doc, "div.responsive-table tr.zeile-transfer");
        for(Element e : elements){
            Transfer transfer = new Transfer();
            //todo implement better solution
            String dateString = ScrapeHelper.getElementData(e, "td:nth-of-type(2)");
            LocalDate dateOfTransfer = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("MMM d, yyyy"));
            transfer.setDateOfTransfer(dateOfTransfer);
            String fromTeam = ScrapeHelper.getElementData(e, "td:nth-of-type(6)");
            transfer.setFromTeam(fromTeam);
            String toTeam = ScrapeHelper.getElementData(e, "td:nth-of-type(10)");
            transfer.setToTeam(toTeam);
            String marketValue = ScrapeHelper.getElementData(e, "td.zelle-mw");
            transfer.setMarketValue(marketValue);
            String transferFee = ScrapeHelper.getElementData(e, "td.zelle-abloese");
            transfer.setTransferFee(transferFee);
            transfer.setPlayer(player);
            player.getTransfers().add(transfer);
        }
        return player;
    }

}
