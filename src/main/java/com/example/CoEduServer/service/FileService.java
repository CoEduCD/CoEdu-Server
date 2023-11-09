package com.example.CoEduServer.service;

import com.example.CoEduServer.domain.File;
import com.example.CoEduServer.dto.req.FileCreateDTO;
import com.example.CoEduServer.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;

    public boolean isFileNameDuplicate(List<File> userFiles, String fileName){
        return userFiles.stream().anyMatch(file -> file.getFile_name().equals(fileName));
    }

}
//
//
//

