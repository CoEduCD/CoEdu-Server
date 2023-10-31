package com.example.CoEduServer.repository;

import com.example.CoEduServer.domain.User_File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserFileRepository extends JpaRepository<User_File, Long> {
    List<User_File> findByUser_Id(Long userId);
}
