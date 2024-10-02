package com.example.controller;

import com.example.repository.WifiDataResponseWrapper;
import com.example.repository.DataRepository;
import com.example.dao.MainDAO;
import com.example.dto.Location_Data;
import com.example.dto.WifiDataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DataController {

    private final MainDAO mainDAO;
    private final DataRepository dataRepository;
    private static final String apiUrl = "http://openapi.seoul.go.kr:8088/4f667541706a793033374666544d75/json/TbPublicWifiInfo/%d/%d/"; // 숫자 맨 끝까지 반복해서 가져오기
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DataController(MainDAO mainDAO, DataRepository dataRepository, JdbcTemplate jdbcTemplate) {
        this.mainDAO = mainDAO;
        this.dataRepository = dataRepository;
        this.jdbcTemplate = jdbcTemplate;
    }


    @GetMapping("/mainPage") // 기본 테이블 생성 - 데이터 있으면 화면에 띄우고 없으면 데이터 없음을 띄움
    public String MainPage(Model model) {
        // 데이터 가져오기
        String locationData = mainDAO.ShowWifiData();
        System.out.println("Fetched Location Data: " + locationData);

        // JSON 문자열을 모델에 추가
        model.addAttribute("locationData", locationData);
        return "index"; // JSP 페이지 이름
    }

    @DeleteMapping("/deletedata") // 웹페이지 실행 시 기존 테이블 데이터 삭제
    public ResponseEntity<Void> resetData() {
        dataRepository.deleteAll();
        jdbcTemplate.execute("TRUNCATE TABLE location_data");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/DataLoad") //openapi 데이터 가져오기
    public String DataLoad(Model model) {
        RestTemplate restTemplate = new RestTemplate();

        try {
            int start = 1;
            int end = 1000;
            int totalRecords = 24593;
            int listTotalCount = 0;

            List<Location_Data> arraydatalist = new ArrayList<>();

            while(start <= totalRecords) {
                String apiUrlformat = String.format(apiUrl, start, end);
                WifiDataResponseWrapper wrapper = restTemplate.getForObject(apiUrlformat, WifiDataResponseWrapper.class);
                if (wrapper != null && wrapper.getTbPublicWifiInfo() != null) {
                    List<WifiDataResponse> wifiDataList = wrapper.getTbPublicWifiInfo().getRow();
                    if (wifiDataList != null && !wifiDataList.isEmpty()) {
                        for (WifiDataResponse value : wifiDataList) {
                            Location_Data entity = new Location_Data();
                            entity.setDistance_num(""); // distance_num 기본값 설정
                            entity.setWifi(value.getWifi());
                            entity.setArea(value.getArea());
                            entity.setWifi_name(value.getWifi_name());
                            entity.setAddress(value.getAddress());
                            entity.setDetail_address(value.getDetail_address());
                            entity.setFloor(value.getFloor());
                            entity.setWifi_type(value.getWifi_type());
                            entity.setWifi_organ(value.getWifi_organ());
                            entity.setService(value.getService());
                            entity.setMesh(value.getMesh());
                            entity.setInstall_year(value.getInstall_year());
                            entity.setIn_out(value.getIn_out());
                            entity.setConnect(value.getConnect());
                            entity.setX(value.getX());
                            entity.setY(value.getY());
                            entity.setWork_year(value.getWork_year());

                            arraydatalist.add(entity);
                        }
                    }
                } else {
                    System.out.println("No data found in the response.");
                }
                start += 1000;
                end = Math.min(end + 1000, totalRecords);
            }
            dataRepository.saveAll(arraydatalist); // JPA를 사용해 저장
            List<Location_Data> latestData = dataRepository.findTopByOrderByIdDesc();

            if (!latestData.isEmpty()) {
                listTotalCount = latestData.getFirst().getId(); // 가장 최신 ID
            } else {
                System.out.println("No data found");
            }
            model.addAttribute("listTotalCount", listTotalCount);
            return "load-wifi";

        } catch (RestClientException e) {
            System.err.print("Error fetching data: \" + e.getMessage()");
            throw new RuntimeException(e);
        }
    }


}
