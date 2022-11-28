package com.example.testTask.server.daoImpl;

import com.example.testTask.server.Service.AccountServiceImpl;
import com.example.testTask.server.Utils.MyBatisUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AccountDAOImplTest {


    private static AccountServiceImpl accountService;
    private static AccountDAOImpl accountDAO;


    @BeforeAll
    public static void setup() {
        MyBatisUtils.initSqlSessionFactory();
        accountService = new AccountServiceImpl();
        accountService.init();
        accountDAO = new AccountDAOImpl();
    }

    @Test
    void test() {
        accountDAO.clearAll();
        accountService.clearCache();
        accountService.addAmount(1, 1L);
        assertEquals(accountService.getAmount(1), 1);
    }

}