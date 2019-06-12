package com.broker.serviceImpl;

import com.broker.dao.ProductDAO;
import com.broker.entity.Product;
import com.broker.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("ProductService")
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Resource
    ProductDAO productDAO;

    @Override
    public List<Product> getAllProduct(){
        log.info("#########  info  #########");
        log.debug("#########  debug  #########");
        log.error("#########  error  #########");
        return  null;
    }

    @Override
    public Integer addProduct(Product product){

        productDAO.insert(product);
        return 1;
    }
}
