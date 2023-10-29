package com.example.CoEduServer.dto.res;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRes extends BaseResponse{
    Long user_id;
    public LoginRes(String msg, Integer status, Long user_id){
        super(msg,status);
        this.user_id = user_id;
    }
}
