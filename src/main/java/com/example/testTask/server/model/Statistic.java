package com.example.testTask.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Data
@AllArgsConstructor
@Component
public class Statistic {
    private Map<String, Integer> totalValue;
    private Map<String, Integer> prevValue;
    private Map<String, Integer> currentValue;


    public Statistic(){
        totalValue = new HashMap<>();
        prevValue = new HashMap<>();
        currentValue = new HashMap<>();
    }

    public void addMethod(String name){
        totalValue.put(name, 0);
        prevValue.put(name, 0);
        currentValue.put(name, 0);
    }

    public void reset(){
        for (String value: totalValue.keySet()) {
            totalValue.replace(value, 0);
            prevValue.replace(value, 0);
            currentValue.replace(value, 0);
        }
    }

    public void increaseCountToTotal(String nameMethod){
        totalValue.replace(nameMethod, totalValue.get(nameMethod)+1);
    }

    @Scheduled(fixedDelay = 1000)
    private void calculateCurrent(String nameMethod) {
        for (String s: totalValue.keySet()) {
            currentValue.replace(nameMethod, totalValue.get(nameMethod));
            prevValue.replace(nameMethod, totalValue.get(nameMethod));
        }
    }


}
