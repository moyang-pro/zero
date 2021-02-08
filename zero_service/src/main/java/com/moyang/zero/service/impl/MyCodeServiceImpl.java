package com.moyang.zero.service.impl;

import com.moyang.zero.entity.MyCode;
import com.moyang.zero.mapper.MyCodeMapper;
import com.moyang.zero.service.IMyCodeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 墨阳编码信息表 服务实现类
 * </p>
 *
 * @author moyang
 * @since 2021-02-08
 */
@Service
public class MyCodeServiceImpl extends ServiceImpl<MyCodeMapper, MyCode> implements IMyCodeService {

}
