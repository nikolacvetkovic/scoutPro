package xyz.riocode.scoutpro.scrape.service;

import xyz.riocode.scoutpro.scrape.enums.ScrapeField;
import xyz.riocode.scoutpro.scrape.model.ScrapeRegExpression;

public interface ScrapeRegExpressionService {
    ScrapeRegExpression findByFieldName(ScrapeField scrapeField);
}
