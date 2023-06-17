package com.example.echatserve.Entity;

import lombok.Data;

import java.util.List;

@Data
public class AddFriendR {
    private Boolean code;
    private List<AddFriendEntity> friendList;
}
