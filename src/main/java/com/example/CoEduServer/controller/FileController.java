package com.example.CoEduServer.controller;

import com.example.CoEduServer.domain.File;
import com.example.CoEduServer.domain.User;
import com.example.CoEduServer.domain.enums.Role;
import com.example.CoEduServer.dto.FileDto;
import com.example.CoEduServer.dto.req.FileCreateDTO;
import com.example.CoEduServer.dto.req.FileDeleteDTO;
import com.example.CoEduServer.dto.req.FileEditDTO;
import com.example.CoEduServer.dto.res.BaseResponse;
import com.example.CoEduServer.repository.FileRepository;
import com.example.CoEduServer.repository.UserRepository;
import com.example.CoEduServer.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;
    private final FileRepository fileRepository;
    private final UserRepository userRepository;
    @GetMapping("/file/{userId}")
    public ResponseEntity<List<FileDto>> getUserFiles(@PathVariable Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            List<File> userFiles = user.getFileList();

            // User 엔티티의 파일 목록을 FileDto 목록으로 변환
            List<FileDto> fileDtoList = userFiles.stream()
                    .map(FileDto::of)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(fileDtoList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/file/create")
    public ResponseEntity<? extends BaseResponse> addFile(@RequestBody FileCreateDTO fileCreateDTO){
        Optional<User> userOptional = userRepository.findById(fileCreateDTO.getUser_id());
        if (userOptional.isPresent()){
            User user = userOptional.get();
            List<File> userFiles = user.getFileList();
            String newFileName = fileCreateDTO.getFile_name();
            if(fileService.isFileNameDuplicate(userFiles, newFileName)){
                newFileName = fileCreateDTO.getFile_name() + " (1)";
            };
            File file = new File();
            file.setFile_name(newFileName);
            file.setLanguage(fileCreateDTO.getLanguage());
            file.setFile_detail(fileCreateDTO.getFile_detail());
            file.setRole(Role.ADMIN);
            file.setUser(userRepository.findById(fileCreateDTO.getUser_id()).get());
            fileRepository.save(file);
            return ResponseEntity.status(200).body(new BaseResponse("파일 생성을 성공하였습니다.", 200));
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/file/edit")
    public ResponseEntity <? extends BaseResponse> editFile(@RequestBody FileEditDTO fileEditDTO){

        return ResponseEntity.status(200).body(new BaseResponse("파일 수정을 성공하였습니다.", 200));
    }

    @DeleteMapping("/file/delete")
    public ResponseEntity<? extends BaseResponse> deleteFile(@RequestBody FileDeleteDTO fileDeleteDTO) {
        Long fileId = fileDeleteDTO.getFile_id();
        Long userId = fileDeleteDTO.getUser_id();

        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            List<File> fileList = user.getFileList();

            // fileList에서 fileId에 해당하는 파일을 찾아 삭제
            fileList.removeIf(file -> file.getFile_id().equals(fileId));

            // 파일 삭제 후 User 엔티티를 저장 (변경사항을 데이터베이스에 반영)
            userRepository.save(user);

            return ResponseEntity.ok(new BaseResponse("파일 삭제를 성공하였습니다.", 200));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
