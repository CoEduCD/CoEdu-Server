package com.example.CoEduServer.domain;

import com.example.CoEduServer.domain.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User_File {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_file_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    private File file;

    private String fileHash;
    private Role role;
}
