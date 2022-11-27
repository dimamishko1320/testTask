package com.example.testTask.server.Service;

import com.example.testTask.server.Service.Interface.AccountService;
import com.example.testTask.server.Utils.MyBatisUtils;
import com.example.testTask.server.dao.AccountDAO;
import com.example.testTask.server.daoImpl.AccountDAOImpl;
import com.example.testTask.server.exception.ErrorCode;
import com.example.testTask.server.exception.ServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Service
public class AccountServiceImpl implements AccountService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyBatisUtils.class);
    private AccountDAO accountDAO = new AccountDAOImpl();
    private final Map<Integer, Long> cache = new ConcurrentHashMap<>();

    @Override
    public Long getAmount(Integer id) {
        if(id<=0){
                LOGGER.error(String.valueOf(new ServerException(ErrorCode.INVALID_ACCOUNT_ID_ERROR).getErrorCode()));
                return Long.valueOf(0);
        }else{
            if (cache.containsKey(id)) {
                return cache.get(id);
            } else if(!cache.containsKey(id)){
                LOGGER.error(new ServerException(ErrorCode.ACCOUNT_NOT_EXIST_ERROR).getErrorCode() + "with id" + id);
                return Long.valueOf(0);
            }
        }
        return Long.valueOf(0);
    }

    @Override
    public void addAmount(Integer id, Long value) {
        if(id<=0){
                LOGGER.error(String.valueOf(new ServerException(ErrorCode.INVALID_ACCOUNT_ID_ERROR).getErrorCode()));
                return;
        }else{
            if (cache.containsKey(id)) {
                long currentVal=cache.get(id);
                accountDAO.updateAmount(id, value+currentVal);
                cache.put(id,value+currentVal);
            } else {
                accountDAO.insertAmount(id, value);
                cache.put(id,value);
            }
        }

    }
}
