package com.fourzeroeight.crm.mapper;

import com.fourzeroeight.crm.bean.Logs;
import org.apache.ibatis.annotations.*;

public interface LogsMapper {
    @Delete({
        "delete from logs",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into logs (title, ",
        "desc, custid, createtime)",
        "values (#{title,jdbcType=VARCHAR}, ",
        "#{desc,jdbcType=VARCHAR}, #{custid,jdbcType=INTEGER}, #{createtime,jdbcType=TIMESTAMP})"
    })
    int insert(Logs record);

    int insertSelective(Logs record);

    @Select({
        "select",
        "id, title, desc, custid, createtime",
        "from logs",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    Logs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Logs record);

    @Update({
        "update logs",
        "set title = #{title,jdbcType=VARCHAR},",
          "desc = #{desc,jdbcType=VARCHAR},",
          "custid = #{custid,jdbcType=INTEGER},",
          "createtime = #{createtime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Logs record);
}