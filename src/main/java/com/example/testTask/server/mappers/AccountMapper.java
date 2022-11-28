package com.example.testTask.server.mappers;

import com.example.testTask.server.model.Account;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface AccountMapper {
    @Insert("INSERT INTO Accounts (id, amount) VALUES " +
            "(#{id}, #{amount})")
    Integer insertInAccount(@Param("id")int id, @Param("amount")long amount);

    @Update("UPDATE Accounts SET amount = #{amount} WHERE id = #{id}")
    Integer updateInAccount(@Param("id")int id, @Param("amount")long amount);

    @Select("SELECT id, amount from Accounts")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "id", column = "id")})
    List<Account> getAllAccounts();

    @Delete("DELETE FROM Accounts")
    int deleteAllAccounts();



}
