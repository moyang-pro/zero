package com.moyang.zero.service.impl;

import com.moyang.zero.entity.BlogUser;
import com.moyang.zero.mapper.BlogUserMapper;
import com.moyang.zero.service.IBlogUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 博客系统用户表 服务实现类
 * </p>
 *
 * @author moyang
 * @since 2022-02-28
 */
@Service
public class BlogUserServiceImpl extends ServiceImpl<BlogUserMapper, BlogUser> implements IBlogUserService {

}
