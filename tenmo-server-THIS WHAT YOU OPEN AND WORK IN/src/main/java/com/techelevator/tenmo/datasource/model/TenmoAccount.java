package com.techelevator.tenmo.datasource.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class TenmoAccount {

    @Positive(message = "Account ID cannot be negative.")
    private Long          account_id;
    @Positive(message = "User ID cannot be negative.")

    private int           user_id;
    @DecimalMin(value = "0.0", inclusive = true, message = "Balance cannot be negative.")
    private BigDecimal    balance;

    public TenmoAccount() {
    }

    public TenmoAccount(Long account_id, int user_id, BigDecimal balance) {
        this.account_id = account_id;
        this.user_id = user_id;
        this.balance = balance;
    }

    public Long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Long account_id) {
        this.account_id = account_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public BigDecimal getBalance() {
        return balance;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "account_id="   + account_id +
                ", user_id="    + user_id +
                ", balance="    + balance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TenmoAccount)) return false;
        TenmoAccount that = (TenmoAccount) o;
        return getAccount_id() == that.getAccount_id() && getUser_id() == that.getUser_id();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAccount_id(), getUser_id());
    }

}
