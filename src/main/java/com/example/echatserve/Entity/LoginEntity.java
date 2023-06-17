package com.example.echatserve.Entity;

import lombok.Data;

import java.util.List;

@Data
public class LoginEntity {
    private Boolean loginState;
    private String nickName;
    private List<FriendEntity> friendList;
    private String token;
    private String message;
}
