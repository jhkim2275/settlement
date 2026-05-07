package com.example.settlement.repository;

import com.example.settlement.entity.Refund;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefundRepository extends JpaRepository<Refund, String> {
}
