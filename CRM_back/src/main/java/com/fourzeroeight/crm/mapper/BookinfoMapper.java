package com.fourzeroeight.crm.mapper;

import com.fourzeroeight.crm.bean.Bookinfo;
import org.apache.ibatis.annotations.*;

public interface BookinfoMapper {
    @Delete({
        "delete from bookinfo",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into bookinfo (id, title, ",
        "desc, booktime, ",
        "createtime)",
        "values (#{id,jdbcType=INTEGER}, #{title,jdbcType=VARCHAR}, ",
        "#{desc,jdbcType=INTEGER}, #{booktime,jdbcType=TIMESTAMP}, ",
        "#{createtime,jdbcType=TIMESTAMP})"
    })
    int insert(Bookinfo record);

    int insertSelective(Bookinfo record);

    @Select({
        "select",
        "id, title, desc, booktime, createtime",
        "from bookinfo",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    Bookinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Bookinfo record);

    @Update({
        "update bookinfo",
        "set title = #{title,jdbcType=VARCHAR},",
          "desc = #{desc,jdbcType=INTEGER},",
          "booktime = #{booktime,jdbcType=TIMESTAMP},",
          "createtime = #{createtime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Bookinfo record);
}