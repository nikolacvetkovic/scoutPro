package com.riocode.scoutpro.scrape.service;

import com.riocode.scoutpro.scrape.enums.ScrapeField;
import com.riocode.scoutpro.scrape.model.ScrapeRegExpression;
import org.springframework.stereotype.Component;

@Component
public class ScrapeRegExpressionServiceImpl implements ScrapeRegExpressionService {
    @Override
    public ScrapeRegExpression findByFieldName(ScrapeField scrapeField) {
        return null;
    }
}
