package com.example.echatserve.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.echatserve.Entity.FriendTableEntity;

import java.util.List;

public interface FriendService extends IService<FriendTableEntity> {
    public List<FriendTableEntity> getFriendList(int userId);
    public Boolean agreeAdd(int userId2,int userId);
}
