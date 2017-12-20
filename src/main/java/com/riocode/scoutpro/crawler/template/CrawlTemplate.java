package com.riocode.scoutpro.crawler.template;

import com.riocode.scoutpro.model.Player;
import org.jsoup.nodes.Document;

/**
 *
 * @author Nikola Cvetkovic
 */

public interface CrawlTemplate {
    Player crawl(Document document);
}
