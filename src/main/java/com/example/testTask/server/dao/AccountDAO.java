package com.example.testTask.server.dao;

import com.example.testTask.server.model.Account;

import java.util.List;

public interface AccountDAO {
    Long getAmount(Integer id);
    boolean insertAmount(Integer id, Long value);
    boolean updateAmount(Integer id, Long value);
    List<Account> loadAll();

}
