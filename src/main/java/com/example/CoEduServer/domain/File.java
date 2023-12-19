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
    @Column(name = "fileId")
    private Long id;

    @Column
    private String fileName;

    @Column
    private String language;

    @Column(length = 65535)
    private String fileDetail;

    @Column(nullable = false)
    private String fileHash;

    @OneToMany(mappedBy = "file")
    private List<User_File> userFiles = new ArrayList<>();

}
