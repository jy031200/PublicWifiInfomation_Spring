package com.example.spring_dao;
import com.example.spring_dto.SalesPost;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@SpringBootApplication
@Repository
public interface SalesPostRepository extends JpaRepository<SalesPost, String> {

    SalesPost findByUserId(String userId);
}
