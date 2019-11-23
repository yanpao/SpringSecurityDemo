package com.wismap.springsecuritydemo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wismap.springsecuritydemo.mapper.Ref_Privilege_RoleMapper;
import com.wismap.springsecuritydemo.mapper.ResourceMapper;
import com.wismap.springsecuritydemo.mapper.RoleMapper;
import com.wismap.springsecuritydemo.model.Ref_Privilege_Role;
import com.wismap.springsecuritydemo.model.Resource;
import com.wismap.springsecuritydemo.model.Role;
import com.wismap.springsecuritydemo.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private Ref_Privilege_RoleMapper ref_privilege_roleMapper;


    public JSONObject selectByRoleName(String roleName)
    {
        Role role = roleMapper.selectByRoleName(roleName);
        JSONObject result = (JSONObject)JSON.toJSON(role);

        List<Long> allPriIds = ref_privilege_roleMapper.selectPriByRoleid(role.getId());
        if (allPriIds.size()>0)
        {
            List<Resource> allResource = resourceMapper.selectByIds(allPriIds.toArray(new Long[allPriIds.size()]));
            result.put("resource",allResource);
        }
        return result;
    }

    public List<Role> selectALL()
    {
        return roleMapper.selectALL();
    }

    public Long insert(Role record)
    {
        return roleMapper.insert(record);
    }

    public Long delete(Long id)
    {
        return roleMapper.delete(id);
    }

    public Boolean Authorize(Long roleID,Long[] privilegeIds)
    {
        try{
            for(Long priID : privilegeIds)
            {
                Ref_Privilege_Role ref_privilege_role=new Ref_Privilege_Role(priID,roleID);
                ref_privilege_roleMapper.insert(ref_privilege_role);
            }
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
    }

}
