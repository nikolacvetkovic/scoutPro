package xyz.riocode.scoutpro.scrape.service;

import org.springframework.stereotype.Component;
import xyz.riocode.scoutpro.scrape.enums.ScrapeField;
import xyz.riocode.scoutpro.scrape.model.ScrapeRegExpression;

@Component
public class ScrapeRegExpressionServiceImpl implements ScrapeRegExpressionService {
    @Override
    public ScrapeRegExpression findByFieldName(ScrapeField scrapeField) {
        return null;
    }
}
