package com.example.echatserve.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.echatserve.Entity.FriendTableEntity;
import com.example.echatserve.Mapper.FriendTableMapper;
import com.example.echatserve.Service.FriendService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("FriendService")
public class FriendImpl extends ServiceImpl<FriendTableMapper, FriendTableEntity> implements FriendService {
    @Override
    public List<FriendTableEntity> getFriendList(int userId) {
        QueryWrapper<FriendTableEntity> wrapper = new QueryWrapper<FriendTableEntity>();
        wrapper.eq("userId1",userId).or().eq("userId2",userId);
        List<FriendTableEntity> friendTableEntityList =null;
        try{
            friendTableEntityList = baseMapper.selectList(wrapper);
        } catch (Exception e){
            return null;
        }
        return friendTableEntityList;
    }

    @Override
    public Boolean agreeAdd(int userId2, int userId) {
        FriendTableEntity friendTableEntity = new FriendTableEntity();
        friendTableEntity.setUserId1(userId);
        friendTableEntity.setUserId2(userId2);
        try{
            baseMapper.insert(friendTableEntity);
        } catch (Exception e){
            return false;
        }
        return true;
    }
}
