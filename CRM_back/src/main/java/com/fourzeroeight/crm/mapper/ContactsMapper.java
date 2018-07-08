package com.fourzeroeight.crm.mapper;

import com.fourzeroeight.crm.bean.Contacts;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ContactsMapper {
    @Delete({
        "delete from contacts",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into contacts (custid, ",
        "name, dept, position, ",
        "telephone, mobile, ",
        "email, qq, degree, ",
        "moreinfo, createtime, ",
        "bookid, title)",
        "values (#{custid,jdbcType=INTEGER}, ",
        "#{name,jdbcType=VARCHAR}, #{dept,jdbcType=VARCHAR}, #{position,jdbcType=VARCHAR}, ",
        "#{telephone,jdbcType=VARCHAR}, #{mobile,jdbcType=VARCHAR}, ",
        "#{email,jdbcType=VARCHAR}, #{qq,jdbcType=VARCHAR}, #{degree,jdbcType=INTEGER}, ",
        "#{moreinfo,jdbcType=VARCHAR}, #{createtime,jdbcType=TIMESTAMP}, ",
        "#{bookid,jdbcType=INTEGER}, #{title,jdbcType=INTEGER})"
    })
    int insert(Contacts record);

    int insertSelective(Contacts record);

    @Select({
        "select",
        "id, custid, name, dept, position, telephone, mobile, email, qq, degree, moreinfo, ",
        "createtime, bookid, title",
        "from contacts",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    Contacts selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Contacts record);

    @Update({
        "update contacts",
        "set custid = #{custid,jdbcType=INTEGER},",
          "name = #{name,jdbcType=VARCHAR},",
          "dept = #{dept,jdbcType=VARCHAR},",
          "position = #{position,jdbcType=VARCHAR},",
          "telephone = #{telephone,jdbcType=VARCHAR},",
          "mobile = #{mobile,jdbcType=VARCHAR},",
          "email = #{email,jdbcType=VARCHAR},",
          "qq = #{qq,jdbcType=VARCHAR},",
          "degree = #{degree,jdbcType=INTEGER},",
          "moreinfo = #{moreinfo,jdbcType=VARCHAR},",
          "createtime = #{createtime,jdbcType=TIMESTAMP},",
          "bookid = #{bookid,jdbcType=INTEGER},",
          "title = #{title,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Contacts record);

    @Select({
            "select",
            "id, custid, name, dept, position, telephone, mobile, email, qq, degree, moreinfo, ",
            "createtime, bookid, title",
            "from contacts"
    })
    @ResultMap("BaseResultMap")
    List<Contacts> select();
}