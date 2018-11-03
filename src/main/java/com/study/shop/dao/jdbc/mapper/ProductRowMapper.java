package com.study.shop.dao.jdbc.mapper;

import com.study.shop.entity.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        Product product = new Product();

        String name = resultSet.getString("name");
        int id = resultSet.getInt("id");
        double price = resultSet.getDouble("price");
        String picturePath = resultSet.getString("picture_path");
        Timestamp timestamp = resultSet.getTimestamp("add_date");
        if (timestamp != null) {
            LocalDateTime addDate = timestamp.toLocalDateTime();
            product.setAddDate(addDate);
        }

        product.setId(id);
        product.setName(name);
        product.setPrice(price);
        product.setPicturePath(picturePath);

        return product;
    }
}
