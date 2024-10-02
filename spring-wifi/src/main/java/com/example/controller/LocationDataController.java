package com.example.controller;

import com.example.dto.Location_Data;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import com.example.dao.MainDAO;
import com.example.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class LocationDataController {
    private final MainDAO mainDAO;
    private final DataRepository dataRepository;
    private static final int EARTH_RADIUS_KM = 6371;

    @Autowired
    public LocationDataController(MainDAO mainDAO, DataRepository dataRepository) {
        this.mainDAO = mainDAO;
        this.dataRepository = dataRepository;
    }

    @GetMapping("/distance") // 현재 내 위치와 와이파이 좌표를 거리 계산해서 distance_num 넣기
    public String Location_distance(@RequestParam double latitude, @RequestParam double longitude) {
        double lat = latitude;  // 입력한 X좌표
        double lng = longitude; // 입력한 Y좌표

        List<Location_Data> arraydatalist = new ArrayList<>();

        List<Double> dbLats = mainDAO.GetLocationXData(); // DB에 있는 X좌표
        List<Double> dbLngs = mainDAO.GetLocationYData(); // DB에 있는 Y좌표
        List<Integer> ids = dataRepository.findAllIds();  // ID 목록

        for (int i = 0; i < dbLats.size(); i++) {
            double dbLat = dbLats.get(i);
            double dbLng = dbLngs.get(i);

            double distance = calculateDistance(lat, lng, dbLat, dbLng); // 거리 계산

            if (i < ids.size()) {
                Location_Data entity = new Location_Data();
                entity.setDistance_num(String.valueOf(distance)); // 거리 설정
                entity.setId(ids.get(i)); // ID 설정
                arraydatalist.add(entity);
            }
        }
        dataRepository.saveAll(arraydatalist); // 입력된 위치 좌표를 통해 distance_num 저장
        dataRepository.findTopByOrderByDistence(); // distance_num을 기준으로 가까운 와이파이 정보 10개 보여주기
        return null; // 적절한 응답
    }


    private static double toRadians(double degree) {
        return degree * Math.PI / 180.0;
    }

    private static double calculateDistance(double lat1, double lng1, double lat2, double lng2) {
        double dLat = toRadians(lat2 - lat1);
        double dLon = toRadians(lng2 - lng1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(toRadians(lat1)) * Math.cos(toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS_KM * c;
    }
}
