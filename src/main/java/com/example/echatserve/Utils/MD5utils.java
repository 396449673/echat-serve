package com.example.echatserve.Utils;

import org.springframework.util.DigestUtils;

public class MD5utils {
    public static String MD5(String password){
        return  DigestUtils.md5DigestAsHex(password.getBytes());
    }
}
