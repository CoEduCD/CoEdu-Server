package com.example.CoEduServer.repository;

import com.example.CoEduServer.domain.User_File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserFileRepository extends JpaRepository<User_File, Long> {
    List<User_File> findByUser_Id(Long userId);

    Optional<User_File> findByFile_Id(Long fileId);

    Optional<User_File> deleteByFile_Id(Long fileId);

    Optional<User_File> findByFile_HashAndUser_Id(String file_hash, Long UserId);

    List<User_File> findByFile_Hash(String file_hash);
}
