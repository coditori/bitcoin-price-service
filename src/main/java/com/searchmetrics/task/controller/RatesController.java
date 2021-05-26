package com.searchmetrics.task.controller;

import com.searchmetrics.task.cache.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RatesController {
    private final String BITCOIN_SYMBOL = "BTCUSDT";

    @Autowired
    private CacheService cacheService;

    @GetMapping("/rates/BTCUSDT")
    public ResponseEntity getBTCPrice() {
        if (cacheService.ratesCache.containsKey(BITCOIN_SYMBOL))
            return ResponseEntity.ok(cacheService.ratesCache.get(BITCOIN_SYMBOL));

        return ResponseEntity.accepted().build();
    }
}
