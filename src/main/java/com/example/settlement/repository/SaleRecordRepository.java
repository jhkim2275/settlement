package com.example.settlement.repository;

import com.example.settlement.entity.SaleRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.List;

public interface SaleRecordRepository extends JpaRepository<SaleRecord, String> {

    List<SaleRecord> findByPaidAtBetween(
            OffsetDateTime start,
            OffsetDateTime end
    );
}