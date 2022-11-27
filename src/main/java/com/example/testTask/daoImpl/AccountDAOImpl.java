package com.example.testTask.daoImpl;

import com.example.testTask.dao.AccountDAO;
import com.example.testTask.exception.ErrorCode;
import com.example.testTask.exception.ServerException;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountDAOImpl extends DaoImplBase implements AccountDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountDAOImpl.class);

    @Override
    public boolean addAmount(Integer id, Long value) {
        LOGGER.debug("DAO add amount {} to id {}", value, id);
        try (SqlSession sqlSession = getSession()) {
            try {
                if(getAmount(id)==null){
                    getAccountMapper(sqlSession).insertInAccount(id,value);
                }else{
                    getAccountMapper(sqlSession).updateInAccount(id, value+getAmount(id));
                }
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
    public Long getAmount(Integer id) {
        long rez;
        LOGGER.debug("DAO get amount id {}", id);
        try (SqlSession sqlSession = getSession()) {
            try {
                if(getAccountMapper(sqlSession).getAmount(id)==null){
                    try {
                        throw new ServerException(ErrorCode.INVALID_ACCOUNT_ID_ERROR);
                    } catch (ServerException e) {
                        LOGGER.error(String.valueOf(e));
                        return null;
                    }
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
}
