package com.study.shop.web.controller;

import com.study.shop.entity.Product;
import com.study.shop.security.SecurityService;
import com.study.shop.security.entity.Session;
import com.study.shop.service.ProductService;
import com.study.shop.web.templater.PageGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Controller
public class WebController {

    @Autowired
    private ProductService productService;

    @Autowired
    private SecurityService securityService;


    @ResponseBody
    @RequestMapping(path = {"/", "/products"}, method = RequestMethod.GET)
    public String getAll() {
        List<Product> products = productService.getAll();

        HashMap<String, Object> params = new HashMap<>();
        params.put("products", products);

        PageGenerator pageGenerator = PageGenerator.instance();
        String page = pageGenerator.getPage("products.html", params);

        return page;
    }

    @ResponseBody
    @RequestMapping(path = "/product/add", method = RequestMethod.GET)
    public String addProductPage() {
        PageGenerator pageGenerator = PageGenerator.instance();
        String page = pageGenerator.getPage("addProduct.html");

        return page;
    }

    @RequestMapping(path = "/product/add", method = RequestMethod.POST)
    public String addProduct(@RequestParam String name, @RequestParam double price, @RequestParam String picturePath) {
        int id = productService.add(name, price, picturePath);

        System.out.println("Product with id: " + id + " was created!");
        return "redirect:/products";
    }

    @ResponseBody
    @RequestMapping(path = "/product/edit/{id}", method = RequestMethod.GET)
    public String editProductPage(@PathVariable int id) {
        PageGenerator pageGenerator = PageGenerator.instance();
        HashMap<String, Object> params = new HashMap<>();
        Product product = productService.getById(id);
        params.put("product", product);

        String page = pageGenerator.getPage("editProduct.html", params);

        return page;
    }

    @RequestMapping(path = "/product/edit", method = RequestMethod.POST)
    public String addProduct(@RequestParam int id, @RequestParam String name, @RequestParam double price, @RequestParam String picturePath) {

        productService.update(id, name, price, LocalDateTime.now(), picturePath);

        System.out.println("Product with id: " + id + " was edited!");
        return "redirect:/products";
    }

    @RequestMapping(path = "/product/delete/{id}", method = RequestMethod.POST)
    public String deleteProduct(@PathVariable int id) {
        productService.delete(id);

        System.out.println("Product with id: " + id + " was deleted!");

        return "redirect:/products";
    }

    @ResponseBody
    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String loginPage() {
        PageGenerator pageGenerator = PageGenerator.instance();
        String page = pageGenerator.getPage("login.html");

        return page;
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
