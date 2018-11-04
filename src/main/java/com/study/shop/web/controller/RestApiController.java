package com.study.shop.web.controller;

import com.study.shop.entity.Product;
import com.study.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestApiController {

    @Autowired
    private ProductService productService;

    @RequestMapping(path = {"/api/v1/products"}, method = RequestMethod.GET)
    public List<Product> getAll() {
        List<Product> products = productService.getAll();
        return products;
    }

    @RequestMapping(path = {"/api/v1/product/{id}"}, method = RequestMethod.GET)
    public Product getProductById(@PathVariable int id) {
        Product product = productService.getById(id);
        return product;
    }

    @RequestMapping(path = {"/api/v1/product"}, method = RequestMethod.POST)
    public void addProduct(@RequestParam String name, @RequestParam double price, @RequestParam String picturePath) {
        Product product = new Product(name, price, picturePath);
        productService.add(product);
    }


    @RequestMapping(path = {"/api/v1/product/{id}"}, method = RequestMethod.PUT)
    public void updateProduct(@RequestParam int id, @RequestParam String name, @RequestParam double price, @RequestParam String picturePath) {
        Product product = new Product(id, name, price, picturePath);
        productService.update(product);
    }


    @RequestMapping(path = {"/api/v1/product/{id}"}, method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable int id) {
        productService.delete(id);
    }
}
