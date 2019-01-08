package com.yuchai.itcommune.controller;

import com.yuchai.itcommune.annotation.UserLoginToken;
import com.yuchai.itcommune.util.JwtUtil;
import com.yuchai.itcommune.util.Result;
import com.yuchai.itcommune.util.ResultUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Haven
 * @create 2019-01-08 10:08
 */
@RestController
public class LoginController {
    @GetMapping("/login")
    public Result login(String userName, String passWord) throws Exception {
        //验证用户密码
        Map<String,String> map = new HashMap<>();
        map.put("userCode",userName);
        return ResultUtil.genSuccessResult("token:"+JwtUtil.createToken(map));
    }

    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage(){
        return "你已通过验证";
    }
}
