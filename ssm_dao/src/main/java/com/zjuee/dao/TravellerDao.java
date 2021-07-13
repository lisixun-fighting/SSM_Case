package com.zjuee.dao;

import com.zjuee.domain.Traveller;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("TravellerDao")
public interface TravellerDao {

    @Select("select " +
            "  t2.* " +
            "from " +
            "  order_traveller t1, traveller t2 " +
            "where " +
            "  t1.orderId = #{value} " +
            "  and " +
            "  t1.travellerId = t2.id ")
    List<Traveller> findByOid(String oid);
}
