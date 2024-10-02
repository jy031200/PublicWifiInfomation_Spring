package com.example.repository;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class WifiDataResponseWrapper {
    @JsonProperty("TbPublicWifiInfo")
    private TbPublicWifiInfo TbPublicWifiInfo;
}