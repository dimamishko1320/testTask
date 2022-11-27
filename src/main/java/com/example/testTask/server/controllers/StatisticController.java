package com.example.testTask.server.controllers;

import com.example.testTask.server.model.Statistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatisticController {
    @Autowired
    private Statistic statistic;

    @GetMapping("/statistic")
    public void getStatistic() {
        System.out.println("Текущая статистика: ");
        statistic.getCurrentValue().toString();
        System.out.println("Общая статистика статистика: ");
        statistic.getTotalValue().toString();
    }

    @DeleteMapping("/statistic")
    public void resetStatistic() {
        statistic.reset();
    }
}
