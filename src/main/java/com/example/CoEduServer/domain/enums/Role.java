package com.example.CoEduServer.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    VIEWER("ROLE_VIEWER", "관찰자"),
    EDITOR("ROLE_EDITOR", "수정자"),
    ADMIN("ROLE_ADMIN", "관리자");

    private final String key;
    private final String title;
}
