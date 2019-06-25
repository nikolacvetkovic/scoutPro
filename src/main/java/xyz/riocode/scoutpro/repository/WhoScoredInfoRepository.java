package xyz.riocode.scoutpro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.riocode.scoutpro.model.WhoScoredInfo;

public interface WhoScoredInfoRepository extends JpaRepository<WhoScoredInfo, Long> {
}
