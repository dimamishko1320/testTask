package com.example.testTask.Service;

import com.example.testTask.Service.Interface.AccountService;
import com.example.testTask.Utils.MyBatisUtils;
import com.example.testTask.dao.AccountDAO;
import com.example.testTask.daoImpl.AccountDAOImpl;
import com.example.testTask.exception.ErrorCode;
import com.example.testTask.exception.ServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class AccountServiceImpl implements AccountService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyBatisUtils.class);
    private AccountDAO accountDAO = new AccountDAOImpl();

    @Override
    public Long getAmount(Integer id) {
        if(id<=0){
            try {
                throw new ServerException(ErrorCode.INVALID_ACCOUNT_ID_ERROR);
            } catch (ServerException e) {
                LOGGER.error(String.valueOf(e));
                return Long.valueOf(0);
            }
        }
        return accountDAO.getAmount(id);
    }

    @Override
    public void addAmount(Integer id, Long value) {
        if(id<=0){
            try {
                throw new ServerException(ErrorCode.INVALID_ACCOUNT_ID_ERROR);
            } catch (ServerException e) {
                LOGGER.error(String.valueOf(e));
            }
        }
        accountDAO.addAmount(id, value);
    }
}
