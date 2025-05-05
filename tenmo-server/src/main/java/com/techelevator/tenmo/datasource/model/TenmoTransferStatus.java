package com.techelevator.tenmo.datasource.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
public class TenmoTransferStatus {
    @Positive(message = "Transfer Status ID cannot be negative.")
    private int transfer_status_id;
    @NotBlank(message = "Status cannot be blank.")
    private String transfer_status_desc;

    public TenmoTransferStatus() {
    }

    public TenmoTransferStatus(int transfer_status_id, String transfer_status_desc) {
        this.transfer_status_id = transfer_status_id;
        this.transfer_status_desc = transfer_status_desc;
    }

    public int getTransfer_status_id() {
        return transfer_status_id;
    }

    public void setTransfer_status_id(int transfer_status_id) {
        this.transfer_status_id = transfer_status_id;
    }

    public String getTransfer_status_desc() {
        return transfer_status_desc;
    }

    public void setTransfer_status_desc(String transfer_status_desc) {
        this.transfer_status_desc = transfer_status_desc;
    }

    @Override
    public String toString() {
        return "TenmoTransferStatus{" +
                "transfer_status_id=" + transfer_status_id +
                ", transfer_status_desc='" + transfer_status_desc + '\'' +
                '}';
    }
}
