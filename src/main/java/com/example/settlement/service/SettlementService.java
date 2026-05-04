package com.example.settlement.service;

import com.example.settlement.entity.SaleRecord;
import com.example.settlement.entity.Type;
import com.example.settlement.repository.SaleRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SettlementService {

    private final SaleRecordRepository repo;

    public Map<String, Object> getMonthlySettlement(Long creatorId, int year, int month) {

        LocalDateTime start = LocalDateTime.of(year, month, 1, 0, 0);
        LocalDateTime end = start.plusMonths(1).minusSeconds(1);

        List<SaleRecord> records =
                repo.findByCreatorIdAndCreatedAtBetween(creatorId, start, end);

        int totalSale = 0;
        int totalRefund = 0;

        for (SaleRecord r : records) {
            if (r.getType() == Type.SALE) {
                totalSale += r.getAmount();
            } else if (r.getType() == Type.REFUND) {
                totalRefund += r.getAmount();
            }
        }

        int net = totalSale - totalRefund;
        int fee = (int) (net * 0.2);
        // fee set at 20% 
        int settlement = net - fee;

        Map<String, Object> result = new HashMap<>();
        result.put("totalSale", totalSale);
        result.put("totalRefund", totalRefund);
        result.put("net", net);
        result.put("fee", fee);
        result.put("settlement", settlement);

        return result;
    }
}
