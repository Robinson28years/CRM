package com.fourzeroeight.crm.mapper;

import com.fourzeroeight.crm.bean.Bookinfo;
import com.fourzeroeight.crm.bean.Search;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface BookinfoMapper {
    @Delete({
        "delete from bookinfo",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into bookinfo (title, ",
        "descs, booktime, ",
        "createtime, custid)",
        "values (#{title,jdbcType=VARCHAR}, ",
        "#{desc,jdbcType=INTEGER}, #{booktime,jdbcType=TIMESTAMP}, ",
        "#{createtime,jdbcType=TIMESTAMP},#{custid,jdbcType=INTEGER})"
    })
    int insert(Bookinfo record);

    int insertSelective(Bookinfo record);

    @Select({
        "select",
        "id, title, descs, booktime, createtime, custid",
        "from bookinfo",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    Bookinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Bookinfo record);

    @Update({
        "update bookinfo",
        "set title = #{title,jdbcType=VARCHAR},",
          "descs = #{descs,jdbcType=INTEGER},",
          "booktime = #{booktime,jdbcType=TIMESTAMP},",
          "createtime = #{createtime,jdbcType=TIMESTAMP}",
          "custid = #{custid,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Bookinfo record);

    @Select({
            "select",
            "id, title, descs, booktime, createtime, custid",
            "from bookinfo"
    })
    @ResultMap("BaseResultMap")
    List<Bookinfo> select();

    List<Bookinfo> getListBySearch(Search search);

    int getCountBySearch(Search search);
}