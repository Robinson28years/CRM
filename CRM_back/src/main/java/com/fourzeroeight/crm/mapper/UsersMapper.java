package com.fourzeroeight.crm.mapper;

import com.fourzeroeight.crm.bean.Users;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface UsersMapper {
    @Delete({
        "delete from users",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into users (username, ",
        "password, sex, birthday, ",
        "roleid, status)",
        "values (#{username,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=VARCHAR}, #{sex,jdbcType=VARCHAR}, #{birthday,jdbcType=TIMESTAMP}, ",
        "#{roleid,jdbcType=INTEGER}, #{status,jdbcType=INTEGER})"
    })
    int insert(Users record);

    int insertSelective(Users record);

    @Select({
        "select",
        "id, username, password, sex, birthday, roleid, status",
        "from users",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("BaseResultMap")
    Users selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Users record);

    @Update({
        "update users",
        "set username = #{username,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "sex = #{sex,jdbcType=VARCHAR},",
          "birthday = #{birthday,jdbcType=TIMESTAMP},",
          "roleid = #{roleid,jdbcType=INTEGER},",
          "status = #{status,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Users record);

    @Select({
            "select",
            "id, username, password, sex, birthday, roleid, status",
            "from users",
    })
    @ResultMap("BaseResultMap")
    List<Users> select();
}