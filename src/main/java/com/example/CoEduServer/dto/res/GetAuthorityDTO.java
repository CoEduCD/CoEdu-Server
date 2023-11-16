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

    public static GetAuthorityDTO toEntity(User_File user_file){
        return GetAuthorityDTO.builder()
                .userId(user_file.getUser().getId())
                .role(user_file.getRole())
                .build();
    }
}
