package com.yuchai.itcommune.controller;

import com.yuchai.itcommune.annotation.Log;
import com.yuchai.itcommune.annotation.UserLoginToken;
import com.yuchai.itcommune.entity.User;
import com.yuchai.itcommune.util.JwtUtil;
import com.yuchai.itcommune.util.Result;
import com.yuchai.itcommune.util.ResultUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Haven
 * @create 2019-01-08 10:08
 */
@RestController
public class LoginController {
    @Log
    @GetMapping("/login")
    public Result login(@RequestParam String userName,@RequestParam String passWord) throws Exception {
        //验证用户密码
        Map<String,String> map = new HashMap<>();
        map.put("userCode",userName);
        return ResultUtil.genSuccessResult("token:"+JwtUtil.createToken(map));
    }

    /**
     * @param user @RequestAttribute("currentUser")获取拦截器中注入的当前用户
     * @return
     */
    @UserLoginToken
    @Log
    @GetMapping("/getMessage")
    public Result getMessage(@RequestAttribute("currentUser") User user){
        return ResultUtil.genSuccessResult(user);
    }
}
