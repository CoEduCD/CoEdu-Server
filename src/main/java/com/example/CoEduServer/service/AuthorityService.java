package com.example.CoEduServer.service;

import com.example.CoEduServer.domain.User_File;
import com.example.CoEduServer.dto.res.GetAuthorityDTO;
import com.example.CoEduServer.repository.FileRepository;
import com.example.CoEduServer.repository.UserFileRepository;
import com.example.CoEduServer.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorityService {
    private final FileRepository fileRepository;
    private final UserRepository userRepository;
    private final UserFileRepository userFileRepository;

    public List<GetAuthorityDTO> getUserFilesByFileHash(String fileHash) {
        // 메서드 구현 내용
        List<User_File> user_files = userFileRepository.findByFileHash(fileHash);
        List<GetAuthorityDTO> dto = new ArrayList<>();
        for(User_File userFile : user_files){
            dto.add(GetAuthorityDTO.toEntity(userFile));
        }
        return dto;
    }

}
