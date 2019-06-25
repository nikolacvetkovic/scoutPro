package xyz.riocode.scoutpro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.riocode.scoutpro.model.PsmlInfo;

public interface PsmlInfoRepository extends JpaRepository<PsmlInfo, Long> {
}
