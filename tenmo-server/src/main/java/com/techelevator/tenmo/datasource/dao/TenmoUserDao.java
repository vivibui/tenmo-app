package com.techelevator.tenmo.datasource.dao;

import com.techelevator.tenmo.datasource.model.TenmoUser;

import java.util.List;

public interface TenmoUserDao {
    TenmoUser getUsernameById(int usernameId);
}
