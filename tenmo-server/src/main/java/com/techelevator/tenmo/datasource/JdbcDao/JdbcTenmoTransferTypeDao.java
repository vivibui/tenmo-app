package com.techelevator.tenmo.datasource.JdbcDao;

import com.techelevator.tenmo.datasource.dao.TenmoTransferTypeDao;
import com.techelevator.tenmo.datasource.model.TenmoTransfer;
import com.techelevator.tenmo.datasource.model.TenmoTransferType;
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
public class JdbcTenmoTransferTypeDao implements TenmoTransferTypeDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTenmoTransferTypeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String getTransferTypeById(int transferId) {
        String type = "";
        String sql = " SELECT transfer_type_desc FROM transfer_type WHERE transfer_type_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transferId);
            if(results.next()){
                type = mapRowToTenmoTransferType(results).getTransfer_type_desc();
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return type;
    }

    private TenmoTransferType mapRowToTenmoTransferType(SqlRowSet results) {
        TenmoTransferType transferType = new TenmoTransferType();
        transferType.setTransfer_type_desc(results.getString("transfer_type_desc"));
        transferType.setTransfer_type_id((results.getInt("transfer_type_id")));
        return transferType;
    }
}
