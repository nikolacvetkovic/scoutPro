package xyz.riocode.scoutpro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.riocode.scoutpro.model.PesDbInfo;

public interface PesDbInfoRepository extends JpaRepository<PesDbInfo, Long> {
}
