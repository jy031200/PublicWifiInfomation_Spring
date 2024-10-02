package com.example.dao;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class MainDAO implements MainDAOImpl {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MainDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public String ShowWifiData() {
        String sql = "SELECT * FROM location_data ORDER BY id ASC LIMIT 10";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        return new Gson().toJson(rows); // JSON 형식으로 반환
    }

    @Override
    public List<Double> GetLocationXData() {
        String sql = "SELECT x FROM location_data"; // SQL 쿼리
        return jdbcTemplate.queryForList(sql, Double.class); // List<Double> 반환
    }

    @Override
    public List<Double> GetLocationYData() {
        String sql = "SELECT y FROM location_data"; // SQL 쿼리
        return jdbcTemplate.queryForList(sql, Double.class); // List<Double> 반환
    }

}
