package com.wismap.springsecuritydemo.service.impl;

import com.wismap.springsecuritydemo.mapper.PrivilegeMapper;
import com.wismap.springsecuritydemo.mapper.Ref_Privilege_ResourceMapper;
import com.wismap.springsecuritydemo.mapper.ResourceMapper;
import com.wismap.springsecuritydemo.model.Privilege;
import com.wismap.springsecuritydemo.model.Ref_Privilege_Resource;
import com.wismap.springsecuritydemo.model.Resource;
import com.wismap.springsecuritydemo.service.IResourceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceImpl implements IResourceService {

    private PrivilegeMapper privilegeMapper;
    private ResourceMapper resourceMapper;
    private Ref_Privilege_ResourceMapper ref_privilege_resourceMapper;

    public ResourceServiceImpl(PrivilegeMapper privilegeMapper,
                               ResourceMapper resourceMapper,
                               Ref_Privilege_ResourceMapper ref_privilege_resourceMapper){
        this.privilegeMapper=privilegeMapper;
        this.resourceMapper=resourceMapper;
        this.ref_privilege_resourceMapper=ref_privilege_resourceMapper;
    }

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

    public Boolean delete(Long resourceid)
    {
        Long deleteCount=0L;
        Ref_Privilege_Resource ref_privilege_resource=ref_privilege_resourceMapper.selectByResid(resourceid);
        deleteCount+=privilegeMapper.delete(ref_privilege_resource.getPriId());
        deleteCount+=ref_privilege_resourceMapper.delete(ref_privilege_resource.getPriId(),resourceid);
        deleteCount+=resourceMapper.delete(resourceid);
        if (deleteCount>0)
            return true;
        else
            return false;
    }

    public Resource update(Resource resource) throws Exception{
        if (resource.getId()==resource.getPid())
            throw new Exception("id不可以和pid相同");
        resourceMapper.update(resource);
        return resourceMapper.selectByUrl(resource.getUrl());
    }
}
