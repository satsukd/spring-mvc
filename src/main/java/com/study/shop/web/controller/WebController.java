package com.study.shop.web.controller;

import com.study.shop.entity.Product;
import com.study.shop.security.SecurityService;
import com.study.shop.security.entity.Session;
import com.study.shop.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class WebController {
    private static final Logger logger = LoggerFactory.getLogger(WebController.class);
    @Autowired
    private ProductService productService;
    @Autowired
    private SecurityService securityService;

    @RequestMapping(path = {"/", "/products"}, method = RequestMethod.GET)
    public String getAll(Model model) {
        List<Product> products = productService.getAll();
        model.addAttribute("products", products);
        return "products";
    }


    @RequestMapping(path = "/product/add", method = RequestMethod.GET)
    public String addProductPage() {
        return "add";
    }

    @RequestMapping(path = "/product/add", method = RequestMethod.POST)
    public String addProduct(@RequestParam String name, @RequestParam double price, @RequestParam String picturePath) {
        Product product = new Product(name, price, picturePath);
        int id = productService.add(product);

        System.out.println("Product with id: " + id + " was created!");
        return "redirect:/products";
    }

    @RequestMapping(path = "/product/edit/{id}", method = RequestMethod.GET)
    public String editProductPage(@PathVariable int id, Model model) {
        Product product = productService.getById(id);
        model.addAttribute("product", product);
        return "edit";
    }

    @RequestMapping(path = "/product/edit", method = RequestMethod.POST)
    public String addProduct(@RequestParam int id, @RequestParam String name, @RequestParam double price, @RequestParam String picturePath) {
        Product product = new Product(id, name, price, picturePath);
        productService.update(product);
        return "redirect:/products";
    }

    @RequestMapping(path = "/product/delete/{id}", method = RequestMethod.POST)
    public String deleteProduct(@PathVariable int id) {
        productService.delete(id);
        return "redirect:/products";
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(@RequestParam String name, @RequestParam String password, HttpServletResponse response) {
        Session session = securityService.auth(name, password);
        if (session != null) {
            response.addCookie(new Cookie("user-token", session.getToken()));
            return "redirect:/";
        } else {
            return "redirect:/login";
        }
    }
}
