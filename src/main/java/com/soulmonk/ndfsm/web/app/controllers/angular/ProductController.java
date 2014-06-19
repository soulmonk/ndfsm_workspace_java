package com.soulmonk.ndfsm.web.app.controllers.angular;

import com.soulmonk.ndfsm.beans.Product;
import com.soulmonk.ndfsm.service.angular.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/angular/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/productList.json")
    public @ResponseBody
    List<Product> getProductList() {
        return productService.getAllProducts();
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public @ResponseBody
    void addProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }
    
    @RequestMapping(value = "/updateProduct", method = RequestMethod.PUT)
    public @ResponseBody
    void updateProduct(@RequestBody Product product) {
    	productService.updateProduct(product);
    }

    @RequestMapping(value = "/removeProduct/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    void removeProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
    }

    @RequestMapping(value = "/removeAllProducts", method = RequestMethod.DELETE)
    public @ResponseBody
    void removeAllProducts() {
        productService.deleteAll();
    }

    @RequestMapping("/layout")
    public String getProductPartialPage() {
        return "angular/products/layout";
    }
}
