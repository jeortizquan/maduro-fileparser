package io.axual.madurobank.entities;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CustomerStatementRecordTest {
    @Test
    public void endBalanceWithPositiveMutationValid() {
        CustomerStatementRecord customerStatementRecord = CustomerStatementRecordBuilder
                .aCustomerStatementRecord()
                .withStartBalance(new BigDecimal("20.00"))
                .withMutation(new BigDecimal("+5.58"))
                .withEndBalance(new BigDecimal("25.58"))
                .build();

        Boolean isValid = customerStatementRecord.hasValidEndBalance();

        assertEquals(true, isValid);
    }

    @Test
    public void endBalanceWithNegativeMutationValid() {
        CustomerStatementRecord customerStatementRecord = CustomerStatementRecordBuilder.aCustomerStatementRecord()
                .withStartBalance(new BigDecimal("25.58"))
                .withMutation(new BigDecimal("-5.50"))
                .withEndBalance(new BigDecimal("20.08"))
                .build();

        Boolean isValid = customerStatementRecord.hasValidEndBalance();

        assertEquals(true, isValid);
    }

    @Test
    public void incorrectEndBalanceWithPositiveMutationValid() {
        CustomerStatementRecord customerStatementRecord = CustomerStatementRecordBuilder.aCustomerStatementRecord()
                .withStartBalance(new BigDecimal("10.00"))
                .withMutation(new BigDecimal("+5.58"))
                .withEndBalance(new BigDecimal("25.58"))
                .build();

        Boolean isValid = customerStatementRecord.hasValidEndBalance();

        assertEquals(false, isValid);
    }

    @Test
    public void incorrectEndBalanceWithNegativeMutationValid() {
        CustomerStatementRecord customerStatementRecord = CustomerStatementRecordBuilder.aCustomerStatementRecord()
                .withStartBalance(new BigDecimal("15.58"))
                .withMutation(new BigDecimal("-5.50"))
                .withEndBalance(new BigDecimal("20.08"))
                .build();

        Boolean isValid = customerStatementRecord.hasValidEndBalance();

        assertEquals(false, isValid);
    }
}