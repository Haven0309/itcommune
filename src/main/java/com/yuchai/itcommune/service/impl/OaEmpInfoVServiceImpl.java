package com.yuchai.itcommune.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.yuchai.itcommune.entity.OaEmpInfoV;
import com.yuchai.itcommune.mapper.OaEmpInfoVMapper;
import com.yuchai.itcommune.service.OaEmpInfoVService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Haven
 * @since 2018-12-06
 */
@Service
@DS("oracle")
public class OaEmpInfoVServiceImpl extends ServiceImpl<OaEmpInfoVMapper, OaEmpInfoV> implements OaEmpInfoVService {

}
