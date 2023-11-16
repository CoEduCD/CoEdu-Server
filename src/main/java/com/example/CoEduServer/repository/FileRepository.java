package com.example.CoEduServer.repository;


import com.example.CoEduServer.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FileRepository extends JpaRepository<File, Long> {
    Optional<File> findById(Long id);

//    List<File> findByFile_Hash(String file_hash);

}
