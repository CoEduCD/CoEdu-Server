package com.example.CoEduServer.domain;

import com.example.CoEduServer.domain.enums.Role;
import com.example.CoEduServer.dto.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long user_id;

    @Column
    private String name;
    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @OneToMany(mappedBy = "user")
    private final List<File> fileList = new ArrayList<>();
}
