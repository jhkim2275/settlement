package com.example.settlement.service;

import com.example.settlement.entity.SaleRecord;
import com.example.settlement.repository.SaleRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.example.settlement.DataLoader.courseToCreator;

@Service
@RequiredArgsConstructor
public class SettlementService {

    private final SaleRecordRepository repo;

    public Map<String, Object> getMonthlySettlement(String creatorId, int year, int month) {

        OffsetDateTime start = OffsetDateTime.of(year, month, 1, 0, 0, 0, 0, ZoneOffset.of("+09:00"));
        OffsetDateTime end = start.plusMonths(1).minusSeconds(1);

        List<SaleRecord> records =
                repo.findByPaidAtBetween(start, end);

        int totalSale = 0;
        int totalRefund = 0;

        for (SaleRecord r : records) {
            String creator = courseToCreator.get(r.getCourseId());
            if (!creatorId.equals(creator)) {continue;}
            if (r.getAmount() > 0) {
                totalSale += r.getAmount();
            } else {
                totalRefund += Math.abs(r.getAmount());
            }
        }

        int net = totalSale - totalRefund;
        int fee = (int) (Math.max(net, 0) * 0.2);
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
