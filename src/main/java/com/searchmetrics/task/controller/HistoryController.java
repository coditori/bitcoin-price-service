package com.searchmetrics.task.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.searchmetrics.task.dto.HistoryResponseDto;
import com.searchmetrics.task.dto.HistoryRequestDto;
import com.searchmetrics.task.util.ApiUtil;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HistoryController {
    private static final Logger logger = LoggerFactory.getLogger(HistoryController.class);
    private static final String historyBaseURI = "https://api.binance.com/api/v3/klines?symbol=%s&interval=1d";

    @GetMapping("/histories/{symbol}")
    public Mono getBySymbol(@PathVariable("symbol") String symbol,
                                    @RequestParam(value = "startTime", required = false) Optional<Long> startTime,
                                    @RequestParam(value = "endTime", required = false) Optional<Long> endTime) {

        String uri = String.format(historyBaseURI, symbol) +
                getStartTime(startTime) +
                getEndTime(endTime);

        return ApiUtil.callApi(uri)
                .bodyToMono(Object.class);
    }

    private String getStartTime(Optional<Long> startTime) {
        return startTime.map(aLong -> "&startTime=" + aLong).orElse("");
    }

    private String getEndTime(Optional<Long> endTime) {
        return endTime.map(aLong -> "&endTime=" + aLong).orElse("");
    }
}