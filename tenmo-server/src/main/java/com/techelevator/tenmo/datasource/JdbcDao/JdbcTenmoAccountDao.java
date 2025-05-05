package com.techelevator.tenmo.datasource.JdbcDao;

import com.techelevator.tenmo.datasource.dao.TenmoAccountDao;
import com.techelevator.tenmo.datasource.model.TenmoAccount;
import com.techelevator.tenmo.exception.DaoException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.stereotype.Component;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service
public class JdbcTenmoAccountDao implements TenmoAccountDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTenmoAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public BigDecimal getBalanceByAccountId(int AccountId) {
        //TODOne: User can get balance from an account
        TenmoAccount account = null;
        BigDecimal balance = new BigDecimal("0");
        String sql = "select account_id, user_id, balance from account where account_id = ?;";

        // Insert DAOExceptions
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, AccountId);

            if (results.next()) {
                account = mapRowToTenmoAccount(results);
                balance = account.getBalance();
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return balance;
    }

    @Override
    public List<TenmoAccount> getAllAccounts() {
        // TODOne: Write a method to get all accounts from the data source
        List<TenmoAccount> accounts = new ArrayList<>();

        String sql = "select * from account;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                accounts.add(mapRowToTenmoAccount(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return accounts;
    }


    @Override
    public List<TenmoAccount> getAccountsForAUserId(int theUserId) {

        // TODONE: Write a method to get all accounts for a particular user id from the data source
        List<TenmoAccount> accounts = new ArrayList<>();

        String sql = "select * from account where user_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, theUserId);
            while (results.next()) {
                accounts.add(mapRowToTenmoAccount(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return accounts;

    }

    @Override
    public TenmoAccount getAccountForAccountId(Long theAccountId) {

        // TODONE: Given an account id, write a method to get a specific account from the data source
        TenmoAccount account = null;
        String sql = "SELECT account_id, user_id, balance " +
                "FROM account " +
                "WHERE account_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, theAccountId);
            if (results.next()) {
                account = mapRowToTenmoAccount(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return account;
    }


    @Override
    public TenmoAccount saveAccount(TenmoAccount tenmoAccount2Save) {

        // TODONE: Given an Account object write a method to get add an account to the data source
        TenmoAccount newAccount = null;
        String sql = "INSERT INTO account (account_id, user_id, balance) " +
                "VALUES (?, ?, ?) RETURNING account_id;";
        try {
            int newAccountId = jdbcTemplate.queryForObject(sql, int.class,
                    tenmoAccount2Save.getAccount_id(), tenmoAccount2Save.getUser_id(), tenmoAccount2Save.getBalance());
            newAccount = getAccountForAccountId((long) newAccountId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newAccount;
    }

    @Override
    public TenmoAccount updateAccount(TenmoAccount tenmoAccount2Update) {

        // TODONE: Given an Account object write a method to get update the account in the data source
        TenmoAccount updatedAccount = null;
        String sql = "UPDATE account SET user_id = ?, balance = ? " +
                "WHERE account_id = ?";
        try {
            int numberOfRows = jdbcTemplate.update(sql, tenmoAccount2Update.getUser_id(), tenmoAccount2Update.getBalance(), tenmoAccount2Update.getAccount_id());
            if (numberOfRows == 0) {
                throw new DaoException("Zero rows affected, expected at least one");
            } else {
                updatedAccount = getAccountForAccountId(tenmoAccount2Update.getAccount_id());
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return updatedAccount;
    }

    private TenmoAccount mapRowToTenmoAccount(SqlRowSet results) {
        TenmoAccount account = new TenmoAccount();
        account.setAccount_id((long) results.getInt("account_id"));
        account.setUser_id(results.getInt("user_id"));
        account.setBalance(results.getBigDecimal("balance"));
        return account;
    }

}