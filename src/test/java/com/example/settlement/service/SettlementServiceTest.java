package com.example.settlement.service;

import com.example.settlement.dto.SettlementResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
@SpringBootTest
public class SettlementServiceTest {

    @Autowired
    private SettlementService service;

    @Test
    void invalidMonthTest() {

        assertThrows(
                IllegalArgumentException.class,
                () -> service.getMonthlySettlement(
                        "creator-1",
                        2025,
                        13
                )
        );
    }

    @Test
    void creatorNotFoundTest() {

        assertThrows(
                IllegalArgumentException.class,
                () -> service.getMonthlySettlement(
                        "invalid-creator",
                        2025,
                        3
                )
        );
    }

    @Test
    void creator1MarchSettlementWithPartialRefundTest() {

        SettlementResponse result =
                service.getMonthlySettlement(
                        "creator-1",
                        2025,
                        3
                );

        assertEquals(260000, result.getTotalSale());
        assertEquals(110000, result.getTotalRefund());
        assertEquals(150000, result.getNet());
        assertEquals(30000, result.getFee());
        assertEquals(120000, result.getSettlement());
        assertEquals(4, result.getSaleCount());
        assertEquals(2, result.getRefundCount());
    }

    @Test
    void creator2FebruaryRefundTest() {

        SettlementResponse result =
                service.getMonthlySettlement(
                        "creator-2",
                        2025,
                        2
                );

        assertEquals(0, result.getTotalSale());
        assertEquals(60000, result.getTotalRefund());
        assertEquals(-60000, result.getNet());
        assertEquals(0, result.getFee());
        assertEquals(-60000, result.getSettlement());
        assertEquals(0, result.getSaleCount());
        assertEquals(1, result.getRefundCount());
    }

    @Test
    void creator3MarchEmptySettlementTest() {

        SettlementResponse result =
                service.getMonthlySettlement(
                        "creator-3",
                        2025,
                        3
                );

        assertEquals(0, result.getTotalSale());
        assertEquals(0, result.getTotalRefund());
        assertEquals(0, result.getNet());
        assertEquals(0, result.getFee());
        assertEquals(0, result.getSettlement());
        assertEquals(0, result.getSaleCount());
        assertEquals(0, result.getRefundCount());
    }
}
