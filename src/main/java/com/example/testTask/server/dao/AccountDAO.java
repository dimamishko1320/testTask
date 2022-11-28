package com.example.testTask.server.dao;

import com.example.testTask.server.model.Account;

import java.util.List;

public interface AccountDAO {
    /**
     * Insert amount in database
     *
     * @param id balance identifier
     * @param value balance value
     */
    void insertAmount(Integer id, Long value);

    /**
     * Update amount in database
     *
     * @param id balance identifier
     * @param value balance value
     */
    void updateAmount(Integer id, Long value);

    /**
     * Load all accounts from database
     * @return list with all accounts
     */
    List<Account> loadAll();

    /**
     * Clear table 'Account' in database
     */
    void clearAll();

}
