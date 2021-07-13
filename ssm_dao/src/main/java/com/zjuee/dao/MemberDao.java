package com.zjuee.dao;

import com.zjuee.domain.Member;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository("MemberDao")
public interface MemberDao {

    @Select("select * from member")
    Member findById(String id);
}
