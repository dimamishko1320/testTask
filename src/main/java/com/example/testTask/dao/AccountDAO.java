package com.example.testTask.dao;

public interface AccountDAO {
    Long getAmount(Integer id);
    boolean addAmount(Integer id, Long value);
}
