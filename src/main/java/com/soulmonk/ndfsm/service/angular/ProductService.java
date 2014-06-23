package com.soulmonk.ndfsm.service.angular;

import com.soulmonk.ndfsm.beans.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProducts();

    public void addProduct(Product product);

    public void deleteProduct(Long id);

    public void deleteAll();

    public void updateProduct(Product product);
}
