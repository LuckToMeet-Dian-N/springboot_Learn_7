package com.wen.test.mapper;

import com.wen.test.vo.Lists;
import com.wen.test.vo.Users;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {
    /**
     * 最低级的使用。保持实体属性名和数据库列名一致
     *
     * @return
     */

    @Select("select * from user")
    List<Users> findAllUsers();

    /**
     * 高级一点的操作，
     * 实体的属性名可以与数据库列名不一致进行映射
     *
     * @return
     */

    @Results({@Result(id = true, property = "user_id", column = "user_id", javaType = Integer.class),
            @Result(property = "phone", column = "phone", javaType = String.class),
            @Result(property = "password", column = "password", javaType = String.class)
    })
    @Select("select * from user")
    List<Users> findAllUsers1();

    /**
     * 注解形式一对多
     *
     * @param user_id
     * @return
     */

    @Results({
            @Result(id = true, column = "user_id", property = "user_id"),
            @Result(column = "phone", property = "phone"),
            @Result(column = "password", property = "password"),
            @Result(column = "user_id", property = "lists", javaType = List.class,
                    many = @Many(
                            select = "com.wen.test.mapper.UserMapper.selectAllByUserId"
                    )
            )
    })
    @Select("select user.user_id,user.phone,user.password from user where  user.user_id=#{user_id}")
    Users getUsersAndLists(@Param("user_id") Integer user_id);


    /**
     * 被引用查询多个对象的方法
     *
     * @param user_id
     * @return
     */
    @Select("select list.lists,list.ids from list where list.user_id=#{user_id}")
    @Results({
            @Result(id = true, column = "ids", property = "id"),
            @Result(column = "lists", property = "lists")
    })
    List<Lists> selectAllByUserId(@Param("user_id") Integer user_id);

    /**
     * 一对一的话，用的是one =@One()这个注解。。
     * 在写一个查询单个的接口。就可以了。。这里就不写了。。持续懒惰中。。。。
     */


}
