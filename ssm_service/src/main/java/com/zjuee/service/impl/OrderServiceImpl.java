package com.zjuee.service.impl;


import com.github.pagehelper.PageHelper;
import com.zjuee.dao.OrderDao;
import com.zjuee.domain.Order;
import com.zjuee.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("OrderService")
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public Order findById(String id) {
        return orderDao.findById(id);
    }

    @Override
    public List<Order> findAll() throws Exception {
        return orderDao.findAll();
    }

    @Override
    public List<Order> findByPage(int page, int size) throws Exception {
        // 参数pageNum是页码值，参数pageSize代表每页显示条数
        PageHelper.startPage(page,size);
        return orderDao.findAll();
    }
}
