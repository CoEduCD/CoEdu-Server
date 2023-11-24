package com.example.CoEduServer.domain;

import com.example.CoEduServer.domain.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User_File {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userFileId")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fileId")
    private File file;

    private Role role;

    public String getRoleKey(){
        return this.role.getKey();
    }
}
