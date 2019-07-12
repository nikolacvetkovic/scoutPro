package xyz.riocode.scoutpro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.riocode.scoutpro.model.Characteristic;

public interface CharacteristicRepository extends JpaRepository<Characteristic, Long> {
}
