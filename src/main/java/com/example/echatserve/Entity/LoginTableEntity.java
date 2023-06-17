package com.example.echatserve.Entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("loginTable")
public class LoginTableEntity {
    @TableId
    private int userId;
    private String password;
    private String nickName;
    private String ipAddress;
    private String token;
}
