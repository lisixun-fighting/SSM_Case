package com.zjuee.service;

import com.zjuee.domain.Order;

import java.util.List;

public interface OrderService {

    /**
     * 根据id查询订单
     * @param id
     * @return
     */
    Order findById(String id);

    /**
     * 查询所有订单
     * @return
     */
    List<Order> findAll() throws Exception;

    /**
     * 分页查询所有订单
     * @param page
     * @param size
     * @return
     */
    List<Order> findByPage(int page, int size) throws Exception;
}
