package com.demo.lyr.mp.service.impl;

import com.demo.lyr.mp.entity.User;
import com.demo.lyr.mp.mapper.UserMapper;
import com.demo.lyr.mp.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lyr
 * @since 2023-01-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
