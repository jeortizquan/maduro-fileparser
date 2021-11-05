package io.axual.madurobank.parser;

import io.axual.madurobank.entities.CustomerStatementRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

public abstract class CustomerStatementProcessor {
    public static final Logger logger = LoggerFactory.getLogger(CustomerStatementProcessor.class);

    private List<CustomerStatementRecord> customerStatementRecords;
    private String fileName;

    public abstract List<CustomerStatementRecord> readFile();

    public final CustomerStatementReport process() {
        CustomerStatementReport report = new CustomerStatementReport();

        try {
            customerStatementRecords = readFile();

            List<CustomerStatementRecord> failedBalanceRecords = customerStatementRecords.stream()
                    .filter(record -> !record.hasValidEndBalance())
                    .collect(Collectors.toList());

            Set<CustomerStatementRecord> failedDuplicates = new HashSet<CustomerStatementRecord>();
            Set<CustomerStatementRecord> finalFailedDuplicates = failedDuplicates;
            failedDuplicates = customerStatementRecords
                    .stream()
                    .filter(n -> !finalFailedDuplicates.add(n))
                    .collect(Collectors.toSet());

            failedBalanceRecords.addAll(failedDuplicates);

            report.failedRecords.addAll(failedBalanceRecords
                    .stream()
                    .collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparingLong(CustomerStatementRecord::getTransactionReference))),
                            ArrayList::new)));

        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return report;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}