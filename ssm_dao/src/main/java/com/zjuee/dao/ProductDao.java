package com.zjuee.dao;

import com.zjuee.domain.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ProductDao")
public interface ProductDao {

    /**
     * 查询所有产品
     * @return
     * @throws Exception
     */
    @Select("select * from product")
    public List<Product> findAll() throws Exception;

    /**
     * 保存产品
     * @param product
     */
    @Insert("insert into product (productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) " +
            " values (#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);

    /**
     * 根据id删除产品
     * @param id
     */
    @Delete("delete from product where id = #{value}")
    void delete(String id);

    /**
     * 根据id查询产品
     * @param id
     * @return
     */
    @Select("select * from product where id = #{value}")
    Product findById(String id);
}
