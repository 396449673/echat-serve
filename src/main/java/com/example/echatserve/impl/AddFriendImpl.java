package com.example.echatserve.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.echatserve.Entity.AddFriendEntity;
import com.example.echatserve.Mapper.AddFriendMapper;
import com.example.echatserve.Service.AddFriendService;
import com.example.echatserve.Service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("AddFriendService")
public class AddFriendImpl extends ServiceImpl<AddFriendMapper, AddFriendEntity> implements AddFriendService {
    @Autowired
    FriendService friendService;
    @Override
    public List<AddFriendEntity> getAddFriendList(int userId) {
        QueryWrapper<AddFriendEntity> wrapper = new QueryWrapper<AddFriendEntity>();
        wrapper.eq("userId1",userId);
        List<AddFriendEntity> AddFriendEntityList =null;
        try{
            AddFriendEntityList = baseMapper.selectList(wrapper);
        } catch (Exception e){
            return null;
        }
        for(AddFriendEntity AFE:AddFriendEntityList){
            if(AFE.getIsAdd()==1){
                if(friendService.agreeAdd(AFE.getUserId2(),AFE.getUserId1())){
                    QueryWrapper<AddFriendEntity> wrapper1 = new QueryWrapper<AddFriendEntity>();
                    wrapper1.eq("userId1",AFE.getUserId1()).eq("userId2",AFE.getUserId2()).eq("isAdd",1);
                    try{
                        baseMapper.delete(wrapper1);
                    } catch (Exception e){

                    }
                }
            }
            else if(AFE.getIsDiss()==1){
                QueryWrapper<AddFriendEntity> wrapper1 = new QueryWrapper<AddFriendEntity>();
                wrapper1.eq("userId1",AFE.getUserId1()).eq("userId2",AFE.getUserId2()).eq("isDiss",1);
                try{
                    baseMapper.delete(wrapper1);
                } catch (Exception e){

                }
            }
        }
        return AddFriendEntityList;
    }

    @Override
    public Boolean addFriend(int userId,int userId2) {
        AddFriendEntity addfriendentity= new AddFriendEntity();
        addfriendentity.setIsAdd(0);
        addfriendentity.setIsDiss(0);
        addfriendentity.setUserId1(userId);
        addfriendentity.setUserId2(userId2);
        try{
            baseMapper.insert(addfriendentity);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public List<AddFriendEntity> getAddList(int userId) {
        QueryWrapper<AddFriendEntity> wrapper = new QueryWrapper<AddFriendEntity>();
        wrapper.eq("userId2",userId);
        List<AddFriendEntity> AddFriendEntityList =null;
        try{
            AddFriendEntityList = baseMapper.selectList(wrapper);
        } catch (Exception e){
            return null;
        }
        return AddFriendEntityList;
    }

    @Override
    public Boolean agreeAdd(int userId, int userId2,int select) {
        QueryWrapper<AddFriendEntity> wrapper = new QueryWrapper<AddFriendEntity>();
        wrapper.eq("userId1",userId).eq("userId2",userId2);
        AddFriendEntity addfriendentity= new AddFriendEntity();
        if (select == 0){
            addfriendentity.setIsDiss(1);
            addfriendentity.setIsAdd(0);
        }
        else{
            addfriendentity.setIsAdd(1);
            addfriendentity.setIsDiss(0);
        }

        addfriendentity.setUserId1(userId);
        addfriendentity.setUserId2(userId2);
        try{

            baseMapper.update(addfriendentity,wrapper);
        } catch (Exception e){

            return false;
        }
        return true;
    }

}
