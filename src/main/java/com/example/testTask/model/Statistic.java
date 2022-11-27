package com.example.testTask.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

enum Method{
    ADDAMOUNT,
    GETAMOUNT
}

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Statistic {
    private int totalRead;
    private int currentRead;
    private Method nameMethod;
}
