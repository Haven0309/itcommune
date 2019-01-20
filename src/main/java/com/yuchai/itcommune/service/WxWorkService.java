package com.yuchai.itcommune.service;

import com.yuchai.itcommune.service.impl.WxWorkServiceHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Haven
 * @create 2019-01-17 9:46
 */
@FeignClient(value = "it-wx-basic-service",fallback = WxWorkServiceHystric.class)
public interface WxWorkService {

    @RequestMapping(value = "/wxwork/{agentId}/sendTextCardMsg",method = RequestMethod.POST)
    String sendTextCardMsg(@RequestParam(value = "toUser") String toUser,
                           @RequestParam(value = "title") String title,
                           @RequestParam(value = "description") String description,
                           @RequestParam(value = "url") String url,
                           @PathVariable(value = "agentId") Integer agentId);

}
