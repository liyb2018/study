package com.jackliang.bddemo.provider;

import com.jackliang.bddemo.provider.Interupt.CatMybatisPlugin;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;

/**
 * @author liyb 2018/5/24
 */
@Configuration
public class InterruptConfig extends WebMvcConfigurerAdapter {

    //注册mybatis拦截器
    @Bean
    public CatMybatisPlugin getCatMybatisPlugin(){
        System.out.println("1111");
        return new CatMybatisPlugin();
    }

    @Bean
    @Autowired
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        System.out.println("2222222");
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);

        sqlSessionFactoryBean.setPlugins(new Interceptor[]{getCatMybatisPlugin()});
        return  sqlSessionFactoryBean.getObject();
    }
}
