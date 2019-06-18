package com.riocode.scoutpro.scrape.service;

import com.riocode.scoutpro.scrape.enums.ScrapeField;
import com.riocode.scoutpro.scrape.model.ScrapeRegExpression;

public interface ScrapeRegExpressionService {
    ScrapeRegExpression findByFieldName(ScrapeField scrapeField);
}
