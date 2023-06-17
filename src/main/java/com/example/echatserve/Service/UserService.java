package com.example.echatserve.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.echatserve.Entity.*;

import java.util.List;

public interface UserService extends IService<LoginTableEntity> {
    public LoginEntity regist(int userId,String password,String nickName);
    public LoginEntity login(int userId,String password,String ipAddress);
    public List<FriendEntity> getFriendList(int userId);
    public LoginEntity checkUserId(int userId);
    public Boolean logout(int userId);
    public RefreshFrientEntity refreshFriend(int userId);
}
