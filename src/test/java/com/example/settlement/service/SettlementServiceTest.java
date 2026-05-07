package com.example.settlement.service;

import com.example.settlement.dto.SettlementResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
public class SettlementServiceTest {

    @Autowired
    private SettlementService service;

    @Test
    void creator1MarchSettlementTest() {

        SettlementResponse result =
                service.getMonthlySettlement("creator-1", 2025, 3);

        assertEquals(260000, result.getTotalSale());
        assertEquals(80000, result.getTotalRefund());
        assertEquals(180000, result.getNet());
        assertEquals(36000, result.getFee());
        assertEquals(144000, result.getSettlement());
    }
}
