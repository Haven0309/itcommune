package com.yuchai.itcommune.service;

import com.yuchai.itcommune.entity.DeptMoneyTop;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yuchai.itcommune.entity.SalaryTop;

import java.util.List;

/**
 * <p>
 * VIEW 服务类
 * </p>
 *
 * @author Haven
 * @since 2019-06-25
 */
public interface DeptMoneyTopService extends IService<DeptMoneyTop> {
    List<DeptMoneyTop> getDeptMoneyTop(String startTime, String endTime);
}
