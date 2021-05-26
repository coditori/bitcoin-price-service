package com.searchmetrics.task.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoryResponseDto {
    private Long openTime; // candle open time based on interval
    private String open;
    private String high;
    private String low;
    private String close;
    private String volume;
    private Long closeTime; // candle close time based on interval
}