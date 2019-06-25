package com.yuchai.itcommune.mapper;

import com.yuchai.itcommune.entity.SalaryTop;
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
public interface SalaryTopMapper extends BaseMapper<SalaryTop> {
    List<SalaryTop> getSalaryTop(@Param("startTime") String startTime, @Param("endTime") String endTime);
}
