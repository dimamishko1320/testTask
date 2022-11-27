package com.example.testTask.server.daoImpl;

import com.example.testTask.server.dao.AccountDAO;
import com.example.testTask.server.exception.ErrorCode;
import com.example.testTask.server.exception.ServerException;
import com.example.testTask.server.model.Account;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class AccountDAOImpl extends DaoImplBase implements AccountDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountDAOImpl.class);

    @Override
    public Long getAmount(Integer id) {
        long rez;
        LOGGER.debug("DAO get amount id {}", id);
        try (SqlSession sqlSession = getSession()) {
            try {
                if(getAccountMapper(sqlSession).getAmount(id)==null){
                        LOGGER.error(String.valueOf(new ServerException(ErrorCode.INVALID_ACCOUNT_ID_ERROR).getErrorCode()));
                        return null;
                }else{
                    rez = getAccountMapper(sqlSession).getAmount(id);
                }
            } catch (RuntimeException e) {
                LOGGER.info("Can't get amount id {}, {}", id, e);
                sqlSession.rollback();
                throw e;
            }
            sqlSession.commit();
        }
        return rez;
    }

    @Override
    public boolean insertAmount(Integer id, Long value) {
        LOGGER.debug("DAO insert amount {} to id {}", value, id);
        try (SqlSession sqlSession = getSession()) {
            try {
                getAccountMapper(sqlSession).insertInAccount(id,value);
            } catch (RuntimeException e) {
                LOGGER.info("Can't add amount {} to id {}, {}",value, id, e);
                sqlSession.rollback();
                throw e;
            }
            sqlSession.commit();
        }
        return true;
    }

    @Override
    public boolean updateAmount(Integer id, Long value) {
        LOGGER.debug("DAO insert amount {} to id {}", value, id);
        try (SqlSession sqlSession = getSession()) {
            try {
                getAccountMapper(sqlSession).updateInAccount(id,value);
            } catch (RuntimeException e) {
                LOGGER.info("Can't add amount {} to id {}, {}",value, id, e);
                sqlSession.rollback();
                throw e;
            }
            sqlSession.commit();
        }
        return true;
    }

    @Override
    public List<Account> loadAll() {
        List<Account> allAccs = new ArrayList<>();
        LOGGER.debug("DAO get all account from database");
        try (SqlSession sqlSession = getSession()) {
            try {
                    allAccs = getAccountMapper(sqlSession).getAllAccounts();
            } catch (RuntimeException e) {
                LOGGER.info("Can't get amount id {}, {}", e);
                sqlSession.rollback();
                throw e;
            }
            sqlSession.commit();
        }
        return allAccs;
    }
}
