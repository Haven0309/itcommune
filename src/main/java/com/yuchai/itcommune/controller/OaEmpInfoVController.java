package com.yuchai.itcommune.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.battcn.swagger.properties.ApiDataType;
import com.battcn.swagger.properties.ApiParamType;
import com.yuchai.itcommune.entity.OaEmpInfoV;
import com.yuchai.itcommune.service.OaEmpInfoVService;
import com.yuchai.itcommune.util.Result;
import com.yuchai.itcommune.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Haven
 * @since 2018-10-25
 */
@CrossOrigin
@RestController
@RequestMapping("/oaEmpInfoV")
@Api(tags = "1.2", description = "玉柴云用户管理", value = "玉柴云用户管理")
public class OaEmpInfoVController {
    @Autowired
    private OaEmpInfoVService oaEmpInfoVService;


    @GetMapping("/search/{name}")
    @ApiOperation(value = "搜索用户（DONE）")
    @ApiImplicitParam(name = "name", value = "用户名及工号", dataType = ApiDataType.STRING, paramType = ApiParamType.PATH)
    public Result search(@PathVariable String name){
        List<OaEmpInfoV> list = oaEmpInfoVService.list(new QueryWrapper<OaEmpInfoV>().like("employee_name", name).or().like("employee_code", name));
        if (list.size() > 0) {
            return ResultUtil.genSuccessResult(list);
        }
        return ResultUtil.genFailResult("无数据");
    }


}

