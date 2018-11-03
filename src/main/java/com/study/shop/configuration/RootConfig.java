package com.study.shop.configuration;

import com.study.shop.dao.ProductDao;
import com.study.shop.dao.UserDao;
import com.study.shop.dao.jdbc.JdbcProductDao;
import com.study.shop.dao.jdbc.JdbcUserDao;
import com.study.shop.security.SecurityService;
import com.study.shop.service.DefaultProductService;
import com.study.shop.service.DefaultUserService;
import com.study.shop.service.ProductService;
import com.study.shop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RootConfig {
    private static final Logger logger = LoggerFactory.getLogger(RootConfig.class);

    @Bean
    ProductService productService() {
        logger.debug("initialization of productService");
        DefaultProductService defaultProductService = new DefaultProductService();
        defaultProductService.setProductDao(productDao());
        return defaultProductService;
    }

    @Bean
    SecurityService securityService() {
        logger.debug("initialization of securityService");
        SecurityService securityService = new SecurityService();
        securityService.setUserService(userService());
        return securityService;
    }

    @Bean
    UserService userService() {
        logger.debug("initialization of userService");
        return new DefaultUserService(userDao());
    }

    @Bean
    ProductDao productDao() {
        logger.debug("initialization of productDao");
        return new JdbcProductDao();
    }

    @Bean
    UserDao userDao() {
        logger.debug("initialization of userDao");
        return new JdbcUserDao();
    }
}
