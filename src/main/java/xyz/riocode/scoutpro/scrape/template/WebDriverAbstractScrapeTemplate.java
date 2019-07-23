package xyz.riocode.scoutpro.scrape.template;

import org.jsoup.nodes.Document;
import xyz.riocode.scoutpro.scrape.helper.ScrapeHelper;

public abstract class WebDriverAbstractScrapeTemplate implements ScrapeTemplate {

    protected Document getPage(String url){
        return ScrapeHelper.getPageWithWebDriver(url);
    }
}
