package com.searchmetrics.task.controller;

import com.searchmetrics.task.dto.PriceDto;
import com.searchmetrics.task.util.ApiUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RatesController {
    private static final String latestPriceBaseURI = "https://crypto.bahabin.com/binance/api/v3/ticker/price?symbol=";

    @GetMapping("/rates/{symbol}")
    public Mono getBTCPrice(@PathVariable("symbol") String symbol) {
        String uri = latestPriceBaseURI + symbol;
        return ApiUtil.callApi(uri)
                .bodyToMono(PriceDto.class);
    }
}
