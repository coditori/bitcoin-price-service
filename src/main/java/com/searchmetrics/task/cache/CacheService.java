package com.searchmetrics.task.cache;

import com.searchmetrics.task.dto.PriceDto;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class CacheService {
    public ConcurrentHashMap<String, PriceDto> ratesCache = new ConcurrentHashMap<>();
}
