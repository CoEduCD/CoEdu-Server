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
    public static class FileDto {
        private Long fileId;
        private String fileName;
        private String language;
        private String fileDetail;

        public static FileDto convert(File file) {
            return FileDto.builder()
                    .fileId(file.getId())
                    .fileName(file.getFile_name())
                    .language(file.getLanguage())
                    .fileDetail(file.getFile_detail())
                    .build();
        }

    }
    @GetMapping("/file/{userId}")
    public ResponseEntity<List<FileDto>> getUserFiles(@PathVariable Long userId) {
        List<User_File> userFiles = userFileRepository.findByUser_Id(userId);
        for (User_File userFile : userFiles) {
            System.out.println(userFile);
        }
        // 파일 ID 목록을 추출
        List<Long> fileIds = userFiles.stream()
                .map(userFile -> userFile.getFile().getId())
                .collect(Collectors.toList());

        // 파일 ID 목록을 사용하여 파일 정보를 가져옴
        List<File> files = fileRepository.findByIdIn(fileIds);
        return ResponseEntity.ok().body(files.stream().map(FileDto::convert).toList());
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
//    @DeleteMapping("/file/delete")
//    public ResponseEntity<? extends BaseResponse> deleteFile(@RequestBody FileDeleteDTO fileDeleteDTO) {
//        Long fileId = fileDeleteDTO.getFile_id();
//        Long userId = fileDeleteDTO.getUser_id();
//
//        Optional<User> userOptional = userRepository.findById(userId);
//
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            List<File> fileList = user.getFileList();
//
//            // fileList에서 fileId에 해당하는 파일을 찾아 삭제
//            fileList.removeIf(file -> file.getFile_id().equals(fileId));
//
//            // 파일 삭제 후 User 엔티티를 저장 (변경사항을 데이터베이스에 반영)
//            userRepository.save(user);
//
//            return ResponseEntity.ok(new BaseResponse("파일 삭제를 성공하였습니다.", 200));
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

}
