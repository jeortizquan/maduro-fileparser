package io.axual.madurobank.parser;

import io.axual.madurobank.entities.CustomerStatementRecordBuilder;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CustomerStatementReportTest {

    @Test
    void hasSuccess() {
        CustomerStatementReport report = new CustomerStatementReport();

        assertEquals(true,report.hasSuccess());
        assertEquals( "\r\nAll records passed\r\n",report.getStatusReport());
    }

    @Test
    void hasFailed() {
        CustomerStatementReport report = new CustomerStatementReport();
        report.failedRecords.add(CustomerStatementRecordBuilder
                .aCustomerStatementRecord()
                        .withTransactionReference(258258)
                        .withAccounNumber("NL91RABO0315273637")
                        .withDescription("Tickets from ABC")
                        .withStartBalance(new BigDecimal("12.21"))
                        .withMutation(new BigDecimal("+13.31"))
                        .withEndBalance(new BigDecimal("25.53"))
                .build());
        assertEquals(false,report.hasSuccess());
        assertEquals( "\r\nFailed records report [TransactionReference,Description]\r\n" +
                "258258::Tickets from ABC\r\n",report.getStatusReport());
    }
}