package com.example.CoEduServer.domain;

import com.example.CoEduServer.domain.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class File extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long id;

    @Column
    private String file_name;

    @Column
    private String language;

    @Column
    private String file_detail;

    @Enumerated(EnumType.STRING)
    @Column
    private Role role;

    @Column(nullable = false)
    private String file_hash;

    @OneToMany(mappedBy = "file")
    private List<User_File> userFiles = new ArrayList<>();

    public String getRoleKey(){
        return this.role.getKey();
    }

}
