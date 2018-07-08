package com.fourzeroeight.crm.mapper;

import com.fourzeroeight.crm.bean.Roles;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface RolesMapper {
    @Delete({
        "delete from roles",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into roles (name, ",
        "descs)",
        "values (#{name,jdbcType=VARCHAR}, ",
        "#{desc,jdbcType=VARCHAR})"
    })
    int insert(Roles record);

    int insertSelective(Roles record);

    @Select({
        "select",
        "id, name, descs",
        "from roles",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    Roles selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Roles record);

    @Update({
        "update roles",
        "set name = #{name,jdbcType=VARCHAR},",
          "descs = #{descs,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Roles record);

    @Select({
            "select",
            "id, name, descs",
            "from roles",
    })
    @ResultMap("BaseResultMap")
    List<Roles> select();
}