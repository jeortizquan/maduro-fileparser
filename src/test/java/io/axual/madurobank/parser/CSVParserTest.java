package io.axual.madurobank.parser;

import io.axual.madurobank.entities.CustomerStatementRecord;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CSVParserTest {

    @Test
    public void processCSVCustomerStatementFile() {
        CustomerStatementProcessor csvCustomerStatementProcessor = StatementProcessorFactory.getCustomerStatementProcessor("CSV");
        csvCustomerStatementProcessor.setFileName("./src/test/java/io/axual/madurobank/data/records.csv");

        List<CustomerStatementRecord> recordList = csvCustomerStatementProcessor.readFile();

        assertEquals(5, recordList.size());
    }

    @Test
    public void processCSVCustomerStatementFileWithDefectiveRecord() {
        CustomerStatementProcessor csvCustomerStatementProcessor = StatementProcessorFactory.getCustomerStatementProcessor("CSV");
        csvCustomerStatementProcessor.setFileName("./src/test/java/io/axual/madurobank/data/records-with-err.csv");

        List<CustomerStatementRecord> recordList = csvCustomerStatementProcessor.readFile();

        assertEquals(5, recordList.size());
        assertEquals(2, recordList.stream().filter(element -> element != null).collect(Collectors.toList()).size());
        assertEquals(3, recordList.stream().filter(element -> element == null).collect(Collectors.toList()).size());
    }

    @Test
    public void processCSVCustomerStatementSuccess() {
        CustomerStatementProcessor csvCustomerStatementProcessor = StatementProcessorFactory.getCustomerStatementProcessor("CSV");
        csvCustomerStatementProcessor.setFileName("./src/test/java/io/axual/madurobank/data/recordsSuccess.csv");

        CustomerStatementReport report = csvCustomerStatementProcessor.process();

        assertEquals(true, report.hasSuccess());
        assertEquals("\r\nAll records passed\r\n", report.getStatusReport());
    }

    @Test
    public void processCSVCustomerStatementFail() {
        CustomerStatementProcessor csvCustomerStatementProcessor = StatementProcessorFactory.getCustomerStatementProcessor("CSV");
        csvCustomerStatementProcessor.setFileName("./src/test/java/io/axual/madurobank/data/records.csv");

        CustomerStatementReport report = csvCustomerStatementProcessor.process();

        assertEquals(false, report.hasSuccess());
        assertEquals("\r\nFailed records report [TransactionReference,Description]\r\n" +
                "115217::Tickets from Willem de Vries\r\n", report.getStatusReport());
    }
}
