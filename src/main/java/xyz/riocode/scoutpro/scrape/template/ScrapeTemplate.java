package xyz.riocode.scoutpro.scrape.template;

import org.jsoup.nodes.Document;
import xyz.riocode.scoutpro.model.Player;

/**
 *
 * @author Nikola Cvetkovic
 */

public interface ScrapeTemplate {
    Player scrape(Document document);
}
