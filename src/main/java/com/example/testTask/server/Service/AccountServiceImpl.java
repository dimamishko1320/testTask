package com.example.testTask.server.Service;

import com.example.testTask.server.Service.Interface.AccountService;
import com.example.testTask.server.Utils.MyBatisUtils;
import com.example.testTask.server.dao.AccountDAO;
import com.example.testTask.server.daoImpl.AccountDAOImpl;
import com.example.testTask.server.exception.ErrorCode;
import com.example.testTask.server.exception.ServerException;
import com.example.testTask.server.model.Account;
import com.example.testTask.server.model.Statistic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyBatisUtils.class);
    private final AccountDAO accountDAO = new AccountDAOImpl();
    private final Map<Integer, Long> cache = new ConcurrentHashMap<>();
    @Autowired
    private Statistic statistic;

    @PostConstruct
    public void init(){
        statistic = new Statistic();
        statistic.addMethod("getAmount");
        statistic.addMethod("addAmount");
        List<Account> accounts = accountDAO.loadAll();
        for(Account acc: accounts){
            cache.put(acc.getId(), acc.getAmount());
        }

    }

    @Override
    public synchronized Long getAmount(Integer id) {
        statistic.increaseCountInTotal("getAmount");
        if(id<=0){
                LOGGER.error(String.valueOf(new ServerException(ErrorCode.INVALID_ACCOUNT_ID_ERROR).getErrorCode()));
                return 0L;
        }else{
            if (cache.containsKey(id)) {
                return cache.get(id);
            } else if(!cache.containsKey(id)){
                LOGGER.error(new ServerException(ErrorCode.ACCOUNT_NOT_EXIST_ERROR).getErrorCode() + "with id" + id);
                return 0L;
            }
        }
        return 0L;
    }

    @Override
    public synchronized void addAmount(Integer id, Long value) {
        statistic.increaseCountInTotal("addAmount");
        if(id<=0){
                LOGGER.error(String.valueOf(new ServerException(ErrorCode.INVALID_ACCOUNT_ID_ERROR).getErrorCode()));
        }else{
            if (cache.containsKey(id)) {
                long currentVal=cache.get(id);
                accountDAO.updateAmount(id, value+currentVal);
                cache.replace(id,value+currentVal);
            } else {
                accountDAO.insertAmount(id, value);
                cache.put(id,value);
            }
        }
    }

    public void clearCache(){
        cache.clear();
    }
}
