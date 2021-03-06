package com.wismap.springsecuritydemo.service;

import com.wismap.springsecuritydemo.model.Resource;

import java.util.List;

public interface IResourceService {

    List<Resource> selectAll();

    Boolean insert(Resource resource);

    Boolean delete(Long resourceid);

    Resource update(Resource resource)throws Exception;

}
