package com.broker.service;

import com.broker.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProduct();

    Integer addProduct(Product product);
}
