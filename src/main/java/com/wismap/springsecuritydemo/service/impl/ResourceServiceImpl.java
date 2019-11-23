package com.wismap.springsecuritydemo.service.impl;

import com.wismap.springsecuritydemo.mapper.PrivilegeMapper;
import com.wismap.springsecuritydemo.mapper.Ref_Privilege_ResourceMapper;
import com.wismap.springsecuritydemo.mapper.ResourceMapper;
import com.wismap.springsecuritydemo.model.Privilege;
import com.wismap.springsecuritydemo.model.Ref_Privilege_Resource;
import com.wismap.springsecuritydemo.model.Resource;
import com.wismap.springsecuritydemo.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceImpl implements IResourceService {

    @Autowired
    private PrivilegeMapper privilegeMapper;

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private Ref_Privilege_ResourceMapper ref_privilege_resourceMapper;

    public List<Resource> selectAll()
    {
        return resourceMapper.selectAll();
    }

    public Boolean insert(Resource resource)
    {
        //新建resource
        Long ResNum = resourceMapper.insert(resource);
        //新建privilege
        Privilege privilege = new Privilege();
        privilege.setPritype(1L);
        Long PriNum = privilegeMapper.insert(privilege);
        //新建关系
        Ref_Privilege_Resource ref_privilege_resource=new Ref_Privilege_Resource(privilege.getId(),resource.getId());
        ref_privilege_resourceMapper.insert(ref_privilege_resource);

        return true;
    }
}
