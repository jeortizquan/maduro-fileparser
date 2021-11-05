package io.axual.madurobank.parser;

import io.axual.madurobank.entities.CustomerStatementRecord;
import io.axual.madurobank.entities.CustomerStatementRecordBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CSVCustomerStatementProcessor extends CustomerStatementProcessor {
    private final static String SPLIT_BY_COMMA = ",";

    @Override
    public List<CustomerStatementRecord> readFile() {
        List<CustomerStatementRecord> customerStatementRecords = new ArrayList<>();

        try
        {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(this.getFileName()));

            customerStatementRecords = bufferedReader.lines()
                    .skip(1)
                    .map(line -> {
                        try {
                            String[] record = line.split(SPLIT_BY_COMMA);
                            CustomerStatementRecord customerStatementRecord = CustomerStatementRecordBuilder
                                    .aCustomerStatementRecord()
                                    .withTransactionReference(Long.parseLong(record[0]))
                                    .withAccounNumber(record[1])
                                    .withDescription(record[2])
                                    .withStartBalance(new BigDecimal(record[3]))
                                    .withMutation(new BigDecimal(record[4]))
                                    .withEndBalance(new BigDecimal(record[5]))
                                    .build();
                            return customerStatementRecord;
                        } catch(Exception e) {
                            logger.error("failed parse csv line {}", line);
                            return null;
                        }
                    })
                    .collect(Collectors.toList());
        }
        catch (IOException e)
        {
            logger.error("failed reading csv {}",e.getMessage());
        }

        return customerStatementRecords;
    }
}
