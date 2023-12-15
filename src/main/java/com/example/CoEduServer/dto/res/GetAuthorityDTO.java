package com.example.CoEduServer.dto.res;

import com.example.CoEduServer.domain.User_File;
import com.example.CoEduServer.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAuthorityDTO {
    private Long userId;
    private Role role;
    private String email;
    private String name;
    public static GetAuthorityDTO toEntity(User_File user_file){
        return GetAuthorityDTO.builder()
                .userId(user_file.getUser().getId())
                .email(user_file.getUser().getEmail())
                .name(user_file.getUser().getName())
                .role(user_file.getRole())
                .build();
    }
}
