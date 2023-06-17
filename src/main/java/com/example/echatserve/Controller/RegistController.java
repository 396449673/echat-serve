package com.example.echatserve.Controller;

import com.example.echatserve.Entity.LoginEntity;
import com.example.echatserve.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistController {
    @Autowired
    UserService userService;

    @GetMapping("regist")
    public LoginEntity regist(int userId, String password, String nickName){
        return userService.regist(userId,password,nickName);
    }
}
