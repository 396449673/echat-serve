package com.example.echatserve.Controller;

import com.example.echatserve.Entity.AddFriendR;
import com.example.echatserve.Service.AddFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddFriendController {
    @Autowired
    AddFriendService addfriendService;
    @GetMapping("addFriend")
    public AddFriendR addFriend(int userId1, int userId2){
        AddFriendR addFriendR = new AddFriendR();
        addFriendR.setCode(addfriendService.addFriend(userId1,userId2));
        addFriendR.setFriendList(null);
        return addFriendR;
    }

    @GetMapping("getAddFriend")
    public AddFriendR getAddFriend(int userId){
        AddFriendR addFriendR = new AddFriendR();
        addFriendR.setCode(true);
        addFriendR.setFriendList(addfriendService.getAddFriendList(userId));
        return addFriendR;
    }

    @GetMapping("agreeAdd")
    public AddFriendR agreeAdd(int userId,int userId2,int select){
        AddFriendR addFriendR = new AddFriendR();
        addFriendR.setCode(addfriendService.agreeAdd(userId2,userId,select));
        addFriendR.setFriendList(null);
        return addFriendR;
    }

    @GetMapping("getAdd")
    public AddFriendR getAdd(int userId){
        AddFriendR addFriendR = new AddFriendR();
        addFriendR.setCode(true);
        addFriendR.setFriendList(addfriendService.getAddList(userId));
        return addFriendR;
    }


}
