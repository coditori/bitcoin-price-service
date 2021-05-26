package com.searchmetrics.task.runner;

import com.searchmetrics.task.cache.CacheService;
import com.searchmetrics.task.dto.PriceDto;
import com.searchmetrics.task.util.ApiUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Component
public class RatesRunner implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(RatesRunner.class);
    private static final String bitcoinRateURI = "https://crypto.bahabin.com/binance/api/v3/ticker/price?symbol=BTCUSDT";

    @Autowired
    private CacheService cacheService;

    @Override
    public void run(ApplicationArguments args) {
        logger.debug("RatesRunner is running...");
        Flux.interval(Duration.ofSeconds(1))
                .onBackpressureDrop()
                .flatMap(x -> ApiUtil.callApi(bitcoinRateURI).bodyToMono(PriceDto.class))
                .doOnNext(x -> cacheService.ratesCache.put("BTCUSDT", x))
                .subscribe();
    }
}