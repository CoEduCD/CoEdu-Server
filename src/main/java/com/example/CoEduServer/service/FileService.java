package com.example.CoEduServer.service;

import com.example.CoEduServer.domain.File;
import com.example.CoEduServer.domain.User;
import com.example.CoEduServer.domain.User_File;
import com.example.CoEduServer.domain.enums.Role;
import com.example.CoEduServer.dto.req.FileCreateDTO;
import com.example.CoEduServer.dto.req.FileDeleteDTO;
import com.example.CoEduServer.dto.req.FileEditDTO;
import com.example.CoEduServer.dto.res.GetFilesDTO;
import com.example.CoEduServer.repository.FileRepository;
import com.example.CoEduServer.repository.UserFileRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;
    private final UserFileRepository userFileRepository;
    public boolean isFileNameDuplicate(List<File> userFiles, String fileName){
        return userFiles.stream().anyMatch(file -> file.getFileName().equals(fileName));
    }

    public List<GetFilesDTO> getFiles(Long userId){
        List<User_File> userFiles = userFileRepository.findByUserId(userId);
        List<GetFilesDTO> dto = new ArrayList<>();
        for(User_File user_file : userFiles){
            dto.add(GetFilesDTO.toEntity(user_file));
        }
        return dto;
    }

    public File saveFile(FileCreateDTO fileCreateDTO){
        File file = fileCreateDTO.toEntity();
        fileRepository.save(file);
        return file;
    }
    public void saveUserFile(User user, File file){
        User_File userFile = new User_File();
        userFile.setUser(user);
        userFile.setFile(file);
        userFile.setRole(Role.ADMIN);
        userFileRepository.save(userFile);
    }

    public void editFile(FileEditDTO fileEditDTO, File file){
        file.setFileName(fileEditDTO.getFileName());
        file.setFileDetail(fileEditDTO.getFileDetail());
        file.setLanguage(fileEditDTO.getLanguage());
        fileRepository.save(file);
    }
    @Transactional
    public void deleteFile(Long fileId){
        List<User_File> user_files = userFileRepository.findByFileId(fileId);

        // 특정 fileId에 해당하는 모든 User_File 엔티티 삭제
        for (User_File userFile : user_files) {
            userFileRepository.delete(userFile);
        }

        // 파일 엔티티 삭제
        fileRepository.deleteById(fileId);
    }
}

