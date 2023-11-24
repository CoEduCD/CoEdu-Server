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

    @GetMapping("/file/authority/{fileId}")
    public ResponseEntity<List<GetAuthorityDTO>> getAuthority(@PathVariable Long fileId){
        return ResponseEntity.ok().body(authorityService.getUserFiles(fileId));
    }

    //권한 추가
    @PostMapping("/file/authority/add")
    public ResponseEntity<List<GetAuthorityDTO>> addAuthority(@RequestBody AddAuthorityDTO addAuthorityDTO){
        // 1. fileId로 해당 파일이 있는지 확인.
        File byId = fileRepository.findById(addAuthorityDTO.getFileId()).orElse(null);
        if(byId == null){
            return ResponseEntity.status(400).body(null);
        }
        // 2. fileId와 userID로 해당 파일에 대한 권한이 ADMIN인지 확인.
        User_File byUserIdAndFileId = userFileRepository.findByUserIdAndFileId(addAuthorityDTO.getUserId(), addAuthorityDTO.getFileId()).orElse(null);
        if(byUserIdAndFileId == null){
            return ResponseEntity.status(400).body(null);
        }
        if(byUserIdAndFileId.getRole() != Role.ADMIN){
            return ResponseEntity.status(400).body(null);
        }
        // 3. 추가하려는 사용자의 email이 user table에 존재하는지 확인.
        User user = userRepository.findByEmail(addAuthorityDTO.getEmail()).orElse(null);
        if(user == null){
            return ResponseEntity.status(404).body(null);
        }
        Long fileId = authorityService.saveAuthority(user, byUserIdAndFileId.getFile());
        return ResponseEntity.ok().body(authorityService.getUserFiles(fileId));
    }

    @PatchMapping("/file/authority/edit")
    public ResponseEntity<List<GetAuthorityDTO>> editAuthority(@RequestBody EditAuthorityDTO editAuthorityDTO){
        // 1. 전달받은 fileId와 userId를 가지는 파일을 찾는다.
        // 2. 수정해주는 사용자가 ADMIN이 맞는지 확인한다.
        User_File user_file = userFileRepository.findByUserIdAndFileId(editAuthorityDTO.getUserId(), editAuthorityDTO.getFileId()).orElse(null);
        if(user_file == null){
            return ResponseEntity.status(400).body(null);
        }
        if(user_file.getRole() != Role.ADMIN){
            return ResponseEntity.status(400).body(null);
        }
        // 권한을 수정하려는 사용자가 해당 파일을 가지고 있는지 확인한다.
        User_File edit_user = userFileRepository.findByUserIdAndFileId(editAuthorityDTO.getEditUserId(), editAuthorityDTO.getFileId()).orElse(null);
        if(edit_user == null){
            return ResponseEntity.status(400).body(null);
        }
        Long fileId = authorityService.editAuthority(edit_user, editAuthorityDTO.getRole());
        return ResponseEntity.status(200).body(authorityService.getUserFiles(fileId));
    }

    @DeleteMapping("/file/authority/delete")
    public ResponseEntity<List<GetAuthorityDTO>> deleteAuthority(@RequestBody DeleteAuthorityDTO deleteAuthorityDTO){
        // 1.삭제하려는 사용자의 권한이 ADMIN인지 확인한다.
        User_File user_file = userFileRepository.findByUserIdAndFileId(deleteAuthorityDTO.getUserId(), deleteAuthorityDTO.getFileId()).orElse(null);
        if(user_file == null){
            return ResponseEntity.status(400).body(null);
        }
        if(user_file.getRole() != Role.ADMIN){
            return ResponseEntity.status(400).body(null);
        }
        // 권한을 삭제당하는 사용자가 해당 파일을 가지고 있는지 확인한다.
        User_File delete_user = userFileRepository.findByUserIdAndFileId(deleteAuthorityDTO.getDeleteUserId(), deleteAuthorityDTO.getFileId()).orElse(null);
        if(delete_user == null){
            return ResponseEntity.status(400).body(null);
        }
        authorityService.deleteAuthority(delete_user);
        return ResponseEntity.status(200).body(authorityService.getUserFiles(deleteAuthorityDTO.getFileId()));
    }
}
