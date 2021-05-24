package com.searchmetrics.task;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;
import reactor.util.retry.RetryBackoffSpec;

import java.time.Duration;

public final class ApiUtil {
    public static final WebClient webclient = WebClient.create();

    static Mono<Object> callApi(String uri) {
        return webclient.get()
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Object.class)
                .retryWhen(ApiUtil.retryCallingApi()); // try another api call if there is an error about getting data
    }

    // retry calling API every 20 seconds for 20 times (at max)
    public static RetryBackoffSpec retryCallingApi() {
        return Retry.backoff(20, Duration.ofSeconds(2))
                .maxBackoff(Duration.ofMinutes(1))
                .filter(ApiUtil::isThereApiError);
    }

    // checks all the cases may happen when getting the data from external API
    public static boolean isThereApiError(Throwable e) {
        return e.getClass() == WebClientResponseException.GatewayTimeout.class ||
                e.getClass() == WebClientResponseException.ServiceUnavailable.class ||
                e.getClass() == WebClientResponseException.BadGateway.class ||
                e.getClass() == io.netty.channel.ConnectTimeoutException.class ||
                e.getClass() == io.netty.handler.timeout.ReadTimeoutException.class ||
                e.getClass() == io.netty.handler.ssl.SslHandshakeTimeoutException.class ||
                e.getClass() == reactor.netty.http.client.PrematureCloseException.class ||
                e.getClass() == java.net.UnknownHostException.class ||
                e.getClass() == NullPointerException.class ||
                e instanceof RuntimeException;
    }
}
