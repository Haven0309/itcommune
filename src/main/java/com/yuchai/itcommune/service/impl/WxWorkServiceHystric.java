package com.yuchai.itcommune.service.impl;

import com.yuchai.itcommune.service.WxWorkService;
import org.springframework.stereotype.Service;

/**
 * @author Haven
 * @create 2019-01-17 14:06
 */
@Service
public class WxWorkServiceHystric implements WxWorkService {
    @Override
    public String sendTextCardMsg(String toUser, String title, String description, String url, Integer agentId) {
        return "服务部不可用，Hystric返回";
    }
}
