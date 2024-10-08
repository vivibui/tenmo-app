package com.techelevator.tenmo.datasource.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

public class TenmoTransfer {

    // data base stores numeric value:    0  ,    1,       2,        3
    public static enum TRANSFER_STATUS {NONE, PENDING, APPROVED, REJECTED}
    public static enum TRANSFER_TYPE   {NONE, REQUEST, SEND}

    @Positive(message = "ID cannot be negative.")
    private Long            transferId;
    private TRANSFER_STATUS transferStatus;
    private TRANSFER_TYPE   transferType;
    private TenmoAccount fromTenmoAccount;
    private TenmoAccount toTenmoAccount;
    @DecimalMin(value = "0.0", inclusive = true, message = "Amount cannot be negative.")
    private BigDecimal      amount;
    private Timestamp create;

    public TenmoTransfer(){}  // default constructor in case Java needs it

    public TenmoTransfer(Long transferId, TRANSFER_STATUS transferStatus, TRANSFER_TYPE transferType, TenmoAccount fromTenmoAccount, TenmoAccount toTenmoAccount, BigDecimal amount) {
        this.transferId = transferId;
        this.transferStatus = TRANSFER_STATUS.PENDING;
        this.transferType = TRANSFER_TYPE.NONE;
        this.fromTenmoAccount = fromTenmoAccount;
        this.toTenmoAccount = toTenmoAccount;
        this.amount = amount;
    }


    public TenmoTransfer(TenmoTransfer aTransfer) {
        this.transferId       = aTransfer.getTransferId();
        this.transferStatus   = aTransfer.getTransferStatus();
        this.transferType     = aTransfer.getTransferType();
        this.fromTenmoAccount = aTransfer.getFromTenmoAccount();
        this.toTenmoAccount   = aTransfer.getToTenmoAccount();
        this.amount           = aTransfer.getAmount();
    }

    public Long  getTransferId() {
        return transferId;
    }
    public void  setTransferId(Long transferId) {
        this.transferId = transferId;
    }

    public TRANSFER_STATUS getTransferStatus() {
        return transferStatus;
    }
    public void            setTransferStatus(TRANSFER_STATUS transferStatus) {
        this.transferStatus = transferStatus;
    }

    public TRANSFER_TYPE getTransferType() {
        return transferType;
    }
    public void          setTransferType(TRANSFER_TYPE transferType) {
        this.transferType = transferType;
    }

    public TenmoAccount getFromTenmoAccount() {
        return fromTenmoAccount;
    }
    public void         setFromTenmoAccount(TenmoAccount fromTenmoAccount) {
        this.fromTenmoAccount = fromTenmoAccount;
    }

    public TenmoAccount getToTenmoAccount() {
        return toTenmoAccount;
    }
    public void         setToTenmoAccount(TenmoAccount toTenmoAccount) {
        this.toTenmoAccount = toTenmoAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }
    public void       setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Timestamp getCreate() {
        return create;
    }

    public void setCreate(Timestamp create) {
        this.create = create;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "transferId="       + transferId +
                ", transferStatus=" + transferStatus +
                ", transferType="   + transferType +
                ", fromAccount="    + fromTenmoAccount +
                ", toAccount="      + toTenmoAccount +
                ", amount="         + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TenmoTransfer)) return false;
        TenmoTransfer tenmoTransfer = (TenmoTransfer) o;
        return getTransferId() == tenmoTransfer.getTransferId() && getTransferStatus() == tenmoTransfer.getTransferStatus() && getTransferType() == tenmoTransfer.getTransferType() && Objects.equals(getFromTenmoAccount(), tenmoTransfer.getFromTenmoAccount()) && Objects.equals(getToTenmoAccount(), tenmoTransfer.getToTenmoAccount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTransferId(), getTransferStatus(), getTransferType(), getFromTenmoAccount(), getToTenmoAccount());
    }
}
