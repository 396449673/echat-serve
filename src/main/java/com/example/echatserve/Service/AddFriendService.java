package com.example.echatserve.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.echatserve.Entity.AddFriendEntity;

import java.util.List;

public interface AddFriendService extends IService<AddFriendEntity> {
    public List<AddFriendEntity> getAddFriendList(int userId);
    public Boolean addFriend(int userId,int userId2);
    public List<AddFriendEntity> getAddList(int userId);
    public Boolean agreeAdd(int userId,int userId2,int select);
}
