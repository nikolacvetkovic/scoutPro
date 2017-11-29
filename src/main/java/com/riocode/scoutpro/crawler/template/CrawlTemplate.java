package com.riocode.scoutpro.crawler.template;

import org.jsoup.nodes.Document;

/**
 *
 * @author Nikola Cvetkovic
 */

public interface CrawlTemplate {
    void crawl(Document document);
}
