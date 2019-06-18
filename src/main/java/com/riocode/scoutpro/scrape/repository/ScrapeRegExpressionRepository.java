package com.riocode.scoutpro.scrape.repository;

import com.riocode.scoutpro.scrape.enums.ScrapeField;
import com.riocode.scoutpro.scrape.model.ScrapeRegExpression;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScrapeRegExpressionRepository extends JpaRepository<ScrapeRegExpression, String> {
    ScrapeRegExpression findByFieldName(ScrapeField scrapeField);
}
