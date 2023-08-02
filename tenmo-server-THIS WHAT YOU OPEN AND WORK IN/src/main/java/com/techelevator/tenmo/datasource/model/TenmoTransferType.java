package com.techelevator.tenmo.datasource.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class TenmoTransferType {
    @Positive(message = "Transfer Status ID cannot be negative.")

    private int transfer_type_id;
    @NotBlank(message = "Status cannot be blank.")

    private String transfer_type_desc;

    public TenmoTransferType(int transfer_type_id, String transfer_type_desc) {
        this.transfer_type_id = transfer_type_id;
        this.transfer_type_desc = transfer_type_desc;
    }

    public TenmoTransferType() {
    }

    public int getTransfer_type_id() {
        return transfer_type_id;
    }

    public void setTransfer_type_id(int transfer_type_id) {
        this.transfer_type_id = transfer_type_id;
    }

    public String getTransfer_type_desc() {
        return transfer_type_desc;
    }

    public void setTransfer_type_desc(String transfer_type_desc) {
        this.transfer_type_desc = transfer_type_desc;
    }

    @Override
    public String toString() {
        return "TenmoTransferType{" +
                "transfer_type_id=" + transfer_type_id +
                ", transfer_type_desc='" + transfer_type_desc + '\'' +
                '}';
    }
}
