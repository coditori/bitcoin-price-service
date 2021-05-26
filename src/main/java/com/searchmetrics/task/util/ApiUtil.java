package com.searchmetrics.task.util;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public final class ApiUtil {
    public static final WebClient webclient = WebClient.create();

    /*

     */
    public static WebClient.ResponseSpec callApi(String uri) {
        System.out.println("start getting data");
        return webclient.get()
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve();
    }
}
