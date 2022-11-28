package com.example.testTask.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;


@Data
@AllArgsConstructor
@Component
@EnableScheduling
public class Statistic {
    private Map<String, Integer> totalValue;
    private Map<String, Integer> prevValue;
    private Map<String, Integer> currentValue;


    public Statistic(){
        totalValue = new HashMap<>();
        prevValue = new HashMap<>();
        currentValue = new HashMap<>();
    }

    /**
     * Add new method to map
     * @param name name new method
     */
    public void addMethod(String name){
        totalValue.put(name, 0);
        prevValue.put(name, 0);
        currentValue.put(name, 0);
    }

    /**
     * Reset all maps with statistic value
     */
    public void reset(){
        for (String value: totalValue.keySet()) {
            totalValue.replace(value, 0);
            prevValue.replace(value, 0);
            currentValue.replace(value, 0);
        }
    }

    /**
     * Increments the value of a counter for a specific method
     * @param nameMethod name method
     */
    public void increaseCountInTotal(String nameMethod){
        totalValue.replace(nameMethod, totalValue.get(nameMethod)+1);
    }

    /**
     * The function calculates the number of calls
     * to all methods per unit of time
     */
    @Scheduled(fixedDelay = 1000)
    private void calculateCurrent() {
        for (String nameMethod: totalValue.keySet()) {
            currentValue.replace(nameMethod, totalValue.get(nameMethod)-prevValue.get(nameMethod));
            prevValue.replace(nameMethod, totalValue.get(nameMethod));
        }
    }


}
