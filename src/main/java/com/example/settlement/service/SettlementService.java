package com.example.settlement.service;

import com.example.settlement.entity.Refund;
import com.example.settlement.entity.SaleRecord;
import com.example.settlement.repository.SaleRecordRepository;
import com.example.settlement.dto.SettlementResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SettlementService {

    private final SaleRecordRepository repo;

    @Transactional
    public SettlementResponse getMonthlySettlement(String creatorId, int year, int month) {

        OffsetDateTime start = OffsetDateTime.of(year, month, 1, 0, 0, 0, 0, ZoneOffset.of("+09:00"));
        OffsetDateTime end = start.plusMonths(1).minusSeconds(1);

        List<SaleRecord> records =
                repo.findByPaidAtBetween(start, end);

        int totalSale = 0;
        int totalRefund = 0;

        for (SaleRecord r : records) {
            String creator = r.getCourse().getCreator().getId();
            if (!creatorId.equals(creator)) {continue;}
            totalSale += r.getAmount();
            for (Refund refund : r.getRefunds()) {
                totalRefund += refund.getAmount();
            }
        }

        int net = totalSale - totalRefund;
        int fee = (int) (Math.max(net, 0) * 0.2);
        // fee set at 20% 
        int settlement = net - fee;

        return new SettlementResponse(totalSale, totalRefund, net, fee, settlement);
    }
}
