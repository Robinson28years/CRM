package com.fourzeroeight.crm.mapper;

import com.fourzeroeight.crm.bean.Logs;
import com.fourzeroeight.crm.bean.Roles;
import com.fourzeroeight.crm.bean.Search;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface LogsMapper {
    @Delete({
        "delete from logs",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into logs (title, ",
        "descs, custid, createtime)",
        "values (#{title,jdbcType=VARCHAR}, ",
        "#{descs,jdbcType=VARCHAR}, #{custid,jdbcType=INTEGER}, #{createtime,jdbcType=TIMESTAMP})"
    })
    int insert(Logs record);

    int insertSelective(Logs record);

    @Select({
        "select",
        "id, title, descs, custid, createtime",
        "from logs",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    Logs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Logs record);

    @Update({
        "update logs",
        "set title = #{title,jdbcType=VARCHAR},",
          "descs = #{descs,jdbcType=VARCHAR},",
          "custid = #{custid,jdbcType=INTEGER},",
          "createtime = #{createtime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Logs record);

    @Select({
            "select",
            "id, title, descs, custid, createtime",
            "from logs",
    })
    @ResultMap("BaseResultMap")
    List<Logs> select();

    List<Logs> getListBySearch(Search search);

    int getCountBySearch(Search search);
}