package io.axual.madurobank.parser;

import io.axual.madurobank.entities.CustomerStatementRecord;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
class XMLParserTest {

    @Test
    public void readXMLCustomerStatementWithGoodRecords() {
        CustomerStatementProcessor xmlCustomerStatementProcessor = StatementProcessorFactory.getCustomerStatementProcessor("XML");
        xmlCustomerStatementProcessor.setFileName("./src/test/java/io/axual/madurobank/data/records.xml");

        List<CustomerStatementRecord> recordList = xmlCustomerStatementProcessor.readFile();

        assertEquals(5, recordList.size());
    }

    @Test
    public void readXMLCustomerStatementFileWithDefectiveRecords() {
        CustomerStatementProcessor xmlCustomerStatementProcessor = StatementProcessorFactory.getCustomerStatementProcessor("XML");
        xmlCustomerStatementProcessor.setFileName("./src/test/java/io/axual/madurobank/data/records-with-err.xml");

        List<CustomerStatementRecord> recordList = xmlCustomerStatementProcessor.readFile();

        assertEquals(5, recordList.size());
        assertEquals( 2, recordList.stream().filter( element -> element !=null).collect(Collectors.toList()).size());
        assertEquals( 3, recordList.stream().filter( element -> element ==null).collect(Collectors.toList()).size());

    }

    @Test
    public void processXMLCustomerStatementSuccess() {
        CustomerStatementProcessor xmlCustomerStatementProcessor = StatementProcessorFactory.getCustomerStatementProcessor("XML");
        xmlCustomerStatementProcessor.setFileName("./src/test/java/io/axual/madurobank/data/recordsSuccess.xml");

        CustomerStatementReport report = xmlCustomerStatementProcessor.process();

        assertEquals(true, report.hasSuccess());
        assertEquals("\r\nAll records passed\r\n", report.getStatusReport());
    }

    @Test
    public void processXMLCustomerStatementFail() {
        CustomerStatementProcessor xmlCustomerStatementProcessor = StatementProcessorFactory.getCustomerStatementProcessor("XML");
        xmlCustomerStatementProcessor.setFileName("./src/test/java/io/axual/madurobank/data/records.xml");

        CustomerStatementReport report = xmlCustomerStatementProcessor.process();

        assertEquals(false, report.hasSuccess());
        assertEquals("\r\nFailed records report [TransactionReference,Description]\r\n" +
                "166703::Flowers from Richard de Vries\r\n", report.getStatusReport());
    }
}
