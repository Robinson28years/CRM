package com.fourzeroeight.crm.mapper;

import com.fourzeroeight.crm.bean.Customers;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CustomersMapper {
    @Delete({
        "delete from customers",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into customers (name, ",
        "type, background, ",
        "listed, capital, ",
        "sales, scale, tester, ",
        "url, zipcode, address, ",
        "products, services, ",
        "userid, NewColumn1, ",
        "createtime)",
        "values (#{name,jdbcType=VARCHAR}, ",
        "#{type,jdbcType=INTEGER}, #{background,jdbcType=INTEGER}, ",
        "#{listed,jdbcType=INTEGER}, #{capital,jdbcType=INTEGER}, ",
        "#{sales,jdbcType=INTEGER}, #{scale,jdbcType=INTEGER}, #{tester,jdbcType=INTEGER}, ",
        "#{url,jdbcType=VARCHAR}, #{zipcode,jdbcType=VARCHAR}, #{address,jdbcType=VARCHAR}, ",
        "#{products,jdbcType=VARCHAR}, #{services,jdbcType=VARCHAR}, ",
        "#{userid,jdbcType=INTEGER}, #{newcolumn1,jdbcType=INTEGER}, ",
        "#{createtime,jdbcType=TIMESTAMP})"
    })
    int insert(Customers record);

    int insertSelective(Customers record);

    @Select({
        "select",
        "id, name, type, background, listed, capital, sales, scale, tester, url, zipcode, ",
        "address, products, services, userid, NewColumn1, createtime",
        "from customers",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    Customers selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Customers record);

    @Update({
        "update customers",
        "set name = #{name,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=INTEGER},",
          "background = #{background,jdbcType=INTEGER},",
          "listed = #{listed,jdbcType=INTEGER},",
          "capital = #{capital,jdbcType=INTEGER},",
          "sales = #{sales,jdbcType=INTEGER},",
          "scale = #{scale,jdbcType=INTEGER},",
          "tester = #{tester,jdbcType=INTEGER},",
          "url = #{url,jdbcType=VARCHAR},",
          "zipcode = #{zipcode,jdbcType=VARCHAR},",
          "address = #{address,jdbcType=VARCHAR},",
          "products = #{products,jdbcType=VARCHAR},",
          "services = #{services,jdbcType=VARCHAR},",
          "userid = #{userid,jdbcType=INTEGER},",
          "NewColumn1 = #{newcolumn1,jdbcType=INTEGER},",
          "createtime = #{createtime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Customers record);

    @Select({
            "select",
            "id, name, type, background, listed, capital, sales, scale, tester, url, zipcode, ",
            "address, products, services, userid, NewColumn1, createtime",
            "from customers"
    })
    @ResultMap("BaseResultMap")
    List<Customers> select();
}