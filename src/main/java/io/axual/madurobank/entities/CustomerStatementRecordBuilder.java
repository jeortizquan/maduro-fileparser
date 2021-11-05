package io.axual.madurobank.entities;

import java.math.BigDecimal;

public final class CustomerStatementRecordBuilder {
    private long transactionReference;
    private String accounNumber;
    private BigDecimal startBalance;
    private BigDecimal mutation;
    private String description;
    private BigDecimal endBalance;

    private CustomerStatementRecordBuilder() {
    }

    public static CustomerStatementRecordBuilder aCustomerStatementRecord() {
        return new CustomerStatementRecordBuilder();
    }

    public CustomerStatementRecordBuilder withTransactionReference(long transactionReference) {
        this.transactionReference = transactionReference;
        return this;
    }

    public CustomerStatementRecordBuilder withAccounNumber(String accounNumber) {
        this.accounNumber = accounNumber;
        return this;
    }

    public CustomerStatementRecordBuilder withStartBalance(BigDecimal startBalance) {
        this.startBalance = startBalance;
        return this;
    }

    public CustomerStatementRecordBuilder withMutation(BigDecimal mutation) {
        this.mutation = mutation;
        return this;
    }

    public CustomerStatementRecordBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public CustomerStatementRecordBuilder withEndBalance(BigDecimal endBalance) {
        this.endBalance = endBalance;
        return this;
    }

    public CustomerStatementRecord build() {
        CustomerStatementRecord customerStatementRecord = new CustomerStatementRecord();
        customerStatementRecord.setTransactionReference(transactionReference);
        customerStatementRecord.setAccounNumber(accounNumber);
        customerStatementRecord.setStartBalance(startBalance);
        customerStatementRecord.setMutation(mutation);
        customerStatementRecord.setDescription(description);
        customerStatementRecord.setEndBalance(endBalance);
        return customerStatementRecord;
    }
}
