package com.yuchai.itcommune.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuchai.itcommune.entity.OaEmpInfoV;
import com.yuchai.itcommune.entity.Tag;
import com.yuchai.itcommune.entity.User;
import com.yuchai.itcommune.service.OaEmpInfoVService;
import com.yuchai.itcommune.service.TagService;
import com.yuchai.itcommune.service.UserService;
import com.yuchai.itcommune.util.Result;
import com.yuchai.itcommune.util.ResultUtil;
import com.yuchai.itcommune.vo.UserVO;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author Haven
 * @since 2018-09-14
 */
@Api(tags = "1.5", description = "用户管理", value = "用户信息管理")
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private TagService tagService;
    @Autowired
    private OaEmpInfoVService oaEmpInfoVService;

    /**
     * 更新用户信息
     * @param userVO
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody UserVO userVO){
        User user = new User();
        BeanUtils.copyProperties(userVO,user);
        OaEmpInfoV employee = oaEmpInfoVService.getOne(new QueryWrapper<OaEmpInfoV>().eq("employee_code", user.getUserCode()));
//        user.setDepartment(employee.getGsFullName());
        user.setPhone(employee.getMobile());
        userService.saveOrUpdate(user);
        List<Tag> tags = userVO.getTags();
        for (Tag tag:tags) {
            tagService.saveOrUpdate(tag);
        }
        return ResultUtil.genSuccessResult(user);
    }
    /**
     * 获取用户信息
     * @param userCode
     * @return
     */
    @GetMapping("/get/{userCode}")
    public Result get(@PathVariable String userCode){
        User user = userService.getOne(new QueryWrapper<User>().eq("user_code", userCode));
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user,userVO);
//        List<TagVO> tagsVO = new ArrayList<>();
        List<Tag> tags = tagService.list(new QueryWrapper<Tag>().eq("user_code", userCode));
//        for (Tag tag : tags) {
//            TagVO tagVO = new TagVO();
//            BeanUtils.copyProperties(tag,tagVO);
//            tagsVO.add(tagVO);
//        }
        userVO.setTags(tags);
        return ResultUtil.genSuccessResult(userVO);
    }

    @GetMapping("/getAllUsers")
    public Result getAllUsers(){
        return ResultUtil.genSuccessResult(userService.list(new QueryWrapper<User>().ne("user_code","admin")));
    }

}

