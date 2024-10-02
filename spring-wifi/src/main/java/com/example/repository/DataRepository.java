package com.example.repository;

import com.example.dto.Location_Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DataRepository extends JpaRepository<Location_Data, Long> {

    // 최신 데이터를 id 기준 내림차순으로 가져오는 메서드
    @Query("SELECT l FROM Location_Data l ORDER BY l.id DESC")
    List<Location_Data> findTopByOrderByIdDesc();

    @Query(value = "SELECT id FROM Location_Data ORDER BY id",nativeQuery = true)
    List<Integer> findAllIds();

    @Modifying
    @Transactional
    @Query(value = "UPDATE Location_Data SET distance_num = ?1 WHERE id = ?2", nativeQuery = true)
    void updateLocation_DataById(String distanceNum, Integer id);

    // 최신 데이터를 거리 기준 오름차순으로 가져오는 메서드
    @Query("SELECT l FROM Location_Data l ORDER BY l.distance_num")
    void findTopByOrderByDistence();
}
