package com.jackliang.bddemo.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jackliang.bddemo.api.service.IUserService;
import com.jackliang.bddemo.api.service.request.LoginRequest;
import com.jackliang.bddemo.api.service.response.LoginResponse;
import com.jackliang.bddemo.provider.domain.User;
import com.jackliang.bddemo.provider.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author liangck
 * @version 1.0
 * @since 16/4/26 17:21
 */
@Service(version = "1.0.0",interfaceClass = com.jackliang.bddemo.api.service.IUserService.class)
public class UserServiceImpl implements IUserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    /**
     * login
     *
     * @param request
     * @return
     */
    @Override
    public LoginResponse login(LoginRequest request) {
        // TODO: 16/4/26 login...
        LOGGER.info("login with {}:{}", request.getUsername(), request.getPassword());
        userMapper.insert("admin", "123456", "12345678911");
        User u = userMapper.findUserByPhone("12345678910");

        if (request.getUsername()!=null && u.getName().equals(request.getUsername()))
            return LoginResponse.SUCCESS();
        return LoginResponse.FAIL("用户名不正确");
    }

}
