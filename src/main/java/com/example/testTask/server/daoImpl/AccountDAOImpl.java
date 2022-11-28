package com.example.testTask.server.daoImpl;

import com.example.testTask.server.dao.AccountDAO;
import com.example.testTask.server.model.Account;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class AccountDAOImpl extends DaoImplBase implements AccountDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountDAOImpl.class);

    @Override
    public void insertAmount(Integer id, Long value) {
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
    }

    @Override
    public void updateAmount(Integer id, Long value) {
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
    }

    @Override
    public List<Account> loadAll() {
        List<Account> allAccs;
        LOGGER.debug("DAO get all account from database");
        try (SqlSession sqlSession = getSession()) {
            try {
                    allAccs = getAccountMapper(sqlSession).getAllAccounts();
            } catch (RuntimeException e) {
                LOGGER.error(String.valueOf(e));
                sqlSession.rollback();
                throw e;
            }
            sqlSession.commit();
        }
        return allAccs;
    }

    @Override
    public void clearAll() {
        LOGGER.debug("DAO delete all account from database");
        try (SqlSession sqlSession = getSession()) {
            try {
                getAccountMapper(sqlSession).deleteAllAccounts();
            } catch (RuntimeException e) {
                LOGGER.error(String.valueOf(e));
                sqlSession.rollback();
                throw e;
            }
            sqlSession.commit();
        }
    }
}
