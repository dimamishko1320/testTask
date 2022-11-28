package com.example.testTask.server.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StatisticTest {

    @Test
    void addMethod() {
        Statistic statistic = new Statistic();

        statistic.addMethod("getAmountTest1");
        assertTrue(statistic.getTotalValue().containsKey("getAmountTest1"));
        assertEquals(statistic.getTotalValue().get("getAmountTest1"), 0);

        assertTrue(statistic.getCurrentValue().containsKey("getAmountTest1"));
        assertEquals(statistic.getCurrentValue().get("getAmountTest1"), 0);

        assertTrue(statistic.getPrevValue().containsKey("getAmountTest1"));
        assertEquals(statistic.getPrevValue().get("getAmountTest1"), 0);


    }

    @Test
    void increaseCountInTotal() {
        Statistic statistic = new Statistic();
        statistic.addMethod("addAmountTest2");
        statistic.increaseCountInTotal("addAmountTest2");

        assertEquals(statistic.getTotalValue().get("addAmountTest2"), 1);

    }

    @Test
    void reset() {
        Statistic statistic = new Statistic();
        statistic.addMethod("getAmountTest3");
        statistic.increaseCountInTotal("getAmountTest3");

        assertEquals(statistic.getTotalValue().get("getAmountTest3"), 1);

        statistic.reset();

        assertEquals(statistic.getTotalValue().get("getAmountTest3"), 0);
    }


}