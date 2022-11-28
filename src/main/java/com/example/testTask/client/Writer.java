package com.example.testTask.client;

import lombok.AllArgsConstructor;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.core5.http.Method;
import java.io.IOException;
import java.net.URI;
import java.util.Random;

/**
 * The writer class that will call
 * the addAmount method
 */
@AllArgsConstructor
class Writer implements Runnable {

    private final String host;
    private final int id;

    @Override
    public void run() {
        while (true) {
            Random random = new Random();
            long amount = random.nextInt(50);
            try {
                Request.create(Method.PUT, URI.create(host + "/account/" + id + "/" + amount)).execute().returnContent();
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}