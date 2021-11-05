package io.axual.madurobank.entities;

import java.math.BigDecimal;

public class CustomerStatementRecord {
    private long transactionReference;
    private String accounNumber;
    private BigDecimal startBalance;
    private BigDecimal mutation;
    private String description;
    private BigDecimal endBalance;

    public long getTransactionReference() {
        return transactionReference;
    }

    public void setTransactionReference(long transactionReference) {
        this.transactionReference = transactionReference;
    }

    public String getAccounNumber() {
        return accounNumber;
    }

    public void setAccounNumber(String accounNumber) {
        this.accounNumber = accounNumber;
    }

    public BigDecimal getStartBalance() {
        return startBalance;
    }

    public void setStartBalance(BigDecimal startBalance) {
        this.startBalance = startBalance;
    }

    public BigDecimal getMutation() {
        return mutation;
    }

    public void setMutation(BigDecimal mutation) {
        this.mutation = mutation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getEndBalance() {
        return endBalance;
    }

    public void setEndBalance(BigDecimal endBalance) {
        this.endBalance = endBalance;
    }

    public Boolean hasValidEndBalance() {
        return this.startBalance.add(this.mutation).compareTo(this.endBalance) == 0;
    }
}
