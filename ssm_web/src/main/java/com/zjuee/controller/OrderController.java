package com.zjuee.controller;

import com.github.pagehelper.PageInfo;
import com.zjuee.domain.Order;
import com.zjuee.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name="id", required = true) String id) {
        ModelAndView mv = new ModelAndView();
        Order order = orderService.findById(id);
        mv.addObject("order",order);
        mv.setViewName("orders-show");
        return mv;
    }

    /**
     * 查询全部订单
     * @return
     * @throws Exception
     */
//    @RequestMapping("/findAll")
    public List<Order> findAll() throws Exception {
        List<Order> orders = orderService.findAll();
        System.out.println(orders);
        return orders;
    }

    /**
     * 分页查询全部订单
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll")
    public ModelAndView findAllByPage(@RequestParam(name="page", required = true, defaultValue = "1") int pageNum,
                                      @RequestParam(name= "size", required = true, defaultValue = "5") int pageSize) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Order> orders = orderService.findByPage(pageNum, pageSize);
        // PageInfo就是一个分页Bean对象
        PageInfo<Order> pageInfo = new PageInfo<Order>();
        pageInfo.setList(orders);
        pageInfo.setPageNum(pageNum);
        pageInfo.setPageSize(pageSize);
        // 计算总记录数与总页数
        List<Order> allOrders = findAll();
        int size = allOrders.size();
        int pages = size % pageSize == 0 ? size / pageSize : size / pageSize + 1;
        pageInfo.setSize(size);
        pageInfo.setPages(pages);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-page-list");
        return mv;
    }
}
