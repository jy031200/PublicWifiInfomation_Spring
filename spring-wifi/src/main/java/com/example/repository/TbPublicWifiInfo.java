package com.example.repository;

import com.example.dto.WifiDataResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TbPublicWifiInfo {
    @JsonProperty("list_total_count")
    private int list_total_count;

    @JsonProperty("row")
    private List<WifiDataResponse> row;

    @JsonProperty("RESULT")
    private RESULT result;
}

@Getter
@Setter
class RESULT {

    @JsonProperty("CODE")
    private String code;

    @JsonProperty("MESSAGE")
    private String message;
}
