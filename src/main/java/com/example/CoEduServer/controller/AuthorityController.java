package com.example.CoEduServer.controller;

import com.example.CoEduServer.domain.File;
import com.example.CoEduServer.domain.User;
import com.example.CoEduServer.domain.User_File;
import com.example.CoEduServer.domain.enums.Role;
import com.example.CoEduServer.dto.req.AddAuthorityDTO;
import com.example.CoEduServer.dto.req.DeleteAuthorityDTO;
import com.example.CoEduServer.dto.req.EditAuthorityDTO;
import com.example.CoEduServer.dto.res.BaseResponse;
import com.example.CoEduServer.dto.res.GetAuthorityDTO;
import com.example.CoEduServer.repository.FileRepository;
import com.example.CoEduServer.repository.UserFileRepository;
import com.example.CoEduServer.repository.UserRepository;
import com.example.CoEduServer.service.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthorityController {
    private final FileRepository fileRepository;
    private final UserRepository userRepository;
    private final UserFileRepository userFileRepository;
    private final AuthorityService authorityService;
    // fileId로 해당 파일의 정보를 받아와
    // Role을 바꿔 새로 저장
    // User_File entity에도 같이 저장

    @GetMapping("/file/authority/{file_hash}")
    public ResponseEntity<List<GetAuthorityDTO>> getAuthority(@PathVariable String file_hash){
        return ResponseEntity.ok().body(authorityService.getUserFilesByFileHash(file_hash));
    }

    //권한 추가
    @PostMapping("/file/authority/add")
    public ResponseEntity<List<GetAuthorityDTO>> addAuthority(@RequestBody AddAuthorityDTO addAuthorityDTO){
        // 1. fileId로 해당 파일을 찾고, 해당 파일의 user_id와 입력받은 user_id가 일치하면서 role이 admin인지 확인.
        File byId = fileRepository.findById(addAuthorityDTO.getFile_id()).orElse(null);
        if(byId == null){
            return ResponseEntity.status(400).body(null);
        }
        User_File byUser_id = userFileRepository.findByFile_Id(byId.getId()).orElse(null);
        if(byUser_id == null){
            return ResponseEntity.status(400).body(null);
        }
        if(byUser_id.getRole() != Role.ADMIN){
            return ResponseEntity.status(400).body(null);
        }
        User user = userRepository.findByEmail(addAuthorityDTO.getEmail()).orElse(null);
        if(user == null){
            return ResponseEntity.status(400).body(null);
        }
        File savedFile = fileRepository.save(addAuthorityDTO.toEntity(byId));
        User_File user_file = new User_File();
        user_file.setUser(user);
        user_file.setFile(savedFile);
        user_file.setFileHash(savedFile.getFileHash());
        user_file.setRole(addAuthorityDTO.getRole());
        userFileRepository.save(user_file);
        return ResponseEntity.ok().body(authorityService.getUserFilesByFileHash(savedFile.getFileHash()));
    }

    @PatchMapping("/file/authority/edit")
    public ResponseEntity<List<GetAuthorityDTO>> editAuthority(@RequestBody EditAuthorityDTO editAuthorityDTO){
        // 1. 전달받은 file_hash와 user_id를 가지는 파일을 찾는다.
        // 2. 전달받은 Role로 해당 사용자의 Role을 수정한다.
        User_File user_file = userFileRepository.findByFileHashAndUser_Id(editAuthorityDTO.getFileHash(), editAuthorityDTO.getUser_id()).orElse(null);
        if(user_file == null){
            return ResponseEntity.status(400).body(null);
        }
        File file = fileRepository.findById(user_file.getFile().getId()).orElse(null);
        if(file == null){
            return ResponseEntity.status(400).body(null);
        }
        file.setRole(editAuthorityDTO.getRole());
        fileRepository.save(file);
        user_file.setRole(editAuthorityDTO.getRole());
        userFileRepository.save(user_file);
        return ResponseEntity.status(200).body(authorityService.getUserFilesByFileHash(user_file.getFileHash()));
    }

    @DeleteMapping("/file/authority/delete")
    public ResponseEntity<List<GetAuthorityDTO>> deleteAuthority(@RequestBody DeleteAuthorityDTO deleteAuthorityDTO){
        User_File user_file = userFileRepository.findByFileHashAndUser_Id(deleteAuthorityDTO.getFile_hash(), deleteAuthorityDTO.getUser_id()).orElse(null);
        if(user_file == null){
            return ResponseEntity.status(400).body(null);
        }
        fileRepository.deleteById(user_file.getFile().getId());
        return ResponseEntity.status(200).body(authorityService.getUserFilesByFileHash(user_file.getFileHash()));
    }
}
