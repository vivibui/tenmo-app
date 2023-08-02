package com.techelevator.tenmo.datasource.dao;

import com.techelevator.tenmo.datasource.model.TenmoAccount;

import java.math.BigDecimal;
import java.util.List;

public interface TenmoAccountDao {

    public BigDecimal getBalanceByAccountId(int AccountId);

    public List<TenmoAccount> getAllAccounts();

    public TenmoAccount getAccountForAccountId(Long theAccountId);

    public List<TenmoAccount> getAccountsForAUserId(int anUserId);

    public TenmoAccount saveAccount(TenmoAccount tenmoAccount2Save);

    public TenmoAccount updateAccount(TenmoAccount tenmoAccount2Update);


}
