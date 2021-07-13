package com.zjuee.service;

import com.zjuee.domain.Product;

import java.util.List;

public interface ProductService {

    /**
     * 查询所有产品
     * @return
     * @throws Exception
     */
    List<Product> findAll() throws Exception;

    /**
     * 添加产品
     * @param product
     */
    void save(Product product);

    /**
     * 删除产品
     * @param id
     */
    void delete(String id);
}
