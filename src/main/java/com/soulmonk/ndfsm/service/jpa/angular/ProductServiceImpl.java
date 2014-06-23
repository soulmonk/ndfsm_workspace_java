package com.soulmonk.ndfsm.service.jpa.angular;

import com.soulmonk.ndfsm.beans.Product;
import com.soulmonk.ndfsm.service.angular.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {
    private static List<Product> productList = new ArrayList<Product>();
    private static Long id = 0L;

    public List<Product> getAllProducts() {
        return productList;
    }

    public void addProduct(Product product) {
        product.setId(++id);
        productList.add(product);
    }

    public void deleteProduct(Long id) {
        Product foundProduct = findProductById(id);
        if (foundProduct != null) {
            productList.remove(foundProduct);
        }
    }

    public void deleteAll() {
        productList.clear();
    }

    public void updateProduct(Product product) {
        Product foundProduct = findProductById(product.getId());
        if (foundProduct != null) {
            productList.remove(foundProduct);
            productList.add(product);
        }
    }

    private Product findProductById(Long id) {
        for (Product product : productList) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
}
