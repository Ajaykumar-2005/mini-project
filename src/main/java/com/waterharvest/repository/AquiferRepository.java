package com.waterharvest.repository;

import com.waterharvest.entity.AquiferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AquiferRepository extends JpaRepository<AquiferEntity, Long> {

    List<AquiferEntity> findByState(String state);

    AquiferEntity findByStateAndDistrict(String state, String district);

    List<AquiferEntity> findAllByStateOrderByDistrict(String state);

    List<String> findDistinctStateBy();

    boolean existsByStateAndDistrict(String state, String district);
}
