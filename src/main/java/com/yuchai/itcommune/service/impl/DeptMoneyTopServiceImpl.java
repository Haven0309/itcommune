package com.yuchai.itcommune.service.impl;

import com.yuchai.itcommune.entity.DeptMoneyTop;
import com.yuchai.itcommune.mapper.DeptMoneyTopMapper;
import com.yuchai.itcommune.service.DeptMoneyTopService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * VIEW 服务实现类
 * </p>
 *
 * @author Haven
 * @since 2019-06-25
 */
@Service
public class DeptMoneyTopServiceImpl extends ServiceImpl<DeptMoneyTopMapper, DeptMoneyTop> implements DeptMoneyTopService {
    @Autowired
    DeptMoneyTopMapper deptMoneyTopMapper;
    @Override
    public List<DeptMoneyTop> getDeptMoneyTop(String startTime, String endTime){
        return deptMoneyTopMapper.getDeptMoneyTop(startTime,endTime);
    }
}
