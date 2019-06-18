package com.riocode.scoutpro.repository;

import com.riocode.scoutpro.model.MarketValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketValueRepository extends JpaRepository<MarketValue, Long> {
}
