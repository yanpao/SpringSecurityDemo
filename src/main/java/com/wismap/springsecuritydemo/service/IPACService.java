package com.wismap.springsecuritydemo.service;

import com.wismap.springsecuritydemo.model.PAC;

import java.util.List;

public interface IPACService {

    PAC selectByPAC(String pac);

    List<PAC> selectByName(String name);

    int delete(String pac);

    PAC insert(PAC record)throws Exception;

    PAC update(PAC record);

}
