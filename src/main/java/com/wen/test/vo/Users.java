package com.wen.test.vo;

import lombok.Data;

import java.util.List;

@Data
public class Users  {

    private Integer user_id;
    private String phone;
    private String password;
    /**
     * 一对多
     */
    private  List<Lists> lists;


}
