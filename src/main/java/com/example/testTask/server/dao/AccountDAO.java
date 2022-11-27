package com.example.testTask.server.dao;

public interface AccountDAO {
    Long getAmount(Integer id);
    boolean insertAmount(Integer id, Long value);
    boolean updateAmount(Integer id, Long value);

}
