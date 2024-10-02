package com.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class WifiDataResponse {
    private String distance_num;

    @JsonProperty("X_SWIFI_MGR_NO")
    private String wifi;

    @JsonProperty("X_SWIFI_WRDOFC")
    private String area;

    @JsonProperty("X_SWIFI_MAIN_NM")
    private String wifi_name;

    @JsonProperty("X_SWIFI_ADRES1")
    private String address;

    @JsonProperty("X_SWIFI_ADRES2")
    private String detail_address;

    @JsonProperty("X_SWIFI_INSTL_FLOOR")
    private String floor;

    @JsonProperty("X_SWIFI_INSTL_TY")
    private String wifi_type;

    @JsonProperty("X_SWIFI_INSTL_MBY")
    private String wifi_organ;

    @JsonProperty("X_SWIFI_SVC_SE")
    private String service;

    @JsonProperty("X_SWIFI_CMCWR")
    private String mesh;

    @JsonProperty("X_SWIFI_CNSTC_YEAR")
    private String install_year;

    @JsonProperty("X_SWIFI_INOUT_DOOR")
    private String in_out;

    @JsonProperty("X_SWIFI_REMARS3")
    private String connect;

    @JsonProperty("LAT")
    private Double x; // X 좌표

    @JsonProperty("LNT")
    private Double y; // Y 좌표

    @JsonProperty("WORK_DTTM")
    private String work_year;

}
