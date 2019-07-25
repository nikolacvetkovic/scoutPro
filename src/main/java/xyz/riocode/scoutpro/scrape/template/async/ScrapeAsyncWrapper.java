package xyz.riocode.scoutpro.scrape.template.async;

import org.jsoup.nodes.Document;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import xyz.riocode.scoutpro.model.Player;
import xyz.riocode.scoutpro.scrape.helper.ScrapeHelper;
import xyz.riocode.scoutpro.scrape.template.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Async
@Component
public class ScrapeAsyncWrapper {

    public CompletableFuture<Player> tmAllScrape(Player player){
        Document page = ScrapeHelper.getPage(player.getTransfermarktUrl());

        CompletableFuture<Player> tmCore = tmCoreScrape(player);
        CompletableFuture<Player> tmMarketValues = tmMarketValueScrape(player);
        CompletableFuture<Player> tmTransfers = tmTransferScrape(player);

        CompletableFuture.allOf(tmCore, tmMarketValues, tmTransfers).join();
        Player p = null;

        try {
            p = tmCore.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(p);
    }

    public CompletableFuture<Player> tmCoreScrape(Player player){
        TMCoreScrapeTemplateImpl tmCoreScrapeTemplate = new TMCoreScrapeTemplateImpl();
        tmCoreScrapeTemplate.scrape(player);
        return CompletableFuture.completedFuture(player);
    }

    public CompletableFuture<Player> tmMarketValueScrape(Player player){
        TMMarketValueScrapeTemplateImpl tmMarketValueScrapeTemplate = new TMMarketValueScrapeTemplateImpl();
        tmMarketValueScrapeTemplate.scrape(player);
        return CompletableFuture.completedFuture(player);
    }

    public CompletableFuture<Player> tmTransferScrape(Player player){
        TMTransferScrapeTemplateImpl tmTransferScrapeTemplate = new TMTransferScrapeTemplateImpl();
        tmTransferScrapeTemplate.scrape(player);
        return CompletableFuture.completedFuture(player);
    }

    public CompletableFuture<Player> wsAllScrape(Player player){

        CompletableFuture<Player> statistics = statisticsScrape(player);
        CompletableFuture<Player> characteristics = characteristicsScrape(player);

        CompletableFuture.allOf(statistics, characteristics).join();
        Player p = null;

        try {
            p = statistics.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(p);
    }

    public CompletableFuture<Player> statisticsScrape(Player player){
        StatisticsScrapeTemplateImpl statisticsScrapeTemplate = new StatisticsScrapeTemplateImpl();
        statisticsScrapeTemplate.scrape(player);
        return CompletableFuture.completedFuture(player);
    }

    public CompletableFuture<Player> characteristicsScrape(Player player){
        CharacteristicsScrapeTemplateImpl characteristicsScrapeTemplate = new CharacteristicsScrapeTemplateImpl();
        characteristicsScrapeTemplate.scrape(player);
        return CompletableFuture.completedFuture(player);
    }

    public CompletableFuture<Player> pesDbScrape(Player player){
        PesDbScrapeTemplateImpl pesDbScrapeTemplate = new PesDbScrapeTemplateImpl();
        pesDbScrapeTemplate.scrape(player);
        return CompletableFuture.completedFuture(player);
    }

    public CompletableFuture<Player> psmlScrape(Player player){
        PsmlScrapeTemplateImpl psmlScrapeTemplate = new PsmlScrapeTemplateImpl();
        psmlScrapeTemplate.scrape(player);
        return CompletableFuture.completedFuture(player);
    }
}
