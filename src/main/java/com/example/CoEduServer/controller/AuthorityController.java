package com.example.CoEduServer.controller;

import com.example.CoEduServer.domain.File;
import com.example.CoEduServer.domain.User;
import com.example.CoEduServer.domain.User_File;
import com.example.CoEduServer.dto.req.AddAuthorityDTO;
import com.example.CoEduServer.dto.req.DeleteAuthorityDTO;
import com.example.CoEduServer.dto.req.EditAuthorityDTO;
import com.example.CoEduServer.dto.res.BaseResponse;
import com.example.CoEduServer.dto.res.GetAuthorityDTO;
import com.example.CoEduServer.repository.FileRepository;
import com.example.CoEduServer.repository.UserFileRepository;
import com.example.CoEduServer.repository.UserRepository;
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
    // fileId로 해당 파일의 정보를 받아와
    // Role을 바꿔 새로 저장
    // User_File entity에도 같이 저장

    @GetMapping("/file/authority/{file_hash}")
    public ResponseEntity<List<GetAuthorityDTO>> getAuthority(@PathVariable String file_hash){
        List<User_File> user_files = userFileRepository.findByFileHash(file_hash);
        List<GetAuthorityDTO> dto = new ArrayList<>();
        for(User_File user_file : user_files){
            dto.add(GetAuthorityDTO.toEntity(user_file));
        }

        return ResponseEntity.ok().body(dto);
    }

    //권한 추가
    @PostMapping("/file/authority/add")
    public ResponseEntity<? extends BaseResponse> addAuthority(@RequestBody AddAuthorityDTO addAuthorityDTO){
        File byId = fileRepository.findById(addAuthorityDTO.getFile_id()).orElse(null);
        User user = userRepository.findById(addAuthorityDTO.getUser_id()).orElse(null);
        if(byId == null){
            return ResponseEntity.status(500).body(new BaseResponse("해당하는 파일이 존재하지 않습니다.", 500));
        }
        if(user == null){
            return ResponseEntity.status(500).body(new BaseResponse("해당하는 유저가 존재하지 않습니다.", 500));
        }
        File savedFile = fileRepository.save(addAuthorityDTO.toEntity());

        User_File user_file = new User_File();
        user_file.setUser(user);
        user_file.setFile(savedFile);
        user_file.setFileHash(addAuthorityDTO.getFileHash());
        user_file.setRole(addAuthorityDTO.getRole());
        userFileRepository.save(user_file);
        return ResponseEntity.status(200).body(new BaseResponse("권한 추가를 성공하였습니다.", 200));
    }

    @PatchMapping("/file/authority/edit")
    public ResponseEntity<? extends BaseResponse> editAuthority(@RequestBody EditAuthorityDTO editAuthorityDTO){
        // 1. 전달받은 file_hash와 user_id를 가지는 파일을 찾는다.
        // 2. 전달받은 Role로 해당 사용자의 Role을 수정한다.
        User_File user_file = userFileRepository.findByFileHashAndUser_Id(editAuthorityDTO.getFileHash(), editAuthorityDTO.getUser_id()).orElse(null);
        if(user_file == null){
            return ResponseEntity.status(500).body(new BaseResponse("유저가 해당 파일을 가지고 있지 않습니다.", 500));
        }
        File file = fileRepository.findById(user_file.getFile().getId()).orElse(null);
        if(file == null){
            return ResponseEntity.status(500).body(new BaseResponse("user_file 정보에는 있으나 file 정보에는 없습니다.", 500));
        }
        file.setRole(editAuthorityDTO.getRole());
        fileRepository.save(file);
        user_file.setRole(editAuthorityDTO.getRole());
        userFileRepository.save(user_file);
        return ResponseEntity.status(200).body(new BaseResponse("권한 수정을 성공하였습니다.",200));
    }

    @DeleteMapping("/file/authority/delete")
    public ResponseEntity<? extends BaseResponse> deleteAuthority(@RequestBody DeleteAuthorityDTO deleteAuthorityDTO){
        File byId = fileRepository.findById(deleteAuthorityDTO.getFile_id()).orElse(null);
        if(byId == null){
            return ResponseEntity.status(400).body(new BaseResponse("해당 id를 가진 파일이 존재하지 않습니다.", 400));
        }
        fileRepository.deleteById(byId.getId());
        userFileRepository.deleteByFile_Id(byId.getId());
        return ResponseEntity.status(200).body(new BaseResponse("파일 삭제를 성공하였습니다.", 200));
    }
}
