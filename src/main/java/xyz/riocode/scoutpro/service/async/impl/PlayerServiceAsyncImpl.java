package xyz.riocode.scoutpro.service.async.impl;

import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 *
 * @author Nikola Cvetkovic
 * @deprecated
 */

@Component
@Transactional
public class PlayerServiceAsyncImpl /*implements PlayerServiceAsync*/{

//    @Autowired
//    private PlayerDao playerDao;
//
//    @Async
//    @Override
//    public CompletableFuture<Player> createTransfermarktInfo(Player player){
//        TransfermarktScrapeTemplateImpl tmCrawlTemplate = new TransfermarktScrapeTemplateImpl(player);
//        tmCrawlTemplate.start();
//        return CompletableFuture.completedFuture(player);
//    }
//
//    @Async
//    @Override
//    public CompletableFuture<Player> createWhoScoredInfo(Player player) {
//        WhoScoredScrapeTemplateImpl wsDbCrawlTemplate = new WhoScoredScrapeTemplateImpl(player);
//        wsDbCrawlTemplate.start();
//        return CompletableFuture.completedFuture(player);
//    }
//
//    @Async
//    @Override
//    public CompletableFuture<Player> createPesDbInfo(Player player) {
//        PesDbScrapeTemplateImpl pesDbCrawlTemplate = new PesDbScrapeTemplateImpl(player);
//        pesDbCrawlTemplate.start();
//        return CompletableFuture.completedFuture(player);
//    }
//
//    @Async
//    @Override
//    public CompletableFuture<Player> createPsmlInfo(Player player) {
//        PsmlScrapeTemplateImpl psmlCrawlTemplate = new PsmlScrapeTemplateImpl(player);
//        psmlCrawlTemplate.start();
//        return CompletableFuture.completedFuture(player);
//    }
    
}
