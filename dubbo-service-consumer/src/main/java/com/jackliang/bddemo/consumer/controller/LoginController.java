package com.jackliang.bddemo.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jackliang.bddemo.api.service.IUserService;
import com.jackliang.bddemo.api.service.request.LoginRequest;
import com.jackliang.bddemo.api.service.response.LoginResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liangck
 * @version 1.0
 * @since 16/4/28 14:05
 */
@RestController
public class LoginController {

    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Reference(version = "1.0.0")
    private IUserService userService;

    @Autowired
    private CloseableHttpClient httpClient;

    /**
     * login
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/login")
    public ResponseEntity<LoginResponse> login(String username, String password) {
        return ResponseEntity.ok(userService.login(new LoginRequest(username, password)));
    }

    /**
     * login
     * @return
     */
    @RequestMapping(value = "/test")
    public String test() throws Exception{
        Thread.sleep(100);
        logger.info("进入controller--test");
//        try{
//            double a= 10/0;
//        }catch (Exception e){
//            logger.error("异常", e);
//        }

        HttpGet get = new HttpGet("http://localhost:8082/login?username=liangck&password=liangck");
        CloseableHttpResponse response = httpClient.execute(get);
        return response.getEntity().toString();

    }

    @RequestMapping(value = "/test2")
    public String test2() throws Exception{
        Thread.sleep(100);

        HttpGet get = new HttpGet("http://localhost:8082/test3");
        CloseableHttpResponse response = httpClient.execute(get);
        return response.getEntity().toString();

    }

    @RequestMapping(value = "/test3")
    public String test3() throws Exception{
        Thread.sleep(100);

        HttpGet get = new HttpGet("http://localhost:8082/test4");
        CloseableHttpResponse response = httpClient.execute(get);
        return response.getEntity().toString();

    }

    @RequestMapping(value = "/test4")
    public String test4() throws Exception{
        Thread.sleep(100);

        return "test4";

    }
}
