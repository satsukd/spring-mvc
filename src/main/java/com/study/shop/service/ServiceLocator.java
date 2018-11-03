package com.study.shop.service;

import com.study.shop.dao.ProductDao;
import com.study.shop.dao.jdbc.JdbcProductDao;

import java.util.HashMap;
import java.util.Map;

public class ServiceLocator {
    private static final Map<Class<?>, Object> SERVICES = new HashMap<>();

    static {
        // dao config
        ProductDao productDao = new JdbcProductDao();

        // service config
        DefaultProductService defaultProductService = new DefaultProductService();
        defaultProductService.setProductDao(productDao);
        SERVICES.put(ProductService.class, defaultProductService);
    }

    public static <T> T getService(Class<T> serviceClass) {
        return serviceClass.cast(SERVICES.get(serviceClass));
    }
}
