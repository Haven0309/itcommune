package com.yuchai.itcommune.mapper;

import com.yuchai.itcommune.entity.DeptMoneyTop;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * VIEW Mapper 接口
 * </p>
 *
 * @author Haven
 * @since 2019-06-25
 */
public interface DeptMoneyTopMapper extends BaseMapper<DeptMoneyTop> {
    List<DeptMoneyTop> getDeptMoneyTop(@Param("startTime") String startTime, @Param("endTime") String endTime);
}
