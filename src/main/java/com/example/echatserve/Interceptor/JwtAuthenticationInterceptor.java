package com.example.echatserve.Interceptor;

import com.example.echatserve.Entity.LoginTableEntity;
import com.example.echatserve.Service.UserService;
import com.example.echatserve.Utils.JwtUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationInterceptor  implements HandlerInterceptor {
    private UserService userService;

    public JwtAuthenticationInterceptor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        int userId = Integer.parseInt(JwtUtils.getAudience(token));
        LoginTableEntity loginTableEntity = userService.getBaseMapper().selectById(userId);
        JwtUtils.verifyToken(token,userId+loginTableEntity.getPassword());
        if (token.equals(loginTableEntity.getToken())){
            return true;
        }
        return false;
    }
}

