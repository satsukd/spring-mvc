package com.study.shop.dao.jdbc;

import com.study.shop.dao.ProductDao;
import com.study.shop.dao.jdbc.mapper.ProductRowMapper;
import com.study.shop.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;


public class JdbcProductDao implements ProductDao {
    private static final Logger logger = LoggerFactory.getLogger(JdbcProductDao.class);
    private static final ProductRowMapper PRODUCT_ROW_MAPPER = new ProductRowMapper();
    private static final String SQL_GET_ALL_PRODUCTS = "SELECT id, name, price, add_date, picture_path FROM product";
    private static final String SQL_GET_PRODUCT_BY_ID = "SELECT id, name, price, add_date, picture_path FROM product WHERE id = ?";
    private static final String SQL_UPDATE_PRODUCT_BY_ID = "UPDATE product SET name = ?, price = ?, add_date = ?, picture_path = ? WHERE id = ?";
    private static final String SQL_DELETE_PRODUCT_BY_ID = "DELETE FROM product WHERE id = ?";
    private static final String SQL_INSERT_PRODUCT = "INSERT INTO product (id, name, price, picture_path) VALUES(DEFAULT, ?, ?, ?)";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Product> getAll() {
        return jdbcTemplate.query(SQL_GET_ALL_PRODUCTS, PRODUCT_ROW_MAPPER);
    }

    @Override
    public Product getById(int id) {
        return jdbcTemplate.queryForObject(SQL_GET_PRODUCT_BY_ID, new Object[]{id}, PRODUCT_ROW_MAPPER);
    }

    @Override
    public void update(Product product) {
        jdbcTemplate.update(SQL_UPDATE_PRODUCT_BY_ID, product.getName(), product.getPrice(), new java.sql.Date(new Date().getTime()), product.getPicturePath(), product.getId());
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update(SQL_DELETE_PRODUCT_BY_ID, id);
    }

    @Override
    public int add(Product product) {
        KeyHolder key = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                final PreparedStatement ps = connection.prepareStatement(SQL_INSERT_PRODUCT,
                        Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, product.getName());
                ps.setDouble(2, product.getPrice());
                ps.setString(3, product.getPicturePath());
                return ps;
            }
        }, key);

        return (int) key.getKeys().get("id");
    }
}
