package com.moyang.zero.service.impl;

import com.moyang.zero.entity.MyApplication;
import com.moyang.zero.mapper.MyApplicationMapper;
import com.moyang.zero.service.IMyApplicationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 墨阳---应用信息信息表 服务实现类
 * </p>
 *
 * @author moyang
 * @since 2021-02-08
 */
@Service
public class MyApplicationServiceImpl extends ServiceImpl<MyApplicationMapper, MyApplication> implements IMyApplicationService {

}
