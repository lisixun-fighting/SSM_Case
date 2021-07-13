package com.zjuee.dao;

import com.zjuee.domain.Order;
import com.zjuee.domain.Product;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 持久层
 */
@Repository("OrderDao")
public interface OrderDao {

    /**
     * 查询所有订单
     * 多表查询：根据productId查询Product表中对应的Product数据
     * @return
     */
    @Select("select * from orders")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "product", column = "productId", one = @One(select = "com.zjuee.dao.ProductDao.findById"))
    })
    List<Order> findAll() throws Exception;

    /**
     * 根据id查询订单详情
     * @param id
     * @return
     */
    @Select("select * from orders where id = #{value}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "product", column = "productId", one = @One(select = "com.zjuee.dao.ProductDao.findById")),
            @Result(property = "member", column = "memberId", one = @One(select = "com.zjuee.dao.MemberDao.findById")),
            @Result(property = "travellers", column = "id", many = @Many(select = "com.zjuee.dao.TravellerDao.findByOid"))
    })
    Order findById(String id);
}
