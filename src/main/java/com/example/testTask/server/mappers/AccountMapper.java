package com.example.testTask.server.mappers;

import org.apache.ibatis.annotations.*;

public interface AccountMapper {
    @Insert("INSERT INTO Accounts (id, amount) VALUES " +
            "(#{id}, #{amount})")
    Integer insertInAccount(@Param("id")int id, @Param("amount")long amount);

    @Update("UPDATE Accounts SET amount = #{amount} WHERE id = #{id}")
    Integer updateInAccount(@Param("id")int id, @Param("amount")long amount);

    @Select("SELECT amount FROM Accounts WHERE id=#{id}")
    Long getAmount(@Param("id")int id);


}
