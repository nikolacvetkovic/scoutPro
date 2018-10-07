package com.riocode.scoutpro.scraper.template;

import com.riocode.scoutpro.model.Player;
import org.jsoup.nodes.Document;

/**
 *
 * @author Nikola Cvetkovic
 */

public interface ScrapeTemplate {
    Player scrape(Document document);
}
