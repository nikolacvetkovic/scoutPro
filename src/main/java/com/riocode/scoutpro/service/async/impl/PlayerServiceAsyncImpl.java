package com.riocode.scoutpro.service.async.impl;

import com.riocode.scoutpro.scraper.template.impl.PesDbScrapeTemplateImpl;
import com.riocode.scoutpro.scraper.template.impl.PsmlScrapeTemplateImpl;
import com.riocode.scoutpro.scraper.template.impl.TransfermarktScrapeTemplateImpl;
import com.riocode.scoutpro.scraper.template.impl.WhoScoredScrapeTemplateImpl;
import com.riocode.scoutpro.dao.PlayerDao;
import com.riocode.scoutpro.model.Player;
import com.riocode.scoutpro.service.async.PlayerServiceAsync;

import java.util.concurrent.CompletableFuture;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 *
 * @author Nikola Cvetkovic
 */

@Component
@Transactional
public class PlayerServiceAsyncImpl implements PlayerServiceAsync{

    @Autowired
    private PlayerDao playerDao;

    @Async
    @Override
    public CompletableFuture<Player> createTransfermarktInfo(Player player){
        TransfermarktScrapeTemplateImpl tmCrawlTemplate = new TransfermarktScrapeTemplateImpl(player);
        tmCrawlTemplate.start();
        return CompletableFuture.completedFuture(player);
    }

    @Async
    @Override
    public CompletableFuture<Player> createWhoScoredInfo(Player player) {
        WhoScoredScrapeTemplateImpl wsDbCrawlTemplate = new WhoScoredScrapeTemplateImpl(player);
        wsDbCrawlTemplate.start();
        return CompletableFuture.completedFuture(player);
    }

    @Async
    @Override
    public CompletableFuture<Player> createPesDbInfo(Player player) {
        PesDbScrapeTemplateImpl pesDbCrawlTemplate = new PesDbScrapeTemplateImpl(player);
        pesDbCrawlTemplate.start();
        return CompletableFuture.completedFuture(player);
    }

    @Async
    @Override
    public CompletableFuture<Player> createPsmlInfo(Player player) {
        PsmlScrapeTemplateImpl psmlCrawlTemplate = new PsmlScrapeTemplateImpl(player);
        psmlCrawlTemplate.start();
        return CompletableFuture.completedFuture(player);
    }
    
}
