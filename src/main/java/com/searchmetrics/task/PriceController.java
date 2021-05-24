package com.searchmetrics.task;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PriceController {
    private static final String uri = "https://crypto.bahabin.com/binance/api/v3/ticker/price?symbol=BTCUSDT";

    @GetMapping("/prices/BTCUSDT")
    public Mono getBTCPrice() {
        return ApiUtil.callApi(uri);
    }
}
