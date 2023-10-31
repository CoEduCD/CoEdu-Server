//package com.example.CoEduServer.dto;
//
//import com.example.CoEduServer.domain.File;
//import com.example.CoEduServer.domain.User;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.ToString;
//
//import java.util.List;
//
//@AllArgsConstructor
//@Builder
//@ToString
//public class UserDto {
//    private Long user_id;
//    private String name;
//    private String email;
//    private List<File> fileList;
//
//    public static UserDto of(User user){
//        return UserDto.builder()
//                .user_id(user.getUser_id())
//                .name(user.getName())
//                .email(user.getEmail())
//                .fileList(user.getFileList())
//                .build();
//    }
//}
