package com.wen.test.controller;


import com.wen.test.mapper.UserMapper;
import com.wen.test.vo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {
    /**
     * 为了简单，也不做太多处理，就不写service层了。。。
     */
    @Autowired
    private UserMapper userMapper;

    /**
     * 查找所有用户信息（列名与实体属性名一致对的方法）
     * @return
     */
    @GetMapping(value = "findAllUsers")
    public List<Users> findAllUsers(){
        return userMapper.findAllUsers();
    }

    /**
     * 查找所有用户信息（列名与实体属性名不一致对的方法）
     * @return
     */
    @GetMapping(value = "find")
    public List<Users> findAllUsers1(){
        return userMapper.findAllUsers1();
    }

    /**
     * 一对多注解形式体现
     * @param id
     * @return
     */
    @GetMapping(value = "all")
    public Users findAll(Integer id){
        return userMapper.getUsersAndLists(id);
    }


}
