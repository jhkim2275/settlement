package com.example.settlement.repository;

import com.example.settlement.entity.SaleRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SaleRecordRepository extends JpaRepository<SaleRecord, Long> {

    List<SaleRecord> findByCreatorIdAndCreatedAtBetween(
            Long creatorId,
            LocalDateTime start,
            LocalDateTime end
    );
}