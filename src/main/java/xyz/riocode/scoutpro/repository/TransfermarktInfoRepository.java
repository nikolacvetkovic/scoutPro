package xyz.riocode.scoutpro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.riocode.scoutpro.model.TransfermarktInfo;

public interface TransfermarktInfoRepository extends JpaRepository<TransfermarktInfo, Long> {
}
