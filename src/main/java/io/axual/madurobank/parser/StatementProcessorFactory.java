package io.axual.madurobank.parser;

public class StatementProcessorFactory {
    public static CustomerStatementProcessor getCustomerStatementProcessor(String processorType) {
        CustomerStatementProcessor processor = null;
        if (processorType.equalsIgnoreCase("xml")) {
            processor = new XMLCustomerStatementProcessor();
        } else if (processorType.equalsIgnoreCase("csv")) {
            processor = new CSVCustomerStatementProcessor();
        }
        return processor;
    }
}
