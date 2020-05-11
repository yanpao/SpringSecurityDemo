package com.wismap.springsecuritydemo.mapper;

import com.wismap.springsecuritydemo.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    int delete(String loginname);

    Integer insert(User record);

    Integer selectAllCount(String name);

    List<User> selectAll(String name, Long limit, Long offset);

    User select(String loginname);

    int update(User record);
}