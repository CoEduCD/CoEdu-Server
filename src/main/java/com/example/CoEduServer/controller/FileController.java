package com.example.CoEduServer.controller;

import com.example.CoEduServer.domain.File;
import com.example.CoEduServer.domain.User;
import com.example.CoEduServer.domain.User_File;
import com.example.CoEduServer.domain.enums.Role;
import com.example.CoEduServer.dto.res.GetFilesDTO;
import com.example.CoEduServer.dto.req.FileCreateDTO;
import com.example.CoEduServer.dto.req.FileDeleteDTO;
import com.example.CoEduServer.dto.req.FileEditDTO;
import com.example.CoEduServer.dto.res.BaseResponse;
import com.example.CoEduServer.repository.FileRepository;
import com.example.CoEduServer.repository.UserFileRepository;
import com.example.CoEduServer.repository.UserRepository;
import com.example.CoEduServer.service.FileService;
import com.example.CoEduServer.service.UserService;
import jakarta.transaction.Transactional;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final FileRepository fileRepository;
    private final UserRepository userRepository;
    private final UserFileRepository userFileRepository;

    private final FileService fileService;
    private final UserService userService;
    @GetMapping("/file/{userId}")
    public ResponseEntity<List<GetFilesDTO>> getUserFiles(@PathVariable Long userId) {
        List<GetFilesDTO> dto = fileService.getFiles(userId);
        return ResponseEntity.ok().body(dto);
    }
    @Transactional
    @PostMapping("/file/create")
    public ResponseEntity<List<GetFilesDTO>> addFile(@RequestBody FileCreateDTO fileCreateDTO){
        User user = userService.isExistUserId(fileCreateDTO.getUserId());
        if(user == null){
            return ResponseEntity.status(500).body(null);
        }
        File file = fileService.saveFile(fileCreateDTO);
        fileService.saveUserFile(user, file);
        List<GetFilesDTO> dto = fileService.getFiles(fileCreateDTO.getUserId());
        return ResponseEntity.ok().body(dto);
    }
//
    @PatchMapping("/file/edit")
    public ResponseEntity<? extends BaseResponse> editFile(@RequestBody FileEditDTO fileEditDTO){
        // 1. user_file에 해당 file이 있는지 확인.
        // 2. user_id와 file_id로 해당
        User_File user_file = userFileRepository.findByUserIdAndFileId(fileEditDTO.getUserId(), fileEditDTO.getFileId()).orElse(null);
        if (user_file == null) {
            return ResponseEntity.status(400).body(new BaseResponse("해당 파일이 존재하지 않습니다.", 400));
        }
        if (user_file.getRole() != Role.ADMIN){
            return ResponseEntity.status(400).body(new BaseResponse("관리자가 아닙니다.", 400));
        }
        fileService.editFile(fileEditDTO, user_file.getFile());
        return ResponseEntity.status(200).body(new BaseResponse("파일 수정을 성공하였습니다.", 200));
    }

    @DeleteMapping("/file/delete")
    public ResponseEntity<? extends BaseResponse> deleteFile(@RequestBody FileDeleteDTO fileDeleteDTO) {
        User_File user_file = userFileRepository.findByUserIdAndFileId(fileDeleteDTO.getUserId(), fileDeleteDTO.getFileId()).orElse(null);
        if(user_file == null){
            return ResponseEntity.status(400).body(new BaseResponse("해당 id를 가진 파일이 존재하지 않습니다.", 400));
        }
        if(user_file.getRole() != Role.ADMIN){
            return ResponseEntity.status(400).body(new BaseResponse("관리자가 아닙니다.", 400));
        }
        fileService.deleteFile(fileDeleteDTO.getFileId());
        return ResponseEntity.status(200).body(new BaseResponse("파일 삭제를 성공하였습니다.", 200));
    }
}
