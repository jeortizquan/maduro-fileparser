package io.axual.madurobank.parser;

import io.axual.madurobank.entities.CustomerStatementRecord;

import java.util.ArrayList;
import java.util.List;

public class CustomerStatementReport {

    public List<CustomerStatementRecord> failedRecords = new ArrayList<>();
    public boolean hasSuccess() {
        return failedRecords.size() == 0;
    }

    public String getStatusReport() {
        if (hasSuccess())
            return "\r\nAll records passed\r\n";
        else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("\r\nFailed records report [TransactionReference,Description]\r\n");
            for(CustomerStatementRecord record : failedRecords) {
                stringBuilder.append(record.getTransactionReference()+"::"+ record.getDescription()+"\r\n");
            }
            return stringBuilder.toString();
        }
    }
}
