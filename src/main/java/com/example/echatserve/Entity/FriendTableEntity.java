package com.example.echatserve.Entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("friendTable")
public class FriendTableEntity {
    @TableField
    private int userId1;
    @TableField
    private int userId2;
}
