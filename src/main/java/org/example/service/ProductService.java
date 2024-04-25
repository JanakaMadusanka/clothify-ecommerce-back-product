package org.example.service;

import org.example.dto.Product;

import java.util.List;

public interface ProductService {
    void addProduct(Product product);
    boolean updateProduct(Product product);
    boolean deleteProduct(Long id);
    List<Product> getAllProduct();
    Product searchProductById(Long id);
}
