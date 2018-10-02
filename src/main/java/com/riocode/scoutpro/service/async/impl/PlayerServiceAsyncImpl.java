package com.riocode.scoutpro.service.async.impl;

import com.riocode.scoutpro.crawler.template.impl.PesDbCrawlTemplateImpl;
import com.riocode.scoutpro.crawler.template.impl.PsmlCrawlTemplateImpl;
import com.riocode.scoutpro.crawler.template.impl.TransfermarktCrawlTemplateImpl;
import com.riocode.scoutpro.crawler.template.impl.WhoScoredCrawlTemplateImpl;
import com.riocode.scoutpro.dao.PlayerDao;
import com.riocode.scoutpro.exception.DuplicatePlayerException;
import com.riocode.scoutpro.exception.PlayerNotFoundException;
import com.riocode.scoutpro.model.PesDbInfo;
import com.riocode.scoutpro.model.Player;
import com.riocode.scoutpro.model.PsmlInfo;
import com.riocode.scoutpro.model.WhoScoredInfo;
import com.riocode.scoutpro.service.async.PlayerServiceAsync;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
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
        TransfermarktCrawlTemplateImpl tmCrawlTemplate = new TransfermarktCrawlTemplateImpl(player);
        tmCrawlTemplate.start();
        return CompletableFuture.completedFuture(player);
    }

    @Async
    @Override
    public CompletableFuture<Player> createWhoScoredInfo(Player player) {
        WhoScoredCrawlTemplateImpl wsDbCrawlTemplate = new WhoScoredCrawlTemplateImpl(player);
        wsDbCrawlTemplate.start();
        return CompletableFuture.completedFuture(player);
    }

    @Async
    @Override
    public CompletableFuture<Player> createPesDbInfo(Player player) {
        PesDbCrawlTemplateImpl pesDbCrawlTemplate = new PesDbCrawlTemplateImpl(player);
        pesDbCrawlTemplate.start();
        return CompletableFuture.completedFuture(player);
    }

    @Async
    @Override
    public CompletableFuture<Player> createPsmlInfo(Player player) {
        PsmlCrawlTemplateImpl psmlCrawlTemplate = new PsmlCrawlTemplateImpl(player);
        psmlCrawlTemplate.start();
        return CompletableFuture.completedFuture(player);
    }
    
}
