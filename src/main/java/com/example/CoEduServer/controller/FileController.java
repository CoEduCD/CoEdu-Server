package com.example.CoEduServer.controller;

import com.example.CoEduServer.domain.File;
import com.example.CoEduServer.domain.User;
import com.example.CoEduServer.domain.User_File;
import com.example.CoEduServer.domain.enums.Role;
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
import org.yaml.snakeyaml.representer.BaseRepresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;
    private final FileRepository fileRepository;
    private final UserRepository userRepository;
    private final UserFileRepository userFileRepository;

    @Data
    @Builder
    @AllArgsConstructor @NoArgsConstructor
    public static class User_FileDto {
        private Long fileId;
        private String fileName;
        private String language;
        private String fileDetail;

        public static User_FileDto of(User_File user_file) {
            return User_FileDto.builder()
                    .fileId(user_file.getFile().getId())
                    .fileName(user_file.getFile().getFile_name())
                    .language(user_file.getFile().getLanguage())
                    .fileDetail(user_file.getFile().getFile_detail())
                    .build();
        }

    }
    @GetMapping("/file/{userId}")
    public ResponseEntity<List<User_FileDto>> getUserFiles(@PathVariable Long userId) {
        List<User_File> userFiles = userFileRepository.findByUser_Id(userId);
        List<User_FileDto> dto = new ArrayList<>();
        for(User_File user_file : userFiles) {
            dto.add(User_FileDto.of(user_file));
        }

        return ResponseEntity.ok().body(dto);

    }
    @PostMapping("/file/create")
    public ResponseEntity<? extends BaseResponse> addFile(@RequestBody FileCreateDTO fileCreateDTO){
        // file을 생성할때 해당 사용자의 id와 파일 정보들을 전달받음.
        // 전달받은 파일 정보들을 저장하는데,
        // 이걸 user_file에도 user_id와 file_id로 같이 저장해주면됨.
        File file = fileCreateDTO.toEntity();
        fileRepository.save(file);
        // 해당 유저의 정보를 찾음
        User user = userRepository.findById(fileCreateDTO.getUser_id()).orElse(null);
        // 해당 유저의 정보와 파일 정보를 user_file에 저장
        User_File userFile = new User_File();
        userFile.setUser(user);
        userFile.setFile(file);
        userFileRepository.save(userFile);
        return ResponseEntity.status(200).body(new BaseResponse("파일 생성 성공", 200));
    }
//
//    @PatchMapping("/file/edit")
//    public ResponseEntity <? extends BaseResponse> editFile(@RequestBody FileEditDTO fileEditDTO){
//
//        return ResponseEntity.status(200).body(new BaseResponse("파일 수정을 성공하였습니다.", 200));
//    }
//
    @DeleteMapping("/file/{file_id}")
    public ResponseEntity<? extends BaseResponse> deleteFile(@RequestBody FileDeleteDTO fileDeleteDTO) {
        Long fileId = fileDeleteDTO.getFile_id();
        if(fileId == null){
            return ResponseEntity.status(400).body(new BaseResponse("해당 id를 가진 파일이 존재하지 않습니다.", 400));
        }
        fileRepository.deleteById(fileId);
        return ResponseEntity.status(200).body(new BaseResponse("파일 삭제를 성공하였습니다.", 200));
    }
}
