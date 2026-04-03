package com.waterharvest.repository;

import com.waterharvest.entity.AssessmentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssessmentHistoryRepository extends JpaRepository<AssessmentHistory, Long> {

    List<AssessmentHistory> findByUserName(String userName);

    List<AssessmentHistory> findByState(String state);

    List<AssessmentHistory> findAllByOrderByCreatedAtDesc();

    long countByIsFeasibleTrue();
}
