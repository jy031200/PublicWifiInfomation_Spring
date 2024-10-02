package com.example.dto;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "location_data")
@AllArgsConstructor
@NoArgsConstructor
public class Location_Data {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String distance_num;
    private String wifi;
    private String area;
    private String wifi_name;
    private String address;
    private String detail_address;
    private String floor;
    private String wifi_type;
    private String wifi_organ;
    private String service;
    private String mesh;
    private String install_year;
    private String in_out;
    private String connect;
    private Double x; // X 좌표
    private Double y; // Y 좌표
    private String work_year;
}

