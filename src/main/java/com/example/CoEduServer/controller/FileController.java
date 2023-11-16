package com.example.CoEduServer.controller;

import com.example.CoEduServer.domain.File;
import com.example.CoEduServer.domain.User;
import com.example.CoEduServer.domain.User_File;
import com.example.CoEduServer.dto.res.GetFilesDTO;
import com.example.CoEduServer.dto.req.FileCreateDTO;
import com.example.CoEduServer.dto.req.FileDeleteDTO;
import com.example.CoEduServer.dto.req.FileEditDTO;
import com.example.CoEduServer.dto.res.BaseResponse;
import com.example.CoEduServer.repository.FileRepository;
import com.example.CoEduServer.repository.UserFileRepository;
import com.example.CoEduServer.repository.UserRepository;
import com.example.CoEduServer.service.FileService;
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


    @GetMapping("/file/{userId}")
    public ResponseEntity<List<GetFilesDTO>> getUserFiles(@PathVariable Long userId) {
        List<User_File> userFiles = userFileRepository.findByUser_Id(userId);
        List<GetFilesDTO> dto = new ArrayList<>();
        for(User_File user_file : userFiles) {
            dto.add(GetFilesDTO.toEntity(user_file));
        }
        return ResponseEntity.ok().body(dto);
    }
    @PostMapping("/file/create")
    public ResponseEntity<? extends BaseResponse> addFile(@RequestBody FileCreateDTO fileCreateDTO){
        // file을 생성할때 해당 사용자의 id와 파일 정보들을 전달받음.
        // 전달받은 파일 정보들을 저장하는데,
        // 이걸 user_file에도 user_id와 file_id로 같이 저장해주면됨.
        // 해당 유저의 정보를 찾음
        User user = userRepository.findById(fileCreateDTO.getUser_id()).orElse(null);
        if(user == null){
            return ResponseEntity.status(500).body(new BaseResponse("해당 유저가 존재하지 않습니다.",500));
        }
        // 해당 유저의 정보와 파일 정보를 user_file에 저장
        File file = fileCreateDTO.toEntity();
        fileRepository.save(file);
        User_File userFile = new User_File();
        userFile.setUser(user);
        userFile.setFile(file);

        userFileRepository.save(userFile);
        return ResponseEntity.status(200).body(new BaseResponse("파일 생성 성공", 200));
    }
//
    @PatchMapping("/file/edit")
    public ResponseEntity<File> editFile(@RequestBody FileEditDTO fileEditDTO){
        // patch시 file
        Long fileId = fileEditDTO.getFile_id();
        File file = fileRepository.findById(fileId).orElse(null);
        if (file == null) {
            return ResponseEntity.status(400).body(null);
        }
        file.setFileDetail(fileEditDTO.getFileDetail());
        file.setFileName(fileEditDTO.getFileName());
        file.setLanguage(fileEditDTO.getLanguage());
        File updatedFile = fileRepository.save(file);
        return ResponseEntity.status(200).body(updatedFile);
    }

    @DeleteMapping("/file/delete")
    public ResponseEntity<? extends BaseResponse> deleteFile(@RequestBody FileDeleteDTO fileDeleteDTO) {
        File byId = fileRepository.findById(fileDeleteDTO.getFile_id()).orElse(null);
        if(byId == null){
            return ResponseEntity.status(400).body(new BaseResponse("해당 id를 가진 파일이 존재하지 않습니다.", 400));
        }
        fileRepository.deleteById(byId.getId());
        userFileRepository.deleteByFile_Id(byId.getId());
        return ResponseEntity.status(200).body(new BaseResponse("파일 삭제를 성공하였습니다.", 200));
    }
}
