package com.yuchai.itcommune.service.impl;

import com.yuchai.itcommune.entity.SalaryTop;
import com.yuchai.itcommune.mapper.SalaryTopMapper;
import com.yuchai.itcommune.service.SalaryTopService;
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
public class SalaryTopServiceImpl extends ServiceImpl<SalaryTopMapper, SalaryTop> implements SalaryTopService {
    @Autowired
    SalaryTopMapper salaryTopMapper;
    @Override
    public List<SalaryTop> getSalaryTop(String startTime, String endTime){
        return salaryTopMapper.getSalaryTop(startTime,endTime);
    }
}
