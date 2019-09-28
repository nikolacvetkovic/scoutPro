package xyz.riocode.scoutpro.scrape.template;

import org.jsoup.nodes.Document;
import xyz.riocode.scoutpro.model.Player;
import xyz.riocode.scoutpro.model.TransfermarktInfo;
import xyz.riocode.scoutpro.scrape.helper.ScrapeHelper;

public class TMCoreScrapeTemplateImpl extends SimpleAbstractScrapeTemplate {

    @Override
    public Player scrape(Player player) {
        Document page = getPage(player.getTransfermarktUrl());
        return scrape(player, page);
    }

    @Override
    public Player scrape(Player player, Document page) {
        TransfermarktInfo transfermarktInfo = player.getTransfermarktInfo();
        if(transfermarktInfo == null){
            transfermarktInfo = new TransfermarktInfo();
            transfermarktInfo.setPlayer(player);
            player.setTransfermarktInfo(transfermarktInfo);
        }
        scrapeCoreData(page, player);
        return player;
    }

    private void scrapeCoreData(Document doc, Player player){
        String playerName = ScrapeHelper.getElementData(doc, "h1[itemprop=name]");
        player.setPlayerName(playerName);
        String clubTeam = ScrapeHelper.getElementData(doc, "table.auflistung tr:has(th:contains(Current club)) td a:nth-of-type(2)");
        player.getTransfermarktInfo().setClubTeam(clubTeam);
        String contractUntil = ScrapeHelper.getElementData(doc, "table.auflistung tr:has(th:contains(Contract expires)) td");
        player.getTransfermarktInfo().setContractUntil(contractUntil);
        String nationality = ScrapeHelper.getElementData(doc, "span[itemprop=nationality]");
        player.getTransfermarktInfo().setNationality(nationality);
        String position = ScrapeHelper.getElementData(doc, "table.auflistung tr:has(th:contains(Position)) td");
        player.getTransfermarktInfo().setPosition(position);
        String birthDate = ScrapeHelper.getElementData(doc, "span[itemprop=birthDate]");
        birthDate = birthDate.substring(0, birthDate.indexOf("("));
        player.getTransfermarktInfo().setDateOfBirth(birthDate);
        int age = Integer.parseInt(ScrapeHelper.getElementData(doc, "table.auflistung tr:has(th:contains(Age)) td"));
        player.getTransfermarktInfo().setAge(age);
        String nationalTeam = ScrapeHelper.getElementData(doc, "div.dataContent div.dataDaten:nth-of-type(3) p:nth-of-type(1) span.dataValue");
        player.getTransfermarktInfo().setNationalTeam(nationalTeam);
    }
}
