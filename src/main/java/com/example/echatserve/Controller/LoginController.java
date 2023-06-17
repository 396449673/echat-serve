package com.example.echatserve.Controller;

import com.example.echatserve.Entity.LoginEntity;
import com.example.echatserve.Entity.RefreshFrientEntity;
import com.example.echatserve.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class LoginController {
    @Autowired
    UserService userService;
    @GetMapping("login")
    public LoginEntity login(int userId,String password,String ipAddress){
        return userService.login(userId, password, ipAddress);
    }

    @GetMapping("checkUserId")
    public LoginEntity checkUserId(int userId){
        return userService.checkUserId(userId);
    }

    @GetMapping("logout")
    public HashMap<String,Boolean> logout(int userId){
        HashMap<String,Boolean> hashMap = new HashMap<String,Boolean>();
        hashMap.put("state",userService.logout(userId));
        return hashMap;
    }

    @GetMapping("refreshFriend")
    public RefreshFrientEntity refreshFriend(int userId){
        return userService.refreshFriend(userId);
    }


}
