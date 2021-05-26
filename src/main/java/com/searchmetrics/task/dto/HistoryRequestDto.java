package com.searchmetrics.task.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoryRequestDto {
    @JsonProperty("0")
    private Long openTime; // candle open time based on interval

    @JsonProperty("1")
    private String open;

    @JsonProperty("2")
    private String high;

    @JsonProperty("3")
    private String low;

    @JsonProperty("4")
    private String close;

    @JsonProperty("5")
    private String volume;

    @JsonProperty("6")
    private Long closeTime; // candle close time based on interval
}