package com.wismap.springsecuritydemo.mapper;

import com.wismap.springsecuritydemo.model.PAC;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PACMapper {

    PAC selectByPAC(String pac);

    List<PAC> selectByName(String name);

    int delete(String pac);

    int insert(PAC record);

    int update(PAC record);

    Integer SelectLevel(String pac);
}