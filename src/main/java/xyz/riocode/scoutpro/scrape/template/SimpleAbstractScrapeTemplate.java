package xyz.riocode.scoutpro.scrape.template;

import org.jsoup.nodes.Document;
import xyz.riocode.scoutpro.scrape.helper.ScrapeHelper;

public abstract class SimpleAbstractScrapeTemplate implements ScrapeTemplate {

    protected Document getPage(String url){
        return ScrapeHelper.getPage(url);
    }
}
