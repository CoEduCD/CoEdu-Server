package com.example.CoEduServer.controller;

import com.example.CoEduServer.domain.File;
import com.example.CoEduServer.dto.req.FileCreateDTO;
import com.example.CoEduServer.dto.res.BaseResponse;
import com.example.CoEduServer.repository.FileRepository;
import com.example.CoEduServer.repository.UserRepository;
import com.example.CoEduServer.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;
    private final FileRepository fileRepository;
    private final UserRepository userRepository;
    @PostMapping("/main/file/add")
    public ResponseEntity<? extends BaseResponse> addFile(@RequestBody FileCreateDTO fileCreateDTO){
        File file = new File();
        file.setFile_name(fileCreateDTO.getFile_name());
        file.setLanguage(fileCreateDTO.getLanguage());
        file.setFile_detail(fileCreateDTO.getFile_detail());
        file.setUser(userRepository.findById(fileCreateDTO.getUserId()).get());
        fileRepository.save(file);
        return ResponseEntity.status(200).body(new BaseResponse("파일 생성을 성공하였습니다.", 200));
    }
}
