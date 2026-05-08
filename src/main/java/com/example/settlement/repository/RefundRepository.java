package com.example.settlement.repository;

import com.example.settlement.entity.Refund;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.List;

public interface RefundRepository extends JpaRepository<Refund, String> {
    List<Refund> findByRefundedAtBetween(
            OffsetDateTime start,
            OffsetDateTime end
    );
}
