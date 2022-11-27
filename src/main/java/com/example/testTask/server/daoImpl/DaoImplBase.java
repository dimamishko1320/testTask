package com.example.testTask.server.daoImpl;

import com.example.testTask.server.Utils.MyBatisUtils;
import com.example.testTask.server.mappers.AccountMapper;
import org.apache.ibatis.session.SqlSession;

public class DaoImplBase {

    protected SqlSession getSession() {
        return MyBatisUtils.getSqlSessionFactory().openSession();
    }

    protected AccountMapper getAccountMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(AccountMapper.class);
    }



}
