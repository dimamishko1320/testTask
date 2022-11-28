package com.example.testTask.client;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class for launching clients thread - readers
 * and writers
 */
public class Client {
    public static void main(String[] args) throws IOException {
        String host = "http://localhost:8080";
        int read;
        int write;
        List<Integer> ids = new ArrayList<>();
        Properties props = new Properties();
        props.load(new FileInputStream("src/main/resources/application.properties"));
        read = Integer.parseInt(props.getProperty("rCount"));
        write = Integer.parseInt(props.getProperty("wCount"));
        String[] parts = props.getProperty("idList").split(";");
        for (String part : parts) {
            ids.add(Integer.valueOf(part));
        }

        ExecutorService executorService = Executors.newFixedThreadPool(read + write);

        for (int i = 0; i < read; i++) {
            int id = i < ids.size() ? i : i % ids.size();
            executorService.submit(new Reader(host, ids.get(id)));
        }

        for (int i = 0; i < write; i++) {
            int id = i < ids.size() ? i : i % ids.size();
            executorService.submit(new Writer(host, ids.get(id)));
        }
    }
}




