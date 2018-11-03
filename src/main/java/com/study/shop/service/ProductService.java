package com.study.shop.service;

import com.study.shop.entity.Product;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductService {
    List<Product> getAll();

    Product getById(int id);

    void update(Product product);

    void delete(int id);

    int add(Product product);
}
