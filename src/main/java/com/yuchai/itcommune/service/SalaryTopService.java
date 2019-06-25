package com.yuchai.itcommune.service;

import com.yuchai.itcommune.entity.SalaryTop;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * VIEW 服务类
 * </p>
 *
 * @author Haven
 * @since 2019-06-25
 */
public interface SalaryTopService extends IService<SalaryTop> {
    List<SalaryTop> getSalaryTop(String startTime, String endTime);
}
