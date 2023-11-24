package com.example.CoEduServer.repository;

import com.example.CoEduServer.domain.User_File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserFileRepository extends JpaRepository<User_File, Long> {
    List<User_File> findByUserId(Long userId);

    Optional<User_File> deleteByFileId(Long fileId);

    List<User_File> findByFileId(Long fileId);
    Optional<User_File> findByUserIdAndFileId(Long userId, Long fileId);
    Optional<User_File> deleteByUserIdAndFileId(Long userId, Long fileId);

}
