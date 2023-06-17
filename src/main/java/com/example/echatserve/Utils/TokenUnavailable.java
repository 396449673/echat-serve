package com.example.echatserve.Utils;

public class TokenUnavailable extends Exception {
    public TokenUnavailable() {
        super("Token验证失败！");
    }
}