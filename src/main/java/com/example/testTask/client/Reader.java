package com.example.testTask.client;

import lombok.AllArgsConstructor;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.core5.http.Method;

import java.io.IOException;
import java.net.URI;

/**
 * The reader class that will call
 * the getAmount method
 */
@AllArgsConstructor
class Reader implements Runnable {

    private final String host;
    private final int id;

    @Override
    public void run() {
        while (true) {
            try {
                Request.create(Method.GET, URI.create(host + "/account/" + id)).execute().returnContent();
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}