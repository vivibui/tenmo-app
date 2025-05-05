package com.techelevator.tenmo.datasource.JdbcDao;

import com.techelevator.tenmo.datasource.dao.TenmoUserDao;
import com.techelevator.tenmo.datasource.model.TenmoAccount;
import com.techelevator.tenmo.datasource.model.TenmoUser;
import com.techelevator.tenmo.exception.DaoException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Service
public class JdbcTenmoUserDao implements TenmoUserDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTenmoUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<TenmoUser> getAllUsernames(){
        List<TenmoUser> users = new ArrayList<>();
        String sql = "SELECT username, user_id from tenmo_user;";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while(results.next()){
                users.add(mapRowToTenmoUser(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return users;
    }

    @Override
    public TenmoUser getUsernameById(int usernameId) {
        TenmoUser user = null;
        String sql = " SELECT username, user_id FROM tenmo_user WHERE user_id = ?";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, usernameId);
            if (results.next()) {
                user = mapRowToTenmoUser(results);

                //userName = results.getString("username");
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        //return userName;
        return user;
    }

    private TenmoUser mapRowToTenmoUser(SqlRowSet results) {
        TenmoUser user = new TenmoUser();
        user.setUser_id(results.getInt("user_id"));
        user.setUsername((results.getString("username")));
        return user;
    }

}
