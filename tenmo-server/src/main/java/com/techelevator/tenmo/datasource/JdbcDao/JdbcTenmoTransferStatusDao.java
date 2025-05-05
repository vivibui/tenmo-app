package com.techelevator.tenmo.datasource.JdbcDao;

import com.techelevator.tenmo.datasource.dao.TenmoTransferStatusDao;
import com.techelevator.tenmo.datasource.model.TenmoTransferStatus;
import com.techelevator.tenmo.datasource.model.TenmoUser;
import com.techelevator.tenmo.exception.DaoException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class JdbcTenmoTransferStatusDao implements TenmoTransferStatusDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTenmoTransferStatusDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String getTransferStatusById(int id) {
        String status = "";
        String sql = " SELECT transfer_status_desc FROM transfer_status WHERE transfer_status_id = ?";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if(results.next()){
                status = mapRowToTenmoTransferStatus(results).getTransfer_status_desc();
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return status;
    }

    private TenmoTransferStatus mapRowToTenmoTransferStatus(SqlRowSet results) {
        TenmoTransferStatus transferStatus = new TenmoTransferStatus();
        transferStatus.setTransfer_status_desc(results.getString("transfer_status_desc"));
        transferStatus.setTransfer_status_id(results.getInt("transfer_status_id"));
        return transferStatus;
    }
}
