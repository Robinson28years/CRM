package com.fourzeroeight.crm.mapper;

import com.fourzeroeight.crm.bean.Files;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FilesMapper {
    @Delete({
        "delete from files",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into files (id, url, ",
        "name, title, descs, ",
        "type, createtime, ",
        "custid)",
        "values (#{id,jdbcType=INTEGER}, #{url,jdbcType=VARCHAR}, ",
        "#{name,jdbcType=VARCHAR}, #{title,jdbcType=VARCHAR}, #{descs,jdbcType=VARCHAR}, ",
        "#{type,jdbcType=VARCHAR}, #{createtime,jdbcType=TIMESTAMP}, ",
        "#{custid,jdbcType=INTEGER})"
    })
    int insert(Files record);

    int insertSelective(Files record);

    @Select({
        "select",
        "id, url, name, title, descs, type, createtime, custid",
        "from files",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    Files selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Files record);

    @Update({
        "update files",
        "set url = #{url,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "title = #{title,jdbcType=VARCHAR},",
          "descs = #{descs,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=VARCHAR},",
          "createtime = #{createtime,jdbcType=TIMESTAMP},",
          "custid = #{custid,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Files record);

    @Select({
            "select",
            "id, url, name, title, descs, type, createtime, custid",
            "from files",
    })
    @ResultMap("BaseResultMap")
    List<Files> select();
}