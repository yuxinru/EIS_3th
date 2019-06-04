package com.broker.serviceImpl;

import com.broker.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Resource
    ProductServiceImpl productService;
    @Test
    public void getAllProduct() {
        productService.getAllProduct();
    }

    @Test
    public void addProduct() {
        Product product = new Product();
        product.setName("黄金");
        product.setIntroduction("这是24k纯金");
        productService.addProduct(product);
    }
}