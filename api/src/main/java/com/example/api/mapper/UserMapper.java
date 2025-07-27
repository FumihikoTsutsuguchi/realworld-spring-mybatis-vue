package com.example.api.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.api.model.User;

@Mapper
public interface UserMapper {

    int insert(User user);                 // users に INSERT して id を返す
    User findByUsername(String username);  // username で 1 件取得
}