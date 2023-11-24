package com.example.CoEduServer.service;

import com.example.CoEduServer.domain.File;
import com.example.CoEduServer.domain.User;
import com.example.CoEduServer.domain.User_File;
import com.example.CoEduServer.domain.enums.Role;
import com.example.CoEduServer.dto.res.GetAuthorityDTO;
import com.example.CoEduServer.repository.FileRepository;
import com.example.CoEduServer.repository.UserFileRepository;
import com.example.CoEduServer.repository.UserRepository;
import jakarta.transaction.Transactional;
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

    public List<GetAuthorityDTO> getUserFiles(Long fileId) {
        // 메서드 구현 내용
        List<User_File> user_files = userFileRepository.findByFileId(fileId);
        List<GetAuthorityDTO> dto = new ArrayList<>();
        for(User_File userFile : user_files){
            dto.add(GetAuthorityDTO.toEntity(userFile));
        }
        return dto;
    }
    @Transactional
    public Long saveAuthority(User user, File file){
        User_File user_file = new User_File();
        user_file.setFile(file);
        user_file.setUser(user);
        user_file.setRole(Role.VIEWER);
        userFileRepository.save(user_file);
        return user_file.getFile().getId();
    }
    @Transactional
    public Long editAuthority(User_File user_file, Role role){
        user_file.setRole(role);
        userFileRepository.save(user_file);
        return user_file.getFile().getId();
    }
    @Transactional
    public void deleteAuthority(User_File user_file){
        userFileRepository.deleteByUserIdAndFileId(user_file.getUser().getId(), user_file.getFile().getId());
    }
}
