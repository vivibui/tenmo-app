package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.datasource.JdbcDao.JdbcTenmoAccountDao;
import com.techelevator.tenmo.datasource.JdbcDao.JdbcTenmoTransferDao;
import com.techelevator.tenmo.datasource.JdbcDao.JdbcTenmoUserDao;
import com.techelevator.tenmo.datasource.dao.TenmoTransferDao;
import com.techelevator.tenmo.datasource.dao.TenmoUserDao;
import com.techelevator.tenmo.datasource.dao.TenmoAccountDao;
import com.techelevator.tenmo.datasource.model.TenmoAccount;
import com.techelevator.tenmo.datasource.model.TenmoTransfer;
import com.techelevator.tenmo.datasource.model.TenmoUser;
import com.techelevator.tenmo.exception.DaoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TenmoController {

    private JdbcTenmoAccountDao tenmoAccountDao;
    private JdbcTenmoTransferDao tenmoTransferDao;
    private JdbcTenmoUserDao tenmoUserDao;

    // constructor
    public TenmoController(JdbcTenmoAccountDao accountDao, JdbcTenmoTransferDao transferDao, JdbcTenmoUserDao userDao){
        this.tenmoAccountDao = accountDao;
        this.tenmoTransferDao = transferDao;
        this.tenmoUserDao = userDao;
    }

    /**
     * Handles an HTTP POST request for the path: /account
     * <p>
     * Add a TenmoAccount object to  the datasource
     *
     * @param aNewAccount - must be present in the request body as a valid JSON object for a TenmoAccount object
     *                    Note: If an account is sent in the JSON object it will be ignored as the data source
     *                    manager will assign a unique account id when storing the TenmoObject
     * @return the TenmoAccount object with the data source assigned accountId
     */

    // TODONe: Write a controller to handle an HTTP POST request for the path: /account

    @ResponseStatus (HttpStatus.CREATED)
    //********NUMBER 1****
    @RequestMapping(path = "/account", method = RequestMethod.POST)
    public TenmoAccount createAccount(@Valid @RequestBody TenmoAccount aNewAccount) {
        return tenmoAccountDao.saveAccount(aNewAccount);
    }


    /**
     * Handle an HTTP GET request for the path: /account/{accountId}
     * <p>
     * Return the account from the data source with the accountId provided
     * <p>
     * Note: The accountId requested must be specified as a path variable in the request
     *
     * @param accountId - must be specified as a path variable
     * @return the TenmoAccount object for the accountId specified or null
     */

    // TODONE: Write a controller to handle an HTTP GET request for the path: /account/{accountId}
    //********NUMBER 2****
    @RequestMapping(path = "/account/{accountId}", method = RequestMethod.GET)
    public TenmoAccount getAccountById(@PathVariable long accountId) {
        return tenmoAccountDao.getAccountForAccountId(accountId);
    }

    /**
     * Handle an HTTP GET request for either the path: /account
     * or: /account?id=userId
     * <p>
     * if the /account path is used for the request, all TenmoAccounts in the datasource will be returned
     * <p>
     * if the /account?id=usedId path is used in the request, all accounts for the specified userid will be returned
     *
     * @param theUserId - optional query parameter to request all accounts for a specific userid
     * @return - a list containing all accounts indicated by the path or an empty list if no accounts found
     */

    // TODO: Write a controller to handle a GET request for either the path: /account
    //                                                                   or: /account?id=userId
//********NUMBER 3****
    //COME BACK TO DIS!
    @RequestMapping(path = "/account", method = RequestMethod.GET)
    public List<TenmoAccount> getAccountById(@RequestParam(name = "userid", defaultValue = "0") int theUserId) {
        if (theUserId == 0) {
            return tenmoAccountDao.getAllAccounts();
        } else {
            return tenmoAccountDao.getAccountsForAUserId(theUserId);

        }
    }

    /**
     * Handles an HTTP GET request for path /user
     *
     * @return - a list of all users in the data source
     */

    // TODONE: Write a controller to handle a  GET request for path /user
    //********NUMBER 4****
    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public List<TenmoUser> getAllUsers(@RequestParam(defaultValue = "0") int userid) {
        //create empty list
        List<TenmoUser> users = new ArrayList<>();
        if (userid == 0){
            return tenmoUserDao.getAllUsernames();
        }else{
            users.add(tenmoUserDao.getUsernameById(userid));
            return users;
        }
    }



    /**
     * Handles an HTTP PUT request for the path: /account
     * <p>
     * Add a TenmoAccount object to  the datasource
     *
     * @param theUpdatedAcct - must be present in the request body as a valid JSON object for a TenmoAccount object
     * @return the update TenmoAccount object from the datasource
     */

    // TODONE: Write a controller to handle an HTTP PUT request for the path: /account
    //********NUMBER 5****
    @RequestMapping(path = "/account", method = RequestMethod.PUT)
    public TenmoAccount updateAccount(@Valid @RequestBody TenmoAccount theUpdatedAcct) {
        try {
            TenmoAccount updatedTenmoAccount = tenmoAccountDao.updateAccount(theUpdatedAcct);
            return updatedTenmoAccount;
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found.");
        }
    }

    /**
     * Handles an HTTP POST request for the path: /transfer
     * <p>
     * Add a TenmoTransfer object to  the datasource
     *
     * @param theTransfer - must be present in the request body as a valid JSON object for a TenmoTransfer object
     * @return the update TenmoTransfer object from the datasource
     */

    // TODONE: Write a controller to handle an HTTP POST request for the path: /transfer
//********NUMBER 6****
    @RequestMapping(path = "/transfer", method = RequestMethod.POST)
    public TenmoTransfer createTransfer(@Valid @RequestBody TenmoTransfer theTransfer) {
        if (theTransfer.getTransferType().equals(TenmoTransfer.TRANSFER_TYPE.SEND)){
            return tenmoTransferDao.saveTransfer(theTransfer, "send");
        } else {
            return tenmoTransferDao.saveTransfer(theTransfer, "request");
        }
    }

    /**
     * Handles HTTP GET for path /transfer?id=userid
     * <p>
     * Return all transfer for the userid given
     *
     * @param id - the userid whose transfers should be returned
     */

    // TODO: Write a controller to handles HTTP GET for path /transfer?id=userid
//********NUMBER 7****
    @RequestMapping(path = "/transfer", method = RequestMethod.GET)
    public List<TenmoTransfer> getTransferByUserId(@RequestParam int id) {
        return tenmoTransferDao.getTransfersForUser(id);
    }

    @RequestMapping(path = "/transfer", method = RequestMethod.PUT)
    public TenmoTransfer updateTransfer(@Valid @RequestBody TenmoTransfer theUpdatedTransfer) {
        try {
            TenmoTransfer updatedTenmoTransfer = tenmoTransferDao.updateTransfer(theUpdatedTransfer);
            return updatedTenmoTransfer;
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Transfer not found.");
        }
    }

    /**
     * Helper method to log API calls made to the server
     *
     * @param message - message to be included in the server log
     */
    public void logAPICall(String message) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss.A");
        String timeNow = now.format(formatter);
        System.out.println(timeNow + "-" + message);
    }

}
