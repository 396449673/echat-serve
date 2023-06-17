package com.example.echatserve.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.echatserve.Entity.*;
import com.example.echatserve.Mapper.LoginTableMapper;
import com.example.echatserve.Service.FriendService;
import com.example.echatserve.Service.UserService;
import com.example.echatserve.Utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service("UserService")
public class UserImpl extends ServiceImpl<LoginTableMapper, LoginTableEntity> implements UserService {
    @Autowired
    FriendService friendService;

    @Override
    public LoginEntity regist(int userId, String password,String nickName) {
        LoginEntity loginEntity = new LoginEntity();
        LoginTableEntity loginTableEntity = new LoginTableEntity();
        loginTableEntity.setUserId(userId);
        loginTableEntity.setNickName(nickName);
        loginTableEntity.setPassword(password);
        loginTableEntity.setToken("");
        loginTableEntity.setIpAddress("");
        try{
            baseMapper.insert(loginTableEntity);
            loginEntity.setLoginState(true);
            loginEntity.setMessage("注册成功！");

        }catch (Exception e){
            loginEntity.setLoginState(false);
            loginEntity.setMessage("账号已存在！");
        }
        return loginEntity;
    }

    @Override
    public LoginEntity login(int userId, String password, String ipAddress) {
        LoginEntity loginEntity = new LoginEntity();
        LoginTableEntity loginTableEntity = baseMapper.selectById(userId);
        if(loginTableEntity == null){
            loginEntity.setLoginState(false);
            loginEntity.setNickName("");
            loginEntity.setFriendList(null);
            loginEntity.setToken("");
            loginEntity.setMessage("登陆失败，账号不存在！");
            return loginEntity;
        }
        if (loginTableEntity.getPassword().equals(password) ){
            if(loginTableEntity.getIpAddress()==null||loginTableEntity.getIpAddress().equals("")){
                loginEntity.setLoginState(true);
                loginEntity.setNickName(loginTableEntity.getNickName());
                String token =JwtUtils.createToken(Integer.toString(userId),ipAddress,loginTableEntity.getNickName(),password);
                loginTableEntity.setIpAddress(ipAddress);
                loginTableEntity.setToken(token);
                baseMapper.updateById(loginTableEntity);
                loginEntity.setToken(token);
                loginEntity.setFriendList(getFriendList(userId));
                loginEntity.setMessage("登陆成功");
            }
            else{
                loginEntity.setLoginState(false);
                loginEntity.setNickName("");
                loginEntity.setFriendList(null);
                loginEntity.setToken("");
                loginEntity.setMessage("账号已被登录，如果不是本人登录，请联系管理员（联系方式：wdesslq@163.com）");
            }

        }else{
            loginEntity.setLoginState(false);
            loginEntity.setNickName("");
            loginEntity.setFriendList(null);
            loginEntity.setToken("");
            loginEntity.setMessage("密码错误");
        }
        return loginEntity;
    }

    @Override
    public List<FriendEntity> getFriendList(int userId) {
        List<FriendTableEntity> friendTableEntityList = null;
        friendTableEntityList = friendService.getFriendList(userId);
        List<FriendEntity> friendEntityList = new LinkedList<FriendEntity>();
        if(friendTableEntityList == null){
            return null;
        }
        for(FriendTableEntity fTEL : friendTableEntityList){
            if (fTEL.getUserId1()==userId){
                FriendEntity friendEntityTemp = new FriendEntity();
                friendEntityTemp.setUserId(fTEL.getUserId2());
                LoginTableEntity loginTableEntityTemp = baseMapper.selectById(fTEL.getUserId2());
                friendEntityTemp.setNickName(loginTableEntityTemp.getNickName());
                friendEntityTemp.setIpAddress(loginTableEntityTemp.getIpAddress());
                friendEntityList.add(friendEntityTemp);
            }else{
                FriendEntity friendEntityTemp = new FriendEntity();
                friendEntityTemp.setUserId(fTEL.getUserId1());
                LoginTableEntity loginTableEntityTemp = baseMapper.selectById(fTEL.getUserId1());
                friendEntityTemp.setNickName(loginTableEntityTemp.getNickName());
                friendEntityTemp.setIpAddress(loginTableEntityTemp.getIpAddress());
                friendEntityList.add(friendEntityTemp);
            }
        }
        return friendEntityList;
    }

    @Override
    public LoginEntity checkUserId(int userId) {
        LoginTableEntity loginTableEntity = baseMapper.selectById(userId);
        LoginEntity loginEntity = new LoginEntity();
        if(loginTableEntity == null){
            loginEntity.setMessage("该用户不存在");
        }
        else{
            loginEntity.setNickName(loginTableEntity.getNickName());
            loginEntity.setMessage("查询成功");
        }
        return loginEntity;
    }

    @Override
    public Boolean logout(int userId) {
        LoginTableEntity loginTableEntity = baseMapper.selectById(userId);
        loginTableEntity.setToken("");
        loginTableEntity.setIpAddress("");
        try{
            baseMapper.updateById(loginTableEntity);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public RefreshFrientEntity refreshFriend(int userId) {
        RefreshFrientEntity refreshfrientEntity = new RefreshFrientEntity();
        refreshfrientEntity.setFriendList(getFriendList(userId));
        return refreshfrientEntity;
    }


}
